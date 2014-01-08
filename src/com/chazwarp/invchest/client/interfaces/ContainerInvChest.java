package com.chazwarp.invchest.client.interfaces;

import com.chazwarp.invchest.tileentity.TileEntityInvChest;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import com.chazwarp.invchest.client.interfaces.SlotArmor;
import net.minecraft.item.ItemStack;

public class ContainerInvChest extends Container{

	private TileEntityInvChest invChest;
	
	public ContainerInvChest(InventoryPlayer invPlayer, TileEntityInvChest invChest) {
		this.invChest = invChest;
		
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
		//Adds The Players Armor Slots
		int z = 39;//Slot Number To Start With
		for(int x = 0; x < 4; x++) {
				addSlotToContainer(new Slot(invPlayer, z, 176, 172 + 18 * x));
				z--;
		}
		//Adds The Chests Armor Slots
		for(int x = 0; x < 4; x++) {
			addSlotToContainer(new Slot(invChest, x, 8, 8 + 18 * x)); 
		}
		//Adds The Configuration Card Slot
		addSlotToContainer(new SlotCard(invChest, 4, 80, 62));
		
		//Adds The Chests Main Inventory
		for(int y = 0; y < 3; y++) {
			for(int x = 0; x < 9; x++) {
				addSlotToContainer(new Slot(invChest, x + y * 9 + 5, 8 + 18 * x, 84 + y * 18));
			}
		}
		//Adds The Chests Hotbar
		int w = 32;//Slot Number To Start With
		for(int x = 0; x < 9; x++) {
			addSlotToContainer(new Slot(invChest, w, 8 + 18 * x, 140));
			w++;
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

}
