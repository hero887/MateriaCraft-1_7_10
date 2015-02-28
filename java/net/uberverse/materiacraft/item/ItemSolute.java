package net.uberverse.materiacraft.item;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import net.uberverse.materiacraft.api.MCSoluteBase;

public class ItemSolute extends MCSoluteBase{

	public ItemSolute() {
		setUnlocalizedName("materiacraft.solute");
		setTextureName("materiacraft:solute");
		setHasSubtypes(true);
	}
/**
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4) {
		list.add("-" + RecipeRegistry.getName(stack.getItemDamage()));
		list.add("Growth:" + (RecipeRegistry.getGrowthTime(stack.getItemDamage())) + " Ticks");
		if (RecipeRegistry.getTier(stack.getItemDamage()) > 0)
			list.add("Tier:" + RecipeRegistry.getTier(stack.getItemDamage()));
		if (!RecipeRegistry.getLore(stack.getItemDamage()).equals("null")) {
			String lore = RecipeRegistry.getLore(stack.getItemDamage());
			lore.replaceAll("\t", "    ");
			String[] lores = lore.split("\n");
			for (String lor : lores) {
				list.add(lor);
			}
		}
	}

	public int getRenderPasses(int metadata) {
		return 1;
	}

	@Override
	public int getColorFromItemStack(ItemStack par1ItemStack, int par2) {
		return RecipeRegistry.getColor(par1ItemStack.getItemDamage());
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void getSubItems(Item p_150895_1_, CreativeTabs p_150895_2_, List list) {
		int numSeeds = RecipeRegistry.getNumSeedRecipes();
		for (int i = 0; i < numSeeds; i++) {
			list.add(new ItemStack(this, 1, i));
		}
	}

	@Override
	public String getItemStackDisplayName(ItemStack stack) {
		return String.format(StatCollector.translateToLocal(getUnlocalizedName() + ".name"), RecipeRegistry.getName(stack.getItemDamage()));
	}
}
*/
}
