package mod.krevik.block;

import mod.krevik.KCore;
import mod.krevik.world.dimension.KathairisDataStorage;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.Random;

public class BlockLayeredSand extends BaseBlock {

    public static final PropertyInteger LAYERS = PropertyInteger.create("layers", 1, 8);
    protected static final AxisAlignedBB[] SAND_AABB = new AxisAlignedBB[] {new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.0D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.125D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.25D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.375D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.5D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.625D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.75D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.875D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D)};

    public BlockLayeredSand(String Name, CreativeTabs tab){
        super(Name,Material.SAND,tab,1f,1f,SoundType.SAND);
        this.setDefaultState(this.blockState.getBaseState().withProperty(LAYERS, Integer.valueOf(1)));
        this.setTickRandomly(true);
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        return SAND_AABB[state.getValue(LAYERS).intValue()];
    }

    @Override
    public boolean isPassable(IBlockAccess worldIn, BlockPos pos)
    {
        return worldIn.getBlockState(pos).getValue(LAYERS).intValue() < 5;
    }

    @Override
    public boolean isTopSolid(IBlockState state)
    {
        return state.getValue(LAYERS).intValue() == 8;
    }

    @Override
    public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face)
    {
        return face == EnumFacing.DOWN ? BlockFaceShape.SOLID : BlockFaceShape.UNDEFINED;
    }

    @Nullable
    @Override
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos)
    {
        int i = blockState.getValue(LAYERS).intValue() - 1;
        float f = 0.125F;
        AxisAlignedBB axisalignedbb = blockState.getBoundingBox(worldIn, pos);
        return new AxisAlignedBB(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ, axisalignedbb.maxX, (double)((float)i * 0.125F), axisalignedbb.maxZ);
    }

    @Override
    public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }

    @Override
    public boolean isFullCube(IBlockState state)
    {
        return false;
    }

    @Override
    public boolean canPlaceBlockAt(World worldIn, BlockPos pos)
    {
        IBlockState iblockstate = worldIn.getBlockState(pos.down());
        Block block = iblockstate.getBlock();

        if (block != Blocks.ICE && block != Blocks.PACKED_ICE && block != Blocks.BARRIER)
        {
            BlockFaceShape blockfaceshape = iblockstate.getBlockFaceShape(worldIn, pos.down(), EnumFacing.UP);
            return blockfaceshape == BlockFaceShape.SOLID || iblockstate.getBlock().isLeaves(iblockstate, worldIn, pos.down()) || block == this && iblockstate.getValue(LAYERS).intValue() == 8;
        }
        else
        {
            return false;
        }
    }

    @Override
    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos)
    {
        this.checkAndDropBlock(worldIn, pos, state);
    }

    private boolean checkAndDropBlock(World worldIn, BlockPos pos, IBlockState state)
    {
        if (!this.canPlaceBlockAt(worldIn, pos))
        {
            worldIn.setBlockToAir(pos);
            return false;
        }
        else
        {
            return true;
        }
    }

    @Override
    public void harvestBlock(World worldIn, EntityPlayer player, BlockPos pos, IBlockState state, @Nullable TileEntity te, ItemStack stack)
    {
        super.harvestBlock(worldIn, player, pos, state, te, stack);
        worldIn.setBlockToAir(pos);
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return Item.getItemFromBlock(KCore.Layered_Sand.getDefaultState().getBlock());
    }

    @Override
    public int quantityDropped(Random random)
    {
        return 1;
    }

    @Override
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
    {
        /*if (worldIn.getLightFor(EnumSkyBlock.BLOCK, pos) > 11)
        {
            worldIn.setBlockToAir(pos);
        }*/

        if(!worldIn.isRemote) {
            giveSandToNeighboursNew(state, worldIn, pos);
            KathairisDataStorage data = KathairisDataStorage.getDataInstance(worldIn);
            if(data!=null){
                if(KathairisDataStorage.getSandstormTime()>-1){
                    if(rand.nextInt(10)==0){
                        int actualLayers=getLayers(state);
                        if(actualLayers<8){
                            worldIn.setBlockState(pos,KCore.Layered_Sand.getDefaultState().withProperty(BlockLayeredSand.LAYERS,actualLayers+1),2);
                        }
                    }
                }
            }
        }

    }

    private void giveSandToNeighboursNew(IBlockState thisState, World world, BlockPos pos){
        int layers = getLayers(thisState);
        if(layers>2){
            BlockPos pos1 = pos.east();
            if(world.getBlockState(pos1).getBlock()==KCore.Layered_Sand) {
                if (getLayers(world.getBlockState(pos1))+1 < layers) {
                    world.setBlockState(pos1, KCore.Layered_Sand.getDefaultState().withProperty(LAYERS, getLayers(world.getBlockState(pos1)) + 1), 2);
                    world.setBlockState(pos, KCore.Layered_Sand.getDefaultState().withProperty(LAYERS, layers - 1), 2);
                    layers--;
                }
            }
            pos1 = pos.west();
            if(world.getBlockState(pos1).getBlock()==KCore.Layered_Sand) {
                if (getLayers(world.getBlockState(pos1))+1 < layers) {
                    world.setBlockState(pos1, KCore.Layered_Sand.getDefaultState().withProperty(LAYERS, getLayers(world.getBlockState(pos1)) + 1), 2);
                    world.setBlockState(pos, KCore.Layered_Sand.getDefaultState().withProperty(LAYERS, layers - 1), 2);
                    layers--;
                }
            }
            pos1 = pos.south();
            if(world.getBlockState(pos1).getBlock()==KCore.Layered_Sand) {
                if (getLayers(world.getBlockState(pos1))+1 < layers) {
                    world.setBlockState(pos1, KCore.Layered_Sand.getDefaultState().withProperty(LAYERS, getLayers(world.getBlockState(pos1)) + 1), 2);
                    world.setBlockState(pos, KCore.Layered_Sand.getDefaultState().withProperty(LAYERS, layers - 1), 2);
                    layers--;
                }
            }
            pos1 = pos.north();
            if(world.getBlockState(pos1).getBlock()==KCore.Layered_Sand) {
                if (getLayers(world.getBlockState(pos1))+1 < layers) {
                    world.setBlockState(pos1, KCore.Layered_Sand.getDefaultState().withProperty(LAYERS, getLayers(world.getBlockState(pos1)) + 1), 2);
                    world.setBlockState(pos, KCore.Layered_Sand.getDefaultState().withProperty(LAYERS, layers - 1), 2);
                    layers--;
                }
            }
            pos1 = pos.down().north();
            if(world.getBlockState(pos1).getBlock()==KCore.Layered_Sand) {
                    if(getLayers(world.getBlockState(pos1))+1<8) {
                        world.setBlockState(pos1, KCore.Layered_Sand.getDefaultState().withProperty(LAYERS, getLayers(world.getBlockState(pos1)) + 1), 2);
                        if (layers - 1 <= 0) {
                            world.setBlockToAir(pos);
                        } else {
                            world.setBlockState(pos, KCore.Layered_Sand.getDefaultState().withProperty(LAYERS, layers - 1), 2);
                        }
                        layers--;
                    }
            }
            pos1 = pos.down().south();
            if(world.getBlockState(pos1).getBlock()==KCore.Layered_Sand) {
                if(getLayers(world.getBlockState(pos1))+1<8) {
                    world.setBlockState(pos1, KCore.Layered_Sand.getDefaultState().withProperty(LAYERS, getLayers(world.getBlockState(pos1)) + 1), 2);
                    if (layers - 1 <= 0) {
                        world.setBlockToAir(pos);
                    } else {
                        world.setBlockState(pos, KCore.Layered_Sand.getDefaultState().withProperty(LAYERS, layers - 1), 2);
                    }
                    layers--;
                }
            }
            pos1 = pos.down().east();
            if(world.getBlockState(pos1).getBlock()==KCore.Layered_Sand) {
                if(getLayers(world.getBlockState(pos1))+1<8) {
                    world.setBlockState(pos1, KCore.Layered_Sand.getDefaultState().withProperty(LAYERS, getLayers(world.getBlockState(pos1)) + 1), 2);
                    if (layers - 1 <= 0) {
                        world.setBlockToAir(pos);
                    } else {
                        world.setBlockState(pos, KCore.Layered_Sand.getDefaultState().withProperty(LAYERS, layers - 1), 2);
                    }
                    layers--;
                }
            }
            pos1 = pos.down().west();
            if(world.getBlockState(pos1).getBlock()==KCore.Layered_Sand) {
                if(getLayers(world.getBlockState(pos1))+1<8) {
                    world.setBlockState(pos1, KCore.Layered_Sand.getDefaultState().withProperty(LAYERS, getLayers(world.getBlockState(pos1)) + 1), 2);
                    if (layers - 1 <= 0) {
                        world.setBlockToAir(pos);
                    } else {
                        world.setBlockState(pos, KCore.Layered_Sand.getDefaultState().withProperty(LAYERS, layers - 1), 2);
                    }
                }
                }
        }
    }

    protected int getLayers(IBlockState state)
    {
        return state.getValue(this.getLayersProperty()).intValue();
    }

    protected PropertyInteger getLayersProperty()
    {
        return LAYERS;
    }


    @Override
    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side)
    {
        if (side == EnumFacing.UP)
        {
            return true;
        }
        else
        {
            IBlockState iblockstate = blockAccess.getBlockState(pos.offset(side));
            return (iblockstate.getBlock() != this || iblockstate.getValue(LAYERS).intValue() < blockState.getValue(LAYERS).intValue()) && super.shouldSideBeRendered(blockState, blockAccess, pos, side);
        }
    }

    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(LAYERS, Integer.valueOf((meta & 7) + 1));
    }

    @Override
    public boolean isReplaceable(IBlockAccess worldIn, BlockPos pos)
    {
        return worldIn.getBlockState(pos).getValue(LAYERS).intValue() == 1;
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        return state.getValue(LAYERS).intValue() - 1;
    }

    @Override public int quantityDropped(IBlockState state, int fortune, Random random){ return state.getValue(LAYERS) + 1; }

    @Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, LAYERS);
    }
}
