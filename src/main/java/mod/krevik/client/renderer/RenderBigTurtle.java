package mod.krevik.client.renderer;

import mod.krevik.client.model.ModelBigTurtle;
import mod.krevik.entity.EntityBigTurtle;
import mod.krevik.util.EntityAndRenderRegistry;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderBigTurtle extends RenderLiving<EntityBigTurtle>
{
	
    public static final Factory FACTORY = new Factory();

    public RenderBigTurtle(RenderManager renderManagerIn)
    {
        super(renderManagerIn, new ModelBigTurtle(), 0.8F);
    }

    
    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(EntityBigTurtle entity)
    {
        return EntityAndRenderRegistry.BigTurtleLoc;
    }
    
    public static class Factory implements IRenderFactory<EntityBigTurtle> {

        @Override
        public Render<? super EntityBigTurtle> createRenderFor(RenderManager manager) {
            return new RenderBigTurtle(manager);
        }

    }
    
    protected void applyRotations(EntityBigTurtle entityLiving, float p_77043_2_, float rotationYaw, float partialTicks)
    {
        super.applyRotations(entityLiving, p_77043_2_, rotationYaw, partialTicks);
        
    }
    
}
