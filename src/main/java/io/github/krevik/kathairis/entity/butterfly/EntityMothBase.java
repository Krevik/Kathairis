package io.github.krevik.kathairis.entity.butterfly;

import io.github.krevik.kathairis.util.KatharianLootTables;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class EntityMothBase extends EntityBasicButterfly
{
    private BlockPos spawnPosition;

    public EntityMothBase(World worldIn, EntityType type)
    {
        super(worldIn,type);
        this.setSize(0.15F, 0.15F);
        this.experienceValue=1;
    }

    @Override
    public int getMaxSpawnedInChunk()
    {
        return 1;
    }

    @Override
    public boolean canBePushed()
    {
        return false;
    }

    @Override
    protected void collideWithEntity(Entity entityIn)
    {
    }

    @Override
    protected void collideWithNearbyEntities()
    {
    }

    @Override
    protected void registerAttributes()
    {
        super.registerAttributes();
        this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(1.0D);
    }

    @Override
    protected boolean canTriggerWalking()
    {
        return false;
    }

    @Override
    public void fall(float distance, float damageMultiplier)
    {
    }

    @Override
    protected void updateFallState(double y, boolean onGroundIn, IBlockState state, BlockPos pos)
    {
    }

    @Override
    public boolean doesEntityNotTriggerPressurePlate()
    {
        return true;
    }

    @Nullable
    @Override
    protected ResourceLocation getLootTable()
    {
        return KatharianLootTables.LOOT_BUTTERFLY;
    }
}