package io.github.krevik.kathairis.block;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;

/**
 * @author Krevik
 */
public class BlockBisonStars extends BlockKathairisPlant {

	protected static final AxisAlignedBB BISON_STARS_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1D, 0.125D, 1D);

	public BlockBisonStars() {
		super();
	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		return VoxelShapes.create(BISON_STARS_AABB);
	}

}
