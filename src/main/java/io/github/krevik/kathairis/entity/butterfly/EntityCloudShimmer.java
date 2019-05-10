package io.github.krevik.kathairis.entity.butterfly;

import io.github.krevik.kathairis.block.BlockKathairisCloud;
import io.github.krevik.kathairis.init.ModEntities;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

public class EntityCloudShimmer extends EntityBasicButterfly
{
    public EntityCloudShimmer(World worldIn)
    {
        super(worldIn, ModEntities.CLOUD_SHIMMER);
        this.setSize(0.6F, 0.5F);
        this.setIsBirdSitting(true);
        this.experienceValue=1;
    }

    @Override
    public boolean canSpawn(IWorld p_205020_1_, boolean p_205020_2_) {
        return super.canSpawn(p_205020_1_, p_205020_2_) && p_205020_1_.getBlockState(getPosition().down()).getBlock() instanceof BlockKathairisCloud;
    }
}