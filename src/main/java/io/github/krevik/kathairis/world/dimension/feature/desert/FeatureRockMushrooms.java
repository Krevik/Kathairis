package io.github.krevik.kathairis.world.dimension.feature.desert;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.IChunkGenSettings;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.Random;

import static io.github.krevik.kathairis.init.ModBlocks.KATHARIAN_SAND;
import static io.github.krevik.kathairis.init.ModBlocks.SOFT_SAND;
import static io.github.krevik.kathairis.init.ModBlocks.WEATHERED_ROCK;

public class FeatureRockMushrooms extends Feature<NoFeatureConfig> {

	@Override
	public boolean place(IWorld world, IChunkGenerator<? extends IChunkGenSettings> generator, Random random, BlockPos pos, NoFeatureConfig config) {
		if (world.getBlockState(pos.down()).getBlock() == SOFT_SAND || world.getBlockState(pos.down()).getBlock() == KATHARIAN_SAND) {
			int radius = 2 + random.nextInt(5);
			int mushroomHeight = 4 + random.nextInt(radius) + random.nextInt(4);
			//base
			for (int x = -radius / 2; x <= radius / 2; x++) {
				for (int z = -radius / 2; z <= radius / 2; z++) {
					for (int y = -5; y <= mushroomHeight; y++) {
						if ((x * x) + (z * z) <= (radius / 2 * radius / 2)) {
							BlockPos tmp = new BlockPos(pos.getX() + x, pos.getY() + y, pos.getZ() + z);
							if (world.isBlockLoaded(tmp)) {
								world.setBlockState(tmp, WEATHERED_ROCK.getDefaultState(), 2);
							}
						}
					}
				}
			}
			//head
			int headRadius = MathHelper.clamp(random.nextInt(9 - radius + 2), radius + 2, 8);
			for (int x = -headRadius / 2; x <= headRadius / 2; x++) {
				for (int y = 0; y <= headRadius / 2; y++) {
					for (int z = -headRadius / 2; z <= headRadius / 2; z++) {
						if ((x * x) + (y * y) + (z * z) <= (headRadius / 2 * headRadius / 2 + 1)) {
							BlockPos tmp = new BlockPos(pos.getX() + x, pos.getY() + y + mushroomHeight, pos.getZ() + z);
							if (world.isBlockLoaded(tmp)) {
								world.setBlockState(tmp, WEATHERED_ROCK.getDefaultState(), 2);
							}
						}
					}
				}
			}

			return true;
		}
		return false;
	}

}
