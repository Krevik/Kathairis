package mod.krevik.Biomes;

import java.util.Random;

import mod.krevik.Main.KCore;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;

public class BiomeKatharianOcean extends KetherBiome
{
    public BiomeKatharianOcean(Biome.BiomeProperties properties)
    {
        super(properties);
        this.spawnableCreatureList.clear();
        this.spawnableCaveCreatureList.clear();
        this.spawnableMonsterList.clear();
        this.spawnableWaterCreatureList.clear();
        this.decorator=createBiomeDecorator();
        this.decorator.treesPerChunk = 0;
        this.decorator.extraTreeChance = 0F;
        this.decorator.flowersPerChunk = 0;
        this.decorator.grassPerChunk = 0;
        this.topBlock=KCore.CorruptedGrass.getDefaultState();
        this.fillerBlock=KCore.CorruptedDirt.getDefaultState();
        this.setRegistryName(KCore.MODID, "Katharian Ocean");
        this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntitySquid.class, 6, 1, 6));
    }

    public void decorate(World worldIn, Random random, BlockPos pos)
    {
        super.decorate(worldIn,random,pos);
    }

    @Override
    public void addDefaultFlowers(){
    }

    public void genTerrainBlocks(World worldIn, Random rand, ChunkPrimer chunkPrimerIn, int x, int z, double noiseVal)
    {
        this.generateBiomeTerrainMysticForest(worldIn, rand, chunkPrimerIn, x, z, noiseVal);
    }

    public final void generateBiomeTerrainMysticForest(World worldIn, Random rand, ChunkPrimer chunkPrimerIn, int x, int z, double noiseVal)
    {
        int i = worldIn.getSeaLevel();
        IBlockState iblockstate = this.topBlock;
        IBlockState iblockstate1 = this.fillerBlock;
        int j = -1;
        int k = (int)(noiseVal / 3.0D + 3.0D + rand.nextDouble() * 0.25D);
        int l = x & 15;
        int i1 = z & 15;
        BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();
        for (int j1 = 255; j1 >= 0; --j1)
        {
            if (j1 <= rand.nextInt(5))
            {
                chunkPrimerIn.setBlockState(i1, j1, l, BEDROCK);
            }
            else
            {
                IBlockState iblockstate2 = chunkPrimerIn.getBlockState(i1, j1, l);

                if (iblockstate2.getMaterial() == Material.AIR)
                {
                    j = -1;
                }
                else if (iblockstate2.getBlock() == KCore.MythicStone)
                {
                    if (j == -1)
                    {
                        if (k <= 0)
                        {
                            iblockstate = AIR;
                            iblockstate1 = KCore.MythicStone.getDefaultState();
                        }
                        else if (j1 >= i - 4 && j1 <= i + 1)
                        {
                            iblockstate = this.topBlock;
                            iblockstate1 = this.fillerBlock;
                        }

                        if (j1 < i && (iblockstate == null || iblockstate.getMaterial() == Material.AIR))
                        {
                            iblockstate = WATER;
                        }

                        j = k;

                        if (j1 >= i - 1)
                        {
                            chunkPrimerIn.setBlockState(i1, j1, l, iblockstate);
                        }
                        else if (j1 < i - 7 - k)
                        {
                            iblockstate = AIR;
                            iblockstate1 = KCore.MythicStone.getDefaultState();
                            chunkPrimerIn.setBlockState(i1, j1, l, GRAVEL);
                        }
                        else
                        {
                            chunkPrimerIn.setBlockState(i1, j1, l, iblockstate1);
                        }
                    }
                    else if (j > 0)
                    {
                        --j;
                        chunkPrimerIn.setBlockState(i1, j1, l, iblockstate1);
                    }
                }
            }
        }
    }

}