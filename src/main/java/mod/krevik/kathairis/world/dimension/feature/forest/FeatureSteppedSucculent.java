package mod.krevik.kathairis.world.dimension.feature.forest;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.IChunkGenSettings;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.Random;

import static mod.krevik.kathairis.init.ModBlocks.STEPPED_SUCCULENT;

public class FeatureSteppedSucculent extends Feature<NoFeatureConfig> {

	public FeatureSteppedSucculent() {
	}

	@Override
	public boolean place(IWorld world, IChunkGenerator<? extends IChunkGenSettings> generator, Random random, BlockPos pos, NoFeatureConfig config) {
		for (int chance = 0; chance < 10; ++chance) {
			BlockPos potentialPos = pos.add(random.nextInt(8) - random.nextInt(8), random.nextInt(4) - random.nextInt(4), random.nextInt(8) - random.nextInt(8));
			if (world.isAirBlock(potentialPos)) {
				int maxYAdd = 1 + random.nextInt(random.nextInt(3) + 1);

				for (int yAdd = 0; yAdd < maxYAdd; ++yAdd) {
					if (STEPPED_SUCCULENT.getDefaultState().isValidPosition(world, potentialPos)) {
						world.setBlockState(potentialPos.up(yAdd), STEPPED_SUCCULENT.getDefaultState(), 2);
					}
				}
			}
		}

		return true;
	}

}
