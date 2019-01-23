package mod.krevik.kathairis.block;

import com.google.common.cache.LoadingCache;
import mod.krevik.kathairis.creativetab.ModCreativeTabs;
import mod.krevik.kathairis.init.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.BlockFlower.EnumFlowerColor;
import net.minecraft.block.BlockFlower.EnumFlowerType;
import net.minecraft.block.BlockPortal;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.BlockWorldState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemMonsterPlacer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Rotation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Random;

import static mod.krevik.kathairis.util.ModReference.KATHAIRIS_DIMENSION_ID;
import static net.minecraft.block.state.pattern.BlockPattern.PatternHelper;
import static net.minecraft.block.state.pattern.BlockPattern.createLoadingCache;

/**
 * @author Cadiboo
 */
public class BlockKatharisPortal extends BlockPortal {

	public BlockKatharisPortal() {
		this.setDefaultState(this.blockState.getBaseState().withProperty(AXIS, EnumFacing.Axis.X));
		this.setTickRandomly(true);
		this.setCreativeTab(ModCreativeTabs.BLOCKS);
//		this.setHardness(hardness);
//		this.setResistance(resistance);
//		this.setSoundType(soundType);
	}

	@Override
	public void updateTick(@Nonnull World worldIn, @Nonnull BlockPos pos, @Nonnull IBlockState state, @Nonnull Random rand) {
		super.updateTick(worldIn, pos, state, rand);

		if (worldIn.provider.isSurfaceWorld() && worldIn.getGameRules().getBoolean("doMobSpawning") && rand.nextInt(2000) < worldIn.getDifficulty().getId()) {
			int i = pos.getY();
			BlockPos blockpos;

			for (blockpos = pos; !worldIn.getBlockState(blockpos).isTopSolid() && blockpos.getY() > 0; blockpos = blockpos.down()) {
				;
			}

			if (i > 0 && !worldIn.getBlockState(blockpos.up()).isNormalCube()) {
				//TODO: not pig zombies
				Entity entity = ItemMonsterPlacer.spawnCreature(worldIn, EntityList.getKey(EntityPigZombie.class), (double) blockpos.getX() + 0.5D, (double) blockpos.getY() + 1.1D, (double) blockpos.getZ() + 0.5D);

				if (entity != null) {
					entity.timeUntilPortal = entity.getPortalCooldown();
				}
			}
		}

		if (worldIn.provider.getDimension() == KATHAIRIS_DIMENSION_ID) {
			List<EntityStrangeWanderer> e = worldIn.getEntitiesWithinAABB(EntityStrangeWanderer.class, new AxisAlignedBB(pos.getX() - 15, pos.getY() - 15, pos.getZ() - 15, pos.getX() + 15, pos.getY() + 15, pos.getZ() + 15));
			if (e.size() == 0) {
				if (!worldIn.isRemote) {
					int x = pos.getX() + rand.nextInt(4) - rand.nextInt(4);
					int y = pos.getY();
					int z = pos.getZ() + rand.nextInt(4) - rand.nextInt(4);
					if (worldIn.isAirBlock(new BlockPos(x, y, z)) && worldIn.isAirBlock(new BlockPos(x, y, z).up())) {
						EntityStrangeWanderer entityStrangeWanderer = new EntityStrangeWanderer(worldIn);
						entityStrangeWanderer.setPosition(x, y, z);
						worldIn.spawnEntity(entityStrangeWanderer);
					}

				}
			}
		}
		for (int x = 0; x < 1 + rand.nextInt(4); x++) {
			updateBlocksAroundPortal(worldIn, pos, state, rand);
		}

	}

	public static final ArrayList<IBlockState> OVERWORLD_FLOWERS = new ArrayList<>();
	public static IBlockState[] KATHAIRIS_FLOWERS;
	public static boolean areFlowersSetup = false;

	public void setupRandomFlowers() {
		for (EnumFlowerType type : EnumFlowerType.values()) {
			if (type.getBlockType() == EnumFlowerColor.YELLOW) {
				continue;
			}
			if (type == EnumFlowerType.BLUE_ORCHID) {
				type = EnumFlowerType.POPPY;
			}
			OVERWORLD_FLOWERS.add(Blocks.RED_FLOWER.getDefaultState().withProperty(Blocks.RED_FLOWER.getTypeProperty(), type));
		}
		for (int x = 0; x <= 10; x++) {
			OVERWORLD_FLOWERS.add(Blocks.TALLGRASS.getDefaultState());
		}
		KATHAIRIS_FLOWERS = {
				ModBlocks.KATHAIRIS_MINI_GRASS.getDefaultState(),
				ModBlocks.KATHAIRIS_TALL_GRASS.getDefaultState(),
				ModBlocks.KATHAIRIS_NIGHT_FLOWER.getDefaultState(),
				ModBlocks.BUTTERFLY_FLOWER.getDefaultState(),
				ModBlocks.BUTTERFLY_FLOWER.getDefaultState(),
				ModBlocks.KATHAIRIS_FUNGUS.getDefaultState(),
				ModBlocks.KATHAIRIS_MINI_GRASS.getDefaultState(),
				ModBlocks.KATHAIRIS_TALL_GRASS.getDefaultState(),
				ModBlocks.KATHAIRIS_MINI_GRASS.getDefaultState(),
				ModBlocks.KATHAIRIS_TALL_GRASS.getDefaultState(),
				ModBlocks.KATHAIRIS_MINI_GRASS.getDefaultState(),
				ModBlocks.KATHAIRIS_TALL_GRASS.getDefaultState(),
				ModBlocks.KATHAIRIS_MINI_GRASS.getDefaultState(),
				ModBlocks.KATHAIRIS_TALL_GRASS.getDefaultState(),
				ModBlocks.KATHAIRIS_MINI_GRASS.getDefaultState(),
				ModBlocks.KATHAIRIS_TALL_GRASS.getDefaultState(),
				ModBlocks.KATHAIRIS_MINI_GRASS.getDefaultState(),
				ModBlocks.KATHAIRIS_TALL_GRASS.getDefaultState(),
				ModBlocks.KATHAIRIS_SAPLING.getDefaultState(),
				ModBlocks.EYE_PLANT.getDefaultState(),
				ModBlocks.MAGIC_BEANS.getDefaultState(),
				ModBlocks.GOOSEBERRY_BLOCK.getDefaultState(),
				ModBlocks.STEPPED_SUCCULENT.getDefaultState()
		};
		areFlowersSetup = true;
	}

	@Nonnull
	private IBlockState getRandomFlowerState(@Nonnull final Random random, final boolean isKathairis) {
		if (!areFlowersSetup) {
			setupRandomFlowers();
		}
		if (!isKathairis) {
			//if overworld kathairis-ify it
			return KATHAIRIS_FLOWERS[random.nextInt(KATHAIRIS_FLOWERS.length)];
		} else {
			//if kathairis overworld-ify it
			return OVERWORLD_FLOWERS.get(RANDOM.nextInt(OVERWORLD_FLOWERS.size()));
		}
	}

	private void updateBlocksAroundPortal(@Nonnull final World worldIn, @Nonnull final BlockPos pos, @Nonnull final IBlockState state, @Nonnull final Random rand) {
		if (worldIn.provider.getDimension() == KATHAIRIS_DIMENSION_ID) {
			int radius = 5;
			int shiftX = +rand.nextInt(radius + 1) - rand.nextInt(radius + 1);
			int shiftY = -rand.nextInt(radius + 1) + rand.nextInt(radius + 1);
			int shiftZ = +rand.nextInt(radius + 1) - rand.nextInt(radius + 1);
			int randomX = pos.getX() + shiftX;
			int randomY = pos.getY() + shiftY;
			int randomZ = pos.getZ() + shiftZ;
			if ((MathHelper.abs(randomX - pos.getX()) * MathHelper.abs(randomX - pos.getX())) +
					(MathHelper.abs(randomZ - pos.getZ()) * MathHelper.abs(randomZ - pos.getZ())) <= radius * radius) {
				BlockPos tmp = new BlockPos(randomX, randomY, randomZ);
				if (worldIn.getBlockState(tmp) == ModBlocks.KATHARIAN_GRASS.getDefaultState()) {
					worldIn.setBlockState(tmp, Blocks.GRASS.getDefaultState(), 2);
				}
				if (worldIn.getBlockState(tmp) == ModBlocks.KATHARIAN_DIRT.getDefaultState()) {
					worldIn.setBlockState(tmp, Blocks.DIRT.getDefaultState(), 2);
				}
				if (worldIn.getBlockState(tmp) == ModBlocks.KATHARIAN_STONE.getDefaultState()) {
					worldIn.setBlockState(tmp, Blocks.STONE.getDefaultState(), 2);
				}
				if (worldIn.getBlockState(tmp) == ModBlocks.KATHARIAN_SAND.getDefaultState()) {
					worldIn.setBlockState(tmp, Blocks.SAND.getDefaultState(), 2);
				}
				if (worldIn.getBlockState(tmp) == Blocks.GRASS.getDefaultState() && worldIn.getBlockState(tmp.up()) == Blocks.AIR.getDefaultState()) {
					worldIn.setBlockState(tmp.up(), getRandomFlowerState(rand, true), 2);
				}
			}
			if (rand.nextInt(2) == 0) {
				this.updateBlocksAroundPortal(worldIn, pos, state, rand);
			}
		} else {
			int radius = 5;
			int randomX = pos.getX() + rand.nextInt(radius + 1) - rand.nextInt(radius + 1);
			int randomY = pos.getY() - rand.nextInt(radius + 1) + rand.nextInt(radius + 1);
			int randomZ = pos.getZ() + rand.nextInt(radius + 1) - rand.nextInt(radius + 1);
			BlockPos tmp = new BlockPos(randomX, randomY, randomZ);
			if ((MathHelper.abs(randomX - pos.getX()) * MathHelper.abs(randomX - pos.getX())) +
					(MathHelper.abs(randomZ - pos.getZ()) * MathHelper.abs(randomZ - pos.getZ())) <= radius * radius) {

				if (worldIn.getBlockState(tmp) == Blocks.GRASS.getDefaultState()) {
					worldIn.setBlockState(tmp, ModBlocks.KATHARIAN_GRASS.getDefaultState(), 2);
				}
				if (worldIn.getBlockState(tmp) == Blocks.DIRT.getDefaultState()) {
					worldIn.setBlockState(tmp, ModBlocks.KATHARIAN_DIRT.getDefaultState(), 2);
				}
				if (worldIn.getBlockState(tmp) == Blocks.STONE.getDefaultState()) {
					worldIn.setBlockState(tmp, ModBlocks.KATHARIAN_STONE.getDefaultState(), 2);
				}
				if (worldIn.getBlockState(tmp) == Blocks.SAND.getDefaultState()) {
					worldIn.setBlockState(tmp, ModBlocks.KATHARIAN_SAND.getDefaultState(), 2);
				}
				if (worldIn.getBlockState(tmp) == ModBlocks.KATHARIAN_GRASS.getDefaultState() && worldIn.getBlockState(tmp.up()) == Blocks.AIR.getDefaultState()) {
					worldIn.setBlockState(tmp.up(), getRandomFlowerState(rand, false), 2);
				}
			}
		}
	}

	@Nullable
	@Override
	public AxisAlignedBB getCollisionBoundingBox(@Nonnull final IBlockState blockState, @Nonnull final IBlockAccess worldIn, @Nonnull final BlockPos pos) {
		return NULL_AABB;
	}

	public static int getMetaForAxis(@Nonnull final EnumFacing.Axis axis) {
		if (axis == EnumFacing.Axis.X) {
			return 1;
		} else {
			return axis == EnumFacing.Axis.Z ? 2 : 0;
		}
	}

	@Override
	public boolean isFullCube(@Nonnull final IBlockState state) {
		return false;
	}

	@Override
	public boolean trySpawnPortal(@Nonnull final World worldIn, @Nonnull final BlockPos pos) {
		Size blockportal$size = new Size(worldIn, pos, EnumFacing.Axis.X);
		if (blockportal$size.isValid() && blockportal$size.portalBlockCount == 0) {
			blockportal$size.placePortalBlocks();
			return true;
		} else {
			Size blockportal$size1 = new Size(worldIn, pos, EnumFacing.Axis.Z);

			if (blockportal$size1.isValid() && blockportal$size1.portalBlockCount == 0) {
				blockportal$size1.placePortalBlocks();
				return true;
			} else {
				return false;

			}
		}
	}

	/**
	 * Called when a neighboring block was changed and marks that this state should perform any checks during a neighbor
	 * change. Cases may include when redstone power is updated, cactus blocks popping off due to a neighboring solid
	 * block, etc.
	 */
	@Override
	public void neighborChanged(@Nonnull IBlockState state, @Nonnull World worldIn, @Nonnull BlockPos pos, @Nonnull Block blockIn, @Nonnull BlockPos fromPos) {
		EnumFacing.Axis enumfacing$axis = (EnumFacing.Axis) state.getValue(AXIS);

		if (enumfacing$axis == EnumFacing.Axis.X) {
			Size blockportal$size = new Size(worldIn, pos, EnumFacing.Axis.X);

			if (!blockportal$size.isValid() || blockportal$size.portalBlockCount < blockportal$size.width * blockportal$size.height) {
				worldIn.setBlockState(pos, Blocks.AIR.getDefaultState());
			}
		} else if (enumfacing$axis == EnumFacing.Axis.Z) {
			Size blockportal$size1 = new Size(worldIn, pos, EnumFacing.Axis.Z);

			if (!blockportal$size1.isValid() || blockportal$size1.portalBlockCount < blockportal$size1.width * blockportal$size1.height) {
				worldIn.setBlockState(pos, Blocks.AIR.getDefaultState());
			}
		}
	}

	@SideOnly(Side.CLIENT)
	@Override
	public boolean shouldSideBeRendered(@Nonnull IBlockState blockState, @Nonnull IBlockAccess blockAccess, @Nonnull BlockPos pos, @Nonnull EnumFacing side) {
		pos = pos.offset(side);
		EnumFacing.Axis enumfacing$axis = null;

		if (blockState.getBlock() == this) {
			enumfacing$axis = blockState.getValue(AXIS);

			if (enumfacing$axis == null) {
				return false;
			}

			if (enumfacing$axis == EnumFacing.Axis.Z && side != EnumFacing.EAST && side != EnumFacing.WEST) {
				return false;
			}

			if (enumfacing$axis == EnumFacing.Axis.X && side != EnumFacing.SOUTH && side != EnumFacing.NORTH) {
				return false;
			}
		}

		boolean flag = blockAccess.getBlockState(pos.west()).getBlock() == this && blockAccess.getBlockState(pos.west(2)).getBlock() != this;
		boolean flag1 = blockAccess.getBlockState(pos.east()).getBlock() == this && blockAccess.getBlockState(pos.east(2)).getBlock() != this;
		boolean flag2 = blockAccess.getBlockState(pos.north()).getBlock() == this && blockAccess.getBlockState(pos.north(2)).getBlock() != this;
		boolean flag3 = blockAccess.getBlockState(pos.south()).getBlock() == this && blockAccess.getBlockState(pos.south(2)).getBlock() != this;
		boolean flag4 = flag || flag1 || enumfacing$axis == EnumFacing.Axis.X;
		boolean flag5 = flag2 || flag3 || enumfacing$axis == EnumFacing.Axis.Z;

		if (flag4 && side == EnumFacing.WEST) {
			return true;
		} else if (flag4 && side == EnumFacing.EAST) {
			return true;
		} else if (flag5 && side == EnumFacing.NORTH) {
			return true;
		} else {
			return flag5 && side == EnumFacing.SOUTH;
		}
	}

	/**
	 * Returns the quantity of items to drop on block destruction.
	 */
	@Override
	public int quantityDropped(Random random) {
		return 0;
	}

	/**
	 * Called When an Entity Collided with the Block
	 */
	@Override
	public void onEntityCollision(@Nonnull World worldIn, @Nonnull BlockPos pos, @Nonnull IBlockState state, @Nonnull Entity entityIn) {
		if ((entityIn.getRidingEntity() == null)) {
			if (entityIn instanceof EntityPlayerMP) {
				TileEntityKathairisTeleporter.teleportPlayer((EntityPlayerMP) entityIn);
			} else {
				TileEntityKathairisTeleporter.teleportEntity(entityIn);
			}
		}
	}

	@Override
	@Nonnull
	public ItemStack getItem(@Nonnull World worldIn, @Nonnull BlockPos pos, @Nonnull IBlockState state) {
		return ItemStack.EMPTY;
	}

	/**
	 * Convert the given metadata into a BlockState for this Block
	 */
	@Override
	@Nonnull
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(AXIS, (meta & 3) == 2 ? EnumFacing.Axis.Z : EnumFacing.Axis.X);
	}

	@Override
	@SideOnly(Side.CLIENT)
	@Nonnull
	public BlockRenderLayer getRenderLayer() {
		return BlockRenderLayer.TRANSLUCENT;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(@Nonnull IBlockState stateIn, @Nonnull World worldIn, @Nonnull BlockPos pos, @Nonnull Random rand) {
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
			Particle particle = new DynamicParticle(
					ParticlesFactory.PORTAL,
					worldIn,
					d0, d1, d2,
					d3, d4, d5)
					.setRotSpeed(((float) Math.random() - 0.5F) * 0.1F)
					.setLifeSpan(20 + rand.nextInt(20))
					.setGravity(0F)
					.setScale(2.0F)
					.setInitialAlpha(1.0F)
					.setFinalAlpha(0.5F)
					.setEnableDepth(true);
			Minecraft.getMinecraft().effectRenderer.addEffect(particle);
		}
	}

	/**
	 * Convert the BlockState into the correct metadata value
	 */
	@Override
	public int getMetaFromState(IBlockState state) {
		return getMetaForAxis((EnumFacing.Axis) state.getValue(AXIS));
	}

	/**
	 * Returns the blockstate with the given rotation from the passed blockstate. If inapplicable, returns the passed
	 * blockstate.
	 */
	@Override
	public IBlockState withRotation(IBlockState state, Rotation rot) {
		switch (rot) {
			case COUNTERCLOCKWISE_90:
			case CLOCKWISE_90:

				switch ((EnumFacing.Axis) state.getValue(AXIS)) {
					case X:
						return state.withProperty(AXIS, EnumFacing.Axis.Z);
					case Z:
						return state.withProperty(AXIS, EnumFacing.Axis.X);
					default:
						return state;
				}

			default:
				return state;
		}
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[]{AXIS});
	}

	@Override
	public PatternHelper createPatternHelper(World worldIn, BlockPos p_181089_2_) {
		EnumFacing.Axis enumfacing$axis = EnumFacing.Axis.Z;
		Size blockportal$size = new Size(worldIn, p_181089_2_, EnumFacing.Axis.X);
		LoadingCache<BlockPos, BlockWorldState> loadingcache = createLoadingCache(worldIn, true);

		if (!blockportal$size.isValid()) {
			enumfacing$axis = EnumFacing.Axis.X;
			blockportal$size = new Size(worldIn, p_181089_2_, EnumFacing.Axis.Z);
		}

		if (!blockportal$size.isValid()) {
			return new PatternHelper(p_181089_2_, EnumFacing.NORTH, EnumFacing.UP, loadingcache, 1, 1, 1);
		} else {
			int[] aint = new int[EnumFacing.AxisDirection.values().length];
			EnumFacing enumfacing = blockportal$size.rightDir.rotateYCCW();
			BlockPos blockpos = blockportal$size.bottomLeft.up(blockportal$size.getHeight() - 1);

			for (EnumFacing.AxisDirection enumfacing$axisdirection : EnumFacing.AxisDirection.values()) {
				PatternHelper blockpattern$patternhelper = new PatternHelper(enumfacing.getAxisDirection() == enumfacing$axisdirection ? blockpos : blockpos.offset(blockportal$size.rightDir, blockportal$size.getWidth() - 1), EnumFacing.getFacingFromAxis(enumfacing$axisdirection, enumfacing$axis), EnumFacing.UP, loadingcache, blockportal$size.getWidth(), blockportal$size.getHeight(), 1);

				for (int i = 0; i < blockportal$size.getWidth(); ++i) {
					for (int j = 0; j < blockportal$size.getHeight(); ++j) {
						BlockWorldState blockworldstate = blockpattern$patternhelper.translateOffset(i, j, 1);

						if (blockworldstate.getBlockState() != null && blockworldstate.getBlockState().getMaterial() != Material.AIR) {
							++aint[enumfacing$axisdirection.ordinal()];
						}
					}
				}
			}

			EnumFacing.AxisDirection enumfacing$axisdirection1 = EnumFacing.AxisDirection.POSITIVE;

			for (EnumFacing.AxisDirection enumfacing$axisdirection2 : EnumFacing.AxisDirection.values()) {
				if (aint[enumfacing$axisdirection2.ordinal()] < aint[enumfacing$axisdirection1.ordinal()]) {
					enumfacing$axisdirection1 = enumfacing$axisdirection2;
				}
			}

			return new PatternHelper(enumfacing.getAxisDirection() == enumfacing$axisdirection1 ? blockpos : blockpos.offset(blockportal$size.rightDir, blockportal$size.getWidth() - 1), EnumFacing.getFacingFromAxis(enumfacing$axisdirection1, enumfacing$axis), EnumFacing.UP, loadingcache, blockportal$size.getWidth(), blockportal$size.getHeight(), 1);
		}
	}

	@Override
	@Nonnull
	public BlockFaceShape getBlockFaceShape(@Nonnull IBlockAccess world, @Nonnull IBlockState state, @Nonnull BlockPos pos, @Nonnull EnumFacing facing) {
		return BlockFaceShape.UNDEFINED;
	}

	public static class Size extends BlockPortal.Size {

		public final World world;
		public int portalBlockCount;
		public BlockPos bottomLeft;
		public int height;
		public int width;
		private final EnumFacing.Axis axis;
		private final EnumFacing leftDir;
		private final EnumFacing rightDir;

		public Size(@Nonnull final World worldIn, @Nonnull BlockPos pos, @Nonnull final EnumFacing.Axis axis0) {
			super(worldIn, pos, axis0);
			this.world = worldIn;
			this.axis = axis0;

			if (axis0 == EnumFacing.Axis.X) {
				this.leftDir = EnumFacing.EAST;
				this.rightDir = EnumFacing.WEST;
			} else {
				this.leftDir = EnumFacing.NORTH;
				this.rightDir = EnumFacing.SOUTH;
			}

			for (BlockPos blockpos = pos; pos.getY() > blockpos.getY() - 21 && pos.getY() > 0 && this.isEmptyBlock(worldIn.getBlockState(pos.down()).getBlock()); pos = pos.down()) {
				;
			}

			int i = this.getDistanceUntilEdge(pos, this.leftDir) - 1;

			if (i >= 0) {
				this.bottomLeft = pos.offset(this.leftDir, i);
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

		protected int getDistanceUntilEdge(@Nonnull final BlockPos blockPos, @Nonnull final EnumFacing facing) {
			int i;
			for (i = 0; i < 22; ++i) {
				BlockPos blockpos = blockPos.offset(facing, i);

				if (!this.isEmptyBlock(this.world.getBlockState(blockpos).getBlock()) || this.world.getBlockState(blockpos.down()) != getPortalFrameBlock().getDefaultState()) {
					break;
				}
			}

			IBlockState block = this.world.getBlockState(blockPos.offset(facing, i));
			return block == getPortalFrameBlock().getDefaultState() ? i : 0;
		}

		private Block getPortalFrameBlock() {
			return Blocks.STONE;
		}

		@Override
		protected int calculatePortalHeight() {
			label56:

			for (this.height = 0; this.height < 21; ++this.height) {
				for (int i = 0; i < this.width; ++i) {
					BlockPos blockpos = this.bottomLeft.offset(this.rightDir, i).up(this.height);
					Block block = this.world.getBlockState(blockpos).getBlock();

					if (!this.isEmptyBlock(block)) {
						break label56;
					}

					if (block == this.getPortalBlock()) {
						++this.portalBlockCount;
					}

					if (i == 0) {
						IBlockState state = this.world.getBlockState(blockpos.offset(this.leftDir));

						if (state != Blocks.STONE.getDefaultState()) {
							break label56;
						}
					} else if (i == this.width - 1) {
						IBlockState state = this.world.getBlockState(blockpos.offset(this.rightDir));

						if (state != Blocks.STONE.getDefaultState()) {
							break label56;
						}
					}
				}
			}

			for (int j = 0; j < this.width; ++j) {
				if (this.world.getBlockState(this.bottomLeft.offset(this.rightDir, j).up(this.height)) != Blocks.STONE.getDefaultState()) {
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

		public BlockPortal getPortalBlock() {
			return ModBlocks.KATHAIRIS_PORTAL;
		}

		@Override
		protected boolean isEmptyBlock(@Nonnull final Block blockIn) {
			return blockIn.getMaterial(blockIn.getDefaultState()) == Material.AIR || blockIn == Blocks.FIRE || blockIn == Blocks.PORTAL || blockIn == ModBlocks.KATHAIRIS_PORTAL;
		}

		@Override
		public void placePortalBlocks() {
			for (int i = 0; i < this.width; ++i) {
				BlockPos blockpos = this.bottomLeft.offset(this.rightDir, i);

				for (int j = 0; j < this.height; ++j) {
					this.world.setBlockState(blockpos.up(j), this.getPortalState(), 2);
				}
			}
		}

		public IBlockState getPortalState() {
			return getPortalBlock().getDefaultState().withProperty(BlockPortal.AXIS, this.axis);
		}

	}

}


