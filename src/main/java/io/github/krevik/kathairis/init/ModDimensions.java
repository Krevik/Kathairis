package io.github.krevik.kathairis.init;

import io.github.krevik.kathairis.world.dimension.ChunkGeneratorKathairis;
import io.github.krevik.kathairis.world.dimension.ModDimensionKathairis;
import io.github.krevik.kathairis.world.dimension.biome.KatharianBiomeProvider;
import io.github.krevik.kathairis.world.dimension.biome.KatharianBiomeProviderSettings;
import net.minecraft.world.biome.provider.BiomeProviderType;
import net.minecraft.world.gen.ChunkGeneratorType;
import net.minecraft.world.gen.OverworldGenSettings;
import net.minecraftforge.registries.ObjectHolder;

import static io.github.krevik.kathairis.util.ModReference.MOD_ID;
import static io.github.krevik.kathairis.util.ModUtil._null;

/**
 * @author Cadiboo
 */
@ObjectHolder(MOD_ID)
public final class ModDimensions {

    public static final ModDimensionKathairis KATHAIRIS = _null();
    public static final BiomeProviderType<KatharianBiomeProviderSettings, KatharianBiomeProvider> KATHAIRIS_BIOME_PROVIDER_TYPE = _null();
    public static final ChunkGeneratorType<OverworldGenSettings, ChunkGeneratorKathairis> KATHAIRIS_CHUNK_GENERATOR_TYPE = _null();

}
