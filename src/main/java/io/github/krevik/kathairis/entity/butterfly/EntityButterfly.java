package io.github.krevik.kathairis.entity.butterfly;

import io.github.krevik.kathairis.init.ModEntities;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EntityButterfly extends EntityBasicButterfly
{
    public BlockPos butterflyFlowerPos=null;

    public EntityButterfly(World worldIn)
    {
        super(worldIn, ModEntities.COMMON_BUTTERFLY1);
        this.setIsBirdSitting(true);
        this.experienceValue=1;
    }

    public EntityButterfly(EntityType<EntityButterfly> entityButterflyEntityType, World world) {
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