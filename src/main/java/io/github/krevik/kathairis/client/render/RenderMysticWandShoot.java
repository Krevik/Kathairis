package io.github.krevik.kathairis.client.render;

import io.github.krevik.kathairis.Kathairis;
import io.github.krevik.kathairis.entity.EntityMysticWandShoot;
import io.github.krevik.kathairis.entity.EntityPhasm;
import io.github.krevik.kathairis.init.ModParticles;
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
import net.minecraft.init.Particles;
import net.minecraft.particles.IParticleData;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;

import javax.annotation.Nullable;

import static io.github.krevik.kathairis.util.ModReference.MOD_ID;

@OnlyIn(Dist.CLIENT)
public class RenderMysticWandShoot extends Render<EntityMysticWandShoot> {

    public RenderMysticWandShoot(RenderManager p_i46176_1_) {
        super(p_i46176_1_);
    }

    public RenderMysticWandShoot() {
        super(Minecraft.getInstance().getRenderManager());
    }

    public static class Factory implements IRenderFactory<EntityMysticWandShoot> {

        @Override
        public Render<? super EntityMysticWandShoot> createRenderFor(RenderManager manager) {
            return new RenderMysticWandShoot(manager);
        }

    }

    public void doRender(EntityMysticWandShoot shoot, double par1, double par2, double par3, float par4, float par5) {
        super.doRender(shoot,par1,par2,par3,par4,par5);
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntityMysticWandShoot entityMysticWandShoot) {
        return null;
    }

}
