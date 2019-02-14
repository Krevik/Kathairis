package mod.krevik.block;

import mod.krevik.KCore;
import mod.krevik.item.ItemMysticAxe;
import mod.krevik.util.CreativeTabsMystic;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
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


    @Override
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

    @Override
    public int getMetaFromState(IBlockState state)
    {
        int i = 0;

        switch (state.getValue(LOG_AXIS))
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

    @Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, LOG_AXIS);
    }
    
    @Override
    public boolean canSustainLeaves(IBlockState state, IBlockAccess world, BlockPos pos)
    {
        return true;
    }

    @Override
    public int damageDropped(IBlockState state)
    {
        return 0;
    }
}