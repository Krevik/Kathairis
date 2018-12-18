package mod.krevik.Dimension;

import java.util.List;
import java.util.Random;

import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome.SpawnListEntry;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.NoiseGeneratorOctaves;
import net.minecraft.world.gen.NoiseGeneratorPerlin;

public class ChunkGeneratorTest implements IChunkGenerator{
	private static final double zScale = 684.41200000000003D;
	private static final int xSize = 16;
	private static final int ySize = 256;
	private static final double yScale = 684.41200000000003D;
	private static final double noiseScale = 200;
	private static final double xScale = 684.41200000000003D;
	private static final int zSize = 16;
	private World world;
    private Random random=new Random();
    private Long Seed = random.nextLong();
    private double[] tempNoise = new double[65536];
    
    NoiseGeneratorPerlin mainNoise = new NoiseGeneratorPerlin(new Random(Seed),16);
    NoiseGeneratorOctaves improvedNoise = new NoiseGeneratorOctaves(new Random(Seed), 16);

    public ChunkGeneratorTest(World worldIn, long seed)
    {
        this.random = new Random(seed);
        this.world = worldIn;
        this.Seed=seed;
    }

    
    
	@Override
	public Chunk generateChunk(int x, int z) {
        ChunkPrimer chunkprimer = new ChunkPrimer();
        this.setBlocksInChunk(x, z, chunkprimer);
		
        Chunk chunk = new Chunk(this.world, chunkprimer, x, z);
        chunk.generateSkylightMap();

		return chunk;
	}
	
    public void setBlocksInChunk(int realX, int realZ, ChunkPrimer primer)
    {
    	
    }

	@Override
	public void populate(int x, int z) {
		
	}

	@Override
	public boolean generateStructures(Chunk chunkIn, int x, int z) {
		return false;
	}

	@Override
	public List<SpawnListEntry> getPossibleCreatures(EnumCreatureType creatureType, BlockPos pos) {
		return null;
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
