package mod.krevik.kathairis.world.dimension.biome.biomes;

import mod.krevik.kathairis.world.dimension.feature.KatharianFeatureList;
import mod.krevik.kathairis.world.dimension.feature.KatharianMinableConfig;
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
import net.minecraft.world.gen.feature.RandomDefaultFeatureListConfig;
import net.minecraft.world.gen.feature.TallGrassConfig;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.placement.ChanceConfig;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.FrequencyConfig;
import net.minecraft.world.gen.placement.LakeChanceConfig;
import net.minecraft.world.gen.surfacebuilders.CompositeSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

import static mod.krevik.kathairis.init.ModBlocks.FOREST_CANDLE;
import static mod.krevik.kathairis.init.ModBlocks.GOOSEBERRY_BUSH;
import static mod.krevik.kathairis.init.ModBlocks.KATHARIAN_DIRT;
import static mod.krevik.kathairis.init.ModBlocks.KATHARIAN_FUNGI;
import static mod.krevik.kathairis.init.ModBlocks.KATHARIAN_GRASS;
import static mod.krevik.kathairis.init.ModBlocks.KATHARIAN_MINI_GRASS;
import static mod.krevik.kathairis.init.ModBlocks.KATHARIAN_NIGHT_FLOWER;
import static mod.krevik.kathairis.init.ModBlocks.KATHARIAN_TALL_GRASS;
import static mod.krevik.kathairis.init.ModBlocks.REVENUM_ORE;
import static mod.krevik.kathairis.init.ModBlocks.TITANIUM_ORE;
import static mod.krevik.kathairis.init.ModBlocks.VILYRIA;

public final class BiomeKatharianForest extends BiomeKatharianBiomeBase {

	public static final SurfaceBuilderConfig KATHARIAN_GRASS_DIRT_GRAVEL_SURFACE = new SurfaceBuilderConfig(KATHARIAN_GRASS.getDefaultState(), KATHARIAN_DIRT.getDefaultState(), GRAVEL);

	public BiomeKatharianForest() {
		super((new BiomeBuilder()).surfaceBuilder(new CompositeSurfaceBuilder<>(DEFAULT_SURFACE_BUILDER, KATHARIAN_GRASS_DIRT_GRAVEL_SURFACE)).precipitation(RainType.RAIN).category(Category.FOREST).depth(0.1F).scale(0.1F).temperature(0.7F).downfall(0.8F).waterColor(4159204).waterFogColor(329011).parent((String) null));
		this.addCarver(Carving.AIR, createWorldCarverWrapper(KatharianFeatureList.KATHARIAN_CAVE_WORLD_CARVER, new ProbabilityConfig(0.14285715F)));
		this.addFeature(Decoration.VEGETAL_DECORATION, createCompositeFeature(Feature.LIQUIDS, new LiquidsConfig(Fluids.WATER), HEIGHT_BIASED_RANGE, new CountRangeConfig(50, 8, 8, 256)));
		this.addFeature(Decoration.VEGETAL_DECORATION, createCompositeFeature(Feature.LIQUIDS, new LiquidsConfig(Fluids.LAVA), HEIGHT_VERY_BIASED_RANGE, new CountRangeConfig(20, 8, 16, 256)));
		this.addFeature(Decoration.LOCAL_MODIFICATIONS, createCompositeFeature(Feature.LAKES, new LakesConfig(Blocks.WATER), LAKE_WATER, new LakeChanceConfig(4)));
		this.addFeature(Decoration.LOCAL_MODIFICATIONS, createCompositeFeature(Feature.LAKES, new LakesConfig(Blocks.LAVA), LAVA_LAKE, new LakeChanceConfig(80)));
		//this.addFeature(Decoration.UNDERGROUND_ORES, createCompositeFeature(Feature.MINABLE, new MinableConfig(KatharianMinableConfig.IS_ROCK, Blocks.DIRT.getDefaultState(), 33), COUNT_RANGE, new CountRangeConfig(10, 0, 0, 256)));
		//this.addFeature(Decoration.UNDERGROUND_ORES, createCompositeFeature(Feature.MINABLE, new MinableConfig(KatharianMinableConfig.IS_ROCK, Blocks.GRAVEL.getDefaultState(), 33), COUNT_RANGE, new CountRangeConfig(8, 0, 0, 256)));
		//this.addFeature(Decoration.UNDERGROUND_ORES, createCompositeFeature(Feature.MINABLE, new MinableConfig(KatharianMinableConfig.IS_ROCK, Blocks.GRANITE.getDefaultState(), 33), COUNT_RANGE, new CountRangeConfig(10, 0, 0, 80)));
		//this.addFeature(Decoration.UNDERGROUND_ORES, createCompositeFeature(Feature.MINABLE, new MinableConfig(KatharianMinableConfig.IS_ROCK, Blocks.DIORITE.getDefaultState(), 33), COUNT_RANGE, new CountRangeConfig(10, 0, 0, 80)));
		//this.addFeature(Decoration.UNDERGROUND_ORES, createCompositeFeature(Feature.MINABLE, new MinableConfig(KatharianMinableConfig.IS_ROCK, Blocks.ANDESITE.getDefaultState(), 33), COUNT_RANGE, new CountRangeConfig(10, 0, 0, 80)));
		this.addFeature(Decoration.UNDERGROUND_ORES, createCompositeFeature(Feature.MINABLE, new MinableConfig(KatharianMinableConfig.IS_ROCK, REVENUM_ORE.getDefaultState(), 17), COUNT_RANGE, new CountRangeConfig(20, 0, 0, 128)));
		this.addFeature(Decoration.UNDERGROUND_ORES, createCompositeFeature(Feature.MINABLE, new MinableConfig(KatharianMinableConfig.IS_ROCK, TITANIUM_ORE.getDefaultState(), 7), COUNT_RANGE, new CountRangeConfig(2, 0, 0, 32)));
		//this.addFeature(Decoration.UNDERGROUND_ORES, createCompositeFeature(Feature.MINABLE, new MinableConfig(KatharianMinableConfig.IS_ROCK, Blocks.IRON_ORE.getDefaultState(), 9), COUNT_RANGE, new CountRangeConfig(20, 0, 0, 64)));
		//this.addFeature(Decoration.UNDERGROUND_ORES, createCompositeFeature(Feature.MINABLE, new MinableConfig(KatharianMinableConfig.IS_ROCK, Blocks.GOLD_ORE.getDefaultState(), 9), COUNT_RANGE, new CountRangeConfig(2, 0, 0, 32)));
		//this.addFeature(Decoration.UNDERGROUND_ORES, createCompositeFeature(Feature.MINABLE, new MinableConfig(KatharianMinableConfig.IS_ROCK, Blocks.REDSTONE_ORE.getDefaultState(), 8), COUNT_RANGE, new CountRangeConfig(8, 0, 0, 16)));
		//this.addFeature(Decoration.UNDERGROUND_ORES, createCompositeFeature(Feature.MINABLE, new MinableConfig(KatharianMinableConfig.IS_ROCK, Blocks.DIAMOND_ORE.getDefaultState(), 8), COUNT_RANGE, new CountRangeConfig(1, 0, 0, 16)));
		//this.addFeature(Decoration.UNDERGROUND_ORES, createCompositeFeature(Feature.MINABLE, new MinableConfig(KatharianMinableConfig.IS_ROCK, Blocks.LAPIS_ORE.getDefaultState(), 7), DEPTH_AVERAGE, new DepthAverageConfig(1, 16, 16)));
		//this.addFeature(Decoration.UNDERGROUND_ORES, createCompositeFeature(Feature.SPHERE_REPLACE, new SphereReplaceConfig(Blocks.SAND, 7, 2, Lists.newArrayList(new Block[]{Blocks.DIRT, Blocks.GRASS_BLOCK})), TOP_SOLID, new FrequencyConfig(3)));
		//this.addFeature(Decoration.UNDERGROUND_ORES, createCompositeFeature(Feature.SPHERE_REPLACE, new SphereReplaceConfig(Blocks.CLAY, 4, 1, Lists.newArrayList(new Block[]{Blocks.DIRT, Blocks.CLAY})), TOP_SOLID, new FrequencyConfig(1)));
		//this.addFeature(Decoration.UNDERGROUND_ORES, createCompositeFeature(Feature.SPHERE_REPLACE, new SphereReplaceConfig(Blocks.GRAVEL, 6, 2, Lists.newArrayList(new Block[]{Blocks.DIRT, Blocks.GRASS_BLOCK})), TOP_SOLID, new FrequencyConfig(1)));
		this.addFeature(Decoration.VEGETAL_DECORATION, createCompositeFeature(Feature.RANDOM_FEATURE_LIST, new RandomDefaultFeatureListConfig(new Feature[]{KatharianFeatureList.KATHARIAN_TREE_2, KatharianFeatureList.KATHARIAN_TREE_1}, new IFeatureConfig[]{IFeatureConfig.NO_FEATURE_CONFIG, IFeatureConfig.NO_FEATURE_CONFIG}, new float[]{0.2F, 0.1F}, KatharianFeatureList.KATHARIAN_TREE_2, IFeatureConfig.NO_FEATURE_CONFIG), AT_SURFACE_WITH_EXTRA, new AtSurfaceWithExtraConfig(2, 0.1F, 1)));
		this.addFeature(Decoration.VEGETAL_DECORATION, createCompositeFeature(Feature.RANDOM_FEATURE_LIST, new RandomDefaultFeatureListConfig(new Feature[]{KatharianFeatureList.KATHARIAN_TREE_3, KatharianFeatureList.KATHARIAN_HUGE_TREE_1}, new IFeatureConfig[]{IFeatureConfig.NO_FEATURE_CONFIG, IFeatureConfig.NO_FEATURE_CONFIG}, new float[]{0.2F, 0.1F}, KatharianFeatureList.KATHARIAN_TREE_3, IFeatureConfig.NO_FEATURE_CONFIG), AT_SURFACE_WITH_EXTRA, new AtSurfaceWithExtraConfig(1, 0.1F, 1)));
		this.addFeature(Decoration.VEGETAL_DECORATION, createCompositeFeature(Feature.TALL_GRASS, new TallGrassConfig(KATHARIAN_TALL_GRASS.getDefaultState()), TWICE_SURFACE, new FrequencyConfig(3)));
		this.addFeature(Decoration.VEGETAL_DECORATION, createCompositeFeature(Feature.TALL_GRASS, new TallGrassConfig(KATHARIAN_MINI_GRASS.getDefaultState()), TWICE_SURFACE, new FrequencyConfig(3)));
		this.addFeature(Decoration.VEGETAL_DECORATION, createCompositeFeature(Feature.BUSH, new BushConfig(GOOSEBERRY_BUSH), TWICE_SURFACE_WITH_CHANCE, new ChanceConfig(2)));
		this.addFeature(Decoration.VEGETAL_DECORATION, createCompositeFeature(Feature.BUSH, new BushConfig(KATHARIAN_FUNGI), TWICE_SURFACE_WITH_CHANCE, new ChanceConfig(2)));
		this.addFeature(Decoration.VEGETAL_DECORATION, createCompositeFeature(Feature.BUSH, new BushConfig(KATHARIAN_NIGHT_FLOWER), TWICE_SURFACE_WITH_CHANCE, new ChanceConfig(2)));
		this.addFeature(Decoration.VEGETAL_DECORATION, createCompositeFeature(Feature.BUSH, new BushConfig(VILYRIA), TWICE_SURFACE_WITH_CHANCE, new ChanceConfig(2)));
		this.addFeature(Decoration.VEGETAL_DECORATION, createCompositeFeature(Feature.BUSH, new BushConfig(FOREST_CANDLE), TWICE_SURFACE_WITH_CHANCE, new ChanceConfig(2)));
		this.addFeature(Decoration.VEGETAL_DECORATION, createCompositeFeature(KatharianFeatureList.STEPPED_SUCCULENT, IFeatureConfig.NO_FEATURE_CONFIG, TWICE_SURFACE_WITH_CHANCE, new ChanceConfig(2)));

		this.addFeature(Decoration.UNDERGROUND_DECORATION, createCompositeFeature(KatharianFeatureList.KATHARIAN_CLOUD, IFeatureConfig.NO_FEATURE_CONFIG, COUNT_RANGE, new CountRangeConfig(1, 0, 0, 128)));
		this.addFeature(Decoration.UNDERGROUND_DECORATION, createCompositeFeature(KatharianFeatureList.KATHARIAN_CLOUD_MINI_ISLAND, IFeatureConfig.NO_FEATURE_CONFIG, COUNT_RANGE, new CountRangeConfig(1, 0, 0, 128)));
		//this.addSpawn(EnumCreatureType.CREATURE, new SpawnListEntry((EntityType<? extends EntityLiving>) KatharianEntityTypes.MYSTIC_BIRD, 12, 1, 2));
		//this.addSpawn(EnumCreatureType.CREATURE, new SpawnListEntry((EntityType<? extends EntityLiving>) KatharianEntityTypes.GECKO, 4, 1, 1));
		//this.addSpawn(EnumCreatureType.CREATURE, new SpawnListEntry((EntityType<? extends EntityLiving>) KatharianEntityTypes.LIVING_FLOWER, 8, 1, 1));
		//this.addSpawn(EnumCreatureType.MONSTER, new SpawnListEntry((EntityType<? extends EntityLiving>) KatharianEntityTypes.HOWLER, 5, 1, 1));
		//this.addSpawn(EnumCreatureType.MONSTER, new SpawnListEntry((EntityType<? extends EntityLiving>) KatharianEntityTypes.FUNGITE, 2, 1, 1));
		//this.addSpawn(EnumCreatureType.CREATURE, new SpawnListEntry((EntityType<? extends EntityLiving>) KatharianEntityTypes.CACTI_SPORE, 4, 1, 1));
	}

    /*@Override
    public int getGrassColor(BlockPos p_180627_1_) {
        return 0x41f4c7;
    }*/

}
