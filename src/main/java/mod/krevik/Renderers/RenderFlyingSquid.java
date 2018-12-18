package mod.krevik.Renderers;

import mod.krevik.Entities.EntityFlyingSquid;
import mod.krevik.Main.EntityAndRenderRegistry;
import mod.krevik.Models.ModelFlyingSquid;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderFlyingSquid extends RenderLiving<EntityFlyingSquid>
{
	
    public static final Factory FACTORY = new Factory();

    public RenderFlyingSquid(RenderManager renderManagerIn)
    {
        super(renderManagerIn, new ModelFlyingSquid(), 1.5F);
    }

    
    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(EntityFlyingSquid entity)
    {
        return EntityAndRenderRegistry.FlyingSquidLoc;
    }
    
    public static class Factory implements IRenderFactory<EntityFlyingSquid> {

        @Override
        public Render<? super EntityFlyingSquid> createRenderFor(RenderManager manager) {
            return new RenderFlyingSquid(manager);
        }

    }
    
    protected void applyRotations(EntityFlyingSquid entityLiving, float p_77043_2_, float rotationYaw, float partialTicks)
    {
    	GlStateManager.translate(0, -1, 0);
    	GlStateManager.rotate(90, 0, 1, 0);
    	if(entityLiving.isDiving()) {
        	GlStateManager.rotate(180, 0, 0, 1);
    	}
    	if(entityLiving.isChild()) {
        	GlStateManager.scale(0.6, 0.6, 0.6);
    	}
        super.applyRotations(entityLiving, p_77043_2_, rotationYaw, partialTicks);
        
    }
    
}
