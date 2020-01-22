package io.github.krevik.kathairis.client.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import io.github.krevik.kathairis.client.model.ModelCamel;
import io.github.krevik.kathairis.entity.EntityCamel;
import io.github.krevik.kathairis.entity.butterfly.EntityButterfly;
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
public class RenderCamel extends MobRenderer<EntityCamel,ModelCamel<EntityCamel>>
{
	
    public static final Factory FACTORY = new Factory();

    public RenderCamel(EntityRendererManager renderManagerIn)
    {
        super(renderManagerIn, new ModelCamel(), 1.0F);
    }


    @Override
    public ResourceLocation getEntityTexture(EntityCamel entity)
    {
        return TextureLocationsRef.CamelLoc;
    }
    
    public static class Factory implements IRenderFactory<EntityCamel> {

        @Override
        public EntityRenderer<? super EntityCamel> createRenderFor(EntityRendererManager manager) {
            return new RenderCamel(manager);
        }

    }

    @Override
    protected void func_225621_a_(EntityCamel e, MatrixStack s, float p_225621_3_, float p_225621_4_, float p_225621_5_) {
        if(e.isChild()) {
            s.scale(0.6f, 0.6f, 0.6f);
        }
        super.func_225621_a_(e, s, p_225621_3_, p_225621_4_, p_225621_5_);
    }

    
}
