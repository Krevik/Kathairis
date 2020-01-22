package io.github.krevik.kathairis.world.dimension.biome.biomes;

import io.github.krevik.kathairis.init.ModBlocks;
import io.github.krevik.kathairis.world.dimension.feature.KatharianFeatureList;
import io.github.krevik.kathairis.world.dimension.structures.sky_fortress.SkyFortressConfig;
import net.minecraft.block.Blocks;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.ProbabilityConfig;
import net.minecraft.world.gen.placement.*;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

public class BiomeKatharianPlainFields extends BiomeKatharianBiomeBase {
    public static final SurfaceBuilderConfig KATHARIAN_GRASS_DIRT_GRAVEL_SURFACE = new SurfaceBuilderConfig(ModBlocks.KATHAIRIS_GRASS.getDefaultState(), ModBlocks.KATHAIRIS_DIRT.getDefaultState(), Blocks.GRAVEL.getDefaultState());
    public BiomeKatharianPlainFields() {
        super((new Builder()).surfaceBuilder(SurfaceBuilder.DEFAULT, KATHARIAN_GRASS_DIRT_GRAVEL_SURFACE).precipitation(RainType.RAIN).category(Category.PLAINS).depth(0.1F).scale(0.0F).temperature(0.7F).downfall(0.8F).waterColor(4159204).waterFogColor(329011).parent((String)null));
        this.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, KatharianFeatureList.FEATURE_SMALL_RUINS.func_225566_b_(IFeatureConfig.NO_FEATURE_CONFIG).func_227228_a_(Placement.CHANCE_HEIGHTMAP.func_227446_a_(new ChanceConfig(600))));
        KatharianFeatureList.addWaterLakes(this);
        this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.field_227248_z_.func_225566_b_(KatharianFeatureList.BLOCK_CLUSTER_TALL_GRASS_FEATURE_CONFIG).func_227228_a_(Placement.COUNT_HEIGHTMAP_DOUBLE.func_227446_a_(new FrequencyConfig(15))));
        this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.field_227248_z_.func_225566_b_(KatharianFeatureList.BLOCK_CLUSTER_MINI_GRASS_FEATURE_CONFIG).func_227228_a_(Placement.COUNT_HEIGHTMAP_DOUBLE.func_227446_a_(new FrequencyConfig(12))));
        this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.field_227248_z_.func_225566_b_(KatharianFeatureList.BLOCK_CLUSTER_MULTI_GRASS_FEATURE_CONFIG).func_227228_a_(Placement.COUNT_HEIGHTMAP_DOUBLE.func_227446_a_(new FrequencyConfig(20))));
        this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.field_227248_z_.func_225566_b_(KatharianFeatureList.BLOCK_CLUSTER_MULTI_GRASS_FEATURE_CONFIG).func_227228_a_(Placement.COUNT_HEIGHTMAP_DOUBLE.func_227446_a_(new FrequencyConfig(8))));
        this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.field_227248_z_.func_225566_b_(KatharianFeatureList.BLOCK_CLUSTER_BUTTERFLY_FLOWER_FEATURE_CONFIG).func_227228_a_(Placement.CHANCE_HEIGHTMAP_DOUBLE.func_227446_a_(new ChanceConfig(2))));
        this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.field_227248_z_.func_225566_b_(KatharianFeatureList.BLOCK_CLUSTER_NIGHT_FLOWER_FEATURE_CONFIG).func_227228_a_(Placement.CHANCE_HEIGHTMAP_DOUBLE.func_227446_a_(new ChanceConfig(1))));
        this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.field_227248_z_.func_225566_b_(KatharianFeatureList.BLOCK_CLUSTER_VILYRIA_FEATURE_CONFIG).func_227228_a_(Placement.CHANCE_HEIGHTMAP_DOUBLE.func_227446_a_(new ChanceConfig(1))));
        this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.field_227248_z_.func_225566_b_(KatharianFeatureList.BLOCK_CLUSTER_BISON_STARS_FEATURE_CONFIG).func_227228_a_(Placement.CHANCE_HEIGHTMAP_DOUBLE.func_227446_a_(new ChanceConfig(1))));
        this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.field_227248_z_.func_225566_b_(KatharianFeatureList.BLOCK_CLUSTER_EYE_PLANT_FEATURE_CONFIG).func_227228_a_(Placement.CHANCE_HEIGHTMAP_DOUBLE.func_227446_a_(new ChanceConfig(1))));
        this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.field_227248_z_.func_225566_b_(KatharianFeatureList.BLOCK_CLUSTER_FRUP_PLANT_FEATURE_CONFIG).func_227228_a_(Placement.CHANCE_HEIGHTMAP_DOUBLE.func_227446_a_(new ChanceConfig(2))));
        this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, KatharianFeatureList.KATHARIAN_PLAIN_FIELDS.func_225566_b_(IFeatureConfig.NO_FEATURE_CONFIG).func_227228_a_(Placement.COUNT_RANGE.func_227446_a_(new CountRangeConfig(4,0,0,128))));
        this.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, KatharianFeatureList.SKY_FORTRESS.func_225566_b_(new SkyFortressConfig("sky_fortress_connections",20)).func_227228_a_(Placement.NOPE.func_227446_a_(IPlacementConfig.NO_PLACEMENT_CONFIG)));
        KatharianFeatureList.addDefaultSkyDecorations(this);

    }
}
