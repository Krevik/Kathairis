package io.github.krevik.kathairis.client.render;

import io.github.krevik.kathairis.client.model.ModelCactiSpore;
import io.github.krevik.kathairis.entity.EntityCactiSpore;
import io.github.krevik.kathairis.util.TextureLocationsRef;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;

@OnlyIn(Dist.CLIENT)
public class RenderCactiSpore extends RenderLiving<EntityCactiSpore>
{

    public static final Factory FACTORY = new Factory();

    public RenderCactiSpore(RenderManager renderManagerIn)
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
        public Render<? super EntityCactiSpore> createRenderFor(RenderManager manager) {
            return new RenderCactiSpore(manager);
        }

    }


}
