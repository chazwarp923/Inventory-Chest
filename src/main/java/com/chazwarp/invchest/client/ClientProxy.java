/**
@author Chaz Kerby
 */
package com.chazwarp.invchest.client;

import com.chazwarp.invchest.CommonProxy;
import com.chazwarp.invchest.render.RenderHoppingBuffer;
import com.chazwarp.invchest.render.RenderPresent;
import com.chazwarp.invchest.tileentity.TileEntityHoppingBuffer;
import com.chazwarp.invchest.tileentity.TileEntityPresent;

import cpw.mods.fml.client.registry.ClientRegistry;

public class ClientProxy extends CommonProxy {

	@Override
	public void registerRenderers() {
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityPresent.class,
				new RenderPresent());
		ClientRegistry.bindTileEntitySpecialRenderer(
				TileEntityHoppingBuffer.class, new RenderHoppingBuffer());
	}
}