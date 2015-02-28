package net.uberverse.materiacraft;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

public class CreativeTabMateriaCraft extends CreativeTabs {
	
	public CreativeTabMateriaCraft() {
		super("MateriaCraft");
	}

	@Override
	public Item getTabIconItem() {
		return Items.apple;
	}

}
