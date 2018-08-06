package com.Krevik.Entities;

import java.util.Random;

import com.Krevik.Main.KCore;
import com.Krevik.Particles.ParticleShocking;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityShockingBall extends EntityThrowable
{
	Random random = new Random();
    public EntityShockingBall(World worldIn)
    {
        super(worldIn);
    }

    public EntityShockingBall(World worldIn, EntityLivingBase throwerIn)
    {
        super(worldIn, throwerIn);
    }

    public EntityShockingBall(World worldIn, double x, double y, double z)
    {
        super(worldIn, x, y, z);
    }

    public static void registerFixesEgg(DataFixer fixer)
    {
        EntityThrowable.registerFixesThrowable(fixer, "entityshockingball");
    }
    protected float getGravityVelocity()
    {
        return 0.00001F;
    }
    /**
     * Handler for {@link World#setEntityState}
     */
    @SideOnly(Side.CLIENT)
    public void handleStatusUpdate(byte id)
    {
        if (id == 3)
        {
            double d0 = 0.08D;

            for (int i = 0; i < 8; ++i)
            {
            	ParticleShocking particle = new ParticleShocking(this.world,this.posX+random.nextDouble()-random.nextDouble(),this.posY+random.nextDouble()-random.nextDouble(),this.posZ+random.nextDouble()-random.nextDouble(),random.nextDouble()-random.nextDouble(),random.nextDouble()-random.nextDouble(),random.nextDouble()-random.nextDouble());
            	KCore.instance.cproxy.drawParticle(this.world, particle);
            }
        }
    }
    
    public void onUpdate()
    {
    	super.onUpdate();

    }

    /**
     * Called when this EntityThrowable hits a block or entity.
     */
    
    public void setDead()
    {
        /*if(this.world.isRemote) {
            for(int x=0;x<52;x++) {
            	ParticleShocking particle = new ParticleShocking(this.world,posX+random.nextDouble()-random.nextDouble(),posY+random.nextDouble()-random.nextDouble(),posZ+random.nextDouble()-random.nextDouble(),random.nextDouble()-random.nextDouble(),random.nextDouble()-random.nextDouble(),random.nextDouble()-random.nextDouble());
            	KCore.instance.cproxy.drawParticle(this.world, particle);
            }
        }*/
        this.isDead = true;
    }
    
    protected void onImpact(RayTraceResult result)
    {
        if (result.entityHit != null)
        {
            if(result.entityHit instanceof EntityLivingBase) {
            	EntityLivingBase el = (EntityLivingBase) result.entityHit;
            	el.addPotionEffect(new PotionEffect(Potion.getPotionById(2),100,7));
            }
            result.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), 7F);
            


            
        }

        if (!this.world.isRemote)
        {
            if(result.entityHit==null) {
                this.world.setEntityState(this, (byte)3);
                this.setDead();
            }

        }
    }
}