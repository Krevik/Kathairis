package com.krevik.Renderers;

import com.krevik.Entities.EntityHowler;
import com.krevik.Main.EntityAndRenderRegistry;
import com.krevik.Models.ModelHowler;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderHowler extends RenderLiving<EntityHowler>
{
	
    public static final Factory FACTORY = new Factory();

    public RenderHowler(RenderManager renderManagerIn)
    {
        super(renderManagerIn, new ModelHowler(), 0.5F);
    }

    
    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(EntityHowler entity)
    {
        return EntityAndRenderRegistry.HowlerLoc;
    }
    
    public static class Factory implements IRenderFactory<EntityHowler> {

        @Override
        public Render<? super EntityHowler> createRenderFor(RenderManager manager) {
            return new RenderHowler(manager);
        }

    }
    
    protected void applyRotations(EntityHowler entityLiving, float p_77043_2_, float rotationYaw, float partialTicks)
    {
        super.applyRotations(entityLiving, p_77043_2_, rotationYaw, partialTicks);
        
    }
    
}
