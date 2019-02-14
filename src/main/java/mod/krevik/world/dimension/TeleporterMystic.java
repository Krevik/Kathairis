package mod.krevik.world.dimension;

import it.unimi.dsi.fastutil.longs.Long2ObjectMap;
import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import mod.krevik.KCore;
import mod.krevik.block.BlockMysticPortal;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockPattern;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldServer;

import java.util.Random;

public class TeleporterMystic extends Teleporter
{
    protected final WorldServer world;
    /** A private Random() function in Teleporter */
    protected final Random random;
    /** Stores successful portal placement locations for rapid lookup. */
    protected final Long2ObjectMap<TeleporterMystic.PortalPosition> destinationCoordinateCache = new Long2ObjectOpenHashMap<TeleporterMystic.PortalPosition>(4096);

    public TeleporterMystic(WorldServer worldIn)
    {
    	super(worldIn);
        this.world = worldIn;
        this.random = new Random(worldIn.getSeed());
    }
    
    IBlockState stoneState = Blocks.STONE.getDefaultState();

    public void placeInPortal(Entity entityIn, float rotationYaw)
    {
        if (this.world.provider.getDimensionType().getId() != -100)
        {
            if (!this.placeInExistingPortal(entityIn, rotationYaw))
            {
                this.makePortal(entityIn);
                this.placeInExistingPortal(entityIn, rotationYaw);
            }
        }
        else
        {
            int i = MathHelper.floor(entityIn.posX);
            int j = MathHelper.floor(entityIn.posY) - 1;
            int k = MathHelper.floor(entityIn.posZ);
            int l = 1;
            int i1 = 0;

            for (int j1 = -2; j1 <= 2; ++j1)
            {
                for (int k1 = -2; k1 <= 2; ++k1)
                {
                    for (int l1 = -1; l1 < 3; ++l1)
                    {
                        int i2 = i + k1 * 1 + j1 * 0;
                        int j2 = j + l1;
                        int k2 = k + k1 * 0 - j1 * 1;
                        boolean flag = l1 < 0;
                        this.world.setBlockState(new BlockPos(i2, j2, k2), flag ? stoneState : Blocks.AIR.getDefaultState());
                    }
                }
            }

            entityIn.setLocationAndAngles((double)i, (double)j, (double)k, entityIn.rotationYaw, 0.0F);
            entityIn.motionX = 0.0D;
            entityIn.motionY = 0.0D;
            entityIn.motionZ = 0.0D;
        }
    }

    public boolean placeInExistingPortal(Entity entityIn, float rotationYaw)
    {
        int i = 128;
        double d0 = -1.0D;
        int j = MathHelper.floor(entityIn.posX);
        int k = MathHelper.floor(entityIn.posZ);
        boolean flag = true;
        BlockPos blockpos = BlockPos.ORIGIN;
        long l = ChunkPos.asLong(j, k);

        if (this.destinationCoordinateCache.containsKey(l))
        {
            TeleporterMystic.PortalPosition teleporter$portalposition = this.destinationCoordinateCache.get(l);
            d0 = 0.0D;
            blockpos = teleporter$portalposition;
            teleporter$portalposition.lastUpdateTime = this.world.getTotalWorldTime();
            flag = false;
        }
        else
        {
            BlockPos blockpos3 = new BlockPos(entityIn);

            for (int i1 = -128; i1 <= 128; ++i1)
            {
                BlockPos blockpos2;

                for (int j1 = -128; j1 <= 128; ++j1)
                {
                    for (BlockPos blockpos1 = blockpos3.add(i1, this.world.getActualHeight() - 1 - blockpos3.getY(), j1); blockpos1.getY() >= 0; blockpos1 = blockpos2)
                    {
                        blockpos2 = blockpos1.down();

                        if (this.world.getBlockState(blockpos1).getBlock() == KCore.MysticPortal)
                        {
                            for (blockpos2 = blockpos1.down(); this.world.getBlockState(blockpos2).getBlock() == KCore.MysticPortal; blockpos2 = blockpos2.down())
                            {
                                blockpos1 = blockpos2;
                            }

                            double d1 = blockpos1.distanceSq(blockpos3);

                            if (d0 < 0.0D || d1 < d0)
                            {
                                d0 = d1;
                                blockpos = blockpos1;
                            }
                        }
                    }
                }
            }
        }

        boolean shouldCheckFurther=true;
        BlockPos anyPortalBlockPos=entityIn.getPosition();
        loop1: for(int x=-20;x<=20;x++){
            loop2: for(int y=-20;y<=20;y++){
                loop3: for(int z=-20;z<=20;z++){
                    BlockPos tmp = new BlockPos(blockpos.getX()+x,blockpos.getY()+y,blockpos.getZ()+z);
                    if(world.getBlockState(tmp).getBlock()==KCore.MysticPortal){
                        shouldCheckFurther=false;
                        anyPortalBlockPos=tmp;
                        d0=0;
                        break loop3;
                    }
                    if(!shouldCheckFurther) break loop2;
                }
                if(!shouldCheckFurther) break loop1;
            }
        }
        if(shouldCheckFurther){
            loop1: for(int x=-128;x<=128;x++){
                loop2: for(int y=-128;y<=128;y++){
                    loop3: for(int z=-128;z<=128;z++){
                        BlockPos tmp = new BlockPos(blockpos.getX()+x,blockpos.getY()+y,blockpos.getZ()+z);
                        if(world.getBlockState(tmp).getBlock()==KCore.MysticPortal){
                            shouldCheckFurther=false;
                            anyPortalBlockPos=tmp;
                            d0=0;
                            break loop3;
                        }
                        if(!shouldCheckFurther) break loop2;
                    }
                    if(!shouldCheckFurther) break loop1;
                }
            }
        }
        if(shouldCheckFurther){
            loop1: for(int x=-256;x<=256;x++){
                loop2: for(int y=-128;y<=128;y++){
                    loop3: for(int z=-256;z<=256;z++){
                        BlockPos tmp = new BlockPos(blockpos.getX()+x,blockpos.getY()+y,blockpos.getZ()+z);
                        if(world.getBlockState(tmp).getBlock()==KCore.MysticPortal){
                            shouldCheckFurther=false;
                            anyPortalBlockPos=tmp;
                            d0=0;
                            break loop3;
                        }
                        if(!shouldCheckFurther) break loop2;
                    }
                    if(!shouldCheckFurther) break loop1;
                }
            }
        }
        if(!world.isRemote) {
            entityIn.setLocationAndAngles(anyPortalBlockPos.getX(), anyPortalBlockPos.getY(), anyPortalBlockPos.getZ(), 0, 0);
            entityIn.setPosition(anyPortalBlockPos.getX(), anyPortalBlockPos.getY(), anyPortalBlockPos.getZ());
        }

        if (d0 >= 0.0D)
        {
            if (flag)
            {
                this.destinationCoordinateCache.put(l, new TeleporterMystic.PortalPosition(blockpos, this.world.getTotalWorldTime()));
            }

            double d5 = (double)blockpos.getX() + 0.5D;
            double d7 = (double)blockpos.getZ() + 0.5D;
            BlockPattern.PatternHelper blockpattern$patternhelper = KCore.MysticPortal.createPatternHelper(this.world, blockpos);
            boolean flag1 = blockpattern$patternhelper.getForwards().rotateY().getAxisDirection() == EnumFacing.AxisDirection.NEGATIVE;
            double d2 = blockpattern$patternhelper.getForwards().getAxis() == EnumFacing.Axis.X ? (double)blockpattern$patternhelper.getFrontTopLeft().getZ() : (double)blockpattern$patternhelper.getFrontTopLeft().getX();
            double d6 = (double)(blockpattern$patternhelper.getFrontTopLeft().getY() + 1) - entityIn.getLastPortalVec().y * (double)blockpattern$patternhelper.getHeight();

            if (flag1)
            {
                ++d2;
            }

            if (blockpattern$patternhelper.getForwards().getAxis() == EnumFacing.Axis.X)
            {
                d7 = d2 + (1.0D - entityIn.getLastPortalVec().x) * (double)blockpattern$patternhelper.getWidth() * (double)blockpattern$patternhelper.getForwards().rotateY().getAxisDirection().getOffset();
            }
            else
            {
                d5 = d2 + (1.0D - entityIn.getLastPortalVec().x) * (double)blockpattern$patternhelper.getWidth() * (double)blockpattern$patternhelper.getForwards().rotateY().getAxisDirection().getOffset();
            }

            float f = 0.0F;
            float f1 = 0.0F;
            float f2 = 0.0F;
            float f3 = 0.0F;

            if (blockpattern$patternhelper.getForwards().getOpposite() == entityIn.getTeleportDirection())
            {
                f = 1.0F;
                f1 = 1.0F;
            }
            else if (blockpattern$patternhelper.getForwards().getOpposite() == entityIn.getTeleportDirection().getOpposite())
            {
                f = -1.0F;
                f1 = -1.0F;
            }
            else if (blockpattern$patternhelper.getForwards().getOpposite() == entityIn.getTeleportDirection().rotateY())
            {
                f2 = 1.0F;
                f3 = -1.0F;
            }
            else
            {
                f2 = -1.0F;
                f3 = 1.0F;
            }

            double d3 = entityIn.motionX;
            double d4 = entityIn.motionZ;
            entityIn.motionX = d3 * (double)f + d4 * (double)f3;
            entityIn.motionZ = d3 * (double)f2 + d4 * (double)f1;
            entityIn.rotationYaw = rotationYaw - (float)(entityIn.getTeleportDirection().getOpposite().getHorizontalIndex() * 90) + (float)(blockpattern$patternhelper.getForwards().getHorizontalIndex() * 90);

            EnumFacing.Axis axis = blockpattern$patternhelper.getForwards().getAxis();
            double destX=blockpos.getX();
            double destZ=blockpos.getZ();
            if(axis==EnumFacing.Axis.X){
                destZ+=1;

            }

            if(axis==EnumFacing.Axis.Z){
                destX+=1;
            }

            if (entityIn instanceof EntityPlayerMP)
            {
            	//((EntityPlayerMP)entityIn).addPotionEffect(new PotionEffect(Potion.getPotionById(10),10,10));
                //((EntityPlayerMP)entityIn).connection.setPlayerLocation(destX, blockpos.getY()+1, destZ, entityIn.rotationYaw, entityIn.rotationPitch);
                
                /*if (!this.world.isRemote) {
                	   EntityEnderPearl entityenderpearl = new EntityEnderPearl(this.world, (EntityLivingBase) entityIn);
                	   entityenderpearl.setLocationAndAngles(d5, d6, d7, entityIn.rotationYaw, entityIn.rotationPitch); 
                	   entityenderpearl.motionY=-1;
                	   this.world.spawnEntity(entityenderpearl);
                    entityIn.setLocationAndAngles(d5, d6, d7, entityIn.rotationYaw, entityIn.rotationPitch);
                }*/
            }
            else
            {
                if (!this.world.isRemote) {
                    //entityIn.setLocationAndAngles(destX, blockpos.getY()+1, destZ, entityIn.rotationYaw, entityIn.rotationPitch);
                }
            }

            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean makePortal(Entity entityIn)
    {
        int i = 16;
        double d0 = -1.0D;
        int j = MathHelper.floor(entityIn.posX);
        int k = MathHelper.floor(entityIn.posY);
        int l = MathHelper.floor(entityIn.posZ);
        int i1 = j;
        int j1 = k;
        int k1 = l;
        int l1 = 0;
        int i2 = this.random.nextInt(4);
        BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

        for (int j2 = j - 16; j2 <= j + 16; ++j2)
        {
            double d1 = (double)j2 + 0.5D - entityIn.posX;

            for (int l2 = l - 16; l2 <= l + 16; ++l2)
            {
                double d2 = (double)l2 + 0.5D - entityIn.posZ;
                label293:

                for (int j3 = this.world.getActualHeight() - 1; j3 >= 0; --j3)
                {
                    if (this.world.isAirBlock(blockpos$mutableblockpos.setPos(j2, j3, l2)))
                    {
                        while (j3 > 0 && this.world.isAirBlock(blockpos$mutableblockpos.setPos(j2, j3 - 1, l2)))
                        {
                            --j3;
                        }

                        for (int k3 = i2; k3 < i2 + 4; ++k3)
                        {
                            int l3 = k3 % 2;
                            int i4 = 1 - l3;

                            if (k3 % 4 >= 2)
                            {
                                l3 = -l3;
                                i4 = -i4;
                            }

                            for (int j4 = 0; j4 < 3; ++j4)
                            {
                                for (int k4 = 0; k4 < 4; ++k4)
                                {
                                    for (int l4 = -1; l4 < 4; ++l4)
                                    {
                                        int i5 = j2 + (k4 - 1) * l3 + j4 * i4;
                                        int j5 = j3 + l4;
                                        int k5 = l2 + (k4 - 1) * i4 - j4 * l3;
                                        blockpos$mutableblockpos.setPos(i5, j5, k5);

                                        if (l4 < 0 && !this.world.getBlockState(blockpos$mutableblockpos).getMaterial().isSolid() || l4 >= 0 && !this.world.isAirBlock(blockpos$mutableblockpos))
                                        {
                                            continue label293;
                                        }
                                    }
                                }
                            }

                            double d5 = (double)j3 + 0.5D - entityIn.posY;
                            double d7 = d1 * d1 + d5 * d5 + d2 * d2;

                            if (d0 < 0.0D || d7 < d0)
                            {
                                d0 = d7;
                                i1 = j2;
                                j1 = j3;
                                k1 = l2;
                                l1 = k3 % 4;
                            }
                        }
                    }
                }
            }
        }

        if (d0 < 0.0D)
        {
            for (int l5 = j - 16; l5 <= j + 16; ++l5)
            {
                double d3 = (double)l5 + 0.5D - entityIn.posX;

                for (int j6 = l - 16; j6 <= l + 16; ++j6)
                {
                    double d4 = (double)j6 + 0.5D - entityIn.posZ;
                    label231:

                    for (int i7 = this.world.getActualHeight() - 1; i7 >= 0; --i7)
                    {
                        if (this.world.isAirBlock(blockpos$mutableblockpos.setPos(l5, i7, j6)))
                        {
                            while (i7 > 0 && this.world.isAirBlock(blockpos$mutableblockpos.setPos(l5, i7 - 1, j6)))
                            {
                                --i7;
                            }

                            for (int k7 = i2; k7 < i2 + 2; ++k7)
                            {
                                int j8 = k7 % 2;
                                int j9 = 1 - j8;

                                for (int j10 = 0; j10 < 4; ++j10)
                                {
                                    for (int j11 = -1; j11 < 4; ++j11)
                                    {
                                        int j12 = l5 + (j10 - 1) * j8;
                                        int i13 = i7 + j11;
                                        int j13 = j6 + (j10 - 1) * j9;
                                        blockpos$mutableblockpos.setPos(j12, i13, j13);

                                        if (j11 < 0 && !this.world.getBlockState(blockpos$mutableblockpos).getMaterial().isSolid() || j11 >= 0 && !this.world.isAirBlock(blockpos$mutableblockpos))
                                        {
                                            continue label231;
                                        }
                                    }
                                }

                                double d6 = (double)i7 + 0.5D - entityIn.posY;
                                double d8 = d3 * d3 + d6 * d6 + d4 * d4;

                                if (d0 < 0.0D || d8 < d0)
                                {
                                    d0 = d8;
                                    i1 = l5;
                                    j1 = i7;
                                    k1 = j6;
                                    l1 = k7 % 2;
                                }
                            }
                        }
                    }
                }
            }
        }

        int i6 = i1;
        int k2 = j1;
        int k6 = k1;
        int l6 = l1 % 2;
        int i3 = 1 - l6;

        if (l1 % 4 >= 2)
        {
            l6 = -l6;
            i3 = -i3;
        }

        if (d0 < 0.0D)
        {
            j1 = MathHelper.clamp(j1, 70, this.world.getActualHeight() - 10);
            k2 = j1;

            for (int j7 = -1; j7 <= 1; ++j7)
            {
                for (int l7 = 1; l7 < 3; ++l7)
                {
                    for (int k8 = -1; k8 < 3; ++k8)
                    {
                        int k9 = i6 + (l7 - 1) * l6 + j7 * i3;
                        int k10 = k2 + k8;
                        int k11 = k6 + (l7 - 1) * i3 - j7 * l6;
                        boolean flag = k8 < 0;
                        this.world.setBlockState(new BlockPos(k9, k10, k11), flag ? stoneState : Blocks.AIR.getDefaultState());
                    }
                }
            }
        }

        IBlockState iblockstate = KCore.MysticPortal.getDefaultState().withProperty(BlockMysticPortal.AXIS, l6 == 0 ? EnumFacing.Axis.Z : EnumFacing.Axis.X);

        for (int i8 = 0; i8 < 4; ++i8)
        {
            for (int l8 = 0; l8 < 4; ++l8)
            {
                for (int l9 = -1; l9 < 4; ++l9)
                {
                    int l10 = i6 + (l8 - 1) * l6;
                    int l11 = k2 + l9;
                    int k12 = k6 + (l8 - 1) * i3;
                    boolean flag1 = l8 == 0 || l8 == 3 || l9 == -1 || l9 == 3;
                    this.world.setBlockState(new BlockPos(l10, l11, k12), flag1 ? stoneState : iblockstate, 2);
                }
            }

            for (int i9 = 0; i9 < 4; ++i9)
            {
                for (int i10 = -1; i10 < 4; ++i10)
                {
                    int i11 = i6 + (i9 - 1) * l6;
                    int i12 = k2 + i10;
                    int l12 = k6 + (i9 - 1) * i3;
                    BlockPos blockpos = new BlockPos(i11, i12, l12);
                    this.world.notifyNeighborsOfStateChange(blockpos, this.world.getBlockState(blockpos).getBlock(), false);
                }
            }
        }

        return true;
    }

    /**
     * called periodically to remove out-of-date portal locations from the cache list. Argument par1 is a
     * WorldServer.getTotalWorldTime() value.
     */
    public void removeStalePortalLocations(long worldTime)
    {
        if (worldTime % 100L == 0L)
        {
            long i = worldTime - 300L;
            ObjectIterator<TeleporterMystic.PortalPosition> objectiterator = this.destinationCoordinateCache.values().iterator();

            while (objectiterator.hasNext())
            {
                TeleporterMystic.PortalPosition teleporter$portalposition = objectiterator.next();

                if (teleporter$portalposition == null || teleporter$portalposition.lastUpdateTime < i)
                {
                    objectiterator.remove();
                }
            }
        }
    }

    public class PortalPosition extends BlockPos
    {
        /** The worldtime at which this PortalPosition was last verified */
        public long lastUpdateTime;

        public PortalPosition(BlockPos pos, long lastUpdate)
        {
            super(pos.getX(), pos.getY(), pos.getZ());
            this.lastUpdateTime = lastUpdate;
        }
    }

}