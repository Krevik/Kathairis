package com.krevik.Renderers.Butterflies;

import com.krevik.Entities.Butterflies.EntityButterfly1;
import com.krevik.Main.EntityAndRenderRegistry;
import com.krevik.Models.Butterflies.ModelButterfly;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import scala.util.Random;

@SideOnly(Side.CLIENT)
public class RenderButterfly1 extends RenderLiving<EntityButterfly1>
{
    public static final Factory FACTORY = new Factory();
    Random random = new Random();
    public RenderButterfly1(RenderManager renderManagerIn)
    {
        super(renderManagerIn, new ModelButterfly(), 0F);
    }

    
    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(EntityButterfly1 entity)
    {
            return EntityAndRenderRegistry.Butterfly1Loc;
    }
    
    public static class Factory implements IRenderFactory<EntityButterfly1> {

        @Override
        public Render<? super EntityButterfly1> createRenderFor(RenderManager manager) {
            return new RenderButterfly1(manager);
        }

    }
    
    protected void applyRotations(EntityButterfly1 entityLiving, float p_77043_2_, float rotationYaw, float partialTicks)
    {
    	GlStateManager.scale(0.2, 0.2, 0.2);
        super.applyRotations(entityLiving, p_77043_2_, rotationYaw, partialTicks);
        
    }
    
}
