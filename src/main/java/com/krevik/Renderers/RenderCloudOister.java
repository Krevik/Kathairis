package com.krevik.Renderers;

import com.krevik.Entities.EntityCloudOister;
import com.krevik.Main.EntityAndRenderRegistry;
import com.krevik.Models.ModelCloudOister;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderCloudOister extends RenderLiving<EntityCloudOister>
{
	
    public static final Factory FACTORY = new Factory();

    public RenderCloudOister(RenderManager renderManagerIn)
    {
        super(renderManagerIn, new ModelCloudOister(), 0.3F);
    }

    
    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(EntityCloudOister entity)
    {
        return EntityAndRenderRegistry.CloudOisterLoc;
    }
    
    public static class Factory implements IRenderFactory<EntityCloudOister> {

        @Override
        public Render<? super EntityCloudOister> createRenderFor(RenderManager manager) {
            return new RenderCloudOister(manager);
        }

    }
    
    protected void applyRotations(EntityCloudOister entityLiving, float p_77043_2_, float rotationYaw, float partialTicks)
    {
    	if(!entityLiving.isChild()) {
        	GlStateManager.scale(1.5, 1.5, 1.5);
    	}
    	GlStateManager.rotate(180, 0, 1, 0);
        super.applyRotations(entityLiving, p_77043_2_, rotationYaw, partialTicks);
        
    }
    
}
