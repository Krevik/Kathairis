package io.github.krevik.kathairis.client.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import io.github.krevik.kathairis.client.model.ModelFlyingSquid;
import io.github.krevik.kathairis.entity.EntityFlyingSquid;
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
    public void func_225623_a_(EntityFlyingSquid e, float f1, float f2, MatrixStack s, IRenderTypeBuffer i1, int i2) {
        RenderSystem.translatef(0, -1, 0);
        RenderSystem.rotatef(90, 0, 1, 0);
        if(e.isDiving()) {
            RenderSystem.rotatef(180, 0, 0, 1);
        }
        if(e.isChild()) {
            RenderSystem.scaled(0.6, 0.6, 0.6);
        }
        super.func_225623_a_(e,f1,f2,s,i1,i2);
    }
    
}
