package io.github.krevik.kathairis;

import io.github.krevik.kathairis.util.ModUtil;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.ForgeRegistries;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import static io.github.krevik.kathairis.Kathairis.KATHAIRIS_LOG;
import static io.github.krevik.kathairis.util.ModReference.MOD_ID;
import static net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus.FORGE;

/**
 * @author Cadiboo
 */
@EventBusSubscriber(modid = MOD_ID, bus = FORGE)
public final class ForgeEventSubscriber {

	@SubscribeEvent
	public static void writeAssets(final ModelBakeEvent ignored) {

		if (!ModUtil.isDeveloperEnvironment()) {
			return;
		}

		final String username = System.getProperty("user.name");

		if (!username.equals("Cadiboo")) {
			return;
		}

		final String RESOURCES_DIR = "/Users/" + username + "/Developer/Modding/Kathairis/src/main/resources/";
		final String ASSETS_DIR = RESOURCES_DIR + "assets/" + MOD_ID + "/";
		final String DATA_DIR = RESOURCES_DIR + "data/" + MOD_ID + "/";

		Paths.get(ASSETS_DIR).toFile().mkdirs();
		Paths.get(DATA_DIR).toFile().mkdirs();

		final ResourceLocation ITEM_GENERATED = new ResourceLocation("", "item/generated");
		final ResourceLocation ITEM_HANDHELD = new ResourceLocation("", "item/handheld");
		final String ITEM_DEFAULT_TEXTURE_NAME = "layer0";

		final HashMap<String, String> blockstates = new HashMap<>();

		ModUtil.getModEntries(ForgeRegistries.BLOCKS).forEach(block -> {

			final String name = block.getRegistryName().getPath();

			final String blockstate = "" +
					"{\n" +
					"\t\"variants\": {\n" +
					"\t\t\"\": { \"model\": \"kathairis:block/" + name + "\" }\n" +
					"\t}\n" +
					"}\n";

			blockstates.put(name, blockstate);

		});

		blockstates.forEach((name, state) -> {
			final ArrayList<String> data = new ArrayList<>(Arrays.asList(state.split("\n")));
			data.removeIf(String::isEmpty);

			final Path file = Paths.get(ASSETS_DIR + "blockstates/" + name.toLowerCase() + ".json");
			file.toFile().getParentFile().mkdirs();
			try {
				KATHAIRIS_LOG.info("Writing Blockstate " + name.toLowerCase() + ".json");
				Files.write(file, data, Charset.forName("UTF-8"));
			} catch (final IOException e) {
				e.printStackTrace();
			}

		});

	}

}
