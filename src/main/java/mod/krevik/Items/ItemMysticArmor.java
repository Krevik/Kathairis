package mod.krevik.Items;


import java.util.List;
import java.util.Random;
import java.util.UUID;

import mod.krevik.Main.KCore;
import mod.krevik.Networking.KetherPacketHandler;
import mod.krevik.Networking.PacketOnCloudBootsUseServer;
import com.google.common.base.Predicates;
import com.google.common.collect.Multimap;

import net.minecraft.block.BlockDispenser;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.dispenser.BehaviorDefaultDispenseItem;
import net.minecraft.dispenser.IBehaviorDispenseItem;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemMysticArmor extends ItemArmor
{
    /** Holds the 'base' maxDamage that each armorType have. */
    private static final int[] MAX_DAMAGE_ARRAY = new int[] {13, 15, 16, 11};
    private static final UUID[] ARMOR_MODIFIERS = new UUID[] {UUID.fromString("845DB27C-C624-495F-8C9F-6020A9A58B6B"), UUID.fromString("D8499B04-0E66-4726-AB29-64469D734E0D"), UUID.fromString("9F3D476D-C118-4544-8365-64846904B48E"), UUID.fromString("2AD3F246-FEE1-4E67-B886-69FD380BB150")};
    public static final String[] EMPTY_SLOT_NAMES = new String[] {"minecraft:items/empty_armor_slot_boots", "minecraft:items/empty_armor_slot_leggings", "minecraft:items/empty_armor_slot_chestplate", "minecraft:items/empty_armor_slot_helmet"};
    public static final IBehaviorDispenseItem DISPENSER_BEHAVIOR = new BehaviorDefaultDispenseItem()
    {
        /**
         * Dispense the specified stack, play the dispense sound and spawn particles.
         */
        protected ItemStack dispenseStack(IBlockSource source, ItemStack stack)
        {
            ItemStack itemstack = ItemMysticArmor.dispenseArmor(source, stack);
            return itemstack.isEmpty() ? super.dispenseStack(source, stack) : itemstack;
        }
    };
    /** Stores the armor type: 0 is helmet, 1 is plate, 2 is legs and 3 is boots */
    public final EntityEquipmentSlot armorType;
    /** Holds the amount of damage that the armor reduces at full durability. */
    public final int damageReduceAmount;
    public final float toughness;
    /**
     * Used on RenderPlayer to select the correspondent armor to be rendered on the player: 0 is cloth, 1 is chain, 2 is
     * iron, 3 is diamond and 4 is gold.
     */
    public final int renderIndex;
    /** The EnumArmorMaterial used for this ItemArmor */
    private final ItemArmor.ArmorMaterial material;

    public static ItemStack dispenseArmor(IBlockSource blockSource, ItemStack stack)
    {
        BlockPos blockpos = blockSource.getBlockPos().offset((EnumFacing)blockSource.getBlockState().getValue(BlockDispenser.FACING));
        List<EntityLivingBase> list = blockSource.getWorld().<EntityLivingBase>getEntitiesWithinAABB(EntityLivingBase.class, new AxisAlignedBB(blockpos), Predicates.and(EntitySelectors.NOT_SPECTATING, new EntitySelectors.ArmoredMob(stack)));

        if (list.isEmpty())
        {
            return ItemStack.EMPTY;
        }
        else
        {
            EntityLivingBase entitylivingbase = list.get(0);
            EntityEquipmentSlot entityequipmentslot = EntityLiving.getSlotForItemStack(stack);
            ItemStack itemstack = stack.splitStack(1);
            entitylivingbase.setItemStackToSlot(entityequipmentslot, itemstack);

            if (entitylivingbase instanceof EntityLiving)
            {
                ((EntityLiving)entitylivingbase).setDropChance(entityequipmentslot, 2.0F);
            }

            return stack;
        }
    }
    
    public boolean wereCloudBootsUsed=false;
    @SideOnly(Side.CLIENT)
    @Override
    public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack) {

	    if (itemStack.getItem().equals(KCore.CloudBoots)&&player.inventory.getStackInSlot(36).getItem().equals(KCore.CloudBoots)) {
	    	player.fallDistance=0;
	    	if(Minecraft.getMinecraft().gameSettings.keyBindJump.isPressed()&&!wereCloudBootsUsed&&!player.onGround) {
	    		wereCloudBootsUsed=true;
	    		if(player.world.isRemote) {
	    			Random rand = new Random();
	                for (int i = 0; i < 24; ++i)
	                {
	                    double d0 = player.posX + rand.nextDouble() - rand.nextDouble();
	                    double d1 = player.posY;
	                    double d2 = player.posZ + rand.nextDouble() - rand.nextDouble();
	                    double d3 = 0;
	                    double d4 = -0.5;
	                    double d5 = 0;
	                    player.world.spawnParticle(EnumParticleTypes.SPELL, d0, d1, d2, d3, d4, d5);
	                }
	    		}
	    		
	    		PacketOnCloudBootsUseServer server = new PacketOnCloudBootsUseServer();
	    		KetherPacketHandler.CHANNEL.sendToServer(server);
			    	player.fallDistance=0;

	    	}
			    }

    }
    
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected)
    {
        if(entityIn instanceof  EntityPlayer) {
            EntityPlayer player = (EntityPlayer) entityIn;
            if (stack.getItem().equals(KCore.CloudBoots) && player.inventory.getStackInSlot(36).getItem().equals(KCore.CloudBoots)) {
                entityIn.fallDistance = 0;
            }
        }
    }
    
    public EnumRarity getRarity(ItemStack stack)
    {
   	 if(this==KCore.CloudBoots){
   		 return EnumRarity.RARE;
   	 }
   	 else
   	 {
   		 return EnumRarity.COMMON;
   	 }
    }
    
    
    

    protected String name;
    public ItemMysticArmor(String name,CreativeTabs tab,ItemArmor.ArmorMaterial materialIn, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn)
    {
    	super(materialIn, renderIndexIn, equipmentSlotIn);
    	 this.name = name;
    	 setUnlocalizedName(name);
    	 setRegistryName(name);
    	 this.setCreativeTab(tab);
        this.material = materialIn;
        this.armorType = equipmentSlotIn;
        this.renderIndex = renderIndexIn;
        this.damageReduceAmount = materialIn.getDamageReductionAmount(equipmentSlotIn);
        this.setMaxDamage(materialIn.getDurability(equipmentSlotIn));
        this.toughness = materialIn.getToughness();
        this.maxStackSize = 1;
        BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(this, DISPENSER_BEHAVIOR);
        KCore.instance.regHelper.armorList.add(this);
        KCore.instance.proxy.itemList.add(this);

    }
    
    @SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }
    


    /**
     * Gets the equipment slot of this armor piece (formerly known as armor type)
     */
    @SideOnly(Side.CLIENT)
    public EntityEquipmentSlot getEquipmentSlot()
    {
        return this.armorType;
    }

    /**
     * Return the enchantability factor of the item, most of the time is based on material.
     */
    public int getItemEnchantability()
    {
        return this.material.getEnchantability();
    }

    /**
     * Return the armor material for this armor item.
     */
    public ItemArmor.ArmorMaterial getArmorMaterial()
    {
        return this.material;
    }


    /**
     * Return whether this item is repairable in an anvil.
     */
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair)
    {
        ItemStack mat = this.material.getRepairItemStack();
        if (!mat.isEmpty() && net.minecraftforge.oredict.OreDictionary.itemMatches(mat,repair,false)) return true;
        return super.getIsRepairable(toRepair, repair);
    }

    /**
     * Called when the equipped item is right clicked.
     */
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
        ItemStack itemstack = playerIn.getHeldItem(handIn);
        EntityEquipmentSlot entityequipmentslot = EntityLiving.getSlotForItemStack(itemstack);
        ItemStack itemstack1 = playerIn.getItemStackFromSlot(entityequipmentslot);

        if (itemstack1.isEmpty())
        {
            playerIn.setItemStackToSlot(entityequipmentslot, itemstack.copy());
            itemstack.setCount(0);
            return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemstack);
        }
        else
        {
            return new ActionResult<ItemStack>(EnumActionResult.FAIL, itemstack);
        }
    }

    /**
     * Gets a map of item attribute modifiers, used by ItemSword to increase hit damage.
     */
    public Multimap<String, AttributeModifier> getItemAttributeModifiers(EntityEquipmentSlot equipmentSlot)
    {
        Multimap<String, AttributeModifier> multimap = super.getItemAttributeModifiers(equipmentSlot);

        if (equipmentSlot == this.armorType)
        {
            multimap.put(SharedMonsterAttributes.ARMOR.getName(), new AttributeModifier(ARMOR_MODIFIERS[equipmentSlot.getIndex()], "Armor modifier", (double)this.damageReduceAmount, 0));
            multimap.put(SharedMonsterAttributes.ARMOR_TOUGHNESS.getName(), new AttributeModifier(ARMOR_MODIFIERS[equipmentSlot.getIndex()], "Armor toughness", (double)this.toughness, 0));
        }

        return multimap;
    }

    /**
     * Determines if this armor will be rendered with the secondary 'overlay' texture.
     * If this is true, the first texture will be rendered using a tint of the color
     * specified by getColor(ItemStack)
     *
     * @param stack The stack
     * @return true/false
     */
    public boolean hasOverlay(ItemStack stack)
    {
        return this.material == ItemArmor.ArmorMaterial.LEATHER || getColor(stack) != 0x00FFFFFF;
    }


}