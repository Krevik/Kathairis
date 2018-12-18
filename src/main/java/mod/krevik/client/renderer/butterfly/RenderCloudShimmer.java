package mod.krevik.client.renderer.butterfly;

import mod.krevik.entity.butterfly.EntityCloudShimmer;
import mod.krevik.util.EntityAndRenderRegistry;
import mod.krevik.client.model.butterfly.ModelCloudShimmer;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import scala.util.Random;

@SideOnly(Side.CLIENT)
public class RenderCloudShimmer extends RenderLiving<EntityCloudShimmer>
{
    public static final Factory FACTORY = new Factory();
    Random random = new Random();
    public RenderCloudShimmer(RenderManager renderManagerIn)
    {
        super(renderManagerIn, new ModelCloudShimmer(), 0F);
    }

    
    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(EntityCloudShimmer entity)
    {
            return EntityAndRenderRegistry.CloudShimmerLoc;
    }
    
    public static class Factory implements IRenderFactory<EntityCloudShimmer> {

        @Override
        public Render<? super EntityCloudShimmer> createRenderFor(RenderManager manager) {
            return new RenderCloudShimmer(manager);
        }

    }
    
    
    protected void applyRotations(EntityCloudShimmer entityLiving, float p_77043_2_, float rotationYaw, float partialTicks)
    {
    	GlStateManager.scale(0.3, 0.3, 0.3);
        super.applyRotations(entityLiving, p_77043_2_, rotationYaw, partialTicks);
        
    }
    
}
