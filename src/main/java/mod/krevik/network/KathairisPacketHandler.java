package mod.krevik.network;

import mod.krevik.KCore;
import mod.krevik.network.packets.*;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class KathairisPacketHandler {
	public static final SimpleNetworkWrapper CHANNEL = NetworkRegistry.INSTANCE.newSimpleChannel(KCore.MODID);

	public static void init() {
		int id = 0;
		CHANNEL.registerMessage(PacketDeathHandlerServer.Handler.class, PacketDeathHandlerServer.class, id++, Side.SERVER);
		CHANNEL.registerMessage(PacketDeathHandlerClient.Handler.class, PacketDeathHandlerClient.class, id++, Side.CLIENT);
		CHANNEL.registerMessage(PacketSpawnDeathServer.Handler.class, PacketSpawnDeathServer.class, id++, Side.SERVER);
		CHANNEL.registerMessage(PacketCloudOisterClient.Handler.class, PacketCloudOisterClient.class, id++, Side.CLIENT);
		CHANNEL.registerMessage(PacketOnCloudBootsUseClient.Handler.class, PacketOnCloudBootsUseClient.class, id++, Side.CLIENT);
		CHANNEL.registerMessage(PacketOnCloudBootsUseServer.Handler.class, PacketOnCloudBootsUseServer.class, id++, Side.SERVER);
		CHANNEL.registerMessage(PacketSquidHoldingPlayerServer.Handler.class, PacketSquidHoldingPlayerServer.class, id++, Side.SERVER);
		CHANNEL.registerMessage(PacketJadeVinesServer.Handler.class, PacketJadeVinesServer.class, id++, Side.SERVER);
		CHANNEL.registerMessage(PacketSandstormUpdatedOnClient.Handler.class, PacketSandstormUpdatedOnClient.class, id++, Side.CLIENT);
		CHANNEL.registerMessage(PacketSandstormUpdatedOnServer.Handler.class, PacketSandstormUpdatedOnServer.class, id++, Side.SERVER);
		CHANNEL.registerMessage(OpenStrangeWandererGuiPacket.Handler.class, OpenStrangeWandererGuiPacket.class, id++, Side.CLIENT);
		CHANNEL.registerMessage(PacketUpdateFogOnClient.Handler.class, PacketUpdateFogOnClient.class, id++, Side.CLIENT);
		CHANNEL.registerMessage(PacketSandstormClient.Handler.class, PacketSandstormClient.class, id++, Side.CLIENT);

	}

}