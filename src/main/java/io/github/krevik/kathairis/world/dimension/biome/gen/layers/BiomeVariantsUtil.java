package io.github.krevik.kathairis.world.dimension.biome.gen.layers;

import io.github.krevik.kathairis.init.ModBiomes;
import net.minecraft.world.biome.Biome;

import java.util.ArrayList;

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
        if(biome==ModBiomes.KATHARIAN_FOREST){
            return ModBiomes.KATHARIAN_DENSE_FOREST;
        }else if(biome==ModBiomes.KATHARIAN_DESERT) {
            return ModBiomes.SOFT_SAND_LAKES;
        }else{
            return biome;
        }

    }
}
