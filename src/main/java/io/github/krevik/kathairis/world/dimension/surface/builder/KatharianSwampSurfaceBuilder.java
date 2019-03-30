package io.github.krevik.kathairis.world.dimension.surface.builder;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.surfacebuilders.ISurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

import java.util.Random;

/**
 * @author Krevik
 */
public class KatharianSwampSurfaceBuilder implements ISurfaceBuilder<SurfaceBuilderConfig> {

	public void buildSurface(Random random, IChunk chunkIn, Biome biomeIn, int x, int z, int startHeight, double noise, IBlockState defaultBlock, IBlockState defaultFluid, int seaLevel, long seed, SurfaceBuilderConfig config) {
		double d0 = Biome.INFO_NOISE.getValue((double) x * 0.25D, (double) z * 0.25D);
		if (d0 > 0.0D) {
			int i = x & 15;
			int j = z & 15;
			BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

			for (int k = startHeight; k >= 0; --k) {
				blockpos$mutableblockpos.setPos(i, k, j);
				if (!chunkIn.getBlockState(blockpos$mutableblockpos).isAir()) {
					if (k == 62 && chunkIn.getBlockState(blockpos$mutableblockpos).getBlock() != defaultFluid.getBlock()) {
						chunkIn.setBlockState(blockpos$mutableblockpos, defaultFluid, false);
					}
					break;
				}
			}
		}

		Biome.DEFAULT_SURFACE_BUILDER.buildSurface(random, chunkIn, biomeIn, x, z, startHeight, noise, defaultBlock, defaultFluid, seaLevel, seed, config);
	}

}
