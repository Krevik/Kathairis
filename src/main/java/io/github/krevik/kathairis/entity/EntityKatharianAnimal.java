package io.github.krevik.kathairis.entity;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.ArrayList;

public class EntityKatharianAnimal extends EntityAnimal {
    public ArrayList<Block> spawnableBlocks;

    public EntityKatharianAnimal(EntityType<?> type, World world){
        super(type,world);
        spawnableBlocks= new ArrayList<>();

    }

    @Nullable
    @Override
    public EntityAgeable createChild(EntityAgeable entityAgeable) {
        return null;
    }
}
