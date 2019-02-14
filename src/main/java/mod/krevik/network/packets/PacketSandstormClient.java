package mod.krevik.network.packets;

import io.netty.buffer.ByteBuf;
import mod.krevik.KCore;
import mod.krevik.client.particle.DynamicParticle;
import mod.krevik.client.particle.ParticlesFactory;
import mod.krevik.world.dimension.KathairisDataStorage;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;

public class PacketSandstormClient implements IMessage {


    float timeLeft;
    public PacketSandstormClient(float timeLef){
        timeLeft=timeLef;
    }

    public PacketSandstormClient(){
    }


    @Override
    public void fromBytes(ByteBuf buf) {
        timeLeft=buf.readFloat();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeFloat(timeLeft);
    }

    public void spawnDustParticles(KathairisDataStorage data){

    }

    public static class Handler implements IMessageHandler<PacketSandstormClient, IMessage> {

        @Override
        public IMessage onMessage(PacketSandstormClient message, MessageContext ctx) {
            if (ctx.side.isClient()) {
                if (Minecraft.getMinecraft().player!=null){
                    if (Minecraft.getMinecraft().player.dimension == KCore.DIMENSION_ID) {
                        if (Minecraft.getMinecraft().player.world.getBiome(Minecraft.getMinecraft().player.getPosition()) == KCore.MysticDesert) {
                            if (Minecraft.getMinecraft().player.getRNG().nextInt(350) == 0) {
                                Minecraft.getMinecraft().player.playSound(KCore.proxy.sandstorm, 1, 1);
                            }
                        }

                        KathairisDataStorage data = KathairisDataStorage.getDataInstance(Minecraft.getMinecraft().player.world);
                        KathairisDataStorage.setShouldAddSandstormFog(true);
                        if(message.timeLeft<2){
                            KathairisDataStorage.setShouldAddSandstormFog(false);
                        }
                        if (Minecraft.getMinecraft().player != null) {
                            if (Minecraft.getMinecraft().player.dimension == KCore.DIMENSION_ID) {
                                ArrayList<BlockPos> positions = new ArrayList<BlockPos>();
                                positions.add(Minecraft.getMinecraft().player.getPosition());

                                for (int x = 0; x <= 8; x++) {
                                    for (int z = 0; z <= 8; z++) {
                                        for (int y = 0; y <= 6; y++) {
                                            BlockPos tmp1 = new BlockPos(Minecraft.getMinecraft().player.posX + x, Minecraft.getMinecraft().player.posY + y, Minecraft.getMinecraft().player.posZ + z);
                                            BlockPos tmp2 = new BlockPos(Minecraft.getMinecraft().player.posX + x, Minecraft.getMinecraft().player.posY + y, Minecraft.getMinecraft().player.posZ - z);
                                            BlockPos tmp3 = new BlockPos(Minecraft.getMinecraft().player.posX - x, Minecraft.getMinecraft().player.posY + y, Minecraft.getMinecraft().player.posZ + z);
                                            BlockPos tmp4 = new BlockPos(Minecraft.getMinecraft().player.posX - x, Minecraft.getMinecraft().player.posY + y, Minecraft.getMinecraft().player.posZ - z);
                                            BlockPos tmp5 = new BlockPos(Minecraft.getMinecraft().player.posX + x, Minecraft.getMinecraft().player.posY - y, Minecraft.getMinecraft().player.posZ + z);
                                            BlockPos tmp6 = new BlockPos(Minecraft.getMinecraft().player.posX + x, Minecraft.getMinecraft().player.posY - y, Minecraft.getMinecraft().player.posZ - z);
                                            BlockPos tmp7 = new BlockPos(Minecraft.getMinecraft().player.posX - x, Minecraft.getMinecraft().player.posY - y, Minecraft.getMinecraft().player.posZ + z);
                                            BlockPos tmp8 = new BlockPos(Minecraft.getMinecraft().player.posX - x, Minecraft.getMinecraft().player.posY - y, Minecraft.getMinecraft().player.posZ - z);
                                            positions.add(tmp1);
                                            positions.add(tmp2);
                                            positions.add(tmp3);
                                            positions.add(tmp4);
                                            positions.add(tmp5);
                                            positions.add(tmp6);
                                            positions.add(tmp7);
                                            positions.add(tmp8);
                                        }
                                    }
                                }
                                for (int x = 0; x < positions.size() - 1; x++) {
                                    if (Minecraft.getMinecraft().player.world.isAirBlock(positions.get(x))) {
                                        if (Minecraft.getMinecraft().player.getRNG().nextInt(50) == 0) {
                                            if (Minecraft.getMinecraft().player.world.getBiome(positions.get(x)) == KCore.MysticDesert && Minecraft.getMinecraft().player.posY > 63 && Minecraft.getMinecraft().player.posY < 84) {
                                                double d0 = positions.get(x).getX() + 0.5 + (Minecraft.getMinecraft().player.getRNG().nextFloat() - Minecraft.getMinecraft().player.getRNG().nextFloat()) * 0.5;
                                                double d1 = positions.get(x).getY() + 0.5 + (Minecraft.getMinecraft().player.getRNG().nextFloat() - Minecraft.getMinecraft().player.getRNG().nextFloat()) * 0.5;
                                                double d2 = positions.get(x).getZ() + 0.5 + (Minecraft.getMinecraft().player.getRNG().nextFloat() - Minecraft.getMinecraft().player.getRNG().nextFloat()) * 0.5;
                                                double d3 = KathairisDataStorage.getSandstormX();
                                                double d4 = (Minecraft.getMinecraft().player.getRNG().nextFloat() - Minecraft.getMinecraft().player.getRNG().nextFloat()) * 0.3;
                                                double d5 = KathairisDataStorage.getSandstormZ();
                                                if (Minecraft.getMinecraft().player.world.isRemote&&
                                                Minecraft.getMinecraft().player.getRNG().nextInt(7)==0) {
                                                    spawnSandParticle(d0, d1, d2, d3, d4, d5);
                                                }
                                                //KCore.instance.cproxy.drawParticle(player.world, new DustParticle(player.world,d0,d1,d2,d3,d4,d5));

                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
            }
        }
            return null;
        }
    }

    @SideOnly(Side.CLIENT)
    private static void spawnSandParticle(double d0,double d1,double d2,double d3,double d4,double d5){
        Particle theParticle = new DynamicParticle(
                ParticlesFactory.DUST,
                Minecraft.getMinecraft().player.world,
                d0, d1, d2,
                d3, d4, d5)
                .setRotSpeed((Minecraft.getMinecraft().player.getRNG().nextFloat()-0.5f)*0.15f)
                .setLifeSpan(40 + Minecraft.getMinecraft().player.getRNG().nextInt(20))
                .setGravity(0F)
                .setScale(2.0F)
                .setInitialAlpha(1.0F)
                .setFinalAlpha(1.0F);
        Minecraft.getMinecraft().effectRenderer.addEffect(theParticle);
    }
}