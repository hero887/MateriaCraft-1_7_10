package net.uberverse.materiacraft.tileEntity;

import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.uberverse.materiacraft.api.MCCrystalBase;
import net.uberverse.materiacraft.blocks.BlockMakoCrystal;

public class TileEntityMakoBlock {

	public ItemStack[] items;;
	public TileEntityMakoBlock() {
		super();
		items = new ItemStack[3];
	}

	public boolean canUpdate() {
		return false;
	}

	public boolean growPlant(World world, int xCoord, int y, int z, boolean night) {
		if (world != null)
			if (world.getBlock(xCoord, y + 1, z) instanceof MCCrystalBase) {
				TileEntityMakoCrystal crystal = (TileEntityMakoCrystal) world.getTileEntity(xCoord, y + 1, z);
				return ((MCCrystalBase) world.getBlock(xCoord, y + 1, z)).growCrop(world, xCoord, y + 1, z, world.rand, night);
			}
		return false;
	}

	public BlockMakoCrystal getCrop(World world, int x, int y, int z) {
		return world.getBlock(x, y + 1, z) != null && world.getBlock(x, y + 1, z) instanceof BlockMakoCrystal ? (BlockMakoCrystal) world.getBlock(x, y + 1, z) : null;
	}

	public TileEntityMakoCrystal getCropTile(World world, int x, int y, int z) {
		return world.getTileEntity(x, y + 1, z) != null && world.getTileEntity(x, y + 1, z) instanceof TileEntityMakoCrystal ? (TileEntityMakoCrystal) world.getTileEntity(x, y + 1, z) : null;
	}

}
