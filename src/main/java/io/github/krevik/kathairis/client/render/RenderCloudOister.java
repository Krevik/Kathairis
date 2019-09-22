package io.github.krevik.kathairis.client.render;

import com.mojang.blaze3d.platform.GlStateManager;
import io.github.krevik.kathairis.client.model.ModelCloudOister;
import io.github.krevik.kathairis.entity.EntityCloudOister;
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
public class RenderCloudOister extends MobRenderer<EntityCloudOister,ModelCloudOister<EntityCloudOister>>
{
	
    public static final Factory FACTORY = new Factory();

    public RenderCloudOister(EntityRendererManager renderManagerIn)
    {
        super(renderManagerIn, new ModelCloudOister(), 0.3F);
    }


    @Override
    protected ResourceLocation getEntityTexture(EntityCloudOister entity)
    {
        return TextureLocationsRef.CloudOisterLoc;
    }
    
    public static class Factory implements IRenderFactory<EntityCloudOister> {

        @Override
        public EntityRenderer<? super EntityCloudOister> createRenderFor(EntityRendererManager manager) {
            return new RenderCloudOister(manager);
        }

    }

    @Override
    protected void applyRotations(EntityCloudOister entityLiving, float p_77043_2_, float rotationYaw, float partialTicks)
    {
    	if(!entityLiving.isChild()) {
        	GlStateManager.scaled(1.5, 1.5, 1.5);
    	}
    	GlStateManager.rotatef(180, 0, 1, 0);
        super.applyRotations(entityLiving, p_77043_2_, rotationYaw, partialTicks);
        
    }
    
}
