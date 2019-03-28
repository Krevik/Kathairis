package mod.krevik.kathairis.item;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.stats.StatList;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

public class ItemFungalDrug extends ItemKathairisFood {

	public ItemFungalDrug(String Name, int healAmountIn, float saturation, boolean meat, ItemGroup group) {
		super(healAmountIn, saturation, meat, group);
	}

	@Override
	//TODO REMOVE SOME BAD EFFECTS
	public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving) {
		if (entityLiving instanceof EntityPlayer) {
			EntityPlayer entityplayer = (EntityPlayer) entityLiving;
			entityplayer.getFoodStats().addStats(this, stack);
			worldIn.playSound(null, entityplayer.posX, entityplayer.posY, entityplayer.posZ, SoundEvents.ENTITY_PLAYER_BURP, SoundCategory.PLAYERS, 0.5F, worldIn.rand.nextFloat() * 0.1F + 0.9F);
			this.onFoodEaten(stack, worldIn, entityplayer);
			entityplayer.addStat(StatList.ITEM_USED.get(this));
			//nausea effect
			entityplayer.addPotionEffect(new PotionEffect(Potion.getPotionById(9), 400, 3));
			if (entityplayer instanceof EntityPlayerMP) {
				CriteriaTriggers.CONSUME_ITEM.trigger((EntityPlayerMP) entityplayer, stack);
			}
		}

		stack.shrink(1);
		return stack;
	}

}
