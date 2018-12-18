package mod.krevik.Renderers;

import mod.krevik.Entities.EntityStrangeWanderer;
import mod.krevik.Main.EntityAndRenderRegistry;
import mod.krevik.Models.ModelStrangeWanderer;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Calendar;

@SideOnly(Side.CLIENT)
public class RenderStrangeWanderer extends RenderLiving<EntityStrangeWanderer>
{
    public static final Factory FACTORY = new Factory();

    public RenderStrangeWanderer(RenderManager renderManagerIn)
    {
        super(renderManagerIn, new ModelStrangeWanderer(), 0.5F);

    }

    
    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(EntityStrangeWanderer entity)
    {
        int month = Calendar.getInstance().get(Calendar.MONTH);
        if(month==11){
            return EntityAndRenderRegistry.Strange_Wanderer_Christmas_Loc;
        }else {
            return EntityAndRenderRegistry.StrangeWandererLoc;
        }
    }
    
    public static class Factory implements IRenderFactory<EntityStrangeWanderer> {

        @Override
        public Render<? super EntityStrangeWanderer> createRenderFor(RenderManager manager) {

            return new RenderStrangeWanderer(manager);

        }

    }

    protected void applyRotations(EntityStrangeWanderer entityLiving, float p_77043_2_, float rotationYaw, float partialTicks)
    {
        super.applyRotations(entityLiving, p_77043_2_, rotationYaw, partialTicks);
    }
    
}
