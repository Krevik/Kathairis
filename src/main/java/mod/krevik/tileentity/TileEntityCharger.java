package mod.krevik.tileentity;

import java.util.Random;

import mod.krevik.container.ContainerCharger;
import mod.krevik.KCore;
import mod.krevik.recipe.ChargerRecipe;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityLockable;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.datafix.FixTypes;
import net.minecraft.util.datafix.walkers.ItemStackDataLists;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TileEntityCharger extends TileEntityLockable implements ITickable, ISidedInventory
{
    private static final int[] SLOTS_TOP = new int[] {0};
    private static final int[] SLOTS_BOTTOM = new int[] {2, 1};
    private static final int[] SLOTS_SIDES = new int[] {1};
    /** The ItemStacks that hold the items currently being used in the furnace */
    private NonNullList<ItemStack> furnaceItemStacks = NonNullList.<ItemStack>withSize(3, ItemStack.EMPTY);
    /** The number of ticks that the furnace will keep burning */
    private int furnaceBurnTime;
    /** The number of ticks that a fresh copy of the currently-burning item would keep the furnace burning for */
    private int currentItemBurnTime;
    private int cookTime;
    private int totalCookTime;
    private String furnaceCustomName;

    /**
     * Returns the number of slots in the inventory.
     */
    public int getSizeInventory()
    {
        return this.furnaceItemStacks.size();
    }

    public boolean isEmpty()
    {
        for (ItemStack itemstack : this.furnaceItemStacks)
        {
            if (!itemstack.isEmpty())
            {
                return false;
            }
        }

        return true;
    }

    /**
     * Returns the stack in the given slot.
     */
    public ItemStack getStackInSlot(int index)
    {
        return this.furnaceItemStacks.get(index);
    }

    /**
     * Removes up to a specified number of items from an inventory slot and returns them in a new stack.
     */
    public ItemStack decrStackSize(int index, int count)
    {
        return ItemStackHelper.getAndSplit(this.furnaceItemStacks, index, count);
    }

    /**
     * Removes a stack from the given slot and returns it.
     */
    public ItemStack removeStackFromSlot(int index)
    {
        return ItemStackHelper.getAndRemove(this.furnaceItemStacks, index);
    }

    /**
     * Sets the given item stack to the specified slot in the inventory (can be crafting or armor sections).
     */
    public void setInventorySlotContents(int index, ItemStack stack)
    {
        ItemStack itemstack = this.furnaceItemStacks.get(index);
        boolean flag = !stack.isEmpty() && stack.isItemEqual(itemstack) && ItemStack.areItemStackTagsEqual(stack, itemstack);
        this.furnaceItemStacks.set(index, stack);

        if (stack.getCount() > this.getInventoryStackLimit())
        {
            stack.setCount(this.getInventoryStackLimit());
        }

        if (index == 0 && !flag)
        {
            this.totalCookTime = this.getCookTime(stack);
            this.cookTime = 0;
            this.markDirty();
        }
    }

    /**
     * Get the name of this object. For players this returns their username
     */
    public String getName()
    {
        return KCore.Ref.Charger;
    }

    /**
     * Returns true if this thing is named
     */
    public boolean hasCustomName()
    {
        return this.furnaceCustomName != null && !this.furnaceCustomName.isEmpty();
    }

    public void setCustomInventoryName(String p_145951_1_)
    {
        this.furnaceCustomName = p_145951_1_;
    }

    public static void registerFixesFurnace(DataFixer fixer)
    {
        fixer.registerWalker(FixTypes.BLOCK_ENTITY, new ItemStackDataLists(TileEntityCharger.class, new String[] {"Items"}));
    }

    public void readFromNBT(NBTTagCompound compound)
    {
        super.readFromNBT(compound);
        this.furnaceItemStacks = NonNullList.<ItemStack>withSize(this.getSizeInventory(), ItemStack.EMPTY);
        ItemStackHelper.loadAllItems(compound, this.furnaceItemStacks);
        this.furnaceBurnTime = compound.getInteger("BurnTime");
        this.cookTime = compound.getInteger("CookTime");
        this.totalCookTime = compound.getInteger("CookTimeTotal");
        this.currentItemBurnTime = KCore.functionHelper.getItemBurnTime(this.furnaceItemStacks.get(1));

        if (compound.hasKey("CustomName", 8))
        {
            this.furnaceCustomName = compound.getString("CustomName");
        }
    }

    public NBTTagCompound writeToNBT(NBTTagCompound compound)
    {
        super.writeToNBT(compound);
        compound.setInteger("BurnTime", (short)this.furnaceBurnTime);
        compound.setInteger("CookTime", (short)this.cookTime);
        compound.setInteger("CookTimeTotal", (short)this.totalCookTime);
        ItemStackHelper.saveAllItems(compound, this.furnaceItemStacks);

        if (this.hasCustomName())
        {
            compound.setString("CustomName", this.furnaceCustomName);
        }

        return compound;
    }

    /**
     * Returns the maximum stack size for a inventory slot. Seems to always be 64, possibly will be extended.
     */
    public int getInventoryStackLimit()
    {
        return 64;
    }

    /**
     * Furnace isBurning
     */
    public boolean isBurning()
    {
        return this.furnaceBurnTime > 0;
    }

    @SideOnly(Side.CLIENT)
    public static boolean isBurning(IInventory inventory)
    {
        return inventory.getField(0) > 0;
    }

    /**
     * Like the old updateEntity(), except more generic.
     */
    boolean spawned=false;
    public EntityItem display=null;
    public boolean willBeDeleted=false;
    public void update()
    {
        boolean flag = this.isBurning();
        boolean flag1 = false;
        if(willBeDeleted) {
        	spawned=true;
    		display.setPosition(0, -3, 0);
    		this.getWorld().removeEntity(display);
    		display=null;
        }
	        ItemStack itemstack = this.furnaceItemStacks.get(2);
	        if(!itemstack.isEmpty()&&spawned) {
		        	display.setItem(itemstack);
	        		display.setPosition(getPos().getX()+0.5, getPos().getY()+0.8, getPos().getZ()+0.5);
	        }
	        if(itemstack.isEmpty()) {
	        	if(spawned) {
	        		display.setPosition(0, -3, 0);
	        	}
	        	spawned=false;
	        	display=null;
	        }
	        if(!spawned&&!itemstack.isEmpty()) {
		        	display=new EntityItem(this.getWorld());
		        	display.makeFakeItem();
		        	this.getWorld().spawnEntity(display);
	        	spawned=true;
	        }
        
        if (this.isBurning())
        {
            --this.furnaceBurnTime;
        }
        if (!this.world.isRemote)
        {
            itemstack = this.furnaceItemStacks.get(1);

            if (this.isBurning() || !itemstack.isEmpty() && !((ItemStack)this.furnaceItemStacks.get(0)).isEmpty())
            {
                if (!this.isBurning() && this.canSmelt())
                {
                    this.furnaceBurnTime = KCore.functionHelper.getItemBurnTime(itemstack);
                    this.currentItemBurnTime = this.furnaceBurnTime;

                    if (this.isBurning())
                    {
                        flag1 = true;

                        if (!itemstack.isEmpty())
                        {
                            Item item = itemstack.getItem();
                            itemstack.shrink(1);

                            if (itemstack.isEmpty())
                            {
                                ItemStack item1 = item.getContainerItem(itemstack);
                                this.furnaceItemStacks.set(1, item1);
                            }
                        }
                    }
                }

                if (this.isBurning() && this.canSmelt())
                {
                    ++this.cookTime;

                    if (this.cookTime == this.totalCookTime)
                    {
                        this.cookTime = 0;
                        this.totalCookTime = this.getCookTime(this.furnaceItemStacks.get(0));
                        this.smeltItem();
                        flag1 = true;
                    }
                }
                else
                {
                    this.cookTime = 0;
                }
            }
            else if (!this.isBurning() && this.cookTime > 0)
            {
                this.cookTime = MathHelper.clamp(this.cookTime - 2, 0, this.totalCookTime);
            }

            if (flag != this.isBurning())
            {
                flag1 = true;
               // BlockCharger.setState(this.isBurning(), this.world, this.pos);
            }
        }

        if (flag1)
        {
            this.markDirty();
        }
    }

    public int getCookTime(ItemStack stack)
    {
        return 600;
    }

    /**
     * Returns true if the furnace can smelt an item, i.e. has a source item, destination stack isn't full, etc.
     */
    private boolean canSmelt()
    {
    	ItemStack itemStackToUpgrade = this.furnaceItemStacks.get(2);
    	ItemStack itemToBeConsumed = this.furnaceItemStacks.get(0);
    	boolean can=false;
    	
    	if(itemStackToUpgrade!=null) {
    		if(itemToBeConsumed!=null) {
    			if(itemStackToUpgrade.getItem()!=null) {
    				if(itemToBeConsumed.getItem()!=null) {
    	    			int recipeID = KCore.instance.chargerRecipes.getRecipe(itemStackToUpgrade.getItem());
    	    			if(recipeID!=-1) {
    	    				ChargerRecipe recipe = KCore.instance.chargerRecipes.recipeList.get(recipeID);
    	    				if(itemStackToUpgrade.getItem()==recipe.getItemToUpgrade()) {
    	    					if(itemToBeConsumed.getItem()==recipe.getItemToBeConsumed()) {
    	    						can=true;
    	    					}
    	    				}
    	    			}
    				}
    			}    			
    		}
    	}
    	
    	/*if(this.furnaceItemStacks.get(2).getItem()!=null) {
    		if(this.furnaceItemStacks.get(2).getItem()==KCore.DeathWand) {
    			if(this.furnaceItemStacks.get(0).getItem()==KCore.DarknessEssence) {
    				if(this.furnaceItemStacks.get(2).getItem().getDamage(this.furnaceItemStacks.get(2))-10>=0) {
    					return true;
    				}
    			}
    		}
    	}
    	if(this.furnaceItemStacks.get(2).getItem()!=null) {
    		if(this.furnaceItemStacks.get(2).getItem()==KCore.ShockWand) {
    			if(this.furnaceItemStacks.get(0).getItem()==KCore.JellyFishTentacle) {
    				if(this.furnaceItemStacks.get(2).getItem().getDamage(this.furnaceItemStacks.get(2))-10>=0) {
    					return true;
    				}else {
    					return false;
    				}
    			}else {
    				return false;
    			}
    		}else {
    			return false;
    		}
    	}else {
    		return false;
    	}*/
    	return can;
    }

    /**
     * Turn one item from the furnace source stack into the appropriate smelted item in the furnace result stack
     */
    Random random = new Random();
    public void smeltItem()
    {
        if (this.canSmelt())
        {
            ItemStack itemstack = this.furnaceItemStacks.get(0);
            ItemStack itemstack2 = this.furnaceItemStacks.get(2);
            itemstack.shrink(1);
            
            if(itemstack2.getItem()!=null) {
        		itemstack2.setItemDamage(itemstack2.getItemDamage()-10);
            	}
            
            /*if(itemstack2.getItem()!=null) {
            	if(itemstack2.getItem()==KCore.ShockWand) {
            		itemstack2.setItemDamage(itemstack2.getItemDamage()-10);
                		//itemstack2.damageItem(-10, Minecraft.getMinecraft().player);
            	}
            	if(itemstack2.getItem()==KCore.DeathWand) {
            		//itemstack2.damageItem(-10, Minecraft.getMinecraft().player);
            		itemstack2.setItemDamage(itemstack2.getItemDamage()-10);
        	}
            }
            */

        }
    }


    /**
     * Don't rename this method to canInteractWith due to conflicts with Container
     */
    public boolean isUsableByPlayer(EntityPlayer player)
    {
        if (this.world.getTileEntity(this.pos) != this)
        {
            return false;
        }
        else
        {
            return player.getDistanceSq((double)this.pos.getX() + 0.5D, (double)this.pos.getY() + 0.5D, (double)this.pos.getZ() + 0.5D) <= 64.0D;
        }
    }

    public void openInventory(EntityPlayer player)
    {
    }

    public void closeInventory(EntityPlayer player)
    {
    }

    /**
     * Returns true if automation is allowed to insert the given stack (ignoring stack size) into the given slot. For
     * guis use Slot.isItemValid
     */
    public boolean isItemValidForSlot(int index, ItemStack stack)
    {
        if (index == 2)
        {
        	if(stack.getItem()==KCore.ShockWand||stack.getItem()==KCore.DeathWand) {
        		return true;
        	}else {
                return false;
        	}
        }
        else if (index != 1)
        {
            return true;
        }
        else
        {
            ItemStack itemstack = this.furnaceItemStacks.get(1);
            return KCore.functionHelper.isItemFuel(stack) && itemstack.getItem() != Items.BUCKET;
        }
    }

    public int[] getSlotsForFace(EnumFacing side)
    {
        if (side == EnumFacing.DOWN)
        {
            return SLOTS_BOTTOM;
        }
        else
        {
            return side == EnumFacing.UP ? SLOTS_TOP : SLOTS_SIDES;
        }
    }

    /**
     * Returns true if automation can insert the given item in the given slot from the given side.
     */
    public boolean canInsertItem(int index, ItemStack itemStackIn, EnumFacing direction)
    {
        return this.isItemValidForSlot(index, itemStackIn);
    }

    /**
     * Returns true if automation can extract the given item in the given slot from the given side.
     */
    public boolean canExtractItem(int index, ItemStack stack, EnumFacing direction)
    {
        if (direction == EnumFacing.DOWN && index == 1)
        {
            Item item = stack.getItem();

            if (item != Items.WATER_BUCKET && item != Items.BUCKET)
            {
                return false;
            }
        }

        return true;
    }

    public String getGuiID()
    {
        return KCore.MODID+"kathairis:charger";
    }

    public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn)
    {
        return new ContainerCharger(playerInventory, this);
    }

    public int getField(int id)
    {
        switch (id)
        {
            case 0:
                return this.furnaceBurnTime;
            case 1:
                return this.currentItemBurnTime;
            case 2:
                return this.cookTime;
            case 3:
                return this.totalCookTime;
            default:
                return 0;
        }
    }

    public void setField(int id, int value)
    {
        switch (id)
        {
            case 0:
                this.furnaceBurnTime = value;
                break;
            case 1:
                this.currentItemBurnTime = value;
                break;
            case 2:
                this.cookTime = value;
                break;
            case 3:
                this.totalCookTime = value;
        }
    }

    public int getFieldCount()
    {
        return 4;
    }

    public void clear()
    {
        this.furnaceItemStacks.clear();
    }

    net.minecraftforge.items.IItemHandler handlerTop = new net.minecraftforge.items.wrapper.SidedInvWrapper(this, net.minecraft.util.EnumFacing.UP);
    net.minecraftforge.items.IItemHandler handlerBottom = new net.minecraftforge.items.wrapper.SidedInvWrapper(this, net.minecraft.util.EnumFacing.DOWN);
    net.minecraftforge.items.IItemHandler handlerSide = new net.minecraftforge.items.wrapper.SidedInvWrapper(this, net.minecraft.util.EnumFacing.WEST);

    @Override
    @javax.annotation.Nullable
    public <T> T getCapability(net.minecraftforge.common.capabilities.Capability<T> capability, @javax.annotation.Nullable net.minecraft.util.EnumFacing facing)
    {
        if (facing != null && capability == net.minecraftforge.items.CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
            if (facing == EnumFacing.DOWN)
                return (T) handlerBottom;
            else if (facing == EnumFacing.UP)
                return (T) handlerTop;
            else
                return (T) handlerSide;
        return super.getCapability(capability, facing);
    }
}
