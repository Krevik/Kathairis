package io.github.krevik.kathairis.client.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import io.github.krevik.kathairis.client.model.ModelCloudySlime;
import io.github.krevik.kathairis.entity.EntityCloudOister;
import io.github.krevik.kathairis.entity.EntityCloudySlime;
import io.github.krevik.kathairis.util.TextureLocationsRef;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.Quaternion;
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
    protected void func_225621_a_(EntityCloudySlime e, MatrixStack s, float p_225621_3_, float p_225621_4_, float p_225621_5_) {
        s.rotate(new Quaternion(90, 0, 1, 0));
        s.scale(0.7f, 0.7f, 0.7f);
        super.func_225621_a_(e, s, p_225621_3_, p_225621_4_, p_225621_5_);
    }
    
}
