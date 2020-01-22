package io.github.krevik.kathairis.client.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import io.github.krevik.kathairis.Kathairis;
import io.github.krevik.kathairis.client.model.ModelFungite;
import io.github.krevik.kathairis.entity.EntityFlyingSquid;
import io.github.krevik.kathairis.entity.EntityFungite;
import io.github.krevik.kathairis.util.TextureLocationsRef;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.Quaternion;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
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
    protected void func_225621_a_(EntityFungite e, MatrixStack s, float p_225621_3_, float p_225621_4_, float p_225621_5_) {
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
        s.scale(0.8f, 0.8f, 0.8f);
        super.func_225621_a_(e, s, p_225621_3_, p_225621_4_, p_225621_5_);
    }
    
}
