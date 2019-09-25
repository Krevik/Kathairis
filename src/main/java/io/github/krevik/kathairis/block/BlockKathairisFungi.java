package io.github.krevik.kathairis.block;

import io.github.krevik.kathairis.init.ModItemGroups;
import io.github.krevik.kathairis.util.IItemGroupProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemGroup;

/**
 * @author Cadiboo
 */
public class BlockKathairisFungi extends BlockKathairisPlant implements IItemGroupProvider {

	public BlockKathairisFungi() {
		super(Properties.create(Material.PLANTS).sound(SoundType.PLANT).hardnessAndResistance(0).lightValue(10).tickRandomly().doesNotBlockMovement());
	}

	@Override
	public ItemGroup getItemGroup() {
		return ModItemGroups.PLANTS;
	}

}
