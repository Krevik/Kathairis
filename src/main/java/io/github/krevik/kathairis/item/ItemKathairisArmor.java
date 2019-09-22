package io.github.krevik.kathairis.item;

import io.github.krevik.kathairis.init.ModItemGroups;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;

/**
 * @author Cadiboo
 */
public class ItemKathairisArmor extends ArmorItem {

	public ItemKathairisArmor(IArmorMaterial material, EquipmentSlotType slot) {
		super(material, slot, new Properties().group(ModItemGroups.ARMOR));
	}

}
