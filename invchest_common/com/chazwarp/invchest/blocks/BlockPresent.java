package com.chazwarp.invchest.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
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

	private final Random random = new Random();
	
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
        TileEntityPresent tileEntityChristmasChest = new TileEntityPresent();
        return tileEntityChristmasChest;
    }
	
	@Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		player.inventory.addItemStackToInventory(new ItemStack(Item.plateIron));
		return true;
	}
	
	@SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister icon)
    {
        this.blockIcon = icon.registerIcon("invchest:present");
    }

}
