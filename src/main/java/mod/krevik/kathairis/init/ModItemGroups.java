package mod.krevik.kathairis.init;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;
import java.util.function.Supplier;

import static mod.krevik.kathairis.util.ModReference.MOD_ID;

/**
 * @author Cadiboo
 */
public final class ModItemGroups {

	public static final ItemGroup BUILDING_BLOCKS = new ModItemGroup("building_blocks", () -> new ItemStack(null));
	public static final ItemGroup FOOD = new ModItemGroup("food", () -> new ItemStack(null));

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
