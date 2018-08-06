package com.Krevik.Renderers;

import com.Krevik.Entities.EntityLivingFlower;
import com.Krevik.Main.EntityAndRenderRegistry;
import com.Krevik.Models.ModelLivingFlower;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderLivingFlower extends RenderLiving<EntityLivingFlower>
{
    public static final Factory FACTORY = new Factory();

    public RenderLivingFlower(RenderManager renderManagerIn)
    {
        super(renderManagerIn, new ModelLivingFlower(), 0F);
    }

    
    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(EntityLivingFlower entity)
    {
        return EntityAndRenderRegistry.LivingFlowerLoc;
    }
    
    public static class Factory implements IRenderFactory<EntityLivingFlower> {

        @Override
        public Render<? super EntityLivingFlower> createRenderFor(RenderManager manager) {
            return new RenderLivingFlower(manager);
        }

    }
    
    protected void applyRotations(EntityLivingFlower entityLiving, float p_77043_2_, float rotationYaw, float partialTicks)
    {
        super.applyRotations(entityLiving, p_77043_2_, rotationYaw, partialTicks);
        
    }
    
}

