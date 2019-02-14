package mod.krevik.client.renderer;

import mod.krevik.client.model.ModelLivingFlower;
import mod.krevik.entity.EntityLivingFlower;
import mod.krevik.util.EntityAndRenderRegistry;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RenderLivingFlower extends RenderLiving<EntityLivingFlower>
{
    public static final Factory FACTORY = new Factory();

    public RenderLivingFlower(RenderManager renderManagerIn)
    {
        super(renderManagerIn, new ModelLivingFlower(), 0F);
    }

    
    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(EntityLivingFlower entity)
    {
        return EntityAndRenderRegistry.LivingFlowerLoc;
    }
    
    public static class Factory implements IRenderFactory<EntityLivingFlower> {

        @Override
        public Render<? super EntityLivingFlower> createRenderFor(RenderManager manager) {
            return new RenderLivingFlower(manager);
        }

    }



    protected void preRenderCallback(EntityLivingFlower entity, float partialTickTime)
    {
        super.preRenderCallback(entity,partialTickTime);
        /*if(entity.getAnimationTime()>0){
            GlStateManager.pushMatrix();
            drawCircle((float)entity.posX,(float)entity.posZ,entity.getAnimationTime()/100,entity);
            GlStateManager.popMatrix();
        }*/
    }

    private void drawCircle(float x, float y, float radius,EntityLivingFlower entity)
    {
        GlStateManager.disableLighting();
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();
        int i;
        int triangleAmount = 1000;
        float twicePi = (float)(2.0f * Math.PI);

        bufferbuilder.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_COLOR);
        for(i = 0; i <= triangleAmount; i++)
        {
            bufferbuilder.pos(x,entity.posY+0.3f,y).color(0.0f, 0.8f, 0.0f, 0.6f).endVertex();
            bufferbuilder.pos(x+(radius * Math.cos(i*twicePi/triangleAmount)),entity.posY+0.3f,y+(radius*Math.sin(i*twicePi/triangleAmount))).color(0.0f, 0.8f, 0.0f, 0.6f).endVertex();
        }
        GlStateManager.enableLighting();
        tessellator.draw();
    }

    protected void applyRotations(EntityLivingFlower entityLiving, float p_77043_2_, float rotationYaw, float partialTicks)
    {
        super.applyRotations(entityLiving, p_77043_2_, rotationYaw, partialTicks);
    }


}

