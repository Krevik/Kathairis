package mod.krevik.kathairis.item;

import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemSword;

public class ItemKathairisSword extends ItemSword {

	public ItemKathairisSword(IItemTier tier, ItemGroup group) {
		super(tier, 3 + (int) tier.getAttackDamage(), 1, new Properties().group(group).maxStackSize(1));
	}

}
