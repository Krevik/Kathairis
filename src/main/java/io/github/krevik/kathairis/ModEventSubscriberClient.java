package io.github.krevik.kathairis;

import io.github.krevik.kathairis.client.ModBlocksColorHandler;
import io.github.krevik.kathairis.util.RenderersRegistry;
import net.minecraft.particles.ParticleType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.registries.IForgeRegistryEntry;

import static io.github.krevik.kathairis.util.ModReference.MOD_ID;
import static net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus.MOD;

@Mod.EventBusSubscriber(modid = MOD_ID, bus = MOD, value=Dist.CLIENT)
public class ModEventSubscriberClient {

    @SubscribeEvent
    public static void registerHandlers(FMLLoadCompleteEvent event){
        ModBlocksColorHandler.registerBlockColors();
        RenderersRegistry.registerParticleRenderers();
    }

    @SubscribeEvent
    public static void onRegisterParticleTypes(final RegistryEvent.Register<ParticleType<?>> event){
        event.getRegistry().registerAll(
                //setup(new BasicParticleType(true), "test")
        );
        //ModParticles.registerParticles();
    }

    @SubscribeEvent
    public static void onRegisterParticleFactories(ParticleFactoryRegisterEvent event){
        //Minecraft.getInstance().particles.registerFactory(ModParticles.TEST, TestParticle.Factory::new);
    }


    public static <T extends IForgeRegistryEntry> T setup(final T entry, final String name) {
        return setup(entry, new ResourceLocation(MOD_ID, name));
    }

    public static <T extends IForgeRegistryEntry> T setup(String name, final T entry) {
        return setup(entry, new ResourceLocation(MOD_ID, name));
    }

    public static <T extends IForgeRegistryEntry> T setup(final T entry, final ResourceLocation registryName) {
        entry.setRegistryName(registryName);
        return entry;
    }
}
