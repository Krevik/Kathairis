package io.github.krevik.kathairis.block;

import net.minecraft.block.FenceGateBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

/**
 * @author Krevik
 */
public class BlockKathairisFenceGate extends FenceGateBlock {

	public BlockKathairisFenceGate(Material material, float hardnessAndResistance, SoundType soundType) {
		super(Properties.create(material).hardnessAndResistance(hardnessAndResistance).sound(soundType));
	}

}
