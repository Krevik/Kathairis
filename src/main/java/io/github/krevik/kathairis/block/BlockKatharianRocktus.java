package io.github.krevik.kathairis.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSand;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

public class BlockKatharianRocktus extends BlockKatharianPlant {

	public BlockKatharianRocktus() {
		super();
	}

	@Override
	protected boolean isValidGround(IBlockState state, IBlockReader worldIn, BlockPos pos) {
		Block block = state.getBlock();
		boolean can = false;
		can = block instanceof BlockSand || block instanceof BlockSoftSand || block instanceof BlockKatharianSand;
		return can;
	}

}
