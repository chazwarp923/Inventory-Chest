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

import com.chazwarp.invchest.client.gui.ContainerInvChest;
import com.chazwarp.invchest.lib.Reference;
import com.chazwarp.invchest.tileentity.TileEntityInvChest;
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
				if(container != null && container instanceof ContainerInvChest) {
					TileEntityInvChest invChest = ((ContainerInvChest)container).getChest();
					invChest.recieveButtonEvent(buttonId, entityPlayer);
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
			System.err.append("Failed to Send Button Packet!");
		}
	}
	
}
