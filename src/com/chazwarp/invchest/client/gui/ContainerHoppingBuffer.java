/**
@author Chaz Kerby
*/
package com.chazwarp.invchest.client.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

import com.chazwarp.invchest.tileentity.TileEntityHoppingBuffer;
import com.chazwarp.invchest.tileentity.TileEntityInventoryChest;

public class ContainerHoppingBuffer extends Container{

	private TileEntityHoppingBuffer hopBuf;
	
	public ContainerHoppingBuffer(InventoryPlayer invPlayer, TileEntityHoppingBuffer hopBuf) {
		this.hopBuf = hopBuf;
		
		//Adds The Players Hotbar
		for(int x=0; x < 9; x++) {
			addSlotToContainer(new Slot(invPlayer, x, 8 + 18 * x, 117));
		}
		
		//Adds The Players Main Inventory
		for(int y=0; y < 3; y++) {
			for(int x = 0; x < 9; x++) {
				addSlotToContainer(new Slot(invPlayer, x + y * 9 + 9, 8 + 18 * x, 59 + y * 18));
			}
		}
		
		//Adds The Hopping Buffer's Inventory
		for(int x=0; x < 9; x++) {
			addSlotToContainer(new Slot(hopBuf, x, 8 + 18 * x, 13));
		}
		
		for(int x=9; x < hopBuf.getSizeInventory(); x++) {
			addSlotToContainer(new Slot(hopBuf, x, 8 + 18 * (x - 9), 31));
		}
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer entityplayer) {
		return hopBuf.isUseableByPlayer(entityplayer);
	}
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int i) {
		return null;
	}
	
	public TileEntityHoppingBuffer getChest() {
		return hopBuf;
	}

}
