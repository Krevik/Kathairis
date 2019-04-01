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
//TODO FIXME Krevik wtf is this?
public class TileEntityKathairisTeleportManager extends TileEntity {

    private static final String S_PLAYER_X = "PlayerPrevX";
    private static final String S_PLAYER_Y = "PlayerPrevY";
    private static final String S_PLAYER_Z = "PlayerPrevZ";
    private static final String S_PLAYER_X2 = "PlayerPrevX2";
    private static final String S_PLAYER_Y2 = "PlayerPrevY2";
    private static final String S_PLAYER_Z2 = "PlayerPrevZ2";
    private static final String dme = "DimEnter";
    public static double prevX;
    public static double prevY;
    public static double prevZ;
    public static double prevX2;
    public static double prevY2;
    public static double prevZ2;
    public static int dme2;
    private static int offsetX;
    private static int offsetZ;

    //FIXME
    public TileEntityKathairisTeleportManager() {
        super(null);
    }

    public static void setOverworldXYZ(double posX, double posY, double posZ) {
        prevX = posX;
        prevY = posY;
        prevZ = posZ;
    }

    public static void setTestXYZ(double posX2, double posY2, double posZ2) {
        prevX2 = posX2;
        prevY2 = posY2;
        prevZ2 = posZ2;
    }

    public static void setDme22() {

        dme2 = 2;

    }

    public static void setDme21() {

        dme2 = 0;

    }

    public static void teleEntity(Entity entity) {
        if (!entity.world.isRemote) {
            DimensionType type = Kathairis.KATH_DIM_TYPE;
            if (!entity.isBeingRidden() && entity.getRidingEntity() == null) {
                MinecraftServer server = entity.getServer();
                entity.setPortal(entity.getPosition());
                if (entity.timeUntilPortal > 0) {
                    entity.timeUntilPortal = 10;
                }

                else if (entity.dimension != type) {

                    // notify everyone that Kathairis is loading (because it currently takes so long)
                    // this can be removed when the world gen is fixed
                    /*if(!dimensionKathairisHasBeenLoadedBefore()) {
                        notifyAllPlayersThatKathairisIsLoading();
                    }*/

                    entity.timeUntilPortal = 10;
                    if (prevX2 == 0.0 && prevY2 == 0.0 && prevZ2 == 0.0) {
                        entity.timeUntilPortal = 10;
                        setDme21();
                        setOverworldXYZ(entity.posX, entity.posY, entity.posZ);
                        //mcServer.getPlayerList().transferPlayerToDimension(entity, KCore.DIMENSION_ID, new TeleporterMystic(mcServer.getWorld(KCore.DIMENSION_ID)));
                        entity.changeDimension(type, new TeleporterKathairis(server.getWorld(type)));
                        setTestXYZ(entity.posX, entity.posY, entity.posZ);
                        //tryToPutInPortal(entity);
                    } else if (prevX2 != 0.0 && prevY2 != 0.0 && prevZ2 != 0.0) {
                        entity.timeUntilPortal = 10;
                        setDme22();
                        setOverworldXYZ(entity.posX, entity.posY, entity.posZ);
                        entity.changeDimension(type, new TeleporterKathairis(server.getWorld(type)));
                        //tryToPutInPortal(entity);
                    }
                } else if (entity.dimension == type) {
                    entity.timeUntilPortal = 10;
                    setDme22();
                    setTestXYZ(entity.posX, entity.posY, entity.posZ);
                    entity.changeDimension(DimensionType.OVERWORLD, new TeleporterKathairis(server.getWorld(DimensionType.OVERWORLD)));
                    //tryToPutInPortal(entity);
                }
            }
        }
    }

    //FIXME TODO
    public static void tele(EntityPlayer player) {
        if (!player.world.isRemote) {
            DimensionType type = Kathairis.KATH_DIM_TYPE;
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

                    if (prevX2 == 0.0 && prevY2 == 0.0 && prevZ2 == 0.0) {
                        player1.timeUntilPortal = 10;
                        setDme21();
                        Validate.notNull(type,"type is null...");
                        WorldServer toWorld = mcServer.getWorld(type);
                        Validate.notNull(toWorld, "server kath must not be null");
                        setOverworldXYZ(player1.posX, player1.posY, player1.posZ);
                        mcServer.getPlayerList().transferEntityToWorld(player1,type,player1.getServerWorld(),toWorld,new TeleporterKathairis(toWorld));
                        player.changeDimension(type, new TeleporterKathairis(toWorld));
                        //player.changeDimension(Kathairis.kath_Dim_Type, new TeleporterKathairis(player1.getServerWorld()));
                        setTestXYZ(player1.posX, player1.posY, player1.posZ);

                    } else if (prevX2 != 0.0 && prevY2 != 0.0 && prevZ2 != 0.0) {
                        player1.timeUntilPortal = 10;
                        setDme22();
                        setOverworldXYZ(player1.posX, player1.posY, player1.posZ);
                        WorldServer toWorld = mcServer.getWorld(type);
                        mcServer.getPlayerList().transferEntityToWorld(player1,type,player1.getServerWorld(),toWorld,new TeleporterKathairis(toWorld));

                        player.changeDimension(type, new TeleporterKathairis(toWorld));

                    }

                } else if (player1.dimension == type) {

                    player1.timeUntilPortal = 10;
                    setDme22();
                    setTestXYZ(player1.posX, player1.posY, player1.posZ);
                    WorldServer toWorld = mcServer.getWorld(DimensionType.OVERWORLD);

                    mcServer.getPlayerList().transferEntityToWorld(player1,DimensionType.OVERWORLD,player1.getServerWorld(),toWorld,new TeleporterKathairis(toWorld));

                    player.changeDimension(DimensionType.OVERWORLD, new TeleporterKathairis(toWorld));

                }

            }
        }
    }

    @Override
    public void read(NBTTagCompound nbt) {
        super.read(nbt);
        prevX = nbt.getDouble(S_PLAYER_X);
        prevY = nbt.getDouble(S_PLAYER_Y);
        prevZ = nbt.getDouble(S_PLAYER_Z);
        prevX2 = nbt.getDouble(S_PLAYER_X2);
        prevY2 = nbt.getDouble(S_PLAYER_Y2);
        prevZ2 = nbt.getDouble(S_PLAYER_Z2);
        dme2 = nbt.getInt(dme);
    }

    // doesn't take worlds that have been generated but aren't in memory (only on disk)
    /*private static boolean dimensionKathairisHasBeenLoadedBefore() {
        return DimensionManager.getWorld(KCore.DIMENSION_ID, false) != null;
    }*/

    /*private static void notifyAllPlayersThatKathairisIsLoading() {
        final String message = "Loading Kathairis, expect some lag";
        Kathairis.LOGGER.warn(message);
        Arrays.stream(DimensionManager.getWorlds())
                .forEach(worldServer -> worldServer.playerEntities
                        .forEach(entityPlayer -> entityPlayer
                                .sendMessage(
                                        new TextComponentString(message)
                                )
                        )
                );
    }*/

    @Override
    public NBTTagCompound write(NBTTagCompound nbt) {
        super.write(nbt);
        nbt.putDouble(S_PLAYER_X, prevX);
        nbt.putDouble(S_PLAYER_Y, prevY);
        nbt.putDouble(S_PLAYER_Z, prevZ);
        nbt.putDouble(S_PLAYER_X2, prevX2);
        nbt.putDouble(S_PLAYER_Y2, prevY2);
        nbt.putDouble(S_PLAYER_Z2, prevZ2);
        //FIXME TODO
//        nbt.putInt(dme, Kathairis.kath_DIM_ID);

        return nbt;
    }

}
