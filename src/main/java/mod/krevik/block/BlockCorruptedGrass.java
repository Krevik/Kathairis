package mod.krevik.block;

import mod.krevik.util.CreativeTabsMystic;
import mod.krevik.KCore;
import net.minecraft.block.Block;
import net.minecraft.block.IGrowable;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.passive.EntityRabbit;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Calendar;
import java.util.Random;

public class BlockCorruptedGrass extends BaseBlock implements IGrowable
{
    public static final PropertyBool FLOWER = PropertyBool.create("flower");
    public static final PropertyBool SNOWY = PropertyBool.create("snowy");
    int month = Calendar.getInstance().get(Calendar.MONTH);

    public BlockCorruptedGrass()
    {
        super(KCore.Ref.CorruptedGrass, Material.GRASS, CreativeTabsMystic.buildingBlocks, 2F, 2F, SoundType.PLANT);
        this.setTickRandomly(true);
        this.setCreativeTab(CreativeTabsMystic.buildingBlocks);
            this.setDefaultState(this.blockState.getBaseState().withProperty(SNOWY, Boolean.valueOf(false)).withProperty(FLOWER, Boolean.valueOf(false)));
    }

    /**
     * Get the actual Block state of this Block at the given position. This applies properties not visible in the
     * metadata, such as fence connections.
     */
    public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos)
    {
        Block block = worldIn.getBlockState(pos.up()).getBlock();
        return state.withProperty(SNOWY, shouldBeSnowed(state)).withProperty(FLOWER, state.getValue(FLOWER));
    }
    private boolean shouldBeSnowed(IBlockState state){
        boolean result=false;
        if(month==11){
            return true;
        }
        return state.getValue(SNOWY);
    }

    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
    {
        if (!worldIn.isRemote)
        {
            if (worldIn.getLightFromNeighbors(pos.up()) < 4 && worldIn.getBlockState(pos.up()).getLightOpacity(worldIn, pos.up()) > 2)
            {
                worldIn.setBlockState(pos, KCore.CorruptedDirt.getDefaultState());
            }
            else
            {
                if (worldIn.getLightFromNeighbors(pos.up()) >= 9)
                {
                    for (int i = 0; i < 4; ++i)
                    {
                        BlockPos blockpos = pos.add(rand.nextInt(3) - 1, rand.nextInt(5) - 3, rand.nextInt(3) - 1);

                        if (blockpos.getY() >= 0 && blockpos.getY() < 256 && !worldIn.isBlockLoaded(blockpos))
                        {
                            return;
                        }

                        IBlockState iblockstate = worldIn.getBlockState(blockpos.up());
                        IBlockState iblockstate1 = worldIn.getBlockState(blockpos);

                        if (iblockstate1.getBlock() == KCore.CorruptedDirt && worldIn.getLightFromNeighbors(blockpos.up()) >= 4 && iblockstate.getLightOpacity(worldIn, pos.up()) <= 2)
                        {
                            worldIn.setBlockState(blockpos, KCore.CorruptedGrass.getDefaultState(),2);
                        }
                    }
                }
            }
        }
        if(rand.nextInt(999999)==0) {
        	int month = Calendar.getInstance().get(Calendar.MONTH);
    		int day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        	if(month==3&&(day==1||day==2)) {
        			if(worldIn.isAirBlock(new BlockPos(pos.getX(), pos.getY()+1, pos.getZ()))) {
	        			if(!worldIn.isRemote) {
		        				for(int i=0;i<(2+rand.nextInt(3));i++) {
			        			EntityRabbit er = new EntityRabbit(worldIn);
			        			er.setPosition(pos.getX()+rand.nextInt(4)-rand.nextInt(4), pos.getY()+1, pos.getZ()+rand.nextInt(4)-rand.nextInt(4));
			        			worldIn.spawnEntity(er);
		        				}
		        			worldIn.setBlockState(new BlockPos(pos.getX(), pos.getY()+1, pos.getZ()), KCore.EasterEgg.getDefaultState());
	        			}
        			}
        	}
        	if(month==2&&(day==31||day==30)) {
    			if(worldIn.isAirBlock(new BlockPos(pos.getX(), pos.getY()+1, pos.getZ()))) {
        			if(!worldIn.isRemote) {
	        				for(int i=0;i<(2+rand.nextInt(3));i++) {
		        			EntityRabbit er = new EntityRabbit(worldIn);
		        			er.setPosition(pos.getX()+rand.nextInt(4)-rand.nextInt(4), pos.getY()+1, pos.getZ()+rand.nextInt(4)-rand.nextInt(4));
		        			worldIn.spawnEntity(er);
	        				}
	        			worldIn.setBlockState(new BlockPos(pos.getX(), pos.getY()+1, pos.getZ()), KCore.EasterEgg.getDefaultState(),2);
        			}
    			}
        	}
        }
        /*if(rand.nextInt(999999)==0) {
            int month = Calendar.getInstance().get(Calendar.MONTH);
            if (month == 11) {
                if (worldIn.isAirBlock(new BlockPos(pos.getX(), pos.getY() + 1, pos.getZ()))) {
                    if (!worldIn.isRemote) {
                        worldIn.setBlockState(new BlockPos(pos.getX(), pos.getY() + 1, pos.getZ()), getRandomGiftState(rand), 2);
                    }
                }
            }
        }*/
    }

    private IBlockState getRandomGiftState(Random random){
        int k=random.nextInt(3);
        if(k==0){
            return KCore.christmas_gift.getDefaultState().withProperty(BlockChristmasGift.VARIANT,BlockChristmasGift.EnumType.RED);
        }
        else if(k==1){
            return KCore.christmas_gift.getDefaultState().withProperty(BlockChristmasGift.VARIANT,BlockChristmasGift.EnumType.YELLOW);
        }
        else if(k==2){
            return KCore.christmas_gift.getDefaultState().withProperty(BlockChristmasGift.VARIANT,BlockChristmasGift.EnumType.VIOLET);
        }else{
            return KCore.christmas_gift.getDefaultState().withProperty(BlockChristmasGift.VARIANT,BlockChristmasGift.EnumType.VIOLET);
        }
    }
    
    /**
     * Whether this IGrowable can grow
     */
    public boolean canGrow(World worldIn, BlockPos pos, IBlockState state, boolean isClient)
    {
        return true;
    }

    public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, IBlockState state)
    {
        return true;
    }

    public void grow(World worldIn, Random rand, BlockPos pos, IBlockState state)
    {

        BlockPos blockpos = pos.up();

        for (int i = 0; i < 128; ++i)
        {
            BlockPos blockpos1 = blockpos;
            int j = 0;

            while (true)
            {
                if (j >= i / 16)
                {
                    if (worldIn.isAirBlock(blockpos1))
                    {
                        if (rand.nextInt(100) == 6)
                        {
                            IBlockState iblockstate0 = KCore.MysticFungus.getDefaultState();                           
                            	if(KCore.MysticFungus.canBlockStay(worldIn, blockpos1, iblockstate0)){
                                    worldIn.setBlockState(blockpos1, iblockstate0, 3);
                            	}
                        }
                        else
                        {
                            IBlockState iblockstate1 = KCore.MysticTallGrass.getDefaultState();
                            IBlockState iblockstate11 = KCore.MysticMiniGrass.getDefaultState();

                            if(rand.nextInt(3)==1){
                            	if(KCore.MysticMiniGrass.canBlockStay(worldIn, blockpos1, iblockstate1)){
                                    worldIn.setBlockState(blockpos1, iblockstate11, 3);
                            	}
                            }
                            else
                            	if(KCore.MysticTallGrass.canBlockStay(worldIn, blockpos1, iblockstate1))
                            {
                                worldIn.setBlockState(blockpos1, iblockstate1, 3);
                            }
                        }
                    }

                    break;
                }

                blockpos1 = blockpos1.add(rand.nextInt(3) - 1, (rand.nextInt(3) - 1) * rand.nextInt(3) / 2, rand.nextInt(3) - 1);
                if (worldIn.getBlockState(blockpos1.down()).getBlock() != KCore.CorruptedGrass || worldIn.getBlockState(blockpos1).isNormalCube())
                {
                    break;
                }

                ++j;
            }
        }
    }

    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.CUTOUT_MIPPED;
    }

    /**
     * Convert the BlockState into the correct metadata value
     */
    public int getMetaFromState(IBlockState state)
    {
        return 0;
    }

    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {FLOWER,SNOWY});
    }


}
