package com.krevik.Gens;

import java.util.Random;

import com.krevik.Gens.Forest.WorldGenSoulTree;
import com.krevik.Main.KCore;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.Mirror;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraft.world.gen.structure.template.PlacementSettings;
import net.minecraft.world.gen.structure.template.Template;
import net.minecraft.world.gen.structure.template.TemplateManager;

public class WorldGenFloatingTree extends WorldGenerator{
    private static final PlacementSettings DEFAULT_PLACE_SETTINGS = new PlacementSettings();
    private Template template;
    
	@Override
	public boolean generate(World world, Random rand, BlockPos position) {
		world.getChunkProvider().provideChunk(position.getX(), position.getZ());
		WorldServer worldserver = (WorldServer) world;
		MinecraftServer minecraftserver = world.getMinecraftServer();
		TemplateManager templatemanager = worldserver.getStructureTemplateManager();
		Template template = templatemanager.getTemplate(minecraftserver, new ResourceLocation(KCore.MODID+":floatingtree"));
		
		if(template == null)
		{
			System.out.println("NO STRUCTURE");
			return false;
		}
			int xSize = template.getSize().getX();
			int ySize = template.getSize().getY();
			int zSize = template.getSize().getZ();

			if(world.isAirBlock(position)&&world.isAirBlock(position.add(xSize, 0, 0))&&world.isAirBlock(position.add(0,ySize,0))&&
					world.isAirBlock(position.add(0, 0, zSize))&&world.isAirBlock(position.add(xSize, 0, zSize))&&world.isAirBlock(position.add(xSize, ySize, zSize))) {
				IBlockState iblockstate = world.getBlockState(position);
				world.notifyBlockUpdate(position, iblockstate, iblockstate, 3);
				PlacementSettings placementsettings = (new PlacementSettings()).setMirror(Mirror.NONE)
						.setRotation(Rotation.NONE).setIgnoreEntities(true).setChunk((ChunkPos) null)
						.setReplacedBlock((Block) null).setIgnoreStructureBlock(true);
				template.addBlocksToWorld(world, position, placementsettings);
				return true;
			}

		return false;
		
	}
	
	
	public boolean generate2(World world, Random rand, BlockPos position) {
		int xSize=3+rand.nextInt(7);
		int zSize=3+rand.nextInt(7);
		int ySize=2+rand.nextInt(7);
		IBlockState cloudState=rand.nextInt(2)==2?KCore.BlueCloud.getDefaultState():KCore.YellowCloud.getDefaultState();
		IBlockState grassState=KCore.CorruptedGrass.getDefaultState();
		IBlockState condensedCloudState=rand.nextInt(2)==2?KCore.BlueCondensedCloud.getDefaultState():KCore.YellowCondensedCloud.getDefaultState();
		boolean isTreeGenerated=false;
		for(int x=-xSize;x<=xSize;x++) {
			for(int y=0;y<=ySize;y++) {
				for(int z=-zSize;z<=zSize;z++) {
					if((x*x+z*z+y*y)<=(xSize*zSize)+1) {
						int actualSize=(x*x)+(z*z)+(y*y);
						BlockPos tmp=new BlockPos(position.getX()+x,position.getY()-y,position.getZ()+z);
						if(actualSize==xSize*zSize||actualSize>=(xSize*zSize)-12) {
							setBlockAndNotifyAdequately(world, tmp, cloudState);
						}else if(actualSize<=(xSize*zSize)-12&&actualSize>=(xSize*zSize)-16){
							setBlockAndNotifyAdequately(world, tmp, condensedCloudState);
						}else {
							setBlockAndNotifyAdequately(world, tmp, grassState);
							if(y==0&&rand.nextInt(2)==0&&!isTreeGenerated) {
								isTreeGenerated=true;
								new WorldGenSoulTree(false).generate(world, rand, tmp.up());
							}
							if(y==0&&rand.nextInt(10)==0) {
								new WorldGenMysticMultiGrass(KCore.SteppedSucculent).generate(world, rand, tmp.up());
							}
						}
					}
				}
			}
		}
		
		
		
		
		if(world.isAirBlock(position)&&world.isAirBlock(position.add(xSize, 0, 0))&&world.isAirBlock(position.add(0,ySize,0))&&
				world.isAirBlock(position.add(0, 0, zSize))&&world.isAirBlock(position.add(xSize, 0, zSize))&&world.isAirBlock(position.add(xSize, ySize, zSize))) {
			
		}
		
		return true;
	}
    protected void setBlockAndNotifyAdequately(World worldIn, BlockPos pos, IBlockState state)
    {
            worldIn.setBlockState(pos, state, 2);
    }

}
