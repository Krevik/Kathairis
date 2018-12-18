package com.krevik.Renderers;

import javax.annotation.Nullable;

import com.krevik.Entities.EntityMysticBird;
import com.krevik.Main.EntityAndRenderRegistry;
import com.krevik.Models.ModelMysticBird;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import scala.util.Random;

@SideOnly(Side.CLIENT)
public class RenderMysticBird extends RenderLiving<EntityMysticBird>
{
    public static final Factory FACTORY = new Factory();
    Random random = new Random();
    public RenderMysticBird(RenderManager renderManagerIn)
    {
        super(renderManagerIn, new ModelMysticBird(), 0F);
    }

    
    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    @Nullable
    protected ResourceLocation getEntityTexture(EntityMysticBird entity)
    {
    	int variant=entity.getVariant();
    	if(variant==0) return EntityAndRenderRegistry.MysticBirdLoc;
    	else if(variant==1) return EntityAndRenderRegistry.MysticBird1Loc;
    	else if(variant==2) return EntityAndRenderRegistry.MysticBird2Loc;
    	else if(variant==3) return EntityAndRenderRegistry.MysticBird3Loc;
    	else return EntityAndRenderRegistry.MysticBirdLoc;
    }
    
    public static class Factory implements IRenderFactory<EntityMysticBird> {

        @Override
        public Render<? super EntityMysticBird> createRenderFor(RenderManager manager) {
            return new RenderMysticBird(manager);
        }

    }
    
    protected void applyRotations(EntityMysticBird entityLiving, float p_77043_2_, float rotationYaw, float partialTicks)
    {
    	GlStateManager.scale(0.4, 0.4, 0.4);
        super.applyRotations(entityLiving, p_77043_2_, rotationYaw, partialTicks);
        
    }
    
}
