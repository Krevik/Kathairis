package mod.krevik.Dimension;

import java.util.List;
import java.util.Random;

import com.krevik.Gens.MapGenKetherCaves;
import com.krevik.Gens.WorldGenCloudTemple;
import com.krevik.Gens.WorldGenClouds;
import com.krevik.Gens.WorldGenCrystalChamber;
import com.krevik.Gens.WorldGenFloatingIslands;
import com.krevik.Gens.WorldGenFloatingTree;
import com.krevik.Gens.WorldGenMysticLakes;
import com.krevik.Gens.WorldGenMysticUniversal;
import mod.krevik.Main.KCore;

import net.minecraft.block.BlockFalling;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.WorldEntitySpawner;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.*;

public class ChunkGeneratorMystic implements IChunkGenerator
{
    protected static final IBlockState STONE = KCore.MythicStone.getDefaultState();
    private final Random rand;
    private NoiseGeneratorOctaves minLimitPerlinNoise;
    private NoiseGeneratorOctaves maxLimitPerlinNoise;
    private NoiseGeneratorOctaves mainPerlinNoise;
    private NoiseGeneratorPerlin surfaceNoise;
    public NoiseGeneratorOctaves scaleNoise;
    public NoiseGeneratorOctaves depthNoise;
    public NoiseGeneratorOctaves forestNoise;
    private final World world;
    private final WorldType terrainType;
    private final double[] heightMap;
    private final float[] biomeWeights;
    private ChunkGeneratorSettings settings;
    private IBlockState oceanBlock = Blocks.WATER.getDefaultState();
    private double[] depthBuffer = new double[256];
    private Biome[] biomesForGeneration;
    double[] mainNoiseRegion;
    double[] minLimitRegion;
    double[] maxLimitRegion;
    double[] depthRegion;
    private MapGenBase caveGenerator = new MapGenKetherCaves();
    
    //Islands
    private NoiseGeneratorOctaves lperlinNoise1;
    private NoiseGeneratorOctaves lperlinNoise2;
    private NoiseGeneratorOctaves perlinNoise1;
    public NoiseGeneratorOctaves noiseGen5;
    public NoiseGeneratorOctaves noiseGen6;
    private NoiseGeneratorSimplex islandNoise;
    private double[] buffer;
    double[] pnr;
    double[] ar;
    double[] br;
    private int chunkX = 0;
    private int chunkZ = 0;
    private int upperScale=130;
    public static final WorldGenMysticUniversal EnergyShard = new WorldGenMysticUniversal(4);
    public int cloudWeight=5;


    public static final WorldGenCrystalChamber CrystalChamber = new WorldGenCrystalChamber();
    public static final WorldGenCloudTemple CloudTemple = new WorldGenCloudTemple();
    public static final WorldGenFloatingIslands FloatingIsland = new WorldGenFloatingIslands(1);

    public ChunkGeneratorMystic(World worldIn, long seed)
    {
        caveGenerator = net.minecraftforge.event.terraingen.TerrainGen.getModdedMapGen(caveGenerator, net.minecraftforge.event.terraingen.InitMapGenEvent.EventType.CAVE);
        this.world = worldIn;
        this.terrainType = worldIn.getWorldInfo().getTerrainType();
        this.rand = new Random(seed);
        this.minLimitPerlinNoise = new NoiseGeneratorOctaves(this.rand, 16);
        this.maxLimitPerlinNoise = new NoiseGeneratorOctaves(this.rand, 16);
        this.mainPerlinNoise = new NoiseGeneratorOctaves(this.rand, 8);
        this.surfaceNoise = new NoiseGeneratorPerlin(this.rand, 4);
        this.scaleNoise = new NoiseGeneratorOctaves(this.rand, 10);
        this.depthNoise = new NoiseGeneratorOctaves(this.rand, 16);
        this.forestNoise = new NoiseGeneratorOctaves(this.rand, 8);
        this.heightMap = new double[825];
        this.biomeWeights = new float[25];

        for (int i = -2; i <= 2; ++i)
        {
            for (int j = -2; j <= 2; ++j)
            {
                float f = 10.0F / MathHelper.sqrt((float)(i * i + j * j) + 0.2F);
                this.biomeWeights[i + 2 + (j + 2) * 5] = f;
            }
        }
        
        this.oceanBlock = Blocks.WATER.getDefaultState();
        worldIn.setSeaLevel(63);
        this.settings = ChunkGeneratorSettings.Factory.jsonToFactory("nope").build();


        net.minecraftforge.event.terraingen.InitNoiseGensEvent.ContextOverworld ctx =
                new net.minecraftforge.event.terraingen.InitNoiseGensEvent.ContextOverworld(minLimitPerlinNoise, maxLimitPerlinNoise, mainPerlinNoise, surfaceNoise, scaleNoise, depthNoise, forestNoise);
        ctx = net.minecraftforge.event.terraingen.TerrainGen.getModdedNoiseGenerators(worldIn, this.rand, ctx);
        this.minLimitPerlinNoise = ctx.getLPerlin1();
        this.maxLimitPerlinNoise = ctx.getLPerlin2();
        this.mainPerlinNoise = ctx.getPerlin();
        this.surfaceNoise = ctx.getHeight();
        this.scaleNoise = ctx.getScale();
        this.depthNoise = ctx.getDepth();
        this.forestNoise = ctx.getForest();
        
        //islands
        this.lperlinNoise1 = new NoiseGeneratorOctaves(this.rand, 16);
        this.lperlinNoise2 = new NoiseGeneratorOctaves(this.rand, 16);
        this.perlinNoise1 = new NoiseGeneratorOctaves(this.rand, 8);
        this.noiseGen5 = new NoiseGeneratorOctaves(this.rand, 10);
        this.noiseGen6 = new NoiseGeneratorOctaves(this.rand, 16);
        this.islandNoise = new NoiseGeneratorSimplex(this.rand);
        net.minecraftforge.event.terraingen.InitNoiseGensEvent.ContextEnd ctx2 =
                new net.minecraftforge.event.terraingen.InitNoiseGensEvent.ContextEnd(lperlinNoise1, lperlinNoise2, perlinNoise1, noiseGen5, noiseGen6, islandNoise);
        ctx2 = net.minecraftforge.event.terraingen.TerrainGen.getModdedNoiseGenerators(worldIn, this.rand, ctx2);
        this.lperlinNoise1 = ctx2.getLPerlin1();
        this.lperlinNoise2 = ctx2.getLPerlin2();
        this.perlinNoise1 = ctx2.getPerlin();
        this.noiseGen5 = ctx2.getDepth();
        this.noiseGen6 = ctx2.getScale();
        this.islandNoise = ctx2.getIsland();
    }

    public void setBlocksInChunk(int x, int z, ChunkPrimer primer)
    {
        this.biomesForGeneration = this.world.getBiomeProvider().getBiomesForGeneration(this.biomesForGeneration, x * 4 - 2, z * 4 - 2, 10, 10);
        this.generateHeightmap(x * 4, 0, z * 4);

        for (int i = 0; i < 4; ++i)
        {
            int j = i * 5;
            int k = (i + 1) * 5;

            for (int l = 0; l < 4; ++l)
            {
                int i1 = (j + l) * 33;
                int j1 = (j + l + 1) * 33;
                int k1 = (k + l) * 33;
                int l1 = (k + l + 1) * 33;

                for (int i2 = 0; i2 < 32; ++i2)
                {
                    double d0 = 0.125D;
                    double d1 = this.heightMap[i1 + i2];
                    double d2 = this.heightMap[j1 + i2];
                    double d3 = this.heightMap[k1 + i2];
                    double d4 = this.heightMap[l1 + i2];
                    double d5 = (this.heightMap[i1 + i2 + 1] - d1) * 0.125D;
                    double d6 = (this.heightMap[j1 + i2 + 1] - d2) * 0.125D;
                    double d7 = (this.heightMap[k1 + i2 + 1] - d3) * 0.125D;
                    double d8 = (this.heightMap[l1 + i2 + 1] - d4) * 0.125D;

                    for (int j2 = 0; j2 < 8; ++j2)
                    {
                        double d9 = 0.25D;
                        double d10 = d1;
                        double d11 = d2;
                        double d12 = (d3 - d1) * 0.25D;
                        double d13 = (d4 - d2) * 0.25D;

                        for (int k2 = 0; k2 < 4; ++k2)
                        {
                            double d14 = 0.25D;
                            double d16 = (d11 - d10) * 0.25D;
                            double lvt_45_1_ = d10 - d16;

                            for (int l2 = 0; l2 < 4; ++l2)
                            {
                                if ((lvt_45_1_ += d16) > 0.0D)
                                {
                                    primer.setBlockState(i * 4 + k2, i2 * 8 + j2, l * 4 + l2, STONE);
                                }
                                else if (i2 * 8 + j2 < this.settings.seaLevel)
                                {
                                    primer.setBlockState(i * 4 + k2, i2 * 8 + j2, l * 4 + l2, this.oceanBlock);
                                }
                            }

                            d10 += d12;
                            d11 += d13;
                        }

                        d1 += d5;
                        d2 += d6;
                        d3 += d7;
                        d4 += d8;
                    }
                }
            }
        }
    }

    public void replaceBiomeBlocks(int x, int z, ChunkPrimer primer, Biome[] biomesIn)
    {
        if (!net.minecraftforge.event.ForgeEventFactory.onReplaceBiomeBlocks(this, x, z, primer, this.world)) return;
        double d0 = 0.03125D;
        this.depthBuffer = this.surfaceNoise.getRegion(this.depthBuffer, (double)(x * 16), (double)(z * 16), 16, 16, 0.0625D, 0.0625D, 1.0D);

        for (int i = 0; i < 16; ++i)
        {
            for (int j = 0; j < 16; ++j)
            {
                Biome biome = biomesIn[j + i * 16];
                biome.genTerrainBlocks(this.world, this.rand, primer, x * 16 + i, z * 16 + j, this.depthBuffer[j + i * 16]);
            }
        }
    }



    private void generateHeightmap(int p_185978_1_, int p_185978_2_, int p_185978_3_)
    {
        this.depthRegion = this.depthNoise.generateNoiseOctaves(this.depthRegion, p_185978_1_, p_185978_3_, 5, 5, (double)this.settings.depthNoiseScaleX, (double)this.settings.depthNoiseScaleZ, (double)this.settings.depthNoiseScaleExponent);
        float f = this.settings.coordinateScale;
        float f1 = this.settings.heightScale;
        this.mainNoiseRegion = this.mainPerlinNoise.generateNoiseOctaves(this.mainNoiseRegion, p_185978_1_, p_185978_2_, p_185978_3_, 5, 33, 5, (double)(f / this.settings.mainNoiseScaleX), (double)(f1 / this.settings.mainNoiseScaleY), (double)(f / this.settings.mainNoiseScaleZ));
        this.minLimitRegion = this.minLimitPerlinNoise.generateNoiseOctaves(this.minLimitRegion, p_185978_1_, p_185978_2_, p_185978_3_, 5, 33, 5, (double)f, (double)f1, (double)f);
        this.maxLimitRegion = this.maxLimitPerlinNoise.generateNoiseOctaves(this.maxLimitRegion, p_185978_1_, p_185978_2_, p_185978_3_, 5, 33, 5, (double)f, (double)f1, (double)f);
        int i = 0;
        int j = 0;

        for (int k = 0; k < 5; ++k)
        {
            for (int l = 0; l < 5; ++l)
            {
                float f2 = 0.0F;
                float f3 = 0.0F;
                float f4 = 0.0F;
                int i1 = 2;
                Biome biome = this.biomesForGeneration[k + 2 + (l + 2) * 10];

                for (int j1 = -2; j1 <= 2; ++j1)
                {
                    for (int k1 = -2; k1 <= 2; ++k1)
                    {
                        Biome biome1 = this.biomesForGeneration[k + j1 + 2 + (l + k1 + 2) * 10];
                        float f5 = this.settings.biomeDepthOffSet + biome1.getBaseHeight() * this.settings.biomeDepthWeight;
                        float f6 = this.settings.biomeScaleOffset + biome1.getHeightVariation() * this.settings.biomeScaleWeight;

                        if (this.terrainType == WorldType.AMPLIFIED && f5 > 0.0F)
                        {
                            f5 = 1.0F + f5 * 2.0F;
                            f6 = 1.0F + f6 * 4.0F;
                        }

                        float f7 = this.biomeWeights[j1 + 2 + (k1 + 2) * 5] / (f5 + 2.0F);

                        if (biome1.getBaseHeight() > biome.getBaseHeight())
                        {
                            f7 /= 2.0F;
                        }

                        f2 += f6 * f7;
                        f3 += f5 * f7;
                        f4 += f7;
                    }
                }

                f2 = f2 / f4;
                f3 = f3 / f4;
                f2 = f2 * 0.9F + 0.1F;
                f3 = (f3 * 4.0F - 1.0F) / 8.0F;
                double d7 = this.depthRegion[j] / 8000.0D;

                if (d7 < 0.0D)
                {
                    d7 = -d7 * 0.3D;
                }

                d7 = d7 * 3.0D - 2.0D;

                if (d7 < 0.0D)
                {
                    d7 = d7 / 2.0D;

                    if (d7 < -1.0D)
                    {
                        d7 = -1.0D;
                    }

                    d7 = d7 / 1.4D;
                    d7 = d7 / 2.0D;
                }
                else
                {
                    if (d7 > 1.0D)
                    {
                        d7 = 1.0D;
                    }

                    d7 = d7 / 8.0D;
                }

                ++j;
                double d8 = (double)f3;
                double d9 = (double)f2;
                d8 = d8 + d7 * 0.2D;
                d8 = d8 * (double)this.settings.baseSize / 8.0D;
                double d0 = (double)this.settings.baseSize + d8 * 4.0D;

                for (int l1 = 0; l1 < 33; ++l1)
                {
                    double d1 = ((double)l1 - d0) * (double)this.settings.stretchY * 128.0D / 256.0D / d9;

                    if (d1 < 0.0D)
                    {
                        d1 *= 4.0D;
                    }

                    double d2 = this.minLimitRegion[i] / (double)this.settings.lowerLimitScale;
                    double d3 = this.maxLimitRegion[i] / (double)this.settings.upperLimitScale;
                    double d4 = (this.mainNoiseRegion[i] / 10.0D + 1.0D) / 2.0D;
                    double d5 = MathHelper.clampedLerp(d2, d3, d4) - d1;

                    if (l1 > 29)
                    {
                        double d6 = (double)((float)(l1 - 29) / 3.0F);
                        d5 = d5 * (1.0D - d6) + -10.0D * d6;
                    }

                    this.heightMap[i] = d5;
                    ++i;
                }
            }
        }
    }
    
    /**
     * Generates the chunk at the specified position, from scratch
     */
    public Chunk generateChunk(int x, int z)
    {
        this.rand.setSeed((long)x * 341873128712L + (long)z * 132897987541L);
        ChunkPrimer chunkprimer = new ChunkPrimer();
        this.setBlocksInChunk(x, z, chunkprimer);
        this.biomesForGeneration = this.world.getBiomeProvider().getBiomes(this.biomesForGeneration, x * 16, z * 16, 16, 16);
    	this.setBlocksInChunkIsland(world,x,z,chunkprimer);
        this.replaceBiomeBlocks(x, z, chunkprimer, this.biomesForGeneration);
        
        int ii = x * 16;
        int j = z * 16;
        BlockPos blockpos = new BlockPos(ii, 0, j);


       // this.caveGenerator.generate(this.world, x, z, chunkprimer);
        	caveGenerator.generate(world, x, z, chunkprimer);

        Chunk chunk = new Chunk(this.world, chunkprimer, x, z);
        byte[] abyte = chunk.getBiomeArray();

        for (int i = 0; i < abyte.length; ++i)
        {
            abyte[i] = (byte)Biome.getIdForBiome(this.biomesForGeneration[i]);
        }



        chunk.generateSkylightMap();
        return chunk;
    }

    /**
     * Generate initial structures in this chunk, e.g. mineshafts, temples, lakes, and dungeons
     */
    public void populate(int x, int z)
    {
        BlockFalling.fallInstantly = true;

        int i = x * 16;
        int j = z * 16;
        BlockPos blockpos = new BlockPos(i, 0, j);
        Biome biome = this.world.getBiome(blockpos.add(16, 0, 16));
        this.rand.setSeed(this.world.getSeed());
        long k = this.rand.nextLong() / 2L * 2L + 1L;
        long l = this.rand.nextLong() / 2L * 2L + 1L;
        this.rand.setSeed((long)x * k + (long)z * l ^ this.world.getSeed());
        ChunkPos chunkpos = new ChunkPos(x, z);

        net.minecraftforge.event.ForgeEventFactory.onChunkPopulate(true, this, this.world, this.rand, x, z, false);


        if (this.settings.useWaterLakes && this.rand.nextInt(this.settings.waterLakeChance) == 0)
        if (net.minecraftforge.event.terraingen.TerrainGen.populate(this, this.world, this.rand, x, z, false, net.minecraftforge.event.terraingen.PopulateChunkEvent.Populate.EventType.LAKE))
        {
            int i1 = this.rand.nextInt(16) + 8;
            int j1 = this.rand.nextInt(256);
            int k1 = this.rand.nextInt(16) + 8;
            (new WorldGenMysticLakes(Blocks.WATER)).generate(this.world, this.rand, blockpos.add(i1, j1, k1));
        }

        if (this.rand.nextInt(this.settings.lavaLakeChance / 10) == 0 && this.settings.useLavaLakes)
        if (net.minecraftforge.event.terraingen.TerrainGen.populate(this, this.world, this.rand, x, z, false, net.minecraftforge.event.terraingen.PopulateChunkEvent.Populate.EventType.LAVA))
        {
            int i2 = this.rand.nextInt(16) + 8;
            int l2 = this.rand.nextInt(this.rand.nextInt(248) + 8);
            int k3 = this.rand.nextInt(16) + 8;

            if (l2 < this.world.getSeaLevel() || this.rand.nextInt(this.settings.lavaLakeChance / 8) == 0)
            {
                (new WorldGenMysticLakes(Blocks.LAVA)).generate(this.world, this.rand, blockpos.add(i2, l2, k3));
            }
        }

        if(this.rand.nextInt(cloudWeight)==0) {
    		int X = this.rand.nextInt(16) + 8;
    		int Z = this.rand.nextInt(16) + 8;
        	int j3=85+this.rand.nextInt(25)+this.rand.nextInt(25)+this.rand.nextInt(25)+this.rand.nextInt(25);
        	if(j3>0){
        		new WorldGenClouds().generate(world, this.rand, blockpos.add(X,j3,Z));
        	}
        }
        if(this.rand.nextInt(30)==0)
        {
    		int X = this.rand.nextInt(16) + 8;
    		int Z = this.rand.nextInt(16) + 8;
    		BlockPos tmp = blockpos.add(X,0,Z);
    		int TOP = world.getHeight(tmp).getY();
        		BlockPos blockposnew = new BlockPos(X,TOP,Z);
            EnergyShard.generate(world, this.rand, blockpos.add(X,TOP,Z));
        }

	        if(this.rand.nextInt(100)==2) {
	    		int X = this.rand.nextInt(16) + 8;
	    		int Z = this.rand.nextInt(16) + 8;
	        	int j3=this.rand.nextInt(60)+8;
	        	if(j3<70&&j3>10){
	        		int i4=this.rand.nextInt(j3)+8;
	        		BlockPos blockposnew = new BlockPos(X,i4,Z);
	        		CrystalChamber.generate(world, this.rand, blockpos.add(X,i4,Z));
	        	}
	        }
	        if(this.rand.nextInt(1200)==0) {
	    		int X = this.rand.nextInt(16) + 8;
	    		int Z = this.rand.nextInt(16) + 8;
	        	int j3=90+this.rand.nextInt(45)+this.rand.nextInt(45)+this.rand.nextInt(45)+this.rand.nextInt(45);
	        	if(j3>100&&j3<220){
	        		CloudTemple.generate(world, this.rand, blockpos.add(X,j3,Z));
	        	}
	        }
	        if(this.rand.nextInt(100)==0) {
	    		int X = this.rand.nextInt(16) + 8;
	    		int Z = this.rand.nextInt(16) + 8;
	        	int j3=100+this.rand.nextInt(25)+this.rand.nextInt(25)+this.rand.nextInt(25)+this.rand.nextInt(25);
	        		new WorldGenFloatingTree().generate2(world, this.rand, blockpos.add(X,j3,Z));
	        }
	        for(int c=0;c<biome.decorator.treesPerChunk;c++){
	    		int X = this.rand.nextInt(16) + 8;
	    		int Z = this.rand.nextInt(16) + 8;
	        		biome.getRandomTreeFeature(this.rand).generate(world, this.rand, world.getHeight(blockpos.add(X,0,Z)));
	        }
	        for (int i3 = 0; i3 < biome.decorator.grassPerChunk; ++i3)
	        {
	    		int X = this.rand.nextInt(16) + 8;
	    		int Z = this.rand.nextInt(16) + 8;
				biome.getRandomWorldGenForGrass(this.rand).generate(world, this.rand, world.getHeight(blockpos.add(X,0,Z)));
	        }
	        
	        /*if(this.rand.nextInt(1000)==0) {
	    		int X = this.rand.nextInt(16) + 8;
	    		int Z = this.rand.nextInt(16) + 8;
	        	int j3=90+this.rand.nextInt(45)+this.rand.nextInt(45)+this.rand.nextInt(45)+this.rand.nextInt(45);
        		FloatingIsland.generate(world, this.rand, blockpos.add(X,j3,Z));
	        }*/
	        
        biome.decorate(this.world, this.rand, new BlockPos(i, 0, j));
        
        if (net.minecraftforge.event.terraingen.TerrainGen.populate(this, this.world, this.rand, x, z, false, net.minecraftforge.event.terraingen.PopulateChunkEvent.Populate.EventType.ANIMALS))
            WorldEntitySpawner.performWorldGenSpawning(this.world, biome, i + 8, j + 8, 16, 16, this.rand);
        	blockpos = blockpos.add(8, 0, 8);


        net.minecraftforge.event.ForgeEventFactory.onChunkPopulate(false, this, this.world, this.rand, x, z, false);

        BlockFalling.fallInstantly = true;

    }
    

    
	   /**
  * Generates a bare-bones chunk of nothing but stone or ocean blocks, formed, but featureless.
  */
 public void setBlocksInChunkIsland(World world,int x, int z, ChunkPrimer primer)
 {
     int i = 2;
     int j = 3;
     int k = 33;
     int l = 3;
     this.buffer = this.getHeights(this.buffer, x * 2, 0, z * 2, 3, 33, 3);

     for (int i1 = 0; i1 < 2; ++i1)
     {
         for (int j1 = 0; j1 < 2; ++j1)
         {
             for (int k1 = 0; k1 < 32; ++k1)
             {
                 double d0 = 0.25D;
                 double d1 = this.buffer[((i1 + 0) * 3 + j1 + 0) * 33 + k1 + 0];
                 
                 double d2 = this.buffer[((i1 + 0) * 3 + j1 + 1) * 33 + k1 + 0];
                 double d3 = this.buffer[((i1 + 1) * 3 + j1 + 0) * 33 + k1 + 0];
                 double d4 = this.buffer[((i1 + 1) * 3 + j1 + 1) * 33 + k1 + 0];
                 double d5 = (this.buffer[((i1 + 0) * 3 + j1 + 0) * 33 + k1 + 1] - d1) * 0.25D;
                 double d6 = (this.buffer[((i1 + 0) * 3 + j1 + 1) * 33 + k1 + 1] - d2) * 0.25D;
                 double d7 = (this.buffer[((i1 + 1) * 3 + j1 + 0) * 33 + k1 + 1] - d3) * 0.25D;
                 double d8 = (this.buffer[((i1 + 1) * 3 + j1 + 1) * 33 + k1 + 1] - d4) * 0.25D;

                 for (int l1 = 0; l1 < 4; ++l1)
                 {
                     double d9 = 0.125D;
                     double d10 = d1;
                     double d11 = d2;
                     double d12 = (d3 - d1) * 0.125D;
                     double d13 = (d4 - d2) * 0.125D;

                     for (int i2 = 0; i2 < 8; ++i2)
                     {
                         double d14 = 0.125D;
                         double d15 = d10;
                         double d16 = (d11 - d10) * 0.125D;

                         for (int j2 = 0; j2 < 8; ++j2)
                         {
                             IBlockState iblockstate = Blocks.AIR.getDefaultState();

                             if (d15 > 0.0D)
                             {
                                 iblockstate = KCore.MythicStone.getDefaultState();
                                 if(this.rand.nextInt(150)==0&&l1<2&&k1<13)iblockstate=KCore.Magnethium.getDefaultState();
                             }
                             int k2 = i2 + i1 * 8;
                             int l2 = l1 + k1 * 4;
                             int i3 = j2 + j1 * 8;
                             //l1=3 k1=31
                             
                        		 primer.setBlockState(k2, l2+upperScale, i3, iblockstate);
                        		 if(iblockstate != Blocks.AIR.getDefaultState()) {
                        			  this.biomesForGeneration[k2 + i3 * 16] = KCore.instance.FloatingIslands; //i am not shure about the coordinates it could be i3 + k2*16 instead
                        		}
                        		 if(l1==3&&k1==31) {
                        			 if(iblockstate!=Blocks.AIR.getDefaultState()) {
	                        			 int cc=2+rand.nextInt(3);
	                        			 for(int c=0;c<=cc;c++) {
	                                		 primer.setBlockState(k2, l2+upperScale+c, i3, KCore.CorruptedDirt.getDefaultState());
	                        			 }
	                            		 primer.setBlockState(k2, l2+upperScale+cc+1, i3, KCore.CorruptedGrass.getDefaultState());
                        			 }
                        		 }
                        			 /*if(primer.getBlockState(k2,l2+upperScale,i3)!=Blocks.AIR.getDefaultState()) {
                        				 if(primer.getBlockState(k2,l2+upperScale-1,i3)==Blocks.AIR.getDefaultState()) {
    	                            		 primer.setBlockState(k2, l2+upperScale-1, i3, KCore.JadeVine_empty.getDefaultState());
                        				 }
                        			 }*/
                             
                             d15 += d16;

                         }

                         d10 += d12;
                         d11 += d13;
                     }

                     d1 += d5;
                     d2 += d6;
                     d3 += d7;
                     d4 += d8;
                 }
             }
         }
     }
 }
 

 
 private double[] getHeights(double[] p_185963_1_, int p_185963_2_, int p_185963_3_, int p_185963_4_, int p_185963_5_, int p_185963_6_, int p_185963_7_)
 {
     net.minecraftforge.event.terraingen.ChunkGeneratorEvent.InitNoiseField event = new net.minecraftforge.event.terraingen.ChunkGeneratorEvent.InitNoiseField(this, p_185963_1_, p_185963_2_, p_185963_3_, p_185963_4_, p_185963_5_, p_185963_6_, p_185963_7_);
     net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(event);
     if (event.getResult() == net.minecraftforge.fml.common.eventhandler.Event.Result.DENY) return event.getNoisefield();
     

     if (p_185963_1_ == null)
     {
         p_185963_1_ = new double[p_185963_5_ * p_185963_6_ * p_185963_7_];
     }

     double d0 = 684.412D;
     double d1 = 684.412D;
     d0 = d0 * 2.0D;
     this.pnr = this.perlinNoise1.generateNoiseOctaves(this.pnr, p_185963_2_, p_185963_3_, p_185963_4_, p_185963_5_, p_185963_6_, p_185963_7_, d0 / 80.0D, 4.277575000000001D, d0 / 80.0D);
     this.ar = this.lperlinNoise1.generateNoiseOctaves(this.ar, p_185963_2_, p_185963_3_, p_185963_4_, p_185963_5_, p_185963_6_, p_185963_7_, d0, 684.412D, d0);
     this.br = this.lperlinNoise2.generateNoiseOctaves(this.br, p_185963_2_, p_185963_3_, p_185963_4_, p_185963_5_, p_185963_6_, p_185963_7_, d0, 684.412D, d0);
     int i = p_185963_2_ / 2;
     int j = p_185963_4_ / 2;
     int k = 0;

     for (int l = 0; l < p_185963_5_; ++l)
     {
         for (int i1 = 0; i1 < p_185963_7_; ++i1)
         {
             float f = this.getIslandHeightValue(i, j, l, i1);

             for (int j1 = 0; j1 < p_185963_6_; ++j1)
             {
                 double d2 = this.ar[k] / 512.0D;
                 double d3 = this.br[k] / 512.0D;
                 double d5 = (this.pnr[k] / 10.0D + 1.0D) / 2.0D;
                 double d4;

                 if (d5 < 0.0D)
                 {
                     d4 = d2;
                 }
                 else if (d5 > 1.0D)
                 {
                     d4 = d3;
                 }
                 else
                 {
                     d4 = d2 + (d3 - d2) * d5;
                 }

                 d4 = d4 - 8.0D;
                 d4 = d4 + (double)f;
                 int k1 = 2;

                 if (j1 > p_185963_6_ / 2 - k1)
                 {
                     double d6 = (double)((float)(j1 - (p_185963_6_ / 2 - k1)) / 64.0F);
                     d6 = MathHelper.clamp(d6, 0.0D, 1.0D);
                     d4 = d4 * (1.0D - d6) + -3000.0D * d6;
                 }

                 k1 = 8;

                 if (j1 < k1)
                 {
                     double d7 = (double)((float)(k1 - j1) / ((float)k1 - 1.0F));
                     d4 = d4 * (1.0D - d7) + -30.0D * d7;
                 }

                 p_185963_1_[k] = d4;
                 ++k;
             }
         }
     }

     return p_185963_1_;
 }
 
 private float getIslandHeightValue(int p_185960_1_, int p_185960_2_, int p_185960_3_, int p_185960_4_)
 {
     float f = (float)(p_185960_1_ * 2 + p_185960_3_);
     float f1 = (float)(p_185960_2_ * 2 + p_185960_4_);
     float f2 = 100.0F - MathHelper.sqrt(f * f + f1 * f1) * 8.0F;

     if (f2 > 80.0F)
     {
         f2 = 80.0F;
     }

     if (f2 < -100.0F)
     {
         f2 = -100.0F;
     }
     
	     for (int i = -12; i <= 12; ++i)
	     {
	         for (int j = -12; j <= 12; ++j)
	         {
	             long k = (long)(p_185960_1_ + i);
	             long l = (long)(p_185960_2_ + j);
	
	             if (k*k+l*l>1000L&&this.islandNoise.getValue((double)k, (double)l) < -0.9899999761581421D)
	             {
	                 float f3 = (MathHelper.abs((float)k) * 3439.0F + MathHelper.abs((float)l) * 147.0F) % 13.0F + 9.0F;
	                 f = (float)(p_185960_3_ - i * 2);
	                 f1 = (float)(p_185960_4_ - j * 2);
	                 float f4 = 100.0F - MathHelper.sqrt(f * f + f1 * f1) * f3;
	
	                 if (f4 > 80.0F)
	                 {
	                     f4 = 80.0F;
	                 }
	
	                 if (f4 < -100.0F)
	                 {
	                     f4 = -100.0F;
	                 }
	
	                 if (f4 > f2)
	                 {
	                     f2 = f4;
	                 }
	             }
	         }
	     }
	
	     return f2;
 	}

    public List<Biome.SpawnListEntry> getPossibleCreatures(EnumCreatureType creatureType, BlockPos pos)
    {
        Biome biome = this.world.getBiome(pos);

        return biome.getSpawnableList(creatureType);
    }

	@Override
	public boolean generateStructures(Chunk chunkIn, int x, int z) {
		return false;
	}

	@Override
	public BlockPos getNearestStructurePos(World worldIn, String structureName, BlockPos position,
			boolean findUnexplored) {
		return null;
	}

	@Override
	public void recreateStructures(Chunk chunkIn, int x, int z) {
		
	}

	@Override
	public boolean isInsideStructure(World worldIn, String structureName, BlockPos pos) {
		return false;
	}
}