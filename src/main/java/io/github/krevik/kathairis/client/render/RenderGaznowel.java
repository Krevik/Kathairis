package io.github.krevik.kathairis.client.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import io.github.krevik.kathairis.client.model.ModelGaznowel;
import io.github.krevik.kathairis.entity.EntityGaznowel;
import io.github.krevik.kathairis.util.TextureLocationsRef;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import org.lwjgl.opengl.GL11;

@OnlyIn(Dist.CLIENT)
public class RenderGaznowel extends MobRenderer<EntityGaznowel,ModelGaznowel<EntityGaznowel>> {

    public static final Factory FACTORY = new Factory();

    public RenderGaznowel(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new ModelGaznowel(), 0.0F);
        //this.addLayer(new RenderLayerHeldItem(this));
    }


    @Override
    public ResourceLocation getEntityTexture(EntityGaznowel entity) {
        return TextureLocationsRef.GaznowelLoc;
    }

    public static class Factory implements IRenderFactory<EntityGaznowel> {

        @Override
        public EntityRenderer<? super EntityGaznowel> createRenderFor(EntityRendererManager manager) {
            return new RenderGaznowel(manager);
        }

    }

    @Override
    public void func_225623_a_(EntityGaznowel e, float f1, float f2, MatrixStack s, IRenderTypeBuffer i1, int i2) {
        GL11.glEnable(GL11.GL_NORMALIZE);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        super.func_225623_a_(e,f1,f2,s,i1,i2);
    }




}