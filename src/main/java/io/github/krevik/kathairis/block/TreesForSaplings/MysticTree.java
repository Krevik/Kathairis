package io.github.krevik.kathairis.block.TreesForSaplings;

import io.github.krevik.kathairis.init.ModBlocks;
import io.github.krevik.kathairis.world.dimension.feature.KatharianFeatureList;
import net.minecraft.block.Blocks;
import net.minecraft.block.trees.Tree;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliageplacer.BlobFoliagePlacer;

import javax.annotation.Nullable;
import java.util.Random;

public class MysticTree extends Tree {

    @Nullable
    protected ConfiguredFeature<TreeFeatureConfig, ?> func_225546_b_(Random p_225546_1_) {
        int k=p_225546_1_.nextInt(3);
        if(k==0) {
            return KatharianFeatureList.KATHARIAN_HUGE_TREE_1.func_225566_b_((new TreeFeatureConfig.Builder(new SimpleBlockStateProvider(ModBlocks.MYSTIC_LOG.getDefaultState()), new SimpleBlockStateProvider(ModBlocks.MYSTIC_LEAVES.getDefaultState()), new BlobFoliagePlacer(0, 0))).setSapling((net.minecraftforge.common.IPlantable) ModBlocks.MYSTIC_SAPLING).func_225568_b_());
        }
        if(k==1) {
            return KatharianFeatureList.KATHARIAN_TREE_2.func_225566_b_((new TreeFeatureConfig.Builder(new SimpleBlockStateProvider(ModBlocks.MYSTIC_LOG.getDefaultState()), new SimpleBlockStateProvider(ModBlocks.MYSTIC_LEAVES.getDefaultState()), new BlobFoliagePlacer(0, 0))).setSapling((net.minecraftforge.common.IPlantable) ModBlocks.MYSTIC_SAPLING).func_225568_b_());
        }
        if(k==2) {        return KatharianFeatureList.KATHARIAN_TREE_2.func_225566_b_((new TreeFeatureConfig.Builder(new SimpleBlockStateProvider(ModBlocks.MYSTIC_LOG.getDefaultState()), new SimpleBlockStateProvider(ModBlocks.MYSTIC_LEAVES.getDefaultState()), new BlobFoliagePlacer(0, 0))).setSapling((net.minecraftforge.common.IPlantable) ModBlocks.MYSTIC_SAPLING).func_225568_b_());
        }
        return KatharianFeatureList.KATHARIAN_HUGE_TREE_1.func_225566_b_((new TreeFeatureConfig.Builder(new SimpleBlockStateProvider(ModBlocks.MYSTIC_LOG.getDefaultState()), new SimpleBlockStateProvider(ModBlocks.MYSTIC_LEAVES.getDefaultState()), new BlobFoliagePlacer(0, 0))).setSapling((net.minecraftforge.common.IPlantable) ModBlocks.MYSTIC_SAPLING).func_225568_b_());
    }
}
