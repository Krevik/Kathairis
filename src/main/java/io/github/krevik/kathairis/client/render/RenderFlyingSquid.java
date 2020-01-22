package io.github.krevik.kathairis.client.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import io.github.krevik.kathairis.client.model.ModelFlyingSquid;
import io.github.krevik.kathairis.entity.EntityCloudOister;
import io.github.krevik.kathairis.entity.EntityFlyingSquid;
import io.github.krevik.kathairis.entity.EntityFungite;
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
public class RenderFlyingSquid extends MobRenderer<EntityFlyingSquid,ModelFlyingSquid<EntityFlyingSquid>>
{
	
    public static final Factory FACTORY = new Factory();

    public RenderFlyingSquid(EntityRendererManager renderManagerIn)
    {
        super(renderManagerIn, new ModelFlyingSquid(), 1.5F);
    }


    @Override
    public ResourceLocation getEntityTexture(EntityFlyingSquid entity)
    {
        return TextureLocationsRef.FlyingSquidLoc;
    }
    
    public static class Factory implements IRenderFactory<EntityFlyingSquid> {

        @Override
        public EntityRenderer<? super EntityFlyingSquid> createRenderFor(EntityRendererManager manager) {
            return new RenderFlyingSquid(manager);
        }

    }

    @Override
    protected void func_225621_a_(EntityFlyingSquid e, MatrixStack s, float p_225621_3_, float p_225621_4_, float p_225621_5_) {
        s.translate(0, -1, 0);
        s.rotate(new Quaternion(90, 0, 1, 0));
        if(e.isDiving()) {
            s.rotate(new Quaternion(180, 0, 0, 1));
        }
        if(e.isChild()) {
            s.scale(0.6f, 0.6f, 0.6f);
        }
        super.func_225621_a_(e, s, p_225621_3_, p_225621_4_, p_225621_5_);
    }
    
}
