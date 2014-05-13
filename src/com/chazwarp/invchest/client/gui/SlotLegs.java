/**
*@author Chaz Kerby
*/
package com.chazwarp.invchest.client.gui;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotLegs extends Slot{

	public SlotLegs(IInventory IInv, int slotIndex, int xDisplay, int yDisplay) {
		super(IInv, slotIndex, xDisplay, yDisplay);
	}
	
	public boolean isItemValid(ItemStack itemstack) {
		
		if(itemstack != null && itemstack.getDisplayName().contains("Leg") || itemstack.getDisplayName().contains("Pants")) {
			return true;
		}
		
		else {
		return false;	
		}
	}

}
