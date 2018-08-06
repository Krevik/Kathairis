package com.Krevik.Particles;

import java.util.Random;

import com.Krevik.Main.KCore;
import com.Krevik.Networking.KetherPacketHandler;
import com.Krevik.Networking.PacketSpawnForgottenSandEntity;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class DustParticle extends Particle
{
    private final float portalParticleScale;
    private final double portalPosX;
    private final double portalPosY;
    private final double portalPosZ;
    Random random = new Random();
    public DustParticle(World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn)
    {
        super(worldIn, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn);
        this.motionX = xSpeedIn;
        this.motionY = ySpeedIn;
        this.motionZ = zSpeedIn;
        this.posX = xCoordIn;
        this.posY = yCoordIn;
        this.posZ = zCoordIn;
        this.portalPosX = this.posX;
        this.portalPosY = this.posY;
        this.portalPosZ = this.posZ;
        this.particleScale = this.rand.nextFloat() * 0.2F + 0.5F;
        this.portalParticleScale = this.particleScale;
       // this.particleRed = 2.32F;
       // this.particleGreen = 2.50F;
       // this.particleBlue = 2.54F;
        this.particleRed=1;
        this.particleGreen=1;
        this.particleBlue=1;
        this.particleAlpha=50;
        this.particleMaxAge = (int)(Math.random() * 60.0D);

        TextureAtlasSprite sprite = Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(KCore.instance.cproxy.dust_particle.toString());

        setParticleTexture(sprite);

    }

    public void move(double x, double y, double z)
    {
        this.setBoundingBox(this.getBoundingBox().offset(x, y, z));
        this.resetPositionToBB();
    }

    @Override
    public void renderParticle(BufferBuilder bufferBuilder, Entity entity, float partialTick,
                              float edgeLRdirectionX, float edgeUDdirectionY, float edgeLRdirectionZ,
                              float edgeUDdirectionX, float edgeUDdirectionZ)
    {
      double minU = this.particleTexture.getMinU();
      double maxU = this.particleTexture.getMaxU();
      double minV = this.particleTexture.getMinV();
      double maxV = this.particleTexture.getMaxV();

      double scale = 0.2F * this.particleScale;  // vanilla scaling factor
      final double scaleLR = scale;
      final double scaleUD = scale;
      double x = this.prevPosX + (this.posX - this.prevPosX) * partialTick - interpPosX;
      double y = this.prevPosY + (this.posY - this.prevPosY) * partialTick - interpPosY;
      double z = this.prevPosZ + (this.posZ - this.prevPosZ) * partialTick - interpPosZ;


      // "lightmap" changes the brightness of the particle depending on the local illumination (block light, sky light)
      //  in this example, it's held constant, but we still need to add it to each vertex anyway.
      int combinedBrightness = this.getBrightnessForRender(partialTick);
      int skyLightTimes16 = combinedBrightness >> 16 & 65535;
      int blockLightTimes16 = combinedBrightness & 65535;

      // the caller has already initiated rendering, using:
//      worldRenderer.begin(GL11.GL_QUADS, DefaultVertexFormats.PARTICLE_POSITION_TEX_COLOR_LMAP);

      bufferBuilder.pos(x - edgeLRdirectionX * scaleLR - edgeUDdirectionX * scaleUD,
              y - edgeUDdirectionY * scaleUD,
              z - edgeLRdirectionZ * scaleLR - edgeUDdirectionZ * scaleUD)
                   .tex(maxU, maxV)
                   .color(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha)
                   .lightmap(skyLightTimes16, blockLightTimes16)
                   .endVertex();
      bufferBuilder.pos(x - edgeLRdirectionX * scaleLR + edgeUDdirectionX * scaleUD,
              y + edgeUDdirectionY * scaleUD,
              z - edgeLRdirectionZ * scaleLR + edgeUDdirectionZ * scaleUD)
              .tex(maxU, minV)
              .color(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha)
              .lightmap(skyLightTimes16, blockLightTimes16)
              .endVertex();
      bufferBuilder.pos(x + edgeLRdirectionX * scaleLR + edgeUDdirectionX * scaleUD,
              y + edgeUDdirectionY * scaleUD,
              z + edgeLRdirectionZ * scaleLR + edgeUDdirectionZ * scaleUD)
              .tex(minU, minV)
              .color(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha)
              .lightmap(skyLightTimes16, blockLightTimes16)
              .endVertex();
      bufferBuilder.pos(x + edgeLRdirectionX * scaleLR - edgeUDdirectionX * scaleUD,
              y - edgeUDdirectionY * scaleUD,
              z + edgeLRdirectionZ * scaleLR - edgeUDdirectionZ * scaleUD)
              .tex(minU, maxV)
              .color(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha)
              .lightmap(skyLightTimes16, blockLightTimes16)
              .endVertex();

    }


    public int getBrightnessForRender(float p_189214_1_)
    {
        int i = super.getBrightnessForRender(p_189214_1_);
        float f = (float)this.particleAge / (float)this.particleMaxAge;
        f = f * f;
        f = f * f;
        int j = i & 255;
        int k = i >> 16 & 255;
        k = k + (int)(f * 15.0F * 16.0F);

        if (k > 240)
        {
            k = 240;
        }

        return j | k << 16;
    }

    public void onUpdate()
    {
    	super.onUpdate();
        float f = (float)this.particleAge / (float)this.particleMaxAge;

        if (this.particleAge++ >= this.particleMaxAge)
        {
        	if(this.world.isAirBlock(new BlockPos(posX,posY,posZ))) {
        		if(this.random.nextInt(30000)==0) {
					IMessage message = new PacketSpawnForgottenSandEntity(posX,posY,posZ);
					KetherPacketHandler.CHANNEL.sendToServer(message);
        		}
        	}
            this.setExpired();
        }
    }

    
    public int getFXLayer()
    {
        return 1;
    }

    @SideOnly(Side.CLIENT)
    public static class Factory implements IParticleFactory
        {
            public Particle createParticle(int particleID, World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn, int... p_178902_15_)
            {
                return new DustParticle(worldIn, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn);
            }
        }
}