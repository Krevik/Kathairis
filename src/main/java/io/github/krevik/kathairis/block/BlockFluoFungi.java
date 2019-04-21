package io.github.krevik.kathairis.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.BlockLog;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReaderBase;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Random;

import static io.github.krevik.kathairis.init.ModBlocks.FLUO_FUNGI;

/**
 * @author Krevik
 */
public class BlockFluoFungi extends BlockKathairisPlant {

	public static final DirectionProperty FACING = BlockHorizontal.HORIZONTAL_FACING;

	public BlockFluoFungi() {
		super(Block.Properties.create(Material.PLANTS).lightValue(10).doesNotBlockMovement().tickRandomly().hardnessAndResistance(0).sound(SoundType.PLANT));
	}

	@Override
	public void tick(IBlockState state, World worldIn, BlockPos pos, Random rand) {
		handleFacing(state, worldIn, pos);
		if (!this.isLogAround(worldIn, pos)) {
			this.dropBlock(worldIn, pos, state);
		}
	}

	@Nullable
	@Override
	public IBlockState getStateForPlacement(BlockItemUseContext context) {
		EnumFacing facing = context.getFace();
		World world = context.getWorld();
		BlockPos pos = context.getPos();
		if (isLog(world.getBlockState(pos.east()).getBlock())) {
			return FLUO_FUNGI.getDefaultState().with(FACING, EnumFacing.WEST);
		} else if (isLog(world.getBlockState(pos.west()).getBlock())) {
			return FLUO_FUNGI.getDefaultState().with(FACING, EnumFacing.EAST);
		} else if (isLog(world.getBlockState(pos.south()).getBlock())) {
			return FLUO_FUNGI.getDefaultState().with(FACING, EnumFacing.NORTH);
		} else if (isLog(world.getBlockState(pos.north()).getBlock())) {
			return FLUO_FUNGI.getDefaultState().with(FACING, EnumFacing.SOUTH);
		} else {
			return FLUO_FUNGI.getDefaultState().with(FACING, EnumFacing.WEST);
		}
	}

	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, IBlockState> builder) {
		super.fillStateContainer(builder);
		builder.add(FACING);
	}

	public boolean isLogAround(IWorldReaderBase worldIn, BlockPos pos) {
		boolean is = false;
		for (int x = -1; x <= 1; x++) {
			for (int z = -1; z <= 1; z++) {
				BlockPos tmp = new BlockPos(pos.getX() + x, pos.getY(), pos.getZ() + z);
				if (worldIn.getBlockState(tmp).getBlock() instanceof BlockLog ||
						worldIn.getBlockState(tmp).getBlock() instanceof BlockKathairisLog) {
					is = true;
				}
			}
		}
		return is;
	}

	private void dropBlock(World worldIn, BlockPos pos, IBlockState state) {
		worldIn.setBlockState(pos, Blocks.AIR.getDefaultState(), 3);
		spawnAsEntity(worldIn, pos, new ItemStack(this));
	}

	@Override
	public IBlockState updatePostPlacement(IBlockState stateIn, EnumFacing facing, IBlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
		handleFacing(stateIn, worldIn, currentPos);
		return super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	@Override
	public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
		handleFacing(state, worldIn, pos);
		if (!this.isLogAround(worldIn, pos)) {
			this.dropBlock(worldIn, pos, state);
		}
	}

	@Nonnull
	@OnlyIn(Dist.CLIENT)
	@Override
	public BlockRenderLayer getRenderLayer() {
		return BlockRenderLayer.CUTOUT_MIPPED;
	}

	@Override
	public boolean isValidPosition(IBlockState state, IWorldReaderBase worldIn, BlockPos pos) {
		return isLogAround(worldIn, pos);
	}

	private void handleFacing(IBlockState state, IWorld world, BlockPos pos) {
		if (isLog(world.getBlockState(pos.east()).getBlock())) {
			world.setBlockState(pos, state.with(FACING, EnumFacing.WEST), 2);
		}
		if (isLog(world.getBlockState(pos.west()).getBlock())) {
			world.setBlockState(pos, state.with(FACING, EnumFacing.EAST), 2);
		}
		if (isLog(world.getBlockState(pos.south()).getBlock())) {
			world.setBlockState(pos, state.with(FACING, EnumFacing.NORTH), 2);
		}
		if (isLog(world.getBlockState(pos.north()).getBlock())) {
			world.setBlockState(pos, state.with(FACING, EnumFacing.SOUTH), 2);
		}
	}

	private boolean isLog(Block block) {
		return block instanceof BlockLog || block instanceof BlockKathairisLog;
	}

   /* @Override
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand)
    {
        if(rand.nextInt(100)==0) {
            double d0 = (double) ((float) pos.getX() + rand.nextFloat());
            double d1 = (double) ((float) pos.getY() + rand.nextFloat());
            double d2 = (double) ((float) pos.getZ() + rand.nextFloat());
            Particle theParticle = new DynamicParticle(
                    ParticlesFactory.HIGHRESPARTICLE,
                    worldIn,
                    d0, d1, d2,new BlockPos(d0,d1,d2))
                    .setRotationSpeed(((float) Math.random() - 0.5F) * 0.1F)
                    .setLifeSpan(400 + rand.nextInt(300))
                    .setGravity(0F)
                    .setScale(2.0F)
                    .setInitialAlpha(1.0F)
                    .setFinalAlpha(0.0F)
                    .setEnableDepth(true);
            Minecraft.getMinecraft().effectRenderer.addEffect(theParticle);
        }
    }*/
}
