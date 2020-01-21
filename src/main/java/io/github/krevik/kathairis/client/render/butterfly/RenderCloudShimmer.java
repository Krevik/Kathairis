package io.github.krevik.kathairis.client.render.butterfly;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import io.github.krevik.kathairis.client.model.butterfly.ModelCloudShimmer;
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
    public void func_225623_a_(EntityCloudShimmer e, float f1, float f2, MatrixStack s, IRenderTypeBuffer i1, int i2) {
        RenderSystem.scaled(0.3, 0.3, 0.3);
        super.func_225623_a_(e,f1,f2,s,i1,i2);
    }
    
}
