package mod.krevik.client.sound;


import mod.krevik.KCore;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

public abstract class SoundEventHandlerMystic extends SoundEvent {
	public SoundEventHandlerMystic(String name) {
		super(new ResourceLocation(KCore.MODID, name));
		this.setRegistryName(new ResourceLocation(KCore.MODID, name));
	}

	public void register() {
		GameRegistry.findRegistry(SoundEvent.class).register(this);
	}
	
}