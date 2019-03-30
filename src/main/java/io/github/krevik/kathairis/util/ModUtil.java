package io.github.krevik.kathairis.util;

import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;
import net.minecraftforge.registries.ObjectHolder;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static io.github.krevik.kathairis.util.ModReference.MOD_ID;

/**
 * @author Cadiboo
 */
public final class ModUtil {

	/**
	 * Suppresses IDE warnings and suggestions about objects that are going to be filled by @{@link ObjectHolder} being null
	 *
	 * @return null
	 */
	@Nonnull
	public static <T> T _null() {
		return null;
	}

	public static boolean isDeveloperEnvironment() {
		final String target = System.getenv().get("target");
		if (target == null) {
			return false;
		}
		return target.contains("userdev");
	}

	public static <T extends IForgeRegistryEntry<T>> List<T> getModEntries(final IForgeRegistry<T> registry) {
		return registry.getValues().stream()
				.filter(entry -> Objects.requireNonNull(entry.getRegistryName()).getNamespace().equals(MOD_ID))
				.collect(Collectors.toList());
	}

}
