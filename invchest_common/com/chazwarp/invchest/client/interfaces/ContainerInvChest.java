package com.chazwarp.invchest.client.interfaces;

import com.chazwarp.invchest.tileentity.TileEntityInvChest;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerInvChest extends Container{

	private TileEntityInvChest invChest;
	
	public ContainerInvChest(InventoryPlayer invPlayer, TileEntityInvChest invChest) {
		this.invChest = invChest;
		
		for(int x = 0; x < 9; x++) {
			addSlotToContainer(new Slot(invPlayer, x, 8 + 18 * x, 225));
			//Renders The Players Hotbar
		}
		
		for(int y = 0; y < 3; y++) {
			for(int x = 0; x < 9; x++) {
				addSlotToContainer(new Slot(invPlayer, x + y * 9 + 9, 8 + 18 * x, 167 + y * 18));
				//Renders The Players Inventory
			}
		}
		int z = 39;
		for(int x = 0; x < 4; x++) {
				addSlotToContainer(new Slot(invPlayer, z, 176, 167 + 18 * x));
				//Renders The Players Armor Slots
				z--;
		}
		
		for(int x = 0; x < 4; x++) {
			addSlotToContainer(new SlotArmor(invChest, x, 8, 8 + 18 * x));
			//Renders The Chests Armor Slots 
		}
		
		addSlotToContainer(new Slot(invChest, 4, 80, 62));
		//Renders The Configuration Card Slot
		
		for(int y = 0; y < 3; y++) {
			for(int x = 0; x < 9; x++) {
				addSlotToContainer(new Slot(invChest, x + y * 9 + 9, 8 + 18 * x, 84 + y * 18));
				//Renders The Chests Main Inventory
			}
		}
		
		for(int x = 0; x < 9; x++) {
			addSlotToContainer(new Slot(invChest, x, 8 + 18 * x, 140));
			//Renders The Chests Hotbar
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
