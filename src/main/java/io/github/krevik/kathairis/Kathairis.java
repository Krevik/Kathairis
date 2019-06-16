package io.github.krevik.kathairis;

import io.github.krevik.kathairis.client.ModBlocksColorHandler;
import io.github.krevik.kathairis.client.gui.GuiOldMan;
import io.github.krevik.kathairis.init.ModDimensions;
import io.github.krevik.kathairis.init.ModParticles;
import io.github.krevik.kathairis.util.FunctionHelper;
import io.github.krevik.kathairis.util.ModReference;
import io.github.krevik.kathairis.util.RenderersRegistry;
import io.github.krevik.kathairis.util.networking.PacketHandler;
import io.netty.buffer.Unpooled;
import net.minecraft.block.BlockWorkbench;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.ExtensionPoint;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.network.FMLPlayMessages;
import net.minecraftforge.fml.network.NetworkHooks;
import net.minecraftforge.fml.network.NetworkRegistry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.function.Function;
import java.util.function.Supplier;

import static io.github.krevik.kathairis.util.ModReference.MOD_ID;
import static io.github.krevik.kathairis.util.ModUtil._null;

/**
 * @author Cadiboo
 */
@Mod(ModReference.MOD_ID)
public final class Kathairis {


	public static final Logger KATHAIRIS_LOG = LogManager.getLogger(ModReference.MOD_ID);
	public static boolean SHOULD_BLOCKS_SPREAD_AROUND_PORTAL;
	public static DimensionType KATH_DIM_TYPE;

	public Kathairis() {
		ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, ConfigHandler.CONFIG_SPEC);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setupClient);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::loadComplete);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::serverStarting);
		ModLoadingContext.get().registerExtensionPoint(ExtensionPoint.GUIFACTORY, ()->GuiOldMan::new);
	}

	private void setup(final FMLCommonSetupEvent event) {
		//ModDimensions.KATH_DIM_TYPE=DimensionManager.registerDimension(new ResourceLocation(MOD_ID,"kath_dim_type"), ModDimensions.KATHAIRIS, new PacketBuffer(Unpooled.buffer(16)));
	}

	private void loadComplete(final FMLLoadCompleteEvent event){

	}

	private void serverStarting(final FMLServerStartingEvent event){
	}


	private void setupClient(final FMLClientSetupEvent event) {
		RenderersRegistry.registerRenders();
	}

	public static FunctionHelper getHelper(){
		return new FunctionHelper();
	}

}
