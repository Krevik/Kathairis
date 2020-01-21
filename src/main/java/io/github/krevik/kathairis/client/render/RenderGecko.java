package io.github.krevik.kathairis.client.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import io.github.krevik.kathairis.client.model.ModelGecko;
import io.github.krevik.kathairis.entity.EntityGecko;
import io.github.krevik.kathairis.util.TextureLocationsRef;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
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
    public void func_225623_a_(EntityGecko entityLiving, float f1, float f2, MatrixStack s, IRenderTypeBuffer i1, int i2) {
        //TODO fix rotations (translatef)
        if(entityLiving.isChild()) {
        	s.func_227862_a_(0.5f, 0.5f, 0.5f);
    	}else {
        	s.func_227862_a_(0.8f, 0.8f, 0.8f);
    	}
        if (entityLiving.isClimbing()) {
            if (entityLiving.climbingSide() == EntityGecko.EnumClimbSide.EAST) {
                //s.func_227861_a_(0, 0, 90);
            } else if (entityLiving.climbingSide() == EntityGecko.EnumClimbSide.WEST) {
                //s.func_227861_a_(0, 0, -90);
            } else if (entityLiving.climbingSide() == EntityGecko.EnumClimbSide.NORTH) {
                //s.func_227861_a_(90, 0, 0);
            } else if (entityLiving.climbingSide() == EntityGecko.EnumClimbSide.SOUTH) {
                //s.func_227861_a_(-90, 0, 0);
            }
            //s.translatef(0f, -0.0F, 0F);
        }
        super.func_225623_a_(entityLiving,f1,f2,s,i1,i2);
    }
    
}
