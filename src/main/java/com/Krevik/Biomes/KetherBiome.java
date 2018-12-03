package com.Krevik.Biomes;

import java.awt.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import com.Krevik.Blocks.BaseBlock;
import com.Krevik.Entities.Butterflies.EntityIllukini;
import com.Krevik.Entities.Butterflies.EntityRubySile;
import com.Krevik.Entities.Butterflies.EntitySkylight;
import com.Krevik.Gens.StructureLoader.StructureLoader;
import com.Krevik.Gens.WorldGenMysticOre;
import com.Krevik.Main.KCore;

import com.google.common.collect.Lists;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.ColorizerGrass;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class KetherBiome extends Biome
{
    public Color baseGrassColor;
    private static final int TitaniumPerChunk = 9;
    private static final int RevenumPerChunk = 15;
    private static StructureLoader structureLoader = new StructureLoader();

    public KetherBiome(Biome.BiomeProperties properties)
    {
        super(properties);
        //this.decorator=createBiomeDecorator();
        this.spawnableCreatureList.clear();
        this.spawnableCaveCreatureList.clear();
        this.spawnableMonsterList.clear();
        this.spawnableWaterCreatureList.clear();
    	KCore.instance.cproxy.biomeList.add(this);
        this.spawnableCaveCreatureList.add(new Biome.SpawnListEntry(EntityIllukini.class, 2, 1, 1));
        this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntityRubySile.class, 8, 1, 2));
        this.spawnableMonsterList.add(new Biome.SpawnListEntry(EntitySkylight.class, 8, 1, 2));
        baseGrassColor=new Color(29,242,228);
    }

    public static StructureLoader getStructureLoader(){
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
        ArrayList<BlockPos> toCalculate = new ArrayList<BlockPos>();
        int radiusCalculation=4;
        for(int x=-radiusCalculation;x<=radiusCalculation;x++){
                for(int z=-radiusCalculation;z<=radiusCalculation;z++){
                    toCalculate.add(new BlockPos(pos.getX()+x,pos.getY(),pos.getZ()+z));
                }
        }

        int sumR=0;int sumG=0;int sumB=0;
        int count=0;
        for(BlockPos positionForAverage:toCalculate){
            if(world.getBiome(positionForAverage) instanceof KetherBiome){
                KetherBiome biome = (KetherBiome) world.getBiome(positionForAverage);
                count++;
                sumR+=biome.baseGrassColor.getRed();
                sumG+=biome.baseGrassColor.getGreen();
                sumB+=biome.baseGrassColor.getBlue();
            }
        }
        Color averageColor = new Color(sumR/count,sumG/count,sumB/count);


        return Integer.parseInt(convertColorToHexadeimal(averageColor),16);
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
        for(int x=0;x<=4+rand.nextInt(4);x++){
        	WorldGenMysticOre normalStone = new WorldGenMysticOre(Blocks.STONE.getDefaultState(),2+rand.nextInt(16));
			int rx = pos.getX() + rand.nextInt(16) + 8;
			int rz = pos.getZ() + rand.nextInt(16) + 8;
			int ry = 5 + rand.nextInt(128);
			normalStone.generate(worldIn, rand, new BlockPos(rx,ry,rz));
        }

        for(int c=0;c<this.TitaniumPerChunk;c++){
            int rx = pos.getX() + rand.nextInt(16) + 8;
            int rz = pos.getZ() + rand.nextInt(16) + 8;
            int ry = 5 + rand.nextInt(80);
                BlockPos blockposnew = new BlockPos(rx,ry,rz);
                new WorldGenMysticOre(KCore.TitaniumOre.getDefaultState(), 2+rand.nextInt(6)).generate(worldIn, rand, blockposnew);
        }
        for(int c=0;c<this.RevenumPerChunk;c++){
            int rx = pos.getX() + rand.nextInt(16) + 8;
            int rz = pos.getZ() + rand.nextInt(16) + 8;
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