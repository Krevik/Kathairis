package io.github.krevik.kathairis.init;

import net.minecraft.item.Food;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

public class ModFoods {
    public static final Food GOOSEBERRY = (new Food.Builder().hunger(2).saturation(0.4f)).build();
    public static final Food FRUP = (new Food.Builder().hunger(3).saturation(0.4f)).build();
    public static final Food MINERAL_FRUIT = (new Food.Builder().hunger(2).saturation(0.1f)).build();
    public static final Food HEART = (new Food.Builder().hunger(0).saturation(0.0f).setAlwaysEdible()).build();
    public static final Food MAGIC_BEANS = (new Food.Builder().hunger(2).saturation(0.4f).setAlwaysEdible()).build();
    public static final Food NECTAR_BOWL = (new Food.Builder().hunger(2).saturation(0.8f).setAlwaysEdible()).build();
    public static final Food FUNGAL_DRUG = (new Food.Builder().hunger(2).saturation(0.3f).setAlwaysEdible().effect(new EffectInstance(Effects.NAUSEA,100,2),1f)).build();

}
