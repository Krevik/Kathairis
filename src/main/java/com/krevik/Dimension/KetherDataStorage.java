package com.krevik.Dimension;

import javax.annotation.Nullable;

import com.krevik.Main.KCore;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.storage.MapStorage;
import net.minecraft.world.storage.WorldSavedData;

public class KetherDataStorage extends WorldSavedData
{
	private static final String DATA_NAME = KCore.MODID + "_data";
	private boolean isDeathSpawned;
	private boolean isDeathFighting;
	private boolean isDeathDefeated;
	private int sandstormTime;
	private boolean isSandstorm;
	private double sandstormX;
	private double sandstormZ;

	public KetherDataStorage() 
	{
		super(DATA_NAME);
		isDeathSpawned=false;
		isDeathFighting=false;
		isDeathDefeated=false;
		isSandstorm=false;
		sandstormTime=0;
		sandstormX=0;
		sandstormZ=0;
	}


	public KetherDataStorage(String name) 
	{
		super(name);
	}
	

	public static KetherDataStorage getDataInstance(World world) 
	{
		MapStorage storage = world.getMapStorage();
		KetherDataStorage instance = (KetherDataStorage) storage.getOrLoadData(KetherDataStorage.class, DATA_NAME);
		if (instance == null) {
			instance = new KetherDataStorage();
			storage.setData(DATA_NAME, instance);
		}
		return (KetherDataStorage) storage.getOrLoadData(KetherDataStorage.class, DATA_NAME);
	}
	
	public static KetherDataStorage initialise() {
		KetherDataStorage instance = new KetherDataStorage();
		return instance;
	}
	
	public void setIsSandstorm(boolean b)
	{
		this.isSandstorm=b;
		markDirty();
	}
	
	public void setSandstormX(double c)
	{
		this.sandstormX=c;
		markDirty();
	}
	public void setSandstormZ(double c)
	{
		this.sandstormZ=c;
		markDirty();
	}
	
	public void setSandstormTime(int c)
	{
		this.sandstormTime=c;
		markDirty();
	}

	public void setIsDeathSpawned(boolean b)
	{
		this.isDeathSpawned=b;
		markDirty();
	}
	
	public void setIsDeathFighting(boolean b)
	{
		this.isDeathFighting=b;
		markDirty();
	}
	
	public void setIsDeathDefeated(boolean b)
	{
		this.isDeathDefeated=b;
		markDirty();
	}
	
	@Nullable
	public Boolean getIsSandstorm()
	{
		return this.isSandstorm;
	}
	
	@Nullable
	public int getSandstormTime()
	{
		return this.sandstormTime;
	}
	
	@Nullable
	public double getSandstormX()
	{
		return this.sandstormX;
	}
	
	@Nullable
	public double getSandstormZ()
	{
		return this.sandstormZ;
	}
	
	@Nullable
	public Boolean getIsDeathSpawned()
	{
		return this.isDeathSpawned;
	}
	
	@Nullable
	public Boolean getIsDeathDefeated()
	{
		return this.isDeathDefeated;
	}
	
	@Nullable
	public Boolean getIsDeathFighting()
	{
		return this.isDeathFighting;
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) 
	{
		this.isDeathSpawned=nbt.getBoolean("is");
		this.isDeathFighting=nbt.getBoolean("fighting");
		this.isDeathDefeated=nbt.getBoolean("defeated");
		this.isSandstorm=nbt.getBoolean("sandstorm");
		this.sandstormTime=nbt.getInteger("sandstormtime");
		this.sandstormX=nbt.getDouble("sandstormx");
		this.sandstormZ=nbt.getDouble("sandstormz");

	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) 
	{	
		nbt.setBoolean("is", isDeathSpawned);
		nbt.setBoolean("fighting", isDeathFighting);
		nbt.setBoolean("defeated", isDeathDefeated);
		nbt.setBoolean("sandstorm", isSandstorm);
		nbt.setInteger("sandstormtime", sandstormTime);
		nbt.setDouble("sandstormx", sandstormX);
		nbt.setDouble("sandstormz", sandstormZ);

		return nbt;
	}
}