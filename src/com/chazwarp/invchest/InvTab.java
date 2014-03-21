/**
@author Chaz Kerby
*/
package com.chazwarp.invchest;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

import com.chazwarp.invchest.blocks.Blocks;

import cpw.mods.fml.common.registry.LanguageRegistry;

public class InvTab extends CreativeTabs
{
    public static InvTab tab = new InvTab();
    static boolean hasInit;
    static int icon;

    public InvTab()
    {
        super("invTab");
    }

    public static void init (int index)
    {
        if (!hasInit)
        {
            hasInit = true;
            icon = index;
        }
    }

    public int getTabIconItemIndex ()
    {
        return icon;
        
    	}
    @Override
    public ItemStack getIconItemStack() {
    	return new ItemStack(Blocks.inventoryChest, 1, 0);
    }
}
