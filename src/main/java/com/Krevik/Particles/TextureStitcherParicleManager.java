package com.Krevik.Particles;

import com.Krevik.Main.KCore;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class TextureStitcherParicleManager
{

	  @SubscribeEvent
	  public void stitcherEventPre(TextureStitchEvent.Pre event) {
	    ResourceLocation particleBlueCloud = KCore.instance.cproxy.particleBlueCloud;
	    ResourceLocation shockingBall = KCore.instance.cproxy.shockingBall;
	    ResourceLocation shockingParticle = KCore.instance.cproxy.shockingParticle;
	    ResourceLocation swampGasParticle = KCore.instance.cproxy.swampGasParticle;
	    ResourceLocation soulTreeParticle = KCore.instance.cproxy.soulTreeParticle;
	    ResourceLocation Mystic_Gem_Block_Particle = KCore.instance.cproxy.Mystic_Gem_Block_Particle;
	    ResourceLocation dust_particle = KCore.instance.cproxy.dust_particle;

	    event.getMap().registerSprite(particleBlueCloud);
	    event.getMap().registerSprite(shockingBall);
	    event.getMap().registerSprite(shockingParticle);
	    event.getMap().registerSprite(swampGasParticle);
	    event.getMap().registerSprite(soulTreeParticle);
	    event.getMap().registerSprite(Mystic_Gem_Block_Particle);
	    event.getMap().registerSprite(dust_particle);

	  }
}