package com.Krevik.Renderers;

import com.Krevik.Entities.EntitySkyray;
import com.Krevik.Main.EntityAndRenderRegistry;
import com.Krevik.Models.ModelSkyray;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderSkyray extends RenderLiving<EntitySkyray>
{
    public static final Factory FACTORY = new Factory();

    public RenderSkyray(RenderManager renderManagerIn)
    {
        super(renderManagerIn, new ModelSkyray(), 6F);

    }

    
    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(EntitySkyray entity)
    {
        return EntityAndRenderRegistry.SkyrayLoc;
    }
    
    public static class Factory implements IRenderFactory<EntitySkyray> {

        @Override
        public Render<? super EntitySkyray> createRenderFor(RenderManager manager) {

            return new RenderSkyray(manager);

        }

    }

    protected void applyRotations(EntitySkyray entityLiving, float p_77043_2_, float rotationYaw, float partialTicks)
    {
    	GlStateManager.rotate(-90, 0, 1, 0);
    	if(entityLiving.getAdult()==0) {
        	GlStateManager.scale(2, 2, 2);
    	}else {
        	GlStateManager.scale(8, 8, 8);
    	}

        super.applyRotations(entityLiving, p_77043_2_, rotationYaw, partialTicks);
    }
    
}
