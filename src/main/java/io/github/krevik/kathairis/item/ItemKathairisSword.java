package io.github.krevik.kathairis.item;

import io.github.krevik.kathairis.init.ModItemGroups;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemSword;

/**
 * @author Krevik
 */
public class ItemKathairisSword extends ItemSword {

	public ItemKathairisSword(IItemTier tier) {
		super(tier, 3 + (int) tier.getAttackDamage(), 1, new Properties().group(ModItemGroups.WEAPONS).maxStackSize(1));
	}

}
