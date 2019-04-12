package io.github.krevik.kathairis.util;

import java.util.Random;

public class FunctionHelper {
    private Random random = new Random();

    public Random getRandom(){
        return random;
    }

    public float degToRad(float degrees)
    {
        return degrees * (float)Math.PI / 180 ;
    }
}
