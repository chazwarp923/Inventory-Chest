package com.chazwarp.invchest.blocks;

import net.minecraft.block.Block;

import com.chazwarp.invchest.items.ItemAdminChest;
import com.chazwarp.invchest.items.ItemInventoryChest;
import com.chazwarp.invchest.items.ItemPresent;
import com.chazwarp.invchest.lib.BlockInfo;
import com.chazwarp.invchest.tileentity.TileEntityAdmChest;
import com.chazwarp.invchest.tileentity.TileEntityInvChest;
import com.chazwarp.invchest.tileentity.TileEntityPresent;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class Blocks {

	public static Block chestInv;
	
		public static void initChestInv() {
			chestInv = new BlockInventoryChest(BlockInfo.INV_CHEST_ID);
			GameRegistry.registerBlock(chestInv, ItemInventoryChest.class, BlockInfo.INV_CHEST_KEY);
		}
		
		public static void addChestInvName() {
			LanguageRegistry.addName(chestInv, BlockInfo.INV_CHEST_UNLOCALIZED_NAME);
		}
		
	public static Block chestAdm;
		
		public static void initChestAdm() {
			chestAdm = new BlockAdminChest(BlockInfo.ADM_CHEST_ID);
			GameRegistry.registerBlock(chestAdm, ItemAdminChest.class, BlockInfo.ADM_CHEST_KEY);
		}
		
		public static void addChestAdmName() {
			LanguageRegistry.addName(chestAdm, BlockInfo.ADM_CHEST_UNLOCALIZED_NAME);
		}
		
	public static Block present;
	
		public static void initPresent() {
			present = new BlockPresent(BlockInfo.PRESENT_ID);
			GameRegistry.registerBlock(present, ItemPresent.class, BlockInfo.PRESENT_KEY);
		}
		
		public static void addPresentName() {
			LanguageRegistry.addName(present, BlockInfo.PRESENT_UNLOCALIZED_NAME);
		}
		
		
	public static void initBlocks() {
		initChestInv();
		initChestAdm();
		initPresent();
	}
	
	public static void addNames() {
		addChestInvName();
		addChestAdmName();
		addPresentName();
	}
		
	public static void registerTileEntities() {
		GameRegistry.registerTileEntity(TileEntityInvChest.class, BlockInfo.INV_CHEST_KEY);
		GameRegistry.registerTileEntity(TileEntityAdmChest.class, BlockInfo.ADM_CHEST_KEY);
		GameRegistry.registerTileEntity(TileEntityPresent.class, BlockInfo.PRESENT_KEY);
	}
		
}
