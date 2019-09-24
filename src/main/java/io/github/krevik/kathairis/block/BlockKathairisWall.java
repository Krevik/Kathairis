package io.github.krevik.kathairis.block;

import io.github.krevik.kathairis.init.ModItemGroups;
import io.github.krevik.kathairis.util.IItemGroupProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.WallBlock;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemGroup;

/**
 * @author Krevik
 */
public class BlockKathairisWall extends WallBlock implements IItemGroupProvider {

	public BlockKathairisWall(Material material, float hardnessAndResistance, SoundType soundType) {
		super(Properties.create(material).sound(soundType).hardnessAndResistance(hardnessAndResistance));
	}

	@Override
	public ItemGroup getItemGroup() {
		return ModItemGroups.BUILDING_BLOCKS;
	}

}
