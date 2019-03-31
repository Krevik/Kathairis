package io.github.krevik.kathairis.world.dimension.feature.desert;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.IChunkGenSettings;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.Random;

import static io.github.krevik.kathairis.init.ModBlocks.KATHAIRIS_SUCCULENT;

/**
 * @author Krevik
 */
public class FeatureKathairisCactus extends Feature<NoFeatureConfig> {

	public FeatureKathairisCactus() {
	}

	@Override
	public boolean place(IWorld world, IChunkGenerator<? extends IChunkGenSettings> generator, Random rand, BlockPos pos, NoFeatureConfig config) {
		for (int chance = 0; chance < 10; ++chance) {
			BlockPos potentialPos = pos.add(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(8) - rand.nextInt(8));
			if (world.isAirBlock(potentialPos)) {
				int maxHeightUp = 1 + rand.nextInt(rand.nextInt(3) + 1);

				for (int heightUp = 0; heightUp < maxHeightUp; ++heightUp) {
					if (KATHAIRIS_SUCCULENT.getDefaultState().isValidPosition(world, potentialPos)) {
						world.setBlockState(potentialPos.up(heightUp), KATHAIRIS_SUCCULENT.getDefaultState(), 2);
					}
				}
			}
		}

		return true;
	}

}
