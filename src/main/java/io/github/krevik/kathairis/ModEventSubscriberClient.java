package io.github.krevik.kathairis;

import io.github.krevik.kathairis.client.ModBlocksColorHandler;
import io.github.krevik.kathairis.init.ModBiomes;
import io.github.krevik.kathairis.init.ModParticles;
import io.github.krevik.kathairis.particle.KatharianParticleTexture;
import io.github.krevik.kathairis.particle.ParticleFast;
import io.github.krevik.kathairis.particle.ParticleKatharianPortal;
import io.github.krevik.kathairis.particle.ParticleMysticWandShoot;
import io.github.krevik.kathairis.util.RenderersRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;

import java.util.ArrayList;

import static io.github.krevik.kathairis.util.ModReference.MOD_ID;
import static net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus.FORGE;
import static net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus.MOD;

@Mod.EventBusSubscriber(modid = MOD_ID, bus = MOD, value=Dist.CLIENT)
public class ModEventSubscriberClient {
    @SubscribeEvent
    public static void registerHandlers(FMLLoadCompleteEvent event){
        ModBlocksColorHandler.registerBlockColors();
        RenderersRegistry.registerParticleRenderers();
    }
}
