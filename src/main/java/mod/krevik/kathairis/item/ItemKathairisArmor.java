package mod.krevik.kathairis.item;

import mod.krevik.kathairis.init.ModItemGroups;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.ItemArmor;

public class ItemKathairisArmor extends ItemArmor {

	public ItemKathairisArmor(IArmorMaterial material, EntityEquipmentSlot slot) {
		super(material, slot, new Properties().group(ModItemGroups.ARMOR));
	}

}
