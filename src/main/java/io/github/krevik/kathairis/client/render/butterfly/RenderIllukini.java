package io.github.krevik.kathairis.client.render.butterfly;

import com.mojang.blaze3d.platform.GlStateManager;
import io.github.krevik.kathairis.client.model.butterfly.ModelIllukini;
import io.github.krevik.kathairis.entity.butterfly.EntityIllukini;
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
public class RenderIllukini extends MobRenderer<EntityIllukini, ModelIllukini<EntityIllukini>>
{
    public static final Factory FACTORY = new Factory();
    public RenderIllukini(EntityRendererManager renderManagerIn)
    {
        super(renderManagerIn, new ModelIllukini(), 0F);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityIllukini entity)
    {
            return TextureLocationsRef.IllukiniLoc;
    }
    
    public static class Factory implements IRenderFactory<EntityIllukini> {

        @Override
        public EntityRenderer<? super EntityIllukini> createRenderFor(EntityRendererManager manager) {
            return new RenderIllukini(manager);
        }

    }

    @Override
    protected void applyRotations(EntityIllukini entityLiving, float p_77043_2_, float rotationYaw, float partialTicks)
    {
    	GlStateManager.scaled(0.3, 0.3, 0.3);
        super.applyRotations(entityLiving, p_77043_2_, rotationYaw, partialTicks);
    }
    
}
