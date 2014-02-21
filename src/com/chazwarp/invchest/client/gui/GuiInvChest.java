/**
@author Chaz Kerby
*/
package com.chazwarp.invchest.client.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import com.chazwarp.invchest.network.PacketHandler;
import com.chazwarp.invchest.tileentity.TileEntityInvChest;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiInvChest extends GuiContainer{

	public GuiInvChest(InventoryPlayer invPlayer, TileEntityInvChest invchest) {
		super(new ContainerInvChest(invPlayer, invchest));
		
		xSize = 198;
		ySize = 249;
	}

	private static final ResourceLocation texture = new ResourceLocation("invchest", "textures/gui/invchest.png");
	
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int x, int y) {
		GL11.glColor4f(1, 1, 1, 1);
		
		this.mc.renderEngine.bindTexture(texture);
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
	}
	@Override
	protected void drawGuiContainerForegroundLayer(int x, int y) {
		fontRenderer.drawString("Inventory", 8, 160, 0x404040);
	}
	@Override
	public void initGui() {
		super.initGui();
		buttonList.clear();
		
		buttonList.add(new GuiButton(0, guiLeft + 83, guiTop + 7, 32, 20, "Swap"));
	}
	@Override
	protected void actionPerformed(GuiButton button) {
		PacketHandler.sendButtonPacket((byte)button.id);
	}
}
