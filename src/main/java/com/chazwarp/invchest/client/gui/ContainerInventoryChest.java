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

public class ContainerInventoryChest extends Container {

	private TileEntityInventoryChest invChest;

	public ContainerInventoryChest(InventoryPlayer invPlayer,
			TileEntityInventoryChest invChest) {
		this.invChest = invChest;

		// Adds The Players Hotbar
		for (int x = 0; x < 9; x++) {
			addSlotToContainer(new Slot(invPlayer, x, 8 + 18 * x, 230));
		}

		// Adds The Players Main Inventory
		for (int y = 0; y < 3; y++) {
			for (int x = 0; x < 9; x++) {
				addSlotToContainer(new Slot(invPlayer, x + y * 9 + 9,
						8 + 18 * x, 172 + y * 18));
			}
		}

		// Adds The Players Armor Slots
		addSlotToContainer(new SlotHelmet(invPlayer, 39, 176, 172));
		addSlotToContainer(new SlotChestplate(invPlayer, 38, 176, 190));
		addSlotToContainer(new SlotLegs(invPlayer, 37, 176, 208));
		addSlotToContainer(new SlotBoots(invPlayer, 36, 176, 226));

		// Adds The Chests Hotbar
		for (int x = 0; x < 9; x++) {
			addSlotToContainer(new Slot(invChest, x, 8 + 18 * x, 140));
		}

		// Adds The Chests Main Inventory
		for (int y = 0; y < 3; y++) {
			for (int x = 0; x < 9; x++) {
				addSlotToContainer(new Slot(invChest, x + y * 9 + 9,
						8 + 18 * x, 84 + y * 18));
			}
		}

		// Adds The Chests Armor Slots
		for (int x = 0; x < 4; x++) {
			addSlotToContainer(new SlotHelmet(invChest, 39, 8, 8));
			addSlotToContainer(new SlotChestplate(invChest, 38, 8, 26));
			addSlotToContainer(new SlotLegs(invChest, 37, 8, 44));
			addSlotToContainer(new SlotBoots(invChest, 36, 8, 62));
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