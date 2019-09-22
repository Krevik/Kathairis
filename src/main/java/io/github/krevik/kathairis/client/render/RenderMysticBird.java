package io.github.krevik.kathairis.client.render;

import com.mojang.blaze3d.platform.GlStateManager;
import io.github.krevik.kathairis.client.model.ModelMysticBird;
import io.github.krevik.kathairis.entity.EntityMysticBird;
import io.github.krevik.kathairis.util.TextureLocationsRef;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;

import javax.annotation.Nullable;

@OnlyIn(Dist.CLIENT)
public class RenderMysticBird extends MobRenderer<EntityMysticBird, ModelMysticBird<EntityMysticBird>>
{
    public static final Factory FACTORY = new Factory();
    public RenderMysticBird(EntityRendererManager renderManagerIn)
    {
        super(renderManagerIn, new ModelMysticBird(), 0F);
    }


    @Override
    @Nullable
    protected ResourceLocation getEntityTexture(EntityMysticBird entity)
    {
    	int variant=entity.getVariant();
    	if(variant==0) return TextureLocationsRef.MysticBirdLoc;
    	else if(variant==1) return TextureLocationsRef.MysticBird1Loc;
    	else if(variant==2) return TextureLocationsRef.MysticBird2Loc;
    	else if(variant==3) return TextureLocationsRef.MysticBird3Loc;
    	else return TextureLocationsRef.MysticBirdLoc;
    }
    
    public static class Factory implements IRenderFactory<EntityMysticBird> {

        @Override
        public EntityRenderer<? super EntityMysticBird> createRenderFor(EntityRendererManager manager) {
            return new RenderMysticBird(manager);
        }

    }

    @Override
    protected void applyRotations(EntityMysticBird entityLiving, float p_77043_2_, float rotationYaw, float partialTicks)
    {
    	GlStateManager.scaled(0.4, 0.4, 0.4);
        super.applyRotations(entityLiving, p_77043_2_, rotationYaw, partialTicks);
        
    }
    
}
