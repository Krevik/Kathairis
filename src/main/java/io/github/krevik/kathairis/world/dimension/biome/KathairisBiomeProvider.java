package io.github.krevik.kathairis.world.dimension.biome;

import com.google.common.collect.Sets;
import io.github.krevik.kathairis.world.dimension.KathairisGenSettings;
import io.github.krevik.kathairis.world.dimension.biome.gen.layers.KathairisLayerUtil;
import net.minecraft.init.Biomes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeCache;
import net.minecraft.world.biome.provider.OverworldBiomeProvider;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.storage.WorldInfo;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Set;

//TODO FIXME

/**
 * @author Krevik
 */
public class KathairisBiomeProvider extends OverworldBiomeProvider {

	private final BiomeCache cache = new BiomeCache(this);
	private final GenLayer genBiomes;
	private final GenLayer biomeFactoryLayer;
//    private final Biome[] biomes;

	public KathairisBiomeProvider(KathairisBiomeProviderSettings p_i48971_1_) {
		super(p_i48971_1_);
//        this.biomes = new Biome[]{Kathairis.BIOME_KATHARIAN_RIVER,Kathairis.BIOME_KATHARIAN_FOREST, Kathairis.BIOME_KATHARIAN_DESERT,
//        Kathairis.BIOME_KATHARIAN_PLAIN_FIELDS,Kathairis.BIOME_KATHARIAN_SWAMPS};
		WorldInfo lvt_2_1_ = p_i48971_1_.getWorldInfo();
		KathairisGenSettings lvt_3_1_ = p_i48971_1_.getGeneratorSettings();
		GenLayer[] lvt_4_1_ = KathairisLayerUtil.buildOverworldProcedure(lvt_2_1_.getSeed(), lvt_2_1_.getGenerator(), lvt_3_1_);
		this.genBiomes = lvt_4_1_[0];
		this.biomeFactoryLayer = lvt_4_1_[1];
	}

	@Override
	@Nullable
	public Biome getBiome(BlockPos p_180300_1_, @Nullable Biome p_180300_2_) {
		return this.cache.getBiome(p_180300_1_.getX(), p_180300_1_.getZ(), p_180300_2_);
	}

	@Override
	public Biome[] getBiomes(int p_201535_1_, int p_201535_2_, int p_201535_3_, int p_201535_4_) {
		return this.genBiomes.generateBiomes(p_201535_1_, p_201535_2_, p_201535_3_, p_201535_4_, Biomes.DEFAULT);
	}

	@Override
	public Biome[] getBiomes(int p_201537_1_, int p_201537_2_, int p_201537_3_, int p_201537_4_, boolean p_201537_5_) {
		return p_201537_5_ && p_201537_3_ == 16 && p_201537_4_ == 16 && (p_201537_1_ & 15) == 0 && (p_201537_2_ & 15) == 0 ? this.cache.getCachedBiomes(p_201537_1_, p_201537_2_) : this.biomeFactoryLayer.generateBiomes(p_201537_1_, p_201537_2_, p_201537_3_, p_201537_4_, Biomes.DEFAULT);
	}

	@Override
	public Set<Biome> getBiomesInSquare(int p_201538_1_, int p_201538_2_, int p_201538_3_) {
		int lvt_4_1_ = p_201538_1_ - p_201538_3_ >> 2;
		int lvt_5_1_ = p_201538_2_ - p_201538_3_ >> 2;
		int lvt_6_1_ = p_201538_1_ + p_201538_3_ >> 2;
		int lvt_7_1_ = p_201538_2_ + p_201538_3_ >> 2;
		int lvt_8_1_ = lvt_6_1_ - lvt_4_1_ + 1;
		int lvt_9_1_ = lvt_7_1_ - lvt_5_1_ + 1;
		Set<Biome> lvt_10_1_ = Sets.newHashSet();
		Collections.addAll(lvt_10_1_, this.genBiomes.generateBiomes(lvt_4_1_, lvt_5_1_, lvt_8_1_, lvt_9_1_, (Biome) null));
		return lvt_10_1_;
	}

	@Nullable
	@Override
	public BlockPos findBiomePosition(int p_180630_1_, int p_180630_2_, int p_180630_3_, List<Biome> p_180630_4_, Random p_180630_5_) {
		int lvt_6_1_ = p_180630_1_ - p_180630_3_ >> 2;
		int lvt_7_1_ = p_180630_2_ - p_180630_3_ >> 2;
		int lvt_8_1_ = p_180630_1_ + p_180630_3_ >> 2;
		int lvt_9_1_ = p_180630_2_ + p_180630_3_ >> 2;
		int lvt_10_1_ = lvt_8_1_ - lvt_6_1_ + 1;
		int lvt_11_1_ = lvt_9_1_ - lvt_7_1_ + 1;
		Biome[] lvt_12_1_ = this.genBiomes.generateBiomes(lvt_6_1_, lvt_7_1_, lvt_10_1_, lvt_11_1_, (Biome) null);
		BlockPos lvt_13_1_ = null;
		int lvt_14_1_ = 0;

		for (int lvt_15_1_ = 0; lvt_15_1_ < lvt_10_1_ * lvt_11_1_; ++lvt_15_1_) {
			int lvt_16_1_ = lvt_6_1_ + lvt_15_1_ % lvt_10_1_ << 2;
			int lvt_17_1_ = lvt_7_1_ + lvt_15_1_ / lvt_10_1_ << 2;
			if (p_180630_4_.contains(lvt_12_1_[lvt_15_1_])) {
				if (lvt_13_1_ == null || p_180630_5_.nextInt(lvt_14_1_ + 1) == 0) {
					lvt_13_1_ = new BlockPos(lvt_16_1_, 0, lvt_17_1_);
				}

				++lvt_14_1_;
			}
		}

		return lvt_13_1_;
	}

//    @Override
//    public boolean hasStructure(Structure<?> p_205004_1_) {
//        return (Boolean)this.hasStructureCache.computeIfAbsent(p_205004_1_, (p_205006_1_) -> {
//            Biome[] var2 = this.biomes;
//            int var3 = var2.length;
//
//            for(int var4 = 0; var4 < var3; ++var4) {
//                Biome lvt_5_1_ = var2[var4];
//                if (lvt_5_1_.hasStructure(p_205006_1_)) {
//                    return true;
//                }
//            }
//
//            return false;
//        });
//    }

//    @Override
//    public Set<IBlockState> getSurfaceBlocks() {
//        if (this.topBlocksCache.isEmpty()) {
//            Biome[] var1 = this.biomes;
//            int var2 = var1.length;
//
//            for(int var3 = 0; var3 < var2; ++var3) {
//                Biome lvt_4_1_ = var1[var3];
//                this.topBlocksCache.add(lvt_4_1_.getSurfaceBuilderConfig().getTop());
//            }
//        }
//
//        return this.topBlocksCache;
//    }

	@Override
	public void tick() {
		this.cache.cleanupCache();
	}

}
