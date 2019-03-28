package mod.krevik.kathairis.world.dimension.feature;

import net.minecraft.block.state.IBlockState;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.IChunkGenSettings;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.Random;

import static mod.krevik.kathairis.init.ModBlocks.KATHARIAN_DEAD_GRASS;

public class FeatureKatharianDeadBush extends Feature<NoFeatureConfig> {

	@Override
	public boolean place(IWorld world, IChunkGenerator<? extends IChunkGenSettings> generator, Random random, BlockPos pos, NoFeatureConfig config) {
		for (IBlockState iblockstate = world.getBlockState(pos); (iblockstate.isAir(world, pos) || iblockstate.isIn(BlockTags.LEAVES)) && pos.getY() > 0; iblockstate = world.getBlockState(pos)) {
			pos = pos.down();
		}

		IBlockState iblockstate1 = KATHARIAN_DEAD_GRASS.getDefaultState();

		for (int i = 0; i < 4; ++i) {
			BlockPos blockpos = pos.add(random.nextInt(8) - random.nextInt(8), random.nextInt(4) - random.nextInt(4), random.nextInt(8) - random.nextInt(8));
			if (world.isAirBlock(blockpos) && iblockstate1.isValidPosition(world, blockpos)) {
				world.setBlockState(blockpos, iblockstate1, 2);
			}
		}

		return true;
	}

}
