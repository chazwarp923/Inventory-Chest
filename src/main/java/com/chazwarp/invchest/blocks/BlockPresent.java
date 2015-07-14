/**
@author Chaz Kerby
 */
package com.chazwarp.invchest.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.chazwarp.invchest.lib.BlockInfo;
import com.chazwarp.invchest.tileentity.TileEntityPresent;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockPresent extends BlockIC {

	protected BlockPresent() {
		super(Material.wood, 1.5F, Block.soundTypeWood,
				BlockInfo.PRESENT_UNLOCALIZED_NAME, BlockIC.AXE, BlockIC.STONE);

		setBlockBounds(0.0625F, 0.0F, 0.0625F, 0.9375F, 0.875F, 0.9375F);
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public int getRenderType() {
		return -1;
	}

	public TileEntity createNewTileEntity(World par1World) {
		return new TileEntityPresent();
	}

	@Override
	@SideOnly(Side.SERVER)
	public boolean onBlockActivated(World world, int x, int y, int z,
			EntityPlayer player, int side, float hitX, float hitY, float hitZ) {

		int randomInt, minimum = 0, maximum = 9;
		randomInt = minimum + (int) (Math.random() * maximum);

		EntityItem droppedItem = new EntityItem(world, x + 0.5, y + 0.5,
				z + 0.5, GiftArray[randomInt]);
		world.spawnEntityInWorld(droppedItem);

		world.setBlockToAir(x, y, z);
		return true;
	}

	ItemStack[] GiftArray;
	{
		GiftArray = new ItemStack[10];

		GiftArray[0] = new ItemStack(Items.golden_apple, 1, 0);
		GiftArray[1] = new ItemStack(Items.experience_bottle, 2, 0);
		GiftArray[2] = new ItemStack(Items.cooked_beef, 5, 0);
		GiftArray[3] = new ItemStack(Items.blaze_rod, 2, 0);
		GiftArray[4] = new ItemStack(Items.bucket, 3, 0);
		GiftArray[5] = new ItemStack(Items.cake, 1, 0);
		GiftArray[6] = new ItemStack(Items.coal, 2, 0);
		GiftArray[7] = new ItemStack(Items.diamond, 1, 0);
		GiftArray[8] = new ItemStack(Items.emerald, 1, 0);
		GiftArray[9] = new ItemStack(Items.ender_pearl, 1, 0);
	}
}