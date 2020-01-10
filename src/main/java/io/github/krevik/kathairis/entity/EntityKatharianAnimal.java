package io.github.krevik.kathairis.entity;

import io.github.krevik.kathairis.init.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.ArrayList;

public class EntityKatharianAnimal extends AnimalEntity {

    public static ArrayList<Block> spawnableBlocks = new ArrayList<>();
    static{
        spawnableBlocks.add(Blocks.GRASS);
        spawnableBlocks.add(Blocks.DIRT);
        spawnableBlocks.add(ModBlocks.KATHAIRIS_GRASS);
        spawnableBlocks.add(ModBlocks.KATHAIRIS_DIRT);
    }

    public EntityKatharianAnimal(World world){
        super(null,world);
    }

    public EntityKatharianAnimal(EntityType<? extends EntityKatharianAnimal> type, World world) {
        super(type, world);
    }


    @Nullable
    @Override
    public AgeableEntity createChild(AgeableEntity entityAgeable) {
        return null;
    }
}
