package io.github.krevik.kathairis;

import io.github.krevik.kathairis.client.gui.GuiOldMan;
import io.github.krevik.kathairis.util.FunctionHelper;
import io.github.krevik.kathairis.util.ModReference;
import io.github.krevik.kathairis.util.ModUtil;
import io.github.krevik.kathairis.util.RenderersRegistry;
import io.github.krevik.kathairis.world.dimension.feature.KatharianFeatureList;
import io.github.krevik.kathairis.world.dimension.structures.crystal_ruins.CrystalRuinsConfig;
import net.minecraft.client.audio.MusicTicker;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.omg.DynamicAny.DynEnumHelper;

import java.util.Random;

/**
 * @author Cadiboo, Krevik
 */
@Mod(ModReference.MOD_ID)
public final class Kathairis {

	public static final Logger KATHAIRIS_LOG = LogManager.getLogger(ModReference.MOD_ID);
	public static boolean SHOULD_BLOCKS_SPREAD_AROUND_PORTAL;
	//public static DimensionType KATHAIRIS;

	public Kathairis() {
		ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, ConfigHandler.CONFIG_SPEC);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setupClient);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::loadComplete);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::serverStarting);
		//ModLoadingContext.get().registerExtensionPoint(ExtensionPoint.GUIFACTORY, ()->GuiOldMan::new);
	}

	private void setup(final FMLCommonSetupEvent event) {
		//StructureCrystalLabirynthPieces.registerStructurePieces();
		//ModDimensions.KATH_DIM_TYPE=DimensionManager.registerDimension(new ResourceLocation(MOD_ID,"kath_dim_type"), ModDimensions.KATHAIRIS, new PacketBuffer(Unpooled.buffer(16)));
	}


	private void loadComplete(final FMLLoadCompleteEvent event){

	}

	private void serverStarting(final FMLServerStartingEvent event){
	}


	private void setupClient(final FMLClientSetupEvent event) {
		RenderersRegistry.registerRenders();
		//OBJLoader.INSTANCE.(ModReference.MOD_ID);
	}

	public static FunctionHelper getHelper(){
		return new FunctionHelper();
	}

}
