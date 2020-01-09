package io.github.krevik.kathairis.entity;

import io.github.krevik.kathairis.enchantement.KathairisEnchantments;
import io.github.krevik.kathairis.init.ModEntities;
import io.github.krevik.kathairis.init.ModSounds;
import io.github.krevik.kathairis.util.networking.PacketHandler;
import io.github.krevik.kathairis.util.networking.packets.PacketClientOpenGuiOldMan;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Map;

public class EntityStrangeWanderer extends MobEntity
{
    public EntityStrangeWanderer(World worldIn)
    {
        super(ModEntities.STRANGE_WANDERER,worldIn);
        this.experienceValue=0;
    }

    public EntityStrangeWanderer(EntityType<EntityStrangeWanderer> type, World world) {
        super(type, world);
    }


    @Override
    public boolean attackEntityFrom(DamageSource source, float amount)
    {
            if(source.getTrueSource() instanceof PlayerEntity) {
                PlayerEntity attacker = (PlayerEntity) source.getTrueSource();
                Map<Enchantment, Integer> map = EnchantmentHelper.getEnchantments(attacker.getHeldItemMainhand());
                if(map.containsKey(KathairisEnchantments.ENCHANTMENT_ETHEREAL)) {
                    this.damageEntity(source, 6);
                    return true;
                }
            }
            if(source == DamageSource.IN_WALL || source == DamageSource.LAVA){
                return true;
            }
        return false;
    }

    @Override
    public int getMaxSpawnedInChunk()
    {
        return 1;
    }

    @Override
    protected void registerAttributes()
    {
        super.registerAttributes();
        this.getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(1.0D);
        this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0D);
        this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0D);
    }

    @Override
    public void notifyDataManagerChange(DataParameter<?> key)
    {
        super.notifyDataManagerChange(key);
    }
    
    @Override
    public void tick() {
    	super.tick();
        PlayerEntity ep = this.world.getClosestPlayer(getPosition().getX(), getPosition().getY(), getPosition().getZ(), 15, true);
    	if(ep!=null) {
            this.getLookController().setLookPosition(ep.getPosition().getX(), ep.getPosition().getY() + (double)ep.getEyeHeight(), ep.getPosition().getZ(), (float)100, (float)100);
    	}

    }

    @Override
    protected SoundEvent getAmbientSound()
    {
        return ModSounds.MOB_OLDMAN_AMBIENT;
    }

    @Override
    public boolean processInteract(PlayerEntity player, Hand hand)
    {
            if(player instanceof ServerPlayerEntity) {
                PacketHandler.sendTo(new PacketClientOpenGuiOldMan(), (ServerPlayerEntity) player);
            }
        return super.processInteract(player, hand);
    }

    @Override
    public CreatureAttribute getCreatureAttribute()
    {
        return CreatureAttribute.UNDEFINED;
    }

    @Nullable
    @Override
    protected ResourceLocation getLootTable()
    {
        return null;
    }
}