/**
@author Chaz Kerby
*/
package com.chazwarp.invchest.client.interfaces;

import com.chazwarp.invchest.InventoryChest;
import com.chazwarp.invchest.tileentity.TileEntityAdmChest;
import com.chazwarp.invchest.tileentity.TileEntityInvChest;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
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
			if (te != null && te instanceof TileEntityInvChest) {
				return new ContainerInvChest(player.inventory, (TileEntityInvChest)te);
			}
			break;
		case 1:
			TileEntity te1 = world.getBlockTileEntity(x, y, z);
			if (te1 != null && te1 instanceof TileEntityAdmChest) {
				return new ContainerAdmChest(player.inventory, (TileEntityAdmChest)te1);
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
			if (te != null && te instanceof TileEntityInvChest) {
				return new GuiInvChest(player.inventory, (TileEntityInvChest)te);
			}
			break;
		case 1:
			TileEntity te1 = world.getBlockTileEntity(x, y, z);
			if (te1 != null && te1 instanceof TileEntityAdmChest) {
				return new GuiAdmChest(player.inventory, (TileEntityAdmChest)te1);
			}
			break;
		}
		return null;
	}

}
