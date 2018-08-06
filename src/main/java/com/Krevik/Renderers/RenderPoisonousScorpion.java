package com.Krevik.Renderers;

import com.Krevik.Entities.EntityPoisonousScorpion;
import com.Krevik.Main.EntityAndRenderRegistry;
import com.Krevik.Models.ModelPoisonousScorpion;
import com.Krevik.Renderers.RenderPoisonousScorpion.Factory;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderPoisonousScorpion extends RenderLiving<EntityPoisonousScorpion>
{
	
    public static final Factory FACTORY = new Factory();

    public RenderPoisonousScorpion(RenderManager renderManagerIn)
    {
        super(renderManagerIn, new ModelPoisonousScorpion(), 0.2F);
    }

    
    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(EntityPoisonousScorpion entity)
    {
        return EntityAndRenderRegistry.PoisonousScorpionLoc;
    }
    
    public static class Factory implements IRenderFactory<EntityPoisonousScorpion> {

        @Override
        public Render<? super EntityPoisonousScorpion> createRenderFor(RenderManager manager) {
            return new RenderPoisonousScorpion(manager);
        }

    }
    
    protected void applyRotations(EntityPoisonousScorpion entityLiving, float p_77043_2_, float rotationYaw, float partialTicks)
    {
    	GlStateManager.scale(0.2, 0.2, 0.2);
        super.applyRotations(entityLiving, p_77043_2_, rotationYaw, partialTicks);
        
    }
    
}
