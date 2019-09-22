package io.github.krevik.kathairis.world.dimension.biome.biomes;

import io.github.krevik.kathairis.init.ModBlocks;
import net.minecraft.block.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

public final class BiomeFloatingIslands extends BiomeKatharianBiomeBase {
    public static final SurfaceBuilderConfig KATHARIAN_GRASS_DIRT_GRAVEL_SURFACE = new SurfaceBuilderConfig(ModBlocks.KATHAIRIS_GRASS.getDefaultState(), ModBlocks.KATHAIRIS_DIRT.getDefaultState(), Blocks.GRAVEL.getDefaultState());

    public BiomeFloatingIslands() {
        super((new Biome.Builder()).surfaceBuilder(new ConfiguredSurfaceBuilder<>(SurfaceBuilder.DEFAULT, KATHARIAN_GRASS_DIRT_GRAVEL_SURFACE)).precipitation(Biome.RainType.RAIN).category(Biome.Category.OCEAN).depth(-1.0F).scale(0.1F).temperature(0.5F).downfall(0.5F).waterColor(4159204).waterFogColor(329011).parent((String)null));
        DefaultBiomeFeatures.addLakes(this);
        DefaultBiomeFeatures.addCarvers(this);
        //this.addFeature(GenerationStage.Decoration.RAW_GENERATION, createCompositeFeature(KatharianFeatureList.KATHARIAN_HUGE_FLOATING_ISLAND, IFeatureConfig.NO_FEATURE_CONFIG,AT_SURFACE_WITH_CHANCE,new ChanceConfig(40)));
    }
}