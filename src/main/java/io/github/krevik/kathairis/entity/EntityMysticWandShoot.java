package io.github.krevik.kathairis.entity;

import io.github.krevik.kathairis.init.ModEntities;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ProjectileHelper;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.network.IPacket;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceContext;
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
    }

    public EntityMysticWandShoot(EntityType<EntityMysticWandShoot> type, World world) {
        super(type, world);
    }



    public LivingEntity shootingEntity;
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
            RayTraceResult raytraceresult = ProjectileHelper.func_221266_a(this, true, this.ticksInAir >= 25, this.shootingEntity, RayTraceContext.BlockMode.COLLIDER);
            if (raytraceresult != null && !net.minecraftforge.event.ForgeEventFactory.onProjectileImpact(this, raytraceresult)) {
                this.onImpact(raytraceresult);
            }

            this.posX += this.getMotion().getX()*2;
            this.posY += this.getMotion().getY()*2;
            this.posZ += this.getMotion().getZ()*2;
            ProjectileHelper.rotateTowardsMovement(this, 0.2F);
            float f = this.getMotionFactor();
            if (this.isInWater()) {
                for(int i = 0; i < 4; ++i) {
                    float f1 = 0.25F;
                    this.world.addParticle(ParticleTypes.BUBBLE, this.posX - this.getMotion().getX() * 0.25D, this.posY - this.getMotion().getY() * 0.25D, this.posZ - this.getMotion().getZ() * 0.25D, this.getMotion().getX(), this.getMotion().getY(), this.getMotion().getZ());
                }

                f = 0.8F;
            }

            if(getMotion().getX()==0.0f&&getMotion().getY()==0.0f&&getMotion().getZ()==0.0f){
                this.remove();
            }
                    for(int c=0;c<=3;c++) {
                        //TODO
                        //world.addParticle((IParticleData) ModParticles.MYSTIC_WAND_SHOOT, this.posX, this.posY, this.posZ, 0, -0.01f, 0);
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
        if(result.hitInfo instanceof Entity){
            Entity entity = (Entity) result.hitInfo;
            if(entity!=null&&entity.isAlive()){
                if(!entity.isInvulnerableTo(DamageSource.MAGIC)) {
                    entity.attackEntityFrom(DamageSource.causeIndirectMagicDamage(this, this.shootingEntity), 10.0F);
                }
            }
        }else{
            remove();
        }
    }

    @Override
    public void writeAdditional(CompoundNBT compound) {
        compound.put("direction", this.newDoubleNBTList(new double[]{this.getMotion().getX(), this.getMotion().getY(), this.getMotion().getZ()}));
        compound.putInt("life", this.ticksAlive);
    }

    @Override
    public void readAdditional(CompoundNBT compound) {

        this.ticksAlive = compound.getInt("life");
        if (compound.contains("direction", 9) && compound.getList("direction", 6).size() == 3) {
            ListNBT nbttaglist1 = compound.getList("direction", 6);
            double mx = nbttaglist1.getDouble(0);
            double my = nbttaglist1.getDouble(1);
            double mz = nbttaglist1.getDouble(2);
            setMotion(new Vec3d(mx,my,mz));
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
    public IPacket<?> createSpawnPacket() {
        return null;
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
                    setMotion(vec3d);
                }

                if (source.getTrueSource() instanceof LivingEntity) {
                    this.shootingEntity = (LivingEntity)source.getTrueSource();
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
