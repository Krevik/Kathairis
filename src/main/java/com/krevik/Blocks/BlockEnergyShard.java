package com.krevik.Blocks;

import com.krevik.Main.CreativeTabsMystic;
import com.krevik.Main.KCore;

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
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.TRANSLUCENT;
    }

    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
    	return AABB_ENERGYSHARD;
    }
    
    public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn)
    {   
    	if(!worldIn.isRemote) {
    		entityIn.attackEntityFrom(DamageSource.MAGIC, 1);
    	}
    }
    
    public AxisAlignedBB getCollisionBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
    	return AABB_ENERGYSHARD;
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
    	if(worldIn.getBlockState(pos.down())==KCore.VioletCrystal.getDefaultState()||worldIn.getBlockState(pos.down())==KCore.YellowCrystal.getDefaultState()||worldIn.getBlockState(pos.down())==KCore.BlueCrystal.getDefaultState()||
    			worldIn.getBlockState(pos.down())==KCore.EnergyShard.getDefaultState()||worldIn.getBlockState(pos.down()).getBlock() instanceof BlockMysticBush || worldIn.getBlockState(pos.down()).getBlock() instanceof BlockBush || !worldIn.getBlockState(pos.down()).getBlock().isFullBlock(worldIn.getBlockState(pos.down()))) {
    		return false;
    	}else {
            return true;
    	}
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