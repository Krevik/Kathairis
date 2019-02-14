package mod.krevik.block.plants;

import mod.krevik.KCore;
import net.minecraft.block.Block;
import net.minecraft.block.IGrowable;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Random;

public class BlockMysticMultiGrass extends BlockMysticBush implements IGrowable
{
    private boolean replacable;
    protected static final AxisAlignedBB TALL_GRASS_AABB = new AxisAlignedBB(0.09999999403953552D, 0.0D, 0.09999999403953552D, 0.8999999761581421D, 0.800000011920929D, 0.8999999761581421D);
    protected static final AxisAlignedBB FULL_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1D, 1D, 1D);

    public BlockMysticMultiGrass(String Name,boolean repl)
    {
        super(Name,repl);
        this.setHardness(0.0f);
        replacable=repl;
        addBlocksThatPlantCanStayOn(Blocks.GRASS,Blocks.DIRT,Blocks.FARMLAND,KCore.KatharianGrass,KCore.KatharianDirt,KCore.MysticMultiGrass);
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        if(!replacable){
            return FULL_AABB;
        }else{
            return TALL_GRASS_AABB;
        }
    }

    @Override
    public boolean isReplaceable(IBlockAccess worldIn, BlockPos pos)
    {
        return replacable;
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return null;
    }

    @Override
    public int quantityDroppedWithBonus(int fortune, Random random)
    {
        return 1 + random.nextInt(fortune * 2 + 1);
    }

    @Override
    public void harvestBlock(World worldIn, EntityPlayer player, BlockPos pos, IBlockState state, @Nullable TileEntity te, ItemStack stack)
    {
        if (!worldIn.isRemote && stack.getItem() == Items.SHEARS)
        {
            player.addStat(StatList.getBlockStats(this));
                spawnAsEntity(worldIn, pos, new ItemStack(KCore.MysticMultiGrass, 1, 0));
        }
        else
        {
            super.harvestBlock(worldIn, player, pos, state, te, stack);
        }
    }

    @Override
    public boolean canGrow(World worldIn, BlockPos pos, IBlockState state, boolean isClient)
    {
        return true;
    }

    @Override
    public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, IBlockState state)
    {
        return true;
    }

    @Override
    public void grow(World worldIn, Random rand, BlockPos pos, IBlockState state)
    {
        //TODO
    }

    @Override
    public Block.EnumOffsetType getOffsetType()
    {
        return Block.EnumOffsetType.XYZ;
    }

    @Override
    protected boolean canSustainBush(IBlockState state)
    {
        boolean can=false;
        if(state.getBlock() == Blocks.GRASS || state.getBlock() == Blocks.DIRT || state.getBlock() == Blocks.FARMLAND || state.getBlock()==KCore.KatharianDirt
                || state.getBlock()==KCore.KatharianGrass
                ||state.getBlock() == KCore.MysticMultiGrass){
            can=true;
        }
        return can;
    }

    @Override
    public boolean canSustainPlantRemake(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing direction, net.minecraftforge.common.IPlantable plantable)
    {
        boolean can=false;
        if(state==KCore.KatharianDirt.getDefaultState() || state==KCore.KatharianGrass.getDefaultState() || state==Blocks.GRASS.getDefaultState() || state==Blocks.DIRT.getDefaultState()||
                state==Blocks.FARMLAND.getDefaultState()||
                state.getBlock() == KCore.MysticMultiGrass){
            can=true;
        }
        return can;
    }
}