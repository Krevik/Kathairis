package io.github.krevik.kathairis.world.dimension.feature.tree;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.feature.AbstractTreeFeature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import static io.github.krevik.kathairis.init.ModBlocks.KATHAIRIS_DIRT;
import static io.github.krevik.kathairis.init.ModBlocks.KATHAIRIS_GRASS;

/**
 * @author Krevik
 */
public abstract class AbstractKathairisTreeFeature extends AbstractTreeFeature<NoFeatureConfig> {

	public AbstractKathairisTreeFeature(boolean notify) {
		super(notify);
	}

	@Override
	protected boolean canGrowInto(net.minecraft.world.IBlockReader world, BlockPos pos) {
		IBlockState iblockstate = world.getBlockState(pos);
		return iblockstate.isAir(world, pos) || iblockstate.isIn(BlockTags.LEAVES) || iblockstate.getBlock() == Blocks.GRASS_BLOCK ||
				Block.isDirt(iblockstate.getBlock()) || iblockstate.isIn(BlockTags.LOGS) || iblockstate.isIn(BlockTags.SAPLINGS) ||
				iblockstate.getBlock() == Blocks.VINE || iblockstate.getBlock() == KATHAIRIS_DIRT ||
				iblockstate.getBlock() == KATHAIRIS_GRASS;
	}

	@Override
	protected void setDirtAt(IWorld worldIn, BlockPos pos, BlockPos origin) {
		worldIn.setBlockState(pos, KATHAIRIS_DIRT.getDefaultState(), 3);
	}

}
