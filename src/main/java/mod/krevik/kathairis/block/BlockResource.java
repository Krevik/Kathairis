package mod.krevik.kathairis.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

/**
 * The same as a block of Iron or a block of Gold but for our stuff
 *
 * @author Cadiboo
 */
public class BlockResource extends Block {

	public BlockResource(final float hardness) {
		super(Material.IRON);
		this.setHardness(hardness);
	}

}
