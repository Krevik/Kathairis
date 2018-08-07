package com.Krevik.Entities;

import java.util.List;

import javax.annotation.Nullable;

import com.Krevik.Dimension.KetherDataStorage;
import com.Krevik.Dimension.TileEntityKether;
import com.Krevik.Main.KCore;
import com.Krevik.Main.MysticLootTables;
import com.Krevik.Networking.KetherPacketHandler;
import com.Krevik.Networking.PacketDeathHandlerServer;
import com.google.common.collect.Lists;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityWitherSkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BossInfo;
import net.minecraft.world.BossInfoServer;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class EntityDeath extends EntityLiving
{
	private boolean defeated;
	public boolean isFighting;
    private final BossInfoServer bossInfo = (BossInfoServer)(new BossInfoServer(this.getDisplayName(), BossInfo.Color.RED, BossInfo.Overlay.PROGRESS)).setDarkenSky(false);

    public EntityDeath(World worldIn)
    {
        super(worldIn);
        this.setSize(1.25F, 4F);
        defeated=false;
        isFighting=false;
    }
    
    @Override
    protected boolean canDespawn()
    {
        return false;
    }

    protected void initEntityAI()
    {
        this.experienceValue=1000;
        this.applyEntityAI();
    }
    public int getMaxSpawnedInChunk()
    {
        return 1;
    }
    protected void applyEntityAI()
    {
    	
    }
    

    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(1000.0D);
        this.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(500);
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(15);


    }

    protected void entityInit()
    {
        super.entityInit();
    }

    /**
     * Get the experience points the entity currently has.
     */
    protected int getExperiencePoints(EntityPlayer player)
    {
        return super.getExperiencePoints(player);
    }

    public void notifyDataManagerChange(DataParameter<?> key)
    {
        super.notifyDataManagerChange(key);
    }

    /**
     * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons
     * use this to react to sunlight and start to burn.
     */
    public void onLivingUpdate()
    {
        super.onLivingUpdate();
    }
    
    /**
     * Add the given player to the list of players tracking this entity. For instance, a player may track a boss in
     * order to view its associated boss bar.
     */
    public void addTrackingPlayer(EntityPlayerMP player)
    {
        super.addTrackingPlayer(player);
        this.bossInfo.addPlayer(player);
    }

    /**
     * Removes the given player from the list of players tracking this entity. See {@link Entity#addTrackingPlayer} for
     * more information on tracking.
     */
    public void removeTrackingPlayer(EntityPlayerMP player)
    {
        super.removeTrackingPlayer(player);
        this.bossInfo.removePlayer(player);
    }
    
    public boolean attackEntityAsMob(Entity entityIn)
    {
        boolean flag = super.attackEntityAsMob(entityIn);
        return flag;
    }
    
    int mode=0;
    int timer=300;
    int mobStage=1;
    KetherDataStorage data;
    public void onUpdate() {
    	super.onUpdate();
	    	if(data==null) {
	    		data=KetherDataStorage.getDataInstance(DimensionManager.getWorld(KCore.instance.DIMENSION_ID));
	    	}
    	if(data!=null) {
    			this.isFighting=data.getIsDeathFighting();
    			if(!this.world.isRemote) {
    				isFighting=data.getIsDeathFighting();
    			}
    	    	if(this.getHealth()<5) {
    	    		if(!this.world.isRemote) {
    	            int damage=KCore.functionHelper.getRandomInteger(700, 990);
    	            ItemStack wand = new ItemStack(KCore.DeathWand,1,damage);
    	            this.dropItem(KCore.DarknessEssence, 10+rand.nextInt(20));
    	            this.dropItem(KCore.AdamantiumIngot, 1+rand.nextInt(5));
    	            this.entityDropItem(wand,0);
    	            defeated=true;
    	            this.setDead();
    	            this.onDeath(DamageSource.GENERIC);
    	    		}

    				IMessage message2 = new PacketDeathHandlerServer(true,true,true);
    				KetherPacketHandler.CHANNEL.sendToServer(message2);

    	    	}
    	    	this.posX=prevPosX;
    	    	this.posY=prevPosY;
    	    	this.posZ=prevPosZ;
    	        this.bossInfo.setPercent(this.getHealth() / this.getMaxHealth());
	        	EntityPlayer ep = this.world.getClosestPlayer(posX, posY, posZ, 15, false);
	        	if(ep!=null) {
	                this.getLookHelper().setLookPosition(ep.posX, ep.posY + (double)ep.getEyeHeight(), ep.posZ, (float)100, (float)100);
	                this.setAttackTarget(ep);
	        	}else {
	        		this.setAttackTarget(null);
	        		this.isFighting=false;
        			IMessage message2 = new PacketDeathHandlerServer(true,false,false);
        			KetherPacketHandler.CHANNEL.sendToServer(message2);
	        	}
	            if(this.getAttackTarget()==null) {
	            	if(isFighting) {
	            		this.isFighting=false;
	        			IMessage message2 = new PacketDeathHandlerServer(true,false,false);
	        			KetherPacketHandler.CHANNEL.sendToServer(message2);
	            	}
	            }
	            if(!isFighting) {
	            	this.setHealth( (float) this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).getAttributeValue());
	            	mode=0;
	            	mobStage=1;
	            	timer=300;
	            }

    	        if(this.getAttackTarget()!=null) {
    				if(this.getDistance(this.getAttackTarget())<2) {
    					this.getAttackTarget().attackEntityFrom(DamageSource.causeMobDamage(this), 15F);
    				}
    	        	if(this.getAttackTarget().isDead) {
    	        		this.isFighting=false;
    	    			IMessage message2 = new PacketDeathHandlerServer(true,false,false);
    	    			KetherPacketHandler.CHANNEL.sendToServer(message2);
    	        	}


    	        	if(isFighting) {
    	        		timer++;
    	        		if(timer>=300+mobStage*20) {
    	        			if(mobStage==1) {
    	            			for(int i=0;i<=3+mobStage;i++) {
    	            				EntityZombie zombie = new EntityZombie(this.getEntityWorld());
    	            				zombie.setPosition(this.posX-rand.nextInt(10), posY+2, posZ-rand.nextInt(7)+rand.nextInt(7));
    	            				if(!this.world.isRemote) {
    	            					this.world.spawnEntity(zombie);
    	            				}
    	            			}
    	        			}
    	        			else if(mobStage==2) {
    	            			for(int i=0;i<=3+mobStage*2;i++) {
    	            				EntitySkeletonWarrior mob = new EntitySkeletonWarrior(this.getEntityWorld());
    	            				mob.setPosition(this.posX-rand.nextInt(10), posY+2, posZ-rand.nextInt(7)+rand.nextInt(7));
    	            				if(!this.world.isRemote) {
    	            					this.world.spawnEntity(mob);
    	            				}
    	            			}
    	        			}
    	        			else if(mobStage==3) {
    	            			for(int i=0;i<=3+mobStage*2;i++) {
    	            				EntitySkeleton mob = new EntitySkeleton(this.getEntityWorld());
    	            				mob.setPosition(this.posX-rand.nextInt(10), posY+2, posZ-rand.nextInt(7)+rand.nextInt(7));
    	            				if(!this.world.isRemote) {
    	            				this.world.spawnEntity(mob);
    	            				}
    	            			}
    	        			}
    	        			else if(mobStage==4) {
    	            			for(int i=0;i<=3+mobStage*2;i++) {
	    	        				EntitySteveGhost mob = new EntitySteveGhost(this.getEntityWorld());
	    	        				mob.setPosition(this.posX-rand.nextInt(10), posY+2, posZ-rand.nextInt(7)+rand.nextInt(7));
	    	        				if(!this.world.isRemote) {
	    	        					this.world.spawnEntity(mob);
	    	        				}
    	            			}
    	        			}
    	        			else if(mobStage==5) {
    	            			for(int i=0;i<=3+mobStage*2;i++) {
    	            				EntityWitherSkeleton mob = new EntityWitherSkeleton(this.getEntityWorld());
    	            				mob.setPosition(this.posX-rand.nextInt(10), posY+2, posZ-rand.nextInt(7)+rand.nextInt(7));
    	            				if(!this.world.isRemote) {
    	            					this.world.spawnEntity(mob);
    	            				}
    	            			}
    	        			}
    	        			else if(mobStage>5) {
    	            			for(int i=0;i<=13;i++) {
    	            				EntityWitherSkeleton mob = new EntityWitherSkeleton(this.getEntityWorld());
    	            				mob.setPosition(this.posX-rand.nextInt(10), posY+2, posZ-rand.nextInt(7)+rand.nextInt(7));
    	            				if(!this.world.isRemote) {
    	            					this.world.spawnEntity(mob);
    	            				}
    	            			}
    	        			}
    	        			mobStage++;
    	        			timer=0;
    	        			
    	        		}
    	        	}
    	        }
    	}

    	
    }
    
    
    public boolean attackEntityFrom(DamageSource source, float amount)
    {
    	if(isFighting) {
    		if(source.getTrueSource() instanceof EntityPlayer) {
    			EntityPlayer attacker = (EntityPlayer) source.getTrueSource();
    			if(attacker.getHeldItemMainhand().getItem().equals(KCore.LightSword)) {
    				this.damageEntity(source, 6);
    				return true;
    			}
    		}
    	}
    	return false;
    }

    protected SoundEvent getAmbientSound()
    {
        return KCore.instance.cproxy.death_living;
    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn)
    {
        return null;
    }

    protected SoundEvent getDeathSound()
    {
        return null;
    }

    protected SoundEvent getStepSound()
    {
        return null;
    }

    protected void playStepSound(BlockPos pos, Block blockIn)
    {
        this.playSound(this.getStepSound(), 0.15F, 1.0F);
    }

    /**
     * Get this Entity's EnumCreatureAttribute
     */
    public EnumCreatureAttribute getCreatureAttribute()
    {
        return EnumCreatureAttribute.UNDEAD;
    }

    @Nullable
    protected ResourceLocation getLootTable()
    {
        return null;
    }

    public static void registerFixesHowler(DataFixer fixer)
    {
        EntityLiving.registerFixesMob(fixer, EntityDeath.class);
    }

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    public void writeEntityToNBT(NBTTagCompound compound)
    {
        super.writeEntityToNBT(compound);
        compound.setBoolean("defeated", defeated);
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readEntityFromNBT(NBTTagCompound compound)
    {
        super.readEntityFromNBT(compound);
        defeated=compound.getBoolean("defeated");
        if (this.hasCustomName())
        {
            this.bossInfo.setName(this.getDisplayName());
        }
    }
    
    public boolean processInteract(EntityPlayer player, EnumHand hand)
    {
    	if(!isFighting) {
        player.openGui(KCore.instance, KCore.GUI_ENUM.DEATH.ordinal(), 
                player.world, 
                (int)player.posX, 
                (int)player.posY, 
                (int)player.posZ); 
    	}
        return super.processInteract(player, hand);

    }

    /**
     * This method gets called when the entity kills another one.
     */
    public void onKillEntity(EntityLivingBase entityLivingIn)
    {
        super.onKillEntity(entityLivingIn);
        isFighting=false;
    }

    protected boolean canEquipItem(ItemStack stack)
    {
        return false;
    }

    /**
     * Called when the mob's health reaches 0.
     */
    public void onDeath(DamageSource cause)
    {

        super.onDeath(cause);

    }

}