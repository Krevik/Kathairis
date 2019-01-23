package mod.krevik.kathairis;

import mod.krevik.kathairis.util.IProxy;
import mod.krevik.kathairis.util.ModGuiHandler;
import net.minecraftforge.common.ForgeModContainer;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLFingerprintViolationEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static mod.krevik.kathairis.util.ModReference.ACCEPTED_VERSIONS;
import static mod.krevik.kathairis.util.ModReference.CERTIFICATE_FINGERPRINT;
import static mod.krevik.kathairis.util.ModReference.CLIENT_PROXY_CLASS;
import static mod.krevik.kathairis.util.ModReference.DEPENDENCIES;
import static mod.krevik.kathairis.util.ModReference.MOD_ID;
import static mod.krevik.kathairis.util.ModReference.MOD_NAME;
import static mod.krevik.kathairis.util.ModReference.SERVER_PROXY_CLASS;
import static mod.krevik.kathairis.util.ModReference.VERSION;

/**
 * Our main mod class
 *
 * @author Cadiboo
 */
@Mod(
		modid = MOD_ID,
		name = MOD_NAME,
		version = VERSION,
		acceptedMinecraftVersions = ACCEPTED_VERSIONS,
		dependencies = DEPENDENCIES,
		certificateFingerprint = CERTIFICATE_FINGERPRINT
)
public final class Kathairis {

	public static final Logger KATHAIRIS_LOG = LogManager.getLogger(MOD_ID);

	private static final Logger LOGGER = LogManager.getLogger();

	@Instance(MOD_ID)
	public static Kathairis instance;

	@SidedProxy(serverSide = SERVER_PROXY_CLASS, clientSide = CLIENT_PROXY_CLASS)
	public static IProxy proxy;

	/**
	 * Run before anything else. <s>Read your config, create blocks, items, etc, and register them with the GameRegistry</s>
	 *
	 * @param event the event
	 * @see ForgeModContainer#preInit(FMLPreInitializationEvent)
	 */
	@EventHandler
	public void onPreInit(final FMLPreInitializationEvent event) {
		GameRegistry.registerWorldGenerator(new ModWorldGenerator(), 3);
		new ModNetworkManager();
		NetworkRegistry.INSTANCE.registerGuiHandler(this, new ModGuiHandler());

		// register Capabilities if you have any

	}

	/**
	 * Do your mod setup. Build whatever data structures you care about. Register recipes, send FMLInterModComms messages to other mods.
	 *
	 * @param event the event
	 */
	@EventHandler
	public void onInit(final FMLInitializationEvent event) {
		LOGGER.debug("init");
	}

	/**
	 * Mod compatibility, or anything which depends on other mods’ init phases being finished.
	 *
	 * @param event the event
	 * @see ForgeModContainer#postInit(FMLPostInitializationEvent)
	 */
	@EventHandler
	public void onPostInit(final FMLPostInitializationEvent event) {
		LOGGER.debug("postInit");
	}

	/**
	 * Do your invalid fingerprint handling here
	 *
	 * @param event the event
	 * @see "https://tutorials.darkhax.net/tutorials/jar_signing/" and "https://mcforge.readthedocs.io/en/latest/concepts/jarsigning/"
	 */
	@EventHandler
	public void onFingerprintViolation(FMLFingerprintViolationEvent event) {
		KATHAIRIS_LOG.warn("Invalid fingerprint detected! The file " + event.getSource().getName() + " may have been tampered with. This version will NOT be supported by the author!");
	}

}
