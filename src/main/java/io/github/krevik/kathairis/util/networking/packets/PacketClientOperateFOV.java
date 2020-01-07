package io.github.krevik.kathairis.util.networking.packets;

import io.github.krevik.kathairis.client.gui.GuiOldMan;
import net.minecraft.client.Minecraft;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class PacketClientOperateFOV  {

    private float FOV=70;
    public PacketClientOperateFOV(float fov){
        FOV=fov;
    }

    public float getFOV(){
        return FOV;
    }

    public static void encode(PacketClientOperateFOV msg, PacketBuffer buf)
    {
        buf.writeFloat(msg.getFOV());
    }

    public static PacketClientOperateFOV decode(PacketBuffer buf)
    {
        return new PacketClientOperateFOV(buf.readFloat());
    }

    public static class Handler
    {
        public static void handle(final PacketClientOperateFOV message, Supplier<NetworkEvent.Context> ctx)
        {
            if(ctx.get().getDirection()== NetworkDirection.PLAY_TO_CLIENT){
                DistExecutor.runWhenOn(Dist.CLIENT,()->new Runnable() {
                    @Override
                    public void run() {
                        double defaultFOV = Minecraft.getInstance().gameSettings.fov;
                        Minecraft.getInstance().gameSettings.fov=defaultFOV+message.getFOV();
                    }
                });
            }
            ctx.get().setPacketHandled(true);
        }
    }
}
