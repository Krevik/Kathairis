package io.github.krevik.kathairis.world.dimension.feature;

import io.github.krevik.kathairis.init.ModBlocks;
import io.github.krevik.kathairis.util.RewardHelper;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.IChunkGenSettings;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.ArrayList;
import java.util.Random;

public class FeatureKatharianFloatingMiniIsland extends Feature<NoFeatureConfig> {
    @Override
    public boolean place(IWorld world, IChunkGenerator<? extends IChunkGenSettings> c, Random rand, BlockPos pos, NoFeatureConfig p_212245_5_) {
      if(rand.nextInt(200)==0) {
          IBlockState cloudState = rand.nextInt(2) == 0 ? ModBlocks.YELLOW_CLOUD.getDefaultState() : ModBlocks.BLUE_CLOUD.getDefaultState();
          int height = 150 + rand.nextInt(25) + rand.nextInt(25) + rand.nextInt(25) + rand.nextInt(25);
          int startRadius = 3 + rand.nextInt(6);
          int radius = startRadius;
          int posX = pos.getX();
          int posY = height;
          int posZ = pos.getZ();
          BlockPos generationPos = new BlockPos(posX, posY, posZ);
          //make base
          for (int y = 0; radius >= 1; y++) {
              doCircle(world, generationPos.down(y), radius, cloudState);
              if (rand.nextInt(2) == 0) {
                  radius--;
              }
          }
          //fill with grass
          int radiusOfGrassPlacing = startRadius - 1;
          for (int x = -radiusOfGrassPlacing / 2; x <= radiusOfGrassPlacing / 2; x++) {
              for (int z = -radiusOfGrassPlacing / 2; z <= radiusOfGrassPlacing / 2; z++) {
                  if ((x * x) + (z * z) <= (radiusOfGrassPlacing / 2 * radiusOfGrassPlacing / 2)) {
                      BlockPos tmp = new BlockPos(posX + x, posY, posZ + z);
                      world.setBlockState(tmp, ModBlocks.KATHAIRIS_GRASS.getDefaultState(), 2);
                      BlockPos upperPos = tmp.up();
                      if (rand.nextInt(5) == 2) {
                          world.setBlockState(upperPos, ModBlocks.KATHAIRIS_MINI_GRASS.getDefaultState(), 2);
                      }
                      if (rand.nextInt(30) == 0) {
                          world.setBlockState(upperPos, Blocks.CHEST.getDefaultState(), 2);
                          ArrayList<ItemStack> rewards = RewardHelper.getFloatingMiniIslandRewards(rand);
                          TileEntityChest chest = (TileEntityChest) world.getTileEntity(upperPos);
                          if (chest != null) {
                              for (int cc = 0; cc < rewards.size(); cc++) {
                                  chest.setInventorySlotContents(rand.nextInt(16), rewards.get(cc));
                              }
                          }
                      }
                  }
              }
          }
          return true;
      }
      return false;
    }


    private void doCircle(IWorld world, BlockPos pos, int radius, IBlockState cloudState){
        int posX=pos.getX();
        int posY=pos.getY();
        int posZ=pos.getZ();
        for(int x=-radius/2;x<=radius/2;x++){
            for(int z=-radius/2;z<=radius/2;z++){
                if((x*x)+(z*z) <= (radius/2*radius/2)){
                    BlockPos tmp = new BlockPos(posX+x,posY,posZ+z);
                    if(world.isBlockLoaded(tmp)){
                        world.setBlockState(tmp,cloudState,2);
                    }
                }
            }
        }
    }
}
