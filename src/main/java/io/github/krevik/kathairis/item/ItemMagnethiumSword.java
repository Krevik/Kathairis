package io.github.krevik.kathairis.item;

import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

/**
 * @author Krevik
 */
public class ItemMagnethiumSword extends ItemKathairisSword {

	public ItemMagnethiumSword(IItemTier tier, ItemGroup group) {
		super(tier);
	}

	@Override
	public boolean hitEntity(ItemStack stack, LivingEntity target, LivingEntity entityLivingBase) {
		stack.damageItem(1, entityLivingBase, (p_220045_0_) -> {
			p_220045_0_.sendBreakAnimation(EquipmentSlotType.MAINHAND);
		});
		target.addPotionEffect(new EffectInstance(Effects.LEVITATION, 100, 1));
		return true;
	}

}
