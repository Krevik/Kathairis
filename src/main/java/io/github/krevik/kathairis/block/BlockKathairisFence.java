package io.github.krevik.kathairis.block;

import net.minecraft.block.FenceBlock;
import net.minecraft.block.IBucketPickupHandler;
import net.minecraft.block.ILiquidContainer;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

/**
 * @author Krevik
 */
public class BlockKathairisFence extends FenceBlock implements IBucketPickupHandler, ILiquidContainer {

	public BlockKathairisFence(Material material, float hardnessAndResistance, SoundType soundType) {
		super(Properties.create(material).hardnessAndResistance(hardnessAndResistance).sound(soundType));
	}

}
