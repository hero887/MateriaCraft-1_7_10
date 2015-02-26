package com.uberverse.materiacraft;

import com.uberverse.materiacraft.blocks.BlockRedMakoCyrstal;
import com.uberverse.materiacraft.blocks.MCCrystalGrowth;
import com.uberverse.materiacraft.item.ItemMakoPowder;
import com.uberverse.materiacraft.item.ItemRedCrystalSolute;
import com.uberverse.materiacraft.item.ItemRedMakoCrystal;
import com.uberverse.materiacraft.item.ItemZackSword;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRedstoneTorch;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSeeds;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.EnumHelper;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = MateriaCraft.MODID, version = MateriaCraft.VERSION)
public class MateriaCraft

{
    public static final String MODID = "Materiacraft";
    public static final String VERSION = "1.0";
    
    //Create New Objects
    
    //Items
	public static Item itemZackSword;
	public static Item itemMakoPowder;
	
	//Crop/Crystal
	/**
	 * public static Item itemMakoSeedCrystal;
	 * public static Block blockMakoCrystal;
	 */
	public static Item itemRedMakoSolute;
	public static Block blockRedMakoCrystal;
	public static Item itemRedMakoCrystal;

	public static final Item.ToolMaterial steelToolMaterial = EnumHelper.addToolMaterial("steelToolMaterial", 3, 1000, 7.0F, 2.0F, 8);


	@EventHandler
	public void preInit (FMLPreInitializationEvent event){
		//Items, Blocks, Int, registering, & config handling
		
		//Initialize New Things
		//Items
		itemZackSword = new ItemZackSword(steelToolMaterial).setUnlocalizedName("ItemZackSword").setTextureName(MODID + ":ZackSword").setCreativeTab(tabMateriaCraft);
		
		itemMakoPowder = new ItemMakoPowder().setUnlocalizedName("MakoPowder").setTextureName(MODID + ":MakoPowder").setCreativeTab(tabMateriaCraft);
		
		//Crops/Crystals
		/**
		 *blockMakoCrystal = new MCCrystalGrowth();
		 *itemMakoSolute = new ItemCrystalSeed(blockMakoCrystal, Material.rock);
		 *EXAMPLE paramaters for block:.setBlockName("blockname").setCreativeTab(tabMateriaCraft).setBlockTextureName(MODID +":blockname"));
		 */
		itemRedMakoSolute = new ItemRedCrystalSolute(0, 0, null, null).setUnlocalizedName("itemRedMakoSolute").setTextureName(MODID + ":redMakoSolute").setCreativeTab(tabMateriaCraft);
		blockRedMakoCrystal = new BlockRedMakoCyrstal().setBlockName("blockRedMakoCrystal").setBlockTextureName(MODID + ":redMakoCrystal_stage_0");
		itemRedMakoCrystal = new ItemRedMakoCrystal().setUnlocalizedName("itemRedMakoCrystal").setTextureName(MODID + ":redMakoCrystal").setCreativeTab(tabMateriaCraft);
		
		//Register
		//Items
		GameRegistry.registerItem(itemMakoPowder, "MakoPowder");
		GameRegistry.registerItem(itemZackSword, itemZackSword.getUnlocalizedName().substring(5));
		
		//Crystals/Crops
		//GameRegistry.registerItem(itemMakoSeedCrystal, "MakoSeedCrystal");
		//GameRegistry.registerBlock(blockMakoCrystal, "GrowingMakoCrystal");
		GameRegistry.registerItem(itemRedMakoCrystal, "RedMakoCrystal");
		
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event){
		//Proxy, TileEntity, Entity, GUI, & Packet Registering
	}
	
	@EventHandler
	public void PostInit(FMLPostInitializationEvent event){
		
	}
	
	public static CreativeTabs tabMateriaCraft = new CreativeTabs("tabMateriaCraft"){
		@Override
		public Item getTabIconItem(){
			return new ItemStack(Items.golden_apple).getItem();
		}
	};
}
