package com.chazwarp.invchest.client.interfaces;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

public class SlotCard extends Slot{

	public SlotCard(IInventory inventory, int id, int x, int y) {
		super(inventory, id, x, y);
	}
	
	public boolean isItemValid(ItemStack stack) {
		if(stack.getItem() instanceof ItemArmor) {
			return true;
		}//End IsItemValid If
		else {
			return false;
		}//End Else
	}
}