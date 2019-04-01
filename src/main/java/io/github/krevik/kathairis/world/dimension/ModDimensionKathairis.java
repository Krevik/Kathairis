package io.github.krevik.kathairis.world.dimension;

import com.mojang.datafixers.FunctionType;
import io.github.krevik.kathairis.Kathairis;
import io.github.krevik.kathairis.init.ModDimensions;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.dimension.Dimension;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.dimension.OverworldDimension;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.ModDimension;

import javax.annotation.Nonnull;
import java.nio.ByteBuffer;
import java.util.function.Function;

import static io.github.krevik.kathairis.util.ModReference.MOD_ID;

/**
 * @author Krevik, Cadiboo
 */
public class ModDimensionKathairis extends ModDimension {

	// Registry name in constructor is ew but apparently necessary.
	public ModDimensionKathairis(final ResourceLocation registryName) {
		this.setRegistryName(registryName);

	}

	@Override
	public Function<DimensionType, ? extends Dimension> getFactory() {
			return DimensionKathairis::new;
	}



}
