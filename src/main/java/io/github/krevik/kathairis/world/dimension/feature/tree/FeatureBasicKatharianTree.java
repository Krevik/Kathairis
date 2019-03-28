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

public class FeatureBasicKatharianTree extends AbstractKatharianTreeFeature {

	private final boolean useExtraRandomHeight;

	public FeatureBasicKatharianTree(boolean notify, boolean useExtraRandomHeightIn) {
		super(notify);
		this.useExtraRandomHeight = useExtraRandomHeightIn;
	}

	public boolean place(Set<BlockPos> changedBlocks, IWorld worldIn, Random rand, BlockPos position) {
		int i = rand.nextInt(3) + 5;
		if (this.useExtraRandomHeight) {
			i += rand.nextInt(7);
		}

		boolean flag = true;
		if (position.getY() >= 1 && position.getY() + i + 1 <= worldIn.getWorld().getHeight()) {
			for (int j = position.getY(); j <= position.getY() + 1 + i; ++j) {
				int k = 1;
				if (j == position.getY()) {
					k = 0;
				}

				if (j >= position.getY() + 1 + i - 2) {
					k = 2;
				}

				BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

				for (int l = position.getX() - k; l <= position.getX() + k && flag; ++l) {
					for (int i1 = position.getZ() - k; i1 <= position.getZ() + k && flag; ++i1) {
						if (j >= 0 && j < worldIn.getWorld().getHeight()) {
							if (!canGrowInto(worldIn, blockpos$mutableblockpos.setPos(l, j, i1))) {
								flag = false;
							}
						} else {
							flag = false;
						}
					}
				}
			}

			if (!flag) {
				return false;
			} else {
				boolean isSoil = canGrowInto(worldIn, position.down());
				if (isSoil && position.getY() < worldIn.getWorld().getHeight() - i - 1) {
					this.setDirtAt(worldIn, position.down(), position);

					for (int i2 = position.getY() - 3 + i; i2 <= position.getY() + i; ++i2) {
						int k2 = i2 - (position.getY() + i);
						int l2 = 1 - k2 / 2;

						for (int i3 = position.getX() - l2; i3 <= position.getX() + l2; ++i3) {
							int j1 = i3 - position.getX();

							for (int k1 = position.getZ() - l2; k1 <= position.getZ() + l2; ++k1) {
								int l1 = k1 - position.getZ();
								if (Math.abs(j1) != l2 || Math.abs(l1) != l2 || rand.nextInt(2) != 0 && k2 != 0) {
									BlockPos blockpos = new BlockPos(i3, i2, k1);
									IBlockState iblockstate = worldIn.getBlockState(blockpos);
									if (iblockstate.isAir(worldIn, blockpos) || iblockstate.isIn(BlockTags.LEAVES)) {
										this.setBlockState(worldIn, blockpos, MYSTIC_LEAVES.getDefaultState());
									}
								}
							}
						}
					}

					for (int j2 = 0; j2 < i; ++j2) {
						IBlockState iblockstate1 = worldIn.getBlockState(position.up(j2));
						if (iblockstate1.isAir(worldIn, position.up(j2)) || iblockstate1.isIn(BlockTags.LEAVES)) {
							this.setLogState(changedBlocks, worldIn, position.up(j2), MYSTIC_LOG.getDefaultState());
						}
					}

					return true;
				} else {
					return false;
				}
			}
		} else {
			return false;
		}
	}

	@Override
	protected boolean canGrowInto(net.minecraft.world.IBlockReader world, BlockPos pos) {
		IBlockState iblockstate = world.getBlockState(pos);
		return iblockstate.isAir(world, pos) || iblockstate.isIn(BlockTags.LEAVES) || iblockstate.getBlock() == Blocks.GRASS_BLOCK ||
				Block.isDirt(iblockstate.getBlock()) || iblockstate.isIn(BlockTags.LOGS) || iblockstate.isIn(BlockTags.SAPLINGS) ||
				iblockstate.getBlock() == Blocks.VINE || iblockstate.getBlock() == KATHARIAN_GRASS ||
				iblockstate.getBlock() == KATHARIAN_DIRT;
	}

}
