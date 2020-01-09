package io.github.krevik.kathairis.client.render;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import io.github.krevik.kathairis.Kathairis;
import io.github.krevik.kathairis.client.model.ModelFungite;
import io.github.krevik.kathairis.entity.EntityFungite;
import io.github.krevik.kathairis.util.TextureLocationsRef;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;

import java.util.Random;

@OnlyIn(Dist.CLIENT)
public class RenderFungite extends MobRenderer<EntityFungite,ModelFungite<EntityFungite>>
{
	boolean isBlinking=false;
    public static final Factory FACTORY = new Factory();

    public RenderFungite(EntityRendererManager renderManagerIn)
    {
        super(renderManagerIn, new ModelFungite(), 1F);
    }


    @Override
    public ResourceLocation getEntityTexture(EntityFungite entity)
    {
    	if(isBlinking) {
    		return TextureLocationsRef.FungiteOLoc;
    	}else {
    		return TextureLocationsRef.FungiteLoc;
    	}
    }
    
    public static class Factory implements IRenderFactory<EntityFungite> {

        @Override
        public EntityRenderer<? super EntityFungite> createRenderFor(EntityRendererManager manager) {
            return new RenderFungite(manager);
        }

    }
    Random random = Kathairis.getHelper().getRandom();
    @Override
    protected void applyRotations(EntityFungite entityLiving, float p_77043_2_, float rotationYaw, float partialTicks)
    {
    	RenderSystem.scaled(0.8, 0.8, 0.8);
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
