package io.github.krevik.kathairis.init;

import net.minecraft.block.SoundType;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.ObjectHolder;

import static io.github.krevik.kathairis.util.ModReference.MOD_ID;

/**
 * @author Cadiboo
 */
@ObjectHolder(MOD_ID)
public final class ModSounds {

	public static SoundHelper BIRD = new SoundHelper("bird", "bird");
	public static SoundHelper HOWLER_LIVING = new SoundHelper("howler", "living");
	public static SoundHelper HOWLER_DEAD = new SoundHelper("howler", "dead");
	public static SoundHelper HOWLER_HURT = new SoundHelper("howler", "hurt");
	public static SoundHelper JELLYFISH_LIVING = new SoundHelper("jellyfish", "living");
	public static SoundHelper JELLYFISH_DEAD = new SoundHelper("jellyfish", "dead");
	public static SoundHelper JELLYFISH_HURT = new SoundHelper("jellyfish", "hurt");
	public static SoundHelper TURTLE_DEAD = new SoundHelper("turtle", "dead");
	public static SoundHelper BISON_DEAD = new SoundHelper("bison", "dead");
	public static SoundHelper BISON_HURT = new SoundHelper("bison", "hurt");
	public static SoundHelper BISON_LIVING = new SoundHelper("bison", "living");
	public static SoundEvent KATHAIRIS_MUSIC_DAY = new SoundHelper("music.day");
	public static SoundEvent KATHAIRIS_MUSIC_NIGHT = new SoundHelper("music.night");
	public static SoundHelper GHOST_LIVING = new SoundHelper("ghost", "living");
	public static SoundHelper GHOST_ATTACK = new SoundHelper("ghost", "attack");
	public static SoundHelper GHOST_DEAD = new SoundHelper("ghost", "dead");
	public static SoundHelper DEATH_LIVING = new SoundHelper("death", "living");
	public static SoundHelper SCARY_FLOWER = new SoundHelper("scary", "flower");
	public static SoundHelper CAMEL_DEAD = new SoundHelper("camel", "dead");
	public static SoundHelper CAMEL_HURT = new SoundHelper("camel", "hurt");
	public static SoundHelper CAMEL_BREATH = new SoundHelper("camel", "breath");
	public static SoundHelper CAMEL_AMBIENT = new SoundHelper("camel", "ambient");
	public static SoundHelper OLDMAN_AMBIENT = new SoundHelper("oldman", "ambient");
	public static SoundHelper SKYRAY_AMBIENT = new SoundHelper("skyray", "ambient");
	public static SoundHelper SKYRAY_HURT = new SoundHelper("skyray", "hurt");
	public static SoundHelper CLOUD_GLASS_BREAK = new SoundHelper("cloudglass.break");
	public static SoundHelper SANDSTORM = new SoundHelper("sandstorm");
	public static SoundHelper PICKAXE_TURN = new SoundHelper("pickaxe_turn");
	public static SoundEvent KATHAIRIS_MUSIC_CHRISTMAS = new SoundHelper("music.xmas");
	public static SoundType CLOUD_GLASS = new SoundType(1.0F, 1.0F, CLOUD_GLASS_BREAK, SoundEvents.BLOCK_GLASS_STEP, SoundEvents.BLOCK_GLASS_PLACE, SoundEvents.BLOCK_GLASS_HIT, SoundEvents.BLOCK_GLASS_FALL);
	SoundEvent MUSIC_DISC_JAZZY = new SoundHelper("music_disc.jazzy");
	SoundEvent MUSIC_DISC_8BIT = new SoundHelper("music_disc.8bit");

	private static final class SoundHelper extends SoundHandlerBase {

		public SoundHelper(String mob, String subset) {
			super("mob." + mob + "." + subset);
			register();
		}

		public SoundHelper(String Name) {
			super("kathairis." + Name);
			register();
		}

	}

	private static class SoundHandlerBase extends SoundEvent {

		SoundHandlerBase(String name) {
			super(new ResourceLocation(MOD_ID, name));
			setRegistryName(MOD_ID, name);
		}

		public void register() {
			GameRegistry.findRegistry(SoundEvent.class).register(this);
		}

	}

}
