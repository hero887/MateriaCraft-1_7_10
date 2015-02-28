package net.uberverse.materiacraft.item;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.uberverse.materiacraft.MateriaCraft;
import net.uberverse.materiacraft.ModInfo;

public class MCItems {
	
	public static Item itemZackSword;
	public static Item makoPowder = new ItemMakoPowder();
	public static Item itemRedMakoSolute;
	public static Item itemRedMakoCrystal;

	public static void init() {
		registerItems();
		registerNBT();
		registerRecipes();

	}

	private static void registerRecipes() {
	}

	private static void registerItems() {
		//seed.setCreativeTab(MateriaCraft.tab);
		//universalSeed.setCreativeTab(MateriaCraft.tab).setTextureName(ModInfo.modid + ":seed").setUnlocalizedName("universalSeed");

		//registerItem(scytheDiamond, "Diamond Scythe", "scytheDiamond");
		
		//GameRegistry.registerItem(universalSeed, "universalSeed");
		//registerItem(gemCutter, "Gem Cutter", "gemCutter");

		// registerArmor(armorHelm, "shardHelm", "shardHelm", "Helm");
		// registerArmor(armorChest, "shardChest", "shardChest", "Chest");
		// registerArmor(armorLegs, "shardLegs", "shardLegs", "Legs");
		// registerArmor(armorBoots, "shardBoots", "shardBoots", "Boots");
		//
	}

	private static void registerNBT() {
	}
/**
	private static void registerArmor(Item item, String name, String key, String type) {
		item.setCreativeTab(MateriaCraft.tab).setTextureName(ModInfo.modid + ":shard" + type).setUnlocalizedName(key);
		GameRegistry.registerItem(item, key);
	}
*/
	private static void registerItem(Item item, String name, String key) {
		item.setCreativeTab(MateriaCraft.tab).setTextureName(ModInfo.modid + ":" + key).setUnlocalizedName(key);
		GameRegistry.registerItem(item, key);
	}

}
