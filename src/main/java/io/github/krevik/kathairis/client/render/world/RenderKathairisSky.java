package io.github.krevik.kathairis.client.render.world;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import io.github.krevik.kathairis.Kathairis;
import io.github.krevik.kathairis.util.FunctionHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.Vector4f;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.IRenderHandler;

import javax.vecmath.Vector4d;
import java.util.ArrayList;
import java.util.Random;

import static io.github.krevik.kathairis.util.ModReference.MOD_ID;

public class RenderKathairisSky implements IRenderHandler {


    private static final ResourceLocation MOON_PHASES_TEXTURES = new ResourceLocation(MOD_ID,"textures/environment/moon_phases.png");
    private static final ResourceLocation SUN_TEXTURES = new ResourceLocation(MOD_ID,"textures/environment/sun.png");

    public RenderKathairisSky()
    {

    }

    private int[] constantLight = new int[3000];
    private ArrayList<FallingStar> fallingStarsList = new ArrayList();
    private FunctionHelper helper = Kathairis.getHelper();

    @Override
    @OnlyIn(Dist.CLIENT)
    public void render(int someInt, float partialTicks, ClientWorld world, Minecraft mc) {
        RenderSystem.disableTexture();
        Vec3d vec3d = world.getSkyColor(mc.player.getPosition(), partialTicks);
        float f = (float)vec3d.x;
        float f1 = (float)vec3d.y;
        float f2 = (float)vec3d.z;
        RenderSystem.color3f(f, f1, f2);
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();
        RenderSystem.depthMask(false);
        RenderSystem.enableFog();

        RenderSystem.disableFog();
        RenderSystem.disableAlphaTest();
        RenderSystem.enableBlend();
        RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        RenderHelper.disableStandardItemLighting();

        //stars
        if(world.getDayTime()>13000&&world.getDayTime()<=23000) {
            for (int x = 0; x < 3000; x++) {
                if (constantLight[x] >= 255) {
                    constantLight[x] -= helper.getRandomInteger(0, 8);
                } else if (constantLight[x] <= 0) {
                    constantLight[x] = helper.getRandomInteger(0, 256);
                } else {
                    constantLight[x] += (helper.getRandomInteger(0, 8) - helper.getRandomInteger(0, 8));
                }
            }
            RenderSystem.pushMatrix();
            Random random = new Random(10842L);
            bufferbuilder.begin(7, DefaultVertexFormats.POSITION_COLOR);
            for (int i = 0; i < 3000; ++i) {
                double d0 = (double) (random.nextFloat() * 2.0F - 1.0F);
                double d1 = (double) (random.nextFloat() * 2.0F - 1.0F);
                double d2 = (double) (random.nextFloat() * 2.0F - 1.0F);
                double d33 = (double) (0.15F + random.nextFloat() * 0.1F);
                double d4 = d0 * d0 + d1 * d1 + d2 * d2;

                if (d4 < 1.0D && d4 > 0.01D) {
                    d4 = 1.0D / Math.sqrt(d4);
                    d0 = d0 * d4;
                    d1 = d1 * d4;
                    d2 = d2 * d4;
                    double d5 = d0 * 100.0D;
                    double d6 = d1 * 100.0D;
                    double d7 = d2 * 100.0D;
                    double d8 = Math.atan2(d0, d2);
                    double d9 = Math.sin(d8);
                    double d10 = Math.cos(d8);
                    double d11 = Math.atan2(Math.sqrt(d0 * d0 + d2 * d2), d1);
                    double d12 = Math.sin(d11);
                    double d13 = Math.cos(d11);
                    double d14 = random.nextDouble() * Math.PI * 2.0D;
                    double d15 = Math.sin(d14);
                    double d16 = Math.cos(d14);

                    for (int j = 0; j < 4; ++j) {
                        Vector4f color = new Vector4f(helper.getRandomInteger(10842L, 66, 137), helper.getRandomInteger(10842L, 65, 244), helper.getRandomInteger(10842L, 229, 244), constantLight[i]);
                        double d18 = (double) ((j & 2) - 1) * d33;
                        double d19 = (double) ((j + 1 & 2) - 1) * d33;
                        double d21 = d18 * d16 - d19 * d15;
                        double d22 = d19 * d16 + d18 * d15;
                        double d23 = d21 * d12 + 0.0D * d13;
                        double d24 = 0.0D * d12 - d21 * d13;
                        double d25 = d24 * d9 - d22 * d10;
                        double d26 = d22 * d9 + d24 * d10;
                        bufferbuilder.pos(d5 + d25, d6 + d23, d7 + d26).color((int) color.x, (int) color.y, (int) color.z, (int) color.w).endVertex();

                    }
                }
            }

            tessellator.draw();
            RenderSystem.popMatrix();

            RenderSystem.pushMatrix();
            if (helper.getRandom().nextInt(100) == 0) {
                random = new Random();
                //generate falling star
                double d0 = (double) (random.nextFloat() * 6.0F - 2F);
                double d1 = (double) (random.nextFloat() * 6.0F - 2F);
                double d2 = (double) (random.nextFloat() * 6.0F - 2F);
                double d33 = (double) (0.15F + random.nextFloat() * 0.1F);
                double d4 = d0 * d0 + d1 * d1 + d2 * d2;

                d4 = 1.0D / Math.sqrt(d4);
                d0 = d0 * d4;
                d1 = d1 * d4;
                d2 = d2 * d4;
                double d5 = d0 * 60.0D;
                double d6 = d1 * 60.0D;
                double d7 = d2 * 60.0D;
                double d8 = Math.atan2(d0, d2);
                double d9 = Math.sin(d8);
                double d10 = Math.cos(d8);
                double d11 = Math.atan2(Math.sqrt(d0 * d0 + d2 * d2), d1);
                double d12 = Math.sin(d11);
                double d13 = Math.cos(d11);
                double d14 = random.nextDouble() * Math.PI * 2.0D;
                double d15 = Math.sin(d14);
                double d16 = Math.cos(d14);

                double d18 = (double) ((0 & 2) - 1) * d33;
                double d19 = (double) ((1 + 1 & 2) - 1) * d33;
                double d21 = d18 * d16 - d19 * d15;
                double d22 = d19 * d16 + d18 * d15;
                double d23 = d21 * d12 + 0.0D * d13;
                double d24 = 0.0D * d12 - d21 * d13;
                double d25 = d24 * d9 - d22 * d10;
                double d26 = d22 * d9 + d24 * d10;
                FallingStar star = new FallingStar(this.fallingStarsList.size(), d5 + d25, d6 + d23, d7 + d26, -0.5F + random.nextFloat(), -0.5F + random.nextFloat(), -0.5F + random.nextFloat(), new Random().nextLong());
                this.fallingStarsList.add(star);
            }

            //operate existing falling stars
            bufferbuilder.begin(7, DefaultVertexFormats.POSITION_COLOR);
            for (int x = 0; x < this.fallingStarsList.size(); x++) {
                if (this.fallingStarsList.get(x) != null) {
                    FallingStar star = this.fallingStarsList.get(x);
                    star.update();
                    Long starSeed = star.getSeed();

                    random = new Random(starSeed);
                    double d0 = (double) (random.nextFloat() * 2.0F - 1.0F);
                    double d1 = (double) (random.nextFloat() * 2.0F - 1.0F);
                    double d2 = (double) (random.nextFloat() * 2.0F - 1.0F);
                    double d33 = (double) (0.15F + random.nextFloat() * 0.1F);
                    double d4 = d0 * d0 + d1 * d1 + d2 * d2;
                    d4 = 1.0D;
                    d0 = d0 * d4;
                    d1 = d1 * d4;
                    d2 = d2 * d4;
                    double d5 = d0 * 100.0D;
                    double d6 = d1 * 100.0D;
                    double d7 = d2 * 100.0D;
                    double d8 = Math.atan2(d0, d2);
                    double d9 = Math.sin(d8);
                    double d10 = Math.cos(d8);
                    double d11 = Math.atan2(Math.sqrt(d0 * d0 + d2 * d2), d1);
                    double d12 = Math.sin(d11);
                    double d13 = Math.cos(d11);
                    double d14 = random.nextDouble() * Math.PI * 2.0D;
                    double d15 = Math.sin(d14);
                    double d16 = Math.cos(d14);


                    int trailSteps = 200;
                    for (int cc = 1; cc <= trailSteps; cc++) {
                        for (int j = 0; j < 4; ++j) {
                            //
                            double d18 = (double) ((j & 2) - 1) * d33;
                            double d19 = (double) ((j + 1 & 2) - 1) * d33;
                            double d21 = d18 * d16 - d19 * d15;
                            double d22 = d19 * d16 + d18 * d15;
                            double d23 = d21 * d12 + 0.0D * d13;
                            double d24 = 0.0D * d12 - d21 * d13;
                            double d25 = d24 * d9 - d22 * d10;
                            double d26 = d22 * d9 + d24 * d10;
                            bufferbuilder.pos(star.getPos().x + d5 + d25 - (star.getMotion().x * cc * 0.07), star.getPos().y + d6 + d23 - (star.getMotion().y * cc * 0.07), star.getPos().z + d7 + d26 - (star.getMotion().z * cc * 0.07)).color(168, 244, 244, 200 - cc).endVertex();
                        }
                    }
                    if (helper.getRandom().nextInt(1500) == 0 || fallingStarsList.size() > 30) {
                        this.fallingStarsList.remove(x);
                    }
                }

            }

            tessellator.draw();

            RenderSystem.popMatrix();
        }

        //stars end

        float f17 = 20.0F;

        float[] afloat = world.getDimension().calcSunriseSunsetColors(world.getCelestialAngle(partialTicks), partialTicks);

        if (afloat != null)
        {
            RenderSystem.disableTexture();
            RenderSystem.shadeModel(7425);
            RenderSystem.pushMatrix();
            RenderSystem.rotatef(90.0F, 1.0F, 0.0F, 0.0F);
            RenderSystem.rotatef(MathHelper.sin(world.getCelestialAngleRadians(partialTicks)) < 0.0F ? 180.0F : 0.0F, 0.0F, 0.0F, 1.0F);
            RenderSystem.rotatef(90.0F, 0.0F, 0.0F, 1.0F);
            float f6 = afloat[0];
            float f7 = afloat[1];
            float f8 = afloat[2];

            //pass !=2
            float f9 = (f6 * 30.0F + f7 * 59.0F + f8 * 11.0F) / 100.0F;
            float f10 = (f6 * 30.0F + f7 * 70.0F) / 100.0F;
            float f11 = (f6 * 30.0F + f8 * 70.0F) / 100.0F;
            f6 = f9;
            f7 = f10;
            f8 = f11;

            bufferbuilder.begin(6, DefaultVertexFormats.POSITION_COLOR);
            bufferbuilder.pos(0.0D, 100.0D, 0.0D).color(f6, f7, f8, afloat[3]).endVertex();

            for (int j2 = 0; j2 <= 16; ++j2)
            {
                float f21 = (float)j2 * ((float)Math.PI * 2F) / 16.0F;
                float f12 = MathHelper.sin(f21);
                float f13 = MathHelper.cos(f21);
                bufferbuilder.pos((double)(f12 * 120.0F), (double)(f13 * 120.0F), (double)(-f13 * 40.0F * afloat[3])).color(afloat[0], afloat[1], afloat[2], 0.0F).endVertex();
            }

            tessellator.draw();
            RenderSystem.popMatrix();
            RenderSystem.shadeModel(7424);
        }

        RenderSystem.enableTexture();
        RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        RenderSystem.pushMatrix();
        float f16 = 1.0F - world.getRainStrength(partialTicks);
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, f16);
        RenderSystem.rotatef(-90.0F, 0.0F, 1.0F, 0.0F);
        RenderSystem.rotatef(world.getCelestialAngle(partialTicks) * 360.0F, 1.0F, 0.0F, 0.0F);
        f17 = 30.0F;
        //sun start
        mc.getTextureManager().bindTexture(SUN_TEXTURES);
        bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
        bufferbuilder.pos((double)(-f17), 100.0D, (double)(-f17)).tex(0.0D, 0.0D).endVertex();
        bufferbuilder.pos((double)f17, 100.0D, (double)(-f17)).tex(1.0D, 0.0D).endVertex();
        bufferbuilder.pos((double)f17, 100.0D, (double)f17).tex(1.0D, 1.0D).endVertex();
        bufferbuilder.pos((double)(-f17), 100.0D, (double)f17).tex(0.0D, 1.0D).endVertex();
        tessellator.draw();
        //sun end

        //moon start
        f17 = 20.0F;
        mc.getTextureManager().bindTexture(MOON_PHASES_TEXTURES);
        int k1 = world.getMoonPhase();
        int i2 = k1 % 4;
        int k2 = k1 / 4 % 2;
        float f22 = (float)(i2 + 0) / 4.0F;
        float f23 = (float)(k2 + 0) / 2.0F;
        float f24 = (float)(i2 + 1) / 4.0F;
        float f14 = (float)(k2 + 1) / 2.0F;
        bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
        bufferbuilder.pos((double)(-f17), -100.0D, (double)f17).tex((double)f24, (double)f14).endVertex();
        bufferbuilder.pos((double)f17, -100.0D, (double)f17).tex((double)f22, (double)f14).endVertex();
        bufferbuilder.pos((double)f17, -100.0D, (double)(-f17)).tex((double)f22, (double)f23).endVertex();
        bufferbuilder.pos((double)(-f17), -100.0D, (double)(-f17)).tex((double)f24, (double)f23).endVertex();
        tessellator.draw();
        //moon end

        RenderSystem.disableTexture();
        float f15 = world.getStarBrightness(partialTicks) * f16;

        if (f15 > 0.0F)
        {
            RenderSystem.color4f(f15, f15, f15, f15);
        }

        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.disableBlend();
        RenderSystem.enableAlphaTest();
        RenderSystem.enableFog();

        RenderSystem.popMatrix();
        RenderSystem.disableTexture();
        RenderSystem.color3f(0.0F, 0.0F, 0.0F);
        double d3 = mc.player.getEyePosition(partialTicks).y - world.getHorizon();

        if (d3 < 0.0D)
        {
            RenderSystem.pushMatrix();
            RenderSystem.translatef(0.0F, 12.0F, 0.0F);

            RenderSystem.popMatrix();
            float f18 = 1.0F;
            float f19 = -((float)(d3 + 65.0D));
            float f20 = -1.0F;
            bufferbuilder.begin(7, DefaultVertexFormats.POSITION_COLOR);
            bufferbuilder.pos(-1.0D, (double)f19, 1.0D).color(0, 0, 0, 255).endVertex();
            bufferbuilder.pos(1.0D, (double)f19, 1.0D).color(0, 0, 0, 255).endVertex();
            bufferbuilder.pos(1.0D, -1.0D, 1.0D).color(0, 0, 0, 255).endVertex();
            bufferbuilder.pos(-1.0D, -1.0D, 1.0D).color(0, 0, 0, 255).endVertex();
            bufferbuilder.pos(-1.0D, -1.0D, -1.0D).color(0, 0, 0, 255).endVertex();
            bufferbuilder.pos(1.0D, -1.0D, -1.0D).color(0, 0, 0, 255).endVertex();
            bufferbuilder.pos(1.0D, (double)f19, -1.0D).color(0, 0, 0, 255).endVertex();
            bufferbuilder.pos(-1.0D, (double)f19, -1.0D).color(0, 0, 0, 255).endVertex();
            bufferbuilder.pos(1.0D, -1.0D, -1.0D).color(0, 0, 0, 255).endVertex();
            bufferbuilder.pos(1.0D, -1.0D, 1.0D).color(0, 0, 0, 255).endVertex();
            bufferbuilder.pos(1.0D, (double)f19, 1.0D).color(0, 0, 0, 255).endVertex();
            bufferbuilder.pos(1.0D, (double)f19, -1.0D).color(0, 0, 0, 255).endVertex();
            bufferbuilder.pos(-1.0D, (double)f19, -1.0D).color(0, 0, 0, 255).endVertex();
            bufferbuilder.pos(-1.0D, (double)f19, 1.0D).color(0, 0, 0, 255).endVertex();
            bufferbuilder.pos(-1.0D, -1.0D, 1.0D).color(0, 0, 0, 255).endVertex();
            bufferbuilder.pos(-1.0D, -1.0D, -1.0D).color(0, 0, 0, 255).endVertex();
            bufferbuilder.pos(-1.0D, -1.0D, -1.0D).color(0, 0, 0, 255).endVertex();
            bufferbuilder.pos(-1.0D, -1.0D, 1.0D).color(0, 0, 0, 255).endVertex();
            bufferbuilder.pos(1.0D, -1.0D, 1.0D).color(0, 0, 0, 255).endVertex();
            bufferbuilder.pos(1.0D, -1.0D, -1.0D).color(0, 0, 0, 255).endVertex();
            tessellator.draw();
        }

        RenderSystem.pushMatrix();
        RenderSystem.translatef(0.0F, -((float)(d3 - 16.0D)), 0.0F);
        RenderSystem.popMatrix();
        RenderSystem.enableTexture();
        RenderSystem.depthMask(true);

    }


}
