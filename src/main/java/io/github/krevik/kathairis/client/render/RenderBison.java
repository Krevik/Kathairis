package io.github.krevik.kathairis.client.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import io.github.krevik.kathairis.client.model.ModelBison;
import io.github.krevik.kathairis.entity.EntityBison;
import io.github.krevik.kathairis.entity.butterfly.EntityButterfly;
import io.github.krevik.kathairis.util.TextureLocationsRef;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.SlimeRenderer;
import net.minecraft.entity.monster.SlimeEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;

@OnlyIn(Dist.CLIENT)
public class RenderBison extends MobRenderer<EntityBison, ModelBison<EntityBison>>
{
    public static final Factory FACTORY = new Factory();

    public RenderBison(EntityRendererManager renderManagerIn)
    {
        super(renderManagerIn, new ModelBison(), 1F);
    }


    @Override
    public ResourceLocation getEntityTexture(EntityBison entity)
    {
        return TextureLocationsRef.BisonLoc;
    }
    
    public static class Factory implements IRenderFactory<EntityBison> {

        @Override
        public EntityRenderer<? super EntityBison> createRenderFor(EntityRendererManager manager) {
            return new RenderBison(manager);
        }

    }

    @Override
    protected void func_225621_a_(EntityBison p_225621_1_, MatrixStack s, float p_225621_3_, float p_225621_4_, float p_225621_5_) {
        s.scale(1.5f,1.5f,1.5f);
        super.func_225621_a_(p_225621_1_, s, p_225621_3_, p_225621_4_, p_225621_5_);
    }

    
}
