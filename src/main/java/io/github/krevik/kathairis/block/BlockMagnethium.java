package io.github.krevik.kathairis.block;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

/**
 * @author Krevik
 */
public class BlockMagnethium extends Block {

	protected static final AxisAlignedBB ALMOST_FULL_BLOCK_AABB = new AxisAlignedBB(0.01D, 0.01D, 0.01D, 0.99D, 0.99D, 0.99D);
	private VoxelShape shape = VoxelShapes.create(ALMOST_FULL_BLOCK_AABB);

	public BlockMagnethium() {
		super(Block.Properties.create(Material.ANVIL).hardnessAndResistance(3f, 3f).sound(SoundType.METAL));
	}

	@Deprecated
	@Override
	public VoxelShape getCollisionShape(IBlockState state, IBlockReader worldIn, BlockPos pos) {
		return shape;
	}

	@Override
	public void onEntityWalk(World worldIn, BlockPos pos, Entity entityIn) {
		if (entityIn instanceof EntityLivingBase) {
			EntityLivingBase living = (EntityLivingBase) entityIn;
			living.addPotionEffect(new PotionEffect(MobEffects.LEVITATION, 50));
		}
	}

	@Override
	public void onEntityCollision(IBlockState state, World worldIn, BlockPos pos, Entity entityIn) {
		if (entityIn instanceof EntityLivingBase) {
			EntityLivingBase living = (EntityLivingBase) entityIn;
			living.addPotionEffect(new PotionEffect(MobEffects.LEVITATION, 50));
		}
	}

}
