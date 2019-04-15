package io.github.krevik.kathairis.init;

import io.github.krevik.kathairis.entity.*;
import io.github.krevik.kathairis.entity.butterfly.*;
import net.minecraft.entity.*;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemSpawnEgg;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.PlainsBiome;
import net.minecraft.world.gen.Heightmap;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ObjectHolder;

import java.util.ArrayList;

import static io.github.krevik.kathairis.util.ModReference.MOD_ID;
import static io.github.krevik.kathairis.util.ModUtil._null;

@ObjectHolder(MOD_ID)
public class ModEntities {

    public static final EntityType<EntityBasicButterfly> BASIC_BUTTERFLY1 = _null();
    public static final EntityType<EntityBasicButterfly> BASIC_BUTTERFLY2 = _null();
    public static final EntityType<EntityCloudShimmer> CLOUD_SHIMMER = _null();
    public static final EntityType<EntityIllukini> ILLUKINI = _null();
    public static final EntityType<EntityRubySile> RUBY_SILE = _null();
    public static final EntityType<EntitySkylight> SKYLIGHT = _null();
    public static final EntityType<EntityBigTurtle> BIG_TURTLE = _null();
    public static final EntityType<EntityBison> BISON = _null();
    public static final EntityType<EntityCactiSpore> CACTI_SPORE = _null();
    public static final EntityType<EntityCamel> CAMEL = _null();
    public static final EntityType<EntityCloudOister> CLOUD_OISTER = _null();
    public static final EntityType<EntityCloudySlime> CLOUDY_SLIME = _null();
    public static final EntityType<EntityFlyingSquid> FLYING_SQUID = _null();
    public static final EntityType<EntityFungite> FUNGITE = _null();
    public static final EntityType<EntityGaznowel> GAZNOWEL = _null();
    public static final EntityType<EntityGecko> GECKO = _null();
    public static final EntityType<EntityHowler> HOWLER = _null();
    public static final EntityType<EntityJellyFish> JELLY_FISH = _null();
    public static final EntityType<EntityLivingFlower> LIVING_FLOWER = _null();
    public static final EntityType<EntityMysticBird> MYSTIC_BIRD = _null();
    public static final EntityType<EntityPhasm> PHASM = _null();
    public static final EntityType<EntityPoisonousScorpion> POISONOUS_SCORPION = _null();
    public static final EntityType<EntitySkyray> SKYRAY = _null();
    public static final EntityType<EntityStrangeWanderer> STRANGE_WANDERER = _null();

    public static void registerPlacementType(EntityType<?> type,EntitySpawnPlacementRegistry.SpawnPlacementType spawnType){
        EntitySpawnPlacementRegistry.register(type, spawnType, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,null);
    }

    public static void registerPlacementTypes(){
        registerPlacementType(ModEntities.BASIC_BUTTERFLY1, EntitySpawnPlacementRegistry.SpawnPlacementType.ON_GROUND);
        registerPlacementType(ModEntities.BASIC_BUTTERFLY2, EntitySpawnPlacementRegistry.SpawnPlacementType.ON_GROUND);
        registerPlacementType(ModEntities.CLOUD_SHIMMER, EntitySpawnPlacementRegistry.SpawnPlacementType.ON_GROUND);
        registerPlacementType(ModEntities.ILLUKINI, EntitySpawnPlacementRegistry.SpawnPlacementType.ON_GROUND);
        registerPlacementType(ModEntities.RUBY_SILE, EntitySpawnPlacementRegistry.SpawnPlacementType.ON_GROUND);
        registerPlacementType(ModEntities.SKYLIGHT, EntitySpawnPlacementRegistry.SpawnPlacementType.ON_GROUND);
        registerPlacementType(ModEntities.BIG_TURTLE, EntitySpawnPlacementRegistry.SpawnPlacementType.ON_GROUND);
        registerPlacementType(ModEntities.BISON, EntitySpawnPlacementRegistry.SpawnPlacementType.ON_GROUND);
        registerPlacementType(ModEntities.CACTI_SPORE, EntitySpawnPlacementRegistry.SpawnPlacementType.ON_GROUND);
        registerPlacementType(ModEntities.CAMEL, EntitySpawnPlacementRegistry.SpawnPlacementType.ON_GROUND);
        registerPlacementType(ModEntities.CLOUD_OISTER, EntitySpawnPlacementRegistry.SpawnPlacementType.ON_GROUND);
        registerPlacementType(ModEntities.CLOUDY_SLIME, EntitySpawnPlacementRegistry.SpawnPlacementType.ON_GROUND);
        registerPlacementType(ModEntities.FLYING_SQUID, EntitySpawnPlacementRegistry.SpawnPlacementType.ON_GROUND);
        registerPlacementType(ModEntities.FUNGITE, EntitySpawnPlacementRegistry.SpawnPlacementType.ON_GROUND);
        registerPlacementType(ModEntities.GAZNOWEL, EntitySpawnPlacementRegistry.SpawnPlacementType.ON_GROUND);
        registerPlacementType(ModEntities.GECKO, EntitySpawnPlacementRegistry.SpawnPlacementType.ON_GROUND);
        registerPlacementType(ModEntities.HOWLER, EntitySpawnPlacementRegistry.SpawnPlacementType.ON_GROUND);
        registerPlacementType(ModEntities.JELLY_FISH, EntitySpawnPlacementRegistry.SpawnPlacementType.ON_GROUND);
        registerPlacementType(ModEntities.LIVING_FLOWER, EntitySpawnPlacementRegistry.SpawnPlacementType.ON_GROUND);
        registerPlacementType(ModEntities.MYSTIC_BIRD, EntitySpawnPlacementRegistry.SpawnPlacementType.ON_GROUND);
        registerPlacementType(ModEntities.PHASM, EntitySpawnPlacementRegistry.SpawnPlacementType.ON_GROUND);
        registerPlacementType(ModEntities.POISONOUS_SCORPION, EntitySpawnPlacementRegistry.SpawnPlacementType.ON_GROUND);
        registerPlacementType(ModEntities.SKYRAY, EntitySpawnPlacementRegistry.SpawnPlacementType.ON_GROUND);
        registerPlacementType(ModEntities.STRANGE_WANDERER, EntitySpawnPlacementRegistry.SpawnPlacementType.ON_GROUND);
    }

    private static void registerEntitySpawn(EntityType<? extends EntityLiving> type,EnumCreatureType creatureType, EntitySpawnPlacementRegistry.SpawnPlacementType spawnType,Biome[] biomes, int weight, int min, int max) {
        for (Biome b : biomes) {
            Biome biome=b;
            if (biome != null) {
                biome.getSpawns(creatureType).add(new Biome.SpawnListEntry(type, weight, min, max));
            }
        }
    }

    public static void registerEntitySpawns(){
        EntitySpawnPlacementRegistry.SpawnPlacementType ON_GROUND = EntitySpawnPlacementRegistry.SpawnPlacementType.ON_GROUND;
        registerEntitySpawn(ModEntities.MYSTIC_BIRD,EnumCreatureType.CREATURE,ON_GROUND,new Biome[]{ModBiomes.KATHARIAN_FOREST,ModBiomes.KATHARIAN_SWAMP,ModBiomes.PLAIN_FIELDS},12,1,2);
        registerEntitySpawn(ModEntities.BIG_TURTLE,EnumCreatureType.CREATURE,ON_GROUND,new Biome[]{ModBiomes.KATHARIAN_DESERT,ModBiomes.KATHARIAN_DESERT_EDGE,ModBiomes.SOFT_SAND_LAKES},12,1,1);
        registerEntitySpawn(ModEntities.POISONOUS_SCORPION,EnumCreatureType.MONSTER,ON_GROUND,new Biome[]{ModBiomes.KATHARIAN_DESERT,ModBiomes.KATHARIAN_DESERT_EDGE,ModBiomes.SOFT_SAND_LAKES},3,1,1);
        registerEntitySpawn(ModEntities.CAMEL,EnumCreatureType.CREATURE,ON_GROUND,new Biome[]{ModBiomes.KATHARIAN_DESERT,ModBiomes.KATHARIAN_DESERT_EDGE,ModBiomes.SOFT_SAND_LAKES},6,1,1);
        registerEntitySpawn(ModEntities.GECKO,EnumCreatureType.CREATURE,ON_GROUND,new Biome[]{ModBiomes.KATHARIAN_FOREST},4,1,1);
        registerEntitySpawn(ModEntities.LIVING_FLOWER,EnumCreatureType.CREATURE,ON_GROUND,new Biome[]{ModBiomes.KATHARIAN_FOREST},8,1,1);
        registerEntitySpawn(ModEntities.HOWLER,EnumCreatureType.MONSTER,ON_GROUND,new Biome[]{ModBiomes.KATHARIAN_FOREST},5,1,1);
        registerEntitySpawn(ModEntities.FUNGITE,EnumCreatureType.MONSTER,ON_GROUND,new Biome[]{ModBiomes.KATHARIAN_FOREST},2,1,1);
        //registerEntitySpawn(ModEntities.CACTI_SPORE,EnumCreatureType.CREATURE,ON_GROUND,new Biome[]{ModBiomes.KATHARIAN_FOREST},4,1,1);
        //registerEntitySpawn(ModEntities.JELLY_FISH,EnumCreatureType.CREATURE,ON_GROUND,new Biome[]{ModBiomes.PLAIN_FIELDS},10,1,2);
        //registerEntitySpawn(ModEntities.BISON,EnumCreatureType.CREATURE,ON_GROUND,new Biome[]{ModBiomes.PLAIN_FIELDS},8,2,4);
        //registerEntitySpawn(ModEntities.BASIC_BUTTERFLY1,EnumCreatureType.CREATURE,ON_GROUND,new Biome[]{ModBiomes.PLAIN_FIELDS},10,1,1);
        //registerEntitySpawn(ModEntities.BASIC_BUTTERFLY2,EnumCreatureType.CREATURE,ON_GROUND,new Biome[]{ModBiomes.PLAIN_FIELDS},10,1,1);
        //registerEntitySpawn(ModEntities.PHASM,EnumCreatureType.MONSTER,ON_GROUND,new Biome[]{ModBiomes.KATHARIAN_SWAMP},2,1,1);
    }
}
