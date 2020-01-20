package io.github.krevik.kathairis;

import io.github.krevik.kathairis.client.ModBlocksColorHandler;
import io.github.krevik.kathairis.init.ModBlocks;
import io.github.krevik.kathairis.util.RenderersRegistry;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.particles.ParticleType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
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

    //Cadiboo thanks a lot!
    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent event) {
        RenderTypeLookup.setRenderLayer(ModBlocks.KATHAIRIS_MULTI_GRASS, RenderType.func_228645_f_());
        RenderTypeLookup.setRenderLayer(ModBlocks.KATHAIRIS_TALL_GRASS, RenderType.func_228645_f_());
        RenderTypeLookup.setRenderLayer(ModBlocks.BLUE_CLOUD, RenderType.func_228645_f_());
        RenderTypeLookup.setRenderLayer(ModBlocks.YELLOW_CLOUD, RenderType.func_228645_f_());
        RenderTypeLookup.setRenderLayer(ModBlocks.BLUE_CLOUD_BRICKS, RenderType.func_228645_f_());
        RenderTypeLookup.setRenderLayer(ModBlocks.YELLOW_CLOUD_BLOCK, RenderType.func_228645_f_());
        RenderTypeLookup.setRenderLayer(ModBlocks.KATHAIRIS_MINI_GRASS, RenderType.func_228645_f_());
        RenderTypeLookup.setRenderLayer(ModBlocks.VILYRIA, RenderType.func_228645_f_());
        RenderTypeLookup.setRenderLayer(ModBlocks.KATHAIRIS_GRASS, RenderType.func_228645_f_());
        RenderTypeLookup.setRenderLayer(ModBlocks.ELDERWILLOW_LEAVES, RenderType.func_228645_f_());
        RenderTypeLookup.setRenderLayer(ModBlocks.MYSTIC_LEAVES, RenderType.func_228645_f_());
        RenderTypeLookup.setRenderLayer(ModBlocks.SHINY_LEAVES, RenderType.func_228645_f_());
        RenderTypeLookup.setRenderLayer(ModBlocks.SOUL_LEAVES, RenderType.func_228645_f_());
        RenderTypeLookup.setRenderLayer(ModBlocks.BUTTERFLY_FLOWER, RenderType.func_228645_f_());
        RenderTypeLookup.setRenderLayer(ModBlocks.GLOWVINES, RenderType.func_228645_f_());
        RenderTypeLookup.setRenderLayer(ModBlocks.FLUO_FUNGI, RenderType.func_228645_f_());
        RenderTypeLookup.setRenderLayer(ModBlocks.KATHAIRIS_NIGHT_FLOWER, RenderType.func_228645_f_());
        RenderTypeLookup.setRenderLayer(ModBlocks.PURPLE_PALM, RenderType.func_228645_f_());
        RenderTypeLookup.setRenderLayer(ModBlocks.BRINE_PUSTULE, RenderType.func_228645_f_());
        RenderTypeLookup.setRenderLayer(ModBlocks.KATHAIRIS_SUCCULENT, RenderType.func_228645_f_());
        RenderTypeLookup.setRenderLayer(ModBlocks.SHINY_SAPLING, RenderType.func_228645_f_());
        RenderTypeLookup.setRenderLayer(ModBlocks.SOUL_SAPLING, RenderType.func_228645_f_());
        RenderTypeLookup.setRenderLayer(ModBlocks.MYSTIC_SAPLING, RenderType.func_228645_f_());
        RenderTypeLookup.setRenderLayer(ModBlocks.ELDERWILLOW_SAPLING, RenderType.func_228645_f_());
        RenderTypeLookup.setRenderLayer(ModBlocks.ROCKTUS, RenderType.func_228645_f_());
        RenderTypeLookup.setRenderLayer(ModBlocks.WILLOW_VINE_MAIN, RenderType.func_228645_f_());
        RenderTypeLookup.setRenderLayer(ModBlocks.WILLOW_VINE_TIP, RenderType.func_228645_f_());
        RenderTypeLookup.setRenderLayer(ModBlocks.FOREST_CANDLE, RenderType.func_228645_f_());
        RenderTypeLookup.setRenderLayer(ModBlocks.GOOSEBERRY_BUSH, RenderType.func_228645_f_());
        RenderTypeLookup.setRenderLayer(ModBlocks.BISON_STARS, RenderType.func_228645_f_());
        RenderTypeLookup.setRenderLayer(ModBlocks.FRUP_PLANT, RenderType.func_228645_f_());
        RenderTypeLookup.setRenderLayer(ModBlocks.EYE_PLANT, RenderType.func_228645_f_());

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
