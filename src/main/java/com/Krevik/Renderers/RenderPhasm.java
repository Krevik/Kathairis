package com.Krevik.Renderers;

import com.Krevik.Entities.EntityBigTurtle;
import com.Krevik.Entities.EntityGaznowel;
import com.Krevik.Entities.EntityPhasm;
import com.Krevik.Main.EntityAndRenderRegistry;
import com.Krevik.Models.ModelBigTurtle;
import com.Krevik.Models.ModelGaznowel;
import com.Krevik.Models.ModelPhasm;
import com.Krevik.Renderers.Layer.RenderLayerHeldItem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderSkeleton;
import net.minecraft.client.renderer.entity.layers.LayerHeldItem;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RenderPhasm extends RenderLiving<EntityPhasm> {

    public static final Factory FACTORY = new Factory();

    public RenderPhasm(RenderManager renderManagerIn) {
        super(renderManagerIn, new ModelPhasm(), 0.0F);
    }


    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(EntityPhasm entity) {
        return EntityAndRenderRegistry.PhasmLoc;
    }

    public static class Factory implements IRenderFactory<EntityPhasm> {

        @Override
        public Render<? super EntityPhasm> createRenderFor(RenderManager manager) {
            return new RenderPhasm(manager);
        }

    }

    protected void applyRotations(EntityPhasm entityLiving, float p_77043_2_, float rotationYaw, float partialTicks) {
        GL11.glEnable(GL11.GL_NORMALIZE);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        super.applyRotations(entityLiving, p_77043_2_, rotationYaw, partialTicks);

    }




}