package io.github.krevik.kathairis.init;

import io.github.krevik.kathairis.particle.*;
import net.minecraft.client.Minecraft;
import net.minecraft.particles.ParticleType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.IRegistry;

import static io.github.krevik.kathairis.util.ModReference.MOD_ID;

public class ModParticles {
    public static ParticleType<BasicKatharianParticleType> KATH_PORTAL_PARTICLE;
    public static ParticleType<BasicKatharianParticleType> FAST_PARTICLE;
    public static ParticleType<BasicKatharianParticleType> MYSTIC_WAND_SHOOT;

    public static void registerParticles(){
        KATH_PORTAL_PARTICLE = register("kath_portal_particle",false);
        FAST_PARTICLE = register("fast_particle",false);
        MYSTIC_WAND_SHOOT = register("mystic_wand_shoot",false);
    }


    public static void registerParticleRenderers(){
        Minecraft.getInstance().particles.registerFactory(KATH_PORTAL_PARTICLE,new ParticleKatharianPortal.Factory(new KatharianParticleTexture("kath_portal_particle",true,31)));
        Minecraft.getInstance().particles.registerFactory(FAST_PARTICLE,new ParticleFast.Factory(new KatharianParticleTexture("fast_particle",false, 1)));
        Minecraft.getInstance().particles.registerFactory(MYSTIC_WAND_SHOOT,new ParticleMysticWandShoot.Factory(new KatharianParticleTexture("mystic_wand_shoot",true, 6)));
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
