package io.github.krevik.kathairis.world.dimension.feature.tree;

import com.mojang.datafixers.Dynamic;
import io.github.krevik.kathairis.init.ModBlocks;
import io.github.krevik.kathairis.world.dimension.feature.config.BaseKatharianTreeFeatureConfig;
import io.github.krevik.kathairis.world.dimension.feature.config.KatharianTreeFeatureConfig;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.CocoaBlock;
import net.minecraft.block.VineBlock;
import net.minecraft.state.BooleanProperty;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.IWorldWriter;
import net.minecraft.world.gen.IWorldGenerationReader;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.foliageplacer.FoliagePlacerType;

import java.util.Random;
import java.util.Set;
import java.util.function.Function;

public class KatharianTreeFeature extends AbstractTreeFeature<KatharianTreeFeatureConfig> {
    private static final BlockState DEFAULT_TRUNK = ModBlocks.MYSTIC_LOG.getDefaultState();
    private static final BlockState DEFAULT_LEAF = ModBlocks.MYSTIC_LEAVES.getDefaultState();
    protected final int minTreeHeight;
    private final boolean vinesGrow;
    private final BlockState trunk;
    private final BlockState leaf;

    public KatharianTreeFeature(Function<Dynamic<?>, ? extends KatharianTreeFeatureConfig> configFactoryIn) {
        this(configFactoryIn, true, 4, DEFAULT_TRUNK, DEFAULT_LEAF, false);
    }

    public KatharianTreeFeature(Function<Dynamic<?>, ? extends KatharianTreeFeatureConfig> configFactoryIn, boolean doBlockNotifyOnPlace, int minTreeHeightIn, BlockState trunkState, BlockState leafState, boolean vinesGrowIn) {
        super(configFactoryIn);
        this.minTreeHeight = minTreeHeightIn;
        this.trunk = trunkState;
        this.leaf = leafState;
        this.vinesGrow = vinesGrowIn;
    }

    protected int getHeight(Random random) {
        return this.minTreeHeight + random.nextInt(3);
    }

    private void placeCocoa(IWorldWriter worldIn, int age, BlockPos pos, Direction side) {
        this.setBlockState(worldIn, pos, Blocks.COCOA.getDefaultState().with(CocoaBlock.AGE, Integer.valueOf(age)).with(CocoaBlock.HORIZONTAL_FACING, side));
    }

    private void addVine(IWorldWriter worldIn, BlockPos pos, BooleanProperty prop) {
        this.setBlockState(worldIn, pos, Blocks.VINE.getDefaultState().with(prop, Boolean.valueOf(true)));
    }

    private void addHangingVine(IWorldGenerationReader worldIn, BlockPos pos, BooleanProperty prop) {
        this.addVine(worldIn, pos, prop);
        int i = 4;

        for(BlockPos blockpos = pos.down(); isAir(worldIn, blockpos) && i > 0; --i) {
            this.addVine(worldIn, blockpos, prop);
            blockpos = blockpos.down();
        }
    }

    public static <T> KatharianTreeFeature func_227338_a_(Dynamic<T> p_227338_0_) {
        BaseTreeFeatureConfig basetreefeatureconfig = BaseTreeFeatureConfig.func_227376_b_(p_227338_0_);
        FoliagePlacerType<?> foliageplacertype = Registry.field_229389_v_.getOrDefault(new ResourceLocation(p_227338_0_.get("foliage_placer").get("type").asString().orElseThrow(RuntimeException::new)));
        return new KatharianTreeFeature(basetreefeatureconfig.field_227368_m_, basetreefeatureconfig.field_227369_n_, foliageplacertype.func_227391_a_(p_227338_0_.get("foliage_placer").orElseEmptyMap()), basetreefeatureconfig.field_227370_o_, basetreefeatureconfig.field_227371_p_, p_227338_0_.get("height_rand_a").asInt(0), p_227338_0_.get("height_rand_b").asInt(0), p_227338_0_.get("trunk_height").asInt(-1), p_227338_0_.get("trunk_height_random").asInt(0), p_227338_0_.get("trunk_top_offset").asInt(0), p_227338_0_.get("trunk_top_offset_random").asInt(0), p_227338_0_.get("foliage_height").asInt(-1), p_227338_0_.get("foliage_height_random").asInt(0), p_227338_0_.get("max_water_depth").asInt(0), p_227338_0_.get("ignore_vines").asBoolean(false));
    }

    @Override
    protected boolean func_225557_a_(IWorldGenerationReader worldIn, Random rand, BlockPos position, Set<BlockPos> leavesSet, Set<BlockPos> trunksSet, MutableBoundingBox p_225557_6_, KatharianTreeFeatureConfig config) {
        int i = this.getHeight(rand);
        boolean flag = true;
        if (position.getY() >= 1 && position.getY() + i + 1 <= worldIn.getMaxHeight()) {
            for(int j = position.getY(); j <= position.getY() + 1 + i; ++j) {
                int k = 1;
                if (j == position.getY()) {
                    k = 0;
                }

                if (j >= position.getY() + 1 + i - 2) {
                    k = 2;
                }

                BlockPos.Mutable blockpos$mutableblockpos = new BlockPos.Mutable();

                for(int l = position.getX() - k; l <= position.getX() + k && flag; ++l) {
                    for(int i1 = position.getZ() - k; i1 <= position.getZ() + k && flag; ++i1) {
                        if (j >= 0 && j < worldIn.getMaxHeight()) {
                            if (!func_214587_a(worldIn, blockpos$mutableblockpos.setPos(l, j, i1))) {
                                flag = false;
                            }
                        } else {
                            flag = false;
                        }
                    }
                }
            }


            if (!flag) {
                return false;
            } else if (isSoil(worldIn, position.down(), config.getSapling()) && position.getY() < worldIn.getMaxHeight() - i - 1) {
                this.setDirtAt(worldIn, position.down(), position);
                int j2 = 3;
                int k2 = 0;

                for(int l2 = position.getY() - 3 + i; l2 <= position.getY() + i; ++l2) {
                    int l3 = l2 - (position.getY() + i);
                    int j4 = 1 - l3 / 2;

                    for(int j1 = position.getX() - j4; j1 <= position.getX() + j4; ++j1) {
                        int k1 = j1 - position.getX();

                        for(int l1 = position.getZ() - j4; l1 <= position.getZ() + j4; ++l1) {
                            int i2 = l1 - position.getZ();
                            if (Math.abs(k1) != j4 || Math.abs(i2) != j4 || rand.nextInt(2) != 0 && l3 != 0) {
                                BlockPos blockpos = new BlockPos(j1, l2, l1);
                                if (isAirOrLeaves(worldIn, blockpos) || isTallPlants(worldIn, blockpos)) {
                                    worldIn.setBlockState(blockpos,leaf,2);
                                    leavesSet.add(blockpos);
                                }
                            }
                        }
                    }
                }


                for(int i3 = 0; i3 < i; ++i3) {
                    if (isAirOrLeaves(worldIn, position.up(i3)) || isTallPlants(worldIn, position.up(i3))) {
                        worldIn.setBlockState(position.up(i3),trunk,2);
                        trunksSet.add(position.up(i3));
                        if (this.vinesGrow && i3 > 0) {
                            if (rand.nextInt(3) > 0 && isAir(worldIn, position.add(-1, i3, 0))) {
                                this.addVine(worldIn, position.add(-1, i3, 0), VineBlock.EAST);
                            }

                            if (rand.nextInt(3) > 0 && isAir(worldIn, position.add(1, i3, 0))) {
                                this.addVine(worldIn, position.add(1, i3, 0), VineBlock.WEST);
                            }

                            if (rand.nextInt(3) > 0 && isAir(worldIn, position.add(0, i3, -1))) {
                                this.addVine(worldIn, position.add(0, i3, -1), VineBlock.SOUTH);
                            }

                            if (rand.nextInt(3) > 0 && isAir(worldIn, position.add(0, i3, 1))) {
                                this.addVine(worldIn, position.add(0, i3, 1), VineBlock.NORTH);
                            }
                        }
                    }
                }

                if (this.vinesGrow) {
                    for(int j3 = position.getY() - 3 + i; j3 <= position.getY() + i; ++j3) {
                        int i4 = j3 - (position.getY() + i);
                        int k4 = 2 - i4 / 2;
                        BlockPos.Mutable blockpos$mutableblockpos1 = new BlockPos.Mutable();

                        for(int l4 = position.getX() - k4; l4 <= position.getX() + k4; ++l4) {
                            for(int i5 = position.getZ() - k4; i5 <= position.getZ() + k4; ++i5) {
                                blockpos$mutableblockpos1.setPos(l4, j3, i5);
                                if (isAirOrLeaves(worldIn, blockpos$mutableblockpos1)) {
                                    BlockPos blockpos3 = blockpos$mutableblockpos1.west();
                                    BlockPos blockpos4 = blockpos$mutableblockpos1.east();
                                    BlockPos blockpos1 = blockpos$mutableblockpos1.north();
                                    BlockPos blockpos2 = blockpos$mutableblockpos1.south();
                                    if (rand.nextInt(4) == 0 && isAir(worldIn, blockpos3)) {
                                        this.addHangingVine(worldIn, blockpos3, VineBlock.EAST);
                                    }

                                    if (rand.nextInt(4) == 0 && isAir(worldIn, blockpos4)) {
                                        this.addHangingVine(worldIn, blockpos4, VineBlock.WEST);
                                    }

                                    if (rand.nextInt(4) == 0 && isAir(worldIn, blockpos1)) {
                                        this.addHangingVine(worldIn, blockpos1, VineBlock.SOUTH);
                                    }

                                    if (rand.nextInt(4) == 0 && isAir(worldIn, blockpos2)) {
                                        this.addHangingVine(worldIn, blockpos2, VineBlock.NORTH);
                                    }
                                }
                            }
                        }
                    }

                    if (rand.nextInt(5) == 0 && i > 5) {
                        for(int k3 = 0; k3 < 2; ++k3) {
                            for(Direction direction : Direction.Plane.HORIZONTAL) {
                                if (rand.nextInt(4 - k3) == 0) {
                                    Direction direction1 = direction.getOpposite();
                                    this.placeCocoa(worldIn, rand.nextInt(3), position.add(direction1.getXOffset(), i - 5 + k3, direction1.getZOffset()), direction);
                                }
                            }
                        }
                    }
                }

                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}