package mod.krevik.server;

/**you can also import constants directly*/
import static mod.krevik.KCore.MODID;
import static net.minecraftforge.fml.relauncher.Side.CLIENT;

import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = MODID, value = CLIENT)
public final class ServerEventSubscriber {

}