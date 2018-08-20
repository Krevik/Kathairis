package com.Krevik.Dimension;

import net.minecraft.util.math.Vec3d;

public class FallingStar {

	private int id;
	private double posX;
	private double posY;
	private double posZ;
	
	private float motionX;
	private float motionY;
	private float motionZ;
	
	public FallingStar(int id,double X,double Y,double Z,float mX,float mY,float mZ) {
		this.id=id;
		posX=X;
		posY=Y;
		posZ=Z;
		motionX=mX;
		motionY=mY;
		motionZ=mZ;
	}
	
	public int getId() {
		return id;
	}
	
	public Vec3d getPos() {
		return new Vec3d(posX,posY,posZ);
	}
	
	public Vec3d getMotion() {
		return new Vec3d(motionX,motionY,motionZ);
	}
	
	public void update() {
		posX+=motionX/10;
		posY+=motionY/10;
		posZ+=motionZ/10;
	}
	
}
