package io.github.krevik.kathairis.world.dimension.feature;

import com.mojang.datafixers.Dynamic;
import io.github.krevik.kathairis.init.ModBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.ArrayList;
import java.util.Map;
import java.util.Random;
import java.util.function.Function;

public class FeatureMagnethiumHalfSphere extends Feature<NoFeatureConfig> {

    public FeatureMagnethiumHalfSphere(Function<Dynamic<?>, ? extends NoFeatureConfig> p_i49878_1_) {
        super(p_i49878_1_);
    }

    @Override
    public boolean place(IWorld worldIn, ChunkGenerator<? extends GenerationSettings> generator, Random rand, BlockPos pos, NoFeatureConfig config) {
        int randomYShift=50+rand.nextInt(20)+rand.nextInt(20)+rand.nextInt(20);

        int radius=4+rand.nextInt(5);
        int posX=pos.getX();
        int posZ=pos.getZ();

        ArrayList<BlockPosWithBlockStateHolder> blocksToMoveCollection = new ArrayList<>();
        //get Sphere shaped blocks on the ground and above
        for(int x=-radius;x<=radius;x++){
            for(int y=-radius;y<=radius/2;y++){
                for(int z=-radius;z<=radius;z++){
                    if(x*x+y*y+z*z<=radius*radius+1) {
                        int groundHeight = worldIn.getHeight(Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, posX + x, posZ + z);
                        BlockPos toMove = new BlockPos(posX + x, groundHeight + y, posZ + z);
                        if (!worldIn.isAirBlock(toMove)) {
                            blocksToMoveCollection.add(new BlockPosWithBlockStateHolder(toMove, worldIn.getBlockState(toMove), new BlockPos(x, y, z)));
                        }
                    }
                }
            }
        }

        //get Circle shaped blocks on the ground and above
        for(int x=-radius;x<=radius;x++){
            for(int y=(radius/2)+1;y<=(radius/2)+15;y++){
                for(int z=-radius;z<=radius;z++){
                    if(x*x+z*z<=radius*radius+1) {
                        int groundHeight = worldIn.getHeight(Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, posX + x, posZ + z);
                        BlockPos toMove = new BlockPos(posX + x, groundHeight + y, posZ + z);
                        if (!worldIn.isAirBlock(toMove)) {
                            blocksToMoveCollection.add(new BlockPosWithBlockStateHolder(toMove, worldIn.getBlockState(toMove), new BlockPos(x, y, z)));
                        }
                    }
                }
            }
        }

        for(BlockPosWithBlockStateHolder blockPosWithBlockStateHolder: blocksToMoveCollection){
            if(!worldIn.isAirBlock(blockPosWithBlockStateHolder.pos)) {
                worldIn.removeBlock(blockPosWithBlockStateHolder.pos, false);
                BlockPos tmp = blockPosWithBlockStateHolder.pos;
                int posXX=tmp.getX();
                int posYY=tmp.getY();
                int posZZ=tmp.getZ();
                worldIn.setBlockState(new BlockPos(posXX,posYY+randomYShift,posZZ),blockPosWithBlockStateHolder.state,2);
                if(isNearSphereBoundlineUnderHalfY(radius,blockPosWithBlockStateHolder.sphereShift)){
                    worldIn.setBlockState(new BlockPos(posXX,posYY+randomYShift,posZZ), ModBlocks.MAGNETHIUM.getDefaultState(),2);
                }
            }
        }

        return true;
    }

    private boolean isNearSphereBoundlineUnderHalfY(int radius, BlockPos sphereRadius){
        boolean result=false;
        int actualXRadius=sphereRadius.getX();
        int actualYRadius=sphereRadius.getY();
        int actualZRadius=sphereRadius.getZ();

        if(actualYRadius<=radius/2){
            if(actualXRadius*actualXRadius+actualYRadius*actualYRadius+actualZRadius*actualZRadius<=radius*radius+1&&
                    actualXRadius*actualXRadius+actualYRadius*actualYRadius+actualZRadius*actualZRadius>=radius*radius-1){
                result=true;
            }
        }
        return result;
    }

    static class BlockPosWithBlockStateHolder{
        private BlockPos pos;
        private BlockState state;
        private BlockPos sphereShift;

        public BlockPosWithBlockStateHolder(BlockPos position, BlockState blockState, BlockPos shift){
            pos=position;
            state=blockState;
            sphereShift=shift;
        }

        public BlockPos getPos() {
            return pos;
        }

        public BlockState getState() {
            return state;
        }

        public BlockPos getSphereShift(){
            return sphereShift;
        }
    }

}
