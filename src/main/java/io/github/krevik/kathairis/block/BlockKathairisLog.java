package io.github.krevik.kathairis.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLog;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Rotation;

/**
 * @author Krevik
 */
public class BlockKathairisLog extends BlockLog {

	public static final EnumProperty<EnumFacing.Axis> AXIS = BlockStateProperties.AXIS;

	public BlockKathairisLog() {
		super(null, Properties.create(Material.WOOD).hardnessAndResistance(3.5f).sound(SoundType.WOOD));
		this.setDefaultState(this.getDefaultState().with(AXIS, EnumFacing.Axis.Y));
	}

	@Override
	public IBlockState rotate(IBlockState state, Rotation rot) {
		switch (rot) {
			case COUNTERCLOCKWISE_90:
			case CLOCKWISE_90:
				switch (state.get(AXIS)) {
					case X:
						return state.with(AXIS, EnumFacing.Axis.Z);
					case Z:
						return state.with(AXIS, EnumFacing.Axis.X);
					default:
						return state;
				}
			default:
				return state;
		}
	}

	protected void fillStateContainer(StateContainer.Builder<Block, IBlockState> builder) {
		builder.add(AXIS);
	}

	public IBlockState getStateForPlacement(BlockItemUseContext context) {
		return this.getDefaultState().with(AXIS, context.getFace().getAxis());
	}

}
