package io.github.krevik.kathairis.block;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;

/**
 * @author Krevik
 */
public class BlockKathairisStairs extends StairsBlock implements IBucketPickupHandler, ILiquidContainer {

	public BlockKathairisStairs(BlockState modelState, Material material, float hardnessResistance, SoundType soundType) {
		super(modelState, Properties.create(material).sound(soundType).hardnessAndResistance(hardnessResistance));
	}
}
