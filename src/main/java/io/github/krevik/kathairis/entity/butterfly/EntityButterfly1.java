package io.github.krevik.kathairis.entity.butterfly;

import io.github.krevik.kathairis.block.BlockButterflyFlower;
import io.github.krevik.kathairis.block.BlockKathairisPlant;
import io.github.krevik.kathairis.init.ModBlocks;
import io.github.krevik.kathairis.init.ModEntities;
import io.github.krevik.kathairis.util.KatharianLootTables;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.Heightmap;

import javax.annotation.Nullable;

public class EntityButterfly1 extends EntityBasicButterfly
{
    private BlockPos spawnPosition;
    public BlockPos butterflyFlowerPos=null;

    public EntityButterfly1(World worldIn)
    {
        super(worldIn, ModEntities.COMMON_BUTTERFLY2);
        this.setSize(0.6F, 0.5F);
        this.setIsBirdSitting(true);
        this.experienceValue=1;
    }

    @Override
    public int getMaxSpawnedInChunk()
    {
        return 2;
    }

    @Override
    public boolean canBePushed()
    {
        return false;
    }

    @Override
    protected void collideWithEntity(Entity entityIn)
    {
    }

    @Override
    protected void collideWithNearbyEntities()
    {
    }

    @Override
    protected void registerAttributes()
    {
        super.registerAttributes();
        this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10.0D);
    }

    @Override
    public void tick()
    {
        super.tick();

        if (this.getIsBirdSitting())
        {
            this.motionX = 0.0D;
            this.motionY = 0.0D;
            this.motionZ = 0.0D;
            this.posY=this.world.getHeight(Heightmap.Type.MOTION_BLOCKING,this.getPosition()).getY();
            if(this.rand.nextInt(1000)==0) {
            	this.setIsBirdSitting(false);
            }
        }
        else
        {
            this.motionY *= 0.6000000238418579D;
        }
    }

    int ticker=0;
    @Override
    protected void updateAITasks()
    {
        ticker++;
        super.updateAITasks();
        BlockPos blockpos = new BlockPos(this);
        BlockPos blockpos1 = blockpos.down();
        if(butterflyFlowerPos!=null) {
        if(this.getIsBirdSitting()) {
    		if(this.getPosition()!=butterflyFlowerPos.up()) {
    			this.setIsBirdSitting(false);
    		}
        }
        }
        if(butterflyFlowerPos!=null) {
        	if(this.world.getBlockState(butterflyFlowerPos)== ModBlocks.BUTTERFLY_FLOWER.getDefaultState().with(BlockButterflyFlower.VARIANT, BlockButterflyFlower.EnumType.WITH)) {
        		this.getMoveHelper().setMoveTo(butterflyFlowerPos.getX()+rand.nextFloat(), butterflyFlowerPos.getY()+5, butterflyFlowerPos.getZ()+rand.nextFloat(), 0.5);
        		this.motionY*=1.6;
        		if(this.getPosition()==butterflyFlowerPos.up()) {
        			this.setIsBirdSitting(true);
        		}
        	}else {
        		butterflyFlowerPos=null;
        		this.setIsBirdSitting(false);
        	}
        	
        }else {
        if (this.getIsBirdSitting())
        {
            if (this.world.getBlockState(blockpos1).isNormalCube()||this.world.getBlockState(blockpos1)==ModBlocks.BUTTERFLY_FLOWER.getDefaultState().with(BlockButterflyFlower.VARIANT, BlockButterflyFlower.EnumType.WITH))
            {

                if (this.world.getNearestPlayerNotCreative(this, 4.0D) != null)
                {
                    this.setIsBirdSitting(false);
                    this.world.playEvent(null, 1025, blockpos, 0);
                }
            }
            else
            {
                this.setIsBirdSitting(false);
                this.world.playEvent(null, 1025, blockpos, 0);
            }
        }
        else
        {
            if (this.spawnPosition != null && (!this.world.isAirBlock(this.spawnPosition) || this.spawnPosition.getY() < 1))
            {
                this.spawnPosition = null;
            }

            if (this.spawnPosition == null || this.rand.nextInt(30) == 0 || this.spawnPosition.distanceSq((double)((int)this.posX), (double)((int)this.posY), (double)((int)this.posZ)) < 4.0D)
            {
                this.spawnPosition = new BlockPos((int)this.posX + this.rand.nextInt(7) - this.rand.nextInt(7), (int)this.posY + this.rand.nextInt(6) - 2, (int)this.posZ + this.rand.nextInt(7) - this.rand.nextInt(7));
            }

            double d0 = (double)this.spawnPosition.getX() + 0.5D - this.posX;
            double d1 = (double)this.spawnPosition.getY() + 0.1D - this.posY;
            double d2 = (double)this.spawnPosition.getZ() + 0.5D - this.posZ;
            this.motionX += (Math.signum(d0) * 0.5D - this.motionX) * 0.10000000149011612D;
            this.motionY += (Math.signum(d1) * 0.699999988079071D - this.motionY) * 0.10000000149011612D;
            this.motionZ += (Math.signum(d2) * 0.5D - this.motionZ) * 0.10000000149011612D;
            float f = (float)(MathHelper.atan2(this.motionZ, this.motionX) * (180D / Math.PI)) - 90.0F;
            float f1 = MathHelper.wrapDegrees(f - this.rotationYaw);
            this.moveForward = 0.5F;
            this.rotationYaw += f1;

            if (this.rand.nextInt(100) == 0 && (this.world.getBlockState(blockpos1).isNormalCube()||this.world.getBlockState(blockpos1).getBlock()instanceof BlockKathairisPlant)||this.world.getBlockState(blockpos1)==ModBlocks.BUTTERFLY_FLOWER.getDefaultState().with(BlockButterflyFlower.VARIANT, BlockButterflyFlower.EnumType.WITH))
            {
                this.setIsBirdSitting(true);
            }
            if(ticker>=30){
                EntityPlayer ep = world.getNearestPlayerNotCreative(this,15D);
                if(ep!=null){
                    d0 = ep.posX + getRNG().nextFloat()-getRNG().nextFloat() + 0.5D - this.posX;
                    d1 = ep.posY + getRNG().nextFloat() + 0.1D - this.posY;
                    d2 = ep.posZ + getRNG().nextFloat()-getRNG().nextFloat() + 0.5D - this.posZ;
                    this.motionX += (Math.signum(d0) * 0.5D - this.motionX) * 0.10000000149011612D;
                    this.motionY += (Math.signum(d1) * 0.699999988079071D - this.motionY) * 0.10000000149011612D;
                    this.motionZ += (Math.signum(d2) * 0.5D - this.motionZ) * 0.10000000149011612D;
                    f = (float)(MathHelper.atan2(this.motionZ, this.motionX) * (180D / Math.PI)) - 90.0F;
                    f1 = MathHelper.wrapDegrees(f - this.rotationYaw);
                    this.moveForward = 0.5F;
                    this.rotationYaw += f1;
                }
            }
        }
        }
    }


    @Override
    protected boolean canTriggerWalking()
    {
        return false;
    }

    @Override
    public void fall(float distance, float damageMultiplier)
    {
    }

    @Override
    protected void updateFallState(double y, boolean onGroundIn, IBlockState state, BlockPos pos)
    {
    }


    @Override
    public boolean doesEntityNotTriggerPressurePlate()
    {
        return true;
    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float amount)
    {
        if (this.isInvulnerableTo(source))
        {
            return false;
        }
        else
        {
            if (!this.world.isRemote && this.getIsBirdSitting())
            {
                this.setIsBirdSitting(false);
            }

            return super.attackEntityFrom(source, amount);
        }
    }

    @Nullable
    @Override
    protected ResourceLocation getLootTable()
    {
        return KatharianLootTables.LOOT_BUTTERFLY;
    }
}