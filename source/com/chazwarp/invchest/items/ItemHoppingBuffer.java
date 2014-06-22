/**
@author Chaz Kerby
*/
package com.chazwarp.invchest.items;

import net.minecraft.item.ItemBlock;

public class ItemHoppingBuffer extends ItemBlock {
	
	public ItemHoppingBuffer(int id) {
		super(id);
		setHasSubtypes(true);
	}
	
	@Override
	public int getMetadata(int dmg) {
		return dmg;
	}
}