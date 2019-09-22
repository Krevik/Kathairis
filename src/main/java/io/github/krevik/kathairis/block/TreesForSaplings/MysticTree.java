package io.github.krevik.kathairis.block.TreesForSaplings;

import io.github.krevik.kathairis.world.dimension.feature.tree.FeatureKatharianTallTree1;
import io.github.krevik.kathairis.world.dimension.feature.tree.FeatureKatharianTallTree2;
import io.github.krevik.kathairis.world.dimension.feature.tree.FeatureKatharianTreeHuge1;
import net.minecraft.block.trees.Tree;
import net.minecraft.world.gen.feature.AbstractTreeFeature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import javax.annotation.Nullable;
import java.util.Random;

public class MysticTree extends Tree {

    @Nullable
    @Override
    protected AbstractTreeFeature<NoFeatureConfig> getTreeFeature(Random random) {
        int k=random.nextInt(3);
        if(k==0) {return new FeatureKatharianTreeHuge1(NoFeatureConfig::deserialize);}
        if(k==1) {return new FeatureKatharianTallTree1(NoFeatureConfig::deserialize);}
        if(k==2) {return new FeatureKatharianTallTree2(NoFeatureConfig::deserialize);}
        return new FeatureKatharianTreeHuge1(NoFeatureConfig::deserialize);
    }
}
