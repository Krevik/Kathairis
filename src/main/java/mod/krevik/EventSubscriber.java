package mod.krevik;

import com.google.common.base.Preconditions;
import mod.krevik.client.gui.GuiEnteringKathairis;
import mod.krevik.item.ItemMysticArmor;
import mod.krevik.item.ItemSand;
import mod.krevik.block.BlockChristmasGift;
import mod.krevik.network.KetherPacketHandler;
import mod.krevik.network.PacketDustStormClient;
import mod.krevik.network.PacketSandstormUpdatedOnClient;
import mod.krevik.world.dimension.KetherDataStorage;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiDownloadTerrain;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemSlab;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

@Mod.EventBusSubscriber(modid = KCore.MODID)
public class EventSubscriber {

	public static ArrayList<Biome> biomeList = new ArrayList();
	public static ArrayList<Item> itemList = new ArrayList();

	@SubscribeEvent
	public static void registerBiomes(final RegistryEvent.Register<Biome> event) {
		final IForgeRegistry<Biome> registry = event.getRegistry();
		for (int x = 0; x < biomeList.size(); x++) {
			registry.register(biomeList.get(x));
		}
		BiomeDictionary.addTypes(KCore.instance.MysticForest, BiomeDictionary.Type.FOREST, BiomeDictionary.Type.DENSE);
		BiomeDictionary.addTypes(KCore.instance.MysticPlains, BiomeDictionary.Type.PLAINS, BiomeDictionary.Type.DENSE);
		BiomeDictionary.addTypes(KCore.instance.MysticDesert, BiomeDictionary.Type.PLAINS, BiomeDictionary.Type.DEAD, BiomeDictionary.Type.HOT);
		BiomeDictionary.addTypes(KCore.instance.MysticSwamps, BiomeDictionary.Type.DRY);
		BiomeDictionary.addTypes(KCore.instance.FloatingIslands, BiomeDictionary.Type.MAGICAL, BiomeDictionary.Type.OCEAN);
		BiomeDictionary.addTypes(KCore.instance.KatharianOcean, BiomeDictionary.Type.MAGICAL, BiomeDictionary.Type.OCEAN);

	}

	@SubscribeEvent
	public static void registerPotions(final RegistryEvent.Register<Potion> event) {
		KCore.onRegisterPotions(event);
	}

	@SubscribeEvent
	public static void registerItems(final RegistryEvent.Register<Item> event) {
		final IForgeRegistry<Item> registry = event.getRegistry();
		for (int x = 0; x < itemList.size(); x++) {
			registry.register(itemList.get(x));
		}
		registry.register(KCore.katharian_arrow);
	}

	/**
	 * Register this mod's {@link Block}s.
	 *
	 * @param event The event
	 */
	@SubscribeEvent
	public static void registerBlocks(final RegistryEvent.Register<Block> event) {
		final IForgeRegistry<Block> registry = event.getRegistry();

		final Block[] blocks = {
				KCore.MysticPortal,
				KCore.CorruptedDirt,
				KCore.CorruptedGrass,
				KCore.MythicStone,
				KCore.MysticPlanks,
				KCore.MysticLog,
				KCore.MysticLeaves,
				KCore.MysticSapling,
				KCore.MysticFungus,
				KCore.MysticMiniGrass,
				KCore.MysticTallGrass,
				KCore.MysticFlower,
				KCore.MysticNightFlower,
				KCore.TitaniumBlock,
				KCore.TitaniumOre,
				KCore.MysticMultiGrass,
				KCore.MythicStoneTiles,
				KCore.ShinyLog,
				KCore.ShinyLeaves,
				KCore.ShinyPlanks,
				KCore.ForgottenSand,
				KCore.MovingSand,
				KCore.WeatheredRock,
				KCore.WeatheredRockTiles,
				KCore.HardenedWeatheredRock,
				KCore.WeatheredRockBricks,
				KCore.MysticDeadGrass,
				KCore.GemsOre,
				KCore.ShinyRock,
				KCore.BlueCloud,
				KCore.RevenumOre,
				KCore.VioletCrystal,
				KCore.YellowCrystal,
				KCore.BlueCrystal,
				KCore.BlueFruitPlant,
				KCore.EyePlant,
				KCore.MagicBeans,
				KCore.MythicStonePillar,
				KCore.JadeVine_empty,
				KCore.HardenedWeatheredRockStairs,
				KCore.MysticWoodStairs,
				KCore.ShinyWoodStairs,
				KCore.JadeVine_top,
				KCore.JadeVine_mid,
				KCore.JadeVine_bottom,
				KCore.MythicStoneStairs,
				KCore.MysticWoodFence,
				KCore.ShinyWoodFence,
				KCore.MysticWoodFenceGate,
				KCore.ShinyWoodFenceGate,
				KCore.ShinyTreeSapling,
				KCore.MythicStoneWall,
				KCore.HardenedWeatheredRockWall,
				KCore.MudBlock,
				KCore.MudBricks,
				KCore.ButterflyFlower,
				KCore.BlueCondensedCloud,
				KCore.BlueCloudBricks,
				KCore.YellowCloud,
				KCore.YellowCloudBlock,
				KCore.YellowCondensedCloud,
				KCore.CloudParticleEmitter,
				KCore.Succulent,
				KCore.GooseberryBlock,
				KCore.Charger,
				KCore.EnergyShard,
				KCore.SwampGas,
				KCore.EasterEgg,
				KCore.SoulLog,
				KCore.SoulLeaves,
				KCore.SoulPlanks,
				KCore.SoulSapling,
				KCore.TurtleShellPlate,
				KCore.LuminescentGnarl,
				KCore.BoneBricks,
				KCore.ChiseledBoneBricks,
				KCore.SoulStoneBricks,
				KCore.SoulStoneBricksWithBones,
				KCore.DeadLichen,
				KCore.CursedFlower,
				KCore.SteppedSucculent,
				KCore.MythicStoneStandingSign,
				KCore.MythicStoneWallSign,
				KCore.Magnethium,
				KCore.HardenedWeatheredRockBricksStairs,
				KCore.ShinyLogBark,
				KCore.MysticWoodHalfSlab,
				KCore.MysticWoodDoubleSlab,
				KCore.ShinyWoodHalfSlab,
				KCore.ShinyWoodDoubleSlab,
				KCore.SoulWoodHalfSlab,
				KCore.SoulWoodDoubleSlab,
				KCore.SoulWoodStairs,
				KCore.MysticLogBark,
				KCore.SoulLogBark,
				KCore.Mystic_Gem_Block,
				KCore.Stripped_Mystic_Log,
				KCore.Stripped_Shiny_Log,
				KCore.Stripped_Soul_Log,
				KCore.Mythic_Cobblestone,
				KCore.Mystic_Wood_Trap_Door,
				KCore.Shiny_Wood_Trap_Door,
				KCore.Soul_Wood_Trap_Door,
				KCore.Mystic_Wood_Door,
				KCore.Shiny_Wood_Door,
				KCore.Soul_Wood_Door,
				KCore.Plant_Blue_Cloud,
				KCore.Plant_Yellow_Cloud,
				KCore.Solis_Crystals,
				KCore.Refined_Cloud_Yellow,
				KCore.Refined_Cloud_Blue,
				KCore.Block_Iron_Gold,
				KCore.Gecko_Eggs,
				KCore.Redwood_log_full,
				KCore.Redwood_planks,
				KCore.Redwood_log_size_6,
				KCore.Redwood_log_size_5,
				KCore.Redwood_log_size_4,
				KCore.Redwood_log_size_3,
				KCore.Redwood_log_size_2,
				KCore.Redwood_log_size_1,
				KCore.Mythic_Stone_Bricks,
				KCore.Layered_Sand,
				KCore.bison_Stars,
				KCore.christmas_gift,
				KCore.baurble,
				KCore.snowdrop_Cyprepedium
				//KCore.Hell_Plant
				//KCore.Butterfly_Analysing_Table

		};

		registry.registerAll(blocks);
	}

	/**
	 * Register this mod's {@link ItemBlock}s.
	 *
	 * @param event The event
	 */
	@SubscribeEvent
	public static void registerItemBlocks(final RegistryEvent.Register<Item> event) {
		final ItemBlock[] items = {
				new ItemBlock(KCore.MysticPortal),
				new ItemBlock(KCore.CorruptedDirt),
				new ItemBlock(KCore.CorruptedGrass),
				new ItemBlock(KCore.MythicStone),
				new ItemBlock(KCore.MysticPlanks),
				new ItemBlock(KCore.MysticLog),
				new ItemBlock(KCore.MysticLeaves),
				new ItemBlock(KCore.MysticSapling),
				new ItemBlock(KCore.MysticFungus),
				new ItemBlock(KCore.MysticMiniGrass),
				new ItemBlock(KCore.MysticTallGrass),
				new ItemBlock(KCore.MysticFlower),
				new ItemBlock(KCore.MysticNightFlower),
				new ItemBlock(KCore.TitaniumBlock),
				new ItemBlock(KCore.TitaniumOre),
				new ItemBlock(KCore.MysticMultiGrass),
				new ItemBlock(KCore.MythicStoneTiles),
				new ItemBlock(KCore.ShinyLog),
				new ItemBlock(KCore.ShinyLeaves),
				new ItemBlock(KCore.ShinyPlanks),
				new ItemBlock(KCore.ForgottenSand),
				new ItemBlock(KCore.MovingSand),
				new ItemBlock(KCore.WeatheredRock),
				new ItemBlock(KCore.HardenedWeatheredRock),
				new ItemBlock(KCore.WeatheredRockTiles),
				new ItemBlock(KCore.WeatheredRockBricks),
				new ItemBlock(KCore.MysticDeadGrass),
				new ItemBlock(KCore.GemsOre),
				new ItemBlock(KCore.ShinyRock),
				new ItemBlock(KCore.BlueCloud),
				new ItemBlock(KCore.RevenumOre),
				new ItemBlock(KCore.VioletCrystal),
				new ItemBlock(KCore.YellowCrystal),
				new ItemBlock(KCore.BlueCrystal),
				new ItemBlock(KCore.BlueFruitPlant),
				new ItemBlock(KCore.EyePlant),
				new ItemBlock(KCore.MagicBeans),
				new ItemBlock(KCore.MythicStonePillar),
				new ItemBlock(KCore.JadeVine_empty),
				new ItemBlock(KCore.HardenedWeatheredRockStairs),
				new ItemBlock(KCore.MysticWoodStairs),
				new ItemBlock(KCore.ShinyWoodStairs),
				new ItemBlock(KCore.JadeVine_top),
				new ItemBlock(KCore.JadeVine_mid),
				new ItemBlock(KCore.JadeVine_bottom),
				new ItemBlock(KCore.MythicStoneStairs),
				new ItemBlock(KCore.MysticWoodFence),
				new ItemBlock(KCore.ShinyWoodFence),
				new ItemBlock(KCore.MysticWoodFenceGate),
				new ItemBlock(KCore.ShinyWoodFenceGate),
				new ItemBlock(KCore.ShinyTreeSapling),
				new ItemBlock(KCore.MythicStoneWall),
				new ItemBlock(KCore.HardenedWeatheredRockWall),
				new ItemBlock(KCore.MudBlock),
				new ItemBlock(KCore.MudBricks),
				new ItemBlock(KCore.ButterflyFlower),
				new ItemBlock(KCore.BlueCondensedCloud),
				new ItemBlock(KCore.BlueCloudBricks),
				new ItemBlock(KCore.YellowCloud),
				new ItemBlock(KCore.YellowCondensedCloud),
				new ItemBlock(KCore.YellowCloudBlock),
				new ItemBlock(KCore.CloudParticleEmitter),
				new ItemBlock(KCore.Succulent),
				new ItemBlock(KCore.GooseberryBlock),
				new ItemBlock(KCore.Charger),
				new ItemBlock(KCore.EnergyShard),
				new ItemBlock(KCore.SwampGas),
				new ItemBlock(KCore.EasterEgg),
				new ItemBlock(KCore.SoulLog),
				new ItemBlock(KCore.SoulLeaves),
				new ItemBlock(KCore.SoulPlanks),
				new ItemBlock(KCore.SoulSapling),
				new ItemBlock(KCore.TurtleShellPlate),
				new ItemBlock(KCore.LuminescentGnarl),
				new ItemBlock(KCore.BoneBricks),
				new ItemBlock(KCore.ChiseledBoneBricks),
				new ItemBlock(KCore.SoulStoneBricks),
				new ItemBlock(KCore.SoulStoneBricksWithBones),
				new ItemBlock(KCore.DeadLichen),
				new ItemBlock(KCore.CursedFlower),
				new ItemBlock(KCore.SteppedSucculent),
				new ItemBlock(KCore.Magnethium),
				new ItemBlock(KCore.HardenedWeatheredRockBricksStairs),
				new ItemBlock(KCore.ShinyLogBark),
				new ItemSlab(KCore.MysticWoodHalfSlab, KCore.MysticWoodHalfSlab, KCore.MysticWoodDoubleSlab),
				new ItemSlab(KCore.ShinyWoodHalfSlab, KCore.ShinyWoodHalfSlab, KCore.ShinyWoodDoubleSlab),
				new ItemSlab(KCore.SoulWoodHalfSlab, KCore.SoulWoodHalfSlab, KCore.SoulWoodDoubleSlab),
				new ItemBlock(KCore.SoulWoodStairs),
				new ItemBlock(KCore.MysticLogBark),
				new ItemBlock(KCore.SoulLogBark),
				new ItemBlock(KCore.Mystic_Gem_Block),
				new ItemBlock(KCore.Stripped_Mystic_Log),
				new ItemBlock(KCore.Stripped_Shiny_Log),
				new ItemBlock(KCore.Stripped_Soul_Log),
				new ItemBlock(KCore.Mythic_Cobblestone),
				new ItemBlock(KCore.Mystic_Wood_Trap_Door),
				new ItemBlock(KCore.Shiny_Wood_Trap_Door),
				new ItemBlock(KCore.Soul_Wood_Trap_Door),
				new ItemBlock(KCore.Plant_Blue_Cloud),
				new ItemBlock(KCore.Plant_Yellow_Cloud),
				new ItemBlock(KCore.Mystic_Wood_Door),
				new ItemBlock(KCore.Shiny_Wood_Door),
				new ItemBlock(KCore.Soul_Wood_Door),
				new ItemBlock(KCore.Solis_Crystals),
				new ItemBlock(KCore.Refined_Cloud_Blue),
				new ItemBlock(KCore.Refined_Cloud_Yellow),
				new ItemBlock(KCore.Block_Iron_Gold),
				new ItemBlock(KCore.Gecko_Eggs),
				new ItemBlock(KCore.Redwood_log_full),
				new ItemBlock(KCore.Redwood_planks),
				new ItemBlock(KCore.Redwood_log_size_6),
				new ItemBlock(KCore.Redwood_log_size_5),
				new ItemBlock(KCore.Redwood_log_size_4),
				new ItemBlock(KCore.Redwood_log_size_3),
				new ItemBlock(KCore.Redwood_log_size_2),
				new ItemBlock(KCore.Redwood_log_size_1),
				new ItemBlock(KCore.Mythic_Stone_Bricks),
				new ItemSand(KCore.Layered_Sand),
				new ItemBlock(KCore.bison_Stars),
				new ItemBlock(KCore.christmas_gift),
				new ItemBlock(KCore.baurble),
				new ItemBlock(KCore.snowdrop_Cyprepedium)
				//new ItemBlock(KCore.Hell_Plant)
				//new ItemBlock(KCore.Butterfly_Analysing_Table)
		};

		final IForgeRegistry<Item> registry = event.getRegistry();

		for (final ItemBlock item : items) {
			final Block block = item.getBlock();
			final ResourceLocation registryName = Preconditions.checkNotNull(block.getRegistryName(), "Block %s has null registry name", block);
			registry.register(item.setRegistryName(registryName));
		}

	}

	@SubscribeEvent
	public static void onEvent1(TickEvent.WorldTickEvent event)
	{
		KCore.instance.updateRendererCount++;
		KetherDataStorage data = KCore.data.getDataInstance(event.world);

		if(!event.world.isRemote){
			data = KCore.data.getDataInstance(event.world);
			if(data.getIsSandstorm()) {
				PacketSandstormUpdatedOnClient message = new PacketSandstormUpdatedOnClient(data.getIsSandstorm(), data.getSandstormX(), data.getSandstormTime(), data.getSandstormZ());
				KetherPacketHandler.CHANNEL.sendToAll(message);
			}
		}

		if(data!=null&&event.world.getTotalWorldTime()>100) {
			if (!data.getIsSandstorm()) {
				if (event.world.rand.nextInt(500000) == 0) {
					if (!event.world.isRemote) {
						data = KCore.data.getDataInstance(event.world);
						float time = 9000 + event.world.rand.nextInt(20000);
						double X = (event.world.rand.nextDouble() - event.world.rand.nextDouble());
						double Z = (event.world.rand.nextDouble() - event.world.rand.nextDouble());
						data.setSandstormTime((int) time);
						data.setSandstormX(X);
						data.setSandstormZ(Z);
						data.setIsSandstorm(true);
						PacketSandstormUpdatedOnClient message = new PacketSandstormUpdatedOnClient(true, X, time, Z);
						KetherPacketHandler.CHANNEL.sendToAll(message);
					}
				}
			}
		}
		if(data.getSandstormTime()>0) {
			data.setIsSandstorm(true);
			data.setSandstormTime(data.getSandstormTime()-1);
			if(!data.getIsSandstorm()) {
				data.setSandstormTime(0);
			}
		}else {
			data.setSandstormTime(0);
			data.setIsSandstorm(false);
			data.setSandstormX(0);
			data.setSandstormZ(0);
		}

		if(data.getIsSandstorm()) {
			IMessage message2 = new PacketDustStormClient();
			KetherPacketHandler.CHANNEL.sendToAll(message2);
		}
		if(data.getIsSandstorm()){
			if(!event.world.isRemote){
				for(EntityPlayer player:event.world.getMinecraftServer().getPlayerList().getPlayers()) {
					if(event.world.getBiome(player.getPosition())==KCore.MysticDesert) {
						if (player.motionY == 0D) {
							player.motionX += data.getSandstormX() * 0.0080;
							player.motionZ += data.getSandstormZ() * 0.0080;
							player.velocityChanged = true;
						} else {
							player.motionX += data.getSandstormX() * 0.0017;
							player.motionZ += data.getSandstormZ() * 0.0017;
							player.velocityChanged = true;
						}
						player.addPotionEffect(new PotionEffect(Potion.getPotionById(15), 100, 100));
					}
				}
			}
		}
	}

	static int month = Calendar.getInstance().get(Calendar.MONTH);
	private static IBlockState pickRandomPresentState(Random random){
		int k=random.nextInt(3);
		if(k==0){
			return KCore.christmas_gift.getDefaultState().withProperty(BlockChristmasGift.VARIANT,BlockChristmasGift.EnumType.RED);
		}
		else if(k==1){
			return KCore.christmas_gift.getDefaultState().withProperty(BlockChristmasGift.VARIANT,BlockChristmasGift.EnumType.YELLOW);
		}
		else if(k==2){
			return KCore.christmas_gift.getDefaultState().withProperty(BlockChristmasGift.VARIANT,BlockChristmasGift.EnumType.VIOLET);
		}else{
			return KCore.christmas_gift.getDefaultState().withProperty(BlockChristmasGift.VARIANT,BlockChristmasGift.EnumType.VIOLET);
		}
	}
	@SubscribeEvent
	public static void onEvent2(TickEvent.PlayerTickEvent event)
	{
		if(event.player!=null){
			if(KCore.functionHelper.random.nextInt(400000)==0){
				if(month==11){
					BlockPos tmp = new BlockPos(event.player.posX-16+KCore.functionHelper.random.nextInt(32),event.player.posY,event.player.posZ-16+KCore.instance.functionHelper.random.nextInt(32));
					BlockPos ground = event.player.world.getHeight(tmp);
					if(event.player.dimension==KCore.DIMENSION_ID) {
						if (event.player.world.isAirBlock(ground) && event.player.world.isBlockFullCube(ground.down())) {
							event.player.world.setBlockState(ground, pickRandomPresentState(KCore.functionHelper.random), 2);
						}
					}
				}
			}
		}
		if(event.player!=null) {
			if(!event.player.inventory.getStackInSlot(36).isEmpty()) {
				if(event.player.inventory.getStackInSlot(36).getItem().equals(KCore.CloudBoots)) {
					event.player.fallDistance=0;
					if(event.player.onGround) {
						((ItemMysticArmor)(event.player.inventory.getStackInSlot(36).getItem())).wereCloudBootsUsed=false;
					}
				}
			}
			if(event.player.world.isRemote) {
				KetherDataStorage data = KCore.data.getDataInstance(event.player.getEntityWorld());
				if (data != null) {
					if (data.getIsSandstorm()) {
						if (event.player.world.getBiome(event.player.getPosition()) == KCore.MysticDesert) {
							if (event.player.getRNG().nextInt(350) == 0) {
								event.player.playSound(KCore.instance.proxy.sandstorm, 1, 1);
							}
						}
					}
				}
			}
		}
	}




	/*@SubscribeEvent
	public static void onRenderPre(RenderGameOverlayEvent.Pre event) {

	}
	@SubscribeEvent
	public static void onRenderPost(RenderGameOverlayEvent.Post event){

	}*/


	@SubscribeEvent
	public static void blockBreakEvent(BlockEvent.HarvestDropsEvent event) {
		if (!event.getWorld().isRemote) {
			EntityPlayer breaker = event.getHarvester();
			if (breaker != null) {
				ItemStack heldStack = breaker.getHeldItemMainhand();
				Item heldItem = heldStack.getItem();
				if (heldItem.equals(KCore.Magnethium_Axe) || heldItem.equals(KCore.Magnethium_Pickaxe) || heldItem.equals(KCore.Magnethium_Shovel)) {
					event.setDropChance(0);

					double x = event.getPos().getX() + 0.5;
					double y = event.getPos().getY() + 0.5;
					double z = event.getPos().getZ() + 0.5;

					for (ItemStack itemStack : event.getDrops()) {
						EntityItem currentItem = new EntityItem(breaker.world, x, y, z, itemStack);

						if (heldItem.equals(KCore.Magnethium_Axe)) {
							currentItem.motionX = breaker.posX - currentItem.posX;
							currentItem.motionY = breaker.posY - currentItem.posY;
							currentItem.motionZ = breaker.posZ - currentItem.posZ;
						} else if (heldItem.equals(KCore.Magnethium_Pickaxe)) {
							currentItem.setNoGravity(true);
							currentItem.motionX = 0;
							currentItem.motionY = -0.01;
							currentItem.motionZ = 0;
						} else if (heldItem.equals(KCore.Magnethium_Shovel)) {
							currentItem.setNoGravity(true);
							currentItem.motionX = 0;
							currentItem.motionY = 0;
							currentItem.motionZ = 0;
						}
						breaker.world.spawnEntity(currentItem);
					}
				}
			}
		}
	}

	@SubscribeEvent
	public static void healAnimalsWithHeartEvent(PlayerInteractEvent.EntityInteract event){
		ItemStack itemstack = event.getEntityPlayer().getHeldItem(EnumHand.MAIN_HAND);
		if(itemstack.getItem().equals(KCore.Heart)	){
			Entity entity = event.getTarget();
			if(entity instanceof EntityLivingBase){
				EntityLivingBase animal = (EntityLivingBase) entity;
				if(animal.getHealth()<animal.getMaxHealth()){
					if(!event.getEntityPlayer().world.isRemote) {
						animal.heal(1);
						if (!event.getEntityPlayer().capabilities.isCreativeMode) {
							itemstack.shrink(1);
						}
					}
					KCore.instance.functionHelper.playTameEffect(event.getEntityPlayer().world,KCore.instance.functionHelper.random,animal,true);
				}
			}
		}
	}

	@SubscribeEvent
	public static void handleServerTick(TickEvent.ServerTickEvent event){

	}

	@SubscribeEvent
	public static void onChangeDimensionEvent(PlayerEvent.PlayerChangedDimensionEvent event){
		if(event.player!=null){

		}
	}









	static int ticker=0;
	@SubscribeEvent
	public static void handlePotionEffectsOnEntityLiving(LivingEvent.LivingUpdateEvent  event){
		if(event.getEntityLiving()!=null) {
			EntityLivingBase entityLivingBaseIn = event.getEntityLiving();
			if (entityLivingBaseIn.isPotionActive(KCore.stun_potion)) {
				entityLivingBaseIn.setJumping(false);
				entityLivingBaseIn.setPositionAndUpdate(entityLivingBaseIn.prevPosX, entityLivingBaseIn.prevPosY, entityLivingBaseIn.prevPosZ);
			}
			if (entityLivingBaseIn.isPotionActive(KCore.dissolution_potion)) {
				ticker++;
				if (ticker >= 40) {
					ticker = 0;
					if (entityLivingBaseIn instanceof EntityPlayer) {
						EntityPlayer player = (EntityPlayer) entityLivingBaseIn;
						player.addExhaustion(10);
						player.addPotionEffect(new PotionEffect(KCore.dissolution_potion, 1000, 1));
					}
				}
			}
		}
	}

	@SubscribeEvent
	public static void handlePotionsAgain(LivingEntityUseItemEvent.Finish event){
		if(event.getItem().getItem() instanceof ItemFood){
			ItemFood food = (ItemFood) event.getItem().getItem();
			if(event.getEntityLiving().isPotionActive(KCore.dissolution_potion) && event.getEntityLiving() instanceof EntityPlayer){
				EntityPlayer player = (EntityPlayer) event.getEntityLiving();
				player.getFoodStats().setFoodLevel(player.getFoodStats().getFoodLevel()-food.getHealAmount(event.getItem()));
			}
		}
	}

	@SubscribeEvent
	public static void denyMilkBucketWhileDissolution(LivingEntityUseItemEvent.Tick event){
		if(event.getItem().getItem().equals(Items.MILK_BUCKET)){
			if(event.getEntityLiving().isPotionActive(KCore.dissolution_potion)){
				event.setDuration(999999);
			}
		}
	}

	@SubscribeEvent
	public static void handleInteracts(PlayerInteractEvent.EntityInteractSpecific event){

	}

	/*@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public static void doButterflyCatcherHandling(){

	}*/

	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public static void messageDimensionChange(GuiOpenEvent event){
		if(event.getGui() instanceof GuiDownloadTerrain){
			if(Minecraft.getMinecraft().player.dimension==KCore.DIMENSION_ID){
				event.setGui(new GuiEnteringKathairis());
			}
		}
	}

}
