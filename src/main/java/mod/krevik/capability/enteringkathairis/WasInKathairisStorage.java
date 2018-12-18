package mod.krevik.capability.enteringkathairis;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;

import javax.annotation.Nullable;

public class WasInKathairisStorage implements Capability.IStorage<IWasInKathairisCapability> {

    @Nullable
    @Override
    public NBTBase writeNBT(Capability<IWasInKathairisCapability> capability, IWasInKathairisCapability instance, EnumFacing side) {
        NBTTagCompound tag = new NBTTagCompound();
        tag.setBoolean("wasInKathairis", instance.wasInKathairisBefore());
        return tag;
    }

    @Override
    public void readNBT(Capability<IWasInKathairisCapability> capability, IWasInKathairisCapability instance, EnumFacing side, NBTBase nbt) {
        if (nbt instanceof NBTTagCompound) {
            NBTTagCompound tag = (NBTTagCompound) nbt;
            instance.setWasInKathairis(tag.getBoolean("wasInKathairis"));
        }
    }
}
