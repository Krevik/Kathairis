package io.github.krevik.kathairis.world.dimension.feature;

import com.mojang.datafixers.Dynamic;
import io.github.krevik.kathairis.block.BlockCrystal;
import io.github.krevik.kathairis.init.ModBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.Random;
import java.util.function.Function;

public class FeatureCrystalChamber extends Feature<NoFeatureConfig> {


    public FeatureCrystalChamber(Function<Dynamic<?>, ? extends NoFeatureConfig> p_i49878_1_) {
        super(p_i49878_1_);
    }

    private boolean isStoneAround(IWorld world, BlockPos pos){
        int posX=pos.getX();
        int posY=pos.getY();
        int posZ=pos.getZ();
        boolean result=false;
        for(int x=-1;x<=1;x++){
            for(int y=-1;y<=1;y++){
                for(int z=-1;z<=1;z++){
                    BlockPos tmp = new BlockPos(posX+x,posY+y,posZ+z);
                    if(world.isBlockLoaded(tmp)){
                        if(world.getBlockState(tmp).getBlock()== ModBlocks.KATHAIRIS_STONE){
                            result=true;
                        }
                    }
                }
            }
        }
        return result;
    }

    private Direction getRandomFacing(Random random){
        Direction facing=random.nextInt(2)==0? Direction.DOWN: Direction.UP;
        int k = random.nextInt(6);
        if(k==0){
            facing= Direction.DOWN;
        }
        if(k==1){
            facing= Direction.UP;
        }
        if(k==2){
            facing= Direction.EAST;
        }
        if(k==3){
            facing= Direction.WEST;
        }
        if(k==4){
            facing= Direction.SOUTH;
        }
        if(k==5){
            facing= Direction.NORTH;
        }
        return facing;
    }

    private BlockState pickupRandomCrystal(Random random){
        int k = random.nextInt(3);
        switch(k){
            case 0:
                return ModBlocks.BLUE_CRYSTAL.getDefaultState();
            case 1:
                return ModBlocks.YELLOW_CRYSTAL.getDefaultState();
            case 2:
                return ModBlocks.VIOLET_CRYSTAL.getDefaultState();
        }
        return ModBlocks.BLUE_CRYSTAL.getDefaultState();
    }

    @Override
    public boolean place(IWorld world, ChunkGenerator<? extends GenerationSettings> generator, Random rand, BlockPos pos, NoFeatureConfig config) {
        int height = 10 + rand.nextInt(10) + rand.nextInt(10) + rand.nextInt(10) + rand.nextInt(10) + rand.nextInt(10);
        int posX = pos.getX();
        int posZ = pos.getZ();
        int diameter = 3 + rand.nextInt(6);
        boolean shouldContinue = true;

        float radiusX = 7-rand.nextInt(8);
        float radiusZ = 7-rand.nextInt(8);

        for (float x = -8; x <= 8; x++) {
            for (float z = -8; z <= 8; z++) {
                for (float y = -8; y <= 8; y++) {
                    if (shouldContinue) {
                        if ((x * x) + (y * y) + (z * z) <= (radiusX * radiusX) + (radiusZ * radiusZ) + (float)(4 * y * Math.sin(x)) - (float)(4 * x * Math.sin(y))) {
                            BlockPos tmp = new BlockPos(posX + x, height + y, posZ + z);
                            if (world.isBlockLoaded(tmp)) {
                                world.setBlockState(tmp, Blocks.CAVE_AIR.getDefaultState(), 2);
                            } else {
                                shouldContinue = false;
                                break;
                            }
                        }
                    }
                }
            }
        }

        for (float x = -8; x <= 8; x++) {
            for (float z = -8; z <= 8; z++) {
                for (float y = -8; y <= 8; y++) {
                    BlockPos tmp = new BlockPos(posX + x, height + y, posZ + z);
                    if (world.isBlockLoaded(tmp)) {
                        Direction facing = getRandomFacing(rand);
                        BlockState crystal = pickupRandomCrystal(rand);
                        BlockState finalCrystal = crystal.with(BlockCrystal.FACING,facing);
                        if(BlockCrystal.isValidPosition1(finalCrystal,world,tmp)&&isStoneAround(world,tmp)&&world.isAirBlock(tmp)){
                            world.setBlockState(tmp, finalCrystal, 2);
                        }
                    }
                }
            }
        }
        return true;

    }
}
