package io.github.krevik.kathairis.world.dimension.feature.tree;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;

import java.util.Random;
import java.util.Set;

import static io.github.krevik.kathairis.init.ModBlocks.KATHARIAN_DIRT;
import static io.github.krevik.kathairis.init.ModBlocks.KATHARIAN_GRASS;
import static io.github.krevik.kathairis.init.ModBlocks.MYSTIC_LEAVES;
import static io.github.krevik.kathairis.init.ModBlocks.MYSTIC_LOG;

/**
 * @author Krevik
 */
public class FeatureKatharianTallTree2 extends AbstractKatharianTreeFeature {

	public FeatureKatharianTallTree2() {
		super(true);
	}

	@Override
	public boolean place(Set<BlockPos> changedBlocks, IWorld worldIn, Random rand, BlockPos pos) {
		if (canGrowInto(worldIn, pos.down())) {
			int treeHeight = 5 + rand.nextInt(7);
			for (int c = 0; c <= treeHeight; c++) {
				setBlocks(changedBlocks, worldIn, new BlockPos(pos.getX(), pos.getY() + c, pos.getZ()), MYSTIC_LOG.getDefaultState());
			}

			generateCrown(worldIn, new BlockPos(pos.getX(), pos.getY() + treeHeight, pos.getZ()), (int) (1 + (treeHeight / 1.5)), rand);
			return true;
		}

		return false;
	}

	private void generateCrown(IWorld world, BlockPos pos, int radius, Random random) {
		IBlockState leaves = MYSTIC_LEAVES.getDefaultState();
		for (int x = -radius / 2; x <= radius / 2; x++) {
			for (int z = -radius / 2; z <= radius / 2; z++) {
				for (int y = -radius / 4; y <= radius; y++) {
					//do main crown
					BlockPos tmp = new BlockPos(pos.getX() + x, pos.getY() + y, pos.getZ() + z);
					if (((x * x) + (z * z) + (y * y) <= (radius / 2 * radius / 2))) {
						if (world.getBlockState(tmp) == Blocks.AIR.getDefaultState()) {
							world.setBlockState(tmp, leaves, 2);
						}
					}
					//add some hanging blocks
					if (((x * x) + (z * z) + (y * y) == (radius / 2 * radius / 2)) && world.getBlockState(tmp.down()).getBlock() == Blocks.AIR) {
						world.setBlockState(tmp.down(), MYSTIC_LEAVES.getDefaultState(), 2);
						if (random.nextInt(3) == 0 && world.getBlockState(tmp.down(2)).getBlock() == Blocks.AIR) {
							world.setBlockState(tmp.down(2), MYSTIC_LEAVES.getDefaultState(), 2);
							if (random.nextInt(3) == 0 && world.getBlockState(tmp.down(3)).getBlock() == Blocks.AIR) {
								world.setBlockState(tmp.down(3), MYSTIC_LEAVES.getDefaultState(), 2);
							}
						}
					}
				}
			}
		}
	}

	@Override
	protected boolean canGrowInto(net.minecraft.world.IBlockReader world, BlockPos pos) {
		IBlockState iblockstate = world.getBlockState(pos);
		return iblockstate.isIn(BlockTags.LEAVES) || iblockstate.getBlock() == Blocks.GRASS_BLOCK ||
				Block.isDirt(iblockstate.getBlock()) || iblockstate.isIn(BlockTags.LOGS) || iblockstate.isIn(BlockTags.SAPLINGS) ||
				iblockstate.getBlock() == Blocks.VINE || iblockstate.getBlock() == KATHARIAN_GRASS ||
				iblockstate.getBlock() == KATHARIAN_DIRT;
	}

	protected final void setBlocks(Set<BlockPos> changedBlocks, IWorld worldIn, BlockPos p_208520_3_, IBlockState p_208520_4_) {
		this.func_208521_b(worldIn, p_208520_3_, p_208520_4_);
		changedBlocks.add(p_208520_3_.toImmutable());

	}

	private void func_208521_b(IWorld p_208521_1_, BlockPos p_208521_2_, IBlockState p_208521_3_) {
		if (this.doBlockNotify) {
			p_208521_1_.setBlockState(p_208521_2_, p_208521_3_, 19);
		} else {
			p_208521_1_.setBlockState(p_208521_2_, p_208521_3_, 18);
		}
	}

}
