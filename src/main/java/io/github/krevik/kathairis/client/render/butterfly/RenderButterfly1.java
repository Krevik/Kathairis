package io.github.krevik.kathairis.client.render.butterfly;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import io.github.krevik.kathairis.client.model.butterfly.ModelButterfly;
import io.github.krevik.kathairis.entity.butterfly.EntityButterfly;
import io.github.krevik.kathairis.entity.butterfly.EntityButterfly1;
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
public class RenderButterfly1 extends MobRenderer<EntityButterfly1, ModelButterfly<EntityButterfly1>>
{
    public static final Factory FACTORY = new Factory();
    public RenderButterfly1(EntityRendererManager renderManagerIn)
    {
        super(renderManagerIn, new ModelButterfly(), 0F);
    }


    @Override
    public ResourceLocation getEntityTexture(EntityButterfly1 entity)
    {
            return TextureLocationsRef.Butterfly1Loc;
    }
    
    public static class Factory implements IRenderFactory<EntityButterfly1> {

        @Override
        public EntityRenderer<? super EntityButterfly1> createRenderFor(EntityRendererManager manager) {
            return new RenderButterfly1(manager);
        }

    }


    @Override
    public void func_225623_a_(EntityButterfly1 e, float f1, float f2, MatrixStack s, IRenderTypeBuffer i1, int i2) {
        s.func_227862_a_(0.2f,0.2f,0.2f);
        //RenderSystem.scaled(0.2, 0.2, 0.2);
        super.func_225623_a_(e,f1,f2,s,i1,i2);
    }
}
