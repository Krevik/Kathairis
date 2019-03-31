package io.github.krevik.kathairis.world.dimension.biome.biomes;

import io.github.krevik.kathairis.world.dimension.feature.KathairisFeatureList;
import io.github.krevik.kathairis.world.dimension.feature.KathairisMinableConfig;
import net.minecraft.init.Blocks;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.BushConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.LakesConfig;
import net.minecraft.world.gen.feature.MinableConfig;
import net.minecraft.world.gen.feature.ProbabilityConfig;
import net.minecraft.world.gen.feature.TallGrassConfig;
import net.minecraft.world.gen.placement.ChanceConfig;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.FrequencyConfig;
import net.minecraft.world.gen.placement.LakeChanceConfig;
import net.minecraft.world.gen.surfacebuilders.CompositeSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

import static io.github.krevik.kathairis.init.ModBlocks.BISON_STARS;
import static io.github.krevik.kathairis.init.ModBlocks.BUTTERFLY_FLOWER;
import static io.github.krevik.kathairis.init.ModBlocks.EYE_PLANT;
import static io.github.krevik.kathairis.init.ModBlocks.FRUP_PLANT;
import static io.github.krevik.kathairis.init.ModBlocks.KATHAIRIS_DIRT;
import static io.github.krevik.kathairis.init.ModBlocks.KATHAIRIS_GRASS;
import static io.github.krevik.kathairis.init.ModBlocks.KATHAIRIS_MINI_GRASS;
import static io.github.krevik.kathairis.init.ModBlocks.KATHAIRIS_MULTI_GRASS;
import static io.github.krevik.kathairis.init.ModBlocks.KATHAIRIS_NIGHT_FLOWER;
import static io.github.krevik.kathairis.init.ModBlocks.KATHAIRIS_TALL_GRASS;
import static io.github.krevik.kathairis.init.ModBlocks.REVENUM_ORE;
import static io.github.krevik.kathairis.init.ModBlocks.TITANIUM_ORE;
import static io.github.krevik.kathairis.init.ModBlocks.VILYRIA;

/**
 * @author Krevik
 */
public class BiomeKathairisPlainFields extends BiomeKathairisBiomeBase {

	public static final SurfaceBuilderConfig KATHAIRIS_GRASS_DIRT_GRAVEL_SURFACE = new SurfaceBuilderConfig(KATHAIRIS_GRASS.getDefaultState(), KATHAIRIS_DIRT.getDefaultState(), GRAVEL);

	public BiomeKathairisPlainFields() {
		super((new BiomeBuilder()).surfaceBuilder(new CompositeSurfaceBuilder<>(DEFAULT_SURFACE_BUILDER, KATHAIRIS_GRASS_DIRT_GRAVEL_SURFACE)).precipitation(RainType.RAIN).category(Category.PLAINS).depth(0.1F).scale(0.0F).temperature(0.7F).downfall(0.8F).waterColor(4159204).waterFogColor(329011).parent(null));
		this.addCarver(GenerationStage.Carving.AIR, createWorldCarverWrapper(KathairisFeatureList.KATHAIRIS_CAVE_WORLD_CARVER, new ProbabilityConfig(0.14285715F)));
		this.addFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS, createCompositeFeature(Feature.LAKES, new LakesConfig(Blocks.WATER), LAKE_WATER, new LakeChanceConfig(4)));
		//this.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, createCompositeFeature(Feature.MINABLE, new MinableConfig(KathairisMinableConfig.IS_ROCK, Blocks.DIRT.getDefaultState(), 33), COUNT_RANGE, new CountRangeConfig(10, 0, 0, 256)));
		//this.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, createCompositeFeature(Feature.MINABLE, new MinableConfig(KathairisMinableConfig.IS_ROCK, Blocks.GRAVEL.getDefaultState(), 33), COUNT_RANGE, new CountRangeConfig(8, 0, 0, 256)));
		//this.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, createCompositeFeature(Feature.MINABLE, new MinableConfig(KathairisMinableConfig.IS_ROCK, Blocks.GRANITE.getDefaultState(), 33), COUNT_RANGE, new CountRangeConfig(10, 0, 0, 80)));
		//this.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, createCompositeFeature(Feature.MINABLE, new MinableConfig(KathairisMinableConfig.IS_ROCK, Blocks.DIORITE.getDefaultState(), 33), COUNT_RANGE, new CountRangeConfig(10, 0, 0, 80)));
		//this.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, createCompositeFeature(Feature.MINABLE, new MinableConfig(KathairisMinableConfig.IS_ROCK, Blocks.ANDESITE.getDefaultState(), 33), COUNT_RANGE, new CountRangeConfig(10, 0, 0, 80)));
		this.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, createCompositeFeature(Feature.MINABLE, new MinableConfig(KathairisMinableConfig.IS_ROCK, REVENUM_ORE.getDefaultState(), 17), COUNT_RANGE, new CountRangeConfig(20, 0, 0, 128)));
		this.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, createCompositeFeature(Feature.MINABLE, new MinableConfig(KathairisMinableConfig.IS_ROCK, TITANIUM_ORE.getDefaultState(), 7), COUNT_RANGE, new CountRangeConfig(2, 0, 0, 32)));
		//this.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, createCompositeFeature(Feature.MINABLE, new MinableConfig(KathairisMinableConfig.IS_ROCK, Blocks.IRON_ORE.getDefaultState(), 9), COUNT_RANGE, new CountRangeConfig(20, 0, 0, 64)));
		//this.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, createCompositeFeature(Feature.MINABLE, new MinableConfig(KathairisMinableConfig.IS_ROCK, Blocks.GOLD_ORE.getDefaultState(), 9), COUNT_RANGE, new CountRangeConfig(2, 0, 0, 32)));
		//this.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, createCompositeFeature(Feature.MINABLE, new MinableConfig(KathairisMinableConfig.IS_ROCK, Blocks.REDSTONE_ORE.getDefaultState(), 8), COUNT_RANGE, new CountRangeConfig(8, 0, 0, 16)));
		//this.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, createCompositeFeature(Feature.MINABLE, new MinableConfig(KathairisMinableConfig.IS_ROCK, Blocks.DIAMOND_ORE.getDefaultState(), 8), COUNT_RANGE, new CountRangeConfig(1, 0, 0, 16)));
		//this.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, createCompositeFeature(Feature.MINABLE, new MinableConfig(KathairisMinableConfig.IS_ROCK, Blocks.LAPIS_ORE.getDefaultState(), 7), DEPTH_AVERAGE, new DepthAverageConfig(1, 16, 16)));
		//this.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, createCompositeFeature(Feature.SPHERE_REPLACE, new SphereReplaceConfig(Blocks.SAND, 7, 2, Lists.newArrayList(new Block[]{Blocks.DIRT, Blocks.GRASS_BLOCK})), TOP_SOLID, new FrequencyConfig(3)));
		//this.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, createCompositeFeature(Feature.SPHERE_REPLACE, new SphereReplaceConfig(Blocks.CLAY, 4, 1, Lists.newArrayList(new Block[]{Blocks.DIRT, Blocks.CLAY})), TOP_SOLID, new FrequencyConfig(1)));
		//this.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, createCompositeFeature(Feature.SPHERE_REPLACE, new SphereReplaceConfig(Blocks.GRAVEL, 6, 2, Lists.newArrayList(new Block[]{Blocks.DIRT, Blocks.GRASS_BLOCK})), TOP_SOLID, new FrequencyConfig(1)));
		this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, createCompositeFeature(Feature.TALL_GRASS, new TallGrassConfig(KATHAIRIS_TALL_GRASS.getDefaultState()), TWICE_SURFACE, new FrequencyConfig(5)));
		this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, createCompositeFeature(Feature.TALL_GRASS, new TallGrassConfig(KATHAIRIS_MINI_GRASS.getDefaultState()), TWICE_SURFACE, new FrequencyConfig(3)));
		this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, createCompositeFeature(Feature.TALL_GRASS, new TallGrassConfig(KATHAIRIS_MULTI_GRASS.getDefaultState()), TWICE_SURFACE, new FrequencyConfig(2)));
		this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, createCompositeFeature(Feature.BUSH, new BushConfig(BUTTERFLY_FLOWER), TWICE_SURFACE_WITH_CHANCE, new ChanceConfig(2)));
		this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, createCompositeFeature(Feature.BUSH, new BushConfig(KATHAIRIS_NIGHT_FLOWER), TWICE_SURFACE_WITH_CHANCE, new ChanceConfig(1)));
		this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, createCompositeFeature(Feature.BUSH, new BushConfig(VILYRIA), TWICE_SURFACE_WITH_CHANCE, new ChanceConfig(1)));
		this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, createCompositeFeature(Feature.BUSH, new BushConfig(BISON_STARS), TWICE_SURFACE_WITH_CHANCE, new ChanceConfig(1)));
		this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, createCompositeFeature(Feature.BUSH, new BushConfig(EYE_PLANT), TWICE_SURFACE_WITH_CHANCE, new ChanceConfig(1)));
		this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, createCompositeFeature(Feature.BUSH, new BushConfig(FRUP_PLANT), TWICE_SURFACE_WITH_CHANCE, new ChanceConfig(2)));
		this.addFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS, createCompositeFeature(KathairisFeatureList.KATHAIRIS_PLAIN_FIELDS, IFeatureConfig.NO_FEATURE_CONFIG, COUNT_RANGE, new CountRangeConfig(4, 0, 0, 128)));
		this.addFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS, createCompositeFeature(KathairisFeatureList.KATHAIRIS_CLOUD, IFeatureConfig.NO_FEATURE_CONFIG, COUNT_RANGE, new CountRangeConfig(1, 0, 0, 128)));
		this.addFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS, createCompositeFeature(KathairisFeatureList.KATHAIRIS_CLOUD_MINI_ISLAND, IFeatureConfig.NO_FEATURE_CONFIG, COUNT_RANGE, new CountRangeConfig(1, 0, 0, 128)));
		//this.addSpawn(EnumCreatureType.CREATURE, new SpawnListEntry((EntityType<? extends EntityLiving>) KathairisEntityTypes.MYSTIC_BIRD, 12, 1, 3));
		//this.addSpawn(EnumCreatureType.CREATURE, new SpawnListEntry((EntityType<? extends EntityLiving>) KathairisEntityTypes.JELLY_FISH, 10, 1, 2));
		//this.addSpawn(EnumCreatureType.CREATURE, new SpawnListEntry((EntityType<? extends EntityLiving>) KathairisEntityTypes.BISON, 8, 2, 4));
		//this.addSpawn(EnumCreatureType.CREATURE, new SpawnListEntry((EntityType<? extends EntityLiving>) KathairisEntityTypes.BASIC_BUTTERFLY1, 10, 1, 1));
		//this.addSpawn(EnumCreatureType.CREATURE, new SpawnListEntry((EntityType<? extends EntityLiving>) KathairisEntityTypes.BASIC_BUTTERFLY2, 10, 1, 1));

	}

}
