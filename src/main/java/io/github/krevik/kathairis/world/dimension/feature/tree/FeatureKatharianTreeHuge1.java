package io.github.krevik.kathairis.world.dimension.feature.tree;

import io.github.krevik.kathairis.block.BlockGlowVines;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.Heightmap;

import java.util.Random;
import java.util.Set;

import static io.github.krevik.kathairis.init.ModBlocks.GLOWVINES;
import static io.github.krevik.kathairis.init.ModBlocks.KATHARIAN_DIRT;
import static io.github.krevik.kathairis.init.ModBlocks.KATHARIAN_GRASS;
import static io.github.krevik.kathairis.init.ModBlocks.MYSTIC_LEAVES;
import static io.github.krevik.kathairis.init.ModBlocks.MYSTIC_LOG;
/**
 * @author Krevik
 */
public class FeatureKatharianTreeHuge1 extends AbstractKatharianTreeFeature {

	public FeatureKatharianTreeHuge1() {
		super(true);
	}

	public boolean place(Set<BlockPos> changedBlocks, IWorld worldIn, Random rand, BlockPos position) {
		if (canGrowInto(worldIn, position) || canGrowInto(worldIn, position.down())) {
			int treeHeight = 8 + rand.nextInt(8);
			int posX = position.getX();
			int posY = position.getY();
			int posZ = position.getZ();
			BlockPos pos1 = new BlockPos(posX, posY, posZ);
			BlockPos pos2 = new BlockPos(posX + 1, posY, posZ);
			BlockPos pos3 = new BlockPos(posX, posY, posZ + 1);
			BlockPos pos4 = new BlockPos(posX + 1, posY, posZ + 1);
			makeRoot(worldIn, pos1, rand, false, false);
			makeRoot(worldIn, pos2, rand, true, false);
			makeRoot(worldIn, pos3, rand, false, true);
			makeRoot(worldIn, pos4, rand, true, true);
			for (int c = 0; c <= treeHeight; c++) {
				pos1 = new BlockPos(posX, posY + c, posZ);
				pos2 = new BlockPos(posX + 1, posY + c, posZ);
				pos3 = new BlockPos(posX, posY + c, posZ + 1);
				pos4 = new BlockPos(posX + 1, posY + c, posZ + 1);
				setBlocks(changedBlocks, worldIn, pos1, MYSTIC_LOG.getDefaultState());
				setBlocks(changedBlocks, worldIn, pos2, MYSTIC_LOG.getDefaultState());
				setBlocks(changedBlocks, worldIn, pos3, MYSTIC_LOG.getDefaultState());
				setBlocks(changedBlocks, worldIn, pos4, MYSTIC_LOG.getDefaultState());
				if (c >= 4 && rand.nextInt(10) == 0) {
					int k = rand.nextInt(4);
					switch (k) {
						case 0:
							doBranch(worldIn, pos1, rand, false, false, changedBlocks);
						case 1:
							doBranch(worldIn, pos2, rand, true, false, changedBlocks);
						case 2:
							doBranch(worldIn, pos3, rand, false, true, changedBlocks);
						case 3:
							doBranch(worldIn, pos4, rand, true, true, changedBlocks);
					}
				}
				if (c > treeHeight / 3 && rand.nextInt(12) == 0) {
					tryToPutVinesRandomly(worldIn, pos1, rand);
				}
			}
			pos1 = new BlockPos(posX, posY + treeHeight, posZ);
			pos2 = new BlockPos(posX + 1, posY + treeHeight, posZ);
			pos3 = new BlockPos(posX, posY + treeHeight, posZ + 1);
			pos4 = new BlockPos(posX + 1, posY + treeHeight, posZ + 1);
			for (int c = 0; c < 4; c++) {
				switch (c) {
					case 0:
						doTopBranch(worldIn, pos1, rand, false, false, changedBlocks);
					case 1:
						doTopBranch(worldIn, pos2, rand, true, false, changedBlocks);
					case 2:
						doTopBranch(worldIn, pos3, rand, false, true, changedBlocks);
					case 3:
						doTopBranch(worldIn, pos4, rand, true, true, changedBlocks);

				}
			}

			return true;
		}
		return false;
	}

	private void tryToPutVinesRandomly(IWorld world, BlockPos pos, Random random) {
		for (int x = -2; x <= 2; x++) {
			for (int y = -1; y <= 1; y++) {
				for (int z = -2; z <= 2; z++) {
					if (random.nextInt(6) == 0) {
						BlockPos tmp = new BlockPos(pos.getX() + x, pos.getY() + y, pos.getZ() + z);
						if (world.isAirBlock(tmp) && BlockGlowVines.canPlaceBlockAtStatic(world, tmp)) {
							world.setBlockState(tmp, GLOWVINES.getDefaultState(), 3);
						}
					}
				}
			}
		}
	}

	private void makeRoot(IWorld world, BlockPos pos, Random rand, boolean canIncreaseX, boolean canIncreaseZ) {
		int rootLength = 2 + rand.nextInt(5);
		int shiftX = 0;
		int shiftZ = 0;
		int currentY = pos.up(rand.nextInt(4)).getY();
		for (int c = 0; c < rootLength; c++) {
			if (canIncreaseX && !canIncreaseZ) {
				if (rand.nextInt(3) == 0) {
					shiftX++;
				}
				if (rand.nextInt(3) == 0) {
					shiftZ--;
				}
			}
			if (canIncreaseX && canIncreaseZ) {
				if (rand.nextInt(3) == 0) {
					shiftX++;
				}
				if (rand.nextInt(3) == 0) {
					shiftZ++;
				}
			}
			if (!canIncreaseX && canIncreaseZ) {
				if (rand.nextInt(3) == 0) {
					shiftX--;
				}
				if (rand.nextInt(3) == 0) {
					shiftZ++;
				}
			}
			if (!canIncreaseX && !canIncreaseZ) {
				if (rand.nextInt(3) == 0) {
					shiftX--;
				}
				if (rand.nextInt(3) == 0) {
					shiftZ--;
				}
			}
			BlockPos tmp = new BlockPos(pos.getX() + shiftX, pos.getY(), pos.getZ() + shiftZ);
			BlockPos ground = world.getHeight(Heightmap.Type.MOTION_BLOCKING, tmp);
			for (int cc = ground.getY(); cc <= currentY; cc++) {
				if (MathHelper.abs(currentY - ground.getY()) < 5) {
					BlockPos tmp2 = new BlockPos(ground.getX(), cc, ground.getZ());
					world.setBlockState(tmp2, MYSTIC_LOG.getDefaultState(), 2);
					if (rand.nextInt(3) == 0) {
						currentY--;
					}
					if (currentY < ground.getY()) {
						break;
					}
				} else {
					break;
				}
			}
		}
	}

	private void doTopBranch(IWorld world, BlockPos pos, Random rand, boolean canIncreaseX, boolean canIncreaseZ, Set<BlockPos> changedBlocks) {
		int branchLength = 4 + rand.nextInt(4);
		int shiftX = 0;
		int shiftY = 0;
		int shiftZ = 0;
		for (int counter = 0; counter <= branchLength; counter++) {
			if (canIncreaseX && !canIncreaseZ) {
				if (rand.nextInt(3) == 0) {
					shiftX++;
				}
				if (rand.nextInt(3) == 0) {
					shiftZ--;
				}
			}
			if (canIncreaseX && canIncreaseZ) {
				if (rand.nextInt(3) == 0) {
					shiftX++;
				}
				if (rand.nextInt(3) == 0) {
					shiftZ++;
				}
			}
			if (!canIncreaseX && canIncreaseZ) {
				if (rand.nextInt(3) == 0) {
					shiftX--;
				}
				if (rand.nextInt(3) == 0) {
					shiftZ++;
				}
			}
			if (!canIncreaseX && !canIncreaseZ) {
				if (rand.nextInt(3) == 0) {
					shiftX--;
				}
				if (rand.nextInt(3) == 0) {
					shiftZ--;
				}
			}
			if (rand.nextInt(2) == 0) {
				shiftY++;
			}
			BlockPos tmp = new BlockPos(pos.getX() + shiftX, pos.getY() + shiftY, pos.getZ() + shiftZ);
			setBlocks(changedBlocks, world, tmp, MYSTIC_LOG.getDefaultState());
			if (counter == branchLength) {
				generateTopCrown(world, tmp, (branchLength), rand);
			}
		}
	}

	private void generateTopCrown(IWorld world, BlockPos pos, int radius, Random random) {
		IBlockState leaves = MYSTIC_LEAVES.getDefaultState();
		for (int x = -radius / 2; x <= radius / 2; x++) {
			for (int z = -radius / 2; z <= radius / 2; z++) {
				for (int y = -radius / 4; y <= radius; y++) {
					//do main crown
					BlockPos tmp = new BlockPos(pos.getX() + x, pos.getY() + y, pos.getZ() + z);
					if (((x * x) + (z * z) + (y * 2 * y * 2) <= (radius / 2 * radius / 2))) {
						if (world.getBlockState(tmp) == Blocks.AIR.getDefaultState()) {
							world.setBlockState(tmp, leaves, 2);
						}
					}
					//add some hanging blocks
					if (((x * x) + (z * z) + (y * 2 * y * 2) == (radius / 2 * radius / 2)) && world.getBlockState(tmp.down()).getBlock() == Blocks.AIR) {
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

	private void doBranch(IWorld world, BlockPos pos, Random rand, boolean canIncreaseX, boolean canIncreaseZ, Set<BlockPos> changedBlocks) {
		int branchLength = 4 + rand.nextInt(4);
		int shiftX = 0;
		int shiftY = 0;
		int shiftZ = 0;
		for (int counter = 0; counter <= branchLength; counter++) {
			if (canIncreaseX && !canIncreaseZ) {
				if (rand.nextInt(3) == 0) {
					shiftX++;
				}
				if (rand.nextInt(3) == 0) {
					shiftZ--;
				}
			}
			if (canIncreaseX && canIncreaseZ) {
				if (rand.nextInt(3) == 0) {
					shiftX++;
				}
				if (rand.nextInt(3) == 0) {
					shiftZ++;
				}
			}
			if (!canIncreaseX && canIncreaseZ) {
				if (rand.nextInt(3) == 0) {
					shiftX--;
				}
				if (rand.nextInt(3) == 0) {
					shiftZ++;
				}
			}
			if (!canIncreaseX && !canIncreaseZ) {
				if (rand.nextInt(3) == 0) {
					shiftX--;
				}
				if (rand.nextInt(3) == 0) {
					shiftZ--;
				}
			}
			if (rand.nextInt(2) == 0) {
				shiftY++;
			}
			BlockPos tmp = new BlockPos(pos.getX() + shiftX, pos.getY() + shiftY, pos.getZ() + shiftZ);
			setBlocks(changedBlocks, world, tmp, MYSTIC_LOG.getDefaultState());
			if (counter == branchLength) {
				generateCrown(world, tmp, (int) (branchLength / 1.5), rand);
			}
		}
	}

	private void generateCrown(IWorld world, BlockPos pos, int radius, Random random) {
		IBlockState leaves = MYSTIC_LEAVES.getDefaultState();
		for (int x = -radius / 2; x <= radius / 2; x++) {
			for (int z = -radius / 2; z <= radius / 2; z++) {
				for (int y = -radius / 4; y <= radius; y++) {
					//do main crown
					BlockPos tmp = new BlockPos(pos.getX() + x, pos.getY() + y, pos.getZ() + z);
					if (((x * x) + (z * z) + (y * 2 * y * 2) <= (radius / 2 * radius / 2))) {
						if (world.getBlockState(tmp) == Blocks.AIR.getDefaultState()) {
							world.setBlockState(tmp, leaves, 2);
						}
					}
					//add some hanging blocks
					if (((x * x) + (z * z) + (y * 2 * y * 2) == (radius / 2 * radius / 2)) && world.getBlockState(tmp.down()).getBlock() == Blocks.AIR) {
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
