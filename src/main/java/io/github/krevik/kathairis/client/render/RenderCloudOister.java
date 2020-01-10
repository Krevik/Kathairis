package io.github.krevik.kathairis.client.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import io.github.krevik.kathairis.client.model.ModelCloudOister;
import io.github.krevik.kathairis.entity.EntityCloudOister;
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
public class RenderCloudOister extends MobRenderer<EntityCloudOister,ModelCloudOister<EntityCloudOister>>
{
	
    public static final Factory FACTORY = new Factory();

    public RenderCloudOister(EntityRendererManager renderManagerIn)
    {
        super(renderManagerIn, new ModelCloudOister(), 0.3F);
    }


    @Override
    public ResourceLocation getEntityTexture(EntityCloudOister entity)
    {
        return TextureLocationsRef.CloudOisterLoc;
    }
    
    public static class Factory implements IRenderFactory<EntityCloudOister> {

        @Override
        public EntityRenderer<? super EntityCloudOister> createRenderFor(EntityRendererManager manager) {
            return new RenderCloudOister(manager);
        }

    }

    @Override
    public void func_225623_a_(EntityCloudOister e, float f1, float f2, MatrixStack s, IRenderTypeBuffer i1, int i2) {
        if(!e.isChild()) {
            RenderSystem.scaled(1.5, 1.5, 1.5);
        }
        RenderSystem.rotatef(180, 0, 1, 0);
        super.func_225623_a_(e,f1,f2,s,i1,i2);
    }
    
}
