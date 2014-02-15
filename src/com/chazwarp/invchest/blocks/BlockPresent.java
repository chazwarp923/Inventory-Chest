/**
@author Chaz Kerby
*/
package com.chazwarp.invchest.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.chazwarp.invchest.InvTab;
import com.chazwarp.invchest.lib.BlockInfo;
import com.chazwarp.invchest.lib.Reference;
import com.chazwarp.invchest.tileentity.TileEntityPresent;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockPresent extends BlockContainer{
	
	protected BlockPresent(int id) {
		super(id, Material.wood);
		
		this.setCreativeTab(InvTab.tab);
		this.setHardness(1.5F);
		this.setStepSound(Block.soundWoodFootstep);
        this.setBlockBounds(0.0625F, 0.0F, 0.0625F, 0.9375F, 0.875F, 0.9375F);
	}
	
	@Override
	public String getUnlocalizedName() {
		return String.format("tile.%s%s", Reference.MOD_ID.toLowerCase() + ":", BlockInfo.PRESENT_UNLOCALIZED_NAME);	
	}

	public boolean isOpaqueCube()
    {
        return false;
    }
	
	@Override
	public boolean renderAsNormalBlock()
    {
        return false;
    }
	
	@Override
	public int getRenderType() {
		return -1;	
	}
	
	public TileEntity createNewTileEntity(World par1World)
    {
        TileEntityPresent tileEntityPresent = new TileEntityPresent();
        return tileEntityPresent;
    }
	
	@Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		int randomInt, minimum = 0, maximum = 9;
		randomInt = minimum + (int)(Math.random()*maximum); 
		
		//player.inventory.addItemStackToInventory(GiftArray[randomInt]);
		EntityItem droppedItem = new EntityItem(world, x + 0.5, y + 0.5, z + 0.5, GiftArray[randomInt]);
		world.spawnEntityInWorld(droppedItem);

		world.setBlockToAir(x, y, z);
		return true;
	}
	
	@SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister icon)
    {
        this.blockIcon = icon.registerIcon("invchest:present");
    }
	
	ItemStack[] GiftArray; {
	GiftArray = new ItemStack[10];
	
	GiftArray[0] = new ItemStack(Item.appleGold.itemID, 1, 0);
	GiftArray[1] = new ItemStack(Item.expBottle.itemID, 2, 0);
	GiftArray[2] = new ItemStack(Item.beefCooked.itemID, 5, 0);
	GiftArray[3] = new ItemStack(Item.blazeRod.itemID, 2, 0);
	GiftArray[4] = new ItemStack(Item.bucketEmpty.itemID, 3, 0);
	GiftArray[5] = new ItemStack(Item.cake.itemID, 1, 0);
	GiftArray[6] = new ItemStack(Item.coal.itemID, 2, 0);
	GiftArray[7] = new ItemStack(Item.diamond.itemID, 1, 0);
	GiftArray[8] = new ItemStack(Item.emerald.itemID, 1, 0);
	GiftArray[9] = new ItemStack(Item.enderPearl.itemID, 1, 0);
	}
}
