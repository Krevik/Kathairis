package io.github.krevik.kathairis.client.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import io.github.krevik.kathairis.client.model.ModelCamel;
import io.github.krevik.kathairis.entity.EntityCamel;
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
        super(renderManagerIn, new ModelCamel(), 1.5F);
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
    public void func_225623_a_(EntityCamel e, float f1, float f2, MatrixStack s, IRenderTypeBuffer i1, int i2) {
        if(e.isChild()) {
            RenderSystem.scaled(0.6, 0.6, 0.6);
        }
        super.func_225623_a_(e,f1,f2,s,i1,i2);
    }

    
}
