package mod.krevik.network;

import io.netty.buffer.ByteBuf;
import mod.krevik.KCore;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;


public class OpenStrangeWandererGuiPacket implements IMessage{

    public OpenStrangeWandererGuiPacket() { }

    @Override
    public void fromBytes(ByteBuf buf) { }

    @Override
    public void toBytes(ByteBuf buf) { }

    public static class Handler implements IMessageHandler<OpenStrangeWandererGuiPacket, IMessage> {

        @SideOnly(Side.CLIENT)
        @Override
        public IMessage onMessage(OpenStrangeWandererGuiPacket message, MessageContext ctx) {
            Minecraft.getMinecraft().player.openGui(KCore.instance, KCore.GUI_ENUM.OLDMAN.ordinal(),
                    Minecraft.getMinecraft().player.world,
                    (int) Minecraft.getMinecraft().player.posX,
                    (int) Minecraft.getMinecraft().player.posY,
                    (int) Minecraft.getMinecraft().player.posZ);

            return null;
        }
    }
}
