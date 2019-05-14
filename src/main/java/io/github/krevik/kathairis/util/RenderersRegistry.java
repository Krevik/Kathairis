package io.github.krevik.kathairis.util;

import io.github.krevik.kathairis.client.render.*;
import io.github.krevik.kathairis.client.render.butterfly.*;
import io.github.krevik.kathairis.entity.*;
import io.github.krevik.kathairis.entity.butterfly.*;
import io.github.krevik.kathairis.init.ModParticles;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

@OnlyIn(Dist.CLIENT)
public class RenderersRegistry {

    public static void registerRenders() {
        RenderingRegistry.registerEntityRenderingHandler(EntityLivingFlower.class, new RenderLivingFlower.Factory());
        RenderingRegistry.registerEntityRenderingHandler(EntityMysticBird.class, new RenderMysticBird.Factory());
        RenderingRegistry.registerEntityRenderingHandler(EntityHowler.class, new RenderHowler.Factory());
        RenderingRegistry.registerEntityRenderingHandler(EntityJellyFish.class, new RenderJellyFish.Factory());
        RenderingRegistry.registerEntityRenderingHandler(EntityBigTurtle.class, new RenderBigTurtle.Factory());
        RenderingRegistry.registerEntityRenderingHandler(EntityPoisonousScorpion.class, new RenderPoisonousScorpion.Factory());
        RenderingRegistry.registerEntityRenderingHandler(EntityBison.class, new RenderBison.Factory());
        RenderingRegistry.registerEntityRenderingHandler(EntityButterfly.class, new RenderButterfly.Factory());
        RenderingRegistry.registerEntityRenderingHandler(EntityButterfly1.class, new RenderButterfly1.Factory());
        RenderingRegistry.registerEntityRenderingHandler(EntityStrangeWanderer.class, new RenderStrangeWanderer.Factory());
        RenderingRegistry.registerEntityRenderingHandler(EntitySkylight.class, new RenderSkylight.Factory());
        RenderingRegistry.registerEntityRenderingHandler(EntityCamel.class, new RenderCamel.Factory());
        RenderingRegistry.registerEntityRenderingHandler(EntityFungite.class, new RenderFungite.Factory());
        RenderingRegistry.registerEntityRenderingHandler(EntityCloudOister.class, new RenderCloudOister.Factory());
        RenderingRegistry.registerEntityRenderingHandler(EntityCloudySlime.class, new RenderCloudySlime.Factory());
        RenderingRegistry.registerEntityRenderingHandler(EntityCloudShimmer.class, new RenderCloudShimmer.Factory());
        RenderingRegistry.registerEntityRenderingHandler(EntityFlyingSquid.class, new RenderFlyingSquid.Factory());
        RenderingRegistry.registerEntityRenderingHandler(EntitySkyray.class, new RenderSkyray.Factory());
        RenderingRegistry.registerEntityRenderingHandler(EntityIllukini.class, new RenderIllukini.Factory());
        RenderingRegistry.registerEntityRenderingHandler(EntityRubySile.class, new RenderRubySile.Factory());
        RenderingRegistry.registerEntityRenderingHandler(EntityGecko.class, new RenderGecko.Factory());
        RenderingRegistry.registerEntityRenderingHandler(EntityGaznowel.class, new RenderGaznowel.Factory());
        RenderingRegistry.registerEntityRenderingHandler(EntityCactiSpore.class, new RenderCactiSpore.Factory());
        RenderingRegistry.registerEntityRenderingHandler(EntityPhasm.class, new RenderPhasm.Factory());
        RenderingRegistry.registerEntityRenderingHandler(EntityMysticWandShoot.class, new RenderMysticWandShoot.Factory());
    }

}
