/**
@author Chaz Kerby
*/
package com.chazwarp.invchest;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import com.chazwarp.invchest.blocks.Blocks;

public class InvTab extends CreativeTabs {
	
    public static InvTab tab = new InvTab();

    public InvTab() {
        super("invTab");
    }

    @Override
    public ItemStack getIconItemStack() {
    	return new ItemStack(Blocks.inventoryChest);
    }

	@Override
	public Item getTabIconItem() {
		return Items.apple;
	}
}