package mod.krevik.client.renderer;

import java.util.Random;

import mod.krevik.entity.EntityFungite;
import mod.krevik.util.EntityAndRenderRegistry;
import mod.krevik.KCore;
import mod.krevik.client.model.ModelFungite;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderFungite extends RenderLiving<EntityFungite>
{
	boolean isBlinking=false;
    public static final Factory FACTORY = new Factory();

    public RenderFungite(RenderManager renderManagerIn)
    {
        super(renderManagerIn, new ModelFungite(), 1F);
    }

    
    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(EntityFungite entity)
    {
    	if(isBlinking) {
    		return EntityAndRenderRegistry.FungiteOLoc;
    	}else {
    		return EntityAndRenderRegistry.FungiteLoc;
    	}
    }
    
    public static class Factory implements IRenderFactory<EntityFungite> {

        @Override
        public Render<? super EntityFungite> createRenderFor(RenderManager manager) {
            return new RenderFungite(manager);
        }

    }
    Random random = KCore.instance.functionHelper.random;
    protected void applyRotations(EntityFungite entityLiving, float p_77043_2_, float rotationYaw, float partialTicks)
    {
    	GlStateManager.scale(0.8, 0.8, 0.8);
        super.applyRotations(entityLiving, p_77043_2_, rotationYaw, partialTicks);
        if(!isBlinking) {
	        if(this.random.nextInt(400)==0) {
	        	this.isBlinking=true;
	        }
        }
        if(isBlinking) {
        	if(random.nextInt(100)==0) {
        		this.isBlinking=false;
        	}
        }
    }
    
}
