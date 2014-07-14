/**
@author Chaz Kerby
*/
package com.chazwarp.invchest.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import net.minecraftforge.common.network.ForgeNetworkHandler;

import com.chazwarp.invchest.InvTab;
import com.chazwarp.invchest.InventoryChest;
import com.chazwarp.invchest.lib.BlockInfo;
import com.chazwarp.invchest.lib.Reference;
import com.chazwarp.invchest.lib.Textures;
import com.chazwarp.invchest.tileentity.TileEntityAdminChest;

import cpw.mods.fml.common.network.FMLNetworkHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockAdminChest extends BlockIC {
	
	public BlockAdminChest() {
		super(Material.wood, 1.5F, Block.soundTypeWood, BlockInfo.ADMIN_CHEST_UNLOCALIZED_NAME, "axe", 1);	
	}
	
	@SideOnly(Side.CLIENT)
	private Icon topIcon;
	@SideOnly(Side.CLIENT)
	private Icon botIcon;
	@SideOnly(Side.CLIENT)
	private Icon sideIcon;
	
    @SideOnly(Side.CLIENT)
    @Override
    public void registerIcons(IconRegister register) {
		topIcon = register.registerIcon(Reference.TEXTURE_LOC + ":" + Textures.ADMIN_CHEST_TOP);
		sideIcon = register.registerIcon(Reference.TEXTURE_LOC + ":" + Textures.ADMIN_CHEST_FRONT);
		botIcon = register.registerIcon(Reference.TEXTURE_LOC + ":" + Textures.ADMIN_CHEST_TOP);
	}
	
    @SideOnly(Side.CLIENT)
    @Override
    public Icon getIcon(int side, int meta) {
    	if (side == 0) {
    		return botIcon;
    	}
    	else if (side == 1) {
    		return topIcon;
    	}
    	else{
    		return sideIcon;
    	}
    }
    
    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		if(!world.isRemote) {
			ForgeNetworkHandler.openGui(player, InventoryChest.instance, 1, world, x, y, z);
		}
		return true;
	} 
    
	@Override
	public TileEntity createNewTileEntity(World world) {
        return new TileEntityAdminChest();
	}
}