package com.chazwarp.invchest.blocks;

import com.chazwarp.invchest.lib.BlockInfo;
import com.chazwarp.invchest.tileentity.TileEntityInvChest;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

import net.minecraft.block.Block;

public class Blocks {

	public static Block chestInv;
	
		public static void initChestInv() {
			chestInv = new BlockInvChest(BlockInfo.INV_CHEST_ID);
			GameRegistry.registerBlock(chestInv, BlockInfo.INV_CHEST_KEY);
		}
		
		public static void addChestInvName() {
			LanguageRegistry.addName(chestInv, BlockInfo.INV_CHEST_UNLOCALIZED_NAME);
		}
		
	public static Block chestAdm;
		
		public static void initChestAdm() {
			chestAdm = new BlockAdmChest(BlockInfo.ADM_CHEST_ID);
			GameRegistry.registerBlock(chestAdm, BlockInfo.ADM_CHEST_KEY);
		}
		
		public static void addChestAdmName() {
			LanguageRegistry.addName(chestAdm, BlockInfo.ADM_CHEST_UNLOCALIZED_NAME);
		}
		
	public static void registerTileEntities() {
		GameRegistry.registerTileEntity(TileEntityInvChest.class, BlockInfo.INV_CHEST_KEY);
	}
		
}
