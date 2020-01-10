package io.github.krevik.kathairis.world.dimension.biome.biomes;

import io.github.krevik.kathairis.init.ModBlocks;
import io.github.krevik.kathairis.world.dimension.feature.KatharianFeatureList;
import net.minecraft.block.Blocks;
import net.minecraft.world.gen.GenerationStage.Carving;
import net.minecraft.world.gen.feature.ProbabilityConfig;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

public final class BiomeKatharianRiver extends BiomeKatharianBiomeBase {
    public static final SurfaceBuilderConfig KATHARIAN_GRASS_DIRT_GRAVEL_SURFACE = new SurfaceBuilderConfig(ModBlocks.KATHAIRIS_GRASS.getDefaultState(), ModBlocks.KATHAIRIS_DIRT.getDefaultState(), Blocks.GRAVEL.getDefaultState());


    public BiomeKatharianRiver() {
        super((new Builder()).surfaceBuilder(SurfaceBuilder.DEFAULT, KATHARIAN_GRASS_DIRT_GRAVEL_SURFACE).precipitation(RainType.RAIN).category(Category.RIVER).depth(-0.7F).scale(0.0F).temperature(0.5F).downfall(0.5F).waterColor(4159204).waterFogColor(329011).parent((String)null));
        this.addCarver(Carving.AIR, createCarver(KatharianFeatureList.KATHARIAN_CAVE_WORLD_CARVER, new ProbabilityConfig(0.14285715F)));
        /*this.addFeature(Decoration.LOCAL_MODIFICATIONS, createDecoratedFeature(Feature.LAKE, new LakesConfig(Blocks.WATER.getDefaultState()), Placement.WATER_LAKE, new LakeChanceConfig(4)));
        this.addFeature(Decoration.LOCAL_MODIFICATIONS, createDecoratedFeature(Feature.LAKE, new LakesConfig(Blocks.LAVA.getDefaultState()), Placement.LAVA_LAKE, new LakeChanceConfig(80)));
        this.addFeature(Decoration.VEGETAL_DECORATION, createDecoratedFeature(Feature.GRASS, new GrassFeatureConfig(ModBlocks.KATHAIRIS_TALL_GRASS.getDefaultState()), Placement.COUNT_HEIGHTMAP_DOUBLE, new FrequencyConfig(4)));
        this.addFeature(Decoration.VEGETAL_DECORATION, createDecoratedFeature(Feature.GRASS, new GrassFeatureConfig(ModBlocks.KATHAIRIS_MINI_GRASS.getDefaultState()), Placement.COUNT_HEIGHTMAP_DOUBLE, new FrequencyConfig(4)));
        this.addFeature(Decoration.VEGETAL_DECORATION, createDecoratedFeature(Feature.BUSH, new BushConfig(ModBlocks.GOOSEBERRY_BUSH.getDefaultState()), Placement.CHANCE_HEIGHTMAP_DOUBLE, new ChanceConfig(2)));
        this.addFeature(Decoration.VEGETAL_DECORATION, createDecoratedFeature(Feature.BUSH, new BushConfig(ModBlocks.KATHAIRIS_FUNGI.getDefaultState()), Placement.CHANCE_HEIGHTMAP_DOUBLE, new ChanceConfig(4)));
        this.addFeature(Decoration.VEGETAL_DECORATION, createDecoratedFeature(Feature.BUSH, new BushConfig(ModBlocks.KATHAIRIS_NIGHT_FLOWER.getDefaultState()), Placement.CHANCE_HEIGHTMAP_DOUBLE, new ChanceConfig(1)));
        this.addFeature(Decoration.VEGETAL_DECORATION, createDecoratedFeature(Feature.BUSH, new BushConfig(ModBlocks.VILYRIA.getDefaultState()), Placement.CHANCE_HEIGHTMAP_DOUBLE, new ChanceConfig(1)));
        this.addFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS, createDecoratedFeature(KatharianFeatureList.KATHARIAN_CLOUD, IFeatureConfig.NO_FEATURE_CONFIG, Placement.CHANCE_HEIGHTMAP, new ChanceConfig(6)));
        this.addFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS, createDecoratedFeature(KatharianFeatureList.KATHARIAN_CLOUD_MINI_ISLAND, IFeatureConfig.NO_FEATURE_CONFIG, Placement.CHANCE_HEIGHTMAP, new ChanceConfig(200)));
        */
    }
}