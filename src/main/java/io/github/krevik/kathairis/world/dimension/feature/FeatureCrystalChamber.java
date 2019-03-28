package io.github.krevik.kathairis.world.dimension.feature;

import io.github.krevik.kathairis.block.BlockCrystal;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.IChunkGenSettings;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.Random;

import static io.github.krevik.kathairis.init.ModBlocks.BLUE_CRYSTAL;
import static io.github.krevik.kathairis.init.ModBlocks.KATHARIAN_STONE;
import static io.github.krevik.kathairis.init.ModBlocks.VIOLET_CRYSTAL;
import static io.github.krevik.kathairis.init.ModBlocks.YELLOW_CRYSTAL;

public class FeatureCrystalChamber extends Feature<NoFeatureConfig> {

	@Override
	public boolean place(IWorld world, IChunkGenerator<? extends IChunkGenSettings> c, Random rand, BlockPos pos, NoFeatureConfig p_212245_5_) {
		if (rand.nextInt(10) == 0) {
			int height = 10 + rand.nextInt(10) + rand.nextInt(10) + rand.nextInt(10) + rand.nextInt(10) + rand.nextInt(10);
			int posX = pos.getX();
			int posZ = pos.getZ();
			int diameter = 3 + rand.nextInt(6);
			boolean shouldContinue = true;
			for (int x = -diameter / 2; x <= diameter / 2; x++) {
				if (shouldContinue) {
					for (int y = -diameter / 2; y <= diameter / 2; y++) {
						for (int z = -diameter / 2; z <= diameter / 2; z++) {
							if ((x * x) + (y * y) + (z * z) <= ((diameter / 2 * diameter / 2) + 1)) {
								BlockPos tmp = new BlockPos(posX + x, height + y, posZ + z);
								if (world.isBlockLoaded(tmp)) {
									world.setBlockState(tmp, Blocks.CAVE_AIR.getDefaultState(), 2);
								} else {
									shouldContinue = false;
									break;
								}
							}
						}
					}
				}
			}
			shouldContinue = true;
			for (int x = -diameter / 2; x <= diameter / 2; x++) {
				if (shouldContinue) {
					for (int y = -diameter / 2; y <= diameter / 2; y++) {
						for (int z = -diameter / 2; z <= diameter / 2; z++) {
							if ((x * x) + (y * y) + (z * z) >= ((diameter / 2 * diameter / 2)) - 3) {
								BlockPos tmp = new BlockPos(posX + x, height + y, posZ + z);
								if (world.isBlockLoaded(tmp)) {
									IBlockState crystalState = pickupRandomCrystal(rand);
									EnumFacing facing = getRandomFacing(rand);
									if (world.isAirBlock(tmp) && crystalState.isValidPosition(world, tmp)) {
										if (isStoneAround(world, tmp)) {
											world.setBlockState(tmp, crystalState.with(BlockCrystal.FACING, facing), 2);
										}
									}
								} else {
									shouldContinue = false;
									break;
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

	private boolean isStoneAround(IWorld world, BlockPos pos) {
		int posX = pos.getX();
		int posY = pos.getY();
		int posZ = pos.getZ();
		boolean result = false;
		for (int x = -1; x <= 1; x++) {
			for (int y = -1; y <= 1; y++) {
				for (int z = -1; z <= 1; z++) {
					BlockPos tmp = new BlockPos(posX + x, posY + y, posZ + z);
					if (world.isBlockLoaded(tmp)) {
						if (world.getBlockState(tmp).getBlock() == KATHARIAN_STONE) {
							result = true;
						}
					}
				}
			}
		}
		return result;
	}

	private EnumFacing getRandomFacing(Random random) {
		EnumFacing facing = random.nextInt(2) == 0 ? EnumFacing.DOWN : EnumFacing.UP;
		int k = random.nextInt(6);
		switch (k) {
			case 0:
				facing = EnumFacing.NORTH;
			case 1:
				facing = EnumFacing.SOUTH;
			case 2:
				facing = EnumFacing.EAST;
			case 3:
				facing = EnumFacing.WEST;
			case 5:
				facing = EnumFacing.UP;
			case 6:
				facing = EnumFacing.DOWN;
		}
		return facing;
	}

	private IBlockState pickupRandomCrystal(Random random) {
		int k = random.nextInt(3);
		switch (k) {
			case 0:
				return BLUE_CRYSTAL.getDefaultState();
			case 1:
				return YELLOW_CRYSTAL.getDefaultState();
			case 2:
				return VIOLET_CRYSTAL.getDefaultState();
		}
		return BLUE_CRYSTAL.getDefaultState();
	}

}
