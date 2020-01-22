package io.github.krevik.kathairis.block;

import io.github.krevik.kathairis.init.ModItemGroups;
import io.github.krevik.kathairis.util.IItemGroupProvider;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;
import java.util.Random;

import static io.github.krevik.kathairis.init.ModBlocks.FLUO_FUNGI;

/**
 * @author Krevik
 */
public class BlockFluoFungi extends BlockKathairisPlant implements IItemGroupProvider {

	public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;

	public BlockFluoFungi() {
		super(Block.Properties.create(Material.PLANTS).lightValue(10).doesNotBlockMovement().tickRandomly().hardnessAndResistance(0).sound(SoundType.PLANT));
	}

	@Override
	public ItemGroup getItemGroup() {
		return ModItemGroups.PLANTS;
	}

	@Override
	public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random random) {
		super.tick(state, worldIn, pos, random);
		handleFacing(state, worldIn, pos);
		if (!this.isLogAround(worldIn, pos)) {
			this.dropBlock(worldIn, pos, state);
		}
	}

	@Nullable
	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		Direction facing = context.getFace();
		World world = context.getWorld();
		BlockPos pos = context.getPos();
		if (isLog(world.getBlockState(pos.east()).getBlock())) {
			return FLUO_FUNGI.getDefaultState().with(FACING, Direction.WEST);
		} else if (isLog(world.getBlockState(pos.west()).getBlock())) {
			return FLUO_FUNGI.getDefaultState().with(FACING, Direction.EAST);
		} else if (isLog(world.getBlockState(pos.south()).getBlock())) {
			return FLUO_FUNGI.getDefaultState().with(FACING, Direction.NORTH);
		} else if (isLog(world.getBlockState(pos.north()).getBlock())) {
			return FLUO_FUNGI.getDefaultState().with(FACING, Direction.SOUTH);
		} else {
			return FLUO_FUNGI.getDefaultState().with(FACING, Direction.WEST);
		}
	}

	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		super.fillStateContainer(builder);
		builder.add(FACING);
	}

	public boolean isLogAround(IWorldReader worldIn, BlockPos pos) {
		boolean is = false;
		for (int x = -1; x <= 1; x++) {
			for (int z = -1; z <= 1; z++) {
				BlockPos tmp = new BlockPos(pos.getX() + x, pos.getY(), pos.getZ() + z);
				if (worldIn.getBlockState(tmp).getBlock() instanceof LogBlock ||
						worldIn.getBlockState(tmp).getBlock() instanceof BlockKathairisLog) {
					is = true;
				}
			}
		}
		return is;
	}

	private void dropBlock(World worldIn, BlockPos pos, BlockState state) {
		worldIn.setBlockState(pos, Blocks.AIR.getDefaultState(), 3);
		spawnAsEntity(worldIn, pos, new ItemStack(this));
	}

	@Override
	public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
		handleFacing(stateIn, worldIn, currentPos);
		return super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);
	}

	@Override
	public void neighborChanged(BlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos, boolean isMoving) {
		handleFacing(state, worldIn, pos);
		if (!this.isLogAround(worldIn, pos)) {
			this.dropBlock(worldIn, pos, state);
		}
	}

	@Override
	public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
		return isLogAround(worldIn, pos);
	}

	private void handleFacing(BlockState state, IWorld world, BlockPos pos) {
		if (isLog(world.getBlockState(pos.east()).getBlock())) {
			world.setBlockState(pos, state.with(FACING, Direction.WEST), 2);
		}
		if (isLog(world.getBlockState(pos.west()).getBlock())) {
			world.setBlockState(pos, state.with(FACING, Direction.EAST), 2);
		}
		if (isLog(world.getBlockState(pos.south()).getBlock())) {
			world.setBlockState(pos, state.with(FACING, Direction.NORTH), 2);
		}
		if (isLog(world.getBlockState(pos.north()).getBlock())) {
			world.setBlockState(pos, state.with(FACING, Direction.SOUTH), 2);
		}
	}

	private boolean isLog(Block block) {
		return block instanceof LogBlock || block instanceof BlockKathairisLog;
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
