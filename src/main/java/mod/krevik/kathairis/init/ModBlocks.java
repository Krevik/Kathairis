package mod.krevik.kathairis.init;

import mod.krevik.kathairis.block.BlockCharger;
import mod.krevik.kathairis.block.BlockKatharisPortal;
import mod.krevik.kathairis.block.BlockModOre;
import mod.krevik.kathairis.block.BlockResource;
import net.minecraft.block.Block;
import net.minecraft.block.BlockPortal;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;

import static mod.krevik.kathairis.util.ModReference.MOD_ID;

/**
 * {@link Block} Instances class
 * All the blocks in here will be public static final and null as their values will be filled by the magical @ObjectHolder
 *
 * @author Cadiboo
 */
@ObjectHolder(MOD_ID)
public final class ModBlocks {

	public static final BlockResource TITANIUM_BLOCK = null;
	public static final BlockModOre TITANIUM_ORE = null;
	public static final BlockCharger CHARGER = null;

	public static final BlockKatharisPortal KATHAIRIS_PORTAL = null;

}
