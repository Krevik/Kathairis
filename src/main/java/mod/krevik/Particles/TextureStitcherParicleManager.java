package mod.krevik.Particles;

import mod.krevik.Main.ClientProxy;
import mod.krevik.Main.KCore;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class TextureStitcherParicleManager
{
	  @SubscribeEvent
	  public void stitcherEventPre(TextureStitchEvent.Pre event) {
		  ResourceLocation particleBlueCloud = ClientProxy.particleBlueCloud;
	    ResourceLocation shockingBall = ClientProxy.shockingBall;
	    ResourceLocation shockingParticle = ClientProxy.shockingParticle;
	    ResourceLocation Mystic_Gem_Block_Particle = ClientProxy.Mystic_Gem_Block_Particle;
	    ResourceLocation living_particle = ClientProxy.living_particle;

	    event.getMap().registerSprite(particleBlueCloud);
	    event.getMap().registerSprite(shockingBall);
	    event.getMap().registerSprite(shockingParticle);
	    event.getMap().registerSprite(Mystic_Gem_Block_Particle);
	    event.getMap().registerSprite(living_particle);

	  }
}