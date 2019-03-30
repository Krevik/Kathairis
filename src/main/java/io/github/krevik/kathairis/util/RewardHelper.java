package io.github.krevik.kathairis.util;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.Random;

import static io.github.krevik.kathairis.init.ModItems.BISON_MEAT;
import static io.github.krevik.kathairis.init.ModItems.CLOUD_ESSENCE;
import static io.github.krevik.kathairis.init.ModItems.CLOUD_PEARL;
import static io.github.krevik.kathairis.init.ModItems.CRYSTAL_SHARD_BLUE;
import static io.github.krevik.kathairis.init.ModItems.CRYSTAL_SHARD_VIOLET;
import static io.github.krevik.kathairis.init.ModItems.CRYSTAL_SHARD_YELLOW;
import static io.github.krevik.kathairis.init.ModItems.FRUP;
import static io.github.krevik.kathairis.init.ModItems.GOOSEBERRIES;
import static io.github.krevik.kathairis.init.ModItems.HEART;
import static io.github.krevik.kathairis.init.ModItems.MAGIC_BEANS;
import static io.github.krevik.kathairis.init.ModItems.MAGNETHIUM_SHARD;
import static io.github.krevik.kathairis.init.ModItems.MYSTIC_GEM;
import static io.github.krevik.kathairis.init.ModItems.REVENUM_INGOT;
import static io.github.krevik.kathairis.init.ModItems.TITANIUM_INGOT;

/**
 * @author Krevik
 */
public class RewardHelper {

	private static ArrayList<ItemStack> floatingMiniIslandRewardsList = new ArrayList<>();

	private static void initFloatingMiniIslandRewardsList(Random random) {
		floatingMiniIslandRewardsList.clear();
		floatingMiniIslandRewardsList.add(new ItemStack(CLOUD_PEARL, 1 + random.nextInt(4)));
		floatingMiniIslandRewardsList.add(new ItemStack(CLOUD_ESSENCE, 1 + random.nextInt(4)));
		floatingMiniIslandRewardsList.add(new ItemStack(HEART, 1 + random.nextInt(4)));
		floatingMiniIslandRewardsList.add(new ItemStack(REVENUM_INGOT, 6 + random.nextInt(16)));
		floatingMiniIslandRewardsList.add(new ItemStack(MYSTIC_GEM, 2 + random.nextInt(16)));
		floatingMiniIslandRewardsList.add(new ItemStack(TITANIUM_INGOT, 4 + random.nextInt(12)));
		floatingMiniIslandRewardsList.add(new ItemStack(MAGIC_BEANS, 4 + random.nextInt(12)));
		floatingMiniIslandRewardsList.add(new ItemStack(FRUP, 4 + random.nextInt(12)));
		floatingMiniIslandRewardsList.add(new ItemStack(GOOSEBERRIES, 4 + random.nextInt(12)));
		floatingMiniIslandRewardsList.add(new ItemStack(BISON_MEAT, 4 + random.nextInt(12)));
		floatingMiniIslandRewardsList.add(new ItemStack(CRYSTAL_SHARD_BLUE, 4 + random.nextInt(12)));
		floatingMiniIslandRewardsList.add(new ItemStack(CRYSTAL_SHARD_YELLOW, 4 + random.nextInt(12)));
		floatingMiniIslandRewardsList.add(new ItemStack(CRYSTAL_SHARD_VIOLET, 4 + random.nextInt(12)));
		floatingMiniIslandRewardsList.add(new ItemStack(Items.DIAMOND, 4 + random.nextInt(12)));
		floatingMiniIslandRewardsList.add(new ItemStack(MAGNETHIUM_SHARD, 4 + random.nextInt(12)));
	}

	public static ArrayList<ItemStack> getFloatingMiniIslandRewards(Random random) {
		initFloatingMiniIslandRewardsList(random);
		ArrayList<ItemStack> rewards = new ArrayList<>();
		for (int x = 1; x <= 2 + random.nextInt(3); x++) {
			rewards.add(floatingMiniIslandRewardsList.get(random.nextInt(floatingMiniIslandRewardsList.size())));
			initFloatingMiniIslandRewardsList(random);
		}
		return rewards;
	}

}
