/**
@author Chaz Kerby
*/

package com.chazwarp.invchest.eventhandler;

import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class DeathEventHandler {

	@SubscribeEvent
	public void onDeath(LivingDeathEvent event) {
		Entity ent = event.entityLiving;
		EntityPlayer entPlay = (EntityPlayer)ent;
		double x = ent.posX;
		double y = ent.posY;
		double z = ent.posZ;
		
		if(ent instanceof EntityPlayer) {
			
		ItemStack head = new ItemStack(Items.skull, 1, 3);
		head.setTagCompound(new NBTTagCompound());
		head.stackTagCompound.setString("SkullOwner", entPlay.getUniqueID().toString());
		
		EntityItem headItem = new EntityItem(entPlay.worldObj, x, y, z, head);
		entPlay.worldObj.spawnEntityInWorld(headItem);
		}
	}
}
