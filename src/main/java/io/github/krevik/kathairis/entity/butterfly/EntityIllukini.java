package io.github.krevik.kathairis.entity.butterfly;

import io.github.krevik.kathairis.init.ModEntities;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

public class EntityIllukini extends EntityBasicButterfly
{

    public EntityIllukini(World worldIn)
    {
        super(worldIn, ModEntities.ILLUKINI);
        this.setIsBirdSitting(true);
        this.experienceValue=1;
    }

    public EntityIllukini(EntityType<EntityIllukini> type, World world) {
        super(type, world);
    }

}