package mod.krevik.Renderers;

import mod.krevik.Entities.EntitySkeletonWarrior;
import mod.krevik.Main.EntityAndRenderRegistry;
import mod.krevik.Models.ModelSkeletonWarrior;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerHeldItem;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderSkeletonWarrior extends RenderBiped<EntitySkeletonWarrior>
{
    public static final Factory FACTORY = new Factory();

    public RenderSkeletonWarrior(RenderManager renderManagerIn)
    {
        super(renderManagerIn, new ModelSkeletonWarrior(), 0.25F);
        this.addLayer(new LayerHeldItem(this));

    }
    public void transformHeldFull3DItemLayer()
    {
    	GlStateManager.translate(0.0F, 0.1875F, 0.0F);
    }
    

    
    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(EntitySkeletonWarrior entity)
    {
        return EntityAndRenderRegistry.SkeletonWarriorLoc;
    }
    
    public static class Factory implements IRenderFactory<EntitySkeletonWarrior> {

        @Override
        public Render<? super EntitySkeletonWarrior> createRenderFor(RenderManager manager) {

            return new RenderSkeletonWarrior(manager);

        }

    }

    protected void applyRotations(EntitySkeletonWarrior entityLiving, float p_77043_2_, float rotationYaw, float partialTicks)
    {
        super.applyRotations(entityLiving, p_77043_2_, rotationYaw, partialTicks);
    }
    
}
