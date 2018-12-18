package mod.krevik.Items;

import java.util.List;

import javax.annotation.Nullable;

import mod.krevik.Entities.EntityShockingBall;
import mod.krevik.Main.FunctionHelper;
import mod.krevik.Main.KCore;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BaseWand extends BaseItem{

	public BaseWand(String name, CreativeTabs tab) {
		super(name, tab);
		this.setMaxStackSize(1);
		this.setMaxDamage(1000);
	}
	private EntityLivingBase lastKilledEntity;
	
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker)
    {
    	if(stack.getItem()==KCore.DeathWand) {
	    		if(!(target instanceof EntityPlayer)&&!(target instanceof EntityPlayerMP)&&!(target instanceof EntityWither)&&!(target instanceof EntityDragon)) {
	            stack.damageItem(2, attacker);
	    		lastKilledEntity=target;
	    		target.setDead();
	    		target.onDeath(DamageSource.MAGIC);
	    		}
    	}
    	
    	return true;
    }
	
	
	
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer player, EnumHand handIn)
    {
        ItemStack itemstack = player.getHeldItem(handIn);

		FunctionHelper helper = KCore.instance.functionHelper;
		if(itemstack.getItem()==KCore.ShockWand) {
			if(this.getMaxDamage()-this.getDamage(itemstack)>1) {

				if (!player.capabilities.isCreativeMode)
				{
					itemstack.damageItem(1, player);
				}

				//worldIn.playSound((EntityPlayer)null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.ENTITY_EGG_THROW, SoundCategory.PLAYERS, 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));

				if (!worldIn.isRemote)
				{
					EntityShockingBall entityegg = new EntityShockingBall(worldIn, player.posX+helper.getForward(player).x,player.posY+player.eyeHeight,player.posZ+helper.getForward(player).z);
					entityegg.shoot(player, player.rotationPitch, player.rotationYaw, 0.0F, 1.5F, 1.0F);
					worldIn.spawnEntity(entityegg);
				}


				player.addStat(StatList.getObjectUseStats(this));
				return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemstack);
			}else {
				return new ActionResult<ItemStack>(EnumActionResult.PASS, itemstack);
			}
		}
		return new ActionResult<ItemStack>(EnumActionResult.PASS, itemstack);

	}
      	
        public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
        {
            ItemStack itemstack = player.getHeldItem(hand);


          	if(this==KCore.DeathWand) {
    	    	if(this.getMaxDamage()-this.getDamage(itemstack)>6) {
    	
    	        if (!player.capabilities.isCreativeMode)
    	        {
    	            itemstack.damageItem(5, player);
    	        }
    	
    	        //worldIn.playSound((EntityPlayer)null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.ENTITY_EGG_THROW, SoundCategory.PLAYERS, 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
    	        
    	        if (!worldIn.isRemote)
    	        {
    	        	if(lastKilledEntity!=null) {
    	        		if(!(lastKilledEntity instanceof EntityPlayerMP)) {
		    	        	lastKilledEntity.setHealth(lastKilledEntity.getMaxHealth());
		    	        	lastKilledEntity.isDead=false;
		    	        	lastKilledEntity.setDropItemsWhenDead(true);
		    	        	lastKilledEntity.setLastAttackedEntity(null);
		    	        	player.world.setEntityState(lastKilledEntity, (byte)(4));
		    	        	lastKilledEntity.resetEntityId();
		    	        	lastKilledEntity.setWorld(player.world);
		    	        	lastKilledEntity.setPosition(pos.getX(), pos.getY()+1, pos.getZ());
		    	        	player.world.spawnEntity(lastKilledEntity);
		    	        	lastKilledEntity.setPosition(pos.getX(), pos.getY()+1, pos.getZ());
		    	        	lastKilledEntity.isDead=false;
		    	        	player.world.setEntityState(lastKilledEntity, (byte)(4));
		    	        	lastKilledEntity=null;
    	        		}

    	        	}

    	        }
    	        

    	        player.addStat(StatList.getObjectUseStats(this));
                return EnumActionResult.SUCCESS;

    	    	}
          	}
            return EnumActionResult.PASS;
        }
    	
	 public EnumRarity getRarity(ItemStack stack)
	 {
		 if(this==KCore.DeathWand) {
			 return EnumRarity.EPIC;
		 }
			 return EnumRarity.UNCOMMON;
	 }
	 @SideOnly(Side.CLIENT)
	 public boolean hasEffect(ItemStack stack)
	 {
		 return true;
	 }
	 
	    @SideOnly(Side.CLIENT)
	    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
	    {
	    	tooltip.add("Durability: " + (this.getMaxDamage()-this.getDamage(stack))+ " /1000");
	    }
	    public boolean isEnchantable(ItemStack stack)
	    {
	        return false;
	    }
}
