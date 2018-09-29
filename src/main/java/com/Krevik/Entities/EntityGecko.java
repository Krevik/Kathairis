package com.Krevik.Entities;

import java.util.Set;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.Krevik.Entities.AI.EntityAIAvoidMovingSands;
import com.Krevik.Main.KCore;
import com.Krevik.Main.MysticLootTables;
import com.google.common.collect.Sets;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIFollowParent;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.pathfinding.PathNavigateClimber;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

public class EntityGecko extends EntityAnimal {
    private static final DataParameter<Integer> climbingSide = EntityDataManager.<Integer>createKey(EntityGecko.class, DataSerializers.VARINT);
    private static final DataParameter<Integer> VARIANT = EntityDataManager.<Integer>createKey(EntityGecko.class, DataSerializers.VARINT);

    public EntityGecko(World worldIn) {
        super(worldIn);
        setSize(0.7F, 0.25F);
        experienceValue = 10;
    }
    
    private static final Set<Item> TEMPTATION_ITEMS = Sets.newHashSet(Items.PORKCHOP);
    public boolean isBreedingItem(ItemStack stack)
    {
        return TEMPTATION_ITEMS.contains(stack.getItem());
    }
    protected void initEntityAI() {
        tasks.addTask(0, new EntityAISwimming(this));
        tasks.addTask(1, new EntityAIPanic(this, 1.25D));
        tasks.addTask(2, new EntityAIMate(this, 1.0D));
        tasks.addTask(4, new EntityAIFollowParent(this, 1.1D));
        tasks.addTask(6, new EntityAIWanderAvoidWater(this, 1.0D));
        tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        tasks.addTask(8, new EntityAILookIdle(this));
        tasks.addTask(3, new EntityAITempt(this, 1.25D, false, TEMPTATION_ITEMS));
        tasks.addTask(0, new EntityAIAvoidMovingSands(this,1.2D));

    }

    @Nonnull
    protected PathNavigate createNavigator(@Nonnull World worldIn)
    {
        return new PathNavigateClimber(this, worldIn);
    }
    
    protected void entityInit() {
        super.entityInit();
        this.dataManager.register(climbingSide, EnumClimbSide.FLOOR.ordinal());
        this.dataManager.register(VARIANT, 0);

    }
    public int getVariant()
    {
        return MathHelper.clamp(dataManager.get(VARIANT), 0, 4);
    }

    public void setVariant(int meta) {
        dataManager.set(VARIANT, meta);
    }

    public boolean isClimbing() {
        return climbingSide() != EnumClimbSide.FLOOR;

    }
    public EnumClimbSide climbingSide() {
    	return EnumClimbSide.values()[dataManager.get(climbingSide)];
    }

    private void setClimbingSide(EnumClimbSide side) {
    	dataManager.set(climbingSide, side.ordinal());
    }
    
    public int getMaxSpawnedInChunk()
    {
        return 4;
    }
    
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(7.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3000000417232513D);
    }

    protected ResourceLocation getLootTable()
    {
    	return MysticLootTables.LOOT_GECKO;
    }
    
    public boolean isOnLadder()
    {
        return this.isClimbing();
    }

    public void onUpdate() {
        super.onUpdate();
        this.fallDistance=0;
        //operate can walk on wall
        if (!world.isRemote) {
            //operate climbing side
            BlockPos pos = getPosition();
            if (collidedHorizontally) {
                if (!world.isAirBlock(pos.east())) setClimbingSide(EnumClimbSide.EAST);
                else if (!world.isAirBlock(pos.west())) setClimbingSide(EnumClimbSide.WEST);
                else if (!world.isAirBlock(pos.north())) setClimbingSide(EnumClimbSide.NORTH);
                else if (!world.isAirBlock(pos.south())) setClimbingSide(EnumClimbSide.SOUTH);
            } else setClimbingSide(EnumClimbSide.FLOOR);
            if(!world.isAirBlock(getPosition())) {
            	motionY=-1;
            	motionX=-0.5+getRNG().nextDouble();
            	motionZ=-0.5+getRNG().nextDouble();
            }
        }
    }
    
    public enum EnumClimbSide {
		EAST(),
		WEST(),
		NORTH(),
		SOUTH(),
        FLOOR()
    }

    public void writeEntityToNBT(NBTTagCompound compound) {
        super.writeEntityToNBT(compound);
        compound.setInteger("climbingSide", climbingSide().ordinal());
        compound.setInteger("Variant", getVariant());

    }

    public void readEntityFromNBT(NBTTagCompound compound) {
        super.readEntityFromNBT(compound);
        //migration purpose
        int side = compound.getInteger("climbingSide");
        if (side < 0) setClimbingSide(EnumClimbSide.FLOOR);
        else setClimbingSide(EnumClimbSide.values()[side]);

        setVariant(compound.getInteger("Variant"));
    }
    

    protected SoundEvent getAmbientSound() {
        return null;
    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return null;
    }

    protected SoundEvent getDeathSound() {
        return null;
    }


    public EntityGecko createChild(@Nonnull EntityAgeable ageable) {
        if (!world.isRemote) {
            if (world.isBlockFullCube(getPosition().down())) {
                if (getRNG().nextInt(6) == 0) {
                    world.setBlockState(getPosition(), KCore.Gecko_Eggs.getDefaultState());
                }
            }
        }
        return null;
    }


    @Nullable
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata) {
        livingdata = super.onInitialSpawn(difficulty, livingdata);
        setVariant(rand.nextInt(4));
        return livingdata;
    }

    public float getEyeHeight() {
        return 0.95F * height;
    }
}