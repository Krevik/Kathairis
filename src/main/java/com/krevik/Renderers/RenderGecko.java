package com.krevik.Renderers;

import com.krevik.Entities.EntityGecko;
import com.krevik.Main.EntityAndRenderRegistry;
import com.krevik.Models.ModelGecko;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderGecko extends RenderLiving<EntityGecko>
{
	
    public static final Factory FACTORY = new Factory();

    public RenderGecko(RenderManager renderManagerIn)
    {
        super(renderManagerIn, new ModelGecko(), 0F);
    }

    
    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(EntityGecko entity)
    {
        return EntityAndRenderRegistry.GeckoLoc;
    }
    
    public static class Factory implements IRenderFactory<EntityGecko> {

        @Override
        public Render<? super EntityGecko> createRenderFor(RenderManager manager) {
            return new RenderGecko(manager);
        }

    }
    
    protected void applyRotations(EntityGecko entityLiving, float p_77043_2_, float rotationYaw, float partialTicks)
    {
    	/*if(entityLiving.isChild()) {
        	GlStateManager.scale(0.5, 0.5, 0.5);
    	}else {
        	GlStateManager.scale(0.8, 0.8, 0.8);
    	}*/
        if (entityLiving.isClimbing()) {
            if (entityLiving.climbingSide() == EntityGecko.EnumClimbSide.EAST) {
                GlStateManager.rotate(90, 0, 0, 1);
            } else if (entityLiving.climbingSide() == EntityGecko.EnumClimbSide.WEST) {
                GlStateManager.rotate(90, 0, 0, -1);
            } else if (entityLiving.climbingSide() == EntityGecko.EnumClimbSide.NORTH) {
                GlStateManager.rotate(90, 1, 0, 0);
            } else if (entityLiving.climbingSide() == EntityGecko.EnumClimbSide.SOUTH) {
                GlStateManager.rotate(90, -1, 0, 0);
            }
            GlStateManager.translate(0f, -0.3F, 0F);
        }
        super.applyRotations(entityLiving, p_77043_2_, rotationYaw, partialTicks);
        
    }
    
}
