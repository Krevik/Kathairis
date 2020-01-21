package io.github.krevik.kathairis.client.render;

import io.github.krevik.kathairis.client.model.ModelLivingFlower;
import io.github.krevik.kathairis.entity.EntityLivingFlower;
import io.github.krevik.kathairis.util.TextureLocationsRef;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;

@OnlyIn(Dist.CLIENT)
public class RenderLivingFlower extends MobRenderer<EntityLivingFlower,ModelLivingFlower<EntityLivingFlower>>
{
    public static final Factory FACTORY = new Factory();

    public RenderLivingFlower(EntityRendererManager renderManagerIn)
    {
        super(renderManagerIn, new ModelLivingFlower(), 0F);
    }


    @Override
    public ResourceLocation getEntityTexture(EntityLivingFlower entity)
    {
        return TextureLocationsRef.LivingFlowerLoc;
    }
    
    public static class Factory implements IRenderFactory<EntityLivingFlower> {

        @Override
        public EntityRenderer<? super EntityLivingFlower> createRenderFor(EntityRendererManager manager) {
            return new RenderLivingFlower(manager);
        }

    }

}

