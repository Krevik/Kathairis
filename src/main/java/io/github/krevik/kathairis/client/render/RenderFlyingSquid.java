package io.github.krevik.kathairis.client.render;

import com.mojang.blaze3d.platform.GlStateManager;
import io.github.krevik.kathairis.client.model.ModelFlyingSquid;
import io.github.krevik.kathairis.entity.EntityFlyingSquid;
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
public class RenderFlyingSquid extends MobRenderer<EntityFlyingSquid,ModelFlyingSquid<EntityFlyingSquid>>
{
	
    public static final Factory FACTORY = new Factory();

    public RenderFlyingSquid(EntityRendererManager renderManagerIn)
    {
        super(renderManagerIn, new ModelFlyingSquid(), 1.5F);
    }


    @Override
    protected ResourceLocation getEntityTexture(EntityFlyingSquid entity)
    {
        return TextureLocationsRef.FlyingSquidLoc;
    }
    
    public static class Factory implements IRenderFactory<EntityFlyingSquid> {

        @Override
        public EntityRenderer<? super EntityFlyingSquid> createRenderFor(EntityRendererManager manager) {
            return new RenderFlyingSquid(manager);
        }

    }

    @Override
    protected void applyRotations(EntityFlyingSquid entityLiving, float p_77043_2_, float rotationYaw, float partialTicks)
    {
    	GlStateManager.translatef(0, -1, 0);
    	GlStateManager.rotatef(90, 0, 1, 0);
    	if(entityLiving.isDiving()) {
        	GlStateManager.rotatef(180, 0, 0, 1);
    	}
    	if(entityLiving.isChild()) {
        	GlStateManager.scaled(0.6, 0.6, 0.6);
    	}
        super.applyRotations(entityLiving, p_77043_2_, rotationYaw, partialTicks);
        
    }
    
}
