package io.github.krevik.kathairis;

import io.github.krevik.kathairis.init.ModDimensions;
import io.github.krevik.kathairis.util.ModReference;
import io.github.krevik.kathairis.world.dimension.ModDimensionKathairis;
import io.netty.buffer.Unpooled;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.event.world.RegisterDimensionsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
	}



	private void setup(final FMLCommonSetupEvent event) {
		KATH_DIM_TYPE=DimensionManager.registerDimension(new ResourceLocation(MOD_ID,"kathairis"), ModDimensions.KATHAIRIS, null);
	}

	private void setupClient(final FMLClientSetupEvent event) {
		OBJLoader.INSTANCE.addDomain(ModReference.MOD_ID);
	}

}
