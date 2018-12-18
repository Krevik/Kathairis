package com.krevik.Commands;

import com.krevik.Dimension.KetherDataStorage;
import com.krevik.Main.KCore;

import com.krevik.Networking.KetherPacketHandler;
import com.krevik.Networking.PacketSandstormUpdatedOnClient;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;

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
			if(!world.isRemote) {
				KetherDataStorage data = KCore.data.getDataInstance(world);
				if(data!=null) {
						if(args[0]!=null) {
							if(Integer.parseInt(args[0])==0) {
								data.setIsSandstorm(false);
								data.setSandstormTime(Integer.parseInt(args[0]));
							}else {
								data.setIsSandstorm(true);
								data.setSandstormTime(Integer.parseInt(args[0]));
								double X=(world.rand.nextDouble()-world.rand.nextDouble());
								double Z=(world.rand.nextDouble()-world.rand.nextDouble());
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
