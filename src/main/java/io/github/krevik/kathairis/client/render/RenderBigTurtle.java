package io.github.krevik.kathairis.client.render;

import io.github.krevik.kathairis.client.model.ModelBigTurtle;
import io.github.krevik.kathairis.entity.EntityBigTurtle;
import io.github.krevik.kathairis.util.TextureLocationsRef;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;

@OnlyIn(Dist.CLIENT)
public class RenderBigTurtle extends RenderLiving<EntityBigTurtle>
{
	
    public static final Factory FACTORY = new Factory();

    public RenderBigTurtle(RenderManager renderManagerIn)
    {
        super(renderManagerIn, new ModelBigTurtle(), 0.8F);
    }


    @Override
    protected ResourceLocation getEntityTexture(EntityBigTurtle entity)
    {
        return TextureLocationsRef.BigTurtleLoc;
    }
    
    public static class Factory implements IRenderFactory<EntityBigTurtle> {

        @Override
        public Render<? super EntityBigTurtle> createRenderFor(RenderManager manager) {
            return new RenderBigTurtle(manager);
        }

    }

    @Override
    protected void applyRotations(EntityBigTurtle entityLiving, float p_77043_2_, float rotationYaw, float partialTicks)
    {
        super.applyRotations(entityLiving, p_77043_2_, rotationYaw, partialTicks);
        
    }
    
}
