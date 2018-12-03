package com.Krevik.Biomes;

import java.util.Calendar;
import java.util.List;
import java.util.Random;

import com.Krevik.Entities.*;
import com.Krevik.Entities.Butterflies.EntityCloudShimmer;
import com.Krevik.Gens.StructureLoader.CreateWorldGenFromStructure;
import com.Krevik.Gens.StructureLoader.StructureList;
import com.Krevik.Gens.WorldGenClouds;
import com.Krevik.Gens.WorldGenHugeSoulTree;
import com.Krevik.Gens.WorldGenMiniTallGrass;
import com.Krevik.Gens.WorldGenSolisCrystals;
import com.Krevik.Main.KCore;

import com.google.common.collect.Lists;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.Mirror;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraft.world.gen.structure.template.PlacementSettings;
import net.minecraft.world.gen.structure.template.Template;
import net.minecraft.world.gen.structure.template.TemplateManager;

public class BiomeFloatingIslands extends KetherBiome
{
    protected static WorldGenMiniTallGrass TALLGRASS_MINI = new WorldGenMiniTallGrass(KCore.MysticMiniGrass);
    protected static WorldGenAbstractTree TREE_FEATURE1 = new WorldGenHugeSoulTree();

    public BiomeFloatingIslands(Biome.BiomeProperties properties)
    {
        super(properties);
        this.decorator.treesPerChunk = 0;
        this.decorator.extraTreeChance = 1F;
        this.decorator.flowersPerChunk = 0;
        this.decorator.grassPerChunk = 6;
        this.topBlock=KCore.CorruptedGrass.getDefaultState();
        this.fillerBlock=KCore.CorruptedDirt.getDefaultState();
        this.setRegistryName(KCore.MODID, "Floating Islands");
        this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntityCloudOister.class, 6, 1, 1));
        this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntityCloudySlime.class, 8, 1, 1));
        this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntityCloudShimmer.class, 4, 1, 1));
        this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntityFlyingSquid.class, 4, 1, 1));
        this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntitySkyray.class, 2, 2, 4));
        this.spawnableMonsterList.add(new Biome.SpawnListEntry(EntityGaznowel.class, 2, 1, 1));
        int month = Calendar.getInstance().get(Calendar.MONTH);
        if(month==11){
            properties.setTemperature(0.0F);
            properties.setSnowEnabled();
        }
    }

    public float getSpawningChance()
    {
        return 0.1F;
    }

    public void decorate(World worldIn, Random random, BlockPos pos)
    {
        super.decorate(worldIn,random,pos);
        if(random.nextInt(3)==0) {
    		int X = pos.getX() + random.nextInt(16);
    		int Z = pos.getZ() + random.nextInt(16);
        	int j3=100+random.nextInt(25)+random.nextInt(25)+random.nextInt(25)+random.nextInt(25)+random.nextInt(25)+random.nextInt(25);
        	if(j3>0){
        		BlockPos blockposnew = new BlockPos(X,j3,Z);
        		new WorldGenClouds().generate(worldIn, random, blockposnew);
        	}
        }
        if(random.nextInt(12)==0) {
        	for(int x=0;x<=1+random.nextInt(4);x++) {
        		int X = pos.getX() + random.nextInt(16);
        		int Z = pos.getZ() + random.nextInt(16);
        		new WorldGenSolisCrystals().generate(worldIn, random, new BlockPos(X,0,Z));
        	}
        }
        if(random.nextInt(20)==0) {
    		int X = pos.getX() + random.nextInt(16);
    		int Z = pos.getZ() + random.nextInt(16);
        	int Y = worldIn.getHeight(new BlockPos(X,50,Z)).getY();
        	if(worldIn.getBlockState(new BlockPos(X,Y-1,Z))!=Blocks.AIR.getDefaultState()) {
        		if(!worldIn.isAirBlock(new BlockPos(X+getStoneTemple(worldIn).getSize().getX(),Y-1,Z))) {
               		if(!worldIn.isAirBlock(new BlockPos(X,Y-1,Z+getStoneTemple(worldIn).getSize().getZ()))) {
                   		if(worldIn.isAirBlock(new BlockPos(X,Y,Z+getStoneTemple(worldIn).getSize().getZ()))) {
                       		if(worldIn.isAirBlock(new BlockPos(X+getStoneTemple(worldIn).getSize().getX(),Y,Z))) {
                    			this.generateTemple(worldIn, new BlockPos(X,Y,Z));
                    		}
                		}
            		}
        		}
        	}
        }
    }
    
    private Template getStoneTemple(World world) {
 		WorldServer worldserver = (WorldServer) world;
 		MinecraftServer minecraftserver = world.getMinecraftServer();
 		TemplateManager templatemanager = worldserver.getStructureTemplateManager();
 		Template template = templatemanager.getTemplate(minecraftserver, new ResourceLocation(KCore.MODID+":stonetemple"));
 		return template;
    }
    

    
    private void generateTemple(World world,BlockPos position) {
 		world.getChunkProvider().provideChunk(position.getX(), position.getZ());
 		Template template = getStoneTemple(world);
 		
 		if(template == null)
 		{
 			System.out.println("NO STRUCTURE");
 		}
 			int xSize = template.getSize().getX();
 			int ySize = template.getSize().getY();
 			int zSize = template.getSize().getZ();

 			if(world.isAirBlock(position)&&world.isAirBlock(position.add(xSize, 0, 0))
                    &&world.isAirBlock(position.add(0,ySize,0))&&
 					world.isAirBlock(position.add(0, 0, zSize))&&
                    world.isAirBlock(position.add(xSize, 0, zSize))&&
                    world.isAirBlock(position.add(xSize, ySize, zSize))) {
 				IBlockState iblockstate = world.getBlockState(position);
 				world.notifyBlockUpdate(position, iblockstate, iblockstate, 3);
 				PlacementSettings placementsettings = (new PlacementSettings()).setMirror(Mirror.NONE)
 						.setRotation(Rotation.NONE).setIgnoreEntities(true).setChunk((ChunkPos) null)
 						.setReplacedBlock((Block) null).setIgnoreStructureBlock(true);
 				template.addBlocksToWorld(world, position, placementsettings);
 			}
     }
    
    public WorldGenerator getRandomWorldGenForGrass(Random rand) {
        if (rand.nextInt(3) == 0) {
            int k = rand.nextInt(3);
            if (k == 0) {
                return new CreateWorldGenFromStructure(StructureList.lahnbush_01, getStructureLoader());
            }
            else if (k == 1) {
                return new CreateWorldGenFromStructure(StructureList.lahnbush_02, getStructureLoader());
            }
            else{
                return new CreateWorldGenFromStructure(StructureList.lahntree_01, getStructureLoader());
            }
        } else {
            return TALLGRASS_MINI;
        }
    }
    
    public WorldGenAbstractTree getRandomTreeFeature(Random rand)
    {
    	return TREE_FEATURE1;
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