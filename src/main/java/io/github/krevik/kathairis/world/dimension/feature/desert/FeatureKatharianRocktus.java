package io.github.krevik.kathairis.world.dimension.feature.desert;

import com.mojang.datafixers.Dynamic;
import io.github.krevik.kathairis.block.BlockKathairisRocktus;
import io.github.krevik.kathairis.init.ModBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.Random;
import java.util.function.Function;

public class FeatureKatharianRocktus extends Feature<NoFeatureConfig> {
    private static final BlockKathairisRocktus field_197166_a = (BlockKathairisRocktus) ModBlocks.ROCKTUS;

    public FeatureKatharianRocktus(Function<Dynamic<?>, ? extends NoFeatureConfig> p_i49878_1_) {
        super(p_i49878_1_);
    }

    public boolean place(IWorld p_212245_1_, ChunkGenerator<? extends GenerationSettings> p_212245_2_, Random p_212245_3_, BlockPos p_212245_4_, NoFeatureConfig p_212245_5_) {
        for(BlockState iblockstate = p_212245_1_.getBlockState(p_212245_4_); (iblockstate.isAir(p_212245_1_, p_212245_4_) || iblockstate.isIn(BlockTags.LEAVES)) && p_212245_4_.getY() > 0; iblockstate = p_212245_1_.getBlockState(p_212245_4_)) {
            p_212245_4_ = p_212245_4_.down();
        }

        BlockState iblockstate1 = field_197166_a.getDefaultState();

        for(int i = 0; i < 4; ++i) {
            BlockPos blockpos = p_212245_4_.add(p_212245_3_.nextInt(8) - p_212245_3_.nextInt(8), p_212245_3_.nextInt(4) - p_212245_3_.nextInt(4), p_212245_3_.nextInt(8) - p_212245_3_.nextInt(8));
            if (p_212245_1_.isAirBlock(blockpos) && iblockstate1.isValidPosition(p_212245_1_, blockpos)) {
                p_212245_1_.setBlockState(blockpos, iblockstate1, 2);
            }
        }

        return true;
    }
}