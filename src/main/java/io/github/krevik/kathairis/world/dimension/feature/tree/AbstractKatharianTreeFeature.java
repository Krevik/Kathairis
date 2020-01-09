package io.github.krevik.kathairis.world.dimension.feature.tree;

import com.mojang.datafixers.Dynamic;
import io.github.krevik.kathairis.init.ModBlocks;
import io.github.krevik.kathairis.world.dimension.feature.config.BaseKatharianTreeFeatureConfig;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.IWorldGenerationBaseReader;
import net.minecraft.world.gen.IWorldGenerationReader;
import net.minecraft.world.gen.feature.AbstractTreeFeature;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.function.Function;

public abstract class AbstractKatharianTreeFeature<T extends BaseKatharianTreeFeatureConfig> extends AbstractTreeFeature<BaseKatharianTreeFeatureConfig> {

    public AbstractKatharianTreeFeature(Function<Dynamic<?>, ? extends T> p_i225797_1_) {
        super(p_i225797_1_);
    }

    public static boolean canGrowInto(IWorldGenerationBaseReader p_214587_0_, BlockPos p_214587_1_) {
            return p_214587_0_.hasBlockState(p_214587_1_, (p_214573_0_) -> {
                Block block = p_214573_0_.getBlock();
                return block == Blocks.GRASS_BLOCK ||
                        block.isIn(BlockTags.SAPLINGS) || block == Blocks.VINE
                        || block == ModBlocks.KATHAIRIS_DIRT || block== ModBlocks.KATHAIRIS_GRASS;
            });
    }


    @Override
    protected void setDirtAt(IWorldGenerationReader worldIn, BlockPos pos, BlockPos origin) {
        worldIn.setBlockState(pos,ModBlocks.KATHAIRIS_DIRT.getDefaultState(),3);
    }

    protected boolean isWillowVineMain(IWorldGenerationBaseReader p_214587_0_, BlockPos p_214587_1_) {
        return p_214587_0_.hasBlockState(p_214587_1_, (p_214573_0_) -> {
            Block block = p_214573_0_.getBlock();
            return block== ModBlocks.WILLOW_VINE_MAIN;
        });
    }


}