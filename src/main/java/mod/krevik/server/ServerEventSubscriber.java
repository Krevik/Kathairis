package mod.krevik.server;

/**you can also import constants directly*/

import net.minecraftforge.fml.common.Mod;

import static mod.krevik.KCore.MODID;
import static net.minecraftforge.fml.relauncher.Side.CLIENT;

@Mod.EventBusSubscriber(modid = MODID, value = CLIENT)
public final class ServerEventSubscriber {

}