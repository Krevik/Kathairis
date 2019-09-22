package io.github.krevik.kathairis.entity;

import io.github.krevik.kathairis.entity.ai.EntityAIAvoidMovingSandsAndCactus;
import io.github.krevik.kathairis.init.ModEntities;
import io.github.krevik.kathairis.init.ModSounds;
import io.github.krevik.kathairis.util.KatharianLootTables;
import net.minecraft.block.SoundType;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.horse.AbstractHorseEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class EntityCamel extends AbstractHorseEntity
{

    public EntityCamel(World worldIn)
    {
        super(ModEntities.CAMEL,worldIn);
        //this.spawnableBlock= ModBlocks.KATHAIRIS_SAND;
    }

    public EntityCamel(EntityType<EntityCamel> type, World world) {
        super(type, world);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new EntityAIAvoidMovingSandsAndCactus(this,1.0D));
    }

    @Override
    protected void playGallopSound(SoundType p_190680_1_)
    {
        super.playGallopSound(p_190680_1_);

        if (this.rand.nextInt(10) == 0)
        {
            this.playSound(ModSounds.MOB_CAMEL_BREATH, p_190680_1_.getVolume() * 0.6F, p_190680_1_.getPitch());
        }
    }

    @Override
    protected ResourceLocation getLootTable() {
        return KatharianLootTables.LOOT_CAMEL;
    }

    @Override
    protected void registerAttributes()
    {
        super.registerAttributes();
        this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue((double)this.getModifiedMaxHealth());
        this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(this.getModifiedMovementSpeed());
        this.getAttribute(JUMP_STRENGTH).setBaseValue(this.getModifiedJumpStrength());
    }

    @Override
    public void tick()
    {
        super.tick();

        if (this.world.isRemote && this.getDataManager().isDirty())
        {
            this.getDataManager().setClean();
        }
    }

    @Override
    protected SoundEvent getAmbientSound()
    {
        super.getAmbientSound();
        return ModSounds.MOB_CAMEL_AMBIENT;
    }

    @Override
    protected SoundEvent getDeathSound()
    {
        super.getDeathSound();
        return ModSounds.MOB_CAMEL_DEAD;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn)
    {
        super.getHurtSound(damageSourceIn);
        return ModSounds.MOB_CAMEL_HURT;
    }

    @Override
    public boolean canMateWith(AnimalEntity otherAnimal)
    {
    	return false;
    }

    @Override
    public EntityCamel createChild(AgeableEntity ageable)
    {
        return new EntityCamel(this.world);
    }
}