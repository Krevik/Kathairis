package io.github.krevik.kathairis.init;

import io.github.krevik.kathairis.block.*;
import io.github.krevik.kathairis.block.TreesForSaplings.ElderwillowTree;
import io.github.krevik.kathairis.block.TreesForSaplings.MysticTree;
import io.github.krevik.kathairis.block.TreesForSaplings.ShinyTree;
import io.github.krevik.kathairis.block.TreesForSaplings.SoulTree;
import io.github.krevik.kathairis.util.RegistryHelper;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

import static io.github.krevik.kathairis.util.ModReference.MOD_ID;
import static io.github.krevik.kathairis.util.ModUtil._null;
import static net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus.MOD;

/**
 * @author Cadiboo
 */
@ObjectHolder(MOD_ID)
@Mod.EventBusSubscriber(modid = MOD_ID, bus = MOD)
public final class ModBlocks {

	public static final Block KATHAIRIS_STONE = _null();
	public static final BlockKathairisPortal KATHAIRIS_PORTAL = _null();
	public static final Block KATHAIRIS_DIRT = _null();
	public static final Block KATHAIRIS_GRASS = _null();
	public static final Block TITANIUM_BLOCK = _null();
	public static final Block MYSTIC_LOG = _null();
	public static final Block MYSTIC_LEAVES = _null();
	public static final Block SHINY_LOG = _null();
	public static final Block SHINY_LEAVES = _null();
	public static final Block SOUL_LOG = _null();
	public static final Block SOUL_LEAVES = _null();
	public static final Block REVENUM_ORE = _null();
	public static final Block TITANIUM_ORE = _null();
	public static final Block KATHAIRIS_SAND = _null();
	public static final Block SOFT_SAND = _null();
	public static final Block BLUE_CLOUD = _null();
	public static final Block YELLOW_CLOUD = _null();
	public static final Block WEATHERED_ROCK = _null();
	public static final Block MYSTIC_PLANKS = _null();
	public static final Block SHINY_PLANKS = _null();
	public static final Block SOUL_PLANKS = _null();
	public static final Block KATHAIRIS_COBBLESTONE = _null();
	public static final Block KATHAIRIS_STONE_TILES = _null();
	public static final Block SHINY_ROCK = _null();
	public static final Block KATHAIRIS_STONE_BRICKS = _null();
	public static final Block MUD_BLOCK = _null();
	public static final Block MUD_BRICKS = _null();
	public static final Block MAGNETHIUM = _null();
	public static final Block IRON_GOLD_BLOCK = _null();
	public static final Block HARDENED_WEATHERED_ROCK = _null();
	public static final Block HARDENED_WEATHERED_ROCK_BRICKS = _null();
	public static final Block HARDENED_WEATHERED_ROCK_TILES = _null();
	public static final Block BLUE_CLOUD_REFINED = _null();
	public static final Block YELLOW_CLOUD_REFINED = _null();
	public static final Block BLUE_CLOUD_CONDENSED = _null();
	public static final Block YELLOW_CLOUD_CONDENSED = _null();
	public static final Block MYSTIC_LOG_STRIPPED = _null();
	public static final Block SHINY_LOG_STRIPPED = _null();
	public static final Block SOUL_LOG_STRIPPED = _null();
	public static final Block MYSTIC_WOOD_FENCE = _null();
	public static final Block MYSTIC_WOOD_FENCE_GATE = _null();
	public static final Block SHINY_WOOD_FENCE = _null();
	public static final Block SHINY_WOOD_FENCE_GATE = _null();
	public static final Block SOUL_WOOD_FENCE = _null();
	public static final Block SOUL_WOOD_FENCE_GATE = _null();
	public static final Block KATHAIRIS_STONE_WALL = _null();
	public static final Block KATHAIRIS_COBBLESTONE_WALL = _null();
	public static final Block WEATHERED_ROCK_WALL = _null();
	public static final Block HARDENED_WEATHERED_ROCK_WALL = _null();
	public static final Block HARDENED_WEATHERED_ROCK_BRICKS_WALL = _null();
	public static final Block MUD_BRICKS_WALL = _null();
	public static final Block KATHAIRIS_STONE_BRICKS_WALL = _null();
	public static final Block MYSTIC_WOOD_STAIRS = _null();
	public static final Block SHINY_WOOD_STAIRS = _null();
	public static final Block SOUL_WOOD_STAIRS = _null();
	public static final Block KATHAIRIS_STONE_STAIRS = _null();
	public static final Block KATHAIRIS_COBBLESTONE_STAIRS = _null();
	public static final Block WEATHERED_ROCK_STAIRS = _null();
	public static final Block HARDENED_WEATHERED_ROCK_STAIRS = _null();
	public static final Block HARDENED_WEATHERED_ROCK_BRICKS_STAIRS = _null();
	public static final Block MUD_BRICKS_STAIRS = _null();
	public static final Block KATHAIRIS_STONE_BRICKS_STAIRS = _null();
	public static final Block KATHAIRIS_STONE_SLAB = _null();
	public static final Block KATHAIRIS_COBBLESTONE_SLAB = _null();
	public static final Block KATHAIRIS_STONE_BRICKS_SLAB = _null();
	public static final Block MUD_BRICKS_SLAB = _null();
	public static final Block MYSTIC_WOOD_SLAB = _null();
	public static final Block SHINY_WOOD_SLAB = _null();
	public static final Block SOUL_WOOD_SLAB = _null();
	public static final Block WEATHERED_ROCK_SLAB = _null();
	public static final Block HARDENED_WEATHERED_ROCK_SLAB = _null();
	public static final Block HARDENED_WEATHERED_ROCK_BRICKS_SLAB = _null();
	public static final Block BLUE_CRYSTAL = _null();
	public static final Block VIOLET_CRYSTAL = _null();
	public static final Block YELLOW_CRYSTAL = _null();
	public static final Block BISON_STARS = _null();
	public static final Block EYE_PLANT = _null();
	public static final Block KATHAIRIS_MINI_GRASS = _null();
	public static final Block KATHAIRIS_TALL_GRASS = _null();
	public static final Block KATHAIRIS_NIGHT_FLOWER = _null();
	public static final Block KATHAIRIS_MULTI_GRASS = _null();
	public static final Block BLUE_CLOUD_BRICKS = _null();
	public static final Block YELLOW_CLOUD_BLOCK = _null();
	public static final Block FRUP_PLANT = _null();
	public static final Block MYSTIC_WOOD_DOORS = _null();
	public static final Block SHINY_WOOD_DOORS = _null();
	public static final Block SOUL_WOOD_DOORS = _null();
	public static final Block MYSTIC_WOOD_TRAPDOOR = _null();
	public static final Block SHINY_WOOD_TRAPDOOR = _null();
	public static final Block SOUL_WOOD_TRAPDOOR = _null();
	public static final Block KATHAIRIS_FUNGI = _null();
	public static final Block GOOSEBERRY_BUSH = _null();
	public static final Block KATHAIRIS_SUCCULENT = _null();
	public static final Block STEPPED_SUCCULENT = _null();
	public static final Block VILYRIA = _null();
	public static final Block GLOWVINES = _null();
	public static final Block JADE_VINES = _null();
	public static final Block KATHAIRIS_DEAD_GRASS = _null();
	public static final Block MYSTIC_GEM_BLOCK = _null();
	public static final Block MAGIC_BEANS = _null();
	public static final Block BAURBLE = _null();
	public static final Block BUTTERFLY_FLOWER = _null();
	public static final Block SOLIS_CRYSTALS = _null();
	public static final Block BLUE_CLOUD_FLOWER = _null();
	public static final Block YELLOW_CLOUD_FLOWER = _null();
	public static final Block SNOWDROP_CYPREPEDIUM = _null();
	public static final Block FLUO_FUNGI = _null();
	public static final Block LAYERED_SAND = _null();
	public static final Block KATHAIRIS_SANDSTONE = _null();
	public static final Block FOREST_CANDLE = _null();
	public static final Block ROCKTUS = _null();
	public static final Block ELDERWILLOW_LOG = _null();
	public static final Block ELDERWILLOW_LEAVES = _null();
	public static final Block PURPLE_PALM = _null();
	public static final Block BRINE_PUSTULE = _null();
	public static final Block WILLOW_VINE_MAIN = _null();
	public static final Block WILLOW_VINE_TIP = _null();
	public static final Block MYSTIC_SAPLING = _null();
	public static final Block SOUL_SAPLING = _null();
	public static final Block SHINY_SAPLING = _null();
	public static final Block ELDERWILLOW_SAPLING = _null();
	public static final Block ELDERWILLOW_WOOD_TRAPDOOR = _null();
	public static final Block ELDERWILLOW_WOOD_DOORS = _null();
	public static final Block ELDERWILLOW_WOOD_SLAB = _null();
	public static final Block ELDERWILLOW_WOOD_STAIRS = _null();
	public static final Block ELDERWILLOW_WOOD_FENCE = _null();
	public static final Block ELDERWILLOW_WOOD_FENCE_GATE = _null();
	public static final Block ELDERWILLOW_LOG_STRIPPED = _null();
	public static final Block ELDERWILLOW_PLANKS = _null();
	public static final Block KATHAIRIS_SANDSTONE_STAIRS = _null();
	public static final Block KATHAIRIS_SANDSTONE_WALL = _null();
	public static final Block KATHAIRIS_SANDSTONE_SLAB = _null();
	public static final Block HANGING_ROOTS = _null();

	@SubscribeEvent
	public static void onRegisterBlocks(final RegistryEvent.Register<Block> event) {

		final Block mysticPlanks;
		final Block shinyPlanks;
		final Block soulPlanks;
		final Block elderwillowPlanks;
		final Block kathairisStone;
		final Block kathairisCobblestone;
		final Block weatheredRock;
		final Block hardenedWeatheredRock;
		final Block hardenedWeatheredRockBricks;
		final Block mudBricks;
		final Block kathairisStoneBricks;
		final Block kathairisSandstone;

		event.getRegistry().registerAll(
				//logs and stripped logs
				RegistryHelper.setup(new BlockKathairisLog(), "mystic_log"),
				RegistryHelper.setup(new BlockKathairisLog(), "shiny_log"),
				RegistryHelper.setup(new BlockKathairisLog(), "soul_log"),
				RegistryHelper.setup(new BlockKathairisLog(), "elderwillow_log"),
				RegistryHelper.setup(new BlockKathairisLog(), "mystic_log_stripped"),
				RegistryHelper.setup(new BlockKathairisLog(), "shiny_log_stripped"),
				RegistryHelper.setup(new BlockKathairisLog(), "soul_log_stripped"),
				RegistryHelper.setup(new BlockKathairisLog(), "elderwillow_log_stripped"),
				//leaves
				RegistryHelper.setup(new BlockKathairisLeaves(), "mystic_leaves"),
				RegistryHelper.setup(new BlockKathairisLeaves(), "shiny_leaves"),
				RegistryHelper.setup(new BlockKathairisLeaves(), "soul_leaves"),
				RegistryHelper.setup(new BlockKathairisLeaves(), "elderwillow_leaves"),
				//planks
				RegistryHelper.setup(mysticPlanks = new BlockKathairisPlanks(), "mystic_planks"),
				RegistryHelper.setup(shinyPlanks = new BlockKathairisPlanks(), "shiny_planks"),
				RegistryHelper.setup(soulPlanks = new BlockKathairisPlanks(), "soul_planks"),
				RegistryHelper.setup(elderwillowPlanks = new BlockKathairisPlanks(), "elderwillow_planks"),
				//ores and crystals
				RegistryHelper.setup(new BlockKathairisOre(1, 2, 4, Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(7f)), "titanium_ore"),
				RegistryHelper.setup(new BlockKathairisOre(1, 1, 3, Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(4f)), "revenum_ore"),
				RegistryHelper.setup(new BlockCrystal(), "blue_crystal"),
				RegistryHelper.setup(new BlockCrystal(), "violet_crystal"),
				RegistryHelper.setup(new BlockCrystal(), "yellow_crystal"),
				//building blocks
				RegistryHelper.setup(new BlockBaurble(), "baurble"),
				RegistryHelper.setup(new BlockLayeredSand(), "layered_sand"), //TODO ADD LOOT TABLE
				RegistryHelper.setup(new BlockSolisCrystals(), "solis_crystals"),
				RegistryHelper.setup(kathairisSandstone = new BlockKathairisSandstone(), "kathairis_sandstone"),
				RegistryHelper.setup(new BlockMysticGemBlock(), "mystic_gem_block"),
				RegistryHelper.setup(new BlockCondensedCloud(), "blue_cloud_bricks"),
				RegistryHelper.setup(new BlockCondensedCloud(), "yellow_cloud_block"),
				RegistryHelper.setup(kathairisStone = new BlockKathairisStone(), "kathairis_stone"),
				RegistryHelper.setup(new BlockKathairisDirt(), "kathairis_dirt"),
				RegistryHelper.setup(new BlockKathairisGrass(), "kathairis_grass"),
				RegistryHelper.setup(new BlockTitaniumBlock(), "titanium_block"),
				RegistryHelper.setup(new BlockKathairisSand(), "kathairis_sand"),
				RegistryHelper.setup(new BlockKathairisCloud(() -> ModItems.CLOUD_DUST_BLUE), "blue_cloud"),
				RegistryHelper.setup(new BlockSoftSand(), "soft_sand"),
				RegistryHelper.setup(new BlockKathairisPortal(), "kathairis_portal"),
				RegistryHelper.setup(new BlockKathairisCloud(() -> ModItems.CLOUD_DUST_YELLOW), "yellow_cloud"),
				RegistryHelper.setup(weatheredRock = new BlockWeatheredRock(), "weathered_rock"),
				RegistryHelper.setup(kathairisCobblestone = new BlockKathairisCobblestone(), "kathairis_cobblestone"),
				RegistryHelper.setup(new BlockKathairisStoneTiles(), "kathairis_stone_tiles"),
				RegistryHelper.setup(new BlockShinyRock(), "shiny_rock"),
				RegistryHelper.setup(kathairisStoneBricks = new BlockKathairisStoneBricks(), "kathairis_stone_bricks"),
				RegistryHelper.setup(new BlockMudBlock(), "mud_block"),
				RegistryHelper.setup(mudBricks = new BlockMudBricks(), "mud_bricks"),
				RegistryHelper.setup(new BlockMagnethium(), "magnethium"),
				RegistryHelper.setup(new BlockIronGoldBlock(), "iron_gold_block"),
				RegistryHelper.setup(hardenedWeatheredRock = new BlockHardenedWeatheredRock(), "hardened_weathered_rock"),
				RegistryHelper.setup(hardenedWeatheredRockBricks = new BlockHardenedWeatheredRockBricks(), "hardened_weathered_rock_bricks"),
				RegistryHelper.setup(new BlockHardenedWeatheredRockTiles(), "hardened_weathered_rock_tiles"),
				RegistryHelper.setup(new BlockRefinedCloud(), "blue_cloud_refined"),
				RegistryHelper.setup(new BlockRefinedCloud(), "yellow_cloud_refined"),
				RegistryHelper.setup(new BlockCondensedCloud(), "blue_cloud_condensed"),
				RegistryHelper.setup(new BlockCondensedCloud(), "yellow_cloud_condensed"),
				//fences and fence_gates
				RegistryHelper.setup(new BlockKathairisFence(Material.WOOD, 3f, SoundType.WOOD), "mystic_wood_fence"),
				RegistryHelper.setup(new BlockKathairisFenceGate(Material.WOOD, 3f, SoundType.WOOD), "mystic_wood_fence_gate"),
				RegistryHelper.setup(new BlockKathairisFence(Material.WOOD, 3f, SoundType.WOOD), "shiny_wood_fence"),
				RegistryHelper.setup(new BlockKathairisFenceGate(Material.WOOD, 3f, SoundType.WOOD), "shiny_wood_fence_gate"),
				RegistryHelper.setup(new BlockKathairisFence(Material.WOOD, 3f, SoundType.WOOD), "soul_wood_fence"),
				RegistryHelper.setup(new BlockKathairisFenceGate(Material.WOOD, 3f, SoundType.WOOD), "soul_wood_fence_gate"),
				RegistryHelper.setup(new BlockKathairisFence(Material.WOOD, 3f, SoundType.WOOD), "elderwillow_wood_fence"),
				RegistryHelper.setup(new BlockKathairisFenceGate(Material.WOOD, 3f, SoundType.WOOD), "elderwillow_wood_fence_gate"),
				//walls
				RegistryHelper.setup(new BlockKathairisWall(Material.ROCK, 2.5f, SoundType.STONE), "kathairis_stone_wall"),
				RegistryHelper.setup(new BlockKathairisWall(Material.ROCK, 2.5f, SoundType.STONE), "kathairis_cobblestone_wall"),
				RegistryHelper.setup(new BlockKathairisWall(Material.ROCK, 1.5f, SoundType.STONE), "weathered_rock_wall"),
				RegistryHelper.setup(new BlockKathairisWall(Material.ROCK, 2f, SoundType.STONE), "hardened_weathered_rock_wall"),
				RegistryHelper.setup(new BlockKathairisWall(Material.ROCK, 2f, SoundType.STONE), "hardened_weathered_rock_bricks_wall"),
				RegistryHelper.setup(new BlockKathairisWall(Material.ROCK, 2f, SoundType.STONE), "mud_bricks_wall"),
				RegistryHelper.setup(new BlockKathairisWall(Material.ROCK, 2.5f, SoundType.STONE), "kathairis_stone_bricks_wall"),
				RegistryHelper.setup(new BlockKathairisWall(Material.ROCK, 2.5f, SoundType.STONE), "kathairis_sandstone_wall"),
				//stairs
				RegistryHelper.setup(new BlockKathairisStairs(mysticPlanks.getDefaultState(), Material.WOOD, 3f, SoundType.WOOD), "mystic_wood_stairs"),
				RegistryHelper.setup(new BlockKathairisStairs(shinyPlanks.getDefaultState(), Material.WOOD, 3f, SoundType.WOOD), "shiny_wood_stairs"),
				RegistryHelper.setup(new BlockKathairisStairs(soulPlanks.getDefaultState(), Material.WOOD, 3f, SoundType.WOOD), "soul_wood_stairs"),
				RegistryHelper.setup(new BlockKathairisStairs(kathairisStone.getDefaultState(), Material.ROCK, 2.5f, SoundType.STONE), "kathairis_stone_stairs"),
				RegistryHelper.setup(new BlockKathairisStairs(kathairisCobblestone.getDefaultState(), Material.ROCK, 2.5f, SoundType.STONE), "kathairis_cobblestone_stairs"),
				RegistryHelper.setup(new BlockKathairisStairs(weatheredRock.getDefaultState(), Material.ROCK, 2.5f, SoundType.STONE), "weathered_rock_stairs"),
				RegistryHelper.setup(new BlockKathairisStairs(hardenedWeatheredRock.getDefaultState(), Material.ROCK, 1.5f, SoundType.STONE), "hardened_weathered_rock_stairs"),
				RegistryHelper.setup(new BlockKathairisStairs(hardenedWeatheredRockBricks.getDefaultState(), Material.ROCK, 2f, SoundType.STONE), "hardened_weathered_rock_bricks_stairs"),
				RegistryHelper.setup(new BlockKathairisStairs(mudBricks.getDefaultState(), Material.ROCK, 2f, SoundType.STONE), "mud_bricks_stairs"),
				RegistryHelper.setup(new BlockKathairisStairs(kathairisStoneBricks.getDefaultState(), Material.ROCK, 2f, SoundType.STONE), "kathairis_stone_bricks_stairs"),
				RegistryHelper.setup(new BlockKathairisStairs(elderwillowPlanks.getDefaultState(), Material.WOOD, 2f, SoundType.WOOD), "elderwillow_wood_stairs"),
				RegistryHelper.setup(new BlockKathairisStairs(kathairisSandstone.getDefaultState(), Material.ROCK, 2f, SoundType.STONE), "kathairis_sandstone_stairs"),
				//slabs
				RegistryHelper.setup(new BlockKathairisSlab(Material.ROCK, 2.5f, SoundType.STONE), "kathairis_stone_slab"),
				RegistryHelper.setup(new BlockKathairisSlab(Material.ROCK, 2.5f, SoundType.STONE), "kathairis_cobblestone_slab"),
				RegistryHelper.setup(new BlockKathairisSlab(Material.ROCK, 2.5f, SoundType.STONE), "kathairis_stone_bricks_slab"),
				RegistryHelper.setup(new BlockKathairisSlab(Material.ROCK, 2f, SoundType.STONE), "mud_bricks_slab"),
				RegistryHelper.setup(new BlockKathairisSlab(Material.WOOD, 3f, SoundType.WOOD), "mystic_wood_slab"),
				RegistryHelper.setup(new BlockKathairisSlab(Material.WOOD, 3f, SoundType.WOOD), "shiny_wood_slab"),
				RegistryHelper.setup(new BlockKathairisSlab(Material.WOOD, 3f, SoundType.WOOD), "soul_wood_slab"),
				RegistryHelper.setup(new BlockKathairisSlab(Material.ROCK, 1.5f, SoundType.STONE), "weathered_rock_slab"),
				RegistryHelper.setup(new BlockKathairisSlab(Material.ROCK, 2f, SoundType.STONE), "hardened_weathered_rock_slab"),
				RegistryHelper.setup(new BlockKathairisSlab(Material.ROCK, 2f, SoundType.STONE), "hardened_weathered_rock_bricks_slab"),
				RegistryHelper.setup(new BlockKathairisSlab(Material.WOOD, 3f, SoundType.WOOD), "elderwillow_wood_slab"),
				RegistryHelper.setup(new BlockKathairisSlab(Material.ROCK, 2f, SoundType.STONE), "kathairis_sandstone_slab"),
				//plants
				RegistryHelper.setup(new BlockBisonStars(), "bison_stars"),
				RegistryHelper.setup(new BlockKathairisPlant(), "eye_plant"),
				RegistryHelper.setup(new BlockKathairisMiniGrass(), "kathairis_mini_grass"),
				RegistryHelper.setup(new BlockKathairisTallGrass(), "kathairis_tall_grass"),
				RegistryHelper.setup(new BlockKathairisNightFlower(), "kathairis_night_flower"),
				RegistryHelper.setup(new BlockKathairisMultiGrass(), "kathairis_multi_grass"),
				RegistryHelper.setup(new BlockFrupPlant(), "frup_plant"),
				RegistryHelper.setup(new BlockKathairisFungi(), "kathairis_fungi"),
				RegistryHelper.setup(new BlockGooseberry(Material.LEAVES, 1f, 1f, SoundType.PLANT), "gooseberry_bush"),
				RegistryHelper.setup(new BlockKathairisSucculent(), "kathairis_succulent"),
				RegistryHelper.setup(new BlockSteppedSucculent(), "stepped_succulent"),
				RegistryHelper.setup(new BlockKathairisPlant(), "vilyria"),
				RegistryHelper.setup(new BlockGlowVines(), "glowvines"),
				RegistryHelper.setup(new BlockJadeVines(), "jade_vines"),
				RegistryHelper.setup(new BlockKathairisDeadGrass(), "kathairis_dead_grass"),
				RegistryHelper.setup(new BlockMagicBeans(), "magic_beans"),
				RegistryHelper.setup(new BlockButterflyFlower(), "butterfly_flower"),
				RegistryHelper.setup(new BlockCloudFlower(), "blue_cloud_flower"),
				RegistryHelper.setup(new BlockCloudFlower(), "yellow_cloud_flower"),
				RegistryHelper.setup(new BlockSnowdropCyprepedium(), "snowdrop_cyprepedium"),
				RegistryHelper.setup(new BlockFluoFungi(), "fluo_fungi"),
				RegistryHelper.setup(new BlockForestCandle(), "forest_candle"),
				RegistryHelper.setup(new BlockKathairisRocktus(), "rocktus"),
				RegistryHelper.setup(new BlockPurplePalm(),"purple_palm"),
				RegistryHelper.setup(new BlockBrinePustule(), "brine_pustule"),
				RegistryHelper.setup(new BlockWillowVineMain(), "willow_vine_main"),
				RegistryHelper.setup(new BlockWillowVineTip(), "willow_vine_tip"),
				RegistryHelper.setup(new BlockKatharianSapling(new MysticTree()), "mystic_sapling"),
				RegistryHelper.setup(new BlockKatharianSapling(new SoulTree()), "soul_sapling"),
				RegistryHelper.setup(new BlockKatharianSapling(new ShinyTree()), "shiny_sapling"),
				RegistryHelper.setup(new BlockKatharianSapling(new ElderwillowTree()), "elderwillow_sapling"),
				RegistryHelper.setup(new BlockHangingRoots(), "hanging_roots"),
				//doors and trapdoors
				RegistryHelper.setup(new BlockKathairisDoors(Material.WOOD, SoundType.WOOD, 3f), "mystic_wood_doors"),
				RegistryHelper.setup(new BlockKathairisDoors(Material.WOOD, SoundType.WOOD, 3f), "shiny_wood_doors"),
				RegistryHelper.setup(new BlockKathairisDoors(Material.WOOD, SoundType.WOOD, 3f), "soul_wood_doors"),
				RegistryHelper.setup(new BlockKathairisDoors(Material.WOOD, SoundType.WOOD, 3f), "elderwillow_wood_doors"),
				RegistryHelper.setup(new BlockKathairisTrapdoor(Material.WOOD, SoundType.WOOD, 3f), "mystic_wood_trapdoor"),
				RegistryHelper.setup(new BlockKathairisTrapdoor(Material.WOOD, SoundType.WOOD, 3f), "shiny_wood_trapdoor"),
				RegistryHelper.setup(new BlockKathairisTrapdoor(Material.WOOD, SoundType.WOOD, 3f), "soul_wood_trapdoor"),
				RegistryHelper.setup(new BlockKathairisTrapdoor(Material.WOOD, SoundType.WOOD, 3f), "elderwillow_wood_trapdoor")
		);

	}
}
