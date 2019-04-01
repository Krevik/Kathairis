package io.github.krevik.kathairis.world.dimension;

import io.github.krevik.kathairis.Kathairis;
import io.github.krevik.kathairis.init.ModDimensions;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldServer;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.common.DimensionManager;
import org.apache.commons.lang3.Validate;

/**
 * @author Krevik
 */
public class TileEntityKathairisTeleportManager {

    public static void tele(Entity entity){
        if(!entity.world.isRemote){
            DimensionType type = DimensionType.getById(Kathairis.KATH_DIM_TYPE.getId());
            if(entity.getRidingEntity()==null && !entity.isBeingRidden()){
                MinecraftServer mcServer = entity.getServer();
                entity.setPortal(new BlockPos(entity.posX,entity.posY,entity.posZ));
                if (entity.timeUntilPortal > 0) {
                    entity.timeUntilPortal = 10;
                }else if(entity.dimension != type){
                    entity.timeUntilPortal=10;
                    entity.changeDimension(type,new TeleporterKathairis(mcServer.getWorld(type)));
                }else if(entity.dimension == type){
                    entity.timeUntilPortal = 10;
                    WorldServer toWorld = mcServer.getWorld(DimensionType.OVERWORLD);
                    entity.changeDimension(DimensionType.OVERWORLD, new TeleporterKathairis(toWorld));
                }
            }
        }
    }

    public static void tele(EntityPlayer player) {
        if (!player.world.isRemote) {
            DimensionType type = DimensionType.getById(Kathairis.KATH_DIM_TYPE.getId());
            if ((player.getRidingEntity() == null) && ((player instanceof EntityPlayerMP))) {

                EntityPlayerMP player1 = (EntityPlayerMP) player;
                MinecraftServer mcServer = player1.getServer();
                Validate.notNull(mcServer, "mcServer is null ....");
                player1.setPortal(new BlockPos(player1.posX, player1.posY, player1.posZ));

                if (player1.timeUntilPortal > 0) {

                    player1.timeUntilPortal = 10;

                } else if (player1.dimension != type) {

                    /*if(!dimensionKathairisHasBeenLoadedBefore()) {
                        notifyAllPlayersThatKathairisIsLoading();
                    }*/
                    player1.timeUntilPortal = 10;
                        player.changeDimension(type,new TeleporterKathairis(mcServer.getWorld(type)));

                } else if (player1.dimension == type) {
                    player1.timeUntilPortal = 10;
                    WorldServer toWorld = mcServer.getWorld(DimensionType.OVERWORLD);
                    player.changeDimension(DimensionType.OVERWORLD, new TeleporterKathairis(toWorld));

                }

            }
        }
    }
}