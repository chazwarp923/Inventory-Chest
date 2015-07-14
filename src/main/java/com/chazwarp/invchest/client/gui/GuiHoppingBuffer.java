/**
@author Chaz Kerby
 */
package com.chazwarp.invchest.client.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import com.chazwarp.invchest.tileentity.TileEntityHoppingBuffer;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiHoppingBuffer extends GuiContainer {

	public GuiHoppingBuffer(InventoryPlayer invPlayer,
			TileEntityHoppingBuffer te) {
		super(new ContainerHoppingBuffer(invPlayer, te));

		xSize = 175;
		ySize = 140;
	}

	private static final ResourceLocation texture = new ResourceLocation(
			"invchest", "textures/gui/hoppingBuffer.png");

	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int x, int y) {
		GL11.glColor4f(1, 1, 1, 1);

		this.mc.renderEngine.bindTexture(texture);
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int x, int y) {
		fontRenderer.drawString("Inventory", 8, 49, 0x404040);
		fontRenderer.drawString("Hopping Buffer", 8, 3, 0x404040);
	}

	@Override
	public void initGui() {
		super.initGui();
	}
}