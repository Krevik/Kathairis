package mod.krevik.client.renderer;

import mod.krevik.entity.EntityJellyFish;
import mod.krevik.util.EntityAndRenderRegistry;
import mod.krevik.client.model.ModelJellyFish;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderJellyFish extends RenderLiving<EntityJellyFish>
{
    public static final Factory FACTORY = new Factory();

    public RenderJellyFish(RenderManager renderManagerIn)
    {
        super(renderManagerIn, new ModelJellyFish(), 0.25F);

    }

    
    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(EntityJellyFish entity)
    {
        return EntityAndRenderRegistry.JellyFishLoc;
    }
    
    public static class Factory implements IRenderFactory<EntityJellyFish> {

        @Override
        public Render<? super EntityJellyFish> createRenderFor(RenderManager manager) {

            return new RenderJellyFish(manager);

        }

    }

    protected void applyRotations(EntityJellyFish entityLiving, float p_77043_2_, float rotationYaw, float partialTicks)
    {
        super.applyRotations(entityLiving, p_77043_2_, rotationYaw, partialTicks);
    }
    
}
