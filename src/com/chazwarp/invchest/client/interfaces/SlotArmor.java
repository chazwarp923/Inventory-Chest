package com.chazwarp.invchest.client.interfaces;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

public class SlotArmor extends Slot{

	public SlotArmor(IInventory inventory, int id, int x, int y) {
		super(inventory, id, x, y);
	}
	
	public boolean isItemValid(ItemStack stack) {
		switch(slotNumber) {
		case 0:
			//if(getArmorType == 0)
			return true;
		}
		return false;
	}
}