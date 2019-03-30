package io.github.krevik.kathairis.block;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReaderBase;

import static io.github.krevik.kathairis.init.ModBlocks.KATHARIAN_DIRT;
import static io.github.krevik.kathairis.init.ModBlocks.KATHARIAN_GRASS;
import static io.github.krevik.kathairis.init.ModBlocks.KATHARIAN_SAND;
import static io.github.krevik.kathairis.init.ModBlocks.SOFT_SAND;
import static io.github.krevik.kathairis.init.ModBlocks.WEATHERED_ROCK;

/**
 * @author Krevik
 */
public class BlockKatharianDeadGrass extends BlockKatharianPlant {

	public BlockKatharianDeadGrass() {
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
				block == Blocks.FARMLAND || block == KATHARIAN_DIRT || block == KATHARIAN_GRASS ||
				block == SOFT_SAND || block == KATHARIAN_SAND || block == WEATHERED_ROCK ||
				block == Blocks.SAND;
	}

}
