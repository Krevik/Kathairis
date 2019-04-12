package io.github.krevik.kathairis.client.render.butterfly;

import io.github.krevik.kathairis.client.model.butterfly.ModelIllukini;
import io.github.krevik.kathairis.entity.butterfly.EntityIllukini;
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
public class RenderIllukini extends RenderLiving<EntityIllukini>
{
    public static final Factory FACTORY = new Factory();
    public RenderIllukini(RenderManager renderManagerIn)
    {
        super(renderManagerIn, new ModelIllukini(), 0F);
    }


    @Override
    protected ResourceLocation getEntityTexture(EntityIllukini entity)
    {
            return TextureLocationsRef.IllukiniLoc;
    }
    
    public static class Factory implements IRenderFactory<EntityIllukini> {

        @Override
        public Render<? super EntityIllukini> createRenderFor(RenderManager manager) {
            return new RenderIllukini(manager);
        }

    }

    @Override
    protected void applyRotations(EntityIllukini entityLiving, float p_77043_2_, float rotationYaw, float partialTicks)
    {
    	GlStateManager.scaled(0.3, 0.3, 0.3);
        super.applyRotations(entityLiving, p_77043_2_, rotationYaw, partialTicks);
        
    }
    
}
