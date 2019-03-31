package io.github.krevik.kathairis.world.dimension.biome.biomes;

import io.github.krevik.kathairis.world.dimension.feature.KathairisFeatureList;
import io.github.krevik.kathairis.world.dimension.feature.KathairisMinableConfig;
import net.minecraft.init.Blocks;
import net.minecraft.init.Fluids;
import net.minecraft.world.gen.GenerationStage.Carving;
import net.minecraft.world.gen.GenerationStage.Decoration;
import net.minecraft.world.gen.feature.BushConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.LakesConfig;
import net.minecraft.world.gen.feature.LiquidsConfig;
import net.minecraft.world.gen.feature.MinableConfig;
import net.minecraft.world.gen.feature.ProbabilityConfig;
import net.minecraft.world.gen.feature.TallGrassConfig;
import net.minecraft.world.gen.placement.ChanceConfig;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.FrequencyConfig;
import net.minecraft.world.gen.placement.LakeChanceConfig;
import net.minecraft.world.gen.surfacebuilders.CompositeSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

import static io.github.krevik.kathairis.init.ModBlocks.GOOSEBERRY_BUSH;
import static io.github.krevik.kathairis.init.ModBlocks.KATHAIRIS_DIRT;
import static io.github.krevik.kathairis.init.ModBlocks.KATHAIRIS_FUNGI;
import static io.github.krevik.kathairis.init.ModBlocks.KATHAIRIS_GRASS;
import static io.github.krevik.kathairis.init.ModBlocks.KATHAIRIS_MINI_GRASS;
import static io.github.krevik.kathairis.init.ModBlocks.KATHAIRIS_NIGHT_FLOWER;
import static io.github.krevik.kathairis.init.ModBlocks.KATHAIRIS_TALL_GRASS;
import static io.github.krevik.kathairis.init.ModBlocks.REVENUM_ORE;
import static io.github.krevik.kathairis.init.ModBlocks.TITANIUM_ORE;
import static io.github.krevik.kathairis.init.ModBlocks.VILYRIA;

/**
 * @author Krevik
 */
public class BiomeKathairisRiver extends BiomeKathairisBiomeBase {

	public static final SurfaceBuilderConfig KATHAIRIS_GRASS_DIRT_GRAVEL_SURFACE = new SurfaceBuilderConfig(KATHAIRIS_GRASS.getDefaultState(), KATHAIRIS_DIRT.getDefaultState(), GRAVEL);

	public BiomeKathairisRiver() {
		super((new BiomeBuilder()).surfaceBuilder(new CompositeSurfaceBuilder<>(DEFAULT_SURFACE_BUILDER, KATHAIRIS_GRASS_DIRT_GRAVEL_SURFACE)).precipitation(RainType.RAIN).category(Category.RIVER).depth(-0.7F).scale(0.0F).temperature(0.5F).downfall(0.5F).waterColor(4159204).waterFogColor(329011).parent(null));
		this.addCarver(Carving.AIR, createWorldCarverWrapper(KathairisFeatureList.KATHAIRIS_CAVE_WORLD_CARVER, new ProbabilityConfig(0.14285715F)));
		this.addCarver(Carving.AIR, createWorldCarverWrapper(CANYON_WORLD_CARVER, new ProbabilityConfig(0.02F)));
		this.addFeature(Decoration.LOCAL_MODIFICATIONS, createCompositeFeature(Feature.LAKES, new LakesConfig(Blocks.WATER), LAKE_WATER, new LakeChanceConfig(4)));
		this.addFeature(Decoration.LOCAL_MODIFICATIONS, createCompositeFeature(Feature.LAKES, new LakesConfig(Blocks.LAVA), LAVA_LAKE, new LakeChanceConfig(80)));
		//this.addFeature(Decoration.UNDERGROUND_ORES, createCompositeFeature(Feature.MINABLE, new MinableConfig(KathairisMinableConfig.IS_ROCK, Blocks.DIRT.getDefaultState(), 33), COUNT_RANGE, new CountRangeConfig(10, 0, 0, 256)));
		//this.addFeature(Decoration.UNDERGROUND_ORES, createCompositeFeature(Feature.MINABLE, new MinableConfig(KathairisMinableConfig.IS_ROCK, Blocks.GRAVEL.getDefaultState(), 33), COUNT_RANGE, new CountRangeConfig(8, 0, 0, 256)));
		//this.addFeature(Decoration.UNDERGROUND_ORES, createCompositeFeature(Feature.MINABLE, new MinableConfig(KathairisMinableConfig.IS_ROCK, Blocks.GRANITE.getDefaultState(), 33), COUNT_RANGE, new CountRangeConfig(10, 0, 0, 80)));
		//this.addFeature(Decoration.UNDERGROUND_ORES, createCompositeFeature(Feature.MINABLE, new MinableConfig(KathairisMinableConfig.IS_ROCK, Blocks.DIORITE.getDefaultState(), 33), COUNT_RANGE, new CountRangeConfig(10, 0, 0, 80)));
		//this.addFeature(Decoration.UNDERGROUND_ORES, createCompositeFeature(Feature.MINABLE, new MinableConfig(KathairisMinableConfig.IS_ROCK, Blocks.ANDESITE.getDefaultState(), 33), COUNT_RANGE, new CountRangeConfig(10, 0, 0, 80)));
		this.addFeature(Decoration.UNDERGROUND_ORES, createCompositeFeature(Feature.MINABLE, new MinableConfig(KathairisMinableConfig.IS_ROCK, REVENUM_ORE.getDefaultState(), 34), COUNT_RANGE, new CountRangeConfig(20, 0, 0, 128)));
		this.addFeature(Decoration.UNDERGROUND_ORES, createCompositeFeature(Feature.MINABLE, new MinableConfig(KathairisMinableConfig.IS_ROCK, TITANIUM_ORE.getDefaultState(), 14), COUNT_RANGE, new CountRangeConfig(2, 0, 0, 32)));
		//this.addFeature(Decoration.UNDERGROUND_ORES, createCompositeFeature(Feature.MINABLE, new MinableConfig(KathairisMinableConfig.IS_ROCK, Blocks.IRON_ORE.getDefaultState(), 18), COUNT_RANGE, new CountRangeConfig(20, 0, 0, 64)));
		//this.addFeature(Decoration.UNDERGROUND_ORES, createCompositeFeature(Feature.MINABLE, new MinableConfig(KathairisMinableConfig.IS_ROCK, Blocks.GOLD_ORE.getDefaultState(), 18), COUNT_RANGE, new CountRangeConfig(2, 0, 0, 32)));
		//this.addFeature(Decoration.UNDERGROUND_ORES, createCompositeFeature(Feature.MINABLE, new MinableConfig(KathairisMinableConfig.IS_ROCK, Blocks.REDSTONE_ORE.getDefaultState(), 18), COUNT_RANGE, new CountRangeConfig(8, 0, 0, 16)));
		//this.addFeature(Decoration.UNDERGROUND_ORES, createCompositeFeature(Feature.MINABLE, new MinableConfig(KathairisMinableConfig.IS_ROCK, Blocks.DIAMOND_ORE.getDefaultState(), 12), COUNT_RANGE, new CountRangeConfig(1, 0, 0, 16)));
		//this.addFeature(Decoration.UNDERGROUND_ORES, createCompositeFeature(Feature.MINABLE, new MinableConfig(KathairisMinableConfig.IS_ROCK, Blocks.LAPIS_ORE.getDefaultState(), 10), DEPTH_AVERAGE, new DepthAverageConfig(1, 16, 16)));
		//this.addFeature(Decoration.UNDERGROUND_ORES, createCompositeFeature(Feature.SPHERE_REPLACE, new SphereReplaceConfig(Blocks.SAND, 7, 2, Lists.newArrayList(new Block[]{Blocks.DIRT, Blocks.GRASS_BLOCK})), TOP_SOLID, new FrequencyConfig(3)));
		//this.addFeature(Decoration.UNDERGROUND_ORES, createCompositeFeature(Feature.SPHERE_REPLACE, new SphereReplaceConfig(Blocks.CLAY, 8, 1, Lists.newArrayList(new Block[]{Blocks.DIRT, Blocks.CLAY})), TOP_SOLID, new FrequencyConfig(1)));
		//this.addFeature(Decoration.UNDERGROUND_ORES, createCompositeFeature(Feature.SPHERE_REPLACE, new SphereReplaceConfig(Blocks.GRAVEL, 6, 2, Lists.newArrayList(new Block[]{Blocks.DIRT, Blocks.GRASS_BLOCK})), TOP_SOLID, new FrequencyConfig(1)));
		this.addFeature(Decoration.VEGETAL_DECORATION, createCompositeFeature(Feature.LIQUIDS, new LiquidsConfig(Fluids.WATER), HEIGHT_BIASED_RANGE, new CountRangeConfig(50, 8, 8, 256)));
		this.addFeature(Decoration.VEGETAL_DECORATION, createCompositeFeature(Feature.LIQUIDS, new LiquidsConfig(Fluids.LAVA), HEIGHT_VERY_BIASED_RANGE, new CountRangeConfig(20, 8, 16, 256)));
		this.addFeature(Decoration.VEGETAL_DECORATION, createCompositeFeature(Feature.TALL_GRASS, new TallGrassConfig(KATHAIRIS_TALL_GRASS.getDefaultState()), TWICE_SURFACE, new FrequencyConfig(4)));
		this.addFeature(Decoration.VEGETAL_DECORATION, createCompositeFeature(Feature.TALL_GRASS, new TallGrassConfig(KATHAIRIS_MINI_GRASS.getDefaultState()), TWICE_SURFACE, new FrequencyConfig(4)));
		this.addFeature(Decoration.VEGETAL_DECORATION, createCompositeFeature(Feature.BUSH, new BushConfig(GOOSEBERRY_BUSH), TWICE_SURFACE_WITH_CHANCE, new ChanceConfig(2)));
		this.addFeature(Decoration.VEGETAL_DECORATION, createCompositeFeature(Feature.BUSH, new BushConfig(KATHAIRIS_FUNGI), TWICE_SURFACE_WITH_CHANCE, new ChanceConfig(4)));
		this.addFeature(Decoration.VEGETAL_DECORATION, createCompositeFeature(Feature.BUSH, new BushConfig(KATHAIRIS_NIGHT_FLOWER), TWICE_SURFACE_WITH_CHANCE, new ChanceConfig(1)));
		this.addFeature(Decoration.VEGETAL_DECORATION, createCompositeFeature(Feature.BUSH, new BushConfig(VILYRIA), TWICE_SURFACE_WITH_CHANCE, new ChanceConfig(1)));
		this.addFeature(Decoration.LOCAL_MODIFICATIONS, createCompositeFeature(KathairisFeatureList.KATHAIRIS_CLOUD, IFeatureConfig.NO_FEATURE_CONFIG, COUNT_RANGE, new CountRangeConfig(3, 0, 0, 128)));
		this.addFeature(Decoration.LOCAL_MODIFICATIONS, createCompositeFeature(KathairisFeatureList.KATHAIRIS_CLOUD_MINI_ISLAND, IFeatureConfig.NO_FEATURE_CONFIG, COUNT_RANGE, new CountRangeConfig(1, 0, 0, 128)));

	}

}
