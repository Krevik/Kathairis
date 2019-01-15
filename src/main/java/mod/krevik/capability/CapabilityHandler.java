package mod.krevik.capability;

import mod.krevik.KCore;
import mod.krevik.capability.enteringkathairis.IWasInKathairisCapability;
import mod.krevik.capability.enteringkathairis.WasInKathairisHandler;
import mod.krevik.capability.enteringkathairis.WasInKathairisStorage;
import mod.krevik.entity.EntityStrangeWanderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

import javax.annotation.Nonnull;
import java.util.List;

/**
 * Capability handler
 *
 * This class is responsible for attaching our capabilities
 */
@Mod.EventBusSubscriber(modid = KCore.MODID)
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
            /*if(event.player.getCapability(wasInKathairis,null).wasInKathairisBefore()){
                event.player.getCapability(wasInKathairis,null).setWasInKathairis(true);
            }*/ //HOW TO EVEN DO THAT?!
        }
    }

    @SubscribeEvent
    public static void onDimensionChange(PlayerEvent.PlayerChangedDimensionEvent event){
        if(event.player!=null){
            if(event.toDim==KCore.DIMENSION_ID){
                if(!event.player.getCapability(wasInKathairis,null).wasInKathairisBefore()){
                    checkForStrangeWandererAndOpenGui(event.player);
                    event.player.getCapability(wasInKathairis,null).setWasInKathairis(true);
                }
            }
        }
    }

    private static void checkForStrangeWandererAndOpenGui(EntityPlayer ep){
        List<EntityStrangeWanderer> e = ep.world.getEntitiesWithinAABB(EntityStrangeWanderer.class, new AxisAlignedBB(ep.posX - 15, ep.posY - 15, ep.posZ - 15, ep.posX  + 15, ep.posY + 15, ep.posZ + 15));
        if(e.size()==0){
            if(!ep.world.isRemote) {
                BlockPos pos = getSuitablePos(ep);
                EntityStrangeWanderer oldMan = new EntityStrangeWanderer(ep.world);
                oldMan.setPositionAndUpdate(pos.getX(),pos.getY(),pos.getZ());
                oldMan.world.spawnEntity(oldMan);
            }
        }
        //THIS IS NOT WORKING!!!!!!
        //OpenStrangeWandererGuiPacket message = new OpenStrangeWandererGuiPacket();
        //KathairisPacketHandler.CHANNEL.sendTo(message,(EntityPlayerMP) ep);
    }

    private static BlockPos getSuitablePos(EntityPlayer player){
        double posX = player.posX-5+player.getRNG().nextInt(10);
        double posY = player.posY-3+player.getRNG().nextInt(6);
        double posZ = player.posZ-5+player.getRNG().nextInt(10);
        BlockPos tmp = new BlockPos(posX,posY,posZ);
        if(player.world.isAirBlock(tmp)&&player.world.isAirBlock(tmp.up())){
            return tmp;
        }else{
            return getSuitablePos(player);
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