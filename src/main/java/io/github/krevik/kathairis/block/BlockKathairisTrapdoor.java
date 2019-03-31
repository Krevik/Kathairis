package io.github.krevik.kathairis.block;

import net.minecraft.block.BlockTrapDoor;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

/**
 * @author Cadiboo
 */
public class BlockKathairisTrapdoor extends BlockTrapDoor {

	public BlockKathairisTrapdoor(Material material, SoundType soundType, float hardnessAndResistance) {
		super(Properties.create(material).sound(soundType).hardnessAndResistance(hardnessAndResistance));
	}

}
