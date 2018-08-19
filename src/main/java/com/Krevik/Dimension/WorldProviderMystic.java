package com.Krevik.Dimension;

import javax.annotation.Nullable;

import com.Krevik.Main.KCore;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProviderSurface;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.border.WorldBorder;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.client.IRenderHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class WorldProviderMystic extends WorldProviderSurface
{
    /**
     * Creates a new {@link BiomeProvider} for the WorldProvider, and also sets the values of {@link #hasSkylight} and
     * {@link #hasNoSky} appropriately.
     *  
     * Note that subclasses generally override this method without calling the parent version.
     */
    public void init()
    {
        this.setDimension(KCore.DIMENSION_ID);
        this.biomeProvider = new BiomeProviderMystic(world.getWorldInfo().getSeed(),world.getWorldType());
        this.generateLightBrightnessTable();
        this.hasSkyLight = true;
    }

    
	@Override
	@SideOnly(Side.CLIENT)
	public IRenderHandler getSkyRenderer() {
		
		if (super.getSkyRenderer() == null) {
			this.setSkyRenderer(new RenderMysticSky());
		}

		return super.getSkyRenderer();
	}
	
	/*@Nullable
    @SideOnly(Side.CLIENT)
    public net.minecraftforge.client.IRenderHandler getWeatherRenderer()
    {
		if (super.getWeatherRenderer() == null) {
			this.setWeatherRenderer(new RenderWeatherRenderer());
		}

		return super.getWeatherRenderer();
    }*/
	
    @Nullable
    @SideOnly(Side.CLIENT)
    public net.minecraftforge.client.IRenderHandler getCloudRenderer()
    {
        return null;
    }

    
    @SideOnly(Side.CLIENT)
    public Vec3d getSkyColor(net.minecraft.entity.Entity cameraEntity, float partialTicks)
    {
    		//return new Vec3d(0.237,0.165,0.071);
            float f = world.getCelestialAngle(partialTicks);
            float f1 = MathHelper.cos(f * ((float)Math.PI * 2F)) * 2.0F + 0.5F;
            f1 = MathHelper.clamp(f1, 0.0F, 1.0F);
            int i = MathHelper.floor(cameraEntity.posX);
            int j = MathHelper.floor(cameraEntity.posY);
            int k = MathHelper.floor(cameraEntity.posZ);
            BlockPos blockpos = new BlockPos(i, j, k);
            int l = net.minecraftforge.client.ForgeHooksClient.getSkyBlendColour(world, blockpos);
            float f3 = (float)(l >> 16 & 255) / 255.0F;
            float f4 = (float)(l >> 8 & 255) / 255.0F;
            float f5 = (float)(l & 255) / 255.0F;
            f3 = (float) (f3 * f1*0.237);
            f4 = (float) (f4 * f1*0.165);
            f5 = (float) (f5 * f1*0.071);
            float f6 = world.getRainStrength(partialTicks);

            if (f6 > 0.0F)
            {
                float f7 = (f3 * 0.3F + f4 * 0.59F + f5 * 0.11F) * 0.6F;
                float f8 = 1.0F - f6 * 0.75F;
                f3 = f3 * f8 + f7 * (1.0F - f8);
                f4 = f4 * f8 + f7 * (1.0F - f8);
                f5 = f5 * f8 + f7 * (1.0F - f8);
            }

            float f10 = world.getThunderStrength(partialTicks);

            if (f10 > 0.0F)
            {
                float f11 = (f3 * 0.3F + f4 * 0.59F + f5 * 0.11F) * 0.2F;
                float f9 = 1.0F - f10 * 0.75F;
                f3 = f3 * f9 + f11 * (1.0F - f9);
                f4 = f4 * f9 + f11 * (1.0F - f9);
                f5 = f5 * f9 + f11 * (1.0F - f9);
            }

            if (world.getLastLightningBolt() > 0)
            {
                float f12 = (float)world.getLastLightningBolt() - partialTicks;

                if (f12 > 1.0F)
                {
                    f12 = 1.0F;
                }

                f12 = f12 * 0.45F;
                f3 = f3 * (1.0F - f12) + 0.8F * f12;
                f4 = f4 * (1.0F - f12) + 0.8F * f12;
                f5 = f5 * (1.0F - f12) + 1.0F * f12;
            }

            return new Vec3d((double)f3, (double)f4, (double)f5);

    }
    
    @SideOnly(Side.CLIENT)
    public Vec3d getFogColor(float p_76562_1_, float p_76562_2_)
    {
        float f = MathHelper.cos(p_76562_1_ * ((float)Math.PI * 2F)) * 2.0F + 0.5F;
        f = MathHelper.clamp(f, 0.0F, 1.0F);
        float f1 = 2.37F;
        float f2 = 1.65F;
        float f3 = 0.71F;
        f1 = f1 * (f * 0.94F + 0.06F);
        f2 = f2 * (f * 0.94F + 0.06F);
        f3 = f3 * (f * 0.91F + 0.09F);
        return new Vec3d((double)f1/5, (double)f2/5, (double)f3/5);
    }

    public IChunkGenerator createChunkGenerator()
    {
        return new ChunkGeneratorMystic(this.world, world.getWorldInfo().getSeed());
    }

    /**
     * Will check if the x, z position specified is alright to be set as the map spawn point
     */
    public boolean canCoordinateBeSpawn(int x, int z)
    {
        BlockPos blockpos = new BlockPos(x, 0, z);

        if (this.world.getBiome(blockpos).ignorePlayerSpawnSuitability())
        {
            return true;
        }
        else
        {
            return this.world.getGroundAboveSeaLevel(blockpos).getBlock() == KCore.CorruptedGrass;
        }
    }
    
    
    @SideOnly(Side.CLIENT)
    public float getCloudHeight()
    {
        return -100F;
    }
    



    @SideOnly(Side.CLIENT)
    public boolean isSkyColored()
    {
        return true;
    }


    /**
     * True if the player can respawn in this dimension (true = overworld, false = nether).
     */
    public boolean canRespawnHere()
    {
        return false;
    }


    public WorldBorder createWorldBorder()
    {
        return new WorldBorder()
        {
            public double getCenterX()
            {
                return super.getCenterX() / 8.0D;
            }
            public double getCenterZ()
            {
                return super.getCenterZ() / 8.0D;
            }
        };
    }

    public DimensionType getDimensionType()
    {
        return KCore.Mystic_DIMENSION;
    }
    
    @Nullable
    @SideOnly(Side.CLIENT)
    public net.minecraft.client.audio.MusicTicker.MusicType getMusicType()
    {
        return KCore.instance.ketherMusic;
    }
    
}