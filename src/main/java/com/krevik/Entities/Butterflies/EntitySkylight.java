package com.krevik.Entities.Butterflies;

import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.world.World;

public class EntitySkylight extends EntityMothBase
{

    public EntitySkylight(World worldIn)
    {
        super(worldIn);
        this.setSize(0.15F, 0.15F);
        this.experienceValue=1;
    }
    public int getMaxSpawnedInChunk()
    {
        return 3;
    }
    protected void entityInit()
    {
        super.entityInit();
    }

    protected void collideWithEntity(Entity entityIn)
    {
    }

    protected void collideWithNearbyEntities()
    {
    }

    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10.0D);
    }

    /**
     * Called to update the entity's position/logic.
     */
    public void onUpdate()
    {
        super.onUpdate();
    }
    


    protected void updateAITasks()
    {
        super.updateAITasks();
    }
}