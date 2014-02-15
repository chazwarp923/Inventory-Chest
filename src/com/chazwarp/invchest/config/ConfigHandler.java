/**
@author Chaz Kerby
*/
package com.chazwarp.invchest.config;

import java.io.File;

import net.minecraftforge.common.Configuration;

import com.chazwarp.invchest.lib.BlockInfo;

public class ConfigHandler {

	
	public static void init(File file) {
		Configuration config = new Configuration(file);
		
		config.load();
		
		BlockInfo.INV_CHEST_ID = config.getBlock(BlockInfo.INV_CHEST_KEY, BlockInfo.INV_CHEST_DEFAULT).getInt();
		BlockInfo.ADM_CHEST_ID = config.getBlock(BlockInfo.ADM_CHEST_KEY, BlockInfo.ADM_CHEST_DEFAULT).getInt();
		BlockInfo.PRESENT_ID = config.getBlock(BlockInfo.PRESENT_KEY, BlockInfo.PRESENT_DEFAULT).getInt();
		
		config.save();
		
	}
}
