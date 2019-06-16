package io.github.krevik.kathairis.entity;

import io.github.krevik.kathairis.client.gui.GuiOldMan;
import io.github.krevik.kathairis.enchantement.KathairisEnchantments;
import io.github.krevik.kathairis.init.ModEntities;
import io.github.krevik.kathairis.init.ModGui;
import io.github.krevik.kathairis.init.ModSounds;
import io.github.krevik.kathairis.util.networking.PacketHandler;
import io.github.krevik.kathairis.util.networking.packets.PacketClientOpenGuiOldMan;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.GuiEventHandler;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.IInteractionObject;
import net.minecraft.world.World;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nullable;
import java.util.Map;

public class EntityStrangeWanderer extends EntityMob
{
    public EntityStrangeWanderer(World worldIn)
    {
        super(ModEntities.STRANGE_WANDERER,worldIn);
        this.setSize(1F, 2F);
        this.experienceValue=0;
    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float amount)
    {
            if(source.getTrueSource() instanceof EntityPlayer) {
                EntityPlayer attacker = (EntityPlayer) source.getTrueSource();
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
        this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(0.0D);
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
    	EntityPlayer ep = this.world.getClosestPlayer(posX, posY, posZ, 15, true);
    	if(ep!=null) {
            this.getLookHelper().setLookPosition(ep.posX, ep.posY + (double)ep.getEyeHeight(), ep.posZ, (float)100, (float)100);
    	}

    }

    @Override
    protected SoundEvent getAmbientSound()
    {
        return ModSounds.MOB_OLDMAN_AMBIENT;
    }

    @Override
    public boolean processInteract(EntityPlayer player, EnumHand hand)
    {
            if(player instanceof EntityPlayerMP) {
                PacketHandler.sendTo(new PacketClientOpenGuiOldMan(), (EntityPlayerMP) player);
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