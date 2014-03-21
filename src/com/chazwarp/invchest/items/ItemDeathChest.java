/**
@author Chaz Kerby
*/
package com.chazwarp.invchest.items;

import net.minecraft.item.ItemBlock;

public class ItemDeathChest extends ItemBlock {
	
	public ItemDeathChest(int id) {
		super(id);
		setHasSubtypes(true);
	}
	
	@Override
	public int getMetadata(int dmg) {
		return dmg;
	}

}