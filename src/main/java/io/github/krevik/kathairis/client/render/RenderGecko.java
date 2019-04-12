package io.github.krevik.kathairis.client.render;

import io.github.krevik.kathairis.client.model.ModelGecko;
import io.github.krevik.kathairis.entity.EntityGecko;
import io.github.krevik.kathairis.util.TextureLocationsRef;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;

@OnlyIn(Dist.CLIENT)
public class RenderGecko extends RenderLiving<EntityGecko>
{
	
    public static final Factory FACTORY = new Factory();

    public RenderGecko(RenderManager renderManagerIn)
    {
        super(renderManagerIn, new ModelGecko(), 0F);
    }


    @Override
    protected ResourceLocation getEntityTexture(EntityGecko entity)
    {
        return TextureLocationsRef.GeckoLoc;
    }
    
    public static class Factory implements IRenderFactory<EntityGecko> {

        @Override
        public Render<? super EntityGecko> createRenderFor(RenderManager manager) {
            return new RenderGecko(manager);
        }

    }

    @Override
    protected void applyRotations(EntityGecko entityLiving, float p_77043_2_, float rotationYaw, float partialTicks)
    {
    	/*if(entityLiving.isChild()) {
        	GlStateManager.scale(0.5, 0.5, 0.5);
    	}else {
        	GlStateManager.scale(0.8, 0.8, 0.8);
    	}*/
        if (entityLiving.isClimbing()) {
            if (entityLiving.climbingSide() == EntityGecko.EnumClimbSide.EAST) {
                GlStateManager.rotatef(90, 0, 0, 1);
            } else if (entityLiving.climbingSide() == EntityGecko.EnumClimbSide.WEST) {
                GlStateManager.rotatef(90, 0, 0, -1);
            } else if (entityLiving.climbingSide() == EntityGecko.EnumClimbSide.NORTH) {
                GlStateManager.rotatef(90, 1, 0, 0);
            } else if (entityLiving.climbingSide() == EntityGecko.EnumClimbSide.SOUTH) {
                GlStateManager.rotatef(90, -1, 0, 0);
            }
            GlStateManager.translatef(0f, -0.3F, 0F);
        }
        super.applyRotations(entityLiving, p_77043_2_, rotationYaw, partialTicks);
        
    }
    
}
