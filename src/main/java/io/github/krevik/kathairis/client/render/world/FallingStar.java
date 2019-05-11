package io.github.krevik.kathairis.client.render.world;

import net.minecraft.util.math.Vec3d;

public class FallingStar {

    private int id;
    private double posX;
    private double posY;
    private double posZ;

    private float motionX;
    private float motionY;
    private float motionZ;

    private long Seed;
    private int age;

    public FallingStar(int id,double X,double Y,double Z,float mX,float mY,float mZ,long Seed1) {
        this.id=id;
        posX=X;
        posY=Y;
        posZ=Z;
        motionX=mX;
        motionY=mY;
        motionZ=mZ;
        Seed=Seed1;
        age=0;
    }

    public int getId() {
        return id;
    }

    public long getSeed() {
        return Seed;
    }

    public Vec3d getPos() {
        return new Vec3d(posX,posY,posZ);
    }

    public Vec3d getMotion() {
        return new Vec3d(motionX,motionY,motionZ);
    }

    public int getAge(){
        return age;
    }

    public void update() {
        posX+=motionX;
        posY+=motionY;
        posZ+=motionZ;
        age++;
    }

}
