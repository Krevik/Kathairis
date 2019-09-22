package io.github.krevik.kathairis.util.networking.packets;

import io.github.krevik.kathairis.enchantement.KathairisEnchantments;
import net.minecraft.enchantment.EnchantmentData;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.EnchantedBookItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
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
                ServerPlayerEntity player = ctx.get().getSender();
                ItemStack stack = new ItemStack(Items.ENCHANTED_BOOK,1);
                EnchantedBookItem.addEnchantment(stack,new EnchantmentData(KathairisEnchantments.ENCHANTMENT_ETHEREAL,1));
                //stack.addEnchantment(KathairisEnchantments.ENCHANTMENT_ETHEREAL,1);
                player.addItemStackToInventory(stack);
                return;

            });
            ctx.get().setPacketHandled(true);
        }
    }
}
