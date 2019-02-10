package mod.krevik.world.gen;

import java.util.Random;

import mod.krevik.KCore;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenCustomTree extends WorldGenerator{
	private Random random = new Random();

	

	int treeHeight;
	
	public WorldGenCustomTree(){
		treeHeight=10;
	}
	
	public boolean initGenerating(World world, BlockPos pos) {
		generateTrunk1(world,pos);
	
		return true;
	}
	
	private int BuildaTree(int height, int boleheight, int[][] layer, int n, World world, BlockPos pos) {
		if (height > boleheight) {
			int[][] newlayer = new int [n][n];
			for(int i=0; i<n; i++){
				for(int j=0; j<n; j++){
					newlayer[i][j]=0;
				}
			}
			{
				for(int i=0; i<n; i++) {
					for(int j=0; j<n; j++) {
						if(layer[i][j]==1) {
							int ni, nj;
							ni = i+random.nextInt(3)-1;
							nj = j+random.nextInt(3)-1;
							if(ni<0 || ni>=n) {
								ni = i;
							}
							if(nj<0 || nj>=n) {
								nj = j;
							}
							newlayer[ni][nj] = 1;
							setBlock(world,new BlockPos(pos.getX()+ni,pos.getY()+height,pos.getZ()+nj),KCore.MysticLog.getDefaultState());
						}
					}
				}
			}
			setBlock(world,new BlockPos(pos.getX()+n/2,pos.getY()+height,pos.getZ()+n/2),Blocks.GLOWSTONE.getDefaultState());
			return BuildaTree(height-1, boleheight, newlayer, n, world, pos);
			
		}
		
		//pierwsze dwie �wiary

		for(int i=0; i<n/2; i++){

		//pierwsza �wiara

			for(int j=0; j<n/2; j++){
				if( layer[i][j] == 1 ){
					int x=0; int z=0;
					while(x<n/2 || z<n/2){
					if(n/2 > x )x++; 
					if(n/2 > z )z++; 			
					
					setBlock( world,new BlockPos(pos.getX()+i+x, pos.getY()+height, pos.getZ()+j+z),Blocks.GLOWSTONE.getDefaultState());	

					}
				}
			}

		//druga �wiara

			for(int j=n/2; j<n; j++){
				if( layer[i][j] == 1 ){
					int x=0; int z=0;
					while(x<n/2 || n/2+z > 0){
					if(n/2 > x )x++; 
					if(n/2 +z > 0 )z--; 			
					
					setBlock( world,new BlockPos(pos.getX()+i+x, pos.getY()+height, pos.getZ()+j+z),Blocks.GLOWSTONE.getDefaultState());	

					}
				}
			}
		}


		//pozosta�e dwie �wiary

		for(int i=n/2; i<n; i++){

		//czecia �wiara

			for(int j=0; j<n/2; j++){
				if( layer[i][j] == 1 ){
					int x=0, z=0;
					while(n/2+x>0 || z<n/2){
					if(n/2+x> 0 )x--; 
					if(n/2 > z )z++; 			
					
					setBlock( world,new BlockPos(pos.getX()+i+x, pos.getY()+height, pos.getZ()+j+z),Blocks.GLOWSTONE.getDefaultState());	

					}
				}
			}

		//czwarta �wiara 

			for(int j=n/2; j<n; j++){
				if( layer[i][j] == 1 ){
					int x=0, z=0;
					while(n/2+x > 0 || n/2+z > 0){
					if(n/2+x > 0 )x--; 
					if(n/2+z > 0 )z--; 			
					
					setBlock( world,new BlockPos(pos.getX()+i+x, pos.getY()+height, pos.getZ()+j+z),Blocks.GLOWSTONE.getDefaultState());	

					}
				}
			}
		}
		
		//building the trunk
		for (int i=0; i<=boleheight; i++) {
			setBlock(world,new BlockPos(pos.getX()+n/2,pos.getY()+i,pos.getZ()+n/2),KCore.MysticLog.getDefaultState());
		}
		return 0;
	}
	
	private void generateTrunk1(World world, BlockPos pos) {
		IBlockState log = KCore.MysticLog.getDefaultState();
		IBlockState leaves = KCore.MysticLeaves.getDefaultState();
		BlockPos StartPos = getTopGrassBlock(world,pos);
		if(isValidBlockToSpawn(world,StartPos)) {
			int[][] firstLayer = new int [10][10];

					for (int i=0; i<10; i++) {
						for (int j=0; j<10; j++) {
							int pom = random.nextInt(100);
							if(pom%20 == 0) {
							firstLayer[i][j] = 1;
							}else {
							firstLayer[i][j] = 0;
							}
						}
						
					}
			
			BuildaTree(10, 4, firstLayer, 10, world, StartPos);
		}
	}
	
	
	private void setBlock(World world,BlockPos pos, IBlockState state) {
		world.setBlockState(pos, state);
	}
	
	private boolean isValidBlockToSpawn(World world, BlockPos pos) {
		if(world.getBlockState(pos).getBlock()==KCore.KatharianDirt ||world.getBlockState(pos).getBlock()==KCore.KatharianGrass) {
			return true;
		}else {
			return false;
		}
	}
	
    private BlockPos getTopGrassBlock(World worldIn,BlockPos position){
    	int top=0;
        	for(top=0;top<256;top++){
        		if((worldIn.getBlockState(new BlockPos(position.getX(),top,position.getZ()))==KCore.KatharianDirt.getDefaultState()
        				||worldIn.getBlockState(new BlockPos(position.getX(),top,position.getZ()))==KCore.KatharianGrass.getDefaultState())&&worldIn.canBlockSeeSky(new BlockPos(position.getX(),top+1,position.getZ()))){
        			break;
        		}
        	}
    	return new BlockPos(position.getX(),top,position.getZ());
    }

	@Override
	public boolean generate(World worldIn, Random rand, BlockPos position) {
		initGenerating(worldIn,position);
		return false;
	}
}
