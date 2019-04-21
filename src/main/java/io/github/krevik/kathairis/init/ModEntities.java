package io.github.krevik.kathairis.init;

import io.github.krevik.kathairis.entity.*;
import io.github.krevik.kathairis.entity.butterfly.*;
import net.minecraft.entity.*;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemSpawnEgg;
import net.minecraft.world.WorldEntitySpawner;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.PlainsBiome;
import net.minecraft.world.gen.Heightmap;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ObjectHolder;

import java.util.ArrayList;

import static io.github.krevik.kathairis.util.ModReference.MOD_ID;
import static io.github.krevik.kathairis.util.ModUtil._null;

//@ObjectHolder(MOD_ID)
public class ModEntities {

    public static EntityType<? extends EntityLiving> BASIC_BUTTERFLY1 = (EntityType<? extends EntityLiving>) EntityType.Builder.create(EntityButterfly.class, EntityButterfly::new).tracker(200, 1, true).build("common_butterfly1").setRegistryName(MOD_ID,"common_butterfly1");
    public static EntityType<? extends EntityLiving> BASIC_BUTTERFLY2 = (EntityType<? extends EntityLiving>) EntityType.Builder.create(EntityButterfly1.class, EntityButterfly1::new).tracker(200, 1, true).build("common_butterfly2").setRegistryName(MOD_ID,"common_butterfly2");
    public static EntityType<? extends EntityLiving> CLOUD_SHIMMER = (EntityType<? extends EntityLiving>) EntityType.Builder.create(EntityCloudShimmer.class, EntityCloudShimmer::new).tracker(200, 1, true).build("cloud_shimmer").setRegistryName(MOD_ID,"cloud_shimmer");
    public static EntityType<? extends EntityLiving> ILLUKINI = (EntityType<? extends EntityLiving>) EntityType.Builder.create(EntityIllukini.class, EntityIllukini::new).tracker(200, 1, true).build("illukini").setRegistryName(MOD_ID,"illukini");
    public static EntityType<? extends EntityLiving> RUBY_SILE = (EntityType<? extends EntityLiving>) EntityType.Builder.create(EntityRubySile.class, EntityRubySile::new).tracker(200, 1, true).build("ruby_sile").setRegistryName(MOD_ID,"ruby_sile");
    public static EntityType<? extends EntityLiving> SKYLIGHT = (EntityType<? extends EntityLiving>) EntityType.Builder.create(EntitySkylight.class, EntitySkylight::new).tracker(200, 1, true).build("skylight").setRegistryName(MOD_ID,"skylight");
    public static EntityType<? extends EntityLiving> BIG_TURTLE = (EntityType<? extends EntityLiving>) EntityType.Builder.create(EntityBigTurtle.class, EntityBigTurtle::new).tracker(32, 1, true).build("big_turtle").setRegistryName(MOD_ID,"big_tortoise");
    public static EntityType<? extends EntityLiving> BISON = (EntityType<? extends EntityLiving>) EntityType.Builder.create(EntityBison.class, EntityBison::new).tracker(32, 1, true).build("bison").setRegistryName(MOD_ID,"bison");
    public static EntityType<? extends EntityLiving> CACTI_SPORE = (EntityType<? extends EntityLiving>) EntityType.Builder.create(EntityCactiSpore.class, EntityCactiSpore::new).tracker(32, 1, true).build("cacti_spore").setRegistryName(MOD_ID,"cacti_spore");
    public static EntityType<? extends EntityLiving> CAMEL = (EntityType<? extends EntityLiving>) EntityType.Builder.create(EntityCamel.class, EntityCamel::new).tracker(32, 1, true).build("camel").setRegistryName(MOD_ID,"camel");
    public static EntityType<? extends EntityLiving> CLOUD_OISTER = (EntityType<? extends EntityLiving>) EntityType.Builder.create(EntityCloudOister.class, EntityCloudOister::new).tracker(32, 1, true).build("cloud_oister").setRegistryName(MOD_ID,"cloud_oister");
    public static EntityType<? extends EntityLiving> CLOUDY_SLIME = (EntityType<? extends EntityLiving>) EntityType.Builder.create(EntityCloudySlime.class, EntityCloudySlime::new).tracker(32, 1, true).build("cloudy_slime").setRegistryName(MOD_ID,"cloudy_slime");
    public static EntityType<? extends EntityLiving> FLYING_SQUID = (EntityType<? extends EntityLiving>) EntityType.Builder.create(EntityFlyingSquid.class, EntityFlyingSquid::new).tracker(32, 1, true).build("flying_squid").setRegistryName(MOD_ID,"flying_squid");
    public static EntityType<? extends EntityLiving> FUNGITE = (EntityType<? extends EntityLiving>) EntityType.Builder.create(EntityFungite.class, EntityFungite::new).tracker(32, 1, true).build("fungite").setRegistryName(MOD_ID,"fungite");
    public static EntityType<? extends EntityLiving> GAZNOWEL = (EntityType<? extends EntityLiving>) EntityType.Builder.create(EntityGaznowel.class, EntityGaznowel::new).tracker(32, 1, true).build("gaznowel").setRegistryName(MOD_ID,"gaznowel");
    public static EntityType<? extends EntityLiving> GECKO = (EntityType<? extends EntityLiving>) EntityType.Builder.create(EntityGecko.class, EntityGecko::new).tracker(32, 1, true).build("gecko").setRegistryName(MOD_ID,"gecko");
    public static EntityType<? extends EntityLiving> HOWLER = (EntityType<? extends EntityLiving>) EntityType.Builder.create(EntityHowler.class, EntityHowler::new).tracker(32, 1, true).build("howler").setRegistryName(MOD_ID,"howler");
    public static EntityType<? extends EntityLiving> JELLY_FISH = (EntityType<? extends EntityLiving>) EntityType.Builder.create(EntityJellyFish.class, EntityJellyFish::new).tracker(32, 1, true).build("jelly_fish").setRegistryName(MOD_ID,"jelly_fish");
    public static EntityType<? extends EntityLiving> LIVING_FLOWER = (EntityType<? extends EntityLiving>) EntityType.Builder.create(EntityLivingFlower.class, EntityLivingFlower::new).tracker(32, 1, true).build("living_flower").setRegistryName(MOD_ID,"living_flower");
    public static EntityType<? extends EntityLiving> MYSTIC_BIRD = (EntityType<? extends EntityLiving>) EntityType.Builder.create(EntityMysticBird.class, EntityMysticBird::new).tracker(32, 1, true).build("mystic_bird").setRegistryName(MOD_ID,"mystic_bird");
    public static EntityType<? extends EntityLiving> PHASM = (EntityType<? extends EntityLiving>) EntityType.Builder.create(EntityPhasm.class, EntityPhasm::new).tracker(32, 1, true).build("phasm").setRegistryName(MOD_ID,"phasm");
    public static EntityType<? extends EntityLiving> POISONOUS_SCORPION = (EntityType<? extends EntityLiving>) EntityType.Builder.create(EntityPoisonousScorpion.class, EntityPoisonousScorpion::new).tracker(32, 1, true).build("poisonous_scorpion").setRegistryName(MOD_ID,"poisonous_scorpion");
    public static EntityType<? extends EntityLiving> SKYRAY = (EntityType<? extends EntityLiving>) EntityType.Builder.create(EntitySkyray.class, EntitySkyray::new).tracker(200, 1, true).build("skyray").setRegistryName(MOD_ID,"skyray");
    public static EntityType<? extends EntityLiving> STRANGE_WANDERER = (EntityType<? extends EntityLiving>) EntityType.Builder.create(EntityStrangeWanderer.class, EntityStrangeWanderer::new).tracker(200, 1, true).build("strange_wanderer").setRegistryName(MOD_ID,"strange_wanderer");
    public static EntityType<?> MYSTIC_WAND_SHOOT = EntityType.Builder.create(EntityMysticWandShoot.class, EntityMysticWandShoot::new).tracker(200, 1, true).build("mystic_wand_shoot").setRegistryName(MOD_ID,"mystic_wand_shoot");


    public static void registerKathairisEntityTypes(final RegistryEvent.Register<EntityType<?>> event){
        event.getRegistry().registerAll(
                BASIC_BUTTERFLY1,
                BASIC_BUTTERFLY2,
                CLOUD_SHIMMER,
                ILLUKINI,
                RUBY_SILE,
                SKYLIGHT,
                BIG_TURTLE,
                BISON,
                CACTI_SPORE,
                CAMEL,
                CLOUD_OISTER,
                CLOUDY_SLIME,
                FLYING_SQUID,
                FUNGITE,
                GAZNOWEL,
                GECKO,
                HOWLER,
                JELLY_FISH,
                LIVING_FLOWER,
                MYSTIC_BIRD,
                PHASM,
                POISONOUS_SCORPION,
                SKYRAY,
                STRANGE_WANDERER,
                MYSTIC_WAND_SHOOT
        );
    }

    public static void registerPlacementType(EntityType<?> type,EntitySpawnPlacementRegistry.SpawnPlacementType spawnType){
        EntitySpawnPlacementRegistry.register(type, spawnType, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,null);
    }

    public static void registerPlacementTypes(){
        registerPlacementType(BASIC_BUTTERFLY1, EntitySpawnPlacementRegistry.SpawnPlacementType.ON_GROUND);
        registerPlacementType(BASIC_BUTTERFLY2, EntitySpawnPlacementRegistry.SpawnPlacementType.ON_GROUND);
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
        registerPlacementType(MYSTIC_BIRD, EntitySpawnPlacementRegistry.SpawnPlacementType.ON_GROUND);
        registerPlacementType(PHASM, EntitySpawnPlacementRegistry.SpawnPlacementType.ON_GROUND);
        registerPlacementType(POISONOUS_SCORPION, EntitySpawnPlacementRegistry.SpawnPlacementType.ON_GROUND);
        registerPlacementType(SKYRAY, EntitySpawnPlacementRegistry.SpawnPlacementType.ON_GROUND);
        registerPlacementType(STRANGE_WANDERER, EntitySpawnPlacementRegistry.SpawnPlacementType.ON_GROUND);

    }

    private static void registerEntitySpawn(EntityType<? extends EntityLiving> type,EnumCreatureType creatureType, EntitySpawnPlacementRegistry.SpawnPlacementType spawnType,Biome[] biomes, int weight, int min, int max) {
        for (Biome biome : biomes) {
            if (biome != null) {
                    biome.getSpawns(creatureType).add(new Biome.SpawnListEntry( type, weight, min, max));
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
        //registerEntitySpawn(ModEntities.FUNGITE,EnumCreatureType.MONSTER,ON_GROUND,new Biome[]{ModBiomes.KATHARIAN_FOREST},2,1,1);
        //registerEntitySpawn(ModEntities.CACTI_SPORE,EnumCreatureType.CREATURE,ON_GROUND,new Biome[]{ModBiomes.KATHARIAN_FOREST},4,1,1);
        //registerEntitySpawn(ModEntities.JELLY_FISH,EnumCreatureType.CREATURE,ON_GROUND,new Biome[]{ModBiomes.PLAIN_FIELDS},10,1,2);
        //registerEntitySpawn(ModEntities.BISON,EnumCreatureType.CREATURE,ON_GROUND,new Biome[]{ModBiomes.PLAIN_FIELDS},8,2,4);
        registerEntitySpawn(ModEntities.BASIC_BUTTERFLY1,EnumCreatureType.CREATURE,ON_GROUND,new Biome[]{ModBiomes.PLAIN_FIELDS},10,1,1);
        registerEntitySpawn(ModEntities.BASIC_BUTTERFLY2,EnumCreatureType.CREATURE,ON_GROUND,new Biome[]{ModBiomes.PLAIN_FIELDS},10,1,1);
        registerEntitySpawn(ModEntities.PHASM,EnumCreatureType.MONSTER,ON_GROUND,new Biome[]{ModBiomes.KATHARIAN_SWAMP},2,1,1);
    }

    public static void registerEggs(final RegistryEvent.Register<Item> event){
        event.getRegistry().register(makeSpawnEgg(BASIC_BUTTERFLY1,0xffffff, 0xff66e2,"basic_butterfly1"));
        event.getRegistry().register(makeSpawnEgg(BASIC_BUTTERFLY2,0xffffff, 0x00c3ff,"basic_butterfly2"));
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
