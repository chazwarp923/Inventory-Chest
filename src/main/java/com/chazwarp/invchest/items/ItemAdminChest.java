/**
@author Chaz Kerby
*/
package com.chazwarp.invchest.items;

import net.minecraft.item.ItemBlock;

public class ItemAdminChest extends ItemBlock {
	
	public ItemAdminChest(int id) {
		super(id);
		setHasSubtypes(true);
	}
	
	@Override
	public int getMetadata(int dmg) {
		return dmg;
	}
}