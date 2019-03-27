package mod.krevik.kathairis;

import mod.krevik.kathairis.block.BlockBaurble;
import mod.krevik.kathairis.block.BlockKathairianPortal;
import mod.krevik.kathairis.block.BlockKatharianDirt;
import mod.krevik.kathairis.block.BlockKatharianGrass;
import mod.krevik.kathairis.block.BlockKatharianStone;
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

import static mod.krevik.kathairis.util.ModReference.MOD_ID;

/**
 * @author Cadiboo
 */
@EventBusSubscriber(modid = MOD_ID)
public final class EventSubscriber {

	@SubscribeEvent
	public static void onRegisterItems(final RegistryEvent.Register<Item> event) {
		event.getRegistry().registerAll(
				setup(new ItemMysticGem(), "mystic_gem"),
				setup(new Item(), "titanium_ingot"),
				setup(new Item(), "titanium_rod"),
				setup(new Item(), "revenum_ingot"),
				setup(new Item(), "cloud_essence"),
				setup(new Item(), "howler_fur"),
				setup(new Item(), "crystal_cluster"),
				setup(new Item(), "magnethium_shard"),
				setup(new Item(), "crystal_shard_yellow"),
				setup(new Item(), "crystal_shard_blue"),
				setup(new Item(), "crystal_shard_violet"),
				setup(new Item(), "tortoise_shell"),
				setup(new Item(), "butterfly_flower_nectar"),
				setup(new Item(), "iron_gold_ingot"),
				setup(new Item(), "titanium_helmet"),
				setup(new Item(), "titanium_chestplate"),
				setup(new Item(), "titanium_leggins"),
				setup(new Item(), "titanium_boots"),
				setup(new Item(), "titanium_sword"),
				setup(new Item(), "titanium_hoe"),
				setup(new Item(), "titanium_axe"),
				setup(new Item(), "titanium_pickaxe"),
				setup(new Item(), "titanium_shovel"),
				setup(new Item(), "revenum_helmet"),
				setup(new Item(), "revenum_chestplate"),
				setup(new Item(), "revenum_leggins"),
				setup(new Item(), "revenum_boots"),
				setup(new Item(), "revenum_sword"),
				setup(new Item(), "revenum_hoe"),
				setup(new Item(), "revenum_axe"),
				setup(new Item(), "revenum_pickaxe"),
				setup(new Item(), "revenum_shovel"),
				setup(new Item(), "skyray_feather"),
				setup(new Item(), "solis_crystal"),
				setup(new Item(), "mystic_helmet"),
				setup(new Item(), "mystic_chestplate"),
				setup(new Item(), "mystic_leggins"),
				setup(new Item(), "mystic_boots"),
				setup(new Item(), "mystic_sword"),
				setup(new Item(), "mystic_hoe"),
				setup(new Item(), "mystic_axe"),
				setup(new Item(), "mystic_pickaxe"),
				setup(new Item(), "mystic_shovel"),
				setup(new Item(), "cloud_pearl"),
				setup(new Item(), "shiny_stick"),
				setup(new Item(), "cloud_dust_blue"),
				setup(new Item(), "cloud_dust_yellow"),
				setup(new Item(), "condensed_cloud_dust_blue"),
				setup(new Item(), "condensed_cloud_dust_yellow"),
				setup(new Item(), "jar_with_swamp_gas"),
				setup(new Item(), "crystal_blend"),
				setup(new Item(), "heart"),
				setup(new Item(), "cotton_candy"),
				setup(new Item(), "bison_meat"),
				setup(new Item(), "cooked_bison_meat"),
				setup(new Item(), "jellyfish_tentacle"),
				setup(new Item(), "gooseberries"),
				setup(new Item(), "venom_sac"),
				setup(new Item(), "glass_jar"),
				setup(new Item(), "nectar_bowl"),
				setup(new Item(), "butterfly_common_1"),
				setup(new Item(), "butterfly_common_2"),
				setup(new Item(), "butterfly_common_moth"),
				setup(new Item(), "butterfly_illukini"),
				setup(new Item(), "butterfly_cloud_shimmer"),
				setup(new Item(), "butterfly_catcher"),
				setup(new Item(), "wings_piece"),
				setup(new Item(), "fungal_drug"),
				setup(new Item(), "bitten_cookie"),
				setup(new Item(), "candy_cane"),
				setup(new Item(), "christmas_chocolate"),
				setup(new Item(), "ice_creams"),
				setup(new Item(), "sweet_muffin"),
				setup(new Item(), "pot_with_living_flower"),
				setup(new Item(), "magnethium_pickaxe"),
				setup(new Item(), "magnethium_axe"),
				setup(new Item(), "magnethium_shovel"),
				setup(new Item(), "magnethium_sword"),
				setup(new Item(), "magnethium_hoe"),
				setup(new Item(), "crystal_pickaxe"),
				setup(new Item(), "frup"),
				setup(new Item(), "magic_beans")
		);

	}

	@SubscribeEvent
	public static void onRegisterBlocks(final RegistryEvent.Register<Block> event) {
		//TODO clean up the order of these, move them all into categories. Yes my minecraft OCD is acting up
		event.getRegistry().registerAll(
				setup(new BlockKatharianStone(), "katharian_stone"),
				setup(new BlockKathairianPortal(), "katharian_portal"),
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
				setup(new BlockKatharianOre(), "revenum_ore"),
				setup(new BlockKatharianOre(), "titanium_ore"),
				setup(new BlockKatharianSand(), "katharian_sand"),
				setup(new BlockSoftSand(), "soft_sand"),
				setup(new BlockKatharianCloud(), "blue_cloud"),
				setup(new BlockKatharianCloud(), "yellow_cloud"),
				setup(new Block(), "weathered_rock"),
				setup(new Block(), "mystic_planks"),
				setup(new Block(), "shiny_planks"),
				setup(new Block(), "soul_planks"),
				setup(new Block(), "katharian_cobblestone"),
				setup(new Block(), "katharian_stone_tiles"),
				setup(new Block(), "shiny_rock"),
				setup(new Block(), "katharian_stone_bricks"),
				setup(new Block(), "mud_block"),
				setup(new Block(), "mud_bricks"),
				setup(new BlockMagnethium(), "magnethium"),
				setup(new Block(), "iron_gold_block"),
				setup(new Block(), "hardened_weathered_rock"),
				setup(new Block(), "hardened_weathered_rock_bricks"),
				setup(new Block(), "hardened_weathered_rock_tiles"),
				setup(new BlockRefinedCloud(), "blue_cloud_refined"),
				setup(new BlockRefinedCloud(), "yellow_cloud_refined"),
				setup(new BlockCondensedCloud(), "blue_cloud_condensed"),
				setup(new BlockCondensedCloud(), "yellow_cloud_condensed"),
				setup(new BlockKatharianLog(), "mystic_log_stripped"),
				setup(new BlockKatharianLog(), "shiny_log_stripped"),
				setup(new BlockKatharianLog(), "soul_log_stripped"),

				setup(new BlockKatharianFence(), "mystic_wood_fence"),
				setup(new BlockKatharianFenceGate(), "mystic_wood_fence_gate"),
				setup(new BlockKatharianFence(), "shiny_wood_fence"),
				setup(new BlockKatharianFenceGate(), "shiny_wood_fence_gate"),
				setup(new BlockKatharianFence(), "soul_wood_fence"),
				setup(new BlockKatharianFenceGate(), "soul_wood_fence_gate"),

				setup(new BlockKatharianWall(), "katharian_stone_wall"),
				setup(new BlockKatharianWall(), "katharian_cobblestone_wall"),
				setup(new BlockKatharianWall(), "weathered_rock_wall"),
				setup(new BlockKatharianWall(), "hardened_weathered_rock_wall"),
				setup(new BlockKatharianWall(), "hardened_weathered_rock_bricks_wall"),
				setup(new BlockKatharianWall(), "mud_bricks_wall"),
				setup(new BlockKatharianWall(), "katharian_stone_bricks_wall"),

				setup(new BlockKatharianStairs(), "mystic_wood_stairs"),
				setup(new BlockKatharianStairs(), "shiny_wood_stairs"),
				setup(new BlockKatharianStairs(), "soul_wood_stairs"),
				setup(new BlockKatharianStairs(), "katharian_stone_stairs"),
				setup(new BlockKatharianStairs(), "katharian_cobblestone_stairs"),
				setup(new BlockKatharianStairs(), "weathered_rock_stairs"),
				setup(new BlockKatharianStairs(), "hardened_weathered_rock_stairs"),
				setup(new BlockKatharianStairs(), "hardened_weathered_rock_bricks_stairs"),
				setup(new BlockKatharianStairs(), "mud_bricks_stairs"),
				setup(new BlockKatharianStairs(), "katharian_stone_bricks_stairs"),

				setup(new BlockKatharianSlab(), "katharian_stone_slab"),
				setup(new BlockKatharianSlab(), "katharian_cobblestone_slab"),
				setup(new BlockKatharianSlab(), "katharian_stone_bricks_slab"),
				setup(new BlockKatharianSlab(), "mud_bricks_slab"),
				setup(new BlockKatharianSlab(), "mystic_wood_slab"),
				setup(new BlockKatharianSlab(), "shiny_wood_slab"),
				setup(new BlockKatharianSlab(), "soul_wood_slab"),
				setup(new BlockKatharianSlab(), "weathered_rock_slab"),
				setup(new BlockKatharianSlab(), "hardened_weathered_rock_slab"),
				setup(new BlockKatharianSlab(), "hardened_weathered_rock_bricks_slab"),

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

				setup(new BlockKatharianDoors(), "mystic_wood_doors"),
				setup(new BlockKatharianDoors(), "shiny_wood_doors"),
				setup(new BlockKatharianDoors(), "soul_wood_doors"),
				setup(new BlockKatharianTrapdoor(), "mystic_wood_trapdoor"),
				setup(new BlockKatharianTrapdoor(), "shiny_wood_trapdoor"),
				setup(new BlockKatharianTrapdoor(), "soul_wood_trapdoor"),

				setup(new BlockKatharianFungi(), "katharian_fungi"),
				setup(new BlockGooseberry(), "gooseberry_bush"),
				setup(new BlockKatharianSucculent(), "katharian_succulent"),
				setup(new BlockSteppedSucculent(), "stepped_succulent"),
				setup(new BlockKatharianPlant(), "vilyria"),
				setup(new BlockGlowVines(), "glowvines"),
				setup(new BlockJadeVines(), "jade_vines"),
				setup(new BlockKatharianDeadGrass(), "katharian_dead_grass"),

				setup(new Block(), "mystic_gem_block"),

				setup(new BlockMagicBeans(), "magic_beans"),
				setup(new BlockBaurble(), "baurble"),
				setup(new BlockButterflyFlower(), "butterfly_flower"),
				setup(new BlockSolisCrystals(), "solis_crystals"),
				setup(new BlockCloudFlower(), "blue_cloud_flower"),
				setup(new BlockCloudFlower(), "yellow_cloud_flower"),
				setup(new BlockSnowdropCyprepedium(), "snowdrop_cyprepedium"),
				setup(new BlockFluoFungi(), "fluo_fungi"),
				setup(new BlockLayeredSand(), "layered_sand"),
				setup(new Block(), "katharian_sandstone"), //TODO: reorganise
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
