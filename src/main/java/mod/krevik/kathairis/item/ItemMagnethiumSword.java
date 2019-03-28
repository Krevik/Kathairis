package mod.krevik.kathairis.item;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public class ItemMagnethiumSword extends ItemKathairisSword {

	public ItemMagnethiumSword(IItemTier tier, ItemGroup group) {
		super(tier);
	}

	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase entityLivingBase) {
		stack.damageItem(1, entityLivingBase);
		// FIXME TODO STOPSHIP: 2019-03-27 DON'T use hardcoded IDs
		target.addPotionEffect(new PotionEffect(Potion.getPotionById(25), 25, 1));
		return true;
	}

}
