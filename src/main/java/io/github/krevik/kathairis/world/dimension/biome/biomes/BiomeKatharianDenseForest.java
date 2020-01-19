package io.github.krevik.kathairis.world.dimension.biome.biomes;

import com.google.common.collect.ImmutableList;
import io.github.krevik.kathairis.init.ModBlocks;
import io.github.krevik.kathairis.world.dimension.feature.KatharianFeatureList;
import io.github.krevik.kathairis.world.dimension.feature.KatharianMinableConfig;
import net.minecraft.block.Blocks;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.biome.ForestBiome;
import net.minecraft.world.biome.PlainsBiome;
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

        this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.RANDOM_SELECTOR
                .func_225566_b_(new MultipleRandomFeatureConfig(ImmutableList.of(KatharianFeatureList.KATHARIAN_TREE_1
                                .func_225566_b_(KatharianFeatureList.MYSTIC_TREE_FEATURE_CONFIG)
                                .func_227227_a_(0.05F),
                KatharianFeatureList.KATHARIAN_TREE_2.func_225566_b_(KatharianFeatureList.MYSTIC_TREE_FEATURE_CONFIG)
                        .func_227227_a_(0.15F),
                        KatharianFeatureList.KATHARIAN_TREE_3
                                .func_225566_b_(KatharianFeatureList.MYSTIC_TREE_FEATURE_CONFIG)
                                .func_227227_a_(0.7F)),
                KatharianFeatureList.KATHARIAN_HUGE_TREE_1.func_225566_b_(KatharianFeatureList.MYSTIC_TREE_FEATURE_CONFIG)))
                .func_227228_a_(Placement.COUNT_EXTRA_HEIGHTMAP
                        .func_227446_a_(new AtSurfaceWithExtraConfig(30, 0.1F, 1))));

        this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.field_227248_z_.func_225566_b_(KatharianFeatureList.BLOCK_CLUSTER_TALL_GRASS_FEATURE_CONFIG).func_227228_a_(Placement.COUNT_HEIGHTMAP_DOUBLE.func_227446_a_(new FrequencyConfig(7))));
        this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.field_227248_z_.func_225566_b_(KatharianFeatureList.BLOCK_CLUSTER_MINI_GRASS_FEATURE_CONFIG).func_227228_a_(Placement.COUNT_HEIGHTMAP_DOUBLE.func_227446_a_(new FrequencyConfig(7))));
        this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.field_227248_z_.func_225566_b_(KatharianFeatureList.BLOCK_CLUSTER_GOOSEBERRY_FEATURE_CONFIG).func_227228_a_(Placement.CHANCE_HEIGHTMAP_DOUBLE.func_227446_a_(new ChanceConfig(2))));
        this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.field_227248_z_.func_225566_b_(KatharianFeatureList.BLOCK_CLUSTER_FUNGI_FEATURE_CONFIG).func_227228_a_(Placement.COUNT_HEIGHTMAP.func_227446_a_(new FrequencyConfig(1))));
        this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.field_227248_z_.func_225566_b_(KatharianFeatureList.BLOCK_CLUSTER_NIGHT_FLOWER_FEATURE_CONFIG).func_227228_a_(Placement.CHANCE_HEIGHTMAP_DOUBLE.func_227446_a_(new ChanceConfig(2))));
        this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.field_227248_z_.func_225566_b_(KatharianFeatureList.BLOCK_CLUSTER_VILYRIA_FEATURE_CONFIG).func_227228_a_(Placement.CHANCE_HEIGHTMAP_DOUBLE.func_227446_a_(new ChanceConfig(2))));
        this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, KatharianFeatureList.KATHARIAN_FOREST_CANDLE_BUSH.func_225566_b_(IFeatureConfig.NO_FEATURE_CONFIG).func_227228_a_(Placement.CHANCE_HEIGHTMAP.func_227446_a_(new ChanceConfig(6))));
        this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, KatharianFeatureList.STEPPED_SUCCULENT.func_225566_b_(IFeatureConfig.NO_FEATURE_CONFIG).func_227228_a_(Placement.COUNT_HEIGHTMAP.func_227446_a_(new FrequencyConfig(2))));
        this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, KatharianFeatureList.KATHARIAN_FOREST_BUSH.func_225566_b_(IFeatureConfig.NO_FEATURE_CONFIG).func_227228_a_(Placement.COUNT_HEIGHTMAP.func_227446_a_(new FrequencyConfig(5))));
        KatharianFeatureList.addDefaultSkyDecorations(this);
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
