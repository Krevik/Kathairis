package io.github.krevik.kathairis.client.render;

import io.github.krevik.kathairis.client.model.ModelCloudySlime;
import io.github.krevik.kathairis.entity.EntityCloudySlime;
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
public class RenderCloudySlime extends RenderLiving<EntityCloudySlime>
{
    public static final Factory FACTORY = new Factory();

    public RenderCloudySlime(RenderManager renderManagerIn)
    {
        super(renderManagerIn, new ModelCloudySlime(), 0F);

    }


    @Override
    protected ResourceLocation getEntityTexture(EntityCloudySlime entity)
    {
        return TextureLocationsRef.CloudySlimeLoc;
    }
    
    public static class Factory implements IRenderFactory<EntityCloudySlime> {

        @Override
        public Render<? super EntityCloudySlime> createRenderFor(RenderManager manager) {

            return new RenderCloudySlime(manager);

        }

    }

    @Override
    protected void applyRotations(EntityCloudySlime entityLiving, float p_77043_2_, float rotationYaw, float partialTicks)
    {
    	GlStateManager.rotatef(90, 0, 1, 0);
    	GlStateManager.scaled(0.7, 0.7, 0.7);

        super.applyRotations(entityLiving, p_77043_2_, rotationYaw, partialTicks);
    }
    
}
