package io.github.krevik.kathairis.world.dimension.feature;

import com.google.common.collect.ImmutableSet;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos.MutableBlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.carver.WorldCarver;
import net.minecraft.world.gen.feature.ProbabilityConfig;

import java.util.BitSet;
import java.util.Random;

import static io.github.krevik.kathairis.init.ModBlocks.KATHARIAN_DIRT;
import static io.github.krevik.kathairis.init.ModBlocks.KATHARIAN_GRASS;
import static io.github.krevik.kathairis.init.ModBlocks.KATHARIAN_SAND;
import static io.github.krevik.kathairis.init.ModBlocks.KATHARIAN_SANDSTONE;
import static io.github.krevik.kathairis.init.ModBlocks.KATHARIAN_STONE;
import static io.github.krevik.kathairis.init.ModBlocks.SOFT_SAND;

public class KatharianWorldCaveCarver extends WorldCarver<ProbabilityConfig> {

	public KatharianWorldCaveCarver() {
		this.terrainBlocks = ImmutableSet.of(KATHARIAN_GRASS, KATHARIAN_DIRT, KATHARIAN_STONE, KATHARIAN_SAND,
				SOFT_SAND, KATHARIAN_SANDSTONE, Blocks.STONE, Blocks.DIRT, Blocks.ANDESITE, Blocks.DIORITE, Blocks.GRAVEL);
	}

	@Override
	public boolean func_212246_a(IBlockReader blockReader, Random random, int p_212246_3_, int p_212246_4_, ProbabilityConfig config) {
		return random.nextFloat() <= config.probability;
	}

	@Override
	public boolean carve(IWorld world, Random random, int chunkX, int chunkZ, int originalX, int originalZ, BitSet bitSet, ProbabilityConfig config) {
		int lvt_9_1_ = (this.func_202520_b() * 2 - 1) * 16;
		int lvt_10_1_ = random.nextInt(random.nextInt(random.nextInt(15) + 1) + 1);

		for (int lvt_11_1_ = 0; lvt_11_1_ < lvt_10_1_; ++lvt_11_1_) {
			double lvt_12_1_ = (double) (chunkX * 16 + random.nextInt(16));
			double lvt_14_1_ = (double) random.nextInt(random.nextInt(120) + 8);
			double lvt_16_1_ = (double) (chunkZ * 16 + random.nextInt(16));
			int lvt_18_1_ = 1;
			float lvt_21_2_;
			if (random.nextInt(4) == 0) {
				double lvt_19_1_ = 0.5D;
				lvt_21_2_ = 1.0F + random.nextFloat() * 6.0F;
				this.addRoom(world, random.nextLong(), originalX, originalZ, lvt_12_1_, lvt_14_1_, lvt_16_1_, lvt_21_2_, 0.5D, bitSet);
				lvt_18_1_ += random.nextInt(4);
			}

			for (int lvt_19_2_ = 0; lvt_19_2_ < lvt_18_1_; ++lvt_19_2_) {
				float lvt_20_1_ = random.nextFloat() * 6.2831855F;
				lvt_21_2_ = (random.nextFloat() - 0.5F) / 4.0F;
				double lvt_22_1_ = 1.0D;
				float lvt_24_1_ = random.nextFloat() * 2.0F + random.nextFloat();
				if (random.nextInt(10) == 0) {
					lvt_24_1_ *= random.nextFloat() * random.nextFloat() * 3.0F + 1.0F;
				}

				int lvt_25_1_ = lvt_9_1_ - random.nextInt(lvt_9_1_ / 4);
				this.addTunnel(world, random.nextLong(), originalX, originalZ, lvt_12_1_, lvt_14_1_, lvt_16_1_, lvt_24_1_, lvt_20_1_, lvt_21_2_, 0, lvt_25_1_, 1.0D, bitSet);
			}
		}

		return true;
	}

	protected void addRoom(IWorld world, long p_203627_2_, int p_203627_4_, int p_203627_5_, double p_203627_6_, double p_203627_8_, double p_203627_10_, float p_203627_12_, double p_203627_13_, BitSet bitSet) {
		double lvt_16_1_ = 1.5D + (double) (MathHelper.sin(1.5707964F) * p_203627_12_);
		double lvt_18_1_ = lvt_16_1_ * p_203627_13_;
		this.carveAtTarget(world, p_203627_2_, p_203627_4_, p_203627_5_, p_203627_6_ + 1.0D, p_203627_8_, p_203627_10_, lvt_16_1_, lvt_18_1_, bitSet);
	}

	protected void addTunnel(IWorld p_202533_1_, long p_202533_2_, int p_202533_4_, int p_202533_5_, double p_202533_6_, double p_202533_8_, double p_202533_10_, float p_202533_12_, float p_202533_13_, float p_202533_14_, int p_202533_15_, int p_202533_16_, double p_202533_17_, BitSet bitSet) {
		Random lvt_20_1_ = new Random(p_202533_2_);
		int lvt_21_1_ = lvt_20_1_.nextInt(p_202533_16_ / 2) + p_202533_16_ / 4;
		boolean lvt_22_1_ = lvt_20_1_.nextInt(6) == 0;
		float lvt_23_1_ = 0.0F;
		float lvt_24_1_ = 0.0F;

		for (int lvt_25_1_ = p_202533_15_; lvt_25_1_ < p_202533_16_; ++lvt_25_1_) {
			double lvt_26_1_ = 1.5D + (double) (MathHelper.sin(3.1415927F * (float) lvt_25_1_ / (float) p_202533_16_) * p_202533_12_);
			double lvt_28_1_ = lvt_26_1_ * p_202533_17_;
			float lvt_30_1_ = MathHelper.cos(p_202533_14_);
			p_202533_6_ += (double) (MathHelper.cos(p_202533_13_) * lvt_30_1_);
			p_202533_8_ += (double) MathHelper.sin(p_202533_14_);
			p_202533_10_ += (double) (MathHelper.sin(p_202533_13_) * lvt_30_1_);
			p_202533_14_ *= lvt_22_1_ ? 0.92F : 0.7F;
			p_202533_14_ += lvt_24_1_ * 0.1F;
			p_202533_13_ += lvt_23_1_ * 0.1F;
			lvt_24_1_ *= 0.9F;
			lvt_23_1_ *= 0.75F;
			lvt_24_1_ += (lvt_20_1_.nextFloat() - lvt_20_1_.nextFloat()) * lvt_20_1_.nextFloat() * 2.0F;
			lvt_23_1_ += (lvt_20_1_.nextFloat() - lvt_20_1_.nextFloat()) * lvt_20_1_.nextFloat() * 4.0F;
			if (lvt_25_1_ == lvt_21_1_ && p_202533_12_ > 1.0F) {
				this.addTunnel(p_202533_1_, lvt_20_1_.nextLong(), p_202533_4_, p_202533_5_, p_202533_6_, p_202533_8_, p_202533_10_, lvt_20_1_.nextFloat() * 0.5F + 0.5F, p_202533_13_ - 1.5707964F, p_202533_14_ / 3.0F, lvt_25_1_, p_202533_16_, 1.0D, bitSet);
				this.addTunnel(p_202533_1_, lvt_20_1_.nextLong(), p_202533_4_, p_202533_5_, p_202533_6_, p_202533_8_, p_202533_10_, lvt_20_1_.nextFloat() * 0.5F + 0.5F, p_202533_13_ + 1.5707964F, p_202533_14_ / 3.0F, lvt_25_1_, p_202533_16_, 1.0D, bitSet);
				return;
			}

			if (lvt_20_1_.nextInt(4) != 0) {
				if (!this.isWithinGenerationDepth(p_202533_4_, p_202533_5_, p_202533_6_, p_202533_10_, lvt_25_1_, p_202533_16_, p_202533_12_)) {
					return;
				}

				this.carveAtTarget(p_202533_1_, p_202533_2_, p_202533_4_, p_202533_5_, p_202533_6_, p_202533_8_, p_202533_10_, lvt_26_1_, lvt_28_1_, bitSet);
			}
		}

	}

	@Override
	protected boolean carveAtTarget(IWorld world, long p_202516_2_, int p_202516_4_, int p_202516_5_, double p_202516_6_, double p_202516_8_, double p_202516_10_, double p_202516_12_, double p_202516_14_, BitSet bitSet) {
		double lvt_17_1_ = (double) (p_202516_4_ * 16 + 8);
		double lvt_19_1_ = (double) (p_202516_5_ * 16 + 8);
		if (p_202516_6_ >= lvt_17_1_ - 16.0D - p_202516_12_ * 2.0D && p_202516_10_ >= lvt_19_1_ - 16.0D - p_202516_12_ * 2.0D && p_202516_6_ <= lvt_17_1_ + 16.0D + p_202516_12_ * 2.0D && p_202516_10_ <= lvt_19_1_ + 16.0D + p_202516_12_ * 2.0D) {
			int lvt_21_1_ = Math.max(MathHelper.floor(p_202516_6_ - p_202516_12_) - p_202516_4_ * 16 - 1, 0);
			int lvt_22_1_ = Math.min(MathHelper.floor(p_202516_6_ + p_202516_12_) - p_202516_4_ * 16 + 1, 16);
			int lvt_23_1_ = Math.max(MathHelper.floor(p_202516_8_ - p_202516_14_) - 1, 1);
			int lvt_24_1_ = Math.min(MathHelper.floor(p_202516_8_ + p_202516_14_) + 1, 248);
			int lvt_25_1_ = Math.max(MathHelper.floor(p_202516_10_ - p_202516_12_) - p_202516_5_ * 16 - 1, 0);
			int lvt_26_1_ = Math.min(MathHelper.floor(p_202516_10_ + p_202516_12_) - p_202516_5_ * 16 + 1, 16);
			if (this.doesAreaHaveFluids(world, p_202516_4_, p_202516_5_, lvt_21_1_, lvt_22_1_, lvt_23_1_, lvt_24_1_, lvt_25_1_, lvt_26_1_)) {
				return false;
			} else {
				boolean lvt_27_1_ = false;
				MutableBlockPos lvt_28_1_ = new MutableBlockPos();
				MutableBlockPos lvt_29_1_ = new MutableBlockPos();
				MutableBlockPos lvt_30_1_ = new MutableBlockPos();

				for (int lvt_31_1_ = lvt_21_1_; lvt_31_1_ < lvt_22_1_; ++lvt_31_1_) {
					int lvt_32_1_ = lvt_31_1_ + p_202516_4_ * 16;
					double lvt_33_1_ = ((double) lvt_32_1_ + 0.5D - p_202516_6_) / p_202516_12_;

					for (int lvt_35_1_ = lvt_25_1_; lvt_35_1_ < lvt_26_1_; ++lvt_35_1_) {
						int lvt_36_1_ = lvt_35_1_ + p_202516_5_ * 16;
						double lvt_37_1_ = ((double) lvt_36_1_ + 0.5D - p_202516_10_) / p_202516_12_;
						if (lvt_33_1_ * lvt_33_1_ + lvt_37_1_ * lvt_37_1_ < 1.0D) {
							boolean lvt_39_1_ = false;

							for (int lvt_40_1_ = lvt_24_1_; lvt_40_1_ > lvt_23_1_; --lvt_40_1_) {
								double lvt_41_1_ = ((double) lvt_40_1_ - 0.5D - p_202516_8_) / p_202516_14_;
								if (lvt_41_1_ > -0.7D && lvt_33_1_ * lvt_33_1_ + lvt_41_1_ * lvt_41_1_ + lvt_37_1_ * lvt_37_1_ < 1.0D) {
									int lvt_43_1_ = lvt_31_1_ | lvt_35_1_ << 4 | lvt_40_1_ << 8;
									if (!bitSet.get(lvt_43_1_)) {
										bitSet.set(lvt_43_1_);
										lvt_28_1_.setPos(lvt_32_1_, lvt_40_1_, lvt_36_1_);
										IBlockState lvt_44_1_ = world.getBlockState(lvt_28_1_);
										IBlockState lvt_45_1_ = world.getBlockState(lvt_29_1_.setPos(lvt_28_1_).move(EnumFacing.UP));
										if (lvt_44_1_.getBlock() == KATHARIAN_GRASS) {
											lvt_39_1_ = true;
										}

										if (this.isTargetSafeFromFalling(lvt_44_1_, lvt_45_1_)) {
											if (lvt_40_1_ < 11) {
												world.setBlockState(lvt_28_1_, WATER_FLUID.getBlockState(), 2);
											} else {
												world.setBlockState(lvt_28_1_, DEFAULT_CAVE_AIR, 2);
												if (lvt_39_1_) {
													lvt_30_1_.setPos(lvt_28_1_).move(EnumFacing.DOWN);
													if (world.getBlockState(lvt_30_1_).getBlock() == KATHARIAN_DIRT) {
														IBlockState lvt_46_1_ = world.getBiome(lvt_28_1_).getSurfaceBuilderConfig().getTopMaterial();
														world.setBlockState(lvt_30_1_, lvt_46_1_, 2);
													}
												}
											}

											lvt_27_1_ = true;
										}
									}
								}
							}
						}
					}
				}

				return lvt_27_1_;
			}
		} else {
			return false;
		}
	}

}
