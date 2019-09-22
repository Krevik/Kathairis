package io.github.krevik.kathairis.block;

import net.minecraft.block.SoundType;
import net.minecraft.block.WallBlock;
import net.minecraft.block.material.Material;

/**
 * @author Krevik
 */
public class BlockKathairisWall extends WallBlock {

	public BlockKathairisWall(Material material, float hardnessAndResistance, SoundType soundType) {
		super(Properties.create(material).sound(soundType).hardnessAndResistance(hardnessAndResistance));
	}

}
