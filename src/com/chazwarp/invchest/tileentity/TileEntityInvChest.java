/**
@author Chaz Kerby
*/
package com.chazwarp.invchest.tileentity;

import java.util.Arrays;
import java.util.List;

import com.chazwarp.invchest.client.interfaces.ContainerInvChest;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

public class TileEntityInvChest extends TileEntity implements IInventory{

	private ItemStack[] items;
	
	public TileEntityInvChest() {
		items = new ItemStack[40];
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
			}else{
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
		return "InventoryInvChest";
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

	public void recieveButtonEvent(byte buttonId, EntityPlayer entityPlayer) {
		
		//Gets The Players Inventory
		ItemStack[] playerInv = new ItemStack[40];
		for(int i = 0; i < 39; i++) {
			playerInv[i] = entityPlayer.inventory.getStackInSlot(i);
		}
		
		//Gets The Chests Inventory
		Container container = entityPlayer.openContainer;
		TileEntityInvChest invChest = ((ContainerInvChest)container).getChest();
		ItemStack[] chestInv = new ItemStack[40];
		for(int i = 0; i < 39; i++) {
			chestInv[i] = invChest.getStackInSlot(i);
		}
		
		//Makes A Buffer Array
		ItemStack[] buffer = new ItemStack[40];
		
		
		switch(buttonId) {
		case 0:
			buffer = playerInv;
			playerInv = chestInv;
			chestInv = buffer;
			
			//Sets The Players Inventory
			for(int i = 0; i < 39; i++) {
				entityPlayer.inventory.setInventorySlotContents(i, playerInv[i]);
			}
			
			//Sets The Chests Inventory
			for(int i = 0; i < 39; i++) {
				invChest.setInventorySlotContents(i, chestInv[i]);
			}
			
		break;
		}
		
	}

}
