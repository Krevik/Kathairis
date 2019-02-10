package mod.krevik.network.packets;

import mod.krevik.world.dimension.KathairisDataStorage;
import mod.krevik.KCore;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketSandstormUpdatedOnClient implements IMessage {

    private double mX;
    private float sandstormTime;
    private double mZ;

    public PacketSandstormUpdatedOnClient() {
    }

    public PacketSandstormUpdatedOnClient( double mx,float sandstormtime,double mz) {
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

    public static class Handler implements IMessageHandler<PacketSandstormUpdatedOnClient, IMessage> {

        @Override
        public IMessage onMessage(PacketSandstormUpdatedOnClient message, MessageContext ctx) {
            if(ctx.side.isClient()) {
                if(Minecraft.getMinecraft().player.dimension==KCore.DIMENSION_ID) {
                    KathairisDataStorage storage = KathairisDataStorage.getDataInstance(Minecraft.getMinecraft().player.world);
                    storage.setSandstormX(message.mX);
                    storage.setSandstormTime((int) message.sandstormTime);
                    storage.setSandstormZ(message.mZ);
                    if(message.sandstormTime>5) {
                        storage.setShouldAddSandstormFog(true);
                    }else{
                        storage.setShouldAddSandstormFog(false);
                    }
                }
            }
            return null;
        }
    }
}