package io.github.krevik.kathairis.init;

import io.github.krevik.kathairis.util.ModUtil;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleType;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.registries.ObjectHolder;

import static io.github.krevik.kathairis.util.ModReference.MOD_ID;

@ObjectHolder(MOD_ID)
public class ModParticles {
    public static BasicParticleType TEST = ModUtil._null();

    public static void registerParticles(){
        TEST=register("portal",true);
    }

    private static BasicParticleType register(String key, boolean alwaysShow) {
        return (BasicParticleType)Registry.<ParticleType<? extends IParticleData>>register(Registry.PARTICLE_TYPE, (MOD_ID+":"+key), new BasicParticleType(alwaysShow));
    }

}
