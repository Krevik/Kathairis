package mod.krevik.kathairis.block;

import net.minecraft.block.BlockDoor;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.state.properties.DoorHingeSide;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.util.EnumFacing;

public class BlockKatharianDoors extends BlockDoor {

	public BlockKatharianDoors(Material material, SoundType soundType, float hardnessAndResistance) {
		super(Properties.create(material).sound(soundType).hardnessAndResistance(hardnessAndResistance));
		this.setDefaultState(this.stateContainer.getBaseState().with(FACING, EnumFacing.NORTH).with(OPEN, false).with(HINGE, DoorHingeSide.LEFT).with(POWERED, false).with(HALF, DoubleBlockHalf.LOWER));
	}

}
