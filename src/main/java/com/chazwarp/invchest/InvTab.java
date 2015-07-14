/**
@author Chaz Kerby
 */
package com.chazwarp.invchest;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import com.chazwarp.invchest.blocks.ModBlocks;

public class InvTab extends CreativeTabs {

	public static InvTab tab = new InvTab();

	public InvTab() {
		super("invTab");
	}

	@Override
	public ItemStack getIconItemStack() {
		return new ItemStack(ModBlocks.inventoryChest);
	}

	@Override
	public Item getTabIconItem() {
		return Items.apple;
	}
}