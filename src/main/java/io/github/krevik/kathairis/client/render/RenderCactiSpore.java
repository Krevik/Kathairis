package io.github.krevik.kathairis.client.render;

import io.github.krevik.kathairis.client.model.ModelCactiSpore;
import io.github.krevik.kathairis.entity.EntityCactiSpore;
import io.github.krevik.kathairis.util.TextureLocationsRef;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;

@OnlyIn(Dist.CLIENT)
public class RenderCactiSpore extends MobRenderer<EntityCactiSpore,ModelCactiSpore<EntityCactiSpore>>
{

    public static final Factory FACTORY = new Factory();

    public RenderCactiSpore(EntityRendererManager renderManagerIn)
    {
        super(renderManagerIn, new ModelCactiSpore(), 0F);
    }


    @Override
    protected ResourceLocation getEntityTexture(EntityCactiSpore entity)
    {
        return TextureLocationsRef.CactiSporeLoc;
    }

    public static class Factory implements IRenderFactory<EntityCactiSpore> {

        @Override
        public EntityRenderer<? super EntityCactiSpore> createRenderFor(EntityRendererManager manager) {
            return new RenderCactiSpore(manager);
        }

    }


}
