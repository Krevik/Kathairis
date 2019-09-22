package io.github.krevik.kathairis.world.dimension.feature.tree;

import com.mojang.datafixers.Dynamic;
import io.github.krevik.kathairis.block.BlockGlowVines;
import io.github.krevik.kathairis.init.ModBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.IWorldGenerationBaseReader;
import net.minecraft.world.gen.IWorldGenerationReader;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.Random;
import java.util.Set;
import java.util.function.Function;

public class FeatureKatharianTreeHuge1 extends AbstractKatharianTreeFeature {
    private static final BlockState LOG = ModBlocks.MYSTIC_LOG.getDefaultState();
    private static final BlockState LEAF = ModBlocks.MYSTIC_LEAVES.getDefaultState();

    public FeatureKatharianTreeHuge1(Function<Dynamic<?>, ? extends NoFeatureConfig> p_i49920_1_) {
        super(p_i49920_1_, true);
    }

    public boolean place(Set<BlockPos> changedBlocks, IWorldGenerationReader worldIn, Random rand, BlockPos position, MutableBoundingBox box) {
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
                setBlocks(changedBlocks,worldIn,pos1,LOG);
                setBlocks(changedBlocks,worldIn,pos2,LOG);
                setBlocks(changedBlocks,worldIn,pos3,LOG);
                setBlocks(changedBlocks,worldIn,pos4,LOG);
                if(c>=4&&rand.nextInt(10)==0){
                    int k=rand.nextInt(4);
                    switch (k){
                        case 0: doBranch(worldIn,pos1,rand,false,false, changedBlocks);
                        case 1: doBranch(worldIn,pos2,rand,true,false, changedBlocks);
                        case 2: doBranch(worldIn,pos3,rand,false,true, changedBlocks);
                        case 3: doBranch(worldIn,pos4,rand,true,true, changedBlocks);
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
                    case 0: doTopBranch(worldIn,pos1,rand,false,false,changedBlocks);
                    case 1: doTopBranch(worldIn,pos2,rand,true,false,changedBlocks);
                    case 2: doTopBranch(worldIn,pos3,rand,false,true,changedBlocks);
                    case 3: doTopBranch(worldIn,pos4,rand,true,true,changedBlocks);

                }
            }


            return true;
        }
        return false;
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


    private void doTopBranch(IWorldGenerationReader world, BlockPos pos, Random rand, boolean canIncreaseX, boolean canIncreaseZ, Set<BlockPos> changedBlocks){
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
            setBlocks(changedBlocks,world,tmp,LOG);
            if(counter==branchLength){
                generateTopCrown(world,tmp, (int) (branchLength),rand);
            }
        }
    }

    private void generateTopCrown(IWorldGenerationReader world, BlockPos pos, int radius, Random random) {
        BlockState leaves = LEAF;
        for(int x=-radius/2;x<=radius/2;x++) {
            for(int z=-radius/2;z<=radius/2;z++) {
                for(int y=-radius/4;y<=radius;y++) {
                    //do main crown
                    BlockPos tmp = new BlockPos(pos.getX()+x,pos.getY()+y,pos.getZ()+z);
                    if(((x*x)+(z*z)+(y*2*y*2)<=(radius/2*radius/2))){
                        if(isAir(world,tmp)){
                            world.setBlockState(tmp,leaves,2);
                        }
                    }
                    //add some hanging blocks
                    if(((x*x)+(z*z)+(y*2*y*2)==(radius/2*radius/2)) && isAir(world,tmp.down())){
                        world.setBlockState(tmp.down(),LEAF,2);
                        if(random.nextInt(3)==0 && isAir(world,tmp.down(2))){
                            world.setBlockState(tmp.down(2),LEAF,2);
                            if(random.nextInt(3)==0 && isAir(world,tmp.down(3))){
                                world.setBlockState(tmp.down(3),LEAF,2);
                            }
                        }
                    }
                }
            }
        }
    }

    private void doBranch(IWorldGenerationReader world, BlockPos pos, Random rand, boolean canIncreaseX, boolean canIncreaseZ, Set<BlockPos> changedBlocks){
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
            setBlocks(changedBlocks,world,tmp,LOG);
            if(counter==branchLength){
                generateCrown(world,tmp, (int) (branchLength/1.5),rand);
            }
        }
    }

    private void generateCrown(IWorldGenerationReader world, BlockPos pos, int radius, Random random) {
        BlockState leaves = LEAF;
        for(int x=-radius/2;x<=radius/2;x++) {
            for(int z=-radius/2;z<=radius/2;z++) {
                for(int y=-radius/4;y<=radius;y++) {
                    //do main crown
                    BlockPos tmp = new BlockPos(pos.getX()+x,pos.getY()+y,pos.getZ()+z);
                    if(((x*x)+(z*z)+(y*2*y*2)<=(radius/2*radius/2))){
                        if(isAir(world,tmp)){
                            world.setBlockState(tmp,leaves,2);
                        }
                    }
                    //add some hanging blocks
                    if(((x*x)+(z*z)+(y*2*y*2)==(radius/2*radius/2)) && isAir(world,tmp.down())){
                        world.setBlockState(tmp.down(),LEAF,2);
                        if(random.nextInt(3)==0 && isAir(world,tmp.down(2))){
                            world.setBlockState(tmp.down(2),LEAF,2);
                            if(random.nextInt(3)==0 && isAir(world,tmp.down(3))){
                                world.setBlockState(tmp.down(3),LEAF,2);
                            }
                        }
                    }
                }
            }
        }
    }


    protected final void setBlocks(Set<BlockPos> changedBlocks, IWorldGenerationReader worldIn, BlockPos p_208520_3_, BlockState p_208520_4_) {
        this.func_208521_b(worldIn, p_208520_3_, p_208520_4_);
        changedBlocks.add(p_208520_3_.toImmutable());

    }

    private void func_208521_b(IWorldGenerationReader p_208521_1_, BlockPos p_208521_2_, BlockState p_208521_3_) {
        if (this.doBlockNotify) {
            p_208521_1_.setBlockState(p_208521_2_, p_208521_3_, 19);
        } else {
            p_208521_1_.setBlockState(p_208521_2_, p_208521_3_, 18);
        }
    }
}