package io.github.krevik.kathairis.util.networking.packets;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class PacketServerPlayerUseJadeVine  {

    public PacketServerPlayerUseJadeVine(){

    }

    public static void encode(PacketServerPlayerUseJadeVine msg, PacketBuffer buf)
    {
    }

    public static PacketServerPlayerUseJadeVine decode(PacketBuffer buf)
    {
        return new PacketServerPlayerUseJadeVine();
    }

    public static class Handler
    {
        public static void handle(final PacketServerPlayerUseJadeVine message, Supplier<NetworkEvent.Context> ctx)
        {
            ctx.get().enqueueWork(() -> {
                ServerPlayerEntity player = ctx.get().getSender();
                ctx.get().getSender().setMotion(new Vec3d(ctx.get().getSender().getMotion().getX(),0.1,ctx.get().getSender().getMotion().getZ()));
                    ctx.get().getSender().fallDistance=0;
                    ctx.get().getSender().velocityChanged=true;
                return;

            });
            ctx.get().setPacketHandled(true);
        }
    }
}
