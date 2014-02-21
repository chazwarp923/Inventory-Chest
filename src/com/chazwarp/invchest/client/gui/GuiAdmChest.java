/**
@author Chaz Kerby
*/
package com.chazwarp.invchest.client.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.tileentity.TileEntityCommandBlock;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import com.chazwarp.invchest.tileentity.TileEntityAdmChest;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiAdmChest extends GuiContainer{

	private GuiTextField playerNameField;
	private final TileEntityAdmChest admChest;
	
	public GuiAdmChest(InventoryPlayer invPlayer, TileEntityAdmChest admchest) {
		super(new ContainerAdmChest(invPlayer, admchest));
		
		this.admChest = admchest;
		
		xSize = 176;
		ySize = 254;
	}
	
	public void updateScreen()
    {
        this.playerNameField.updateCursorCounter();
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
		this.buttonList.clear();
		Keyboard.enableRepeatEvents(true);
		
		this.playerNameField.setMaxStringLength(32767);
        this.playerNameField.setFocused(true);
        this.playerNameField.setText(this.admChest.getplayerName());
	}
	
	@Override
	public void onGuiClosed() {
        Keyboard.enableRepeatEvents(false);
    }
	
	@Override
	protected void keyTyped(char par1, int par2) {
        this.playerNameField.textboxKeyTyped(par1, par2);
    }

	@Override
	protected void mouseClicked(int par1, int par2, int par3) {
        super.mouseClicked(par1, par2, par3);
        this.playerNameField.mouseClicked(par1, par2, par3);
    }
	
	@Override
	public void drawScreen(int par1, int par2, float par3)
    {
        this.drawDefaultBackground();
        this.drawCenteredString(this.fontRenderer, I18n.getString("advMode.setCommand"), this.width / 2, 20, 16777215);
        this.drawString(this.fontRenderer, I18n.getString("advMode.command"), this.width / 2 - 150, 47, 10526880);
        this.drawString(this.fontRenderer, I18n.getString("advMode.nearestPlayer"), this.width / 2 - 150, 97, 10526880);
        this.drawString(this.fontRenderer, I18n.getString("advMode.randomPlayer"), this.width / 2 - 150, 108, 10526880);
        this.drawString(this.fontRenderer, I18n.getString("advMode.allPlayers"), this.width / 2 - 150, 119, 10526880);
        this.playerNameField.drawTextBox();
        super.drawScreen(par1, par2, par3);
    }
}
