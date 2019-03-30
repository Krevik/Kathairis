//package mod.krevik.kathairis.world.dimension.biome.gen_layers;
//
//import mod.krevik.kathairis.Kathairis;
//import net.minecraft.util.registry.IRegistry;
//import net.minecraft.world.gen.IContext;
//import net.minecraft.world.gen.layer.traits.ICastleTransformer;
//
///**
// * @author Krevik
// */
//public enum GenLayerKatharianRiver implements ICastleTransformer {
//	INSTANCE;
//
//	public static final int RIVER = IRegistry.BIOME.getId(Kathairis.BIOME_KATHARIAN_RIVER);
//
//	private GenLayerKatharianRiver() {
//
//	}
//
//	private static int riverFilter(int p_151630_0_) {
//		return p_151630_0_ >= 2 ? 2 + (p_151630_0_ & 1) : p_151630_0_;
//	}
//
//	@Override
//	public int apply(IContext p_202748_1_, int p_202748_2_, int p_202748_3_, int p_202748_4_, int p_202748_5_, int p_202748_6_) {
//		int lvt_7_1_ = riverFilter(p_202748_6_);
//		return lvt_7_1_ == riverFilter(p_202748_5_) && lvt_7_1_ == riverFilter(p_202748_2_) && lvt_7_1_ == riverFilter(p_202748_3_) && lvt_7_1_ == riverFilter(p_202748_4_) ? -1 : RIVER;
//	}
//}
