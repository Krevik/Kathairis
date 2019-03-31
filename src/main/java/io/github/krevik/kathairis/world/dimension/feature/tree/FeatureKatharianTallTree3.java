package io.github.krevik.kathairis.world.dimension.feature.tree;

import io.github.krevik.kathairis.init.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;

import java.util.Random;
import java.util.Set;

public class FeatureKatharianTallTree3 extends AbstractKatharianTreeFeature {
    private static final IBlockState LOG = ModBlocks.SHINY_LOG.getDefaultState();
    private static final IBlockState LEAF = ModBlocks.SHINY_LEAVES.getDefaultState();

    public FeatureKatharianTallTree3() {
        super(true);
    }

    @Override
    public boolean place(Set<BlockPos> changedBlocks, IWorld worldIn, Random rand, BlockPos position) {
        if(canGrowInto(worldIn,position)||canGrowInto(worldIn,position.down())){
            int mainTrunkHeight=10+rand.nextInt(8);
            int branches = (int) (mainTrunkHeight/3);
            int posX=position.getX();
            int posY=position.getY();
            int posZ=position.getZ();
            for(int c=0;c<=mainTrunkHeight;c++){
                setBlocks(changedBlocks,worldIn,new BlockPos(posX,posY+c,posZ),LOG);
            }
            int branchesHeights[] = new int[branches];
            for(int count=0;count<branches;count++){
                branchesHeights[count]=2+((mainTrunkHeight-2)/branches)+count+count;
            }
            for(int count=0;count<branchesHeights.length;count++){
                int branchLength=4+rand.nextInt(3);
                int shiftX=0; int shiftZ=0;
                for(int y=0;y<branchLength;y++){
                    shiftX=shiftX+rand.nextInt(2)-rand.nextInt(2);
                    shiftZ=shiftZ+rand.nextInt(2)-rand.nextInt(2);
                    BlockPos pos = new BlockPos(posX+shiftX,posY+y+branchesHeights[count],posZ+shiftZ);
                    setBlocks(changedBlocks,worldIn,pos,LOG);
                    if(y==branchLength-1){
                        int radius=3+rand.nextInt(3);
                        generateCrown(worldIn,pos,radius,rand);
                    }
                }
            }
            generateCrown(worldIn,new BlockPos(posX,posY+mainTrunkHeight,posZ),2+rand.nextInt(5),rand);

            return true;
        }
        return false;
    }

    private void generateCrown(IWorld world, BlockPos pos, int radius, Random random) {
        IBlockState leaves = LEAF;
        for(int x=-radius/2;x<=radius/2;x++) {
            for(int z=-radius/2;z<=radius/2;z++) {
                for(int y=-radius/4;y<=radius;y++) {
                    //do main crown
                    BlockPos tmp = new BlockPos(pos.getX()+x,pos.getY()+y,pos.getZ()+z);
                    if(((x*x)+(z*z)+(y*y)<=(radius/2*radius/2))){
                        if(world.getBlockState(tmp)== Blocks.AIR.getDefaultState()){
                            world.setBlockState(tmp,leaves,2);
                        }
                    }
                    //add some hanging blocks
                    if(((x*x)+(z*z)+(y*y)==(radius/2*radius/2)) && world.getBlockState(tmp.down()).getBlock()== Blocks.AIR){
                        world.setBlockState(tmp.down(),LEAF,2);
                        if(random.nextInt(3)==0 && world.getBlockState(tmp.down(2)).getBlock()== Blocks.AIR){
                            world.setBlockState(tmp.down(2),LEAF,2);
                            if(random.nextInt(3)==0 && world.getBlockState(tmp.down(3)).getBlock()== Blocks.AIR){
                                world.setBlockState(tmp.down(3),LEAF,2);
                            }
                        }
                    }
                }
            }
        }
    }


    @Override
    protected boolean canGrowInto(net.minecraft.world.IBlockReader world, BlockPos pos) {
        IBlockState iblockstate = world.getBlockState(pos);
        return  iblockstate.isIn(BlockTags.LEAVES) || iblockstate.getBlock() == Blocks.GRASS_BLOCK ||
                Block.isDirt(iblockstate.getBlock()) || iblockstate.isIn(BlockTags.LOGS) || iblockstate.isIn(BlockTags.SAPLINGS) ||
                iblockstate.getBlock() == Blocks.VINE || iblockstate.getBlock() == ModBlocks.KATHAIRIS_GRASS ||
                iblockstate.getBlock() == ModBlocks.KATHAIRIS_DIRT;
    }

    protected final void setBlocks(Set<BlockPos> changedBlocks, IWorld worldIn, BlockPos p_208520_3_, IBlockState p_208520_4_) {
        this.func_208521_b(worldIn, p_208520_3_, p_208520_4_);
        changedBlocks.add(p_208520_3_.toImmutable());

    }

    private void func_208521_b(IWorld p_208521_1_, BlockPos p_208521_2_, IBlockState p_208521_3_) {
        if (this.doBlockNotify) {
            p_208521_1_.setBlockState(p_208521_2_, p_208521_3_, 19);
        } else {
            p_208521_1_.setBlockState(p_208521_2_, p_208521_3_, 18);
        }
    }
}