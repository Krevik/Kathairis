package io.github.krevik.kathairis.client.render;

import io.github.krevik.kathairis.client.model.ModelBison;
import io.github.krevik.kathairis.entity.EntityBison;
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
public class RenderBison extends RenderLiving<EntityBison>
{
	
    public static final Factory FACTORY = new Factory();

    public RenderBison(RenderManager renderManagerIn)
    {
        super(renderManagerIn, new ModelBison(), 1F);
    }


    @Override
    protected ResourceLocation getEntityTexture(EntityBison entity)
    {
        return TextureLocationsRef.BisonLoc;
    }
    
    public static class Factory implements IRenderFactory<EntityBison> {

        @Override
        public Render<? super EntityBison> createRenderFor(RenderManager manager) {
            return new RenderBison(manager);
        }

    }

    @Override
    protected void applyRotations(EntityBison entityLiving, float p_77043_2_, float rotationYaw, float partialTicks)
    {
    	if(!entityLiving.isChild()) {
        	GlStateManager.scaled(1.5, 1.5, 1.5);
    	}
        super.applyRotations(entityLiving, p_77043_2_, rotationYaw, partialTicks);
        
    }
    
}
