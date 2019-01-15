package mod.krevik.network.packets;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MoveMeDuringSandstormServer implements IMessage {

    private double mX;
    private double mZ;

    public MoveMeDuringSandstormServer() {
    }

    public MoveMeDuringSandstormServer(double mx,double mz) {
        this.mX=mx;
        this.mZ=mz;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        mX=buf.readDouble();
        mZ=buf.readDouble();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeDouble(mX);
        buf.writeDouble(mZ);
    }

    public static class Handler implements IMessageHandler<MoveMeDuringSandstormServer, IMessage> {

        @Override
        public IMessage onMessage(MoveMeDuringSandstormServer message, MessageContext ctx) {
            ctx.getServerHandler().player.getServerWorld().addScheduledTask(new Runnable() {
                @Override
                public void run() {
                    EntityPlayerMP player = ctx.getServerHandler().player;
                    if (player.motionY == 0D) {
                        player.motionX += message.mX * 0.0080;
                        player.motionZ += message.mZ * 0.0080;
                        player.velocityChanged=true;
                    } else {
                        player.motionX += message.mX * 0.0017;
                        player.motionZ += message.mZ * 0.0017;
                        player.velocityChanged=true;
                    }
                    player.addPotionEffect(new PotionEffect(Potion.getPotionById(15), 100, 100));

                }

            });
            return null;
        }
    }
}