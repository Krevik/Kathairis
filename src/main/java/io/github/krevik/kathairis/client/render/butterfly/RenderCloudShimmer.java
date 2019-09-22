package io.github.krevik.kathairis.client.render.butterfly;

import com.mojang.blaze3d.platform.GlStateManager;
import io.github.krevik.kathairis.client.model.butterfly.ModelCloudShimmer;
import io.github.krevik.kathairis.entity.butterfly.EntityCloudShimmer;
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
public class RenderCloudShimmer extends MobRenderer<EntityCloudShimmer, ModelCloudShimmer<EntityCloudShimmer>>
{
    public static final Factory FACTORY = new Factory();
    public RenderCloudShimmer(EntityRendererManager renderManagerIn)
    {
        super(renderManagerIn, new ModelCloudShimmer(), 0F);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityCloudShimmer entity)
    {
            return TextureLocationsRef.CloudShimmerLoc;
    }
    
    public static class Factory implements IRenderFactory<EntityCloudShimmer> {

        @Override
        public EntityRenderer<? super EntityCloudShimmer> createRenderFor(EntityRendererManager manager) {
            return new RenderCloudShimmer(manager);
        }

    }

    @Override
    protected void applyRotations(EntityCloudShimmer entityLiving, float p_77043_2_, float rotationYaw, float partialTicks)
    {
    	GlStateManager.scaled(0.3, 0.3, 0.3);
        super.applyRotations(entityLiving, p_77043_2_, rotationYaw, partialTicks);
    }
    
}
