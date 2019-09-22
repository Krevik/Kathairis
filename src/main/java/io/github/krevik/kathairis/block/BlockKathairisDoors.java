package io.github.krevik.kathairis.block;

import net.minecraft.block.DoorBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.state.properties.DoorHingeSide;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.util.Direction;

/**
 * @author Krevik
 */
public class BlockKathairisDoors extends DoorBlock {

	public BlockKathairisDoors(Material material, SoundType soundType, float hardnessAndResistance) {
		super(Properties.create(material).sound(soundType).hardnessAndResistance(hardnessAndResistance));
		this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.NORTH).with(OPEN, false).with(HINGE, DoorHingeSide.LEFT).with(POWERED, false).with(HALF, DoubleBlockHalf.LOWER));
	}

}
