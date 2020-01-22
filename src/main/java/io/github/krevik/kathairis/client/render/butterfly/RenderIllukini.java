package io.github.krevik.kathairis.client.render.butterfly;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import io.github.krevik.kathairis.client.model.butterfly.ModelIllukini;
import io.github.krevik.kathairis.entity.butterfly.EntityButterfly;
import io.github.krevik.kathairis.entity.butterfly.EntityIllukini;
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
public class RenderIllukini extends MobRenderer<EntityIllukini, ModelIllukini<EntityIllukini>>
{
    public static final Factory FACTORY = new Factory();
    public RenderIllukini(EntityRendererManager renderManagerIn)
    {
        super(renderManagerIn, new ModelIllukini(), 0F);
    }

    @Override
    public ResourceLocation getEntityTexture(EntityIllukini entity)
    {
            return TextureLocationsRef.IllukiniLoc;
    }
    
    public static class Factory implements IRenderFactory<EntityIllukini> {

        @Override
        public EntityRenderer<? super EntityIllukini> createRenderFor(EntityRendererManager manager) {
            return new RenderIllukini(manager);
        }

    }

    @Override
    protected void func_225621_a_(EntityIllukini p_225621_1_, MatrixStack s, float p_225621_3_, float p_225621_4_, float p_225621_5_) {
        s.scale(0.2f,0.2f,0.2f);
        super.func_225621_a_(p_225621_1_, s, p_225621_3_, p_225621_4_, p_225621_5_);
    }

    
}
