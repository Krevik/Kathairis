package io.github.krevik.kathairis.client.render;

import io.github.krevik.kathairis.client.model.ModelPhasm;
import io.github.krevik.kathairis.entity.EntityPhasm;
import io.github.krevik.kathairis.util.TextureLocationsRef;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import org.lwjgl.opengl.GL11;

@OnlyIn(Dist.CLIENT)
public class RenderPhasm extends RenderLiving<EntityPhasm> {

    public static final Factory FACTORY = new Factory();

    public RenderPhasm(RenderManager renderManagerIn) {
        super(renderManagerIn, new ModelPhasm(), 0.0F);
    }


    @Override
    protected ResourceLocation getEntityTexture(EntityPhasm entity) {
        return TextureLocationsRef.PhasmLoc;
    }

    public static class Factory implements IRenderFactory<EntityPhasm> {

        @Override
        public Render<? super EntityPhasm> createRenderFor(RenderManager manager) {
            return new RenderPhasm(manager);
        }

    }

    @Override
    protected void applyRotations(EntityPhasm entityLiving, float p_77043_2_, float rotationYaw, float partialTicks) {
        GL11.glEnable(GL11.GL_NORMALIZE);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        super.applyRotations(entityLiving, p_77043_2_, rotationYaw, partialTicks);

    }




}