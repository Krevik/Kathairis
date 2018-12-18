package mod.krevik.Renderers.Butterflies;

import mod.krevik.Entities.Butterflies.EntityButterfly;
import mod.krevik.Main.EntityAndRenderRegistry;
import mod.krevik.Models.Butterflies.ModelButterfly;

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
public class RenderButterfly extends RenderLiving<EntityButterfly>
{
    public static final Factory FACTORY = new Factory();
    Random random = new Random();
    public RenderButterfly(RenderManager renderManagerIn)
    {
        super(renderManagerIn, new ModelButterfly(), 0F);
    }

    
    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(EntityButterfly entity)
    {
            return EntityAndRenderRegistry.ButterflyLoc;
    }
    
    public static class Factory implements IRenderFactory<EntityButterfly> {

        @Override
        public Render<? super EntityButterfly> createRenderFor(RenderManager manager) {
            return new RenderButterfly(manager);
        }

    }
    
    protected void applyRotations(EntityButterfly entityLiving, float p_77043_2_, float rotationYaw, float partialTicks)
    {
    	GlStateManager.scale(0.2, 0.2, 0.2);
        super.applyRotations(entityLiving, p_77043_2_, rotationYaw, partialTicks);
        
    }
    
}
