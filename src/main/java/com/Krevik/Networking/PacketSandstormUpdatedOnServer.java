package com.Krevik.Networking;

import com.Krevik.Dimension.KetherDataStorage;
import com.Krevik.Main.KCore;
import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import java.util.Random;

public class PacketSandstormUpdatedOnServer implements IMessage {

    private boolean isSandstorm;
    private float mX;
    private float sandstormTime;
    private float mZ;

    public PacketSandstormUpdatedOnServer() {
    }

    public PacketSandstormUpdatedOnServer(boolean isSandstorm1, float mx,float sandstormtime,float mz) {
        isSandstorm=isSandstorm1;
        this.mX=mx;
        this.sandstormTime=sandstormtime;
        this.mZ=mz;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        isSandstorm=buf.readBoolean();
        mX=buf.readFloat();
        sandstormTime=buf.readFloat();
        mZ=buf.readFloat();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeBoolean(isSandstorm);
        buf.writeFloat(mX);
        buf.writeFloat(sandstormTime);
        buf.writeFloat(mZ);
    }

    public static class Handler implements IMessageHandler<PacketSandstormUpdatedOnServer, IMessage> {

        @Override
        public IMessage onMessage(PacketSandstormUpdatedOnServer message, MessageContext ctx) {
            ctx.getServerHandler().player.getServerWorld().addScheduledTask(new Runnable() {
                @Override
                public void run() {
                    KetherDataStorage data = KCore.data.getDataInstance(FMLCommonHandler.instance().getMinecraftServerInstance().getWorld(KCore.instance.DIMENSION_ID));
                    data.setIsSandstorm(message.isSandstorm);
                    data.setSandstormX(message.mX);
                    data.setSandstormTime((int) message.sandstormTime);
                    data.setSandstormZ(message.mZ);
                    PacketSandstormUpdatedOnClient message1 = new PacketSandstormUpdatedOnClient(message.isSandstorm, message.mX,message.sandstormTime, message.mZ);
                    KetherPacketHandler.CHANNEL.sendToAll(message1);
                }

            });
            return null;
        }
    }
}