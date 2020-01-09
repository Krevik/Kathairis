package io.github.krevik.kathairis.init;

import io.github.krevik.kathairis.item.*;
import io.github.krevik.kathairis.util.ModReference;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.Rarity;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;

import static io.github.krevik.kathairis.util.ModReference.MOD_ID;
import static io.github.krevik.kathairis.util.ModUtil._null;

/**
 * @author Cadiboo
 */
@ObjectHolder(MOD_ID)
@Mod.EventBusSubscriber(modid = ModReference.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class ModItems {

	public static final Item MYSTIC_GEM = _null();
	public static final Item TITANIUM_INGOT = _null();
	public static final Item TITANIUM_ROD = _null();
	public static final Item REVENUM_INGOT = _null();
	public static final Item CLOUD_ESSENCE = _null();
	public static final Item HOWLER_FUR = _null();
	public static final Item CRYSTAL_CLUSTER = _null();
	public static final Item MAGNETHIUM_SHARD = _null();
	public static final Item CRYSTAL_SHARD_YELLOW = _null();
	public static final Item CRYSTAL_SHARD_BLUE = _null();
	public static final Item CRYSTAL_SHARD_VIOLET = _null();
	public static final Item TORTOISE_SHELL = _null();
	public static final Item BUTTERFLY_FLOWER_NECTAR = _null();
	public static final Item IRON_GOLD_INGOT = _null();
	public static final Item TITANIUM_HELMET = _null();
	public static final Item TITANIUM_CHESTPLATE = _null();
	public static final Item TITANIUM_LEGGINGS = _null();
	public static final Item TITANIUM_BOOTS = _null();
	public static final Item TITANIUM_SWORD = _null();
	public static final Item TITANIUM_HOE = _null();
	public static final Item TITANIUM_AXE = _null();
	public static final Item TITANIUM_PICKAXE = _null();
	public static final Item TITANIUM_SHOVEL = _null();
	public static final Item REVENUM_HELMET = _null();
	public static final Item REVENUM_CHESTPLATE = _null();
	public static final Item REVENUM_LEGGINGS = _null();
	public static final Item REVENUM_BOOTS = _null();
	public static final Item REVENUM_SWORD = _null();
	public static final Item REVENUM_HOE = _null();
	public static final Item REVENUM_AXE = _null();
	public static final Item REVENUM_PICKAXE = _null();
	public static final Item REVENUM_SHOVEL = _null();
	public static final Item SKYRAY_FEATHER = _null();
	public static final Item SOLIS_CRYSTAL = _null();
	public static final Item MYSTIC_HELMET = _null();
	public static final Item MYSTIC_CHESTPLATE = _null();
	public static final Item MYSTIC_LEGGINGS = _null();
	public static final Item MYSTIC_BOOTS = _null();
	public static final Item MYSTIC_SWORD = _null();
	public static final Item MYSTIC_HOE = _null();
	public static final Item MYSTIC_AXE = _null();
	public static final Item MYSTIC_PICKAXE = _null();
	public static final Item MYSTIC_SHOVEL = _null();
	public static final Item CLOUD_PEARL = _null();
	public static final Item SHINY_STICK = _null();
	public static final Item CLOUD_DUST_BLUE = _null();
	public static final Item CLOUD_DUST_YELLOW = _null();
	public static final Item CONDENSED_CLOUD_DUST_BLUE = _null();
	public static final Item CONDENSED_CLOUD_DUST_YELLOW = _null();
	public static final Item JAR_WITH_SWAMP_GAS = _null();
	public static final Item CRYSTAL_BLEND = _null();
	public static final Item HEART = _null();
	public static final Item COTTON_CANDY = _null();
	public static final Item BISON_MEAT = _null();
	public static final Item COOKED_BISON_MEAT = _null();
	public static final Item JELLYFISH_TENTACLE = _null();
	public static final Item GOOSEBERRIES = _null();
	public static final Item VENOM_SAC = _null();
	public static final Item GLASS_JAR = _null();
	public static final Item NECTAR_BOWL = _null();
	public static final Item BUTTERFLY_COMMON_1 = _null();
	public static final Item BUTTERFLY_COMMON_2 = _null();
	public static final Item BUTTERFLY_COMMON_MOTH = _null();
	public static final Item BUTTERFLY_ILLUKINI = _null();
	public static final Item BUTTERFLY_CLOUD_SHIMMER = _null();
	public static final Item BUTTERFLY_CATCHER = _null();
	public static final Item WINGS_PIECE = _null();
	public static final Item FUNGAL_DRUG = _null();
	public static final Item BITTEN_COOKIE = _null();
	public static final Item CANDY_CANE = _null();
	public static final Item CHRISTMAS_CHOCOLATE = _null();
	public static final Item ICE_CREAMS = _null();
	public static final Item SWEET_MUFFIN = _null();
	public static final Item POT_WITH_LIVING_FLOWER = _null();
	public static final Item MAGNETHIUM_PICKAXE = _null();
	public static final Item MAGNETHIUM_AXE = _null();
	public static final Item MAGNETHIUM_SHOVEL = _null();
	public static final Item MAGNETHIUM_SWORD = _null();
	public static final Item MAGNETHIUM_HOE = _null();
	public static final Item CRYSTAL_PICKAXE = _null();
	public static final Item FRUP = _null();
	public static final Item MAGIC_BEANS = _null();
	public static final Item MYSTIC_WAND = _null();
	public static final Item MINERAL_FRUIT = _null();

	@SubscribeEvent
	public static void onRegisterItems(final RegistryEvent.Register<Item> event) {
		final IForgeRegistry<Item> registry = event.getRegistry();
		registry.registerAll(
				RegistryHelper.setup(new ItemMysticGem(), "mystic_gem"),
				RegistryHelper.setup(new Item(new Item.Properties().group(ModItemGroups.MATERIALS)), "titanium_ingot"),
				RegistryHelper.setup(new Item(new Item.Properties().group(ModItemGroups.MATERIALS)), "titanium_rod"),
				RegistryHelper.setup(new Item(new Item.Properties().group(ModItemGroups.MATERIALS)), "revenum_ingot"),
				RegistryHelper.setup(new Item(new Item.Properties().group(ModItemGroups.MISCELLANEOUS).rarity(Rarity.EPIC)), "cloud_essence"),
				RegistryHelper.setup(new Item(new Item.Properties().group(ModItemGroups.MISCELLANEOUS).rarity(Rarity.RARE)), "howler_fur"),
				RegistryHelper.setup(new Item(new Item.Properties().group(ModItemGroups.MATERIALS).rarity(Rarity.RARE)), "crystal_cluster"),
				RegistryHelper.setup(new Item(new Item.Properties().group(ModItemGroups.MATERIALS).rarity(Rarity.RARE)), "magnethium_shard"),
				RegistryHelper.setup(new Item(new Item.Properties().group(ModItemGroups.MISCELLANEOUS)), "crystal_shard_yellow"),
				RegistryHelper.setup(new Item(new Item.Properties().group(ModItemGroups.MISCELLANEOUS)), "crystal_shard_blue"),
				RegistryHelper.setup(new Item(new Item.Properties().group(ModItemGroups.MISCELLANEOUS)), "crystal_shard_violet"),
				RegistryHelper.setup(new Item(new Item.Properties().group(ModItemGroups.MISCELLANEOUS)), "tortoise_shell"),
				RegistryHelper.setup(new Item(new Item.Properties().group(ModItemGroups.MISCELLANEOUS)), "butterfly_flower_nectar"),
				RegistryHelper.setup(new Item(new Item.Properties().group(ModItemGroups.MATERIALS)), "iron_gold_ingot"),
				RegistryHelper.setup(new ItemKathairisArmor(ModArmorMaterials.TITANIUM, EquipmentSlotType.HEAD), "titanium_helmet"),
				RegistryHelper.setup(new ItemKathairisArmor(ModArmorMaterials.TITANIUM, EquipmentSlotType.CHEST), "titanium_chestplate"),
				RegistryHelper.setup(new ItemKathairisArmor(ModArmorMaterials.TITANIUM, EquipmentSlotType.LEGS), "titanium_leggings"),
				RegistryHelper.setup(new ItemKathairisArmor(ModArmorMaterials.TITANIUM, EquipmentSlotType.FEET), "titanium_boots"),
				RegistryHelper.setup(new ItemKathairisSword(ModItemTiers.TITANIUM), "titanium_sword"),
				RegistryHelper.setup(new ItemKathairisHoe(ModItemTiers.TITANIUM), "titanium_hoe"),
				RegistryHelper.setup(new ItemKathairisAxe(ModItemTiers.TITANIUM), "titanium_axe"),
				RegistryHelper.setup(new ItemKathairisPickaxe(ModItemTiers.TITANIUM), "titanium_pickaxe"),
				RegistryHelper.setup(new ItemKathairisShovel(ModItemTiers.TITANIUM), "titanium_shovel"),
				RegistryHelper.setup(new ItemKathairisArmor(ModArmorMaterials.REVENUM, EquipmentSlotType.HEAD), "revenum_helmet"),
				RegistryHelper.setup(new ItemKathairisArmor(ModArmorMaterials.REVENUM, EquipmentSlotType.CHEST), "revenum_chestplate"),
				RegistryHelper.setup(new ItemKathairisArmor(ModArmorMaterials.REVENUM, EquipmentSlotType.LEGS), "revenum_leggings"),
				RegistryHelper.setup(new ItemKathairisArmor(ModArmorMaterials.REVENUM, EquipmentSlotType.FEET), "revenum_boots"),
				RegistryHelper.setup(new ItemKathairisSword(ModItemTiers.REVENUM), "revenum_sword"),
				RegistryHelper.setup(new ItemKathairisHoe(ModItemTiers.REVENUM), "revenum_hoe"),
				RegistryHelper.setup(new ItemKathairisAxe(ModItemTiers.REVENUM), "revenum_axe"),
				RegistryHelper.setup(new ItemKathairisPickaxe(ModItemTiers.REVENUM), "revenum_pickaxe"),
				RegistryHelper.setup(new ItemKathairisShovel(ModItemTiers.REVENUM), "revenum_shovel"),
				RegistryHelper.setup(new Item(new Item.Properties().group(ModItemGroups.MISCELLANEOUS).rarity(Rarity.EPIC)), "skyray_feather"),
				RegistryHelper.setup(new Item(new Item.Properties().group(ModItemGroups.MISCELLANEOUS).rarity(Rarity.EPIC)), "solis_crystal"),
				RegistryHelper.setup(new ItemKathairisArmor(ModArmorMaterials.MYSTIC, EquipmentSlotType.HEAD), "mystic_helmet"),
				RegistryHelper.setup(new ItemKathairisArmor(ModArmorMaterials.MYSTIC, EquipmentSlotType.CHEST), "mystic_chestplate"),
				RegistryHelper.setup(new ItemKathairisArmor(ModArmorMaterials.MYSTIC, EquipmentSlotType.LEGS), "mystic_leggings"),
				RegistryHelper.setup(new ItemKathairisArmor(ModArmorMaterials.MYSTIC, EquipmentSlotType.FEET), "mystic_boots"),
				RegistryHelper.setup(new ItemKathairisSword(ModItemTiers.MYSTIC), "mystic_sword"),
				RegistryHelper.setup(new ItemKathairisHoe(ModItemTiers.MYSTIC), "mystic_hoe"),
				RegistryHelper.setup(new ItemKathairisAxe(ModItemTiers.MYSTIC), "mystic_axe"),
				RegistryHelper.setup(new ItemKathairisPickaxe(ModItemTiers.MYSTIC), "mystic_pickaxe"),
				RegistryHelper.setup(new ItemKathairisShovel(ModItemTiers.MYSTIC), "mystic_shovel"),
				RegistryHelper.setup(new Item(new Item.Properties().group(ModItemGroups.MISCELLANEOUS).rarity(Rarity.EPIC)), "cloud_pearl"),
				RegistryHelper.setup(new Item(new Item.Properties().group(ModItemGroups.MATERIALS)), "shiny_stick"),
				RegistryHelper.setup(new Item(new Item.Properties().group(ModItemGroups.MISCELLANEOUS)), "cloud_dust_blue"),
				RegistryHelper.setup(new Item(new Item.Properties().group(ModItemGroups.MISCELLANEOUS)), "cloud_dust_yellow"),
				RegistryHelper.setup(new Item(new Item.Properties().group(ModItemGroups.MISCELLANEOUS)), "condensed_cloud_dust_blue"),
				RegistryHelper.setup(new Item(new Item.Properties().group(ModItemGroups.MISCELLANEOUS)), "condensed_cloud_dust_yellow"),
				RegistryHelper.setup(new Item(new Item.Properties().group(ModItemGroups.MISCELLANEOUS)), "jar_with_swamp_gas"),
				RegistryHelper.setup(new Item(new Item.Properties().group(ModItemGroups.MISCELLANEOUS)), "crystal_blend"),
				RegistryHelper.setup(new Item(new Item.Properties().food(ModFoods.HEART).group(ModItemGroups.FOOD)), "heart"),
				RegistryHelper.setup(new Item(new Item.Properties().food(ModFoods.COTTON_CANDY).group(ModItemGroups.FOOD)), "cotton_candy"),
				RegistryHelper.setup(new Item(new Item.Properties().food(ModFoods.BISON_MEAT).group(ModItemGroups.FOOD)), "bison_meat"),
				RegistryHelper.setup(new Item(new Item.Properties().food(ModFoods.COOKED_BISON_MEAT).group(ModItemGroups.FOOD)), "cooked_bison_meat"),
				RegistryHelper.setup(new Item(new Item.Properties().group(ModItemGroups.MISCELLANEOUS).rarity(Rarity.RARE)), "jellyfish_tentacle"),
				RegistryHelper.setup(new BlockNamedItem(ModBlocks.GOOSEBERRY_BUSH, new Item.Properties().food(ModFoods.GOOSEBERRY).group(ModItemGroups.FOOD)), "gooseberries"),
				RegistryHelper.setup(new Item(new Item.Properties().group(ModItemGroups.MISCELLANEOUS)), "venom_sac"),
				RegistryHelper.setup(new Item(new Item.Properties().group(ModItemGroups.MISCELLANEOUS)), "glass_jar"),
				RegistryHelper.setup(new Item(new Item.Properties().group(ModItemGroups.FOOD).food(ModFoods.NECTAR_BOWL)), "nectar_bowl"),
				RegistryHelper.setup(new Item(new Item.Properties().group(ModItemGroups.MISCELLANEOUS).rarity(Rarity.COMMON)), "butterfly_common_1"),
				RegistryHelper.setup(new Item(new Item.Properties().group(ModItemGroups.MISCELLANEOUS).rarity(Rarity.COMMON)), "butterfly_common_2"),
				RegistryHelper.setup(new Item(new Item.Properties().group(ModItemGroups.MISCELLANEOUS).rarity(Rarity.COMMON)), "butterfly_common_moth"),
				RegistryHelper.setup(new Item(new Item.Properties().group(ModItemGroups.MISCELLANEOUS).rarity(Rarity.UNCOMMON)), "butterfly_illukini"),
				RegistryHelper.setup(new Item(new Item.Properties().group(ModItemGroups.MISCELLANEOUS).rarity(Rarity.RARE)), "butterfly_cloud_shimmer"),
				RegistryHelper.setup(new Item(new Item.Properties().group(ModItemGroups.MISCELLANEOUS)), "butterfly_catcher"),
				RegistryHelper.setup(new Item(new Item.Properties().group(ModItemGroups.MISCELLANEOUS)), "wings_piece"),
				RegistryHelper.setup(new Item(new Item.Properties().group(ModItemGroups.FOOD).food(ModFoods.FUNGAL_DRUG)), "fungal_drug"),
				RegistryHelper.setup(new Item(new Item.Properties().food(ModFoods.EASTER_FOOD).group(ModItemGroups.FOOD)), "bitten_cookie"),
				RegistryHelper.setup(new Item(new Item.Properties().food(ModFoods.EASTER_FOOD).group(ModItemGroups.FOOD)), "candy_cane"),
				RegistryHelper.setup(new Item(new Item.Properties().food(ModFoods.EASTER_FOOD).group(ModItemGroups.FOOD)), "christmas_chocolate"),
				RegistryHelper.setup(new Item(new Item.Properties().food(ModFoods.EASTER_FOOD).group(ModItemGroups.FOOD)), "ice_creams"),
				RegistryHelper.setup(new Item(new Item.Properties().food(ModFoods.EASTER_FOOD).group(ModItemGroups.FOOD)), "sweet_muffin"),
				RegistryHelper.setup(new Item(new Item.Properties().group(ModItemGroups.MISCELLANEOUS)), "pot_with_living_flower"),
				RegistryHelper.setup(new ItemKathairisPickaxe(ModItemTiers.MAGNETHIUM), "magnethium_pickaxe"),
				RegistryHelper.setup(new ItemKathairisAxe(ModItemTiers.MAGNETHIUM), "magnethium_axe"),
				RegistryHelper.setup(new ItemKathairisShovel(ModItemTiers.MAGNETHIUM), "magnethium_shovel"),
				RegistryHelper.setup(new ItemKathairisSword(ModItemTiers.MAGNETHIUM), "magnethium_sword"),
				RegistryHelper.setup(new ItemKathairisHoe(ModItemTiers.MAGNETHIUM), "magnethium_hoe"),
				RegistryHelper.setup(new ItemKathairisPickaxe(ModItemTiers.CRYSTAL), "crystal_pickaxe"),
				RegistryHelper.setup(new BlockNamedItem(ModBlocks.FRUP_PLANT, new Item.Properties().food(ModFoods.FRUP).group(ModItemGroups.FOOD)), "frup"),
				RegistryHelper.setup(new BlockNamedItem(ModBlocks.MAGIC_BEANS, new Item.Properties().food(ModFoods.MAGIC_BEANS).group(ModItemGroups.FOOD)), "magic_beans"),
				RegistryHelper.setup(new ItemMysticWand(ModItemGroups.WEAPONS),"mystic_wand"),
				RegistryHelper.setup(new BlockNamedItem(ModBlocks.BRINE_PUSTULE, new Item.Properties().food(ModFoods.MINERAL_FRUIT).group(ModItemGroups.FOOD)),"mineral_fruit")
		);
		//ModEntities.registerEggs(event);
		for (final Block block : ModUtil.getModEntries(ForgeRegistries.BLOCKS)) {
			if(IItemGroupProvider.class.isAssignableFrom(block.getClass())){
				registry.register(RegistryHelper.setup(new BlockItem(block, new Item.Properties().group(((IItemGroupProvider)(block)).getItemGroup())), block.getRegistryName()));
			}
		}

	}

}
