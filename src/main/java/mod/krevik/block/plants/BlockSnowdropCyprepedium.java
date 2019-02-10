package mod.krevik.block.plants;

import mod.krevik.KCore;
import mod.krevik.block.BlockKatharianGrass;
import net.minecraft.block.BlockGrass;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class BlockSnowdropCyprepedium extends BlockMysticBush {

    public BlockSnowdropCyprepedium(String Name){
        super(Name,false);
        setTickRandomly(true);
        addBlocksThatPlantCanStayOn(Blocks.GRASS,Blocks.DIRT,Blocks.FARMLAND,KCore.KatharianGrass,KCore.KatharianDirt,Blocks.SNOW,
                Blocks.STONE,Blocks.COBBLESTONE,KCore.Mythic_Cobblestone,KCore.MythicStone);
    }

    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
    {
        super.updateTick(worldIn,pos,state,rand);

        if(!worldIn.isRemote){
            for(int c=0;c<=1+rand.nextInt(5);c++){
                BlockPos tmp = new BlockPos(pos.getX()-6+rand.nextInt(12),pos.getY()-3+rand.nextInt(6),pos.getZ()-6+rand.nextInt(12));
                if(worldIn.getBlockState(tmp).getBlock()==KCore.KatharianGrass){
                    worldIn.setBlockState(tmp,KCore.KatharianGrass.getDefaultState().withProperty(BlockKatharianGrass.SNOWY,Boolean.valueOf(true)),2);
                }
                if(worldIn.getBlockState(tmp)== Blocks.GRASS.getDefaultState()){
                    worldIn.setBlockState(tmp,Blocks.GRASS.getDefaultState().withProperty(BlockGrass.SNOWY,Boolean.valueOf(true)));
                }
            }
        }
    }
}
