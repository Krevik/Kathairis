package io.github.krevik.kathairis.particle;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.awt.*;

/**
 * @author Krevik
 * @credit Jabelar
 */
public class BasicKatharianParticle extends Particle {
    private static final VertexFormat VERTEX_FORMAT = (new VertexFormat()).addElement(DefaultVertexFormats.POSITION_3F).addElement(DefaultVertexFormats.TEX_2F).addElement(DefaultVertexFormats.COLOR_4UB).addElement(DefaultVertexFormats.TEX_2S).addElement(DefaultVertexFormats.NORMAL_3B).addElement(DefaultVertexFormats.PADDING_1B);
    private KatharianParticleTexture textureConfig;
    private static final double GRAVITY = 0.04D;
    private static final double GROUND_DECCEL = 0.7D;
    private int currentAnimationFrame = 0;
    private float progress = 0.0F;
    private float rotSpeed = 0.0F;
    private double deccel = 0.98D;
    private float initialAlpha = 1.0F;
    private float finalAlpha = 1.0F;
    private Color initialColor = Color.WHITE;
    private Color finalColor = Color.WHITE;
    private float initialScale = 1.0F;
    private float finalScale = 1.0F;

    private boolean enableDepth;

    protected BasicKatharianParticle(KatharianParticleTexture parTextureConfig, World world, double posX, double posY, double posZ) {
        super(world, posX, posY, posZ);
        textureConfig=parTextureConfig;
    }

    public BasicKatharianParticle(KatharianParticleTexture parTexDef, World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
        super(world, posX, posY, posZ);
        textureConfig = parTexDef;
        this.motionX = motionX;
        this.motionY = motionY;
        this.motionZ = motionZ;
    }

    public KatharianParticleTexture getTextureConfig() {
        return textureConfig;
    }

    @Override
    public void tick() {
        updateTick();
        updateMotions();
        move(motionX, motionY, motionZ);
        updateAlphaColor();
        updateColors();
        updateScale();
    }

    private void updateTick() {
        if (age++ >= maxAge) {
            setExpired();
        }
        progress = ((float) age) / ((float) maxAge);

        if (textureConfig.isTweenAnimationMode()) {
            currentAnimationFrame = (int) (progress * (textureConfig.getAnimationFrameCount() + 1));
        } else {
            if (currentAnimationFrame++ >= textureConfig.getAnimationFrameCount()) {
                currentAnimationFrame = 0;
            }
        }

        prevPosX = posX;
        prevPosY = posY;
        prevPosZ = posZ;

        prevParticleAngle = particleAngle;
        particleAngle += (float) Math.PI * rotSpeed * 2.0F;
    }

    public void updateMotions() {
        motionY -= GRAVITY * particleGravity;
        motionX *= deccel;
        motionY *= deccel;
        motionZ *= deccel;
        rotSpeed *= deccel;
        if (onGround && canCollide) {
            motionX *= GROUND_DECCEL * deccel;
            motionZ *= GROUND_DECCEL * deccel;
        }
    }

    protected void updateAlphaColor() {
        particleAlpha = initialAlpha + progress * (finalAlpha - initialAlpha);

    }

    protected void updateColors() {
        particleRed = (initialColor.getRed() + progress * (finalColor.getRed() - initialColor.getRed())) / 256.0F;
        particleBlue = (initialColor.getBlue() + progress * (finalColor.getBlue() - initialColor.getBlue())) / 256.0F;
        particleGreen = (initialColor.getGreen() + progress * (finalColor.getGreen() - initialColor.getGreen())) / 256.0F;
    }

    protected void updateScale() {
        particleScale = initialScale + progress * (finalScale - initialScale);
    }

    @Override
    public void renderParticle(BufferBuilder bufferIn, Entity entityIn, float partialTicks, float rotationX, float rotationZ, float rotationYZ, float rotationXY, float rotationXZ) {
        GlStateManager.pushMatrix();
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
        GlStateManager.alphaFunc(516, 0.003921569F);
        GlStateManager.color4f(1.0F, 1.0F, 1.0F, particleAlpha);
        GlStateManager.depthMask(enableDepth);

        Minecraft.getInstance().getTextureManager().bindTexture(textureConfig.getResourceLocation());

        float uMin = textureConfig.getUMin();
        float uMax = textureConfig.getUMax();
        float frameV = currentAnimationFrame * (textureConfig.getVMax() - textureConfig.getVmin());
        float vMin = textureConfig.getVmin() + frameV;
        float vMax = textureConfig.getVMax() + frameV;
        float scale = 0.1F * particleScale;
        float xInterp = (float) (prevPosX + (posX - prevPosX) * partialTicks - interpPosX);
        float yInterp = (float) (prevPosY + (posY - prevPosY) * partialTicks - interpPosY);
        float zInterp = (float) (prevPosZ + (posZ - prevPosZ) * partialTicks - interpPosZ);

        Vec3d[] avec3d = new Vec3d[]{new Vec3d(-rotationX * scale - rotationXY * scale, -rotationZ * scale, -rotationYZ * scale - rotationXZ * scale), new Vec3d(-rotationX * scale + rotationXY * scale, rotationZ * scale, -rotationYZ * scale + rotationXZ * scale), new Vec3d(rotationX * scale + rotationXY * scale, rotationZ * scale, rotationYZ * scale + rotationXZ * scale), new Vec3d(rotationX * scale - rotationXY * scale, -rotationZ * scale, rotationYZ * scale - rotationXZ * scale)};

        if (particleAngle != 0.0F) {
            float angleInterp = particleAngle + (particleAngle - prevParticleAngle) * partialTicks;
            float f9 = MathHelper.cos(angleInterp * 0.5F);
            float xComponent = MathHelper.sin(angleInterp * 0.5F) * (float) cameraViewDir.x;
            float yComponent = MathHelper.sin(angleInterp * 0.5F) * (float) cameraViewDir.y;
            float zComponent = MathHelper.sin(angleInterp * 0.5F) * (float) cameraViewDir.z;
            Vec3d vec3d = new Vec3d(xComponent, yComponent, zComponent);

            for (int l = 0; l < 4; ++l) {
                avec3d[l] = vec3d.scale(2.0D * avec3d[l].dotProduct(vec3d)).add(avec3d[l].scale(f9 * f9 - vec3d.dotProduct(vec3d))).add(vec3d.crossProduct(avec3d[l]).scale(2.0F * f9));
            }
        }

        int brightness = getBrightnessForRender(partialTicks);
        int j = brightness >> 16 & 65535;
        int k = brightness & 65535;

        bufferIn.begin(7, VERTEX_FORMAT);
        bufferIn.pos(
                xInterp + avec3d[0].x,
                yInterp + avec3d[0].y,
                zInterp + avec3d[0].z)
                .tex(uMax, vMax)
                .color(particleRed, particleGreen, particleBlue, particleAlpha)
                .lightmap(j, k)
                .normal(0.0F, 1.0F, 0.0F)
                .endVertex();
        bufferIn.pos(
                xInterp + avec3d[1].x,
                yInterp + avec3d[1].y,
                zInterp + avec3d[1].z)
                .tex(uMax, vMin)
                .color(particleRed, particleGreen, particleBlue, particleAlpha)
                .lightmap(j, k)
                .normal(0.0F, 1.0F, 0.0F)
                .endVertex();
        bufferIn.pos(
                xInterp + avec3d[2].x,
                yInterp + avec3d[2].y,
                zInterp + avec3d[2].z)
                .tex(uMin, vMin)
                .color(particleRed, particleGreen, particleBlue, particleAlpha)
                .lightmap(j, k)
                .normal(0.0F, 1.0F, 0.0F)
                .endVertex();
        bufferIn.pos(
                xInterp + avec3d[3].x,
                yInterp + avec3d[3].y,
                zInterp + avec3d[3].z)
                .tex(uMin, vMax)
                .color(particleRed, particleGreen, particleBlue, particleAlpha)
                .lightmap(j, k)
                .normal(0.0F, 1.0F, 0.0F)
                .endVertex();
        Tessellator.getInstance().draw();
        GlStateManager.popMatrix();
    }

    @Override
    public int getFXLayer() {
        return 3;
    }

    public BasicKatharianParticle setCanCollide(boolean canCollideIn) {
        canCollide = canCollideIn;
        return this;
    }

    public BasicKatharianParticle setAABB(AxisAlignedBB boundingBoxIn) {
        setBoundingBox(boundingBoxIn);
        return this;
    }

    public BasicKatharianParticle setDeccel(double deccelIn) {
        deccel = deccelIn;
        return this;
    }

    public BasicKatharianParticle setScale(float scaleIn) {
        particleScale = scaleIn;
        initialScale = scaleIn;
        finalScale = scaleIn;
        return this;
    }

    public BasicKatharianParticle setTintColor(float redIn, float greenIn, float blueIn) {
        particleRed = redIn;
        particleGreen = greenIn;
        particleBlue = blueIn;
        initialColor = new Color(redIn / 256.0F, greenIn / 256.0F, blueIn / 256.0F);
        finalColor = initialColor;
        return this;
    }

    public BasicKatharianParticle setTintColor(Color colorIn) {
        particleGreen = colorIn.getGreen() / 256.0F;
        particleBlue = colorIn.getBlue() / 256.0F;
        particleRed = colorIn.getRed() / 256.0F;
        initialColor = colorIn;
        finalColor = initialColor;
        return this;
    }

    public BasicKatharianParticle setTintColorAndAlpha(float redIn, float greenIn, float blueIn, float alphaIn) {
        setTintColor(redIn, greenIn, blueIn);
        setAlpha(alphaIn);
        return this;
    }

    public BasicKatharianParticle setTintColorAndAlpha(Color colorIn) {
        setTintColor(colorIn);
        setAlpha(colorIn);
        return this;
    }

    public BasicKatharianParticle setLifeSpan(int age){
        setMaxAge(age);
        return this;
    }

    public BasicKatharianParticle setAlpha(float alphaIn) {
        particleAlpha = alphaIn;
        initialAlpha = alphaIn;
        finalAlpha = initialAlpha;
        return this;
    }

    public BasicKatharianParticle setAlpha(Color colorIn) {
        particleAlpha = colorIn.getAlpha() / 256.0F;
        initialAlpha = particleAlpha;
        finalAlpha = initialAlpha;
        return this;
    }

    public BasicKatharianParticle setInitialColor(Color colorIn) {
        initialColor = colorIn;
        particleGreen = colorIn.getGreen() / 256.0F;
        particleBlue = colorIn.getBlue() / 256.0F;
        particleRed = colorIn.getRed() / 256.0F;
        return this;
    }

    public BasicKatharianParticle setFinalColor(Color colorIn) {
        finalColor = colorIn;
        return this;
    }

    public BasicKatharianParticle setInitialAlpha(float alphaIn) {
        initialAlpha = alphaIn;
        particleAlpha = alphaIn;
        return this;
    }

    public BasicKatharianParticle setInitialAlpha(Color colorIn) {
        initialAlpha = colorIn.getAlpha() / 256.0F;
        particleAlpha = colorIn.getAlpha() / 256.0F;
        return this;
    }

    public BasicKatharianParticle setFinalAlpha(float alphaIn) {
        finalAlpha = alphaIn;
        return this;
    }

    public BasicKatharianParticle setFinalAlpha(Color colorIn) {
        finalAlpha = colorIn.getAlpha() / 256.0F;
        return this;
    }

    public BasicKatharianParticle setInitialScale(float scaleIn) {
        particleScale = scaleIn;
        initialScale = scaleIn;
        return this;
    }

    public BasicKatharianParticle setFinalScale(float scaleIn) {
        finalScale = scaleIn;
        return this;
    }

    public BasicKatharianParticle setGravity(float gravityIn) {
        particleGravity = gravityIn;
        return this;
    }

    public BasicKatharianParticle setRotationSpeed(float rotIn) {
        rotSpeed = rotIn;
        return this;
    }

    public BasicKatharianParticle setEnableDepth(boolean enableDepthIn) {
        enableDepth = enableDepthIn;
        return this;
    }
}
