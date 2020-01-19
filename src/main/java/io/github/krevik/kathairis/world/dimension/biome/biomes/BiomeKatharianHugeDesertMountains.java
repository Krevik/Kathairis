package io.github.krevik.kathairis.world.dimension.biome.biomes;

import io.github.krevik.kathairis.init.ModBlocks;
import io.github.krevik.kathairis.world.dimension.feature.KatharianFeatureList;
import net.minecraft.block.Blocks;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.BlockStateFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.placement.ChanceConfig;
import net.minecraft.world.gen.placement.FrequencyConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

import java.awt.*;

public class BiomeKatharianHugeDesertMountains extends BiomeKatharianBiomeBase{
    public static final SurfaceBuilderConfig SAND_SAND_SANDSTONE_SURFACE = new SurfaceBuilderConfig(ModBlocks.KATHAIRIS_SAND.getDefaultState(), ModBlocks.KATHAIRIS_SANDSTONE.getDefaultState(), ModBlocks.KATHAIRIS_SAND.getDefaultState());

    public BiomeKatharianHugeDesertMountains() {
        super((new Builder()).surfaceBuilder(SurfaceBuilder.DEFAULT, SAND_SAND_SANDSTONE_SURFACE).precipitation(RainType.NONE).category(Category.DESERT).depth(1.8F).scale(2F).temperature(2.0F).downfall(0.0F).waterColor(4159204).waterFogColor(329011).parent((String)null));
        this.addFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS, Feature.LAKE.func_225566_b_(new BlockStateFeatureConfig(ModBlocks.KATHAIRIS_SAND.getDefaultState())).func_227228_a_(Placement.WATER_LAKE.func_227446_a_(new ChanceConfig(15))));
        this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, KatharianFeatureList.KATHARIAN_DEAD_BUSH.func_225566_b_(IFeatureConfig.NO_FEATURE_CONFIG).func_227228_a_(Placement.COUNT_HEIGHTMAP_DOUBLE.func_227446_a_(new FrequencyConfig(2))));
        this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, KatharianFeatureList.KATHARIAN_ROCKTUS.func_225566_b_(IFeatureConfig.NO_FEATURE_CONFIG).func_227228_a_(Placement.COUNT_HEIGHTMAP_DOUBLE.func_227446_a_(new FrequencyConfig(2))));
        this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, KatharianFeatureList.KATHARIAN_CACTUS.func_225566_b_(IFeatureConfig.NO_FEATURE_CONFIG).func_227228_a_(Placement.COUNT_HEIGHTMAP_DOUBLE.func_227446_a_(new FrequencyConfig(10))));
        KatharianFeatureList.addDefaultSkyDecorations(this);
        baseGrassColor=Color.ORANGE;
    }
}