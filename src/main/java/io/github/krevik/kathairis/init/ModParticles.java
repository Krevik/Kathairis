package io.github.krevik.kathairis.init;

import io.github.krevik.kathairis.particle.BasicKatharianParticleType;
import io.github.krevik.kathairis.particle.KatharianParticleTexture;
import io.github.krevik.kathairis.particle.ParticleKatharianPortal;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.ParticleManager;
import net.minecraft.init.Particles;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.IRegistry;
import static io.github.krevik.kathairis.util.ModReference.MOD_ID;

public class ModParticles {
    public static ParticleType<BasicKatharianParticleType> KATH_PORTAL_PARTICLE;

    public static void registerParticles(){
        KATH_PORTAL_PARTICLE = register("kath_portal_particle",false);
    }


    public static void registerParticleRenderers(){
        Minecraft.getInstance().particles.registerFactory(KATH_PORTAL_PARTICLE,new ParticleKatharianPortal.Factory(new KatharianParticleTexture("kath_portal_particle",true,31)));
    }

    private static <T extends ParticleType<?>> T register(String name, boolean alwaysShow) {
        IRegistry.PARTICLE_TYPE.put(new ResourceLocation(MOD_ID,name), new BasicKatharianParticleType(new ResourceLocation(MOD_ID,name), alwaysShow));
        return getRegisteredParticleTypes(name);
    }

    public static <T extends ParticleType<?>> T getRegisteredParticleTypes(String name) {
        T t = (T)IRegistry.PARTICLE_TYPE.get(new ResourceLocation(MOD_ID,name));
        if (t == null) {
            throw new IllegalStateException("Invalid or unknown particle type: " + name);
        } else {
            return t;
        }
    }


}
