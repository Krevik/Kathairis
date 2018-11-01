package com.Krevik.Biomes;

import java.awt.*;
import java.util.List;
import java.util.Random;

import com.Krevik.Entities.EntityBigTurtle;
import com.Krevik.Entities.EntityCamel;
import com.Krevik.Entities.EntityPoisonousScorpion;
import com.Krevik.Gens.WorldGenMovingSand;
import com.Krevik.Gens.WorldGenMysticDesertCactus;
import com.Krevik.Gens.WorldGenSingleGen;
import com.Krevik.Gens.Desert.WorldGenBigRockMushroom;
import com.Krevik.Gens.Desert.WorldGenOldLibrary;
import com.Krevik.Gens.Desert.WorldGenRedwoodTree;
import com.Krevik.Gens.Desert.WorldGenRockMushroom;
import com.Krevik.Gens.Desert.WorldGenWeatheredCaveCustom;
import com.Krevik.Main.KCore;

import com.google.common.collect.Lists;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BiomeMysticDesert extends KetherBiome
{

    protected static WorldGenMovingSand MOVINGSANDGEN = new WorldGenMovingSand();
    protected static WorldGenRockMushroom RockMushroom = new WorldGenRockMushroom();
    protected static WorldGenWeatheredCaveCustom CAVE = new WorldGenWeatheredCaveCustom();
    protected static WorldGenBigRockMushroom BIGROCKMUSHROOM = new WorldGenBigRockMushroom();
    protected static WorldGenOldLibrary OLDLIBRARY = new WorldGenOldLibrary();
    protected static WorldGenMysticDesertCactus CACTUS = new WorldGenMysticDesertCactus();
    protected static WorldGenSingleGen SINGLEGENDESERT = new WorldGenSingleGen(1);
    //protected static WorldGenRedwoodTree REDWOODTREE = new WorldGenRedwoodTree();

    public BiomeMysticDesert(Biome.BiomeProperties properties)
    {
        super(properties);
        this.decorator.treesPerChunk = 0;
        this.decorator.extraTreeChance = 0F;
        this.decorator.flowersPerChunk = 0;
        this.decorator.grassPerChunk = 3;
        this.topBlock=KCore.ForgottenSand.getDefaultState();
        this.fillerBlock=KCore.WeatheredRock.getDefaultState();
        this.setRegistryName(KCore.MODID, "Mystic Desert");
        this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntityBigTurtle.class, 12, 1, 1));
        this.spawnableMonsterList.add(new Biome.SpawnListEntry(EntityPoisonousScorpion.class, 3, 1, 2));
        this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntityCamel.class, 6, 1, 2));
        baseGrassColor=new Color(255,242,0);
    }
    
    public WorldGenerator getRandomWorldGenForGrass(Random rand)
    {
    	int k=rand.nextInt(3);
    	if(k==0) {
    		return MOVINGSANDGEN;
    	}
    	else if(k==1) {
    		return SINGLEGENDESERT;
    	}
    	else if(k==2) {
    		return CACTUS;
    	}else {
    		return CACTUS;
    	}
    }
    
    public WorldGenAbstractTree getRandomTreeFeature(Random rand)
    {
    	return null;
    }

    public void decorate(World world, Random random, BlockPos pos)
    {
        super.decorate(world,random,pos);
        /*if(random.nextInt(8)==0){
			int rx = pos.getX() + random.nextInt(16) + 8;
			int rz = pos.getZ() + random.nextInt(16) + 8;
    		REDWOODTREE.generate(world, random, world.getHeight(new BlockPos(rx, 0, rz)));
        }*/


        
        if(random.nextInt(3)==0){
        	if(random.nextInt(4)==1) {
    			int rx = pos.getX() + random.nextInt(16) + 8;
    			int rz = pos.getZ() + random.nextInt(16) + 8;
        		BIGROCKMUSHROOM.generate(world, random, world.getHeight(new BlockPos(rx, 0, rz)));
        	}else {
    			int rx = pos.getX() + random.nextInt(16) + 8;
    			int rz = pos.getZ() + random.nextInt(16) + 8;
            RockMushroom.generate(world, random, world.getHeight(new BlockPos(rx, 0, rz)));
        	}
        }
        if(random.nextInt(35)==20){
			int rx = pos.getX() + random.nextInt(16) + 8;
			int rz = pos.getZ() + random.nextInt(16) + 8;
            CAVE.generate(world, random, world.getHeight(new BlockPos(rx, 0, rz)));
        }
        if(random.nextInt(400)==0){
			int rx = pos.getX() + random.nextInt(16) + 8;
			int rz = pos.getZ() + random.nextInt(16) + 8;
            OLDLIBRARY.generate(world, random, world.getHeight(new BlockPos(rx, 0, rz)));
        }
        
        /*if(random.nextInt(150)==0) {
    		int X = pos.getX() + random.nextInt(16)+8;
    		int Z = pos.getZ() + random.nextInt(16)+8;
        	int j3=100+random.nextInt(25)+random.nextInt(25)+random.nextInt(25)+random.nextInt(25);
        	if(j3>0){
        		BlockPos blockposnew = new BlockPos(X,j3,Z);
        		new WorldGenFloatingIslands().generate(world, random, blockposnew);
        	}
        }
        */
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
                            iblockstate1 = KCore.WeatheredRock.getDefaultState();
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
                            iblockstate1 = KCore.WeatheredRock.getDefaultState();
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