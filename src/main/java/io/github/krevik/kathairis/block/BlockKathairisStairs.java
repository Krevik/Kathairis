package io.github.krevik.kathairis.block;

import io.github.krevik.kathairis.init.ModItemGroups;
import io.github.krevik.kathairis.util.IItemGroupProvider;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemGroup;

/**
 * @author Krevik
 */
public class BlockKathairisStairs extends StairsBlock implements IBucketPickupHandler, ILiquidContainer, IItemGroupProvider {

	public BlockKathairisStairs(BlockState modelState, Material material, float hardnessResistance, SoundType soundType) {
		super(modelState, Properties.create(material).sound(soundType).hardnessAndResistance(hardnessResistance));
	}

	@Override
	public ItemGroup getItemGroup() {
		return ModItemGroups.BUILDING_BLOCKS;
	}
}
