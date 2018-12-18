package mod.krevik.client.renderer;

import mod.krevik.entity.EntityCloudySlime;
import mod.krevik.util.EntityAndRenderRegistry;
import mod.krevik.client.model.ModelCloudySlime;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderCloudySlime extends RenderLiving<EntityCloudySlime>
{
    public static final Factory FACTORY = new Factory();

    public RenderCloudySlime(RenderManager renderManagerIn)
    {
        super(renderManagerIn, new ModelCloudySlime(), 0F);

    }

    
    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(EntityCloudySlime entity)
    {
        return EntityAndRenderRegistry.CloudySlimeLoc;
    }
    
    public static class Factory implements IRenderFactory<EntityCloudySlime> {

        @Override
        public Render<? super EntityCloudySlime> createRenderFor(RenderManager manager) {

            return new RenderCloudySlime(manager);

        }

    }

    protected void applyRotations(EntityCloudySlime entityLiving, float p_77043_2_, float rotationYaw, float partialTicks)
    {
    	GlStateManager.rotate(90, 0, 1, 0);
    	GlStateManager.scale(0.7, 0.7, 0.7);

        super.applyRotations(entityLiving, p_77043_2_, rotationYaw, partialTicks);
    }
    
}
