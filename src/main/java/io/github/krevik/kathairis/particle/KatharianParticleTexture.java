package io.github.krevik.kathairis.particle;

import net.minecraft.util.ResourceLocation;

import static io.github.krevik.kathairis.util.ModReference.MOD_ID;
/**
 * @author Krevik
 * @credit Jabelar
 */
public class KatharianParticleTexture {

    private String name;
    private ResourceLocation resourceLocation;
    private float uMin;
    private float vMin;
    private float uMax;
    private float vMax;
    private boolean tweenAnimationMode;
    private int animationFrameCount;

    public KatharianParticleTexture(String name, boolean animMode, int framesCount)
    {
        this(name, 0.0F, 0.0F, 1.0F, 1.0F / framesCount, animMode, framesCount);
    }

    private KatharianParticleTexture(String name, float uMin, float vMin, float uMax, float vMax, boolean animMode, int framesCount)
    {
        this.name = name;
        resourceLocation = new ResourceLocation(MOD_ID, "textures/effect/"+ this.name +".png");
        this.uMin = uMin;
        this.vMin = vMin;
        this.uMax = uMax;
        this.vMax = vMax;
        tweenAnimationMode = animMode;
        animationFrameCount = framesCount;
    }
    public String getName() { return name; }

    public ResourceLocation getResourceLocation() { return resourceLocation; }

    public float getUMin() { return uMin; }

    public float getVmin() { return vMin; }

    public float getUMax() { return uMax; }

    public float getVMax() { return vMax; }

    public boolean isTweenAnimationMode() { return tweenAnimationMode; }

    public int getAnimationFrameCount() { return animationFrameCount; }
}
