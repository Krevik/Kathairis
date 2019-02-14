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

    @Override
    public int damageDropped(IBlockState state)
    {
        return 0;
    }

    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState();
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        return 0;
    }
}