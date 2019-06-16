package io.github.krevik.kathairis.util.networking.packets;

import io.github.krevik.kathairis.client.gui.GuiOldMan;
import io.github.krevik.kathairis.enchantement.KathairisEnchantments;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class PacketClientOpenGuiOldMan  {

    public PacketClientOpenGuiOldMan(){

    }

    public static void encode(PacketClientOpenGuiOldMan msg, PacketBuffer buf)
    {
    }

    public static PacketClientOpenGuiOldMan decode(PacketBuffer buf)
    {
        return new PacketClientOpenGuiOldMan();
    }

    public static class Handler
    {
        public static void handle(final PacketClientOpenGuiOldMan message, Supplier<NetworkEvent.Context> ctx)
        {
            if(ctx.get().getDirection()== NetworkDirection.PLAY_TO_CLIENT){
                DistExecutor.runWhenOn(Dist.CLIENT,()->new Runnable() {
                    @Override
                    public void run() {
                        Minecraft.getInstance().displayGuiScreen(new GuiOldMan());
                    }
                });
            }
            ctx.get().setPacketHandled(true);
        }
    }
}
