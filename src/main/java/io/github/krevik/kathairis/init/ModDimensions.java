package io.github.krevik.kathairis.init;

import io.github.krevik.kathairis.world.dimension.DimensionKathairis;
import io.github.krevik.kathairis.world.dimension.ModDimensionKathairis;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.registries.ObjectHolder;

import static io.github.krevik.kathairis.util.ModReference.MOD_ID;
import static io.github.krevik.kathairis.util.ModUtil._null;

/**
 * @author Cadiboo
 */
@ObjectHolder(MOD_ID)
public final class ModDimensions {

    public static final ModDimensionKathairis KATHAIRIS = _null();
    public static DimensionType KATH_DIM_TYPE = _null();
}
