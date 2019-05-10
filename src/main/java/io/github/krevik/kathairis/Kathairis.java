package io.github.krevik.kathairis;

import com.google.common.collect.ImmutableList;
import io.github.krevik.kathairis.client.ClientEventSubscriber;
import io.github.krevik.kathairis.init.*;
import io.github.krevik.kathairis.util.FunctionHelper;
import io.github.krevik.kathairis.util.ModReference;
import io.github.krevik.kathairis.util.RenderersRegistry;
import io.github.krevik.kathairis.util.networking.PacketHandler;
import io.netty.buffer.Unpooled;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.BiomeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

import static io.github.krevik.kathairis.util.ModReference.MOD_ID;

/**
 * @author Cadiboo
 */
@Mod(ModReference.MOD_ID)
public final class Kathairis {

	public static final Logger KATHAIRIS_LOG = LogManager.getLogger(ModReference.MOD_ID);
	public static DimensionType KATH_DIM_TYPE;
	public Kathairis() {
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setupClient);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::loadComplete);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::serverStarting);
		MinecraftForge.EVENT_BUS.register(ClientEventSubscriber.class);
	}

	private void setup(final FMLCommonSetupEvent event) {
		KATH_DIM_TYPE=DimensionManager.registerDimension(new ResourceLocation(MOD_ID,"kath_dim_type"), ModDimensions.KATHAIRIS, new PacketBuffer(Unpooled.buffer(16)));
		ModParticles.registerParticles();
		PacketHandler.register();
	}

	private void loadComplete(final FMLLoadCompleteEvent event){

	}

	private void serverStarting(final FMLServerStartingEvent event){

	}

	private void setupClient(final FMLClientSetupEvent event) {
		OBJLoader.INSTANCE.addDomain(ModReference.MOD_ID);
		RenderersRegistry.registerRenders();
	}

	public static FunctionHelper getHelper(){
		return new FunctionHelper();
	}

}
