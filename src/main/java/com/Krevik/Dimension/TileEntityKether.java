package com.Krevik.Dimension;

import com.Krevik.Main.KCore;
import com.Krevik.Networking.KetherPacketHandler;
import com.Krevik.Networking.PacketDeathHandlerClient;
import com.Krevik.Networking.PacketDeathHandlerServer;
import com.Krevik.Networking.PacketSpawnDeathServer;

import net.minecraft.block.BlockPortal;
import net.minecraft.block.state.pattern.BlockPattern;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.server.FMLServerHandler;

public class TileEntityKether extends TileEntity
{
	
	private static final String S_PLAYER_X = "PlayerPrevX";
	private static final String S_PLAYER_Y = "PlayerPrevY";
	private static final String S_PLAYER_Z = "PlayerPrevZ";
	private static final String S_PLAYER_X2 = "PlayerPrevX2";
	private static final String S_PLAYER_Y2 = "PlayerPrevY2";
	private static final String S_PLAYER_Z2 = "PlayerPrevZ2";
	private static final String dme = "DimEnter";
	private static int offsetX;
	private static int offsetZ;	
	public static double prevX;
	public static double prevY;
	public static double prevZ;
	public static double prevX2;
	public static double prevY2;
	public static double prevZ2;
	public static int dme2;
	

	public TileEntityKether()
	{
		super();
	}
	

	@Override
	public void readFromNBT(NBTTagCompound nbt)
	{
		super.readFromNBT(nbt);
		this.prevX = nbt.getDouble(S_PLAYER_X);
		this.prevY = nbt.getDouble(S_PLAYER_Y);
		this.prevZ = nbt.getDouble(S_PLAYER_Z);
		this.prevX2 = nbt.getDouble(S_PLAYER_X2);
		this.prevY2 = nbt.getDouble(S_PLAYER_Y2);
		this.prevZ2 = nbt.getDouble(S_PLAYER_Z2);
		this.dme2 = nbt.getInteger(dme);
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt)
	{
		super.writeToNBT(nbt);
		nbt.setDouble(S_PLAYER_X, prevX);
		nbt.setDouble(S_PLAYER_Y, prevY);
		nbt.setDouble(S_PLAYER_Z, prevZ);
		nbt.setDouble(S_PLAYER_X2, prevX2);
		nbt.setDouble(S_PLAYER_Y2, prevY2);
		nbt.setDouble(S_PLAYER_Z2, prevZ2);
		nbt.setInteger(dme, KCore.DIMENSION_ID);

		return nbt;
	}

	public static void setOverworldXYZ(double posX, double posY, double posZ)
	{
		prevX = posX;
		prevY = posY;
		prevZ = posZ;
	}
	
	public static void setTestXYZ(double posX2, double posY2, double posZ2)
	{
		prevX2 = posX2;
		prevY2 = posY2;
		prevZ2 = posZ2;
	}

	public static void setDme22()
	{
		
		dme2 = KCore.DIMENSION_ID;
		
	}
	
	public static void setDme21()
	{
		
		dme2 = 0;
		
	}

	public static void teleEntity(Entity entity)
	{
		if(!entity.world.isRemote) {
			if (!entity.isBeingRidden() && entity.getRidingEntity() == null) {
				MinecraftServer server = FMLCommonHandler.instance().getMinecraftServerInstance();
				entity.setPortal(entity.getPosition());
				if (entity.timeUntilPortal > 0) {
					entity.timeUntilPortal = 10;
				} else if (entity.dimension != KCore.DIMENSION_ID) {
					entity.timeUntilPortal = 10;
					if (prevX2 == 0.0 && prevY2 == 0.0 && prevZ2 == 0.0) {
						entity.timeUntilPortal = 10;
						setDme21();
						setOverworldXYZ(entity.posX, entity.posY, entity.posZ);
						//mcServer.getPlayerList().transferPlayerToDimension(entity, KCore.DIMENSION_ID, new TeleporterMystic(mcServer.getWorld(KCore.DIMENSION_ID)));
						entity.changeDimension(KCore.DIMENSION_ID, new TeleporterMystic(server.getWorld(KCore.DIMENSION_ID)));
						setTestXYZ(entity.posX, entity.posY, entity.posZ);
					} else if (prevX2 != 0.0 && prevY2 != 0.0 && prevZ2 != 0.0) {
						entity.timeUntilPortal = 10;
						setDme22();
						setOverworldXYZ(entity.posX, entity.posY, entity.posZ);
						entity.changeDimension(KCore.DIMENSION_ID, new TeleporterMystic(server.getWorld(KCore.DIMENSION_ID)));
					}
				} else if (entity.dimension == KCore.DIMENSION_ID) {

					entity.timeUntilPortal = 10;
					setDme22();
					setTestXYZ(entity.posX, entity.posY, entity.posZ);
					entity.changeDimension(0, new TeleporterMystic(server.getWorld(KCore.DIMENSION_ID)));
				}
			}
		}
	}

	public static void tele(EntityPlayer player)
	{
		if(!player.world.isRemote) {
			if ((player.getRidingEntity() == null) && ((player instanceof EntityPlayerMP))) {

				EntityPlayerMP player1 = (EntityPlayerMP) player;
				MinecraftServer mcServer = player1.getServer();
				player1.setPortal(new BlockPos(player1.posX, player1.posY, player1.posZ));

				if (player1.timeUntilPortal > 0) {

					player1.timeUntilPortal = 10;

				} else if (player1.dimension != KCore.DIMENSION_ID) {

					player1.timeUntilPortal = 10;

					if (prevX2 == 0.0 && prevY2 == 0.0 && prevZ2 == 0.0) {
						player1.timeUntilPortal = 10;
						setDme21();
						setOverworldXYZ(player1.posX, player1.posY, player1.posZ);
						mcServer.getPlayerList().transferPlayerToDimension(player1, KCore.DIMENSION_ID, new TeleporterMystic(mcServer.getWorld(KCore.DIMENSION_ID)));
						setTestXYZ(player1.posX, player1.posY, player1.posZ);


					} else if (prevX2 != 0.0 && prevY2 != 0.0 && prevZ2 != 0.0) {
						player1.timeUntilPortal = 10;
						setDme22();
						setOverworldXYZ(player1.posX, player1.posY, player1.posZ);
						mcServer.getPlayerList().transferPlayerToDimension(player1, KCore.DIMENSION_ID, new TeleporterMystic(mcServer.getWorld(KCore.DIMENSION_ID)));


					}

				} else if (player1.dimension == KCore.DIMENSION_ID) {

					player1.timeUntilPortal = 10;
					setDme22();
					setTestXYZ(player1.posX, player1.posY, player1.posZ);
					mcServer.getPlayerList().transferPlayerToDimension(player1, 0, new TeleporterMystic(mcServer.getWorld(0)));

				}

			}
		}
	}
}