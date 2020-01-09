package io.github.krevik.kathairis.block.TreesForSaplings;

import io.github.krevik.kathairis.world.dimension.feature.tree.FeatureKatharianSoulTree;
import net.minecraft.block.trees.Tree;
import net.minecraft.world.gen.feature.AbstractTreeFeature;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.TreeFeatureConfig;

import javax.annotation.Nullable;
import java.util.Random;

public class SoulTree extends Tree {

    @Nullable
    @Override
    protected ConfiguredFeature<TreeFeatureConfig, ?> func_225546_b_(Random p_225546_1_) {
        return new FeatureKatharianSoulTree(NoFeatureConfig::deserialize);
    }
}
