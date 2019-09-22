package io.github.krevik.kathairis.particle;

import net.minecraft.client.particle.*;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TestParticle extends SpriteTexturedParticle {
    private final double portalPosX;
    private final double portalPosY;
    private final double portalPosZ;
    public TestParticle(World p_i46351_1_, double p_i46351_2_, double p_i46351_4_, double p_i46351_6_, double p_i46351_8_, double p_i46351_10_, double p_i46351_12_) {
        super(p_i46351_1_, p_i46351_2_, p_i46351_4_, p_i46351_6_);
        this.motionX = p_i46351_8_;
        this.motionY = p_i46351_10_;
        this.motionZ = p_i46351_12_;
        this.posX = p_i46351_2_;
        this.posY = p_i46351_4_;
        this.posZ = p_i46351_6_;
        this.portalPosX = this.posX;
        this.portalPosY = this.posY;
        this.portalPosZ = this.posZ;
        this.particleScale = 0.1F * (this.rand.nextFloat() * 0.2F + 0.5F);
        float lvt_14_1_ = this.rand.nextFloat() * 0.6F + 0.4F;
        this.particleRed = lvt_14_1_ * 0.9F;
        this.particleGreen = lvt_14_1_ * 0.3F;
        this.particleBlue = lvt_14_1_;
        this.maxAge = (int)(Math.random() * 10.0D) + 40;
    }

    public IParticleRenderType getRenderType() {
        return IParticleRenderType.PARTICLE_SHEET_OPAQUE;
    }

    public void move(double p_187110_1_, double p_187110_3_, double p_187110_5_) {
        this.setBoundingBox(this.getBoundingBox().offset(p_187110_1_, p_187110_3_, p_187110_5_));
        this.resetPositionToBB();
    }

    public float getScale(float p_217561_1_) {
        float lvt_2_1_ = ((float)this.age + p_217561_1_) / (float)this.maxAge;
        lvt_2_1_ = 1.0F - lvt_2_1_;
        lvt_2_1_ *= lvt_2_1_;
        lvt_2_1_ = 1.0F - lvt_2_1_;
        return this.particleScale * lvt_2_1_;
    }

    public int getBrightnessForRender(float p_189214_1_) {
        int lvt_2_1_ = super.getBrightnessForRender(p_189214_1_);
        float lvt_3_1_ = (float)this.age / (float)this.maxAge;
        lvt_3_1_ *= lvt_3_1_;
        lvt_3_1_ *= lvt_3_1_;
        int lvt_4_1_ = lvt_2_1_ & 255;
        int lvt_5_1_ = lvt_2_1_ >> 16 & 255;
        lvt_5_1_ += (int)(lvt_3_1_ * 15.0F * 16.0F);
        if (lvt_5_1_ > 240) {
            lvt_5_1_ = 240;
        }

        return lvt_4_1_ | lvt_5_1_ << 16;
    }

    public void tick() {
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;
        if (this.age++ >= this.maxAge) {
            this.setExpired();
        } else {
            float lvt_1_1_ = (float)this.age / (float)this.maxAge;
            float lvt_2_1_ = lvt_1_1_;
            lvt_1_1_ = -lvt_1_1_ + lvt_1_1_ * lvt_1_1_ * 2.0F;
            lvt_1_1_ = 1.0F - lvt_1_1_;
            this.posX = this.portalPosX + this.motionX * (double)lvt_1_1_;
            this.posY = this.portalPosY + this.motionY * (double)lvt_1_1_ + (double)(1.0F - lvt_2_1_);
            this.posZ = this.portalPosZ + this.motionZ * (double)lvt_1_1_;
        }
    }

    @OnlyIn(Dist.CLIENT)
    public static class Factory implements IParticleFactory<BasicParticleType> {
        private final IAnimatedSprite spriteSet;

        public Factory(IAnimatedSprite p_i50607_1_) {
            this.spriteSet = p_i50607_1_;
        }

        public Particle makeParticle(BasicParticleType p_199234_1_, World p_199234_2_, double p_199234_3_, double p_199234_5_, double p_199234_7_, double p_199234_9_, double p_199234_11_, double p_199234_13_) {
            TestParticle lvt_15_1_ = new TestParticle(p_199234_2_, p_199234_3_, p_199234_5_, p_199234_7_, p_199234_9_, p_199234_11_, p_199234_13_);
            lvt_15_1_.selectSpriteRandomly(this.spriteSet);
            return lvt_15_1_;
        }
    }
}
