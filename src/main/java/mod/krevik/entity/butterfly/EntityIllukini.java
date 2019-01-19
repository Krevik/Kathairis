package mod.krevik.entity.butterfly;

import net.minecraft.world.World;

public class EntityIllukini extends EntityBasicButterfly
{

    public EntityIllukini(World worldIn)
    {
        super(worldIn);
        this.setSize(0.6F, 0.5F);
        this.setIsBirdSitting(true);
        this.experienceValue=1;
    }
}