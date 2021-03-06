/**
@author Chaz Kerby
 */
package com.chazwarp.invchest;

import net.minecraftforge.common.MinecraftForge;

import com.chazwarp.invchest.blocks.ModBlocks;
import com.chazwarp.invchest.client.gui.GuiHandler;
import com.chazwarp.invchest.config.ConfigHandler;
import com.chazwarp.invchest.crafting.Shaped;
import com.chazwarp.invchest.crafting.Shapeless;
import com.chazwarp.invchest.eventhandler.DeathEventHandler;
import com.chazwarp.invchest.lib.Reference;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION)
public class InventoryChest {

	@Mod.Instance(Reference.MOD_ID)
	public static InventoryChest instance;

	// Says where the client and server 'proxy' code is loaded.
	@SidedProxy(clientSide = "com.chazwarp.invchest.client.ClientProxy", serverSide = "com.chazwarp.invchest.CommonProxy")
	public static CommonProxy proxy;

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {

		ConfigHandler.init(event.getSuggestedConfigurationFile());

		// Registers Blocks
		ModBlocks.initBlocks();

		// Registers Renderers
		proxy.registerRenderers();

		// Adds Recipe
		Shaped.addCrafting();
		Shapeless.addCrafting();

		// Hard Codes The Mod Data
		event.getModMetadata().credits = "enderblaze2 & Reika";
		event.getModMetadata().description = "A Mod That Adds Different Types of Chests";
		event.getModMetadata().logoFile = "assets/" + Reference.RESOURCE_PREFIX
				+ "/textures/logo.png";
		event.getModMetadata().modId = Reference.MOD_ID;
		event.getModMetadata().name = Reference.MOD_NAME;
		event.getModMetadata().version = Reference.VERSION;
	}

	@Mod.EventHandler
	public void Init(FMLInitializationEvent event) {
		// Registers The EventHandlers
		MinecraftForge.EVENT_BUS.register(new DeathEventHandler());

		// Registers The TileEntities
		ModBlocks.registerTileEntities();

		// Registers The Gui Handlers
		new GuiHandler();
	}

	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event) {

	}
}
