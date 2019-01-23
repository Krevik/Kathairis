package mod.krevik.network.packets;

import mod.krevik.network.KathairisPacketHandler;
import mod.krevik.world.dimension.KathairisDataStorage;
import mod.krevik.KCore;
import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketSandstormUpdatedOnServer implements IMessage {

    private double mX;
    private float sandstormTime;
    private double mZ;

    public PacketSandstormUpdatedOnServer() {
    }

    public PacketSandstormUpdatedOnServer(double mx,float sandstormtime,double mz) {
        this.mX=mx;
        this.sandstormTime=sandstormtime;
        this.mZ=mz;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        mX=buf.readDouble();
        sandstormTime=buf.readFloat();
        mZ=buf.readDouble();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeDouble(mX);
        buf.writeFloat(sandstormTime);
        buf.writeDouble(mZ);
    }

    public static class Handler implements IMessageHandler<PacketSandstormUpdatedOnServer, IMessage> {

        @Override
        public IMessage onMessage(PacketSandstormUpdatedOnServer message, MessageContext ctx) {
            ctx.getServerHandler().player.getServerWorld().addScheduledTask(new Runnable() {
                @Override
                public void run() {
                    KathairisDataStorage data = KCore.data.getDataInstance(ctx.getServerHandler().player.world);
                    data.setSandstormX(message.mX);
                    data.setSandstormTime((int) message.sandstormTime);
                    data.setSandstormZ(message.mZ);
                    PacketSandstormUpdatedOnClient message2 = new PacketSandstormUpdatedOnClient(message.mX,message.sandstormTime,message.mZ);
                    KathairisPacketHandler.CHANNEL.sendToAll(message2);
                }

            });
            return null;
        }
    }
}