package io.github.krevik.kathairis.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
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
		super(Properties.create(Material.ANVIL).hardnessAndResistance(3f, 3f).sound(SoundType.METAL));
	}

	@Override
	public VoxelShape getCollisionShape(BlockState p_220071_1_, IBlockReader p_220071_2_, BlockPos p_220071_3_, ISelectionContext p_220071_4_) {
		return shape;
	}

	@Override
	public void onEntityWalk(World worldIn, BlockPos pos, Entity entityIn) {
		if (entityIn instanceof LivingEntity) {
			LivingEntity living = (LivingEntity) entityIn;
			living.addPotionEffect(new EffectInstance(Effects.LEVITATION, 50));
		}
	}

	@Override
	public void onEntityCollision(BlockState state, World worldIn, BlockPos pos, Entity entityIn) {
		if (entityIn instanceof LivingEntity) {
			LivingEntity living = (LivingEntity) entityIn;
			living.addPotionEffect(new EffectInstance(Effects.LEVITATION, 50));
		}
	}

}
