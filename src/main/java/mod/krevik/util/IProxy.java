package mod.krevik.util;

import mod.krevik.world.dimension.TileEntityKether;
import mod.krevik.KCore;
import mod.krevik.client.gui.GuiHandler;
import mod.krevik.client.sound.SoundHelper;
import mod.krevik.tileentity.TileEntityCharger;
import mod.krevik.tileentity.TileEntityMythicStoneSign;
import net.minecraft.block.SoundType;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;

/**
 * Some basic functions that differ depending on the physical side
 *
 * @author Cadiboo
 */
public interface IProxy {

	String localize(String unlocalized);

	String localizeAndFormat(String unlocalized, Object... args);

	default void logPhysicalSide() {
		KCore.LOGGER.info("Physical Side: " + getPhysicalSide());
	}

	Side getPhysicalSide();

	public static SoundHelper bird = new SoundHelper("bird", "bird");
	public static SoundHelper Howler_living = new SoundHelper("howler", "living");
	public static SoundHelper Howler_dead = new SoundHelper("howler", "dead");
	public static SoundHelper Howler_hurt = new SoundHelper("howler", "hurt");
	public static SoundHelper jellyfish_living = new SoundHelper("jellyfish", "living");
	public static SoundHelper jellyfish_dead = new SoundHelper("jellyfish", "dead");
	public static SoundHelper jellyfish_hurt = new SoundHelper("jellyfish", "hurt");
	public static SoundHelper turtle_dead = new SoundHelper("turtle", "dead");
	public static SoundHelper bison_dead = new SoundHelper("bison", "dead");
	public static SoundHelper bison_hurt = new SoundHelper("bison", "hurt");
	public static SoundHelper bison_living = new SoundHelper("bison", "living");
	public static SoundEvent ketherMusicDaySound = new SoundHelper("music.day");
	public static SoundEvent ketherMusicNightSound = new SoundHelper("music.night");
	public static SoundHelper ghost_living = new SoundHelper("ghost", "living");
	public static SoundHelper ghost_attack = new SoundHelper("ghost", "attack");
	public static SoundHelper ghost_dead = new SoundHelper("ghost", "dead");
	public static SoundHelper death_living = new SoundHelper("death", "living");
	public static SoundHelper scary_flower = new SoundHelper("scary", "flower");
	public static SoundHelper camel_dead = new SoundHelper("camel", "dead");
	public static SoundHelper camel_hurt = new SoundHelper("camel", "hurt");
	public static SoundHelper camel_breath = new SoundHelper("camel", "breath");
	public static SoundHelper camel_ambient = new SoundHelper("camel", "ambient");
	public static SoundHelper oldman_ambient = new SoundHelper("oldman", "ambient");
	public static SoundHelper skyray_ambient = new SoundHelper("skyray", "ambient");
	public static SoundHelper skyray_hurt = new SoundHelper("skyray", "hurt");
	public static SoundHelper cloud_glass_break = new SoundHelper("cloudglass.break");
	public static SoundHelper sandstorm = new SoundHelper("sandstorm");
	public static SoundHelper pickaxe_turn = new SoundHelper("pickaxe_turn");
	public static SoundEvent ketherMusicChristmasSound = new SoundHelper("music.xmas");

	public static SoundEvent music_disc_jazzy = new SoundHelper("music_disc.jazzy");
	public static SoundEvent music_disc_8bit = new SoundHelper("music_disc.8bit");

	public static final SoundType CLOUDGLASS = new SoundType(1.0F, 1.0F, cloud_glass_break, SoundEvents.BLOCK_GLASS_STEP, SoundEvents.BLOCK_GLASS_PLACE, SoundEvents.BLOCK_GLASS_HIT, SoundEvents.BLOCK_GLASS_FALL);

	public default void preInit(FMLPreInitializationEvent e) {
	}

	public default void init(FMLInitializationEvent e) {
		EntityAndRenderRegistry.registerEntitiesAndEggs();
		GameRegistry.registerTileEntity(TileEntityCharger.class, new ResourceLocation(KCore.MODID, "charger").toString());
		GameRegistry.registerTileEntity(TileEntityKether.class, new ResourceLocation(KCore.MODID, "entityDimKether"));
		GameRegistry.registerTileEntity(TileEntityMythicStoneSign.class, new ResourceLocation(KCore.MODID, "mythicstonesign"));
		NetworkRegistry.INSTANCE.registerGuiHandler(KCore.instance, new GuiHandler());
		this.initFlamables();
		//GameRegistry.registerWorldGenerator(new WorldGeneratorHandler(), 10);

	}

	public default void postInit(FMLPostInitializationEvent e) {

	}

	public default void initFlamables() {
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
		Blocks.FIRE.setFireInfo(KCore.bison_Stars, 30, 60);

	}

}