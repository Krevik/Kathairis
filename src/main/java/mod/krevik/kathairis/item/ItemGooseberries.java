package mod.krevik.kathairis.item;

import mod.krevik.kathairis.init.ModBlocks;
import mod.krevik.kathairis.init.ModItemGroups;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemSeedFood;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;

public class ItemGooseberries extends ItemSeedFood {

	//TODO fix names
	IBlockState field_195972_b;

	public ItemGooseberries(int p_i48473_1_, float p_i48473_2_, Block p_i48473_3_) {
		super(p_i48473_1_, p_i48473_2_, p_i48473_3_, new Properties().group(ModItemGroups.FOOD));
		field_195972_b = p_i48473_3_.getDefaultState();
	}

	@Override
	public EnumActionResult onItemUse(ItemUseContext context) {
		IWorld iworld = context.getWorld();
		BlockPos blockpos = context.getPos().up();
		boolean canPlaceHere = ModBlocks.GOOSEBERRY_BUSH.isValidPosition(iworld.getBlockState(blockpos), iworld, blockpos);
		if (context.getFace() == EnumFacing.UP && iworld.isAirBlock(blockpos) && canPlaceHere) {
			iworld.setBlockState(blockpos, this.field_195972_b, 11);
			EntityPlayer entityplayer = context.getPlayer();
			ItemStack itemstack = context.getItem();
			if (entityplayer instanceof EntityPlayerMP) {
				CriteriaTriggers.PLACED_BLOCK.trigger((EntityPlayerMP) entityplayer, blockpos, itemstack);
			}

			itemstack.shrink(1);
			return EnumActionResult.SUCCESS;
		} else {
			return EnumActionResult.PASS;
		}
	}

}
