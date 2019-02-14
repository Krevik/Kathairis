package mod.krevik.client.gui;

import mod.krevik.KCore;
import mod.krevik.container.ContainerCharger;
import mod.krevik.entity.EntityDeath;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

import java.util.List;

public class GuiHandler implements IGuiHandler
{

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity tileEntity = world.getTileEntity(new BlockPos(x, y, z));

        if (tileEntity != null)
        {
            if (ID == KCore.GUI_ENUM.CHARGER.ordinal())
            {
                return new ContainerCharger(player.inventory, 
                      (IInventory)tileEntity);
            }
        }

        return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity tileEntity = world.getTileEntity(new BlockPos(x, y, z));

        if (ID == KCore.GUI_ENUM.OLDMAN.ordinal())
        {
            return new GuiOldMan();
        }
        if (ID == KCore.GUI_ENUM.DEATH.ordinal())
        {
            List<EntityDeath> e = world.getEntitiesWithinAABB(EntityDeath.class, new AxisAlignedBB(player.posX - 5,player.posY - 5, player.posZ - 5, player.posX  + 5, player.posY + 5, player.posZ + 5));
            return new GuiDeath(e.get(0));
        }
        if (tileEntity != null)
        {
            if (ID == KCore.GUI_ENUM.CHARGER.ordinal())
            {
                return new GuiCharger(player.inventory, 
                      (IInventory)tileEntity);
            }
        }
        return null;
	}
}