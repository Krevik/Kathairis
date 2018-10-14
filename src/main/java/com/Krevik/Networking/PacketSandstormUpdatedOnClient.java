package com.Krevik.Networking;

import com.Krevik.Dimension.KetherDataStorage;
import com.Krevik.Main.KCore;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumParticleTypes;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketSandstormUpdatedOnClient implements IMessage {

    private boolean isSandstorm;
    private float mX;
    private float sandstormTime;
    private float mZ;

    public PacketSandstormUpdatedOnClient() {
    }

    public PacketSandstormUpdatedOnClient(boolean isSandstorm1, float mx,float sandstormtime,float mz) {
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

    public static class Handler implements IMessageHandler<PacketSandstormUpdatedOnClient, IMessage> {

        @Override
        public IMessage onMessage(PacketSandstormUpdatedOnClient message, MessageContext ctx) {
            if(ctx.side.isClient()) {
                KetherDataStorage storage = KCore.data.getDataInstance(FMLCommonHandler.instance().getMinecraftServerInstance().getWorld(KCore.instance.DIMENSION_ID));
                storage.setIsSandstorm(message.isSandstorm);
                storage.setSandstormX(message.mX);
                storage.setSandstormTime((int) message.sandstormTime);
                storage.setSandstormZ(message.mZ);
            }
            return null;
        }
    }
}