package mod.krevik.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;

import java.util.Random;

public class ModelJellyFish extends ModelBase
{
  //fields
    public ModelRenderer Leg1;
    public ModelRenderer Part2;
    public ModelRenderer Part1;
    public ModelRenderer overhang1;
    public ModelRenderer overhang2;
    public ModelRenderer overhang3;
    public ModelRenderer Leg2;
    public ModelRenderer Leg3;
    public ModelRenderer Leg4;
    public ModelRenderer Leg5;
    public ModelRenderer Leg6;
    public ModelRenderer Leg7;
    public ModelRenderer Leg8;
    
    protected int animIndex=0;
    protected int mode=0;
    Random random = new Random();
    protected float[][] animationTimer = new float[][]
    		{
    		    { 0F,1F,2F,3F,4F,5F,6F,7F,8F,9F,10F,11F,12F,13F,14F,15F,16F,17F,18F,19F,20F,21F,22F,23F,24F,25F,26F,27F,28F,29F,30F,31F,32F,33F,34F,35F,36F,37F,38F,39F,40F,41F,42F,43F,44F,45F }
    		};
    		
    	    public ModelJellyFish() {
    	        this.textureWidth = 128;
    	        this.textureHeight = 128;
    	        this.overhang2 = new ModelRenderer(this, 112, -8);
    	        this.overhang2.setRotationPoint(2.2F, 2.4F, -1.3F);
    	        this.overhang2.addBox(0.0F, 0.0F, 0.0F, 0, 31, 8, 0.0F);
    	        this.setRotation(overhang2, 0.0F, -1.0927506446736497F, 0.0F);
    	        this.Leg4 = new ModelRenderer(this, 0, 20);
    	        this.Leg4.setRotationPoint(-4.0F, 2.8F, 0.0F);
    	        this.Leg4.addBox(0.0F, 0.0F, 0.0F, 1, 8, 1, 0.0F);
    	        this.Leg8 = new ModelRenderer(this, 0, 20);
    	        this.Leg8.setRotationPoint(-3.1F, 2.7F, -3.0F);
    	        this.Leg8.addBox(0.0F, 0.0F, 0.0F, 1, 8, 1, 0.0F);
    	        this.Part1 = new ModelRenderer(this, 0, 2);
    	        this.Part1.setRotationPoint(-5.0F, 1.0F, -4.9F);
    	        this.Part1.addBox(0.0F, 0.0F, 0.0F, 10, 2, 10, 0.0F);
    	        this.Leg2 = new ModelRenderer(this, 0, 20);
    	        this.Leg2.setRotationPoint(-0.2F, 2.8F, -3.9F);
    	        this.Leg2.addBox(0.0F, 0.0F, 0.0F, 1, 8, 1, 0.0F);
    	        this.Part2 = new ModelRenderer(this, 3, 1);
    	        this.Part2.setRotationPoint(-4.0F, -1.0F, -4.0F);
    	        this.Part2.addBox(0.0F, 0.0F, 0.0F, 8, 2, 8, 0.0F);
    	        this.Leg6 = new ModelRenderer(this, 0, 20);
    	        this.Leg6.setRotationPoint(-3.4F, 2.7F, 2.9F);
    	        this.Leg6.addBox(0.0F, 0.0F, 0.0F, 1, 8, 1, 0.0F);
    	        this.Leg5 = new ModelRenderer(this, 0, 20);
    	        this.Leg5.setRotationPoint(2.0F, 2.8F, 2.5F);
    	        this.Leg5.addBox(0.0F, 0.0F, 0.0F, 1, 8, 1, 0.0F);
    	        this.Leg1 = new ModelRenderer(this, 0, 20);
    	        this.Leg1.setRotationPoint(-0.7F, 2.7F, 3.3F);
    	        this.Leg1.addBox(0.0F, 0.0F, 0.0F, 1, 8, 1, 0.0F);
    	        this.overhang3 = new ModelRenderer(this, 112, -8);
    	        this.overhang3.setRotationPoint(-2.3F, 2.5F, -1.3F);
    	        this.overhang3.addBox(0.0F, 0.0F, 0.0F, 0, 31, 8, 0.0F);
    	        this.setRotation(overhang3, 0.0F, 1.0471975511965976F, 0.0F);
    	        this.Leg7 = new ModelRenderer(this, 0, 20);
    	        this.Leg7.setRotationPoint(2.0F, 2.7F, -2.4F);
    	        this.Leg7.addBox(0.0F, 0.0F, 0.0F, 1, 8, 1, 0.0F);
    	        this.Leg3 = new ModelRenderer(this, 0, 20);
    	        this.Leg3.setRotationPoint(3.0F, 2.8F, 0.0F);
    	        this.Leg3.addBox(0.0F, 0.0F, 0.0F, 1, 8, 1, 0.0F);
    	        this.overhang1 = new ModelRenderer(this, 112, -8);
    	        this.overhang1.setRotationPoint(-0.1F, 2.4F, -2.4F);
    	        this.overhang1.addBox(0.0F, 0.0F, 0.0F, 0, 31, 8, 0.0F);
    	    }
  
    	    @Override
    	    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
    	        GlStateManager.pushMatrix();
    	        GlStateManager.translate(this.overhang2.offsetX, this.overhang2.offsetY, this.overhang2.offsetZ);
    	        GlStateManager.translate(this.overhang2.rotationPointX * f5, this.overhang2.rotationPointY * f5, this.overhang2.rotationPointZ * f5);
    	        GlStateManager.scale(0.6D, 0.7D, 0.7D);
    	        GlStateManager.translate(-this.overhang2.offsetX, -this.overhang2.offsetY, -this.overhang2.offsetZ);
    	        GlStateManager.translate(-this.overhang2.rotationPointX * f5, -this.overhang2.rotationPointY * f5, -this.overhang2.rotationPointZ * f5);
    	        this.overhang2.render(f5);
    	        GlStateManager.popMatrix();
    	        GlStateManager.pushMatrix();
    	        GlStateManager.translate(this.Leg4.offsetX, this.Leg4.offsetY, this.Leg4.offsetZ);
    	        GlStateManager.translate(this.Leg4.rotationPointX * f5, this.Leg4.rotationPointY * f5, this.Leg4.rotationPointZ * f5);
    	        GlStateManager.scale(1.0D, 1.5D, 1.0D);
    	        GlStateManager.translate(-this.Leg4.offsetX, -this.Leg4.offsetY, -this.Leg4.offsetZ);
    	        GlStateManager.translate(-this.Leg4.rotationPointX * f5, -this.Leg4.rotationPointY * f5, -this.Leg4.rotationPointZ * f5);
    	        this.Leg4.render(f5);
    	        GlStateManager.popMatrix();
    	        GlStateManager.pushMatrix();
    	        GlStateManager.translate(this.Leg8.offsetX, this.Leg8.offsetY, this.Leg8.offsetZ);
    	        GlStateManager.translate(this.Leg8.rotationPointX * f5, this.Leg8.rotationPointY * f5, this.Leg8.rotationPointZ * f5);
    	        GlStateManager.scale(1.0D, 1.5D, 1.0D);
    	        GlStateManager.translate(-this.Leg8.offsetX, -this.Leg8.offsetY, -this.Leg8.offsetZ);
    	        GlStateManager.translate(-this.Leg8.rotationPointX * f5, -this.Leg8.rotationPointY * f5, -this.Leg8.rotationPointZ * f5);
    	        this.Leg8.render(f5);
    	        GlStateManager.popMatrix();
    	        this.Part1.render(f5);
    	        GlStateManager.pushMatrix();
    	        GlStateManager.translate(this.Leg2.offsetX, this.Leg2.offsetY, this.Leg2.offsetZ);
    	        GlStateManager.translate(this.Leg2.rotationPointX * f5, this.Leg2.rotationPointY * f5, this.Leg2.rotationPointZ * f5);
    	        GlStateManager.scale(1.0D, 1.5D, 1.0D);
    	        GlStateManager.translate(-this.Leg2.offsetX, -this.Leg2.offsetY, -this.Leg2.offsetZ);
    	        GlStateManager.translate(-this.Leg2.rotationPointX * f5, -this.Leg2.rotationPointY * f5, -this.Leg2.rotationPointZ * f5);
    	        this.Leg2.render(f5);
    	        GlStateManager.popMatrix();
    	        this.Part2.render(f5);
    	        GlStateManager.pushMatrix();
    	        GlStateManager.translate(this.Leg6.offsetX, this.Leg6.offsetY, this.Leg6.offsetZ);
    	        GlStateManager.translate(this.Leg6.rotationPointX * f5, this.Leg6.rotationPointY * f5, this.Leg6.rotationPointZ * f5);
    	        GlStateManager.scale(1.0D, 1.5D, 1.0D);
    	        GlStateManager.translate(-this.Leg6.offsetX, -this.Leg6.offsetY, -this.Leg6.offsetZ);
    	        GlStateManager.translate(-this.Leg6.rotationPointX * f5, -this.Leg6.rotationPointY * f5, -this.Leg6.rotationPointZ * f5);
    	        this.Leg6.render(f5);
    	        GlStateManager.popMatrix();
    	        GlStateManager.pushMatrix();
    	        GlStateManager.translate(this.Leg5.offsetX, this.Leg5.offsetY, this.Leg5.offsetZ);
    	        GlStateManager.translate(this.Leg5.rotationPointX * f5, this.Leg5.rotationPointY * f5, this.Leg5.rotationPointZ * f5);
    	        GlStateManager.scale(1.0D, 1.5D, 1.0D);
    	        GlStateManager.translate(-this.Leg5.offsetX, -this.Leg5.offsetY, -this.Leg5.offsetZ);
    	        GlStateManager.translate(-this.Leg5.rotationPointX * f5, -this.Leg5.rotationPointY * f5, -this.Leg5.rotationPointZ * f5);
    	        this.Leg5.render(f5);
    	        GlStateManager.popMatrix();
    	        GlStateManager.pushMatrix();
    	        GlStateManager.translate(this.Leg1.offsetX, this.Leg1.offsetY, this.Leg1.offsetZ);
    	        GlStateManager.translate(this.Leg1.rotationPointX * f5, this.Leg1.rotationPointY * f5, this.Leg1.rotationPointZ * f5);
    	        GlStateManager.scale(1.0D, 1.5D, 1.0D);
    	        GlStateManager.translate(-this.Leg1.offsetX, -this.Leg1.offsetY, -this.Leg1.offsetZ);
    	        GlStateManager.translate(-this.Leg1.rotationPointX * f5, -this.Leg1.rotationPointY * f5, -this.Leg1.rotationPointZ * f5);
    	        this.Leg1.render(f5);
    	        GlStateManager.popMatrix();
    	        GlStateManager.pushMatrix();
    	        GlStateManager.translate(this.overhang3.offsetX, this.overhang3.offsetY, this.overhang3.offsetZ);
    	        GlStateManager.translate(this.overhang3.rotationPointX * f5, this.overhang3.rotationPointY * f5, this.overhang3.rotationPointZ * f5);
    	        GlStateManager.scale(0.6D, 0.7D, 0.7D);
    	        GlStateManager.translate(-this.overhang3.offsetX, -this.overhang3.offsetY, -this.overhang3.offsetZ);
    	        GlStateManager.translate(-this.overhang3.rotationPointX * f5, -this.overhang3.rotationPointY * f5, -this.overhang3.rotationPointZ * f5);
    	        this.overhang3.render(f5);
    	        GlStateManager.popMatrix();
    	        GlStateManager.pushMatrix();
    	        GlStateManager.translate(this.Leg7.offsetX, this.Leg7.offsetY, this.Leg7.offsetZ);
    	        GlStateManager.translate(this.Leg7.rotationPointX * f5, this.Leg7.rotationPointY * f5, this.Leg7.rotationPointZ * f5);
    	        GlStateManager.scale(1.0D, 1.5D, 1.0D);
    	        GlStateManager.translate(-this.Leg7.offsetX, -this.Leg7.offsetY, -this.Leg7.offsetZ);
    	        GlStateManager.translate(-this.Leg7.rotationPointX * f5, -this.Leg7.rotationPointY * f5, -this.Leg7.rotationPointZ * f5);
    	        this.Leg7.render(f5);
    	        GlStateManager.popMatrix();
    	        GlStateManager.pushMatrix();
    	        GlStateManager.translate(this.Leg3.offsetX, this.Leg3.offsetY, this.Leg3.offsetZ);
    	        GlStateManager.translate(this.Leg3.rotationPointX * f5, this.Leg3.rotationPointY * f5, this.Leg3.rotationPointZ * f5);
    	        GlStateManager.scale(1.0D, 1.5D, 1.0D);
    	        GlStateManager.translate(-this.Leg3.offsetX, -this.Leg3.offsetY, -this.Leg3.offsetZ);
    	        GlStateManager.translate(-this.Leg3.rotationPointX * f5, -this.Leg3.rotationPointY * f5, -this.Leg3.rotationPointZ * f5);
    	        this.Leg3.render(f5);
    	        GlStateManager.popMatrix();
    	        GlStateManager.pushMatrix();
    	        GlStateManager.translate(this.overhang1.offsetX, this.overhang1.offsetY, this.overhang1.offsetZ);
    	        GlStateManager.translate(this.overhang1.rotationPointX * f5, this.overhang1.rotationPointY * f5, this.overhang1.rotationPointZ * f5);
    	        GlStateManager.scale(0.6D, 0.7D, 0.7D);
    	        GlStateManager.translate(-this.overhang1.offsetX, -this.overhang1.offsetY, -this.overhang1.offsetZ);
    	        GlStateManager.translate(-this.overhang1.rotationPointX * f5, -this.overhang1.rotationPointY * f5, -this.overhang1.rotationPointZ * f5);
    	        this.overhang1.render(f5);
    	        GlStateManager.popMatrix();
    	    }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
  {
    super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    float d0 = (float) (Math.PI * -2.0D / 8 + (Math.PI / 2D));
    
    Leg2.rotateAngleX=-degToRad(animationTimer[0][animIndex/10]);
    Leg1.rotateAngleX=degToRad(animationTimer[0][animIndex/10]);
 	Leg3.rotateAngleZ=-degToRad(animationTimer[0][animIndex/10]);
    Leg4.rotateAngleZ=degToRad(animationTimer[0][animIndex/10]);
    Leg5.rotateAngleX=degToRad(animationTimer[0][animIndex/10]);
    Leg5.rotateAngleZ=-degToRad(animationTimer[0][animIndex/10]);
    Leg6.rotateAngleX=degToRad(animationTimer[0][animIndex/10]);
    Leg6.rotateAngleZ=degToRad(animationTimer[0][animIndex/10]);
    Leg7.rotateAngleX=-degToRad(animationTimer[0][animIndex/10]);
    Leg7.rotateAngleZ=-degToRad(animationTimer[0][animIndex/10]);
    Leg8.rotateAngleX=-degToRad(animationTimer[0][animIndex/10]);
    Leg8.rotateAngleZ=degToRad(animationTimer[0][animIndex/10]);
    
        if(mode==0) {
        	animIndex++;
        	animIndex++;
        	animIndex++;

        	if(animIndex>440) {
        		mode=1;
        	}
        }
        if(mode==1) {
        	animIndex--;
        	animIndex--;
        	animIndex--;
        	animIndex--;
        	animIndex--;
        	animIndex--;

        	if(animIndex<10) {
        		mode=-1;
        	}
        }
        
        if(mode==-1) {
            if(random.nextInt(50)==1) {
            	mode=0;
            }	
        }
    
  }
  
  
  protected float degToRad(float degrees)
  {
      return degrees * (float)Math.PI / 180 ;
  }

}
