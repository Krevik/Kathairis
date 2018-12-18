package mod.krevik.Renderers;

import mod.krevik.Entities.EntityBison;
import mod.krevik.Main.EntityAndRenderRegistry;
import mod.krevik.Models.ModelBison;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderBison extends RenderLiving<EntityBison>
{
	
    public static final Factory FACTORY = new Factory();

    public RenderBison(RenderManager renderManagerIn)
    {
        super(renderManagerIn, new ModelBison(), 1F);
    }

    
    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(EntityBison entity)
    {
        return EntityAndRenderRegistry.BisonLoc;
    }
    
    public static class Factory implements IRenderFactory<EntityBison> {

        @Override
        public Render<? super EntityBison> createRenderFor(RenderManager manager) {
            return new RenderBison(manager);
        }

    }
    
    protected void applyRotations(EntityBison entityLiving, float p_77043_2_, float rotationYaw, float partialTicks)
    {
    	if(!entityLiving.isChild()) {
        	GlStateManager.scale(1.5, 1.5, 1.5);
    	}
        super.applyRotations(entityLiving, p_77043_2_, rotationYaw, partialTicks);
        
    }
    
}
