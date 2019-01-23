package mod.krevik.kathairis.block;

import mod.krevik.kathairis.tileentity.TileEntityCharger;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * The Block for {@link TileEntityCharger}
 *
 * @author Cadiboo
 */
public class BlockCharger extends Block {

	public BlockCharger() {
		super(Material.IRON);
		this.setHardness(1);
	}

	@Override
	@Nonnull
	public EnumBlockRenderType getRenderType(@Nonnull final IBlockState state) {
		return EnumBlockRenderType.ENTITYBLOCK_ANIMATED;
	}

	@Override
	public boolean isOpaqueCube(@Nonnull final IBlockState state) {
		return false;
	}

	@Override
	public boolean hasTileEntity(@Nonnull final IBlockState state) {
		return true;
	}

	@Nullable
	@Override
	public TileEntityCharger createTileEntity(@Nonnull final World world, @Nonnull final IBlockState state) {
		return new TileEntityCharger();
	}

}
