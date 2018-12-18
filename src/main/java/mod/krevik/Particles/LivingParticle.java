package mod.krevik.Particles;

import mod.krevik.Blocks.BlockMysticBush;
import mod.krevik.Main.ClientProxy;
import mod.krevik.Main.KCore;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.vecmath.Vector3d;
import java.util.Random;

@SideOnly(Side.CLIENT)
public class LivingParticle extends Particle
{
    private final float portalParticleScale;
    private final double spawnPosX;
    private final double spawnPosY;
    private final double spawnPosZ;
    Random random = new Random();
    public LivingParticle(World worldIn, double xCoordIn, double yCoordIn, double zCoordIn)
    {
        super(worldIn, xCoordIn, yCoordIn, zCoordIn, 0, 0, 0);
        this.posX = xCoordIn;
        this.posY = yCoordIn;
        this.posZ = zCoordIn;
        this.spawnPosX = this.posX;
        this.spawnPosY = this.posY;
        this.spawnPosZ = this.posZ;
        this.particleScale = this.rand.nextFloat() * 0.2F+0.05f;
        this.portalParticleScale = this.particleScale;
        this.particleRed=0.8f;
        this.particleGreen=0.8f;
        this.particleBlue=1f;
        this.particleAlpha=1f;
        this.particleMaxAge = (int)(Math.random() * 300.0D+100);

        TextureAtlasSprite sprite = Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(ClientProxy.living_particle.toString());

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

    Vector3d destination;
    public void onUpdate()
    {
        super.onUpdate();
        if(destination==null){
            getNewDestination();
        }else{
            motionX=(destination.x-posX)/100;
            motionY=(destination.y-posY)/100;
            motionZ=(destination.z-posZ)/100;
                if (random.nextInt(100)==0) {
                    getNewDestination();
                }
        }
    }

    private void getNewDestination(){
        double targetX;
        double targetY;
        double targetZ;

        targetX=posX+random.nextDouble()-random.nextDouble();
        targetY=posY+random.nextDouble()-random.nextDouble();
        targetZ=posZ+random.nextDouble()-random.nextDouble();

        Vector3d result = new Vector3d(targetX,targetY,targetZ);
            while(targetY<spawnPosY){
                targetY+=random.nextDouble();
                result=new Vector3d(targetX,targetY,targetZ);
            }
            if(MathHelper.abs((float)spawnPosX-(float)posX)>4||MathHelper.abs((float)spawnPosY-(float)posY)>4||MathHelper.abs((float)spawnPosZ-(float)posZ)>4){
                targetX=spawnPosX+random.nextDouble()-random.nextDouble();
                targetY=spawnPosY+random.nextDouble()-random.nextDouble();
                targetZ=spawnPosZ+random.nextDouble()-random.nextDouble();
                result = new Vector3d(targetX,targetY,targetZ);
            }
            if(!world.isAirBlock(new BlockPos(result.x,result.y,result.z))){
                if(!(world.getBlockState(new BlockPos(result.x,result.y,result.z)).getBlock() instanceof BlockMysticBush)){
                    targetX=spawnPosX+random.nextDouble()-random.nextDouble();
                    targetY=spawnPosY+random.nextDouble()-random.nextDouble();
                    targetZ=spawnPosZ+random.nextDouble()-random.nextDouble();
                    result=new Vector3d(targetX,targetY,targetZ);
                }
                destination=result;
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
            return new LivingParticle(worldIn, xCoordIn, yCoordIn, zCoordIn);
        }
    }
}