package io.github.krevik.kathairis.block;

import io.github.krevik.kathairis.init.ModItemGroups;
import io.github.krevik.kathairis.util.IItemGroupProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.TrapDoorBlock;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemGroup;

/**
 * @author Cadiboo
 */
public class BlockKathairisTrapdoor extends TrapDoorBlock implements IItemGroupProvider {

	public BlockKathairisTrapdoor(Material material, SoundType soundType, float hardnessAndResistance) {
		super(Properties.create(material).sound(soundType).hardnessAndResistance(hardnessAndResistance));
	}

	@Override
	public ItemGroup getItemGroup() {
		return ModItemGroups.BUILDING_BLOCKS;
	}

}
