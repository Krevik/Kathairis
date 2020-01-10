package io.github.krevik.kathairis.client.render;

import io.github.krevik.kathairis.client.model.ModelBigTurtle;
import io.github.krevik.kathairis.entity.EntityBigTurtle;
import io.github.krevik.kathairis.util.TextureLocationsRef;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;

@OnlyIn(Dist.CLIENT)
public class RenderBigTurtle extends MobRenderer<EntityBigTurtle,ModelBigTurtle<EntityBigTurtle>>
{
	
    public static final Factory FACTORY = new Factory();

    public RenderBigTurtle(EntityRendererManager renderManagerIn)
    {
        super(renderManagerIn, new ModelBigTurtle(), 0.8F);
    }


    @Override
    public ResourceLocation getEntityTexture(EntityBigTurtle entity)
    {
        return TextureLocationsRef.BigTurtleLoc;
    }
    
    public static class Factory implements IRenderFactory<EntityBigTurtle> {

        @Override
        public EntityRenderer<? super EntityBigTurtle> createRenderFor(EntityRendererManager manager) {
            return new RenderBigTurtle(manager);
        }

    }
    
}
