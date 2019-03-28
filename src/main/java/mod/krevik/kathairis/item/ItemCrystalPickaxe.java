package mod.krevik.kathairis.item;

import mod.krevik.kathairis.init.ModSounds;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Enchantments;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.Map;

public class ItemCrystalPickaxe extends ItemKathairisPickaxe {

	//TODO should this be in the itemstack? Items are singletons
	protected int mode;

	public ItemCrystalPickaxe(IItemTier tier, ItemGroup group) {
		super(tier);
		mode = 0;
	}

	//TODO: pooled mutable block pos and code cleanup
	@Override
	public boolean onBlockDestroyed(ItemStack stack, World worldIn, IBlockState state, BlockPos pos, EntityLivingBase entityLiving) {
		if (mode == 0) {
			if (!worldIn.isRemote && (double) state.getBlockHardness(worldIn, pos) != 0.0D) {
				stack.damageItem(1, entityLiving);
			}
			if (entityLiving instanceof EntityPlayer) {
				EntityPlayer player = (EntityPlayer) entityLiving;
				player.addExhaustion(1f);
				Vec3d vec = new Vec3d(pos.getX() - player.posX, pos.getY() - player.posY, pos.getZ() - player.posZ).normalize();
				EnumFacing facing;
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
				if (!worldIn.isRemote) {
					Map<Enchantment, Integer> map = EnchantmentHelper.getEnchantments(stack);
					if (map.containsKey(Enchantments.SILK_TOUCH) && !map.containsKey(Enchantments.FORTUNE)) {
						for (BlockPos positionToBreak : blocksToBreak) {
							Block.spawnAsEntity(worldIn, positionToBreak, new ItemStack(worldIn.getBlockState(positionToBreak).getBlock(), 1));
							destroyBlock(worldIn, positionToBreak, false);
						}
					} else if (!map.containsKey(Enchantments.SILK_TOUCH) && map.containsKey(Enchantments.FORTUNE)) {
						for (BlockPos positionToBreak : blocksToBreak) {
							worldIn.getBlockState(positionToBreak).getBlock().dropBlockAsItemWithChance(worldIn.getBlockState(positionToBreak), worldIn, positionToBreak, 1, map.get(Enchantments.FORTUNE).intValue());
							destroyBlock(worldIn, positionToBreak, false);
						}
					} else {
						for (BlockPos positionToBreak : blocksToBreak) {
							destroyBlock(worldIn, positionToBreak, true);
						}
					}
				}
			}
		}
		return true;
	}

	private void destroyBlock(World worldIn, BlockPos pos, boolean dropBlock) {
		if (worldIn.getBlockState(pos).getBlock() != Blocks.BEDROCK && !worldIn.isAirBlock(pos)) {
			worldIn.destroyBlock(pos, dropBlock);
		}
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		if (mode == 0) {
			if (!worldIn.isRemote) {
				mode = 1;
			}
			if (worldIn.isRemote) {
				playerIn.sendMessage(new TextComponentString("Mythical power is OFF"));
				worldIn.playSound(playerIn, playerIn.getPosition(), ModSounds.PICKAXE_TURN, SoundCategory.PLAYERS, 1F, 1F);
			}
		} else {
			if (!worldIn.isRemote) {
				mode = 0;
			}
			if (worldIn.isRemote) {
				playerIn.sendMessage(new TextComponentString("Mythical power is ON"));
				worldIn.playSound(playerIn, playerIn.getPosition(), ModSounds.PICKAXE_TURN, SoundCategory.PLAYERS, 1F, 1F);
			}
		}
		return new ActionResult<>(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
	}

	@Override
	public float getDestroySpeed(ItemStack stack, IBlockState state) {
		if (mode == 0) {
			return super.getDestroySpeed(stack, state) / 4.5f;
		} else {
			return super.getDestroySpeed(stack, state);
		}
	}

}
