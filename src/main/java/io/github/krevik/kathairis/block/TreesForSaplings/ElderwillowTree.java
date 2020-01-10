package io.github.krevik.kathairis.block.TreesForSaplings;

import io.github.krevik.kathairis.init.ModBlocks;
import io.github.krevik.kathairis.world.dimension.feature.KatharianFeatureList;
import io.github.krevik.kathairis.world.dimension.feature.config.KatharianTreeFeatureConfig;
import io.github.krevik.kathairis.world.dimension.feature.tree.FeatureKatharianSwampTallTree1;
import net.minecraft.block.Blocks;
import net.minecraft.block.trees.OakTree;
import net.minecraft.block.trees.Tree;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.foliageplacer.BlobFoliagePlacer;

import javax.annotation.Nullable;
import java.util.Random;

public class ElderwillowTree extends Tree {

    @Nullable
    @Override
    protected AbstractTreeFeature<NoFeatureConfig> getTreeFeature(Random random) {
        return new FeatureKatharianSwampTallTree1(NoFeatureConfig::deserialize);
    }
    
    @Nullable
    protected ConfiguredFeature<TreeFeatureConfig, ?> func_225546_b_(Random p_225546_1_) {
        return KatharianFeatureList.KATHARIAN_SWAMP_TALL_TREE_1.func_225566_b_((new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(ModBlocks.ELDERWILLOW_LOG.getDefaultState()), new SimpleBlockStateProvider(ModBlocks.ELDERWILLOW_LEAVES.getDefaultState()))).setSapling((net.minecraftforge.common.IPlantable) Blocks.OAK_SAPLING).func_225568_b_());
    }
}
