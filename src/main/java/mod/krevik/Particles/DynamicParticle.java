package mod.krevik.Particles;

import java.awt.Color;

import mod.krevik.Blocks.BlockMysticBush;
import net.minecraft.block.BlockBush;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class DynamicParticle extends Particle {

    private static final double GRAVITY = 0.04D;
    private static final double GROUND_DECCEL = 0.7D;
    private static final VertexFormat VERTEX_FORMAT = (new VertexFormat()).addElement(DefaultVertexFormats.POSITION_3F).addElement(DefaultVertexFormats.TEX_2F).addElement(DefaultVertexFormats.COLOR_4UB).addElement(DefaultVertexFormats.TEX_2S).addElement(DefaultVertexFormats.NORMAL_3B).addElement(DefaultVertexFormats.PADDING_1B);

    private TextureDefinition TEXTURE_DEF;

    private int currentAnimationFrame = 0;
    private float progress = 0.0F;
    private float rotSpeed = 0.0F;
    private double deccel = 0.98D;
    private float initialAlpha = 1.0F;
    private float finalAlpha = 1.0F;
    private Color initialTint = Color.WHITE;
    private Color finalTint = Color.WHITE;
    private float initialScale = 1.0F;
    private float finalScale = 1.0F;

    private boolean enableDepth = true;

    private BlockPos spawnPos;


    public DynamicParticle(
            TextureDefinition parTexDef,
            World parWorld,
            double parX, double parY, double parZ) {
        super(parWorld, parX, parY, parZ);
        TEXTURE_DEF = parTexDef;
    }

    public DynamicParticle(
            TextureDefinition parTexDef,
            World parWorld,
            double parX, double parY, double parZ,BlockPos spawnPosition) {
        super(parWorld, parX, parY, parZ);
        TEXTURE_DEF = parTexDef;
        spawnPos=spawnPosition;
    }



    public DynamicParticle(
            TextureDefinition parTexDef,
            World parWorld,
            double parX, double parY, double parZ,
            double parMotionX, double parMotionY, double parMotionZ) {
        super(parWorld, parX, parY, parZ);
        TEXTURE_DEF = parTexDef;
        motionX = parMotionX;
        motionY = parMotionY;
        motionZ = parMotionZ;
    }

    @Override
    public void onUpdate() {
        updateTick();
        processGravityAndDeccel();
        if(TEXTURE_DEF.ShouldHaveCustomMovement()){
            handleCustomMovement();
        }
        move(motionX, motionY, motionZ);
        processAlphaTween();
        processTintTween();
        processScaleTween();
    }

    BlockPos target;
    private void handleCustomMovement(){
        if(target!=null){
            motionX=(target.getX()-posX)/20;
            motionY=(target.getY()-posY)/20;
            motionZ=(target.getZ()-posZ)/20;
        }else{
            target = findSuitablePos();
        }
        if(rand.nextInt(50)==0){
            //find suitable target
            target = findSuitablePos();
            motionX=(target.getX()-posX)/20;
            motionY=(target.getY()-posY)/20;
            motionZ=(target.getZ()-posZ)/20;
        }
    }

    private BlockPos findSuitablePos(){
        double targetX=spawnPos.getX()-2+4*rand.nextFloat();
        double targetY=spawnPos.getY()-2+4*rand.nextFloat();
        double targetZ=spawnPos.getZ()-2+4*rand.nextFloat();
        BlockPos result = new BlockPos(targetX,targetY,targetZ);
        if(world.isAirBlock(result)||world.getBlockState(result).getBlock() instanceof BlockBush||
                world.getBlockState(result).getBlock() instanceof BlockMysticBush){
            return result;
        }else{
            return findSuitablePos();
        }
    }

    private void updateTick() {
        if (particleAge++ >= particleMaxAge) {
            setExpired();
        }
        progress = ((float) particleAge) / ((float) particleMaxAge);

        if (TEXTURE_DEF.isTweenAnimationMode()) {
            currentAnimationFrame = (int) (progress * (TEXTURE_DEF.getAnimationFrameCount() + 1));
        } else {
            if (currentAnimationFrame++ >= TEXTURE_DEF.getAnimationFrameCount()) {
                currentAnimationFrame = 0;
            }
        }

        prevPosX = posX;
        prevPosY = posY;
        prevPosZ = posZ;

        prevParticleAngle = particleAngle;
        particleAngle += (float) Math.PI * rotSpeed * 2.0F;
    }

    private void processGravityAndDeccel() {
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

    protected void processAlphaTween() {
        particleAlpha = initialAlpha + progress * (finalAlpha - initialAlpha);

    }

    protected void processTintTween() {
        particleRed = (initialTint.getRed() + progress * (finalTint.getRed() - initialTint.getRed())) / 256.0F;
        particleBlue = (initialTint.getBlue() + progress * (finalTint.getBlue() - initialTint.getBlue())) / 256.0F;
        particleGreen = (initialTint.getGreen() + progress * (finalTint.getGreen() - initialTint.getGreen())) / 256.0F;
    }

    protected void processScaleTween() {
        particleScale = initialScale + progress * (finalScale - initialScale);
    }

    @Override
    public int getFXLayer() {
        return 3;
    }

    @Override
    public boolean shouldDisableDepth() {
        return (!enableDepth);
    }

    @Override
    public void renderParticle(
            BufferBuilder bufferIn,
            Entity entityIn,
            float partialTicks,
            float rotationX, float rotationZ,
            float rotationYZ, float rotationXY, float rotationXZ) {
        GlStateManager.pushMatrix();
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
        GlStateManager.alphaFunc(516, 0.003921569F);
        GlStateManager.color(1.0F, 1.0F, 1.0F, particleAlpha);

        Minecraft.getMinecraft().getTextureManager().bindTexture(TEXTURE_DEF.getResourceLocation());

        float uMin = TEXTURE_DEF.getUMin();
        float uMax = TEXTURE_DEF.getUMax();
        float frameV = currentAnimationFrame * (TEXTURE_DEF.getVMax() - TEXTURE_DEF.getVmin());
        float vMin = TEXTURE_DEF.getVmin() + frameV;
        float vMax = TEXTURE_DEF.getVMax() + frameV;
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

    public DynamicParticle setLifeSpan(int lifeIn) {
        particleMaxAge = lifeIn;
        return this;
    }

    public DynamicParticle setCanCollide(boolean canCollideIn) {
        canCollide = canCollideIn;
        return this;
    }

    public DynamicParticle setAABB(AxisAlignedBB boundingBoxIn) {
        setBoundingBox(boundingBoxIn);
        return this;
    }

    public DynamicParticle setDeccel(double deccelIn) {
        deccel = deccelIn;
        return this;
    }

    public DynamicParticle setScale(float scaleIn) {
        particleScale = scaleIn;
        initialScale = scaleIn;
        finalScale = scaleIn;
        return this;
    }

    public DynamicParticle setTintColor(float redIn, float greenIn, float blueIn) {
        particleRed = redIn;
        particleGreen = greenIn;
        particleBlue = blueIn;
        initialTint = new Color(redIn / 256.0F, greenIn / 256.0F, blueIn / 256.0F);
        finalTint = initialTint;
        return this;
    }

    public DynamicParticle setTintColor(Color colorIn) {
        particleGreen = colorIn.getGreen() / 256.0F;
        particleBlue = colorIn.getBlue() / 256.0F;
        particleRed = colorIn.getRed() / 256.0F;
        initialTint = colorIn;
        finalTint = initialTint;
        return this;
    }

    public DynamicParticle setTintColorAndAlpha(float redIn, float greenIn, float blueIn, float alphaIn) {
        setTintColor(redIn, greenIn, blueIn);
        setAlpha(alphaIn);
        return this;
    }

    public DynamicParticle setTintColorAndAlpha(Color colorIn) {
        setTintColor(colorIn);
        setAlpha(colorIn);
        return this;
    }

    public DynamicParticle setAlpha(float alphaIn) {
        particleAlpha = alphaIn;
        initialAlpha = alphaIn;
        finalAlpha = initialAlpha;
        return this;
    }

    public DynamicParticle setAlpha(Color colorIn) {
        particleAlpha = colorIn.getAlpha() / 256.0F;
        initialAlpha = particleAlpha;
        finalAlpha = initialAlpha;
        return this;
    }

    public DynamicParticle setInitalTint(Color colorIn) {
        initialTint = colorIn;
        particleGreen = colorIn.getGreen() / 256.0F;
        particleBlue = colorIn.getBlue() / 256.0F;
        particleRed = colorIn.getRed() / 256.0F;
        return this;
    }

    public DynamicParticle setFinalTint(Color colorIn) {
        finalTint = colorIn;
        return this;
    }

    public DynamicParticle setInitialAlpha(float alphaIn) {
        initialAlpha = alphaIn;
        particleAlpha = alphaIn;
        return this;
    }

    public DynamicParticle setInitialAlpha(Color colorIn) {
        initialAlpha = colorIn.getAlpha() / 256.0F;
        particleAlpha = colorIn.getAlpha() / 256.0F;
        return this;
    }

    public DynamicParticle setFinalAlpha(float alphaIn) {
        finalAlpha = alphaIn;
        return this;
    }

    public DynamicParticle setFinalAlpha(Color colorIn) {
        finalAlpha = colorIn.getAlpha() / 256.0F;
        return this;
    }

    public DynamicParticle setInitialScale(float scaleIn) {
        particleScale = scaleIn;
        initialScale = scaleIn;
        return this;
    }

    public DynamicParticle setFinalScale(float scaleIn) {
        finalScale = scaleIn;
        return this;
    }

    public DynamicParticle setGravity(float gravityIn) {
        particleGravity = gravityIn;
        return this;
    }

    public DynamicParticle setRotSpeed(float rotIn) {
        rotSpeed = rotIn;
        return this;
    }

    public DynamicParticle setEnableDepth(boolean enableDepthIn) {
        enableDepth = enableDepthIn;
        return this;
    }

}