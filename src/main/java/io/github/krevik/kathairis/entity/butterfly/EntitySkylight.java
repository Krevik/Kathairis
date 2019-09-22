package io.github.krevik.kathairis.entity.butterfly;

import io.github.krevik.kathairis.init.ModEntities;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.world.World;

public class EntitySkylight extends EntityMothBase
{

    public EntitySkylight(World worldIn)
    {
        super(worldIn, ModEntities.SKYLIGHT);
        this.experienceValue=1;
    }

    public EntitySkylight(EntityType<EntitySkylight> type, World world) {
        super(type, world);
    }

    @Override
    protected void registerAttributes()
    {
        super.registerAttributes();
        this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10.0D);
    }
}