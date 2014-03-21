/**
@author Chaz Kerby
*/
package com.chazwarp.invchest.client.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

import com.chazwarp.invchest.tileentity.TileEntityInventoryChest;

public class ContainerInventoryChest extends Container{

	private TileEntityInventoryChest invChest;
	
	public ContainerInventoryChest(InventoryPlayer invPlayer, TileEntityInventoryChest invChest) {
		this.invChest = invChest;
		
		//Adds The Players Hotbar
		for(int x = 0; x < 9; x++) {
			addSlotToContainer(new Slot(invPlayer, x, 8 + 18 * x, 230));
		}
		
		//Adds The Players Main Inventory
		for(int y = 0; y < 3; y++) {
			for(int x = 0; x < 9; x++) {
				addSlotToContainer(new Slot(invPlayer, x + y * 9 + 9, 8 + 18 * x, 172 + y * 18));
			}
		}
		
		//Adds The Players Armor Slots
		int z = 39;//Slot Number To Start With
		for(int x = 0; x < 4; x++) {
				addSlotToContainer(new Slot(invPlayer, z, 176, 172 + 18 * x));
				z--;
		}
		
		//Adds The Chests Hotbar
		for(int x = 0; x < 9; x++) {
			addSlotToContainer(new Slot(invChest, x, 8 + 18 * x, 140));
		}
		
		//Adds The Chests Main Inventory
		for(int y = 0; y < 3; y++) {
			for(int x = 0; x < 9; x++) {
				addSlotToContainer(new Slot(invChest, x + y * 9 + 9, 8 + 18 * x, 84 + y * 18));
			}
		}
		
		//Adds The Chests Armor Slots
		int d = 39;//Slot Number To Start With
		for(int x = 0; x < 4; x++) {
			addSlotToContainer(new Slot(invChest, d, 8, 8 + 18 * x));
			d--;
		}
		
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer entityplayer) {
		return invChest.isUseableByPlayer(entityplayer);
	}
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int i) {
		return null;
	}
	
	public TileEntityInventoryChest getChest() {
		return invChest;
	}

}
