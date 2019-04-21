package io.github.krevik.kathairis.client.render;

import io.github.krevik.kathairis.entity.EntityMysticWandShoot;
import io.github.krevik.kathairis.entity.EntityPhasm;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderFireball;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.init.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;

@OnlyIn(Dist.CLIENT)
public class RenderMysticWandShoot extends RenderFireball {
    private final float scale;

    public RenderMysticWandShoot(RenderManager p_i46176_1_, float p_i46176_2_) {
        super(p_i46176_1_,p_i46176_2_);
        this.scale = p_i46176_2_;
    }

    public RenderMysticWandShoot() {
        super(Minecraft.getInstance().getRenderManager(),1);
        this.scale = 1;
    }

    public static class Factory implements IRenderFactory<EntityMysticWandShoot> {

        @Override
        public Render<? super EntityMysticWandShoot> createRenderFor(RenderManager manager) {
            return new RenderMysticWandShoot(manager,1);
        }

    }

    public void doRender(EntityFireball p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_) {
        GlStateManager.pushMatrix();
        this.bindEntityTexture(p_76986_1_);
        GlStateManager.translatef((float)p_76986_2_, (float)p_76986_4_, (float)p_76986_6_);
        GlStateManager.enableRescaleNormal();
        GlStateManager.scalef(this.scale, this.scale, this.scale);
        TextureAtlasSprite lvt_10_1_ = Minecraft.getInstance().getItemRenderer().getItemModelMesher().getParticleIcon(Items.FIRE_CHARGE);
        Tessellator lvt_11_1_ = Tessellator.getInstance();
        BufferBuilder lvt_12_1_ = lvt_11_1_.getBuffer();
        float lvt_13_1_ = lvt_10_1_.getMinU();
        float lvt_14_1_ = lvt_10_1_.getMaxU();
        float lvt_15_1_ = lvt_10_1_.getMinV();
        float lvt_16_1_ = lvt_10_1_.getMaxV();
        float lvt_17_1_ = 1.0F;
        float lvt_18_1_ = 0.5F;
        float lvt_19_1_ = 0.25F;
        GlStateManager.rotatef(180.0F - this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotatef((float)(this.renderManager.options.thirdPersonView == 2 ? -1 : 1) * -this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
        if (this.renderOutlines) {
            GlStateManager.enableColorMaterial();
            GlStateManager.enableOutlineMode(this.getTeamColor(p_76986_1_));
        }

        lvt_12_1_.begin(7, DefaultVertexFormats.POSITION_TEX_NORMAL);
        lvt_12_1_.pos(-0.5D, -0.25D, 0.0D).tex((double)lvt_13_1_, (double)lvt_16_1_).normal(0.0F, 1.0F, 0.0F).endVertex();
        lvt_12_1_.pos(0.5D, -0.25D, 0.0D).tex((double)lvt_14_1_, (double)lvt_16_1_).normal(0.0F, 1.0F, 0.0F).endVertex();
        lvt_12_1_.pos(0.5D, 0.75D, 0.0D).tex((double)lvt_14_1_, (double)lvt_15_1_).normal(0.0F, 1.0F, 0.0F).endVertex();
        lvt_12_1_.pos(-0.5D, 0.75D, 0.0D).tex((double)lvt_13_1_, (double)lvt_15_1_).normal(0.0F, 1.0F, 0.0F).endVertex();
        lvt_11_1_.draw();
        if (this.renderOutlines) {
            GlStateManager.disableOutlineMode();
            GlStateManager.disableColorMaterial();
        }

        GlStateManager.disableRescaleNormal();
        GlStateManager.popMatrix();
        super.doRender(p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
    }

    protected ResourceLocation getEntityTexture(EntityFireball p_110775_1_) {
        return TextureMap.LOCATION_BLOCKS_TEXTURE;
    }
}
