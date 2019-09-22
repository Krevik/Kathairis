package io.github.krevik.kathairis.util.networking;

import io.github.krevik.kathairis.util.networking.packets.PacketClientOpenGuiOldMan;
import io.github.krevik.kathairis.util.networking.packets.PacketServerGivePlayerEthereal;
import io.github.krevik.kathairis.util.networking.packets.PacketServerPlayerUseJadeVine;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;
import net.minecraftforge.fml.server.ServerLifecycleHooks;

import static io.github.krevik.kathairis.util.ModReference.MOD_ID;
/**
* thank you @williewillus
 */
public final class PacketHandler
{
    private static final String PROTOCOL_VERSION = Integer.toString(1);
    private static final SimpleChannel HANDLER = NetworkRegistry.ChannelBuilder
            .named(new ResourceLocation(MOD_ID, "channel_kathairis_"+PROTOCOL_VERSION))
            .clientAcceptedVersions(PROTOCOL_VERSION::equals)
            .serverAcceptedVersions(PROTOCOL_VERSION::equals)
            .networkProtocolVersion(() -> PROTOCOL_VERSION)
            .simpleChannel();

    public static void register()
    {
        int id = 0;
        HANDLER.registerMessage(id++, PacketServerPlayerUseJadeVine.class, PacketServerPlayerUseJadeVine::encode, PacketServerPlayerUseJadeVine::decode, PacketServerPlayerUseJadeVine.Handler::handle);
        HANDLER.registerMessage(id++, PacketServerGivePlayerEthereal.class, PacketServerGivePlayerEthereal::encode, PacketServerGivePlayerEthereal::decode, PacketServerGivePlayerEthereal.Handler::handle);
        HANDLER.registerMessage(id++, PacketClientOpenGuiOldMan.class, PacketClientOpenGuiOldMan::encode, PacketClientOpenGuiOldMan::decode, PacketClientOpenGuiOldMan.Handler::handle);

    }

    public static void sendToServer(Object msg)
    {
        HANDLER.sendToServer(msg);
    }

    public static void sendTo(Object msg, ServerPlayerEntity player)
    {
        if (!(player instanceof FakePlayer))
        {
            HANDLER.sendTo(msg, player.connection.netManager, NetworkDirection.PLAY_TO_CLIENT);
        }
    }

    public static void sendToAll(Object msg){
        for (ServerPlayerEntity player : ServerLifecycleHooks.getCurrentServer().getPlayerList().getPlayers())
        {
            sendTo(msg,player);
        }
    }
}