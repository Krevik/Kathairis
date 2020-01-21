package io.github.krevik.kathairis;

import io.github.krevik.kathairis.block.*;
import io.github.krevik.kathairis.block.TreesForSaplings.ElderwillowTree;
import io.github.krevik.kathairis.block.TreesForSaplings.MysticTree;
import io.github.krevik.kathairis.block.TreesForSaplings.ShinyTree;
import io.github.krevik.kathairis.block.TreesForSaplings.SoulTree;
import io.github.krevik.kathairis.enchantement.KathairisEnchantments;
import io.github.krevik.kathairis.entity.*;
import io.github.krevik.kathairis.entity.butterfly.*;
import io.github.krevik.kathairis.init.ModEntities;
import io.github.krevik.kathairis.init.ModItems;
import io.github.krevik.kathairis.util.networking.PacketHandler;
import joptsimple.internal.Strings;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.registries.IForgeRegistryEntry;

import javax.annotation.Nonnull;

import static io.github.krevik.kathairis.util.ModReference.MOD_ID;
import static net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus.MOD;

/**
 * @author Cadiboo
 */
@EventBusSubscriber(modid = MOD_ID, bus = MOD)
public final class ModEventSubscriber {

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
				setup(new BlockKathairisLog(), "mystic_log"),
				setup(new BlockKathairisLog(), "shiny_log"),
				setup(new BlockKathairisLog(), "soul_log"),
				setup(new BlockKathairisLog(), "elderwillow_log"),
				setup(new BlockKathairisLog(), "mystic_log_stripped"),
				setup(new BlockKathairisLog(), "shiny_log_stripped"),
				setup(new BlockKathairisLog(), "soul_log_stripped"),
				setup(new BlockKathairisLog(), "elderwillow_log_stripped"),
				//leaves
				setup(new BlockKathairisLeaves(), "mystic_leaves"),
				setup(new BlockKathairisLeaves(), "shiny_leaves"),
				setup(new BlockKathairisLeaves(), "soul_leaves"),
				setup(new BlockKathairisLeaves(), "elderwillow_leaves"),
				//planks
				setup(mysticPlanks = new BlockKathairisPlanks(), "mystic_planks"),
				setup(shinyPlanks = new BlockKathairisPlanks(), "shiny_planks"),
				setup(soulPlanks = new BlockKathairisPlanks(), "soul_planks"),
				setup(elderwillowPlanks = new BlockKathairisPlanks(), "elderwillow_planks"),
				//ores and crystals
				setup(new BlockKathairisOre(1, 2, 4, Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(7f)), "titanium_ore"),
				setup(new BlockKathairisOre(1, 1, 3, Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(4f)), "revenum_ore"),
				setup(new BlockCrystal(), "blue_crystal"),
				setup(new BlockCrystal(), "violet_crystal"),
				setup(new BlockCrystal(), "yellow_crystal"),
				//building blocks
				setup(new BlockBaurble(), "baurble"),
				setup(new BlockLayeredSand(), "layered_sand"), //TODO ADD LOOT TABLE
				setup(new BlockSolisCrystals(), "solis_crystals"),
				setup(kathairisSandstone = new BlockKathairisSandstone(), "kathairis_sandstone"),
				setup(new BlockMysticGemBlock(), "mystic_gem_block"),
				setup(new BlockCondensedCloud(), "blue_cloud_bricks"),
				setup(new BlockCondensedCloud(), "yellow_cloud_block"),
				setup(kathairisStone = new BlockKathairisStone(), "kathairis_stone"),
				setup(new BlockKathairisDirt(), "kathairis_dirt"),
				setup(new BlockKathairisGrass(), "kathairis_grass"),
				setup(new BlockTitaniumBlock(), "titanium_block"),
				setup(new BlockKathairisSand(), "kathairis_sand"),
				setup(new BlockKathairisCloud(() -> ModItems.CLOUD_DUST_BLUE), "blue_cloud"),
				setup(new BlockSoftSand(), "soft_sand"),
				setup(new BlockKathairisPortal(), "kathairis_portal"),
				setup(new BlockKathairisCloud(() -> ModItems.CLOUD_DUST_YELLOW), "yellow_cloud"),
				setup(weatheredRock = new BlockWeatheredRock(), "weathered_rock"),
				setup(kathairisCobblestone = new BlockKathairisCobblestone(), "kathairis_cobblestone"),
				setup(new BlockKathairisStoneTiles(), "kathairis_stone_tiles"),
				setup(new BlockShinyRock(), "shiny_rock"),
				setup(kathairisStoneBricks = new BlockKathairisStoneBricks(), "kathairis_stone_bricks"),
				setup(new BlockMudBlock(), "mud_block"),
				setup(mudBricks = new BlockMudBricks(), "mud_bricks"),
				setup(new BlockMagnethium(), "magnethium"),
				setup(new BlockIronGoldBlock(), "iron_gold_block"),
				setup(hardenedWeatheredRock = new BlockHardenedWeatheredRock(), "hardened_weathered_rock"),
				setup(hardenedWeatheredRockBricks = new BlockHardenedWeatheredRockBricks(), "hardened_weathered_rock_bricks"),
				setup(new BlockHardenedWeatheredRockTiles(), "hardened_weathered_rock_tiles"),
				setup(new BlockRefinedCloud(), "blue_cloud_refined"),
				setup(new BlockRefinedCloud(), "yellow_cloud_refined"),
				setup(new BlockCondensedCloud(), "blue_cloud_condensed"),
				setup(new BlockCondensedCloud(), "yellow_cloud_condensed"),
				//fences and fence_gates
				setup(new BlockKathairisFence(Material.WOOD, 3f, SoundType.WOOD), "mystic_wood_fence"),
				setup(new BlockKathairisFenceGate(Material.WOOD, 3f, SoundType.WOOD), "mystic_wood_fence_gate"),
				setup(new BlockKathairisFence(Material.WOOD, 3f, SoundType.WOOD), "shiny_wood_fence"),
				setup(new BlockKathairisFenceGate(Material.WOOD, 3f, SoundType.WOOD), "shiny_wood_fence_gate"),
				setup(new BlockKathairisFence(Material.WOOD, 3f, SoundType.WOOD), "soul_wood_fence"),
				setup(new BlockKathairisFenceGate(Material.WOOD, 3f, SoundType.WOOD), "soul_wood_fence_gate"),
				setup(new BlockKathairisFence(Material.WOOD, 3f, SoundType.WOOD), "elderwillow_wood_fence"),
				setup(new BlockKathairisFenceGate(Material.WOOD, 3f, SoundType.WOOD), "elderwillow_wood_fence_gate"),
				//walls
				setup(new BlockKathairisWall(Material.ROCK, 2.5f, SoundType.STONE), "kathairis_stone_wall"),
				setup(new BlockKathairisWall(Material.ROCK, 2.5f, SoundType.STONE), "kathairis_cobblestone_wall"),
				setup(new BlockKathairisWall(Material.ROCK, 1.5f, SoundType.STONE), "weathered_rock_wall"),
				setup(new BlockKathairisWall(Material.ROCK, 2f, SoundType.STONE), "hardened_weathered_rock_wall"),
				setup(new BlockKathairisWall(Material.ROCK, 2f, SoundType.STONE), "hardened_weathered_rock_bricks_wall"),
				setup(new BlockKathairisWall(Material.ROCK, 2f, SoundType.STONE), "mud_bricks_wall"),
				setup(new BlockKathairisWall(Material.ROCK, 2.5f, SoundType.STONE), "kathairis_stone_bricks_wall"),
				setup(new BlockKathairisWall(Material.ROCK, 2.5f, SoundType.STONE), "kathairis_sandstone_wall"),
				//stairs
				setup(new BlockKathairisStairs(mysticPlanks.getDefaultState(), Material.WOOD, 3f, SoundType.WOOD), "mystic_wood_stairs"),
				setup(new BlockKathairisStairs(shinyPlanks.getDefaultState(), Material.WOOD, 3f, SoundType.WOOD), "shiny_wood_stairs"),
				setup(new BlockKathairisStairs(soulPlanks.getDefaultState(), Material.WOOD, 3f, SoundType.WOOD), "soul_wood_stairs"),
				setup(new BlockKathairisStairs(kathairisStone.getDefaultState(), Material.ROCK, 2.5f, SoundType.STONE), "kathairis_stone_stairs"),
				setup(new BlockKathairisStairs(kathairisCobblestone.getDefaultState(), Material.ROCK, 2.5f, SoundType.STONE), "kathairis_cobblestone_stairs"),
				setup(new BlockKathairisStairs(weatheredRock.getDefaultState(), Material.ROCK, 2.5f, SoundType.STONE), "weathered_rock_stairs"),
				setup(new BlockKathairisStairs(hardenedWeatheredRock.getDefaultState(), Material.ROCK, 1.5f, SoundType.STONE), "hardened_weathered_rock_stairs"),
				setup(new BlockKathairisStairs(hardenedWeatheredRockBricks.getDefaultState(), Material.ROCK, 2f, SoundType.STONE), "hardened_weathered_rock_bricks_stairs"),
				setup(new BlockKathairisStairs(mudBricks.getDefaultState(), Material.ROCK, 2f, SoundType.STONE), "mud_bricks_stairs"),
				setup(new BlockKathairisStairs(kathairisStoneBricks.getDefaultState(), Material.ROCK, 2f, SoundType.STONE), "kathairis_stone_bricks_stairs"),
				setup(new BlockKathairisStairs(elderwillowPlanks.getDefaultState(), Material.WOOD, 2f, SoundType.WOOD), "elderwillow_wood_stairs"),
				setup(new BlockKathairisStairs(kathairisSandstone.getDefaultState(), Material.ROCK, 2f, SoundType.STONE), "kathairis_sandstone_stairs"),
				//slabs
				setup(new BlockKathairisSlab(Material.ROCK, 2.5f, SoundType.STONE), "kathairis_stone_slab"),
				setup(new BlockKathairisSlab(Material.ROCK, 2.5f, SoundType.STONE), "kathairis_cobblestone_slab"),
				setup(new BlockKathairisSlab(Material.ROCK, 2.5f, SoundType.STONE), "kathairis_stone_bricks_slab"),
				setup(new BlockKathairisSlab(Material.ROCK, 2f, SoundType.STONE), "mud_bricks_slab"),
				setup(new BlockKathairisSlab(Material.WOOD, 3f, SoundType.WOOD), "mystic_wood_slab"),
				setup(new BlockKathairisSlab(Material.WOOD, 3f, SoundType.WOOD), "shiny_wood_slab"),
				setup(new BlockKathairisSlab(Material.WOOD, 3f, SoundType.WOOD), "soul_wood_slab"),
				setup(new BlockKathairisSlab(Material.ROCK, 1.5f, SoundType.STONE), "weathered_rock_slab"),
				setup(new BlockKathairisSlab(Material.ROCK, 2f, SoundType.STONE), "hardened_weathered_rock_slab"),
				setup(new BlockKathairisSlab(Material.ROCK, 2f, SoundType.STONE), "hardened_weathered_rock_bricks_slab"),
				setup(new BlockKathairisSlab(Material.WOOD, 3f, SoundType.WOOD), "elderwillow_wood_slab"),
				setup(new BlockKathairisSlab(Material.ROCK, 2f, SoundType.STONE), "kathairis_sandstone_slab"),
				//plants
				setup(new BlockBisonStars(), "bison_stars"),
				setup(new BlockKathairisPlant(), "eye_plant"),
				setup(new BlockKathairisMiniGrass(), "kathairis_mini_grass"),
				setup(new BlockKathairisTallGrass(), "kathairis_tall_grass"),
				setup(new BlockKathairisNightFlower(), "kathairis_night_flower"),
				setup(new BlockKathairisMultiGrass(), "kathairis_multi_grass"),
				setup(new BlockFrupPlant(), "frup_plant"),
				setup(new BlockKathairisFungi(), "kathairis_fungi"),
				setup(new BlockGooseberry(Material.LEAVES, 1f, 1f, SoundType.PLANT), "gooseberry_bush"),
				setup(new BlockKathairisSucculent(), "kathairis_succulent"),
				setup(new BlockSteppedSucculent(), "stepped_succulent"),
				setup(new BlockKathairisPlant(), "vilyria"),
				setup(new BlockGlowVines(), "glowvines"),
				setup(new BlockJadeVines(), "jade_vines"),
				setup(new BlockKathairisDeadGrass(), "kathairis_dead_grass"),
				setup(new BlockMagicBeans(), "magic_beans"),
				setup(new BlockButterflyFlower(), "butterfly_flower"),
				setup(new BlockCloudFlower(), "blue_cloud_flower"),
				setup(new BlockCloudFlower(), "yellow_cloud_flower"),
				setup(new BlockSnowdropCyprepedium(), "snowdrop_cyprepedium"),
				setup(new BlockFluoFungi(), "fluo_fungi"),
				setup(new BlockForestCandle(), "forest_candle"),
				setup(new BlockKathairisRocktus(), "rocktus"),
				setup(new BlockPurplePalm(),"purple_palm"),
				setup(new BlockBrinePustule(), "brine_pustule"),
				setup(new BlockWillowVineMain(), "willow_vine_main"),
				setup(new BlockWillowVineTip(), "willow_vine_tip"),
				setup(new BlockKatharianSapling(new MysticTree()), "mystic_sapling"),
				setup(new BlockKatharianSapling(new SoulTree()), "soul_sapling"),
				setup(new BlockKatharianSapling(new ShinyTree()), "shiny_sapling"),
				setup(new BlockKatharianSapling(new ElderwillowTree()), "elderwillow_sapling"),
				//doors and trapdoors
				setup(new BlockKathairisDoors(Material.WOOD, SoundType.WOOD, 3f), "mystic_wood_doors"),
				setup(new BlockKathairisDoors(Material.WOOD, SoundType.WOOD, 3f), "shiny_wood_doors"),
				setup(new BlockKathairisDoors(Material.WOOD, SoundType.WOOD, 3f), "soul_wood_doors"),
				setup(new BlockKathairisDoors(Material.WOOD, SoundType.WOOD, 3f), "elderwillow_wood_doors"),
				setup(new BlockKathairisTrapdoor(Material.WOOD, SoundType.WOOD, 3f), "mystic_wood_trapdoor"),
				setup(new BlockKathairisTrapdoor(Material.WOOD, SoundType.WOOD, 3f), "shiny_wood_trapdoor"),
				setup(new BlockKathairisTrapdoor(Material.WOOD, SoundType.WOOD, 3f), "soul_wood_trapdoor"),
				setup(new BlockKathairisTrapdoor(Material.WOOD, SoundType.WOOD, 3f), "elderwillow_wood_trapdoor")
				);

	}


	@SubscribeEvent
	public static void onRegisterSounds(final RegistryEvent.Register<SoundEvent> event) {

		event.getRegistry().registerAll(
				setupSound("mob", "bird"),

				setupSound("mob", "howler", "living"),
				setupSound("mob", "howler", "hurt"),
				setupSound("mob", "howler", "dead"),

				setupSound("mob", "jellyfish", "living"),
				setupSound("mob", "jellyfish", "hurt"),
				setupSound("mob", "jellyfish", "dead"),

				setupSound("mob", "turtle", "dead"),

				setupSound("mob", "bison", "living"),
				setupSound("mob", "bison", "hurt"),
				setupSound("mob", "bison", "dead"),

				setupSound("mob", "ghost", "living"),
				setupSound("mob", "ghost", "attack"),
				setupSound("mob", "ghost", "dead"),

				setupSound("mob", "death", "living"),

				setupSound("mob", "camel", "ambient"),
				setupSound("mob", "camel", "breath"),
				setupSound("mob", "camel", "hurt"),
				setupSound("mob", "camel", "dead"),

				setupSound("mob", "oldman", "ambient"),

				setupSound("mob", "skyray", "ambient"),
				setupSound("mob", "skyray", "hurt"),

				//

				setupSound("scary", "flower"),

				//Hardcoding "kathairis" because its referring to the dimension not the mod
				setupSound("kathairis", "music", "day"),
				setupSound("kathairis", "music", "night"),
				setupSound("kathairis", "music", "xmas"),

				setupSound("music_disc", "jazzy"),
				setupSound("music_disc", "8bit"),

				setupSound("pickaxe", "turn"),
				setupSound("magic_shoot"),

				setupSound("sandstorm")

		);

	}

	/*@SubscribeEvent
	public static void onDimensionTypeRegister(final RegistryEvent.Register<DimensionType> event){
		event.getRegistry().registerAll(
				setup(Kathairis.KATHAIRIS=DimensionManager.registerDimension(ModReference.KATHAIRIS, ModDimensions.KATHAIRIS, new PacketBuffer(Unpooled.buffer(16)),true),ModReference.KATHAIRIS)
		);
	}*/

	@SubscribeEvent
	public static void onFMLCommonSetup(final FMLCommonSetupEvent event){
		PacketHandler.register();
	}

	@SubscribeEvent
	public static void onRegisterEnchantements(final RegistryEvent.Register<Enchantment> event){
		event.getRegistry().registerAll(
				setup(KathairisEnchantments.ENCHANTMENT_ETHEREAL,"ethereal")
		);
	}







	public static SoundEvent setupSound(@Nonnull final String... nameParts) {
		final ResourceLocation name = new ResourceLocation(MOD_ID, Strings.join(nameParts, "."));
		final String registryName = Strings.join(nameParts, "_");
		return setup(new SoundEvent(name), registryName);
	}

	public static <T extends IForgeRegistryEntry> T setup(final T entry, final String name) {
		return setup(entry, new ResourceLocation(MOD_ID, name));
	}

	public static <T extends IForgeRegistryEntry> T setup(String name, final T entry) {
		return setup(entry, new ResourceLocation(MOD_ID, name));
	}

	public static <T extends IForgeRegistryEntry> T setup(final T entry, final ResourceLocation registryName) {
		entry.setRegistryName(registryName);
		return entry;
	}



}
