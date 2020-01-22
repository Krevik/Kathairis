package io.github.krevik.kathairis.client.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import io.github.krevik.kathairis.client.model.ModelGecko;
import io.github.krevik.kathairis.entity.EntityCloudOister;
import io.github.krevik.kathairis.entity.EntityGecko;
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
    protected void func_225621_a_(EntityGecko e, MatrixStack s, float p_225621_3_, float p_225621_4_, float p_225621_5_) {
        //TODO fix rotations
        if(e.isChild()) {
            s.scale(0.5f, 0.5f, 0.5f);
        }else {
            s.scale(0.8f, 0.8f, 0.8f);
        }
        if (e.isClimbing()) {
            if (e.climbingSide() == EntityGecko.EnumClimbSide.EAST) {
                s.rotate(new Quaternion(0,0, 1, 90));
            } else if (e.climbingSide() == EntityGecko.EnumClimbSide.WEST) {
                s.rotate(new Quaternion(0,0, 1, -90));
            } else if (e.climbingSide() == EntityGecko.EnumClimbSide.NORTH) {
                s.rotate(new Quaternion(1,0, 0, 90));
            } else if (e.climbingSide() == EntityGecko.EnumClimbSide.SOUTH) {
                s.rotate(new Quaternion(1, 0, 0, -90));
            }
        }
        super.func_225621_a_(e, s, p_225621_3_, p_225621_4_, p_225621_5_);
    }
    
}
