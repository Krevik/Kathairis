package io.github.krevik.kathairis.block;

import io.github.krevik.kathairis.init.ModItemGroups;
import io.github.krevik.kathairis.util.IItemGroupProvider;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.math.shapes.VoxelShape;

/**
 * @author Krevik
 */
public class BlockKathairisNightFlower extends BlockKathairisPlant implements IItemGroupProvider {

	public BlockKathairisNightFlower() {
		super(Properties.create(Material.PLANTS).hardnessAndResistance(0f).sound(SoundType.PLANT).lightValue(7));
	}

	@Override
	public ItemGroup getItemGroup() {
		return ModItemGroups.PLANTS;
	}

}
