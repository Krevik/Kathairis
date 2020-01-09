package io.github.krevik.kathairis.world.dimension.biome;

import io.github.krevik.kathairis.world.dimension.KathairisGenSettings;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.provider.IBiomeProviderSettings;
import net.minecraft.world.biome.provider.OverworldBiomeProviderSettings;
import net.minecraft.world.gen.OverworldGenSettings;
import net.minecraft.world.storage.WorldInfo;

public class KatharianBiomeProviderSettings extends OverworldBiomeProviderSettings {
    private final long field_226848_a_;
    private final WorldType field_226849_b_;
    private OverworldGenSettings generatorSettings = new OverworldGenSettings();

    public KatharianBiomeProviderSettings(WorldInfo p_i225751_1_) {
        super(p_i225751_1_);
        this.field_226848_a_ = p_i225751_1_.getSeed();
        this.field_226849_b_ = p_i225751_1_.getGenerator();
    }

    public net.minecraft.world.biome.provider.OverworldBiomeProviderSettings setGeneratorSettings(OverworldGenSettings p_205441_1_) {
        this.generatorSettings = p_205441_1_;
        return this;
    }

    public long func_226850_a_() {
        return this.field_226848_a_;
    }

    public WorldType func_226851_b_() {
        return this.field_226849_b_;
    }

    public OverworldGenSettings getGeneratorSettings() {
        return this.generatorSettings;
    }
}