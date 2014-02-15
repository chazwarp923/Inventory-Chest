/**
@author Chaz Kerby
*/
package com.chazwarp.invchest.client;

import com.chazwarp.invchest.CommonProxy;
import com.chazwarp.invchest.lib.BlockInfo;
import com.chazwarp.invchest.render.PresentRenderer;
import com.chazwarp.invchest.tileentity.TileEntityPresent;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy {
       
        @Override
        public void registerRenderers() {
                // This is for rendering entities and so forth later on
        	ClientRegistry.bindTileEntitySpecialRenderer(TileEntityPresent.class, new PresentRenderer());
        }
       
}
