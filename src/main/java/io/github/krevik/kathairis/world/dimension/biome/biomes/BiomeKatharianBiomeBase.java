package io.github.krevik.kathairis.world.dimension.biome.biomes;

import net.minecraft.client.Minecraft;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.awt.Color;
import java.util.ArrayList;

public class BiomeKatharianBiomeBase extends Biome {

	public Color baseGrassColor = new Color(66, 244, 238);

	protected BiomeKatharianBiomeBase(BiomeBuilder builder) {
		super(builder);
		//this.addFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS, createCompositeFeature(KatharianFeatureList.KATHARIAN_CRYSTAL_CHAMBER, IFeatureConfig.NO_FEATURE_CONFIG, COUNT_RANGE, new CountRangeConfig(1, 0, 0, 128)));
	}

	public static String convertColorToHexadeimal(Color color) {
		String hex = Integer.toHexString(color.getRGB() & 0xffffff);
		if (hex.length() < 6) {
			if (hex.length() == 5)
				hex = "0" + hex;
			if (hex.length() == 4)
				hex = "00" + hex;
			if (hex.length() == 3)
				hex = "000" + hex;
		}
		hex = hex;
		return hex;
	}

	@OnlyIn(Dist.CLIENT)
	public int getGrassColor(BlockPos pos) {
		World world = Minecraft.getInstance().world;
		ArrayList<BlockPos> toCalculate = new ArrayList<BlockPos>();
		int radiusCalculation = 6;
		for (int x = -radiusCalculation; x <= radiusCalculation; x++) {
			for (int z = -radiusCalculation; z <= radiusCalculation; z++) {
				toCalculate.add(new BlockPos(pos.getX() + x, pos.getY(), pos.getZ() + z));
			}
		}

		int sumR = 0;
		int sumG = 0;
		int sumB = 0;
		int count = 0;
		for (BlockPos positionForAverage : toCalculate) {
			if (world.getBiome(positionForAverage) instanceof BiomeKatharianBiomeBase) {
				BiomeKatharianBiomeBase biome = (BiomeKatharianBiomeBase) world.getBiome(positionForAverage);
				count++;
				sumR += biome.baseGrassColor.getRed();
				sumG += biome.baseGrassColor.getGreen();
				sumB += biome.baseGrassColor.getBlue();
			} else {
				return super.getGrassColor(positionForAverage);
			}
		}
		Color averageColor = new Color(sumR / count, sumG / count, sumB / count);

		return Integer.parseInt(convertColorToHexadeimal(averageColor), 16);
	}

}
