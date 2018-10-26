package com.Krevik.Biomes;

import java.util.Random;

import com.Krevik.Entities.EntityFungite;
import com.Krevik.Entities.EntityGecko;
import com.Krevik.Entities.EntityHowler;
import com.Krevik.Entities.EntityLivingFlower;
import com.Krevik.Entities.EntityMysticBird;
import com.Krevik.Gens.Forest.*;
import com.Krevik.Gens.WorldGenMiniTallGrass;
import com.Krevik.Gens.WorldGenMysticMultiGrass;
import com.Krevik.Gens.WorldGenNewTree;
import com.Krevik.Gens.WorldGenSingleGen;
import com.Krevik.Main.KCore;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BiomeMysticForest extends KetherBiome
{
    protected static WorldGenAbstractTree TREE_FEATURE2 = new WorldGenBasicMysticTree(false);
    protected static WorldGenAbstractTree TREE_FEATURE3 = new WorldGenShinyTree(true);
    protected static WorldGenAbstractTree TREE_FEATURE4 = new WorldGenNewTree();
    protected static WorldGenCustomTree2 CUSTOMTREE2 = new WorldGenCustomTree2();
    //protected static final WorldGenAbstractTree CUSTOMTREE3 = new WorldGenMysticForestTree();
    protected static WorldGenMiniTallGrass TALLGRASS1 = new WorldGenMiniTallGrass(KCore.MysticTallGrass);
    protected static WorldGenMiniTallGrass TALLGRASS2 = new WorldGenMiniTallGrass(KCore.MysticMiniGrass);
    protected static WorldGenSingleGen SINGLEGEN = new WorldGenSingleGen();
    protected static WorldGenSingleGen SINGLEGEN2 = new WorldGenSingleGen(3);
    protected static WorldGenMysticMultiGrass GrassGen = new WorldGenMysticMultiGrass(KCore.SteppedSucculent);
    protected static WorldGenAbstractTree CUSTOMTREE4 = new WorldGenHugeKathairisTree();

    protected static WorldGenAbstractTree FlippedHeartTree = new WorldGenFlippedHeartTree(2);

    public BiomeMysticForest(Biome.BiomeProperties properties)
    {
        super(properties);
        this.decorator.treesPerChunk = 3;
        this.decorator.extraTreeChance = 0F;
        this.decorator.flowersPerChunk = 0;
        this.decorator.grassPerChunk = 2;
        this.topBlock=KCore.CorruptedGrass.getDefaultState();
        this.fillerBlock=KCore.CorruptedDirt.getDefaultState();
        this.setRegistryName(KCore.MODID, "Mystic Forest");
        this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntityMysticBird.class, 12, 1, 2));
        this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntityGecko.class, 4, 1, 1));
        this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntityLivingFlower.class, 8, 1, 1));
        this.spawnableMonsterList.add(new Biome.SpawnListEntry(EntityHowler.class, 5, 1, 1));
        //this.spawnableMonsterList.add(new Biome.SpawnListEntry(EntitySkylight.class, 98, 1, 1));
        this.spawnableMonsterList.add(new Biome.SpawnListEntry(EntityFungite.class, 2, 1, 1));

    }

    public void decorate(World worldIn, Random random, BlockPos pos)
    {
        super.decorate(worldIn,random,pos);
    }
    public WorldGenAbstractTree getRandomTreeFeature(Random rand)
    {
    	if(rand.nextInt(5)==2){
    		if(rand.nextInt(50)==0) {
    			return (WorldGenAbstractTree)TREE_FEATURE3;
    		}else {
        		return (WorldGenAbstractTree)TREE_FEATURE2;
    		}
    	}
    	else if(rand.nextInt(5)==3) {
    		return TREE_FEATURE4;
    	}else if(rand.nextInt(5)==1) {
    		return CUSTOMTREE2;
    	}else if(rand.nextInt(6)==2||rand.nextInt(6)==3) {
    		return (WorldGenAbstractTree)CUSTOMTREE4;
    	}
    	else{
            return (WorldGenAbstractTree)TREE_FEATURE2;
    	}
    }
    
    public WorldGenerator getRandomWorldGenForGrass(Random rand)
    {
    	int i=rand.nextInt(13);
    	WorldGenerator worldgen=TALLGRASS1;
    	if(i<10){
	    	if(i==1||i==2||i==3||i==8){
	    		worldgen=SINGLEGEN;
	    	}
	    	if(i==4||i==7){
	    		worldgen=TALLGRASS2;
	    	}
	    	if(i==5||i==6) {
	    		worldgen=SINGLEGEN2;
	    	}
    	}else{
    		if(i==10) {
    			worldgen=GrassGen;
    		}else {
        		worldgen=TALLGRASS1;
    		}
    	}
    	return worldgen;
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