package io.github.krevik.kathairis.client.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import io.github.krevik.kathairis.client.model.ModelPoisonousScorpion;
import io.github.krevik.kathairis.entity.EntityPoisonousScorpion;
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
public class RenderPoisonousScorpion extends MobRenderer<EntityPoisonousScorpion, ModelPoisonousScorpion<EntityPoisonousScorpion>>
{
	
    public static final Factory FACTORY = new Factory();

    public RenderPoisonousScorpion(EntityRendererManager renderManagerIn)
    {
        super(renderManagerIn, new ModelPoisonousScorpion(), 0.4F);
    }


    @Override
    public ResourceLocation getEntityTexture(EntityPoisonousScorpion entity)
    {
        return TextureLocationsRef.PoisonousScorpionLoc;
    }
    
    public static class Factory implements IRenderFactory<EntityPoisonousScorpion> {

        @Override
        public EntityRenderer<? super EntityPoisonousScorpion> createRenderFor(EntityRendererManager manager) {
            return new RenderPoisonousScorpion(manager);
        }

    }

    @Override
    public void func_225623_a_(EntityPoisonousScorpion e, float f1, float f2, MatrixStack s, IRenderTypeBuffer i1, int i2) {
        s.func_227862_a_(0.5f, 0.5f, 0.5f);
        super.func_225623_a_(e,f1,f2,s,i1,i2);
    }
    
}
