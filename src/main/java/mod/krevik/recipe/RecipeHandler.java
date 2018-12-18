package mod.krevik.recipe;

import mod.krevik.KCore;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

public class RecipeHandler {
	
	public static void addRecipes() {
		addGemsRecipes();
		addBasicSmelting();
		addToolsAndSwords();
		addArmorSets();
		addOresBlocks();
		addOther();
		addFood();
		registerOreDictionary();
	}

	private static void registerOreDictionary(){
		OreDictionary.registerOre("stickWood",new ItemStack(KCore.ShinyStick));
		OreDictionary.registerOre("plankWood",new ItemStack(KCore.MysticPlanks));
		OreDictionary.registerOre("plankWood",new ItemStack(KCore.SoulPlanks));
	}

	private static void addGemsRecipes() {
		GameRegistry.addShapedRecipe(new ResourceLocation(KCore.MODID,"Mystic Gem1"),new ResourceLocation("Gems1"),new ItemStack(KCore.MysticGem), "#Y#", "YXY", "#Y#", Character.valueOf('#'), Items.ENDER_PEARL,Character.valueOf('X'),Items.DIAMOND,Character.valueOf('Y'),new ItemStack(Items.DYE, 1, 4));
		GameRegistry.addShapedRecipe(new ResourceLocation(KCore.MODID,"Mystic Gem2"),new ResourceLocation("Gems2"),new ItemStack(KCore.MysticGem),"X#", Character.valueOf('#'), KCore.MysticFungus,Character.valueOf('X'),Items.DIAMOND);

	}
	private static void addBasicSmelting() {
		GameRegistry.addSmelting(KCore.TitaniumOre, new ItemStack(KCore.TitaniumIngot), 2.0f);
		GameRegistry.addSmelting(KCore.WeatheredRock, new ItemStack(Item.getItemFromBlock(KCore.HardenedWeatheredRock)), 0.1f);
		GameRegistry.addSmelting(new ItemStack(KCore.BlueCloudDust,4), new ItemStack(KCore.CondensedBlueCloudDust), 0.3f);
		GameRegistry.addSmelting(new ItemStack(KCore.YellowCloudDust,4), new ItemStack(KCore.CondensedYellowCloudDust), 0.3f);
		GameRegistry.addSmelting(KCore.RevenumOre, new ItemStack(KCore.RevenumIngot), 25.0f);
		GameRegistry.addSmelting(KCore.MudBlock, new ItemStack(KCore.MudBricks), 0.3f);
		GameRegistry.addSmelting(KCore.CrystalsCluster, new ItemStack(KCore.CrystalBlend,3), 1.0f);
		GameRegistry.addSmelting(KCore.Mythic_Cobblestone, new ItemStack(KCore.MythicStone,1), 0.2f);
		GameRegistry.addSmelting(KCore.BisonMeat, new ItemStack(KCore.CookedBisonMeat), 0.2f);
		GameRegistry.addSmelting(KCore.BlueCloud, new ItemStack(KCore.Refined_Cloud_Blue), 0.5f);
		GameRegistry.addSmelting(KCore.YellowCloud, new ItemStack(KCore.Refined_Cloud_Yellow), 0.5f);
		GameRegistry.addSmelting(KCore.MythicStone, new ItemStack(KCore.Mythic_Stone_Bricks), 0.5f);


	}
	private static void addToolsAndSwords() {
		GameRegistry.addShapedRecipe(new ResourceLocation(KCore.MODID, "Mystic Sword1"),new ResourceLocation("Swords"),new ItemStack(KCore.MysticSword)," X ", " X ", " C ", Character.valueOf('X'), KCore.MysticGem,Character.valueOf('C'),KCore.TitaniumRod);
		GameRegistry.addShapedRecipe(new ResourceLocation(KCore.MODID, "Mystic Sword2"),new ResourceLocation("Swords"),new ItemStack(KCore.MysticSword),"X  ", "X  ", "C  ", Character.valueOf('X'), KCore.MysticGem,Character.valueOf('C'),KCore.TitaniumRod);
		GameRegistry.addShapedRecipe(new ResourceLocation(KCore.MODID, "Mystic Sword3"),new ResourceLocation("Swords"),new ItemStack(KCore.MysticSword),"  X", "  X", "  C", Character.valueOf('X'), KCore.MysticGem,Character.valueOf('C'),KCore.TitaniumRod);
		GameRegistry.addShapedRecipe(new ResourceLocation(KCore.MODID, "Mystic Axe"),new ResourceLocation("Axes"),new ItemStack(KCore.MysticAxe),"XX ", "XC ", " C ", Character.valueOf('X'), KCore.MysticGem,Character.valueOf('C'),KCore.TitaniumRod);
		GameRegistry.addShapedRecipe(new ResourceLocation(KCore.MODID, "Mystic Pickaxe"),new ResourceLocation("Pickaxes"),new ItemStack(KCore.MysticPickaxe),"XXX", " C ", " C ", Character.valueOf('X'), KCore.MysticGem,Character.valueOf('C'),KCore.TitaniumRod);
		GameRegistry.addShapedRecipe(new ResourceLocation(KCore.MODID, "Mystic Hoe1"),new ResourceLocation("Hoes"),new ItemStack(KCore.MysticHoe)," XX", " C ", " C ", Character.valueOf('X'), KCore.MysticGem,Character.valueOf('C'),KCore.TitaniumRod);
		GameRegistry.addShapedRecipe(new ResourceLocation(KCore.MODID, "Mystic Hoe2"),new ResourceLocation("Hoes"),new ItemStack(KCore.MysticHoe),"XX ", " C ", " C ", Character.valueOf('X'), KCore.MysticGem,Character.valueOf('C'),KCore.TitaniumRod);
		GameRegistry.addShapedRecipe(new ResourceLocation(KCore.MODID, "Mystic Hoe3"),new ResourceLocation("Hoes"),new ItemStack(KCore.MysticHoe),"XX ", "C  ", "C  ", Character.valueOf('X'), KCore.MysticGem,Character.valueOf('C'),KCore.TitaniumRod);
		GameRegistry.addShapedRecipe(new ResourceLocation(KCore.MODID, "Mystic Hoe4"),new ResourceLocation("Hoes"),new ItemStack(KCore.MysticHoe)," XX", "  C", "  C", Character.valueOf('X'), KCore.MysticGem,Character.valueOf('C'),KCore.TitaniumRod);
		GameRegistry.addShapedRecipe(new ResourceLocation(KCore.MODID, "Mystic Shovel1"),new ResourceLocation("Shovels"),new ItemStack(KCore.MysticShovel)," X ", " C ", " C ", Character.valueOf('X'), KCore.MysticGem,Character.valueOf('C'),KCore.TitaniumRod);
		GameRegistry.addShapedRecipe(new ResourceLocation(KCore.MODID, "Mystic Shovel2"),new ResourceLocation("Shovels"),new ItemStack(KCore.MysticShovel),"X  ", "C  ", "C  ", Character.valueOf('X'), KCore.MysticGem,Character.valueOf('C'),KCore.TitaniumRod);
		GameRegistry.addShapedRecipe(new ResourceLocation(KCore.MODID, "Mystic Shovel3"),new ResourceLocation("Shovels"),new ItemStack(KCore.MysticShovel),"  X", "  C", "  C", Character.valueOf('X'), KCore.MysticGem,Character.valueOf('C'),KCore.TitaniumRod);
		GameRegistry.addShapedRecipe(new ResourceLocation(KCore.MODID, "Titanium Sword1"),new ResourceLocation("Swords"),new ItemStack(KCore.TitaniumSword)," X ", " X ", " C ", Character.valueOf('X'), KCore.TitaniumIngot,Character.valueOf('C'),KCore.TitaniumRod);
		GameRegistry.addShapedRecipe(new ResourceLocation(KCore.MODID, "Titanium Sword2"),new ResourceLocation("Swords"),new ItemStack(KCore.TitaniumSword),"X  ", "X  ", "C  ", Character.valueOf('X'), KCore.TitaniumIngot,Character.valueOf('C'),KCore.TitaniumRod);
		GameRegistry.addShapedRecipe(new ResourceLocation(KCore.MODID, "Titanium Sword3"),new ResourceLocation("Swords"),new ItemStack(KCore.TitaniumSword),"  X", "  X", "  C", Character.valueOf('X'), KCore.TitaniumIngot,Character.valueOf('C'),KCore.TitaniumRod);
		GameRegistry.addShapedRecipe(new ResourceLocation(KCore.MODID, "Titanium Axe"),new ResourceLocation("Axes"),new ItemStack(KCore.TitaniumAxe),"XX ", "XC ", " C ", Character.valueOf('X'), KCore.TitaniumIngot,Character.valueOf('C'),KCore.TitaniumRod);
		GameRegistry.addShapedRecipe(new ResourceLocation(KCore.MODID, "Titanium Pickaxe"),new ResourceLocation("Pickaxes"),new ItemStack(KCore.TitaniumPickaxe),"XXX", " C ", " C ", Character.valueOf('X'), KCore.TitaniumIngot,Character.valueOf('C'),KCore.TitaniumRod);
		GameRegistry.addShapedRecipe(new ResourceLocation(KCore.MODID, "Titanium Hoe1"),new ResourceLocation("Hoes"),new ItemStack(KCore.TitaniumHoe)," XX", " C ", " C ", Character.valueOf('X'), KCore.TitaniumIngot,Character.valueOf('C'),KCore.TitaniumRod);
		GameRegistry.addShapedRecipe(new ResourceLocation(KCore.MODID, "Titanium Hoe2"),new ResourceLocation("Hoes"),new ItemStack(KCore.TitaniumHoe),"XX ", " C ", " C ", Character.valueOf('X'), KCore.TitaniumIngot,Character.valueOf('C'),KCore.TitaniumRod);
		GameRegistry.addShapedRecipe(new ResourceLocation(KCore.MODID, "Titanium Hoe3"),new ResourceLocation("Hoes"),new ItemStack(KCore.TitaniumHoe),"XX ", "C  ", "C  ", Character.valueOf('X'), KCore.TitaniumIngot,Character.valueOf('C'),KCore.TitaniumRod);
		GameRegistry.addShapedRecipe(new ResourceLocation(KCore.MODID, "Titanium Hoe4"),new ResourceLocation("Hoes"),new ItemStack(KCore.TitaniumHoe)," XX", "  C", "  C", Character.valueOf('X'), KCore.TitaniumIngot,Character.valueOf('C'),KCore.TitaniumRod);
		GameRegistry.addShapedRecipe(new ResourceLocation(KCore.MODID, "Titanium Shovel1"),new ResourceLocation("Shovels"),new ItemStack(KCore.TitaniumShovel)," X ", " C ", " C ", Character.valueOf('X'), KCore.TitaniumIngot,Character.valueOf('C'),KCore.TitaniumRod);
		GameRegistry.addShapedRecipe(new ResourceLocation(KCore.MODID, "Titanium Shovel2"),new ResourceLocation("Shovels"),new ItemStack(KCore.TitaniumShovel),"X  ", "C  ", "C  ", Character.valueOf('X'), KCore.TitaniumIngot,Character.valueOf('C'),KCore.TitaniumRod);
		GameRegistry.addShapedRecipe(new ResourceLocation(KCore.MODID, "Titanium Shovel3"),new ResourceLocation("Shovels"),new ItemStack(KCore.TitaniumShovel),"  X", "  C", "  C", Character.valueOf('X'), KCore.TitaniumIngot,Character.valueOf('C'),KCore.TitaniumRod);
		GameRegistry.addShapedRecipe(new ResourceLocation(KCore.MODID, "Revenum Sword1"),new ResourceLocation("Swords"),new ItemStack(KCore.RevenumSword)," X ", " X ", " C ", Character.valueOf('X'), KCore.RevenumIngot,Character.valueOf('C'),KCore.TitaniumRod);
		GameRegistry.addShapedRecipe(new ResourceLocation(KCore.MODID, "Revenum Sword2"),new ResourceLocation("Swords"),new ItemStack(KCore.RevenumSword),"X  ", "X  ", "C  ", Character.valueOf('X'), KCore.RevenumIngot,Character.valueOf('C'),KCore.TitaniumRod);
		GameRegistry.addShapedRecipe(new ResourceLocation(KCore.MODID, "Revenum Sword3"),new ResourceLocation("Swords"),new ItemStack(KCore.RevenumSword),"  X", "  X", "  C", Character.valueOf('X'), KCore.RevenumIngot,Character.valueOf('C'),KCore.TitaniumRod);
		GameRegistry.addShapedRecipe(new ResourceLocation(KCore.MODID, "Revenum Axe"),new ResourceLocation("Axes"),new ItemStack(KCore.RevenumAxe),"XX ", "XC ", " C ", Character.valueOf('X'), KCore.RevenumIngot,Character.valueOf('C'),KCore.TitaniumRod);
		GameRegistry.addShapedRecipe(new ResourceLocation(KCore.MODID, "Revenum Pickaxe"),new ResourceLocation("Pickaxes"),new ItemStack(KCore.RevenumPickaxe),"XXX", " C ", " C ", Character.valueOf('X'), KCore.RevenumIngot,Character.valueOf('C'),KCore.TitaniumRod);

		GameRegistry.addShapedRecipe(new ResourceLocation(KCore.MODID, "Crystal Pickaxe"),new ResourceLocation("Pickaxes"),new ItemStack(KCore.CrystalPickaxe),"XXX", " C ", " C ", Character.valueOf('X'), KCore.CrystalsCluster,Character.valueOf('C'),KCore.TitaniumRod);
				
		GameRegistry.addShapedRecipe(new ResourceLocation(KCore.MODID, "Magnethium Sword1"),new ResourceLocation("Swords"),new ItemStack(KCore.Magnethium_Sword)," X ", " X ", " C ", Character.valueOf('X'), KCore.Magnethium_Shard,Character.valueOf('C'),KCore.TitaniumRod);
		GameRegistry.addShapedRecipe(new ResourceLocation(KCore.MODID, "Magnethium Sword2"),new ResourceLocation("Swords"),new ItemStack(KCore.Magnethium_Sword),"X  ", "X  ", "C  ", Character.valueOf('X'), KCore.Magnethium_Shard,Character.valueOf('C'),KCore.TitaniumRod);
		GameRegistry.addShapedRecipe(new ResourceLocation(KCore.MODID, "Magnethium Sword3"),new ResourceLocation("Swords"),new ItemStack(KCore.Magnethium_Sword),"  X", "  X", "  C", Character.valueOf('X'), KCore.Magnethium_Shard,Character.valueOf('C'),KCore.TitaniumRod);
		GameRegistry.addShapedRecipe(new ResourceLocation(KCore.MODID, "Magnethium Axe"),new ResourceLocation("Axes"),new ItemStack(KCore.Magnethium_Axe),"XX ", "XC ", " C ", Character.valueOf('X'), KCore.Magnethium_Shard,Character.valueOf('C'),KCore.TitaniumRod);
		GameRegistry.addShapedRecipe(new ResourceLocation(KCore.MODID, "Magnethium Pickaxe"),new ResourceLocation("Pickaxes"),new ItemStack(KCore.Magnethium_Pickaxe),"XXX", " C ", " C ", Character.valueOf('X'), KCore.Magnethium_Shard,Character.valueOf('C'),KCore.TitaniumRod);
		GameRegistry.addShapedRecipe(new ResourceLocation(KCore.MODID, "Magnethium Hoe1"),new ResourceLocation("Hoes"),new ItemStack(KCore.Magnethium_Hoe)," XX", " C ", " C ", Character.valueOf('X'), KCore.Magnethium_Shard,Character.valueOf('C'),KCore.TitaniumRod);
		GameRegistry.addShapedRecipe(new ResourceLocation(KCore.MODID, "Magnethium Hoe2"),new ResourceLocation("Hoes"),new ItemStack(KCore.Magnethium_Hoe),"XX ", " C ", " C ", Character.valueOf('X'), KCore.Magnethium_Shard,Character.valueOf('C'),KCore.TitaniumRod);
		GameRegistry.addShapedRecipe(new ResourceLocation(KCore.MODID, "Magnethium Hoe3"),new ResourceLocation("Hoes"),new ItemStack(KCore.Magnethium_Hoe),"XX ", "C  ", "C  ", Character.valueOf('X'), KCore.Magnethium_Shard,Character.valueOf('C'),KCore.TitaniumRod);
		GameRegistry.addShapedRecipe(new ResourceLocation(KCore.MODID, "Magnethium Hoe4"),new ResourceLocation("Hoes"),new ItemStack(KCore.Magnethium_Hoe)," XX", "  C", "  C", Character.valueOf('X'), KCore.Magnethium_Shard,Character.valueOf('C'),KCore.TitaniumRod);
		GameRegistry.addShapedRecipe(new ResourceLocation(KCore.MODID, "Magnethium Shovel1"),new ResourceLocation("Shovels"),new ItemStack(KCore.Magnethium_Shovel)," X ", " C ", " C ", Character.valueOf('X'), KCore.Magnethium_Shard,Character.valueOf('C'),KCore.TitaniumRod);
		GameRegistry.addShapedRecipe(new ResourceLocation(KCore.MODID, "Magnethium Shovel2"),new ResourceLocation("Shovels"),new ItemStack(KCore.Magnethium_Shovel),"X  ", "C  ", "C  ", Character.valueOf('X'), KCore.Magnethium_Shard,Character.valueOf('C'),KCore.TitaniumRod);
		GameRegistry.addShapedRecipe(new ResourceLocation(KCore.MODID, "Magnethium Shovel3"),new ResourceLocation("Shovels"),new ItemStack(KCore.Magnethium_Shovel),"  X", "  C", "  C", Character.valueOf('X'), KCore.Magnethium_Shard,Character.valueOf('C'),KCore.TitaniumRod);
		
	}
	private static void addArmorSets() {

		GameRegistry.addShapedRecipe(new ResourceLocation(KCore.MODID, "Mystic Helmet"),new ResourceLocation("Helmets"),new ItemStack(KCore.MysticHelmet),"XXX", "X X", Character.valueOf('X'), KCore.MysticGem);
		GameRegistry.addShapedRecipe(new ResourceLocation(KCore.MODID, "Mystic Chestplate"),new ResourceLocation("Chestplates"),new ItemStack(KCore.MysticChestplate),"X X", "XXX", "XXX", Character.valueOf('X'), KCore.MysticGem);
		GameRegistry.addShapedRecipe(new ResourceLocation(KCore.MODID, "Mystic Leggins"),new ResourceLocation("Leggins"),new ItemStack(KCore.MysticLeggins),"XXX", "X X", "X X", Character.valueOf('X'), KCore.MysticGem);
		GameRegistry.addShapedRecipe(new ResourceLocation(KCore.MODID, "Mystic Boots"),new ResourceLocation("Boots"),new ItemStack(KCore.MysticBoots),"X X", "X X", Character.valueOf('X'), KCore.MysticGem);
		GameRegistry.addShapedRecipe(new ResourceLocation(KCore.MODID, "Titanium Helmet"),new ResourceLocation("Helmets"),new ItemStack(KCore.TitaniumHelmet),"XXX", "X X", Character.valueOf('X'), KCore.TitaniumIngot);
		GameRegistry.addShapedRecipe(new ResourceLocation(KCore.MODID, "Titanium Chestplate"),new ResourceLocation("Chestplates"),new ItemStack(KCore.TitaniumChestplate),"X X", "XXX", "XXX", Character.valueOf('X'), KCore.TitaniumIngot);
		GameRegistry.addShapedRecipe(new ResourceLocation(KCore.MODID, "Titanium Leggins"),new ResourceLocation("Leggins"),new ItemStack(KCore.TitaniumLeggins),"XXX", "X X", "X X", Character.valueOf('X'), KCore.TitaniumIngot);
		GameRegistry.addShapedRecipe(new ResourceLocation(KCore.MODID, "Titanium Boots"),new ResourceLocation("Boots"),new ItemStack(KCore.TitaniumBoots),"X X", "X X", Character.valueOf('X'), KCore.TitaniumIngot);
		GameRegistry.addShapedRecipe(new ResourceLocation(KCore.MODID, "Revenum Helmet"),new ResourceLocation("Helmets"),new ItemStack(KCore.RevenumHelmet),"XXX", "X X", Character.valueOf('X'), KCore.RevenumIngot);
		GameRegistry.addShapedRecipe(new ResourceLocation(KCore.MODID, "Revenum Chestplate"),new ResourceLocation("Chestplates"),new ItemStack(KCore.RevenumChestplate),"X X", "XXX", "XXX", Character.valueOf('X'), KCore.RevenumIngot);
		GameRegistry.addShapedRecipe(new ResourceLocation(KCore.MODID, "Revenum Leggins"),new ResourceLocation("Leggins"),new ItemStack(KCore.RevenumLeggins),"XXX", "X X", "X X", Character.valueOf('X'), KCore.RevenumIngot);
		GameRegistry.addShapedRecipe(new ResourceLocation(KCore.MODID, "Revenum Boots"),new ResourceLocation("Boots"),new ItemStack(KCore.RevenumBoots),"X X", "X X", Character.valueOf('X'), KCore.RevenumIngot);
		GameRegistry.addShapedRecipe(new ResourceLocation(KCore.MODID, "Cloud Boots"),new ResourceLocation("Boots"),new ItemStack(KCore.CloudBoots),"X X", "X X", Character.valueOf('X'), KCore.CondensedBlueCloudDust);
		
	}
	private static void addOresBlocks() {
		GameRegistry.addShapedRecipe(new ResourceLocation(KCore.MODID, "Titanium Block"),new ResourceLocation("Blocks"), new ItemStack(Item.getItemFromBlock(KCore.TitaniumBlock)),"XXX", "XXX", "XXX", Character.valueOf('X'), KCore.TitaniumIngot);
		GameRegistry.addShapelessRecipe(new ResourceLocation(KCore.MODID, "Titanium Ingot"),new ResourceLocation("Ingots"), new ItemStack(KCore.TitaniumIngot,9),Ingredient.fromItem(Item.getItemFromBlock(KCore.TitaniumBlock)));

	}
	
	private static void addFood() {
		GameRegistry.addShapedRecipe(new ResourceLocation(KCore.MODID, "Cotton Candy"),new ResourceLocation("Food"),new ItemStack(KCore.CottonCandy),"  X", " C ", "Z  ", Character.valueOf('X'), KCore.BlueCloudDust, Character.valueOf('C'), Items.SUGAR, Character.valueOf('Z'), Items.STICK);

	}
	
	private static void addOther() {
		GameRegistry.addShapelessRecipe(new ResourceLocation(KCore.MODID, "Titanium Rod"),new ResourceLocation("Rods"), new ItemStack(KCore.TitaniumRod,2),Ingredient.fromItem(KCore.TitaniumIngot));
		GameRegistry.addShapedRecipe(new ResourceLocation(KCore.MODID, "Mythic Stone Tiles"),new ResourceLocation("Blocks"), new ItemStack(Item.getItemFromBlock(KCore.MythicStoneTiles),4),"XX", "XX", Character.valueOf('X'), KCore.MythicStone);
		GameRegistry.addShapelessRecipe(new ResourceLocation(KCore.MODID, "Mystic Planks"),new ResourceLocation("Blocks"), new ItemStack(Item.getItemFromBlock(KCore.MysticPlanks),4),Ingredient.fromItem(Item.getItemFromBlock(KCore.MysticLog)));
		GameRegistry.addShapelessRecipe(new ResourceLocation(KCore.MODID, "Shiny Planks"),new ResourceLocation("Blocks"), new ItemStack(Item.getItemFromBlock(KCore.ShinyPlanks),4),Ingredient.fromItem(Item.getItemFromBlock(KCore.ShinyLog)));
		GameRegistry.addShapelessRecipe(new ResourceLocation(KCore.MODID, "Soul Planks"),new ResourceLocation("Blocks"), new ItemStack(Item.getItemFromBlock(KCore.SoulPlanks),4),Ingredient.fromItem(Item.getItemFromBlock(KCore.SoulLog)));
		GameRegistry.addShapelessRecipe(new ResourceLocation(KCore.MODID, "Redwood Planks"),new ResourceLocation("Blocks"), new ItemStack(Item.getItemFromBlock(KCore.Redwood_planks),4),Ingredient.fromItem(Item.getItemFromBlock(KCore.Redwood_log_full)));
		GameRegistry.addShapelessRecipe(new ResourceLocation(KCore.MODID, "Redwood Planks"),new ResourceLocation("Blocks"), new ItemStack(Item.getItemFromBlock(KCore.Redwood_planks),4),Ingredient.fromItem(Item.getItemFromBlock(KCore.Redwood_log_size_6)));
		GameRegistry.addShapelessRecipe(new ResourceLocation(KCore.MODID, "Redwood Planks"),new ResourceLocation("Blocks"), new ItemStack(Item.getItemFromBlock(KCore.Redwood_planks),3),Ingredient.fromItem(Item.getItemFromBlock(KCore.Redwood_log_size_5)));
		GameRegistry.addShapelessRecipe(new ResourceLocation(KCore.MODID, "Redwood Planks"),new ResourceLocation("Blocks"), new ItemStack(Item.getItemFromBlock(KCore.Redwood_planks),3),Ingredient.fromItem(Item.getItemFromBlock(KCore.Redwood_log_size_4)));
		GameRegistry.addShapelessRecipe(new ResourceLocation(KCore.MODID, "Redwood Planks"),new ResourceLocation("Blocks"), new ItemStack(Item.getItemFromBlock(KCore.Redwood_planks),2),Ingredient.fromItem(Item.getItemFromBlock(KCore.Redwood_log_size_3)));
		GameRegistry.addShapelessRecipe(new ResourceLocation(KCore.MODID, "Redwood Planks"),new ResourceLocation("Blocks"), new ItemStack(Item.getItemFromBlock(KCore.Redwood_planks),2),Ingredient.fromItem(Item.getItemFromBlock(KCore.Redwood_log_size_2)));
		GameRegistry.addShapelessRecipe(new ResourceLocation(KCore.MODID, "Redwood Planks"),new ResourceLocation("Blocks"), new ItemStack(Item.getItemFromBlock(KCore.Redwood_planks),1),Ingredient.fromItem(Item.getItemFromBlock(KCore.Redwood_log_size_1)));
		GameRegistry.addShapedRecipe(new ResourceLocation(KCore.MODID, "Redwood Log Size 6"),new ResourceLocation("Blocks"), new ItemStack(Item.getItemFromBlock(KCore.Redwood_log_size_6),9),"XXX", "XXX", "XXX", Character.valueOf('X'), KCore.Redwood_log_full);
		GameRegistry.addShapedRecipe(new ResourceLocation(KCore.MODID, "Redwood Log Size 5"),new ResourceLocation("Blocks"), new ItemStack(Item.getItemFromBlock(KCore.Redwood_log_size_5),9),"XXX", "XXX", "XXX", Character.valueOf('X'), KCore.Redwood_log_size_6);
		GameRegistry.addShapedRecipe(new ResourceLocation(KCore.MODID, "Redwood Log Size 4"),new ResourceLocation("Blocks"), new ItemStack(Item.getItemFromBlock(KCore.Redwood_log_size_4),9),"XXX", "XXX", "XXX", Character.valueOf('X'), KCore.Redwood_log_size_5);
		GameRegistry.addShapedRecipe(new ResourceLocation(KCore.MODID, "Redwood Log Size 3"),new ResourceLocation("Blocks"), new ItemStack(Item.getItemFromBlock(KCore.Redwood_log_size_3),9),"XXX", "XXX", "XXX", Character.valueOf('X'), KCore.Redwood_log_size_4);
		GameRegistry.addShapedRecipe(new ResourceLocation(KCore.MODID, "Redwood Log Size 2"),new ResourceLocation("Blocks"), new ItemStack(Item.getItemFromBlock(KCore.Redwood_log_size_2),9),"XXX", "XXX", "XXX", Character.valueOf('X'), KCore.Redwood_log_size_3);
		GameRegistry.addShapedRecipe(new ResourceLocation(KCore.MODID, "Redwood Log Size 1"),new ResourceLocation("Blocks"), new ItemStack(Item.getItemFromBlock(KCore.Redwood_log_size_1),9),"XXX", "XXX", "XXX", Character.valueOf('X'), KCore.Redwood_log_size_2);

		GameRegistry.addShapedRecipe(new ResourceLocation(KCore.MODID, "Hardened Weathered Rock Tiles"),new ResourceLocation("Blocks"), new ItemStack(Item.getItemFromBlock(KCore.WeatheredRockTiles),4),"XX", "XX", Character.valueOf('X'), KCore.HardenedWeatheredRock);
		GameRegistry.addShapedRecipe(new ResourceLocation(KCore.MODID, "Hardened Weathered Rock Bricks"),new ResourceLocation("Blocks"), new ItemStack(Item.getItemFromBlock(KCore.WeatheredRockBricks),6),"XXX", "XXX", Character.valueOf('X'), KCore.HardenedWeatheredRock);
		GameRegistry.addShapedRecipe(new ResourceLocation(KCore.MODID, "Hardened Weathered Rock Brick Stairs"),new ResourceLocation("Blocks"), new ItemStack(Item.getItemFromBlock(KCore.HardenedWeatheredRockBricksStairs),4),"XX", "XX", Character.valueOf('X'), KCore.HardenedWeatheredRock);
		GameRegistry.addShapedRecipe(new ResourceLocation(KCore.MODID, "Mythic Stone Sign"),new ResourceLocation("Blocks"), new ItemStack(KCore.MythicStoneSign,6),"XXX", "XXX", " X ", Character.valueOf('X'), KCore.MythicStone);

		GameRegistry.addShapedRecipe(new ResourceLocation(KCore.MODID, "Crystals_Cluster1"),new ResourceLocation("Crystals"), new ItemStack(KCore.CrystalsCluster,1),"XXX", "YYY", "ZZZ", Character.valueOf('X'), KCore.BlueCrystalShard,Character.valueOf('Y'), KCore.YellowCrystalShard,Character.valueOf('Z'), KCore.VioletCrystalShard);
		GameRegistry.addShapedRecipe(new ResourceLocation(KCore.MODID, "Crystals_Cluster2"),new ResourceLocation("Crystals"), new ItemStack(KCore.CrystalsCluster,1),"XXX", "YYY", "ZZZ", Character.valueOf('X'), KCore.BlueCrystalShard,Character.valueOf('Y'), KCore.VioletCrystalShard,Character.valueOf('Z'), KCore.YellowCrystalShard);
		GameRegistry.addShapedRecipe(new ResourceLocation(KCore.MODID, "Crystals_Cluster3"),new ResourceLocation("Crystals"), new ItemStack(KCore.CrystalsCluster,1),"XXX", "YYY", "ZZZ", Character.valueOf('X'), KCore.YellowCrystalShard,Character.valueOf('Y'), KCore.BlueCrystalShard,Character.valueOf('Z'), KCore.VioletCrystalShard);
		GameRegistry.addShapedRecipe(new ResourceLocation(KCore.MODID, "Crystals_Cluster4"),new ResourceLocation("Crystals"), new ItemStack(KCore.CrystalsCluster,1),"XXX", "YYY", "ZZZ", Character.valueOf('X'), KCore.YellowCrystalShard,Character.valueOf('Y'), KCore.VioletCrystalShard,Character.valueOf('Z'), KCore.BlueCrystalShard);
		GameRegistry.addShapedRecipe(new ResourceLocation(KCore.MODID, "Crystals_Cluster5"),new ResourceLocation("Crystals"), new ItemStack(KCore.CrystalsCluster,1),"XXX", "YYY", "ZZZ", Character.valueOf('X'), KCore.VioletCrystalShard,Character.valueOf('Y'), KCore.YellowCrystalShard,Character.valueOf('Z'), KCore.BlueCrystalShard);
		GameRegistry.addShapedRecipe(new ResourceLocation(KCore.MODID, "Crystals_Cluster6"),new ResourceLocation("Crystals"), new ItemStack(KCore.CrystalsCluster,1),"XXX", "YYY", "ZZZ", Character.valueOf('X'), KCore.VioletCrystalShard,Character.valueOf('Y'), KCore.VioletCrystalShard,Character.valueOf('Z'), KCore.YellowCrystalShard);
		 
		GameRegistry.addShapedRecipe(new ResourceLocation(KCore.MODID, "Sticks"),new ResourceLocation("Items"), new ItemStack(Items.STICK,4),"X","X",Character.valueOf('X'), KCore.MysticPlanks);
		GameRegistry.addShapedRecipe(new ResourceLocation(KCore.MODID, "Hardened Weathered Rock Stairs"),new ResourceLocation("Blocks"), new ItemStack(Item.getItemFromBlock(KCore.HardenedWeatheredRockStairs),6),"  X", " XX", "XXX", Character.valueOf('X'), KCore.HardenedWeatheredRock);
		GameRegistry.addShapedRecipe(new ResourceLocation(KCore.MODID, "Mystic Wood Stairs"),new ResourceLocation("Blocks"), new ItemStack(Item.getItemFromBlock(KCore.MysticWoodStairs),6),"  X", " XX", "XXX", Character.valueOf('X'), KCore.MysticPlanks);
		GameRegistry.addShapedRecipe(new ResourceLocation(KCore.MODID, "Shiny Wood Stairs"),new ResourceLocation("Blocks"), new ItemStack(Item.getItemFromBlock(KCore.ShinyWoodStairs),6),"  X", " XX", "XXX", Character.valueOf('X'), KCore.ShinyPlanks);
		GameRegistry.addShapedRecipe(new ResourceLocation(KCore.MODID, "Mythic Stone Stairs"),new ResourceLocation("Blocks"), new ItemStack(Item.getItemFromBlock(KCore.MythicStoneStairs),6),"  X", " XX", "XXX", Character.valueOf('X'), KCore.MythicStone);
		GameRegistry.addShapedRecipe(new ResourceLocation(KCore.MODID, "Mystic Wood Fence"),new ResourceLocation("Blocks"), new ItemStack(Item.getItemFromBlock(KCore.MysticWoodFence),3),"XYX", "XYX", Character.valueOf('X'), KCore.MysticPlanks,Character.valueOf('Y'),Items.STICK);
		GameRegistry.addShapedRecipe(new ResourceLocation(KCore.MODID, "Shiny Wood Fence"),new ResourceLocation("Blocks"), new ItemStack(Item.getItemFromBlock(KCore.ShinyWoodFence),3),"XYX", "XYX", Character.valueOf('X'), KCore.ShinyPlanks,Character.valueOf('Y'),Items.STICK);
		GameRegistry.addShapedRecipe(new ResourceLocation(KCore.MODID, "Mystic Wood Fence Gate"),new ResourceLocation("Blocks"), new ItemStack(Item.getItemFromBlock(KCore.MysticWoodFenceGate),1),"XYX", "XYX", Character.valueOf('X'), Items.STICK,Character.valueOf('Y'),KCore.MysticPlanks);
		GameRegistry.addShapedRecipe(new ResourceLocation(KCore.MODID, "Shiny Wood Fence Gate"),new ResourceLocation("Blocks"), new ItemStack(Item.getItemFromBlock(KCore.ShinyWoodFenceGate),1),"XYX", "XYX", Character.valueOf('X'), Items.STICK,Character.valueOf('Y'),KCore.ShinyPlanks);
		GameRegistry.addShapedRecipe(new ResourceLocation(KCore.MODID, "Mythic Stone Wall"),new ResourceLocation("Blocks"), new ItemStack(Item.getItemFromBlock(KCore.MythicStoneWall),6),"XXX", "XXX", Character.valueOf('X'), KCore.MythicStone);
		GameRegistry.addShapedRecipe(new ResourceLocation(KCore.MODID, "Hardened Weathered Rock Wall"),new ResourceLocation("Blocks"), new ItemStack(Item.getItemFromBlock(KCore.HardenedWeatheredRockWall),6),"XXX", "XXX", Character.valueOf('X'), KCore.HardenedWeatheredRock);
		GameRegistry.addShapedRecipe(new ResourceLocation(KCore.MODID, "Blue Cloud"),new ResourceLocation("Blocks"), new ItemStack(Item.getItemFromBlock(KCore.BlueCloud),1),"XX", "XX", Character.valueOf('X'), KCore.BlueCloudDust);
		GameRegistry.addShapedRecipe(new ResourceLocation(KCore.MODID, "Yellow Cloud"),new ResourceLocation("Blocks"), new ItemStack(Item.getItemFromBlock(KCore.YellowCloud),1),"XX", "XX", Character.valueOf('X'), KCore.YellowCloudDust);
		GameRegistry.addShapedRecipe(new ResourceLocation(KCore.MODID, "Blue Condensed Cloud"),new ResourceLocation("Blocks"), new ItemStack(Item.getItemFromBlock(KCore.BlueCondensedCloud),1),"XX", "XX", Character.valueOf('X'), KCore.CondensedBlueCloudDust);
		GameRegistry.addShapedRecipe(new ResourceLocation(KCore.MODID, "Yellow Condensed Cloud"),new ResourceLocation("Blocks"), new ItemStack(Item.getItemFromBlock(KCore.YellowCondensedCloud),1),"XX", "XX", Character.valueOf('X'), KCore.CondensedYellowCloudDust);
		GameRegistry.addShapedRecipe(new ResourceLocation(KCore.MODID, "Blue Cloud Bricks"),new ResourceLocation("Blocks"), new ItemStack(Item.getItemFromBlock(KCore.BlueCloudBricks),1),"XXX", "XXX", Character.valueOf('X'), KCore.CondensedBlueCloudDust);
		GameRegistry.addShapedRecipe(new ResourceLocation(KCore.MODID, "Yellow Cloud Block"),new ResourceLocation("Blocks"), new ItemStack(Item.getItemFromBlock(KCore.YellowCloudBlock),1),"XXX", "XXX", Character.valueOf('X'), KCore.CondensedYellowCloudDust);
		GameRegistry.addShapedRecipe(new ResourceLocation(KCore.MODID, "Yellow Cloud Dust"),new ResourceLocation("Items"), new ItemStack(KCore.YellowCloudDust,1),"XY", Character.valueOf('X'), KCore.BlueCloudDust, Character.valueOf('Y'), KCore.YellowCrystalShard);
		GameRegistry.addShapedRecipe(new ResourceLocation(KCore.MODID, "Blue Cloud Dust"),new ResourceLocation("Items"), new ItemStack(KCore.BlueCloudDust,1),"XY", Character.valueOf('X'), KCore.YellowCloudDust, Character.valueOf('Y'), KCore.BlueCrystalShard);
		GameRegistry.addShapedRecipe(new ResourceLocation(KCore.MODID, "Glass Jar"),new ResourceLocation("Items"), new ItemStack(KCore.GlassJar,16)," X ", "X X", " X ", Character.valueOf('X'), Blocks.GLASS);

		GameRegistry.addShapedRecipe(new ResourceLocation(KCore.MODID, "Blue Condensed Cloud1"),new ResourceLocation("Blocks"), new ItemStack(Item.getItemFromBlock(KCore.BlueCondensedCloud),4),"XX", "XX", Character.valueOf('X'), KCore.BlueCloud);
		GameRegistry.addShapedRecipe(new ResourceLocation(KCore.MODID, "Yellow Condensed Cloud1"),new ResourceLocation("Blocks"), new ItemStack(Item.getItemFromBlock(KCore.YellowCondensedCloud),4),"XX", "XX", Character.valueOf('X'), KCore.YellowCloud);
		GameRegistry.addShapedRecipe(new ResourceLocation(KCore.MODID, "Blue Cloud Bricks1"),new ResourceLocation("Blocks"), new ItemStack(Item.getItemFromBlock(KCore.BlueCloudBricks),6),"XXX", "XXX", Character.valueOf('X'), KCore.BlueCondensedCloud);
		GameRegistry.addShapedRecipe(new ResourceLocation(KCore.MODID, "Yellow Cloud Block1"),new ResourceLocation("Blocks"), new ItemStack(Item.getItemFromBlock(KCore.YellowCloudBlock),6),"XXX", "XXX", Character.valueOf('X'), KCore.YellowCondensedCloud);
		GameRegistry.addShapedRecipe(new ResourceLocation(KCore.MODID, "Shiny Stick"),new ResourceLocation("Items"), new ItemStack(KCore.ShinyStick,2),"X", Character.valueOf('X'), KCore.ShinyPlanks);
		GameRegistry.addShapedRecipe(new ResourceLocation(KCore.MODID, "Shock Wand"),new ResourceLocation("Items"), new ItemStack(KCore.ShockWand,1,990)," XX", " YX","Y  ", Character.valueOf('X'), KCore.JellyFishTentacle, Character.valueOf('Y'), KCore.ShinyStick);
		GameRegistry.addShapedRecipe(new ResourceLocation(KCore.MODID, "Charger"),new ResourceLocation("Items"), new ItemStack(KCore.Charger,1),"XYX", "YZY","XYX", Character.valueOf('X'), KCore.MythicStone, Character.valueOf('Y'), KCore.EnergyShard, Character.valueOf('Z'), KCore.MysticGem);
		GameRegistry.addShapedRecipe(new ResourceLocation(KCore.MODID, "Turtle Shell Plate"),new ResourceLocation("Blocks"), new ItemStack(KCore.TurtleShellPlate,1),"XXX", "XXX","XXX", Character.valueOf('X'), KCore.TurtleShell);
		GameRegistry.addShapedRecipe(new ResourceLocation(KCore.MODID, "Big Wings"),new ResourceLocation("Items"), new ItemStack(KCore.BigWings,1),"XXX", "XXX","XXX", Character.valueOf('X'), KCore.WingsPiece);
		GameRegistry.addShapedRecipe(new ResourceLocation(KCore.MODID, "Shiny Bark"),new ResourceLocation("Blocks"), new ItemStack(KCore.ShinyLogBark,3),"XX", "XX", Character.valueOf('X'), KCore.ShinyLog);
		GameRegistry.addShapedRecipe(new ResourceLocation(KCore.MODID, "Mystic Wood Slab"),new ResourceLocation("Blocks"), new ItemStack(KCore.MysticWoodHalfSlab,6),"XXX", Character.valueOf('X'), KCore.MysticPlanks);
		GameRegistry.addShapedRecipe(new ResourceLocation(KCore.MODID, "Shiny Wood Slab"),new ResourceLocation("Blocks"), new ItemStack(KCore.ShinyWoodHalfSlab,6),"XXX", Character.valueOf('X'), KCore.ShinyPlanks);
		GameRegistry.addShapedRecipe(new ResourceLocation(KCore.MODID, "Soul Wood Slab"),new ResourceLocation("Blocks"), new ItemStack(KCore.SoulWoodHalfSlab,6),"XXX", Character.valueOf('X'), KCore.SoulPlanks);
		GameRegistry.addShapedRecipe(new ResourceLocation(KCore.MODID, "Soul Wood Stairs"),new ResourceLocation("Blocks"), new ItemStack(Item.getItemFromBlock(KCore.SoulWoodStairs),6),"  X", " XX", "XXX", Character.valueOf('X'), KCore.SoulPlanks);
		GameRegistry.addShapedRecipe(new ResourceLocation(KCore.MODID, "Mystic Bark"),new ResourceLocation("Blocks"), new ItemStack(KCore.MysticLogBark,3),"XX", "XX", Character.valueOf('X'), KCore.MysticLog);
		GameRegistry.addShapedRecipe(new ResourceLocation(KCore.MODID, "Soul Bark"),new ResourceLocation("Blocks"), new ItemStack(KCore.SoulLogBark,3),"XX", "XX", Character.valueOf('X'), KCore.SoulLog);
		GameRegistry.addShapedRecipe(new ResourceLocation(KCore.MODID, "Mystic Gem Block"),new ResourceLocation("Blocks"), new ItemStack(KCore.Mystic_Gem_Block,1),"XXX", "XXX", "XXX", Character.valueOf('X'), KCore.MysticGem);
		GameRegistry.addShapelessRecipe(new ResourceLocation(KCore.MODID, "Mystic Gem"),new ResourceLocation("Blocks"), new ItemStack(KCore.MysticGem,9),Ingredient.fromItem(Item.getItemFromBlock(KCore.Mystic_Gem_Block)));
		GameRegistry.addShapedRecipe(new ResourceLocation(KCore.MODID, "Mystic Wood Trap Door"),new ResourceLocation("Blocks"), new ItemStack(KCore.Mystic_Wood_Trap_Door,2),"XXX", "XXX", Character.valueOf('X'), KCore.MysticPlanks);
		GameRegistry.addShapedRecipe(new ResourceLocation(KCore.MODID, "Shiny Wood Trap Door"),new ResourceLocation("Blocks"), new ItemStack(KCore.Shiny_Wood_Trap_Door,2),"XXX", "XXX", Character.valueOf('X'), KCore.ShinyPlanks);
		GameRegistry.addShapedRecipe(new ResourceLocation(KCore.MODID, "Soul Wood Trap Door"),new ResourceLocation("Blocks"), new ItemStack(KCore.Soul_Wood_Trap_Door,2),"XXX", "XXX", Character.valueOf('X'), KCore.SoulPlanks);
		GameRegistry.addShapedRecipe(new ResourceLocation(KCore.MODID, "Mystic Wood Door"),new ResourceLocation("Blocks"), new ItemStack(KCore.Mystic_Wood_Door,3),"XX","XX", "XX", Character.valueOf('X'), KCore.MysticPlanks);
		GameRegistry.addShapedRecipe(new ResourceLocation(KCore.MODID, "Shiny Wood Door"),new ResourceLocation("Blocks"), new ItemStack(KCore.Shiny_Wood_Door,3),"XX","XX", "XX", Character.valueOf('X'), KCore.ShinyPlanks);
		GameRegistry.addShapedRecipe(new ResourceLocation(KCore.MODID, "Soul Wood Door"),new ResourceLocation("Blocks"), new ItemStack(KCore.Soul_Wood_Door,3),"XX","XX", "XX", Character.valueOf('X'), KCore.SoulPlanks);
		GameRegistry.addShapedRecipe(new ResourceLocation(KCore.MODID, "Magnethium"),new ResourceLocation("Blocks"), new ItemStack(KCore.Magnethium,1),"XX","XX", Character.valueOf('X'), KCore.Magnethium_Shard);
		GameRegistry.addShapedRecipe(new ResourceLocation(KCore.MODID, "Magnethium Shards"),new ResourceLocation("Blocks"), new ItemStack(KCore.Magnethium_Shard,4),"X", Character.valueOf('X'), KCore.Magnethium);
		GameRegistry.addShapedRecipe(new ResourceLocation(KCore.MODID, "Iron Gold Ingot 1"),new ResourceLocation("Ingots"), new ItemStack(KCore.Ingot_Iron_Gold,3),"XYX", Character.valueOf('X'), Items.IRON_INGOT, Character.valueOf('Y'),Items.GOLD_INGOT);
		GameRegistry.addShapedRecipe(new ResourceLocation(KCore.MODID, "Iron Gold Block"),new ResourceLocation("Blocks"), new ItemStack(KCore.Block_Iron_Gold,1),"XXX","XXX","XXX", Character.valueOf('X'), KCore.Ingot_Iron_Gold);
		GameRegistry.addShapelessRecipe(new ResourceLocation(KCore.MODID, "Iron Gold Ingot 2"),new ResourceLocation("Ingots"), new ItemStack(KCore.Ingot_Iron_Gold,9),Ingredient.fromItem(Item.getItemFromBlock(KCore.Block_Iron_Gold)));
		GameRegistry.addShapedRecipe(new ResourceLocation(KCore.MODID, "Ritual Blade"),new ResourceLocation("Items"), new ItemStack(KCore.Ritual_Blade,1),"X  "," Y ","  Z", Character.valueOf('X'), Items.STICK, Character.valueOf('Y'),KCore.Ingot_Iron_Gold,Character.valueOf('Z'),KCore.MysticGem);
		GameRegistry.addShapelessRecipe(new ResourceLocation(KCore.MODID, "Bowl of Nectar"), new ResourceLocation("Food"), new ItemStack(KCore.Nectar_Bowl), Ingredient.fromItem(Items.BOWL), Ingredient.fromItem(KCore.ButterflyFlowerNectar));

		GameRegistry.addShapelessRecipe(new ResourceLocation(KCore.MODID, "Mystic Planks S"),new ResourceLocation("Blocks"), new ItemStack(Item.getItemFromBlock(KCore.MysticPlanks),4),Ingredient.fromItem(Item.getItemFromBlock(KCore.Stripped_Mystic_Log)));
		GameRegistry.addShapelessRecipe(new ResourceLocation(KCore.MODID, "Shiny Planks S"),new ResourceLocation("Blocks"), new ItemStack(Item.getItemFromBlock(KCore.ShinyPlanks),4),Ingredient.fromItem(Item.getItemFromBlock(KCore.Stripped_Shiny_Log)));
		GameRegistry.addShapelessRecipe(new ResourceLocation(KCore.MODID, "Soul Planks S"),new ResourceLocation("Blocks"), new ItemStack(Item.getItemFromBlock(KCore.SoulPlanks),4),Ingredient.fromItem(Item.getItemFromBlock(KCore.Stripped_Soul_Log)));

		GameRegistry.addShapedRecipe(new ResourceLocation(KCore.MODID, "Katharian Arrow1"),new ResourceLocation("Items"), new ItemStack(KCore.katharian_arrow,4),"X  "," Y ","  Z", Character.valueOf('X'), KCore.skyray_feather, Character.valueOf('Y'),KCore.ShinyStick,Character.valueOf('Z'),KCore.TitaniumIngot);
		GameRegistry.addShapedRecipe(new ResourceLocation(KCore.MODID, "Katharian Arrow2"),new ResourceLocation("Items"), new ItemStack(KCore.katharian_arrow,4)," X "," Y "," Z ", Character.valueOf('X'), KCore.skyray_feather, Character.valueOf('Y'),KCore.ShinyStick,Character.valueOf('Z'),KCore.TitaniumIngot);

		GameRegistry.addShapedRecipe(new ResourceLocation(KCore.MODID, "Baurble"),new ResourceLocation("Blocks"), new ItemStack(KCore.baurble,1),"XZX","XYX","XXX", Character.valueOf('X'), Items.PAPER, Character.valueOf('Y'),Items.GLOWSTONE_DUST,Character.valueOf('Z'),KCore.ShinyStick);

	}
}