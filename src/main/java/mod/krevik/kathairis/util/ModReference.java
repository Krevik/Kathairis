package mod.krevik.kathairis.util;

import mod.krevik.kathairis.Kathairis;
import net.minecraft.util.ResourceLocation;

/**
 * Holds mod-wide constant values
 *
 * @author Cadiboo
 */
@SuppressWarnings("WeakerAccess")
public final class ModReference {

	/**
	 * This is our Mod's Name.
	 */
	public static final String MOD_NAME = "Kathairis";

	/**
	 * This is our Mod's Mod Id that is used for stuff like resource locations.
	 */
	public static final String MOD_ID = "kathairis";

	/**
	 * The fully qualified name of the version of IProxy that gets injected into {@link Kathairis#proxy} on a PHYSICAL CLIENT
	 */
	public static final String CLIENT_PROXY_CLASS = "mod.krevik.kathairis.client.ClientProxy";

	/**
	 * The fully qualified name of the version of IProxy that gets injected into {@link Kathairis#proxy} on a PHYSICAL/DEDICATED SERVER
	 */
	public static final String SERVER_PROXY_CLASS = "mod.krevik.kathairis.server.ServerProxy";

	/**
	 * @see "https://maven.apache.org/enforcer/enforcer-rules/versionRanges.html"
	 */
	public static final String ACCEPTED_VERSIONS = "[1.12.2]";

	/**
	 * @see "https://maven.apache.org/enforcer/enforcer-rules/versionRanges.html"
	 */
	public static final String DEPENDENCIES = "" +
			"required-after:minecraft;" +
			"required-after:forge@[14.23.5.2768,);" +
			"";

	/**
	 * "@VERSION@" is replaced by build.gradle with the actual version
	 *
	 * @see <a href= "https://mcforge.readthedocs.io/en/latest/conventions/versioning/">Forge Versioning Docs</a>
	 */
	public static final String VERSION = "@VERSION@";

	/**
	 * "@FINGERPRINT@" is replaced by build.gradle with the actual fingerprint
	 *
	 * @see "https://tutorials.darkhax.net/tutorials/jar_signing/"
	 */
	public static final String CERTIFICATE_FINGERPRINT = "@FINGERPRINT@";

	public static final int KATHAIRIS_DIMENSION_ID = 123;

	public static final ResourceLocation HAS_ENTERED_KATHAIRIS = new ResourceLocation(MOD_ID, "has_entered_kathairis");

}
