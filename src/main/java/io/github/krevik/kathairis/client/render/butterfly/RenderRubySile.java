package io.github.krevik.kathairis.client.render.butterfly;

import io.github.krevik.kathairis.client.model.butterfly.ModelRubySile;
import io.github.krevik.kathairis.entity.butterfly.EntityRubySile;
import io.github.krevik.kathairis.util.TextureLocationsRef;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;

@OnlyIn(Dist.CLIENT)
public class RenderRubySile extends RenderLiving<EntityRubySile>
{
    public static final Factory FACTORY = new Factory();
    public RenderRubySile(RenderManager renderManagerIn)
    {
        super(renderManagerIn, new ModelRubySile(), 0F);
    }


    @Override
    protected ResourceLocation getEntityTexture(EntityRubySile entity)
    {
            return TextureLocationsRef.RubySileLoc;
    }
    
    public static class Factory implements IRenderFactory<EntityRubySile> {

        @Override
        public Render<? super EntityRubySile> createRenderFor(RenderManager manager) {
            return new RenderRubySile(manager);
        }

    }

    @Override
    protected void applyRotations(EntityRubySile entityLiving, float p_77043_2_, float rotationYaw, float partialTicks)
    {
    	GlStateManager.scaled(0.2, 0.2, 0.2);
        super.applyRotations(entityLiving, p_77043_2_, rotationYaw, partialTicks);
        
    }
    
}
