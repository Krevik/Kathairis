package io.github.krevik.kathairis.world.dimension.feature;

import io.github.krevik.kathairis.init.ModBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.surfacebuilders.ISurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

import java.util.Random;

public class KatharianDesertEdgeSurfaceBuilder implements ISurfaceBuilder<SurfaceBuilderConfig> {

    @Override
    public void buildSurface(Random random, IChunk chunkIn, Biome biomeIn, int x, int z, int startHeight, double noise, IBlockState defaultBlock, IBlockState defaultFluid, int seaLevel, long seed, SurfaceBuilderConfig config) {
        double d0 = Biome.INFO_NOISE.getValue((double)x * 0.25D, (double)z * 0.25D);
        if (d0 > 0.0D) {
            int i = x & 15;
            int j = z & 15;
            BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

            for(int k = startHeight; k >= 50; --k) {
                blockpos$mutableblockpos.setPos(i, k, j);
                if (!chunkIn.getBlockState(blockpos$mutableblockpos).isAir()) {
                    if (chunkIn.getBlockState(blockpos$mutableblockpos).getBlock() != defaultFluid.getBlock()) {
                        chunkIn.setBlockState(blockpos$mutableblockpos, ModBlocks.KATHAIRIS_GRASS.getDefaultState(), false);
                    }
                    break;
                }
            }
        }

        Biome.DEFAULT_SURFACE_BUILDER.buildSurface(random, chunkIn, biomeIn, x, z, startHeight, noise, defaultBlock, defaultFluid, seaLevel, seed, config);
    }
}