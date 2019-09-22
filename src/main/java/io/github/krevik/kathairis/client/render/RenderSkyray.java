package io.github.krevik.kathairis.client.render;

import com.mojang.blaze3d.platform.GlStateManager;
import io.github.krevik.kathairis.client.model.ModelSkyray;
import io.github.krevik.kathairis.entity.EntitySkyray;
import io.github.krevik.kathairis.util.TextureLocationsRef;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;

@OnlyIn(Dist.CLIENT)
public class RenderSkyray extends MobRenderer<EntitySkyray, ModelSkyray<EntitySkyray>>
{
    public static final Factory FACTORY = new Factory();

    public RenderSkyray(EntityRendererManager renderManagerIn)
    {
        super(renderManagerIn, new ModelSkyray(), 6F);

    }


    @Override
    protected ResourceLocation getEntityTexture(EntitySkyray entity)
    {
        return TextureLocationsRef.SkyrayLoc;
    }
    
    public static class Factory implements IRenderFactory<EntitySkyray> {

        @Override
        public EntityRenderer<? super EntitySkyray> createRenderFor(EntityRendererManager manager) {

            return new RenderSkyray(manager);

        }

    }

    @Override
    protected void applyRotations(EntitySkyray entityLiving, float p_77043_2_, float rotationYaw, float partialTicks)
    {
    	GlStateManager.rotatef(-90, 0, 1, 0);
    	if(entityLiving.getAdult()==0) {
        	GlStateManager.scaled(2, 2, 2);
    	}else {
        	GlStateManager.scaled(8, 8, 8);
    	}

        super.applyRotations(entityLiving, p_77043_2_, rotationYaw, partialTicks);
    }
    
}
