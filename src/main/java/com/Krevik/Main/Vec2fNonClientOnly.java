package com.Krevik.Main;

public class Vec2fNonClientOnly
{
    /** An immutable vector with {@code 0.0F} as the x and y components. */
    public static final Vec2fNonClientOnly ZERO = new Vec2fNonClientOnly(0.0F, 0.0F);
    /** An immutable vector with {@code 1.0F} as the x and y components. */
    public static final Vec2fNonClientOnly ONE = new Vec2fNonClientOnly(1.0F, 1.0F);
    /** An immutable vector with {@code 1.0F} as the x component. */
    public static final Vec2fNonClientOnly UNIT_X = new Vec2fNonClientOnly(1.0F, 0.0F);
    /** An immutable vector with {@code -1.0F} as the x component. */
    public static final Vec2fNonClientOnly NEGATIVE_UNIT_X = new Vec2fNonClientOnly(-1.0F, 0.0F);
    /** An immutable vector with {@code 1.0F} as the y component. */
    public static final Vec2fNonClientOnly UNIT_Y = new Vec2fNonClientOnly(0.0F, 1.0F);
    /** An immutable vector with {@code -1.0F} as the y component. */
    public static final Vec2fNonClientOnly NEGATIVE_UNIT_Y = new Vec2fNonClientOnly(0.0F, -1.0F);
    /** An immutable vector with {@link Float#MAX_VALUE} as the x and y components. */
    public static final Vec2fNonClientOnly MAX = new Vec2fNonClientOnly(Float.MAX_VALUE, Float.MAX_VALUE);
    /** An immutable vector with {@link Float#MIN_VALUE} as the x and y components. */
    public static final Vec2fNonClientOnly MIN = new Vec2fNonClientOnly(Float.MIN_VALUE, Float.MIN_VALUE);
    /** The x component of this vector. */
    public final float x;
    /** The y component of this vector. */
    public final float y;

    public Vec2fNonClientOnly(float xIn, float yIn)
    {
        this.x = xIn;
        this.y = yIn;
    }
}