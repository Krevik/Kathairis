package io.github.krevik.kathairis.world.dimension.biome.gen.layers;

import io.github.krevik.kathairis.init.ModBiomes;
import net.minecraft.world.biome.Biome;

import java.util.ArrayList;
import java.util.Random;

/**
 * @author Krevik
 */
public class BiomeVariantsUtil {

    private ArrayList<Biome> biomesWithVariantsList=new ArrayList<>();

    public BiomeVariantsUtil(){
        biomesWithVariantsList.add(ModBiomes.KATHARIAN_DESERT);
        biomesWithVariantsList.add(ModBiomes.KATHARIAN_FOREST);
    }

    public ArrayList<Biome> getBiomesWithVariantsList(){
        return biomesWithVariantsList;
    }

    public Biome getBiomeVariant(Biome biome){
        Random random = new Random();
        if(biome==ModBiomes.KATHARIAN_FOREST){
            return ModBiomes.KATHARIAN_DENSE_FOREST;
        }else if(biome==ModBiomes.KATHARIAN_DESERT) {
            if(random.nextInt(5)==0){
                return ModBiomes.HUGE_DESERT_MOUNTAINS;
            }
            return ModBiomes.SOFT_SAND_LAKES;
        }else{
            return biome;
        }

    }
}
