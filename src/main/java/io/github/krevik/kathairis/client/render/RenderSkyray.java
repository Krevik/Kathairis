package io.github.krevik.kathairis.client.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import io.github.krevik.kathairis.client.model.ModelSkyray;
import io.github.krevik.kathairis.entity.EntityPhasm;
import io.github.krevik.kathairis.entity.EntitySkyray;
import io.github.krevik.kathairis.util.TextureLocationsRef;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.Quaternion;
import net.minecraft.client.renderer.entity.EnderDragonRenderer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import org.lwjgl.opengl.GL11;

@OnlyIn(Dist.CLIENT)
public class RenderSkyray extends MobRenderer<EntitySkyray, ModelSkyray<EntitySkyray>>
{
    public static final Factory FACTORY = new Factory();

    public RenderSkyray(EntityRendererManager renderManagerIn)
    {
        super(renderManagerIn, new ModelSkyray(), 6F);

    }


    @Override
    public ResourceLocation getEntityTexture(EntitySkyray entity)
    {
        return TextureLocationsRef.SkyrayLoc;
    }
    
    public static class Factory implements IRenderFactory<EntitySkyray> {

        @Override
        public EntityRenderer<? super EntitySkyray> createRenderFor(EntityRendererManager manager) {

            return new RenderSkyray(manager);

        }

    }

    @Override
    protected void func_225621_a_(EntitySkyray e, MatrixStack s, float p_225621_3_, float p_225621_4_, float p_225621_5_) {
        //TODO fix rotations
        s.rotate(new Quaternion(0,0,1,-90));
        if(e.getAdult()==0) {
            s.scale(2, 2, 2);
        }else {
            s.scale(8, 8, 8);
        }

        super.func_225621_a_(e, s, p_225621_3_, p_225621_4_, p_225621_5_);
    }

    
}
