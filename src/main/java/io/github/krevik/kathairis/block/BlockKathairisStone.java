package io.github.krevik.kathairis.block;

import io.github.krevik.kathairis.init.ModItemGroups;
import io.github.krevik.kathairis.util.IItemGroupProvider;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemGroup;

/**
 * @author Krevik
 */
public class BlockKathairisStone extends Block implements IItemGroupProvider {

	public BlockKathairisStone() {
		super(Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(2.5f, 2.5f));
	}

	@Override
	public ItemGroup getItemGroup() {
		return ModItemGroups.BUILDING_BLOCKS;
	}
}
