package mod.krevik.entity;

import mod.krevik.util.MysticLootTables;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityTippedArrow;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import javax.vecmath.Vector3d;

public class EntityGaznowel extends EntityFlying implements IRangedAttackMob, IMob {

    private static final DataParameter<Boolean> SWINGING_ARMS = EntityDataManager.<Boolean>createKey(EntityGaznowel.class, DataSerializers.BOOLEAN);
    private boolean hasMovementVector=false;
    private Vector3d movementVector=new Vector3d(0,0,0);
    public EntityGaznowel(World worldIn)
    {
        super(worldIn);
        this.setSize(1F, 2.0F);
        this.experienceValue = 35;
        this.moveHelper = new EntityGaznowel.GaznowelMoveHelper(this);

    }

    public boolean getIsSwingingArms(){
        return dataManager.get(SWINGING_ARMS).booleanValue();
    }

    protected void initEntityAI()
    {
        this.targetTasks.addTask(1, new EntityAIFindEntityNearestPlayer(this));
        this.tasks.addTask(1, new EntityAIWatchClosest(this, EntityPlayer.class, 25.0F));
        this.targetTasks.addTask(2, new EntityGaznowel.AIInteractWithPlayer(this));
        this.tasks.addTask(2, new EntityGaznowel.AIWander(this));

    }

    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3000000417232513D);
        this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(4.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(30.0D);
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(20.0D);
    }

    public void attackEntityWithRangedAttack(EntityLivingBase target, float distanceFactor)
    {
        EntityArrow entityarrow = this.getArrow(distanceFactor);
        double d0 = target.posX - this.posX;
        double d1 = target.getEntityBoundingBox().minY + (double)(target.height / 3.0F) - entityarrow.posY;
        double d2 = target.posZ - this.posZ;
        double d3 = (double)MathHelper.sqrt(d0 * d0 + d2 * d2);
        entityarrow.shoot(d0, d1 + d3 * 0.20000000298023224D, d2, 1.6F, (float)(14 - this.world.getDifficulty().getDifficultyId() * 4));
        this.playSound(SoundEvents.ENTITY_SKELETON_SHOOT, 1.0F, 1.0F / (this.getRNG().nextFloat() * 0.4F + 0.8F));
        this.world.spawnEntity(entityarrow);
    }

    protected EntityArrow getArrow(float p_190726_1_)
    {
        EntityTippedArrow entitytippedarrow = new EntityTippedArrow(this.world, this);
        entitytippedarrow.setEnchantmentEffectsFromEntity(this, p_190726_1_);
        return entitytippedarrow;
    }

   /* public void setCombatTask()
    {
        if (this.world != null && !this.world.isRemote)
        {
            this.tasks.removeTask(this.aiArrowAttack);
            ItemStack itemstack = this.getHeldItemMainhand();

                int i = 20;

                this.aiArrowAttack.setAttackCooldown(i);
                this.tasks.addTask(4, this.aiArrowAttack);
        }
    }*/

   int timer=0;
    public void onUpdate()
    {
        super.onUpdate();

        if (!this.world.isRemote && this.world.getDifficulty() == EnumDifficulty.PEACEFUL)
        {
            this.setDead();
        }

        if(movementVector.x-posX<0.001&&movementVector.y-posY<0.001&&movementVector.z-posZ<0.001){
            hasMovementVector=false;
        }
        if(hasMovementVector){
            motionX=(movementVector.x-posX)*0.01f;
            motionY=(movementVector.y-posY)*0.01f;
            motionZ=(movementVector.z-posZ)*0.01f;
        }
        if(getAttackTarget()!=null){
            dataManager.set(SWINGING_ARMS,Boolean.valueOf(true));
        }else{
            dataManager.set(SWINGING_ARMS,Boolean.valueOf(false));
        }
        timer++;
        if(timer>=10){
            timer=0;
            EntityPlayer ep = world.getNearestAttackablePlayer(this,15,15);
            if(ep!=null){
                setAttackTarget(ep);
            }else{
                setAttackTarget(null);
            }
        }
    }

    BlockPos findPositionNearPlayer(EntityGaznowel gaznowel){
        BlockPos result;
        double posX=gaznowel.getRNG().nextInt(2)==0?gaznowel.getAttackTarget().posX-5-gaznowel.getRNG().nextInt(6):gaznowel.getAttackTarget().posX+5+gaznowel.getRNG().nextInt(6);
        double posY=gaznowel.getAttackTarget().posY+gaznowel.getRNG().nextInt(8);
        double posZ=gaznowel.getRNG().nextInt(2)==0?gaznowel.getAttackTarget().posZ-5-gaznowel.getRNG().nextInt(6):gaznowel.getAttackTarget().posZ+5+gaznowel.getRNG().nextInt(6);
        result=new BlockPos(posX,posY,posZ);
        if(!(gaznowel.world.isAirBlock(result)&&gaznowel.world.isAirBlock(result.up()))){
            return findPositionNearPlayer(gaznowel);
        }else{
            return result;
        }
    }

    BlockPos findSomePosition(EntityGaznowel gaznowel){
        BlockPos result;
        double posX=gaznowel.posX-5+gaznowel.getRNG().nextInt(10);
        double posZ=gaznowel.posZ-5+gaznowel.getRNG().nextInt(10);
        double posY=gaznowel.posY-5+gaznowel.getRNG().nextInt(10);
        result=new BlockPos(posX,posY,posZ);
        if(!(gaznowel.world.isAirBlock(result)&&gaznowel.world.isAirBlock(result.up()))){
            return findSomePosition(gaznowel);
        }else{
            return result;
        }
    }
    public boolean attackEntityFrom(DamageSource source, float amount)
    {
        if(getAttackTarget()!=null){
            BlockPos target = findPositionNearPlayer(this);
            movementVector = new Vector3d(target.getX(), target.getY(), target.getZ());
        }else {
            BlockPos target = findSomePosition(this);
            movementVector = new Vector3d(target.getX(), target.getY(), target.getZ());
        }
        if (this.isEntityInvulnerable(source))
        {
            return false;
        }
        else if (source.getTrueSource() instanceof EntityPlayer)
        {
            super.attackEntityFrom(source, amount);
            return true;
        }
        else
        {
            return super.attackEntityFrom(source, amount);
        }
    }

    public void onDeath(DamageSource cause){
        if(!world.isRemote) {
            EntitySkeleton skeleton = new EntitySkeleton(world);
            skeleton.setPositionAndUpdate(posX,posY,posZ);
            world.spawnEntity(skeleton);
        }
        super.onDeath(cause);
    }

    protected void setEquipmentBasedOnDifficulty(DifficultyInstance difficulty)
    {
        super.setEquipmentBasedOnDifficulty(difficulty);
        this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(Items.BOW));
    }
    @Nullable
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata) {
        livingdata = super.onInitialSpawn(difficulty, livingdata);
        this.setEquipmentBasedOnDifficulty(difficulty);
        this.setHeldItem(EnumHand.MAIN_HAND, new ItemStack(Items.BOW));
        return livingdata;
    }

    protected void entityInit()
    {
        super.entityInit();
        this.dataManager.register(SWINGING_ARMS, Boolean.valueOf(false));
    }


    public SoundCategory getSoundCategory()
    {
        return SoundCategory.HOSTILE;
    }

    protected SoundEvent getAmbientSound()
    {
        return null;
    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn)
    {
        return null;
    }

    protected SoundEvent getDeathSound()
    {
        return null;
    }

    @Nullable
    protected ResourceLocation getLootTable()
    {
        return MysticLootTables.LOOT_GAZNOWEL;
    }

    protected float getSoundVolume()
    {
        return 3.0F;
    }


    public int getMaxSpawnedInChunk()
    {
        return 1;
    }

    public void writeEntityToNBT(NBTTagCompound compound)
    {
        super.writeEntityToNBT(compound);
    }

    public void readEntityFromNBT(NBTTagCompound compound)
    {
        super.readEntityFromNBT(compound);
    }


    @Override
    public void setSwingingArms(boolean swingingArms) {
        this.dataManager.set(SWINGING_ARMS, Boolean.valueOf(swingingArms));
    }

    static class GaznowelMoveHelper extends EntityMoveHelper
    {
        private final EntityGaznowel parentEntity;
        private int courseChangeCooldown;

        public GaznowelMoveHelper(EntityGaznowel ghast)
        {
            super(ghast);
            this.parentEntity = ghast;
        }

        public void onUpdateMoveHelper()
        {
            if (this.action == EntityMoveHelper.Action.MOVE_TO)
            {
                double d0 = this.posX - this.parentEntity.posX;
                double d1 = this.posY - this.parentEntity.posY;
                double d2 = this.posZ - this.parentEntity.posZ;
                double d3 = d0 * d0 + d1 * d1 + d2 * d2;

                if (this.courseChangeCooldown-- <= 0)
                {
                    this.courseChangeCooldown += this.parentEntity.getRNG().nextInt(5) + 2;
                    d3 = (double) MathHelper.sqrt(d3);

                    if (this.isNotColliding(this.posX, this.posY, this.posZ, d3))
                    {
                        this.parentEntity.motionX += d0 / d3 * 0.1D;
                        this.parentEntity.motionY += d1 / d3 * 0.1D;
                        this.parentEntity.motionZ += d2 / d3 * 0.1D;
                    }
                    else
                    {
                        this.action = EntityMoveHelper.Action.WAIT;
                    }
                }
            }
        }

        /**
         * Checks if entity bounding box is not colliding with terrain
         */
        private boolean isNotColliding(double x, double y, double z, double p_179926_7_)
        {
            double d0 = (x - this.parentEntity.posX) / p_179926_7_;
            double d1 = (y - this.parentEntity.posY) / p_179926_7_;
            double d2 = (z - this.parentEntity.posZ) / p_179926_7_;
            AxisAlignedBB axisalignedbb = this.parentEntity.getEntityBoundingBox();

            for (int i = 1; (double)i < p_179926_7_; ++i)
            {
                axisalignedbb = axisalignedbb.offset(d0, d1, d2);

                if (!this.parentEntity.world.getCollisionBoxes(this.parentEntity, axisalignedbb).isEmpty())
                {
                    return false;
                }
            }

            return true;
        }
    }

    static class AIInteractWithPlayer extends EntityAIBase
    {
        private final EntityGaznowel gaznowel;

        public AIInteractWithPlayer(EntityGaznowel p_i45859_1_)
        {
            this.gaznowel = p_i45859_1_;
            this.setMutexBits(3);
        }

        /**
         * Returns whether the EntityAIBase should begin execution.
         */
        public boolean shouldExecute()
        {
            return gaznowel.getAttackTarget()!=null;
        }

        /**
         * Keep ticking a continuous task that has already been started
         */
        private int seeTime=0;
        private final float maxAttackDistance=10;
        private int attackTime = -1;
        private int strafingTime = -1;
        private final double moveSpeedAmp=1;
        private int attackCooldown=15;
        public void updateTask() {
            if(gaznowel.getAttackTarget()!=null) {
                gaznowel.getLookHelper().setLookPositionWithEntity(gaznowel.getAttackTarget(), 10.0F, 100F);
                gaznowel.faceEntity(gaznowel.getAttackTarget(), 1000, 1000);
                gaznowel.getLookHelper().onUpdateLook();
                if (gaznowel.getDistance(gaznowel.getAttackTarget()) > 8F || !gaznowel.hasMovementVector || gaznowel.getRNG().nextInt(100) == 0) {
                    BlockPos targetPos = findPositionNearPlayer(gaznowel);
                    gaznowel.movementVector = new Vector3d(targetPos.getX(), targetPos.getY(), targetPos.getZ());
                    gaznowel.hasMovementVector = true;
                }
                EntityLiving entity = gaznowel;
                EntityLivingBase entitylivingbase = entity.getAttackTarget();

                if (entitylivingbase != null) {
                    gaznowel.getDataManager().set(gaznowel.SWINGING_ARMS, true);
                    double d0 = entity.getDistanceSq(entitylivingbase.posX, entitylivingbase.getEntityBoundingBox().minY, entitylivingbase.posZ);
                    boolean flag = entity.getEntitySenses().canSee(entitylivingbase);
                    boolean flag1 = this.seeTime > 0;

                    if (flag != flag1) {
                        this.seeTime = 0;
                    }

                    if (flag) {
                        ++this.seeTime;
                    } else {
                        --this.seeTime;
                    }

                    if (entity.isHandActive()) {
                        if (!flag && this.seeTime < -60) {
                            entity.resetActiveHand();
                        } else if (flag) {
                            int i = entity.getItemInUseMaxCount();

                            if (i >= 15) {
                                entity.resetActiveHand();
                                ((IRangedAttackMob) entity).attackEntityWithRangedAttack(entitylivingbase, ItemBow.getArrowVelocity(i));
                                this.attackTime = this.attackCooldown;
                            }
                        }
                    } else if (--this.attackTime <= 0 && this.seeTime >= -60) {
                        entity.setActiveHand(EnumHand.MAIN_HAND);
                    }
                }
            }
        }

        BlockPos findPositionNearPlayer(EntityGaznowel gaznowel){
            BlockPos result;
            double posX=gaznowel.getRNG().nextInt(2)==0?gaznowel.getAttackTarget().posX-5-gaznowel.getRNG().nextInt(6):gaznowel.getAttackTarget().posX+5+gaznowel.getRNG().nextInt(6);
            double posY=gaznowel.getAttackTarget().posY+gaznowel.getRNG().nextInt(8);
            double posZ=gaznowel.getRNG().nextInt(2)==0?gaznowel.getAttackTarget().posZ-5-gaznowel.getRNG().nextInt(6):gaznowel.getAttackTarget().posZ+5+gaznowel.getRNG().nextInt(6);
            result=new BlockPos(posX,posY,posZ);
            if(!(gaznowel.world.isAirBlock(result)&&gaznowel.world.isAirBlock(result.up()))){
                return findPositionNearPlayer(gaznowel);
            }else{
                return result;
            }
        }
    }

    static class AIWander extends EntityAIBase
    {
        private final EntityGaznowel gaznowel;

        public AIWander(EntityGaznowel p_i45859_1_)
        {
            this.gaznowel = p_i45859_1_;
            this.setMutexBits(3);
        }

        /**
         * Returns whether the EntityAIBase should begin execution.
         */
        public boolean shouldExecute()
        {
            return gaznowel.getAttackTarget()==null;
        }

        /**
         * Keep ticking a continuous task that has already been started
         */
        public void updateTask() {

            if(!gaznowel.hasMovementVector&&gaznowel.getRNG().nextInt(500)==0){
                BlockPos targetPos = findSomePosition(gaznowel);
                gaznowel.movementVector=new Vector3d(targetPos.getX(),targetPos.getY(),targetPos.getZ());
                gaznowel.hasMovementVector=true;
                gaznowel.getLookHelper().setLookPosition((float)gaznowel.movementVector.x,(float)gaznowel.movementVector.y,(float)gaznowel.movementVector.z,40,100);
                gaznowel.getLookHelper().onUpdateLook();
            }
        }

        BlockPos findSomePosition(EntityGaznowel gaznowel){
            BlockPos result;
            double posX=gaznowel.posX-5+gaznowel.getRNG().nextInt(10);
            double posZ=gaznowel.posZ-5+gaznowel.getRNG().nextInt(10);
            double posY=gaznowel.posY-5+gaznowel.getRNG().nextInt(10);
            result=new BlockPos(posX,posY,posZ);
            if(!(gaznowel.world.isAirBlock(result)&&gaznowel.world.isAirBlock(result.up()))){
                return findSomePosition(gaznowel);
            }else{
                return result;
            }
        }
    }



}
