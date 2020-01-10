package io.github.krevik.kathairis.entity;

import io.github.krevik.kathairis.Kathairis;
import io.github.krevik.kathairis.enchantement.KathairisEnchantments;
import io.github.krevik.kathairis.init.ModEntities;
import io.github.krevik.kathairis.util.FunctionHelper;
import io.github.krevik.kathairis.util.KatharianLootTables;
import net.minecraft.block.Blocks;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.controller.FlyingMovementController;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.Difficulty;
import net.minecraft.world.World;
import net.minecraft.world.gen.Heightmap;

import java.util.EnumSet;
import java.util.Map;

public class EntityPhasm extends FlyingEntity implements IMob {

    private static final DataParameter<Boolean> SWINGING_ARMS = EntityDataManager.createKey(EntityPhasm.class, DataSerializers.BOOLEAN);
    public EntityPhasm(World worldIn)
    {
        super(ModEntities.PHASM,worldIn);
        this.experienceValue = 35;
        this.moveController = new PhasmMoveHelper(this);

    }

    public EntityPhasm(EntityType<EntityPhasm> type, World world) {
        super(type, world);
    }


    public boolean getIsSwingingArms(){
        return getDataManager().get(SWINGING_ARMS).booleanValue();
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(2, new LookAtGoal(this, PlayerEntity.class, 25.0F));
        this.goalSelector.addGoal(5, new AIWander(this));
        this.goalSelector.addGoal(2,new AILookAtTheFuckingPlayer(this));
        this.goalSelector.addGoal(3, new AIInteractWithPlayer(this));
    }

    @Override
    protected void registerAttributes()
    {
        super.registerAttributes();
        this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.4000000417232513D);
        this.getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(4.0D);
        this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0D);
        this.getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(5.0D);
    }


    int timer=0;
    int ticker=100;
    FunctionHelper helper = Kathairis.getHelper();

    @Override
    public void tick()
    {
        super.tick();

        if (!this.world.isRemote && this.world.getDifficulty() == Difficulty.PEACEFUL)
        {
            this.onKillCommand();
        }

        if(getAttackTarget()!=null){
            getDataManager().set(SWINGING_ARMS,Boolean.valueOf(true));
        }else{
            getDataManager().set(SWINGING_ARMS,Boolean.valueOf(false));
        }
        timer++;
        if(timer>=10) {
            timer = 0;
            PlayerEntity ep = world.getClosestPlayer(this, 5);
            if (ep != null) {
                setAttackTarget(ep);
            }
        }
        if(getAttackTarget()instanceof PlayerEntity){
            PlayerEntity player = (PlayerEntity) getAttackTarget();
            if(player.isCreative()||!player.isAlive()||player==null){
                setAttackTarget(null);
            }

        }
        if(getAttackTarget()!=null) {
            double d0 = getDistance(getAttackTarget());
            if(d0<1.5f){
                attackEntityAsMob(getAttackTarget(),5);
            }
        }
        if(ticker>2) {ticker=0;}
        ticker++;
    }

    /*@SideOnly(Side.CLIENT)
    public void onLivingUpdate(){
        super.onLivingUpdate();
            if (ticker >= 1) {
                double d0 = ((float) getPosition().getX() + (getEntityBoundingBox().maxX - getEntityBoundingBox().minX) / 2) - 0.5;
                double d1 = ((float) getPosition().getY() + (getEntityBoundingBox().maxY - getEntityBoundingBox().minY) / 2);
                double d2 = ((float) getPosition().getZ() + (getEntityBoundingBox().maxZ - getEntityBoundingBox().minZ) / 2) - 0.5;
                Particle theParticle = new DynamicParticle(
                        ParticlesFactory.TWINKLINGPARTICLE,
                        world,
                        d0, d1, d2,
                        0, 0, 0)
                        .setRotationSpeed(0F)
                        .setLifeSpan(1)
                        .setGravity(0F)
                        .setScale(15F)
                        .setInitialAlpha(0.5F)
                        .setFinalAlpha(0.1F).setEnableDepth(true);
                Minecraft.getMinecraft().effectRenderer.addEffect(theParticle);
            }
    }*/

    @Override
    protected ResourceLocation getLootTable() {
        return KatharianLootTables.LOOT_PHASM;
    }

    public boolean attackEntityAsMob(Entity entityIn, float damage)
    {
        float f = 5;
        int i = 0;

        if (entityIn instanceof LivingEntity)
        {
            f += EnchantmentHelper.getModifierForCreature(this.getHeldItemMainhand(), ((LivingEntity)entityIn).getCreatureAttribute());
            i += EnchantmentHelper.getKnockbackModifier(this);
        }

        boolean flag = entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), f);

        if (flag)
        {
            if (i > 0 && entityIn instanceof LivingEntity)
            {
                ((LivingEntity)entityIn).knockBack(this, (float)i * 0.5F, (double) MathHelper.sin(this.rotationYaw * 0.017453292F), (double)(-MathHelper.cos(this.rotationYaw * 0.017453292F)));
                setMotionMultiplier(Blocks.AIR.getDefaultState(),new Vec3d(0.6D,1,0.6D));
            }

            int j = EnchantmentHelper.getFireAspectModifier(this);

            if (j > 0)
            {
                entityIn.setFire(j * 4);
            }

            if (entityIn instanceof PlayerEntity)
            {
                PlayerEntity entityplayer = (PlayerEntity)entityIn;
                ItemStack itemstack = this.getHeldItemMainhand();
                ItemStack itemstack1 = entityplayer.isHandActive() ? entityplayer.getActiveItemStack() : ItemStack.EMPTY;

                if (!itemstack.isEmpty() && !itemstack1.isEmpty() && itemstack.getItem().canDisableShield(itemstack, itemstack1, entityplayer, this) && itemstack1.getItem().isShield(itemstack1, entityplayer))
                {
                    float f1 = 0.25F + (float) EnchantmentHelper.getEfficiencyModifier(this) * 0.05F;

                    if (this.rand.nextFloat() < f1)
                    {
                        entityplayer.getCooldownTracker().setCooldown(itemstack1.getItem(), 100);
                        this.world.setEntityState(entityplayer, (byte)30);
                    }
                }
            }

            this.applyEnchantments(this, entityIn);
        }

        return flag;
    }



    @Override
    public boolean attackEntityFrom(DamageSource source, float amount)
    {
        if(source.getTrueSource() instanceof PlayerEntity) {
            PlayerEntity attacker = (PlayerEntity) source.getTrueSource();
            Map<Enchantment, Integer> map = EnchantmentHelper.getEnchantments(attacker.getHeldItemMainhand());
            if(map.containsKey(KathairisEnchantments.ENCHANTMENT_ETHEREAL)) {
                return super.attackEntityFrom(source,amount);
            }
        }
        return false;
    }

    @Override
    protected void registerData()
    {
        super.registerData();
        this.getDataManager().register(SWINGING_ARMS, Boolean.valueOf(false));
    }

    @Override
    public int getMaxSpawnedInChunk()
    {
        return 1;
    }

    public void setSwingingArms(boolean swingingArms) {
        this.getDataManager().set(SWINGING_ARMS, Boolean.valueOf(swingingArms));
    }

    static class PhasmMoveHelper extends FlyingMovementController
    {
        private final EntityPhasm parentEntity;
        private int courseChangeCooldown;

        public PhasmMoveHelper(EntityPhasm ghast)
        {
            super(ghast,10,false);
            this.parentEntity = ghast;
        }

        @Override
        public void tick()
        {
            if (this.action == Action.MOVE_TO)
            {
                double d0 = this.parentEntity.getPosition().getX() - this.parentEntity.getPosition().getX();
                double d1 = this.parentEntity.getPosition().getY() - this.parentEntity.getPosition().getY();
                double d2 = this.parentEntity.getPosition().getZ() - this.parentEntity.getPosition().getZ();
                double d3 = d0 * d0 + d1 * d1 + d2 * d2;

                if (this.courseChangeCooldown-- <= 0)
                {
                    this.courseChangeCooldown += this.parentEntity.getRNG().nextInt(5) + 2;
                    d3 = (double) MathHelper.sqrt(d3);

                    if (this.isNotColliding(this.parentEntity.getPosition().getX(), this.parentEntity.getPosition().getY(), this.parentEntity.getPosition().getZ(), d3))
                    {
                        double mX = parentEntity.getMotion().getX() + d0 / d3 * 0.1D;
                        double mY = parentEntity.getMotion().getY() + d1 / d3 * 0.1D;
                        double mZ = parentEntity.getMotion().getZ() + d2 / d3 * 0.1D;
                        parentEntity.setMotion(new Vec3d(mX,mY,mZ));
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


                if (!this.parentEntity.world.checkNoEntityCollision(this.parentEntity, VoxelShapes.create(axisalignedbb)))
                {
                    return false;
                }
            }

            return true;
        }
    }

    static class AILookAtTheFuckingPlayer extends Goal {
        private final EntityPhasm phasm;

        public AILookAtTheFuckingPlayer(EntityPhasm entity) {
            phasm = entity;
            this.setMutexFlags(EnumSet.of(Flag.JUMP, Flag.MOVE, Flag.LOOK, Flag.TARGET));
        }

        @Override
        public boolean shouldExecute() {
            return phasm.getAttackTarget() != null;
        }

        @Override
        public void tick() {
            if (phasm.getAttackTarget() != null) {
                LivingEntity entitylivingbase = phasm.getAttackTarget();
                double d1 = entitylivingbase.getPosition().getX() - this.phasm.getPosition().getX();
                double d2 = entitylivingbase.getPosition().getZ() - this.phasm.getPosition().getZ();
                this.phasm.rotationYaw = -((float) MathHelper.atan2(d1, d2)) * (180F / (float)Math.PI);
                this.phasm.renderYawOffset = this.phasm.rotationYaw;
            }
        }
    }

    static class AIInteractWithPlayer extends Goal {
        private final EntityPhasm phasm;

        public AIInteractWithPlayer(EntityPhasm entity){
            phasm=entity;
            this.setMutexFlags(EnumSet.of(Flag.JUMP, Flag.MOVE, Flag.LOOK, Flag.TARGET));
        }

        @Override
        public boolean shouldExecute() {return phasm.getAttackTarget()!=null;}

        @Override
        public void tick(){
            if(phasm.getAttackTarget()!=null){
                phasm.setMotion(new Vec3d((phasm.getAttackTarget().getPosition().getX()-phasm.getPosition().getX())/50,(phasm.getAttackTarget().getPosition().getY()+1-phasm.getPosition().getY())/50,(phasm.getAttackTarget().getPosition().getZ()-phasm.getPosition().getZ())/50));
                if(phasm.getDistance(phasm.getAttackTarget())>10f){
                    BlockPos teleportPos = getClearPositionNearItself();
                    phasm.getAttackTarget().setPositionAndUpdate(teleportPos.getX(),teleportPos.getY(),teleportPos.getZ());
                }
            }
        }

        private BlockPos getClearPositionNearItself(){
            double X=phasm.getPosition().getX()-5+phasm.getRNG().nextDouble()*10;
            double Z=phasm.getPosition().getZ()-5+phasm.getRNG().nextDouble()*10;
            BlockPos tmp = phasm.world.getHeight(Heightmap.Type.MOTION_BLOCKING,new BlockPos(X,phasm.getPosition().getY(),Z));
            if(phasm.world.getBlockState(tmp.down()).isSolid()&&!phasm.world.isAirBlock(tmp.down())&&phasm.world.isAirBlock(tmp)&&
                    phasm.world.isAirBlock(tmp.up())){
                return tmp;
            }else{
                return getClearPositionNearItself();
            }
        }
    }

    static class AIWander extends Goal
    {
        private final EntityPhasm phasm;

        public AIWander(EntityPhasm p_i45859_1_)
        {
            this.phasm = p_i45859_1_;
            this.setMutexFlags(EnumSet.of(Flag.JUMP, Flag.MOVE, Flag.LOOK, Flag.TARGET));
        }

        @Override
        public boolean shouldExecute()
        {
            return phasm.getAttackTarget()==null;
        }

        @Override
        public void tick() {

            if(phasm.getAttackTarget()==null&& phasm.getRNG().nextInt(500)==0){
                BlockPos targetPos = findSomePosition(phasm);
                phasm.setMotion(new Vec3d((targetPos.getX()- phasm.getPosition().getX())/50,(targetPos.getY()- phasm.getPosition().getY())/50,(targetPos.getZ()- phasm.getPosition().getZ())/50));
                double d1 = targetPos.getX() - this.phasm.getPosition().getX();
                double d2 = targetPos.getZ() - this.phasm.getPosition().getZ();
                this.phasm.rotationYaw = -((float) MathHelper.atan2(d1, d2)) * (180F / (float)Math.PI);
                this.phasm.renderYawOffset = this.phasm.rotationYaw;
            }
        }

        BlockPos findSomePosition(EntityPhasm gaznowel){
            BlockPos result;
            double posX=gaznowel.getPosition().getX()-5+gaznowel.getRNG().nextInt(10);
            double posZ=gaznowel.getPosition().getZ()-5+gaznowel.getRNG().nextInt(10);
            double posY=gaznowel.getPosition().getY()-5+gaznowel.getRNG().nextInt(10);
            if(gaznowel.getPosition().getY()>255){
                posY=-20;
            }
            if(gaznowel.getPosition().getY()<0) {
               posY+=20;
            }
            result=new BlockPos(posX,posY,posZ);
            if(!(gaznowel.world.isAirBlock(result)&&gaznowel.world.isAirBlock(result.up()))){
                return findSomePosition(gaznowel);
            }else{
                return result;
            }
        }
    }



}

