package mod.krevik.biome;

import com.google.common.collect.Lists;
import mod.krevik.EventSubscriber;
import mod.krevik.KCore;
import mod.krevik.entity.butterfly.EntityIllukini;
import mod.krevik.entity.butterfly.EntityRubySile;
import mod.krevik.entity.butterfly.EntitySkylight;
import mod.krevik.world.gen.WorldGenMysticOre;
import mod.krevik.world.gen.WorldGenMysticUniversal;
import mod.krevik.world.gen.structureloader.StructureLoader;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class KetherBiome extends Biome
{
    public Color baseGrassColor;
    private static final int TitaniumPerChunk = 9;
    private static final int RevenumPerChunk = 15;
    private StructureLoader structureLoader = new StructureLoader();
    private WorldGenMysticUniversal SNOWDROP = new WorldGenMysticUniversal(6);

    public KetherBiome(Biome.BiomeProperties properties)
    {
        super(properties);
        //this.decorator=createBiomeDecorator();
        this.spawnableCreatureList.clear();
        this.spawnableCaveCreatureList.clear();
        this.spawnableMonsterList.clear();
        this.spawnableWaterCreatureList.clear();
    	EventSubscriber.biomeList.add(this);
        this.spawnableCaveCreatureList.add(new Biome.SpawnListEntry(EntityIllukini.class, 2, 1, 1));
        this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntityRubySile.class, 8, 1, 2));
        this.spawnableMonsterList.add(new Biome.SpawnListEntry(EntitySkylight.class, 8, 1, 2));
        baseGrassColor=new Color(29,242,228);
        this.decorator=createBiomeDecorator();
    }

    public BiomeDecorator createBiomeDecorator()
    {
        return getModdedBiomeDecorator(new BiomeDecoratorKathairis());
    }


    public StructureLoader getStructureLoader(){
        return structureLoader;
    }

    public void setBaseGrassColor(Color color){
        baseGrassColor=color;
    }

    private static final int MONSTER_SPAWN_WEIGHT = 18;

    @Override
    public List<SpawnListEntry> getSpawnableList(EnumCreatureType creatureType) {
        if (creatureType == EnumCreatureType.MONSTER) {
            return KCore.functionHelper.random.nextInt(MONSTER_SPAWN_WEIGHT) == 0 ? this.spawnableMonsterList : Lists.newArrayList();
        }
        return super.getSpawnableList(creatureType);
    }


    @SideOnly(Side.CLIENT)
    public int getGrassColorAtPos(BlockPos pos)
    {
        World world = Minecraft.getMinecraft().world;
        if(world.getBiome(pos) instanceof KetherBiome) {
            ArrayList<BlockPos> toCalculate = new ArrayList<BlockPos>();
            int radiusCalculation = 4;
            for (int x = -radiusCalculation; x <= radiusCalculation; x++) {
                for (int z = -radiusCalculation; z <= radiusCalculation; z++) {
                    toCalculate.add(new BlockPos(pos.getX() + x, pos.getY(), pos.getZ() + z));
                }
            }

            int sumR = 0;
            int sumG = 0;
            int sumB = 0;
            int count = 0;
            for (BlockPos positionForAverage : toCalculate) {
                if(world.getBiome(positionForAverage) instanceof KetherBiome) {
                    KetherBiome biome = (KetherBiome) world.getBiome(positionForAverage);
                    count++;
                    sumR += biome.baseGrassColor.getRed();
                    sumG += biome.baseGrassColor.getGreen();
                    sumB += biome.baseGrassColor.getBlue();
                }
            }
            Color averageColor = new Color(sumR / count, sumG / count, sumB / count);


            return Integer.parseInt(convertColorToHexadeimal(averageColor), 16);
        }
        else{
            return super.getGrassColorAtPos(pos);
        }
    }

    public static String convertColorToHexadeimal(Color color)
    {
        String hex = Integer.toHexString(color.getRGB() & 0xffffff);
        if(hex.length() < 6)
        {
            if(hex.length()==5)
                hex = "0" + hex;
            if(hex.length()==4)
                hex = "00" + hex;
            if(hex.length()==3)
                hex = "000" + hex;
        }
        hex = hex;
        return hex;
    }



    @SideOnly(Side.CLIENT)
    public int getSkyColorByTemp(float currentTemperature)
    {
        currentTemperature = currentTemperature / 3.0F;
        currentTemperature = MathHelper.clamp(currentTemperature, -1.0F, 1.0F);
        return MathHelper.hsvToRGB(0.62222224F - currentTemperature * 0.05F, 0.5F + currentTemperature * 0.1F, 1.0F);
    }


    public void decorate(World worldIn, Random rand, BlockPos pos)
    {
        super.decorate(worldIn,rand,pos);
        for(int x=0;x<=4+rand.nextInt(4);x++){
        	WorldGenMysticOre normalStone = new WorldGenMysticOre(Blocks.STONE.getDefaultState(),2+rand.nextInt(16));
			int rx = pos.getX() + rand.nextInt(16);
			int rz = pos.getZ() + rand.nextInt(16);
			int ry = 5 + rand.nextInt(128);
			normalStone.generate(worldIn, rand, new BlockPos(rx,ry,rz));
        }

        for(int x=0;x<=2+rand.nextInt(2);x++){
            int rx = pos.getX() + rand.nextInt(16);
            int rz = pos.getZ() + rand.nextInt(16);
            int ry = 5 + rand.nextInt(128);
            SNOWDROP.generate(worldIn, rand, new BlockPos(rx,ry,rz));
        }

        for(int c = 0; c< TitaniumPerChunk; c++){
            int rx = pos.getX() + rand.nextInt(16);
            int rz = pos.getZ() + rand.nextInt(16);
            int ry = 5 + rand.nextInt(80);
                BlockPos blockposnew = new BlockPos(rx,ry,rz);
                new WorldGenMysticOre(KCore.TitaniumOre.getDefaultState(), 2+rand.nextInt(6)).generate(worldIn, rand, blockposnew);
        }
        for(int c = 0; c< RevenumPerChunk; c++){
            int rx = pos.getX() + rand.nextInt(16);
            int rz = pos.getZ() + rand.nextInt(16);
            int ry = 5 + rand.nextInt(128);
                BlockPos blockposnew = new BlockPos(rx,ry,rz);
                new WorldGenMysticOre(KCore.RevenumOre.getDefaultState(), 4+rand.nextInt(10)).generate(worldIn, rand, blockposnew);
        }

    }

    @SideOnly(Side.CLIENT)
    public int getFoliageColorAtPos(BlockPos pos)
    {
        double d0 = (double)MathHelper.clamp(this.getTemperature(pos), 0.0F, 1.0F);
        double d1 = (double)MathHelper.clamp(this.getRainfall(), 0.0F, 1.0F);
        return getModdedBiomeFoliageColor(ColorizerFoliage.getFoliageColor(d0, d1));
    }

    /*public BiomeDecorator getModdedBiomeDecorator(BiomeDecorator original)
    {
        return new net.minecraftforge.event.terraingen.DeferredBiomeDecorator(original);
    }*/

    public int getModdedBiomeGrassColor(int original)
    {
        net.minecraftforge.event.terraingen.BiomeEvent.GetGrassColor event = new net.minecraftforge.event.terraingen.BiomeEvent.GetGrassColor(this, original);
        net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(event);
        return event.getNewColor();
    }

    public int getModdedBiomeFoliageColor(int original)
    {
        net.minecraftforge.event.terraingen.BiomeEvent.GetFoliageColor event = new net.minecraftforge.event.terraingen.BiomeEvent.GetFoliageColor(this, original);
        net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(event);
        return event.getNewColor();
    }
}