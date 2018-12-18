package mod.krevik.client.renderer;

import mod.krevik.entity.EntityCamel;
import mod.krevik.util.EntityAndRenderRegistry;
import mod.krevik.client.model.ModelCamel;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderCamel extends RenderLiving<EntityCamel>
{
	
    public static final Factory FACTORY = new Factory();

    public RenderCamel(RenderManager renderManagerIn)
    {
        super(renderManagerIn, new ModelCamel(), 1.5F);
    }

    
    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(EntityCamel entity)
    {
        return EntityAndRenderRegistry.CamelLoc;
    }
    
    public static class Factory implements IRenderFactory<EntityCamel> {

        @Override
        public Render<? super EntityCamel> createRenderFor(RenderManager manager) {
            return new RenderCamel(manager);
        }

    }
    
    protected void applyRotations(EntityCamel entityLiving, float p_77043_2_, float rotationYaw, float partialTicks)
    {
    	if(entityLiving.isChild()) {
        	GlStateManager.scale(0.6, 0.6, 0.6);
    	}
        super.applyRotations(entityLiving, p_77043_2_, rotationYaw, partialTicks);
        
    }
    
}
