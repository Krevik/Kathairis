package io.github.krevik.kathairis;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import org.apache.commons.lang3.tuple.Pair;

@Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
public class ConfigHandler {

    public static final Config CONFIG;
    public static final ForgeConfigSpec CONFIG_SPEC;

    static
    {
        final Pair<Config, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(Config::new);
        CONFIG_SPEC = specPair.getRight();
        CONFIG = specPair.getLeft();
    }
    public static class Config
    {
        public ForgeConfigSpec.BooleanValue SHOULD_BLOCKS_SPREAD_AROUND_PORTAL;
        Config(ForgeConfigSpec.Builder builder) {

            builder.push("general");
            SHOULD_BLOCKS_SPREAD_AROUND_PORTAL = builder.comment("Defines if kathairis blocks should spread around the portal in overworld and reverse")
                    .translation("config.kathairis.should_blocks_spread_around_portal")
                    .define("should_blocks_spread_around_portal", true);
            builder.pop();
        }
    }

    @SubscribeEvent
    public static void onConfigEvent(ModConfig.ModConfigEvent ev)
    {
        if(ev.getConfig().getSpec() == CONFIG_SPEC)
        {
            Kathairis.SHOULD_BLOCKS_SPREAD_AROUND_PORTAL = ev.getConfig().getConfigData().get("general.should_blocks_spread_around_portal");
        }
    }
}
