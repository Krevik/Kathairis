package io.github.krevik.kathairis.world.dimension.biome.biomes;

import io.github.krevik.kathairis.init.ModBlocks;
import io.github.krevik.kathairis.world.dimension.feature.KatharianFeatureList;
import net.minecraft.block.Blocks;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placement.*;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

public class BiomeKatharianDenseForest extends BiomeKatharianBiomeBase{
    public static final SurfaceBuilderConfig KATHARIAN_GRASS_DIRT_GRAVEL_SURFACE = new SurfaceBuilderConfig(ModBlocks.KATHAIRIS_GRASS.getDefaultState(), ModBlocks.KATHAIRIS_DIRT.getDefaultState(), Blocks.GRAVEL.getDefaultState());

    public BiomeKatharianDenseForest() {
        super((new Builder()).surfaceBuilder(SurfaceBuilder.DEFAULT, KATHARIAN_GRASS_DIRT_GRAVEL_SURFACE).precipitation(RainType.RAIN).category(Category.FOREST).depth(0.1F).scale(0.1F).temperature(0.7F).downfall(0.8F).waterColor(4159204).waterFogColor(329011).parent((String)null));
        this.addCarver(GenerationStage.Carving.AIR, createCarver(KatharianFeatureList.KATHARIAN_CAVE_WORLD_CARVER, new ProbabilityConfig(0.14285715F)));
        DefaultBiomeFeatures.addLakes(this);
        this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, createDecoratedFeature(Feature.RANDOM_SELECTOR, new MultipleRandomFeatureConfig(new Feature[]{KatharianFeatureList.KATHARIAN_TREE_2, KatharianFeatureList.KATHARIAN_TREE_1}, new IFeatureConfig[]{IFeatureConfig.NO_FEATURE_CONFIG, IFeatureConfig.NO_FEATURE_CONFIG}, new float[]{0.6F, 0.4F}, KatharianFeatureList.KATHARIAN_TREE_2, IFeatureConfig.NO_FEATURE_CONFIG), Placement.COUNT_EXTRA_HEIGHTMAP, new AtSurfaceWithExtraConfig(6, 0.4F, 2)));
        this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, createDecoratedFeature(Feature.RANDOM_SELECTOR, new MultipleRandomFeatureConfig(new Feature[]{KatharianFeatureList.KATHARIAN_TREE_3, KatharianFeatureList.KATHARIAN_HUGE_TREE_1}, new IFeatureConfig[]{IFeatureConfig.NO_FEATURE_CONFIG, IFeatureConfig.NO_FEATURE_CONFIG}, new float[]{0.6F, 0.4F}, KatharianFeatureList.KATHARIAN_TREE_3, IFeatureConfig.NO_FEATURE_CONFIG), Placement.COUNT_EXTRA_HEIGHTMAP, new AtSurfaceWithExtraConfig(3, 0.4F, 2)));
        this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, createDecoratedFeature(Feature.GRASS, new GrassFeatureConfig(ModBlocks.KATHAIRIS_TALL_GRASS.getDefaultState()), Placement.COUNT_HEIGHTMAP_DOUBLE, new FrequencyConfig(7)));
        this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, createDecoratedFeature(Feature.GRASS, new GrassFeatureConfig(ModBlocks.KATHAIRIS_MINI_GRASS.getDefaultState()), Placement.COUNT_HEIGHTMAP_DOUBLE, new FrequencyConfig(7)));
        this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, createDecoratedFeature(Feature.BUSH, new BushConfig(ModBlocks.GOOSEBERRY_BUSH.getDefaultState()), Placement.CHANCE_HEIGHTMAP_DOUBLE, new ChanceConfig(2)));
        this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, createDecoratedFeature(Feature.BUSH, new BushConfig(ModBlocks.KATHAIRIS_FUNGI.getDefaultState()), Placement.COUNT_HEIGHTMAP, new FrequencyConfig(1)));
        this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, createDecoratedFeature(Feature.BUSH, new BushConfig(ModBlocks.KATHAIRIS_NIGHT_FLOWER.getDefaultState()), Placement.CHANCE_HEIGHTMAP_DOUBLE, new ChanceConfig(2)));
        this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, createDecoratedFeature(Feature.BUSH, new BushConfig(ModBlocks.VILYRIA.getDefaultState()), Placement.CHANCE_HEIGHTMAP_DOUBLE, new ChanceConfig(2)));
        this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, createDecoratedFeature(KatharianFeatureList.KATHARIAN_FOREST_CANDLE_BUSH, new BushConfig(ModBlocks.FOREST_CANDLE.getDefaultState()), Placement.CHANCE_HEIGHTMAP, new ChanceConfig(6)));
        this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, createDecoratedFeature(KatharianFeatureList.STEPPED_SUCCULENT, IFeatureConfig.NO_FEATURE_CONFIG, Placement.COUNT_HEIGHTMAP, new FrequencyConfig(2)));
        this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, createDecoratedFeature(KatharianFeatureList.KATHARIAN_FOREST_BUSH, IFeatureConfig.NO_FEATURE_CONFIG, Placement.COUNT_HEIGHTMAP, new FrequencyConfig(5)));
        this.addFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS, createDecoratedFeature(KatharianFeatureList.KATHARIAN_CLOUD, IFeatureConfig.NO_FEATURE_CONFIG, Placement.CHANCE_HEIGHTMAP, new ChanceConfig(6)));
        this.addFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS, createDecoratedFeature(KatharianFeatureList.KATHARIAN_CLOUD_MINI_ISLAND, IFeatureConfig.NO_FEATURE_CONFIG, Placement.CHANCE_HEIGHTMAP, new ChanceConfig(200)));
        this.addFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS, createDecoratedFeature(KatharianFeatureList.FEATURE_SOUL_CLOUD_WITH_CHESTS, IFeatureConfig.NO_FEATURE_CONFIG, Placement.CHANCE_HEIGHTMAP, new ChanceConfig(350)));
        this.addFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS, createDecoratedFeature(KatharianFeatureList.FEATURE_CLOUD_RUINS, IFeatureConfig.NO_FEATURE_CONFIG, Placement.CHANCE_HEIGHTMAP, new ChanceConfig(350)));
    }

    /*@Override
    public int getGrassColor(BlockPos p_180627_1_) {
        return 0x41f4c7;
    }*/

    @Override
    public float getSpawningChance() {
        return 0.1f;
    }
}
