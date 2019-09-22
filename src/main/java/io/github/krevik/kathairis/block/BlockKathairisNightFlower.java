package io.github.krevik.kathairis.block;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

/**
 * @author Krevik
 */
public class BlockKathairisNightFlower extends BlockKathairisPlant {

	public BlockKathairisNightFlower() {
		super(Properties.create(Material.PLANTS).hardnessAndResistance(0f).sound(SoundType.PLANT).lightValue(7));
	}

}
