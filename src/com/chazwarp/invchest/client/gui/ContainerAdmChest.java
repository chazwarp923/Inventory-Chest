/**
@author Chaz Kerby
*/
package com.chazwarp.invchest.client.interfaces;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

import com.chazwarp.invchest.tileentity.TileEntityAdmChest;

public class ContainerAdmChest extends Container{

	private TileEntityAdmChest admChest;
	
	public ContainerAdmChest(InventoryPlayer invPlayer, TileEntityAdmChest admchest) {
		this.admChest = admchest;
		
		//Adds The Players Hotbar
		for(int x = 0; x < 9; x++) {
			addSlotToContainer(new Slot(invPlayer, x, 8 + 18 * x, 230));
		}
		//Adds The Players Inventory
		for(int y = 0; y < 3; y++) {
			for(int x = 0; x < 9; x++) {
				addSlotToContainer(new Slot(invPlayer, x + y * 9 + 9, 8 + 18 * x, 172 + y * 18));
			}
		}
		//Adds The Chests Armor Slots
		for(int x = 0; x < 4; x++) {
			addSlotToContainer(new Slot(admchest, x, 8, 8 + 18 * x)); 
		}
		
		//Adds The Chests Main Inventory
		for(int y = 0; y < 3; y++) {
			for(int x = 0; x < 9; x++) {
				addSlotToContainer(new Slot(admchest, x + y * 9 + 4, 8 + 18 * x, 84 + y * 18));
			}
		}
		//Adds The Chests Hotbar
		int w = 31;//Slot Number To Start With
		for(int x = 0; x < 9; x++) {
			addSlotToContainer(new Slot(admchest, w, 8 + 18 * x, 140));
			w++;
		}
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer entityplayer) {
		return admChest.isUseableByPlayer(entityplayer);
	}
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int i) {
		return null;
	}

}
