/**
@author Chaz Kerby
*/
package com.chazwarp.invchest.client.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.chazwarp.invchest.InventoryChest;
import com.chazwarp.invchest.tileentity.TileEntityAdminChest;
import com.chazwarp.invchest.tileentity.TileEntityInventoryChest;

import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.NetworkRegistry;

public class GuiHandler implements IGuiHandler{

	public GuiHandler() {
		NetworkRegistry.instance().registerGuiHandler(InventoryChest.instance, this);
	}
	
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		switch (ID) {
		case 0:
			TileEntity te = world.getBlockTileEntity(x, y, z);
			if (te != null && te instanceof TileEntityInventoryChest) {
				return new ContainerInventoryChest(player.inventory, (TileEntityInventoryChest)te);
			}
			break;
		case 1:
			TileEntity te1 = world.getBlockTileEntity(x, y, z);
			if (te1 != null && te1 instanceof TileEntityAdminChest) {
				return new ContainerAdminChest(player.inventory, (TileEntityAdminChest)te1);
			}
			break;
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		switch (ID) {
		case 0:
			TileEntity te = world.getBlockTileEntity(x, y, z);
			if (te != null && te instanceof TileEntityInventoryChest) {
				return new GuiInventoryChest(player.inventory, (TileEntityInventoryChest)te);
			}
			break;
		case 1:
			TileEntity te1 = world.getBlockTileEntity(x, y, z);
			if (te1 != null && te1 instanceof TileEntityAdminChest) {
				return new GuiAdminChest(player.inventory, (TileEntityAdminChest)te1);
			}
			break;
		}
		return null;
	}

}
