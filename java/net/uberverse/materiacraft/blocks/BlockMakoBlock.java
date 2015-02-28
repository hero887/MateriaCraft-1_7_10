package net.uberverse.materiacraft.blocks;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.uberverse.materiacraft.tileEntity.TileEntityMakoBlock;

public class BlockMakoBlock extends Block { //implements ITileEntityProvider {

	protected BlockMakoBlock() {
		super(Material.rock);
		this.setHardness(1.0F);
	}

	public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
		ArrayList<ItemStack> stack = new ArrayList<ItemStack>();
		stack.add(new ItemStack(this));
		return stack;
	}

	/**
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int meta, float hitX, float hitY, float hitZ) {
		TileEntityMakoBlock tile = (TileEntityMakoBlock) world.getTileEntity(x, y, z);
		if (player.inventory.getCurrentItem() != null && player.inventory.getCurrentItem().getItem() instanceof Upgrade) {
			if (tile.addUpgrade(player.inventory.getCurrentItem())) {
				player.inventory.decrStackSize(player.inventory.currentItem, 1);
			}

		}

		if (player.isSneaking() && player.inventory.getCurrentItem() == null) {
			ItemStack stack = tile.removeUpgrade();
			if (stack != null) {
				dropBlockAsItem(world, x, y, z, stack);
			}
		}

		return false;
	}
*/
	
}


