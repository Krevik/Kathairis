package io.github.krevik.kathairis.block.TreesForSaplings;

import io.github.krevik.kathairis.init.ModBlocks;
import io.github.krevik.kathairis.world.dimension.feature.KatharianFeatureList;
import net.minecraft.block.trees.Tree;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliageplacer.BlobFoliagePlacer;

import javax.annotation.Nullable;
import java.util.Random;

public class ElderwillowTree extends Tree {

    @Nullable
    protected ConfiguredFeature<TreeFeatureConfig, ?> func_225546_b_(Random p_225546_1_) {
        return KatharianFeatureList.KATHARIAN_SWAMP_TALL_TREE_1.func_225566_b_((new TreeFeatureConfig.Builder(new SimpleBlockStateProvider(ModBlocks.ELDERWILLOW_LOG.getDefaultState()), new SimpleBlockStateProvider(ModBlocks.ELDERWILLOW_LEAVES.getDefaultState()), new BlobFoliagePlacer(0, 0))).setSapling((net.minecraftforge.common.IPlantable) ModBlocks.ELDERWILLOW_SAPLING).build());
    }
}
