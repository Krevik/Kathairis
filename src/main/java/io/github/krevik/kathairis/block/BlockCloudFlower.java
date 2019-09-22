package io.github.krevik.kathairis.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

/**
 * @author Krevik
 */
public class BlockCloudFlower extends BlockKathairisPlant {

	public BlockCloudFlower() {
		super();
	}

	@Override
	protected boolean isValidGround(BlockState state, IBlockReader worldIn, BlockPos pos) {
		Block block = state.getBlock();
		return block instanceof BlockKathairisCloud || block instanceof BlockRefinedCloud || block instanceof BlockCondensedCloud;
	}

}
