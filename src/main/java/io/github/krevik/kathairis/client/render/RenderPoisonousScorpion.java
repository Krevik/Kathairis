package io.github.krevik.kathairis.client.render;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import io.github.krevik.kathairis.client.model.ModelPoisonousScorpion;
import io.github.krevik.kathairis.entity.EntityPoisonousScorpion;
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
public class RenderPoisonousScorpion extends MobRenderer<EntityPoisonousScorpion, ModelPoisonousScorpion<EntityPoisonousScorpion>>
{
	
    public static final Factory FACTORY = new Factory();

    public RenderPoisonousScorpion(EntityRendererManager renderManagerIn)
    {
        super(renderManagerIn, new ModelPoisonousScorpion(), 0.4F);
    }


    @Override
    public ResourceLocation getEntityTexture(EntityPoisonousScorpion entity)
    {
        return TextureLocationsRef.PoisonousScorpionLoc;
    }
    
    public static class Factory implements IRenderFactory<EntityPoisonousScorpion> {

        @Override
        public EntityRenderer<? super EntityPoisonousScorpion> createRenderFor(EntityRendererManager manager) {
            return new RenderPoisonousScorpion(manager);
        }

    }

    @Override
    protected void applyRotations(EntityPoisonousScorpion entityLiving, float p_77043_2_, float rotationYaw, float partialTicks)
    {
    	RenderSystem.scaled(0.5, 0.5, 0.5);
        super.applyRotations(entityLiving, p_77043_2_, rotationYaw, partialTicks);
        
    }
    
}
