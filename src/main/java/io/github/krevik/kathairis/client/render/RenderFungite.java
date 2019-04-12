package io.github.krevik.kathairis.client.render;

import io.github.krevik.kathairis.Kathairis;
import io.github.krevik.kathairis.client.model.ModelFungite;
import io.github.krevik.kathairis.entity.EntityFungite;
import io.github.krevik.kathairis.util.TextureLocationsRef;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;

import java.util.Random;

@OnlyIn(Dist.CLIENT)
public class RenderFungite extends RenderLiving<EntityFungite>
{
	boolean isBlinking=false;
    public static final Factory FACTORY = new Factory();

    public RenderFungite(RenderManager renderManagerIn)
    {
        super(renderManagerIn, new ModelFungite(), 1F);
    }


    @Override
    protected ResourceLocation getEntityTexture(EntityFungite entity)
    {
    	if(isBlinking) {
    		return TextureLocationsRef.FungiteOLoc;
    	}else {
    		return TextureLocationsRef.FungiteLoc;
    	}
    }
    
    public static class Factory implements IRenderFactory<EntityFungite> {

        @Override
        public Render<? super EntityFungite> createRenderFor(RenderManager manager) {
            return new RenderFungite(manager);
        }

    }
    Random random = Kathairis.getHelper().getRandom();
    @Override
    protected void applyRotations(EntityFungite entityLiving, float p_77043_2_, float rotationYaw, float partialTicks)
    {
    	GlStateManager.scaled(0.8, 0.8, 0.8);
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
