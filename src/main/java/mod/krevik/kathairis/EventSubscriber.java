package mod.krevik.kathairis;

import com.google.common.base.Preconditions;
import mod.krevik.kathairis.block.BlockCharger;
import mod.krevik.kathairis.block.BlockKatharisPortal;
import mod.krevik.kathairis.block.BlockResource;
import mod.krevik.kathairis.init.ModBlocks;
import mod.krevik.kathairis.item.ItemIngot;
import mod.krevik.kathairis.tileentity.TileEntityCharger;
import mod.krevik.kathairis.util.ModUtil;
import net.minecraft.block.Block;
import net.minecraft.crash.CrashReport;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ReportedException;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;

import javax.annotation.Nonnull;
import java.util.Arrays;

import static mod.krevik.kathairis.Kathairis.KATHAIRIS_LOG;
import static mod.krevik.kathairis.util.ModReference.MOD_ID;

/**
 * @author Cadiboo
 * Subscribe to events that should be handled on both PHYSICAL sides in this class
 */
@Mod.EventBusSubscriber(modid = MOD_ID)
public final class EventSubscriber {

	private static int entityId = 0;

	/* register blocks */
	@SubscribeEvent
	public static void onRegisterBlocksEvent(@Nonnull final RegistryEvent.Register<Block> event) {
		final IForgeRegistry<Block> registry = event.getRegistry();

		registry.register(setup(new BlockResource(6.0F), "titanium_block"));
		registry.register(setup(new BlockResource(3.0F), "titanium_ore"));
		registry.register(setup(new BlockCharger(), "charger"));
		registry.register(setup(new BlockKatharisPortal(), "kathairis_portal"));

		KATHAIRIS_LOG.debug("Registered blocks");

		registerTileEntities();

		KATHAIRIS_LOG.debug("Registered tile entities");

	}

	private static void registerTileEntities() {
		registerTileEntity(TileEntityCharger.class, "charger");
	}

	private static void registerTileEntity(@Nonnull final Class<? extends TileEntity> clazz, String name) {
		try {
			GameRegistry.registerTileEntity(clazz, new ResourceLocation(MOD_ID, name));
		} catch (final Exception exception) {
			CrashReport crashReport = new CrashReport("Error registering Tile Entity " + clazz.getSimpleName(), exception);
			crashReport.makeCategory("Registering Tile Entity");
			throw new ReportedException(crashReport);
		}
	}

	@Nonnull
	private static Block setup(@Nonnull Block block, @Nonnull String name) {
		ModUtil.setRegistryNames(block, name);
		return block;
	}

	/* register items */
	@SubscribeEvent
	public static void onRegisterItemsEvent(@Nonnull final RegistryEvent.Register<Item> event) {
		final IForgeRegistry<Item> registry = event.getRegistry();

		// item blocks
		// make an array of all the blocks we want to have items
		Arrays.stream(new Block[]{

				ModBlocks.TITANIUM_BLOCK,
				ModBlocks.TITANIUM_ORE,
				ModBlocks.CHARGER,
				ModBlocks.KATHAIRIS_PORTAL,

		}).forEach(block -> {
			Preconditions.checkNotNull(block.getRegistryName(), "Registry name cannot be null!");
			registry.register(setup(new ItemBlock(block), block.getRegistryName()));
		});

		// items
		registry.register(setup(new Item(), "titanium_rod"));
		registry.register(setup(new ItemIngot(), "titanium_ingot"));

		registry.register(setup(new ItemIngot(), "revenum_ingot"));

		KATHAIRIS_LOG.debug("Registered items");

	}

	@Nonnull
	private static Item setup(@Nonnull Item item, @Nonnull String name) {
		ModUtil.setRegistryNames(item, name);
		return item;
	}

	@Nonnull
	private static Item setup(@Nonnull final Item item, @Nonnull final ResourceLocation registryName) {
		ModUtil.setRegistryNames(item, registryName);
		return item;
	}

	/* register entities */
	@SubscribeEvent
	public static void onRegisterEntitiesEvent(@Nonnull final RegistryEvent.Register<EntityEntry> event) {
		final IForgeRegistry<EntityEntry> registry = event.getRegistry();

		{
//			final Class clazz = Entity____.class;
//			final ResourceLocation registryName = new ResourceLocation(MOD_ID, ModUtil.getRegistryNameForClass(clazz, "Entity"));
//			registry.register(
//					EntityEntryBuilder.create()
//							.entity(clazz)
//							.id(registryName, entityId++)
//							.name(registryName.getPath())
//							.tracker(range, updateFrequency, sendVelocityUpdates)
//							.egg(primaryColor, secondaryColor)
//							.build()
//			);
		}

		KATHAIRIS_LOG.debug("Registered entities");

	}

}
