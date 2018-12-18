package mod.krevik.block;

import mod.krevik.util.CreativeTabsMystic;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;

public class BlockMysticPlanks extends BaseBlock
{

    public BlockMysticPlanks(String Name)
    {
        super(Name, Material.WOOD, CreativeTabsMystic.buildingBlocks, 4F, 4F, SoundType.WOOD);
        this.setDefaultState(this.blockState.getBaseState());
    }

    /**
     * Gets the metadata of the item this Block can drop. This method is called when the block gets destroyed. It
     * returns the metadata of the dropped item based on the old metadata of the block.
     */
    public int damageDropped(IBlockState state)
    {
        return 0;
    }

    /**
     * Convert the given metadata into a BlockState for this Block
     */
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState();
    }

    /**
     * Convert the BlockState into the correct metadata value
     */
    public int getMetaFromState(IBlockState state)
    {
        return 0;
    }
}