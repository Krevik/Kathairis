package io.github.krevik.kathairis.entity;

import io.github.krevik.kathairis.init.ModEntities;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityMysticWandShoot extends EntityFireball {
    public EntityMysticWandShoot(EntityType<?> type, World world, float sizeX, float sizeY) {
        super(type, world, sizeX, sizeY);
    }

    public EntityMysticWandShoot(World worldIn) {
        super(ModEntities.MYSTIC_WAND_SHOOT, worldIn, 0.3125F, 0.3125F);
    }


    @Override
    protected void onImpact(RayTraceResult result) {

    }
}
