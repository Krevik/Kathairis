package io.github.krevik.kathairis.entity;

import io.github.krevik.kathairis.entity.ai.EntityAIAvoidMovingSandsAndCactus;
import io.github.krevik.kathairis.init.ModEntities;
import io.github.krevik.kathairis.util.KatharianLootTables;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.pathfinding.PathNavigateClimber;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class EntityGecko extends EntityAnimal {
    private static final DataParameter<Integer> climbingSide = EntityDataManager.createKey(EntityGecko.class, DataSerializers.VARINT);
    private static final DataParameter<Integer> VARIANT = EntityDataManager.createKey(EntityGecko.class, DataSerializers.VARINT);

    public EntityGecko(World worldIn) {
        super(ModEntities.GECKO,worldIn);
        setSize(0.7F, 0.25F);
        experienceValue = 10;
    }

    @Override
    protected void initEntityAI() {
        tasks.addTask(0, new EntityAISwimming(this));
        tasks.addTask(1, new EntityAIPanic(this, 1.25D));
        tasks.addTask(2, new EntityAIMate(this, 1.0D));
        tasks.addTask(4, new EntityAIFollowParent(this, 1.1D));
        tasks.addTask(6, new EntityAIWanderAvoidWater(this, 1.0D));
        tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        tasks.addTask(8, new EntityAILookIdle(this));
        tasks.addTask(3, new EntityAITempt(this, 1.25D, false, Ingredient.fromItems(Items.PORKCHOP)));
        tasks.addTask(0, new EntityAIAvoidMovingSandsAndCactus(this,1.2D));

    }

    @Nonnull
    @Override
    protected PathNavigate createNavigator(@Nonnull World worldIn)
    {
        return new PathNavigateClimber(this, worldIn);
    }

    @Override
    protected void registerData() {
        super.registerData();
        this.getDataManager().register(climbingSide, EnumClimbSide.FLOOR.ordinal());
        this.getDataManager().register(VARIANT, 0);

    }

    public int getVariant()
    {
        return MathHelper.clamp(getDataManager().get(VARIANT), 0, 4);
    }

    public void setVariant(int meta) {
        getDataManager().set(VARIANT, meta);
    }

    public boolean isClimbing() {
        return climbingSide() != EnumClimbSide.FLOOR;

    }
    public EnumClimbSide climbingSide() {
    	return EnumClimbSide.values()[getDataManager().get(climbingSide)];
    }

    private void setClimbingSide(EnumClimbSide side) {
    	getDataManager().set(climbingSide, side.ordinal());
    }

    @Override
    public int getMaxSpawnedInChunk()
    {
        return 4;
    }

    @Override
    protected void registerAttributes() {
        super.registerAttributes();
        this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(7.0D);
        this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3000000417232513D);
    }

    @Override
    protected ResourceLocation getLootTable()
    {
    	return KatharianLootTables.LOOT_GECKO;
    }

    @Override
    public boolean isOnLadder()
    {
        return this.isClimbing();
    }

    @Override
    public void tick() {
        super.tick();
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

    @Override
    public void writeAdditional(NBTTagCompound compound) {
        super.writeAdditional(compound);
        compound.putInt("climbingSide", climbingSide().ordinal());
        compound.putInt("Variant", getVariant());

    }

    @Override
    public void readAdditional(NBTTagCompound compound) {
        super.readAdditional(compound);
        //migration purpose
        int side = compound.getInt("climbingSide");
        if (side < 0) setClimbingSide(EnumClimbSide.FLOOR);
        else setClimbingSide(EnumClimbSide.values()[side]);

        setVariant(compound.getInt("Variant"));
    }

    @Override
    public EntityGecko createChild(@Nonnull EntityAgeable ageable) {
        if (!world.isRemote) {
            if (world.isBlockFullCube(getPosition().down())) {
                if (getRNG().nextInt(6) == 0) {
                   // world.setBlockState(getPosition(), KCore.Gecko_Eggs.getDefaultState());
                }
            }
        }
        return null;
    }

    @Nullable
    @Override
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata, @Nullable NBTTagCompound nbt) {
        livingdata = super.onInitialSpawn(difficulty, livingdata,nbt);
        setVariant(rand.nextInt(4));
        return livingdata;
    }

    @Override
    public float getEyeHeight() {
        return 0.95F * height;
    }

}