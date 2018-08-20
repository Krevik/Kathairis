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
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemMysticPickaxe extends MysticTool
{
    private static final Set<Block> EFFECTIVE_ON = Sets.newHashSet(Blocks.ACTIVATOR_RAIL, Blocks.COAL_ORE, Blocks.COBBLESTONE, 
    		Blocks.DETECTOR_RAIL, Blocks.DIAMOND_BLOCK, Blocks.DIAMOND_ORE, Blocks.DOUBLE_STONE_SLAB, Blocks.GOLDEN_RAIL, Blocks.GOLD_BLOCK, 
    		Blocks.GOLD_ORE, Blocks.ICE, Blocks.IRON_BLOCK, Blocks.IRON_ORE, Blocks.LAPIS_BLOCK, Blocks.LAPIS_ORE, Blocks.LIT_REDSTONE_ORE, 
    		Blocks.MOSSY_COBBLESTONE, Blocks.NETHERRACK, Blocks.PACKED_ICE, Blocks.RAIL, Blocks.REDSTONE_ORE, Blocks.SANDSTONE, 
    		Blocks.RED_SANDSTONE, Blocks.STONE, Blocks.STONE_SLAB, Blocks.STONE_BUTTON, Blocks.STONE_PRESSURE_PLATE,KCore.MythicStone,
    		KCore.TitaniumOre,KCore.TitaniumBlock,KCore.MythicStoneTiles,KCore.WeatheredRock,KCore.HardenedWeatheredRock,KCore.WeatheredRockTiles,
    		KCore.WeatheredRockBricks,KCore.ShinyRock,KCore.RevenumOre,KCore.YellowCrystal,KCore.BlueCrystal,KCore.VioletCrystal,
    		KCore.MythicStonePillar,KCore.GemsOre,KCore.HardenedWeatheredRockStairs,KCore.MythicStoneStairs,
    		KCore.MythicStoneWall,KCore.HardenedWeatheredRockWall,KCore.BlueCloudBricks,KCore.YellowCloudBlock,KCore.TurtleShellPlate,
    		KCore.Magnethium,KCore.HardenedWeatheredRockBricksStairs,KCore.SoulStoneBricksWithBones,KCore.SoulStoneBricks,KCore.BoneBricks,
    		KCore.ChiseledBoneBricks,KCore.Magnethium,KCore.Mystic_Gem_Block,KCore.Mythic_Cobblestone,KCore.Revenum_Trapdoor,
    		KCore.Revenum_Door);

    public ItemMysticPickaxe(String Name, CreativeTabs tab, Item.ToolMaterial material)
    {
        super(Name,tab, 1.0F, -2.8F, material, EFFECTIVE_ON);
    }

    /**
     * Check whether this Item can harvest the given Block
     */
    public boolean canHarvestBlock(IBlockState blockIn)
    {
        Block block = blockIn.getBlock();

        if (block == Blocks.OBSIDIAN)
        {
            return this.toolMaterial.getHarvestLevel() == 3;
        }
        else if (block != Blocks.DIAMOND_BLOCK && block != Blocks.DIAMOND_ORE)
        {
            if (block != Blocks.EMERALD_ORE && block != Blocks.EMERALD_BLOCK)
            {
                if (block != Blocks.GOLD_BLOCK && block != Blocks.GOLD_ORE)
                {
                    if (block != Blocks.IRON_BLOCK && block != Blocks.IRON_ORE)
                    {
                        if (block != Blocks.LAPIS_BLOCK && block != Blocks.LAPIS_ORE)
                        {
                            if (block != Blocks.REDSTONE_ORE && block != Blocks.LIT_REDSTONE_ORE)
                            {
                                Material material = blockIn.getMaterial();

                                if (material == Material.ROCK)
                                {
                                    return true;
                                }
                                else if (material == Material.IRON)
                                {
                                    return true;
                                }
                                else
                                {
                                    return material == Material.ANVIL;
                                }
                            }
                            else
                            {
                                return this.toolMaterial.getHarvestLevel() >= 2;
                            }
                        }
                        else
                        {
                            return this.toolMaterial.getHarvestLevel() >= 1;
                        }
                    }
                    else
                    {
                        return this.toolMaterial.getHarvestLevel() >= 1;
                    }
                }
                else
                {
                    return this.toolMaterial.getHarvestLevel() >= 2;
                }
            }
            else
            {
                return this.toolMaterial.getHarvestLevel() >= 2;
            }
        }
        else
        {
            return this.toolMaterial.getHarvestLevel() >= 2;
        }
    }
    
    public boolean onBlockDestroyed(ItemStack stack, World worldIn, IBlockState state, BlockPos pos, EntityLivingBase entityLiving)
    {
    	boolean done=false;
        if (!worldIn.isRemote && (double)state.getBlockHardness(worldIn, pos) != 0.0D)
        {
            stack.damageItem(1, entityLiving);
        }
        if(this==KCore.CrystalPickaxe) {
        	if(!worldIn.isRemote) {
           		//down
        		if(!done) {
            	if(!worldIn.isAirBlock(pos.down())&&!worldIn.isAirBlock(pos.west())&&!worldIn.isAirBlock(pos.east())&&!worldIn.isAirBlock(pos.west())&&
            			!worldIn.isAirBlock(pos.north())&&!worldIn.isAirBlock(pos.south())&&!worldIn.isAirBlock(pos.south().west())&&!worldIn.isAirBlock(pos.south().east())&&
            			!worldIn.isAirBlock(pos.north().east())&&!worldIn.isAirBlock(pos.north().west())) {
            		worldIn.destroyBlock(pos.east(), true);
            		worldIn.destroyBlock(pos.west(), true);
            		worldIn.destroyBlock(pos.south(), true);
            		worldIn.destroyBlock(pos.north(), true);
            		worldIn.destroyBlock(pos.south().east(), true);
            		worldIn.destroyBlock(pos.south().west(), true);
            		worldIn.destroyBlock(pos.north().east(), true);
            		worldIn.destroyBlock(pos.north().west(), true);
            		done=true;
            	}

            	}	
            	//up
        		if(!done)
        		{
            	if(!worldIn.isAirBlock(pos.up())&&!worldIn.isAirBlock(pos.west())&&!worldIn.isAirBlock(pos.east())&&!worldIn.isAirBlock(pos.west())&&
            			!worldIn.isAirBlock(pos.north())&&!worldIn.isAirBlock(pos.south())&&!worldIn.isAirBlock(pos.south().west())&&!worldIn.isAirBlock(pos.south().east())&&
            			!worldIn.isAirBlock(pos.north().east())&&!worldIn.isAirBlock(pos.north().west())) {
            		worldIn.destroyBlock(pos.east(), true);
            		worldIn.destroyBlock(pos.west(), true);
            		worldIn.destroyBlock(pos.south(), true);
            		worldIn.destroyBlock(pos.north(), true);
            		worldIn.destroyBlock(pos.south().east(), true);
            		worldIn.destroyBlock(pos.south().west(), true);
            		worldIn.destroyBlock(pos.north().east(), true);
            		worldIn.destroyBlock(pos.north().west(), true);
            		done=true;
            	}

            	}
            	//west/east
        		if(!done)
        		{
            	if(!worldIn.isAirBlock(pos.down())&&!worldIn.isAirBlock(pos.up())&&
            		!worldIn.isAirBlock(pos.west())&&!worldIn.isAirBlock(pos.east())
            		&&!worldIn.isAirBlock(pos.west().up())&&!worldIn.isAirBlock(pos.west().down())
            		&&!worldIn.isAirBlock(pos.east().up())&&!worldIn.isAirBlock(pos.east().down())) {
            		worldIn.destroyBlock(pos.down(), true);
            		worldIn.destroyBlock(pos.up(), true);
            		worldIn.destroyBlock(pos.west(), true);
            		worldIn.destroyBlock(pos.east(), true);
            		worldIn.destroyBlock(pos.west().down(), true);
            		worldIn.destroyBlock(pos.west().up(), true);
            		worldIn.destroyBlock(pos.east().down(), true);
            		worldIn.destroyBlock(pos.east().up(), true);
            		done=true;
            	}
            	}
            	//north/south
        		if(!done) {
            	if(!worldIn.isAirBlock(pos.down())&&!worldIn.isAirBlock(pos.up())&&
                		!worldIn.isAirBlock(pos.north())&&!worldIn.isAirBlock(pos.south())
                		&&!worldIn.isAirBlock(pos.north().up())&&!worldIn.isAirBlock(pos.north().down())
                		&&!worldIn.isAirBlock(pos.south().up())&&!worldIn.isAirBlock(pos.south().down())) {
                		worldIn.destroyBlock(pos.down(), true);
                		worldIn.destroyBlock(pos.up(), true);
                		worldIn.destroyBlock(pos.north(), true);
                		worldIn.destroyBlock(pos.south(), true);
                		worldIn.destroyBlock(pos.north().down(), true);
                		worldIn.destroyBlock(pos.north().up(), true);
                		worldIn.destroyBlock(pos.south().down(), true);
                		worldIn.destroyBlock(pos.south().up(), true);
                		done=true;
            	}
                	}
        	}else {
           		//down
        		if(!done) {
            	if(!worldIn.isAirBlock(pos.down())&&!worldIn.isAirBlock(pos.west())&&!worldIn.isAirBlock(pos.east())&&!worldIn.isAirBlock(pos.west())&&
            			!worldIn.isAirBlock(pos.north())&&!worldIn.isAirBlock(pos.south())&&!worldIn.isAirBlock(pos.south().west())&&!worldIn.isAirBlock(pos.south().east())&&
            			!worldIn.isAirBlock(pos.north().east())&&!worldIn.isAirBlock(pos.north().west())) {
            		worldIn.destroyBlock(pos.east(), true);
            		worldIn.destroyBlock(pos.west(), true);
            		worldIn.destroyBlock(pos.south(), true);
            		worldIn.destroyBlock(pos.north(), true);
            		worldIn.destroyBlock(pos.south().east(), true);
            		worldIn.destroyBlock(pos.south().west(), true);
            		worldIn.destroyBlock(pos.north().east(), true);
            		worldIn.destroyBlock(pos.north().west(), true);
            		done=true;
            	}

            	}	
            	//up
        		if(!done)
        		{
            	if(!worldIn.isAirBlock(pos.up())&&!worldIn.isAirBlock(pos.west())&&!worldIn.isAirBlock(pos.east())&&!worldIn.isAirBlock(pos.west())&&
            			!worldIn.isAirBlock(pos.north())&&!worldIn.isAirBlock(pos.south())&&!worldIn.isAirBlock(pos.south().west())&&!worldIn.isAirBlock(pos.south().east())&&
            			!worldIn.isAirBlock(pos.north().east())&&!worldIn.isAirBlock(pos.north().west())) {
            		worldIn.destroyBlock(pos.east(), true);
            		worldIn.destroyBlock(pos.west(), true);
            		worldIn.destroyBlock(pos.south(), true);
            		worldIn.destroyBlock(pos.north(), true);
            		worldIn.destroyBlock(pos.south().east(), true);
            		worldIn.destroyBlock(pos.south().west(), true);
            		worldIn.destroyBlock(pos.north().east(), true);
            		worldIn.destroyBlock(pos.north().west(), true);
            		done=true;
            	}
            	}
            	//west/east
        		if(!done) {
            	if(!worldIn.isAirBlock(pos.down())&&!worldIn.isAirBlock(pos.up())&&
            		!worldIn.isAirBlock(pos.west())&&!worldIn.isAirBlock(pos.east())
            		&&!worldIn.isAirBlock(pos.west().up())&&!worldIn.isAirBlock(pos.west().down())
            		&&!worldIn.isAirBlock(pos.east().up())&&!worldIn.isAirBlock(pos.east().down())) {
            		worldIn.destroyBlock(pos.down(), true);
            		worldIn.destroyBlock(pos.up(), true);
            		worldIn.destroyBlock(pos.west(), true);
            		worldIn.destroyBlock(pos.east(), true);
            		worldIn.destroyBlock(pos.west().down(), true);
            		worldIn.destroyBlock(pos.west().up(), true);
            		worldIn.destroyBlock(pos.east().down(), true);
            		worldIn.destroyBlock(pos.east().up(), true);
            		done=true;
            	}
            	}
            	//north/south
        		if(!done) {
            	if(!worldIn.isAirBlock(pos.down())&&!worldIn.isAirBlock(pos.up())&&
                		!worldIn.isAirBlock(pos.north())&&!worldIn.isAirBlock(pos.south())
                		&&!worldIn.isAirBlock(pos.north().up())&&!worldIn.isAirBlock(pos.north().down())
                		&&!worldIn.isAirBlock(pos.south().up())&&!worldIn.isAirBlock(pos.south().down())) {
                		worldIn.destroyBlock(pos.down(), true);
                		worldIn.destroyBlock(pos.up(), true);
                		worldIn.destroyBlock(pos.north(), true);
                		worldIn.destroyBlock(pos.south(), true);
                		worldIn.destroyBlock(pos.north().down(), true);
                		worldIn.destroyBlock(pos.north().up(), true);
                		worldIn.destroyBlock(pos.south().down(), true);
                		worldIn.destroyBlock(pos.south().up(), true);
                		done=true;
                	}
        		}
        	}
 
        }
        return true;
    }
    
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
        return new ActionResult<ItemStack>(EnumActionResult.PASS, playerIn.getHeldItem(handIn));
    }

    public float getStrVsBlock(ItemStack stack, IBlockState state)
    {
        Material material = state.getMaterial();
        return material != Material.IRON && material != Material.ANVIL && material != Material.ROCK ? super.getStrVsBlock(stack, state) : this.efficiencyOnProperMaterial;
    }
    
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
    	tooltip.add("Durability: " + (this.getMaxDamage()-this.getDamage(stack)));
    }
}