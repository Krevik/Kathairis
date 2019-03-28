package mod.krevik.kathairis.item;

import mod.krevik.kathairis.init.ModBlocks;
import mod.krevik.kathairis.init.ModItemGroups;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemSeedFood;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

public class ItemMagicBeans extends ItemSeedFood {

	//TODO fix names
	IBlockState field_195972_b;

	public ItemMagicBeans(int p_i48473_1_, float p_i48473_2_, Block p_i48473_3_) {
		super(p_i48473_1_, p_i48473_2_, p_i48473_3_, new Properties().group(ModItemGroups.FOOD));
		setAlwaysEdible();
		field_195972_b = p_i48473_3_.getDefaultState();
	}

	@Override
	public EnumActionResult onItemUse(ItemUseContext context) {
		IWorld iworld = context.getWorld();
		BlockPos blockpos = context.getPos().up();
		boolean canPlaceHere = ModBlocks.MAGIC_BEANS.isValidPosition(iworld.getBlockState(blockpos), iworld, blockpos);
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

	public ItemStack onItemUseFinish(ItemStack p_77654_1_, World p_77654_2_, EntityLivingBase p_77654_3_) {

		setRandomPotionEffectOnPlayer((EntityPlayerMP) p_77654_3_);
		return super.onItemUseFinish(p_77654_1_, p_77654_2_, p_77654_3_);
	}

	private void setRandomPotionEffectOnPlayer(EntityPlayerMP ep) {
		//TODO FIXME FIX HARDCODED IDs!
		int[] allowedPotionIds = {1, 3, 4, 5, 6, 8, 10, 11, 12, 13, 14, 16, 21, 22, 23, 24, 25, 26};
		ep.addPotionEffect(new PotionEffect(Potion.getPotionById(allowedPotionIds[random.nextInt(allowedPotionIds.length)]), 300 + random.nextInt(2000), random.nextInt(5)));
	}

}
