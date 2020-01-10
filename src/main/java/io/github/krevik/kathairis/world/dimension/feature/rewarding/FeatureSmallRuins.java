package io.github.krevik.kathairis.world.dimension.feature.rewarding;

import com.mojang.datafixers.Dynamic;
import io.github.krevik.kathairis.init.ModBlocks;
import io.github.krevik.kathairis.util.RewardHelper;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.BushBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.ChestTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.ArrayList;
import java.util.Random;
import java.util.function.Function;

public class FeatureSmallRuins extends Feature<NoFeatureConfig> {

    public FeatureSmallRuins(Function<Dynamic<?>, ? extends NoFeatureConfig> p_i49878_1_) {
        super(p_i49878_1_);
    }

    private void placeRuins(IWorld world, BlockPos startingPos, Random random, int scanningRadius){
        int posX=startingPos.getX();
        int posY=startingPos.getY();
        int posZ=startingPos.getZ();
        for(int x=-scanningRadius;x<=scanningRadius;x++){
            for(int y=-scanningRadius;y<=scanningRadius;y++){
                for(int z=-scanningRadius;z<=scanningRadius;z++){
                    BlockPos actualPos = new BlockPos(posX+x,posY+y,posZ+z);
                        if(world.getBlockState(actualPos).isSolid() && world.isAirBlock(actualPos.up()) && world.isAirBlock(actualPos.up())){
                            Block bricksToReplace = ModBlocks.KATHAIRIS_STONE_BRICKS;
                            if(random.nextInt(7)==0){
                                for(int i=1;i<=4+random.nextInt(5);i++){
                                    world.setBlockState(actualPos.up(i),bricksToReplace.getDefaultState(),2);
                                    if(world.getBlockState(actualPos.west(2)).getBlock()==bricksToReplace){
                                        if(random.nextInt(10)==0){
                                            world.setBlockState(actualPos.west(),bricksToReplace.getDefaultState(),2);
                                        }
                                    }
                                    if(world.getBlockState(actualPos.east(2)).getBlock()==bricksToReplace){
                                        if(random.nextInt(10)==0){
                                            world.setBlockState(actualPos.east(),bricksToReplace.getDefaultState(),2);
                                        }
                                    }
                                    if(world.getBlockState(actualPos.south(2)).getBlock()==bricksToReplace){
                                        if(random.nextInt(10)==0){
                                            world.setBlockState(actualPos.south(),bricksToReplace.getDefaultState(),2);
                                        }
                                    }
                                    if(world.getBlockState(actualPos.north(2)).getBlock()==bricksToReplace){
                                        if(random.nextInt(10)==0){
                                            world.setBlockState(actualPos.north(),bricksToReplace.getDefaultState(),2);
                                        }
                                    }
                                }
                            }else{
                                if(random.nextInt(20)==0){
                                    for(int i=1;i<=1+random.nextInt(2);i++){
                                        world.setBlockState(actualPos.up(i),Blocks.COBWEB.getDefaultState(),2);
                                    }
                                }
                                if(random.nextInt(20)==0){
                                    world.setBlockState(actualPos,Blocks.AIR.getDefaultState(),2);
                                }
                            }

                        }
                }
            }
        }
    }

    private void placeChestsWithRewards(IWorld world, BlockPos startingPos, Random random, int scanningRadius){
        int posX=startingPos.getX();
        int posY=startingPos.getY();
        int posZ=startingPos.getZ();
        for(int x=-scanningRadius;x<=scanningRadius;x++){
            for(int y=-scanningRadius;y<=scanningRadius;y++){
                for(int z=-scanningRadius;z<=scanningRadius;z++){
                    BlockPos actualPos = new BlockPos(posX+x,posY+y,posZ+z);
                    if(!world.isAirBlock(actualPos.down()) &&  world.getBlockState(actualPos.down()).isSolid() && !(world.getBlockState(actualPos.down()).getBlock() instanceof BushBlock)){
                        if(world.isAirBlock(actualPos.up())){
                            if(random.nextInt(200)==0){
                                world.setBlockState(actualPos.up(),Blocks.CHEST.getDefaultState(),2);
                                TileEntity tileEntity = world.getTileEntity(actualPos.up());
                                if(tileEntity instanceof ChestTileEntity){
                                    ChestTileEntity chest = (ChestTileEntity) tileEntity;
                                    ArrayList<ItemStack> rewards = RewardHelper.getRuinsRewardsList(random);
                                    RewardHelper.addRewardsToTheChest(rewards,chest);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public boolean place(IWorld world, ChunkGenerator<? extends GenerationSettings> generator, Random random, BlockPos pos, NoFeatureConfig config) {
        placeRuins(world,pos,random,12);
        placeChestsWithRewards(world,pos,random,12);
        return true;
    }
}