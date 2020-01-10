package io.github.krevik.kathairis.world.dimension.feature.tree;

import com.mojang.datafixers.Dynamic;
import io.github.krevik.kathairis.init.ModBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.IWorldGenerationReader;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.TreeFeatureConfig;

import java.util.ArrayList;
import java.util.Random;
import java.util.Set;
import java.util.function.Function;

public class FeatureKatharianSwampTallTree1 extends AbstractKatharianTreeFeature {
    private static final BlockState LOG = ModBlocks.ELDERWILLOW_LOG.getDefaultState();
    private static final BlockState LEAF = ModBlocks.ELDERWILLOW_LEAVES.getDefaultState();

    public FeatureKatharianSwampTallTree1(Function<Dynamic<?>, ? extends TreeFeatureConfig> p_i49920_1_) {
        super(p_i49920_1_);
    }


    private void generateCrown(IWorldGenerationReader world, BlockPos pos, int radius, Random random, Set<BlockPos> leavesSet) {
        BlockState leaves = LEAF;
        for(int x=-radius/2;x<=radius/2;x++) {
            for(int z=-radius/2;z<=radius/2;z++) {
                for(int y=-radius/4;y<=radius;y++) {
                    //do main crown
                    BlockPos tmp = new BlockPos(pos.getX()+x,pos.getY()+y,pos.getZ()+z);
                        if (((x * x) + (z * z) + (y * y) <= (radius / 2 * radius / 2))) {
                            if (isAir(world,tmp)) {
                                world.setBlockState(tmp, leaves, 2);
                                leavesSet.add(tmp);
                            }
                        }
                        //add some hanging blocks
                        if (((x * x) + (z * z) + (y * y) == (radius / 2 * radius / 2)) && isAir(world,tmp.down())) {
                            world.setBlockState(tmp.down(), LEAF, 2);
                            int yyLength=random.nextInt(4);
                            for (int yy = 0; yy <= yyLength; yy++) {
                                if (isAir(world,tmp.down(yy))) {
                                    world.setBlockState(tmp.down(yy), LEAF, 2);
                                    leavesSet.add((tmp.down(yy)));
                                }
                            }
                            for(int yyy=1;yyy<=2;yyy++){
                                if(!isAir(world,tmp.down(yyLength))) {
                                    if (isAir(world, tmp.down(yyLength + yyy))) {
                                        if (yyy == 1) {
                                            world.setBlockState(tmp.down(yyLength + yyy), ModBlocks.WILLOW_VINE_MAIN.getDefaultState(), 2);
                                        } else {
                                            world.setBlockState(tmp.down(yyLength + yyy), ModBlocks.WILLOW_VINE_TIP.getDefaultState(), 2);
                                        }
                                    }
                                }
                            }
                        }

                }
            }
        }
    }

    private BlockPos getBranchDirection(Random random){
        int X=1-random.nextInt(3);
        int Z=1-random.nextInt(3);
        if(X==0||Z==0){
            return getBranchDirection(random);
        }
        BlockPos result=new BlockPos(X,0,Z);
        return result;
    }

    private int getStartLoopInts(int xz,Random random){
        if(xz==0){
            return random.nextInt(2)==0?-1:1;
        }else{
            return xz;
        }
    }

    private int getEndLoopInts(int xz,Random random){
        if(xz==-1){return 0;}
        else if(xz==1){return 2;}
        else{return 1;}
    }

    @Override
    protected boolean func_225557_a_(IWorldGenerationReader worldIn, Random rand, BlockPos position, Set leavesSet, Set trunksSet, MutableBoundingBox mutableBoundingBox, BaseTreeFeatureConfig config) {
        if(canGrowInto(worldIn,position)||canGrowInto(worldIn,position.down())){
            int mainTrunkHeight=7+rand.nextInt(10);
            int thickTrunkHeight=3+rand.nextInt(3);
            int higherTrunkShiftX=1-rand.nextInt(3);
            int higherTrunkShiftZ=1-rand.nextInt(3);
            int posX=position.getX();
            int posY=position.getY();
            int posZ=position.getZ();
            ArrayList<BlockPos> availablePosesForRoots = new ArrayList<>();
            BlockPos tmp;
            //let's do thick base
            for(int x=-1;x<=1;x++){
                for(int z=-1;z<=1;z++){
                    for(int y=0;y<=thickTrunkHeight;y++){
                        tmp=new BlockPos(posX+x,posY+y,posZ+z);
                        if(y>1){
                            availablePosesForRoots.add(tmp);
                        }
                        if (y == thickTrunkHeight) {
                            if (rand.nextInt(2) == 0) {
                                worldIn.setBlockState(tmp,LOG,2);
                                trunksSet.add(tmp);
                            }
                        } else {
                            worldIn.setBlockState(tmp,LOG,2);
                            trunksSet.add(tmp);
                        }
                    }
                }
            }
            //rest of the trunk with some shift
            ArrayList<BlockPos> availableTrunkStartPoses = new ArrayList<>();
            int xLoopStart = -getStartLoopInts(higherTrunkShiftX,rand);
            int zLoopStart = -getStartLoopInts(higherTrunkShiftZ,rand);
            int xLoopEnd = getEndLoopInts(xLoopStart,rand);
            int zLoopEnd = getEndLoopInts(zLoopStart,rand);
            for(int x=xLoopStart;x<=xLoopEnd;x++){
                for(int z=zLoopStart;z<=zLoopEnd;z++){
                    for(int y=thickTrunkHeight;y<=mainTrunkHeight;y++){
                        tmp = new BlockPos(posX+x+higherTrunkShiftX,posY+y,posZ+z+higherTrunkShiftZ);
                        worldIn.setBlockState(tmp,LOG,2);
                        trunksSet.add(tmp);
                        availableTrunkStartPoses.add(tmp);
                        if(y==mainTrunkHeight){
                            //doMainCrown
                            generateCrown(worldIn,tmp.down(),4+rand.nextInt(6),rand,leavesSet);
                        }
                    }
                }
            }
            //well, let's make the fucking constant roots......
            BlockPos rootPos1 = new BlockPos(posX+1,posY+2+rand.nextInt(3),posZ+1);
            BlockPos rootPos2 = new BlockPos(posX-1,posY+2+rand.nextInt(3),posZ-1);
            BlockPos rootPos3 = new BlockPos(posX-1,posY+2+rand.nextInt(3),posZ+1);
            BlockPos rootPos4 = new BlockPos(posX+1,posY+2+rand.nextInt(3),posZ-1);
            for(int rootsNumber=0;rootsNumber<4;rootsNumber++) {
                int length=2+rand.nextInt(4);
                if(rootsNumber==0) {
                    for(int c=1;c<=length;c++){
                        int shiftX=c;
                        int shiftZ=c;
                        int shiftY=c;
                        BlockPos rootPiecePos=new BlockPos(rootPos1.getX()+shiftX,rootPos1.getY()-c,rootPos1.getZ()+shiftZ);
                        int dist=0;
                        for(int y=0;y<=9;y++){
                            dist=y;
                            if(!isAir(worldIn,rootPiecePos.down(y))){
                                break;
                            }
                        }
                        if(dist<8) {
                            for (int cc = 0; cc <= dist; cc++) {
                                worldIn.setBlockState(rootPiecePos.down(cc),LOG,2);
                                trunksSet.add(rootPiecePos.down(cc));
                            }
                        }else{
                            break;
                        }
                    }
                }
                if(rootsNumber==1) {
                    for(int c=1;c<=length;c++){
                        int shiftX=-c;
                        int shiftZ=-c;
                        int shiftY=c;
                        BlockPos rootPiecePos=new BlockPos(rootPos2.getX()+shiftX,rootPos2.getY()-c,rootPos2.getZ()+shiftZ);
                        int dist=0;
                        for(int y=0;y<=7;y++){
                            dist=y;
                            if(isAir(worldIn,rootPiecePos.down(y))){
                                break;
                            }
                        }
                        if(dist<8) {
                            for(int cc=0;cc<=dist;cc++){
                                worldIn.setBlockState(rootPiecePos.down(cc),LOG,2);
                                trunksSet.add(rootPiecePos.down(cc));
                            }
                        }else{
                            break;
                        }
                    }
                }
                if(rootsNumber==2) {
                    for(int c=1;c<=length;c++){
                        int shiftX=-c;
                        int shiftZ=c;
                        int shiftY=c;
                        BlockPos rootPiecePos=new BlockPos(rootPos3.getX()+shiftX,rootPos3.getY()-c,rootPos3.getZ()+shiftZ);
                        int dist=0;
                        for(int y=0;y<=7;y++){
                            dist=y;
                            if(!isAir(worldIn,rootPiecePos.down(y))){
                                break;
                            }
                        }
                        if(dist<8) {
                            for(int cc=0;cc<=dist;cc++){
                                worldIn.setBlockState(rootPiecePos.down(cc),LOG,2);
                                trunksSet.add(rootPiecePos.down(cc));
                            }
                        }else{
                            break;
                        }
                    }
                }
                if(rootsNumber==3) {
                    for(int c=1;c<=length;c++){
                        int shiftX=c;
                        int shiftZ=-c;
                        int shiftY=c;
                        BlockPos rootPiecePos=new BlockPos(rootPos4.getX()+shiftX,rootPos4.getY()-c,rootPos4.getZ()+shiftZ);
                        int dist=0;
                        for(int y=0;y<=7;y++){
                            dist=y;
                            if(!isAir(worldIn,rootPiecePos.down(y))){
                                break;
                            }
                        }
                        if(dist<8) {
                            for(int cc=0;cc<=dist;cc++){
                                worldIn.setBlockState(rootPiecePos.down(cc),LOG,2);
                                trunksSet.add(rootPiecePos.down(cc));
                            }
                        }else{
                            break;
                        }
                    }
                }
            }


            //branches and crowns :O
            int branchesNumber=6+rand.nextInt(7);
            for(int c=0;c<branchesNumber;c++){
                int shiftX=0;
                int shiftZ=0;
                int shiftY=0;
                int randomIndex=rand.nextInt(availableTrunkStartPoses.size());
                BlockPos branchStartPos = availableTrunkStartPoses.get(randomIndex);
                availableTrunkStartPoses.remove(randomIndex);
                BlockPos trunkXZDirection = getBranchDirection(rand);
                int branchLength = 3+rand.nextInt(4);
                for(int cc=1;cc<=branchLength;cc++){
                    shiftX=trunkXZDirection.getX()*cc;
                    shiftZ=trunkXZDirection.getZ()*cc;
                    if(rand.nextInt(2)==0){shiftY++;}
                    BlockPos actualPos=new BlockPos(branchStartPos.getX()+shiftX,branchStartPos.getY()+shiftY,branchStartPos.getZ()+shiftZ);
                    worldIn.setBlockState(actualPos,LOG,2);
                    trunksSet.add(actualPos);
                    if(cc==branchLength){
                        generateCrown(worldIn,actualPos,4+rand.nextInt(5),rand,leavesSet);
                    }
                }
            }


            return true;
        }
        return false;
    }

    @Override
    public boolean place(IWorld worldIn, ChunkGenerator generator, Random rand, BlockPos pos, IFeatureConfig config) {
        return true;
    }
}