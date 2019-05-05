package io.github.krevik.kathairis.entity;

import io.github.krevik.kathairis.Kathairis;
import io.github.krevik.kathairis.init.ModEntities;
import io.github.krevik.kathairis.init.ModParticles;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.entity.projectile.EntitySmallFireball;
import net.minecraft.entity.projectile.ProjectileHelper;
import net.minecraft.init.Blocks;
import net.minecraft.init.Particles;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.particles.IParticleData;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class EntityMysticWandShoot extends Entity {

    @Override
    protected void registerData() {

    }

    public EntityMysticWandShoot(World worldIn) {
        super(ModEntities.MYSTIC_WAND_SHOOT, worldIn);
        setSize(1,1);
    }


    public EntityLivingBase shootingEntity;
    private int ticksAlive;
    private int ticksInAir;

    @OnlyIn(Dist.CLIENT)
    @Override
    public boolean isInRangeToRenderDist(double distance) {
        double d0 = this.getBoundingBox().getAverageEdgeLength() * 8.0D;
        if (Double.isNaN(d0)) {
            d0 = 4.0D;
        }

        d0 = d0 * 64.0D;
        return distance < d0 * d0;
    }

    @Override
    public void tick() {
        if (this.world.isRemote || (this.shootingEntity == null || !this.shootingEntity.removed) && this.world.isBlockLoaded(new BlockPos(this)) && ticksInAir<10000) {
            super.tick();

            ++this.ticksInAir;
            RayTraceResult raytraceresult = ProjectileHelper.forwardsRaycast(this, true, this.ticksInAir >= 25, this.shootingEntity);
            if (raytraceresult != null && !net.minecraftforge.event.ForgeEventFactory.onProjectileImpact(this, raytraceresult)) {
                this.onImpact(raytraceresult);
            }

            this.posX += this.motionX*2;
            this.posY += this.motionY*2;
            this.posZ += this.motionZ*2;
            ProjectileHelper.rotateTowardsMovement(this, 0.2F);
            float f = this.getMotionFactor();
            if (this.isInWater()) {
                for(int i = 0; i < 4; ++i) {
                    float f1 = 0.25F;
                    this.world.addParticle(Particles.BUBBLE, this.posX - this.motionX * 0.25D, this.posY - this.motionY * 0.25D, this.posZ - this.motionZ * 0.25D, this.motionX, this.motionY, this.motionZ);
                }

                f = 0.8F;
            }

            if(motionX==0.0f&&motionY==0.0f&&motionZ==0.0f){
                this.remove();
            }
                    for(int c=0;c<=3;c++) {
                        world.addParticle((IParticleData) ModParticles.MYSTIC_WAND_SHOOT, this.posX, this.posY, this.posZ, 0, -0.01f, 0);
                    }
            this.setPosition(this.posX, this.posY, this.posZ);
        } else {
            this.remove();
        }
    }

    protected float getMotionFactor() {
        return 0.95F;
    }


    protected void onImpact(RayTraceResult result){
        if(result.entity!=null&&result.entity.isAlive()){
            if(!result.entity.isInvulnerableTo(DamageSource.MAGIC)) {
                result.entity.attackEntityFrom(DamageSource.causeIndirectMagicDamage(this, this.shootingEntity), 10.0F);
            }
        }else{
            remove();
        }
    }

    @Override
    public void writeAdditional(NBTTagCompound compound) {
        compound.put("direction", this.newDoubleNBTList(new double[]{this.motionX, this.motionY, this.motionZ}));
        compound.putInt("life", this.ticksAlive);
    }

    @Override
    public void readAdditional(NBTTagCompound compound) {

        this.ticksAlive = compound.getInt("life");
        if (compound.contains("direction", 9) && compound.getList("direction", 6).size() == 3) {
            NBTTagList nbttaglist1 = compound.getList("direction", 6);
            this.motionX = nbttaglist1.getDouble(0);
            this.motionY = nbttaglist1.getDouble(1);
            this.motionZ = nbttaglist1.getDouble(2);
        } else {
            this.remove();
        }

    }

    /**
     * Returns true if other Entities should be prevented from moving through this Entity.
     */
    @Override
    public boolean canBeCollidedWith() {
        return true;
    }

    @Override
    public float getCollisionBorderSize() {
        return 1.0F;
    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float amount) {
        if (this.isInvulnerableTo(source)) {
            return false;
        } else {
            this.markVelocityChanged();
            if (source.getTrueSource() != null) {
                Vec3d vec3d = source.getTrueSource().getLookVec();
                if (vec3d != null) {
                    this.motionX = vec3d.x;
                    this.motionY = vec3d.y;
                    this.motionZ = vec3d.z;
                }

                if (source.getTrueSource() instanceof EntityLivingBase) {
                    this.shootingEntity = (EntityLivingBase)source.getTrueSource();
                }

                return true;
            } else {
                return false;
            }
        }
    }

    @Override
    public float getBrightness() {
        return 0.0F;
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public int getBrightnessForRender() {
        return 0;
    }
}
