package mod.krevik.kathairis.world.dimension.feature.desert;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.IChunkGenSettings;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.Random;

public class FeatureDesertSandLayers extends Feature<NoFeatureConfig> {

	@Override
	public boolean place(IWorld world, IChunkGenerator<? extends IChunkGenSettings> generator, Random random, BlockPos pos, NoFeatureConfig config) {
		BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();
		BlockPos.MutableBlockPos blockpos$mutableblockpos1 = new BlockPos.MutableBlockPos();

		//FIXME TODO
//		for (int i = 0; i < 16; ++i) {
//			for (int j = 0; j < 16; ++j) {
//				int k = pos.getX() + i;
//				int l = pos.getZ() + j;
//				int i1 = world.getHeight(Heightmap.Type.MOTION_BLOCKING, k, l);
//				blockpos$mutableblockpos.setPos(k, i1, l);
//				blockpos$mutableblockpos1.setPos(blockpos$mutableblockpos).move(EnumFacing.DOWN, 1);
//				Biome biome = world.getBiome(blockpos$mutableblockpos);
//
//				if (biome == ModBiomes.BIOME_KATHARIAN_DESERT) {
//					world.setBlockState(blockpos$mutableblockpos, KBlocks.LAYERED_SAND.getDefaultState().with(BlockLayeredSand.LAYERS, 1 + p_212245_3_.nextInt(3)), 2);
//				}
//			}
//		}

		return true;
	}

}
