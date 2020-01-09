package io.github.krevik.kathairis.world.dimension;

import net.minecraft.world.gen.OverworldGenSettings;

/**
 * @author Krevik
 */
public class KathairisGenSettings extends OverworldGenSettings {

	private final int field_202212_j = 4;
	private final int field_202213_k = 4;
	private final int field_202214_l = -1;
	private final int field_202215_m = 63;

	public int getBiomeSize() {
		return 4;
	}

	public int getRiverSize() {
		return 4;
	}

	public int getBiomeId() {
		return -1;
	}

	public int getBedrockFloorHeight() {
		return 0;
	}

}
