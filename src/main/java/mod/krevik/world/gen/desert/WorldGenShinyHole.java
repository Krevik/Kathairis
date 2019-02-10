package mod.krevik.world.gen.desert;

import mod.krevik.KCore;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public class WorldGenShinyHole extends WorldGenerator {

    public WorldGenShinyHole(){

    }

    @Override
    public boolean generate(World worldIn, Random rand, BlockPos pos) {
        if(worldIn.getBlockState(pos.down())==KCore.MovingSand.getDefaultState()) {
            int shiftY = 4;
            int radius = 4 + rand.nextInt(5);
            int deep = 5 + rand.nextInt(12);
            for (int x = -radius / 2; x <= radius / 2; x++) {
                for (int z = -radius / 2; z <= radius / 2; z++) {
                    //doing the hole
                    if ((x * x) + (z * z) <= (radius / 2 * radius / 2)) {
                        for (int y = 0; y <= deep; y++) {
                            worldIn.setBlockToAir(new BlockPos(pos.getX() + x, pos.getY() - y, pos.getZ() + z));
                        }
                    }
                    //making hole walls
                    if ((x * x) + (z * z) <= (radius / 2 * radius / 2 + radius / 2.5) && (x * x) + (z * z) >= (radius / 2 * radius / 2 - radius / 2.5)) {
                        //under ground walls
                        for (int y = -deep; y <= 0; y++) {
                            worldIn.setBlockState(new BlockPos(pos.getX() + x, pos.getY() + y, pos.getZ() + z), KCore.WeatheredRock.getDefaultState());
                            if (rand.nextInt(50) == 0)
                                worldIn.setBlockState(new BlockPos(pos.getX() + x, pos.getY() + y, pos.getZ() + z), KCore.GemsOre.getDefaultState());
                        }
                        //above the ground walls
                        for (int y = 0; y <= shiftY; y++) {
                            worldIn.setBlockState(new BlockPos(pos.getX() + x, pos.getY() + y, pos.getZ() + z), KCore.WeatheredRock.getDefaultState());
                            if (rand.nextInt(50) == 0)
                                worldIn.setBlockState(new BlockPos(pos.getX() + x, pos.getY() + y, pos.getZ() + z), KCore.GemsOre.getDefaultState());
                            shiftY = shiftY - rand.nextInt(2) + rand.nextInt(2);
                            if (shiftY < 2) shiftY++;
                            if (shiftY > 8) shiftY--;
                        }
                    }

                    BlockPos pos1 = new BlockPos(pos.getX(), pos.getY() - deep, pos.getZ());
                    for (int y = -radius / 2; y <= 0; y++) {
                        //clearing underground sphere
                        if ((x * x) + (z * z) + (y * y) <= (radius / 2 * radius / 2)) {
                            worldIn.setBlockToAir(new BlockPos(pos1.getX() + x, pos1.getY() + y, pos1.getZ() + z));
                        }
                        //filling underground sphere
                        if ((x * x) + (z * z) + (y * y) <= (radius / 2 * radius / 2 + radius / 2.5) && (x * x) + (z * z) + (y * y) >= (radius / 2 * radius / 2 - radius / 2.5)) {
                            worldIn.setBlockState(new BlockPos(pos1.getX() + x, pos1.getY() + y, pos1.getZ() + z), KCore.ShinyRock.getDefaultState());
                        }
                    }
                }
            }

            return true;
        }else{
            return false;
        }
    }
}
