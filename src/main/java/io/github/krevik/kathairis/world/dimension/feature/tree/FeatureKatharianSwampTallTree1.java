package io.github.krevik.kathairis.world.dimension.feature.tree;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;

import java.util.ArrayList;
import java.util.Random;
import java.util.Set;

import static io.github.krevik.kathairis.init.ModBlocks.ELDERWILLOW_LEAVES;
import static io.github.krevik.kathairis.init.ModBlocks.ELDERWILLOW_LOG;
import static io.github.krevik.kathairis.init.ModBlocks.KATHARIAN_DIRT;
import static io.github.krevik.kathairis.init.ModBlocks.KATHARIAN_GRASS;

/**
 * @author Krevik
 */
public class FeatureKatharianSwampTallTree1 extends AbstractKatharianTreeFeature {

	public FeatureKatharianSwampTallTree1() {
		super(true);
	}

	public boolean place(Set<BlockPos> changedBlocks, IWorld worldIn, Random rand, BlockPos position) {
		if (canGrowInto(worldIn, position) || canGrowInto(worldIn, position.down())) {
			int mainTrunkHeight = 7 + rand.nextInt(10);
			int thickTrunkHeight = 3 + rand.nextInt(3);
			int higherTrunkShiftX = 1 - rand.nextInt(3);
			int higherTrunkShiftZ = 1 - rand.nextInt(3);
			int posX = position.getX();
			int posY = position.getY();
			int posZ = position.getZ();
			ArrayList<BlockPos> availablePosesForRoots = new ArrayList<>();
			BlockPos tmp;
			//let's do thick base
			for (int x = -1; x <= 1; x++) {
				for (int z = -1; z <= 1; z++) {
					for (int y = 0; y <= thickTrunkHeight; y++) {
						tmp = new BlockPos(posX + x, posY + y, posZ + z);
						if (worldIn.isBlockLoaded(tmp)) {
							if (y > 1) {
								availablePosesForRoots.add(tmp);
							}
							if (y == thickTrunkHeight) {
								if (rand.nextInt(2) == 0) {
									setBlocks(changedBlocks, worldIn, tmp, ELDERWILLOW_LOG.getDefaultState());
								}
							} else {
								setBlocks(changedBlocks, worldIn, tmp, ELDERWILLOW_LOG.getDefaultState());
							}
						}
					}
				}
			}
			//rest of the trunk with some shift
			ArrayList<BlockPos> availableTrunkStartPoses = new ArrayList<>();
			int xLoopStart = -getStartLoopInts(higherTrunkShiftX, rand);
			int zLoopStart = -getStartLoopInts(higherTrunkShiftZ, rand);
			int xLoopEnd = getEndLoopInts(xLoopStart, rand);
			int zLoopEnd = getEndLoopInts(zLoopStart, rand);
			for (int x = xLoopStart; x <= xLoopEnd; x++) {
				for (int z = zLoopStart; z <= zLoopEnd; z++) {
					for (int y = thickTrunkHeight; y <= mainTrunkHeight; y++) {
						tmp = new BlockPos(posX + x + higherTrunkShiftX, posY + y, posZ + z + higherTrunkShiftZ);
						if (worldIn.isBlockLoaded(tmp)) {
							setBlocks(changedBlocks, worldIn, tmp, ELDERWILLOW_LOG.getDefaultState());
							availableTrunkStartPoses.add(tmp);
							if (y == mainTrunkHeight) {
								//doMainCrown
								generateCrown(worldIn, tmp.down(), 4 + rand.nextInt(6), rand);
							}
						}
					}
				}
			}
			//well, let's make the fucking constant roots......
			BlockPos rootPos1 = new BlockPos(posX + 1, posY + 2 + rand.nextInt(3), posZ + 1);
			BlockPos rootPos2 = new BlockPos(posX - 1, posY + 2 + rand.nextInt(3), posZ - 1);
			BlockPos rootPos3 = new BlockPos(posX - 1, posY + 2 + rand.nextInt(3), posZ + 1);
			BlockPos rootPos4 = new BlockPos(posX + 1, posY + 2 + rand.nextInt(3), posZ - 1);
			for (int rootsNumber = 0; rootsNumber < 4; rootsNumber++) {
				int length = 2 + rand.nextInt(4);
				if (rootsNumber == 0) {
					for (int c = 1; c <= length; c++) {
						int shiftX = c;
						int shiftZ = c;
						int shiftY = c;
						BlockPos rootPiecePos = new BlockPos(rootPos1.getX() + shiftX, rootPos1.getY() - c, rootPos1.getZ() + shiftZ);
						int dist = 0;
						for (int y = 0; y <= 9; y++) {
							dist = y;
							if (!worldIn.isAirBlock(rootPiecePos.down(y))) {
								break;
							}
						}
						if (dist < 8) {
							for (int cc = 0; cc <= dist; cc++) {
								setBlocks(changedBlocks, worldIn, rootPiecePos.down(cc), ELDERWILLOW_LOG.getDefaultState());
							}
						} else {
							break;
						}
					}
				}
				if (rootsNumber == 1) {
					for (int c = 1; c <= length; c++) {
						int shiftX = -c;
						int shiftZ = -c;
						int shiftY = c;
						BlockPos rootPiecePos = new BlockPos(rootPos2.getX() + shiftX, rootPos2.getY() - c, rootPos2.getZ() + shiftZ);
						int dist = 0;
						for (int y = 0; y <= 7; y++) {
							dist = y;
							if (!worldIn.isAirBlock(rootPiecePos.down(y))) {
								break;
							}
						}
						if (dist < 8) {
							for (int cc = 0; cc <= dist; cc++) {
								setBlocks(changedBlocks, worldIn, rootPiecePos.down(cc), ELDERWILLOW_LOG.getDefaultState());
							}
						} else {
							break;
						}
					}
				}
				if (rootsNumber == 2) {
					for (int c = 1; c <= length; c++) {
						int shiftX = -c;
						int shiftZ = c;
						int shiftY = c;
						BlockPos rootPiecePos = new BlockPos(rootPos3.getX() + shiftX, rootPos3.getY() - c, rootPos3.getZ() + shiftZ);
						int dist = 0;
						for (int y = 0; y <= 7; y++) {
							dist = y;
							if (!worldIn.isAirBlock(rootPiecePos.down(y))) {
								break;
							}
						}
						if (dist < 8) {
							for (int cc = 0; cc <= dist; cc++) {
								setBlocks(changedBlocks, worldIn, rootPiecePos.down(cc), ELDERWILLOW_LOG.getDefaultState());
							}
						} else {
							break;
						}
					}
				}
				if (rootsNumber == 3) {
					for (int c = 1; c <= length; c++) {
						int shiftX = c;
						int shiftZ = -c;
						int shiftY = c;
						BlockPos rootPiecePos = new BlockPos(rootPos4.getX() + shiftX, rootPos4.getY() - c, rootPos4.getZ() + shiftZ);
						int dist = 0;
						for (int y = 0; y <= 7; y++) {
							dist = y;
							if (!worldIn.isAirBlock(rootPiecePos.down(y))) {
								break;
							}
						}
						if (dist < 8) {
							for (int cc = 0; cc <= dist; cc++) {
								setBlocks(changedBlocks, worldIn, rootPiecePos.down(cc), ELDERWILLOW_LOG.getDefaultState());
							}
						} else {
							break;
						}
					}
				}
			}

			//branches and crowns :O
			int branchesNumber = 4 + rand.nextInt(5);
			for (int c = 0; c < branchesNumber; c++) {
				int shiftX = 0;
				int shiftZ = 0;
				int shiftY = 0;
				int randomIndex = rand.nextInt(availableTrunkStartPoses.size());
				BlockPos branchStartPos = availableTrunkStartPoses.get(randomIndex);
				availableTrunkStartPoses.remove(randomIndex);
				BlockPos trunkXZDirection = getBranchDirection(rand);
				int branchLength = 3 + rand.nextInt(4);
				for (int cc = 1; cc <= branchLength; cc++) {
					shiftX = trunkXZDirection.getX() * cc;
					shiftZ = trunkXZDirection.getZ() * cc;
					if (rand.nextInt(2) == 0) {
						shiftY++;
					}
					BlockPos actualPos = new BlockPos(branchStartPos.getX() + shiftX, branchStartPos.getY() + shiftY, branchStartPos.getZ() + shiftZ);
					if (worldIn.isBlockLoaded(actualPos)) {
						setBlocks(changedBlocks, worldIn, actualPos, ELDERWILLOW_LOG.getDefaultState());
						if (cc == branchLength) {
							generateCrown(worldIn, actualPos, 4 + rand.nextInt(5), rand);
						}
					} else {
						break;
					}
				}
			}

			return true;
		}
		return false;
	}

	private void generateCrown(IWorld world, BlockPos pos, int radius, Random random) {
		IBlockState leaves = ELDERWILLOW_LEAVES.getDefaultState();
		for (int x = -radius / 2; x <= radius / 2; x++) {
			for (int z = -radius / 2; z <= radius / 2; z++) {
				for (int y = -radius / 4; y <= radius; y++) {
					//do main crown
					BlockPos tmp = new BlockPos(pos.getX() + x, pos.getY() + y, pos.getZ() + z);
					if (world.isBlockLoaded(tmp)) {
						if (((x * x) + (z * z) + (y * y) <= (radius / 2 * radius / 2))) {
							if (world.getBlockState(tmp) == Blocks.AIR.getDefaultState()) {
								world.setBlockState(tmp, leaves, 2);
							}
						}
						//add some hanging blocks
						if (((x * x) + (z * z) + (y * y) == (radius / 2 * radius / 2)) && world.getBlockState(tmp.down()).getBlock() == Blocks.AIR) {
							world.setBlockState(tmp.down(), ELDERWILLOW_LEAVES.getDefaultState(), 2);
							for (int yy = 0; yy <= random.nextInt(6); yy++) {
								if (world.isAirBlock(tmp.down(yy))) {
									world.setBlockState(tmp.down(yy), ELDERWILLOW_LEAVES.getDefaultState(), 2);
								}
							}
						}
					} else {
						break;
					}
				}
			}
		}
	}

	private BlockPos getBranchDirection(Random random) {
		int X = 1 - random.nextInt(3);
		int Z = 1 - random.nextInt(3);
		if (X == 0 || Z == 0) {
			return getBranchDirection(random);
		}
		BlockPos result = new BlockPos(X, 0, Z);
		return result;
	}

	private int getStartLoopInts(int xz, Random random) {
		if (xz == 0) {
			return random.nextInt(2) == 0 ? -1 : 1;
		} else {
			return xz;
		}
	}

	private int getEndLoopInts(int xz, Random random) {
		if (xz == -1) {
			return 0;
		} else if (xz == 1) {
			return 2;
		} else {
			return 1;
		}
	}

	@Override
	protected boolean canGrowInto(net.minecraft.world.IBlockReader world, BlockPos pos) {
		IBlockState iblockstate = world.getBlockState(pos);
		return iblockstate.getBlock() == Blocks.GRASS_BLOCK ||
				Block.isDirt(iblockstate.getBlock()) || iblockstate.isIn(BlockTags.SAPLINGS) ||
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
