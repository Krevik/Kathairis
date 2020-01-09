package io.github.krevik.kathairis.world.dimension.biome.biomes;

import io.github.krevik.kathairis.init.ModBlocks;
import io.github.krevik.kathairis.world.dimension.feature.KatharianFeatureList;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.ProbabilityConfig;
import net.minecraft.world.gen.placement.*;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

import java.awt.*;

public class BiomeKatharianHugeDesertMountains extends BiomeKatharianBiomeBase{
    public static final SurfaceBuilderConfig SAND_SAND_SANDSTONE_SURFACE = new SurfaceBuilderConfig(ModBlocks.KATHAIRIS_SAND.getDefaultState(), ModBlocks.KATHAIRIS_SANDSTONE.getDefaultState(), ModBlocks.KATHAIRIS_SAND.getDefaultState());

    public BiomeKatharianHugeDesertMountains() {

        super((new Builder()).surfaceBuilder(SurfaceBuilder.DEFAULT, SAND_SAND_SANDSTONE_SURFACE).precipitation(RainType.NONE).category(Category.DESERT).depth(1.8F).scale(2F).temperature(2.0F).downfall(0.0F).waterColor(4159204).waterFogColor(329011).parent((String)null));
        /*this.addCarver(GenerationStage.Carving.AIR, createCarver(KatharianFeatureList.KATHARIAN_CAVE_WORLD_CARVER, new ProbabilityConfig(0.14285715F)));
        this.addFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS, createDecoratedFeature(Feature.LAKE, new LakesConfig(ModBlocks.KATHAIRIS_SAND.getDefaultState()), Placement.LAVA_LAKE, new LakeChanceConfig(15)));
        this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, createDecoratedFeature(KatharianFeatureList.KATHARIAN_DEAD_BUSH, IFeatureConfig.NO_FEATURE_CONFIG, Placement.COUNT_HEIGHTMAP_DOUBLE, new FrequencyConfig(2)));
        this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, createDecoratedFeature(KatharianFeatureList.KATHARIAN_ROCKTUS, IFeatureConfig.NO_FEATURE_CONFIG, Placement.COUNT_HEIGHTMAP_DOUBLE, new FrequencyConfig(2)));
        this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, createDecoratedFeature(KatharianFeatureList.KATHARIAN_CACTUS, IFeatureConfig.NO_FEATURE_CONFIG, Placement.COUNT_HEIGHTMAP_DOUBLE, new FrequencyConfig(10)));
        this.addFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS, createDecoratedFeature(KatharianFeatureList.KATHARIAN_CLOUD, IFeatureConfig.NO_FEATURE_CONFIG, Placement.CHANCE_HEIGHTMAP, new ChanceConfig(6)));
        this.addFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS, createDecoratedFeature(KatharianFeatureList.KATHARIAN_CLOUD_MINI_ISLAND, IFeatureConfig.NO_FEATURE_CONFIG, Placement.CHANCE_HEIGHTMAP, new ChanceConfig(200)));
        */
        baseGrassColor=Color.ORANGE;
    }
}