/**
@author Chaz Kerby
*/
package com.chazwarp.invchest.client.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;

import com.chazwarp.invchest.tileentity.TileEntityAdminChest;
import com.chazwarp.invchest.tileentity.TileEntityInventoryChest;

public class ContainerAdminChest extends Container{

	private TileEntityAdminChest adminChest;
	
	public ContainerAdminChest(InventoryPlayer invMainPlayer, TileEntityAdminChest adminChest) {
		this.adminChest = adminChest;
		
		//Adds The Main Players Hotbar
		for(int x = 0; x < 9; x++) {
			addSlotToContainer(new Slot(invMainPlayer, x, 8 + 18 * x, 230));
		}
		//Adds The Main Players Inventory
		for(int y = 0; y < 3; y++) {
			for(int x = 0; x < 9; x++) {
				addSlotToContainer(new Slot(invMainPlayer, x + y * 9 + 9, 8 + 18 * x, 172 + y * 18));
			}
		}
		
		//Adds The Observed Players Armor Slots
		
		String username = adminChest.getPlayerName();
		
		if(adminChest.worldObj.getPlayerEntityByName(username) != null) {
			
			InventoryPlayer invObservedPlayer = adminChest.worldObj.getPlayerEntityByName(username).inventory;
			
			for(int x = 0; x < 4; x++) {
				addSlotToContainer(new Slot(invObservedPlayer, x, 8, 8 + 18 * x)); 
			}
		
			//Adds The Observed Players Inventory
			for(int y = 0; y < 3; y++) {
				for(int x = 0; x < 9; x++) {
					addSlotToContainer(new Slot(invObservedPlayer, x + y * 9 + 4, 8 + 18 * x, 84 + y * 18));
				}
			}
			//Adds The Observed Players Hotbar
			int w = 31;//Slot Number To Start With
			for(int x = 0; x < 9; x++) {
				addSlotToContainer(new Slot(invObservedPlayer, w, 8 + 18 * x, 140));
				w++;
			}
		}//End isPlayerReal If
	}

	@Override
	public boolean canInteractWith(EntityPlayer entityplayer) {
		return adminChest.isUseableByPlayer(entityplayer);
	}
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int i) {
		return null;
	}

	public TileEntityAdminChest getChest() {
		return adminChest;
	}
	
}
