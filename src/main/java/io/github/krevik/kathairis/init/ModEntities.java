package io.github.krevik.kathairis.init;

import io.github.krevik.kathairis.entity.*;
import io.github.krevik.kathairis.entity.butterfly.*;
import io.github.krevik.kathairis.util.RegistryHelper;
import io.github.krevik.kathairis.util.SpawnPredicator;
import io.github.krevik.kathairis.world.dimension.biome.biomes.BiomeKatharianBiomeBase;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.Heightmap;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

import static io.github.krevik.kathairis.util.ModReference.MOD_ID;
import static io.github.krevik.kathairis.util.ModUtil._null;
import static net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus.MOD;

@ObjectHolder(MOD_ID)
@Mod.EventBusSubscriber(modid = MOD_ID, bus = MOD)
public class ModEntities {

    public static EntityType<EntityButterfly> COMMON_BUTTERFLY1 = _null();
    public static EntityType<EntityButterfly1> COMMON_BUTTERFLY2 = _null();
    public static EntityType<EntityCloudShimmer> CLOUD_SHIMMER = _null();
    public static EntityType<EntityIllukini> ILLUKINI = _null();
    public static EntityType<EntityRubySile> RUBY_SILE = _null();
    public static EntityType<EntitySkylight> SKYLIGHT = _null();
    public static EntityType<EntityBigTurtle> BIG_TURTLE = _null();
    public static EntityType<EntityBison> BISON = _null();
    public static EntityType<EntityCactiSpore> CACTI_SPORE = _null();
    public static EntityType<EntityCamel> CAMEL = _null();
    public static EntityType<EntityCloudOister> CLOUD_OISTER = _null();
    public static EntityType<EntityCloudySlime> CLOUDY_SLIME = _null();
    public static EntityType<EntityFlyingSquid> FLYING_SQUID = _null();
    public static EntityType<EntityFungite> FUNGITE = _null();
    public static EntityType<EntityGaznowel> GAZNOWEL = _null();
    public static EntityType<EntityGecko> GECKO = _null();
    public static EntityType<EntityHowler> HOWLER = _null();
    public static EntityType<EntityJellyFish> JELLY_FISH = _null();
    public static EntityType<EntityLivingFlower> LIVING_FLOWER = _null();
    public static EntityType<EntityMysticBird> MYSTIC_BIRD = _null();
    public static EntityType<EntityPhasm> PHASM = _null();
    public static EntityType<EntityPoisonousScorpion> POISONOUS_SCORPION = _null();
    public static EntityType<EntitySkyray> SKYRAY = _null();
    public static EntityType<EntityStrangeWanderer> STRANGE_WANDERER = _null();
    public static EntityType<EntityMysticWandShoot> MYSTIC_WAND_SHOOT = _null();

    @SubscribeEvent
    public static void onRegisterEntityTypes(final RegistryEvent.Register<EntityType<?>> event){
        event.getRegistry().registerAll(
                RegistryHelper.setup(ModEntities.COMMON_BUTTERFLY1=EntityType.Builder.<EntityButterfly>create(EntityButterfly::new, EntityClassification.AMBIENT).size(0.6F,0.5F).build("kathairis:common_butterfly1"),"common_butterfly1"),
                RegistryHelper.setup(ModEntities.COMMON_BUTTERFLY2=EntityType.Builder.<EntityButterfly1>create(EntityButterfly1::new, EntityClassification.AMBIENT).size(0.6F,0.5F).build("kathairis:common_butterfly2"),"common_butterfly2"),
                RegistryHelper.setup(ModEntities.CLOUD_SHIMMER=EntityType.Builder.<EntityCloudShimmer>create(EntityCloudShimmer::new, EntityClassification.AMBIENT).size(0.6F,0.5F).build("kathairis:cloud_shimmer"),"cloud_shimmer"),
                RegistryHelper.setup(ModEntities.ILLUKINI=EntityType.Builder.<EntityIllukini>create(EntityIllukini::new, EntityClassification.AMBIENT).size(0.6F,0.5F).setTrackingRange(200).build("kathairis:illukini"),"illukini"),
                RegistryHelper.setup(ModEntities.RUBY_SILE=EntityType.Builder.<EntityRubySile>create(EntityRubySile::new, EntityClassification.AMBIENT).size(0.6F,0.5F).setTrackingRange(200).build("kathairis:ruby_sile"),"ruby_sile"),
                RegistryHelper.setup(ModEntities.SKYLIGHT=EntityType.Builder.<EntitySkylight>create(EntitySkylight::new, EntityClassification.AMBIENT).size(0.15F,0.15F).setTrackingRange(200).build("kathairis:skylight"),"skylight"),
                RegistryHelper.setup(ModEntities.BIG_TURTLE=EntityType.Builder.<EntityBigTurtle>create(EntityBigTurtle::new, EntityClassification.CREATURE).size(0.9F,1.2F).setTrackingRange(200).build("kathairis:big_turtle"),"big_turtle"),
                RegistryHelper.setup(ModEntities.BISON=EntityType.Builder.<EntityBison>create(EntityBison::new, EntityClassification.CREATURE).size(1.5F,1.7F).setTrackingRange(200).build("kathairis:bison"),"bison"),
                RegistryHelper.setup(ModEntities.CACTI_SPORE=EntityType.Builder.<EntityCactiSpore>create(EntityCactiSpore::new, EntityClassification.MONSTER).size(1F,1F).setTrackingRange(200).build("kathairis:cacti_spore"),"cacti_spore"),
                RegistryHelper.setup(ModEntities.CAMEL=EntityType.Builder.<EntityCamel>create(EntityCamel::new, EntityClassification.CREATURE).size(1.6F,1.5F).setTrackingRange(200).build("kathairis:camel"),"camel"),
                RegistryHelper.setup(ModEntities.CLOUD_OISTER=EntityType.Builder.<EntityCloudOister>create(EntityCloudOister::new, EntityClassification.CREATURE).size(0.6F,0.6F).setTrackingRange(200).build("kathairis:cloud_oister"),"cloud_oister"),
                RegistryHelper.setup(ModEntities.CLOUDY_SLIME=EntityType.Builder.<EntityCloudySlime>create(EntityCloudySlime::new, EntityClassification.CREATURE).size(1.4F,1.4F).setTrackingRange(200).build("kathairis:cloudy_slime"),"cloudy_slime"),
                RegistryHelper.setup(ModEntities.FLYING_SQUID=EntityType.Builder.<EntityFlyingSquid>create(EntityFlyingSquid::new, EntityClassification.CREATURE).size(1.5F,2F).setTrackingRange(200).build("kathairis:flying_squid"),"flying_squid"),
                RegistryHelper.setup(ModEntities.FUNGITE=EntityType.Builder.<EntityFungite>create(EntityFungite::new, EntityClassification.MONSTER).size(1.5F,2.4F).setTrackingRange(200).build("kathairis:fungite"),"fungite"),
                RegistryHelper.setup(ModEntities.GAZNOWEL=EntityType.Builder.<EntityGaznowel>create(EntityGaznowel::new, EntityClassification.MONSTER).size(1F,2F).setTrackingRange(200).build("kathairis:gaznowel"),"gaznowel"),
                RegistryHelper.setup(ModEntities.GECKO=EntityType.Builder.<EntityGecko>create(EntityGecko::new, EntityClassification.CREATURE).size(0.7F,0.25F).setTrackingRange(200).build("kathairis:gecko"),"gecko"),
                RegistryHelper.setup(ModEntities.HOWLER=EntityType.Builder.<EntityHowler>create(EntityHowler::new, EntityClassification.MONSTER).size(0.85F,1F).setTrackingRange(200).build("kathairis:howler"),"howler"),
                RegistryHelper.setup(ModEntities.JELLY_FISH=EntityType.Builder.<EntityJellyFish>create(EntityJellyFish::new, EntityClassification.MONSTER).size(1f,1f).setTrackingRange(200).build("kathairis:jelly_fish"),"jelly_fish"),
                RegistryHelper.setup(ModEntities.LIVING_FLOWER=EntityType.Builder.<EntityLivingFlower>create(EntityLivingFlower::new, EntityClassification.AMBIENT).size(0.3F,0.5F).setTrackingRange(200).build("kathairis:living_flower"),"living_flower"),
                RegistryHelper.setup(ModEntities.MYSTIC_BIRD=EntityType.Builder.<EntityMysticBird>create(EntityMysticBird::new, EntityClassification.AMBIENT).size(0.5F,0.7F).setTrackingRange(200).build("kathairis:mystic_bird"),"mystic_bird"),
                RegistryHelper.setup(ModEntities.PHASM=EntityType.Builder.<EntityPhasm>create(EntityPhasm::new, EntityClassification.MONSTER).size(1F,2F).setTrackingRange(200).build("kathairis:phasm"),"phasm"),
                RegistryHelper.setup(ModEntities.POISONOUS_SCORPION=EntityType.Builder.<EntityPoisonousScorpion>create(EntityPoisonousScorpion::new, EntityClassification.MONSTER).size(0.4F,0.7F).setTrackingRange(200).build("kathairis:poisonous_scorpion"),"poisonous_scorpion"),
                RegistryHelper.setup(ModEntities.SKYRAY=EntityType.Builder.<EntitySkyray>create(EntitySkyray::new, EntityClassification.AMBIENT).size(10F,2.5F).setTrackingRange(200).build("kathairis:skyray"),"skyray"),
                RegistryHelper.setup(ModEntities.STRANGE_WANDERER=EntityType.Builder.<EntityStrangeWanderer>create(EntityStrangeWanderer::new, EntityClassification.AMBIENT).size(1F,2F).setTrackingRange(200).build("kathairis:strange_wanderer"),"strange_wanderer"),
                RegistryHelper.setup(ModEntities.MYSTIC_WAND_SHOOT=EntityType.Builder.<EntityMysticWandShoot>create(EntityMysticWandShoot::new, EntityClassification.MISC).size(1F,1F).setTrackingRange(200).build("kathairis:mystic_wand_shoot"),"mystic_wand_shoot")
        );
        ModEntities.registerPlacementTypes();
        ModEntities.registerEntitySpawns();
    }


    public static void registerPlacementType(EntityType type,EntitySpawnPlacementRegistry.PlacementType spawnType,Heightmap.Type heightType, EntitySpawnPlacementRegistry.IPlacementPredicate predicate){
        EntitySpawnPlacementRegistry.register(type, spawnType, heightType,predicate);
    }

    public static void registerPlacementTypes(){
        registerPlacementType(COMMON_BUTTERFLY1, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING, SpawnPredicator::canSpawnAnimal);
        registerPlacementType(COMMON_BUTTERFLY2, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING, SpawnPredicator::canSpawnAnimal);
        registerPlacementType(CLOUD_SHIMMER, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING, SpawnPredicator::canSpawnNimbus);
        registerPlacementType(ILLUKINI, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING, SpawnPredicator::canSpawnAnimal);
        registerPlacementType(RUBY_SILE, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING, SpawnPredicator::canSpawnAnimal);
        registerPlacementType(SKYLIGHT, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING, SpawnPredicator::canSpawnAnimal);
        registerPlacementType(BIG_TURTLE, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING, SpawnPredicator::canSpawnAnimal);
        registerPlacementType(BISON, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING, SpawnPredicator::canSpawnAnimal);
        registerPlacementType(CACTI_SPORE, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING, SpawnPredicator::canSpawnAnimal);
        registerPlacementType(CAMEL, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING, SpawnPredicator::canSpawnAnimal);
        registerPlacementType(CLOUD_OISTER, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING, SpawnPredicator::canSpawnAnimal);
        registerPlacementType(CLOUDY_SLIME, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING, SpawnPredicator::canSpawnAnimal);
        registerPlacementType(FLYING_SQUID, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING, SpawnPredicator::canSpawnAnimal);
        registerPlacementType(FUNGITE, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING, SpawnPredicator::canSpawnMonster);
        registerPlacementType(GAZNOWEL, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING, SpawnPredicator::canSpawnMonster);
        registerPlacementType(GECKO, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING, SpawnPredicator::canSpawnMonster);
        registerPlacementType(HOWLER, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING, SpawnPredicator::canSpawnMonster);
        registerPlacementType(JELLY_FISH, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING, SpawnPredicator::canSpawnMonster);
        registerPlacementType(LIVING_FLOWER, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING, SpawnPredicator::canSpawnAnimal);
        registerPlacementType(MYSTIC_BIRD, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING, SpawnPredicator::canSpawnAnimal);
        registerPlacementType(PHASM, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING, SpawnPredicator::canSpawnMonster);
        registerPlacementType(POISONOUS_SCORPION, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING, SpawnPredicator::canSpawnMonster);
        registerPlacementType(SKYRAY, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING, SpawnPredicator::canSpawnAnimal);
        registerPlacementType(STRANGE_WANDERER, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING, SpawnPredicator::canSpawnAnimal);

    }

    private static void registerEntitySpawn(EntityType<? extends LivingEntity> type, EntityClassification creatureType, Biome[] biomes, int weight, int min, int max) {
        for (Biome biome : biomes) {
            if (biome != null) {
                if(biome instanceof BiomeKatharianBiomeBase){
                    ((BiomeKatharianBiomeBase) biome).addSpawn(creatureType,new Biome.SpawnListEntry( type, weight, min, max));
                }
            }
        }
    }



    public static void registerEntitySpawns(){

        registerEntitySpawn(BIG_TURTLE,EntityClassification.CREATURE,new Biome[]{ModBiomes.KATHARIAN_DESERT,ModBiomes.KATHARIAN_DESERT_EDGE},12,1,1);
        //registerEntitySpawn(MYSTIC_BIRD,EntityClassification.CREATURE,new Biome[]{ModBiomes.KATHARIAN_FOREST,ModBiomes.KATHARIAN_SWAMP,ModBiomes.KATHARIAN_DENSE_FOREST,ModBiomes.PLAIN_FIELDS},12,1,3);
        registerEntitySpawn(POISONOUS_SCORPION,EntityClassification.MONSTER,new Biome[]{ModBiomes.KATHARIAN_DESERT,ModBiomes.KATHARIAN_DESERT_EDGE},3,1,1);
        registerEntitySpawn(CAMEL,EntityClassification.CREATURE,new Biome[]{ModBiomes.KATHARIAN_DESERT,ModBiomes.KATHARIAN_DESERT_EDGE},6,1,1);
        //registerEntitySpawn(GECKO,EntityClassification.CREATURE,new Biome[]{ModBiomes.KATHARIAN_FOREST,ModBiomes.KATHARIAN_DENSE_FOREST},4,1,1);
        //registerEntitySpawn(LIVING_FLOWER,EntityClassification.CREATURE,new Biome[]{ModBiomes.KATHARIAN_FOREST,ModBiomes.KATHARIAN_DENSE_FOREST},8,1,1);
        registerEntitySpawn(HOWLER,EntityClassification.MONSTER,new Biome[]{ModBiomes.KATHARIAN_FOREST,ModBiomes.KATHARIAN_DENSE_FOREST},5,1,1);
        registerEntitySpawn(FUNGITE,EntityClassification.MONSTER,new Biome[]{ModBiomes.KATHARIAN_FOREST,ModBiomes.KATHARIAN_DENSE_FOREST},2,1,1);
        registerEntitySpawn(CACTI_SPORE,EntityClassification.CREATURE,new Biome[]{ModBiomes.KATHARIAN_FOREST,ModBiomes.KATHARIAN_DENSE_FOREST},4,1,1);
        //registerEntitySpawn((EntityType<? extends EntityLiving>)JELLY_FISH,EnumCreatureType.CREATURE,new Biome[]{ModBiomes.PLAIN_FIELDS},10,1,2);
        registerEntitySpawn(BISON,EntityClassification.CREATURE,new Biome[]{ModBiomes.PLAIN_FIELDS},8,2,4);
        registerEntitySpawn(COMMON_BUTTERFLY1,EntityClassification.CREATURE,new Biome[]{ModBiomes.PLAIN_FIELDS},10,1,1);
        registerEntitySpawn(COMMON_BUTTERFLY2,EntityClassification.CREATURE,new Biome[]{ModBiomes.PLAIN_FIELDS},10,1,1);
        registerEntitySpawn(PHASM,EntityClassification.MONSTER,new Biome[]{ModBiomes.KATHARIAN_SWAMP},2,1,1);
        registerEntitySpawn(RUBY_SILE,EntityClassification.CREATURE,new Biome[]{ModBiomes.KATHARIAN_FOREST,ModBiomes.KATHARIAN_DENSE_FOREST,ModBiomes.PLAIN_FIELDS},12,1,1);
        registerEntitySpawn(RUBY_SILE,EntityClassification.CREATURE,new Biome[]{ModBiomes.KATHARIAN_FOREST,ModBiomes.KATHARIAN_DENSE_FOREST,ModBiomes.PLAIN_FIELDS},12,1,1);
        registerEntitySpawn(CLOUD_SHIMMER,EntityClassification.AMBIENT,new Biome[]{ModBiomes.KATHARIAN_FOREST,ModBiomes.KATHARIAN_DENSE_FOREST,ModBiomes.PLAIN_FIELDS,ModBiomes.KATHARIAN_SWAMP,ModBiomes.KATHARIAN_DESERT,ModBiomes.KATHARIAN_DESERT_EDGE},12,1,1);
        registerEntitySpawn(SKYRAY,EntityClassification.CREATURE,new Biome[]{ModBiomes.HUGE_DESERT_MOUNTAINS},12,1,5);
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
        return new SpawnEggItem(type,color1,color2,new Item.Properties().group(ItemGroup.SEARCH)).setRegistryName(MOD_ID,name);
    }
}
