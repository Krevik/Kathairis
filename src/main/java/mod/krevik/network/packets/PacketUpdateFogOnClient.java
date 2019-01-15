package mod.krevik.network.packets;

import io.netty.buffer.ByteBuf;
import mod.krevik.KCore;
import mod.krevik.world.dimension.KetherDataStorage;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumParticleTypes;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketUpdateFogOnClient implements IMessage {

    private float fogTime;
    private float lastFogTime;

    public PacketUpdateFogOnClient() {
    }

    public PacketUpdateFogOnClient(float fogTime1,float lastFogTime1) {
        this.fogTime=fogTime1;
        this.lastFogTime=lastFogTime1;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        fogTime=buf.readFloat();
        lastFogTime=buf.readFloat();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeFloat(fogTime);
        buf.writeFloat(lastFogTime);
    }

    public static class Handler implements IMessageHandler<PacketUpdateFogOnClient, IMessage> {

        @Override
        public IMessage onMessage(PacketUpdateFogOnClient message, MessageContext ctx) {
            EntityPlayer player = Minecraft.getMinecraft().player;
            if(player!=null) {
                if (player.dimension == KCore.DIMENSION_ID) {
                    KetherDataStorage data = KCore.data.getDataInstance(player.world);
                    if(data!=null) {
                        data.setFogTime(message.fogTime);
                        data.setLastFogTime(message.lastFogTime);
                    }
                }
            }
            return null;
        }
    }
}