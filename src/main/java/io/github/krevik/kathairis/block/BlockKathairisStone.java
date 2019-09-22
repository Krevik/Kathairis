package io.github.krevik.kathairis.block;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

/**
 * @author Krevik
 */
public class BlockKathairisStone extends Block {

	public BlockKathairisStone() {
		super(Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(2.5f, 2.5f));
	}

}
