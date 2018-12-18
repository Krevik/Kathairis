package mod.krevik.Renderers.Butterflies;

import mod.krevik.Entities.Butterflies.EntitySkylight;
import mod.krevik.Main.EntityAndRenderRegistry;
import mod.krevik.Models.Butterflies.ModelSkylight;

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
public class RenderSkylight extends RenderLiving<EntitySkylight>
{
    public static final Factory FACTORY = new Factory();
    Random random = new Random();
    public RenderSkylight(RenderManager renderManagerIn)
    {
        super(renderManagerIn, new ModelSkylight(), 0F);
    }

    
    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(EntitySkylight entity)
    {
            return EntityAndRenderRegistry.SkylightLoc;
    }
    
    public static class Factory implements IRenderFactory<EntitySkylight> {

        @Override
        public Render<? super EntitySkylight> createRenderFor(RenderManager manager) {
            return new RenderSkylight(manager);
        }

    }
    
    
    protected void applyRotations(EntitySkylight entityLiving, float p_77043_2_, float rotationYaw, float partialTicks)
    {
    	GlStateManager.scale(0.3, 0.3, 0.3);
        super.applyRotations(entityLiving, p_77043_2_, rotationYaw, partialTicks);
        
    }
    
}
