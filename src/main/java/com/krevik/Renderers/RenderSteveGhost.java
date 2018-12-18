package com.krevik.Renderers;

import com.krevik.Entities.EntitySteveGhost;
import com.krevik.Main.EntityAndRenderRegistry;
import com.krevik.Models.ModelSteveGhost;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderSteveGhost extends RenderLiving<EntitySteveGhost>
{
	
    public static final Factory FACTORY = new Factory();

    public RenderSteveGhost(RenderManager renderManagerIn)
    {
        super(renderManagerIn, new ModelSteveGhost(), 0.75F);
    }

    
    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(EntitySteveGhost entity)
    {
        return EntityAndRenderRegistry.SteveGhostLoc;
    }
    
    public static class Factory implements IRenderFactory<EntitySteveGhost> {

        @Override
        public Render<? super EntitySteveGhost> createRenderFor(RenderManager manager) {
            return new RenderSteveGhost(manager);
        }

    }
    
    protected void applyRotations(EntitySteveGhost entityLiving, float p_77043_2_, float rotationYaw, float partialTicks)
    {
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.enableNormalize();
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
        super.applyRotations(entityLiving, p_77043_2_, rotationYaw, partialTicks);
        
    }
    
}
