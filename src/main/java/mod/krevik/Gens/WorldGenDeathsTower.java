package mod.krevik.Gens;

import java.util.Random;

import mod.krevik.Main.KCore;

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

public class WorldGenDeathsTower extends WorldGenerator{
    private static final PlacementSettings DEFAULT_PLACE_SETTINGS = new PlacementSettings();
    private Template template;
    
	@Override
	public boolean generate(World world, Random rand, BlockPos position) {
		world.getChunkProvider().provideChunk(position.getX(), position.getZ());
		WorldServer worldserver = (WorldServer) world;
		MinecraftServer minecraftserver = world.getMinecraftServer();
		TemplateManager templatemanager = worldserver.getStructureTemplateManager();
		Template template = templatemanager.getTemplate(minecraftserver, new ResourceLocation(KCore.MODID+":deathstower"));
		
		if(template == null)
		{
			System.out.println("NO STRUCTURE");
			return false;
		}
		
			IBlockState iblockstate = world.getBlockState(position);
			world.notifyBlockUpdate(position, iblockstate, iblockstate, 3);
			PlacementSettings placementsettings = (new PlacementSettings()).setMirror(Mirror.NONE)
					.setRotation(Rotation.NONE).setIgnoreEntities(false).setChunk((ChunkPos) null)
					.setReplacedBlock((Block) null).setIgnoreStructureBlock(true);
			template.addBlocksToWorld(world, position.add(0, 1, 0), placementsettings);
			return true;
		
		
	}

}
