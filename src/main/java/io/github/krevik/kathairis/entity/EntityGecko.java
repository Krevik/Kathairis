package io.github.krevik.kathairis.entity;

import io.github.krevik.kathairis.entity.ai.EntityAIAvoidMovingSandsAndCactus;
import io.github.krevik.kathairis.init.ModEntities;
import io.github.krevik.kathairis.util.KatharianLootTables;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.ClimberPathNavigator;
import net.minecraft.pathfinding.PathNavigator;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class EntityGecko extends AnimalEntity {
    private static final DataParameter<Integer> climbingSide = EntityDataManager.createKey(EntityGecko.class, DataSerializers.VARINT);
    private static final DataParameter<Integer> VARIANT = EntityDataManager.createKey(EntityGecko.class, DataSerializers.VARINT);

    public EntityGecko(World worldIn) {
        super(ModEntities.GECKO,worldIn);
        experienceValue = 10;
    }

    public EntityGecko(EntityType<EntityGecko> type, World world) {
        super(type, world);
    }


    @Override
    protected void registerGoals() {
        super.registerGoals();
        goalSelector.addGoal(0, new SwimGoal(this));
        goalSelector.addGoal(1, new PanicGoal(this, 1.25D));
        goalSelector.addGoal(2, new BreedGoal(this, 1.0D));
        goalSelector.addGoal(4, new FollowParentGoal(this, 1.1D));
        goalSelector.addGoal(6, new RandomWalkingGoal(this, 1.0D));
        goalSelector.addGoal(7, new LookAtGoal(this, PlayerEntity.class, 6.0F));
        goalSelector.addGoal(8, new LookRandomlyGoal(this));
        goalSelector.addGoal(3, new TemptGoal(this, 1.25D, false, Ingredient.fromItems(Items.PORKCHOP)));
        goalSelector.addGoal(0, new EntityAIAvoidMovingSandsAndCactus(this,1.2D));
    }

    @Override
    protected ResourceLocation getLootTable() {
        return KatharianLootTables.LOOT_GECKO;
    }


    @Nonnull
    @Override
    protected PathNavigator createNavigator(@Nonnull World worldIn)
    {
        return new ClimberPathNavigator(this, worldIn);
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
                setMotion(new Vec3d(-0.5+getRNG().nextDouble(),-1,-0.5+getRNG().nextDouble()));
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
    public void writeAdditional(CompoundNBT compound) {
        super.writeAdditional(compound);
        compound.putInt("climbingSide", climbingSide().ordinal());
        compound.putInt("Variant", getVariant());

    }

    @Override
    public void readAdditional(CompoundNBT compound) {
        super.readAdditional(compound);
        //migration purpose
        int side = compound.getInt("climbingSide");
        if (side < 0) setClimbingSide(EnumClimbSide.FLOOR);
        else setClimbingSide(EnumClimbSide.values()[side]);

        setVariant(compound.getInt("Variant"));
    }

    @Override
    public EntityGecko createChild(@Nonnull AgeableEntity ageable) {
        if (!world.isRemote) {
            if (world.getBlockState(getPosition().down()).isSolid()) {
                if (getRNG().nextInt(6) == 0) {
                   // world.setBlockState(getPosition(), KCore.Gecko_Eggs.getDefaultState());
                }
            }
        }
        return null;
    }

    @Nullable
    @Override
    public ILivingEntityData onInitialSpawn(IWorld p_213386_1_, DifficultyInstance p_213386_2_, SpawnReason p_213386_3_, @Nullable ILivingEntityData p_213386_4_, @Nullable CompoundNBT p_213386_5_) {
        setVariant(rand.nextInt(4));
        return super.onInitialSpawn(p_213386_1_, p_213386_2_, p_213386_3_, p_213386_4_, p_213386_5_);
    }


}