package io.github.krevik.kathairis.client.render.butterfly;

import com.mojang.blaze3d.platform.GlStateManager;
import io.github.krevik.kathairis.client.model.butterfly.ModelButterfly;
import io.github.krevik.kathairis.entity.butterfly.EntityButterfly;
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
public class RenderButterfly extends MobRenderer<EntityButterfly, ModelButterfly<EntityButterfly>>
{
    public static final Factory FACTORY = new Factory();

    public RenderButterfly(EntityRendererManager renderManagerIn)
    {
        super(renderManagerIn, new ModelButterfly(), 0F);
    }

    
    @Override
    protected ResourceLocation getEntityTexture(EntityButterfly entity)
    {
            return TextureLocationsRef.ButterflyLoc;
    }
    
    public static class Factory implements IRenderFactory<EntityButterfly> {

        @Override
        public EntityRenderer<? super EntityButterfly> createRenderFor(EntityRendererManager manager) {
            return new RenderButterfly(manager);
        }

    }

    @Override
    protected void applyRotations(EntityButterfly entityLiving, float p_77043_2_, float rotationYaw, float partialTicks)
    {
    	GlStateManager.scaled(0.2, 0.2, 0.2);
        super.applyRotations(entityLiving, p_77043_2_, rotationYaw, partialTicks);
    }
}
