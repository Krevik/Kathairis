package io.github.krevik.kathairis.world.dimension.feature;

import io.github.krevik.kathairis.util.ModReference;
import io.github.krevik.kathairis.world.dimension.feature.carver.KatharianWorldCaveCarver;
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
import io.github.krevik.kathairis.world.dimension.structures.crystal_ruins.CrystalRuinsConfig;
import io.github.krevik.kathairis.world.dimension.structures.crystal_ruins.CrystalRuinsPieces;
import io.github.krevik.kathairis.world.dimension.structures.crystal_ruins.StructureCrystalRuins;
import io.github.krevik.kathairis.world.dimension.surface.builder.KathairisSwampSurfaceBuilder;
import io.github.krevik.kathairis.world.dimension.surface.builder.KatharianDesertEdgeSurfaceBuilder;
import io.github.krevik.kathairis.world.dimension.surface.builder.KatharianSoftSandLakesSurfaceBuilder;
import net.minecraft.block.Block;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.PlainsBiome;
import net.minecraft.world.gen.carver.ICarverConfig;
import net.minecraft.world.gen.carver.WorldCarver;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.structure.*;
import net.minecraft.world.gen.surfacebuilders.ISurfaceBuilderConfig;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.GameData;
import net.minecraftforge.registries.ObjectHolder;

import java.util.Locale;

import static io.github.krevik.kathairis.util.ModReference.MOD_ID;
import static io.github.krevik.kathairis.util.ModUtil._null;

public class KatharianFeatureList {

    public static final AbstractTreeFeature<NoFeatureConfig> KATHARIAN_TREE = registerFeature("katharian_tree", new KatharianTreeFeature(NoFeatureConfig::deserialize,true));
    public static final AbstractTreeFeature<NoFeatureConfig> BASIC_STANDARD_TREE = registerFeature("basic_standard_tree", new FeatureBasicKatharianTree(NoFeatureConfig::deserialize,true));
    public static final AbstractTreeFeature<NoFeatureConfig> KATHARIAN_TREE_1 = registerFeature("katharian_tree_1",new FeatureKatharianTallTree1(NoFeatureConfig::deserialize));
    public static final AbstractTreeFeature<NoFeatureConfig> KATHARIAN_TREE_2 = registerFeature("katharian_tree_2",new FeatureKatharianTallTree2(NoFeatureConfig::deserialize));
    public static final AbstractTreeFeature<NoFeatureConfig> KATHARIAN_TREE_3 = registerFeature("katharian_tree_3",new FeatureKatharianTallTree3(NoFeatureConfig::deserialize));
    public static final AbstractTreeFeature<NoFeatureConfig> KATHARIAN_HUGE_TREE_1 = registerFeature("katharian_huge_tree_1",new FeatureKatharianTreeHuge1(NoFeatureConfig::deserialize));
    public static final AbstractTreeFeature<NoFeatureConfig> KATHARIAN_SOUL_TREE = registerFeature("katharian_soul_tree",new FeatureKatharianSoulTree(NoFeatureConfig::deserialize));
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
    public static final AbstractTreeFeature<NoFeatureConfig> KATHARIAN_SWAMP_TALL_TREE_1 = registerFeature("katharian_swamp_tall_tree_1",new FeatureKatharianSwampTallTree1(NoFeatureConfig::deserialize));
    public static final Feature<NoFeatureConfig> BASIC_SWAMP_FEATURE = registerFeature("basic_swamp_feature",new FeatureBasicSwamp(NoFeatureConfig::deserialize));
    public static final Feature<NoFeatureConfig> SMALL_ROCK = registerFeature("small_rock",new FeatureDesertSmallRocks(NoFeatureConfig::deserialize));
    public static final Feature<NoFeatureConfig> KATHARIAN_FOREST_BUSH = registerFeature("katharian_forest_bush",new FeatureKatharianForestBush(NoFeatureConfig::deserialize));
    public static final Feature<BushConfig> KATHARIAN_FOREST_CANDLE_BUSH = registerFeature("katharian_forest_candle_bush",new FeatureForestCandleBush(BushConfig::deserialize));
    public static final Feature<NoFeatureConfig> KATHARIAN_HUGE_FLOATING_ISLAND = registerFeature("katharian_huge_floating_island",new FeatureHugeFloatingIsland(NoFeatureConfig::deserialize));
    public static final Feature<KatharianMinableConfig> ORE = registerFeature("ore", new BasicKatharianOreFeature(KatharianMinableConfig::deserialize));
    public static final Feature<NoFeatureConfig> FEATURE_SOUL_CLOUD_WITH_CHESTS = registerFeature("feature_soul_cloud_with_chests",new FeatureSoulCloudWithChests(NoFeatureConfig::deserialize));
    public static final Feature<NoFeatureConfig> FEATURE_CLOUD_RUINS = registerFeature("feature_cloud_ruins",new FeatureKatharianCloudRuins(NoFeatureConfig::deserialize));
    public static final Feature<NoFeatureConfig> FEATURE_SMALL_RUINS = registerFeature("feature_small_ruins",new FeatureSmallRuins(NoFeatureConfig::deserialize));
    //public static final Structure<CrystalRuinsConfig> CRYSTAL_RUINS = registerFeature("crystal_ruins", new StructureCrystalRuins(CrystalRuinsConfig::deserialize));
    public static final Feature<CrystalRuinsConfig> CRYSTAL_RUINS = registerFeature("crystal_ruins", new StructureCrystalRuins(CrystalRuinsConfig::deserialize));

    public static final WorldCarver<ProbabilityConfig> KATHARIAN_CAVE_WORLD_CARVER = registerWorldCarver("katharian_cave_world_carver",new KatharianWorldCaveCarver(ProbabilityConfig::deserialize,256));
    public static final SurfaceBuilder<SurfaceBuilderConfig> KATHARIAN_SWAMP_SURFACE_BUILDER = registerSurfaceBuilder("katharian_swamp_surface_builder", new KathairisSwampSurfaceBuilder(SurfaceBuilderConfig::deserialize));
    public static final SurfaceBuilder<SurfaceBuilderConfig> KATHARIAN_DESERT_EDGE_SURFACE_BUILDER = registerSurfaceBuilder("katharian_desert_edge_surface_builder", new KatharianDesertEdgeSurfaceBuilder(SurfaceBuilderConfig::deserialize));
    public static final SurfaceBuilder<SurfaceBuilderConfig> KATHARIAN_SOFT_SAND_LAKES_SURFACE_BUILDER = registerSurfaceBuilder("katharian_soft_sand_lakes_surface_builder", new KatharianSoftSandLakesSurfaceBuilder(SurfaceBuilderConfig::deserialize));

    public static IStructurePieceType CRYSTAL_RUINS_PIECES_TYPE = registerStructurePieces(CrystalRuinsPieces.CrystalRuins::new, "crystal_ruins_pieces_type");

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

}
