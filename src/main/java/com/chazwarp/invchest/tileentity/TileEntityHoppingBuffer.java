/**
@author Chaz Kerby
*/
package com.chazwarp.invchest.tileentity;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockChest;
import net.minecraft.block.BlockHopper;
import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.Hopper;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntityHopper;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Facing;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class TileEntityHoppingBuffer extends TileEntity implements IInventory, Hopper{

	private ItemStack[] items;
    private int transferCooldown = -1;
	
	public TileEntityHoppingBuffer() {
		items = new ItemStack[18];
	}
	
	@Override
	public int getSizeInventory() {
		return items.length;
	}

	@Override
	public ItemStack getStackInSlot(int i) {
		return items[i];
	}

	@Override
	public ItemStack decrStackSize(int i, int count) {
		ItemStack itemstack = getStackInSlot(i);
		
		if (itemstack != null) {
			if (itemstack.stackSize <= count) {
				setInventorySlotContents(i, null);
			}
			else {
				itemstack = itemstack.splitStack(count);
				onInventoryChanged();
			}
		}
		return itemstack;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int i) {
		ItemStack item = getStackInSlot(i);
		setInventorySlotContents(i, null);
		return item;
	}

	@Override
	public void setInventorySlotContents(int i, ItemStack itemstack) {
		items[i] = itemstack;
		
		if (itemstack != null && itemstack.stackSize > getInventoryStackLimit()) {
			itemstack.stackSize = getInventoryStackLimit();
		}	
		onInventoryChanged();
	}

	@Override
	public String getInvName() {
		return "InventoryHoppingBuffer";
	}

	@Override
	public boolean isInvNameLocalized() {
		return false;
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer entityplayer) {
		return entityplayer.getDistanceSq(xCoord + 0.5, yCoord + 0.5, zCoord + 0.5) <= 64;
	}

	@Override
	public void openChest() {}

	@Override
	public void closeChest() {}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack) {
		return true;
	}
	
	@Override
	public void writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		
		NBTTagList items = new NBTTagList();
		
		for (int i = 0; i < getSizeInventory(); i++) {		
			ItemStack stack = getStackInSlot(i);
			
			if (stack != null) {
				NBTTagCompound item = new NBTTagCompound();
				item.setByte("Slot", (byte)i);
				stack.writeToNBT(item);
				items.appendTag(item);
			}
		}
		compound.setTag("Items", items);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		
		NBTTagList items = compound.getTagList("Items");
		
		for (int i = 0; i < items.tagCount(); i++) {
			NBTTagCompound item = (NBTTagCompound)items.tagAt(i);
			int slot = item.getByte("Slot");
			
			if (slot >= 0 && slot < getSizeInventory()) {
				setInventorySlotContents(slot, ItemStack.loadItemStackFromNBT(item));
			}
		}
	}
	
    public void updateEntity() {
        if (this.worldObj != null && !this.worldObj.isRemote)
        {
            --this.transferCooldown;

            if (!this.isCoolingDown())
            {
                this.setTransferCooldown(0);
                this.updateHopper();
            }
        }
    }

    public boolean updateHopper() {
        if (this.worldObj != null && !this.worldObj.isRemote)
        {
            if (!this.isCoolingDown() && BlockHopper.getIsBlockNotPoweredFromMetadata(this.getBlockMetadata()))
            {
                boolean flag = this.insertItemToInventory();
                flag = suckItemsIntoHopper(this) || flag;

                if (flag)
                {
                    this.setTransferCooldown(8);
                    this.onInventoryChanged();
                    return true;
                }
            }
            return false;
        }
        else {
            return false;
        }
    }

    /**
     * Inserts one item from the hopper into the inventory the hopper is pointing at.
     */
    private boolean insertItemToInventory() {
        IInventory iinventory = this.getOutputInventory();

        if (iinventory == null) {
            return false;
        }
        else {
            for (int i = 0; i < this.getSizeInventory(); ++i)
            {
                if (this.getStackInSlot(i) != null)
                {
                    ItemStack itemstack = this.getStackInSlot(i).copy();
                    ItemStack itemstack1 = insertStack(iinventory, this.decrStackSize(i, 1), Facing.oppositeSide[BlockHopper.getDirectionFromMetadata(this.getBlockMetadata())]);

                    if (itemstack1 == null || itemstack1.stackSize == 0)
                    {
                        iinventory.onInventoryChanged();
                        return true;
                    }

                    this.setInventorySlotContents(i, itemstack);
                }
            }
            return false;
        }
    }

    /**
     * Sucks one item into the given hopper from an inventory or EntityItem above it.
     */
    public static boolean suckItemsIntoHopper(Hopper par0Hopper) {
        IInventory iinventory = getInventoryAboveHopper(par0Hopper);

        if (iinventory != null)
        {
            byte b0 = 0;

            if (iinventory instanceof ISidedInventory && b0 > -1)
            {
                ISidedInventory isidedinventory = (ISidedInventory)iinventory;
                int[] aint = isidedinventory.getAccessibleSlotsFromSide(b0);

                for (int i = 0; i < aint.length; ++i)
                {
                    if (insertStackFromInventory(par0Hopper, iinventory, aint[i], b0))
                    {
                        return true;
                    }
                }
            }
            else
            {
                int j = iinventory.getSizeInventory();

                for (int k = 0; k < j; ++k)
                {
                    if (insertStackFromInventory(par0Hopper, iinventory, k, b0))
                    {
                        return true;
                    }
                }
            }
        }
        else
        {
            EntityItem entityitem = getEntityAbove(par0Hopper.getWorldObj(), par0Hopper.getXPos(), par0Hopper.getYPos() + 1.0D, par0Hopper.getZPos());

            if (entityitem != null)
            {
                return insertStackFromEntity(par0Hopper, entityitem);
            }
        }

        return false;
    }

    private static boolean insertStackFromInventory(Hopper par0Hopper, IInventory par1IInventory, int par2, int par3) {
        ItemStack itemstack = par1IInventory.getStackInSlot(par2);

        if (itemstack != null && canExtractItemFromInventory(par1IInventory, itemstack, par2, par3))
        {
            ItemStack itemstack1 = itemstack.copy();
            ItemStack itemstack2 = insertStack(par0Hopper, par1IInventory.decrStackSize(par2, 1), -1);

            if (itemstack2 == null || itemstack2.stackSize == 0)
            {
                par1IInventory.onInventoryChanged();
                return true;
            }

            par1IInventory.setInventorySlotContents(par2, itemstack1);
        }
        return false;
    }

    public static boolean insertStackFromEntity(IInventory par0IInventory, EntityItem par1EntityItem)
    {
        boolean flag = false;

        if (par1EntityItem == null)
        {
            return false;
        }
        else
        {
            ItemStack itemstack = par1EntityItem.getEntityItem().copy();
            ItemStack itemstack1 = insertStack(par0IInventory, itemstack, -1);

            if (itemstack1 != null && itemstack1.stackSize != 0)
            {
                par1EntityItem.setEntityItemStack(itemstack1);
            }
            else
            {
                flag = true;
                par1EntityItem.setDead();
            }
            return flag;
        }
    }

    /**
     * Inserts a stack into an inventory. Args: Inventory, stack, side. Returns leftover items.
     */
    public static ItemStack insertStack(IInventory par0IInventory, ItemStack par1ItemStack, int par2)
    {
        if (par0IInventory instanceof ISidedInventory && par2 > -1)
        {
            ISidedInventory isidedinventory = (ISidedInventory)par0IInventory;
            int[] aint = isidedinventory.getAccessibleSlotsFromSide(par2);

            for (int j = 0; j < aint.length && par1ItemStack != null && par1ItemStack.stackSize > 0; ++j)
            {
                par1ItemStack = func_102014_c(par0IInventory, par1ItemStack, aint[j], par2);
            }
        }
        else
        {
            int k = par0IInventory.getSizeInventory();

            for (int l = 0; l < k && par1ItemStack != null && par1ItemStack.stackSize > 0; ++l)
            {
                par1ItemStack = func_102014_c(par0IInventory, par1ItemStack, l, par2);
            }
        }

        if (par1ItemStack != null && par1ItemStack.stackSize == 0)
        {
            par1ItemStack = null;
        }
        return par1ItemStack;
    }

    /**
     * Args: inventory, item, slot, side
     */
    private static boolean canInsertItemToInventory(IInventory par0IInventory, ItemStack par1ItemStack, int par2, int par3) {
        return !par0IInventory.isItemValidForSlot(par2, par1ItemStack) ? false : !(par0IInventory instanceof ISidedInventory) || ((ISidedInventory)par0IInventory).canInsertItem(par2, par1ItemStack, par3);
    }

    private static boolean canExtractItemFromInventory(IInventory par0IInventory, ItemStack par1ItemStack, int par2, int par3) {
        return !(par0IInventory instanceof ISidedInventory) || ((ISidedInventory)par0IInventory).canExtractItem(par2, par1ItemStack, par3);
    }

    private static ItemStack func_102014_c(IInventory par0IInventory, ItemStack par1ItemStack, int par2, int par3) {
        ItemStack itemstack1 = par0IInventory.getStackInSlot(par2);

        if (canInsertItemToInventory(par0IInventory, par1ItemStack, par2, par3))
        {
            boolean flag = false;

            if (itemstack1 == null)
            {
                int max = Math.min(par1ItemStack.getMaxStackSize(), par0IInventory.getInventoryStackLimit());
                if (max >= par1ItemStack.stackSize)
                {
                    par0IInventory.setInventorySlotContents(par2, par1ItemStack);
                    par1ItemStack = null;
                }
                else
                {
                    par0IInventory.setInventorySlotContents(par2, par1ItemStack.splitStack(max));
                }
                flag = true;
            }
            else if (areItemStacksEqualItem(itemstack1, par1ItemStack))
            {
                int max = Math.min(par1ItemStack.getMaxStackSize(), par0IInventory.getInventoryStackLimit());
                if (max > itemstack1.stackSize)
                {
                    int l = Math.min(par1ItemStack.stackSize, max - itemstack1.stackSize);
                    par1ItemStack.stackSize -= l;
                    itemstack1.stackSize += l;
                    flag = l > 0;
                }
            }

            if (flag)
            {
                if (par0IInventory instanceof TileEntityHopper)
                {
                    ((TileEntityHopper)par0IInventory).setTransferCooldown(8);
                    par0IInventory.onInventoryChanged();
                }

                par0IInventory.onInventoryChanged();
            }
        }
        return par1ItemStack;
    }

    /**
     * Gets the inventory the hopper is pointing at.
     */
    private IInventory getOutputInventory() {
        int i = BlockHopper.getDirectionFromMetadata(this.getBlockMetadata());
        return getInventoryAtLocation(this.getWorldObj(), (double)(this.xCoord + Facing.offsetsXForSide[i]), (double)(this.yCoord + Facing.offsetsYForSide[i]), (double)(this.zCoord + Facing.offsetsZForSide[i]));
    }

    /**
     * Looks for anything, that can hold items (like chests, furnaces, etc.) one block above the given hopper.
     */
    public static IInventory getInventoryAboveHopper(Hopper par0Hopper) {
        return getInventoryAtLocation(par0Hopper.getWorldObj(), par0Hopper.getXPos(), par0Hopper.getYPos() + 1.0D, par0Hopper.getZPos());
    }

    public static EntityItem getEntityAbove(World par0World, double par1, double par3, double par5) {
        List list = par0World.selectEntitiesWithinAABB(EntityItem.class, AxisAlignedBB.getAABBPool().getAABB(par1, par3, par5, par1 + 1.0D, par3 + 1.0D, par5 + 1.0D), IEntitySelector.selectAnything);
        return list.size() > 0 ? (EntityItem)list.get(0) : null;
    }

    /**
     * Gets an inventory at the given location to extract items into or take items from. Can find either a tile entity
     * or regular entity implementing IInventory.
     */
    public static IInventory getInventoryAtLocation(World par0World, double par1, double par3, double par5) {
        IInventory iinventory = null;
        int i = MathHelper.floor_double(par1);
        int j = MathHelper.floor_double(par3);
        int k = MathHelper.floor_double(par5);
        TileEntity tileentity = par0World.getBlockTileEntity(i, j, k);

        if (tileentity != null && tileentity instanceof IInventory)
        {
            iinventory = (IInventory)tileentity;

            if (iinventory instanceof TileEntityChest)
            {
                int l = par0World.getBlockId(i, j, k);
                Block block = Block.blocksList[l];

                if (block instanceof BlockChest)
                {
                    iinventory = ((BlockChest)block).getInventory(par0World, i, j, k);
                }
            }
        }

        if (iinventory == null)
        {
            List list = par0World.getEntitiesWithinAABBExcludingEntity((Entity)null, AxisAlignedBB.getAABBPool().getAABB(par1, par3, par5, par1 + 1.0D, par3 + 1.0D, par5 + 1.0D), IEntitySelector.selectInventories);

            if (list != null && list.size() > 0)
            {
                iinventory = (IInventory)list.get(par0World.rand.nextInt(list.size()));
            }
        }

        return iinventory;
    }

    private static boolean areItemStacksEqualItem(ItemStack par0ItemStack, ItemStack par1ItemStack) {
        return par0ItemStack.itemID != par1ItemStack.itemID ? false : (par0ItemStack.getItemDamage() != par1ItemStack.getItemDamage() ? false : (par0ItemStack.stackSize > par0ItemStack.getMaxStackSize() ? false : ItemStack.areItemStackTagsEqual(par0ItemStack, par1ItemStack)));
    }

    /**
     * Gets the world X position for this hopper entity.
     */
    public double getXPos() {
        return (double)this.xCoord;
    }

    /**
     * Gets the world Y position for this hopper entity.
     */
    public double getYPos() {
        return (double)this.yCoord;
    }

    /**
     * Gets the world Z position for this hopper entity.
     */
    public double getZPos() {
        return (double)this.zCoord;
    }

    public void setTransferCooldown(int par1) {
        this.transferCooldown = par1;
    }

    public boolean isCoolingDown() {
        return this.transferCooldown > 0;
    }
}