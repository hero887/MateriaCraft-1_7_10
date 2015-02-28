package net.uberverse.materiacraft.blocks;

import com.sun.xml.internal.stream.Entity;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.uberverse.materiacraft.api.MCCrystalBase;
import net.uberverse.materiacraft.tileEntity.TileEntityMakoCrystal;
import net.uberverse.materiacraft.utils.DamageSourceMakoCrystal;

public class BlockMakoCrystal extends MCCrystalBase { //implements ITileEntityProvider
	public BlockMakoCrystal() {
		setTickRandomly(true);
	}

	/**
	 * I think this gives a cactus affect to players who touch the crystals.
	 
	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {
		TileEntityMakoCrystal crop = (TileEntityMakoCrystal) world.getTileEntity(x, y, z);
			if (!world.isRemote && world.getWorldTime() % 5 == 0)
				((EntityPlayer) entity).attackEntityFrom(new DamageSourceMakoCrystal(), world.getBlockMetadata(x, y, z));
		}
		*/

	public boolean canBlockStay(World world, int x, int y, int z) {
		return world.getBlock(x, y - 1, z) == MCBlocks.makoBlock;
	}

	public void onNeighborBlockChange(World world, int x, int y, int z, Block block) {
		super.onNeighborBlockChange(world, x, y, z, block);
		if (!canBlockStay(world, x, y, z)) {
			onBlockHarvested(world, x, y, z, world.getBlockMetadata(x, y, z), null);
			world.setBlock(x, y, z, Blocks.air);
		}
	}

	// @Override
	// public Item getItem(World world, int x, int y, int z) {
	// ItemStack returnStack = new ItemStack(FCItems.seed,
	// RecipeRegistry.getSeedReturn(((TileEntityCrystal) world.getTileEntity(x,
	// y, z)).getIndex()), ((TileEntityCrystal) world.getTileEntity(x, y,
	// z)).getIndex());
	// Item returnItem = returnStack.getItem();
	// return returnItem;
	// }

	/**
	 * Need to study this code and remove what I don't want.
	 * 
	public void dropCropDrops(World world, int x, int y, int z, int fortune, boolean seed) {
		TileEntityMakoCrystal crop = (TileEntityMakoCrystal) world.getTileEntity(x, y, z);
		if (world.getBlockMetadata(x, y, z) >= 7) {
			doDrop(crop, world, x, y, z, 0, seed);
		}
	}

	public boolean isOpaqueCube() {
		return false;
	}

	public boolean renderAsNormalBlock() {
		return false;
	}
	
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
		return null;
	}

	private void doDrop(TileEntityCrystal crop, World world, int x, int y, int z, int itemMultiplier, boolean seed) {

		if (seed)
			dropBlockAsItem(world, x, y, z, new ItemStack(FCItems.seed, RecipeRegistry.getSeedReturn(crop.getIndex()), crop.getIndex()));
		if (ConfigProps.normalShardRecipes) {
			dropBlockAsItem(world, x, y, z, new ItemStack(FCItems.shard, RecipeRegistry.getDropAmount(crop.getIndex()) + itemMultiplier, crop.getIndex()));
		} else if (RecipeRegistry.getDrop(crop.getIndex()) != null) {
			ItemStack drop = RecipeRegistry.getDrop(crop.getIndex()).copy();
			drop.stackSize = RecipeRegistry.getDropAmount(crop.getIndex()) + itemMultiplier;
			dropBlockAsItem(world, x, y, z, drop);

		} else {
			dropBlockAsItem(world, x, y, z, new ItemStack(FCItems.roughChunk, RecipeRegistry.getDropAmount(crop.getIndex()) + itemMultiplier, crop.getIndex()));
		}
		try {
			if (RecipeRegistry.getEntityID(crop.getIndex()) > 0) {
				int id = RecipeRegistry.getEntityID(crop.getIndex());

				if (id == 48 || id == 49 || id == 1 || id == 8 || id == 9 || id == 21) {

				} else if (id == 22) {
					TTEntityUtils.spawnFirework(new BlockCoord(crop), world.provider.dimensionId);
				} else {
					Entity ent = EntityList.createEntityByID(id, world);
					ent.setLocationAndAngles(x + .5, y + .5, z + .5, 0, 0);
					world.spawnEntityInWorld(ent);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (RecipeRegistry.getWeightedDrop(crop.getIndex()) != null) {
			if (RecipeRegistry.getWeightedDropChance(crop.getIndex()) == world.rand.nextInt(9) + 1) {
				dropBlockAsItem(world, x, y, z, RecipeRegistry.getWeightedDrop(crop.getIndex()));
			}
		}
	}

	@Override
	public void breakBlock(World world, int x, int y, int z, Block block, int meta) {
		TileEntityCrystal crop = (TileEntityCrystal) world.getTileEntity(x, y, z);
		if (world.getBlockMetadata(x, y, z) >= 7)
			if (RecipeRegistry.getWeightedDrop(crop.getIndex()) != null) {
				if (RecipeRegistry.getWeightedDropChance(crop.getIndex()) == world.rand.nextInt(9) + 1) {
					dropBlockAsItem(world, x, y, z, RecipeRegistry.getWeightedDrop(crop.getIndex()));

				}
			}

		if (!crop.isHarvested()) {
			dropCropDrops(world, x, y, z, 0, true);
		}
		world.setBlockToAir(x, y, z);
		super.breakBlock(world, x, y, z, block, meta);
	}

	@Override
	public void onBlockHarvested(World world, int x, int y, int z, int meta, EntityPlayer player) {
		TileEntityCrystal crop = (TileEntityCrystal) world.getTileEntity(x, y, z);
		dropBlockAsItem(world, x, y, z, new ItemStack(FCItems.seed, RecipeRegistry.getSeedReturn(crop.getIndex()), crop.getIndex()));
		if (world.getBlockMetadata(x, y, z) >= 7) {
			if (player != null && player.getCurrentEquippedItem() != null) {
				ItemStack stack = player.getCurrentEquippedItem();
				if (stack != null && stack.getItem() instanceof ItemScythe) {
					if (stack.isItemEqual(new ItemStack(FCItems.scytheWood))) {
						if (world.rand.nextInt(4) == 0) {
							dropCropDrops(world, x, y, z, RecipeRegistry.getDropAmount(crop.getIndex()), true);
						} else {
							dropCropDrops(world, x, y, z, 0, true);
						}
					}
					if (stack.isItemEqual(new ItemStack(FCItems.scytheStone))) {
						if (world.rand.nextInt(3) == 0) {
							dropCropDrops(world, x, y, z, RecipeRegistry.getDropAmount(crop.getIndex()), true);
						} else {
							dropCropDrops(world, x, y, z, 0, true);
						}
					}
					if (stack.isItemEqual(new ItemStack(FCItems.scytheIron))) {
						if (world.rand.nextInt(2) == 0) {
							dropCropDrops(world, x, y, z, RecipeRegistry.getDropAmount(crop.getIndex()), true);
						} else {
							dropCropDrops(world, x, y, z, 0, true);
						}
					}
					if (stack.isItemEqual(new ItemStack(FCItems.scytheGold))) {
						if (world.rand.nextInt(1) == 0) {
							dropCropDrops(world, x, y, z, RecipeRegistry.getDropAmount(crop.getIndex()), true);
						} else {
							dropCropDrops(world, x, y, z, 0, true);
						}
					}
					if (stack.isItemEqual(new ItemStack(FCItems.scytheDiamond))) {
						dropCropDrops(world, x, y, z, RecipeRegistry.getDropAmount(crop.getIndex()), true);
					}

					crop.setHarvested(true);
				} else {
					dropCropDrops(world, x, y, z, 0, true);
					crop.setHarvested(true);
				}
			}
		}
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int metadata, float hitX, float hitY, float hitZ) {
		TileEntityCrystal crop = (TileEntityCrystal) world.getTileEntity(x, y, z);
		if (world.getBlockMetadata(x, y, z) >= 7) {
			ItemStack stack = player.getCurrentEquippedItem();
			if (stack != null && stack.getItem() instanceof ItemScythe) {
				if (stack.isItemEqual(new ItemStack(FCItems.scytheWood))) {
					if (world.rand.nextInt(4) == 0) {
						dropCropDrops(world, x, y, z, RecipeRegistry.getDropAmount(crop.getIndex()), false);
					}
				}
				if (stack.isItemEqual(new ItemStack(FCItems.scytheStone))) {
					if (world.rand.nextInt(3) == 0) {
						dropCropDrops(world, x, y, z, RecipeRegistry.getDropAmount(crop.getIndex()), false);
					}
				}
				if (stack.isItemEqual(new ItemStack(FCItems.scytheIron))) {
					if (world.rand.nextInt(2) == 0) {
						dropCropDrops(world, x, y, z, RecipeRegistry.getDropAmount(crop.getIndex()), false);
					}
				}
				if (stack.isItemEqual(new ItemStack(FCItems.scytheGold))) {
					if (world.rand.nextInt(1) == 0) {
						dropCropDrops(world, x, y, z, RecipeRegistry.getDropAmount(crop.getIndex()), false);
					}
				}
				if (stack.isItemEqual(new ItemStack(FCItems.scytheDiamond))) {
					dropCropDrops(world, x, y, z, RecipeRegistry.getDropAmount(crop.getIndex()), false);
				}
			} else {
				dropCropDrops(world, x, y, z, 0, false);
			}
			world.setBlockMetadataWithNotify(x, y, z, 0, 3);
			return true;
		}
		return false;
	}

	public void setData(ItemStack seed, IBlockAccess world, int x, int y, int z) {
		TileEntityCrystal tile = (TileEntityCrystal) world.getTileEntity(x, y + 1, z);
		if (tile != null) {
			tile.init(seed.getItemDamage());
		}
	}

	@Override
	public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
		return null;
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TileEntityCrystal();
	}

	@Override
	public void getWailaInfo(List<String> tooltip, int x, int y, int z, World world) {
		TileEntity te = world.getTileEntity(x, y, z);
		if (te != null && te instanceof IWailaInfo) {
			((IWailaInfo) te).getWailaInfo(tooltip, x, y, z, world);
		}
	}

	@Override
	public ItemStack getWailaStack(IWailaDataAccessor accessor, IWailaConfigHandler config) {
		TileEntity te = accessor.getTileEntity();
		return ((IWailaInfo) te).getWailaStack(accessor, config);
	}

	public Item getItem(World world, int x, int y, int z) {
		TileEntityCrystal crop = (TileEntityCrystal) world.getTileEntity(x, y, z);

		return new ItemStack(FCItems.seed, RecipeRegistry.getSeedReturn(crop.getIndex()), crop.getIndex()).getItem();
	}
}{
*/
}
