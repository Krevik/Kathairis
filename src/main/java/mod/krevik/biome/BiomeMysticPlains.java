package mod.krevik.biome;

import java.util.Random;

import mod.krevik.entity.EntityBison;
import mod.krevik.entity.EntityJellyFish;
import mod.krevik.entity.EntityMysticBird;
import mod.krevik.entity.butterfly.EntityButterfly;
import mod.krevik.entity.butterfly.EntityButterfly1;
import mod.krevik.world.gen.structureloader.CreateTreeFromStructure;
import mod.krevik.world.gen.structureloader.StructureList;
import mod.krevik.world.gen.WorldGenMiniTallGrass;
import mod.krevik.world.gen.WorldGenMysticMultiGrass;
import mod.krevik.world.gen.WorldGenMysticUniversal;
import mod.krevik.KCore;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BiomeMysticPlains extends KetherBiome
{
    protected static WorldGenMysticMultiGrass TALLGRASS3PLAINS = new WorldGenMysticMultiGrass();
    protected static WorldGenMysticUniversal SINGLEGENPLAINS = new WorldGenMysticUniversal();
    protected static WorldGenMysticUniversal SINGLEGEN1PLAINS = new WorldGenMysticUniversal(2);
    protected static WorldGenMiniTallGrass TALLGRASS1PLAINS = new WorldGenMiniTallGrass(KCore.MysticTallGrass);
    protected static WorldGenMiniTallGrass FRUPGEN = new WorldGenMiniTallGrass(KCore.BlueFruitPlant);
    protected static WorldGenMiniTallGrass BISONSTARSGEN = new WorldGenMiniTallGrass(KCore.bison_Stars);

    public BiomeMysticPlains(Biome.BiomeProperties properties)
    {
        super(properties);
        this.decorator.treesPerChunk = 0;
        this.decorator.extraTreeChance = 0F;
        this.decorator.flowersPerChunk = 0;
        this.decorator.grassPerChunk = 8; //8
        this.topBlock=KCore.KatharianGrass.getDefaultState();
        this.fillerBlock=KCore.KatharianDirt.getDefaultState();
        this.setRegistryName(KCore.MODID, "Mystic Plains");
        this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntityMysticBird.class, 12, 1, 3));
        this.spawnableMonsterList.add(new Biome.SpawnListEntry(EntityJellyFish.class, 10, 1, 1));
       this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntityBison.class, 8, 2, 4));
       this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntityButterfly.class, 10, 1, 1));
       this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntityButterfly1.class, 10, 1, 1));
    }

    public WorldGenerator getRandomWorldGenForGrass(Random rand)
    {
    	int k=rand.nextInt(15);
    	if(k==0) {
    		return SINGLEGEN1PLAINS;
    	}else if(k==1||k==2||k==3||k==4) {
    		return SINGLEGENPLAINS;
    	}else if(k==5||k==6||k==7) {
    		return TALLGRASS1PLAINS;
    	}else if(rand.nextInt(25)==0) {
    	    int c=rand.nextInt(4);
    	    if(c==0||c==1||c==2){
    	        return BISONSTARSGEN;
            }else{
                return FRUPGEN;
            }
    	}
    	else if(k==9){
    	    return new CreateTreeFromStructure(StructureList.nathbush_01_plains,getStructureLoader());
        }
    	else {
    		return TALLGRASS3PLAINS;
    	}
    }
    public void decorate(World worldIn, Random rand, BlockPos pos)
    {
        super.decorate(worldIn,rand,pos);
        /*if(rand.nextInt(140)==20){
        int j = rand.nextInt(16) + 8;
        int k = rand.nextInt(16) + 8;
        int l = rand.nextInt(worldIn.getHeight(pos.add(j, 0, k)).getY() + 32);
        new WorldGenImprovedCave().generate(worldIn, rand, pos.add(j,l,k));
        //new WorldGenCustomCaveSecond().generate(worldIn, rand, pos.add(j,l,k));
         */
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