package mod.krevik.kathairis.creativetab;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.common.registry.GameRegistry.ItemStackHolder;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import java.util.function.Supplier;

import static mod.krevik.kathairis.util.ModReference.MOD_ID;

/**
 * All the creative tabs of our mod
 *
 * @author Cadiboo
 */

//Stop IDEs telling us that ItemStackHolder fields can be private. They can't
@SuppressWarnings("WeakerAccess")
public final class ModCreativeTabs {

	@ItemStackHolder(MOD_ID + ":" + "nothingyet")
	public static final ItemStack BLOCKS_ICON_ITEMSTACK = null;
	@ItemStackHolder(MOD_ID + ":" + "nothingyet")
	public static final ItemStack FOOD_ICON_ITEMSTACK = null;
	@ItemStackHolder(MOD_ID + ":" + "nothingyet")
	public static final ItemStack PLANTS_ICON_ITEMSTACK = null;
	@ItemStackHolder(MOD_ID + ":" + "nothingyet")
	public static final ItemStack WEAPONS_ICON_ITEMSTACK = null;
	@ItemStackHolder(MOD_ID + ":" + "nothingyet")
	public static final ItemStack ARMOR_ICON_ITEMSTACK = null;
	@ItemStackHolder(MOD_ID + ":" + "nothingyet")
	public static final ItemStack TOOLS_ICON_ITEMSTACK = null;
	@ItemStackHolder(MOD_ID + ":" + "nothingyet")
	public static final ItemStack MISC_ICON_ITEMSTACK = null;

	/**
	 * Instantiate creative tabs
	 */
	public static final CustomCreativeTab BLOCKS = new CustomCreativeTab("blocks", () -> BLOCKS_ICON_ITEMSTACK, true);
	public static final CustomCreativeTab FOOD = new CustomCreativeTab("blocks", () -> FOOD_ICON_ITEMSTACK, true);
	public static final CustomCreativeTab PLANTS = new CustomCreativeTab("blocks", () -> PLANTS_ICON_ITEMSTACK, true);
	public static final CustomCreativeTab WEAPONS = new CustomCreativeTab("blocks", () -> WEAPONS_ICON_ITEMSTACK, true);
	public static final CustomCreativeTab ARMOR = new CustomCreativeTab("blocks", () -> ARMOR_ICON_ITEMSTACK, true);
	public static final CustomCreativeTab TOOLS = new CustomCreativeTab("blocks", () -> TOOLS_ICON_ITEMSTACK, true);
	public static final CustomCreativeTab MISC = new CustomCreativeTab("blocks", () -> MISC_ICON_ITEMSTACK, true);

	/**
	 * This class is used for an extra tab in the creative inventory. Many mods like to group their special items and blocks in a dedicated tab although it is also perfectly acceptable to put them in the vanilla tabs where it makes sense.
	 *
	 * @author jablear
	 * Modified by
	 * @author Cadiboo
	 */
	public static class CustomCreativeTab extends CreativeTabs {

		private final Supplier<ItemStack> iconSupplier;
		private final boolean hasSearchBar;

		CustomCreativeTab(final String name, final Supplier<ItemStack> iconSupplier, final boolean hasSearchBar) {
			super(MOD_ID + "." + name);
			this.iconSupplier = iconSupplier;
			this.hasSearchBar = hasSearchBar;
		}

		/**
		 * gets the {@link ItemStack} to display for the tab's icon
		 */
		@SideOnly(Side.CLIENT)
		@Override
		@Nonnull
		public ItemStack createIcon() {
			return iconSupplier.get();
		}

		@Override
		@Nonnull
		public String getBackgroundImageName() {
			if (this.hasSearchBar) {
				return "item_search.png";
			} else {
				return super.getBackgroundImageName();
			}
		}

		/**
		 * Useful for adding extra items such as full variants of energy related items
		 */
		@SideOnly(Side.CLIENT)
		@Override
		public void displayAllRelevantItems(@Nonnull final NonNullList<ItemStack> items) {
			super.displayAllRelevantItems(items);
		}

		@Override
		public boolean hasSearchBar() {
			return this.hasSearchBar;
		}

	}

}
