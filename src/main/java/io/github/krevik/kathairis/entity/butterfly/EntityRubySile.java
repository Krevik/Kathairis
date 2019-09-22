package io.github.krevik.kathairis.entity.butterfly;

import io.github.krevik.kathairis.init.ModEntities;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

public class EntityRubySile extends EntityBasicButterfly
{
    public EntityRubySile(World worldIn)
    {
        super(worldIn, ModEntities.RUBY_SILE);
        this.setIsBirdSitting(true);
        this.experienceValue=1;
    }

    public EntityRubySile(EntityType<EntityRubySile> type, World world) {
        super(type, world);
    }

}