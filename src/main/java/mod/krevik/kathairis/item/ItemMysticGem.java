package mod.krevik.kathairis.item;

import mod.krevik.kathairis.init.ModBlocks;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.EnumActionResult;

import javax.annotation.Nonnull;

public class ItemMysticGem extends Item {

	public ItemMysticGem(ItemGroup group, EnumRarity rarity) {
		super(new Item.Properties().group(group).rarity(rarity));
	}

	@Nonnull
	@Override
	public EnumActionResult onItemUse(@Nonnull final ItemUseContext context) {
		if (!context.getWorld().isRemote) {
			if (ModBlocks.KATHARIAN_PORTAL.trySpawnPortal(context.getWorld(), context.getPos().up())) {
				ItemStack itemstack = context.getItem();
				itemstack.shrink(1);
			}
		}
		return EnumActionResult.SUCCESS;
	}

}
