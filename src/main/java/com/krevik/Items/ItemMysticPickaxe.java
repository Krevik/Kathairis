package com.krevik.Items;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Nullable;

import com.krevik.Main.KCore;
import com.google.common.collect.Sets;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.math.*;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemMysticPickaxe extends MysticTool
{
	protected int mode;
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
    		KCore.ChiseledBoneBricks,KCore.Magnethium,KCore.Mystic_Gem_Block,KCore.Mythic_Cobblestone,
    		KCore.Block_Iron_Gold, KCore.Refined_Cloud_Blue, KCore.Refined_Cloud_Yellow, KCore.Solis_Crystals
    		);
	private EntityPlayer player;

    public ItemMysticPickaxe(String Name, CreativeTabs tab, Item.ToolMaterial material)
    {
        super(Name,tab, 1.0F, -2.8F, material, EFFECTIVE_ON);
        mode=0;
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
		if(this==KCore.CrystalPickaxe&&mode==0) {
			if (entityLiving instanceof EntityPlayer) {
				EntityPlayer player = (EntityPlayer) entityLiving;
				player.addExhaustion(1f);
				Vec3d vec = new Vec3d(pos.getX() - player.posX, pos.getY() - player.posY, pos.getZ() - player.posZ).normalize();
				EnumFacing facing = null;
				if (vec.z >= 0.5f && MathHelper.abs((float) vec.x) < 0.5f && MathHelper.abs((float) vec.y) < 0.5f) {
					facing = EnumFacing.SOUTH;
				} else if (vec.z <= -0.5f && MathHelper.abs((float) vec.x) < 0.5f && MathHelper.abs((float) vec.y) < 0.5f) {
					facing = EnumFacing.NORTH;
				} else if (MathHelper.abs((float) vec.z) < 0.5f && MathHelper.abs((float) vec.y) < 0.5f && vec.x >= 0.5f) {
					facing = EnumFacing.EAST;
				} else if (MathHelper.abs((float) vec.z) < 0.5f && MathHelper.abs((float) vec.y) < 0.5f && vec.x <= -0.5f) {
					facing = EnumFacing.WEST;
				} else if (vec.y >= 0.5f) {
					facing = EnumFacing.UP;
				} else if (vec.y <= -0.5f) {
					facing = EnumFacing.DOWN;
				} else {
					facing = EnumFacing.EAST;
				}
				ArrayList<BlockPos> blocksToBreak = new ArrayList<>();
				if (facing == EnumFacing.SOUTH|| facing==EnumFacing.NORTH) {
					BlockPos pos0 = pos.west();
					BlockPos pos1 = pos.east();
					BlockPos pos2 = pos.up();
					BlockPos pos3 = pos.down();
					BlockPos pos4 = pos.west().down();
					BlockPos pos5 = pos.east().up();
					BlockPos pos6 = pos.west().up();
					BlockPos pos7 = pos.east().down();
					blocksToBreak.add(pos0);
					blocksToBreak.add(pos1);
					blocksToBreak.add(pos2);
					blocksToBreak.add(pos3);
					blocksToBreak.add(pos4);
					blocksToBreak.add(pos5);
					blocksToBreak.add(pos6);
					blocksToBreak.add(pos7);
				}
				if(facing==EnumFacing.EAST||facing==EnumFacing.WEST){
					BlockPos pos0 = pos.north();
					BlockPos pos1 = pos.south();
					BlockPos pos2 = pos.up();
					BlockPos pos3 = pos.down();
					BlockPos pos4 = pos.north().down();
					BlockPos pos5 = pos.south().up();
					BlockPos pos6 = pos.north().up();
					BlockPos pos7 = pos.south().down();
					blocksToBreak.add(pos0);
					blocksToBreak.add(pos1);
					blocksToBreak.add(pos2);
					blocksToBreak.add(pos3);
					blocksToBreak.add(pos4);
					blocksToBreak.add(pos5);
					blocksToBreak.add(pos6);
					blocksToBreak.add(pos7);
				}
				if(facing==EnumFacing.UP||facing==EnumFacing.DOWN){
					BlockPos pos0 = pos.north();
					BlockPos pos1 = pos.south();
					BlockPos pos2 = pos.east();
					BlockPos pos3 = pos.west();
					BlockPos pos4 = pos.north().west();
					BlockPos pos5 = pos.south().east();
					BlockPos pos6 = pos.north().east();
					BlockPos pos7 = pos.south().west();
					blocksToBreak.add(pos0);
					blocksToBreak.add(pos1);
					blocksToBreak.add(pos2);
					blocksToBreak.add(pos3);
					blocksToBreak.add(pos4);
					blocksToBreak.add(pos5);
					blocksToBreak.add(pos6);
					blocksToBreak.add(pos7);
				}
				if(!worldIn.isRemote){
					Map<Enchantment, Integer> map = EnchantmentHelper.getEnchantments(stack);
					if(map.containsKey(Enchantments.SILK_TOUCH)&&!map.containsKey(Enchantments.FORTUNE)) {
						for(BlockPos positionToBreak:blocksToBreak){
							Block.spawnAsEntity(worldIn,positionToBreak,new ItemStack(worldIn.getBlockState(positionToBreak).getBlock(),1));
							destroyBlock(worldIn,positionToBreak, false);
						}
					}
					else if(!map.containsKey(Enchantments.SILK_TOUCH)&&map.containsKey(Enchantments.FORTUNE)) {
						for (BlockPos positionToBreak : blocksToBreak) {
							worldIn.getBlockState(positionToBreak).getBlock().dropBlockAsItem(worldIn,positionToBreak,worldIn.getBlockState(positionToBreak),map.get(Enchantments.FORTUNE).intValue());
							destroyBlock(worldIn,positionToBreak, false);
						}
					} else{
						for(BlockPos positionToBreak:blocksToBreak){
							destroyBlock(worldIn,positionToBreak, true);
						}
					}
				}
			}

			/*if (!worldIn.isRemote) {
				//down
				if(!done) {
					if(!worldIn.isAirBlock(pos.down())&&!worldIn.isAirBlock(pos.west())&&!worldIn.isAirBlock(pos.east())&&!worldIn.isAirBlock(pos.west())&&
							!worldIn.isAirBlock(pos.north())&&!worldIn.isAirBlock(pos.south())&&!worldIn.isAirBlock(pos.south().west())&&!worldIn.isAirBlock(pos.south().east())&&
							!worldIn.isAirBlock(pos.north().east())&&!worldIn.isAirBlock(pos.north().west())) {

						destroyBlock(worldIn,pos.east(), true);
						destroyBlock(worldIn,pos.west(), true);
						destroyBlock(worldIn,pos.south(), true);
						destroyBlock(worldIn,pos.north(), true);
						destroyBlock(worldIn,pos.south().east(), true);
						destroyBlock(worldIn,pos.south().west(), true);
						destroyBlock(worldIn,pos.north().east(), true);
						destroyBlock(worldIn,pos.north().west(), true);
						done=true;
					}

				}
				//up
				if(!done)
				{
					if(!worldIn.isAirBlock(pos.up())&&!worldIn.isAirBlock(pos.west())&&!worldIn.isAirBlock(pos.east())&&!worldIn.isAirBlock(pos.west())&&
							!worldIn.isAirBlock(pos.north())&&!worldIn.isAirBlock(pos.south())&&!worldIn.isAirBlock(pos.south().west())&&!worldIn.isAirBlock(pos.south().east())&&
							!worldIn.isAirBlock(pos.north().east())&&!worldIn.isAirBlock(pos.north().west())) {
						destroyBlock(worldIn,pos.east(), true);
						destroyBlock(worldIn,pos.west(), true);
						destroyBlock(worldIn,pos.south(), true);
						destroyBlock(worldIn,pos.north(), true);
						destroyBlock(worldIn,pos.south().east(), true);
						destroyBlock(worldIn,pos.south().west(), true);
						destroyBlock(worldIn,pos.north().east(), true);
						destroyBlock(worldIn,pos.north().west(), true);
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
						destroyBlock(worldIn,pos.down(), true);
						destroyBlock(worldIn,pos.up(), true);
						destroyBlock(worldIn,pos.west(), true);
						destroyBlock(worldIn,pos.east(), true);
						destroyBlock(worldIn,pos.west().down(), true);
						destroyBlock(worldIn,pos.west().up(), true);
						destroyBlock(worldIn,pos.east().down(), true);
						destroyBlock(worldIn,pos.east().up(), true);
						done=true;
					}
				}
				//north/south
				if(!done) {
					if(!worldIn.isAirBlock(pos.down())&&!worldIn.isAirBlock(pos.up())&&
							!worldIn.isAirBlock(pos.north())&&!worldIn.isAirBlock(pos.south())
							&&!worldIn.isAirBlock(pos.north().up())&&!worldIn.isAirBlock(pos.north().down())
							&&!worldIn.isAirBlock(pos.south().up())&&!worldIn.isAirBlock(pos.south().down())) {
						destroyBlock(worldIn,pos.down(), true);
						destroyBlock(worldIn,pos.up(), true);
						destroyBlock(worldIn,pos.north(), true);
						destroyBlock(worldIn,pos.south(), true);
						destroyBlock(worldIn,pos.north().down(), true);
						destroyBlock(worldIn,pos.north().up(), true);
						destroyBlock(worldIn,pos.south().down(), true);
						destroyBlock(worldIn,pos.south().up(), true);
						done=true;
					}
				}
			}else {
				//down
				if(!done) {
					if(!worldIn.isAirBlock(pos.down())&&!worldIn.isAirBlock(pos.west())&&!worldIn.isAirBlock(pos.east())&&!worldIn.isAirBlock(pos.west())&&
							!worldIn.isAirBlock(pos.north())&&!worldIn.isAirBlock(pos.south())&&!worldIn.isAirBlock(pos.south().west())&&!worldIn.isAirBlock(pos.south().east())&&
							!worldIn.isAirBlock(pos.north().east())&&!worldIn.isAirBlock(pos.north().west())) {
						destroyBlock(worldIn,pos.east(), true);
						destroyBlock(worldIn,pos.west(), true);
						destroyBlock(worldIn,pos.south(), true);
						destroyBlock(worldIn,pos.north(), true);
						destroyBlock(worldIn,pos.south().east(), true);
						destroyBlock(worldIn,pos.south().west(), true);
						destroyBlock(worldIn,pos.north().east(), true);
						destroyBlock(worldIn,pos.north().west(), true);
						done=true;
					}

				}
				//up
				if(!done)
				{
					if(!worldIn.isAirBlock(pos.up())&&!worldIn.isAirBlock(pos.west())&&!worldIn.isAirBlock(pos.east())&&!worldIn.isAirBlock(pos.west())&&
							!worldIn.isAirBlock(pos.north())&&!worldIn.isAirBlock(pos.south())&&!worldIn.isAirBlock(pos.south().west())&&!worldIn.isAirBlock(pos.south().east())&&
							!worldIn.isAirBlock(pos.north().east())&&!worldIn.isAirBlock(pos.north().west())) {
						destroyBlock(worldIn,pos.east(), true);
						destroyBlock(worldIn,pos.west(), true);
						destroyBlock(worldIn,pos.south(), true);
						destroyBlock(worldIn,pos.north(), true);
						destroyBlock(worldIn,pos.south().east(), true);
						destroyBlock(worldIn,pos.south().west(), true);
						destroyBlock(worldIn,pos.north().east(), true);
						destroyBlock(worldIn,pos.north().west(), true);
						done=true;
					}
				}
				//west/east
				if(!done) {
					if(!worldIn.isAirBlock(pos.down())&&!worldIn.isAirBlock(pos.up())&&
							!worldIn.isAirBlock(pos.west())&&!worldIn.isAirBlock(pos.east())
							&&!worldIn.isAirBlock(pos.west().up())&&!worldIn.isAirBlock(pos.west().down())
							&&!worldIn.isAirBlock(pos.east().up())&&!worldIn.isAirBlock(pos.east().down())) {
						destroyBlock(worldIn,pos.down(), true);
						destroyBlock(worldIn,pos.up(), true);
						destroyBlock(worldIn,pos.west(), true);
						destroyBlock(worldIn,pos.east(), true);
						destroyBlock(worldIn,pos.west().down(), true);
						destroyBlock(worldIn,pos.west().up(), true);
						destroyBlock(worldIn,pos.east().down(), true);
						destroyBlock(worldIn,pos.east().up(), true);
						done=true;
					}
				}
				//north/south
				if(!done) {
					if(!worldIn.isAirBlock(pos.down())&&!worldIn.isAirBlock(pos.up())&&
							!worldIn.isAirBlock(pos.north())&&!worldIn.isAirBlock(pos.south())
							&&!worldIn.isAirBlock(pos.north().up())&&!worldIn.isAirBlock(pos.north().down())
							&&!worldIn.isAirBlock(pos.south().up())&&!worldIn.isAirBlock(pos.south().down())) {
						destroyBlock(worldIn,pos.down(), true);
						destroyBlock(worldIn,pos.up(), true);
						destroyBlock(worldIn,pos.north(), true);
						destroyBlock(worldIn,pos.south(), true);
						destroyBlock(worldIn,pos.north().down(), true);
						destroyBlock(worldIn,pos.north().up(), true);
						destroyBlock(worldIn,pos.south().down(), true);
						destroyBlock(worldIn,pos.south().up(), true);
						done=true;
					}
				}
			}
		}*/
		}
		return true;
	}
	private void destroyBlock(World worldIn, BlockPos pos, boolean dropBlock){
		if(worldIn.getBlockState(pos).getBlock()!=Blocks.BEDROCK&&!worldIn.isAirBlock(pos)){
			worldIn.destroyBlock(pos,dropBlock);
		}
	}

    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
    	ItemStack stack = playerIn.getHeldItem(handIn);
    	if(stack.getItem().equals(KCore.CrystalPickaxe)){
    		if(mode==0){
					mode=1;
				if(worldIn.isRemote) {
					playerIn.sendMessage(new TextComponentString("Mythical power is OFF"));
					worldIn.playSound(playerIn, playerIn.getPosition(), KCore.instance.cproxy.pickaxe_turn, SoundCategory.PLAYERS, 1F, 1F);
				}
			}else{
					mode = 0;
				if(worldIn.isRemote) {
					playerIn.sendMessage(new TextComponentString("Mythical power is ON"));
					worldIn.playSound(playerIn, playerIn.getPosition(), KCore.instance.cproxy.pickaxe_turn, SoundCategory.PLAYERS, 1F, 1F);
				}
			}
			return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
		}
        return new ActionResult<ItemStack>(EnumActionResult.PASS, playerIn.getHeldItem(handIn));
    }

	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected)
	{
		if(stack.getItem().equals(KCore.CrystalPickaxe)) {
			if (entityIn != null) {
				if (entityIn instanceof EntityPlayer) {
					player = (EntityPlayer) entityIn;
				} else {
					player = null;
				}
			}
		}
	}

    public float getDestroySpeed(ItemStack stack, IBlockState state)
    {
		if(stack.getItem().equals(KCore.CrystalPickaxe)&&mode==0){
			if(player!=null){
				Vec3d posVec = new Vec3d(player.posX,player.posY,player.posZ);
				Vec3d lookVec = player.getLookVec();
				RayTraceResult ray = player.world.rayTraceBlocks(posVec, lookVec);
				if(ray!=null) {
					BlockPos pos = ray.getBlockPos();
					Vec3d vec = new Vec3d(pos.getX() - player.posX, pos.getY() - player.posY, pos.getZ() - player.posZ).normalize();
					EnumFacing facing = null;
					if (vec.z >= 0.5f && MathHelper.abs((float) vec.x) < 0.5f && MathHelper.abs((float) vec.y) < 0.5f) {
						facing = EnumFacing.SOUTH;
					} else if (vec.z <= -0.5f && MathHelper.abs((float) vec.x) < 0.5f && MathHelper.abs((float) vec.y) < 0.5f) {
						facing = EnumFacing.NORTH;
					} else if (MathHelper.abs((float) vec.z) < 0.5f && MathHelper.abs((float) vec.y) < 0.5f && vec.x >= 0.5f) {
						facing = EnumFacing.EAST;
					} else if (MathHelper.abs((float) vec.z) < 0.5f && MathHelper.abs((float) vec.y) < 0.5f && vec.x <= -0.5f) {
						facing = EnumFacing.WEST;
					} else if (vec.y >= 0.5f) {
						facing = EnumFacing.UP;
					} else if (vec.y <= -0.5f) {
						facing = EnumFacing.DOWN;
					} else {
						facing = EnumFacing.EAST;
					}
					ArrayList<BlockPos> blocksToBreak = new ArrayList<>();
					if (facing == EnumFacing.SOUTH || facing == EnumFacing.NORTH) {
						BlockPos pos0 = pos.west();
						BlockPos pos1 = pos.east();
						BlockPos pos2 = pos.up();
						BlockPos pos3 = pos.down();
						BlockPos pos4 = pos.west().down();
						BlockPos pos5 = pos.east().up();
						BlockPos pos6 = pos.west().up();
						BlockPos pos7 = pos.east().down();
						blocksToBreak.add(pos0);
						blocksToBreak.add(pos1);
						blocksToBreak.add(pos2);
						blocksToBreak.add(pos3);
						blocksToBreak.add(pos4);
						blocksToBreak.add(pos5);
						blocksToBreak.add(pos6);
						blocksToBreak.add(pos7);
					}
					if (facing == EnumFacing.EAST || facing == EnumFacing.WEST) {
						BlockPos pos0 = pos.north();
						BlockPos pos1 = pos.south();
						BlockPos pos2 = pos.up();
						BlockPos pos3 = pos.down();
						BlockPos pos4 = pos.north().down();
						BlockPos pos5 = pos.south().up();
						BlockPos pos6 = pos.north().up();
						BlockPos pos7 = pos.south().down();
						blocksToBreak.add(pos0);
						blocksToBreak.add(pos1);
						blocksToBreak.add(pos2);
						blocksToBreak.add(pos3);
						blocksToBreak.add(pos4);
						blocksToBreak.add(pos5);
						blocksToBreak.add(pos6);
						blocksToBreak.add(pos7);
					}
					if (facing == EnumFacing.UP || facing == EnumFacing.DOWN) {
						BlockPos pos0 = pos.north();
						BlockPos pos1 = pos.south();
						BlockPos pos2 = pos.east();
						BlockPos pos3 = pos.west();
						BlockPos pos4 = pos.north().west();
						BlockPos pos5 = pos.south().east();
						BlockPos pos6 = pos.north().east();
						BlockPos pos7 = pos.south().west();
						blocksToBreak.add(pos0);
						blocksToBreak.add(pos1);
						blocksToBreak.add(pos2);
						blocksToBreak.add(pos3);
						blocksToBreak.add(pos4);
						blocksToBreak.add(pos5);
						blocksToBreak.add(pos6);
						blocksToBreak.add(pos7);
					}
					float averageEffection=0;
					int counter=0;
					for(BlockPos blockToBreakPos:blocksToBreak){
						if(!player.world.isAirBlock(blockToBreakPos)) {
							counter++;
							averageEffection += player.world.getBlockState(blockToBreakPos).getBlockHardness(player.world, blockToBreakPos)*efficiencyOnProperMaterial;
						}
					}
					averageEffection+=state.getBlockHardness(player.world,pos)*efficiencyOnProperMaterial;
					averageEffection=(averageEffection/counter+1);
					return averageEffection;
				}
			}
		}
        Material material = state.getMaterial();
        return material != Material.IRON && material != Material.ANVIL && material != Material.ROCK ? super.getDestroySpeed(stack, state) : this.efficiencyOnProperMaterial;
    }
    
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
    	tooltip.add("Durability: " + (this.getMaxDamage()-this.getDamage(stack)));
    }
}