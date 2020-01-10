package io.github.krevik.kathairis.entity;

import com.google.common.collect.Sets;
import io.github.krevik.kathairis.entity.ai.EntityAIAttackTarget;
import io.github.krevik.kathairis.init.ModEntities;
import io.github.krevik.kathairis.init.ModItems;
import io.github.krevik.kathairis.util.KatharianLootTables;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.controller.FlyingMovementController;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.ParrotEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.FlyingPathNavigator;
import net.minecraft.pathfinding.PathNavigator;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.gen.Heightmap;

import javax.annotation.Nullable;
import java.util.Set;

public class EntityCloudySlime extends TameableEntity
{
    private static final DataParameter<Integer> VARIANT = EntityDataManager.createKey(EntityCloudySlime.class, DataSerializers.VARINT);
    private static final Set<Item> TAME_ITEMS = Sets.newHashSet(ModItems.CLOUD_ESSENCE);
    protected SitGoal sitGoal;

    public EntityCloudySlime(World worldIn)
    {
        super(ModEntities.CLOUDY_SLIME,worldIn);

        this.moveController = new FlyingMovementController(this,10,false);
        this.setTamed(false);
    }

    public EntityCloudySlime(EntityType<EntityCloudySlime> type, World world) {
        super(type, world);
    }


    @Override
    protected void registerGoals() {
        super.registerGoals();
        sitGoal = new SitGoal(this);
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(1, new LookAtGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.addGoal(2, this.sitGoal);
        this.goalSelector.addGoal(3, new FollowOwnerGoal(this, 1.0D, 5.0F, 1.0F,true));
        this.goalSelector.addGoal(4, new RandomWalkingGoal(this, 1.0D));
        this.goalSelector.addGoal(1, new OwnerHurtByTargetGoal(this));
        this.goalSelector.addGoal(1, new OwnerHurtTargetGoal(this));
        this.goalSelector.addGoal(1, new EntityAIAttackTarget(this, this.getAttackTarget()));
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.0D, true));
    }


    @Override
    public SitGoal getAISit() {
        return sitGoal;
    }

    @Override
    protected void registerAttributes()
    {
        super.registerAttributes();
        this.getAttributes().registerAttribute(SharedMonsterAttributes.FLYING_SPEED);
        this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(30.0D);
        this.getAttribute(SharedMonsterAttributes.FLYING_SPEED).setBaseValue(0.6000000059604645D);
        this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.30000000298023224D);
    }

    @Override
    protected PathNavigator createNavigator(World worldIn)
    {
        FlyingPathNavigator pathnavigateflying = new FlyingPathNavigator(this, worldIn);
        pathnavigateflying.setCanOpenDoors(false);
        pathnavigateflying.setCanEnterDoors(true);
        return pathnavigateflying;
    }

    @Override
    protected ResourceLocation getLootTable() {
        return KatharianLootTables.LOOT_CLOUDYSLIME;
    }

    @Override
    public void travel(Vec3d direction) {
        if (this.isInWater()) {
            this.moveRelative(0.02f,new Vec3d(direction.getX(), direction.getY(), direction.getZ()));
            this.move(MoverType.SELF, getMotion());
            setMotionMultiplier(Blocks.WATER.getDefaultState(),new Vec3d(0.8,0.8,0.8));
        } else if (this.isInLava()) {
            this.moveRelative(0.02f,new Vec3d(direction.getX(), direction.getY(), direction.getZ()));
            this.move(MoverType.SELF, getMotion());
            setMotionMultiplier(Blocks.LAVA.getDefaultState(),new Vec3d(0.5,0.5,0.5));
        } else {
            float f = 0.91F;
            if (this.onGround) {
                BlockPos underPos = new BlockPos(MathHelper.floor(this.getPosition().getX()), MathHelper.floor(this.getBoundingBox().minY) - 1, MathHelper.floor(this.getPosition().getZ()));
                f = this.world.getBlockState(underPos).getSlipperiness(world, underPos, this) * 0.91F;
            }

            float f1 = 0.16277137F / (f * f * f);
            this.moveRelative(this.onGround ? 0.1F * f1 : 0.02F, new Vec3d(direction.getX(), direction.getY(), direction.getZ()));
            f = 0.91F;
            if (this.onGround) {
                BlockPos underPos = new BlockPos(MathHelper.floor(this.getPosition().getX()), MathHelper.floor(this.getBoundingBox().minY) - 1, MathHelper.floor(this.getPosition().getZ()));
                f = this.world.getBlockState(underPos).getSlipperiness(world, underPos, this) * 0.91F;
            }

            this.move(MoverType.SELF, getMotion());
            setMotionMultiplier(Blocks.AIR.getDefaultState(),new Vec3d(f,f,f));
        }

        this.prevLimbSwingAmount = this.limbSwingAmount;
        double d1 = this.getPosition().getX() - this.prevPosX;
        double d0 = this.getPosition().getZ() - this.prevPosZ;
        float f2 = MathHelper.sqrt(d1 * d1 + d0 * d0) * 4.0F;
        if (f2 > 1.0F) {
            f2 = 1.0F;
        }

        this.limbSwingAmount += (f2 - this.limbSwingAmount) * 0.4F;
        this.limbSwing += this.limbSwingAmount;
    }

    @Override
    public void tick() {
        super.tick();
        LivingEntity target = this.getAttackTarget();
        if(target!=null&&!this.isSitting()) {
            Vec3d vec3d = target.getEyePosition(1.0F);
            EntityCloudySlime.this.moveController.setMoveTo(vec3d.x, vec3d.y, vec3d.z, 1.0D);
        }
        if(!this.isTamed()) {
            this.setAttackTarget(this.getRevengeTarget());
        }
        if(this.getOwner()!=null) {
            if(this.getAttackTarget() instanceof TameableEntity) {
                TameableEntity et = (TameableEntity) this.getAttackTarget();
                if(this.getOwner()==et.getOwner()) {
                    this.setAttackTarget(null);
                }
            }
        }

    }

    @Override
    public boolean processInteract(PlayerEntity player, Hand hand)
    {
        ItemStack itemstack = player.getHeldItem(hand);

        if (!this.isTamed() && TAME_ITEMS.contains(itemstack.getItem()))
        {
        	if(this.world.getDayTime()>0&&this.world.getDayTime()<1000) {
	            if (!player.isCreative())
	            {
	                itemstack.shrink(1);
	            }

	            if (!this.world.isRemote)
	            {
	                if (this.rand.nextInt(8) == 0 && !net.minecraftforge.event.ForgeEventFactory.onAnimalTame(this, player))
	                {
	                    this.setTamedBy(player);
	                    this.playTameEffect(true);
	                    this.world.setEntityState(this, (byte)7);
	                }
	                else
	                {
	                    this.playTameEffect(false);
	                    this.world.setEntityState(this, (byte)6);
	                }
	            }
        	}
            return true;
        	
        }
        else
        {
            if (!this.world.isRemote && this.isTamed() && this.isOwner(player))
            {
                if(itemstack.getItem().equals(ModItems.CLOUD_ESSENCE)){
                    if(this.getHealth()<this.getMaxHealth()){
                        this.heal(3.0f);
                        this.playTameEffect(true);
                        if (!player.isCreative())
                        {
                            itemstack.shrink(1);
                        }
                    }
                }else {
                    this.getAISit().setSitting(!this.isSitting());
                }
            }

            return super.processInteract(player, hand);
        }
    }

    @Override
    public boolean isBreedingItem(ItemStack stack)
    {
        return false;
    }

    @Override
    protected void updateFallState(double y, boolean onGroundIn, BlockState state, BlockPos pos)
    {
    	
    }

    @Override
    public boolean canMateWith(AnimalEntity otherAnimal)
    {
        return false;
    }

    @Nullable
    @Override
    public AgeableEntity createChild(AgeableEntity ageable)
    {
        return null;
    }

    @Override
    public boolean attackEntityAsMob(Entity entityIn)
    {
        return entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), 3.0F);
    }

    @Override
    public boolean canBePushed()
    {
        return true;
    }

    @Override
    protected void collideWithEntity(Entity entityIn)
    {
        if (!(entityIn instanceof PlayerEntity))
        {
            super.collideWithEntity(entityIn);
        }
    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float amount)
    {
        if (this.isInvulnerableTo(source))
        {
            return false;
        }
        else
        {
            if (this.getAISit() != null)
            {
                this.getAISit().setSitting(false);
            }
            if(source== DamageSource.IN_WALL){
                if(this.getOwner()!=null&&getOwner().isAlive()){
                    setPosition(getOwner().getPosition().getX(),getOwner().getPosition().getY(),getOwner().getPosition().getZ());
                }else{
                    BlockPos tmp = new BlockPos(getPosition().getX(),world.getHeight(Heightmap.Type.MOTION_BLOCKING,getPosition()).getY()+1,getPosition().getZ());
                    setPosition(tmp.getX(),tmp.getY(),tmp.getZ());
                }
            }
            return super.attackEntityFrom(source, amount);
        }
    }

    public int getVariant()
    {
        return MathHelper.clamp(this.getDataManager().get(VARIANT).intValue(), 0, 4);
    }

    public void setVariant(int p_191997_1_)
    {
        this.getDataManager().set(VARIANT, Integer.valueOf(p_191997_1_));
    }

    @Override
    protected void registerData()
    {
        super.registerData();
        this.getDataManager().register(VARIANT, Integer.valueOf(0));
    }

    @Override
    public void writeAdditional(CompoundNBT compound)
    {
        super.writeAdditional(compound);
        compound.putInt("Variant", this.getVariant());
    }

    @Override
    public void readAdditional(CompoundNBT compound)
    {
        super.readAdditional(compound);
        this.setVariant(compound.getInt("Variant"));
    }

}