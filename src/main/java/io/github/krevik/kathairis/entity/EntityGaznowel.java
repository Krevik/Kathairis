package io.github.krevik.kathairis.entity;

import io.github.krevik.kathairis.init.ModEntities;
import io.github.krevik.kathairis.util.KatharianLootTables;
import net.minecraft.client.renderer.Vector3d;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.controller.FlyingMovementController;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.monster.SkeletonEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.BowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

import javax.vecmath.Vector3d;
import java.util.EnumSet;

public class EntityGaznowel extends FlyingEntity implements IRangedAttackMob, IMob {

    private static final DataParameter<Boolean> SWINGING_ARMS = EntityDataManager.createKey(EntityGaznowel.class, DataSerializers.BOOLEAN);
    private boolean hasMovementVector=false;
    private Vector3d movementVector=new Vector3d(0,0,0);
    public EntityGaznowel(World worldIn)
    {
        super(ModEntities.GAZNOWEL,worldIn);
        this.experienceValue = 35;
        this.moveController = new GaznowelMoveHelper(this);

    }

    @Override
    protected ResourceLocation getLootTable() {
        return KatharianLootTables.LOOT_GAZNOWEL;
    }

    public EntityGaznowel(EntityType<EntityGaznowel> type, World world) {
        super(type, world);
    }


    public boolean getIsSwingingArms(){
        return getDataManager().get(SWINGING_ARMS).booleanValue();
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(1, new NearestAttackableTargetGoal<PlayerEntity>(this, PlayerEntity.class,true));
        this.goalSelector.addGoal(1, new LookAtGoal(this, PlayerEntity.class, 25.0F));
        this.goalSelector.addGoal(2, new AIInteractWithPlayer(this));
        this.goalSelector.addGoal(2, new AIWander(this));
    }


    @Override
    protected void registerAttributes()
    {
        super.registerAttributes();
        this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3000000417232513D);
        this.getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(4.0D);
        this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(30.0D);
        this.getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(20.0D);
    }

    @Override
    public void attackEntityWithRangedAttack(LivingEntity target, float distanceFactor)
    {
        ArrowEntity entityarrow = this.getArrow(distanceFactor);
        double d0 = target.getPosition().getX() - this.getPosition().getX();
        double d1 = target.getBoundingBox().minY + (double)(target.getHeight() / 3.0F) - entityarrow.getPosition().getY();
        double d2 = target.getPosition().getZ() - this.getPosition().getZ();
        double d3 = (double) MathHelper.sqrt(d0 * d0 + d2 * d2);
        entityarrow.shoot(d0, d1 + d3 * 0.20000000298023224D, d2, 1.6F, (float)(14 - this.world.getDifficulty().getId() * 4));
        this.playSound(SoundEvents.ENTITY_SKELETON_SHOOT, 1.0F, 1.0F / (this.getRNG().nextFloat() * 0.4F + 0.8F));
        this.world.addEntity(entityarrow);
    }

    protected ArrowEntity getArrow(float p_190726_1_)
    {
        ArrowEntity entitytippedarrow = new ArrowEntity(this.world, this);
        entitytippedarrow.setEnchantmentEffectsFromEntity(this, p_190726_1_);
        return entitytippedarrow;
    }

   int timer=0;
    @Override
    public void tick()
    {
        super.tick();

        if (!this.world.isRemote && this.world.getDifficulty() == Difficulty.PEACEFUL)
        {
            this.onKillCommand();
        }

        if(movementVector.x-getPosition().getX()<0.001&&movementVector.y-getPosition().getY()<0.001&&movementVector.z-getPosition().getZ()<0.001){
            hasMovementVector=false;
        }
        if(hasMovementVector){
            setMotion(new Vec3d((movementVector.x-getPosition().getX())*0.01f,(movementVector.y-getPosition().getY())*0.01f,(movementVector.z-getPosition().getZ())*0.01f));
        }
        if(getAttackTarget()!=null){
            getDataManager().set(SWINGING_ARMS,Boolean.valueOf(true));
        }else{
            getDataManager().set(SWINGING_ARMS,Boolean.valueOf(false));
        }
        timer++;
        if(timer>=10){
            timer=0;
            PlayerEntity ep = world.getClosestPlayer(this,15);
            if(ep!=null){
                setAttackTarget(ep);
            }else{
                setAttackTarget(null);
            }
        }
    }

    BlockPos findPositionNearPlayer(EntityGaznowel gaznowel){
        BlockPos result;
        double getPosition().getX()=gaznowel.getRNG().nextInt(2)==0?gaznowel.getAttackTarget().getPosition().getX()-5-gaznowel.getRNG().nextInt(6):gaznowel.getAttackTarget().getPosition().getX()+5+gaznowel.getRNG().nextInt(6);
        double getPosition().getY()=gaznowel.getAttackTarget().getPosition().getY()+gaznowel.getRNG().nextInt(8);
        double getPosition().getZ()=gaznowel.getRNG().nextInt(2)==0?gaznowel.getAttackTarget().getPosition().getZ()-5-gaznowel.getRNG().nextInt(6):gaznowel.getAttackTarget().getPosition().getZ()+5+gaznowel.getRNG().nextInt(6);
        result=new BlockPos(getPosition().getX(),getPosition().getY(),getPosition().getZ());
        if(!(gaznowel.world.isAirBlock(result)&&gaznowel.world.isAirBlock(result.up()))){
            return findPositionNearPlayer(gaznowel);
        }else{
            return result;
        }
    }

    BlockPos findSomePosition(EntityGaznowel gaznowel){
        BlockPos result;
        double getPosition().getX()=gaznowel.getPosition().getX()-5+gaznowel.getRNG().nextInt(10);
        double getPosition().getZ()=gaznowel.getPosition().getZ()-5+gaznowel.getRNG().nextInt(10);
        double getPosition().getY()=gaznowel.getPosition().getY()-5+gaznowel.getRNG().nextInt(10);
        result=new BlockPos(getPosition().getX(),getPosition().getY(),getPosition().getZ());
        if(!(gaznowel.world.isAirBlock(result)&&gaznowel.world.isAirBlock(result.up()))){
            return findSomePosition(gaznowel);
        }else{
            return result;
        }
    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float amount)
    {
        if(getAttackTarget()!=null){
            BlockPos target = findPositionNearPlayer(this);
            movementVector = new Vector3d(target.getX(), target.getY(), target.getZ());
        }else {
            BlockPos target = findSomePosition(this);
            movementVector = new Vector3d(target.getX(), target.getY(), target.getZ());
        }
        if (this.isInvulnerableTo(source))
        {
            return false;
        }
        else if (source.getTrueSource() instanceof PlayerEntity)
        {
            super.attackEntityFrom(source, amount);
            return true;
        }
        else
        {
            return super.attackEntityFrom(source, amount);
        }
    }

    @Override
    public void onDeath(DamageSource cause){
        if(!world.isRemote) {
            SkeletonEntity skeleton = new SkeletonEntity(EntityType.SKELETON,world);
            skeleton.setPositionAndUpdate(getPosition().getX(),getPosition().getY(),getPosition().getZ());
            world.addEntity(skeleton);
        }
        super.onDeath(cause);
    }

    @Override
    protected void setEquipmentBasedOnDifficulty(DifficultyInstance difficulty)
    {
        super.setEquipmentBasedOnDifficulty(difficulty);
        this.setItemStackToSlot(EquipmentSlotType.MAINHAND, new ItemStack(Items.BOW));
    }

    /*@Nullable
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata) {
        livingdata = super.onInitialSpawn(difficulty, livingdata);
        this.setEquipmentBasedOnDifficulty(difficulty);
        this.setHeldItem(EnumHand.MAIN_HAND, new ItemStack(Items.BOW));
        return livingdata;
    }*/

    @Override
    protected void registerData()
    {
        super.registerData();
        this.getDataManager().register(SWINGING_ARMS, Boolean.valueOf(false));
    }

    @Override
    public SoundCategory getSoundCategory()
    {
        return SoundCategory.HOSTILE;
    }


    @Override
    protected float getSoundVolume()
    {
        return 3.0F;
    }

    @Override
    public int getMaxSpawnedInChunk()
    {
        return 1;
    }


    public void setSwingingArms(boolean swingingArms) {
        this.getDataManager().set(SWINGING_ARMS, Boolean.valueOf(swingingArms));
    }

    static class GaznowelMoveHelper extends FlyingMovementController
    {
        private final EntityGaznowel parentEntity;
        private int courseChangeCooldown;

        public GaznowelMoveHelper(EntityGaznowel ghast)
        {
            super(ghast);
            this.parentEntity = ghast;
        }

        @Override
        public void tick()
        {
            if (this.action == Action.MOVE_TO)
            {
                double d0 = this.getPosition().getX() - this.parentEntity.getPosition().getX();
                double d1 = this.getPosition().getY() - this.parentEntity.getPosition().getY();
                double d2 = this.getPosition().getZ() - this.parentEntity.getPosition().getZ();
                double d3 = d0 * d0 + d1 * d1 + d2 * d2;

                if (this.courseChangeCooldown-- <= 0)
                {
                    this.courseChangeCooldown += this.parentEntity.getRNG().nextInt(5) + 2;
                    d3 = (double) MathHelper.sqrt(d3);

                    if (this.isNotColliding(this.getPosition().getX(), this.getPosition().getY(), this.getPosition().getZ(), d3))
                    {
                        parentEntity.setMotion(new Vec3d(d0 / d3 * 0.1D,d1 / d3 * 0.1D,d2 / d3 * 0.1D));
                    }
                    else
                    {
                        this.action = Action.WAIT;
                    }
                }
            }
        }

        private boolean isNotColliding(double x, double y, double z, double p_179926_7_)
        {
            double d0 = (x - this.parentEntity.getPosition().getX()) / p_179926_7_;
            double d1 = (y - this.parentEntity.getPosition().getY()) / p_179926_7_;
            double d2 = (z - this.parentEntity.getPosition().getZ()) / p_179926_7_;
            AxisAlignedBB axisalignedbb = this.parentEntity.getBoundingBox();

            for (int i = 1; (double)i < p_179926_7_; ++i)
            {
                axisalignedbb = axisalignedbb.offset(d0, d1, d2);

                if (!this.parentEntity.world.isCollisionBoxesEmpty(this.parentEntity, axisalignedbb))
                {
                    return false;
                }
            }

            return true;
        }
    }

    static class AIInteractWithPlayer extends Goal
    {
        private final EntityGaznowel gaznowel;

        public AIInteractWithPlayer(EntityGaznowel p_i45859_1_)
        {
            this.gaznowel = p_i45859_1_;
            this.setMutexFlags(EnumSet.of(Flag.JUMP, Flag.MOVE, Flag.LOOK, Flag.TARGET));
        }

        @Override
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

        @Override
        public void tick() {
            if(gaznowel.getAttackTarget()!=null) {
                gaznowel.getLookController().setLookPositionWithEntity(gaznowel.getAttackTarget(), 10.0F, 100F);
                gaznowel.faceEntity(gaznowel.getAttackTarget(), 1000, 1000);
                gaznowel.getLookController().tick();
                if (gaznowel.getDistance(gaznowel.getAttackTarget()) > 8F || !gaznowel.hasMovementVector || gaznowel.getRNG().nextInt(100) == 0) {
                    BlockPos targetPos = findPositionNearPlayer(gaznowel);
                    gaznowel.movementVector = new Vector3d(targetPos.getX(), targetPos.getY(), targetPos.getZ());
                    gaznowel.hasMovementVector = true;
                }
                EntityGaznowel entity = gaznowel;
                LivingEntity entitylivingbase = entity.getAttackTarget();

                if (entitylivingbase != null) {
                    gaznowel.getDataManager().set(SWINGING_ARMS, true);
                    double d0 = entity.getDistanceSq(entitylivingbase.getPosition().getX(), entitylivingbase.getBoundingBox().minY, entitylivingbase.getPosition().getZ());
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
                                ((IRangedAttackMob) entity).attackEntityWithRangedAttack(entitylivingbase, BowItem.getArrowVelocity(i));
                                this.attackTime = this.attackCooldown;
                            }
                        }
                    } else if (--this.attackTime <= 0 && this.seeTime >= -60) {
                        entity.setActiveHand(Hand.MAIN_HAND);
                    }
                }
            }
        }

        BlockPos findPositionNearPlayer(EntityGaznowel gaznowel){
            BlockPos result;
            double getPosition().getX()=gaznowel.getRNG().nextInt(2)==0?gaznowel.getAttackTarget().getPosition().getX()-5-gaznowel.getRNG().nextInt(6):gaznowel.getAttackTarget().getPosition().getX()+5+gaznowel.getRNG().nextInt(6);
            double getPosition().getY()=gaznowel.getAttackTarget().getPosition().getY()+gaznowel.getRNG().nextInt(8);
            double getPosition().getZ()=gaznowel.getRNG().nextInt(2)==0?gaznowel.getAttackTarget().getPosition().getZ()-5-gaznowel.getRNG().nextInt(6):gaznowel.getAttackTarget().getPosition().getZ()+5+gaznowel.getRNG().nextInt(6);
            result=new BlockPos(getPosition().getX(),getPosition().getY(),getPosition().getZ());
            if(!(gaznowel.world.isAirBlock(result)&&gaznowel.world.isAirBlock(result.up()))){
                return findPositionNearPlayer(gaznowel);
            }else{
                return result;
            }
        }
    }

    static class AIWander extends Goal
    {
        private final EntityGaznowel gaznowel;

        public AIWander(EntityGaznowel p_i45859_1_)
        {
            this.gaznowel = p_i45859_1_;
            this.setMutexFlags(EnumSet.of(Flag.JUMP, Flag.MOVE, Flag.LOOK, Flag.TARGET));
        }

        @Override
        public boolean shouldExecute()
        {
            return gaznowel.getAttackTarget()==null;
        }

        @Override
        public void tick() {

            if(!gaznowel.hasMovementVector&&gaznowel.getRNG().nextInt(500)==0){
                BlockPos targetPos = findSomePosition(gaznowel);
                gaznowel.movementVector=new Vector3d(targetPos.getX(),targetPos.getY(),targetPos.getZ());
                gaznowel.hasMovementVector=true;
                gaznowel.getLookController().setLookPosition((float)gaznowel.movementVector.x,(float)gaznowel.movementVector.y,(float)gaznowel.movementVector.z,40,100);
                gaznowel.getLookController().tick();
            }
        }

        BlockPos findSomePosition(EntityGaznowel gaznowel){
            BlockPos result;
            double getPosition().getX()=gaznowel.getPosition().getX()-5+gaznowel.getRNG().nextInt(10);
            double getPosition().getZ()=gaznowel.getPosition().getZ()-5+gaznowel.getRNG().nextInt(10);
            double getPosition().getY()=gaznowel.getPosition().getY()-5+gaznowel.getRNG().nextInt(10);
            result=new BlockPos(getPosition().getX(),getPosition().getY(),getPosition().getZ());
            if(!(gaznowel.world.isAirBlock(result)&&gaznowel.world.isAirBlock(result.up()))){
                return findSomePosition(gaznowel);
            }else{
                return result;
            }
        }
    }



}
