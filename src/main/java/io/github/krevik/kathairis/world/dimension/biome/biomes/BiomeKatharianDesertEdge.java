package io.github.krevik.kathairis.world.dimension.biome.biomes;

import io.github.krevik.kathairis.init.ModBlocks;
import io.github.krevik.kathairis.world.dimension.feature.KatharianFeatureList;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.ProbabilityConfig;
import net.minecraft.world.gen.placement.ChanceConfig;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.FrequencyConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

import java.awt.*;

public class BiomeKatharianDesertEdge extends BiomeKatharianBiomeBase{
    public static final SurfaceBuilderConfig SAND_SAND_SANDSTONE_SURFACE = new SurfaceBuilderConfig(ModBlocks.KATHAIRIS_SAND.getDefaultState(), ModBlocks.KATHAIRIS_SANDSTONE.getDefaultState(), ModBlocks.SOFT_SAND.getDefaultState());
    public BiomeKatharianDesertEdge() {
        super((new Builder()).surfaceBuilder(KatharianFeatureList.KATHARIAN_DESERT_EDGE_SURFACE_BUILDER, SAND_SAND_SANDSTONE_SURFACE).precipitation(RainType.NONE).category(Category.DESERT).depth(0.125F).scale(0.05F).temperature(2.0F).downfall(0.0F).waterColor(4159204).waterFogColor(329011).parent((String)null));
        this.addCarver(GenerationStage.Carving.AIR, createCarver(KatharianFeatureList.KATHARIAN_CAVE_WORLD_CARVER, new ProbabilityConfig(0.14285715F)));
        this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, createDecoratedFeature(KatharianFeatureList.KATHARIAN_DEAD_BUSH, IFeatureConfig.NO_FEATURE_CONFIG, Placement.COUNT_HEIGHTMAP, new FrequencyConfig(8)));
        this.addFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS, createDecoratedFeature(KatharianFeatureList.KATHARIAN_CLOUD, IFeatureConfig.NO_FEATURE_CONFIG, Placement.CHANCE_HEIGHTMAP, new ChanceConfig(6)));
        this.addFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS, createDecoratedFeature(KatharianFeatureList.KATHARIAN_CLOUD_MINI_ISLAND, IFeatureConfig.NO_FEATURE_CONFIG, Placement.CHANCE_HEIGHTMAP, new ChanceConfig(200)));
        this.addFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS, createDecoratedFeature(KatharianFeatureList.FEATURE_SOUL_CLOUD_WITH_CHESTS, IFeatureConfig.NO_FEATURE_CONFIG, Placement.CHANCE_HEIGHTMAP, new ChanceConfig(350)));
        this.addFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS, createDecoratedFeature(KatharianFeatureList.FEATURE_CLOUD_RUINS, IFeatureConfig.NO_FEATURE_CONFIG, Placement.CHANCE_HEIGHTMAP, new ChanceConfig(350)));
        baseGrassColor= Color.ORANGE;
    }

}
