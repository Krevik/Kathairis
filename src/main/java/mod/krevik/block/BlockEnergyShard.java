package mod.krevik.block;

import mod.krevik.KCore;
import mod.krevik.block.plants.BlockMysticBush;
import mod.krevik.util.CreativeTabsMystic;
import net.minecraft.block.BlockBush;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.EnumPushReaction;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockEnergyShard extends BaseBlock
{

    protected static final AxisAlignedBB AABB_ENERGYSHARD = new AxisAlignedBB(0.3D, 0D, 0.3D, 0.7D, 0.6D, 0.7D);

    public BlockEnergyShard(String Name)
    {
    	super(Name,Material.GLASS,CreativeTabsMystic.miscellaneous,1F,1F,SoundType.GLASS);
    }
    
    
    @SideOnly(Side.CLIENT)
    @Override
    public BlockRenderLayer getRenderLayer()
    {
        return BlockRenderLayer.TRANSLUCENT;
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
    	return AABB_ENERGYSHARD;
    }
    
    @Override
    public void onEntityCollision(World worldIn, BlockPos pos, IBlockState state, Entity entityIn)
    {   
    	if(!worldIn.isRemote) {
    		entityIn.attackEntityFrom(DamageSource.MAGIC, 1);
    	}
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
    	return AABB_ENERGYSHARD;
    }

    @Override
    public boolean isFullCube(IBlockState state)
    {
        return false;
    }

    @Override
    public boolean canPlaceBlockAt(World worldIn, BlockPos pos)
    {
        return worldIn.getBlockState(pos.down()) != KCore.VioletCrystal.getDefaultState() && worldIn.getBlockState(pos.down()) != KCore.YellowCrystal.getDefaultState() && worldIn.getBlockState(pos.down()) != KCore.BlueCrystal.getDefaultState() &&
                worldIn.getBlockState(pos.down()) != KCore.EnergyShard.getDefaultState() && !(worldIn.getBlockState(pos.down()).getBlock() instanceof BlockMysticBush) && !(worldIn.getBlockState(pos.down()).getBlock() instanceof BlockBush) && worldIn.getBlockState(pos.down()).getBlock().isFullBlock(worldIn.getBlockState(pos.down()));
    }

    @Override
    public EnumPushReaction getPushReaction(IBlockState state)
    {
        return EnumPushReaction.NORMAL;
    }

    @Override
    public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face)
    {
        return BlockFaceShape.UNDEFINED;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }
}