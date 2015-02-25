package com.uberverse.materiacraft.blocks;

import java.util.Random;

import com.uberverse.materiacraft.MateriaCraft;

import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.IGrowable;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class MCCrystalGrowth extends Block implements IGrowable
{

	public MCCrystalGrowth(Material plants) {
		super(Material.plants);
		
	}
	public static final Block.SoundType soundTypeGravel = new Block.SoundType("gravel", 1.0F, 1.0F);
	  
	public String getBreakSound()
       {
           return "dig.glass";
       }
	
	 public EnumPlantType getPlantType(IBlockAccess world, int x, int y, int z)
	    {
	        return EnumPlantType.Cave;
	    }

	    public Block getPlant(IBlockAccess world, int x, int y, int z)
	    {
	        return this;
	    }

	    public int getPlantMetadata(IBlockAccess world, int x, int y, int z)
	    {
	        return world.getBlockMetadata(x, y, z);
	    }
	@SideOnly(Side.CLIENT)
	private IIcon [] iconArray;
	
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons (IIconRegister iconRegister){
		this.iconArray = new IIcon[8];
		
		for (int i = 0; i < this.iconArray.length; i++) {
			this.iconArray[i] = iconRegister.registerIcon(MateriaCraft.MODID + ":" + this.getUnlocalizedName().substring(5) + (i + 1));
		}
	}
	
	public IIcon getIcon(int side, int metadata) {
		if (metadata < 7){
			if (metadata == 6) {
				metadata = 5;
			}
			
			return this.iconArray[metadata >> 1];
		}
		
		return this.iconArray[3];
	}
	
	public int quantityDropped (Random random) {
		return 1;
	}
	
	protected Item func_149866_i() {
		return MateriaCraft.itemMakoPowder;
	}
	
	protected Item func_149865_P() {
		return MateriaCraft.itemMakoCrystal;
	}
	
    protected boolean canPlaceBlockOn(Block p_149854_1_)
    {
        return p_149854_1_ == Blocks.stone;
    }
    
    public void onNeighborBlockChange(World p_149695_1_, int p_149695_2_, int p_149695_3_, int p_149695_4_, Block p_149695_5_) {}

    public void onBlockAdded(World p_149726_1_, int p_149726_2_, int p_149726_3_, int p_149726_4_) {}

	@Override
	public boolean func_149851_a(World p_149851_1_, int p_149851_2_,
			int p_149851_3_, int p_149851_4_, boolean p_149851_5_) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean func_149852_a(World p_149852_1_, Random p_149852_2_,
			int p_149852_3_, int p_149852_4_, int p_149852_5_) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void func_149853_b(World p_149853_1_, Random p_149853_2_,
			int p_149853_3_, int p_149853_4_, int p_149853_5_) {
		// TODO Auto-generated method stub
		
	}

}
