package mod.krevik;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Config(modid = KCore.MODID)
@Config.LangKey("kathairis.config.title")
public class ModConfig {

    @Config.Comment("Should blocks from kathairis and overworld be spread around portal.")
    public static boolean shouldBlocksSpreadAroundPortal = true;

    @Mod.EventBusSubscriber(modid = KCore.MODID)
    private static class EventHandler {

        @SubscribeEvent
        public static void onConfigChanged(final ConfigChangedEvent.OnConfigChangedEvent event) {
            if (event.getModID().equals(KCore.MODID)) {
                ConfigManager.sync(KCore.MODID, Config.Type.INSTANCE);
            }
        }
    }
}