package mod.krevik.kathairis.block;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tags.BlockTags;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.IWorldReaderBase;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Random;

import static mod.krevik.kathairis.init.ModBlocks.LAYERED_SAND;

public class BlockLayeredSand extends Block {

	public static final IntegerProperty LAYERS = BlockStateProperties.LAYERS_1_8;
	protected static final AxisAlignedBB[] SAND_AABB = new AxisAlignedBB[]{new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.0D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.125D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.25D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.375D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.5D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.625D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.75D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.875D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D)};

	public BlockLayeredSand() {
		super(Block.Properties.create(Material.SAND).sound(SoundType.SAND).hardnessAndResistance(1f).tickRandomly());
		this.setDefaultState(this.stateContainer.getBaseState().with(LAYERS, Integer.valueOf(1)));
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return getLayers(state) == 8;
	}

	@Override
	public boolean isTopSolid(IBlockState state) {
		return state.get(LAYERS).intValue() == 8;
	}

	@Override
	public boolean isReplaceable(IBlockState state, BlockItemUseContext useContext) {
		int i = state.get(LAYERS);
		if (useContext.getItem().getItem() == this.asItem() && i < 8) {
			if (useContext.replacingClickedOnBlock()) {
				return useContext.getFace() == EnumFacing.UP;
			} else {
				return true;
			}
		} else {
			return i == 1;
		}
	}

	@Override
	public BlockFaceShape getBlockFaceShape(IBlockReader p_193383_1_, IBlockState p_193383_2_, BlockPos p_193383_3_, EnumFacing p_193383_4_) {
		return p_193383_4_ == EnumFacing.DOWN ? BlockFaceShape.SOLID : BlockFaceShape.UNDEFINED;
	}

	@Override
	public VoxelShape getShape(IBlockState p_196244_1_, IBlockReader p_196244_2_, BlockPos p_196244_3_) {
		return VoxelShapes.create(SAND_AABB[p_196244_1_.get(LAYERS).intValue()]);
	}

	@Override
	public void tick(IBlockState state, World worldIn, BlockPos pos, Random rand) {
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
	public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
		this.checkAndDropBlock(worldIn, pos, state);
	}

	@Override
	public int quantityDropped(IBlockState p_196264_1_, Random p_196264_2_) {
		return p_196264_1_.get(LAYERS) + 1;
	}

	@Override
	public IItemProvider getItemDropped(IBlockState state, World worldIn, BlockPos pos, int fortune) {
		return Item.getItemFromBlock(LAYERED_SAND.getDefaultState().getBlock());
	}

	@Override
	public boolean isValidPosition(IBlockState state, IWorldReaderBase worldIn, BlockPos pos) {
		IBlockState iblockstate = worldIn.getBlockState(pos.down());
		Block block = iblockstate.getBlock();
		if (block != Blocks.ICE && block != Blocks.PACKED_ICE && block != Blocks.BARRIER) {
			BlockFaceShape blockfaceshape = iblockstate.getBlockFaceShape(worldIn, pos.down(), EnumFacing.UP);
			return blockfaceshape == BlockFaceShape.SOLID || iblockstate.isIn(BlockTags.LEAVES) || block == this && iblockstate.get(LAYERS) == 8;
		} else {
			return false;
		}
	}

	@Nullable
	public IBlockState getStateForPlacement(BlockItemUseContext context) {
		IBlockState iblockstate = context.getWorld().getBlockState(context.getPos());
		if (iblockstate.getBlock() == this) {
			int i = iblockstate.get(LAYERS);
			return iblockstate.with(LAYERS, Integer.valueOf(Math.min(8, i + 1)));
		} else {
			return super.getStateForPlacement(context);
		}
	}

	@Override
	public void harvestBlock(World worldIn, EntityPlayer player, BlockPos pos, IBlockState state, @Nullable TileEntity te, ItemStack stack) {
		super.harvestBlock(worldIn, player, pos, state, te, stack);
		worldIn.setBlockState(pos, Blocks.AIR.getDefaultState());
	}

	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, IBlockState> builder) {
		super.fillStateContainer(builder);
		builder.add(LAYERS);
	}

	private boolean checkAndDropBlock(World worldIn, BlockPos pos, IBlockState state) {
		if (!this.isValidPosition(state, worldIn, pos)) {
			worldIn.setBlockState(pos, Blocks.AIR.getDefaultState());
			return false;
		} else {
			return true;
		}
	}

	private void giveSandToNeighboursNew(IBlockState thisState, World world, BlockPos pos) {
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

	protected int getLayers(IBlockState state) {
		return state.get(this.getLayersProperty()).intValue();
	}

	protected IntegerProperty getLayersProperty() {
		return LAYERS;
	}

	@Override
	public boolean doesSideBlockRendering(IBlockState state, IWorldReader world, BlockPos pos, EnumFacing face) {
		if (face == EnumFacing.UP) {
			return true;
		} else {
			IBlockState iblockstate = world.getBlockState(pos.offset(face));
			return (iblockstate.getBlock() != this || iblockstate.get(LAYERS).intValue() < state.get(LAYERS).intValue()) && shouldSideBeRendered(state, world, pos, face);
		}
	}

}
