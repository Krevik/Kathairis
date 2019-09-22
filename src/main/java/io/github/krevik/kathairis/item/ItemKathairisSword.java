package io.github.krevik.kathairis.item;

import io.github.krevik.kathairis.init.ModItemGroups;
import net.minecraft.item.IItemTier;
import net.minecraft.item.SwordItem;

/**
 * @author Krevik
 */
public class ItemKathairisSword extends SwordItem {

	public ItemKathairisSword(IItemTier tier) {
		super(tier, 3 + (int) tier.getAttackDamage(), 1, new Properties().group(ModItemGroups.WEAPONS).maxStackSize(1));
	}

}
