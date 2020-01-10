package io.github.krevik.kathairis.init;

import io.github.krevik.kathairis.util.ModReference;
import io.github.krevik.kathairis.util.RegistryHelper;
import io.github.krevik.kathairis.world.dimension.biome.biomes.*;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

import static io.github.krevik.kathairis.util.ModReference.MOD_ID;
import static io.github.krevik.kathairis.util.ModUtil._null;

/**
 * @author Cadiboo
 */
@ObjectHolder(MOD_ID)
@Mod.EventBusSubscriber(modid = ModReference.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
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

	@SubscribeEvent
	public static void onRegisterBiomes(final RegistryEvent.Register<Biome> event) {
		event.getRegistry().registerAll(
				RegistryHelper.setup(new BiomeKatharianRiver(),"kathairis_river"),
				RegistryHelper.setup(new BiomeKatharianDesert(),"katharian_desert"),
				RegistryHelper.setup(new BiomeKatharianDesertEdge(),"katharian_desert_edge"),
				RegistryHelper.setup(new BiomeKatharianSoftSandLakes(),"soft_sand_lakes"),
				RegistryHelper.setup(new BiomeKatharianForest(),"katharian_forest"),
				RegistryHelper.setup(new BiomeKatharianDenseForest(),"katharian_dense_forest"),
				RegistryHelper.setup(new BiomeKatharianPlainFields(),"plain_fields"),
				RegistryHelper.setup(new BiomeKatharianSwamps(),"katharian_swamp"),
				RegistryHelper.setup(new BiomeKatharianHugeDesertMountains(),"huge_desert_mountains")
		);
	}
}
