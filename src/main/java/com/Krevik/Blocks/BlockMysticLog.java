package com.Krevik.Blocks;

import com.Krevik.Items.ItemMysticAxe;
import com.Krevik.Main.CreativeTabsMystic;
import com.Krevik.Main.KCore;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockMysticLog extends BlockBaseLog
{

    public BlockMysticLog(String Name)
    {
    	super(Name,Material.WOOD,CreativeTabsMystic.buildingBlocks,3F,3F,SoundType.WOOD);
        this.setDefaultState(this.blockState.getBaseState().withProperty(LOG_AXIS, BlockMysticLog.EnumAxis.Y));
    }
    

    /**
     * Convert the given metadata into a BlockState for this Block
     */
    public IBlockState getStateFromMeta(int meta)
    {
        IBlockState iblockstate = this.getDefaultState();

        switch (meta & 12)
        {
            case 0:
                iblockstate = iblockstate.withProperty(LOG_AXIS, BlockMysticLog.EnumAxis.Y);
                break;
            case 4:
                iblockstate = iblockstate.withProperty(LOG_AXIS, BlockMysticLog.EnumAxis.X);
                break;
            case 8:
                iblockstate = iblockstate.withProperty(LOG_AXIS, BlockMysticLog.EnumAxis.Z);
                break;
            default:
                iblockstate = iblockstate.withProperty(LOG_AXIS, BlockMysticLog.EnumAxis.NONE);
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

        switch ((BlockMysticLog.EnumAxis)state.getValue(LOG_AXIS))
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
    
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        	ItemStack stack = playerIn.getHeldItem(hand);
           	if(stack.getItem() instanceof ItemAxe||stack.getItem() instanceof ItemMysticAxe) {
        		if(this==KCore.MysticLog) {
        			if(!worldIn.isRemote) {
            			worldIn.setBlockState(pos, KCore.Stripped_Mystic_Log.getDefaultState().withProperty(LOG_AXIS, state.getValue(LOG_AXIS)));
            			stack.damageItem(1, playerIn);
        			}
            		return true;
        		}
        		if(this==KCore.ShinyLog) {
        			if(!worldIn.isRemote) {
	        			worldIn.setBlockState(pos, KCore.Stripped_Shiny_Log.getDefaultState().withProperty(LOG_AXIS, state.getValue(LOG_AXIS)));
	        			stack.damageItem(1, playerIn);
        			}
            		return true;
        		}
        		if(this==KCore.SoulLog) {
        			if(!worldIn.isRemote) {
	        			worldIn.setBlockState(pos, KCore.Stripped_Soul_Log.getDefaultState().withProperty(LOG_AXIS, state.getValue(LOG_AXIS)));
	        			stack.damageItem(1, playerIn);
        			}
            		return true;
        		}

        	}
    	return false;
    }

    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {LOG_AXIS});
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