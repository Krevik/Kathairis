package mod.krevik.kathairis.world.dimension.biome;

import mod.krevik.kathairis.world.dimension.KatharianGenSettings;
import net.minecraft.world.biome.provider.IBiomeProviderSettings;
import net.minecraft.world.biome.provider.OverworldBiomeProviderSettings;
import net.minecraft.world.gen.OverworldGenSettings;
import net.minecraft.world.storage.WorldInfo;

public class KatharianBiomeProviderSettings extends OverworldBiomeProviderSettings {
    private WorldInfo worldInfo;
    private KatharianGenSettings generatorSettings;

    public KatharianBiomeProviderSettings() {
    }

    public KatharianBiomeProviderSettings setWorldInfo(WorldInfo p_205439_1_) {
        this.worldInfo = p_205439_1_;
        return this;
    }

    public KatharianBiomeProviderSettings setGeneratorSettings(KatharianGenSettings p_205441_1_) {
        this.generatorSettings = p_205441_1_;
        return this;
    }

    public WorldInfo getWorldInfo() {
        return this.worldInfo;
    }

    public KatharianGenSettings getGeneratorSettings() {
        return this.generatorSettings;
    }
}
