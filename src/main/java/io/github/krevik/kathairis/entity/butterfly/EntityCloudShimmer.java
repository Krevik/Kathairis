package io.github.krevik.kathairis.entity.butterfly;

import io.github.krevik.kathairis.init.ModEntities;
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
}