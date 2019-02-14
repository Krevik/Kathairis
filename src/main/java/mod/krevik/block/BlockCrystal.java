package mod.krevik.block;

import mod.krevik.KCore;
import mod.krevik.util.CreativeTabsMystic;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.EnumPushReaction;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockCrystal extends BaseBlock
{

    public static final PropertyDirection FACING = PropertyDirection.create("facing");

    protected static final AxisAlignedBB AABB_CRYSTAL_VERTICAL = new AxisAlignedBB(0.2D, 0.3D, 0.2D, 0.8D, 1D, 0.8D);
    protected static final AxisAlignedBB AABB_CRYSTAL_NS_AABB = new AxisAlignedBB(0.2D, 0.2D, 0.3D, 0.8D, 0.8D, 1D);
    protected static final AxisAlignedBB AABB_CRYSTAL_EW_AABB = new AxisAlignedBB(0.3D, 0.2D, 0.2D, 1D, 0.8D, 0.8D);

    public BlockCrystal(String Name)
    {
    	super(Name,Material.GLASS,CreativeTabsMystic.buildingBlocks,3F,3F,SoundType.GLASS);
        this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.UP));
    }
    
    
    @SideOnly(Side.CLIENT)
    @Override
    public BlockRenderLayer getRenderLayer()
    {
        return BlockRenderLayer.CUTOUT_MIPPED;
    }

    @Override
    public IBlockState withRotation(IBlockState state, Rotation rot)
    {
        return state.withProperty(FACING, rot.rotate(state.getValue(FACING)));
    }


    @Override
    public IBlockState withMirror(IBlockState state, Mirror mirrorIn)
    {
        return state.withProperty(FACING, mirrorIn.mirror(state.getValue(FACING)));
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        switch (state.getValue(FACING).getAxis())
        {
            case X:
            default:
                return AABB_CRYSTAL_EW_AABB;
            case Z:
                return AABB_CRYSTAL_NS_AABB;
            case Y:
                return AABB_CRYSTAL_VERTICAL;
        }
    }
    
    @Override
    public void onEntityCollision(World worldIn, BlockPos pos, IBlockState state, Entity entityIn)
    {   
    	if(!worldIn.isRemote) {
    		entityIn.attackEntityFrom(DamageSource.MAGIC, 1);
    	}
    }

    @Override
    public void onEntityWalk(World worldIn, BlockPos pos, Entity entityIn)
    {
        if(!worldIn.isRemote) {
            entityIn.attackEntityFrom(DamageSource.MAGIC, 1);
        }
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        switch (state.getValue(FACING).getAxis())
        {
            case X:
            default:
                return AABB_CRYSTAL_EW_AABB;
            case Z:
                return AABB_CRYSTAL_NS_AABB;
            case Y:
                return AABB_CRYSTAL_VERTICAL;
        }
    }


    @Override
    public boolean isFullCube(IBlockState state)
    {
        return false;
    }

    @Override
    public boolean canPlaceBlockAt(World worldIn, BlockPos pos)
    {
        return worldIn.getBlockState(pos.down()) != KCore.VioletCrystal.getDefaultState() && worldIn.getBlockState(pos.down()) != KCore.YellowCrystal.getDefaultState() && worldIn.getBlockState(pos.down()) != KCore.BlueCrystal.getDefaultState();
    }

    @Override
    public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    {
        IBlockState iblockstate = worldIn.getBlockState(pos.offset(facing.getOpposite()));

        if (iblockstate.getBlock() == KCore.VioletCrystal)
        {
            EnumFacing enumfacing = iblockstate.getValue(FACING);

            if (enumfacing == facing)
            {
                return Blocks.AIR.getDefaultState();
            }
        }
        if (iblockstate.getBlock() == KCore.YellowCrystal)
        {
            EnumFacing enumfacing = iblockstate.getValue(FACING);

            if (enumfacing == facing)
            {
                return Blocks.AIR.getDefaultState();
            }
        }
        if (iblockstate.getBlock() == KCore.BlueCrystal)
        {
            EnumFacing enumfacing = iblockstate.getValue(FACING);

            if (enumfacing == facing)
            {
                return Blocks.AIR.getDefaultState();
            }
        }

        return this.getDefaultState().withProperty(FACING, facing);
    }

    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        IBlockState iblockstate = this.getDefaultState();
        iblockstate = iblockstate.withProperty(FACING, EnumFacing.byIndex(meta));
        return iblockstate;
    }


    @Override
    public int getMetaFromState(IBlockState state)
    {
        return state.getValue(FACING).getIndex();
    }

    @Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, FACING);
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