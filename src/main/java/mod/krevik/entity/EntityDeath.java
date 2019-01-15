package mod.krevik.entity;

import java.util.Map;

import javax.annotation.Nullable;

import mod.krevik.world.dimension.KetherDataStorage;
import mod.krevik.enchantment.KathairisEnchantments;
import mod.krevik.KCore;
import mod.krevik.network.KathairisPacketHandler;
import mod.krevik.network.packets.PacketDeathHandlerServer;

import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BossInfo;
import net.minecraft.world.BossInfoServer;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

public class EntityDeath extends EntityMob
{
	private boolean defeated;
	public boolean isFighting;
    private final BossInfoServer bossInfo = (BossInfoServer)(new BossInfoServer(this.getDisplayName(), BossInfo.Color.RED, BossInfo.Overlay.PROGRESS)).setDarkenSky(false);
    private static final DataParameter<Integer> scytheAttackTimer = EntityDataManager.<Integer>createKey(EntityDeath.class, DataSerializers.VARINT);
    private static final DataParameter<Boolean> isUsingSomeAttack = EntityDataManager.<Boolean>createKey(EntityDeath.class, DataSerializers.BOOLEAN);
    private static final DataParameter<Integer> dissolutionAttackTimer = EntityDataManager.<Integer>createKey(EntityDeath.class, DataSerializers.VARINT);

    public EntityDeath(World worldIn)
    {
        super(worldIn);
        this.setSize(1.25F, 4F);
        defeated=false;
        isFighting=false;
        dataManager.set(scytheAttackTimer,-1);
        dataManager.set(isUsingSomeAttack,false);
        dataManager.set(dissolutionAttackTimer,-1);

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
        this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
        this.tasks.addTask(2, new EntityAIAttackMelee(this, 1.0D, true));

    }
    

    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.2D);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(1000.0D);
        this.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(500);
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(25);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(8F);


    }

    protected void entityInit()
    {
        super.entityInit();
        this.dataManager.register(scytheAttackTimer, Integer.valueOf(-1));
        this.dataManager.register(isUsingSomeAttack, false);
        this.dataManager.register(dissolutionAttackTimer, Integer.valueOf(-1));

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
    protected void setEquipmentBasedOnDifficulty(DifficultyInstance difficulty)
    {
        super.setEquipmentBasedOnDifficulty(difficulty);
    }
    @Nullable
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata) {
        livingdata = super.onInitialSpawn(difficulty, livingdata);
        this.setEquipmentBasedOnDifficulty(difficulty);
        return livingdata;
    }
    public boolean attackEntityAsMob(Entity entityIn)
    {
        if(getIsUsingSomeAttack()){
            if(getScytheAttackTimer()>-1&&getScytheAttackTimer()<30){
                return super.attackEntityAsMob(entityIn);
            }
        }
        return false;
    }

    int mode=0;
    KetherDataStorage data;
    public void onUpdate() {
    	super.onUpdate();
	    	if(data==null) {
	    		data=KetherDataStorage.getDataInstance(world);
	    	}
    	if(data!=null) {
    	    isFighting=data.getIsDeathFighting();
            if(isFighting) {

            }
            if(getAttackTarget()!=null){
                if(mode==0){

                }
            }
    	}

    	if(!getIsUsingSomeAttack()){
            if(getRNG().nextInt(100)==0){
                dataManager.set(scytheAttackTimer,90);
            }
        }
        if(dataManager.get(scytheAttackTimer)>=0){
            dataManager.set(scytheAttackTimer,(Integer)dataManager.get(scytheAttackTimer)-1);
        }
        if(dataManager.get(dissolutionAttackTimer)>=0){
            dataManager.set(dissolutionAttackTimer,(Integer)dataManager.get(dissolutionAttackTimer)-1);
        }

        if((dataManager.get(scytheAttackTimer)!=-1||dataManager.get(dissolutionAttackTimer)!=-1)){
            dataManager.set(isUsingSomeAttack,true);
        }else{
            dataManager.set(isUsingSomeAttack,false);
        }
    }

    public boolean getIsUsingSomeAttack(){
        return dataManager.get(isUsingSomeAttack);
    }
    public int getScytheAttackTimer(){
        return dataManager.get(scytheAttackTimer);
    }
    public int getDissolutionAttackTimer(){
        return dataManager.get(dissolutionAttackTimer);
    }


    public boolean attackEntityFrom(DamageSource source, float amount)
    {
    	if(isFighting) {
    		if(source.getTrueSource() instanceof EntityPlayer) {
    			EntityPlayer attacker = (EntityPlayer) source.getTrueSource();
            	Map<Enchantment, Integer> map = EnchantmentHelper.getEnchantments(attacker.getHeldItemMainhand());
    			if(map.containsKey(KathairisEnchantments.Ethereal)) {
    				return super.attackEntityFrom(source,amount);
    			}
    		}
    	}
    	return false;
    }

    protected SoundEvent getAmbientSound()
    {
        return KCore.instance.proxy.death_living;
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
        isFighting=false;
        defeated=true;
        PacketDeathHandlerServer message = new PacketDeathHandlerServer(true,false,true);
        KathairisPacketHandler.CHANNEL.sendToServer(message);
        super.onKillEntity(entityLivingIn);

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