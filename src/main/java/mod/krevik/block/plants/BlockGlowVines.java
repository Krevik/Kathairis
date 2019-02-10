package mod.krevik.block.plants;

import mod.krevik.KCore;
import net.minecraft.block.*;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.Random;

public class BlockGlowVines extends BlockMysticBush {

    public static final PropertyEnum<BlockGlowVines.EnumType> VARIANT = PropertyEnum.<BlockGlowVines.EnumType>create("variant", BlockGlowVines.EnumType.class);
    public static final PropertyDirection FACING = BlockHorizontal.FACING;

    public BlockGlowVines(String Name, boolean replacable1) {
        super(Name, replacable1);
        setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, EnumType.TOP).withProperty(FACING,EnumFacing.EAST));
    }

    @Nullable
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos)
    {
        return NULL_AABB;
    }

    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        return FULL_BLOCK_AABB;
    }



    /**
     * Used to determine ambient occlusion and culling when rebuilding chunks for render
     */
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

    /**
     * Whether this Block can be replaced directly by other blocks (true for e.g. tall grass)
     */
    @Override
    public boolean isReplaceable(IBlockAccess worldIn, BlockPos pos)
    {
        return false;
    }

    @Override
    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos)
    {
        if (!worldIn.isRemote) {
            if (!canBlockStay(worldIn, pos, state)) {
                worldIn.destroyBlock(pos, true);
            }
        }
        handleFacing(worldIn,pos,state);
        handleVariants(worldIn,pos,state);
    }

    @Override
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
    {
        if (!worldIn.isRemote)
        {
            if(!canBlockStay(worldIn,pos,state)){
                worldIn.destroyBlock(pos, true);
            }
            if(rand.nextInt(40)==0){
                grow(worldIn,pos,rand);
            }
        }
        handleFacing(worldIn,pos,state);
        handleVariants(worldIn,pos,state);
    }



    @Override
    public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn)
    {
    }

    public void grow(World worldIn, BlockPos pos, Random random) {
        if(worldIn.getBlockState(pos).getBlock() instanceof BlockGlowVines) {
            if (worldIn.isAirBlock(pos.down())) {
                EnumType variant = worldIn.getBlockState(pos).getValue(VARIANT);
                if (variant == EnumType.TOP) {
                    worldIn.setBlockState(pos.down(), KCore.glowvines.getDefaultState().withProperty(VARIANT, EnumType.MID));
                }
                if (variant == EnumType.MID) {
                    if (random.nextInt(5) == 0) {
                        worldIn.setBlockState(pos.down(), KCore.glowvines.getDefaultState().withProperty(VARIANT, EnumType.BOTTOM));
                    } else {
                        worldIn.setBlockState(pos.down(), KCore.glowvines.getDefaultState().withProperty(VARIANT, EnumType.MID));
                    }
                    if (!worldIn.isAirBlock(pos.down(2))) {
                        worldIn.setBlockState(pos.down(), KCore.glowvines.getDefaultState().withProperty(VARIANT, EnumType.BOTTOM));
                    }
                }
            }
        }
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
    {
        handleFacing(worldIn,pos,state);
        handleVariants(worldIn,pos,state);
        if(worldIn.getBlockState(pos.up()).getBlock() instanceof BlockGlowVines){
            if(worldIn.getBlockState(pos.up()).getValue(VARIANT)==EnumType.MID){
                if(KCore.functionHelper.random.nextInt(5)==0){
                    worldIn.setBlockState(pos, state.withProperty(VARIANT,EnumType.BOTTOM));
                }
            }
        }
    }

    public void handleFacing(World world,BlockPos pos,IBlockState state){
            if (!world.isAirBlock(pos.east())&&!(world.getBlockState(pos.east()).getBlock() instanceof BlockGlowVines)) {
                world.setBlockState(pos, state.withProperty(FACING, EnumFacing.NORTH));
            } else if (!world.isAirBlock(pos.west())&&!(world.getBlockState(pos.west()).getBlock() instanceof BlockGlowVines)) {
                world.setBlockState(pos, state.withProperty(FACING, EnumFacing.SOUTH));
            } else if (!world.isAirBlock(pos.south())&&!(world.getBlockState(pos.south()).getBlock() instanceof BlockGlowVines)) {
                world.setBlockState(pos, state.withProperty(FACING, EnumFacing.EAST));
            } else if (!world.isAirBlock(pos.north())&&!(world.getBlockState(pos.north()).getBlock() instanceof BlockGlowVines)) {
                world.setBlockState(pos, state.withProperty(FACING, EnumFacing.WEST));
            }
    }



    public void handleVariants(World world,BlockPos pos,IBlockState state){
        if(world.getBlockState(pos).getBlock() instanceof BlockGlowVines){
            EnumType variant = world.getBlockState(pos).getValue(VARIANT);
            if(world.getBlockState(pos.up()).getBlock() instanceof BlockGlowVines){
                EnumType upperVariant = world.getBlockState(pos.up()).getValue(VARIANT);
                if(variant==EnumType.TOP){
                    if(upperVariant==EnumType.TOP||upperVariant==EnumType.MID||upperVariant==EnumType.BOTTOM){
                        world.setBlockState(pos,state.withProperty(VARIANT,EnumType.MID));
                    }
                }
                if(variant==EnumType.MID){
                    if(upperVariant==EnumType.BOTTOM){
                        world.destroyBlock(pos, true);
                    }
                    if(world.getBlockState(pos.down(2)).isFullBlock()||world.getBlockState(pos.down()).isFullBlock()){
                        world.setBlockState(pos,state.withProperty(VARIANT,EnumType.BOTTOM));
                    }
                }
                if(variant==EnumType.BOTTOM){
                    if(upperVariant==EnumType.BOTTOM||upperVariant==EnumType.TOP){
                        world.destroyBlock(pos, true);
                    }
                }
            }
        }
    }

    @Override
    public boolean canPlaceBlockAt(World worldIn, BlockPos pos)
    {
        boolean can=false;
        if(!worldIn.isAirBlock(pos.east())&&worldIn.getBlockState(pos.east()).isFullBlock()){
            can=true;
        }
        if(!worldIn.isAirBlock(pos.west())&&worldIn.getBlockState(pos.west()).isFullBlock()){
            can=true;
        }
        if(!worldIn.isAirBlock(pos.south())&&worldIn.getBlockState(pos.south()).isFullBlock()){
            can=true;
        }
        if(!worldIn.isAirBlock(pos.north())&&worldIn.getBlockState(pos.north()).isFullBlock()){
            can=true;
        }
        if(!worldIn.isAirBlock(pos.up())) {
            if (worldIn.getBlockState(pos.up()).getBlock() instanceof BlockGlowVines) {
                EnumType upperVariant = worldIn.getBlockState(pos.up()).getValue(VARIANT);
                if(upperVariant==EnumType.BOTTOM){
                    can=false;
                }else{
                    can=true;
                }
            }
        }
        return can;
    }

    public boolean canBlockStay(World worldIn, BlockPos pos, IBlockState state)
    {
        return canPlaceBlockAt(worldIn, pos);
    }


    /**
     * Get the Item that this Block should drop when harvested.
     */
    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return Items.AIR;
    }

    /**
     * Returns the quantity of items to drop on block destruction.
     */
    @Override
    public int quantityDropped(Random random)
    {
        return 0;
    }


    /**
     * Spawns the block's drops in the world. By the time this is called the Block has possibly been set to air via
     * Block.removedByPlayer
     */
    @Override
    public void harvestBlock(World worldIn, EntityPlayer player, BlockPos pos, IBlockState state, @Nullable TileEntity te, ItemStack stack)
    {
        if (!worldIn.isRemote && stack.getItem() == Items.SHEARS)
        {
            player.addStat(StatList.getBlockStats(this));
            spawnAsEntity(worldIn, pos, new ItemStack(KCore.glowvines, 1));
        }
        else
        {
            super.harvestBlock(worldIn, player, pos, state, te, stack);
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.CUTOUT_MIPPED;
    }

    @Override public boolean isLadder(IBlockState state, IBlockAccess world, BlockPos pos, EntityLivingBase entity){ return false; }
    public boolean isShearable(ItemStack item, IBlockAccess world, BlockPos pos){ return true; }

    public java.util.List<ItemStack> onSheared(ItemStack item, IBlockAccess world, BlockPos pos, int fortune)
    {
        return java.util.Arrays.asList(new ItemStack(this, 1));
    }

    @Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {VARIANT,FACING});
    }

    /*@Override
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(VARIANT, BlockGlowVines.EnumType.byMetadata(meta));
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        return ((BlockGlowVines.EnumType)state.getValue(VARIANT)).getMetadata();
    }*/


    public IBlockState getStateFromMeta(int i)
    {
        EnumType variant = EnumType.BOTTOM;
        EnumFacing facing = EnumFacing.NORTH;
        if(i>=0&&i<=4){
            variant=EnumType.TOP;
            if(i==1){ facing=EnumFacing.WEST;}
            if(i==2){ facing=EnumFacing.EAST;}
            if(i==3){ facing=EnumFacing.SOUTH;}
            if(i==4){ facing=EnumFacing.NORTH;}
        }
        if(i>=5&&i<=8){
            variant=EnumType.MID;
            if(i==5){ facing=EnumFacing.WEST;}
            if(i==6){ facing=EnumFacing.EAST;}
            if(i==7){ facing=EnumFacing.SOUTH;}
            if(i==8){ facing=EnumFacing.NORTH;}
        }
        if(i>=9&&i<=12){
            variant=EnumType.BOTTOM;
            if(i==9){ facing=EnumFacing.WEST;}
            if(i==10){ facing=EnumFacing.EAST;}
            if(i==11){ facing=EnumFacing.SOUTH;}
            if(i==12){ facing=EnumFacing.NORTH;}
        }
        IBlockState state = KCore.glowvines.getDefaultState().withProperty(VARIANT,variant).withProperty(FACING,facing);
        return state;
    }

    @SuppressWarnings("incomplete-switch")
    public int getMetaFromState(IBlockState state)
    {
        int a=0;
        int b=1;
        int i=a+b;
        EnumType variant = state.getValue(VARIANT);
        EnumFacing facing = state.getValue(FACING);
        if(variant==EnumType.TOP){a=0;}
        if(variant==EnumType.MID){a=5;}
        if(variant==EnumType.BOTTOM){a=9;}

        if(facing==EnumFacing.WEST) {b=1;}
        if(facing==EnumFacing.EAST) {b=2;}
        if(facing==EnumFacing.SOUTH) {b=3;}
        if(facing==EnumFacing.NORTH) {b=4;}

        i=a+b;
        return i;
    }

    public static enum EnumType implements IStringSerializable
    {
        TOP(0,"top"),
        MID(1,"mid"),
        BOTTOM(2,"bottom");

        private static final BlockGlowVines.EnumType[] META_LOOKUP = new BlockGlowVines.EnumType[values().length];
        private final int meta;
        private final String name;

        private EnumType(int p_i46384_3_,String Name)
        {
            this.meta = p_i46384_3_;
            name=Name;
        }

        /**
         * Returns the EnumType's metadata value.
         */
        public int getMetadata()
        {
            return this.meta;
        }

        /**
         * Returns an EnumType for the BlockState from a metadata value.
         */
        public static BlockGlowVines.EnumType byMetadata(int meta)
        {
            if (meta < 0 || meta >= META_LOOKUP.length)
            {
                meta = 0;
            }

            return META_LOOKUP[meta];
        }

        static
        {
            for (BlockGlowVines.EnumType blockstone$enumtype : values())
            {
                META_LOOKUP[blockstone$enumtype.getMetadata()] = blockstone$enumtype;
            }
        }

        @Override
        public String getName() {
            return name;
        }
    }

}
