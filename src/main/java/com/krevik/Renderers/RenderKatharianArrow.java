package com.krevik.Renderers;

import com.krevik.Entities.EntityKatharianArrow;
import com.krevik.Main.KCore;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderArrow;
import net.minecraft.client.renderer.entity.RenderManager;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderKatharianArrow extends RenderArrow<EntityKatharianArrow> {
    public static final RenderKatharianArrow.Factory FACTORY = new RenderKatharianArrow.Factory();

    public static final ResourceLocation RES_ARROW = new ResourceLocation(KCore.MODID, "textures/entity/projectiles/katharian_arrow.png");
    public static final ResourceLocation RES_TIPPED_ARROW = new ResourceLocation(KCore.MODID, "textures/entity/projectiles/katharian_arrow.png");

    public RenderKatharianArrow(RenderManager manager) {
        super(manager);
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(EntityKatharianArrow entity) {
        return entity.getColor() > 0 ? RES_TIPPED_ARROW : RES_ARROW;
    }

    public static class Factory implements IRenderFactory<EntityKatharianArrow> {

        @Override
        public Render<? super EntityKatharianArrow> createRenderFor(RenderManager manager) {
            return new RenderKatharianArrow(manager);
        }

    }
}
