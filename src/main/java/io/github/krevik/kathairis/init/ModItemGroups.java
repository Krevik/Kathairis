package io.github.krevik.kathairis.init;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;
import java.util.function.Supplier;

import static io.github.krevik.kathairis.init.ModBlocks.KATHAIRIS_MINI_GRASS;
import static io.github.krevik.kathairis.init.ModBlocks.KATHAIRIS_STONE;
import static io.github.krevik.kathairis.init.ModItems.*;
import static io.github.krevik.kathairis.util.ModReference.MOD_ID;

/**
 * @author Cadiboo
 */
public final class ModItemGroups {

	public static final ItemGroup BUILDING_BLOCKS = new ModItemGroup("building_blocks", () -> new ItemStack(KATHAIRIS_STONE));
	public static final ItemGroup PLANTS = new ModItemGroup("plants", () -> new ItemStack(KATHAIRIS_MINI_GRASS));
	public static final ItemGroup FOOD = new ModItemGroup("food", () -> new ItemStack(BISON_MEAT));
	public static final ItemGroup MATERIALS = new ModItemGroup("materials", () -> new ItemStack(MYSTIC_GEM));
	public static final ItemGroup MISCELLANEOUS = new ModItemGroup("miscellaneous", () -> new ItemStack(CRYSTAL_CLUSTER));
	public static final ItemGroup ARMOR = new ModItemGroup("armor", () -> new ItemStack(MYSTIC_CHESTPLATE));
	public static final ItemGroup TOOLS = new ModItemGroup("tools", () -> new ItemStack(MYSTIC_PICKAXE));
	public static final ItemGroup WEAPONS = new ModItemGroup("weapons", () -> new ItemStack(MYSTIC_SWORD));

	private static class ModItemGroup extends ItemGroup {

		@Nonnull
		private final Supplier<ItemStack> iconSupplier;

		public ModItemGroup(@Nonnull final String name, @Nonnull final Supplier<ItemStack> iconSupplier) {
			super(MOD_ID + "." + name);
			this.iconSupplier = iconSupplier;
		}

		@Nonnull
		@Override
		public ItemStack createIcon() {
			return iconSupplier.get();
		}

	}

}
