package com.chazwarp.invchest.client.interfaces;

import org.lwjgl.opengl.GL11;

import com.chazwarp.invchest.tileentity.TileEntityInvChest;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
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

}
