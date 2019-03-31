package io.github.krevik.kathairis.world.dimension.feature;

import io.github.krevik.kathairis.world.dimension.feature.desert.FeatureDesertSandLayers;
import io.github.krevik.kathairis.world.dimension.feature.desert.FeatureKathairisCactus;
import io.github.krevik.kathairis.world.dimension.feature.desert.FeatureKathairisRocktus;
import io.github.krevik.kathairis.world.dimension.feature.desert.FeatureRockMushrooms;
import io.github.krevik.kathairis.world.dimension.feature.forest.FeatureSteppedSucculent;
import io.github.krevik.kathairis.world.dimension.feature.plainfields.FeaturePlainFields;
import io.github.krevik.kathairis.world.dimension.feature.swamp.FeatureBasicSwamp;
import io.github.krevik.kathairis.world.dimension.feature.tree.FeatureBasicKathairisTree;
import io.github.krevik.kathairis.world.dimension.feature.tree.FeatureKathairisSwampTallTree1;
import io.github.krevik.kathairis.world.dimension.feature.tree.FeatureKathairisTallTree1;
import io.github.krevik.kathairis.world.dimension.feature.tree.FeatureKathairisTallTree2;
import io.github.krevik.kathairis.world.dimension.feature.tree.FeatureKathairisTallTree3;
import io.github.krevik.kathairis.world.dimension.feature.tree.FeatureKathairisTreeHuge1;
import io.github.krevik.kathairis.world.dimension.feature.tree.KathairisTreeFeature;
import io.github.krevik.kathairis.world.dimension.surface.builder.KathairisSwampSurfaceBuilder;
import net.minecraft.world.gen.carver.WorldCarver;
import net.minecraft.world.gen.feature.AbstractTreeFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.ProbabilityConfig;
import net.minecraft.world.gen.surfacebuilders.ISurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

/**
 * @author Krevik
 */
public class KathairisFeatureList {

	public static final WorldCarver<ProbabilityConfig> KATHAIRIS_CAVE_WORLD_CARVER = new KathairisWorldCaveCarver();

	public static final AbstractTreeFeature<NoFeatureConfig> KATHAIRIS_TREE = new KathairisTreeFeature(true);
	public static final AbstractTreeFeature<NoFeatureConfig> BASIC_STANDARD_TREE = new FeatureBasicKathairisTree(true, false);
	public static final AbstractTreeFeature<NoFeatureConfig> KATHAIRIS_TREE_1 = new FeatureKathairisTallTree1();
	public static final AbstractTreeFeature<NoFeatureConfig> KATHAIRIS_TREE_2 = new FeatureKathairisTallTree2();
	public static final AbstractTreeFeature<NoFeatureConfig> KATHAIRIS_TREE_3 = new FeatureKathairisTallTree3();
	public static final AbstractTreeFeature<NoFeatureConfig> KATHAIRIS_HUGE_TREE_1 = new FeatureKathairisTreeHuge1();
	public static final Feature<NoFeatureConfig> KATHAIRIS_CLOUD = new FeatureKathairisCloud();
	public static final Feature<NoFeatureConfig> KATHAIRIS_CACTUS = new FeatureKathairisCactus();
	public static final Feature<NoFeatureConfig> KATHAIRIS_DEAD_BUSH = new FeatureKathairisDeadBush();
	public static final Feature<NoFeatureConfig> KATHAIRIS_ROCK_MUSHROOM = new FeatureRockMushrooms();
	public static final Feature<NoFeatureConfig> KATHAIRIS_PLAIN_FIELDS = new FeaturePlainFields();
	public static final Feature<NoFeatureConfig> KATHAIRIS_CLOUD_MINI_ISLAND = new FeatureKathairisFloatingMiniIsland();
	public static final Feature<NoFeatureConfig> KATHAIRIS_CRYSTAL_CHAMBER = new FeatureCrystalChamber();
	public static final Feature<NoFeatureConfig> KATHAIRIS_ROCKTUS = new FeatureKathairisRocktus();
	public static final Feature<NoFeatureConfig> SAND_LAYERS = new FeatureDesertSandLayers();
	public static final Feature<NoFeatureConfig> STEPPED_SUCCULENT = new FeatureSteppedSucculent();
	public static final AbstractTreeFeature<NoFeatureConfig> KATHAIRIS_SWAMP_TALL_TREE_1 = new FeatureKathairisSwampTallTree1();
	public static final Feature<NoFeatureConfig> BASIC_SWAMP_FEATURE = new FeatureBasicSwamp();

	public static final ISurfaceBuilder<SurfaceBuilderConfig> KATHAIRIS_SWAMP_SURFACE_BUILDER = new KathairisSwampSurfaceBuilder();

}
