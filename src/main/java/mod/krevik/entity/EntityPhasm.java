package mod.krevik.entity;

import mod.krevik.enchantment.KathairisEnchantments;
import mod.krevik.util.FunctionHelper;
import mod.krevik.KCore;
import mod.krevik.util.MysticLootTables;
import mod.krevik.client.particle.DynamicParticle;
import mod.krevik.client.particle.ParticlesFactory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
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
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.Map;

public class EntityPhasm extends EntityFlying implements IMob {

    private static final DataParameter<Boolean> SWINGING_ARMS = EntityDataManager.<Boolean>createKey(EntityPhasm.class, DataSerializers.BOOLEAN);
    public EntityPhasm(World worldIn)
    {
        super(worldIn);
        this.setSize(1F, 2.0F);
        this.experienceValue = 35;
        this.moveHelper = new EntityPhasm.PhasmMoveHelper(this);

    }

    public boolean getIsSwingingArms(){
        return dataManager.get(SWINGING_ARMS).booleanValue();
    }

    protected void initEntityAI()
    {
        this.tasks.addTask(2, new EntityAIWatchClosest(this, EntityPlayer.class, 25.0F));
        this.tasks.addTask(5, new EntityPhasm.AIWander(this));
        this.tasks.addTask(2,new EntityPhasm.AILookAtTheFuckingPlayer(this));
        this.targetTasks.addTask(3, new EntityPhasm.AIInteractWithPlayer(this));
    }

    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.4000000417232513D);
        this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(4.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0D);
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(5.0D);
    }


    int timer=0;
    int ticker=100;
    FunctionHelper helper = KCore.instance.functionHelper;

    public void onUpdate()
    {
        super.onUpdate();

        if (!this.world.isRemote && this.world.getDifficulty() == EnumDifficulty.PEACEFUL)
        {
            this.setDead();
        }

        if(getAttackTarget()!=null){
            dataManager.set(SWINGING_ARMS,Boolean.valueOf(true));
        }else{
            dataManager.set(SWINGING_ARMS,Boolean.valueOf(false));
        }
        timer++;
        if(timer>=10) {
            timer = 0;
            EntityPlayer ep = world.getNearestAttackablePlayer(this, 5, 5);
            if (ep != null) {
                setAttackTarget(ep);
            }
        }
        if(getAttackTarget()instanceof EntityPlayer){
            EntityPlayer player = (EntityPlayer) getAttackTarget();
            if(player.isCreative()||player.isDead||player==null){
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
        if(world.isRemote){
            if(ticker>=1) {
                double d0 = (double) ((float) posX + (getEntityBoundingBox().maxX-getEntityBoundingBox().minX)/2)-0.5;
                double d1 = (double) ((float) posY + (getEntityBoundingBox().maxY-getEntityBoundingBox().minY)/2);
                double d2 = (double) ((float) posZ + (getEntityBoundingBox().maxZ-getEntityBoundingBox().minZ)/2)-0.5;
                Particle theParticle = new DynamicParticle(
                        ParticlesFactory.TWINKLINGPARTICLE,
                        world,
                        d0, d1, d2,
                        0, 0, 0)
                        .setRotSpeed(0F)
                        .setLifeSpan(1)
                        .setGravity(0F)
                        .setScale(15F)
                        .setInitialAlpha(0.5F)
                        .setFinalAlpha(0.1F).setEnableDepth(true);
                Minecraft.getMinecraft().effectRenderer.addEffect(theParticle);
            }
        }

    }

    public boolean attackEntityAsMob(Entity entityIn,float damage)
    {
        float f = 5;
        int i = 0;

        if (entityIn instanceof EntityLivingBase)
        {
            f += EnchantmentHelper.getModifierForCreature(this.getHeldItemMainhand(), ((EntityLivingBase)entityIn).getCreatureAttribute());
            i += EnchantmentHelper.getKnockbackModifier(this);
        }

        boolean flag = entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), f);

        if (flag)
        {
            if (i > 0 && entityIn instanceof EntityLivingBase)
            {
                ((EntityLivingBase)entityIn).knockBack(this, (float)i * 0.5F, (double)MathHelper.sin(this.rotationYaw * 0.017453292F), (double)(-MathHelper.cos(this.rotationYaw * 0.017453292F)));
                this.motionX *= 0.6D;
                this.motionZ *= 0.6D;
            }

            int j = EnchantmentHelper.getFireAspectModifier(this);

            if (j > 0)
            {
                entityIn.setFire(j * 4);
            }

            if (entityIn instanceof EntityPlayer)
            {
                EntityPlayer entityplayer = (EntityPlayer)entityIn;
                ItemStack itemstack = this.getHeldItemMainhand();
                ItemStack itemstack1 = entityplayer.isHandActive() ? entityplayer.getActiveItemStack() : ItemStack.EMPTY;

                if (!itemstack.isEmpty() && !itemstack1.isEmpty() && itemstack.getItem().canDisableShield(itemstack, itemstack1, entityplayer, this) && itemstack1.getItem().isShield(itemstack1, entityplayer))
                {
                    float f1 = 0.25F + (float)EnchantmentHelper.getEfficiencyModifier(this) * 0.05F;

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



    public boolean attackEntityFrom(DamageSource source, float amount)
    {
        if(source.getTrueSource() instanceof EntityPlayer) {
            EntityPlayer attacker = (EntityPlayer) source.getTrueSource();
            Map<Enchantment, Integer> map = EnchantmentHelper.getEnchantments(attacker.getHeldItemMainhand());
            if(map.containsKey(KathairisEnchantments.Ethereal)) {
                return super.attackEntityFrom(source,amount);
            }
        }
        return false;
    }

    @Nullable
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata) {
        livingdata = super.onInitialSpawn(difficulty, livingdata);
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
        return MysticLootTables.LOOT_PHASM;
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


    public void setSwingingArms(boolean swingingArms) {
        this.dataManager.set(SWINGING_ARMS, Boolean.valueOf(swingingArms));
    }

    static class PhasmMoveHelper extends EntityMoveHelper
    {
        private final EntityPhasm parentEntity;
        private int courseChangeCooldown;

        public PhasmMoveHelper(EntityPhasm ghast)
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

    static class AILookAtTheFuckingPlayer extends EntityAIBase {
        private final EntityPhasm phasm;

        public AILookAtTheFuckingPlayer(EntityPhasm entity) {
            phasm = entity;
            this.setMutexBits(3);
        }

        public boolean shouldExecute() {
            return phasm.getAttackTarget() != null;
        }

        public void updateTask() {
            if (phasm.getAttackTarget() != null) {
                EntityLivingBase entitylivingbase = phasm.getAttackTarget();
                double d1 = entitylivingbase.posX - this.phasm.posX;
                double d2 = entitylivingbase.posZ - this.phasm.posZ;
                this.phasm.rotationYaw = -((float)MathHelper.atan2(d1, d2)) * (180F / (float)Math.PI);
                this.phasm.renderYawOffset = this.phasm.rotationYaw;
            }
        }
    }

    static class AIInteractWithPlayer extends EntityAIBase{
        private final EntityPhasm phasm;

        public AIInteractWithPlayer(EntityPhasm entity){
            phasm=entity;
            this.setMutexBits(3);
        }

        public boolean shouldExecute() {return phasm.getAttackTarget()!=null;}
        public void updateTask(){
            if(phasm.getAttackTarget()!=null){
                phasm.motionX=(phasm.getAttackTarget().posX-phasm.posX)/50;
                phasm.motionY=(phasm.getAttackTarget().posY+1-phasm.posY)/50;
                phasm.motionZ=(phasm.getAttackTarget().posZ-phasm.posZ)/50;
                if(phasm.getDistance(phasm.getAttackTarget())>10f){
                    BlockPos teleportPos = getClearPositionNearItself();
                    phasm.getAttackTarget().setPositionAndUpdate(teleportPos.getX(),teleportPos.getY(),teleportPos.getZ());
                }
            }
        }

        private BlockPos getClearPositionNearItself(){
            double X=phasm.posX-5+phasm.getRNG().nextDouble()*10;
            double Z=phasm.posZ-5+phasm.getRNG().nextDouble()*10;
            BlockPos tmp = phasm.world.getHeight(new BlockPos(X,phasm.posY,Z));
            if(phasm.world.getBlockState(tmp.down()).isFullBlock()&&!phasm.world.isAirBlock(tmp.down())&&phasm.world.isAirBlock(tmp)&&
                    phasm.world.isAirBlock(tmp.up())){
                return tmp;
            }else{
                return getClearPositionNearItself();
            }
        }
    }

    static class AIWander extends EntityAIBase
    {
        private final EntityPhasm gaznowel;

        public AIWander(EntityPhasm p_i45859_1_)
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

            if(gaznowel.getAttackTarget()==null&&gaznowel.getRNG().nextInt(500)==0){
                BlockPos targetPos = findSomePosition(gaznowel);
                gaznowel.motionX=(targetPos.getX()-gaznowel.posX)/50;
                gaznowel.motionY=(targetPos.getY()-gaznowel.posY)/50;
                gaznowel.motionZ=(targetPos.getZ()-gaznowel.posZ)/50;
                double d1 = targetPos.getX() - this.gaznowel.posX;
                double d2 = targetPos.getZ() - this.gaznowel.posZ;
                this.gaznowel.rotationYaw = -((float)MathHelper.atan2(d1, d2)) * (180F / (float)Math.PI);
                this.gaznowel.renderYawOffset = this.gaznowel.rotationYaw;
            }
        }

        BlockPos findSomePosition(EntityPhasm gaznowel){
            BlockPos result;
            double posX=gaznowel.posX-5+gaznowel.getRNG().nextInt(10);
            double posZ=gaznowel.posZ-5+gaznowel.getRNG().nextInt(10);
            double posY=gaznowel.posY-5+gaznowel.getRNG().nextInt(10);
            if(posY>255){ posY=-20;}
            if(posY<0) {posY+=20;}
            result=new BlockPos(posX,posY,posZ);
            if(!(gaznowel.world.isAirBlock(result)&&gaznowel.world.isAirBlock(result.up()))){
                return findSomePosition(gaznowel);
            }else{
                return result;
            }
        }
    }



}

