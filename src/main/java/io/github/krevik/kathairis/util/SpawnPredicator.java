package io.github.krevik.kathairis.util;

import io.github.krevik.kathairis.init.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Difficulty;
import net.minecraft.world.IWorld;

import java.util.ArrayList;
import java.util.Random;

public class SpawnPredicator {

    public static ArrayList<Block> spawnBlocksStandardAnimal = new ArrayList<>();
    public static ArrayList<Block> spawnBlocksStandardMonster = new ArrayList<>();
    public static ArrayList<Block> spawnBlocksCloud = new ArrayList<>();

    public static boolean canSpawnAnimal(EntityType<?> p_223316_0_, IWorld p_223316_1_, SpawnReason p_223316_2_, BlockPos p_223316_3_, Random p_223316_4_) {
        spawnBlocksStandardAnimal.clear();
        spawnBlocksStandardAnimal.add(ModBlocks.KATHAIRIS_GRASS);
        spawnBlocksStandardAnimal.add(ModBlocks.KATHAIRIS_DIRT);
        spawnBlocksStandardAnimal.add(ModBlocks.KATHAIRIS_SAND);
        return  spawnBlocksStandardAnimal.contains(p_223316_1_.getBlockState(p_223316_3_.down()).getBlock()) && p_223316_1_.getLightSubtracted(p_223316_3_, 0) > 8;
    }

    public static boolean canSpawnNimbus(EntityType<?> p_223316_0_, IWorld p_223316_1_, SpawnReason p_223316_2_, BlockPos p_223316_3_, Random p_223316_4_) {
        spawnBlocksCloud.clear();
        spawnBlocksCloud.add(ModBlocks.BLUE_CLOUD);
        spawnBlocksCloud.add(ModBlocks.YELLOW_CLOUD);
        return  spawnBlocksCloud.contains(p_223316_1_.getBlockState(p_223316_3_.down()).getBlock()) && p_223316_1_.getLightSubtracted(p_223316_3_, 0) > 8;
    }

    public static boolean canSpawnMonster(EntityType<?> p_223316_0_, IWorld p_223316_1_, SpawnReason p_223316_2_, BlockPos p_223316_3_, Random p_223316_4_) {
        spawnBlocksStandardMonster.clear();
        spawnBlocksStandardMonster.add(ModBlocks.KATHAIRIS_GRASS);
        spawnBlocksStandardMonster.add(ModBlocks.KATHAIRIS_DIRT);
        spawnBlocksStandardMonster.add(ModBlocks.KATHAIRIS_SAND);
        spawnBlocksStandardMonster.add(ModBlocks.KATHAIRIS_STONE);
        return spawnBlocksStandardMonster.contains(p_223316_1_.getBlockState(p_223316_3_.down()).getBlock()) && p_223316_1_.getDifficulty() != Difficulty.PEACEFUL;
    }
}
