package io.github.krevik.kathairis.world.dimension.feature.forest;

import com.mojang.datafixers.Dynamic;
import io.github.krevik.kathairis.init.ModBlocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.Random;
import java.util.function.Function;

public class FeatureSteppedSucculent extends Feature<NoFeatureConfig> {


    public FeatureSteppedSucculent(Function<Dynamic<?>, ? extends NoFeatureConfig> p_i49878_1_) {
        super(p_i49878_1_);
    }

    @Override
    public boolean place(IWorld p_212245_1_, ChunkGenerator<? extends GenerationSettings> p_212245_2_, Random p_212245_3_, BlockPos p_212245_4_, NoFeatureConfig p_212245_5_) {
        for(int lvt_6_1_ = 0; lvt_6_1_ < 10; ++lvt_6_1_) {
            BlockPos lvt_7_1_ = p_212245_4_.add(p_212245_3_.nextInt(8) - p_212245_3_.nextInt(8), p_212245_3_.nextInt(4) - p_212245_3_.nextInt(4), p_212245_3_.nextInt(8) - p_212245_3_.nextInt(8));
            if (p_212245_1_.isAirBlock(lvt_7_1_)) {
                int lvt_8_1_ = 1 + p_212245_3_.nextInt(p_212245_3_.nextInt(3) + 1);

                for(int lvt_9_1_ = 0; lvt_9_1_ < lvt_8_1_; ++lvt_9_1_) {
                    if (ModBlocks.STEPPED_SUCCULENT.getDefaultState().isValidPosition(p_212245_1_, lvt_7_1_)) {
                        p_212245_1_.setBlockState(lvt_7_1_.up(lvt_9_1_), ModBlocks.STEPPED_SUCCULENT.getDefaultState(), 2);
                    }
                }
            }
        }

        return true;
    }
}