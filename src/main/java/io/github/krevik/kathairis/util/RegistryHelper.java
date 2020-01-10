package io.github.krevik.kathairis.util;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.IForgeRegistryEntry;

import static io.github.krevik.kathairis.util.ModReference.MOD_ID;

public class RegistryHelper {

    public static <T extends IForgeRegistryEntry> T setup(final T entry, final String name) {
        return setup(entry, new ResourceLocation(MOD_ID, name));
    }

    public static <T extends IForgeRegistryEntry> T setup(String name, final T entry) {
        return setup(entry, new ResourceLocation(MOD_ID, name));
    }

    public static <T extends IForgeRegistryEntry> T setup(final T entry, final ResourceLocation registryName) {
        entry.setRegistryName(registryName);
        return entry;
    }
}