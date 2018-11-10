package com.Krevik.Blocks;

import com.Krevik.Main.CreativeTabsMystic;
import com.Krevik.Main.KCore;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.EnumPushReaction;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
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
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.CUTOUT_MIPPED;
    }
    /**
     * Returns the blockstate with the given rotation from the passed blockstate. If inapplicable, returns the passed
     * blockstate.
     */
    public IBlockState withRotation(IBlockState state, Rotation rot)
    {
        return state.withProperty(FACING, rot.rotate((EnumFacing)state.getValue(FACING)));
    }

    /**
     * Returns the blockstate with the given mirror of the passed blockstate. If inapplicable, returns the passed
     * blockstate.
     */
    public IBlockState withMirror(IBlockState state, Mirror mirrorIn)
    {
        return state.withProperty(FACING, mirrorIn.mirror((EnumFacing)state.getValue(FACING)));
    }

    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        switch (((EnumFacing)state.getValue(FACING)).getAxis())
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
    
    public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn)
    {   
    	if(!worldIn.isRemote) {
    		entityIn.attackEntityFrom(DamageSource.MAGIC, 1);
    	}
    }

    public void onEntityWalk(World worldIn, BlockPos pos, Entity entityIn)
    {
        if(!worldIn.isRemote) {
            entityIn.attackEntityFrom(DamageSource.MAGIC, 1);
        }
    }
    
    public AxisAlignedBB getCollisionBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        switch (((EnumFacing)state.getValue(FACING)).getAxis())
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


    public boolean isFullCube(IBlockState state)
    {
        return false;
    }

    /**
     * Checks if this block can be placed exactly at the given position.
     */
    public boolean canPlaceBlockAt(World worldIn, BlockPos pos)
    {
    	if(worldIn.getBlockState(pos.down())==KCore.VioletCrystal.getDefaultState()||worldIn.getBlockState(pos.down())==KCore.YellowCrystal.getDefaultState()||worldIn.getBlockState(pos.down())==KCore.BlueCrystal.getDefaultState()) {
    		return false;
    	}else {
            return true;
    	}
    }

    /**
     * Called by ItemBlocks just before a block is actually set in the world, to allow for adjustments to the
     * IBlockstate
     */
    public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    {
        IBlockState iblockstate = worldIn.getBlockState(pos.offset(facing.getOpposite()));

        if (iblockstate.getBlock() == KCore.VioletCrystal)
        {
            EnumFacing enumfacing = (EnumFacing)iblockstate.getValue(FACING);

            if (enumfacing == facing)
            {
                return Blocks.AIR.getDefaultState();
            }
        }
        if (iblockstate.getBlock() == KCore.YellowCrystal)
        {
            EnumFacing enumfacing = (EnumFacing)iblockstate.getValue(FACING);

            if (enumfacing == facing)
            {
                return Blocks.AIR.getDefaultState();
            }
        }
        if (iblockstate.getBlock() == KCore.BlueCrystal)
        {
            EnumFacing enumfacing = (EnumFacing)iblockstate.getValue(FACING);

            if (enumfacing == facing)
            {
                return Blocks.AIR.getDefaultState();
            }
        }

        return this.getDefaultState().withProperty(FACING, facing);
    }

    /**
     * Convert the given metadata into a BlockState for this Block
     */
    public IBlockState getStateFromMeta(int meta)
    {
        IBlockState iblockstate = this.getDefaultState();
        iblockstate = iblockstate.withProperty(FACING, EnumFacing.getFront(meta));
        return iblockstate;
    }

    /**
     * Convert the BlockState into the correct metadata value
     */
    public int getMetaFromState(IBlockState state)
    {
        return ((EnumFacing)state.getValue(FACING)).getIndex();
    }

    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {FACING});
    }

    public EnumPushReaction getMobilityFlag(IBlockState state)
    {
        return EnumPushReaction.NORMAL;
    }

    /**
     * Get the geometry of the queried face at the given position and state. This is used to decide whether things like
     * buttons are allowed to be placed on the face, or how glass panes connect to the face, among other things.
     * <p>
     * Common values are {@code SOLID}, which is the default, and {@code UNDEFINED}, which represents something that
     * does not fit the other descriptions and will generally cause other things not to connect to the face.
     * 
     * @return an approximation of the form of the given face
     */
    public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face)
    {
        return BlockFaceShape.UNDEFINED;
    }
    public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }
}