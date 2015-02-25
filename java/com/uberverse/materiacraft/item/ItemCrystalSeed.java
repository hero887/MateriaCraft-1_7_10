package com.uberverse.materiacraft.item;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;

public class ItemCrystalSeed extends Item implements IPlantable
{
	private Block crystalGrowingMako;
	private Material soilMaterialID;
	private static final String __OBFID = "CL_00000061";

	    public ItemCrystalSeed(Block p_i45352_1_, Material rock)
	    {
	        this.crystalGrowingMako = p_i45352_1_;
	        this.soilMaterialID = Material.rock;
	    }
	    
	    public boolean onItemUse(ItemStack p_77648_1_, EntityPlayer p_77648_2_, World p_77648_3_, int p_77648_4_, int p_77648_5_, int p_77648_6_, int p_77648_7_, float p_77648_8_, float p_77648_9_, float p_77648_10_)
	    {
	        if (p_77648_7_ != 1)
	        {
	            return false;
	        }
			return false;
	    }

	    @Override
	    public EnumPlantType getPlantType(IBlockAccess world, int x, int y, int z)
	    {
	        return crystalGrowingMako == Blocks.nether_wart ? EnumPlantType.Nether : EnumPlantType.Cave;
	    }

	    @Override
	    public Block getPlant(IBlockAccess world, int x, int y, int z)
	    {
	        return crystalGrowingMako;
	    }

	    @Override
	    public int getPlantMetadata(IBlockAccess world, int x, int y, int z)
	    {
	        return 0;
	    }
}
