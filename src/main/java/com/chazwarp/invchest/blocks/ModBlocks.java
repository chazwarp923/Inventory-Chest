/**
@author Chaz Kerby
 */
package com.chazwarp.invchest.blocks;

import net.minecraft.block.Block;

import com.chazwarp.invchest.items.ItemAdminChest;
import com.chazwarp.invchest.items.ItemDeathChest;
import com.chazwarp.invchest.items.ItemInventoryChest;
import com.chazwarp.invchest.lib.BlockInfo;
import com.chazwarp.invchest.tileentity.TileEntityAdminChest;
import com.chazwarp.invchest.tileentity.TileEntityDeathChest;
import com.chazwarp.invchest.tileentity.TileEntityInventoryChest;
import com.chazwarp.miscadditions.blocks.BlockHoppingBuffer;
import com.chazwarp.miscadditions.blocks.BlockPresent;
import com.chazwarp.miscadditions.blocks.tileentity.TileEntityHoppingBuffer;
import com.chazwarp.miscadditions.blocks.tileentity.TileEntityPresent;
import com.chazwarp.miscadditions.items.ItemHoppingBuffer;
import com.chazwarp.miscadditions.items.ItemPresent;

import cpw.mods.fml.common.registry.GameRegistry;

public class ModBlocks {

	public static Block inventoryChest;

	public static void initInventoryChest() {
		inventoryChest = new BlockInventoryChest(BlockInfo.INVENTORY_CHEST_ID);
		GameRegistry.registerBlock(inventoryChest, ItemInventoryChest.class,
				BlockInfo.INVENTORY_CHEST_KEY);
	}

	public static Block adminChest;

	public static void initAdminChest() {
		adminChest = new BlockAdminChest(BlockInfo.ADMIN_CHEST_ID);
		GameRegistry.registerBlock(adminChest, ItemAdminChest.class,
				BlockInfo.ADMIN_CHEST_KEY);
	}

	public static Block deathChest;

	public static void initDeathChest() {
		deathChest = new BlockDeathChest(BlockInfo.DEATH_CHEST_ID);
		GameRegistry.registerBlock(deathChest, ItemDeathChest.class,
				BlockInfo.DEATH_CHEST_KEY);
	}

	public static void initBlocks() {
		initInventoryChest();
		initAdminChest();
		initDeathChest();
	}

	public static void registerTileEntities() {
		GameRegistry.registerTileEntity(TileEntityInventoryChest.class,
				BlockInfo.INVENTORY_CHEST_KEY);
		GameRegistry.registerTileEntity(TileEntityAdminChest.class,
				BlockInfo.ADMIN_CHEST_KEY);
		GameRegistry.registerTileEntity(TileEntityDeathChest.class,
				BlockInfo.DEATH_CHEST_KEY);
	}
}