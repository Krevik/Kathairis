package io.github.krevik.kathairis.init;

import net.minecraft.world.biome.Biome;
import net.minecraftforge.registries.ObjectHolder;

import static io.github.krevik.kathairis.util.ModReference.MOD_ID;
import static io.github.krevik.kathairis.util.ModUtil._null;

/**
 * @author Cadiboo
 */
@ObjectHolder(MOD_ID)
public final class ModBiomes {
	//river
	public static final Biome KATHAIRIS_RIVER = _null();
	//desert
	public static final Biome KATHARIAN_DESERT = _null();
	public static final Biome KATHARIAN_DESERT_EDGE = _null();
	public static final Biome SOFT_SAND_LAKES = _null();
	public static final Biome HUGE_DESERT_MOUNTAINS = _null();
	//forest
	public static final Biome KATHARIAN_FOREST = _null();
	public static final Biome KATHARIAN_DENSE_FOREST = _null();
	//plain fields
	public static final Biome PLAIN_FIELDS = _null();
	//swamp
	public static final Biome KATHARIAN_SWAMP = _null();
}
