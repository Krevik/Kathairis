package mod.krevik.world.gen;

import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.MapGenBase;

public class MapGenImprovedCaves extends MapGenBase{
	
	int actualRealX;
	int actualRealZ;
    int actualRealY;
    protected void recursiveGenerate(World worldIn, int chunkX, int chunkZ, int originalX, int originalZ, ChunkPrimer chunkPrimerIn)
    {    	
    	
        int i = this.rand.nextInt(this.rand.nextInt(this.rand.nextInt(15) + 1) + 1);
        int startY=worldIn.getHeight(originalX, originalZ);
        int actualCircledRadius=3+rand.nextInt(7);
        if (this.rand.nextInt(7) != 0)
        {
            i = 0;
        }
        
        for(int j=0;j<=i;j++) {
        	if(this.rand.nextInt(100)==0) {
        		this.recursiveGenerate(worldIn, chunkX, chunkZ, originalX, originalZ, chunkPrimerIn);
        	}
        	addTunnel(worldIn,chunkPrimerIn);
        }
    }
    
    private void addTunnel(World world, ChunkPrimer primer) {
    	
    }
}
