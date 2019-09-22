package io.github.krevik.kathairis.client.render;

import com.mojang.blaze3d.platform.GlStateManager;
import io.github.krevik.kathairis.client.model.ModelCamel;
import io.github.krevik.kathairis.entity.EntityCamel;
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
public class RenderCamel extends MobRenderer<EntityCamel,ModelCamel<EntityCamel>>
{
	
    public static final Factory FACTORY = new Factory();

    public RenderCamel(EntityRendererManager renderManagerIn)
    {
        super(renderManagerIn, new ModelCamel(), 1.5F);
    }


    @Override
    protected ResourceLocation getEntityTexture(EntityCamel entity)
    {
        return TextureLocationsRef.CamelLoc;
    }
    
    public static class Factory implements IRenderFactory<EntityCamel> {

        @Override
        public EntityRenderer<? super EntityCamel> createRenderFor(EntityRendererManager manager) {
            return new RenderCamel(manager);
        }

    }

    @Override
    protected void applyRotations(EntityCamel entityLiving, float p_77043_2_, float rotationYaw, float partialTicks)
    {
    	if(entityLiving.isChild()) {
        	GlStateManager.scaled(0.6, 0.6, 0.6);
    	}
        super.applyRotations(entityLiving, p_77043_2_, rotationYaw, partialTicks);
        
    }
    
}
