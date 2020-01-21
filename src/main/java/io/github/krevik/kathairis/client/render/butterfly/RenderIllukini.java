package io.github.krevik.kathairis.client.render.butterfly;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import io.github.krevik.kathairis.client.model.butterfly.ModelIllukini;
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
    public void func_225623_a_(EntityIllukini e, float f1, float f2, MatrixStack s, IRenderTypeBuffer i1, int i2) {
        s.func_227862_a_(0.3f, 0.3f, 0.3f);
        super.func_225623_a_(e,f1,f2,s,i1,i2);
    }
    
}
