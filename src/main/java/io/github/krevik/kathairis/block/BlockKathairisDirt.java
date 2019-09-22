package io.github.krevik.kathairis.block;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

/**
 * @author Cadiboo
 */
public class BlockKathairisDirt extends Block {

	public BlockKathairisDirt() {
		super(Properties.create(Material.GOURD).sound(SoundType.GROUND).hardnessAndResistance(0.5F, 0.5F));
	}

}
