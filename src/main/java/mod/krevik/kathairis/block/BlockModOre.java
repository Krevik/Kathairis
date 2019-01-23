package mod.krevik.kathairis.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

/**
 * The same as Iron Ore or Gold Ore but for our stuff
 *
 * @author Cadiboo
 */
public class BlockModOre extends Block {

	public BlockModOre(final float hardness) {
		super(Material.ROCK);
		this.setHardness(hardness);
	}

}
