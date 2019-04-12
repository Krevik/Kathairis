package io.github.krevik.kathairis.client.render.butterfly;

import io.github.krevik.kathairis.client.model.butterfly.ModelSkylight;
import io.github.krevik.kathairis.entity.butterfly.EntitySkylight;
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
public class RenderSkylight extends RenderLiving<EntitySkylight>
{
    public static final Factory FACTORY = new Factory();
    public RenderSkylight(RenderManager renderManagerIn)
    {
        super(renderManagerIn, new ModelSkylight(), 0F);
    }


    @Override
    protected ResourceLocation getEntityTexture(EntitySkylight entity)
    {
            return TextureLocationsRef.SkylightLoc;
    }
    
    public static class Factory implements IRenderFactory<EntitySkylight> {

        @Override
        public Render<? super EntitySkylight> createRenderFor(RenderManager manager) {
            return new RenderSkylight(manager);
        }

    }

    @Override
    protected void applyRotations(EntitySkylight entityLiving, float p_77043_2_, float rotationYaw, float partialTicks)
    {
    	GlStateManager.scaled(0.3, 0.3, 0.3);
        super.applyRotations(entityLiving, p_77043_2_, rotationYaw, partialTicks);
        
    }
    
}
