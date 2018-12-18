package com.krevik.Networking;

import com.krevik.Dimension.KetherDataStorage;
import com.krevik.Main.KCore;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketSandstormUpdatedOnClient implements IMessage {

    private boolean isSandstorm;
    private double mX;
    private float sandstormTime;
    private double mZ;

    public PacketSandstormUpdatedOnClient() {
    }

    public PacketSandstormUpdatedOnClient(boolean isSandstorm1, double mx,float sandstormtime,double mz) {
        isSandstorm=isSandstorm1;
        this.mX=mx;
        this.sandstormTime=sandstormtime;
        this.mZ=mz;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        isSandstorm=buf.readBoolean();
        mX=buf.readDouble();
        sandstormTime=buf.readFloat();
        mZ=buf.readDouble();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeBoolean(isSandstorm);
        buf.writeDouble(mX);
        buf.writeFloat(sandstormTime);
        buf.writeDouble(mZ);
    }

    public static class Handler implements IMessageHandler<PacketSandstormUpdatedOnClient, IMessage> {

        @Override
        public IMessage onMessage(PacketSandstormUpdatedOnClient message, MessageContext ctx) {
            if(ctx.side.isClient()) {
                KetherDataStorage storage = KCore.data.getDataInstance(Minecraft.getMinecraft().player.world);
                storage.setIsSandstorm(message.isSandstorm);
                storage.setSandstormX(message.mX);
                storage.setSandstormTime((int) message.sandstormTime);
                storage.setSandstormZ(message.mZ);
            }
            return null;
        }
    }
}