package io.github.krevik.kathairis.client.render;

import io.github.krevik.kathairis.client.model.ModelStrangeWanderer;
import io.github.krevik.kathairis.entity.EntityStrangeWanderer;
import io.github.krevik.kathairis.util.TextureLocationsRef;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;

import java.util.Calendar;

@OnlyIn(Dist.CLIENT)
public class RenderStrangeWanderer extends MobRenderer<EntityStrangeWanderer, ModelStrangeWanderer<EntityStrangeWanderer>>
{
    public static final Factory FACTORY = new Factory();

    public RenderStrangeWanderer(EntityRendererManager renderManagerIn)
    {
        super(renderManagerIn, new ModelStrangeWanderer(), 0.5F);

    }

    @Override
    public ResourceLocation getEntityTexture(EntityStrangeWanderer entity)
    {
        int month = Calendar.getInstance().get(Calendar.MONTH);
        if(month==11){
            return TextureLocationsRef.Strange_Wanderer_Christmas_Loc;
        }else {
            return TextureLocationsRef.StrangeWandererLoc;
        }
    }
    
    public static class Factory implements IRenderFactory<EntityStrangeWanderer> {

        @Override
        public EntityRenderer<? super EntityStrangeWanderer> createRenderFor(EntityRendererManager manager) {

            return new RenderStrangeWanderer(manager);

        }

    }
    
}
