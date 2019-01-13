package mod.krevik.world.gen.desert.AncientLabirynth;

import mod.krevik.KCore;
import mod.krevik.block.BlockLeavesBase;
import mod.krevik.block.BlockMysticCloud;
import mod.krevik.block.BlockMysticLeaf;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.gui.GuiDownloadTerrain;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public class WorldGenCrystalMaze extends WorldGenerator {

    public WorldGenCrystalMaze(){

    }

    @Override
    public boolean generate(World world, Random random, BlockPos pos) {
        BlockPos topPos = world.getHeight(pos);
        if(world.getBlockState(topPos.down()).getBlock() instanceof BlockMysticCloud||pos.getY()>80||world.getBlockState(topPos.down()).getBlock() instanceof BlockLeavesBase){
            generate(world,random, new BlockPos(pos.getX()+random.nextInt(8)-random.nextInt(8),pos.getY()-20,pos.getZ()+random.nextInt(8)-random.nextInt(8)));
        }else{
            //gen Entrance
            BlockPos endedEntrance;
            int posY=topPos.getY();
            int posX=topPos.getX();
            int posZ=topPos.getZ();
            while(posY>29){
                generateStairsSlice(world,random,new BlockPos(posX,posY,posZ));
                posZ++;
                posY--;
                endedEntrance = new BlockPos(posX,posY,posZ);
            }
            //first Simple corridor
            int zShift=0;
            while(zShift<7){
                BlockPos tmp = new BlockPos(posX,posY,posZ);
                generateSimpleCorridorSlice1(world,random,tmp);
                posZ++;
                zShift++;
                endedEntrance = tmp;
            }
            //first room and generating rooms and corridors
            int desiredRoomNumber=5+random.nextInt(5);
            int currentRoomNumber=0;
            while(currentRoomNumber<desiredRoomNumber){
                if(currentRoomNumber==0) {
                    Room room = new Room(random);
                    BlockPos roomSize = room.getRoomSize();
                    ArrayList<BlockPos> exits = room.generateRoom(world, random, new BlockPos(posX, posY, posZ));
                    posX=exits.get(0).getX();
                    posY=posY;
                    posZ=exits.get(0).getZ();
                    //clear entrance to the first room
                    world.setBlockToAir(new BlockPos(posX,posY,posZ-roomSize.getZ()));
                    world.setBlockToAir(new BlockPos(posX,posY+1,posZ-roomSize.getZ()));
                }else{
                    Room room = new Room(random);
                    BlockPos roomSize = room.getRoomSize();
                    //do corridor to the room
                    int corridorLength = roomSize.getZ()+random.nextInt(5);
                    for(int i=0;i<=corridorLength;i++){
                        generateCorridorSliceWithSize(world,random,new BlockPos(posX,posY,posZ+1),2,3);
                        posZ++;
                    }
                    ArrayList<BlockPos> exits = room.generateRoom(world,random,new BlockPos(posX,posY,posZ));
                    posX=exits.get(0).getX();
                    posY=posY;
                    posZ=exits.get(0).getZ();
                    //clear entrance to the first room
                    world.setBlockToAir(new BlockPos(posX,posY,posZ-roomSize.getZ()));
                    world.setBlockToAir(new BlockPos(posX,posY+1,posZ-roomSize.getZ()));
                    //optionally generate additional corridors and rooms
                    if(random.nextInt(3)==0){
                        Room room2 = new Room(random);
                        BlockPos roomSize2 = room2.getRoomSize();
                        int corridorLength2 = roomSize2.getX()+random.nextInt(5);
                        for(int i=0;i<=corridorLength2;i++){
                            generateCorridorSliceWithSize(world,random,new BlockPos(posX+i,posY,posZ),1,3);
                        }
                        ArrayList<BlockPos> exits2 = room.generateRoom(world,random,new BlockPos(posX+corridorLength2,posY,posZ+(roomSize.getZ()+1)/2));
                        world.setBlockToAir(new BlockPos(posX-corridorLength2,posY,posZ-(roomSize.getZ()+1)/2));
                        world.setBlockToAir(new BlockPos(posX-corridorLength2,posY+1,posZ-(roomSize.getZ()+1)/2));
                    }
                    if(random.nextInt(3)==0){
                        Room room2 = new Room(random);
                        BlockPos roomSize2 = room2.getRoomSize();
                        int corridorLength2 = roomSize2.getX()+random.nextInt(5);
                        for(int i=0;i<=corridorLength2;i++){
                            generateCorridorSliceWithSize(world,random,new BlockPos(posX-i,posY,posZ),1,3);
                        }
                        ArrayList<BlockPos> exits2 = room.generateRoom(world,random,new BlockPos(posX-corridorLength2,posY,posZ+(roomSize.getZ()+1)/2));
                        world.setBlockToAir(new BlockPos(posX+corridorLength2,posY,posZ-(roomSize.getZ()+1)/2));
                        world.setBlockToAir(new BlockPos(posX+corridorLength2,posY+1,posZ-(roomSize.getZ()+1)/2));
                    }
                }


                currentRoomNumber++;
            }
        }
        return true;
    }

    private void generateStairsSlice(World world, Random random, BlockPos pos){
        for(int x=-2;x<=2;x++){
            for(int y=-1;y<=4;y++){
                int posX = pos.getX()+x;
                int posY = pos.getY()+y;
                BlockPos tmpPos = new BlockPos(posX,posY,pos.getZ());
                if((x==-2||x==2)||(y==-1||y==4)){
                    setBlock(world,tmpPos,KCore.WeatheredRockBricks.getDefaultState());
                }else{
                    world.setBlockToAir(tmpPos);
                }
            }
        }
    }

    private void generateSimpleCorridorSlice1(World world, Random random, BlockPos pos){
        for(int x=-2;x<=2;x++){
            for(int y=-1;y<=3;y++){
                int posX = pos.getX()+x;
                int posY = pos.getY()+y;
                BlockPos tmpPos = new BlockPos(posX,posY,pos.getZ());
                if((x==-2||x==2)||(y==-1||y==3)){
                    setBlock(world,tmpPos,KCore.WeatheredRockBricks.getDefaultState());
                }else{
                    world.setBlockToAir(tmpPos);
                }
            }
        }
    }

    private void generateCorridorSliceWithSize(World world, Random random, BlockPos pos, int eastwestORnorthsouth,int size){
        if(eastwestORnorthsouth==2) {
            for (int x = (-size + 1) / 2; x <= (size - 1) / 2; x++) {
                for (int y = -1; y <= 2; y++) {
                    BlockPos tmp = new BlockPos(pos.getX()+x,pos.getY()+y,pos.getZ());
                    if((x==(-size + 1) / 2||(x==(size - 1) / 2))||(y==-1||y==2)){
                        setBlock(world,tmp,KCore.WeatheredRockTiles.getDefaultState());
                    }else{
                        world.setBlockToAir(tmp);
                    }
                }
            }
        }
        if(eastwestORnorthsouth==1) {
            for (int z = (-size + 1) / 2; z <= (size - 1) / 2; z++) {
                for (int y = -1; y <= 2; y++) {
                    BlockPos tmp = new BlockPos(pos.getX(),pos.getY()+y,pos.getZ()+z);
                    if((z==(-size + 1) / 2||(z==(size - 1) / 2))||(y==-1||y==2)){
                        setBlock(world,tmp,KCore.WeatheredRockTiles.getDefaultState());
                    }else{
                        world.setBlockToAir(tmp);
                    }
                }
            }
        }
    }

    private void setBlock(World world, BlockPos pos, IBlockState state){
        world.setBlockState(pos,state,2);
    }

    private class Room{
        private BlockPos Size;
        private Random random;
        private int roomType;
        private BlockPos entrancePosition;
        public Room(Random rng){
            random=rng;
            initializeRoom();
        }

        private void initializeRoom(){
            roomType=0;
            int baseSize=2;
            int k=random.nextInt(10);
            if((baseSize+k)%2==0){
                k++;
            }
            Size=new BlockPos(baseSize+k,baseSize+2+random.nextInt(6),baseSize+k);
        }

        public BlockPos getRoomSize(){
            return Size;
        }

        public ArrayList<BlockPos> generateRoom(World world, Random random, BlockPos pos){
            if(roomType==0){
                for(int x=((-getRoomSize().getX()+1)/2);x<=((getRoomSize().getX()-1))/2;x++){
                    for(int y=-1;y<=getRoomSize().getY()-1;y++){
                        for(int z=0;z<=getRoomSize().getZ();z++){
                            BlockPos tmp = new BlockPos(pos.getX()+x,pos.getY()+y,pos.getZ()+z);
                            if((x==((-getRoomSize().getX()+1)/2)||x==((getRoomSize().getX()-1))/2)||
                                    ((z==0)||z==getRoomSize().getZ())||
                                    ((y==-1)||(y==getRoomSize().getY()-1))){
                                                    setBlock(world,tmp,KCore.WeatheredRockTiles.getDefaultState());
                                                                        }else{
                                                                        world.setBlockToAir(tmp);
                                                                        }
                        }
                    }
                }
            }
            entrancePosition=pos;
            //make exits and return their positions
            //first 100% exit
            BlockPos tmp1 = new BlockPos(pos.getX(),pos.getY(),pos.getZ()+getRoomSize().getZ());
            BlockPos tmp2 = new BlockPos(pos.getX(),pos.getY()+1,pos.getZ()+getRoomSize().getZ());
            world.setBlockToAir(tmp1);
            world.setBlockToAir(tmp2);
            //second potential exit
            BlockPos tmp3 = new BlockPos(pos.getX()+((getRoomSize().getX()+1)/2),pos.getY(),pos.getZ()+(getRoomSize().getZ()+1)/2);
            BlockPos tmp4 = tmp3.up();
            world.setBlockToAir(tmp3);
            world.setBlockToAir(tmp4);
            //third potential exit
            BlockPos tmp5 = new BlockPos(pos.getX()-((getRoomSize().getX()+1)/2),pos.getY(),pos.getZ()+(getRoomSize().getZ()+1)/2);
            BlockPos tmp6 = tmp5.up();
            world.setBlockToAir(tmp5);
            world.setBlockToAir(tmp6);

            ArrayList<BlockPos> exits = new ArrayList<>();
            if(tmp1.getX()!=entrancePosition.getX()||tmp1.getZ()!=entrancePosition.getZ()){
                exits.add(tmp1);
            }
            if(random.nextInt(3)==0) {
                if (tmp3.getX() != entrancePosition.getX() || tmp3.getZ() != entrancePosition.getZ()) {
                    exits.add(tmp3);
                }
            }
            if(random.nextInt(4)==0) {
                if (tmp5.getX() != entrancePosition.getX() || tmp5.getZ() != entrancePosition.getZ()) {
                    exits.add(tmp5);
                }
            }
            return exits;
        }

    }

}
