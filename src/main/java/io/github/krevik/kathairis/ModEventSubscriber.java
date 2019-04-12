package io.github.krevik.kathairis;

import io.github.krevik.kathairis.block.*;
import io.github.krevik.kathairis.init.*;
import io.github.krevik.kathairis.item.*;
import io.github.krevik.kathairis.util.ModUtil;
import io.github.krevik.kathairis.world.dimension.ModDimensionKathairis;
import io.github.krevik.kathairis.world.dimension.biome.biomes.*;
import joptsimple.internal.Strings;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.ModDimension;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;

import javax.annotation.Nonnull;

import static io.github.krevik.kathairis.init.ModItemGroups.BUILDING_BLOCKS;
import static io.github.krevik.kathairis.util.ModReference.MOD_ID;
import static net.minecraft.inventory.EntityEquipmentSlot.*;
import static net.minecraft.item.EnumRarity.*;
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
		final Block kathairisStone;
		final Block kathairisCobblestone;
		final Block weatheredRock;
		final Block hardenedWeatheredRock;
		final Block hardenedWeatheredRockBricks;
		final Block mudBricks;
		final Block kathairisStoneBricks;

		//TODO clean up the order of these, move them all into categories. Yes my minecraft OCD is acting up
		event.getRegistry().registerAll(
				setup(kathairisStone = new BlockKathairisStone(), "kathairis_stone"),
				setup(new BlockKathairisPortal(), "kathairis_portal"),
				setup(new BlockKathairisDirt(), "kathairis_dirt"),
				setup(new BlockKathairisGrass(), "kathairis_grass"),
				//TODO turn this into a class?
				setup(new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(2.5f, 2.5f).sound(SoundType.METAL)), "titanium_block"),
				setup(new BlockKathairisLog(), "mystic_log"),
				setup(new BlockKathairisLog(), "mystic_leaves"),
				setup(new BlockKathairisLog(), "shiny_log"),
				setup(new BlockKathairisLeaves(), "shiny_leaves"),
				setup(new BlockKathairisLog(), "soul_log"),
				setup(new BlockKathairisLog(), "soul_leaves"),
				setup(new BlockKathairisOre(1, 1, 3, Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(4f)), "revenum_ore"),
				setup(new BlockKathairisOre(1, 2, 4, Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(7f)), "titanium_ore"),
				setup(new BlockKathairisSand(), "kathairis_sand"),
				setup(new BlockSoftSand(), "soft_sand"),
				setup(new BlockKathairisCloud(() -> ModItems.CLOUD_DUST_BLUE), "blue_cloud"),
		 		setup(new BlockKathairisCloud(() -> ModItems.CLOUD_DUST_YELLOW), "yellow_cloud"),
				setup(weatheredRock = new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(1.5f, 1.5f).sound(SoundType.STONE)), "weathered_rock"),
				setup(mysticPlanks = new Block(Block.Properties.create(Material.WOOD).hardnessAndResistance(3f, 3f).sound(SoundType.WOOD)), "mystic_planks"),
				setup(shinyPlanks = new Block(Block.Properties.create(Material.WOOD).hardnessAndResistance(3f, 3f).sound(SoundType.WOOD)), "shiny_planks"),
				setup(soulPlanks = new Block(Block.Properties.create(Material.WOOD).hardnessAndResistance(3f, 3f).sound(SoundType.WOOD)), "soul_planks"),
				setup(kathairisCobblestone = new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(2.5f, 2.5f).sound(SoundType.STONE)), "kathairis_cobblestone"),
				setup(new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(2.5f, 2.5f).sound(SoundType.STONE)), "kathairis_stone_tiles"),
				setup(new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(3f).sound(SoundType.STONE).lightValue(12)), "shiny_rock"),
				setup(kathairisStoneBricks = new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(2.5f, 2.5f).sound(SoundType.STONE)), "kathairis_stone_bricks"),
				setup(new Block(Block.Properties.create(Material.GROUND).hardnessAndResistance(1f, 1f).sound(SoundType.GROUND)), "mud_block"),
				setup(mudBricks = new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(2f, 2f).sound(SoundType.STONE)), "mud_bricks"),
				setup(new BlockMagnethium(), "magnethium"),
				setup(new Block(Block.Properties.create(Material.ANVIL).hardnessAndResistance(4f, 4f).sound(SoundType.METAL)), "iron_gold_block"),
				setup(hardenedWeatheredRock = new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(2f, 2f).sound(SoundType.STONE)), "hardened_weathered_rock"),
				setup(hardenedWeatheredRockBricks = new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(2f, 2f).sound(SoundType.STONE)), "hardened_weathered_rock_bricks"),
				setup(new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(2f, 2f).sound(SoundType.STONE)), "hardened_weathered_rock_tiles"),
				setup(new BlockRefinedCloud(), "blue_cloud_refined"),
				setup(new BlockRefinedCloud(), "yellow_cloud_refined"),
				setup(new BlockCondensedCloud(), "blue_cloud_condensed"),
				setup(new BlockCondensedCloud(), "yellow_cloud_condensed"),
				setup(new BlockKathairisLog(), "mystic_log_stripped"),
				setup(new BlockKathairisLog(), "shiny_log_stripped"),
				setup(new BlockKathairisLog(), "soul_log_stripped"),

				setup(new BlockKathairisFence(Material.WOOD, 3f, SoundType.WOOD), "mystic_wood_fence"),
				setup(new BlockKathairisFenceGate(Material.WOOD, 3f, SoundType.WOOD), "mystic_wood_fence_gate"),
				setup(new BlockKathairisFence(Material.WOOD, 3f, SoundType.WOOD), "shiny_wood_fence"),
				setup(new BlockKathairisFenceGate(Material.WOOD, 3f, SoundType.WOOD), "shiny_wood_fence_gate"),
				setup(new BlockKathairisFence(Material.WOOD, 3f, SoundType.WOOD), "soul_wood_fence"),
				setup(new BlockKathairisFenceGate(Material.WOOD, 3f, SoundType.WOOD), "soul_wood_fence_gate"),

				setup(new BlockKathairisWall(Material.ROCK, 2.5f, SoundType.STONE), "kathairis_stone_wall"),
				setup(new BlockKathairisWall(Material.ROCK, 2.5f, SoundType.STONE), "kathairis_cobblestone_wall"),
				setup(new BlockKathairisWall(Material.ROCK, 1.5f, SoundType.STONE), "weathered_rock_wall"),
				setup(new BlockKathairisWall(Material.ROCK, 2f, SoundType.STONE), "hardened_weathered_rock_wall"),
				setup(new BlockKathairisWall(Material.ROCK, 2f, SoundType.STONE), "hardened_weathered_rock_bricks_wall"),
				setup(new BlockKathairisWall(Material.ROCK, 2f, SoundType.STONE), "mud_bricks_wall"),
				setup(new BlockKathairisWall(Material.ROCK, 2.5f, SoundType.STONE), "kathairis_stone_bricks_wall"),

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

				setup(new BlockCrystal(), "blue_crystal"),
				setup(new BlockCrystal(), "violet_crystal"),
				setup(new BlockCrystal(), "yellow_crystal"),

				setup(new BlockBisonStars(), "bison_stars"),
				setup(new BlockKathairisPlant(), "eye_plant"),
				setup(new BlockKathairisMiniGrass(), "kathairis_mini_grass"),
				setup(new BlockKathairisTallGrass(), "kathairis_tall_grass"),
				setup(new BlockKathairisNightFlower(), "kathairis_night_flower"),
				setup(new BlockKathairisMultiGrass(), "kathairis_multi_grass"),

				setup(new BlockCondensedCloud(), "blue_cloud_bricks"),
				setup(new BlockCondensedCloud(), "yellow_cloud_block"),

				setup(new BlockFrupPlant(), "frup_plant"),

				setup(new BlockKathairisDoors(Material.WOOD, SoundType.WOOD, 3f), "mystic_wood_doors"),
				setup(new BlockKathairisDoors(Material.WOOD, SoundType.WOOD, 3f), "shiny_wood_doors"),
				setup(new BlockKathairisDoors(Material.WOOD, SoundType.WOOD, 3f), "soul_wood_doors"),
				setup(new BlockKathairisTrapdoor(Material.WOOD, SoundType.WOOD, 3f), "mystic_wood_trapdoor"),
				setup(new BlockKathairisTrapdoor(Material.WOOD, SoundType.WOOD, 3f), "shiny_wood_trapdoor"),
				setup(new BlockKathairisTrapdoor(Material.WOOD, SoundType.WOOD, 3f), "soul_wood_trapdoor"),

				setup(new BlockKathairisFungi(), "kathairis_fungi"),
				setup(new BlockGooseberry(Material.LEAVES, 1f, 1f, SoundType.PLANT), "gooseberry_bush"),
				setup(new BlockKathairisSucculent(), "kathairis_succulent"),
				setup(new BlockSteppedSucculent(), "stepped_succulent"),
				setup(new BlockKathairisPlant(), "vilyria"),
				setup(new BlockGlowVines(), "glowvines"),
				setup(new BlockJadeVines(), "jade_vines"),
				setup(new BlockKathairisDeadGrass(), "kathairis_dead_grass"),

				setup(new Block(Block.Properties.create(Material.IRON).hardnessAndResistance(4f, 4f).sound(SoundType.METAL)), "mystic_gem_block"),

				setup(new BlockMagicBeans(), "magic_beans"),
				setup(new BlockBaurble(), "baurble"),
				setup(new BlockButterflyFlower(), "butterfly_flower"),
				setup(new BlockSolisCrystals(), "solis_crystals"),
				setup(new BlockCloudFlower(), "blue_cloud_flower"),
				setup(new BlockCloudFlower(), "yellow_cloud_flower"),
				setup(new BlockSnowdropCyprepedium(), "snowdrop_cyprepedium"),
				setup(new BlockFluoFungi(), "fluo_fungi"),
				setup(new BlockLayeredSand(), "layered_sand"),
				setup(new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(1.5f, 1.5f).sound(SoundType.STONE)), "kathairis_sandstone"), //TODO: reorganise
				setup(new BlockKathairisPlant(), "forest_candle"),
				setup(new BlockKathairisRocktus(), "rocktus"),
				setup(new BlockKathairisLog(), "elderwillow_log"),
				setup(new BlockKathairisLeaves(), "elderwillow_leaves"),
				setup(new BlockPurplePalm(),"purple_palm")
		);

	}

	@SubscribeEvent
	public static void onRegisterItems(final RegistryEvent.Register<Item> event) {
		final IForgeRegistry<Item> registry = event.getRegistry();
		registry.registerAll(
				setup(new ItemMysticGem(), "mystic_gem"),
				setup(new Item(new Item.Properties().group(ModItemGroups.MATERIALS)), "titanium_ingot"),
				setup(new Item(new Item.Properties().group(ModItemGroups.MATERIALS)), "titanium_rod"),
				setup(new Item(new Item.Properties().group(ModItemGroups.MATERIALS)), "revenum_ingot"),
				setup(new Item(new Item.Properties().group(ModItemGroups.MISCELLANEOUS).rarity(EPIC)), "cloud_essence"),
				setup(new Item(new Item.Properties().group(ModItemGroups.MISCELLANEOUS).rarity(RARE)), "howler_fur"),
				setup(new Item(new Item.Properties().group(ModItemGroups.MATERIALS).rarity(RARE)), "crystal_cluster"),
				setup(new Item(new Item.Properties().group(ModItemGroups.MATERIALS).rarity(RARE)), "magnethium_shard"),
				setup(new Item(new Item.Properties().group(ModItemGroups.MISCELLANEOUS)), "crystal_shard_yellow"),
				setup(new Item(new Item.Properties().group(ModItemGroups.MISCELLANEOUS)), "crystal_shard_blue"),
				setup(new Item(new Item.Properties().group(ModItemGroups.MISCELLANEOUS)), "crystal_shard_violet"),
				setup(new Item(new Item.Properties().group(ModItemGroups.MISCELLANEOUS)), "tortoise_shell"),
				setup(new Item(new Item.Properties().group(ModItemGroups.MISCELLANEOUS)), "butterfly_flower_nectar"),
				setup(new Item(new Item.Properties().group(ModItemGroups.MATERIALS)), "iron_gold_ingot"),
				setup(new ItemKathairisArmor(ModArmorMaterials.TITANIUM, HEAD), "titanium_helmet"),
				setup(new ItemKathairisArmor(ModArmorMaterials.TITANIUM, CHEST), "titanium_chestplate"),
				setup(new ItemKathairisArmor(ModArmorMaterials.TITANIUM, LEGS), "titanium_leggings"),
				setup(new ItemKathairisArmor(ModArmorMaterials.TITANIUM, FEET), "titanium_boots"),
				setup(new ItemKathairisSword(ModItemTiers.TITANIUM), "titanium_sword"),
				setup(new ItemKathairisHoe(ModItemTiers.TITANIUM), "titanium_hoe"),
				setup(new ItemKathairisAxe(ModItemTiers.TITANIUM), "titanium_axe"),
				setup(new ItemKathairisPickaxe(ModItemTiers.TITANIUM), "titanium_pickaxe"),
				setup(new ItemKathairisShovel(ModItemTiers.TITANIUM), "titanium_shovel"),
				setup(new ItemKathairisArmor(ModArmorMaterials.REVENUM, HEAD), "revenum_helmet"),
				setup(new ItemKathairisArmor(ModArmorMaterials.REVENUM, CHEST), "revenum_chestplate"),
				setup(new ItemKathairisArmor(ModArmorMaterials.REVENUM, LEGS), "revenum_leggings"),
				setup(new ItemKathairisArmor(ModArmorMaterials.REVENUM, FEET), "revenum_boots"),
				setup(new ItemKathairisSword(ModItemTiers.REVENUM), "revenum_sword"),
				setup(new ItemKathairisHoe(ModItemTiers.REVENUM), "revenum_hoe"),
				setup(new ItemKathairisAxe(ModItemTiers.REVENUM), "revenum_axe"),
				setup(new ItemKathairisPickaxe(ModItemTiers.REVENUM), "revenum_pickaxe"),
				setup(new ItemKathairisShovel(ModItemTiers.REVENUM), "revenum_shovel"),
				setup(new Item(new Item.Properties().group(ModItemGroups.MISCELLANEOUS).rarity(EPIC)), "skyray_feather"),
				setup(new Item(new Item.Properties().group(ModItemGroups.MISCELLANEOUS).rarity(EPIC)), "solis_crystal"),
				setup(new ItemKathairisArmor(ModArmorMaterials.MYSTIC, HEAD), "mystic_helmet"),
				setup(new ItemKathairisArmor(ModArmorMaterials.MYSTIC, CHEST), "mystic_chestplate"),
				setup(new ItemKathairisArmor(ModArmorMaterials.MYSTIC, LEGS), "mystic_leggings"),
				setup(new ItemKathairisArmor(ModArmorMaterials.MYSTIC, FEET), "mystic_boots"),
				setup(new ItemKathairisSword(ModItemTiers.MYSTIC), "mystic_sword"),
				setup(new ItemKathairisHoe(ModItemTiers.MYSTIC), "mystic_hoe"),
				setup(new ItemKathairisAxe(ModItemTiers.MYSTIC), "mystic_axe"),
				setup(new ItemKathairisPickaxe(ModItemTiers.MYSTIC), "mystic_pickaxe"),
				setup(new ItemKathairisShovel(ModItemTiers.MYSTIC), "mystic_shovel"),
				setup(new Item(new Item.Properties().group(ModItemGroups.MISCELLANEOUS).rarity(EPIC)), "cloud_pearl"),
				setup(new Item(new Item.Properties().group(ModItemGroups.MATERIALS)), "shiny_stick"),
				setup(new Item(new Item.Properties().group(ModItemGroups.MISCELLANEOUS)), "cloud_dust_blue"),
				setup(new Item(new Item.Properties().group(ModItemGroups.MISCELLANEOUS)), "cloud_dust_yellow"),
				setup(new Item(new Item.Properties().group(ModItemGroups.MISCELLANEOUS)), "condensed_cloud_dust_blue"),
				setup(new Item(new Item.Properties().group(ModItemGroups.MISCELLANEOUS)), "condensed_cloud_dust_yellow"),
				setup(new Item(new Item.Properties().group(ModItemGroups.MISCELLANEOUS)), "jar_with_swamp_gas"),
				setup(new Item(new Item.Properties().group(ModItemGroups.MISCELLANEOUS)), "crystal_blend"),
				setup(new Item(new Item.Properties().group(ModItemGroups.FOOD)), "heart"),
				setup(new Item(new Item.Properties().group(ModItemGroups.FOOD)), "cotton_candy"),
				setup(new Item(new Item.Properties().group(ModItemGroups.FOOD)), "bison_meat"),
				setup(new Item(new Item.Properties().group(ModItemGroups.FOOD)), "cooked_bison_meat"),
				setup(new Item(new Item.Properties().group(ModItemGroups.MISCELLANEOUS).rarity(RARE)), "jellyfish_tentacle"),
				setup(new ItemGooseberries(2, 0.4f, ModBlocks.GOOSEBERRY_BUSH), "gooseberries"),
				setup(new Item(new Item.Properties().group(ModItemGroups.MISCELLANEOUS)), "venom_sac"),
				setup(new Item(new Item.Properties().group(ModItemGroups.MISCELLANEOUS)), "glass_jar"),
				setup(new Item(new Item.Properties().group(ModItemGroups.FOOD)), "nectar_bowl"),
				setup(new Item(new Item.Properties().group(ModItemGroups.MISCELLANEOUS).rarity(COMMON)), "butterfly_common_1"),
				setup(new Item(new Item.Properties().group(ModItemGroups.MISCELLANEOUS).rarity(COMMON)), "butterfly_common_2"),
				setup(new Item(new Item.Properties().group(ModItemGroups.MISCELLANEOUS).rarity(COMMON)), "butterfly_common_moth"),
				setup(new Item(new Item.Properties().group(ModItemGroups.MISCELLANEOUS).rarity(UNCOMMON)), "butterfly_illukini"),
				setup(new Item(new Item.Properties().group(ModItemGroups.MISCELLANEOUS).rarity(RARE)), "butterfly_cloud_shimmer"),
				setup(new Item(new Item.Properties().group(ModItemGroups.MISCELLANEOUS)), "butterfly_catcher"),
				setup(new Item(new Item.Properties().group(ModItemGroups.MISCELLANEOUS)), "wings_piece"),
				setup(new Item(new Item.Properties().group(ModItemGroups.FOOD)), "fungal_drug"),
				setup(new Item(new Item.Properties().group(ModItemGroups.FOOD)), "bitten_cookie"),
				setup(new Item(new Item.Properties().group(ModItemGroups.FOOD)), "candy_cane"),
				setup(new Item(new Item.Properties().group(ModItemGroups.FOOD)), "christmas_chocolate"),
				setup(new Item(new Item.Properties().group(ModItemGroups.FOOD)), "ice_creams"),
				setup(new Item(new Item.Properties().group(ModItemGroups.FOOD)), "sweet_muffin"),
				setup(new Item(new Item.Properties().group(ModItemGroups.MISCELLANEOUS)), "pot_with_living_flower"),
				setup(new ItemKathairisPickaxe(ModItemTiers.MAGNETHIUM), "magnethium_pickaxe"),
				setup(new ItemKathairisAxe(ModItemTiers.MAGNETHIUM), "magnethium_axe"),
				setup(new ItemKathairisShovel(ModItemTiers.MAGNETHIUM), "magnethium_shovel"),
				setup(new ItemKathairisSword(ModItemTiers.MAGNETHIUM), "magnethium_sword"),
				setup(new ItemKathairisHoe(ModItemTiers.MAGNETHIUM), "magnethium_hoe"),
				setup(new ItemKathairisPickaxe(ModItemTiers.CRYSTAL), "crystal_pickaxe"),
				setup(new ItemFrup(3, 0.4f, ModBlocks.FRUP_PLANT), "frup"),
				setup(new ItemMagicBeans(2, 0.4f, ModBlocks.MAGIC_BEANS), "magic_beans")
		);
		for (final Block block : ModUtil.getModEntries(ForgeRegistries.BLOCKS)) {

			//TODO find better way, maybe make all blocks provide their creative tab and if they have an itemblock?
			if (block == ModBlocks.MAGIC_BEANS || block == ModBlocks.KATHAIRIS_PORTAL) {
				continue;
			}
			registry.register(setup(new ItemBlock(block, new Item.Properties().group(BUILDING_BLOCKS)), block.getRegistryName()));
		}

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

				setupSound("cloudglass", "break"),

				setupSound("pickaxe", "turn"),

				setupSound("sandstorm")

		);

	}

	@SubscribeEvent
	public static void onRegisterModDimensions(final RegistryEvent.Register<ModDimension> event) {
				event.getRegistry().registerAll(
						new ModDimensionKathairis(new ResourceLocation(MOD_ID, "kathairis"))
		);
	}


	@SubscribeEvent
	public static void onRegisterBiomes(final RegistryEvent.Register<Biome> event) {
		event.getRegistry().registerAll(
				setup(new BiomeKatharianRiver(),"kathairis_river"),
				setup(new BiomeKatharianDesert(),"katharian_desert"),
				setup(new BiomeKatharianDesertEdge(),"katharian_desert_edge"),
				setup(new BiomeKatharianSoftSandLakes(),"soft_sand_lakes"),
				setup(new BiomeKatharianForest(),"katharian_forest"),
				setup(new BiomeKatharianPlainFields(),"plain_fields"),
				setup(new BiomeKatharianSwamps(),"katharian_swamp")
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

	public static <T extends IForgeRegistryEntry> T setup(final T entry, final ResourceLocation registryName) {
		entry.setRegistryName(registryName);
		return entry;
	}

}
