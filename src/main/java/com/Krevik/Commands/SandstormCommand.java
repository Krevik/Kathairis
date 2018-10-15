package com.Krevik.Commands;

import com.Krevik.Dimension.KetherDataStorage;
import com.Krevik.Main.KCore;

import com.Krevik.Networking.KetherPacketHandler;
import com.Krevik.Networking.PacketSandstormUpdatedOnClient;
import com.Krevik.Networking.PacketSandstormUpdatedOnServer;
import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.fml.common.FMLCommonHandler;

public class SandstormCommand extends CommandBase{

	public SandstormCommand() {
		
	}
	
	@Override
	public String getName() {
		String Name = "sandstorm";
		return Name;
	}

	@Override
	public String getUsage(ICommandSender sender) {
		return "commands.sandstorm.usage";
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		if(args.length<=0) {
            throw new WrongUsageException("/sandstorm <time>", new Object[0]);
		}else {
			World world = sender.getEntityWorld();
			KetherDataStorage data = KCore.data.getDataInstance(world);
			if(!world.isRemote) {
					if(data!=null) {
						if(args[0]!=null) {
							if(Integer.parseInt(args[0])==0) {
								data.setIsSandstorm(false);
								data.setSandstormTime(Integer.parseInt(args[0]));
							}else {
								data.setIsSandstorm(true);
								data.setSandstormTime(Integer.parseInt(args[0]));
								float X=(float)(world.rand.nextDouble()-world.rand.nextDouble());
								float Z=(float)(world.rand.nextDouble()-world.rand.nextDouble());
								data.setSandstormX(X);
								data.setSandstormZ(Z);
								PacketSandstormUpdatedOnClient message = new PacketSandstormUpdatedOnClient(true, X, Integer.parseInt(args[0]),Z);
								KetherPacketHandler.CHANNEL.sendToAll(message);
							}
		                    notifyCommandListener(sender, this, "sandstorm time set to: "+Integer.parseInt(args[0]), new Object[] {Integer.parseInt(args[0]), sender.getName()});
						}else {
				            throw new WrongUsageException("/sandstorm <time>", new Object[0]);
						}
					}else {
			            throw new WrongUsageException("Kathairis data storage is not loaded!", new Object[0]);	
					}
			}else {
	            throw new WrongUsageException("Cannot proccess on client side!", new Object[0]);
			}
		}
		
	}
	
}
