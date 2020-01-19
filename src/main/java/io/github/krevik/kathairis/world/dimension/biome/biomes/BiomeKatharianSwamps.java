package io.github.krevik.kathairis.world.dimension.biome.biomes;


import io.github.krevik.kathairis.init.ModBlocks;
import io.github.krevik.kathairis.world.dimension.feature.KatharianFeatureList;
import net.minecraft.block.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.BlockStateFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.placement.ChanceConfig;
import net.minecraft.world.gen.placement.FrequencyConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

import java.awt.*;

public class BiomeKatharianSwamps extends BiomeKatharianBiomeBase {

    public static final SurfaceBuilderConfig GRASS_WATER_SURFACE = new SurfaceBuilderConfig(ModBlocks.KATHAIRIS_GRASS.getDefaultState(), ModBlocks.KATHAIRIS_DIRT.getDefaultState(), Blocks.WATER.getDefaultState());


    public BiomeKatharianSwamps() {
        super((new Biome.Builder()).surfaceBuilder(KatharianFeatureList.KATHARIAN_SWAMP_SURFACE_BUILDER, GRASS_WATER_SURFACE).precipitation(RainType.RAIN).category(Category.SWAMP).depth(-0.1F).scale(0.0000005F).temperature(2.0F).downfall(0.0F).waterColor(4159204).waterFogColor(329011).parent((String)null));
        this.addFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS,Feature.LAKE.func_225566_b_(new BlockStateFeatureConfig(ModBlocks.MUD_BLOCK.getDefaultState())).func_227228_a_(Placement.WATER_LAKE.func_227446_a_(new ChanceConfig(25))));
        this.addFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS,Feature.LAKE.func_225566_b_(new BlockStateFeatureConfig(Blocks.CLAY.getDefaultState())).func_227228_a_(Placement.WATER_LAKE.func_227446_a_(new ChanceConfig(25))));
        this.addFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS,Feature.LAKE.func_225566_b_(new BlockStateFeatureConfig(Blocks.GRAVEL.getDefaultState())).func_227228_a_(Placement.WATER_LAKE.func_227446_a_(new ChanceConfig(25))));
        this.addFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS,Feature.LAKE.func_225566_b_(new BlockStateFeatureConfig(Blocks.WATER.getDefaultState())).func_227228_a_(Placement.WATER_LAKE.func_227446_a_(new ChanceConfig(25))));
        this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, KatharianFeatureList.KATHARIAN_SWAMP_TALL_TREE_1.func_225566_b_(KatharianFeatureList.MYSTIC_TREE_FEATURE_CONFIG).func_227228_a_(Placement.COUNT_EXTRA_HEIGHTMAP.func_227446_a_(new AtSurfaceWithExtraConfig(3, 0.1F, 1))));
        this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, KatharianFeatureList.BASIC_SWAMP_FEATURE.func_225566_b_(IFeatureConfig.NO_FEATURE_CONFIG).func_227228_a_(Placement.COUNT_EXTRA_HEIGHTMAP.func_227446_a_(new AtSurfaceWithExtraConfig(2, 0.5F, 1))));
        this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.field_227248_z_.func_225566_b_(KatharianFeatureList.BLOCK_CLUSTER_FUNGI_FEATURE_CONFIG).func_227228_a_(Placement.COUNT_HEIGHTMAP.func_227446_a_(new FrequencyConfig(2))));
        this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.field_227248_z_.func_225566_b_(KatharianFeatureList.BLOCK_CLUSTER_TALL_GRASS_FEATURE_CONFIG).func_227228_a_(Placement.COUNT_HEIGHTMAP_DOUBLE.func_227446_a_(new FrequencyConfig(5))));
        this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.field_227248_z_.func_225566_b_(KatharianFeatureList.BLOCK_CLUSTER_MINI_GRASS_FEATURE_CONFIG).func_227228_a_(Placement.COUNT_HEIGHTMAP_DOUBLE.func_227446_a_(new FrequencyConfig(3))));
        KatharianFeatureList.addDefaultSkyDecorations(this);
        this.baseGrassColor=new Color(Color.YELLOW.getRed(),Color.YELLOW.getGreen(),Color.YELLOW.getBlue());

    }
}
