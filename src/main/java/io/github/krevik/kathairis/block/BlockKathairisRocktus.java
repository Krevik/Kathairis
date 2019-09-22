package io.github.krevik.kathairis.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SandBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

/**
 * @author Krevik
 */
public class BlockKathairisRocktus extends BlockKathairisPlant {

	public BlockKathairisRocktus() {
		super();
	}

	@Override
	protected boolean isValidGround(BlockState state, IBlockReader worldIn, BlockPos pos) {
		Block block = state.getBlock();
		boolean can = false;
		can = block instanceof SandBlock || block instanceof BlockSoftSand || block instanceof BlockKathairisSand;
		return can;
	}

}
