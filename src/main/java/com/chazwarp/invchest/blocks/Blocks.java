/**
@author Chaz Kerby
*/
package com.chazwarp.invchest.blocks;

import net.minecraft.block.Block;

import com.chazwarp.invchest.items.ItemAdminChest;
import com.chazwarp.invchest.items.ItemDeathChest;
import com.chazwarp.invchest.items.ItemHoppingBuffer;
import com.chazwarp.invchest.items.ItemInventoryChest;
import com.chazwarp.invchest.items.ItemPresent;
import com.chazwarp.invchest.lib.BlockInfo;
import com.chazwarp.invchest.tileentity.TileEntityAdminChest;
import com.chazwarp.invchest.tileentity.TileEntityDeathChest;
import com.chazwarp.invchest.tileentity.TileEntityHoppingBuffer;
import com.chazwarp.invchest.tileentity.TileEntityInventoryChest;
import com.chazwarp.invchest.tileentity.TileEntityPresent;

import cpw.mods.fml.common.registry.GameRegistry;

public class Blocks {

	public static Block inventoryChest;
	
		public static void initInventoryChest() {
			inventoryChest = new BlockInventoryChest(BlockInfo.INVENTORY_CHEST_ID);
			GameRegistry.registerBlock(inventoryChest, ItemInventoryChest.class, BlockInfo.INVENTORY_CHEST_KEY);
		}
		
	public static Block adminChest;
		
		public static void initAdminChest() {
			adminChest = new BlockAdminChest(BlockInfo.ADMIN_CHEST_ID);
			GameRegistry.registerBlock(adminChest, ItemAdminChest.class, BlockInfo.ADMIN_CHEST_KEY);
		}
		
	public static Block present;
	
		public static void initPresent() {
			present = new BlockPresent();
			GameRegistry.registerBlock(present, ItemPresent.class, BlockInfo.PRESENT_KEY);
		}
		
	public static Block deathChest;
		
		public static void initDeathChest() {
			deathChest = new BlockDeathChest(BlockInfo.DEATH_CHEST_ID);
			GameRegistry.registerBlock(deathChest, ItemDeathChest.class, BlockInfo.DEATH_CHEST_KEY);
		}
		
	public static Block hoppingBuffer;
		
		public static void initHoppingBuffer() {
			hoppingBuffer = new BlockHoppingBuffer(BlockInfo.HOPPING_BUFFER_ID);
			GameRegistry.registerBlock(hoppingBuffer, ItemHoppingBuffer.class, BlockInfo.HOPPING_BUFFER_KEY);
		}		
		
	public static void initBlocks() {
		initInventoryChest();
		initAdminChest();
		initPresent();
		initDeathChest();
		initHoppingBuffer();
	}
		
	public static void registerTileEntities() {
		GameRegistry.registerTileEntity(TileEntityInventoryChest.class, BlockInfo.INVENTORY_CHEST_KEY);
		GameRegistry.registerTileEntity(TileEntityAdminChest.class, BlockInfo.ADMIN_CHEST_KEY);
		GameRegistry.registerTileEntity(TileEntityPresent.class, BlockInfo.PRESENT_KEY);
		GameRegistry.registerTileEntity(TileEntityDeathChest.class, BlockInfo.DEATH_CHEST_KEY);
		GameRegistry.registerTileEntity(TileEntityHoppingBuffer.class, BlockInfo.HOPPING_BUFFER_KEY);
	}	
}