package io.github.krevik.kathairis.item;

import io.github.krevik.kathairis.init.ModBlocks;
import io.github.krevik.kathairis.init.ModItemGroups;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.EnumActionResult;

import javax.annotation.Nonnull;

public class ItemMysticGem extends Item {

	public ItemMysticGem() {
		super(new Item.Properties().group(ModItemGroups.MATERIALS).rarity(EnumRarity.EPIC));
	}

	@Nonnull
	@Override
	public EnumActionResult onItemUse(@Nonnull final ItemUseContext context) {
		if (!context.getWorld().isRemote) {
			if (ModBlocks.KATHARIS_PORTAL.trySpawnPortal(context.getWorld(), context.getPos().up())) {
				ItemStack itemstack = context.getItem();
				itemstack.shrink(1);
			}
		}
		return EnumActionResult.SUCCESS;
	}

}
