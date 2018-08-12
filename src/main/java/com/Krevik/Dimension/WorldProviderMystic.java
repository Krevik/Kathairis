package com.Krevik.Dimension;

import javax.annotation.Nullable;

import com.Krevik.Main.KCore;

import net.minecraft.client.Minecraft;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProviderSurface;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.border.WorldBorder;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.client.IRenderHandler;
import net.minecraftforge.common.DimensionManager;
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
        return new Vec3d(2.37,1.65,0.71);
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
        return new Vec3d((double)f1, (double)f2, (double)f3);
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