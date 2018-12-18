package mod.krevik.client.renderer;

import mod.krevik.entity.EntityDeath;
import mod.krevik.util.EntityAndRenderRegistry;
import mod.krevik.client.model.ModelDeath;

import mod.krevik.client.renderer.layer.RenderLayerDeathScythe;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderDeath extends RenderLiving<EntityDeath>
{
	
    public static final Factory FACTORY = new Factory();

    public RenderDeath(RenderManager renderManagerIn)
    {
        super(renderManagerIn, new ModelDeath(), 0.8F);
        this.addLayer(new RenderLayerDeathScythe(this));

    }

    
    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(EntityDeath entity)
    {
        return EntityAndRenderRegistry.DeathLoc;
    }
    
    public static class Factory implements IRenderFactory<EntityDeath> {

        @Override
        public Render<? super EntityDeath> createRenderFor(RenderManager manager) {
            return new RenderDeath(manager);
        }

    }
    
    protected void applyRotations(EntityDeath entityLiving, float p_77043_2_, float rotationYaw, float partialTicks)
    {
        super.applyRotations(entityLiving, p_77043_2_, rotationYaw, partialTicks);
        
    }
    
}
