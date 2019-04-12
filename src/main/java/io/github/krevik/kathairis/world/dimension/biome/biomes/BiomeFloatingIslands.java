package io.github.krevik.kathairis.world.dimension.biome.biomes;

import io.github.krevik.kathairis.init.ModBlocks;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.LakesConfig;
import net.minecraft.world.gen.feature.ProbabilityConfig;
import net.minecraft.world.gen.placement.LakeChanceConfig;
import net.minecraft.world.gen.surfacebuilders.CompositeSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

public final class BiomeFloatingIslands extends BiomeKatharianBiomeBase {
    public static final SurfaceBuilderConfig KATHARIAN_GRASS_DIRT_GRAVEL_SURFACE = new SurfaceBuilderConfig(ModBlocks.KATHAIRIS_GRASS.getDefaultState(), ModBlocks.KATHAIRIS_DIRT.getDefaultState(), GRAVEL);

    public BiomeFloatingIslands() {
        super((new Biome.BiomeBuilder()).surfaceBuilder(new CompositeSurfaceBuilder<>(DEFAULT_SURFACE_BUILDER, KATHARIAN_GRASS_DIRT_GRAVEL_SURFACE)).precipitation(Biome.RainType.RAIN).category(Biome.Category.OCEAN).depth(-1.0F).scale(0.1F).temperature(0.5F).downfall(0.5F).waterColor(4159204).waterFogColor(329011).parent((String)null));
        this.addCarver(GenerationStage.Carving.AIR, createWorldCarverWrapper(CAVE_WORLD_CARVER, new ProbabilityConfig(0.14285715F)));
        this.addCarver(GenerationStage.Carving.AIR, createWorldCarverWrapper(CANYON_WORLD_CARVER, new ProbabilityConfig(0.02F)));
        this.addCarver(GenerationStage.Carving.LIQUID, createWorldCarverWrapper(UNDERWATER_CANYON_WORLD_CARVER, new ProbabilityConfig(0.02F)));
        this.addFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS, createCompositeFeature(Feature.LAKES, new LakesConfig(Blocks.WATER), LAKE_WATER, new LakeChanceConfig(4)));
        this.addFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS, createCompositeFeature(Feature.LAKES, new LakesConfig(Blocks.LAVA), LAVA_LAKE, new LakeChanceConfig(80)));
        //this.addFeature(GenerationStage.Decoration.RAW_GENERATION, createCompositeFeature(KatharianFeatureList.KATHARIAN_HUGE_FLOATING_ISLAND, IFeatureConfig.NO_FEATURE_CONFIG,AT_SURFACE_WITH_CHANCE,new ChanceConfig(40)));
    }
}