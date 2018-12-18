package com.krevik.Blocks;


import java.util.Random;

import com.krevik.Main.CreativeTabsMystic;
import com.krevik.Main.KCore;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockMysticCloud extends BaseBlock
{
	
    protected static final AxisAlignedBB AABB_BOTTOM_HALF = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.6D, 1.0D);

    boolean ignoreSimilarity=false;
    public BlockMysticCloud(String Name)
    {
        super(Name, Material.CLOTH, CreativeTabsMystic.buildingBlocks, 0.5F, 0.5F, SoundType.CLOTH);
        this.setDefaultState(this.blockState.getBaseState());
        this.setLightOpacity(1);
    }
    
    
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
    	return null;
    }
    
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state)
    {
        EntityPlayer ep = worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 10, true);
        if(ep!=null) {
	        if(!ep.isCreative()) {
	        	if(this==KCore.BlueCloud) {
	        		worldIn.setBlockState(pos, KCore.CloudParticleEmitter.getDefaultState().withProperty(BlockCloudParticleEmitter.VARIANT, BlockCloudParticleEmitter.EnumType.BLUE));
	        	}
	        	if(this==KCore.YellowCloud) {
	        		worldIn.setBlockState(pos, KCore.CloudParticleEmitter.getDefaultState().withProperty(BlockCloudParticleEmitter.VARIANT, BlockCloudParticleEmitter.EnumType.YELLOW));
	        	}
	        }else {
	        	super.breakBlock(worldIn, pos, state);
	        }
        }else {
        	super.breakBlock(worldIn, pos, state);
        }

    }
    
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.TRANSLUCENT;
    }
    public boolean isFullCube(IBlockState state)
    {
        return false;
    }
    
    public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }
    
    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side)
    {
        IBlockState iblockstate = blockAccess.getBlockState(pos.offset(side));
        Block block = iblockstate.getBlock();

        return !this.ignoreSimilarity && block == this ? false : super.shouldSideBeRendered(blockState, blockAccess, pos, side);
    }
    
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos)
    {
            return null;
    }
    
    public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn)
    { 
    	entityIn.motionY=-0.0000001;
    }


    /**
     * Gets the metadata of the item this Block can drop. This method is called when the block gets destroyed. It
     * returns the metadata of the dropped item based on the old metadata of the block.
     */
    public int damageDropped(IBlockState state)
    {
        return 0;
    }
    
    /**
     * Convert the given metadata into a BlockState for this Block
     */
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState();
    }

    /**
     * Convert the BlockState into the correct metadata value
     */
    public int getMetaFromState(IBlockState state)
    {
        return 0;
    }
}