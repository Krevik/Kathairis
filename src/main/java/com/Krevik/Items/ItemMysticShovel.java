package com.Krevik.Items;

import java.util.List;
import java.util.Set;

import javax.annotation.Nullable;

import com.Krevik.Main.KCore;
import com.google.common.collect.Sets;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemMysticShovel extends MysticTool
{
    private static final Set<Block> EFFECTIVE_ON = Sets.newHashSet(Blocks.CLAY, Blocks.DIRT, Blocks.FARMLAND, Blocks.GRASS, Blocks.GRAVEL, 
    		Blocks.MYCELIUM, Blocks.SAND, Blocks.SNOW, Blocks.SNOW_LAYER, Blocks.SOUL_SAND, Blocks.GRASS_PATH, Blocks.CONCRETE_POWDER,
    		KCore.ForgottenSand, KCore.MovingSand, KCore.CorruptedDirt, KCore.CorruptedGrass,KCore.MudBlock
    		,KCore.BlueCloud,KCore.BlueCondensedCloud,KCore.YellowCloud,KCore.YellowCondensedCloud);

    public ItemMysticShovel(String Name,CreativeTabs tab,Item.ToolMaterial material)
    {
        super(Name,tab,1.5F, -3.0F, material, EFFECTIVE_ON);
    }
    
    public boolean onBlockDestroyed(ItemStack stack, World worldIn, IBlockState state, BlockPos pos, EntityLivingBase entityLiving)
    {
    	if(!worldIn.isRemote) {
        	if(stack.getItem().equals(KCore.Magnethium_Shovel)) {
	        	EntityLivingBase player = entityLiving;
	        	List<EntityItem> list = worldIn.getEntitiesWithinAABB(EntityItem.class, new AxisAlignedBB(player.posX-5,player.posY-5,player.posZ-5,player.posX+5,player.posY+5,player.posZ+5));
	        	for(int c=0;c<list.size();c++) {
	        		list.get(c).setNoGravity(true);
	        	}
        	}
    	}
    	return super.onBlockDestroyed(stack, worldIn, state, pos, entityLiving);
    }

    /**
     * Check whether this Item can harvest the given Block
     */
    public boolean canHarvestBlock(IBlockState blockIn)
    {
        Block block = blockIn.getBlock();

        if (block == Blocks.SNOW_LAYER)
        {
            return true;
        }
        else
        {
            return block == Blocks.SNOW;
        }
    }
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
    	tooltip.add("Durability: " + (this.getMaxDamage()-this.getDamage(stack)));
    }

    /**
     * Called when a Block is right-clicked with this Item
     */
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        ItemStack itemstack = player.getHeldItem(hand);

        if (!player.canPlayerEdit(pos.offset(facing), facing, itemstack))
        {
            return EnumActionResult.FAIL;
        }
        else
        {
            IBlockState iblockstate = worldIn.getBlockState(pos);
            Block block = iblockstate.getBlock();

            if (facing != EnumFacing.DOWN && worldIn.getBlockState(pos.up()).getMaterial() == Material.AIR && block == Blocks.GRASS)
            {
                IBlockState iblockstate1 = Blocks.GRASS_PATH.getDefaultState();
                worldIn.playSound(player, pos, SoundEvents.ITEM_SHOVEL_FLATTEN, SoundCategory.BLOCKS, 1.0F, 1.0F);

                if (!worldIn.isRemote)
                {
                    worldIn.setBlockState(pos, iblockstate1, 11);
                    itemstack.damageItem(1, player);
                }

                return EnumActionResult.SUCCESS;
            }
            else
            {
                return EnumActionResult.PASS;
            }
        }
    }
}