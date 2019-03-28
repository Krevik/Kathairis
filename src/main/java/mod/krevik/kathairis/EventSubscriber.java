package mod.krevik.kathairis;

import mod.krevik.kathairis.block.BlockBaurble;
import mod.krevik.kathairis.block.BlockBisonStars;
import mod.krevik.kathairis.block.BlockButterflyFlower;
import mod.krevik.kathairis.block.BlockCloudFlower;
import mod.krevik.kathairis.block.BlockCondensedCloud;
import mod.krevik.kathairis.block.BlockCrystal;
import mod.krevik.kathairis.block.BlockFluoFungi;
import mod.krevik.kathairis.block.BlockFrupPlant;
import mod.krevik.kathairis.block.BlockGlowVines;
import mod.krevik.kathairis.block.BlockGooseberry;
import mod.krevik.kathairis.block.BlockJadeVines;
import mod.krevik.kathairis.block.BlockKathairisPortal;
import mod.krevik.kathairis.block.BlockKatharianCloud;
import mod.krevik.kathairis.block.BlockKatharianDeadGrass;
import mod.krevik.kathairis.block.BlockKatharianDirt;
import mod.krevik.kathairis.block.BlockKatharianDoors;
import mod.krevik.kathairis.block.BlockKatharianFence;
import mod.krevik.kathairis.block.BlockKatharianFenceGate;
import mod.krevik.kathairis.block.BlockKatharianFungi;
import mod.krevik.kathairis.block.BlockKatharianGrass;
import mod.krevik.kathairis.block.BlockKatharianLeaves;
import mod.krevik.kathairis.block.BlockKatharianLog;
import mod.krevik.kathairis.block.BlockKatharianMiniGrass;
import mod.krevik.kathairis.block.BlockKatharianMultiGrass;
import mod.krevik.kathairis.block.BlockKatharianNightFlower;
import mod.krevik.kathairis.block.BlockKatharianOre;
import mod.krevik.kathairis.block.BlockKatharianPlant;
import mod.krevik.kathairis.block.BlockKatharianRocktus;
import mod.krevik.kathairis.block.BlockKatharianSand;
import mod.krevik.kathairis.block.BlockKatharianSlab;
import mod.krevik.kathairis.block.BlockKatharianStairs;
import mod.krevik.kathairis.block.BlockKatharianStone;
import mod.krevik.kathairis.block.BlockKatharianSucculent;
import mod.krevik.kathairis.block.BlockKatharianTallGrass;
import mod.krevik.kathairis.block.BlockKatharianTrapdoor;
import mod.krevik.kathairis.block.BlockKatharianWall;
import mod.krevik.kathairis.block.BlockLayeredSand;
import mod.krevik.kathairis.block.BlockMagicBeans;
import mod.krevik.kathairis.block.BlockMagnethium;
import mod.krevik.kathairis.block.BlockRefinedCloud;
import mod.krevik.kathairis.block.BlockSnowdropCyprepedium;
import mod.krevik.kathairis.block.BlockSoftSand;
import mod.krevik.kathairis.block.BlockSolisCrystals;
import mod.krevik.kathairis.block.BlockSteppedSucculent;
import mod.krevik.kathairis.init.ModArmorMaterials;
import mod.krevik.kathairis.init.ModItemTiers;
import mod.krevik.kathairis.item.ItemFrup;
import mod.krevik.kathairis.item.ItemGooseberries;
import mod.krevik.kathairis.item.ItemKathairisArmor;
import mod.krevik.kathairis.item.ItemKathairisAxe;
import mod.krevik.kathairis.item.ItemKathairisHoe;
import mod.krevik.kathairis.item.ItemKathairisPickaxe;
import mod.krevik.kathairis.item.ItemKathairisShovel;
import mod.krevik.kathairis.item.ItemKathairisSword;
import mod.krevik.kathairis.item.ItemMagicBeans;
import mod.krevik.kathairis.item.ItemMysticGem;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.IForgeRegistryEntry;

import static mod.krevik.kathairis.init.ModBlocks.FRUP_PLANT;
import static mod.krevik.kathairis.init.ModBlocks.GOOSEBERRY_BUSH;
import static mod.krevik.kathairis.init.ModBlocks.MAGIC_BEANS;
import static mod.krevik.kathairis.init.ModItemGroups.FOOD;
import static mod.krevik.kathairis.init.ModItemGroups.MATERIALS;
import static mod.krevik.kathairis.init.ModItemGroups.MISCELLANEOUS;
import static mod.krevik.kathairis.init.ModItems.CLOUD_DUST_BLUE;
import static mod.krevik.kathairis.init.ModItems.CLOUD_DUST_YELLOW;
import static mod.krevik.kathairis.util.ModReference.MOD_ID;
import static net.minecraft.inventory.EntityEquipmentSlot.CHEST;
import static net.minecraft.inventory.EntityEquipmentSlot.FEET;
import static net.minecraft.inventory.EntityEquipmentSlot.HEAD;
import static net.minecraft.inventory.EntityEquipmentSlot.LEGS;
import static net.minecraft.item.EnumRarity.COMMON;
import static net.minecraft.item.EnumRarity.EPIC;
import static net.minecraft.item.EnumRarity.RARE;
import static net.minecraft.item.EnumRarity.UNCOMMON;

/**
 * @author Cadiboo
 */
@EventBusSubscriber(modid = MOD_ID)
public final class EventSubscriber {

	@SubscribeEvent
	public static void onRegisterItems(final RegistryEvent.Register<Item> event) {
		event.getRegistry().registerAll(
				setup(new ItemMysticGem(), "mystic_gem"),
				setup(new Item(new Item.Properties().group(MATERIALS)), "titanium_ingot"),
				setup(new Item(new Item.Properties().group(MATERIALS)), "titanium_rod"),
				setup(new Item(new Item.Properties().group(MATERIALS)), "revenum_ingot"),
				setup(new Item(new Item.Properties().group(MISCELLANEOUS).rarity(EPIC)), "cloud_essence"),
				setup(new Item(new Item.Properties().group(MISCELLANEOUS).rarity(RARE)), "howler_fur"),
				setup(new Item(new Item.Properties().group(MATERIALS).rarity(RARE)), "crystal_cluster"),
				setup(new Item(new Item.Properties().group(MATERIALS).rarity(RARE)), "magnethium_shard"),
				setup(new Item(new Item.Properties().group(MISCELLANEOUS)), "crystal_shard_yellow"),
				setup(new Item(new Item.Properties().group(MISCELLANEOUS)), "crystal_shard_blue"),
				setup(new Item(new Item.Properties().group(MISCELLANEOUS)), "crystal_shard_violet"),
				setup(new Item(new Item.Properties().group(MISCELLANEOUS)), "tortoise_shell"),
				setup(new Item(new Item.Properties().group(MISCELLANEOUS)), "butterfly_flower_nectar"),
				setup(new Item(new Item.Properties().group(MATERIALS)), "iron_gold_ingot"),
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
				setup(new Item(new Item.Properties().group(MISCELLANEOUS).rarity(EPIC)), "skyray_feather"),
				setup(new Item(new Item.Properties().group(MISCELLANEOUS).rarity(EPIC)), "solis_crystal"),
				setup(new ItemKathairisArmor(ModArmorMaterials.MYSTIC, HEAD), "mystic_helmet"),
				setup(new ItemKathairisArmor(ModArmorMaterials.MYSTIC, CHEST), "mystic_chestplate"),
				setup(new ItemKathairisArmor(ModArmorMaterials.MYSTIC, LEGS), "mystic_leggings"),
				setup(new ItemKathairisArmor(ModArmorMaterials.MYSTIC, FEET), "mystic_boots"),
				setup(new ItemKathairisSword(ModItemTiers.MYSTIC), "mystic_sword"),
				setup(new ItemKathairisHoe(ModItemTiers.MYSTIC), "mystic_hoe"),
				setup(new ItemKathairisAxe(ModItemTiers.MYSTIC), "mystic_axe"),
				setup(new ItemKathairisPickaxe(ModItemTiers.MYSTIC), "mystic_pickaxe"),
				setup(new ItemKathairisShovel(ModItemTiers.MYSTIC), "mystic_shovel"),
				setup(new Item(new Item.Properties().group(MISCELLANEOUS).rarity(EPIC)), "cloud_pearl"),
				setup(new Item(new Item.Properties().group(MATERIALS)), "shiny_stick"),
				setup(new Item(new Item.Properties().group(MISCELLANEOUS)), "cloud_dust_blue"),
				setup(new Item(new Item.Properties().group(MISCELLANEOUS)), "cloud_dust_yellow"),
				setup(new Item(new Item.Properties().group(MISCELLANEOUS)), "condensed_cloud_dust_blue"),
				setup(new Item(new Item.Properties().group(MISCELLANEOUS)), "condensed_cloud_dust_yellow"),
				setup(new Item(new Item.Properties().group(MISCELLANEOUS)), "jar_with_swamp_gas"),
				setup(new Item(new Item.Properties().group(MISCELLANEOUS)), "crystal_blend"),
				setup(new Item(new Item.Properties().group(FOOD)), "heart"),
				setup(new Item(new Item.Properties().group(FOOD)), "cotton_candy"),
				setup(new Item(new Item.Properties().group(FOOD)), "bison_meat"),
				setup(new Item(new Item.Properties().group(FOOD)), "cooked_bison_meat"),
				setup(new Item(new Item.Properties().group(MISCELLANEOUS).rarity(RARE)), "jellyfish_tentacle"),
				setup(new ItemGooseberries(2, 0.4f, GOOSEBERRY_BUSH), "gooseberries"),
				setup(new Item(new Item.Properties().group(MISCELLANEOUS)), "venom_sac"),
				setup(new Item(new Item.Properties().group(MISCELLANEOUS)), "glass_jar"),
				setup(new Item(new Item.Properties().group(FOOD)), "nectar_bowl"),
				setup(new Item(new Item.Properties().group(MISCELLANEOUS).rarity(COMMON)), "butterfly_common_1"),
				setup(new Item(new Item.Properties().group(MISCELLANEOUS).rarity(COMMON)), "butterfly_common_2"),
				setup(new Item(new Item.Properties().group(MISCELLANEOUS).rarity(COMMON)), "butterfly_common_moth"),
				setup(new Item(new Item.Properties().group(MISCELLANEOUS).rarity(UNCOMMON)), "butterfly_illukini"),
				setup(new Item(new Item.Properties().group(MISCELLANEOUS).rarity(RARE)), "butterfly_cloud_shimmer"),
				setup(new Item(new Item.Properties().group(MISCELLANEOUS)), "butterfly_catcher"),
				setup(new Item(new Item.Properties().group(MISCELLANEOUS)), "wings_piece"),
				setup(new Item(new Item.Properties().group(FOOD)), "fungal_drug"),
				setup(new Item(new Item.Properties().group(FOOD)), "bitten_cookie"),
				setup(new Item(new Item.Properties().group(FOOD)), "candy_cane"),
				setup(new Item(new Item.Properties().group(FOOD)), "christmas_chocolate"),
				setup(new Item(new Item.Properties().group(FOOD)), "ice_creams"),
				setup(new Item(new Item.Properties().group(FOOD)), "sweet_muffin"),
				setup(new Item(new Item.Properties().group(MISCELLANEOUS)), "pot_with_living_flower"),
				setup(new ItemKathairisPickaxe(ModItemTiers.MAGNETHIUM), "magnethium_pickaxe"),
				setup(new ItemKathairisAxe(ModItemTiers.MAGNETHIUM), "magnethium_axe"),
				setup(new ItemKathairisShovel(ModItemTiers.MAGNETHIUM), "magnethium_shovel"),
				setup(new ItemKathairisSword(ModItemTiers.MAGNETHIUM), "magnethium_sword"),
				setup(new ItemKathairisHoe(ModItemTiers.MAGNETHIUM), "magnethium_hoe"),
				setup(new ItemKathairisPickaxe(ModItemTiers.CRYSTAL), "crystal_pickaxe"),
				setup(new ItemFrup(3, 0.4f, FRUP_PLANT), "frup"),
				setup(new ItemMagicBeans(2, 0.4f, MAGIC_BEANS), "magic_beans")
		);

	}

	@SubscribeEvent
	public static void onRegisterBlocks(final RegistryEvent.Register<Block> event) {

		final Block mysticPlanks;
		final Block shinyPlanks;
		final Block soulPlanks;
		final Block katharianStone;
		final Block katharianCobblestone;
		final Block weatheredRock;
		final Block hardenedWeatheredRock;
		final Block hardenedWeatheredRockBricks;
		final Block mudBricks;
		final Block katharianStoneBricks;

		//TODO clean up the order of these, move them all into categories. Yes my minecraft OCD is acting up
		event.getRegistry().registerAll(
				setup(katharianStone = new BlockKatharianStone(), "katharian_stone"),
				setup(new BlockKathairisPortal(), "katharis_portal"),
				setup(new BlockKatharianDirt(), "katharian_dirt"),
				setup(new BlockKatharianGrass(), "katharian_grass"),
				//TODO turn this into a class?
				setup(new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(2.5f, 2.5f).sound(SoundType.METAL)), "titanium_block"),
				setup(new BlockKatharianLog(), "mystic_log"),
				setup(new BlockKatharianLog(), "mystic_leaves"),
				setup(new BlockKatharianLog(), "shiny_log"),
				setup(new BlockKatharianLeaves(), "shiny_leaves"),
				setup(new BlockKatharianLeaves(), "soul_log"),
				setup(new BlockKatharianLeaves(), "soul_leaves"),
				setup(new BlockKatharianOre(1, 1, 3, Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(4f)), "revenum_ore"),
				setup(new BlockKatharianOre(1, 2, 4, Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(7f)), "titanium_ore"),
				setup(new BlockKatharianSand(), "katharian_sand"),
				setup(new BlockSoftSand(), "soft_sand"),
				setup(new BlockKatharianCloud(() -> CLOUD_DUST_BLUE), "blue_cloud"),
				setup(new BlockKatharianCloud(() -> CLOUD_DUST_YELLOW), "yellow_cloud"),
				setup(weatheredRock = new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(1.5f, 1.5f).sound(SoundType.STONE)), "weathered_rock"),
				setup(mysticPlanks = new Block(Block.Properties.create(Material.WOOD).hardnessAndResistance(3f, 3f).sound(SoundType.WOOD)), "mystic_planks"),
				setup(shinyPlanks = new Block(Block.Properties.create(Material.WOOD).hardnessAndResistance(3f, 3f).sound(SoundType.WOOD)), "shiny_planks"),
				setup(soulPlanks = new Block(Block.Properties.create(Material.WOOD).hardnessAndResistance(3f, 3f).sound(SoundType.WOOD)), "soul_planks"),
				setup(katharianCobblestone = new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(2.5f, 2.5f).sound(SoundType.STONE)), "katharian_cobblestone"),
				setup(new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(2.5f, 2.5f).sound(SoundType.STONE)), "katharian_stone_tiles"),
				setup(new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(3f).sound(SoundType.STONE).lightValue(12)), "shiny_rock"),
				setup(katharianStoneBricks = new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(2.5f, 2.5f).sound(SoundType.STONE)), "katharian_stone_bricks"),
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
				setup(new BlockKatharianLog(), "mystic_log_stripped"),
				setup(new BlockKatharianLog(), "shiny_log_stripped"),
				setup(new BlockKatharianLog(), "soul_log_stripped"),

				setup(new BlockKatharianFence(Material.WOOD, 3f, SoundType.WOOD), "mystic_wood_fence"),
				setup(new BlockKatharianFenceGate(Material.WOOD, 3f, SoundType.WOOD), "mystic_wood_fence_gate"),
				setup(new BlockKatharianFence(Material.WOOD, 3f, SoundType.WOOD), "shiny_wood_fence"),
				setup(new BlockKatharianFenceGate(Material.WOOD, 3f, SoundType.WOOD), "shiny_wood_fence_gate"),
				setup(new BlockKatharianFence(Material.WOOD, 3f, SoundType.WOOD), "soul_wood_fence"),
				setup(new BlockKatharianFenceGate(Material.WOOD, 3f, SoundType.WOOD), "soul_wood_fence_gate"),

				setup(new BlockKatharianWall(Material.ROCK, 2.5f, SoundType.STONE), "katharian_stone_wall"),
				setup(new BlockKatharianWall(Material.ROCK, 2.5f, SoundType.STONE), "katharian_cobblestone_wall"),
				setup(new BlockKatharianWall(Material.ROCK, 1.5f, SoundType.STONE), "weathered_rock_wall"),
				setup(new BlockKatharianWall(Material.ROCK, 2f, SoundType.STONE), "hardened_weathered_rock_wall"),
				setup(new BlockKatharianWall(Material.ROCK, 2f, SoundType.STONE), "hardened_weathered_rock_bricks_wall"),
				setup(new BlockKatharianWall(Material.ROCK, 2f, SoundType.STONE), "mud_bricks_wall"),
				setup(new BlockKatharianWall(Material.ROCK, 2.5f, SoundType.STONE), "katharian_stone_bricks_wall"),

				setup(new BlockKatharianStairs(mysticPlanks.getDefaultState(), Material.WOOD, 3f, SoundType.WOOD), "mystic_wood_stairs"),
				setup(new BlockKatharianStairs(shinyPlanks.getDefaultState(), Material.WOOD, 3f, SoundType.WOOD), "shiny_wood_stairs"),
				setup(new BlockKatharianStairs(soulPlanks.getDefaultState(), Material.WOOD, 3f, SoundType.WOOD), "soul_wood_stairs"),
				setup(new BlockKatharianStairs(katharianStone.getDefaultState(), Material.ROCK, 2.5f, SoundType.STONE), "katharian_stone_stairs"),
				setup(new BlockKatharianStairs(katharianCobblestone.getDefaultState(), Material.ROCK, 2.5f, SoundType.STONE), "katharian_cobblestone_stairs"),
				setup(new BlockKatharianStairs(weatheredRock.getDefaultState(), Material.ROCK, 2.5f, SoundType.STONE), "weathered_rock_stairs"),
				setup(new BlockKatharianStairs(hardenedWeatheredRock.getDefaultState(), Material.ROCK, 1.5f, SoundType.STONE), "hardened_weathered_rock_stairs"),
				setup(new BlockKatharianStairs(hardenedWeatheredRockBricks.getDefaultState(), Material.ROCK, 2f, SoundType.STONE), "hardened_weathered_rock_bricks_stairs"),
				setup(new BlockKatharianStairs(mudBricks.getDefaultState(), Material.ROCK, 2f, SoundType.STONE), "mud_bricks_stairs"),
				setup(new BlockKatharianStairs(katharianStoneBricks.getDefaultState(), Material.ROCK, 2f, SoundType.STONE), "katharian_stone_bricks_stairs"),

				setup(new BlockKatharianSlab(Material.ROCK, 2.5f, SoundType.STONE), "katharian_stone_slab"),
				setup(new BlockKatharianSlab(Material.ROCK, 2.5f, SoundType.STONE), "katharian_cobblestone_slab"),
				setup(new BlockKatharianSlab(Material.ROCK, 2.5f, SoundType.STONE), "katharian_stone_bricks_slab"),
				setup(new BlockKatharianSlab(Material.ROCK, 2f, SoundType.STONE), "mud_bricks_slab"),
				setup(new BlockKatharianSlab(Material.WOOD, 3f, SoundType.WOOD), "mystic_wood_slab"),
				setup(new BlockKatharianSlab(Material.WOOD, 3f, SoundType.WOOD), "shiny_wood_slab"),
				setup(new BlockKatharianSlab(Material.WOOD, 3f, SoundType.WOOD), "soul_wood_slab"),
				setup(new BlockKatharianSlab(Material.ROCK, 1.5f, SoundType.STONE), "weathered_rock_slab"),
				setup(new BlockKatharianSlab(Material.ROCK, 2f, SoundType.STONE), "hardened_weathered_rock_slab"),
				setup(new BlockKatharianSlab(Material.ROCK, 2f, SoundType.STONE), "hardened_weathered_rock_bricks_slab"),

				setup(new BlockCrystal(), "blue_crystal"),
				setup(new BlockCrystal(), "violet_crystal"),
				setup(new BlockCrystal(), "yellow_crystal"),

				setup(new BlockBisonStars(), "bison_stars"),
				setup(new BlockKatharianPlant(), "eye_plant"),
				setup(new BlockKatharianMiniGrass(), "katharian_mini_grass"),
				setup(new BlockKatharianTallGrass(), "katharian_tall_grass"),
				setup(new BlockKatharianNightFlower(), "katharian_night_flower"),
				setup(new BlockKatharianMultiGrass(), "katharian_multi_grass"),

				setup(new BlockCondensedCloud(), "blue_cloud_bricks"),
				setup(new BlockCondensedCloud(), "yellow_cloud_block"),

				setup(new BlockFrupPlant(), "frup_plant"),

				setup(new BlockKatharianDoors(Material.WOOD, SoundType.WOOD, 3f), "mystic_wood_doors"),
				setup(new BlockKatharianDoors(Material.WOOD, SoundType.WOOD, 3f), "shiny_wood_doors"),
				setup(new BlockKatharianDoors(Material.WOOD, SoundType.WOOD, 3f), "soul_wood_doors"),
				setup(new BlockKatharianTrapdoor(Material.WOOD, SoundType.WOOD, 3f), "mystic_wood_trapdoor"),
				setup(new BlockKatharianTrapdoor(Material.WOOD, SoundType.WOOD, 3f), "shiny_wood_trapdoor"),
				setup(new BlockKatharianTrapdoor(Material.WOOD, SoundType.WOOD, 3f), "soul_wood_trapdoor"),

				setup(new BlockKatharianFungi(), "katharian_fungi"),
				setup(new BlockGooseberry(Material.LEAVES, 1f, 1f, SoundType.PLANT), "gooseberry_bush"),
				setup(new BlockKatharianSucculent(), "katharian_succulent"),
				setup(new BlockSteppedSucculent(), "stepped_succulent"),
				setup(new BlockKatharianPlant(), "vilyria"),
				setup(new BlockGlowVines(), "glowvines"),
				setup(new BlockJadeVines(), "jade_vines"),
				setup(new BlockKatharianDeadGrass(), "katharian_dead_grass"),

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
				setup(new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(1.5f, 1.5f).sound(SoundType.STONE)), "katharian_sandstone"), //TODO: reorganise
				setup(new BlockKatharianPlant(), "forest_candle"),
				setup(new BlockKatharianRocktus(), "rocktus"),
				setup(new BlockKatharianLog(), "elderwillow_log"),
				setup(new BlockKatharianLeaves(), "elderwillow_leaves")
		);

	}

	public static <T extends IForgeRegistryEntry> T setup(final T entry, final String name) {
		entry.setRegistryName(new ResourceLocation(MOD_ID, name));
		return entry;
	}

}
