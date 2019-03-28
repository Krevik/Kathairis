package io.github.krevik.kathairis.block;

import net.minecraft.block.BlockTrapDoor;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class BlockKatharianTrapdoor extends BlockTrapDoor {

	public BlockKatharianTrapdoor(Material material, SoundType soundType, float hardnessAndResistance) {
		super(Properties.create(material).sound(soundType).hardnessAndResistance(hardnessAndResistance));
	}

}
