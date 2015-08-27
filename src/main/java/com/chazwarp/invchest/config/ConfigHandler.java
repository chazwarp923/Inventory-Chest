/**
@author Chaz Kerby
 */
package com.chazwarp.invchest.config;

import java.io.File;

import com.chazwarp.invchest.lib.BlockInfo;

public class ConfigHandler {

	public static void init(File file) {
		Configuration config = new Configuration(file);

		config.load();

		BlockInfo.INVENTORY_CHEST_ID = config.getBlock(BlockInfo.INVENTORY_CHEST_KEY, BlockInfo.INVENTORY_CHEST_DEFAULT).getInt();
		BlockInfo.ADMIN_CHEST_ID = config.getBlock(BlockInfo.ADMIN_CHEST_KEY, BlockInfo.ADMIN_CHEST_DEFAULT).getInt();
		BlockInfo.DEATH_CHEST_ID = config.getBlock(BlockInfo.DEATH_CHEST_KEY, BlockInfo.DEATH_CHEST_DEFAULT).getInt();

		config.save();
	}
}