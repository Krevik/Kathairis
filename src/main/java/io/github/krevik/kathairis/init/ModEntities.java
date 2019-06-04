package io.github.krevik.kathairis.init;

import io.github.krevik.kathairis.world.dimension.biome.biomes.BiomeKatharianBiomeBase;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemSpawnEgg;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.Heightmap;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.registries.ObjectHolder;

import static io.github.krevik.kathairis.util.ModReference.MOD_ID;
import static io.github.krevik.kathairis.util.ModUtil._null;

@ObjectHolder(MOD_ID)
public class ModEntities {

    public static EntityType<?> COMMON_BUTTERFLY1 = _null();
    public static EntityType<?> COMMON_BUTTERFLY2 = _null();
    public static EntityType<?> CLOUD_SHIMMER = _null();
    public static EntityType<?> ILLUKINI = _null();
    public static EntityType<?> RUBY_SILE = _null();
    public static EntityType<?> SKYLIGHT = _null();
    public static EntityType<?> BIG_TURTLE = _null();
    public static EntityType<?> BISON = _null();
    public static EntityType<?> CACTI_SPORE = _null();
    public static EntityType<?> CAMEL = _null();
    public static EntityType<?> CLOUD_OISTER = _null();
    public static EntityType<?> CLOUDY_SLIME = _null();
    public static EntityType<?> FLYING_SQUID = _null();
    public static EntityType<?> FUNGITE = _null();
    public static EntityType<?> GAZNOWEL = _null();
    public static EntityType<?> GECKO = _null();
    public static EntityType<?> HOWLER = _null();
    public static EntityType<?> JELLY_FISH = _null();
    public static EntityType<?> LIVING_FLOWER = _null();
    public static EntityType<?> MYSTIC_BIRD = _null();
    public static EntityType<?> PHASM = _null();
    public static EntityType<?> POISONOUS_SCORPION = _null();
    public static EntityType<?> SKYRAY = _null();
    public static EntityType<?> STRANGE_WANDERER = _null();
    public static EntityType<?> MYSTIC_WAND_SHOOT = _null();

    public static void registerPlacementType(EntityType<?> type,EntitySpawnPlacementRegistry.SpawnPlacementType spawnType){
        EntitySpawnPlacementRegistry.register(type, spawnType, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,null);
    }

    public static void registerPlacementType(EntityType<?> type,EntitySpawnPlacementRegistry.SpawnPlacementType spawnType,Heightmap.Type heightType){
        EntitySpawnPlacementRegistry.register(type, spawnType, heightType,null);
    }

    public static void registerPlacementTypes(){
        registerPlacementType(COMMON_BUTTERFLY1, EntitySpawnPlacementRegistry.SpawnPlacementType.ON_GROUND);
        registerPlacementType(COMMON_BUTTERFLY2, EntitySpawnPlacementRegistry.SpawnPlacementType.ON_GROUND);
        registerPlacementType(CLOUD_SHIMMER, EntitySpawnPlacementRegistry.SpawnPlacementType.ON_GROUND);
        registerPlacementType(ILLUKINI, EntitySpawnPlacementRegistry.SpawnPlacementType.ON_GROUND);
        registerPlacementType(RUBY_SILE, EntitySpawnPlacementRegistry.SpawnPlacementType.ON_GROUND);
        registerPlacementType(SKYLIGHT, EntitySpawnPlacementRegistry.SpawnPlacementType.ON_GROUND);
        registerPlacementType(BIG_TURTLE, EntitySpawnPlacementRegistry.SpawnPlacementType.ON_GROUND);
        registerPlacementType(BISON, EntitySpawnPlacementRegistry.SpawnPlacementType.ON_GROUND);
        registerPlacementType(CACTI_SPORE, EntitySpawnPlacementRegistry.SpawnPlacementType.ON_GROUND);
        registerPlacementType(CAMEL, EntitySpawnPlacementRegistry.SpawnPlacementType.ON_GROUND);
        registerPlacementType(CLOUD_OISTER, EntitySpawnPlacementRegistry.SpawnPlacementType.ON_GROUND);
        registerPlacementType(CLOUDY_SLIME, EntitySpawnPlacementRegistry.SpawnPlacementType.ON_GROUND);
        registerPlacementType(FLYING_SQUID, EntitySpawnPlacementRegistry.SpawnPlacementType.ON_GROUND);
        registerPlacementType(FUNGITE, EntitySpawnPlacementRegistry.SpawnPlacementType.ON_GROUND);
        registerPlacementType(GAZNOWEL, EntitySpawnPlacementRegistry.SpawnPlacementType.ON_GROUND);
        registerPlacementType(GECKO, EntitySpawnPlacementRegistry.SpawnPlacementType.ON_GROUND);
        registerPlacementType(HOWLER, EntitySpawnPlacementRegistry.SpawnPlacementType.ON_GROUND);
        registerPlacementType(JELLY_FISH, EntitySpawnPlacementRegistry.SpawnPlacementType.ON_GROUND);
        registerPlacementType(LIVING_FLOWER, EntitySpawnPlacementRegistry.SpawnPlacementType.ON_GROUND);
        registerPlacementType(MYSTIC_BIRD, EntitySpawnPlacementRegistry.SpawnPlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING);
        registerPlacementType(PHASM, EntitySpawnPlacementRegistry.SpawnPlacementType.ON_GROUND);
        registerPlacementType(POISONOUS_SCORPION, EntitySpawnPlacementRegistry.SpawnPlacementType.ON_GROUND);
        registerPlacementType(SKYRAY, EntitySpawnPlacementRegistry.SpawnPlacementType.ON_GROUND);
        registerPlacementType(STRANGE_WANDERER, EntitySpawnPlacementRegistry.SpawnPlacementType.ON_GROUND);

    }

    private static void registerEntitySpawn(EntityType<? extends EntityLiving> type,EnumCreatureType creatureType,Biome[] biomes, int weight, int min, int max) {
        for (Biome biome : biomes) {
            if (biome != null) {
                if(biome instanceof BiomeKatharianBiomeBase){
                    ((BiomeKatharianBiomeBase) biome).addSpawn(creatureType,new Biome.SpawnListEntry( type, weight, min, max));
                }
            }
        }
    }



    public static void registerEntitySpawns(){
        registerEntitySpawn((EntityType<? extends EntityLiving>)BIG_TURTLE,EnumCreatureType.CREATURE,new Biome[]{ModBiomes.KATHARIAN_DESERT,ModBiomes.KATHARIAN_DESERT_EDGE},12,1,1);
        registerEntitySpawn((EntityType<? extends EntityLiving>)MYSTIC_BIRD,EnumCreatureType.CREATURE,new Biome[]{ModBiomes.KATHARIAN_FOREST,ModBiomes.KATHARIAN_SWAMP,ModBiomes.KATHARIAN_DENSE_FOREST,ModBiomes.PLAIN_FIELDS},12,1,3);
        registerEntitySpawn((EntityType<? extends EntityLiving>)POISONOUS_SCORPION,EnumCreatureType.MONSTER,new Biome[]{ModBiomes.KATHARIAN_DESERT,ModBiomes.KATHARIAN_DESERT_EDGE},3,1,1);
        registerEntitySpawn((EntityType<? extends EntityLiving>)CAMEL,EnumCreatureType.CREATURE,new Biome[]{ModBiomes.KATHARIAN_DESERT,ModBiomes.KATHARIAN_DESERT_EDGE},6,1,1);
        registerEntitySpawn((EntityType<? extends EntityLiving>)GECKO,EnumCreatureType.CREATURE,new Biome[]{ModBiomes.KATHARIAN_FOREST,ModBiomes.KATHARIAN_DENSE_FOREST},4,1,1);
        registerEntitySpawn((EntityType<? extends EntityLiving>)LIVING_FLOWER,EnumCreatureType.CREATURE,new Biome[]{ModBiomes.KATHARIAN_FOREST,ModBiomes.KATHARIAN_DENSE_FOREST},8,1,1);
        registerEntitySpawn((EntityType<? extends EntityLiving>)HOWLER,EnumCreatureType.MONSTER,new Biome[]{ModBiomes.KATHARIAN_FOREST,ModBiomes.KATHARIAN_DENSE_FOREST},5,1,1);
        registerEntitySpawn((EntityType<? extends EntityLiving>)FUNGITE,EnumCreatureType.MONSTER,new Biome[]{ModBiomes.KATHARIAN_FOREST,ModBiomes.KATHARIAN_DENSE_FOREST},2,1,1);
        registerEntitySpawn((EntityType<? extends EntityLiving>)CACTI_SPORE,EnumCreatureType.CREATURE,new Biome[]{ModBiomes.KATHARIAN_FOREST,ModBiomes.KATHARIAN_DENSE_FOREST},4,1,1);
        //registerEntitySpawn((EntityType<? extends EntityLiving>)JELLY_FISH,EnumCreatureType.CREATURE,new Biome[]{ModBiomes.PLAIN_FIELDS},10,1,2);
        registerEntitySpawn((EntityType<? extends EntityLiving>)BISON,EnumCreatureType.CREATURE,new Biome[]{ModBiomes.PLAIN_FIELDS},8,2,4);
        registerEntitySpawn((EntityType<? extends EntityLiving>)COMMON_BUTTERFLY1,EnumCreatureType.CREATURE,new Biome[]{ModBiomes.PLAIN_FIELDS},10,1,1);
        registerEntitySpawn((EntityType<? extends EntityLiving>)COMMON_BUTTERFLY2,EnumCreatureType.CREATURE,new Biome[]{ModBiomes.PLAIN_FIELDS},10,1,1);
        registerEntitySpawn((EntityType<? extends EntityLiving>)PHASM,EnumCreatureType.MONSTER,new Biome[]{ModBiomes.KATHARIAN_SWAMP},2,1,1);
        registerEntitySpawn((EntityType<? extends EntityLiving>)RUBY_SILE,EnumCreatureType.CREATURE,new Biome[]{ModBiomes.KATHARIAN_FOREST,ModBiomes.KATHARIAN_DENSE_FOREST,ModBiomes.PLAIN_FIELDS},12,1,1);
        registerEntitySpawn((EntityType<? extends EntityLiving>)RUBY_SILE,EnumCreatureType.CREATURE,new Biome[]{ModBiomes.KATHARIAN_FOREST,ModBiomes.KATHARIAN_DENSE_FOREST,ModBiomes.PLAIN_FIELDS},12,1,1);
        registerEntitySpawn((EntityType<? extends EntityLiving>)CLOUD_SHIMMER,EnumCreatureType.AMBIENT,new Biome[]{ModBiomes.KATHARIAN_FOREST,ModBiomes.KATHARIAN_DENSE_FOREST,ModBiomes.PLAIN_FIELDS,ModBiomes.KATHARIAN_SWAMP,ModBiomes.KATHARIAN_DESERT,ModBiomes.KATHARIAN_DESERT_EDGE},12,1,1);
        registerEntitySpawn((EntityType<? extends EntityLiving>)SKYRAY,EnumCreatureType.CREATURE,new Biome[]{ModBiomes.HUGE_DESERT_MOUNTAINS},12,1,5);
        //registerEntitySpawn((EntityType<? extends EntityLiving>)SKYLIGHT,EnumCreatureType.MONSTER,new Biome[]{ModBiomes.KATHARIAN_FOREST,ModBiomes.KATHARIAN_DENSE_FOREST,ModBiomes.PLAIN_FIELDS,ModBiomes.KATHARIAN_SWAMP,ModBiomes.KATHARIAN_DESERT,ModBiomes.KATHARIAN_DESERT_EDGE},2,1,1);

    }

    public static void registerEggs(final RegistryEvent.Register<Item> event){
        event.getRegistry().register(makeSpawnEgg(COMMON_BUTTERFLY1,0xffffff, 0xff66e2,"basic_butterfly1"));
        event.getRegistry().register(makeSpawnEgg(COMMON_BUTTERFLY2,0xffffff, 0x00c3ff,"basic_butterfly2"));
        event.getRegistry().register(makeSpawnEgg(CLOUD_SHIMMER,0x996600, 0x00ff00,"cloud_shimmer"));
        event.getRegistry().register(makeSpawnEgg(ILLUKINI,0x996600, 0x00ff00,"illukini"));
        event.getRegistry().register(makeSpawnEgg(RUBY_SILE,0x996600, 0x00ff00,"ruby_sile"));
        event.getRegistry().register(makeSpawnEgg(SKYLIGHT,0xffe62d, 0xffffff,"skylight"));
        event.getRegistry().register(makeSpawnEgg(BIG_TURTLE,0xa87001, 0x21b6d,"big_turtle"));
        event.getRegistry().register(makeSpawnEgg(BISON,0xa87801, 0x2d2400,"bison"));
        event.getRegistry().register(makeSpawnEgg(CACTI_SPORE,0x996600, 0x00ff00,"cacti_spore"));
        event.getRegistry().register(makeSpawnEgg(CAMEL,0xffd36b, 0xf9c039,"camel"));
        event.getRegistry().register(makeSpawnEgg(CLOUD_OISTER,0xffffff, 0xff93f0,"cloud_oister"));
        event.getRegistry().register(makeSpawnEgg(CLOUDY_SLIME,0xffffff, 0x9ef3ff,"cloudy_slime"));
        event.getRegistry().register(makeSpawnEgg(FLYING_SQUID,0x996600, 0x00ff00,"flying_squid"));
        event.getRegistry().register(makeSpawnEgg(FUNGITE,0x03204f, 0xb51405,"fungite"));
        event.getRegistry().register(makeSpawnEgg(GAZNOWEL,0x996600, 0x00ff00,"gaznowel"));
        event.getRegistry().register(makeSpawnEgg(GECKO,0x996600, 0x00ff00,"gecko"));
        event.getRegistry().register(makeSpawnEgg(HOWLER,0x07003a, 0x4f0000,"howler"));
        event.getRegistry().register(makeSpawnEgg(JELLY_FISH,0x5e0059, 0xff00f1,"jelly_fish"));
        event.getRegistry().register(makeSpawnEgg(LIVING_FLOWER,0x00ff15, 0xff0000,"living_flower"));
        event.getRegistry().register(makeSpawnEgg(MYSTIC_BIRD,0xDFCE9B, 0xff0000,"mystic_bird"));
        event.getRegistry().register(makeSpawnEgg(PHASM,0x996600, 0x00ff00,"phasm"));
        event.getRegistry().register(makeSpawnEgg(POISONOUS_SCORPION,0x030b2b, 0x0fc625,"poisonous_scorpion"));
        event.getRegistry().register(makeSpawnEgg(SKYRAY,0x996600, 0x00ff00,"skyray"));
        event.getRegistry().register(makeSpawnEgg(STRANGE_WANDERER,0xffdfa0, 0xffffff,"strange_wanderer"));
    }

    private static Item makeSpawnEgg(EntityType<?> type, int color1, int color2, String name){
        return new ItemSpawnEgg(type,color1,color2,new Item.Properties().group(ItemGroup.SEARCH)).setRegistryName(MOD_ID,name);
    }
}
