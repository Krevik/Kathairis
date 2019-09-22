package io.github.krevik.kathairis.world.dimension.feature.carver;

import com.mojang.datafixers.Dynamic;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.carver.WorldCarver;
import net.minecraft.world.gen.feature.ProbabilityConfig;

import java.util.BitSet;
import java.util.Random;
import java.util.function.Function;

public class KatharianWorldCaveCarver extends WorldCarver<ProbabilityConfig> {
    public KatharianWorldCaveCarver(Function<Dynamic<?>, ? extends ProbabilityConfig> p_i49929_1_, int p_i49929_2_) {
        super(p_i49929_1_, p_i49929_2_);
    }

    public boolean shouldCarve(Random p_212868_1_, int p_212868_2_, int p_212868_3_, ProbabilityConfig p_212868_4_) {
        return p_212868_1_.nextFloat() <= p_212868_4_.probability;
    }

    public boolean carve(IChunk p_212867_1_, Random p_212867_2_, int p_212867_3_, int p_212867_4_, int p_212867_5_, int p_212867_6_, int p_212867_7_, BitSet p_212867_8_, ProbabilityConfig p_212867_9_) {
        int lvt_10_1_ = (this.func_222704_c() * 2 - 1) * 16;
        int lvt_11_1_ = p_212867_2_.nextInt(p_212867_2_.nextInt(p_212867_2_.nextInt(this.func_222724_a()) + 1) + 1);

        for(int lvt_12_1_ = 0; lvt_12_1_ < lvt_11_1_; ++lvt_12_1_) {
            double lvt_13_1_ = (double)(p_212867_4_ * 16 + p_212867_2_.nextInt(16));
            double lvt_15_1_ = (double)this.generateCaveStartY(p_212867_2_);
            double lvt_17_1_ = (double)(p_212867_5_ * 16 + p_212867_2_.nextInt(16));
            int lvt_19_1_ = 1;
            float lvt_22_2_;
            if (p_212867_2_.nextInt(4) == 0) {
                double lvt_20_1_ = 0.5D;
                lvt_22_2_ = 1.0F + p_212867_2_.nextFloat() * 6.0F;
                this.func_222723_a(p_212867_1_, p_212867_2_.nextLong(), p_212867_3_, p_212867_6_, p_212867_7_, lvt_13_1_, lvt_15_1_, lvt_17_1_, lvt_22_2_, 0.5D, p_212867_8_);
                lvt_19_1_ += p_212867_2_.nextInt(4);
            }

            for(int lvt_20_2_ = 0; lvt_20_2_ < lvt_19_1_; ++lvt_20_2_) {
                float lvt_21_1_ = p_212867_2_.nextFloat() * 6.2831855F;
                lvt_22_2_ = (p_212867_2_.nextFloat() - 0.5F) / 4.0F;
                float lvt_23_1_ = this.generateCaveRadius(p_212867_2_);
                int lvt_24_1_ = lvt_10_1_ - p_212867_2_.nextInt(lvt_10_1_ / 4);
                this.carveTunnel(p_212867_1_, p_212867_2_.nextLong(), p_212867_3_, p_212867_6_, p_212867_7_, lvt_13_1_, lvt_15_1_, lvt_17_1_, lvt_23_1_, lvt_21_1_, lvt_22_2_, 0, lvt_24_1_, this.func_222725_b(), p_212867_8_);
            }
        }

        return true;
    }

    protected int func_222724_a() {
        return 15;
    }

    protected float generateCaveRadius(Random p_222722_1_) {
        float lvt_2_1_ = p_222722_1_.nextFloat() * 2.0F + p_222722_1_.nextFloat();
        if (p_222722_1_.nextInt(10) == 0) {
            lvt_2_1_ *= p_222722_1_.nextFloat() * p_222722_1_.nextFloat() * 3.0F + 1.0F;
        }

        return lvt_2_1_;
    }

    protected double func_222725_b() {
        return 1.0D;
    }

    protected int generateCaveStartY(Random p_222726_1_) {
        return p_222726_1_.nextInt(p_222726_1_.nextInt(120) + 8);
    }

    protected void func_222723_a(IChunk p_222723_1_, long p_222723_2_, int p_222723_4_, int p_222723_5_, int p_222723_6_, double p_222723_7_, double p_222723_9_, double p_222723_11_, float p_222723_13_, double p_222723_14_, BitSet p_222723_16_) {
        double lvt_17_1_ = 1.5D + (double)(MathHelper.sin(1.5707964F) * p_222723_13_);
        double lvt_19_1_ = lvt_17_1_ * p_222723_14_;
        this.func_222705_a(p_222723_1_, p_222723_2_, p_222723_4_, p_222723_5_, p_222723_6_, p_222723_7_ + 1.0D, p_222723_9_, p_222723_11_, lvt_17_1_, lvt_19_1_, p_222723_16_);
    }

    protected void carveTunnel(IChunk p_222727_1_, long p_222727_2_, int p_222727_4_, int p_222727_5_, int p_222727_6_, double p_222727_7_, double p_222727_9_, double p_222727_11_, float p_222727_13_, float p_222727_14_, float p_222727_15_, int p_222727_16_, int p_222727_17_, double p_222727_18_, BitSet p_222727_20_) {
        Random lvt_21_1_ = new Random(p_222727_2_);
        int lvt_22_1_ = lvt_21_1_.nextInt(p_222727_17_ / 2) + p_222727_17_ / 4;
        boolean lvt_23_1_ = lvt_21_1_.nextInt(6) == 0;
        float lvt_24_1_ = 0.0F;
        float lvt_25_1_ = 0.0F;

        for(int lvt_26_1_ = p_222727_16_; lvt_26_1_ < p_222727_17_; ++lvt_26_1_) {
            double lvt_27_1_ = 1.5D + (double)(MathHelper.sin(3.1415927F * (float)lvt_26_1_ / (float)p_222727_17_) * p_222727_13_);
            double lvt_29_1_ = lvt_27_1_ * p_222727_18_;
            float lvt_31_1_ = MathHelper.cos(p_222727_15_);
            p_222727_7_ += (double)(MathHelper.cos(p_222727_14_) * lvt_31_1_);
            p_222727_9_ += (double)MathHelper.sin(p_222727_15_);
            p_222727_11_ += (double)(MathHelper.sin(p_222727_14_) * lvt_31_1_);
            p_222727_15_ *= lvt_23_1_ ? 0.92F : 0.7F;
            p_222727_15_ += lvt_25_1_ * 0.1F;
            p_222727_14_ += lvt_24_1_ * 0.1F;
            lvt_25_1_ *= 0.9F;
            lvt_24_1_ *= 0.75F;
            lvt_25_1_ += (lvt_21_1_.nextFloat() - lvt_21_1_.nextFloat()) * lvt_21_1_.nextFloat() * 2.0F;
            lvt_24_1_ += (lvt_21_1_.nextFloat() - lvt_21_1_.nextFloat()) * lvt_21_1_.nextFloat() * 4.0F;
            if (lvt_26_1_ == lvt_22_1_ && p_222727_13_ > 1.0F) {
                this.carveTunnel(p_222727_1_, lvt_21_1_.nextLong(), p_222727_4_, p_222727_5_, p_222727_6_, p_222727_7_, p_222727_9_, p_222727_11_, lvt_21_1_.nextFloat() * 0.5F + 0.5F, p_222727_14_ - 1.5707964F, p_222727_15_ / 3.0F, lvt_26_1_, p_222727_17_, 1.0D, p_222727_20_);
                this.carveTunnel(p_222727_1_, lvt_21_1_.nextLong(), p_222727_4_, p_222727_5_, p_222727_6_, p_222727_7_, p_222727_9_, p_222727_11_, lvt_21_1_.nextFloat() * 0.5F + 0.5F, p_222727_14_ + 1.5707964F, p_222727_15_ / 3.0F, lvt_26_1_, p_222727_17_, 1.0D, p_222727_20_);
                return;
            }

            if (lvt_21_1_.nextInt(4) != 0) {
                if (!this.func_222702_a(p_222727_5_, p_222727_6_, p_222727_7_, p_222727_11_, lvt_26_1_, p_222727_17_, p_222727_13_)) {
                    return;
                }

                this.func_222705_a(p_222727_1_, p_222727_2_, p_222727_4_, p_222727_5_, p_222727_6_, p_222727_7_, p_222727_9_, p_222727_11_, lvt_27_1_, lvt_29_1_, p_222727_20_);
            }
        }

    }

    protected boolean func_222708_a(double p_222708_1_, double p_222708_3_, double p_222708_5_, int p_222708_7_) {
        return p_222708_3_ <= -0.7D || p_222708_1_ * p_222708_1_ + p_222708_3_ * p_222708_3_ + p_222708_5_ * p_222708_5_ >= 1.0D;
    }
}
