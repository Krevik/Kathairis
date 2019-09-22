package io.github.krevik.kathairis.client.render;

import io.github.krevik.kathairis.entity.EntityMysticWandShoot;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;

import javax.annotation.Nullable;

@OnlyIn(Dist.CLIENT)
public class RenderMysticWandShoot extends EntityRenderer<EntityMysticWandShoot> {

    public RenderMysticWandShoot(EntityRendererManager p_i46553_1_) {
        super(p_i46553_1_);
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntityMysticWandShoot entity) {
        return null;
    }

    public void doRender(EntityMysticWandShoot p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_) {

    }

    public static class Factory implements IRenderFactory<EntityMysticWandShoot> {

        @Override
        public EntityRenderer<? super EntityMysticWandShoot> createRenderFor(EntityRendererManager manager) {
            return new RenderMysticWandShoot(manager);
        }

    }

}