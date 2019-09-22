package io.github.krevik.kathairis;

import io.github.krevik.kathairis.client.ModBlocksColorHandler;
import io.github.krevik.kathairis.util.RenderersRegistry;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;

import static io.github.krevik.kathairis.util.ModReference.MOD_ID;
import static net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus.MOD;

@Mod.EventBusSubscriber(modid = MOD_ID, bus = MOD, value=Dist.CLIENT)
public class ModEventSubscriberClient {

    @SubscribeEvent
    public static void registerHandlers(FMLLoadCompleteEvent event){
        ModBlocksColorHandler.registerBlockColors();
        RenderersRegistry.registerParticleRenderers();
    }
}
