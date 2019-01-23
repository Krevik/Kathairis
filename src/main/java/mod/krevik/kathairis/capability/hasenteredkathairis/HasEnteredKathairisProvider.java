package mod.krevik.kathairis.capability.hasenteredkathairis;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

import javax.annotation.Nonnull;

import static mod.krevik.kathairis.capability.hasenteredkathairis.CapabilityHasEnteredKathairis.HAS_ENTERED_KATHAIRIS_CAPABILITY;

/**
 * @author Cadiboo
 */
public class HasEnteredKathairisProvider implements ICapabilitySerializable<NBTTagCompound> {

	private final HasEnteredKathairis capabilityInstance = HAS_ENTERED_KATHAIRIS_CAPABILITY.getDefaultInstance();

	@Override
	public boolean hasCapability(@Nonnull Capability<?> capability, EnumFacing facing) {
		return capability == HAS_ENTERED_KATHAIRIS_CAPABILITY;
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T> T getCapability(@Nonnull Capability<T> capability, EnumFacing facing) {
		if (capability == HAS_ENTERED_KATHAIRIS_CAPABILITY) {
			return (T) capabilityInstance;
		} else {
			return null;
		}
	}

	@Override
	public NBTTagCompound serializeNBT() {
		return (NBTTagCompound) HAS_ENTERED_KATHAIRIS_CAPABILITY.getStorage().writeNBT(HAS_ENTERED_KATHAIRIS_CAPABILITY, capabilityInstance, null);
	}

	@Override
	public void deserializeNBT(NBTTagCompound nbt) {
		HAS_ENTERED_KATHAIRIS_CAPABILITY.getStorage().readNBT(HAS_ENTERED_KATHAIRIS_CAPABILITY, capabilityInstance, null, nbt);
	}

}
