package com.krevik.Particles;

import com.krevik.Main.KCore;
import net.minecraft.util.ResourceLocation;

public class TextureDefinition
{
    private String name;
    private ResourceLocation resourceLocation;
    private float uMin;
    private float vMin;
    private float uMax;
    private float vMax;
    private boolean tweenAnimationMode;
    private int animationFrameCount;
    private boolean shouldHaveCustomMovement;

    /**
     * Instantiates a new texture definition. It assumes that the image file
     * is at a file location in /textures/particles/ folder in your mod assets.
     *
     * @param parName the name
     */
    public TextureDefinition(String parName)
    {
        this(parName, 0.0F, 0.0F, 1.0F, 1.0F);
    }

    private TextureDefinition(String parName, float parUMin, float parVMin, float parUMax, float parVMax)
    {
        this(parName, parUMin, parVMin, parUMax, parVMax, false, 1);
    }

    /**
     * Instantiates a new texture definition. It assumes that the image file
     * is at a file location in /textures/particles/ folder in your mod assets.
     *
     * @param parName the name
     * @param parAnimMode the animation mode
     * @param parAnimFrames the animation frames
     */
    public TextureDefinition(String parName, boolean parAnimMode, int parAnimFrames,boolean shouldHaveAnotherWayOfMovement)
    {
        this(parName, 0.0F, 0.0F, 1.0F, 1.0F / parAnimFrames, parAnimMode, parAnimFrames);
        shouldHaveCustomMovement=shouldHaveAnotherWayOfMovement;
    }

    private TextureDefinition(String parName, float parUMin, float parVMin, float parUMax, float parVMax, boolean parAnimMode, int parAnimFrames)
    {
        name = parName;
        resourceLocation = new ResourceLocation(KCore.MODID, "textures/effect/"+name+".png");
        uMin = parUMin;
        vMin = parVMin;
        uMax = parUMax;
        vMax = parVMax;
        tweenAnimationMode = parAnimMode;
        animationFrameCount = parAnimFrames;
    }

    /*
     * Getter methods.
     */

    /**
     * Gets the name.
     *
     * @return the name
     */
    public String getName() { return name; }
    public boolean ShouldHaveCustomMovement() { return shouldHaveCustomMovement; }

    /**
     * Gets the resource location.
     *
     * @return the resource location
     */
    public ResourceLocation getResourceLocation() { return resourceLocation; }

    /**
     * Gets the u min.
     *
     * @return the u min
     */
    public float getUMin() { return uMin; }

    /**
     * Gets the v min.
     *
     * @return the v min
     */
    public float getVmin() { return vMin; }

    /**
     * Gets the u max.
     *
     * @return the u max
     */
    public float getUMax() { return uMax; }

    /**
     * Gets the v max.
     *
     * @return the v max
     */
    public float getVMax() { return vMax; }

    /**
     * Checks if is tween animation mode.
     *
     * @return true, if is tween animation mode
     */
    public boolean isTweenAnimationMode() { return tweenAnimationMode; }

    /**
     * Gets the animation frame count.
     *
     * @return the animation frame count
     */
    public int getAnimationFrameCount() { return animationFrameCount; }
}