package io.github.krevik.kathairis.client.render;

import io.github.krevik.kathairis.client.model.ModelPoisonousScorpion;
import io.github.krevik.kathairis.entity.EntityPoisonousScorpion;
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
public class RenderPoisonousScorpion extends RenderLiving<EntityPoisonousScorpion>
{
	
    public static final Factory FACTORY = new Factory();

    public RenderPoisonousScorpion(RenderManager renderManagerIn)
    {
        super(renderManagerIn, new ModelPoisonousScorpion(), 0.4F);
    }


    @Override
    protected ResourceLocation getEntityTexture(EntityPoisonousScorpion entity)
    {
        return TextureLocationsRef.PoisonousScorpionLoc;
    }
    
    public static class Factory implements IRenderFactory<EntityPoisonousScorpion> {

        @Override
        public Render<? super EntityPoisonousScorpion> createRenderFor(RenderManager manager) {
            return new RenderPoisonousScorpion(manager);
        }

    }

    @Override
    protected void applyRotations(EntityPoisonousScorpion entityLiving, float p_77043_2_, float rotationYaw, float partialTicks)
    {
    	GlStateManager.scaled(0.5, 0.5, 0.5);
        super.applyRotations(entityLiving, p_77043_2_, rotationYaw, partialTicks);
        
    }
    
}
