package io.github.krevik.kathairis.world.dimension.surface.builder;

import com.mojang.datafixers.Dynamic;
import io.github.krevik.kathairis.init.ModBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

import java.util.Random;
import java.util.function.Function;

public class KatharianSoftSandLakesSurfaceBuilder extends SurfaceBuilder<SurfaceBuilderConfig> {

    public KatharianSoftSandLakesSurfaceBuilder(Function<Dynamic<?>, ? extends SurfaceBuilderConfig> p_i51305_1_) {
        super(p_i51305_1_);
    }

    @Override
    public void buildSurface(Random random, IChunk chunkIn, Biome biomeIn, int x, int z, int startHeight, double noise, BlockState defaultBlock, BlockState defaultFluid, int seaLevel, long seed, SurfaceBuilderConfig config) {
        double d0 = Biome.INFO_NOISE.getValue((double)x * 0.25D, (double)z * 0.25D);
        if (d0 > 0.0D) {
            int i = x & 15;
            int j = z & 15;
            BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

            for(int k = startHeight; k >= 50; --k) {
                blockpos$mutableblockpos.setPos(i, k, j);
                if (!chunkIn.getBlockState(blockpos$mutableblockpos).isAir()) {
                    if (chunkIn.getBlockState(blockpos$mutableblockpos).getBlock() != defaultFluid.getBlock()) {
                        chunkIn.setBlockState(blockpos$mutableblockpos, ModBlocks.SOFT_SAND.getDefaultState(), false);
                    }
                    break;
                }
            }
        }

        SurfaceBuilder.DEFAULT.buildSurface(random, chunkIn, biomeIn, x, z, startHeight, noise, defaultBlock, defaultFluid, seaLevel, seed, config);
    }
}