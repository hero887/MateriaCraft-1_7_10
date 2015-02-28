package net.uberverse.materiacraft;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
import net.uberverse.materiacraft.blocks.MCBlocks;
import net.uberverse.materiacraft.item.MCItems;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = ModInfo.modid, name = ModInfo.name, version = ModInfo.version)
public class MateriaCraft {
	
	@Instance("materiacraft")
	public static MateriaCraft instance;

	public static final Logger logger = LogManager.getLogger(ModInfo.name);
	
	public static final CreativeTabMateriaCraft tab = new CreativeTabMateriaCraft();

	public static final Item.ToolMaterial steelToolMaterial = EnumHelper.addToolMaterial("steelToolMaterial", 3, 1000, 7.0F, 2.0F, 8);

	/**TO-DO: Make Mod.init classes for items, blocks, entities, structures etc.
	 * see here for reference: 
	 * https://github.com/TheXFactor117/Ascension/blob/master/forge/src/main/java/com/thexfactor117/ascension/Ascension.java
	 */

	@EventHandler
	public void preInit (FMLPreInitializationEvent event){
		//Items, Blocks, Int, registering, & config handling
		logger.info("Starting Pre Init.");
		
		MCItems.init();
		MCBlocks.init();
		
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event){
		//Proxy, TileEntity, Entity, GUI, & Packet Registering
		logger.info("Starting Init.");
	}
	
	@EventHandler
	public void PostInit(FMLPostInitializationEvent event){
		
	}
}
