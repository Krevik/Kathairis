package io.github.krevik.kathairis.client.render.butterfly;

import io.github.krevik.kathairis.client.model.butterfly.ModelButterfly;
import io.github.krevik.kathairis.entity.butterfly.EntityButterfly1;
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
public class RenderButterfly1 extends RenderLiving<EntityButterfly1>
{
    public static final Factory FACTORY = new Factory();
    public RenderButterfly1(RenderManager renderManagerIn)
    {
        super(renderManagerIn, new ModelButterfly(), 0F);
    }


    @Override
    protected ResourceLocation getEntityTexture(EntityButterfly1 entity)
    {
            return TextureLocationsRef.Butterfly1Loc;
    }
    
    public static class Factory implements IRenderFactory<EntityButterfly1> {

        @Override
        public Render<? super EntityButterfly1> createRenderFor(RenderManager manager) {
            return new RenderButterfly1(manager);
        }

    }

    @Override
    protected void applyRotations(EntityButterfly1 entityLiving, float p_77043_2_, float rotationYaw, float partialTicks)
    {
    	GlStateManager.scaled(0.2, 0.2, 0.2);
        super.applyRotations(entityLiving, p_77043_2_, rotationYaw, partialTicks);
        
    }
    
}
