package io.github.krevik.kathairis.world.dimension.biome;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import io.github.krevik.kathairis.init.ModBiomes;
import io.github.krevik.kathairis.world.dimension.KathairisGenSettings;
import io.github.krevik.kathairis.world.dimension.biome.gen.layers.KatharianLayerUtil;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.biome.provider.OverworldBiomeProvider;
import net.minecraft.world.biome.provider.OverworldBiomeProviderSettings;
import net.minecraft.world.gen.OverworldGenSettings;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.layer.Layer;
import net.minecraft.world.gen.layer.LayerUtil;
import net.minecraft.world.storage.WorldInfo;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class KatharianBiomeProvider extends BiomeProvider {
    private final Layer genBiomes;
    private static final Set<Biome> field_226847_e_ =
            ImmutableSet.of(ModBiomes.KATHAIRIS_RIVER, ModBiomes.KATHARIAN_FOREST, ModBiomes.KATHARIAN_DESERT,
                    ModBiomes.PLAIN_FIELDS, ModBiomes.KATHARIAN_SWAMP);

    public KatharianBiomeProvider(KatharianBiomeProviderSettings settingsProvider) {
        super(field_226847_e_);
        this.genBiomes = KatharianLayerUtil.buildOverworldProcedure(settingsProvider.func_226850_a_(),
                settingsProvider.func_226851_b_(), settingsProvider.getGeneratorSettings());
    }

    public Biome func_225526_b_(int p_225526_1_, int p_225526_2_, int p_225526_3_) {
        return this.genBiomes.func_215738_a(p_225526_1_, p_225526_3_);
    }
}