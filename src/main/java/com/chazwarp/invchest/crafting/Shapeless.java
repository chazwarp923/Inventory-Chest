/**
@author Chaz Kerby
 */
package com.chazwarp.invchest.crafting;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import com.chazwarp.invchest.blocks.ModBlocks;

import cpw.mods.fml.common.registry.GameRegistry;

public class Shapeless {

	public static void addCrafting() {

		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.inventoryChest), new ItemStack(Item.skull, 1, 3), new ItemStack(Block.chest));
	}
}