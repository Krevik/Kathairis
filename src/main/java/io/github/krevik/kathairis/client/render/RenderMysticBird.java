package io.github.krevik.kathairis.client.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import io.github.krevik.kathairis.client.model.ModelMysticBird;
import io.github.krevik.kathairis.entity.EntityMysticBird;
import io.github.krevik.kathairis.util.TextureLocationsRef;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
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
    public ResourceLocation getEntityTexture(EntityMysticBird entity)
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
    public void func_225623_a_(EntityMysticBird e, float f1, float f2, MatrixStack s, IRenderTypeBuffer i1, int i2) {
        RenderSystem.scaled(0.4, 0.4, 0.4);
        super.func_225623_a_(e,f1,f2,s,i1,i2);
    }
    
}
