package com.Krevik.Items;

import java.util.Random;

import com.Krevik.Main.CreativeTabsMystic;
import com.Krevik.Main.KCore;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.stats.StatList;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemMysticSeedFood extends ItemMysticFood implements net.minecraftforge.common.IPlantable
{
    private final Block crops;
    /** Block ID of the soil this seed food should be planted on. */
    private final Block soilId;
    private Random random = new Random();

    public ItemMysticSeedFood(String Name,int healAmount, float saturation, Block crops, Block soil)
    {
        super(Name, CreativeTabsMystic.mainTab, healAmount, saturation, false);
        this.crops = crops;
        this.soilId = soil;
    }
    
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving)
    {
        if (entityLiving instanceof EntityPlayer)
        {
            EntityPlayer entityplayer = (EntityPlayer)entityLiving;
            entityplayer.getFoodStats().addStats(this, stack);
            worldIn.playSound((EntityPlayer)null, entityplayer.posX, entityplayer.posY, entityplayer.posZ, SoundEvents.ENTITY_PLAYER_BURP, SoundCategory.PLAYERS, 0.5F, worldIn.rand.nextFloat() * 0.1F + 0.9F);
            this.onFoodEaten(stack, worldIn, entityplayer);
            entityplayer.addStat(StatList.getObjectUseStats(this));

            if (entityplayer instanceof EntityPlayerMP)
            {
                CriteriaTriggers.CONSUME_ITEM.trigger((EntityPlayerMP)entityplayer, stack);
                if(this==KCore.Heart) {
                	entityplayer.heal(2);
                }
                if(this==KCore.MagicBeansItem) {
                	setRandomPotionEffectOnPlayer((EntityPlayerMP)entityplayer);
                }
            }
            
        }

        stack.shrink(1);
        return stack;
    }
    
    private void setRandomPotionEffectOnPlayer(EntityPlayerMP ep) {
    	int[] allowedPotionIds = {1,3,4,5,6,8,10,11,12,13,14,16,21,22,23,24,25,26};
    	ep.addPotionEffect(new PotionEffect(Potion.getPotionById(allowedPotionIds[random.nextInt(allowedPotionIds.length)]),random.nextInt(420),random.nextInt(5)));
    }
    /**
     * Called when a Block is right-clicked with this Item
     */
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        ItemStack itemstack = player.getHeldItem(hand);
        net.minecraft.block.state.IBlockState state = worldIn.getBlockState(pos);
        if (facing == EnumFacing.UP && player.canPlayerEdit(pos.offset(facing), facing, itemstack) && KCore.functionHelper.canSustainPlantRemake(state, worldIn, pos, EnumFacing.UP, this) && worldIn.isAirBlock(pos.up()))
        {
            worldIn.setBlockState(pos.up(), this.crops.getDefaultState(), 11);
            itemstack.shrink(1);
            return EnumActionResult.SUCCESS;
        }
        else
        {
            return EnumActionResult.FAIL;
        }
    }

    @Override
    public net.minecraftforge.common.EnumPlantType getPlantType(net.minecraft.world.IBlockAccess world, BlockPos pos)
    {
        return net.minecraftforge.common.EnumPlantType.Crop;
    }

    @Override
    public net.minecraft.block.state.IBlockState getPlant(net.minecraft.world.IBlockAccess world, BlockPos pos)
    {
        return this.crops.getDefaultState();
    }
    
    
}