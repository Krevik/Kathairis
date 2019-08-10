package io.github.krevik.kathairis.world.dimension.structures.crystal_labirynth;

import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.structure.MineshaftConfig;
import net.minecraft.world.gen.feature.structure.MineshaftStructure;

public class CrystalLabirynthConfig implements IFeatureConfig {
    public final double probability;

    public CrystalLabirynthConfig(double chances) {
        this.probability = chances;
    }
}
