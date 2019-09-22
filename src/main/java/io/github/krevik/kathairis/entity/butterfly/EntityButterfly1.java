package io.github.krevik.kathairis.entity.butterfly;

import io.github.krevik.kathairis.init.ModEntities;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EntityButterfly1 extends EntityBasicButterfly
{

    private BlockPos spawnPosition;

    public EntityButterfly1(World worldIn)
    {
        super(worldIn, ModEntities.COMMON_BUTTERFLY2);
        this.setIsBirdSitting(true);
        this.experienceValue=1;
    }

    public EntityButterfly1(EntityType<EntityButterfly1> entityType, World world) {
        super(world);
    }


    @Override
    public int getMaxSpawnedInChunk()
    {
        return 2;
    }

    @Override
    protected void registerAttributes()
    {
        super.registerAttributes();
        this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10.0D);
    }

}