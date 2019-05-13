package io.github.krevik.kathairis.particle;

import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.particle.Particle;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Random;

public class ParticleKatharianPortal extends BasicKatharianParticle {

    public ParticleKatharianPortal(KatharianParticleTexture parTexDef, World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
        super(parTexDef, world, posX, posY, posZ, motionX, motionY, motionZ);
    }

    @OnlyIn(Dist.CLIENT)
    public static class Factory implements IParticleFactory<BasicKatharianParticleType> {
        KatharianParticleTexture textureConfig;
        public Factory(KatharianParticleTexture textureDef){
            textureConfig=textureDef;
        }

        public Particle makeParticle(BasicKatharianParticleType p_199234_1_, World p_199234_2_, double p_199234_3_, double p_199234_5_, double p_199234_7_, double p_199234_9_, double p_199234_11_, double p_199234_13_) {
            return new ParticleKatharianPortal(textureConfig,p_199234_2_, p_199234_3_, p_199234_5_, p_199234_7_, p_199234_9_, p_199234_11_, p_199234_13_)
                    .setRotationSpeed(((float) Math.random() - 0.5F) * 0.1F)
                    .setLifeSpan(20 + new Random().nextInt(40))
                    .setGravity(0F)
                    .setScale(2.0F)
                    .setInitialAlpha(1.0F)
                    .setFinalAlpha(0.0F)
                    .setEnableDepth(false);
        }
    }
}
