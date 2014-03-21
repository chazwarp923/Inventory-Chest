/**
@author Chaz Kerby
*/
package com.chazwarp.invchest.network;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;

import com.chazwarp.invchest.client.gui.ContainerAdminChest;
import com.chazwarp.invchest.client.gui.ContainerInventoryChest;
import com.chazwarp.invchest.lib.Reference;
import com.chazwarp.invchest.tileentity.TileEntityAdminChest;
import com.chazwarp.invchest.tileentity.TileEntityInventoryChest;
import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;

import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;

public class PacketHandler implements IPacketHandler{

	@Override
	public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player player) {
		ByteArrayDataInput reader = ByteStreams.newDataInput(packet.data);
		
		EntityPlayer entityPlayer = (EntityPlayer)player;
		
		byte packetId = reader.readByte();
		
		switch(packetId) {
			case 0:
				byte buttonId = reader.readByte();
				Container container = entityPlayer.openContainer;
				if(container != null && container instanceof ContainerInventoryChest) {
					TileEntityInventoryChest inventoryChest = ((ContainerInventoryChest)container).getChest();
					inventoryChest.recieveButtonEvent(buttonId, entityPlayer);
				break;
				}
			case 1:
				String playername = reader.readUTF();
				Container container1 = entityPlayer.openContainer;
				if(container1 != null && container1 instanceof ContainerAdminChest) {
					TileEntityAdminChest adminChest = ((ContainerAdminChest)container1).getChest();
					adminChest.setPlayerName(playername);
				break;
				}
		}
    }

	public static void sendButtonPacket(byte id) {
		ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
		DataOutputStream dataStream = new DataOutputStream(byteStream);
		
		try {
			dataStream.writeByte((byte)0);

			dataStream.writeByte(id);
			
			PacketDispatcher.sendPacketToServer(PacketDispatcher.getPacket(Reference.CHANNEL, byteStream.toByteArray()));
		}catch(IOException ex) {
			System.err.append("Failed to Send Button Click Packet!");
		}
	}

	public static void sendPlayerNamePacket(String text) {
		ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
		DataOutputStream dataStream = new DataOutputStream(byteStream);
		
		try {
			dataStream.writeByte((byte)1);

			dataStream.writeUTF(text);
			
			PacketDispatcher.sendPacketToServer(PacketDispatcher.getPacket(Reference.CHANNEL, byteStream.toByteArray()));
		}catch(IOException ex) {
			System.err.append("Failed to Send Player Name Packet!");
		}
	}	
}
