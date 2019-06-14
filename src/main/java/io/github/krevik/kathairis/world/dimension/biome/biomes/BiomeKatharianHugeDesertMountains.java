package io.github.krevik.kathairis.world.dimension.biome.biomes;

import io.github.krevik.kathairis.init.ModBlocks;
import io.github.krevik.kathairis.world.dimension.feature.KatharianFeatureList;
import io.github.krevik.kathairis.world.dimension.feature.KatharianMinableConfig;
import net.minecraft.init.Fluids;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.MountainsBiome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placement.ChanceConfig;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.FrequencyConfig;
import net.minecraft.world.gen.placement.LakeChanceConfig;
import net.minecraft.world.gen.surfacebuilders.CompositeSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

import java.awt.*;

public class BiomeKatharianHugeDesertMountains extends BiomeKatharianBiomeBase{
    public static final SurfaceBuilderConfig SAND_SAND_SANDSTONE_SURFACE = new SurfaceBuilderConfig(ModBlocks.KATHAIRIS_SAND.getDefaultState(), ModBlocks.KATHAIRIS_SANDSTONE.getDefaultState(), ModBlocks.KATHAIRIS_SAND.getDefaultState());

    public BiomeKatharianHugeDesertMountains() {

        super((new Biome.BiomeBuilder()).surfaceBuilder(new CompositeSurfaceBuilder(DEFAULT_SURFACE_BUILDER, SAND_SAND_SANDSTONE_SURFACE)).precipitation(Biome.RainType.NONE).category(Biome.Category.DESERT).depth(1.8F).scale(2F).temperature(2.0F).downfall(0.0F).waterColor(4159204).waterFogColor(329011).parent((String)null));
        this.addCarver(GenerationStage.Carving.AIR, createWorldCarverWrapper(KatharianFeatureList.KATHARIAN_CAVE_WORLD_CARVER, new ProbabilityConfig(0.14285715F)));
        this.addFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS, createCompositeFeature(Feature.LAKES, new LakesConfig(ModBlocks.KATHAIRIS_SAND), LAVA_LAKE, new LakeChanceConfig(15)));
        this.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, createCompositeFeature(Feature.MINABLE, new MinableConfig(KatharianMinableConfig.IS_ROCK, ModBlocks.REVENUM_ORE.getDefaultState(), 17), COUNT_RANGE, new CountRangeConfig(20, 0, 0, 128)));
        this.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, createCompositeFeature(Feature.MINABLE, new MinableConfig(KatharianMinableConfig.IS_ROCK, ModBlocks.TITANIUM_ORE.getDefaultState(), 7), COUNT_RANGE, new CountRangeConfig(2, 0, 0, 32)));
        this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, createCompositeFeature(KatharianFeatureList.KATHARIAN_DEAD_BUSH, IFeatureConfig.NO_FEATURE_CONFIG, TWICE_SURFACE, new FrequencyConfig(2)));
        this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, createCompositeFeature(KatharianFeatureList.KATHARIAN_ROCKTUS, IFeatureConfig.NO_FEATURE_CONFIG, TWICE_SURFACE, new FrequencyConfig(2)));
        this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, createCompositeFeature(KatharianFeatureList.KATHARIAN_CACTUS, IFeatureConfig.NO_FEATURE_CONFIG, TWICE_SURFACE, new FrequencyConfig(10)));
        this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, createCompositeFeature(Feature.LIQUIDS, new LiquidsConfig(Fluids.WATER), HEIGHT_BIASED_RANGE, new CountRangeConfig(50, 8, 8, 256)));
        this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, createCompositeFeature(Feature.LIQUIDS, new LiquidsConfig(Fluids.LAVA), HEIGHT_VERY_BIASED_RANGE, new CountRangeConfig(20, 8, 16, 256)));
        this.addFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS, createCompositeFeature(KatharianFeatureList.KATHARIAN_CLOUD, IFeatureConfig.NO_FEATURE_CONFIG, COUNT_RANGE, new CountRangeConfig(1, 0, 0, 128)));
        this.addFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, createCompositeFeature(KatharianFeatureList.KATHARIAN_CLOUD_MINI_ISLAND, IFeatureConfig.NO_FEATURE_CONFIG, COUNT_RANGE, new CountRangeConfig(1, 0, 0, 128)));
        this.addFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS, createCompositeFeature(KatharianFeatureList.CLOUD_MINI_TEMPLE, IFeatureConfig.NO_FEATURE_CONFIG, WITH_CHANCE, new ChanceConfig(KatharianFeatureList.mini_Cloud_Temple_Chance)));
        baseGrassColor=Color.ORANGE;
    }
}