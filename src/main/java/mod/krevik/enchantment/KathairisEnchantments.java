package mod.krevik.enchantment;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.enchantment.Enchantment;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class KathairisEnchantments {
	
	public static final List<Enchantment> ENCHANTMENTS = new ArrayList<Enchantment>();
	public static final Enchantment Ethereal = addEnchantment(new EtherealEnchantement(), "ethereal");

	public static Enchantment addEnchantment(Enchantment enchantment, String name) {
		ENCHANTMENTS.add(enchantment);
		return enchantment.setRegistryName(name).setName(name);
	}
	
	@SubscribeEvent
	public static void registerEnchantments(Register<Enchantment> event) {
		for(Enchantment enchantment: ENCHANTMENTS)
			event.getRegistry().register(enchantment);
	}
}
