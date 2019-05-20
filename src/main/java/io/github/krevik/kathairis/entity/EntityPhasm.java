package io.github.krevik.kathairis.entity;

import io.github.krevik.kathairis.Kathairis;
import io.github.krevik.kathairis.init.ModEntities;
import io.github.krevik.kathairis.util.FunctionHelper;
import io.github.krevik.kathairis.util.KatharianLootTables;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityFlying;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.EntityMoveHelper;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraft.world.gen.Heightmap;

import javax.annotation.Nullable;
import java.util.Map;

public class EntityPhasm extends EntityFlying implements IMob {

    private static final DataParameter<Boolean> SWINGING_ARMS = EntityDataManager.createKey(EntityPhasm.class, DataSerializers.BOOLEAN);
    public EntityPhasm(World worldIn)
    {
        super(ModEntities.PHASM,worldIn);
        this.setSize(1F, 2.0F);
        this.experienceValue = 35;
        this.moveHelper = new EntityPhasm.PhasmMoveHelper(this);

    }

    public boolean getIsSwingingArms(){
        return getDataManager().get(SWINGING_ARMS).booleanValue();
    }

    @Override
    protected void initEntityAI()
    {
        this.tasks.addTask(2, new EntityAIWatchClosest(this, EntityPlayer.class, 25.0F));
        this.tasks.addTask(5, new EntityPhasm.AIWander(this));
        this.tasks.addTask(2,new EntityPhasm.AILookAtTheFuckingPlayer(this));
        this.targetTasks.addTask(3, new EntityPhasm.AIInteractWithPlayer(this));
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

        if (!this.world.isRemote && this.world.getDifficulty() == EnumDifficulty.PEACEFUL)
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
            EntityPlayer ep = world.getNearestAttackablePlayer(this, 5, 5);
            if (ep != null) {
                setAttackTarget(ep);
            }
        }
        if(getAttackTarget()instanceof EntityPlayer){
            EntityPlayer player = (EntityPlayer) getAttackTarget();
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
                double d0 = ((float) posX + (getEntityBoundingBox().maxX - getEntityBoundingBox().minX) / 2) - 0.5;
                double d1 = ((float) posY + (getEntityBoundingBox().maxY - getEntityBoundingBox().minY) / 2);
                double d2 = ((float) posZ + (getEntityBoundingBox().maxZ - getEntityBoundingBox().minZ) / 2) - 0.5;
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

    public boolean attackEntityAsMob(Entity entityIn, float damage)
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
                ((EntityLivingBase)entityIn).knockBack(this, (float)i * 0.5F, (double) MathHelper.sin(this.rotationYaw * 0.017453292F), (double)(-MathHelper.cos(this.rotationYaw * 0.017453292F)));
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
        if(source.getTrueSource() instanceof EntityPlayer) {
            EntityPlayer attacker = (EntityPlayer) source.getTrueSource();
            Map<Enchantment, Integer> map = EnchantmentHelper.getEnchantments(attacker.getHeldItemMainhand());
            /*if(map.containsKey(KathairisEnchantments.Ethereal)) {
                //return super.attackEntityFrom(source,amount);
            }*/
            return true;
        }
        return false;
    }

    @Override
    protected void registerData()
    {
        super.registerData();
        this.getDataManager().register(SWINGING_ARMS, Boolean.valueOf(false));
    }

    @Nullable
    @Override
    protected ResourceLocation getLootTable()
    {
        return KatharianLootTables.LOOT_PHASM;
    }

    @Override
    public int getMaxSpawnedInChunk()
    {
        return 1;
    }

    public void setSwingingArms(boolean swingingArms) {
        this.getDataManager().set(SWINGING_ARMS, Boolean.valueOf(swingingArms));
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

        @Override
        public void tick()
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

        private boolean isNotColliding(double x, double y, double z, double p_179926_7_)
        {
            double d0 = (x - this.parentEntity.posX) / p_179926_7_;
            double d1 = (y - this.parentEntity.posY) / p_179926_7_;
            double d2 = (z - this.parentEntity.posZ) / p_179926_7_;
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

    static class AILookAtTheFuckingPlayer extends EntityAIBase {
        private final EntityPhasm phasm;

        public AILookAtTheFuckingPlayer(EntityPhasm entity) {
            phasm = entity;
            this.setMutexBits(3);
        }

        @Override
        public boolean shouldExecute() {
            return phasm.getAttackTarget() != null;
        }

        @Override
        public void tick() {
            if (phasm.getAttackTarget() != null) {
                EntityLivingBase entitylivingbase = phasm.getAttackTarget();
                double d1 = entitylivingbase.posX - this.phasm.posX;
                double d2 = entitylivingbase.posZ - this.phasm.posZ;
                this.phasm.rotationYaw = -((float) MathHelper.atan2(d1, d2)) * (180F / (float)Math.PI);
                this.phasm.renderYawOffset = this.phasm.rotationYaw;
            }
        }
    }

    static class AIInteractWithPlayer extends EntityAIBase {
        private final EntityPhasm phasm;

        public AIInteractWithPlayer(EntityPhasm entity){
            phasm=entity;
            this.setMutexBits(3);
        }

        @Override
        public boolean shouldExecute() {return phasm.getAttackTarget()!=null;}

        @Override
        public void tick(){
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
            BlockPos tmp = phasm.world.getHeight(Heightmap.Type.MOTION_BLOCKING,new BlockPos(X,phasm.posY,Z));
            if(phasm.world.getBlockState(tmp.down()).isFullCube()&&!phasm.world.isAirBlock(tmp.down())&&phasm.world.isAirBlock(tmp)&&
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

        @Override
        public boolean shouldExecute()
        {
            return gaznowel.getAttackTarget()==null;
        }

        @Override
        public void tick() {

            if(gaznowel.getAttackTarget()==null&&gaznowel.getRNG().nextInt(500)==0){
                BlockPos targetPos = findSomePosition(gaznowel);
                gaznowel.motionX=(targetPos.getX()-gaznowel.posX)/50;
                gaznowel.motionY=(targetPos.getY()-gaznowel.posY)/50;
                gaznowel.motionZ=(targetPos.getZ()-gaznowel.posZ)/50;
                double d1 = targetPos.getX() - this.gaznowel.posX;
                double d2 = targetPos.getZ() - this.gaznowel.posZ;
                this.gaznowel.rotationYaw = -((float) MathHelper.atan2(d1, d2)) * (180F / (float)Math.PI);
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

