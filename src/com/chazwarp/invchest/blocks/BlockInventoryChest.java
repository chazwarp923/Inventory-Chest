package com.chazwarp.invchest.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

import com.chazwarp.invchest.InvTab;
import com.chazwarp.invchest.InventoryChest;
import com.chazwarp.invchest.lib.BlockInfo;
import com.chazwarp.invchest.lib.Reference;
import com.chazwarp.invchest.lib.Textures;
import com.chazwarp.invchest.tileentity.TileEntityInvChest;

import cpw.mods.fml.common.network.FMLNetworkHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockInventoryChest extends BlockContainer {
	
	public BlockInventoryChest(int id) {
		super(id, Material.wood);
		
		setCreativeTab(InvTab.tab);
		setHardness(1.5F);
		setStepSound(Block.soundWoodFootstep);
		
	}
	
	@Override
	public String getUnlocalizedName() {
		return String.format("tile.%s%s", Reference.MOD_ID.toLowerCase() + ":", BlockInfo.INV_CHEST_UNLOCALIZED_NAME);	
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
		topIcon = register.registerIcon(Reference.TEXTURE_LOC + ":" + Textures.CHEST_TOP);
		sideIcon = register.registerIcon(Reference.TEXTURE_LOC + ":" + Textures.INV_CHEST_SIDE);
		botIcon = register.registerIcon(Reference.TEXTURE_LOC + ":" + Textures.CHEST_TOP);
	}
	
    @SideOnly(Side.CLIENT)
    @Override
    public Icon getIcon(int side, int meta) {
    	if (side == 0) {
    		return botIcon;
    	}else if (side == 1) {
    		return topIcon;
    	}else{
    		return sideIcon;
    	}
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		if(!world.isRemote) {
			FMLNetworkHandler.openGui(player, InventoryChest.instance, 0, world, x, y, z);
		}
		return true;
	}
    
    
    
	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEntityInvChest();
	}
	@Override
	public void breakBlock(World world, int x, int y, int z, int id, int meta) {
		TileEntity te = world.getBlockTileEntity(x, y, z);
		if(te != null && te instanceof TileEntityInvChest) {
			TileEntityInvChest invChest = (TileEntityInvChest)te;
			
			for(int i = 0; i < invChest.getSizeInventory(); i++) {
				ItemStack stack = invChest.getStackInSlotOnClosing(i);
				
				if(stack != null) {
					float spawnX = x + world.rand.nextFloat();
					float spawnY = y + world.rand.nextFloat();
					float spawnZ = z + world.rand.nextFloat();
					
					EntityItem droppedItem = new EntityItem(world, spawnX, spawnY, spawnZ, stack);
					
					float mult = 0.05F;
					
					droppedItem.motionX = (-0.5F + world.rand.nextFloat()) * mult;
					droppedItem.motionY = (4 + world.rand.nextFloat()) * mult;
					droppedItem.motionZ = (-0.5F + world.rand.nextFloat()) * mult;
					
					world.spawnEntityInWorld(droppedItem);
				}
			}
		}
		super.breakBlock(world, x, y, z, id, meta);
	}
}
