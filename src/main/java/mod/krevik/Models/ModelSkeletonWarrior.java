package mod.krevik.Models;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.AbstractSkeleton;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModelSkeletonWarrior extends ModelBiped {
    public ModelRenderer LeftArm;
    public ModelRenderer LeftLeg;
    public ModelRenderer HeadInside;
    public ModelRenderer Body;
    public ModelRenderer RightArm;
    public ModelRenderer RightLeg;
    public ModelRenderer HeadOutside;
    public ModelRenderer Helmet;

    public ModelSkeletonWarrior() {
        this.textureWidth = 128;
        this.textureHeight = 64;
        this.Body = new ModelRenderer(this, 48, 0);
        this.Body.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Body.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, 0.0F);
        this.HeadOutside = new ModelRenderer(this, 16, 0);
        this.HeadOutside.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.HeadOutside.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.5F);
        this.HeadInside = new ModelRenderer(this, 0, 0);
        this.HeadInside.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.HeadInside.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F);
        this.Helmet = new ModelRenderer(this, 0, 16);
        this.Helmet.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Helmet.addBox(-5.0F, -10.0F, -5.0F, 10, 10, 10, 0.0F);
        this.RightArm = new ModelRenderer(this, 72, 0);
        this.RightArm.mirror = true;
        this.RightArm.setRotationPoint(5.0F, 2.0F, 0.0F);
        this.RightArm.addBox(-1.0F, -2.0F, -1.0F, 2, 12, 2, 0.0F);
        this.setRotateAngle(RightArm, 0.0F, 0.0F, -0.10000736613927509F);
        this.LeftArm = new ModelRenderer(this, 0, 0);
        this.LeftArm.setRotationPoint(-5.0F, 2.0F, 0.0F);
        this.LeftArm.addBox(-1.0F, -2.0F, -1.0F, 2, 12, 2, 0.0F);
        this.setRotateAngle(LeftArm, 0.0F, 0.0F, 0.10000736613927509F);
        this.LeftLeg = new ModelRenderer(this, 8, 0);
        this.LeftLeg.setRotationPoint(-2.0F, 12.0F, 0.1F);
        this.LeftLeg.addBox(-1.0F, 0.0F, -1.0F, 2, 12, 2, 0.0F);
        this.RightLeg = new ModelRenderer(this, 80, 0);
        this.RightLeg.mirror = true;
        this.RightLeg.setRotationPoint(2.0F, 12.0F, 0.1F);
        this.RightLeg.addBox(-1.0F, 0.0F, -1.0F, 2, 12, 2, 0.0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        this.Body.render(f5);
        this.HeadOutside.render(f5);
        this.Helmet.render(f5);
        this.RightArm.render(f5);
        this.LeftArm.render(f5);
        this.LeftLeg.render(f5);
        this.RightLeg.render(f5);
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
    
    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
    {
        ItemStack itemstack = ((EntityLivingBase)entity).getHeldItemMainhand();
        AbstractSkeleton abstractskeleton = (AbstractSkeleton)entity;

        if (abstractskeleton.isSwingingArms())
        {
            float ff = MathHelper.sin(this.swingProgress * (float)Math.PI);
            float ff1 = MathHelper.sin((1.0F - (1.0F - this.swingProgress) * (1.0F - this.swingProgress)) * (float)Math.PI);
            this.RightArm.rotateAngleZ = 0.0F;
            this.LeftArm.rotateAngleZ = 0.0F;
            this.RightArm.rotateAngleY = -(0.1F - ff * 0.6F);
            this.LeftArm.rotateAngleY = 0.1F - ff * 0.6F;
            this.RightArm.rotateAngleX = -((float)Math.PI / 2F);
            this.LeftArm.rotateAngleX = -((float)Math.PI / 2F);
            this.RightArm.rotateAngleX -= ff * 1.2F - ff1 * 0.4F;
            this.LeftArm.rotateAngleX -= ff * 1.2F - ff1 * 0.4F;
            this.RightArm.rotateAngleZ += MathHelper.cos(f2 * 0.09F) * 0.05F + 0.05F;
            this.LeftArm.rotateAngleZ -= MathHelper.cos(f2 * 0.09F) * 0.05F + 0.05F;
            this.RightArm.rotateAngleX += MathHelper.sin(f2 * 0.067F) * 0.05F;
            this.LeftArm.rotateAngleX -= MathHelper.sin(f2 * 0.067F) * 0.05F;
        }
        float ff = 1.0F;
        this.RightArm.rotateAngleX = MathHelper.cos(f * 0.6662F + (float)Math.PI) * 2.0F * f1 * 0.5F / ff;
        this.LeftArm.rotateAngleX = MathHelper.cos(f * 0.6662F) * 2.0F * f1 * 0.5F / ff;
        this.RightLeg.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1 / ff;
        this.LeftLeg.rotateAngleX = MathHelper.cos(f * 0.6662F + (float)Math.PI) * 1.4F * f1 / ff;
        
        switch (this.leftArmPose)
        {
            case EMPTY:
                this.LeftArm.rotateAngleY = 0.0F;
                break;
            case BLOCK:
                this.LeftArm.rotateAngleX = this.LeftArm.rotateAngleX * 0.5F - 0.9424779F;
                this.LeftArm.rotateAngleY = 0.5235988F;
                break;
            case ITEM:
                this.LeftArm.rotateAngleX = this.LeftArm.rotateAngleX * 0.5F - ((float)Math.PI / 10F);
                this.LeftArm.rotateAngleY = 0.0F;
		case BOW_AND_ARROW:
			break;
		default:
			break;
        }

        switch (this.rightArmPose)
        {
            case EMPTY:
                this.RightArm.rotateAngleY = 0.0F;
                break;
            case BLOCK:
                this.RightArm.rotateAngleX = this.RightArm.rotateAngleX * 0.5F - 0.9424779F;
                this.RightArm.rotateAngleY = -0.5235988F;
                break;
            case ITEM:
                this.RightArm.rotateAngleX = this.RightArm.rotateAngleX * 0.5F - ((float)Math.PI / 10F);
                this.RightArm.rotateAngleY = 0.0F;
		case BOW_AND_ARROW:
			break;
		default:
			break;
        }

        if (this.swingProgress > 0.0F)
        {
            EnumHandSide enumhandside = this.getMainHand(entity);
            ModelRenderer modelrenderer = this.getArmForSide(enumhandside);
            float f11 = this.swingProgress;
            this.bipedBody.rotateAngleY = MathHelper.sin(MathHelper.sqrt(f11) * ((float)Math.PI * 2F)) * 0.2F;

            if (enumhandside == EnumHandSide.LEFT)
            {
                this.bipedBody.rotateAngleY *= -1.0F;
            }

            this.RightArm.rotationPointZ = MathHelper.sin(this.bipedBody.rotateAngleY) * 5.0F;
            this.RightArm.rotationPointX = -MathHelper.cos(this.bipedBody.rotateAngleY) * 5.0F;
            this.LeftArm.rotationPointZ = -MathHelper.sin(this.bipedBody.rotateAngleY) * 5.0F;
            this.LeftArm.rotationPointX = MathHelper.cos(this.bipedBody.rotateAngleY) * 5.0F;
            this.RightArm.rotateAngleY += this.bipedBody.rotateAngleY;
            this.LeftArm.rotateAngleY += this.bipedBody.rotateAngleY;
            this.LeftArm.rotateAngleX += this.bipedBody.rotateAngleY;
            f11 = 1.0F - this.swingProgress;
            f11 = f11 * f11;
            f11 = f11 * f11;
            f11 = 1.0F - f11;
            float f22 = MathHelper.sin(f11 * (float)Math.PI);
            float f33 = MathHelper.sin(this.swingProgress * (float)Math.PI) * -(this.bipedHead.rotateAngleX - 0.7F) * 0.75F;
            modelrenderer.rotateAngleX = (float)((double)modelrenderer.rotateAngleX - ((double)f22 * 1.2D + (double)f33));
            modelrenderer.rotateAngleY += this.bipedBody.rotateAngleY * 2.0F;
            modelrenderer.rotateAngleZ += MathHelper.sin(this.swingProgress * (float)Math.PI) * -0.4F;
        }
    }
    
    
    public void postRenderArm(float scale, EnumHandSide side)
    {
        this.getArmForSide(side).postRender(scale);
    }

    protected ModelRenderer getArmForSide(EnumHandSide side)
    {
        return side == EnumHandSide.LEFT ? this.LeftArm : this.RightArm;
    }

    protected EnumHandSide getMainHand(Entity entityIn)
    {
        if (entityIn instanceof EntityLivingBase)
        {
            EntityLivingBase entitylivingbase = (EntityLivingBase)entityIn;
            EnumHandSide enumhandside = entitylivingbase.getPrimaryHand();
            return entitylivingbase.swingingHand == EnumHand.MAIN_HAND ? enumhandside : enumhandside.opposite();
        }
        else
        {
            return EnumHandSide.RIGHT;
        }
    }

    @SideOnly(Side.CLIENT)
    public static enum ArmPose
    {
        EMPTY,
        ITEM,
        BLOCK,
        BOW_AND_ARROW;
    }
}
