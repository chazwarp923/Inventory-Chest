/**
*@author Chaz Kerby
*/
package com.chazwarp.invchest.client.gui;

import net.minecraft.entity.Entity;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

public class SlotHelmet extends Slot{

	public SlotHelmet(IInventory IInv, int slotIndex, int xDisplay, int yDisplay) {
		super(IInv, slotIndex, xDisplay, yDisplay);
	}
	
	public boolean isItemValid(ItemStack itemstack) {
		
		if(itemstack != null && itemstack.getDisplayName().contains("Helm") || itemstack.getDisplayName().contains("Hat") || itemstack.getDisplayName().contains("Gogg")) {
			return true;
		}
		
		else {
		return false;	
		}
	}

}
