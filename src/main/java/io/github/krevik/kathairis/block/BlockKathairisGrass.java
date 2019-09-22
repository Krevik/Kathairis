package io.github.krevik.kathairis.block;

import io.github.krevik.kathairis.init.ModBlocks;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.lighting.LightEngine;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Calendar;
import java.util.Random;

/**
 * @author Krevik
 */
//TODO cleanup
public class BlockKathairisGrass extends GrassBlock implements IGrowable {

	public static final BooleanProperty FLOWER = BooleanProperty.create("flower");
	int month = Calendar.getInstance().get(Calendar.MONTH);

	public BlockKathairisGrass() {
		super(Properties.create(Material.ORGANIC).hardnessAndResistance(0.6F, 0.6F).tickRandomly().sound(SoundType.PLANT));
		this.setDefaultState(this.stateContainer.getBaseState().with(SNOWY, Boolean.FALSE).with(FLOWER, Boolean.FALSE));
	}


	@Override
	public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
		return stateIn.with(SNOWY, shouldBeSnowed(stateIn)).with(FLOWER, stateIn.get(FLOWER));
	}

	@Override
	public boolean isSolid(BlockState p_200124_1_) {
		return true;
	}

	private static boolean func_220257_b(BlockState p_220257_0_, IWorldReader p_220257_1_, BlockPos p_220257_2_) {
		BlockPos blockpos = p_220257_2_.up();
		BlockState blockstate = p_220257_1_.getBlockState(blockpos);
		if (blockstate.getBlock() == Blocks.SNOW && blockstate.get(SnowBlock.LAYERS) == 1) {
			return true;
		} else {
			int i = LightEngine.func_215613_a(p_220257_1_, p_220257_0_, p_220257_2_, blockstate, blockpos, Direction.UP, blockstate.getOpacity(p_220257_1_, blockpos));
			return i < p_220257_1_.getMaxLightLevel();
		}
	}

	private static boolean func_220256_c(BlockState p_220256_0_, IWorldReader p_220256_1_, BlockPos p_220256_2_) {
		BlockPos blockpos = p_220256_2_.up();
		return func_220257_b(p_220256_0_, p_220256_1_, p_220256_2_) && !p_220256_1_.getFluidState(blockpos).isTagged(FluidTags.WATER);
	}

	@Override
	public void tick(BlockState state, World worldIn, BlockPos pos, Random random) {
		if (!worldIn.isRemote) {
			if (!worldIn.isAreaLoaded(pos, 3)) return; // Forge: prevent loading unloaded chunks when checking neighbor's light and spreading
			if (!func_220257_b(state, worldIn, pos)) {
				worldIn.setBlockState(pos, Blocks.DIRT.getDefaultState());
			} else {
				if (worldIn.getLight(pos.up()) >= 9) {
					BlockState blockstate = this.getDefaultState();

					for(int i = 0; i < 4; ++i) {
						BlockPos blockpos = pos.add(random.nextInt(3) - 1, random.nextInt(5) - 3, random.nextInt(3) - 1);
						if (worldIn.getBlockState(blockpos).getBlock() == Blocks.DIRT && func_220256_c(blockstate, worldIn, blockpos)) {
							worldIn.setBlockState(blockpos, blockstate.with(SNOWY, Boolean.valueOf(worldIn.getBlockState(blockpos.up()).getBlock() == Blocks.SNOW)));
						}
					}
				}

			}
		}

		//TODO ADD THESE EVENTS
        /*if(rand.nextInt(999999)==0) {
            int month = Calendar.getInstance().get(Calendar.MONTH);
            int day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
            if(month==3&&(day==1||day==2)) {
                if(worldIn.isAirBlock(new BlockPos(pos.getX(), pos.getY()+1, pos.getZ()))) {
                    if(!worldIn.isRemote) {
                        for(int i=0;i<(2+rand.nextInt(3));i++) {
                            EntityRabbit er = new EntityRabbit(worldIn);
                            er.setPosition(pos.getX()+rand.nextInt(4)-rand.nextInt(4), pos.getY()+1, pos.getZ()+rand.nextInt(4)-rand.nextInt(4));
                            worldIn.spawnEntity(er);
                        }
                        worldIn.setBlockState(new BlockPos(pos.getX(), pos.getY()+1, pos.getZ()), KCore.EasterEgg.getDefaultState());
                    }
                }
            }
            if(month==2&&(day==31||day==30)) {
                if(worldIn.isAirBlock(new BlockPos(pos.getX(), pos.getY()+1, pos.getZ()))) {
                    if(!worldIn.isRemote) {
                        for(int i=0;i<(2+rand.nextInt(3));i++) {
                            EntityRabbit er = new EntityRabbit(worldIn);
                            er.setPosition(pos.getX()+rand.nextInt(4)-rand.nextInt(4), pos.getY()+1, pos.getZ()+rand.nextInt(4)-rand.nextInt(4));
                            worldIn.spawnEntity(er);
                        }
                        worldIn.setBlockState(new BlockPos(pos.getX(), pos.getY()+1, pos.getZ()), KCore.EasterEgg.getDefaultState(),2);
                    }
                }
            }
        }*/
	}

	@OnlyIn(Dist.CLIENT)
	@Override
	public BlockRenderLayer getRenderLayer() {
		return BlockRenderLayer.CUTOUT_MIPPED;
	}

    /*private IBlockState getRandomGiftState(Random random){
        int k=random.nextInt(3);
        if(k==0){
            return KCore.christmas_gift.getDefaultState().withProperty(BlockChristmasGift.VARIANT,BlockChristmasGift.EnumType.RED);
        }
        else if(k==1){
            return KCore.christmas_gift.getDefaultState().withProperty(BlockChristmasGift.VARIANT,BlockChristmasGift.EnumType.YELLOW);
        }
        else if(k==2){
            return KCore.christmas_gift.getDefaultState().withProperty(BlockChristmasGift.VARIANT,BlockChristmasGift.EnumType.VIOLET);
        }else{
            return KCore.christmas_gift.getDefaultState().withProperty(BlockChristmasGift.VARIANT,BlockChristmasGift.EnumType.VIOLET);
        }
    }*/

    @Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		return this.getDefaultState();
	}

	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(FLOWER).add(SNOWY);
	}

	private boolean shouldBeSnowed(BlockState state) {
		boolean result = false;
		if (month == 11) {
			return true;
		}
		return state.get(SNOWY);
	}

    @Override
    public void grow(World worldIn, Random rand, BlockPos pos, BlockState state)
    {
        BlockPos blockpos = pos.up();

        for (int i = 0; i < 128; ++i)
        {
            BlockPos blockpos1 = blockpos;
            int j = 0;

            while (true)
            {
                if (j >= i / 16)
                {
                    if (worldIn.isAirBlock(blockpos1))
                    {
                        if (rand.nextInt(100) == 6)
                        {
							BlockState iblockstate0 = ModBlocks.KATHAIRIS_FUNGI.getDefaultState();
                            if(ModBlocks.KATHAIRIS_FUNGI.isValidPosition(iblockstate0,worldIn,blockpos1)){
                                worldIn.setBlockState(blockpos1, iblockstate0, 3);
                            }
                        }
                        else
                        {
							BlockState iblockstate1 = ModBlocks.KATHAIRIS_TALL_GRASS.getDefaultState();
							BlockState iblockstate11 = ModBlocks.KATHAIRIS_MINI_GRASS.getDefaultState();

                            if(rand.nextInt(3)==1){
                                if(ModBlocks.KATHAIRIS_MINI_GRASS.isValidPosition(iblockstate1,worldIn,blockpos1)){
                                    worldIn.setBlockState(blockpos1, iblockstate11, 3);
                                }
                            }
                            else
                            if(ModBlocks.KATHAIRIS_TALL_GRASS.isValidPosition(iblockstate1,worldIn,blockpos1))
                            {
                                worldIn.setBlockState(blockpos1, iblockstate1, 3);
                            }
                        }
                    }

                    break;
                }

                blockpos1 = blockpos1.add(rand.nextInt(3) - 1, (rand.nextInt(3) - 1) * rand.nextInt(3) / 2, rand.nextInt(3) - 1);
                if (worldIn.getBlockState(blockpos1.down()).getBlock() != ModBlocks.KATHAIRIS_GRASS || worldIn.getBlockState(blockpos1).isNormalCube(worldIn,blockpos1))
                {
                    break;
                }

                ++j;
            }
        }
    }

	@Override
	public boolean canGrow(IBlockReader p_176473_1_, BlockPos p_176473_2_, BlockState p_176473_3_, boolean p_176473_4_) {
		return p_176473_1_.getBlockState(p_176473_2_.up()).isAir();
	}

	@Override
	public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, BlockState state) {
		return true;
	}

}
