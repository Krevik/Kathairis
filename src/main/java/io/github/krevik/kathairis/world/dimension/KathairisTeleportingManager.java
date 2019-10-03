package io.github.krevik.kathairis.world.dimension;

import io.github.krevik.kathairis.init.ModBlocks;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.Blocks;
import net.minecraft.block.pattern.BlockPattern;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.play.server.*;
import net.minecraft.potion.EffectInstance;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.PlayerList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.storage.WorldInfo;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Map;

/**
 * @author Krevik
 */
public class KathairisTeleportingManager {
    public static void tele(Entity entity){
        if(!entity.world.isRemote && (entity instanceof ServerPlayerEntity)){
            //this is handled through ForgeEventSubscriber#playerTeleporting
            entity.timeUntilPortal=10;
        }
        if(!entity.world.isRemote && !(entity instanceof ServerPlayerEntity)){
            DimensionType type = ModDimensionKathairis.getDimensionType();
            if(entity.getRidingEntity()==null && !entity.isBeingRidden()){
                entity.setPortal(new BlockPos(entity.posX,entity.posY,entity.posZ));
                if (entity.timeUntilPortal > 0) {
                    entity.timeUntilPortal = 10;
                }else if(entity.dimension != type){
                    entity.timeUntilPortal=10;
                    changeDim(entity, type);
                }else if(entity.dimension == type){
                    entity.timeUntilPortal = 10;
                    changeDim(entity, DimensionType.OVERWORLD);
                }
            }
        }
    }




    /*public static void teleOld(Entity entity){
        if(!entity.world.isRemote && entity instanceof ServerPlayerEntity){
            DimensionType type = ModDimensionKathairis.getDimensionType();
            if(entity.getRidingEntity()==null && !entity.isBeingRidden()){
                entity.setPortal(new BlockPos(entity.posX,entity.posY,entity.posZ));
                if (entity.timeUntilPortal > 0) {
                    entity.timeUntilPortal = 10;
                }else if(entity.dimension != type){
                    entity.timeUntilPortal=10;
                    changeDimPlayer((ServerPlayerEntity) entity, type);
                }else if(entity.dimension == type){
                    entity.timeUntilPortal = 10;
                    changeDimPlayer((ServerPlayerEntity) entity, DimensionType.OVERWORLD);
                }
            }
        }
        if(!(entity instanceof ServerPlayerEntity)){
            DimensionType type = ModDimensionKathairis.getDimensionType();
            if(entity.getRidingEntity()==null && !entity.isBeingRidden()){
                entity.setPortal(new BlockPos(entity.posX,entity.posY,entity.posZ));
                if (entity.timeUntilPortal > 0) {
                    entity.timeUntilPortal = 10;
                }else if(entity.dimension != type){
                    entity.timeUntilPortal=10;
                    changeDim(entity, type);
                }else if(entity.dimension == type){
                    entity.timeUntilPortal = 10;
                    changeDim(entity, DimensionType.OVERWORLD);
                }
            }
        }
    }*/



    @Nullable
    public static void changeDim(Entity entity,DimensionType destination) {
        if (!net.minecraftforge.common.ForgeHooks.onTravelToDimension(entity, destination)) {//return null;
            }
            if (!entity.world.isRemote && !entity.removed) {
                entity.world.getProfiler().startSection("changeDimension");
                MinecraftServer minecraftserver = entity.getServer();
                DimensionType dimensiontype = entity.dimension;
                ServerWorld serverworld = minecraftserver.getWorld(dimensiontype);
                ServerWorld serverworld1 = minecraftserver.getWorld(destination);
                entity.dimension = destination;
                entity.detach();
                entity.world.getProfiler().startSection("reposition");
                Vec3d vec3d = entity.getMotion();
                float f = 0.0F;
                BlockPos blockpos;
                if (dimensiontype == DimensionType.THE_END && destination == DimensionType.OVERWORLD) {
                    blockpos = serverworld1.getHeight(Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, serverworld1.getSpawnPoint());
                } else if (destination == DimensionType.THE_END) {
                    blockpos = serverworld1.getSpawnCoordinate();
                } else {
                    double movementFactor = serverworld.getDimension().getMovementFactor() / serverworld1.getDimension().getMovementFactor();
                    double d0 = entity.posX * movementFactor;
                    double d1 = entity.posZ * movementFactor;

                    double d3 = Math.min(-2.9999872E7D, serverworld1.getWorldBorder().minX() + 16.0D);
                    double d4 = Math.min(-2.9999872E7D, serverworld1.getWorldBorder().minZ() + 16.0D);
                    double d5 = Math.min(2.9999872E7D, serverworld1.getWorldBorder().maxX() - 16.0D);
                    double d6 = Math.min(2.9999872E7D, serverworld1.getWorldBorder().maxZ() - 16.0D);
                    d0 = MathHelper.clamp(d0, d3, d5);
                    d1 = MathHelper.clamp(d1, d4, d6);
                    Vec3d vec3d1 = entity.getLastPortalVec();
                    blockpos = new BlockPos(d0, entity.posY, d1);
                    BlockPattern.PortalInfo blockpattern$portalinfo = new TeleporterKathairis(serverworld1).func_222272_a(blockpos, vec3d, entity.getTeleportDirection(), vec3d1.x, vec3d1.y, entity instanceof PlayerEntity);
                    if (blockpattern$portalinfo == null) {
                        //return null
                    }

                    blockpos = new BlockPos(blockpattern$portalinfo.field_222505_a);
                    vec3d = blockpattern$portalinfo.field_222506_b;
                    f = (float) blockpattern$portalinfo.field_222507_c;
                }

                entity.world.getProfiler().endStartSection("reloading");
                Entity entityNew = entity.getType().create(serverworld1);
                if (entityNew != null) {
                    entityNew.copyDataFromOld(entity);
                    entityNew.moveToBlockPosAndAngles(blockpos, entity.rotationYaw + f, entity.rotationPitch);
                    entityNew.setMotion(vec3d);
                    serverworld1.func_217460_e(entityNew);
                }

                entity.remove(false);
                entity.world.getProfiler().endSection();
                serverworld.resetUpdateEntityTick();
                serverworld1.resetUpdateEntityTick();
                entity.world.getProfiler().endSection();
                //return entityNew;
            } else {
                //return null;
            }
    }


    @Nullable
    public static void changeDimPlayer(ServerPlayerEntity player, DimensionType destination) {
        if (!net.minecraftforge.common.ForgeHooks.onTravelToDimension(player, destination)){ //return null;
             }
        DimensionType dimensiontype = player.dimension;
        if (dimensiontype == DimensionType.THE_END && destination == DimensionType.OVERWORLD) {
        } else {
            ServerWorld serverworld = player.server.getWorld(dimensiontype);
            player.dimension = destination;
            ServerWorld serverworld1 = player.server.getWorld(destination);
            WorldInfo worldinfo = player.world.getWorldInfo();
            player.connection.sendPacket(new SRespawnPacket(destination, worldinfo.getGenerator(), player.interactionManager.getGameType()));
            player.connection.sendPacket(new SServerDifficultyPacket(worldinfo.getDifficulty(), worldinfo.isDifficultyLocked()));
            PlayerList playerlist = player.server.getPlayerList();
            playerlist.updatePermissionLevel(player);
            serverworld.removeEntity(player, true); //Forge: the player entity is moved to the new world, NOT cloned. So keep the data alive with no matching invalidate call.
            player.revive();
            double d0 = player.posX;
            double d1 = player.posY;
            double d2 = player.posZ;
            float f = player.rotationPitch;
            float f1 = player.rotationYaw;
            double d3 = 8.0D;
            float f2 = f1;
            serverworld.getProfiler().startSection("moving");
            double moveFactor = serverworld.getDimension().getMovementFactor() / serverworld1.getDimension().getMovementFactor();
            d0 *= moveFactor;
            d2 *= moveFactor;
            if (dimensiontype == DimensionType.OVERWORLD && destination == DimensionType.THE_NETHER) {
            } else if (dimensiontype == DimensionType.OVERWORLD && destination == DimensionType.THE_END) {
                BlockPos blockpos = serverworld1.getSpawnCoordinate();
                d0 = (double)blockpos.getX();
                d1 = (double)blockpos.getY();
                d2 = (double)blockpos.getZ();
                f1 = 90.0F;
                f = 0.0F;
            }

            player.setLocationAndAngles(d0, d1, d2, f1, f);
            serverworld.getProfiler().endSection();
            serverworld.getProfiler().startSection("placing");
            double d7 = Math.min(-2.9999872E7D, serverworld1.getWorldBorder().minX() + 16.0D);
            double d4 = Math.min(-2.9999872E7D, serverworld1.getWorldBorder().minZ() + 16.0D);
            double d5 = Math.min(2.9999872E7D, serverworld1.getWorldBorder().maxX() - 16.0D);
            double d6 = Math.min(2.9999872E7D, serverworld1.getWorldBorder().maxZ() - 16.0D);
            d0 = MathHelper.clamp(d0, d7, d5);
            d2 = MathHelper.clamp(d2, d4, d6);
            player.setLocationAndAngles(d0, d1, d2, f1, f);
            if (destination == DimensionType.THE_END) {
                int i = MathHelper.floor(player.posX);
                int j = MathHelper.floor(player.posY) - 1;
                int k = MathHelper.floor(player.posZ);
                int l = 1;
                int i1 = 0;

                for(int j1 = -2; j1 <= 2; ++j1) {
                    for(int k1 = -2; k1 <= 2; ++k1) {
                        for(int l1 = -1; l1 < 3; ++l1) {
                            int i2 = i + k1 * 1 + j1 * 0;
                            int j2 = j + l1;
                            int k2 = k + k1 * 0 - j1 * 1;
                            boolean flag = l1 < 0;
                            serverworld1.setBlockState(new BlockPos(i2, j2, k2), flag ? Blocks.STONE.getDefaultState() : Blocks.AIR.getDefaultState());
                        }
                    }
                }

                player.setLocationAndAngles((double)i, (double)j, (double)k, f1, 0.0F);
                player.setMotion(Vec3d.ZERO);
            } else if (!new TeleporterKathairis(serverworld1).func_222268_a(player, f2)) {
                new TeleporterKathairis(serverworld1).makePortal(player);
                new TeleporterKathairis(serverworld1).func_222268_a(player, f2);
            }

            serverworld.getProfiler().endSection();
            player.setWorld(serverworld1);
            serverworld1.func_217447_b(player);
            CriteriaTriggers.CHANGED_DIMENSION.trigger(player, dimensiontype, destination);
            player.connection.setPlayerLocation(player.posX, player.posY, player.posZ, f1, f);
            player.interactionManager.setWorld(serverworld1);
            player.connection.sendPacket(new SPlayerAbilitiesPacket(player.abilities));
            playerlist.sendWorldInfo(player, serverworld1);
            playerlist.sendInventory(player);

            for(EffectInstance effectinstance : player.getActivePotionEffects()) {
                player.connection.sendPacket(new SPlayEntityEffectPacket(player.getEntityId(), effectinstance));
            }

            player.connection.sendPacket(new SPlaySoundEventPacket(1032, BlockPos.ZERO, 0, false));
            net.minecraftforge.fml.hooks.BasicEventHooks.firePlayerChangedDimensionEvent(player, dimensiontype, destination);
        }
    }

}