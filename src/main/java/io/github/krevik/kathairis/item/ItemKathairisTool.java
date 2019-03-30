package io.github.krevik.kathairis.item;

import net.minecraft.block.Block;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemTool;

import java.util.Set;

/**
 * @author Krevik
 */
public class ItemKathairisTool extends ItemTool {

	public ItemKathairisTool(IItemTier tier, Set<Block> effectiveBlocksIn, Properties properties) {
		super(tier.getAttackDamage(), 1, tier, effectiveBlocksIn, properties);
	}

}
