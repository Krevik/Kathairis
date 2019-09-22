package io.github.krevik.kathairis.block.TreesForSaplings;

import io.github.krevik.kathairis.world.dimension.feature.tree.FeatureKatharianSwampTallTree1;
import net.minecraft.block.trees.Tree;
import net.minecraft.world.gen.feature.AbstractTreeFeature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import javax.annotation.Nullable;
import java.util.Random;

public class ElderwillowTree extends Tree {
    @Nullable
    @Override
    protected AbstractTreeFeature<NoFeatureConfig> getTreeFeature(Random random) {
        return new FeatureKatharianSwampTallTree1(NoFeatureConfig::deserialize);
    }
}
