package io.github.krevik.kathairis.item;

import io.github.krevik.kathairis.init.ModItemGroups;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.ItemArmor;

/**
 * @author Cadiboo
 */
public class ItemKathairisArmor extends ItemArmor {

	public ItemKathairisArmor(IArmorMaterial material, EntityEquipmentSlot slot) {
		super(material, slot, new Properties().group(ModItemGroups.ARMOR));
	}

}
