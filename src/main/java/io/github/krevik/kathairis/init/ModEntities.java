package io.github.krevik.kathairis.init;

import io.github.krevik.kathairis.entity.*;
import io.github.krevik.kathairis.entity.butterfly.*;
import net.minecraft.entity.*;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemSpawnEgg;
import net.minecraft.world.biome.Biome;
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
        EntitySpawnPlacementRegistry.register(type, spawnType, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, null);
    }

    public static void registerPlacementTypes(){
        registerPlacementType(EntityType.getById(ModEntities.BASIC_BUTTERFLY1.getRegistryName().toString()), EntitySpawnPlacementRegistry.SpawnPlacementType.ON_GROUND);
        registerPlacementType(EntityType.getById(ModEntities.BASIC_BUTTERFLY2.getRegistryName().toString()), EntitySpawnPlacementRegistry.SpawnPlacementType.ON_GROUND);
        registerPlacementType(EntityType.getById(ModEntities.CLOUD_SHIMMER.getRegistryName().toString()), EntitySpawnPlacementRegistry.SpawnPlacementType.ON_GROUND);
        registerPlacementType(EntityType.getById(ModEntities.ILLUKINI.getRegistryName().toString()), EntitySpawnPlacementRegistry.SpawnPlacementType.ON_GROUND);
        registerPlacementType(EntityType.getById(ModEntities.RUBY_SILE.getRegistryName().toString()), EntitySpawnPlacementRegistry.SpawnPlacementType.ON_GROUND);
        registerPlacementType(EntityType.getById(ModEntities.SKYLIGHT.getRegistryName().toString()), EntitySpawnPlacementRegistry.SpawnPlacementType.ON_GROUND);
        registerPlacementType(EntityType.getById(ModEntities.BIG_TURTLE.getRegistryName().toString()), EntitySpawnPlacementRegistry.SpawnPlacementType.ON_GROUND);
        registerPlacementType(EntityType.getById(ModEntities.BISON.getRegistryName().toString()), EntitySpawnPlacementRegistry.SpawnPlacementType.ON_GROUND);
        registerPlacementType(EntityType.getById(ModEntities.CACTI_SPORE.getRegistryName().toString()), EntitySpawnPlacementRegistry.SpawnPlacementType.ON_GROUND);
        registerPlacementType(EntityType.getById(ModEntities.CAMEL.getRegistryName().toString()), EntitySpawnPlacementRegistry.SpawnPlacementType.ON_GROUND);
        registerPlacementType(EntityType.getById(ModEntities.CLOUD_OISTER.getRegistryName().toString()), EntitySpawnPlacementRegistry.SpawnPlacementType.ON_GROUND);
        registerPlacementType(EntityType.getById(ModEntities.CLOUDY_SLIME.getRegistryName().toString()), EntitySpawnPlacementRegistry.SpawnPlacementType.ON_GROUND);
        registerPlacementType(EntityType.getById(ModEntities.FLYING_SQUID.getRegistryName().toString()), EntitySpawnPlacementRegistry.SpawnPlacementType.ON_GROUND);
        registerPlacementType(EntityType.getById(ModEntities.FUNGITE.getRegistryName().toString()), EntitySpawnPlacementRegistry.SpawnPlacementType.ON_GROUND);
        registerPlacementType(EntityType.getById(ModEntities.GAZNOWEL.getRegistryName().toString()), EntitySpawnPlacementRegistry.SpawnPlacementType.ON_GROUND);
        registerPlacementType(EntityType.getById(ModEntities.GECKO.getRegistryName().toString()), EntitySpawnPlacementRegistry.SpawnPlacementType.ON_GROUND);
        registerPlacementType(EntityType.getById(ModEntities.HOWLER.getRegistryName().toString()), EntitySpawnPlacementRegistry.SpawnPlacementType.ON_GROUND);
        registerPlacementType(EntityType.getById(ModEntities.JELLY_FISH.getRegistryName().toString()), EntitySpawnPlacementRegistry.SpawnPlacementType.ON_GROUND);
        registerPlacementType(EntityType.getById(ModEntities.LIVING_FLOWER.getRegistryName().toString()), EntitySpawnPlacementRegistry.SpawnPlacementType.ON_GROUND);
        registerPlacementType(EntityType.getById(ModEntities.MYSTIC_BIRD.getRegistryName().toString()), EntitySpawnPlacementRegistry.SpawnPlacementType.ON_GROUND);
        registerPlacementType(EntityType.getById(ModEntities.PHASM.getRegistryName().toString()), EntitySpawnPlacementRegistry.SpawnPlacementType.ON_GROUND);
        registerPlacementType(EntityType.getById(ModEntities.POISONOUS_SCORPION.getRegistryName().toString()), EntitySpawnPlacementRegistry.SpawnPlacementType.ON_GROUND);
        registerPlacementType(EntityType.getById(ModEntities.SKYRAY.getRegistryName().toString()), EntitySpawnPlacementRegistry.SpawnPlacementType.ON_GROUND);
        registerPlacementType(EntityType.getById(ModEntities.STRANGE_WANDERER.getRegistryName().toString()), EntitySpawnPlacementRegistry.SpawnPlacementType.ON_GROUND);

    }

    private static void registerEntitySpawn(EntityType<? extends EntityLiving> type,EnumCreatureType creatureType, EntitySpawnPlacementRegistry.SpawnPlacementType spawnType,Biome[] biomes, int weight, int min, int max) {
        for (Biome b : biomes) {
            Biome biome = ForgeRegistries.BIOMES.getValue(b.getRegistryName());
            if (biome != null) {
                biome.getSpawns(creatureType).add(new Biome.SpawnListEntry(type, weight, min, max));
            }
        }
    }

    public static void registerEntitySpawns(){
        EntitySpawnPlacementRegistry.SpawnPlacementType ON_GROUND = EntitySpawnPlacementRegistry.SpawnPlacementType.ON_GROUND;
        registerEntitySpawn((EntityType<? extends EntityLiving>) EntityType.getById(ModEntities.MYSTIC_BIRD.getRegistryName().toString()),EnumCreatureType.CREATURE,ON_GROUND,new Biome[]{ModBiomes.KATHARIAN_FOREST,ModBiomes.KATHARIAN_SWAMP,ModBiomes.PLAIN_FIELDS},12,1,2);
        registerEntitySpawn((EntityType<? extends EntityLiving>) EntityType.getById(ModEntities.BIG_TURTLE.getRegistryName().toString()),EnumCreatureType.CREATURE,ON_GROUND,new Biome[]{ModBiomes.KATHARIAN_DESERT,ModBiomes.KATHARIAN_DESERT_EDGE,ModBiomes.SOFT_SAND_LAKES},12,1,1);
        registerEntitySpawn((EntityType<? extends EntityLiving>) EntityType.getById(ModEntities.POISONOUS_SCORPION.getRegistryName().toString()),EnumCreatureType.MONSTER,ON_GROUND,new Biome[]{ModBiomes.KATHARIAN_DESERT,ModBiomes.KATHARIAN_DESERT_EDGE,ModBiomes.SOFT_SAND_LAKES},3,1,1);
        registerEntitySpawn((EntityType<? extends EntityLiving>) EntityType.getById(ModEntities.CAMEL.getRegistryName().toString()),EnumCreatureType.CREATURE,ON_GROUND,new Biome[]{ModBiomes.KATHARIAN_DESERT,ModBiomes.KATHARIAN_DESERT_EDGE,ModBiomes.SOFT_SAND_LAKES},6,1,1);
        registerEntitySpawn((EntityType<? extends EntityLiving>) EntityType.getById(ModEntities.GECKO.getRegistryName().toString()),EnumCreatureType.CREATURE,ON_GROUND,new Biome[]{ModBiomes.KATHARIAN_FOREST},4,1,1);
        registerEntitySpawn((EntityType<? extends EntityLiving>) EntityType.getById(ModEntities.LIVING_FLOWER.getRegistryName().toString()),EnumCreatureType.CREATURE,ON_GROUND,new Biome[]{ModBiomes.KATHARIAN_FOREST},8,1,1);
        registerEntitySpawn((EntityType<? extends EntityLiving>) EntityType.getById(ModEntities.HOWLER.getRegistryName().toString()),EnumCreatureType.MONSTER,ON_GROUND,new Biome[]{ModBiomes.KATHARIAN_FOREST},5,1,1);
        registerEntitySpawn((EntityType<? extends EntityLiving>) EntityType.getById(ModEntities.FUNGITE.getRegistryName().toString()),EnumCreatureType.MONSTER,ON_GROUND,new Biome[]{ModBiomes.KATHARIAN_FOREST},2,1,1);
        registerEntitySpawn((EntityType<? extends EntityLiving>) EntityType.getById(ModEntities.CACTI_SPORE.getRegistryName().toString()),EnumCreatureType.CREATURE,ON_GROUND,new Biome[]{ModBiomes.KATHARIAN_FOREST},4,1,1);
        registerEntitySpawn((EntityType<? extends EntityLiving>) EntityType.getById(ModEntities.JELLY_FISH.getRegistryName().toString()),EnumCreatureType.CREATURE,ON_GROUND,new Biome[]{ModBiomes.PLAIN_FIELDS},10,1,2);
        registerEntitySpawn((EntityType<? extends EntityLiving>) EntityType.getById(ModEntities.BISON.getRegistryName().toString()),EnumCreatureType.CREATURE,ON_GROUND,new Biome[]{ModBiomes.PLAIN_FIELDS},8,2,4);
        registerEntitySpawn((EntityType<? extends EntityLiving>) EntityType.getById(ModEntities.BASIC_BUTTERFLY1.getRegistryName().toString()),EnumCreatureType.CREATURE,ON_GROUND,new Biome[]{ModBiomes.PLAIN_FIELDS},10,1,1);
        registerEntitySpawn((EntityType<? extends EntityLiving>) EntityType.getById(ModEntities.BASIC_BUTTERFLY2.getRegistryName().toString()),EnumCreatureType.CREATURE,ON_GROUND,new Biome[]{ModBiomes.PLAIN_FIELDS},10,1,1);
        registerEntitySpawn((EntityType<? extends EntityLiving>) EntityType.getById(ModEntities.PHASM.getRegistryName().toString()),EnumCreatureType.MONSTER,ON_GROUND,new Biome[]{ModBiomes.KATHARIAN_SWAMP},2,1,1);
    }
}
