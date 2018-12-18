package com.krevik.Renderers;

import com.krevik.Entities.EntityGaznowel;
import com.krevik.Main.EntityAndRenderRegistry;
import com.krevik.Models.ModelGaznowel;
import com.krevik.Renderers.Layer.RenderLayerHeldItem;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RenderGaznowel extends RenderLiving<EntityGaznowel> {

    public static final Factory FACTORY = new Factory();

    public RenderGaznowel(RenderManager renderManagerIn) {
        super(renderManagerIn, new ModelGaznowel(), 0.0F);
        this.addLayer(new RenderLayerHeldItem(this));
    }


    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(EntityGaznowel entity) {
        return EntityAndRenderRegistry.GaznowelLoc;
    }

    public static class Factory implements IRenderFactory<EntityGaznowel> {

        @Override
        public Render<? super EntityGaznowel> createRenderFor(RenderManager manager) {
            return new RenderGaznowel(manager);
        }

    }

    protected void applyRotations(EntityGaznowel entityLiving, float p_77043_2_, float rotationYaw, float partialTicks) {
        GL11.glEnable(GL11.GL_NORMALIZE);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        super.applyRotations(entityLiving, p_77043_2_, rotationYaw, partialTicks);

    }




}