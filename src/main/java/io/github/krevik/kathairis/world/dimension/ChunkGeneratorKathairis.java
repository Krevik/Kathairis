package io.github.krevik.kathairis.world.dimension;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.*;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.chunk.ChunkStatus;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.*;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.structure.SwampHutStructure;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * @author Krevik
 */
public class ChunkGeneratorKathairis extends AbstractChunkGenerator<OverworldGenSettings> {

	private static final Logger LOGGER = LogManager.getLogger();
	private final OverworldGenSettings settings;
	private final WorldType terrainType;
	private final float[] biomeWeights;
	private final PhantomSpawner phantomSpawner = new PhantomSpawner();
	private final IBlockState defaultBlock;
	private final IBlockState defaultFluid;
	private NoiseGeneratorOctaves minLimitPerlinNoise;
	private NoiseGeneratorOctaves maxLimitPerlinNoise;
	private NoiseGeneratorOctaves mainPerlinNoise;
	private NoiseGeneratorPerlin surfaceNoise;
	private NoiseGeneratorOctaves scaleNoise;
	private NoiseGeneratorOctaves depthNoise;
	private NoiseGeneratorSimplex islandNoise;


	public ChunkGeneratorKathairis(IWorld worldIn, BiomeProvider provider, OverworldGenSettings settingsIn) {
		super(worldIn, provider);
		this.terrainType = worldIn.getWorldInfo().getGenerator();
		SharedSeedRandom sharedseedrandom = new SharedSeedRandom(this.seed);
		this.minLimitPerlinNoise = new NoiseGeneratorOctaves(sharedseedrandom, 16);
		this.maxLimitPerlinNoise = new NoiseGeneratorOctaves(sharedseedrandom, 16);
		this.mainPerlinNoise = new NoiseGeneratorOctaves(sharedseedrandom, 8);
		this.surfaceNoise = new NoiseGeneratorPerlin(sharedseedrandom, 4);
		this.scaleNoise = new NoiseGeneratorOctaves(sharedseedrandom, 10);
		this.depthNoise = new NoiseGeneratorOctaves(sharedseedrandom, 16);
		this.biomeWeights = new float[25];
		this.islandNoise = new NoiseGeneratorSimplex(sharedseedrandom);

		for (int i = -2; i <= 2; ++i) {
			for (int j = -2; j <= 2; ++j) {
				float f = 10.0F / MathHelper.sqrt((float) (i * i + j * j) + 0.2F);
				this.biomeWeights[i + 2 + (j + 2) * 5] = f;
			}
		}

		this.settings = settingsIn;
		this.defaultBlock = this.settings.getDefaultBlock();
		this.defaultFluid = this.settings.getDefaultFluid();

		net.minecraftforge.event.terraingen.InitNoiseGensEvent.ContextOverworld ctx =
				new net.minecraftforge.event.terraingen.InitNoiseGensEvent.ContextOverworld(minLimitPerlinNoise, maxLimitPerlinNoise, mainPerlinNoise, surfaceNoise, scaleNoise, depthNoise);
		ctx = net.minecraftforge.event.terraingen.TerrainGen.getModdedNoiseGenerators(worldIn, sharedseedrandom, ctx);
		this.minLimitPerlinNoise = ctx.getLPerlin1();
		this.maxLimitPerlinNoise = ctx.getLPerlin2();
		this.mainPerlinNoise = ctx.getPerlin();
		this.surfaceNoise = ctx.getHeight();
		this.scaleNoise = ctx.getScale();
		this.depthNoise = ctx.getDepth();
	}


	public void makeBase(IChunk chunkIn) {
		ChunkPos chunkpos = chunkIn.getPos();
		int i = chunkpos.x;
		int j = chunkpos.z;
		SharedSeedRandom sharedseedrandom = new SharedSeedRandom();
		sharedseedrandom.setBaseChunkSeed(i, j);
		Biome[] abiome = this.biomeProvider.getBiomeBlock(i * 16, j * 16, 16, 16);
		this.setBlocksInChunkIsland(i,j,chunkIn);
		chunkIn.setBiomes(abiome);
		this.setBlocksInChunk(i, j, chunkIn);
		chunkIn.createHeightMap(Heightmap.Type.WORLD_SURFACE_WG, Heightmap.Type.OCEAN_FLOOR_WG);
		this.buildSurface(chunkIn, abiome, sharedseedrandom, this.world.getSeaLevel());
		this.makeBedrock(chunkIn, sharedseedrandom);
		chunkIn.createHeightMap(Heightmap.Type.WORLD_SURFACE_WG, Heightmap.Type.OCEAN_FLOOR_WG);
		chunkIn.setStatus(ChunkStatus.BASE);
	}

	public void spawnMobs(WorldGenRegion region) {
		int i = region.getMainChunkX();
		int j = region.getMainChunkZ();
		Biome biome = region.getChunk(i, j).getBiomes()[0];
		SharedSeedRandom sharedseedrandom = new SharedSeedRandom();
		sharedseedrandom.setDecorationSeed(region.getSeed(), i << 4, j << 4);
		WorldEntitySpawner.performWorldGenSpawning(region, biome, i, j, sharedseedrandom);
	}

	public List<Biome.SpawnListEntry> getPossibleCreatures(EnumCreatureType creatureType, BlockPos pos) {
		Biome biome = this.world.getBiome(pos);
		if (creatureType == EnumCreatureType.MONSTER && ((SwampHutStructure) Feature.SWAMP_HUT).func_202383_b(this.world, pos)) {
			return Feature.SWAMP_HUT.getSpawnList();
		} else {
			return creatureType == EnumCreatureType.MONSTER && Feature.OCEAN_MONUMENT.isPositionInStructure(this.world, pos) ? Feature.OCEAN_MONUMENT.getSpawnList() : biome.getSpawns(creatureType);
		}
	}

	public int spawnMobs(World worldIn, boolean spawnHostileMobs, boolean spawnPeacefulMobs) {
		int i = 0;
		i = i + this.phantomSpawner.spawnMobs(worldIn, spawnHostileMobs, spawnPeacefulMobs);
		return i;
	}

	public int getGroundHeight() {
		return this.world.getSeaLevel() + 1;
	}

	public void setBlocksInChunk(int x, int z, IChunk primer) {
		Biome[] abiome = this.biomeProvider.getBiomes(primer.getPos().x * 4 - 2, primer.getPos().z * 4 - 2, 10, 10);
		double[] adouble = new double[825];
		this.generateHeightMap(abiome, primer.getPos().x * 4, 0, primer.getPos().z * 4, adouble);
		BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

		for (int i = 0; i < 4; ++i) {
			int j = i * 5;
			int k = (i + 1) * 5;

			for (int l = 0; l < 4; ++l) {
				int i1 = (j + l) * 33;
				int j1 = (j + l + 1) * 33;
				int k1 = (k + l) * 33;
				int l1 = (k + l + 1) * 33;

				for (int i2 = 0; i2 < 32; ++i2) {
					double d0 = 0.125D;
					double d1 = adouble[i1 + i2];
					double d2 = adouble[j1 + i2];
					double d3 = adouble[k1 + i2];
					double d4 = adouble[l1 + i2];
					double d5 = (adouble[i1 + i2 + 1] - d1) * 0.125D;
					double d6 = (adouble[j1 + i2 + 1] - d2) * 0.125D;
					double d7 = (adouble[k1 + i2 + 1] - d3) * 0.125D;
					double d8 = (adouble[l1 + i2 + 1] - d4) * 0.125D;

					for (int j2 = 0; j2 < 8; ++j2) {
						double d9 = 0.25D;
						double d10 = d1;
						double d11 = d2;
						double d12 = (d3 - d1) * 0.25D;
						double d13 = (d4 - d2) * 0.25D;

						for (int k2 = 0; k2 < 4; ++k2) {
							double d14 = 0.25D;
							double d16 = (d11 - d10) * 0.25D;
							double lvt_48_1_ = d10 - d16;

							for (int l2 = 0; l2 < 4; ++l2) {
								blockpos$mutableblockpos.setPos(i * 4 + k2, i2 * 8 + j2, l * 4 + l2);
								if ((lvt_48_1_ += d16) > 0.0D) {
									primer.setBlockState(blockpos$mutableblockpos, this.defaultBlock, false);
								} else if (i2 * 8 + j2 < this.settings.getSeaLevel()) {
									primer.setBlockState(blockpos$mutableblockpos, this.defaultFluid, false);
								}
							}

							d10 += d12;
							d11 += d13;
						}

						d1 += d5;
						d2 += d6;
						d3 += d7;
						d4 += d8;
					}
				}
			}
		}

	}

	private void generateHeightMap(Biome[] p_202108_1_, int x, int y, int z, double[] p_202108_5_) {
		double[] adouble = this.depthNoise.func_202646_a(x, z, 5, 5, this.settings.getDepthNoiseScaleX(), this.settings.getDepthNoiseScaleZ(), this.settings.getDepthNoiseScaleExponent());
		float f = this.settings.getCoordinateScale();
		float f1 = this.settings.getHeightScale();
		double[] adouble1 = this.minLimitPerlinNoise.func_202647_a(x, y, z, 5, 33, 5, (double) (f / this.settings.getMainNoiseScaleX()), (double) (f1 / this.settings.getMainNoiseScaleY()), (double) (f / this.settings.getMainNoiseScaleZ()));
		double[] adouble2 = this.maxLimitPerlinNoise.func_202647_a(x, y, z, 5, 33, 5, (double) f, (double) f1, (double) f);
		double[] adouble3 = this.mainPerlinNoise.func_202647_a(x, y, z, 5, 33, 5, (double) f, (double) f1, (double) f);
		int i = 0;
		int j = 0;

		for (int k = 0; k < 5; ++k) {
			for (int l = 0; l < 5; ++l) {
				float f2 = 0.0F;
				float f3 = 0.0F;
				float f4 = 0.0F;
				int i1 = 2;
				Biome biome = p_202108_1_[k + 2 + (l + 2) * 10];

				for (int j1 = -2; j1 <= 2; ++j1) {
					for (int k1 = -2; k1 <= 2; ++k1) {
						Biome biome1 = p_202108_1_[k + j1 + 2 + (l + k1 + 2) * 10];
						float f5 = this.settings.func_202203_v() + biome1.getDepth() * this.settings.func_202202_w();
						float f6 = this.settings.func_202204_x() + biome1.getScale() * this.settings.func_202205_y();
						if (this.terrainType == WorldType.AMPLIFIED && f5 > 0.0F) {
							f5 = 1.0F + f5 * 2.0F;
							f6 = 1.0F + f6 * 4.0F;
						}

						float f7 = this.biomeWeights[j1 + 2 + (k1 + 2) * 5] / (f5 + 2.0F);
						if (biome1.getDepth() > biome.getDepth()) {
							f7 /= 2.0F;
						}

						f2 += f6 * f7;
						f3 += f5 * f7;
						f4 += f7;
					}
				}

				f2 = f2 / f4;
				f3 = f3 / f4;
				f2 = f2 * 0.9F + 0.1F;
				f3 = (f3 * 4.0F - 1.0F) / 8.0F;
				double d7 = adouble[j] / 8000.0D;
				if (d7 < 0.0D) {
					d7 = -d7 * 0.3D;
				}

				d7 = d7 * 3.0D - 2.0D;
				if (d7 < 0.0D) {
					d7 = d7 / 2.0D;
					if (d7 < -1.0D) {
						d7 = -1.0D;
					}

					d7 = d7 / 1.4D;
					d7 = d7 / 2.0D;
				} else {
					if (d7 > 1.0D) {
						d7 = 1.0D;
					}

					d7 = d7 / 8.0D;
				}

				++j;
				double d8 = (double) f3;
				double d9 = (double) f2;
				d8 = d8 + d7 * 0.2D;
				d8 = d8 * this.settings.func_202201_z() / 8.0D;
				double d0 = this.settings.func_202201_z() + d8 * 4.0D;

				for (int l1 = 0; l1 < 33; ++l1) {
					double d1 = ((double) l1 - d0) * this.settings.func_202206_A() * 128.0D / 256.0D / d9;
					if (d1 < 0.0D) {
						d1 *= 4.0D;
					}

					double d2 = adouble2[i] / this.settings.getLowerLimitScale();
					double d3 = adouble3[i] / this.settings.getUpperLimitScale();
					double d4 = (adouble1[i] / 10.0D + 1.0D) / 2.0D;
					double d5 = MathHelper.clampedLerp(d2, d3, d4) - d1;
					if (l1 > 29) {
						double d6 = (double) ((float) (l1 - 29) / 3.0F);
						d5 = d5 * (1.0D - d6) - 10.0D * d6;
					}

					p_202108_5_[i] = d5;
					++i;
				}
			}
		}
	}

	public void setBlocksInChunkIsland(int p_202114_1_, int p_202114_2_, IChunk p_202114_3_) {
		int i = 2;
		int j = 3;
		int k = 33;
		int l = 3;
		double[] adouble = this.func_202113_a(p_202114_1_ * 2, 0, p_202114_2_ * 2, 3, 33, 3);
		BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();
		int upperScale=130;
		for(int i1 = 0; i1 < 2; ++i1) {
			for(int j1 = 0; j1 < 2; ++j1) {
				for(int k1 = 0; k1 < 32; ++k1) {
					double d0 = 0.25D;
					double d1 = adouble[((i1 + 0) * 3 + j1 + 0) * 33 + k1 + 0];
					double d2 = adouble[((i1 + 0) * 3 + j1 + 1) * 33 + k1 + 0];
					double d3 = adouble[((i1 + 1) * 3 + j1 + 0) * 33 + k1 + 0];
					double d4 = adouble[((i1 + 1) * 3 + j1 + 1) * 33 + k1 + 0];
					double d5 = (adouble[((i1 + 0) * 3 + j1 + 0) * 33 + k1 + 1] - d1) * 0.25D;
					double d6 = (adouble[((i1 + 0) * 3 + j1 + 1) * 33 + k1 + 1] - d2) * 0.25D;
					double d7 = (adouble[((i1 + 1) * 3 + j1 + 0) * 33 + k1 + 1] - d3) * 0.25D;
					double d8 = (adouble[((i1 + 1) * 3 + j1 + 1) * 33 + k1 + 1] - d4) * 0.25D;

					for(int l1 = 0; l1 < 4; ++l1) {
						double d9 = 0.125D;
						double d10 = d1;
						double d11 = d2;
						double d12 = (d3 - d1) * 0.125D;
						double d13 = (d4 - d2) * 0.125D;

						for(int i2 = 0; i2 < 8; ++i2) {
							double d14 = 0.125D;
							double d15 = d10;
							double d16 = (d11 - d10) * 0.125D;

							for(int j2 = 0; j2 < 8; ++j2) {
								IBlockState iblockstate = Blocks.AIR.getDefaultState();
								if (d15 > 0.0D) {
									iblockstate = this.defaultBlock;
								}

								int k2 = i2 + i1 * 8;
								int l2 = l1 + k1 * 4;
								int i3 = j2 + j1 * 8;
								p_202114_3_.setBlockState(blockpos$mutableblockpos.setPos(k2, l2+upperScale, i3), iblockstate, false);
								if(iblockstate!=Blocks.AIR.getDefaultState()){

								}
								d15 += d16;
							}

							d10 += d12;
							d11 += d13;
						}

						d1 += d5;
						d2 += d6;
						d3 += d7;
						d4 += d8;
					}
				}
			}
		}

	}

	private double[] func_202113_a(int p_202113_1_, int p_202113_2_, int p_202113_3_, int p_202113_4_, int p_202113_5_, int p_202113_6_) {
		double[] adouble = new double[p_202113_4_ * p_202113_5_ * p_202113_6_];
		double d0 = 684.412D;
		double d1 = 684.412D;
		d0 = d0 * 2.0D;
		double[] adouble1 = this.mainPerlinNoise.func_202647_a(p_202113_1_, p_202113_2_, p_202113_3_, p_202113_4_, p_202113_5_, p_202113_6_, d0 / 80.0D, 4.277575000000001D, d0 / 80.0D);
		double[] adouble2 = this.minLimitPerlinNoise.func_202647_a(p_202113_1_, p_202113_2_, p_202113_3_, p_202113_4_, p_202113_5_, p_202113_6_, d0, 684.412D, d0);
		double[] adouble3 = this.maxLimitPerlinNoise.func_202647_a(p_202113_1_, p_202113_2_, p_202113_3_, p_202113_4_, p_202113_5_, p_202113_6_, d0, 684.412D, d0);
		int i = p_202113_1_ / 2;
		int j = p_202113_3_ / 2;
		int k = 0;

		for(int l = 0; l < p_202113_4_; ++l) {
			for(int i1 = 0; i1 < p_202113_6_; ++i1) {
				float f = this.getIslandHeightValue(i, j, l, i1);

				for(int j1 = 0; j1 < p_202113_5_; ++j1) {
					double d2 = adouble2[k] / 512.0D;
					double d3 = adouble3[k] / 512.0D;
					double d5 = (adouble1[k] / 10.0D + 1.0D) / 2.0D;
					double d4;
					if (d5 < 0.0D) {
						d4 = d2;
					} else if (d5 > 1.0D) {
						d4 = d3;
					} else {
						d4 = d2 + (d3 - d2) * d5;
					}

					d4 = d4 - 8.0D;
					d4 = d4 + (double)f;
					int k1 = 2;
					if (j1 > p_202113_5_ / 2 - k1) {
						double d6 = (double)((float)(j1 - (p_202113_5_ / 2 - k1)) / 64.0F);
						d6 = MathHelper.clamp(d6, 0.0D, 1.0D);
						d4 = d4 * (1.0D - d6) - 3000.0D * d6;
					}

					k1 = 8;
					if (j1 < k1) {
						double d7 = (double)((float)(k1 - j1) / ((float)k1 - 1.0F));
						d4 = d4 * (1.0D - d7) - 30.0D * d7;
					}

					adouble[k] = d4;
					++k;
				}
			}
		}

		return adouble;
	}

	private float getIslandHeightValue(int p_185960_1_, int p_185960_2_, int p_185960_3_, int p_185960_4_)
	{
		float f = (float)(p_185960_1_ * 2 + p_185960_3_);
		float f1 = (float)(p_185960_2_ * 2 + p_185960_4_);
		float f2 = 100.0F - MathHelper.sqrt(f * f + f1 * f1) * 8.0F;

		if (f2 > 80.0F)
		{
			f2 = 80.0F;
		}

		if (f2 < -100.0F)
		{
			f2 = -100.0F;
		}

		for (int i = -12; i <= 12; ++i)
		{
			for (int j = -12; j <= 12; ++j)
			{
				long k = (long)(p_185960_1_ + i);
				long l = (long)(p_185960_2_ + j);

				if (k*k+l*l>1000L&&this.islandNoise.getValue((double)k, (double)l) < -0.9899999761581421D)
				{
					float f3 = (MathHelper.abs((float)k) * 3439.0F + MathHelper.abs((float)l) * 147.0F) % 13.0F + 9.0F;
					f = (float)(p_185960_3_ - i * 2);
					f1 = (float)(p_185960_4_ - j * 2);
					float f4 = 100.0F - MathHelper.sqrt(f * f + f1 * f1) * f3;

					if (f4 > 80.0F)
					{
						f4 = 80.0F;
					}

					if (f4 < -100.0F)
					{
						f4 = -100.0F;
					}

					if (f4 > f2)
					{
						f2 = f4;
					}
				}
			}
		}

		return f2;
	}



	public OverworldGenSettings getSettings() {
		return this.settings;
	}

	public double[] generateNoiseRegion(int x, int z) {
		double d0 = 0.03125D;
		return this.surfaceNoise.generateRegion((double) (x << 4), (double) (z << 4), 16, 16, 0.0625D, 0.0625D, 1.0D);
	}

}
