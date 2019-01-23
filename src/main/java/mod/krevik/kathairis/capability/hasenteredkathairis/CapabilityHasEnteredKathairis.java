package mod.krevik.kathairis.capability.hasenteredkathairis;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;

public class CapabilityHasEnteredKathairis {

	@CapabilityInject(HasEnteredKathairis.class)
	public static Capability<HasEnteredKathairis> HAS_ENTERED_KATHAIRIS_CAPABILITY = null;

	public static final String NBT_TAG = "hasEnteredKathairis";

	public static void register() {
		CapabilityManager.INSTANCE.register(HasEnteredKathairis.class, new IStorage<HasEnteredKathairis>() {
			@Override
			public NBTBase writeNBT(Capability<HasEnteredKathairis> capability, HasEnteredKathairis instance, EnumFacing side) {
				NBTTagCompound tag = new NBTTagCompound();
				tag.setBoolean(NBT_TAG, instance.hasEnteredKathairis());
				return tag;
			}

			@Override
			public void readNBT(Capability<HasEnteredKathairis> capability, HasEnteredKathairis instance, EnumFacing side, NBTBase nbt) {
				if (nbt instanceof NBTTagCompound) {
					NBTTagCompound tag = (NBTTagCompound) nbt;
					instance.setHasEnteredKathairis(tag.getBoolean(NBT_TAG));
				}
			}
		}, HasEnteredKathairis::new);
	}

}
