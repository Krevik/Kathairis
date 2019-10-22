package io.github.krevik.kathairis.block;

import com.google.common.cache.LoadingCache;
import io.github.krevik.kathairis.Kathairis;
import io.github.krevik.kathairis.client.model.ModelPhasm;
import io.github.krevik.kathairis.entity.EntityStrangeWanderer;
import io.github.krevik.kathairis.init.ModBlocks;
import io.github.krevik.kathairis.init.ModParticles;
import io.github.krevik.kathairis.world.dimension.KathairisTeleportingManager;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.pattern.BlockPattern;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.IParticleData;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static io.github.krevik.kathairis.init.ModBlocks.KATHAIRIS_PORTAL;

/**
 * @author Krevik
 */
public class BlockKathairisPortal extends NetherPortalBlock {

	public static final EnumProperty<Direction.Axis> AXIS = BlockStateProperties.HORIZONTAL_AXIS;
	protected static final VoxelShape X_AABB = Block.makeCuboidShape(0.0D, 0.0D, 6.0D, 16.0D, 16.0D, 10.0D);
	protected static final VoxelShape Z_AABB = Block.makeCuboidShape(6.0D, 0.0D, 0.0D, 10.0D, 16.0D, 16.0D);

	public BlockKathairisPortal() {
		super(Properties.create(Material.PORTAL).tickRandomly().hardnessAndResistance(-1, -1).doesNotBlockMovement().lightValue(1).sound(SoundType.GLASS));
		this.setDefaultState(this.stateContainer.getBaseState().with(AXIS, Direction.Axis.X));
	}

	@Override
	public VoxelShape getCollisionShape(BlockState p_220071_1_, IBlockReader p_220071_2_, BlockPos p_220071_3_, ISelectionContext p_220071_4_) {
		return VoxelShapes.empty();
	}

    private BlockState pickUpRandomFlowerStateForKether(Random random) {
		BlockState[] flowers = {ModBlocks.KATHAIRIS_MINI_GRASS.getDefaultState(),ModBlocks.KATHAIRIS_TALL_GRASS.getDefaultState(),ModBlocks.KATHAIRIS_NIGHT_FLOWER.getDefaultState(),
                ModBlocks.BUTTERFLY_FLOWER.getDefaultState(),ModBlocks.KATHAIRIS_FUNGI.getDefaultState(),ModBlocks.STEPPED_SUCCULENT.getDefaultState(),
                ModBlocks.EYE_PLANT.getDefaultState(),ModBlocks.MAGIC_BEANS.getDefaultState(),ModBlocks.BISON_STARS.getDefaultState(),ModBlocks.GOOSEBERRY_BUSH.getDefaultState(),
				ModBlocks.KATHAIRIS_MINI_GRASS.getDefaultState(),ModBlocks.KATHAIRIS_MINI_GRASS.getDefaultState(),ModBlocks.KATHAIRIS_MINI_GRASS.getDefaultState(),ModBlocks.KATHAIRIS_MINI_GRASS.getDefaultState(),
				ModBlocks.KATHAIRIS_MINI_GRASS.getDefaultState(),ModBlocks.KATHAIRIS_MINI_GRASS.getDefaultState(),ModBlocks.KATHAIRIS_MINI_GRASS.getDefaultState(),ModBlocks.KATHAIRIS_MINI_GRASS.getDefaultState(),
				ModBlocks.KATHAIRIS_MINI_GRASS.getDefaultState(),ModBlocks.KATHAIRIS_MINI_GRASS.getDefaultState(),ModBlocks.KATHAIRIS_MINI_GRASS.getDefaultState(),ModBlocks.KATHAIRIS_MINI_GRASS.getDefaultState(),
				ModBlocks.KATHAIRIS_MINI_GRASS.getDefaultState(),ModBlocks.KATHAIRIS_MINI_GRASS.getDefaultState(),ModBlocks.KATHAIRIS_MINI_GRASS.getDefaultState(),ModBlocks.KATHAIRIS_MINI_GRASS.getDefaultState(),
        };
        return flowers[random.nextInt(flowers.length)];
    }

    private BlockState pickUpRandomFlowerState(Random random){
		ArrayList<Block> flowers_List = new ArrayList<>();
		flowers_List.add(Blocks.SUNFLOWER);
		flowers_List.add(Blocks.TALL_GRASS);
		flowers_List.add(Blocks.POPPY);
		flowers_List.add(Blocks.TALL_GRASS);
		flowers_List.add(Blocks.DANDELION);
		flowers_List.add(Blocks.BLUE_ORCHID);
		flowers_List.add(Blocks.TALL_GRASS);
		flowers_List.add(Blocks.OAK_SAPLING);
		flowers_List.add(Blocks.TALL_GRASS);
		flowers_List.add(Blocks.TALL_GRASS);
		flowers_List.add(Blocks.TALL_GRASS);
		flowers_List.add(Blocks.TALL_GRASS);
		flowers_List.add(Blocks.TALL_GRASS);
		flowers_List.add(Blocks.TALL_GRASS);
		flowers_List.add(Blocks.TALL_GRASS);
		flowers_List.add(Blocks.TALL_GRASS);
		return flowers_List.get(random.nextInt(flowers_List.size())).getDefaultState();
	}


    private void updateBlocksAroundPortal(World worldIn, BlockPos pos, BlockState state, Random rand) {
		int type1 = Kathairis.KATHAIRIS.getId();
		DimensionType type = DimensionType.getById(type1);
		if(worldIn.getDimension().getType().getId()==type1) {
            int radius=5;
            int shiftX=+rand.nextInt(radius+1)-rand.nextInt(radius+1);
            int shiftY=-rand.nextInt(radius+1)+rand.nextInt(radius+1);
            int shiftZ=+rand.nextInt(radius+1)-rand.nextInt(radius+1);
            int randomX=pos.getX()+shiftX;
            int randomY=pos.getY()+shiftY;
            int randomZ=pos.getZ()+shiftZ;
            if((MathHelper.abs(randomX-pos.getX())*MathHelper.abs(randomX-pos.getX()))+
                    (MathHelper.abs(randomZ-pos.getZ())*MathHelper.abs(randomZ-pos.getZ()))<=radius*radius) {
                BlockPos tmp = new BlockPos(randomX,randomY,randomZ);
                if(worldIn.getBlockState(tmp)==ModBlocks.KATHAIRIS_GRASS.getDefaultState()&&worldIn.isAirBlock(tmp.up())) {
                    worldIn.setBlockState(tmp, Blocks.GRASS.getDefaultState(),2);
                }
                if(worldIn.getBlockState(tmp)==ModBlocks.KATHAIRIS_DIRT.getDefaultState()) {
                    worldIn.setBlockState(tmp, Blocks.DIRT.getDefaultState(),2);
                }
                if(worldIn.getBlockState(tmp)==ModBlocks.KATHAIRIS_STONE.getDefaultState()) {
                    worldIn.setBlockState(tmp, Blocks.STONE.getDefaultState(),2);
                }
                if(worldIn.getBlockState(tmp)==ModBlocks.KATHAIRIS_SAND.getDefaultState()) {
                    worldIn.setBlockState(tmp, Blocks.SAND.getDefaultState(),2);
                }
                if(worldIn.getBlockState(tmp)==Blocks.GRASS.getDefaultState()&&worldIn.getBlockState(tmp.up())==Blocks.AIR.getDefaultState()) {
                    worldIn.setBlockState(tmp.up(), pickUpRandomFlowerState(rand),2);
                }
            }
            if(rand.nextInt(2)==0) {
                this.updateBlocksAroundPortal(worldIn, pos, state, rand);
            }
        }else {
            int radius=5;
            int randomX=pos.getX()+rand.nextInt(radius+1)-rand.nextInt(radius+1);
            int randomY=pos.getY()-rand.nextInt(radius+1)+rand.nextInt(radius+1);
            int randomZ=pos.getZ()+rand.nextInt(radius+1)-rand.nextInt(radius+1);
            BlockPos tmp = new BlockPos(randomX,randomY,randomZ);
            if((MathHelper.abs(randomX-pos.getX())*MathHelper.abs(randomX-pos.getX()))+
                    (MathHelper.abs(randomZ-pos.getZ())*MathHelper.abs(randomZ-pos.getZ()))<=radius*radius) {
                if(worldIn.getBlockState(tmp)==Blocks.GRASS.getDefaultState()) {
                    worldIn.setBlockState(tmp, ModBlocks.KATHAIRIS_GRASS.getDefaultState(),2);
                }
                if(worldIn.getBlockState(tmp)==Blocks.DIRT.getDefaultState()) {
                    worldIn.setBlockState(tmp, ModBlocks.KATHAIRIS_DIRT.getDefaultState(),2);
                }
                if(worldIn.getBlockState(tmp)==Blocks.STONE.getDefaultState()) {
                    //worldIn.setBlockState(tmp, ModBlocks.KATHAIRIS_STONE.getDefaultState(),2);
                }
                if(worldIn.getBlockState(tmp)==Blocks.SAND.getDefaultState()) {
                    worldIn.setBlockState(tmp, ModBlocks.KATHAIRIS_SAND.getDefaultState(),2);
                }
                if(worldIn.getBlockState(tmp)==ModBlocks.KATHAIRIS_GRASS.getDefaultState()&&worldIn.getBlockState(tmp.up())==Blocks.AIR.getDefaultState()) {
                    worldIn.setBlockState(tmp.up(), pickUpRandomFlowerStateForKether(rand),2);
                }
            }
        }
    }

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		switch (state.get(AXIS)) {
			case Z:
				return Z_AABB;
			case X:
			default:
				return X_AABB;
		}
	}

	@Override
	public void tick(BlockState state, World worldIn, BlockPos pos, Random rand) {
		super.tick(state, worldIn, pos, rand);

		int type1 = Kathairis.KATHAIRIS.getId();
        if(worldIn.dimension.getType() == DimensionType.getById(type1)) {
            List<EntityStrangeWanderer> e = worldIn.getEntitiesWithinAABB(EntityStrangeWanderer.class, new AxisAlignedBB(pos.getX() - 15, pos.getY() - 15, pos.getZ() - 15, pos.getX()  + 15, pos.getY() + 15, pos.getZ() + 15));
            if(e.size()==0) {
                if(!worldIn.isRemote) {
                    int X=pos.getX()+rand.nextInt(4)-rand.nextInt(4);
                    int Y=pos.getY();
                    int Z = pos.getZ()+rand.nextInt(4)-rand.nextInt(4);
                    if(worldIn.isAirBlock(new BlockPos(X,Y,Z))&&worldIn.isAirBlock(new BlockPos(X,Y,Z).up())) {
                        EntityStrangeWanderer esw = new EntityStrangeWanderer(worldIn);
                        esw.setPosition(X,Y,Z);
                        worldIn.addEntity(esw);
                    }

                }
            }
        }

        if(Kathairis.SHOULD_BLOCKS_SPREAD_AROUND_PORTAL) {
            for (int x = 0; x < 1 + rand.nextInt(4); x++) {
                updateBlocksAroundPortal(worldIn, pos, state, rand);
            }
        }

	}

	@Override
	public boolean trySpawnPortal(IWorld worldIn, BlockPos pos) {
		Size blockportal$size = this.isPortal1(worldIn, pos);
		if (blockportal$size != null) {
			blockportal$size.placePortalBlocks();
			return true;
		} else {
			return false;
		}
	}

	public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
		Direction.Axis enumfacing$axis = facing.getAxis();
		Direction.Axis enumfacing$axis1 = stateIn.get(AXIS);
		boolean flag = enumfacing$axis1 != enumfacing$axis && enumfacing$axis.isHorizontal();
		return !flag && facingState.getBlock() != this && !(new Size(worldIn, currentPos, enumfacing$axis1)).func_208508_f() ? Blocks.AIR.getDefaultState() : super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);
	}

	@Override
	public BlockRenderLayer getRenderLayer() {
		return BlockRenderLayer.TRANSLUCENT;
	}

	@Override
	public void onEntityCollision(BlockState state, World worldIn, BlockPos pos, Entity entityIn) {
		if (entityIn != null) {
			KathairisTeleportingManager.tele(entityIn);
		}
    }

    @Override
    public ItemStack getItem(IBlockReader worldIn, BlockPos pos, BlockState state) {
        return ItemStack.EMPTY;
    }

    @OnlyIn(Dist.CLIENT)
    public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {
        if (rand.nextInt(100) == 0) {
            worldIn.playSound((double) pos.getX() + 0.5D, (double) pos.getY() + 0.5D, (double) pos.getZ() + 0.5D, SoundEvents.BLOCK_PORTAL_AMBIENT, SoundCategory.BLOCKS, 0.5F, rand.nextFloat() * 0.4F + 0.8F, false);
        }

        for (int i = 0; i < 6; ++i) {
            double d0 = (double) ((float) pos.getX() + rand.nextFloat());
            double d1 = (double) ((float) pos.getY() + rand.nextFloat());
            double d2 = (double) ((float) pos.getZ() + rand.nextFloat());
            double d3 = ((double) rand.nextFloat() - 0.5D) * 0.15D;
            double d4 = ((double) rand.nextFloat() - 0.5D) * 0.15D;
            double d5 = ((double) rand.nextFloat() - 0.5D) * 0.15D;
			//worldIn.addParticle(ModParticles.TEST,d0,d1,d2,d3,d4,d5);
        }
		/*if (rand.nextInt(100) == 0) {
			worldIn.playSound((double)pos.getX() + 0.5D, (double)pos.getY() + 0.5D, (double)pos.getZ() + 0.5D, SoundEvents.BLOCK_PORTAL_AMBIENT, SoundCategory.BLOCKS, 0.5F, rand.nextFloat() * 0.4F + 0.8F, false);
		}

		for(int i = 0; i < 4; ++i) {
			double d0 = (double)((float)pos.getX() + rand.nextFloat());
			double d1 = (double)((float)pos.getY() + rand.nextFloat());
			double d2 = (double)((float)pos.getZ() + rand.nextFloat());
			double d3 = ((double)rand.nextFloat() - 0.5D) * 0.5D;
			double d4 = ((double)rand.nextFloat() - 0.5D) * 0.5D;
			double d5 = ((double)rand.nextFloat() - 0.5D) * 0.5D;
			int j = rand.nextInt(2) * 2 - 1;
			if (worldIn.getBlockState(pos.west()).getBlock() != this && worldIn.getBlockState(pos.east()).getBlock() != this) {
				d0 = (double)pos.getX() + 0.5D + 0.25D * (double)j;
				d3 = (double)(rand.nextFloat() * 2.0F * (float)j);
			} else {
				d2 = (double)pos.getZ() + 0.5D + 0.25D * (double)j;
				d5 = (double)(rand.nextFloat() * 2.0F * (float)j);
			}

			worldIn.addParticle(ParticleTypes.PORTAL, d0, d1, d2, d3, d4, d5);
		}*/
	}


	@Override
	public BlockState rotate(BlockState state, Rotation rot) {
		switch (rot) {
			case COUNTERCLOCKWISE_90:
			case CLOCKWISE_90:
				switch (state.get(AXIS)) {
					case Z:
						return state.with(AXIS, Direction.Axis.X);
					case X:
						return state.with(AXIS, Direction.Axis.Z);
					default:
						return state;
				}
			default:
				return state;
		}
	}

	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(AXIS);
	}

	@Override
	public BlockPattern.PatternHelper createPatternHelper(IWorld worldIn, BlockPos p_181089_2_) {
		Direction.Axis direction$axis = Direction.Axis.Z;
		Size netherportalblock$size = new Size(worldIn, p_181089_2_, Direction.Axis.X);
		LoadingCache<BlockPos, CachedBlockInfo> loadingcache = BlockPattern.createLoadingCache(worldIn, true);
		if (!netherportalblock$size.isValid()) {
			direction$axis = Direction.Axis.X;
			netherportalblock$size = new Size(worldIn, p_181089_2_, Direction.Axis.Z);
		}

		if (!netherportalblock$size.isValid()) {
			return new BlockPattern.PatternHelper(p_181089_2_, Direction.NORTH, Direction.UP, loadingcache, 1, 1, 1);
		} else {
			int[] aint = new int[Direction.AxisDirection.values().length];
			Direction direction = netherportalblock$size.rightDir.rotateYCCW();
			BlockPos blockpos = netherportalblock$size.bottomLeft.up(netherportalblock$size.getHeight() - 1);

			for(Direction.AxisDirection direction$axisdirection : Direction.AxisDirection.values()) {
				BlockPattern.PatternHelper blockpattern$patternhelper = new BlockPattern.PatternHelper(direction.getAxisDirection() == direction$axisdirection ? blockpos : blockpos.offset(netherportalblock$size.rightDir, netherportalblock$size.getWidth() - 1), Direction.getFacingFromAxis(direction$axisdirection, direction$axis), Direction.UP, loadingcache, netherportalblock$size.getWidth(), netherportalblock$size.getHeight(), 1);

				for(int i = 0; i < netherportalblock$size.getWidth(); ++i) {
					for(int j = 0; j < netherportalblock$size.getHeight(); ++j) {
						CachedBlockInfo cachedblockinfo = blockpattern$patternhelper.translateOffset(i, j, 1);
						if (!cachedblockinfo.getBlockState().isAir()) {
							++aint[direction$axisdirection.ordinal()];
						}
					}
				}
			}

			Direction.AxisDirection direction$axisdirection1 = Direction.AxisDirection.POSITIVE;

			for(Direction.AxisDirection direction$axisdirection2 : Direction.AxisDirection.values()) {
				if (aint[direction$axisdirection2.ordinal()] < aint[direction$axisdirection1.ordinal()]) {
					direction$axisdirection1 = direction$axisdirection2;
				}
			}

			return new BlockPattern.PatternHelper(direction.getAxisDirection() == direction$axisdirection1 ? blockpos : blockpos.offset(netherportalblock$size.rightDir, netherportalblock$size.getWidth() - 1), Direction.getFacingFromAxis(direction$axisdirection1, direction$axis), Direction.UP, loadingcache, netherportalblock$size.getWidth(), netherportalblock$size.getHeight(), 1);
		}
	}

	@Nullable
	public Size isPortal1(IWorld p_201816_1_, BlockPos p_201816_2_) {
		Size blockportal$size = new Size(p_201816_1_, p_201816_2_, Direction.Axis.X);
		if (blockportal$size.isValid() && blockportal$size.portalBlockCount == 0) {
			return blockportal$size;
		} else {
			Size blockportal$size1 = new Size(p_201816_1_, p_201816_2_, Direction.Axis.Z);
			return blockportal$size1.isValid() && blockportal$size1.portalBlockCount == 0 ? blockportal$size1 : null;
		}
	}

	public static class Size  {

		private final IWorld world;
		private final Direction.Axis axis;
		private final Direction rightDir;
		private final Direction leftDir;
		private int portalBlockCount;
		private BlockPos bottomLeft;
		private int height;
		private int width;

		public Size(IWorld p_i48740_1_, BlockPos p_i48740_2_, Direction.Axis p_i48740_3_) {
			this.world = p_i48740_1_;
			this.axis = p_i48740_3_;
			if (p_i48740_3_ == Direction.Axis.X) {
				this.leftDir = Direction.EAST;
				this.rightDir = Direction.WEST;
			} else {
				this.leftDir = Direction.NORTH;
				this.rightDir = Direction.SOUTH;
			}

			for (BlockPos blockpos = p_i48740_2_; p_i48740_2_.getY() > blockpos.getY() - 21 && p_i48740_2_.getY() > 0 && this.func_196900_a(p_i48740_1_.getBlockState(p_i48740_2_.down())); p_i48740_2_ = p_i48740_2_.down()) {
			}

			int i = this.getDistanceUntilEdge(p_i48740_2_, this.leftDir) - 1;
			if (i >= 0) {
				this.bottomLeft = p_i48740_2_.offset(this.leftDir, i);
				this.width = this.getDistanceUntilEdge(this.bottomLeft, this.rightDir);
				if (this.width < 2 || this.width > 21) {
					this.bottomLeft = null;
					this.width = 0;
				}
			}

			if (this.bottomLeft != null) {
				this.height = this.calculatePortalHeight();
			}

		}

		protected int getDistanceUntilEdge(BlockPos p_180120_1_, Direction p_180120_2_) {
			int i;
			for(i = 0; i < 22; ++i) {
				BlockPos blockpos = p_180120_1_.offset(p_180120_2_, i);
				if (!this.func_196900_a(this.world.getBlockState(blockpos)) || this.world.getBlockState(blockpos.down()).getBlock() != Blocks.STONE) {
					break;
				}
			}

			Block block = this.world.getBlockState(p_180120_1_.offset(p_180120_2_, i)).getBlock();
			return block == Blocks.STONE ? i : 0;
		}

		public int getHeight() {
			return this.height;
		}

		public int getWidth() {
			return this.width;
		}

		protected int calculatePortalHeight() {
			label56:
			for (this.height = 0; this.height < 21; ++this.height) {
				for (int i = 0; i < this.width; ++i) {
					BlockPos blockpos = this.bottomLeft.offset(this.rightDir, i).up(this.height);
					BlockState iblockstate = this.world.getBlockState(blockpos);
					if (!this.func_196900_a(iblockstate)) {
						break label56;
					}

					Block block = iblockstate.getBlock();
					if (block == KATHAIRIS_PORTAL) {
						++this.portalBlockCount;
					}

					if (i == 0) {
						block = this.world.getBlockState(blockpos.offset(this.leftDir)).getBlock();
						if ((block != Blocks.STONE)) {
							break label56;
						}
					} else if (i == this.width - 1) {
						block = this.world.getBlockState(blockpos.offset(this.rightDir)).getBlock();
						if ((block != Blocks.STONE)) {
							break label56;
						}
					}
				}
			}

			for (int j = 0; j < this.width; ++j) {
				if (this.world.getBlockState(this.bottomLeft.offset(this.rightDir, j).up(this.height)).getBlock() != Blocks.STONE) {
					this.height = 0;
					break;
				}
			}

			if (this.height <= 21 && this.height >= 3) {
				return this.height;
			} else {
				this.bottomLeft = null;
				this.width = 0;
				this.height = 0;
				return 0;
			}
		}

		protected boolean func_196900_a(BlockState p_196900_1_) {
			Block block = p_196900_1_.getBlock();
			return p_196900_1_.isAir() || block == Blocks.FIRE || block == ModBlocks.KATHAIRIS_PORTAL;
		}

		public boolean isValid() {
			return this.bottomLeft != null && this.width >= 2 && this.width <= 21 && this.height >= 3 && this.height <= 21;
		}

		public void placePortalBlocks() {
			for (int i = 0; i < this.width; ++i) {
				BlockPos blockpos = this.bottomLeft.offset(this.rightDir, i);

				for (int j = 0; j < this.height; ++j) {
					this.world.setBlockState(blockpos.up(j), KATHAIRIS_PORTAL.getDefaultState().with(NetherPortalBlock.AXIS, this.axis), 18);
				}
			}

		}

		private boolean func_196899_f() {
			return this.portalBlockCount >= this.width * this.height;
		}

		public boolean func_208508_f() {
			return this.isValid() && this.func_196899_f();
		}

	}

}
