package io.github.krevik.kathairis.world.dimension.biome.biomes;

import io.github.krevik.kathairis.init.ModBlocks;
import io.github.krevik.kathairis.world.dimension.feature.KatharianFeatureList;
import io.github.krevik.kathairis.world.dimension.feature.KatharianMinableConfig;
import net.minecraft.init.Blocks;
import net.minecraft.init.Fluids;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.GenerationStage.Carving;
import net.minecraft.world.gen.GenerationStage.Decoration;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placement.ChanceConfig;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.FrequencyConfig;
import net.minecraft.world.gen.placement.LakeChanceConfig;
import net.minecraft.world.gen.surfacebuilders.CompositeSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

public final class BiomeKatharianRiver extends BiomeKatharianBiomeBase {
    public static final SurfaceBuilderConfig KATHARIAN_GRASS_DIRT_GRAVEL_SURFACE = new SurfaceBuilderConfig(ModBlocks.KATHAIRIS_GRASS.getDefaultState(), ModBlocks.KATHAIRIS_DIRT.getDefaultState(), GRAVEL);


    public BiomeKatharianRiver() {
        super((new BiomeBuilder()).surfaceBuilder(new CompositeSurfaceBuilder(DEFAULT_SURFACE_BUILDER, KATHARIAN_GRASS_DIRT_GRAVEL_SURFACE)).precipitation(RainType.RAIN).category(Category.RIVER).depth(-0.7F).scale(0.0F).temperature(0.5F).downfall(0.5F).waterColor(4159204).waterFogColor(329011).parent((String)null));
        this.addCarver(Carving.AIR, createWorldCarverWrapper(KatharianFeatureList.KATHARIAN_CAVE_WORLD_CARVER, new ProbabilityConfig(0.14285715F)));
        this.addCarver(Carving.AIR, createWorldCarverWrapper(CANYON_WORLD_CARVER, new ProbabilityConfig(0.02F)));
        this.addFeature(Decoration.LOCAL_MODIFICATIONS, createCompositeFeature(Feature.LAKES, new LakesConfig(Blocks.WATER), LAKE_WATER, new LakeChanceConfig(4)));
        this.addFeature(Decoration.LOCAL_MODIFICATIONS, createCompositeFeature(Feature.LAKES, new LakesConfig(Blocks.LAVA), LAVA_LAKE, new LakeChanceConfig(80)));
        //this.addFeature(Decoration.UNDERGROUND_ORES, createCompositeFeature(Feature.MINABLE, new MinableConfig(KatharianMinableConfig.IS_ROCK, Blocks.DIRT.getDefaultState(), 33), COUNT_RANGE, new CountRangeConfig(10, 0, 0, 256)));
        //this.addFeature(Decoration.UNDERGROUND_ORES, createCompositeFeature(Feature.MINABLE, new MinableConfig(KatharianMinableConfig.IS_ROCK, Blocks.GRAVEL.getDefaultState(), 33), COUNT_RANGE, new CountRangeConfig(8, 0, 0, 256)));
        //this.addFeature(Decoration.UNDERGROUND_ORES, createCompositeFeature(Feature.MINABLE, new MinableConfig(KatharianMinableConfig.IS_ROCK, Blocks.GRANITE.getDefaultState(), 33), COUNT_RANGE, new CountRangeConfig(10, 0, 0, 80)));
        //this.addFeature(Decoration.UNDERGROUND_ORES, createCompositeFeature(Feature.MINABLE, new MinableConfig(KatharianMinableConfig.IS_ROCK, Blocks.DIORITE.getDefaultState(), 33), COUNT_RANGE, new CountRangeConfig(10, 0, 0, 80)));
        //this.addFeature(Decoration.UNDERGROUND_ORES, createCompositeFeature(Feature.MINABLE, new MinableConfig(KatharianMinableConfig.IS_ROCK, Blocks.ANDESITE.getDefaultState(), 33), COUNT_RANGE, new CountRangeConfig(10, 0, 0, 80)));
        this.addFeature(Decoration.UNDERGROUND_ORES, createCompositeFeature(Feature.MINABLE, new MinableConfig(KatharianMinableConfig.IS_ROCK, ModBlocks.REVENUM_ORE.getDefaultState(), 34), COUNT_RANGE, new CountRangeConfig(20, 0, 0, 128)));
        this.addFeature(Decoration.UNDERGROUND_ORES, createCompositeFeature(Feature.MINABLE, new MinableConfig(KatharianMinableConfig.IS_ROCK, ModBlocks.TITANIUM_ORE.getDefaultState(), 14), COUNT_RANGE, new CountRangeConfig(2, 0, 0, 32)));
        //this.addFeature(Decoration.UNDERGROUND_ORES, createCompositeFeature(Feature.MINABLE, new MinableConfig(KatharianMinableConfig.IS_ROCK, Blocks.IRON_ORE.getDefaultState(), 18), COUNT_RANGE, new CountRangeConfig(20, 0, 0, 64)));
        //this.addFeature(Decoration.UNDERGROUND_ORES, createCompositeFeature(Feature.MINABLE, new MinableConfig(KatharianMinableConfig.IS_ROCK, Blocks.GOLD_ORE.getDefaultState(), 18), COUNT_RANGE, new CountRangeConfig(2, 0, 0, 32)));
        //this.addFeature(Decoration.UNDERGROUND_ORES, createCompositeFeature(Feature.MINABLE, new MinableConfig(KatharianMinableConfig.IS_ROCK, Blocks.REDSTONE_ORE.getDefaultState(), 18), COUNT_RANGE, new CountRangeConfig(8, 0, 0, 16)));
        //this.addFeature(Decoration.UNDERGROUND_ORES, createCompositeFeature(Feature.MINABLE, new MinableConfig(KatharianMinableConfig.IS_ROCK, Blocks.DIAMOND_ORE.getDefaultState(), 12), COUNT_RANGE, new CountRangeConfig(1, 0, 0, 16)));
        //this.addFeature(Decoration.UNDERGROUND_ORES, createCompositeFeature(Feature.MINABLE, new MinableConfig(KatharianMinableConfig.IS_ROCK, Blocks.LAPIS_ORE.getDefaultState(), 10), DEPTH_AVERAGE, new DepthAverageConfig(1, 16, 16)));
        //this.addFeature(Decoration.UNDERGROUND_ORES, createCompositeFeature(Feature.SPHERE_REPLACE, new SphereReplaceConfig(Blocks.SAND, 7, 2, Lists.newArrayList(new Block[]{Blocks.DIRT, Blocks.GRASS_BLOCK})), TOP_SOLID, new FrequencyConfig(3)));
        //this.addFeature(Decoration.UNDERGROUND_ORES, createCompositeFeature(Feature.SPHERE_REPLACE, new SphereReplaceConfig(Blocks.CLAY, 8, 1, Lists.newArrayList(new Block[]{Blocks.DIRT, Blocks.CLAY})), TOP_SOLID, new FrequencyConfig(1)));
        //this.addFeature(Decoration.UNDERGROUND_ORES, createCompositeFeature(Feature.SPHERE_REPLACE, new SphereReplaceConfig(Blocks.GRAVEL, 6, 2, Lists.newArrayList(new Block[]{Blocks.DIRT, Blocks.GRASS_BLOCK})), TOP_SOLID, new FrequencyConfig(1)));
        this.addFeature(Decoration.VEGETAL_DECORATION, createCompositeFeature(Feature.LIQUIDS, new LiquidsConfig(Fluids.WATER), HEIGHT_BIASED_RANGE, new CountRangeConfig(50, 8, 8, 256)));
        this.addFeature(Decoration.VEGETAL_DECORATION, createCompositeFeature(Feature.LIQUIDS, new LiquidsConfig(Fluids.LAVA), HEIGHT_VERY_BIASED_RANGE, new CountRangeConfig(20, 8, 16, 256)));
        this.addFeature(Decoration.VEGETAL_DECORATION, createCompositeFeature(Feature.TALL_GRASS, new TallGrassConfig(ModBlocks.KATHAIRIS_TALL_GRASS.getDefaultState()), TWICE_SURFACE, new FrequencyConfig(4)));
        this.addFeature(Decoration.VEGETAL_DECORATION, createCompositeFeature(Feature.TALL_GRASS, new TallGrassConfig(ModBlocks.KATHAIRIS_MINI_GRASS.getDefaultState()), TWICE_SURFACE, new FrequencyConfig(4)));
        this.addFeature(Decoration.VEGETAL_DECORATION, createCompositeFeature(Feature.BUSH, new BushConfig(ModBlocks.GOOSEBERRY_BUSH), TWICE_SURFACE_WITH_CHANCE, new ChanceConfig(2)));
        this.addFeature(Decoration.VEGETAL_DECORATION, createCompositeFeature(Feature.BUSH, new BushConfig(ModBlocks.KATHAIRIS_FUNGI), TWICE_SURFACE_WITH_CHANCE, new ChanceConfig(4)));
        this.addFeature(Decoration.VEGETAL_DECORATION, createCompositeFeature(Feature.BUSH, new BushConfig(ModBlocks.KATHAIRIS_NIGHT_FLOWER), TWICE_SURFACE_WITH_CHANCE, new ChanceConfig(1)));
        this.addFeature(Decoration.VEGETAL_DECORATION, createCompositeFeature(Feature.BUSH, new BushConfig(ModBlocks.VILYRIA), TWICE_SURFACE_WITH_CHANCE, new ChanceConfig(1)));
        this.addFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS, createCompositeFeature(KatharianFeatureList.KATHARIAN_CLOUD, IFeatureConfig.NO_FEATURE_CONFIG, COUNT_RANGE, new CountRangeConfig(3, 0, 0, 128)));
        this.addFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS, createCompositeFeature(KatharianFeatureList.KATHARIAN_CLOUD_MINI_ISLAND, IFeatureConfig.NO_FEATURE_CONFIG, COUNT_RANGE, new CountRangeConfig(1, 0, 0, 128)));

    }
}