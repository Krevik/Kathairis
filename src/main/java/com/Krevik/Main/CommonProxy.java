package com.Krevik.Main;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.Nullable;

import com.Krevik.Dimension.TileEntityKether;
import com.Krevik.Entities.EntityGaznowel;
import com.Krevik.Gui.GuiHandler;
import com.Krevik.Items.ItemSand;
import com.Krevik.Sounds.SoundHelper;
import com.Krevik.TileEntity.TileEntityCharger;
import com.Krevik.TileEntity.TileEntityMythicStoneSign;
import com.google.common.base.Preconditions;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.client.audio.MusicTicker;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemSlab;
import net.minecraft.item.ItemSnow;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.client.EnumHelperClient;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;

@Mod.EventBusSubscriber
public class CommonProxy {
	public static SoundHelper bird = new SoundHelper("bird","bird");
	public static SoundHelper Howler_living = new SoundHelper("howler","living");
	public static SoundHelper Howler_dead = new SoundHelper("howler","dead");
	public static SoundHelper Howler_hurt = new SoundHelper("howler","hurt");
	public static SoundHelper jellyfish_living = new SoundHelper("jellyfish","living");
	public static SoundHelper jellyfish_dead = new SoundHelper("jellyfish","dead");
	public static SoundHelper jellyfish_hurt = new SoundHelper("jellyfish","hurt");
	public static SoundHelper turtle_dead = new SoundHelper("turtle","dead");
	public static SoundHelper bison_dead = new SoundHelper("bison","dead");
	public static SoundHelper bison_hurt = new SoundHelper("bison","hurt");
	public static SoundHelper bison_living = new SoundHelper("bison","living");
	public static SoundEvent ketherMusicDaySound = new SoundHelper("music.day");
	public static SoundEvent ketherMusicNightSound = new SoundHelper("music.night");
	public static SoundHelper ghost_living = new SoundHelper("ghost","living");
	public static SoundHelper ghost_attack = new SoundHelper("ghost","attack");
	public static SoundHelper ghost_dead = new SoundHelper("ghost","dead");
	public static SoundHelper death_living = new SoundHelper("death","living");
	public static SoundHelper scary_flower = new SoundHelper("scary","flower");
	public static SoundHelper camel_dead = new SoundHelper("camel","dead");
	public static SoundHelper camel_hurt = new SoundHelper("camel","hurt");
	public static SoundHelper camel_breath = new SoundHelper("camel","breath");
	public static SoundHelper camel_ambient = new SoundHelper("camel","ambient");
	public static SoundHelper oldman_ambient = new SoundHelper("oldman","ambient");
	public static SoundHelper skyray_ambient = new SoundHelper("skyray","ambient");
	public static SoundHelper skyray_hurt = new SoundHelper("skyray","hurt");
	public static SoundHelper cloud_glass_break = new SoundHelper("cloudglass.break");
	public static SoundHelper sandstorm = new SoundHelper("sandstorm");
	public static SoundHelper pickaxe_turn = new SoundHelper("pickaxe_turn");
	public static SoundEvent ketherMusicChristmasSound = new SoundHelper("music.xmas");

	public static SoundEvent music_disc_jazzy = new SoundHelper("music_disc.jazzy");
	public static SoundEvent music_disc_8bit = new SoundHelper("music_disc.8bit");

	public static final SoundType CLOUDGLASS = new SoundType(1.0F, 1.0F, cloud_glass_break, SoundEvents.BLOCK_GLASS_STEP, SoundEvents.BLOCK_GLASS_PLACE, SoundEvents.BLOCK_GLASS_HIT, SoundEvents.BLOCK_GLASS_FALL);


	public static ArrayList<Biome> biomeList = new ArrayList();
	public static ArrayList<Item> itemList = new ArrayList();

	@SubscribeEvent
	public static void registerBiomes(final RegistryEvent.Register<Biome> event) {
		final IForgeRegistry<Biome> registry = event.getRegistry();
		for(int x=0;x<biomeList.size();x++) {
			registry.register(biomeList.get(x));
		}
		BiomeDictionary.addTypes(KCore.instance.MysticForest, BiomeDictionary.Type.FOREST,BiomeDictionary.Type.DENSE);
		BiomeDictionary.addTypes(KCore.instance.MysticPlains, BiomeDictionary.Type.PLAINS,BiomeDictionary.Type.DENSE);
		BiomeDictionary.addTypes(KCore.instance.MysticDesert, BiomeDictionary.Type.PLAINS,BiomeDictionary.Type.DEAD,BiomeDictionary.Type.HOT);
		BiomeDictionary.addTypes(KCore.instance.MysticSwamps, BiomeDictionary.Type.DRY);
		BiomeDictionary.addTypes(KCore.instance.FloatingIslands, BiomeDictionary.Type.MAGICAL,BiomeDictionary.Type.OCEAN);
		BiomeDictionary.addTypes(KCore.instance.KatharianOcean, BiomeDictionary.Type.MAGICAL,BiomeDictionary.Type.OCEAN);

	}

	@SubscribeEvent
	public static void registerPotions(final RegistryEvent.Register<Potion> event) {
		KCore.onRegisterPotions(event);
	}

    public void preInit(FMLPreInitializationEvent e) {
    	
    }
    

    public void init(FMLInitializationEvent e) {
		EntityAndRenderRegistry.registerEntitiesAndEggs();
    	GameRegistry.registerTileEntity(TileEntityCharger.class, new ResourceLocation("mystic:charger").toString());
    	GameRegistry.registerTileEntity(TileEntityKether.class, "mystic:entityDimKether");
    	GameRegistry.registerTileEntity(TileEntityMythicStoneSign.class, "mystic:mythicstonesign");
    	NetworkRegistry.INSTANCE.registerGuiHandler(KCore.instance, new GuiHandler()); 
    	this.initFlamables();
    	//GameRegistry.registerWorldGenerator(new WorldGeneratorHandler(), 10);


    }
    

    public void postInit(FMLPostInitializationEvent e) {

    }
	 
	@Mod.EventBusSubscriber(modid = KCore.MODID)
	public static class RegistrationHandler {
		@SubscribeEvent
		public static void registerItems(final RegistryEvent.Register<Item> event) {
			final IForgeRegistry<Item> registry = event.getRegistry();
			for(int x=0;x<itemList.size();x++) {
				registry.register(itemList.get(x));
			}
			registry.register(KCore.katharian_arrow);
		}
	}
	



	@Mod.EventBusSubscriber(modid = KCore.MODID)
	public static class RegistrationHandler2 {
		public static final Set<ItemBlock> ITEM_BLOCKS = new HashSet<>();

		/**
		 * Register this mod's {@link Block}s.
		 *
		 * @param event The event
		 */
		@SubscribeEvent
		public static void registerBlocks(final RegistryEvent.Register<Block> event) {
			final IForgeRegistry<Block> registry = event.getRegistry();

			final Block[] blocks = {
					KCore.MysticPortal,
					KCore.CorruptedDirt,
					KCore.CorruptedGrass,
					KCore.MythicStone,
					KCore.MysticPlanks,
					KCore.MysticLog,
					KCore.MysticLeaves,
					KCore.MysticSapling,
					KCore.MysticFungus,
					KCore.MysticMiniGrass,
					KCore.MysticTallGrass,
					KCore.MysticFlower,
					KCore.MysticNightFlower,
					KCore.TitaniumBlock,
					KCore.TitaniumOre,
					KCore.MysticMultiGrass,
					KCore.MythicStoneTiles,
					KCore.ShinyLog,
					KCore.ShinyLeaves,
					KCore.ShinyPlanks,
					KCore.ForgottenSand,
					KCore.MovingSand,
					KCore.WeatheredRock,
					KCore.WeatheredRockTiles,
					KCore.HardenedWeatheredRock,
					KCore.WeatheredRockBricks,
					KCore.MysticDeadGrass,
					KCore.GemsOre,
					KCore.ShinyRock,
					KCore.BlueCloud,
					KCore.RevenumOre,
					KCore.VioletCrystal,
					KCore.YellowCrystal,
					KCore.BlueCrystal,
					KCore.BlueFruitPlant,
					KCore.EyePlant,
					KCore.MagicBeans,
					KCore.MythicStonePillar,
					KCore.JadeVine_empty,
					KCore.HardenedWeatheredRockStairs,
					KCore.MysticWoodStairs,
					KCore.ShinyWoodStairs,
					KCore.JadeVine_top,
					KCore.JadeVine_mid,
					KCore.JadeVine_bottom,
					KCore.MythicStoneStairs,
					KCore.MysticWoodFence,
					KCore.ShinyWoodFence,
					KCore.MysticWoodFenceGate,
					KCore.ShinyWoodFenceGate,
					KCore.ShinyTreeSapling,
					KCore.MythicStoneWall,
					KCore.HardenedWeatheredRockWall,
					KCore.MudBlock,
					KCore.MudBricks,
					KCore.ButterflyFlower,
					KCore.BlueCondensedCloud,
					KCore.BlueCloudBricks,
					KCore.YellowCloud,
					KCore.YellowCloudBlock,
					KCore.YellowCondensedCloud,
					KCore.CloudParticleEmitter,
					KCore.Succulent,
					KCore.GooseberryBlock,
					KCore.Charger,
					KCore.EnergyShard,
					KCore.SwampGas,
					KCore.EasterEgg,
					KCore.SoulLog,
					KCore.SoulLeaves,
					KCore.SoulPlanks,
					KCore.SoulSapling,
					KCore.TurtleShellPlate,
					KCore.LuminescentGnarl,
					KCore.BoneBricks,
					KCore.ChiseledBoneBricks,
					KCore.SoulStoneBricks,
					KCore.SoulStoneBricksWithBones,
					KCore.DeadLichen,
					KCore.CursedFlower,
					KCore.SteppedSucculent,
					KCore.MythicStoneStandingSign,
					KCore.MythicStoneWallSign,
					KCore.Magnethium,
					KCore.HardenedWeatheredRockBricksStairs,
					KCore.ShinyLogBark,
					KCore.MysticWoodHalfSlab,
					KCore.MysticWoodDoubleSlab,
					KCore.ShinyWoodHalfSlab,
					KCore.ShinyWoodDoubleSlab,
					KCore.SoulWoodHalfSlab,
					KCore.SoulWoodDoubleSlab,
					KCore.SoulWoodStairs,
					KCore.MysticLogBark,
					KCore.SoulLogBark,
					KCore.Mystic_Gem_Block,
					KCore.Stripped_Mystic_Log,
					KCore.Stripped_Shiny_Log,
					KCore.Stripped_Soul_Log,
					KCore.Mythic_Cobblestone,
					KCore.Mystic_Wood_Trap_Door,
					KCore.Shiny_Wood_Trap_Door,
					KCore.Soul_Wood_Trap_Door,
					KCore.Mystic_Wood_Door,
					KCore.Shiny_Wood_Door,
					KCore.Soul_Wood_Door,
					KCore.Plant_Blue_Cloud,
					KCore.Plant_Yellow_Cloud,
					KCore.Solis_Crystals,
					KCore.Refined_Cloud_Yellow,
					KCore.Refined_Cloud_Blue,
					KCore.Block_Iron_Gold,
					KCore.Gecko_Eggs,
					KCore.Redwood_log_full,
					KCore.Redwood_planks,
					KCore.Redwood_log_size_6,
					KCore.Redwood_log_size_5,
					KCore.Redwood_log_size_4,
					KCore.Redwood_log_size_3,
					KCore.Redwood_log_size_2,
					KCore.Redwood_log_size_1,
					KCore.Mythic_Stone_Bricks,
					KCore.Layered_Sand
					//KCore.Hell_Plant
					//KCore.Butterfly_Analysing_Table

			};

			registry.registerAll(blocks);
		}
		


		/**
		 * Register this mod's {@link ItemBlock}s.
		 *
		 * @param event The event
		 */
		@SubscribeEvent
		public static void registerItemBlocks(final RegistryEvent.Register<Item> event) {
			final ItemBlock[] items = {
					new ItemBlock(KCore.MysticPortal),
					new ItemBlock(KCore.CorruptedDirt),
					new ItemBlock(KCore.CorruptedGrass),
					new ItemBlock(KCore.MythicStone),
					new ItemBlock(KCore.MysticPlanks),
					new ItemBlock(KCore.MysticLog),
					new ItemBlock(KCore.MysticLeaves),
					new ItemBlock(KCore.MysticSapling),
					new ItemBlock(KCore.MysticFungus),
					new ItemBlock(KCore.MysticMiniGrass),
					new ItemBlock(KCore.MysticTallGrass),
					new ItemBlock(KCore.MysticFlower),
					new ItemBlock(KCore.MysticNightFlower),
					new ItemBlock(KCore.TitaniumBlock),
					new ItemBlock(KCore.TitaniumOre),
					new ItemBlock(KCore.MysticMultiGrass),
					new ItemBlock(KCore.MythicStoneTiles),
					new ItemBlock(KCore.ShinyLog),
					new ItemBlock(KCore.ShinyLeaves),
					new ItemBlock(KCore.ShinyPlanks),
					new ItemBlock(KCore.ForgottenSand),
					new ItemBlock(KCore.MovingSand),
					new ItemBlock(KCore.WeatheredRock),
					new ItemBlock(KCore.HardenedWeatheredRock),
					new ItemBlock(KCore.WeatheredRockTiles),
					new ItemBlock(KCore.WeatheredRockBricks),
					new ItemBlock(KCore.MysticDeadGrass),
					new ItemBlock(KCore.GemsOre),
					new ItemBlock(KCore.ShinyRock),
					new ItemBlock(KCore.BlueCloud),
					new ItemBlock(KCore.RevenumOre),
					new ItemBlock(KCore.VioletCrystal),
					new ItemBlock(KCore.YellowCrystal),
					new ItemBlock(KCore.BlueCrystal),
					new ItemBlock(KCore.BlueFruitPlant),
					new ItemBlock(KCore.EyePlant),
					new ItemBlock(KCore.MagicBeans),
					new ItemBlock(KCore.MythicStonePillar),
					new ItemBlock(KCore.JadeVine_empty),
					new ItemBlock(KCore.HardenedWeatheredRockStairs),
					new ItemBlock(KCore.MysticWoodStairs),
					new ItemBlock(KCore.ShinyWoodStairs),
					new ItemBlock(KCore.JadeVine_top),
					new ItemBlock(KCore.JadeVine_mid),
					new ItemBlock(KCore.JadeVine_bottom),
					new ItemBlock(KCore.MythicStoneStairs),
					new ItemBlock(KCore.MysticWoodFence),
					new ItemBlock(KCore.ShinyWoodFence),
					new ItemBlock(KCore.MysticWoodFenceGate),
					new ItemBlock(KCore.ShinyWoodFenceGate),
					new ItemBlock(KCore.ShinyTreeSapling),
					new ItemBlock(KCore.MythicStoneWall),
					new ItemBlock(KCore.HardenedWeatheredRockWall),
					new ItemBlock(KCore.MudBlock),
					new ItemBlock(KCore.MudBricks),
					new ItemBlock(KCore.ButterflyFlower),
					new ItemBlock(KCore.BlueCondensedCloud),
					new ItemBlock(KCore.BlueCloudBricks),
					new ItemBlock(KCore.YellowCloud),
					new ItemBlock(KCore.YellowCondensedCloud),
					new ItemBlock(KCore.YellowCloudBlock),
					new ItemBlock(KCore.CloudParticleEmitter),
					new ItemBlock(KCore.Succulent),
					new ItemBlock(KCore.GooseberryBlock),
					new ItemBlock(KCore.Charger),
					new ItemBlock(KCore.EnergyShard),
					new ItemBlock(KCore.SwampGas),
					new ItemBlock(KCore.EasterEgg),
					new ItemBlock(KCore.SoulLog),
					new ItemBlock(KCore.SoulLeaves),
					new ItemBlock(KCore.SoulPlanks),
					new ItemBlock(KCore.SoulSapling),
					new ItemBlock(KCore.TurtleShellPlate),
					new ItemBlock(KCore.LuminescentGnarl),
					new ItemBlock(KCore.BoneBricks),
					new ItemBlock(KCore.ChiseledBoneBricks),
					new ItemBlock(KCore.SoulStoneBricks),
					new ItemBlock(KCore.SoulStoneBricksWithBones),
					new ItemBlock(KCore.DeadLichen),
					new ItemBlock(KCore.CursedFlower),
					new ItemBlock(KCore.SteppedSucculent),
					new ItemBlock(KCore.Magnethium),
					new ItemBlock(KCore.HardenedWeatheredRockBricksStairs),
					new ItemBlock(KCore.ShinyLogBark),
					new ItemSlab(KCore.MysticWoodHalfSlab,KCore.MysticWoodHalfSlab,KCore.MysticWoodDoubleSlab),
					new ItemSlab(KCore.ShinyWoodHalfSlab,KCore.ShinyWoodHalfSlab,KCore.ShinyWoodDoubleSlab),
					new ItemSlab(KCore.SoulWoodHalfSlab,KCore.SoulWoodHalfSlab,KCore.SoulWoodDoubleSlab),
					new ItemBlock(KCore.SoulWoodStairs),
					new ItemBlock(KCore.MysticLogBark),
					new ItemBlock(KCore.SoulLogBark),
					new ItemBlock(KCore.Mystic_Gem_Block),
					new ItemBlock(KCore.Stripped_Mystic_Log),
					new ItemBlock(KCore.Stripped_Shiny_Log),
					new ItemBlock(KCore.Stripped_Soul_Log),
					new ItemBlock(KCore.Mythic_Cobblestone),
					new ItemBlock(KCore.Mystic_Wood_Trap_Door),
					new ItemBlock(KCore.Shiny_Wood_Trap_Door),
					new ItemBlock(KCore.Soul_Wood_Trap_Door),
					new ItemBlock(KCore.Plant_Blue_Cloud),
					new ItemBlock(KCore.Plant_Yellow_Cloud),
					new ItemBlock(KCore.Mystic_Wood_Door),
					new ItemBlock(KCore.Shiny_Wood_Door),
					new ItemBlock(KCore.Soul_Wood_Door),
					new ItemBlock(KCore.Solis_Crystals),
					new ItemBlock(KCore.Refined_Cloud_Blue),
					new ItemBlock(KCore.Refined_Cloud_Yellow),
					new ItemBlock(KCore.Block_Iron_Gold),
					new ItemBlock(KCore.Gecko_Eggs),
					new ItemBlock(KCore.Redwood_log_full),
					new ItemBlock(KCore.Redwood_planks),
					new ItemBlock(KCore.Redwood_log_size_6),
					new ItemBlock(KCore.Redwood_log_size_5),
					new ItemBlock(KCore.Redwood_log_size_4),
					new ItemBlock(KCore.Redwood_log_size_3),
					new ItemBlock(KCore.Redwood_log_size_2),
					new ItemBlock(KCore.Redwood_log_size_1),
					new ItemBlock(KCore.Mythic_Stone_Bricks),
					new ItemSand(KCore.Layered_Sand)
					//new ItemBlock(KCore.Hell_Plant)
					//new ItemBlock(KCore.Butterfly_Analysing_Table)
					};

			final IForgeRegistry<Item> registry = event.getRegistry();

			for (final ItemBlock item : items) {
				final Block block = item.getBlock();
				final ResourceLocation registryName = Preconditions.checkNotNull(block.getRegistryName(), "Block %s has null registry name", block);
				registry.register(item.setRegistryName(registryName));
				ITEM_BLOCKS.add(item);
			}

		}
		
		
	}

	public void initFlamables() {
		Blocks.FIRE.setFireInfo(KCore.MysticPlanks, 5, 20);
		Blocks.FIRE.setFireInfo(KCore.ShinyPlanks, 5, 20);
		Blocks.FIRE.setFireInfo(KCore.SoulPlanks, 5, 20);
		Blocks.FIRE.setFireInfo(KCore.Redwood_planks, 5, 20);
        Blocks.FIRE.setFireInfo(KCore.MysticWoodDoubleSlab, 5, 20);
        Blocks.FIRE.setFireInfo(KCore.ShinyWoodDoubleSlab, 5, 20);
        Blocks.FIRE.setFireInfo(KCore.SoulWoodDoubleSlab, 5, 20);
        Blocks.FIRE.setFireInfo(KCore.MysticWoodHalfSlab, 5, 20);
        Blocks.FIRE.setFireInfo(KCore.SoulWoodHalfSlab, 5, 20);
        Blocks.FIRE.setFireInfo(KCore.ShinyWoodHalfSlab, 5, 20);
        Blocks.FIRE.setFireInfo(KCore.MysticWoodFenceGate, 5, 20);
        Blocks.FIRE.setFireInfo(KCore.ShinyWoodFenceGate, 5, 20);
        Blocks.FIRE.setFireInfo(KCore.MysticWoodFence, 5, 20);
        Blocks.FIRE.setFireInfo(KCore.ShinyWoodFence, 5, 20);
        Blocks.FIRE.setFireInfo(KCore.MysticWoodStairs, 5, 20);
        Blocks.FIRE.setFireInfo(KCore.ShinyWoodStairs, 5, 20);
        Blocks.FIRE.setFireInfo(KCore.MysticLog, 5, 5);
        Blocks.FIRE.setFireInfo(KCore.ShinyLog, 5, 5);
        Blocks.FIRE.setFireInfo(KCore.SoulLog, 5, 5);
        Blocks.FIRE.setFireInfo(KCore.Redwood_log_full, 5, 5);
        Blocks.FIRE.setFireInfo(KCore.Redwood_log_size_6, 5, 5);
        Blocks.FIRE.setFireInfo(KCore.Redwood_log_size_5, 5, 5);
        Blocks.FIRE.setFireInfo(KCore.Redwood_log_size_4, 5, 5);
        Blocks.FIRE.setFireInfo(KCore.Redwood_log_size_3, 5, 5);
        Blocks.FIRE.setFireInfo(KCore.Redwood_log_size_2, 5, 5);
        Blocks.FIRE.setFireInfo(KCore.Redwood_log_size_1, 5, 5);
        Blocks.FIRE.setFireInfo(KCore.MysticLeaves, 30, 60);
        Blocks.FIRE.setFireInfo(KCore.ShinyLeaves, 30, 60);
        Blocks.FIRE.setFireInfo(KCore.SoulLeaves, 30, 60);
        Blocks.FIRE.setFireInfo(KCore.JadeVine_bottom, 30, 60);
        Blocks.FIRE.setFireInfo(KCore.JadeVine_empty, 30, 60);
        Blocks.FIRE.setFireInfo(KCore.JadeVine_mid, 30, 60);
        Blocks.FIRE.setFireInfo(KCore.JadeVine_top, 30, 60);
        Blocks.FIRE.setFireInfo(KCore.MysticTallGrass, 30, 60);
        Blocks.FIRE.setFireInfo(KCore.MysticMiniGrass, 30, 60);
        Blocks.FIRE.setFireInfo(KCore.MysticNightFlower, 30, 60);
        Blocks.FIRE.setFireInfo(KCore.ButterflyFlower, 30, 60);
        Blocks.FIRE.setFireInfo(KCore.CursedFlower, 30, 60);
        Blocks.FIRE.setFireInfo(KCore.MysticFlower, 30, 60);
        Blocks.FIRE.setFireInfo(KCore.MysticMultiGrass, 30, 60);
        Blocks.FIRE.setFireInfo(KCore.DeadLichen, 30, 60);
        Blocks.FIRE.setFireInfo(KCore.MysticDeadGrass, 30, 60);

	}


	public void init() {
		// TODO Auto-generated method stub
		
	}


}