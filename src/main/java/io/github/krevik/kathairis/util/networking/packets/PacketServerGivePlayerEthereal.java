package io.github.krevik.kathairis.util.networking.packets;

import io.github.krevik.kathairis.enchantement.KathairisEnchantments;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBook;
import net.minecraft.item.ItemEnchantedBook;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class PacketServerGivePlayerEthereal  {

    public PacketServerGivePlayerEthereal(){

    }

    public static void encode(PacketServerGivePlayerEthereal msg, PacketBuffer buf)
    {
    }

    public static PacketServerGivePlayerEthereal decode(PacketBuffer buf)
    {
        return new PacketServerGivePlayerEthereal();
    }

    public static class Handler
    {
        public static void handle(final PacketServerGivePlayerEthereal message, Supplier<NetworkEvent.Context> ctx)
        {
            ctx.get().enqueueWork(() -> {
                EntityPlayerMP player = ctx.get().getSender();
                ItemStack stack = new ItemStack(Items.BOOK,1);
                stack.addEnchantment(KathairisEnchantments.ENCHANTMENT_ETHEREAL,1);
                player.addItemStackToInventory(stack);
                return;

            });
            ctx.get().setPacketHandled(true);
        }
    }
}
