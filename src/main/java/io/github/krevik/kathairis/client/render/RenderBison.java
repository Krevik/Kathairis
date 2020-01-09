package io.github.krevik.kathairis.client.render;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import io.github.krevik.kathairis.client.model.ModelBison;
import io.github.krevik.kathairis.entity.EntityBison;
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
public class RenderBison extends MobRenderer<EntityBison, ModelBison<EntityBison>>
{
    public static final Factory FACTORY = new Factory();

    public RenderBison(EntityRendererManager renderManagerIn)
    {
        super(renderManagerIn, new ModelBison(), 1F);
    }


    @Override
    public ResourceLocation getEntityTexture(EntityBison entity)
    {
        return TextureLocationsRef.BisonLoc;
    }
    
    public static class Factory implements IRenderFactory<EntityBison> {

        @Override
        public EntityRenderer<? super EntityBison> createRenderFor(EntityRendererManager manager) {
            return new RenderBison(manager);
        }

    }

    @Override
    protected void applyRotations(EntityBison entityLiving, float p_77043_2_, float rotationYaw, float partialTicks)
    {
    	if(!entityLiving.isChild()) {
        	RenderSystem.scaled(1.5, 1.5, 1.5);
    	}
        super.applyRotations(entityLiving, p_77043_2_, rotationYaw, partialTicks);
        
    }
    
}
