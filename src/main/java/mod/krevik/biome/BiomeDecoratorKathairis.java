package mod.krevik.biome;

import mod.krevik.world.gen.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.gen.ChunkGeneratorSettings;

import java.util.Random;

public class BiomeDecoratorKathairis extends BiomeDecorator {
    public static final WorldGenMysticUniversal EnergyShard = new WorldGenMysticUniversal(4);
    public int cloudWeight=5;
    public static final WorldGenCrystalChamber CrystalChamber = new WorldGenCrystalChamber();
    public static final WorldGenCloudTemple CloudTemple = new WorldGenCloudTemple();
    public static final WorldGenFloatingIslands FloatingIsland = new WorldGenFloatingIslands(1);
    @Override
    public void decorate(World worldIn, Random random, Biome biome, BlockPos pos)
    {
        if (this.decorating)
        {
            throw new RuntimeException("Already decorating");
        }
        else
        {
            this.chunkProviderSettings = ChunkGeneratorSettings.Factory.jsonToFactory(worldIn.getWorldInfo().getGeneratorOptions()).build();
            this.chunkPos = pos;
            this.genDecorations(biome, worldIn, random);
            this.decorating = false;
        }
    }

    @Override
    protected void genDecorations(Biome biomeIn, World worldIn, Random random)
    {
        net.minecraft.util.math.ChunkPos forgeChunkPos = new net.minecraft.util.math.ChunkPos(chunkPos); // actual ChunkPos instead of BlockPos, used for events
        net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(new net.minecraftforge.event.terraingen.DecorateBiomeEvent.Pre(worldIn, random, forgeChunkPos));

        int k1 = this.treesPerChunk;

        if (random.nextFloat() < this.extraTreeChance)
        {
            ++k1;
        }

        if(net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, random, forgeChunkPos, net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.TREE))
        for(int c=0;c<treesPerChunk;c++){
            int X = random.nextInt(16) + 8;
            int Z = random.nextInt(16) + 8;
            biomeIn.getRandomTreeFeature(random).generate(worldIn, random, worldIn.getHeight(chunkPos.add(X,0,Z)));
        }

        if(net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, random, forgeChunkPos, net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.GRASS))
        for (int i3 = 0; i3 < grassPerChunk; ++i3)
        {
            int X = random.nextInt(16) + 8;
            int Z = random.nextInt(16) + 8;
            biomeIn.getRandomWorldGenForGrass(random).generate(worldIn, random, worldIn.getHeight(chunkPos.add(X,0,Z)));
        }

        if(random.nextInt(100)==0) {
            int X = random.nextInt(16) + 8;
            int Z = random.nextInt(16) + 8;
            int j3=100+random.nextInt(25)+random.nextInt(25)+random.nextInt(25)+random.nextInt(25);
            new WorldGenFloatingTree().generate2(worldIn, random, chunkPos.add(X,j3,Z));
        }

        if(random.nextInt(cloudWeight)==0) {
            int X = random.nextInt(16) + 8;
            int Z = random.nextInt(16) + 8;
            int j3=85+random.nextInt(25)+random.nextInt(25)+random.nextInt(25)+random.nextInt(25);
            if(j3>0){
                new WorldGenClouds().generate(worldIn, random, chunkPos.add(X,j3,Z));
            }
        }
        if(random.nextInt(30)==0)
        {
            int X = random.nextInt(16) + 8;
            int Z = random.nextInt(16) + 8;
            BlockPos tmp = chunkPos.add(X,0,Z);
            int TOP = worldIn.getHeight(tmp).getY();
            BlockPos blockposnew = new BlockPos(X,TOP,Z);
            EnergyShard.generate(worldIn, random, chunkPos.add(X,TOP,Z));
        }

        if(random.nextInt(15)==1) {
            int X = random.nextInt(16) + 8;
            int Z = random.nextInt(16) + 8;
            int j3=random.nextInt(45)+12;
                BlockPos blockposnew = new BlockPos(X,j3,Z);
                CrystalChamber.generate(worldIn, random, chunkPos.add(X,j3,Z));
        }
        /*if(random.nextInt(100)==1) {
            int X = random.nextInt(16) + 8;
            int Z = random.nextInt(16) + 8;
            int j3=random.nextInt(45)+12;
            BlockPos blockposnew = new BlockPos(X,j3,Z);
            new WorldGenCrystalMaze().generate(worldIn, random, chunkPos.add(X,j3,Z));
        }*/
        if(random.nextInt(1200)==0) {
            int X = random.nextInt(16) + 8;
            int Z = random.nextInt(16) + 8;
            int j3=90+random.nextInt(45)+random.nextInt(45)+random.nextInt(45)+random.nextInt(45);
            if(j3>100&&j3<220){
                CloudTemple.generate(worldIn, random, chunkPos.add(X,j3,Z));
            }
        }
        net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(new net.minecraftforge.event.terraingen.DecorateBiomeEvent.Post(worldIn, random, forgeChunkPos));
    }

}
