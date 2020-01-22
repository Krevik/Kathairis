package io.github.krevik.kathairis.util.networking.packets;

import io.github.krevik.kathairis.client.gui.GuiOldMan;
import net.minecraft.client.Minecraft;
import net.minecraft.network.PacketBuffer;
import net.minecraft.particles.ParticleTypes;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class PacketClientSpawnHeartParticle  {

    private double posX;
    private double posY;
    private double posZ;
    private double motionX;
    private double motionY;
    private double motionZ;
    public PacketClientSpawnHeartParticle(double d1, double d2, double d3, double d4, double d5, double d6){
        posX=d1;
        posY=d2;
        posZ=d3;
        motionX=d4;
        motionY=d5;
        motionZ=d6;
    }

    public static void encode(PacketClientSpawnHeartParticle msg, PacketBuffer buf)
    {
        buf.writeDouble(msg.posX);
        buf.writeDouble(msg.posY);
        buf.writeDouble(msg.posZ);
        buf.writeDouble(msg.motionX);
        buf.writeDouble(msg.motionY);
        buf.writeDouble(msg.motionZ);

    }

    public static PacketClientSpawnHeartParticle decode(PacketBuffer buf)
    {
        return new PacketClientSpawnHeartParticle(buf.readDouble(),buf.readDouble(),buf.readDouble(),buf.readDouble(),buf.readDouble(),buf.readDouble());
    }

    public static class Handler
    {
        public static void handle(final PacketClientSpawnHeartParticle message, Supplier<NetworkEvent.Context> ctx)
        {
            if(ctx.get().getDirection()== NetworkDirection.PLAY_TO_CLIENT){
                DistExecutor.runWhenOn(Dist.CLIENT,()->new Runnable() {
                    @Override
                    public void run() {
                        ctx.get().getSender().getEntityWorld().addParticle(ParticleTypes.HEART, message.posX, message.posY,
                                message.posZ, message.motionX, message.motionY, message.motionZ);
                        Minecraft.getInstance().displayGuiScreen(new GuiOldMan());
                    }
                });
            }
            ctx.get().setPacketHandled(true);
        }
    }
}
