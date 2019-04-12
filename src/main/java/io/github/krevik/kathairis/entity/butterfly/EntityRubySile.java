package io.github.krevik.kathairis.entity.butterfly;

import io.github.krevik.kathairis.init.ModEntities;
import net.minecraft.world.World;

public class EntityRubySile extends EntityBasicButterfly
{
    public EntityRubySile(World worldIn)
    {
        super(worldIn, ModEntities.RUBY_SILE);
        this.setSize(0.6F, 0.5F);
        this.setIsBirdSitting(true);
        this.experienceValue=1;
    }
}