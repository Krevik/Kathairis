package io.github.krevik.kathairis.world.dimension.feature.rewarding;

import com.mojang.datafixers.Dynamic;
import io.github.krevik.kathairis.block.BlockCondensedCloud;
import io.github.krevik.kathairis.block.BlockKathairisCloud;
import io.github.krevik.kathairis.block.TreesForSaplings.SoulTree;
import io.github.krevik.kathairis.init.ModBlocks;
import io.github.krevik.kathairis.util.RewardHelper;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.trees.Tree;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.ChestTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.ChunkGeneratorType;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.ArrayList;
import java.util.Random;
import java.util.function.Function;

public class FeatureSoulCloudWithChests extends Feature<NoFeatureConfig> {

    public FeatureSoulCloudWithChests(Function<Dynamic<?>, ? extends NoFeatureConfig> p_i49878_1_) {
        super(p_i49878_1_);
    }

    private void generateCloud(IWorld world, BlockPos pos, int radius, Random random, BlockState state, int count) {
        if(count<6) {
            for (int x = -radius / 2; x <= radius / 2; x++) {
                for (int z = -radius / 2; z <= radius / 2; z++) {
                    for (int y = -radius / 4; y <= radius; y++) {
                        //do main crown
                        BlockPos tmp = new BlockPos(pos.getX() + x, pos.getY() + y, pos.getZ() + z);
                        if (((x * x) + (z * z) + (y * 2 * y * 2) <= (radius / 2 * radius / 2))) {
                            if (world.isBlockLoaded(tmp)) {
                                if (world.getBlockState(tmp) == Blocks.AIR.getDefaultState()) {
                                    world.setBlockState(tmp, state, 2);
                                }
                                if (((x * x) + (z * z) + (y * 2 * y * 2) >= (radius / 2 * radius / 2) - 4)) {
                                    if (random.nextInt(7) == 0) {
                                        generateCloud(world, tmp, random.nextInt(6), random, state, count + 1);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private void placeTrees(IWorld world, BlockPos startingPos, Random random, int scanningRadius, ChunkGenerator<?> chunkGenerator){
        int posX=startingPos.getX();
        int posY=startingPos.getY();
        int posZ=startingPos.getZ();
        for(int x=-scanningRadius;x<=scanningRadius;x++){
            for(int y=-scanningRadius;y<=scanningRadius;y++){
                for(int z=-scanningRadius;z<=scanningRadius;z++){
                    BlockPos actualPos = new BlockPos(posX+x,posY+y,posZ+z);
                    if(world.getBlockState(actualPos).getBlock() instanceof BlockKathairisCloud){
                        if(world.isAirBlock(actualPos.up())){
                            if(world.isAirBlock(actualPos.up(2))&&world.isAirBlock(actualPos.up(3))&&world.isAirBlock(actualPos.up(4))){
                                if(random.nextInt(15)==0){
                                    world.setBlockState(actualPos,ModBlocks.KATHAIRIS_GRASS.getDefaultState(),2);
                                    Tree soulTree = new SoulTree();
                                    soulTree.func_225545_a_(world,chunkGenerator,actualPos.up(),ModBlocks.KATHAIRIS_GRASS.getDefaultState(),random);
                                }
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
                    if(world.getBlockState(actualPos).getBlock() instanceof BlockKathairisCloud ||
                    world.getBlockState(actualPos).getBlock() instanceof BlockCondensedCloud){
                        if(world.isAirBlock(actualPos.up())){
                                if(random.nextInt(50)==0){
                                    world.setBlockState(actualPos.up(),Blocks.CHEST.getDefaultState(),2);
                                    TileEntity tileEntity = world.getTileEntity(actualPos.up());
                                    if(tileEntity instanceof ChestTileEntity){
                                        ChestTileEntity chest = (ChestTileEntity) tileEntity;
                                        ArrayList<ItemStack> rewards = RewardHelper.getFloatingMiniIslandRewards(random);
                                        for(int i=0;i<rewards.size();i++){
                                            ItemStack stackToPut = rewards.get(i);
                                            chest.setInventorySlotContents(random.nextInt(16),stackToPut);
                                        }
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
            BlockState block = random.nextInt(2)==0 ? ModBlocks.BLUE_CLOUD.getDefaultState() : ModBlocks.YELLOW_CLOUD.getDefaultState();
            int height = 150+random.nextInt(50)+random.nextInt(20);
            generateCloud(world,new BlockPos(pos.getX(),height,pos.getZ()),4+random.nextInt(9),random,block,0);
            placeTrees(world,new BlockPos(pos.getX(),height,pos.getZ()),random,12, generator);
            placeChestsWithRewards(world,new BlockPos(pos.getX(),height,pos.getZ()),random,12);
        return true;
    }
}
