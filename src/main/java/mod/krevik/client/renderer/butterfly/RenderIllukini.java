package mod.krevik.client.renderer.butterfly;

import mod.krevik.client.model.butterfly.ModelIllukini;
import mod.krevik.entity.butterfly.EntityIllukini;
import mod.krevik.util.EntityAndRenderRegistry;
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
public class RenderIllukini extends RenderLiving<EntityIllukini>
{
    public static final Factory FACTORY = new Factory();
    Random random = new Random();
    public RenderIllukini(RenderManager renderManagerIn)
    {
        super(renderManagerIn, new ModelIllukini(), 0F);
    }

    
    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(EntityIllukini entity)
    {
            return EntityAndRenderRegistry.IllukiniLoc;
    }
    
    public static class Factory implements IRenderFactory<EntityIllukini> {

        @Override
        public Render<? super EntityIllukini> createRenderFor(RenderManager manager) {
            return new RenderIllukini(manager);
        }

    }
    
    protected void applyRotations(EntityIllukini entityLiving, float p_77043_2_, float rotationYaw, float partialTicks)
    {
    	GlStateManager.scale(0.3, 0.3, 0.3);
        super.applyRotations(entityLiving, p_77043_2_, rotationYaw, partialTicks);
        
    }
    
}
