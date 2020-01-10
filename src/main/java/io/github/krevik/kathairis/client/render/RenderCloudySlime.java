package io.github.krevik.kathairis.client.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import io.github.krevik.kathairis.client.model.ModelCloudySlime;
import io.github.krevik.kathairis.entity.EntityCloudySlime;
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
public class RenderCloudySlime extends MobRenderer<EntityCloudySlime,ModelCloudySlime<EntityCloudySlime>>
{
    public static final Factory FACTORY = new Factory();

    public RenderCloudySlime(EntityRendererManager renderManagerIn)
    {
        super(renderManagerIn, new ModelCloudySlime(), 0F);

    }


    @Override
    public ResourceLocation getEntityTexture(EntityCloudySlime entity)
    {
        return TextureLocationsRef.CloudySlimeLoc;
    }
    
    public static class Factory implements IRenderFactory<EntityCloudySlime> {

        @Override
        public EntityRenderer<? super EntityCloudySlime> createRenderFor(EntityRendererManager manager) {

            return new RenderCloudySlime(manager);

        }

    }

    @Override
    public void func_225623_a_(EntityCloudySlime e, float f1, float f2, MatrixStack s, IRenderTypeBuffer i1, int i2) {
        RenderSystem.rotatef(90, 0, 1, 0);
        RenderSystem.scaled(0.7, 0.7, 0.7);
        super.func_225623_a_(e,f1,f2,s,i1,i2);
    }
    
}
