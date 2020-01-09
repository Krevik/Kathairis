package io.github.krevik.kathairis.init;

import io.github.krevik.kathairis.util.ModReference;
import io.github.krevik.kathairis.world.dimension.ChunkGeneratorKathairis;
import io.github.krevik.kathairis.world.dimension.ModDimensionKathairis;
import io.github.krevik.kathairis.world.dimension.biome.KatharianBiomeProvider;
import io.github.krevik.kathairis.world.dimension.biome.KatharianBiomeProviderSettings;
import net.minecraft.world.biome.provider.BiomeProviderType;
import net.minecraft.world.gen.ChunkGeneratorType;
import net.minecraft.world.gen.OverworldGenSettings;
import net.minecraftforge.common.ModDimension;
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
public final class ModDimensions {

    public static final ModDimensionKathairis KATHAIRIS = _null();
    public static final BiomeProviderType<KatharianBiomeProviderSettings, KatharianBiomeProvider> KATHAIRIS_BIOME_PROVIDER_TYPE = _null();
    public static final ChunkGeneratorType<OverworldGenSettings, ChunkGeneratorKathairis> KATHAIRIS_CHUNK_GENERATOR_TYPE = _null();

    @SubscribeEvent
    public static void onRegisterModDimensions(final RegistryEvent.Register<ModDimension> event) {
        event.getRegistry().registerAll(
                new ModDimensionKathairis(ModReference.KATHAIRIS)
                //new ModDimensionKathairis(ModReference.KATHAIRIS)
        );
    }

    @SubscribeEvent
    public static void onRegisterBiomeProviderType(final RegistryEvent.Register<BiomeProviderType<?,?>> event) {
        event.getRegistry().registerAll(
                RegistryHelper.setup(new BiomeProviderType<>(KatharianBiomeProvider::new, KatharianBiomeProviderSettings::new),"kathairis_biome_provider_type")
        );
    }

    @SubscribeEvent
    public static void onRegisterChunkGeneratorType(final RegistryEvent.Register<ChunkGeneratorType<?,?>> event) {
        event.getRegistry().registerAll(
                RegistryHelper.setup(new ChunkGeneratorType<>(ChunkGeneratorKathairis::new,true, KathairisGenSettings::new),"kathairis_chunk_generator_type")
        );
    }

    /*@SubscribeEvent
    public static void onDimensionRegisterEvent(final RegisterDimensionsEvent event){
        Kathairis.KATHAIRIS = DimensionManager.registerDimension(ModReference.KATHAIRIS, ModDimensions.KATHAIRIS, new PacketBuffer(Unpooled.buffer(16)),true);
    }*/
}
