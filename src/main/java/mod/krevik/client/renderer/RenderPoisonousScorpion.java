package mod.krevik.client.renderer;

import mod.krevik.client.model.ModelPoisonousScorpion;
import mod.krevik.entity.EntityPoisonousScorpion;
import mod.krevik.util.EntityAndRenderRegistry;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderPoisonousScorpion extends RenderLiving<EntityPoisonousScorpion>
{
	
    public static final Factory FACTORY = new Factory();

    public RenderPoisonousScorpion(RenderManager renderManagerIn)
    {
        super(renderManagerIn, new ModelPoisonousScorpion(), 0.4F);
    }

    
    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(EntityPoisonousScorpion entity)
    {
        return EntityAndRenderRegistry.PoisonousScorpionLoc;
    }
    
    public static class Factory implements IRenderFactory<EntityPoisonousScorpion> {

        @Override
        public Render<? super EntityPoisonousScorpion> createRenderFor(RenderManager manager) {
            return new RenderPoisonousScorpion(manager);
        }

    }
    
    protected void applyRotations(EntityPoisonousScorpion entityLiving, float p_77043_2_, float rotationYaw, float partialTicks)
    {
    	GlStateManager.scale(0.5, 0.5, 0.5);
        super.applyRotations(entityLiving, p_77043_2_, rotationYaw, partialTicks);
        
    }
    
}
