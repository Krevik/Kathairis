package mod.krevik.Renderers;

import mod.krevik.Entities.EntityCactiSpore;
import mod.krevik.Main.EntityAndRenderRegistry;
import mod.krevik.Models.ModelCactiSpore;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderCactiSpore extends RenderLiving<EntityCactiSpore>
{

    public static final Factory FACTORY = new Factory();

    public RenderCactiSpore(RenderManager renderManagerIn)
    {
        super(renderManagerIn, new ModelCactiSpore(), 0F);
    }


    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(EntityCactiSpore entity)
    {
        return EntityAndRenderRegistry.CactiSporeLoc;
    }

    public static class Factory implements IRenderFactory<EntityCactiSpore> {

        @Override
        public Render<? super EntityCactiSpore> createRenderFor(RenderManager manager) {
            return new RenderCactiSpore(manager);
        }

    }


}
