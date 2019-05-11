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

    public int getRandomInteger(int min, int max) {
        return min+random.nextInt(((max+1)-min));
    }

    public int getRandomInteger(long Seed, int min, int max) {
        int result=0;
        Random rand = new Random(Seed);
        result=min+rand.nextInt(((max+1)-min));
        return result;
    }
}
