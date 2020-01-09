package io.github.krevik.kathairis.client.render.butterfly;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import io.github.krevik.kathairis.client.model.butterfly.ModelSkylight;
import io.github.krevik.kathairis.entity.butterfly.EntitySkylight;
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
public class RenderSkylight extends MobRenderer<EntitySkylight, ModelSkylight<EntitySkylight>>
{
    public static final Factory FACTORY = new Factory();
    public RenderSkylight(EntityRendererManager renderManagerIn)
    {
        super(renderManagerIn, new ModelSkylight(), 0F);
    }


    @Override
    public ResourceLocation getEntityTexture(EntitySkylight entity)
    {
            return TextureLocationsRef.SkylightLoc;
    }
    
    public static class Factory implements IRenderFactory<EntitySkylight> {

        @Override
        public EntityRenderer<? super EntitySkylight> createRenderFor(EntityRendererManager manager) {
            return new RenderSkylight(manager);
        }

    }

    @Override
    protected void applyRotations(EntitySkylight entityLiving, float p_77043_2_, float rotationYaw, float partialTicks)
    {
        RenderSystem.scaled(0.3, 0.3, 0.3);
        super.applyRotations(entityLiving, p_77043_2_, rotationYaw, partialTicks);
    }
    
}
