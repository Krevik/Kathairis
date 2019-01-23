package mod.krevik.kathairis.item;

import mod.krevik.kathairis.creativetab.ModCreativeTabs;
import mod.krevik.kathairis.util.ModUtil;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/**
 * The same as an Iron Ingot or a Gold Ingot but for our stuff
 *
 * @author Cadiboo
 */
public class ItemIngot extends Item {

	public ItemIngot() {
		ModUtil.setCreativeTab(this, ModCreativeTabs.MISC);
	}

	@Override
	public boolean isBeaconPayment(final ItemStack stack) {
		return true;
	}

}
