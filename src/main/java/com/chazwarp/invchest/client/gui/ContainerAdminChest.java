/**
@author Chaz Kerby
 */
package com.chazwarp.invchest.client.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

import com.chazwarp.invchest.tileentity.TileEntityAdminChest;

public class ContainerAdminChest extends Container {

	private TileEntityAdminChest adminChest;

	public ContainerAdminChest(InventoryPlayer invMainPlayer,
			TileEntityAdminChest adminChest) {
		this.adminChest = adminChest;

		// Adds The Main Players Hotbar
		for (int x = 0; x < 9; x++) {
			addSlotToContainer(new Slot(invMainPlayer, x, 8 + 18 * x, 230));
		}
		// Adds The Main Players Inventory
		for (int y = 0; y < 3; y++) {
			for (int x = 0; x < 9; x++) {
				addSlotToContainer(new Slot(invMainPlayer, x + y * 9 + 9,
						8 + 18 * x, 172 + y * 18));
			}
		}

		String username = adminChest.getPlayerName();

		if (adminChest.worldObj.getPlayerEntityByName(username) != null) {

			EntityPlayer player = adminChest.worldObj
					.getPlayerEntityByName(username);
			InventoryPlayer invObservedPlayer = player.inventory;

			// Adds The Observed Players Armor Slots
			addSlotToContainer(new SlotHelmet(invObservedPlayer, 39, 8, 8));
			addSlotToContainer(new SlotChestplate(invObservedPlayer, 38, 8, 26));
			addSlotToContainer(new SlotLegs(invObservedPlayer, 37, 8, 44));
			addSlotToContainer(new SlotBoots(invObservedPlayer, 36, 8, 62));

			// Adds The Observed Players Inventory
			for (int x = 0; x < 9; x++) {
				addSlotToContainer(new Slot(invObservedPlayer, x, 8 + 18 * x,
						84));
			}

			for (int x = 9; x < 18; x++) {
				addSlotToContainer(new Slot(invObservedPlayer, x,
						8 + 18 * (x - 9), 102));
			}

			for (int x = 18; x < 35; x++) {
				addSlotToContainer(new Slot(invObservedPlayer, x,
						8 + 18 * (x - 18), 120));
			}

			// Adds The Observed Players Hotbar
			// int w = 31;//Slot Number To Start With
			for (int x = 0; x < 9; x++) {
				addSlotToContainer(new Slot(invObservedPlayer, x, 8 + 18 * x,
						140));
				// w++;
			}
		}// End If
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