package mod.krevik.Capabilities;

import mod.krevik.Capabilities.EnteringKathairis.IWasInKathairisCapability;
import mod.krevik.Capabilities.EnteringKathairis.WasInKathairisHandler;
import mod.krevik.Capabilities.EnteringKathairis.WasInKathairisStorage;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

import javax.annotation.Nonnull;

/**
 * Capability handler
 *
 * This class is responsible for attaching our capabilities
 */
public class CapabilityHandler
{
    @CapabilityInject(IWasInKathairisCapability.class)
    public static final Capability<IWasInKathairisCapability> wasInKathairis=null;

    public static void registerCapabilities() {
        CapabilityManager.INSTANCE.register(IWasInKathairisCapability.class, new WasInKathairisStorage(), WasInKathairisHandler::new);
        MinecraftForge.EVENT_BUS.register(CapabilityHandler.class);
    }

    @SubscribeEvent
    public static void onPlayerDeathClone(PlayerEvent.PlayerRespawnEvent event){
        if(event.player!=null){

        }
    }

    @SubscribeEvent
    public static void attachEntityCapability(AttachCapabilitiesEvent<Entity> e) {
        if (e.getObject() instanceof EntityLivingBase) {
            e.addCapability(IWasInKathairisCapability.ID, new ICapabilitySerializable<NBTTagCompound>() {

                IWasInKathairisCapability inst = wasInKathairis.getDefaultInstance();

                {
                    inst.setEntity((EntityLivingBase) e.getObject());
                }

                @Override
                public boolean hasCapability(@Nonnull Capability<?> capability, EnumFacing facing) {
                    return capability == wasInKathairis;
                }

                @Override
                public <T> T getCapability(@Nonnull Capability<T> capability, EnumFacing facing) {
                    return capability == wasInKathairis ? wasInKathairis.<T>cast(inst) : null;
                }

                @Override
                public NBTTagCompound serializeNBT() {
                    return (NBTTagCompound) wasInKathairis.getStorage().writeNBT(wasInKathairis, inst, null);
                }

                @Override
                public void deserializeNBT(NBTTagCompound nbt) {
                    wasInKathairis.getStorage().readNBT(wasInKathairis, inst, null, nbt);
                }

            });
        }
    }
}