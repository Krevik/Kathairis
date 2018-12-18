package com.krevik.Blocks;

import com.krevik.Items.ItemMysticAxe;
import com.krevik.Main.CreativeTabsMystic;
import com.krevik.Main.KCore;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockRedwoodLog extends BlockBaseLog
{
    public BlockRedwoodLog(String Name)
    {
    	super(Name,Material.WOOD,CreativeTabsMystic.buildingBlocks,4F,4F,SoundType.WOOD);
        this.setDefaultState(this.blockState.getBaseState().withProperty(LOG_AXIS, BlockRedwoodLog.EnumAxis.Y));
    }
    
    
    protected static final AxisAlignedBB[] FULL_SIZES_AABB = new AxisAlignedBB[] {
    		new AxisAlignedBB(0D, 0.0D, 0D, 1D, 1D, 1D)};
    protected static final AxisAlignedBB[] SIZES_6_AABB = new AxisAlignedBB[] {
    		new AxisAlignedBB(0D, 0.0625D, 0.0625D, 1.0D, 0.9375D, 0.9375D),
    		new AxisAlignedBB(0.0625D, 0.0D, 0.0625D, 0.9375D, 1D, 0.9375D),
    		new AxisAlignedBB(0.0625D, 0.0625D, 0D, 0.9375D, 0.9375D, 1D)};
    protected static final AxisAlignedBB[] SIZES_5_AABB = new AxisAlignedBB[] {
    		new AxisAlignedBB(0D, 0.125D, 0.125D, 1D, 0.875D, 0.875D),
    		new AxisAlignedBB(0.125D, 0.0D, 0.125D, 0.875D, 1D, 0.875D),
    		new AxisAlignedBB(0.125D, 0.125D, 0D, 0.875D, 0.875D, 1D)};
    protected static final AxisAlignedBB[] SIZES_4_AABB = new AxisAlignedBB[] {
    		new AxisAlignedBB(0D, 0.1875D, 0.1875D, 1D, 0.8125D, 0.8125D),
    		new AxisAlignedBB(0.1875D, 0.0D, 0.1875D, 0.8125D, 1D, 0.8125D),
    		new AxisAlignedBB(0.1875D, 0.1875D, 0D, 0.8125D, 0.8125D, 1D)};
    protected static final AxisAlignedBB[] SIZES_3_AABB = new AxisAlignedBB[] {
    		new AxisAlignedBB(0D, 0.25D, 0.25D, 1D, 0.75D, 0.75D),
    		new AxisAlignedBB(0.25D, 0.0D, 0.25D, 0.75D, 1D, 0.75D),
    		new AxisAlignedBB(0.25D, 0.25D, 0D, 0.75D, 0.75D, 1D)};
    protected static final AxisAlignedBB[] SIZES_2_AABB = new AxisAlignedBB[] {
    		new AxisAlignedBB(0D, 0.3125D, 0.3125D, 1D, 0.6875D, 0.6875D),
    		new AxisAlignedBB(0.3125D, 0.0D, 0.3125D, 0.6875D, 1D, 0.6875D),
    		new AxisAlignedBB(0.3125D, 0.3125D, 0D, 0.6875D, 0.6875D, 1D)};
    protected static final AxisAlignedBB[] SIZES_1_AABB = new AxisAlignedBB[] {
    		new AxisAlignedBB(0D, 0.375D, 0.375D, 1D, 0.625D, 0.625D),
    		new AxisAlignedBB(0.375D, 0.0D, 0.375D, 0.625D, 1D, 0.625D),
    		new AxisAlignedBB(0.375D, 0.375D, 0D, 0.625D, 0.625D, 1D)};
    

    
    public AxisAlignedBB getCollisionBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
    	return getBoundingBox(state,source,pos);
    }
    
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
    	boolean result=false;
    	ItemStack stack = playerIn.getHeldItem(hand);
	       	if(stack.getItem() instanceof ItemAxe||stack.getItem() instanceof ItemMysticAxe) {
   				EntityItem item = new EntityItem(worldIn,pos.getX()+0.5,pos.getY()+0.5,pos.getZ()+0.5,new ItemStack(KCore.Redwood_planks,1));
	       		if(state.getBlock()==KCore.Redwood_log_full) {
	       			result=true;
	       			if(!worldIn.isRemote) {
	       				IBlockState desiredState = KCore.Redwood_log_size_6.getDefaultState().withProperty(LOG_AXIS, state.getValue(LOG_AXIS));
		       			worldIn.setBlockState(pos, desiredState);
		       			stack.damageItem(1, playerIn);
	       			}
	       		}
	       		if(state.getBlock()==KCore.Redwood_log_size_6) {
	       			result=true;
	       			if(!worldIn.isRemote) {
	       				worldIn.spawnEntity(item);
	       				IBlockState desiredState = KCore.Redwood_log_size_5.getDefaultState().withProperty(LOG_AXIS, state.getValue(LOG_AXIS));
		       			worldIn.setBlockState(pos, desiredState);
		       			stack.damageItem(1, playerIn);
	       			}
	       		}
	       		if(state.getBlock()==KCore.Redwood_log_size_5) {
	       			result=true;
	       			if(!worldIn.isRemote) {
	       				IBlockState desiredState = KCore.Redwood_log_size_4.getDefaultState().withProperty(LOG_AXIS, state.getValue(LOG_AXIS));
		       			worldIn.setBlockState(pos, desiredState);
		       			stack.damageItem(1, playerIn);
	       			}
	       		}
	       		if(state.getBlock()==KCore.Redwood_log_size_4) {
	       			result=true;
	       			if(!worldIn.isRemote) {
	       				worldIn.spawnEntity(item);
	       				IBlockState desiredState = KCore.Redwood_log_size_3.getDefaultState().withProperty(LOG_AXIS, state.getValue(LOG_AXIS));
		       			worldIn.setBlockState(pos, desiredState);
		       			stack.damageItem(1, playerIn);
	       			}
	       		}
	       		if(state.getBlock()==KCore.Redwood_log_size_3) {
	       			result=true;
	       			if(!worldIn.isRemote) {
	       				IBlockState desiredState = KCore.Redwood_log_size_2.getDefaultState().withProperty(LOG_AXIS, state.getValue(LOG_AXIS));
		       			worldIn.setBlockState(pos, desiredState);
		       			stack.damageItem(1, playerIn);
	       			}
	       		}
	       		if(state.getBlock()==KCore.Redwood_log_size_2) {
	       			result=true;
	       			if(!worldIn.isRemote) {
	       				worldIn.spawnEntity(item);
	       				IBlockState desiredState = KCore.Redwood_log_size_1.getDefaultState().withProperty(LOG_AXIS, state.getValue(LOG_AXIS));
		       			worldIn.setBlockState(pos, desiredState);
		       			stack.damageItem(1, playerIn);
	       			}
	       		}
	       	}
	       	
    	return result;
    }
	
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
		if(state.getBlock()==KCore.Redwood_log_full) return FULL_SIZES_AABB[0];
		else if(state.getBlock()==KCore.Redwood_log_size_6) {
	        switch ((BlockRedwoodLog.EnumAxis)state.getValue(LOG_AXIS))
	        {
	            case X:
	            	default:
	                return SIZES_6_AABB[0];
	            case Z:
	                return SIZES_6_AABB[2];
	            case Y:
	                return SIZES_6_AABB[1];
	        }
		}else if(state.getBlock()==KCore.Redwood_log_size_5) {
	        switch ((BlockRedwoodLog.EnumAxis)state.getValue(LOG_AXIS))
	        {
	            case X:
	            	default:
	                return SIZES_5_AABB[0];
	            case Z:
	                return SIZES_5_AABB[2];
	            case Y:
	                return SIZES_5_AABB[1];
	        }
		}else if(state.getBlock()==KCore.Redwood_log_size_4) {
	        switch ((BlockRedwoodLog.EnumAxis)state.getValue(LOG_AXIS))
	        {
	            case X:
	            	default:
	                return SIZES_4_AABB[0];
	            case Z:
	                return SIZES_4_AABB[2];
	            case Y:
	                return SIZES_4_AABB[1];
	        }
		}else if(state.getBlock()==KCore.Redwood_log_size_3) {
	        switch ((BlockRedwoodLog.EnumAxis)state.getValue(LOG_AXIS))
	        {
	            case X:
	            	default:
	                return SIZES_3_AABB[0];
	            case Z:
	                return SIZES_3_AABB[2];
	            case Y:
	                return SIZES_3_AABB[1];
	        }
		}else if(state.getBlock()==KCore.Redwood_log_size_2) {
	        switch ((BlockRedwoodLog.EnumAxis)state.getValue(LOG_AXIS))
	        {
	            case X:
	            	default:
	                return SIZES_2_AABB[0];
	            case Z:
	                return SIZES_2_AABB[2];
	            case Y:
	                return SIZES_2_AABB[1];
	        }
		}else if(state.getBlock()==KCore.Redwood_log_size_1) {
	        switch ((BlockRedwoodLog.EnumAxis)state.getValue(LOG_AXIS))
	        {
	            case X:
	            	default:
	                return SIZES_1_AABB[0];
	            case Z:
	                return SIZES_1_AABB[2];
	            case Y:
	                return SIZES_1_AABB[1];
	        }
		}else {
			return FULL_BLOCK_AABB;
		}
    }

    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {LOG_AXIS});
    }
    
    public boolean isFullCube(IBlockState state)
    {
    	if(this==KCore.Redwood_log_full) {
    		return true;
    	}else {
    		return false;
    	}
    }
    
    public boolean isOpaqueCube(IBlockState state)
    {
    	return isFullCube(state);
    }
    
    public IBlockState getStateFromMeta(int meta)
    {
        IBlockState iblockstate = this.getDefaultState();

        switch (meta & 12)
        {
            case 0:
                iblockstate = iblockstate.withProperty(LOG_AXIS, BlockRedwoodLog.EnumAxis.Y);
                break;
            case 4:
                iblockstate = iblockstate.withProperty(LOG_AXIS, BlockRedwoodLog.EnumAxis.X);
                break;
            case 8:
                iblockstate = iblockstate.withProperty(LOG_AXIS, BlockRedwoodLog.EnumAxis.Z);
                break;
            default:
                iblockstate = iblockstate.withProperty(LOG_AXIS, BlockRedwoodLog.EnumAxis.NONE);
        }

        return iblockstate;
    }

    /**
     * Convert the BlockState into the correct metadata value
     */
    @SuppressWarnings("incomplete-switch")
    public int getMetaFromState(IBlockState state)
    {
        int i = 0;

        switch ((BlockRedwoodLog.EnumAxis)state.getValue(LOG_AXIS))
        {
            case X:
                i |= 4;
                break;
            case Z:
                i |= 8;
                break;
            case NONE:
                i |= 12;
        }

        return i;
    }
    
    @Override
    public boolean canSustainLeaves(IBlockState state, IBlockAccess world, BlockPos pos)
    {
        return true;
    }
    /**
     * Gets the metadata of the item this Block can drop. This method is called when the block gets destroyed. It
     * returns the metadata of the dropped item based on the old metadata of the block.
     */
    public int damageDropped(IBlockState state)
    {
        return 0;
    }
    
}
