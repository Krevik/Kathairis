package io.github.krevik.kathairis.client.render.butterfly;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import io.github.krevik.kathairis.client.model.butterfly.ModelSkylight;
import io.github.krevik.kathairis.entity.butterfly.EntityButterfly;
import io.github.krevik.kathairis.entity.butterfly.EntitySkylight;
import io.github.krevik.kathairis.util.TextureLocationsRef;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
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
    protected void func_225621_a_(EntitySkylight p_225621_1_, MatrixStack s, float p_225621_3_, float p_225621_4_, float p_225621_5_) {
        s.scale(0.2f,0.2f,0.2f);
        super.func_225621_a_(p_225621_1_, s, p_225621_3_, p_225621_4_, p_225621_5_);
    }
    
}
