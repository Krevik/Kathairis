package io.github.krevik.kathairis.world.dimension.biome.gen.layers;

import io.github.krevik.kathairis.init.ModBiomes;
import net.minecraft.util.registry.IRegistry;
import net.minecraft.world.gen.IContext;
import net.minecraft.world.gen.layer.traits.ICastleTransformer;

/**
 * @author Krevik
 */
public enum GenLayerKathairisRiver implements ICastleTransformer {
	INSTANCE;

	private GenLayerKathairisRiver() {

	}

	private static int riverFilter(int p_151630_0_) {
		return p_151630_0_ >= 2 ? 2 + (p_151630_0_ & 1) : p_151630_0_;
	}

	@Override
	public int apply(IContext context, int i0, int i1, int i2, int i3, int i4) {
		final int riverFilter = riverFilter(i4);
		if (riverFilter == riverFilter(i3) && riverFilter == riverFilter(i0) && riverFilter == riverFilter(i1) && riverFilter == riverFilter(i2)) {
			return -1;
		} else {
			//TODO wtf
			return IRegistry.BIOME.getId(ModBiomes.KATHARIS_RIVER);
		}
	}
}
