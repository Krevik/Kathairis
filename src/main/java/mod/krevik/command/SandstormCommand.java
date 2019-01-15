package mod.krevik.command;

import mod.krevik.network.KathairisPacketHandler;
import mod.krevik.world.dimension.KetherDataStorage;
import mod.krevik.KCore;

import mod.krevik.network.packets.PacketSandstormUpdatedOnClient;
import net.minecraft.command.*;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;

public class SandstormCommand extends CommandBase {

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
		if (args.length <= 0) {
			throw new WrongUsageException("/sandstorm <time>|<true/false>", new Object[0]);
		} else {
			if (sender.getEntityWorld().provider.getDimension() == KCore.DIMENSION_ID) {
				World world = sender.getEntityWorld();
				String word1 = args[0];
				if (!world.isRemote) {
					KetherDataStorage data = KCore.data.getDataInstance(world);
					if (data != null) {
						if (word1 != null) {
							if (word1.equalsIgnoreCase("true") || word1.equalsIgnoreCase("false")) {
								if (word1.equalsIgnoreCase("false")) {
									data.setIsSandstorm(false);
									data.setSandstormTime(0);
									PacketSandstormUpdatedOnClient message = new PacketSandstormUpdatedOnClient(false, 0, 0, 0);
									KathairisPacketHandler.CHANNEL.sendToAll(message);
									notifyCommandListener(sender, this, "Sandstorm time set to: " + 0, new Object[]{0, sender.getName()});
								}
								if (word1.equalsIgnoreCase("true")) {
									int time = 9000 + world.rand.nextInt(20000);
									data.setIsSandstorm(true);
									data.setSandstormTime(time);
									double X = (world.rand.nextDouble() - world.rand.nextDouble());
									double Z = (world.rand.nextDouble() - world.rand.nextDouble());
									data.setSandstormX(X);
									data.setSandstormZ(Z);
									PacketSandstormUpdatedOnClient message = new PacketSandstormUpdatedOnClient(true, X, time, Z);
									KathairisPacketHandler.CHANNEL.sendToAll(message);
									notifyCommandListener(sender, this, "Sandstorm time set to: " + time, new Object[]{0, sender.getName()});
								}
							} else {
								if (Integer.parseInt(word1) > 0) {
									int time = Integer.parseInt(word1);
									data.setIsSandstorm(true);
									data.setSandstormTime(time);
									double X = (world.rand.nextDouble() - world.rand.nextDouble());
									double Z = (world.rand.nextDouble() - world.rand.nextDouble());
									data.setSandstormX(X);
									data.setSandstormZ(Z);
									PacketSandstormUpdatedOnClient message = new PacketSandstormUpdatedOnClient(true, X, time, Z);
									KathairisPacketHandler.CHANNEL.sendToAll(message);
									notifyCommandListener(sender, this, "Sandstorm time set to: " + time, new Object[]{0, sender.getName()});
								}
								else if (Integer.parseInt(word1) == 0) {
									data.setIsSandstorm(false);
									data.setSandstormTime(0);
									PacketSandstormUpdatedOnClient message = new PacketSandstormUpdatedOnClient(false, 0, 0, 0);
									KathairisPacketHandler.CHANNEL.sendToAll(message);
									notifyCommandListener(sender, this, "Sandstorm time set to: " + 0, new Object[]{0, sender.getName()});
								}else{
									throw new WrongUsageException("/sandstorm <time>|<true/false>", new Object[0]);
								}
							}
						} else {
							throw new WrongUsageException("/sandstorm <time>|<true/false>", new Object[0]);
						}
					} else {
						throw new WrongUsageException("Kathairis data storage is not loaded!", new Object[0]);
					}
			/*if(!world.isRemote) {
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
								KathairisPacketHandler.CHANNEL.sendToAll(message);
							}
		                    notifyCommandListener(sender, this, "sandstorm time set to: "+Integer.parseInt(args[0]), new Object[] {Integer.parseInt(args[0]), sender.getName()});
						}
					}
			}*/
				}else{

				}
			}else{
				throw new WrongUsageException("You must be in kathairis in order to proccess this command!", new Object[0]);
			}
		}
	}
}
