package com.Krevik.Entities;

import java.util.Set;

import javax.annotation.Nullable;

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
import net.minecraft.util.EnumHand;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityGecko extends EntityAnimal
{
    private static final DataParameter<Boolean> isClimbing = EntityDataManager.<Boolean>createKey(EntityGecko.class, DataSerializers.BOOLEAN);
    private static final DataParameter<Boolean> canWalkOnWall = EntityDataManager.<Boolean>createKey(EntityGecko.class, DataSerializers.BOOLEAN);
    private static final DataParameter<Integer> climbingSide = EntityDataManager.<Integer>createKey(EntityGecko.class, DataSerializers.VARINT);
    private static final DataParameter<Integer> VARIANT = EntityDataManager.<Integer>createKey(EntityGecko.class, DataSerializers.VARINT);

    public EntityGecko(World worldIn)
    {
        super(worldIn);
        this.setSize(0.7F, 0.25F);
        this.experienceValue=10;
    }
    
    private static final Set<Item> TEMPTATION_ITEMS = Sets.newHashSet(Items.PORKCHOP);
    public boolean isBreedingItem(ItemStack stack)
    {
        return TEMPTATION_ITEMS.contains(stack.getItem());
    }
    protected void initEntityAI()
    {
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new EntityAIPanic(this, 1.25D));
        this.tasks.addTask(2, new EntityAIMate(this, 1.0D));
        this.tasks.addTask(4, new EntityAIFollowParent(this, 1.1D));
        this.tasks.addTask(6, new EntityAIWanderAvoidWater(this, 1.0D));
        this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        this.tasks.addTask(8, new EntityAILookIdle(this));
        this.tasks.addTask(3, new EntityAITempt(this, 1.25D, false, TEMPTATION_ITEMS));
    }
    
    protected PathNavigate createNavigator(World worldIn)
    {
        return new PathNavigateClimber(this, worldIn);
    }
    
    protected void entityInit()
    {
        super.entityInit();
        this.dataManager.register(isClimbing, Boolean.FALSE);
        this.dataManager.register(canWalkOnWall, Boolean.FALSE);
        this.dataManager.register(climbingSide, Integer.valueOf(-1));
        this.dataManager.register(VARIANT, Integer.valueOf(0));

    }
    public int getVariant()
    {
        return MathHelper.clamp(((Integer)this.dataManager.get(VARIANT)).intValue(), 0, 4);
    }

    public void setVariant(int p_191997_1_)
    {
        this.dataManager.set(VARIANT, Integer.valueOf(p_191997_1_));
    }
    public boolean isClimbing() {
        return ((Boolean)this.dataManager.get(isClimbing)).booleanValue();
    }
    public void setClimbing(boolean truorfalse) {
        this.dataManager.set(isClimbing, Boolean.valueOf(truorfalse));
    }
    public boolean canWalkOnWall() {
        return ((Boolean)this.dataManager.get(canWalkOnWall)).booleanValue();
    }
    public void setCanWalkOnWall(boolean truorfalse) {
        this.dataManager.set(canWalkOnWall, Boolean.valueOf(truorfalse));
    }
    public int climbingSide() {
    	return ((Integer)this.dataManager.get(climbingSide)).intValue();
    }
    public void setClimbingSide(int x) {
    	this.dataManager.set(climbingSide, Integer.valueOf(x));
    }
    protected void updateAITasks()
    {
        super.updateAITasks();
    }
    
    public int getMaxSpawnedInChunk()
    {
        return 4;
    }
    
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(7.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3000000417232513D);
    }

    protected ResourceLocation getLootTable()
    {
    	return MysticLootTables.LOOT_GECKO;
    }

    @SideOnly(Side.CLIENT)
    public void handleStatusUpdate(byte id)
    {
            super.handleStatusUpdate(id);
    }

    public boolean processInteract(EntityPlayer player, EnumHand hand)
    {
    	return super.processInteract(player, hand);
    }


    public void onLivingUpdate()
    {
    	super.onLivingUpdate();

    }
    
    public boolean isOnLadder()
    {
        return this.isClimbing();
    }
    
    private void OperateWalkingOnWall() {

    }
    
    public void onUpdate() {
    	super.onUpdate();
    	//operate can walk on wall
    	this.setCanWalkOnWall(this.collidedHorizontally);
    	//operate climbing side
    	if(this.canWalkOnWall()) {
    		if(!this.world.isAirBlock(getPosition().east())) {
    			this.setClimbingSide(0);
    		}else if(!this.world.isAirBlock(getPosition().west())) {
    			this.setClimbingSide(1);
    		}else if(!this.world.isAirBlock(getPosition().north())) {
    			this.setClimbingSide(2);
    		}else if(!this.world.isAirBlock(getPosition().south())) {
    			this.setClimbingSide(3);
    		}else {
    			this.setClimbingSide(-1);
    		}
    	}
    	
    	if(this.isClimbing()) {
    		this.setNoGravity(true);
    	}else {
    		this.setNoGravity(false);
    	}
    	
    	if(this.canWalkOnWall()&&this.climbingSide()>=0) {
    		if(this.getRNG().nextInt(100)==0) {
    			this.setClimbing(true);
    		}
    	}
    	
    }
    
    private static enum EnumType implements IStringSerializable{
		EAST(0,"east"),
		WEST(1,"west"),
		NORTH(2,"north"),
		SOUTH(3,"south");
    	
        private final String name;
        private final int meta;
        
        private EnumType(int num,String Name)
        {
            this.meta = num;
            name=Name;
        }
        
		@Override
		public String getName() {
			return name;
		}
    	
		public int getMeta() {
			return meta;
		}
    }


    public void writeEntityToNBT(NBTTagCompound compound)
    {
        super.writeEntityToNBT(compound);
        compound.setBoolean("isClimbing", this.isClimbing());
        compound.setBoolean("canWalkOnWall", this.canWalkOnWall());
        compound.setInteger("climbingSide", this.climbingSide());
        compound.setInteger("Variant", this.getVariant());

    }

    public void readEntityFromNBT(NBTTagCompound compound)
    {
        super.readEntityFromNBT(compound);
        this.dataManager.set(isClimbing, compound.getBoolean("isClimbing"));
        this.setCanWalkOnWall(compound.getBoolean("canWalkOnWall"));
        this.setClimbingSide(compound.getInteger("climbingSide"));
        this.setVariant(compound.getInteger("Variant"));
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

 
    public EntityGecko createChild(EntityAgeable ageable)
    {
    	if(!this.world.isRemote) {
    		if(this.world.isBlockFullCube(this.getPosition().down())) {
    			if(this.getRNG().nextInt(6)==0) {
    				this.world.setBlockState(this.getPosition(), KCore.Gecko_Eggs.getDefaultState());
    			}
    		}
    	}
        return null;
    }

 
    @Nullable
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata)
    {
        livingdata = super.onInitialSpawn(difficulty, livingdata);
        this.setVariant(this.rand.nextInt(4));
        return livingdata;
    }

    public float getEyeHeight()
    {
        return 0.95F * this.height;
    }
    
    

}