package io.github.krevik.kathairis.world.dimension.biome.biomes;

import io.github.krevik.kathairis.init.ModBlocks;
import io.github.krevik.kathairis.world.dimension.feature.KatharianFeatureList;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.PlainsBiome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.BlockStateFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.placement.ChanceConfig;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.FrequencyConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

import java.awt.*;

public class BiomeKatharianDesert extends BiomeKatharianBiomeBase{
    public static final SurfaceBuilderConfig SAND_SAND_SANDSTONE_SURFACE = new SurfaceBuilderConfig(ModBlocks.KATHAIRIS_SAND.getDefaultState(), ModBlocks.KATHAIRIS_SANDSTONE.getDefaultState(), ModBlocks.SOFT_SAND.getDefaultState());
    public BiomeKatharianDesert() {
        super((new Biome.Builder()).surfaceBuilder(SurfaceBuilder.DEFAULT, SAND_SAND_SANDSTONE_SURFACE).precipitation(Biome.RainType.NONE).category(Biome.Category.DESERT).depth(0.2F).scale(0.1F).temperature(2.0F).downfall(0.0F).waterColor(4159204).waterFogColor(329011).parent((String)null));
        this.addFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS, Feature.LAKE.func_225566_b_(new BlockStateFeatureConfig(ModBlocks.SOFT_SAND.getDefaultState())).func_227228_a_(Placement.LAVA_LAKE.func_227446_a_(new ChanceConfig(15))));
        this.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, KatharianFeatureList.KATHARIAN_ROCK_MUSHROOM.func_225566_b_(IFeatureConfig.NO_FEATURE_CONFIG).func_227228_a_(Placement.COUNT_RANGE.func_227446_a_(new CountRangeConfig(12,0,0,128))));
        this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, KatharianFeatureList.KATHARIAN_DEAD_BUSH.func_225566_b_(IFeatureConfig.NO_FEATURE_CONFIG).func_227228_a_(Placement.COUNT_HEIGHTMAP_DOUBLE.func_227446_a_(new FrequencyConfig(2))));
        this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, KatharianFeatureList.KATHARIAN_ROCKTUS.func_225566_b_(IFeatureConfig.NO_FEATURE_CONFIG).func_227228_a_(Placement.COUNT_HEIGHTMAP_DOUBLE.func_227446_a_(new FrequencyConfig(2))));
        this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, KatharianFeatureList.KATHARIAN_CACTUS.func_225566_b_(IFeatureConfig.NO_FEATURE_CONFIG).func_227228_a_(Placement.COUNT_HEIGHTMAP_DOUBLE.func_227446_a_(new FrequencyConfig(10))));
        this.addFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS, KatharianFeatureList.MAGNETHIUM_HALF_SPHERE.func_225566_b_(IFeatureConfig.NO_FEATURE_CONFIG).func_227228_a_(Placement.CHANCE_HEIGHTMAP.func_227446_a_(new ChanceConfig(100))));

        KatharianFeatureList.addDefaultSkyDecorations(this);
        //this.addStructure((Structure<CrystalRuinsConfig>)KatharianFeatureList.CRYSTAL_RUINS,new CrystalRuinsConfig("crystal_ruins/entrances",6));
        //this.addFeature(GenerationStage.Decoration.UNDERGROUND_STRUCTURES, createDecoratedFeature(KatharianFeatureList.CRYSTAL_RUINS, new CrystalRuinsConfig("crystal_ruins/entrances",6), Placement.NOPE, IPlacementConfig.NO_PLACEMENT_CONFIG));

        baseGrassColor=Color.ORANGE;
    }

}
