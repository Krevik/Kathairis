package mod.krevik.kathairis.world.dimension.feature.tree;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;

import java.util.Random;
import java.util.Set;

import static mod.krevik.kathairis.init.ModBlocks.KATHARIAN_DIRT;
import static mod.krevik.kathairis.init.ModBlocks.KATHARIAN_GRASS;
import static mod.krevik.kathairis.init.ModBlocks.MYSTIC_LEAVES;
import static mod.krevik.kathairis.init.ModBlocks.MYSTIC_LOG;

public class FeatureKatharianTallTree1 extends AbstractKatharianTreeFeature {

	public FeatureKatharianTallTree1() {
		super(true);
	}

	public boolean place(Set<BlockPos> changedBlocks, IWorld worldIn, Random rand, BlockPos position) {
		if (canGrowInto(worldIn, position) || canGrowInto(worldIn, position.down())) {
			int mainTrunkHeight = 6 + rand.nextInt(6);
			int branches = 2 + rand.nextInt(4);
			int posX = position.getX();
			int posY = position.getY();
			int posZ = position.getZ();
			for (int xx = 0; xx <= mainTrunkHeight; xx++) {
				setBlocks(changedBlocks, worldIn, new BlockPos(posX, posY + xx, posZ), MYSTIC_LOG.getDefaultState());
			}
			for (int c = 1; c <= branches; c++) {
				int branchHeight = 5 + rand.nextInt(6) - rand.nextInt(4);
				int shiftX = 0;
				int shiftZ = 0;
				int desiredShiftX = rand.nextInt(10) - 5;
				int desiredShiftZ = rand.nextInt(10) - 5;
				for (int height = 1; height <= branchHeight; height++) {
					if (shiftX <= desiredShiftX) {
						shiftX = shiftX + rand.nextInt(2);
					}
					if (shiftX >= desiredShiftX) {
						shiftX = shiftX - rand.nextInt(2);
					}
					if (shiftZ <= desiredShiftZ) {
						shiftZ = shiftZ + rand.nextInt(2);
					}
					if (shiftZ >= desiredShiftZ) {
						shiftZ = shiftZ - rand.nextInt(2);
					}
					BlockPos actualPos = new BlockPos(posX + shiftX, posY + height + mainTrunkHeight, posZ + shiftZ);
					setBlocks(changedBlocks, worldIn, actualPos, MYSTIC_LOG.getDefaultState());
					int actualLeavesRadius = 2;
					for (int x = -actualLeavesRadius / 2; x <= actualLeavesRadius / 2; x++) {
						for (int y = -actualLeavesRadius / 2; y <= actualLeavesRadius; y++) {
							for (int z = -actualLeavesRadius / 2; z <= actualLeavesRadius / 2; z++) {
								BlockPos tmp = new BlockPos(posX + shiftX + x, posY + mainTrunkHeight + height + y + 1, posZ + shiftZ + z);
								if (x * x + y * y + z * z <= actualLeavesRadius / 2 * actualLeavesRadius / 2 + 1) {
									if (worldIn.getBlockState(tmp) == Blocks.AIR.getDefaultState()) {
										worldIn.setBlockState(tmp, MYSTIC_LEAVES.getDefaultState(), 2);
									}
								}
							}
						}
					}
				}

			}

			return true;
		}
		return false;
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
