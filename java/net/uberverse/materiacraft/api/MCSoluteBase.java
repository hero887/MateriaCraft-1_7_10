package net.uberverse.materiacraft.api;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.uberverse.materiacraft.ModInfo;
import net.uberverse.materiacraft.blocks.BlockMakoCrystal;
import net.uberverse.materiacraft.blocks.MCBlocks;

public abstract class MCSoluteBase extends Item implements ISolute {
	private static IIcon overlay;

	public void registerIcons(IIconRegister icon) {
		this.itemIcon = icon.registerIcon(ModInfo.modid + ":seed");
	}

	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int meta, float hitX, float hitY, float hitZ) {
		ItemStack seeds = stack.copy();
		seeds.stackSize = 1;
		if (world.getBlock(x, y, z) == MCBlocks.makoBlock) {
			if (hitY == 1.0F) {
				world.setBlock(x, y + 1, z, MCBlocks.crystalCrop);
				((BlockMakoCrystal) world.getBlock(x, y + 1, z)).setData(stack, world, x, y, z);
				player.inventory.decrStackSize(player.inventory.currentItem, 1);
				return true;
			}
		}
		return false;
	}

}


