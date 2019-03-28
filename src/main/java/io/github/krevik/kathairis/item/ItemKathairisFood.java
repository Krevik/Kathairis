package io.github.krevik.kathairis.item;

import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemGroup;

public class ItemKathairisFood extends ItemFood {

	//TODO COMPLETE
	public ItemKathairisFood(int healAmountIn, float saturation, boolean meat, ItemGroup group) {
		super(healAmountIn, saturation, meat, new Properties().group(group));
	}

}
