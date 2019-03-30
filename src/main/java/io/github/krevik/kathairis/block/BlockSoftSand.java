package io.github.krevik.kathairis.block;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.init.Blocks;
import net.minecraft.init.Particles;
import net.minecraft.particles.BlockParticleData;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReaderBase;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Random;

/**
 * @author Krevik
 */
public class BlockSoftSand extends Block {

	public static boolean fallInstantly = false;

	public BlockSoftSand() {
		super(Block.Properties.create(Material.SAND).tickRandomly().hardnessAndResistance(0.3f).sound(SoundType.SAND));
	}

	public static boolean canFallThrough(IBlockState state) {
		Block block = state.getBlock();
		Material material = state.getMaterial();
		return state.isAir() || block == Blocks.FIRE || material.isLiquid() || material.isReplaceable();
	}

	@Override
	public IBlockState updatePostPlacement(IBlockState stateIn, EnumFacing facing, IBlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
		worldIn.getPendingBlockTicks().scheduleTick(currentPos, this, this.tickRate(worldIn));
		return super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	@Override
	public BlockFaceShape getBlockFaceShape(IBlockReader worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
		return BlockFaceShape.UNDEFINED;
	}

	@Override
	public VoxelShape getCollisionShape(IBlockState p_196268_1_, IBlockReader p_196268_2_, BlockPos p_196268_3_) {
		return VoxelShapes.empty();
	}

	@Override
	public void tick(IBlockState state, World worldIn, BlockPos pos, Random random) {
		if (!worldIn.isRemote) {
			this.checkFallable(worldIn, pos);
		}

	}

	@OnlyIn(Dist.CLIENT)
	@Override
	public void animateTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand) {
		if (rand.nextInt(16) == 0) {
			BlockPos blockpos = pos.down();
			if (canFallThrough(worldIn.getBlockState(blockpos))) {
				double d0 = (double) ((float) pos.getX() + rand.nextFloat());
				double d1 = (double) pos.getY() - 0.05D;
				double d2 = (double) ((float) pos.getZ() + rand.nextFloat());
				worldIn.addParticle(new BlockParticleData(Particles.FALLING_DUST, stateIn), d0, d1, d2, 0.0D, 0.0D, 0.0D);
			}
		}

	}

	@Override
	public int tickRate(IWorldReaderBase worldIn) {
		return 2;
	}

	@Override
	public void onBlockAdded(IBlockState state, World worldIn, BlockPos pos, IBlockState oldState) {
		worldIn.getPendingBlockTicks().scheduleTick(pos, this, this.tickRate(worldIn));
	}

	@Override
	public BlockRenderLayer getRenderLayer() {
		return BlockRenderLayer.CUTOUT;
	}

	@Override
	public void onEntityCollision(IBlockState state, World worldIn, BlockPos pos, Entity entityIn) {
		entityIn.setInWeb();
	}

	private void checkFallable(World worldIn, BlockPos pos) {
		if (canFallThrough(worldIn.getBlockState(pos.down())) && pos.getY() >= 0) {
			int i = 32;
			if (!fallInstantly && worldIn.isAreaLoaded(pos.add(-32, -32, -32), pos.add(32, 32, 32))) {
				if (!worldIn.isRemote) {
					EntityFallingBlock entityfallingblock = new EntityFallingBlock(worldIn, (double) pos.getX() + 0.5D, (double) pos.getY(), (double) pos.getZ() + 0.5D, worldIn.getBlockState(pos));
					this.onStartFalling(entityfallingblock);
					worldIn.spawnEntity(entityfallingblock);
				}
			} else {
				IBlockState state = getDefaultState();
				if (worldIn.getBlockState(pos).getBlock() == this) {
					state = worldIn.getBlockState(pos);
					worldIn.removeBlock(pos);
				}

				BlockPos blockpos;
				for (blockpos = pos.down(); canFallThrough(worldIn.getBlockState(blockpos)) && blockpos.getY() > 0; blockpos = blockpos.down()) {
				}

				if (blockpos.getY() > 0) {
					worldIn.setBlockState(blockpos.up(), state);
				}
			}

		}
	}

	protected void onStartFalling(EntityFallingBlock fallingEntity) {

	}

	public void onEndFalling(World worldIn, BlockPos pos, IBlockState fallingState, IBlockState hitState) {
	}

	public void onBroken(World worldIn, BlockPos pos) {
	}

	@OnlyIn(Dist.CLIENT)
	public int getDustColor(IBlockState state) {
		return -16777216;
	}

}
