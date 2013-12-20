package com.chazwarp.invchest.client.interfaces;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import com.chazwarp.invchest.tileentity.TileEntityAdmChest;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiAdmChest extends GuiContainer{

	public GuiAdmChest(InventoryPlayer invPlayer, TileEntityAdmChest admchest) {
		super(new ContainerAdmChest(invPlayer, admchest));
		
		xSize = 176;
		ySize = 254;
	}

	private static final ResourceLocation texture = new ResourceLocation("invchest", "textures/gui/admchest.png");
	
	
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
		if(button.id == 0) {
			System.out.println("Not Yet Implemented :P");
			
		}
	}
}
