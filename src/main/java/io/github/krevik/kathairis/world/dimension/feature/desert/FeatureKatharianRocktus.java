package io.github.krevik.kathairis.world.dimension.feature.desert;

import net.minecraft.block.state.IBlockState;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.IChunkGenSettings;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import javax.annotation.Nonnull;
import java.util.Random;

import static io.github.krevik.kathairis.init.ModBlocks.ROCKTUS;

public class FeatureKatharianRocktus extends Feature<NoFeatureConfig> {

	@Override
	public boolean place(@Nonnull IWorld world, @Nonnull IChunkGenerator<? extends IChunkGenSettings> generator, @Nonnull Random random, @Nonnull BlockPos pos, @Nonnull NoFeatureConfig config) {
		for (IBlockState iblockstate = world.getBlockState(pos); (iblockstate.isAir(world, pos) || iblockstate.isIn(BlockTags.LEAVES)) && pos.getY() > 0; iblockstate = world.getBlockState(pos)) {
			pos = pos.down();
		}

		IBlockState iblockstate1 = ROCKTUS.getDefaultState();

		for (int i = 0; i < 4; ++i) {
			BlockPos blockpos = pos.add(random.nextInt(8) - random.nextInt(8), random.nextInt(4) - random.nextInt(4), random.nextInt(8) - random.nextInt(8));
			if (world.isAirBlock(blockpos) && iblockstate1.isValidPosition(world, blockpos)) {
				world.setBlockState(blockpos, iblockstate1, 2);
			}
		}

		return true;
	}

}
