/**
@author Chaz Kerby
*/
package com.chazwarp.invchest.client.gui;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import com.chazwarp.invchest.network.PacketHandler;
import com.chazwarp.invchest.tileentity.TileEntityAdminChest;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiAdminChest extends GuiContainer{

	private GuiTextField playerNameField;
	private final TileEntityAdminChest adminChest;
	private GuiButton doneBtn;
	
	public GuiAdminChest(InventoryPlayer invPlayer, TileEntityAdminChest adminChest) {
		super(new ContainerAdminChest(invPlayer, adminChest));
		
		this.adminChest = adminChest;
		
		xSize = 176;
		ySize = 254;
	}
	
	public void updateScreen()
    {
        this.playerNameField.updateCursorCounter();
    }

	private static final ResourceLocation texture = new ResourceLocation("invchest", "textures/gui/adminChest.png");
	
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int x, int y) {
		GL11.glColor4f(1, 1, 1, 1);
		
		this.mc.renderEngine.bindTexture(texture);
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int x, int y) {
		fontRenderer.drawString("Inventory", 8, 160, 0x404040);
		fontRenderer.drawString("Player Name", 83, 8, 0x404040);
		this.playerNameField.drawTextBox();
	}
	
	@Override
	public void initGui() {
		super.initGui();
		Keyboard.enableRepeatEvents(true);
		
		this.buttonList.clear();
		buttonList.add(this.doneBtn = new GuiButton(1, guiLeft + 83, guiTop + 39, 32, 20, "Done"));
		
		//GuiTextField(FontRenderer, int xPos, int yPos, int width, int height)
		this.playerNameField = new GuiTextField(this.fontRenderer, 83, 20, 85, 15);
		this.playerNameField.setMaxStringLength(32);
        this.playerNameField.setFocused(true);
        this.playerNameField.setText(this.adminChest.getPlayerName());//Need to request from server
        this.doneBtn.enabled = this.playerNameField.getText().trim().length() > 0;
	}
	
	@Override
	protected void actionPerformed(GuiButton button) {
		if (button.enabled) {
			PacketHandler.sendPlayerNamePacket((String)playerNameField.getText());
        }
	}
	
	@Override
	public void onGuiClosed() {
        Keyboard.enableRepeatEvents(false);
    }
	
	@Override
	protected void keyTyped(char par1, int par2) {
        this.playerNameField.textboxKeyTyped(par1, par2);
        this.doneBtn.enabled = this.playerNameField.getText().trim().length() > 0;
        
        if (par2 != 28 && par2 != 156)
        {
        	if (par2 == 1)
        	{
        		this.mc.thePlayer.closeScreen();
        	}
        }
    }

	@Override
	protected void mouseClicked(int par1, int par2, int par3) {
        super.mouseClicked(par1, par2, par3);
        this.playerNameField.mouseClicked(par1, par2, par3);
    }
	
}
