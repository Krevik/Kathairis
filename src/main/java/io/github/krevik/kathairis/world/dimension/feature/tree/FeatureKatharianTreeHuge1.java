package io.github.krevik.kathairis.world.dimension.feature.tree;

import com.mojang.datafixers.Dynamic;
import io.github.krevik.kathairis.block.BlockGlowVines;
import io.github.krevik.kathairis.init.ModBlocks;
import io.github.krevik.kathairis.world.dimension.feature.config.BaseKatharianTreeFeatureConfig;
import io.github.krevik.kathairis.world.dimension.feature.config.KatharianTreeFeatureConfig;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.IWorldGenerationBaseReader;
import net.minecraft.world.gen.IWorldGenerationReader;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.Random;
import java.util.Set;
import java.util.function.Function;

public class FeatureKatharianTreeHuge1 extends AbstractKatharianTreeFeature {
    private static final BlockState LOG = ModBlocks.MYSTIC_LOG.getDefaultState();
    private static final BlockState LEAF = ModBlocks.MYSTIC_LEAVES.getDefaultState();

    public FeatureKatharianTreeHuge1(Function<Dynamic<?>, ? extends KatharianTreeFeatureConfig> p_i49920_1_) {
        super(p_i49920_1_);
    }

    private void tryToPutVinesRandomly(IWorldGenerationReader world, BlockPos pos, Random random){
        for(int x=-2;x<=2;x++){
            for(int y=-1;y<=1;y++){
                for(int z=-2;z<=2;z++){
                    if(random.nextInt(6)==0){
                        BlockPos tmp = new BlockPos(pos.getX()+x,pos.getY()+y,pos.getZ()+z);
                        if(isAir(world,tmp)&& canPlaceVinesAt(world,tmp)){
                            world.setBlockState(tmp,ModBlocks.GLOWVINES.getDefaultState(),3);
                        }
                    }
                }
            }
        }
    }

    public boolean canPlaceVinesAt(IWorldGenerationReader worldIn, BlockPos pos) {
        boolean can = false;
        if (!isAir(worldIn,pos.east()) && !isAlreadyVine(worldIn,pos.east())) {
            can = true;
        }
        if (!isAir(worldIn,pos.west()) && !isAlreadyVine(worldIn,pos.west())) {
            can = true;
        }
        if (!isAir(worldIn,pos.south()) && !isAlreadyVine(worldIn,pos.south())) {
            can = true;
        }
        if (!isAir(worldIn,pos.north()) && !isAlreadyVine(worldIn,pos.north())) {
            can = true;
        }
        if (!isAir(worldIn,pos.up())) {
            if (isAlreadyVine(worldIn,pos.up())) {
                boolean isBottomVariant = worldIn.hasBlockState(pos.up(), (p_214583_0_) -> {
                    if(p_214583_0_.has(BlockGlowVines.VARIANT)){
                        if(p_214583_0_.get(BlockGlowVines.VARIANT)==BlockGlowVines.EnumType.BOTTOM){
                            return true;
                        }else{
                            return false;
                        }
                    }else{
                        return false;
                    }
                });
                can = !isBottomVariant;
            }
        }
        return can;
    }

    protected static boolean isAlreadyVine(IWorldGenerationBaseReader worldIn, BlockPos pos) {
        return worldIn.hasBlockState(pos, (p_214583_0_) -> {
            return p_214583_0_.getBlock() == ModBlocks.GLOWVINES;
        });
    }

    private void makeRoot(IWorldGenerationReader world, BlockPos pos, Random rand, boolean canIncreaseX, boolean canIncreaseZ){
        int rootLength=2+rand.nextInt(5);
        int shiftX=0; int shiftZ=0; int currentY=pos.up(rand.nextInt(4)).getY();
        for(int c=0;c<rootLength;c++){
            if(canIncreaseX && !canIncreaseZ){
                if(rand.nextInt(3)==0){shiftX++;}
                if(rand.nextInt(3)==0){shiftZ--;}
            }
            if(canIncreaseX && canIncreaseZ){
                if(rand.nextInt(3)==0){shiftX++;}
                if(rand.nextInt(3)==0){shiftZ++;}
            }
            if(!canIncreaseX && canIncreaseZ){
                if(rand.nextInt(3)==0){shiftX--;}
                if(rand.nextInt(3)==0){shiftZ++;}
            }
            if(!canIncreaseX && !canIncreaseZ){
                if(rand.nextInt(3)==0){shiftX--;}
                if(rand.nextInt(3)==0){shiftZ--;}
            }
            BlockPos tmp = new BlockPos(pos.getX()+shiftX,pos.getY(),pos.getZ()+shiftZ);
            BlockPos ground = world.getHeight(Heightmap.Type.MOTION_BLOCKING,tmp);
            for(int cc=ground.getY();cc<=currentY;cc++){
                if(MathHelper.abs(currentY-ground.getY())<5) {
                    BlockPos tmp2 = new BlockPos(ground.getX(), cc, ground.getZ());
                    world.setBlockState(tmp2, LOG, 2);
                    if (rand.nextInt(3) == 0) {
                        currentY--;
                    }
                    if (currentY < ground.getY()) {
                        break;
                    }
                }else{
                    break;
                }
            }
        }
    }


    private void doTopBranch(IWorldGenerationReader world, BlockPos pos, Random rand, boolean canIncreaseX, boolean canIncreaseZ, Set<BlockPos> trunksSet, Set<BlockPos> leavesSet){
        int branchLength=4+rand.nextInt(4);
        int shiftX=0; int shiftY=0; int shiftZ=0;
        for(int counter=0;counter<=branchLength;counter++){
            if(canIncreaseX && !canIncreaseZ){
                if(rand.nextInt(3)==0){shiftX++;}
                if(rand.nextInt(3)==0){shiftZ--;}
            }
            if(canIncreaseX && canIncreaseZ){
                if(rand.nextInt(3)==0){shiftX++;}
                if(rand.nextInt(3)==0){shiftZ++;}
            }
            if(!canIncreaseX && canIncreaseZ){
                if(rand.nextInt(3)==0){shiftX--;}
                if(rand.nextInt(3)==0){shiftZ++;}
            }
            if(!canIncreaseX && !canIncreaseZ){
                if(rand.nextInt(3)==0){shiftX--;}
                if(rand.nextInt(3)==0){shiftZ--;}
            }
            if(rand.nextInt(2)==0){shiftY++;}
            BlockPos tmp = new BlockPos(pos.getX()+shiftX,pos.getY()+shiftY,pos.getZ()+shiftZ);
            world.setBlockState(tmp,LOG,2);
            trunksSet.add(tmp);
            if(counter==branchLength){
                generateTopCrown(world,tmp, (int) (branchLength),rand, leavesSet);
            }
        }
    }

    private void generateTopCrown(IWorldGenerationReader world, BlockPos pos, int radius, Random random, Set<BlockPos> leavesSet) {
        BlockState leaves = LEAF;
        for(int x=-radius/2;x<=radius/2;x++) {
            for(int z=-radius/2;z<=radius/2;z++) {
                for(int y=-radius/4;y<=radius;y++) {
                    //do main crown
                    BlockPos tmp = new BlockPos(pos.getX()+x,pos.getY()+y,pos.getZ()+z);
                    if(((x*x)+(z*z)+(y*2*y*2)<=(radius/2*radius/2))){
                        if(isAir(world,tmp)){
                            world.setBlockState(tmp,leaves,2);
                            leavesSet.add(tmp);
                        }
                    }
                    //add some hanging blocks
                    if(((x*x)+(z*z)+(y*2*y*2)==(radius/2*radius/2)) && isAir(world,tmp.down())){
                        world.setBlockState(tmp.down(),LEAF,2);
                        leavesSet.add(tmp.down());
                        if(random.nextInt(3)==0 && isAir(world,tmp.down(2))){
                            world.setBlockState(tmp.down(2),LEAF,2);
                            leavesSet.add(tmp.down(2));
                            if(random.nextInt(3)==0 && isAir(world,tmp.down(3))){
                                world.setBlockState(tmp.down(3),LEAF,2);
                                leavesSet.add(tmp.down(3));
                            }
                        }
                    }
                }
            }
        }
    }

    private void doBranch(IWorldGenerationReader world, BlockPos pos, Random rand, boolean canIncreaseX, boolean canIncreaseZ, Set<BlockPos> trunksSet, Set<BlockPos> leavesSet){
        int branchLength=4+rand.nextInt(4);
        int shiftX=0; int shiftY=0; int shiftZ=0;
        for(int counter=0;counter<=branchLength;counter++){
            if(canIncreaseX && !canIncreaseZ){
                if(rand.nextInt(3)==0){shiftX++;}
                if(rand.nextInt(3)==0){shiftZ--;}
            }
            if(canIncreaseX && canIncreaseZ){
                if(rand.nextInt(3)==0){shiftX++;}
                if(rand.nextInt(3)==0){shiftZ++;}
            }
            if(!canIncreaseX && canIncreaseZ){
                if(rand.nextInt(3)==0){shiftX--;}
                if(rand.nextInt(3)==0){shiftZ++;}
            }
            if(!canIncreaseX && !canIncreaseZ){
                if(rand.nextInt(3)==0){shiftX--;}
                if(rand.nextInt(3)==0){shiftZ--;}
            }
            if(rand.nextInt(2)==0){shiftY++;}
            BlockPos tmp = new BlockPos(pos.getX()+shiftX,pos.getY()+shiftY,pos.getZ()+shiftZ);
            world.setBlockState(tmp,LOG,2);
            trunksSet.add(tmp);
            if(counter==branchLength){
                generateCrown(world,tmp, (int) (branchLength/1.5),rand, leavesSet);
            }
        }
    }

    private void generateCrown(IWorldGenerationReader world, BlockPos pos, int radius, Random random, Set<BlockPos> leavesSet) {
        BlockState leaves = LEAF;
        for(int x=-radius/2;x<=radius/2;x++) {
            for(int z=-radius/2;z<=radius/2;z++) {
                for(int y=-radius/4;y<=radius;y++) {
                    //do main crown
                    BlockPos tmp = new BlockPos(pos.getX()+x,pos.getY()+y,pos.getZ()+z);
                    if(((x*x)+(z*z)+(y*2*y*2)<=(radius/2*radius/2))){
                        if(isAir(world,tmp)){
                            world.setBlockState(tmp,leaves,2);
                            leavesSet.add(tmp);
                        }
                    }
                    //add some hanging blocks
                    if(((x*x)+(z*z)+(y*2*y*2)==(radius/2*radius/2)) && isAir(world,tmp.down())){
                        world.setBlockState(tmp.down(),LEAF,2);
                        leavesSet.add(tmp.down());
                        if(random.nextInt(3)==0 && isAir(world,tmp.down(2))){
                            world.setBlockState(tmp.down(2),LEAF,2);
                            leavesSet.add(tmp.down(2));
                            if(random.nextInt(3)==0 && isAir(world,tmp.down(3))){
                                world.setBlockState(tmp.down(3),LEAF,2);
                                leavesSet.add(tmp.down(3));
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    protected boolean func_225557_a_(IWorldGenerationReader worldIn, Random rand, BlockPos position, Set leavesSet, Set trunksSet, MutableBoundingBox p_225557_6_, BaseTreeFeatureConfig p_225557_7_) {
        if(canGrowInto(worldIn,position)||canGrowInto(worldIn,position.down())){
            int treeHeight=8+rand.nextInt(8);
            int posX=position.getX();
            int posY=position.getY();
            int posZ=position.getZ();
            BlockPos pos1 = new BlockPos(posX,posY,posZ);
            BlockPos pos2 = new BlockPos(posX+1,posY,posZ);
            BlockPos pos3 = new BlockPos(posX,posY,posZ+1);
            BlockPos pos4 = new BlockPos(posX+1,posY,posZ+1);
            makeRoot(worldIn,pos1,rand,false,false);
            makeRoot(worldIn,pos2,rand,true,false);
            makeRoot(worldIn,pos3,rand,false,true);
            makeRoot(worldIn,pos4,rand,true,true);
            for(int c=0;c<=treeHeight;c++){
                pos1 = new BlockPos(posX,posY+c,posZ);
                pos2 = new BlockPos(posX+1,posY+c,posZ);
                pos3 = new BlockPos(posX,posY+c,posZ+1);
                pos4 = new BlockPos(posX+1,posY+c,posZ+1);
                worldIn.setBlockState(pos1,LOG,2);
                trunksSet.add(pos1);
                worldIn.setBlockState(pos2,LOG,2);
                trunksSet.add(pos2);
                worldIn.setBlockState(pos3,LOG,2);
                trunksSet.add(pos3);
                worldIn.setBlockState(pos4,LOG,2);
                trunksSet.add(pos4);
                if(c>=4&&rand.nextInt(10)==0){
                    int k=rand.nextInt(4);
                    switch (k){
                        case 0: doBranch(worldIn,pos1,rand,false,false, trunksSet,leavesSet);
                        case 1: doBranch(worldIn,pos2,rand,true,false, trunksSet,leavesSet);
                        case 2: doBranch(worldIn,pos3,rand,false,true, trunksSet,leavesSet);
                        case 3: doBranch(worldIn,pos4,rand,true,true, trunksSet,leavesSet);
                    }
                }
                if(c>treeHeight/3&&rand.nextInt(12)==0){
                    tryToPutVinesRandomly(worldIn,pos1,rand);
                }
            }
            pos1 = new BlockPos(posX,posY+treeHeight,posZ);
            pos2 = new BlockPos(posX+1,posY+treeHeight,posZ);
            pos3 = new BlockPos(posX,posY+treeHeight,posZ+1);
            pos4 = new BlockPos(posX+1,posY+treeHeight,posZ+1);
            for(int c=0;c<4;c++){
                switch(c){
                    case 0: doTopBranch(worldIn,pos1,rand,false,false,trunksSet,leavesSet);
                    case 1: doTopBranch(worldIn,pos2,rand,true,false,trunksSet,leavesSet);
                    case 2: doTopBranch(worldIn,pos3,rand,false,true,trunksSet,leavesSet);
                    case 3: doTopBranch(worldIn,pos4,rand,true,true,trunksSet,leavesSet);

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