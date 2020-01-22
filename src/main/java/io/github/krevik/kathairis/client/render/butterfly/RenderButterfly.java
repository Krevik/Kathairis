package io.github.krevik.kathairis.client.render.butterfly;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import io.github.krevik.kathairis.client.model.butterfly.ModelButterfly;
import io.github.krevik.kathairis.entity.butterfly.EntityButterfly;
import io.github.krevik.kathairis.util.TextureLocationsRef;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.entity.passive.BatEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;

@OnlyIn(Dist.CLIENT)
public class RenderButterfly extends MobRenderer<EntityButterfly, ModelButterfly<EntityButterfly>>
{
    public static final Factory FACTORY = new Factory();


    public RenderButterfly(EntityRendererManager renderManagerIn)
    {
        super(renderManagerIn, new ModelButterfly(), 0F);
    }

    
    @Override
    public ResourceLocation getEntityTexture(EntityButterfly entity)
    {
            return TextureLocationsRef.ButterflyLoc;
    }
    
    public static class Factory implements IRenderFactory<EntityButterfly> {

        @Override
        public EntityRenderer<? super EntityButterfly> createRenderFor(EntityRendererManager manager) {
            return new RenderButterfly(manager);
        }

    }

    @Override
    protected void func_225621_a_(EntityButterfly p_225621_1_, MatrixStack s, float p_225621_3_, float p_225621_4_, float p_225621_5_) {
        s.scale(0.2f,0.2f,0.2f);
        super.func_225621_a_(p_225621_1_, s, p_225621_3_, p_225621_4_, p_225621_5_);
    }

}
