package io.github.krevik.kathairis;

import io.github.krevik.kathairis.client.ModBlocksColorHandler;
import io.github.krevik.kathairis.init.ModBiomes;
import io.github.krevik.kathairis.init.ModParticles;
import io.github.krevik.kathairis.particle.KatharianParticleTexture;
import io.github.krevik.kathairis.particle.ParticleFast;
import io.github.krevik.kathairis.particle.ParticleKatharianPortal;
import io.github.krevik.kathairis.particle.ParticleMysticWandShoot;
import io.github.krevik.kathairis.util.RenderersRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;

import java.util.ArrayList;

import static io.github.krevik.kathairis.util.ModReference.MOD_ID;
import static net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus.FORGE;
import static net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus.MOD;

@Mod.EventBusSubscriber(modid = MOD_ID, bus = FORGE, value=Dist.CLIENT)
public class ForgeEventSubscriberClient {

    @SubscribeEvent
    public static void fogThings(EntityViewRenderEvent.RenderFogEvent event){
        Entity player = event.getEntity();
        int radius=6;
        ArrayList<BlockPos> posesToCalculate = new ArrayList<>();
        for(int x=-radius;x<=radius;x++){
            for(int z=-radius;z<=radius;z++){
                BlockPos pos = new BlockPos(player.getPosition().getX()+x,player.getPosition().getY(),player.getPosition().getZ()+z);
                posesToCalculate.add(pos);
            }
        }
        boolean isSwampNear=false;
        for(BlockPos pos:posesToCalculate){
            if(player.world.getBiome(pos)== ModBiomes.KATHARIAN_SWAMP){
                isSwampNear=true;
            }
        }
        if(isSwampNear){
            GlStateManager.fogStart(getAverage(player.world,posesToCalculate,0f,4f));
            GlStateManager.fogEnd(getAverage(player.world,posesToCalculate,event.getFarPlaneDistance(),40f));
            GlStateManager.fogDensity(getAverage(player.world,posesToCalculate,0.5f,0.005f));
        }
    }

    private static float getAverage(IWorld world, ArrayList<BlockPos> posesToCalculate, float normalValue, float swampValue){
        float result;
        float sum=0;
        for(BlockPos pos:posesToCalculate){
            if(world.getBiome(pos)==ModBiomes.KATHARIAN_SWAMP){
                sum+=swampValue;
            }else{
                sum+=normalValue;
            }
        }
        result=sum/posesToCalculate.size();
        return result;
    }


}