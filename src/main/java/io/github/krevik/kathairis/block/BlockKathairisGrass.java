package io.github.krevik.kathairis.block;

import io.github.krevik.kathairis.init.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.IGrowable;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReaderBase;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Calendar;
import java.util.Random;

/**
 * @author Krevik
 */
//TODO cleanup
public class BlockKathairisGrass extends Block implements IGrowable {

	public static final BooleanProperty FLOWER = BooleanProperty.create("flower");
	public static final BooleanProperty SNOWY = BooleanProperty.create("snowy");
	int month = Calendar.getInstance().get(Calendar.MONTH);

	public BlockKathairisGrass() {
		super(Block.Properties.create(Material.GRASS).hardnessAndResistance(0.6F, 0.6F).tickRandomly().sound(SoundType.PLANT));
		this.setDefaultState(this.stateContainer.getBaseState().with(SNOWY, Boolean.FALSE).with(FLOWER, Boolean.FALSE));
	}

	private static boolean func_196383_a(IWorldReaderBase p_196383_0_, BlockPos p_196383_1_) {
		BlockPos blockpos = p_196383_1_.up();
		return p_196383_0_.getLight(blockpos) >= 4 || p_196383_0_.getBlockState(blockpos).getOpacity(p_196383_0_, blockpos) < p_196383_0_.getMaxLightLevel();
	}

	private static boolean func_196384_b(IWorldReaderBase p_196384_0_, BlockPos p_196384_1_) {
		BlockPos blockpos = p_196384_1_.up();
		return p_196384_0_.getLight(blockpos) >= 4 && p_196384_0_.getBlockState(blockpos).getOpacity(p_196384_0_, blockpos) < p_196384_0_.getMaxLightLevel() && !p_196384_0_.getFluidState(blockpos).isTagged(FluidTags.WATER);
	}

	public IBlockState updatePostPlacement(IBlockState stateIn, EnumFacing facing, IBlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
		return stateIn.with(SNOWY, shouldBeSnowed(stateIn)).with(FLOWER, stateIn.get(FLOWER));
	}

	@Override
	public boolean isSolid(IBlockState p_200124_1_) {
		return true;
	}

	public void tick(IBlockState state, World worldIn, BlockPos pos, Random random) {
		if (!worldIn.isRemote) {
			if (!worldIn.isAreaLoaded(pos, 3))
				return; // Forge: prevent loading unloaded chunks when checking neighbor's light and spreading
			if (!func_196383_a(worldIn, pos)) {
				worldIn.setBlockState(pos, ModBlocks.KATHAIRIS_DIRT.getDefaultState());
			} else {
				if (worldIn.getLight(pos.up()) >= 9) {
					for (int i = 0; i < 4; ++i) {
						BlockPos blockpos = pos.add(random.nextInt(3) - 1, random.nextInt(5) - 3, random.nextInt(3) - 1);
						if (!worldIn.isBlockPresent(blockpos)) {
							return;
						}

						if (worldIn.getBlockState(blockpos).getBlock() == ModBlocks.KATHAIRIS_DIRT && func_196384_b(worldIn, blockpos) && worldIn.isAirBlock(blockpos.up())) {
							worldIn.setBlockState(blockpos, this.getDefaultState());
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

	public IBlockState getStateForPlacement(BlockItemUseContext context) {
		return this.getDefaultState();
	}

	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, IBlockState> builder) {
		builder.add(FLOWER).add(SNOWY);
	}

	private boolean shouldBeSnowed(IBlockState state) {
		boolean result = false;
		if (month == 11) {
			return true;
		}
		return state.get(SNOWY);
	}

    /*@Override
    public void grow(World worldIn, Random rand, BlockPos pos, IBlockState state)
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
                            IBlockState iblockstate0 = KCore.MysticFungus.getDefaultState();
                            if(KCore.MysticFungus.canBlockStay(worldIn, blockpos1, iblockstate0)){
                                worldIn.setBlockState(blockpos1, iblockstate0, 3);
                            }
                        }
                        else
                        {
                            IBlockState iblockstate1 = KCore.MysticTallGrass.getDefaultState();
                            IBlockState iblockstate11 = KCore.MysticMiniGrass.getDefaultState();

                            if(rand.nextInt(3)==1){
                                if(KCore.MysticMiniGrass.canBlockStay(worldIn, blockpos1, iblockstate1)){
                                    worldIn.setBlockState(blockpos1, iblockstate11, 3);
                                }
                            }
                            else
                            if(KCore.MysticTallGrass.canBlockStay(worldIn, blockpos1, iblockstate1))
                            {
                                worldIn.setBlockState(blockpos1, iblockstate1, 3);
                            }
                        }
                    }

                    break;
                }

                blockpos1 = blockpos1.add(rand.nextInt(3) - 1, (rand.nextInt(3) - 1) * rand.nextInt(3) / 2, rand.nextInt(3) - 1);
                if (worldIn.getBlockState(blockpos1.down()).getBlock() != KCore.KathairisGrass || worldIn.getBlockState(blockpos1).isNormalCube())
                {
                    break;
                }

                ++j;
            }
        }
    }*/

	@Override
	public boolean canGrow(IBlockReader p_176473_1_, BlockPos p_176473_2_, IBlockState p_176473_3_, boolean p_176473_4_) {
		return p_176473_1_.getBlockState(p_176473_2_.up()).isAir();
	}

	@Override
	public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, IBlockState state) {
		return true;
	}

	@Override
	public void grow(World worldIn, Random rand, BlockPos pos, IBlockState state) {
		//TODO
	}

}
