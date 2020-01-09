package io.github.krevik.kathairis;

import io.github.krevik.kathairis.init.ModBlocks;
import io.github.krevik.kathairis.init.ModDimensions;
import io.github.krevik.kathairis.init.ModItems;
import io.github.krevik.kathairis.util.ModReference;
import io.github.krevik.kathairis.util.networking.PacketHandler;
import io.github.krevik.kathairis.util.networking.packets.PacketClientOperateFOV;
import io.github.krevik.kathairis.world.dimension.KathairisTeleportingManager;
import io.github.krevik.kathairis.world.dimension.ModDimensionKathairis;
import io.github.krevik.kathairis.world.dimension.PlayerInPortal;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.structure.Structures;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.event.world.RegisterDimensionsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

import java.util.ArrayList;

import static io.github.krevik.kathairis.util.ModReference.MOD_ID;
import static net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus.FORGE;

/**
 * @author Cadiboo
 */
@EventBusSubscriber(modid = MOD_ID, bus = FORGE)
public final class ForgeEventSubscriber {

    @SubscribeEvent
    public static void onRegisterDimensionsEvent(RegisterDimensionsEvent event)
    {
        if (DimensionType.byName(ModReference.KATHAIRIS) == null)
        {
            DimensionManager.registerDimension(ModReference.KATHAIRIS, ModDimensions.KATHAIRIS, null, true);
        }
    }

    private static ArrayList<PlayerInPortal> playersInPortal=new ArrayList<>();
    private static ArrayList<PlayerInPortal> playersToRemove=new ArrayList<>();
    @SubscribeEvent
    public static void playerTeleporting(TickEvent.WorldTickEvent event){
        World world = event.world;
        if(event.side==LogicalSide.SERVER){
            for(ServerPlayerEntity playerEntity:world.getServer().getPlayerList().getPlayers()){
                if(playerEntity.timeUntilPortal>0){
                    if(playerEntity.getEntityWorld().getBlockState(playerEntity.getPosition()).getBlock()==ModBlocks.KATHAIRIS_PORTAL){
                        if(!playersInPortal.isEmpty()){
                            boolean isThatPlayerInTheList=false;
                            for(PlayerInPortal playerInPortal:playersInPortal){
                                if(playerInPortal.getPlayer()==playerEntity){
                                    isThatPlayerInTheList=true;
                                }
                            }
                            if(!isThatPlayerInTheList){
                                playersInPortal.add(new PlayerInPortal(playerEntity,0));
                            }
                        }else{
                            playersInPortal.add(new PlayerInPortal(playerEntity,0));
                        }
                    }
                }
            }
            if(!playersInPortal.isEmpty()){
                for(PlayerInPortal playerInPortal:playersInPortal){
                        playerInPortal.setTimeInPortal(playerInPortal.getTimeInPortal() + 1);
                    if(playerInPortal.getTimeInPortal()>=500){
                            changeDimensionPlayer(playerInPortal.getPlayer());
                            playersToRemove.add(playerInPortal);
                            playerInPortal.setTimeInPortal(600);
                            recoverStandardFOV(playerInPortal);
                    }else{
                        operateFOV(playerInPortal);
                        if(playerInPortal.getPlayer().getEntityWorld().getBlockState(playerInPortal.getPlayer().getPosition()).getBlock()!=ModBlocks.KATHAIRIS_PORTAL){
                            recoverStandardFOV(playerInPortal);
                            playerInPortal.setTimeInPortal(0);
                            playersToRemove.add(playerInPortal);
                        }
                    }
                }
                playersInPortal.removeAll(playersToRemove);
                playersToRemove.clear();
            }
        }
    }

    private static void changeDimensionPlayer(PlayerEntity entity){
        DimensionType type = ModDimensionKathairis.getDimensionType();
        entity.timeUntilPortal=0;
        if(entity.getRidingEntity()==null && !entity.isBeingRidden()){
            entity.setPortal(new BlockPos(entity.posX,entity.posY,entity.posZ));
            if (entity.timeUntilPortal > 0) {
                entity.timeUntilPortal = 10;
            }else if(entity.dimension != type){
                entity.timeUntilPortal=10;
                KathairisTeleportingManager.changeDimPlayer((ServerPlayerEntity) entity, type);
            }else if(entity.dimension == type){
                entity.timeUntilPortal = 10;
                KathairisTeleportingManager.changeDimPlayer((ServerPlayerEntity) entity, DimensionType.OVERWORLD);
            }
        }
    }

    private static void operateFOV(PlayerInPortal playerInPortal){
        if(!playerInPortal.getPlayer().getEntityWorld().isRemote()){
            PacketHandler.sendTo(new PacketClientOperateFOV((playerInPortal.getTimeInPortal()/6)), (ServerPlayerEntity) playerInPortal.getPlayer());
        }
    }

    private static void recoverStandardFOV(PlayerInPortal playerInPortal){
        if(!playerInPortal.getPlayer().getEntityWorld().isRemote()){
            PacketHandler.sendTo(new PacketClientOperateFOV(0), (ServerPlayerEntity) playerInPortal.getPlayer());
        }
    }



    /*@SubscribeEvent
    public static void onEatenEvents(LivingEntityUseItemEvent event){
        if(event.getItem().getItem() == ModItems.HEART){
            event.getEntityLiving().heal(2F);
        }
        if(event.getItem().getItem() == ModItems.MAGIC_BEANS){
            setRandomPotionEffectOnPlayer(event.getEntityLiving());
        }
        if(event.getItem().getItem() == ModItems.NECTAR_BOWL){
            event.getEntityLiving().heal(2f);
        }
    }

    private static void setRandomPotionEffectOnPlayer(LivingEntity ep) {
        Random random = ep.getRNG();
        ArrayList<Effect> allowedEffects = new ArrayList<>();
        allowedEffects.add(Effects.SPEED);
        allowedEffects.add(Effects.HASTE);
        allowedEffects.add(Effects.STRENGTH);
        allowedEffects.add(Effects.INSTANT_HEALTH);
        allowedEffects.add(Effects.JUMP_BOOST);
        allowedEffects.add(Effects.REGENERATION);
        allowedEffects.add(Effects.RESISTANCE);
        allowedEffects.add(Effects.FIRE_RESISTANCE);
        allowedEffects.add(Effects.WATER_BREATHING);
        allowedEffects.add(Effects.INVISIBILITY);
        allowedEffects.add(Effects.NIGHT_VISION);
        allowedEffects.add(Effects.HEALTH_BOOST);
        allowedEffects.add(Effects.ABSORPTION);
        allowedEffects.add(Effects.SATURATION);
        allowedEffects.add(Effects.GLOWING);
        allowedEffects.add(Effects.LUCK);
        allowedEffects.add(Effects.SLOW_FALLING);
        allowedEffects.add(Effects.CONDUIT_POWER);
        allowedEffects.add(Effects.DOLPHINS_GRACE);
        allowedEffects.add(Effects.BAD_OMEN);
        ep.addPotionEffect(new EffectInstance(allowedEffects.get(random.nextInt(allowedEffects.size())),300+random.nextInt(2000),random.nextInt(5)));
    }
/*
	@SubscribeEvent
	public static void writeAssets(final ModelBakeEvent ignored) {

		if (true) {
			return;
		}

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

			final ImmutableList<IBlockState> validStates = block.getStateContainer().getValidStates();
			if (validStates.size() == 1) {
				final String blockstate = "" +
						"{\n" +
						"\t\"variants\": {\n" +
						"\t\t\"\": {\n" +
						"\t\t\t\"model\": \"kathairis:block/" + name + "\"\n" +
						"\t\t}\n" +
						"\t}\n" +
						"}\n";

				blockstates.put(name, blockstate);
			} else {
				StringBuilder blockstate = new StringBuilder("" +
						"{\n" +
						"\t\"variants\": {\n");
				for (int i = 0; i < validStates.size(); i++) {
					final IBlockState validState = validStates.get(i);
					final String propertiesString = validState.getProperties().stream().map(property -> property.getName().toLowerCase() + "=" + validState.get(property).toString().toLowerCase()).collect(Collectors.joining(","));

					blockstate.append("\t\t\"").append(propertiesString).append("\": {\n");
					blockstate.append("\t\t\t\"model\": \"kathairis:block/autogenerated\"\n");
					blockstate.append("\t\t}");
					if (i < validStates.size() - 1)
						blockstate.append(",");
					blockstate.append("\n");
				}

				blockstate.append("" + "\t}\n" + "}\n");

				blockstates.put(name, blockstate.toString());
			}

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
*/
}
