package io.github.krevik.kathairis.world.dimension;

import io.github.krevik.kathairis.Kathairis;
import io.github.krevik.kathairis.init.ModDimensions;
import io.github.krevik.kathairis.util.ModReference;
import net.minecraft.entity.Entity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldServer;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.common.DimensionManager;

/**
 * @author Krevik
 */
public class KathairisTeleportingManager {

    public static void tele(Entity entity){
        if(!entity.world.isRemote){
            int type1 = ModDimensions.KATH_DIM_TYPE.getId();
            DimensionType type = DimensionType.getById(type1);
            if(entity.getRidingEntity()==null && !entity.isBeingRidden()){
                MinecraftServer mcServer = entity.getServer();
                entity.setPortal(new BlockPos(entity.posX,entity.posY,entity.posZ));
                if (entity.timeUntilPortal > 0) {
                    entity.timeUntilPortal = 10;
                }else if(entity.dimension.getId() != type1){
                    entity.timeUntilPortal=10;
                    entity.changeDimension(type,new TeleporterKathairis(mcServer.getWorld(type)));
                }else if(entity.dimension.getId() == type1){
                    entity.timeUntilPortal = 10;
                    WorldServer toWorld = mcServer.getWorld(DimensionType.OVERWORLD);
                    entity.changeDimension(DimensionType.OVERWORLD, new TeleporterKathairis(toWorld));
                }
            }
        }
    }
}