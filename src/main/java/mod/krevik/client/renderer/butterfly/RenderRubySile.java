package mod.krevik.client.renderer.butterfly;

import mod.krevik.entity.butterfly.EntityRubySile;
import mod.krevik.util.EntityAndRenderRegistry;
import mod.krevik.client.model.butterfly.ModelRubySile;

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
public class RenderRubySile extends RenderLiving<EntityRubySile>
{
    public static final Factory FACTORY = new Factory();
    Random random = new Random();
    public RenderRubySile(RenderManager renderManagerIn)
    {
        super(renderManagerIn, new ModelRubySile(), 0F);
    }

    
    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(EntityRubySile entity)
    {
            return EntityAndRenderRegistry.RubySileLoc;
    }
    
    public static class Factory implements IRenderFactory<EntityRubySile> {

        @Override
        public Render<? super EntityRubySile> createRenderFor(RenderManager manager) {
            return new RenderRubySile(manager);
        }

    }
    
    protected void applyRotations(EntityRubySile entityLiving, float p_77043_2_, float rotationYaw, float partialTicks)
    {
    	GlStateManager.scale(0.2, 0.2, 0.2);
        super.applyRotations(entityLiving, p_77043_2_, rotationYaw, partialTicks);
        
    }
    
}
