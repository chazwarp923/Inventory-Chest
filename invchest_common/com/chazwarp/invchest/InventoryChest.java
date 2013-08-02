package com.chazwarp.invchest;

import com.chazwarp.invchest.blocks.Blocks;
import com.chazwarp.invchest.config.ConfigHandler;
import com.chazwarp.invchest.lib.Reference;
import com.chazwarp.invchest.network.PacketHandler;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION)
@NetworkMod(channels = { Reference.CHANNEL }, clientSideRequired = true, serverSideRequired = false, packetHandler = PacketHandler.class)
public class InventoryChest {

    @Instance(Reference.MOD_ID)
    public static InventoryChest instance;
    
    // Says where the client and server 'proxy' code is loaded.
    @SidedProxy(clientSide="com.chazwarp.invchest.client.ClientProxy", serverSide="com.chazwarp.invchest.CommonProxy")
    public static CommonProxy proxy;
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
    	ConfigHandler.init(event.getSuggestedConfigurationFile());
        Blocks.initChestInv();
    	Blocks.initChestAdm();
    	proxy.registerRenderers();
        
    }
    
    @EventHandler
    public void Init(FMLInitializationEvent event) {
        Blocks.addChestInvName();
        Blocks.addChestAdmName();
        
    }
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        
    }
}
