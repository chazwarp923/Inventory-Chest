/**
@author Chaz Kerby
*/
package com.chazwarp.invchest.crafting;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

import com.chazwarp.invchest.blocks.Blocks;

import cpw.mods.fml.common.registry.GameRegistry;

public class Shaped {

public static void addCrafting() {
		
		ItemStack hopBuf = new ItemStack(Blocks.hoppingBuffer);
		
		GameRegistry.addShapedRecipe(hopBuf, new Object[] {"H", "C", 'H', Block.hopperBlock, 'C', Block.chest});
	}
}
