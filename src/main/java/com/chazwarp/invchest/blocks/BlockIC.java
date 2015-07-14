/**
@author Chaz Kerby
 */

package com.chazwarp.invchest.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.chazwarp.invchest.InvTab;
import com.chazwarp.invchest.lib.Reference;

public class BlockIC extends Block implements ITileEntityProvider {

	public static String SWORD = "sword";
	public static String AXE = "axe";
	public static String PICKAXE = "pickaxe";
	public static String SHOVEL = "spade";

	public static int WOOD = 0;
	public static int STONE = 1;
	public static int IRON = 2;
	public static int DIAMOND = 3;

	protected BlockIC(Material mat, float hardness, SoundType sound,
			String unlocalizedName, String harvestTool, int harvestLevel) {
		super(mat);

		setCreativeTab(InvTab.tab);
		setHardness(hardness);
		setStepSound(sound);
		setBlockName(unlocalizedName);
		setBlockTextureName(Reference.RESOURCE_PREFIX + unlocalizedName);
		setHarvestLevel(harvestTool, harvestLevel);
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return null;
	}
}