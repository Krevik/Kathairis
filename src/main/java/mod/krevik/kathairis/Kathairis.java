package mod.krevik.kathairis;

import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static mod.krevik.kathairis.util.ModReference.MOD_ID;

/**
 * @author Cadiboo
 */
@Mod(MOD_ID)
public final class Kathairis {

	public static final Logger KATHAIRIS_LOG = LogManager.getLogger(MOD_ID);

	public Kathairis() {
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setupClient);
	}

	private void setup(final FMLCommonSetupEvent event) {

	}

	private void setupClient(final FMLClientSetupEvent event) {
		OBJLoader.INSTANCE.addDomain(MOD_ID);
	}

}
