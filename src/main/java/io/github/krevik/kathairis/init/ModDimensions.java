package io.github.krevik.kathairis.init;

import io.github.krevik.kathairis.world.dimension.DimensionKathairis;
import io.github.krevik.kathairis.world.dimension.ModDimensionKathairis;
import io.github.krevik.kathairis.world.dimension.biome.KatharianBiomeProvider;
import io.github.krevik.kathairis.world.dimension.biome.KatharianBiomeProviderSettings;
import net.minecraft.world.biome.provider.BiomeProviderType;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.storage.DimensionSavedDataManager;
import net.minecraft.world.storage.WorldSavedDataStorage;
import net.minecraftforge.common.ModDimension;
import net.minecraftforge.registries.ObjectHolder;

import static io.github.krevik.kathairis.util.ModReference.MOD_ID;
import static io.github.krevik.kathairis.util.ModUtil._null;

/**
 * @author Cadiboo
 */
@ObjectHolder(MOD_ID)
public final class ModDimensions {

	public static final ModDimensionKathairis KATHAIRIS = _null();

}
