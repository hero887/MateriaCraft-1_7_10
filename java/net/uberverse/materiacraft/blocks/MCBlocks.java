package net.uberverse.materiacraft.blocks;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.uberverse.materiacraft.MateriaCraft;
import net.uberverse.materiacraft.ModInfo;
import net.uberverse.materiacraft.tileEntity.TileEntityMakoBlock;
import net.uberverse.materiacraft.tileEntity.TileEntityMakoCrystal;

public class MCBlocks {

	public static Block crystalCrop = new BlockMakoCrystal();
	public static Block makoBlock = new BlockMakoBlock();
	public static Block blockRedMakoCrystal;
	
	public static void init() {
		
		regsiterBlocks();
		//registerTileEntity();
		
	}
	/**
	 * Should move this to the Proxies
	 * 
	private static void registerTileEntity() {

		GameRegistry.registerTileEntity(TileEntityMakoBlock.class, "makoBlock");
		GameRegistry.registerTileEntity(TileEntityMakoCrystal.class, "cyrstalCrop");
		
	}
	*/
	
	public static void regsiterBlocks() {
		
	GameRegistry.registerBlock(crystalCrop, "Block Mako Crystal");
	registerBlock(makoBlock, "Mako Block", "mako_block");
	
	}
	
	private static void registerBlock(Block block, String name, String key) {
		block.setBlockName(name).setBlockTextureName(ModInfo.modid + ":" + key).setCreativeTab(MateriaCraft.tab);
		GameRegistry.registerBlock(block, key);
	}
	
	/**
	private static void registerTileEntitys(Class<? extends TileEntity> tile, String name, String modidActive, boolean configActive) {
		if (Loader.isModLoaded(modidActive) && configActive)
			GameRegistry.registerTileEntity(tile, name);
	}
	*/

	private static void registerBlock(Block block, String name, String key, String modidActive, boolean configActive) {
		if (Loader.isModLoaded(modidActive) && configActive) {
			block.setBlockName(name).setBlockTextureName(ModInfo.modid + ":" + key).setCreativeTab(MateriaCraft.tab);
			GameRegistry.registerBlock(block, key);
		}
	}
	
}
