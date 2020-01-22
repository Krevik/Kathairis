package io.github.krevik.kathairis.client.render.butterfly;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import io.github.krevik.kathairis.client.model.butterfly.ModelCloudShimmer;
import io.github.krevik.kathairis.entity.EntityCloudySlime;
import io.github.krevik.kathairis.entity.butterfly.EntityButterfly;
import io.github.krevik.kathairis.entity.butterfly.EntityCloudShimmer;
import io.github.krevik.kathairis.util.TextureLocationsRef;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.SlimeRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;

@OnlyIn(Dist.CLIENT)
public class RenderCloudShimmer extends MobRenderer<EntityCloudShimmer, ModelCloudShimmer<EntityCloudShimmer>>
{
    public static final Factory FACTORY = new Factory();
    public RenderCloudShimmer(EntityRendererManager renderManagerIn)
    {
        super(renderManagerIn, new ModelCloudShimmer(), 0F);
    }


    @Override
    public ResourceLocation getEntityTexture(EntityCloudShimmer entity)
    {
            return TextureLocationsRef.CloudShimmerLoc;
    }
    
    public static class Factory implements IRenderFactory<EntityCloudShimmer> {

        @Override
        public EntityRenderer<? super EntityCloudShimmer> createRenderFor(EntityRendererManager manager) {
            return new RenderCloudShimmer(manager);
        }

    }

    @Override
    protected void func_225621_a_(EntityCloudShimmer p_225621_1_, MatrixStack s, float p_225621_3_, float p_225621_4_, float p_225621_5_) {
        s.scale(0.3f,0.3f,0.3f);
        super.func_225621_a_(p_225621_1_, s, p_225621_3_, p_225621_4_, p_225621_5_);
    }
    
}
