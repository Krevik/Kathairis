package io.github.krevik.kathairis.client.render;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import io.github.krevik.kathairis.client.model.ModelLivingFlower;
import io.github.krevik.kathairis.entity.EntityLivingFlower;
import io.github.krevik.kathairis.util.TextureLocationsRef;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import org.lwjgl.opengl.GL11;

@OnlyIn(Dist.CLIENT)
public class RenderLivingFlower extends MobRenderer<EntityLivingFlower,ModelLivingFlower<EntityLivingFlower>>
{
    public static final Factory FACTORY = new Factory();

    public RenderLivingFlower(EntityRendererManager renderManagerIn)
    {
        super(renderManagerIn, new ModelLivingFlower(), 0F);
    }


    @Override
    public ResourceLocation getEntityTexture(EntityLivingFlower entity)
    {
        return TextureLocationsRef.LivingFlowerLoc;
    }
    
    public static class Factory implements IRenderFactory<EntityLivingFlower> {

        @Override
        public EntityRenderer<? super EntityLivingFlower> createRenderFor(EntityRendererManager manager) {
            return new RenderLivingFlower(manager);
        }

    }


    @Override
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
        RenderSystem.disableLighting();
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
        RenderSystem.enableLighting();
        tessellator.draw();
    }

    @Override
    protected void applyRotations(EntityLivingFlower entityLiving, float p_77043_2_, float rotationYaw, float partialTicks)
    {
        super.applyRotations(entityLiving, p_77043_2_, rotationYaw, partialTicks);
    }


}

