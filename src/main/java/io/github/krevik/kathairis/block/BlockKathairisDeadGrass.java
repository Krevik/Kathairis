package io.github.krevik.kathairis.block;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReaderBase;

import static io.github.krevik.kathairis.init.ModBlocks.*;

/**
 * @author Krevik
 */
public class BlockKathairisDeadGrass extends BlockKathairisPlant {

	public BlockKathairisDeadGrass() {
		super();
	}

	@Override
	public boolean isValidPosition(IBlockState state, IWorldReaderBase worldIn, BlockPos pos) {
		BlockPos blockpos = pos.down();
		return this.isValidGround(worldIn.getBlockState(blockpos), worldIn, blockpos);
	}

	@Override
	protected boolean isValidGround(IBlockState state, IBlockReader worldIn, BlockPos pos) {
		Block block = state.getBlock();
		return block == Blocks.GRASS_BLOCK || block == Blocks.DIRT || block == Blocks.COARSE_DIRT || block == Blocks.PODZOL ||
				block == Blocks.FARMLAND || block == KATHAIRIS_DIRT || block == KATHAIRIS_GRASS ||
				block == SOFT_SAND || block == KATHAIRIS_SAND || block == WEATHERED_ROCK ||
				block == Blocks.SAND;
	}

}
