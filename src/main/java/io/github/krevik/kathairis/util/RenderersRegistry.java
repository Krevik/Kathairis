package io.github.krevik.kathairis.util;

import io.github.krevik.kathairis.client.render.*;
import io.github.krevik.kathairis.client.render.butterfly.*;
import io.github.krevik.kathairis.entity.*;
import io.github.krevik.kathairis.entity.butterfly.*;
import io.github.krevik.kathairis.init.ModEntities;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

@OnlyIn(Dist.CLIENT)
public class RenderersRegistry {

    public static void registerRenders() {
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.LIVING_FLOWER, new RenderLivingFlower.Factory());
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.MYSTIC_BIRD, new RenderMysticBird.Factory());
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.HOWLER, new RenderHowler.Factory());
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.JELLY_FISH, new RenderJellyFish.Factory());
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.BIG_TURTLE, new RenderBigTurtle.Factory());
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.POISONOUS_SCORPION, new RenderPoisonousScorpion.Factory());
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.BISON, new RenderBison.Factory());
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.COMMON_BUTTERFLY1, new RenderButterfly.Factory());
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.COMMON_BUTTERFLY2, new RenderButterfly1.Factory());
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.STRANGE_WANDERER, new RenderStrangeWanderer.Factory());
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.SKYLIGHT, new RenderSkylight.Factory());
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.CAMEL, new RenderCamel.Factory());
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.FUNGITE, new RenderFungite.Factory());
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.CLOUD_OISTER, new RenderCloudOister.Factory());
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.CLOUDY_SLIME, new RenderCloudySlime.Factory());
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.CLOUD_SHIMMER, new RenderCloudShimmer.Factory());
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.FLYING_SQUID, new RenderFlyingSquid.Factory());
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.SKYRAY, new RenderSkyray.Factory());
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.ILLUKINI, new RenderIllukini.Factory());
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.RUBY_SILE, new RenderRubySile.Factory());
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.GECKO, new RenderGecko.Factory());
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.GAZNOWEL, new RenderGaznowel.Factory());
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.CACTI_SPORE, new RenderCactiSpore.Factory());
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.PHASM, new RenderPhasm.Factory());
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.MYSTIC_WAND_SHOOT, new RenderMysticWandShoot.Factory());
    }

    public static void registerParticleRenderers(){
        //Minecraft.getInstance().particles.registerFactory(ModParticles.TEST, TestParticle.Factory::new);
        //Minecraft.getInstance().particles.registerFactory(ModParticles.KATH_PORTAL_PARTICLE,new ParticleKatharianPortal.Factory(new KatharianParticleTexture("kath_portal_particle",true,31)));
        //Minecraft.getInstance().particles.registerFactory(ModParticles.FAST_PARTICLE,ParticleFast.Factory::new);
        //Minecraft.getInstance().particles.registerFactory(ModParticles.MYSTIC_WAND_SHOOT,new ParticleMysticWandShoot.Factory(new KatharianParticleTexture("mystic_wand_shoot",true, 6)));
    }

}
