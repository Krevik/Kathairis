package io.github.krevik.kathairis.client.render;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import io.github.krevik.kathairis.client.model.ModelGecko;
import io.github.krevik.kathairis.entity.EntityGecko;
import io.github.krevik.kathairis.util.TextureLocationsRef;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;

@OnlyIn(Dist.CLIENT)
public class RenderGecko extends MobRenderer<EntityGecko, ModelGecko<EntityGecko>>
{
	
    public static final Factory FACTORY = new Factory();

    public RenderGecko(EntityRendererManager renderManagerIn)
    {
        super(renderManagerIn, new ModelGecko(), 0F);
    }


    @Override
    public ResourceLocation getEntityTexture(EntityGecko entity)
    {
        return TextureLocationsRef.GeckoLoc;
    }
    
    public static class Factory implements IRenderFactory<EntityGecko> {

        @Override
        public EntityRenderer<? super EntityGecko> createRenderFor(EntityRendererManager manager) {
            return new RenderGecko(manager);
        }

    }

    @Override
    protected void applyRotations(EntityGecko entityLiving, float p_77043_2_, float rotationYaw, float partialTicks)
    {
    	/*if(entityLiving.isChild()) {
        	GlStateManager.scale(0.5, 0.5, 0.5);
    	}else {
        	GlStateManager.scale(0.8, 0.8, 0.8);
    	}*/
        if (entityLiving.isClimbing()) {
            if (entityLiving.climbingSide() == EntityGecko.EnumClimbSide.EAST) {
                RenderSystem.rotatef(90, 0, 0, 1);
            } else if (entityLiving.climbingSide() == EntityGecko.EnumClimbSide.WEST) {
                RenderSystem.rotatef(90, 0, 0, -1);
            } else if (entityLiving.climbingSide() == EntityGecko.EnumClimbSide.NORTH) {
                RenderSystem.rotatef(90, 1, 0, 0);
            } else if (entityLiving.climbingSide() == EntityGecko.EnumClimbSide.SOUTH) {
                RenderSystem.rotatef(90, -1, 0, 0);
            }
            RenderSystem.translatef(0f, -0.3F, 0F);
        }
        super.applyRotations(entityLiving, p_77043_2_, rotationYaw, partialTicks);
        
    }
    
}
