package io.github.krevik.kathairis.item;

import io.github.krevik.kathairis.init.ModBlocks;
import io.github.krevik.kathairis.init.ModItemGroups;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.item.Rarity;
import net.minecraft.util.ActionResultType;

import javax.annotation.Nonnull;

/**
 * @author Krevik
 */
public class ItemMysticGem extends Item {

	public ItemMysticGem() {
		super(new Properties().group(ModItemGroups.MATERIALS).rarity(Rarity.EPIC));
	}

	@Nonnull
	@Override
	public ActionResultType onItemUse(@Nonnull final ItemUseContext context) {
		if (!context.getWorld().isRemote) {
			if (ModBlocks.KATHAIRIS_PORTAL.trySpawnPortal(context.getWorld(), context.getPos().up())) {
				ItemStack itemstack = context.getItem();
				itemstack.shrink(1);
			}
		}
		return ActionResultType.SUCCESS;
	}

}
