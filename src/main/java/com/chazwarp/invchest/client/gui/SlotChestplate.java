/**
 *@author Chaz Kerby
 */
package com.chazwarp.invchest.client.gui;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotChestplate extends Slot {

	public SlotChestplate(IInventory IInv, int slotIndex, int xDisplay,
			int yDisplay) {
		super(IInv, slotIndex, xDisplay, yDisplay);
	}

	@Override
	public boolean isItemValid(ItemStack itemstack) {

		if (itemstack != null
				&& itemstack.getDisplayName().contains("Chestplate")
				|| itemstack.getDisplayName().contains("Armor")) {
			return true;
		}

		else {
			return false;
		}
	}
}