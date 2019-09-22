package io.github.krevik.kathairis.client.render;

import io.github.krevik.kathairis.client.model.ModelHowler;
import io.github.krevik.kathairis.entity.EntityHowler;
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
public class RenderHowler extends MobRenderer<EntityHowler, ModelHowler<EntityHowler>>
{
	
    public static final Factory FACTORY = new Factory();

    public RenderHowler(EntityRendererManager renderManagerIn)
    {
        super(renderManagerIn, new ModelHowler(), 0.5F);
    }


    @Override
    protected ResourceLocation getEntityTexture(EntityHowler entity)
    {
        return TextureLocationsRef.HowlerLoc;
    }
    
    public static class Factory implements IRenderFactory<EntityHowler> {

        @Override
        public EntityRenderer<? super EntityHowler> createRenderFor(EntityRendererManager manager) {
            return new RenderHowler(manager);
        }

    }

    @Override
    protected void applyRotations(EntityHowler entityLiving, float p_77043_2_, float rotationYaw, float partialTicks)
    {
        super.applyRotations(entityLiving, p_77043_2_, rotationYaw, partialTicks);
        
    }
    
}
