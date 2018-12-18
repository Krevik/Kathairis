package mod.krevik.world.gen.desert.AncientLabirynth;

import mod.krevik.KCore;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.ArrayList;
import java.util.Random;

public class WorldGenAncientLabirynth extends WorldGenerator
{
    public WorldGenAncientLabirynth(){

    }
    Random random = new Random();
    int roomCount=0;
    int roomNumber=15+random.nextInt(15);

    @Override
    public boolean generate(World world, Random random, BlockPos pos){
        BlockPos entrance = new BlockPos(pos.getX(),30,pos.getZ());
        IBlockState wallState = KCore.MythicStone.getDefaultState();
        IBlockState floorState = KCore.MythicStoneTiles.getDefaultState();
        IBlockState ceilingState = KCore.MythicStone.getDefaultState();
        IBlockState corridorWallState = wallState;
        IBlockState corridorFloorState = floorState;
        IBlockState corridorCeilingState = ceilingState;
        int roomType=0;
        ArrayList<BlockPos> exits = new ArrayList<BlockPos>();
        exits.add(entrance);
        ArrayList<BlockPos> newExits = new ArrayList<BlockPos>();

        int size=4+random.nextInt(8);
        if(size%2!=0) {size++;}
        //clearing room
        int halfedSize=size/2;
        for(int x=-halfedSize;x<=halfedSize;x++){
            for(int y=0;y<=size;y++){
                for(int z=-halfedSize;z<=halfedSize;z++){
                    BlockPos tmp = new BlockPos(exits.get(0).getX()+x,exits.get(0).getY()+y,exits.get(0).getZ()+z);
                    world.setBlockToAir(tmp);
                }
            }
        }
        //walls
        for(int x=-size/2;x<=size/2;x++){
            for(int y=0;y<=size;y++){
                BlockPos tmp1 = new BlockPos(exits.get(0).getX()+x,exits.get(0).getY()+y,exits.get(0).getZ());
                BlockPos tmp2 = new BlockPos(exits.get(0).getX()+x,exits.get(0).getY()+y,exits.get(0).getZ()+size);
                if(!((y==0||y==1)&&x==0)){
                    world.setBlockState(tmp1,wallState,2);
                    world.setBlockState(tmp2,wallState,2);
                }
            }
        }
        //walls
        for(int z=-size/2;z<=size/2;z++){
            for(int y=0;y<=size;y++){
                BlockPos tmp1 = new BlockPos(exits.get(0).getX()-size/2,exits.get(0).getY()+y,exits.get(0).getZ()+z);
                BlockPos tmp2 = new BlockPos(exits.get(0).getX()+size/2,exits.get(0).getY()+y,exits.get(0).getZ()+z);
                if(!((y==0||y==1)&&z==0)){
                    world.setBlockState(tmp1,wallState,2);
                    world.setBlockState(tmp2,wallState,2);
                }
            }
        }
        //floor
        for(int x=-size/2;x<=size/2;x++){
            for(int z=-size/2;z<=size/2;z++){
                BlockPos tmp = new BlockPos(exits.get(0).getX()+x,exits.get(0).getY()-1,exits.get(0).getZ()+z);
                world.setBlockState(tmp,floorState,2);
            }
        }
        //ceiling
        for(int x=-size/2;x<=size/2;x++){
            for(int z=-size/2;z<=size/2;z++){
                BlockPos tmp = new BlockPos(exits.get(0).getX()+x,exits.get(0).getY()+size,exits.get(0).getZ()+z);
                world.setBlockState(tmp,ceilingState,2);
            }
        }
        //do Exit corridors
        int corridorLength=6+random.nextInt(8);
        for(int length=0;length<=corridorLength;length++){
            BlockPos corridorCentre = new BlockPos(exits.get(0).getX(),exits.get(0).getY(),exits.get(0).getZ()+size+length);
            BlockPos corridorCentreUp = corridorCentre.up();
            BlockPos corridorWall1 = corridorCentre.west(); //unsure
            BlockPos corridorWall2 = corridorWall1.up();
            BlockPos corridorWall3 = corridorCentre.east();  //unsure
            BlockPos corridorWall4 = corridorWall3.up();
            BlockPos corridorFloor = corridorCentre.down();
            BlockPos corridorCeiling = corridorCentreUp.up();

            world.setBlockToAir(corridorCentre);
            world.setBlockToAir(corridorCentreUp);
            world.setBlockState(corridorWall1,corridorWallState,2);
            world.setBlockState(corridorWall2,corridorWallState,2);
            world.setBlockState(corridorWall3,corridorWallState,2);
            world.setBlockState(corridorWall4,corridorWallState,2);
            world.setBlockState(corridorFloor,corridorFloorState,2);
            world.setBlockState(corridorCeiling,corridorCeilingState,2);

            if(length==corridorLength){
                newExits.add(corridorCentre);
            }
        }
        //do Exit corridors
        corridorLength=6+random.nextInt(8);
        for(int length=0;length<=corridorLength;length++){
            BlockPos corridorCentre = new BlockPos(exits.get(0).getX()+size/2+length,exits.get(0).getY(),exits.get(0).getZ()+size/2);
            BlockPos corridorCentreUp = corridorCentre.up();
            BlockPos corridorWall1 = corridorCentre.north(); //unsure
            BlockPos corridorWall2 = corridorWall1.up();
            BlockPos corridorWall3 = corridorCentre.south();  //unsure
            BlockPos corridorWall4 = corridorWall3.up();
            BlockPos corridorFloor = corridorCentre.down();
            BlockPos corridorCeiling = corridorCentreUp.up();

            world.setBlockToAir(corridorCentre);
            world.setBlockToAir(corridorCentreUp);
            world.setBlockState(corridorWall1,corridorWallState,2);
            world.setBlockState(corridorWall2,corridorWallState,2);
            world.setBlockState(corridorWall3,corridorWallState,2);
            world.setBlockState(corridorWall4,corridorWallState,2);
            world.setBlockState(corridorFloor,corridorFloorState,2);
            world.setBlockState(corridorCeiling,corridorCeilingState,2);

            if(length==corridorLength){
                newExits.add(corridorCentre);
            }
        }
        //do Exit corridors
        corridorLength=6+random.nextInt(8);
        for(int length=0;length<=corridorLength;length++){
            BlockPos corridorCentre = new BlockPos(exits.get(0).getX()-size/2-length,exits.get(0).getY(),exits.get(0).getZ()+size/2);
            BlockPos corridorCentreUp = corridorCentre.up();
            BlockPos corridorWall1 = corridorCentre.north(); //unsure
            BlockPos corridorWall2 = corridorWall1.up();
            BlockPos corridorWall3 = corridorCentre.south();  //unsure
            BlockPos corridorWall4 = corridorWall3.up();
            BlockPos corridorFloor = corridorCentre.down();
            BlockPos corridorCeiling = corridorCentreUp.up();

            world.setBlockToAir(corridorCentre);
            world.setBlockToAir(corridorCentreUp);
            world.setBlockState(corridorWall1,corridorWallState,2);
            world.setBlockState(corridorWall2,corridorWallState,2);
            world.setBlockState(corridorWall3,corridorWallState,2);
            world.setBlockState(corridorWall4,corridorWallState,2);
            world.setBlockState(corridorFloor,corridorFloorState,2);
            world.setBlockState(corridorCeiling,corridorCeilingState,2);

            if(length==corridorLength){
                newExits.add(corridorCentre);
            }
        }

        roomCount++;
        if(roomCount<roomNumber){
            int exitNumber=random.nextInt(newExits.size());
            generate(world,random,newExits.get(exitNumber));
        }
        return true;
    }

    public boolean generateNew(World world, Random random, BlockPos pos1,BlockPos pos2){
        BlockPos entrance = new BlockPos(pos1.getX(),30,pos1.getZ());
        BlockPos oldEntrance = new BlockPos(pos2.getX(),30,pos2.getZ());
        IBlockState wallState = KCore.MythicStone.getDefaultState();
        IBlockState floorState = KCore.MythicStoneTiles.getDefaultState();
        IBlockState ceilingState = KCore.MythicStone.getDefaultState();
        ArrayList<BlockPos> exits = new ArrayList<BlockPos>();
        int corridorLength=12+random.nextInt(12);
        int size = MathHelper.clamp(random.nextInt(corridorLength/3),4,corridorLength/2-2);
        if(size%2!=0){
            size++;
        }
        //clear room space
        for(int x=-size/2;x<=size/2;x++){
            for(int y=0;y<=size;y++){
                for(int z=-size/2;z<=size/2;z++){
                    BlockPos tmp = new BlockPos(entrance.getX()+x,entrance.getY()+y,entrance.getZ()+z);
                    world.setBlockToAir(tmp);
                }
            }
        }

        //make corridors and exits
        for(int cc=0;cc<=corridorLength;cc++){
            BlockPos tmp1 = new BlockPos(entrance.getX(),entrance.getY(),entrance.getZ()+size/2+cc);
            BlockPos tmp2 = tmp1.up();
            BlockPos tmp3 = new BlockPos(entrance.getX()+size/2+cc,entrance.getY(),entrance.getZ());
            BlockPos tmp4 = tmp3.up();
            BlockPos tmp5 = new BlockPos(entrance.getX()-size/2-cc,entrance.getY(),entrance.getZ());
            BlockPos tmp6 = tmp5.up();
            BlockPos tmp7 = new BlockPos(entrance.getX(),entrance.getY(),entrance.getZ()-size/2-cc);
            BlockPos tmp8 = tmp7.up();
            world.setBlockToAir(tmp1);
            world.setBlockToAir(tmp2);
            world.setBlockToAir(tmp3);
            world.setBlockToAir(tmp4);
            world.setBlockToAir(tmp5);
            world.setBlockToAir(tmp6);
            world.setBlockToAir(tmp7);
            world.setBlockToAir(tmp8);
            exits.add(tmp1);
            exits.add(tmp3);
            exits.add(tmp5);
            exits.add(tmp7);

        }
        int k = random.nextInt(exits.size());
        while(exits.get(k)==oldEntrance){
            k=random.nextInt(exits.size());
        }
        roomCount++;
        while(MathHelper.abs(exits.get(k).getX()-oldEntrance.getX())<5&&MathHelper.abs(exits.get(k).getZ()-oldEntrance.getZ())<5){
            k=random.nextInt(exits.size());
        }
        if(roomCount<roomNumber){
            generateNew(world,random,exits.get(k),entrance);
            if(random.nextInt(3)==0) {
                int k2 = random.nextInt(exits.size());
                while(MathHelper.abs(exits.get(k2).getX()-oldEntrance.getX())<5&&MathHelper.abs(exits.get(k2).getZ()-oldEntrance.getZ())<5){
                    k2=random.nextInt(exits.size());
                }
                generateNew(world,random,exits.get(k2),entrance);
            }

        }

        return true;
    }



}