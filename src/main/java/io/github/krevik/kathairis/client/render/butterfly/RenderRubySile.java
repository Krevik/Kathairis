package io.github.krevik.kathairis.client.render.butterfly;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import io.github.krevik.kathairis.client.model.butterfly.ModelRubySile;
import io.github.krevik.kathairis.entity.butterfly.EntityRubySile;
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
public class RenderRubySile extends MobRenderer<EntityRubySile, ModelRubySile<EntityRubySile>>
{
    public static final Factory FACTORY = new Factory();
    public RenderRubySile(EntityRendererManager renderManagerIn)
    {
        super(renderManagerIn, new ModelRubySile(), 0F);
    }

    @Override
    public ResourceLocation getEntityTexture(EntityRubySile entity)
    {
            return TextureLocationsRef.RubySileLoc;
    }
    
    public static class Factory implements IRenderFactory<EntityRubySile> {

        @Override
        public EntityRenderer<? super EntityRubySile> createRenderFor(EntityRendererManager manager) {
            return new RenderRubySile(manager);
        }

    }

    @Override
    public void func_225623_a_(EntityRubySile e, float f1, float f2, MatrixStack s, IRenderTypeBuffer i1, int i2) {
        RenderSystem.scaled(0.2, 0.2, 0.2);
        super.func_225623_a_(e,f1,f2,s,i1,i2);
    }
    
}
