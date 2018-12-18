package mod.krevik.Biomes;

import java.awt.*;
import java.util.Random;

import mod.krevik.Entities.EntityMysticBird;
import mod.krevik.Entities.EntityPhasm;
import com.krevik.Gens.WorldGenMiniTallGrass;
import com.krevik.Gens.WorldGenMysticUniversal;
import mod.krevik.Gens.Swamps.WorldGenMudPaddle;
import mod.krevik.Gens.Swamps.WorldGenMysticSwampsTree;
import mod.krevik.Gens.Swamps.WorldGenMysticSwampsWater;
import mod.krevik.Gens.Swamps.WorldGenOldTrunk;
import mod.krevik.Gens.Swamps.WorldGenSwampLakes;
import mod.krevik.Main.KCore;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BiomeMysticSwamps extends KetherBiome
{
    protected static WorldGenMiniTallGrass TALLGRASS1SWAMPS = new WorldGenMiniTallGrass(KCore.MysticTallGrass);
    protected static WorldGenAbstractTree TREE_FEATURE3SWAMPS = new WorldGenMysticSwampsTree();
    protected static WorldGenMudPaddle MUDPADDLESWAMPS = new WorldGenMudPaddle();
    protected static WorldGenMysticUniversal SWAMPGASSWAMPS = new WorldGenMysticUniversal(5);
    protected static WorldGenMysticUniversal SINGLEGENSWAMPS = new WorldGenMysticUniversal();
    protected static WorldGenMysticSwampsWater SWAMPSGENSWAMPS = new WorldGenMysticSwampsWater();

    public BiomeMysticSwamps(Biome.BiomeProperties properties)
    {
        super(properties);
        this.decorator.treesPerChunk = 1;
        this.decorator.extraTreeChance = 0F;
        this.decorator.flowersPerChunk = 0;
        this.decorator.grassPerChunk = 11;
        this.topBlock=KCore.CorruptedGrass.getDefaultState();
        this.fillerBlock=KCore.CorruptedDirt.getDefaultState();
        this.setRegistryName(KCore.MODID, "Mystic Swamps");
        this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntityMysticBird.class, 5, 1, 3));
        this.spawnableMonsterList.add(new Biome.SpawnListEntry(EntityPhasm.class, 2, 1, 1));

        baseGrassColor=new Color(92,105,77);
    }



    public WorldGenerator getRandomWorldGenForGrass(Random rand)
    {
    	int k=rand.nextInt(30);
    	if(k==3||k==15) {
    		return MUDPADDLESWAMPS;
    	}else if(k==5) {
    		return SWAMPGASSWAMPS;
    	}else if(k==4||k==6||k==7) {
    		return SINGLEGENSWAMPS;
    	}else if(k==8||k==9) {
    		return SWAMPSGENSWAMPS;
    	}else if(k==10||k==11) {
    		return TALLGRASS1SWAMPS;
    	}else {
        	return TALLGRASS1SWAMPS;
    	}
    }
    
    public WorldGenAbstractTree getRandomTreeFeature(Random rand)
    {
    		return (WorldGenAbstractTree)TREE_FEATURE3SWAMPS;
    }
    

    

    
    public void decorate(World world, Random random, BlockPos pos)
    {
        super.decorate(world,random,pos);
    			int rx = pos.getX() + random.nextInt(16) + 8;
    			int rz = pos.getZ() + random.nextInt(16) + 8;
    			new WorldGenSwampLakes(KCore.MudBlock).generate(world, random, world.getHeight(new BlockPos(rx, 0, rz)));
    			if(random.nextInt(2)==0) {
        			rx = pos.getX() + random.nextInt(16) + 8;
        			rz = pos.getZ() + random.nextInt(16) + 8;
        			new WorldGenSwampLakes(Blocks.WATER).generate(world, random, world.getHeight(new BlockPos(rx, 0, rz)));
    			}
    			if(random.nextInt(2)==0) {
        			rx = pos.getX() + random.nextInt(16) + 8;
        			rz = pos.getZ() + random.nextInt(16) + 8;
        			new WorldGenOldTrunk().generate(world, random, world.getHeight(new BlockPos(rx, 0, rz)));
    			}
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


                        j = k;

                        if (j1 >= i - 1)
                        {
                            chunkPrimerIn.setBlockState(i1, j1, l, iblockstate);
                        }
                        else if (j1 < i - 7 - k)
                        {
                            iblockstate = AIR;
                            iblockstate1 = KCore.MythicStone.getDefaultState();
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