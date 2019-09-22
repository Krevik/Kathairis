package io.github.krevik.kathairis.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IEnviromentBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Random;

import static io.github.krevik.kathairis.init.ModBlocks.LAYERED_SAND;

/**
 * @author Krevik
 */
public class BlockLayeredSand extends Block {

	public static final IntegerProperty LAYERS = BlockStateProperties.LAYERS_1_8;
	protected static final AxisAlignedBB[] SAND_AABB = new AxisAlignedBB[]{new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.0D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.125D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.25D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.375D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.5D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.625D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.75D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.875D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D)};

	public BlockLayeredSand() {
		super(Properties.create(Material.SAND).sound(SoundType.SAND).hardnessAndResistance(1f).tickRandomly());
		this.setDefaultState(this.stateContainer.getBaseState().with(LAYERS, Integer.valueOf(1)));
	}

	@Override
	public boolean isReplaceable(BlockState state, BlockItemUseContext useContext) {
		int i = state.get(LAYERS);
		if (useContext.getItem().getItem() == this.asItem() && i < 8) {
			if (useContext.replacingClickedOnBlock()) {
				return useContext.getFace() == Direction.UP;
			} else {
				return true;
			}
		} else {
			return i == 1;
		}
	}


	@Override
	public VoxelShape getShape(BlockState p_196244_1_, IBlockReader p_196244_2_, BlockPos p_196244_3_, ISelectionContext context) {
		return VoxelShapes.create(SAND_AABB[p_196244_1_.get(LAYERS).intValue()]);
	}

	@Override
	public void tick(BlockState state, World worldIn, BlockPos pos, Random rand) {
		if (!worldIn.isRemote) {
			giveSandToNeighboursNew(state, worldIn, pos);
		}
		//TODO SANDSTORM
        /*
        if(!worldIn.isRemote) {
            giveSandToNeighboursNew(state, worldIn, pos);
            KathairisDataStorage data = KathairisDataStorage.getDataInstance(worldIn);
            if(data!=null){
                if(KathairisDataStorage.getSandstormTime()>-1){
                    if(rand.nextInt(10)==0){
                        int actualLayers=getLayers(state);
                        if(actualLayers<8){
                            worldIn.setBlockState(pos,KCore.Layered_Sand.getDefaultState().withProperty(BlockLayeredSand.LAYERS,actualLayers+1),2);
                        }
                    }
                }
            }
        }
        */

	}

	@Override
	public void neighborChanged(BlockState state, World worldIn, BlockPos pos, Block p_220069_4_, BlockPos p_220069_5_, boolean p_220069_6_) {
		this.checkAndDropBlock(worldIn, pos, state);
	}


	@Override
	public boolean isValidPosition(BlockState p_196260_1_, IWorldReader p_196260_2_, BlockPos p_196260_3_) {
		BlockState lvt_4_1_ = p_196260_2_.getBlockState(p_196260_3_.down());
		Block lvt_5_1_ = lvt_4_1_.getBlock();
		if (lvt_5_1_ != Blocks.ICE && lvt_5_1_ != Blocks.PACKED_ICE && lvt_5_1_ != Blocks.BARRIER) {
			return Block.doesSideFillSquare(lvt_4_1_.getCollisionShape(p_196260_2_, p_196260_3_.down()), Direction.UP) || lvt_5_1_ == this && (Integer)lvt_4_1_.get(LAYERS) == 8;
		} else {
			return false;
		}
	}

	@Nullable
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		BlockState iblockstate = context.getWorld().getBlockState(context.getPos());
		if (iblockstate.getBlock() == this) {
			int i = iblockstate.get(LAYERS);
			return iblockstate.with(LAYERS, Integer.valueOf(Math.min(8, i + 1)));
		} else {
			return super.getStateForPlacement(context);
		}
	}

	@Override
	public void harvestBlock(World worldIn, PlayerEntity player, BlockPos pos, BlockState state, @Nullable TileEntity te, ItemStack stack) {
		super.harvestBlock(worldIn, player, pos, state, te, stack);
		worldIn.setBlockState(pos, Blocks.AIR.getDefaultState());
	}

	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		super.fillStateContainer(builder);
		builder.add(LAYERS);
	}

	private boolean checkAndDropBlock(World worldIn, BlockPos pos, BlockState state) {
		if (!this.isValidPosition(state, worldIn, pos)) {
			worldIn.setBlockState(pos, Blocks.AIR.getDefaultState());
			return false;
		} else {
			return true;
		}
	}

	private void giveSandToNeighboursNew(BlockState thisState, World world, BlockPos pos) {
		int layers = getLayers(thisState);
		if (layers > 2) {
			BlockPos pos1 = pos.east();
			if (world.getBlockState(pos1).getBlock() == LAYERED_SAND) {
				if (getLayers(world.getBlockState(pos1)) + 1 < layers) {
					world.setBlockState(pos1, LAYERED_SAND.getDefaultState().with(LAYERS, getLayers(world.getBlockState(pos1)) + 1), 2);
					world.setBlockState(pos, LAYERED_SAND.getDefaultState().with(LAYERS, layers - 1), 2);
					layers--;
				}
			}
			pos1 = pos.west();
			if (world.getBlockState(pos1).getBlock() == LAYERED_SAND) {
				if (getLayers(world.getBlockState(pos1)) + 1 < layers) {
					world.setBlockState(pos1, LAYERED_SAND.getDefaultState().with(LAYERS, getLayers(world.getBlockState(pos1)) + 1), 2);
					world.setBlockState(pos, LAYERED_SAND.getDefaultState().with(LAYERS, layers - 1), 2);
					layers--;
				}
			}
			pos1 = pos.south();
			if (world.getBlockState(pos1).getBlock() == LAYERED_SAND) {
				if (getLayers(world.getBlockState(pos1)) + 1 < layers) {
					world.setBlockState(pos1, LAYERED_SAND.getDefaultState().with(LAYERS, getLayers(world.getBlockState(pos1)) + 1), 2);
					world.setBlockState(pos, LAYERED_SAND.getDefaultState().with(LAYERS, layers - 1), 2);
					layers--;
				}
			}
			pos1 = pos.north();
			if (world.getBlockState(pos1).getBlock() == LAYERED_SAND) {
				if (getLayers(world.getBlockState(pos1)) + 1 < layers) {
					world.setBlockState(pos1, LAYERED_SAND.getDefaultState().with(LAYERS, getLayers(world.getBlockState(pos1)) + 1), 2);
					world.setBlockState(pos, LAYERED_SAND.getDefaultState().with(LAYERS, layers - 1), 2);
					layers--;
				}
			}
			pos1 = pos.down().north();
			if (world.getBlockState(pos1).getBlock() == LAYERED_SAND) {
				if (getLayers(world.getBlockState(pos1)) + 1 < 8) {
					world.setBlockState(pos1, LAYERED_SAND.getDefaultState().with(LAYERS, getLayers(world.getBlockState(pos1)) + 1), 2);
					if (layers - 1 <= 0) {
						world.setBlockState(pos, Blocks.AIR.getDefaultState());
					} else {
						world.setBlockState(pos, LAYERED_SAND.getDefaultState().with(LAYERS, layers - 1), 2);
					}
					layers--;
				}
			}
			pos1 = pos.down().south();
			if (world.getBlockState(pos1).getBlock() == LAYERED_SAND) {
				if (getLayers(world.getBlockState(pos1)) + 1 < 8) {
					world.setBlockState(pos1, LAYERED_SAND.getDefaultState().with(LAYERS, getLayers(world.getBlockState(pos1)) + 1), 2);
					if (layers - 1 <= 0) {
						world.setBlockState(pos, Blocks.AIR.getDefaultState());
					} else {
						world.setBlockState(pos, LAYERED_SAND.getDefaultState().with(LAYERS, layers - 1), 2);
					}
					layers--;
				}
			}
			pos1 = pos.down().east();
			if (world.getBlockState(pos1).getBlock() == LAYERED_SAND) {
				if (getLayers(world.getBlockState(pos1)) + 1 < 8) {
					world.setBlockState(pos1, LAYERED_SAND.getDefaultState().with(LAYERS, getLayers(world.getBlockState(pos1)) + 1), 2);
					if (layers - 1 <= 0) {
						world.setBlockState(pos, Blocks.AIR.getDefaultState());
					} else {
						world.setBlockState(pos, LAYERED_SAND.getDefaultState().with(LAYERS, layers - 1), 2);
					}
					layers--;
				}
			}
			pos1 = pos.down().west();
			if (world.getBlockState(pos1).getBlock() == LAYERED_SAND) {
				if (getLayers(world.getBlockState(pos1)) + 1 < 8) {
					world.setBlockState(pos1, LAYERED_SAND.getDefaultState().with(LAYERS, getLayers(world.getBlockState(pos1)) + 1), 2);
					if (layers - 1 <= 0) {
						world.setBlockState(pos, Blocks.AIR.getDefaultState());
					} else {
						world.setBlockState(pos, LAYERED_SAND.getDefaultState().with(LAYERS, layers - 1), 2);
					}
				}
			}
		}
	}

	protected int getLayers(BlockState state) {
		return state.get(this.getLayersProperty()).intValue();
	}

	protected IntegerProperty getLayersProperty() {
		return LAYERS;
	}


	@Override
	public boolean doesSideBlockRendering(BlockState state, IEnviromentBlockReader world, BlockPos pos, Direction face) {
		if (face == Direction.UP) {
			return true;
		} else {
			BlockState iblockstate = world.getBlockState(pos.offset(face));
			return (iblockstate.getBlock() != this || iblockstate.get(LAYERS).intValue() < state.get(LAYERS).intValue()) && shouldSideBeRendered(state, world, pos, face);
		}
	}

}
