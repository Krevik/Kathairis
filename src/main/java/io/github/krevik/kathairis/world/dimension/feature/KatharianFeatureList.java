package io.github.krevik.kathairis.world.dimension.feature;

import io.github.krevik.kathairis.init.ModBlocks;
import io.github.krevik.kathairis.util.ModReference;
import io.github.krevik.kathairis.world.dimension.feature.carver.KatharianWorldCaveCarver;
import io.github.krevik.kathairis.world.dimension.feature.config.KatharianTreeFeatureConfig;
import io.github.krevik.kathairis.world.dimension.feature.desert.*;
import io.github.krevik.kathairis.world.dimension.feature.floating_islands.FeatureHugeFloatingIsland;
import io.github.krevik.kathairis.world.dimension.feature.forest.FeatureForestCandleBush;
import io.github.krevik.kathairis.world.dimension.feature.forest.FeatureSteppedSucculent;
import io.github.krevik.kathairis.world.dimension.feature.plainfields.FeaturePlainFields;
import io.github.krevik.kathairis.world.dimension.feature.rewarding.FeatureKatharianCloudRuins;
import io.github.krevik.kathairis.world.dimension.feature.rewarding.FeatureKatharianFloatingMiniIsland;
import io.github.krevik.kathairis.world.dimension.feature.rewarding.FeatureSmallRuins;
import io.github.krevik.kathairis.world.dimension.feature.rewarding.FeatureSoulCloudWithChests;
import io.github.krevik.kathairis.world.dimension.feature.swamp.FeatureBasicSwamp;
import io.github.krevik.kathairis.world.dimension.feature.tree.*;
import io.github.krevik.kathairis.world.dimension.structures.cloud_temple.CloudTemplePieces;
import io.github.krevik.kathairis.world.dimension.structures.cloud_temple.StructureCloudTemple;
import io.github.krevik.kathairis.world.dimension.structures.crystal_ruins.CrystalRuinsConfig;
import io.github.krevik.kathairis.world.dimension.structures.crystal_ruins.CrystalRuinsPieces;
import io.github.krevik.kathairis.world.dimension.structures.crystal_ruins.StructureCrystalRuins;
import io.github.krevik.kathairis.world.dimension.surface.builder.KathairisSwampSurfaceBuilder;
import io.github.krevik.kathairis.world.dimension.surface.builder.KatharianDesertEdgeSurfaceBuilder;
import io.github.krevik.kathairis.world.dimension.surface.builder.KatharianSoftSandLakesSurfaceBuilder;
import net.minecraft.block.Blocks;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.blockplacer.SimpleBlockPlacer;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.carver.ICarverConfig;
import net.minecraft.world.gen.carver.WorldCarver;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.structure.IStructurePieceType;
import net.minecraft.world.gen.foliageplacer.BlobFoliagePlacer;
import net.minecraft.world.gen.placement.ChanceConfig;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.FrequencyConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.surfacebuilders.ISurfaceBuilderConfig;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

import java.util.Locale;

public class KatharianFeatureList {

    public static final TreeFeatureConfig MYSTIC_TREE_FEATURE_CONFIG = (new TreeFeatureConfig.Builder(new SimpleBlockStateProvider(ModBlocks.MYSTIC_LOG.getDefaultState()), new SimpleBlockStateProvider(ModBlocks.MYSTIC_LEAVES.getDefaultState()), new BlobFoliagePlacer(0, 0))).setSapling((net.minecraftforge.common.IPlantable) ModBlocks.MYSTIC_SAPLING).func_225568_b_();
    public static final BlockClusterFeatureConfig BLOCK_CLUSTER_TALL_GRASS_FEATURE_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(ModBlocks.KATHAIRIS_TALL_GRASS.getDefaultState()), new SimpleBlockPlacer())).func_227315_a_(64).func_227317_b_().func_227322_d_();
    public static final BlockClusterFeatureConfig BLOCK_CLUSTER_MINI_GRASS_FEATURE_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(ModBlocks.KATHAIRIS_MINI_GRASS.getDefaultState()), new SimpleBlockPlacer())).func_227315_a_(64).func_227317_b_().func_227322_d_();
    public static final BlockClusterFeatureConfig BLOCK_CLUSTER_GOOSEBERRY_FEATURE_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(ModBlocks.GOOSEBERRY_BUSH.getDefaultState()), new SimpleBlockPlacer())).func_227315_a_(64).func_227317_b_().func_227322_d_();
    public static final BlockClusterFeatureConfig BLOCK_CLUSTER_FUNGI_FEATURE_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(ModBlocks.KATHAIRIS_FUNGI.getDefaultState()), new SimpleBlockPlacer())).func_227315_a_(64).func_227317_b_().func_227322_d_();
    public static final BlockClusterFeatureConfig BLOCK_CLUSTER_NIGHT_FLOWER_FEATURE_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(ModBlocks.KATHAIRIS_NIGHT_FLOWER.getDefaultState()), new SimpleBlockPlacer())).func_227315_a_(64).func_227317_b_().func_227322_d_();
    public static final BlockClusterFeatureConfig BLOCK_CLUSTER_VILYRIA_FEATURE_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(ModBlocks.VILYRIA.getDefaultState()), new SimpleBlockPlacer())).func_227315_a_(64).func_227317_b_().func_227322_d_();
    public static final BlockClusterFeatureConfig BLOCK_CLUSTER_MULTI_GRASS_FEATURE_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(ModBlocks.KATHAIRIS_MULTI_GRASS.getDefaultState()), new SimpleBlockPlacer())).func_227315_a_(64).func_227317_b_().func_227322_d_();
    public static final BlockClusterFeatureConfig BLOCK_CLUSTER_BUTTERFLY_FLOWER_FEATURE_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(ModBlocks.BUTTERFLY_FLOWER.getDefaultState()), new SimpleBlockPlacer())).func_227315_a_(64).func_227317_b_().func_227322_d_();
    public static final BlockClusterFeatureConfig BLOCK_CLUSTER_EYE_PLANT_FEATURE_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(ModBlocks.EYE_PLANT.getDefaultState()), new SimpleBlockPlacer())).func_227315_a_(64).func_227317_b_().func_227322_d_();
    public static final BlockClusterFeatureConfig BLOCK_CLUSTER_BISON_STARS_FEATURE_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(ModBlocks.BISON_STARS.getDefaultState()), new SimpleBlockPlacer())).func_227315_a_(64).func_227317_b_().func_227322_d_();
    public static final BlockClusterFeatureConfig BLOCK_CLUSTER_FRUP_PLANT_FEATURE_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(ModBlocks.FRUP_PLANT.getDefaultState()), new SimpleBlockPlacer())).func_227315_a_(64).func_227317_b_().func_227322_d_();

    public static final AbstractTreeFeature<TreeFeatureConfig> KATHARIAN_TREE = registerFeature("katharian_tree", new KatharianTreeFeature(KatharianTreeFeatureConfig::deserializeMystic));
    public static final AbstractTreeFeature<TreeFeatureConfig> BASIC_STANDARD_TREE = registerFeature("basic_standard_tree", new FeatureBasicKatharianTree(KatharianTreeFeatureConfig::deserializeMystic));
    public static final AbstractTreeFeature<TreeFeatureConfig> KATHARIAN_TREE_1 = registerFeature("katharian_tree_1",new FeatureKatharianTallTree1(KatharianTreeFeatureConfig::deserializeMystic));
    public static final AbstractTreeFeature<TreeFeatureConfig> KATHARIAN_TREE_2 = registerFeature("katharian_tree_2",new FeatureKatharianTallTree2(KatharianTreeFeatureConfig::deserializeMystic));
    public static final AbstractTreeFeature<TreeFeatureConfig> KATHARIAN_TREE_3 = registerFeature("katharian_tree_3",new FeatureKatharianTallTree3(KatharianTreeFeatureConfig::deserializeShiny));
    public static final AbstractTreeFeature<TreeFeatureConfig> KATHARIAN_HUGE_TREE_1 = registerFeature("katharian_huge_tree_1",new FeatureKatharianTreeHuge1(KatharianTreeFeatureConfig::deserializeMystic));
    public static final AbstractTreeFeature<TreeFeatureConfig> KATHARIAN_SOUL_TREE = registerFeature("katharian_soul_tree",new FeatureKatharianSoulTree(KatharianTreeFeatureConfig::deserializeSoul));
    public static final Feature<NoFeatureConfig> KATHARIAN_CLOUD = registerFeature("katharian_cloud",new FeatureKatharianCloud(NoFeatureConfig::deserialize));
    public static final Feature<NoFeatureConfig> KATHARIAN_CACTUS = registerFeature("katharian_cactus",new FeatureKatharianCactus(NoFeatureConfig::deserialize));
    public static final Feature<NoFeatureConfig> KATHARIAN_DEAD_BUSH = registerFeature("katharian_dead_bush",new FeatureKatharianDeadBush(NoFeatureConfig::deserialize));
    public static final Feature<NoFeatureConfig> KATHARIAN_ROCK_MUSHROOM = registerFeature("katharian_rock_mushroom",new FeatureRockMushrooms(NoFeatureConfig::deserialize));
    public static final Feature<NoFeatureConfig> KATHARIAN_PLAIN_FIELDS = registerFeature("katharian_plain_fields",new FeaturePlainFields(NoFeatureConfig::deserialize));
    public static final Feature<NoFeatureConfig> KATHARIAN_CLOUD_MINI_ISLAND = registerFeature("katharian_cloud_mini_island",new FeatureKatharianFloatingMiniIsland(NoFeatureConfig::deserialize));
    public static final Feature<NoFeatureConfig> KATHARIAN_CRYSTAL_CHAMBER = registerFeature("katharian_crystal_chamber",new FeatureCrystalChamber(NoFeatureConfig::deserialize));
    public static final Feature<NoFeatureConfig> KATHARIAN_ROCKTUS = registerFeature("katharian_rocktus",new FeatureKatharianRocktus(NoFeatureConfig::deserialize));
    public static final Feature<NoFeatureConfig> SAND_LAYERS = registerFeature("sand_layers",new FeatureDesertSandLayers(NoFeatureConfig::deserialize));
    public static final Feature<NoFeatureConfig> STEPPED_SUCCULENT = registerFeature("stepped_succulent",new FeatureSteppedSucculent(NoFeatureConfig::deserialize));
    public static final AbstractTreeFeature<TreeFeatureConfig> KATHARIAN_SWAMP_TALL_TREE_1 = registerFeature("katharian_swamp_tall_tree_1",new FeatureKatharianSwampTallTree1(KatharianTreeFeatureConfig::deserializeElderwillow));
    public static final Feature<NoFeatureConfig> BASIC_SWAMP_FEATURE = registerFeature("basic_swamp_feature",new FeatureBasicSwamp(NoFeatureConfig::deserialize));
    public static final Feature<NoFeatureConfig> SMALL_ROCK = registerFeature("small_rock",new FeatureDesertSmallRocks(NoFeatureConfig::deserialize));
    public static final Feature<NoFeatureConfig> KATHARIAN_FOREST_BUSH = registerFeature("katharian_forest_bush",new FeatureKatharianForestBush(NoFeatureConfig::deserialize));
    public static final Feature<NoFeatureConfig> KATHARIAN_FOREST_CANDLE_BUSH = registerFeature("katharian_forest_candle_bush",new FeatureForestCandleBush(NoFeatureConfig::deserialize));
    public static final Feature<NoFeatureConfig> KATHARIAN_HUGE_FLOATING_ISLAND = registerFeature("katharian_huge_floating_island",new FeatureHugeFloatingIsland(NoFeatureConfig::deserialize));
    public static final Feature<KatharianMinableConfig> ORE = registerFeature("ore", new BasicKatharianOreFeature(KatharianMinableConfig::deserialize));
    public static final Feature<NoFeatureConfig> FEATURE_SOUL_CLOUD_WITH_CHESTS = registerFeature("feature_soul_cloud_with_chests",new FeatureSoulCloudWithChests(NoFeatureConfig::deserialize));
    public static final Feature<NoFeatureConfig> FEATURE_CLOUD_RUINS = registerFeature("feature_cloud_ruins",new FeatureKatharianCloudRuins(NoFeatureConfig::deserialize));
    public static final Feature<NoFeatureConfig> FEATURE_SMALL_RUINS = registerFeature("feature_small_ruins",new FeatureSmallRuins(NoFeatureConfig::deserialize));
    public static final Feature<CrystalRuinsConfig> CRYSTAL_RUINS = registerFeature("crystal_ruins", new StructureCrystalRuins(CrystalRuinsConfig::deserialize));
    public static final Feature<NoFeatureConfig> CLOUD_TEMPLE = registerFeature("cloud_temple", new StructureCloudTemple(NoFeatureConfig::deserialize));

    public static final WorldCarver<ProbabilityConfig> KATHARIAN_CAVE_WORLD_CARVER = registerWorldCarver("katharian_cave_world_carver",new KatharianWorldCaveCarver(ProbabilityConfig::deserialize,256));
    public static final SurfaceBuilder<SurfaceBuilderConfig> KATHARIAN_SWAMP_SURFACE_BUILDER = registerSurfaceBuilder("katharian_swamp_surface_builder", new KathairisSwampSurfaceBuilder(SurfaceBuilderConfig::deserialize));
    public static final SurfaceBuilder<SurfaceBuilderConfig> KATHARIAN_DESERT_EDGE_SURFACE_BUILDER = registerSurfaceBuilder("katharian_desert_edge_surface_builder", new KatharianDesertEdgeSurfaceBuilder(SurfaceBuilderConfig::deserialize));
    public static final SurfaceBuilder<SurfaceBuilderConfig> KATHARIAN_SOFT_SAND_LAKES_SURFACE_BUILDER = registerSurfaceBuilder("katharian_soft_sand_lakes_surface_builder", new KatharianSoftSandLakesSurfaceBuilder(SurfaceBuilderConfig::deserialize));

    public static IStructurePieceType CRYSTAL_RUINS_PIECES_TYPE = registerStructurePieces(CrystalRuinsPieces.CrystalRuins::new, "crystal_ruins_pieces_type");
    public static IStructurePieceType CLOUD_TEMPLE_PIECES_TYPE = registerStructurePieces(CloudTemplePieces.CloudTemple::new, "cloud_temple_pieces_type");

    static IStructurePieceType registerStructurePieces(IStructurePieceType p_214750_0_, String key) {
        return Registry.register(Registry.STRUCTURE_PIECE, key.toLowerCase(Locale.ROOT), p_214750_0_);
    }

    private static <C extends IFeatureConfig, F extends Feature<C>> F registerFeature(String key, F value) {
        return (F)(Registry.<Feature<?>>register(Registry.FEATURE, ModReference.MOD_ID+":"+key, value));
    }

    private static <C extends ISurfaceBuilderConfig, F extends SurfaceBuilder<C>> F registerSurfaceBuilder(String key, F builderIn) {
        return (F)(Registry.<SurfaceBuilder<?>>register(Registry.SURFACE_BUILDER, ModReference.MOD_ID+":"+key, builderIn));
    }

    private static <C extends ICarverConfig, F extends WorldCarver<C>> F registerWorldCarver(String key, F carver) {
        return (F)(Registry.<WorldCarver<?>>register(Registry.CARVER, ModReference.MOD_ID+":"+key, carver));
    }

    public static void addDefaultSkyDecorations(Biome biome) {
        biome.addFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS, KatharianFeatureList.KATHARIAN_CLOUD.func_225566_b_(IFeatureConfig.NO_FEATURE_CONFIG).func_227228_a_(Placement.CHANCE_HEIGHTMAP.func_227446_a_(new ChanceConfig(3))));
        biome.addFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS, KatharianFeatureList.KATHARIAN_CLOUD_MINI_ISLAND.func_225566_b_(IFeatureConfig.NO_FEATURE_CONFIG).func_227228_a_(Placement.CHANCE_HEIGHTMAP.func_227446_a_(new ChanceConfig(200))));
        biome.addFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS, KatharianFeatureList.FEATURE_SOUL_CLOUD_WITH_CHESTS.func_225566_b_(IFeatureConfig.NO_FEATURE_CONFIG).func_227228_a_(Placement.CHANCE_HEIGHTMAP.func_227446_a_(new ChanceConfig(750))));
        biome.addFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS, KatharianFeatureList.FEATURE_CLOUD_RUINS.func_225566_b_(IFeatureConfig.NO_FEATURE_CONFIG).func_227228_a_(Placement.CHANCE_HEIGHTMAP.func_227446_a_(new ChanceConfig(750))));
    }

    public static void addDefaultCarvers(Biome biomeIn) {
        biomeIn.addCarver(GenerationStage.Carving.AIR, Biome.createCarver(KatharianFeatureList.KATHARIAN_CAVE_WORLD_CARVER, new ProbabilityConfig(0.14285715F)));
    }

    public static void addDefaultOres(Biome biome){
        biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, KatharianFeatureList.ORE.func_225566_b_(new KatharianMinableConfig(KatharianMinableConfig.FillerBlockType.NATURAL_STONE, ModBlocks.TITANIUM_ORE.getDefaultState(),9)).func_227228_a_(Placement.COUNT_RANGE.func_227446_a_(new CountRangeConfig(20, 0, 0, 64))));
        biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, KatharianFeatureList.ORE.func_225566_b_(new KatharianMinableConfig(KatharianMinableConfig.FillerBlockType.NATURAL_STONE, ModBlocks.REVENUM_ORE.getDefaultState(),18)).func_227228_a_(Placement.COUNT_RANGE.func_227446_a_(new CountRangeConfig(20, 0, 0, 128))));
    }

    public static void addDefaultLakes(Biome biome){
        biome.addFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS, Feature.LAKE.func_225566_b_(new BlockStateFeatureConfig(Blocks.WATER.getDefaultState())).func_227228_a_(Placement.WATER_LAKE.func_227446_a_(new ChanceConfig(4))));
        biome.addFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS, Feature.LAKE.func_225566_b_(new BlockStateFeatureConfig(Blocks.LAVA.getDefaultState())).func_227228_a_(Placement.LAVA_LAKE.func_227446_a_(new ChanceConfig(80))));
    }

    public static void addWaterLakes(Biome biome){
        biome.addFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS, Feature.LAKE.func_225566_b_(new BlockStateFeatureConfig(Blocks.WATER.getDefaultState())).func_227228_a_(Placement.WATER_LAKE.func_227446_a_(new ChanceConfig(4))));
    }
}
