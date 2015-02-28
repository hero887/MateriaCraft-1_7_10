package net.uberverse.materiacraft.api;

import java.util.ArrayList;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.uberverse.materiacraft.ModInfo;
import net.uberverse.materiacraft.blocks.MCBlocks;
import net.uberverse.materiacraft.tileEntity.TileEntityMakoCrystal;

	public abstract class MCCrystalBase extends Block {
		
		protected int maxGrowthStage = 7;
		
		@SideOnly(Side.CLIENT)
	    protected IIcon[] iIcon;

		public MCCrystalBase() {
			super(Material.plants);
			float f = 0.5F;
			this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 0.25F, 0.5F + f);
			this.setStepSound(soundTypeGravel);
			this.disableStats();
			setHardness(3.0F);
	        setResistance(5.0F);
	        setHarvestLevel("pickaxe", 2); //check what level of pickaxe "2" is. Desired pickaxe level = stone
	        setLightLevel(0.5F);
		}

		/**
		public boolean growCrop(World world, int x, int y, int z, Random rand, boolean night) {
			if (world.getBlockLightValue(x, y + 1, z) >= 9 || night) {
				int l = world.getBlockMetadata(x, y, z);
				if (l < 7) {

					// if (rand.nextInt((int) (25.0F / f) + 1) == 0) {
					++l;
					world.setBlockMetadataWithNotify(x, y, z, l, 3);
					return true;
					// }
				}
			}
			return false;
		}
		*/
		   
		    public void incrementGrowStage(World parWorld, Random parRand, int parX, int parY, int parZ)
		    {
		        int growStage = parWorld.getBlockMetadata(parX, parY, parZ) + 
		              MathHelper.getRandomIntegerInRange(parRand, 2, 5);

		        if (growStage > maxGrowthStage)
		        {
		         growStage = maxGrowthStage;
		        }

		        parWorld.setBlockMetadataWithNotify(parX, parY, parZ, growStage, 2);
		    }
		    
		    @Override
		    public Item getItemDropped(int p_149650_1_, Random parRand, int parFortune)
		    {
		        return Item.getItemFromBlock(this);
		    }

		/**
		 * The type of render function that is called for this block
		 */
		public int getRenderType() {
			return 1;
		}
		
		   @Override
		    @SideOnly(Side.CLIENT)
		    public IIcon getIcon(int parSide, int parGrowthStage)
		    {
		     return iIcon[parGrowthStage];
		    }


		/**
		 * Checks to see if its valid to put this block at the specified
		 * coordinates. Args: world, x, y, z
		 */
		@Override
		public boolean canPlaceBlockAt(World world, int x, int y, int z) {
			return world.getBlock(x, y, z).getMaterial() == Material.rock;
		}
		// checks if finished growing
		public boolean func_149851_a(World parWorld, int parX, int parY, int parZ, boolean p_149851_5_) {
			return parWorld.getBlockMetadata(parX, parY, parZ) != 7;
		}

		public boolean func_149852_a(World p_149852_1_, Random p_149852_2_, int p_149852_3_, int p_149852_4_, int p_149852_5_) {
			return false;
		}

		/**
		 * Can this block stay at this position. Similar to canPlaceBlockAt except
		 * gets checked often with plants.
		 */
		public boolean canBlockStay(World world, int x, int y, int z) {
			return world.getBlock(x, y - 1, z).getMaterial() == Material.rock;
		}

		public void dropBlockAsItemWithChance(World p_149690_1_, int p_149690_2_, int p_149690_3_, int p_149690_4_, int p_149690_5_, float p_149690_6_, int p_149690_7_) {

		}
		
		/**
		 * I think this gives a cactus affect to players who touch the crystals.
		 
		public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {
			TileEntityMakoCrystal crop = (TileEntityMakoCrystal) world.getTileEntity(x, y, z);
				if (!world.isRemote && world.getWorldTime() % 5 == 0)
					((EntityPlayer) entity).attackEntityFrom(new DamageSourceMakoCrystal(), world.getBlockMetadata(x, y, z));
			}
			*/

		public void onNeighborBlockChange(World world, int x, int y, int z, Block block) {
			super.onNeighborBlockChange(world, x, y, z, block);
			if (!canBlockStay(world, x, y, z)) {
				onBlockHarvested(world, x, y, z, world.getBlockMetadata(x, y, z), null);
				world.setBlock(x, y, z, Blocks.air);
			}
		}
		
	    /**
	     * checks if the block can stay, if not drop as item
	     */
	    protected void checkAndDropBlock(World p_149855_1_, int p_149855_2_, int p_149855_3_, int p_149855_4_)
	    {
	        if (!this.canBlockStay(p_149855_1_, p_149855_2_, p_149855_3_, p_149855_4_))
	        {
	            this.dropBlockAsItem(p_149855_1_, p_149855_2_, p_149855_3_, p_149855_4_, p_149855_1_.getBlockMetadata(p_149855_2_, p_149855_3_, p_149855_4_), 0);
	            p_149855_1_.setBlock(p_149855_2_, p_149855_3_, p_149855_4_, getBlockById(0), 0, 2);
	        }
	    }

		
		public void setData(ItemStack seed, IBlockAccess world, int x, int y, int z) {
			TileEntityMakoCrystal tile = (TileEntityMakoCrystal) world.getTileEntity(x, y + 1, z);
			if (tile != null) {
				tile.init(seed.getItemDamage());
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
		public void dropCropDrops(World world, int x, int y, int z, int fortune, boolean seed) {
			TileEntityMakoCrystal crop = (TileEntityMakoCrystal) world.getTileEntity(x, y, z);
			if (world.getBlockMetadata(x, y, z) >= 7) {
				doDrop(crop, world, x, y, z, 0, seed);
			}
		}
		*/
		
		public boolean isOpaqueCube() {
			return false;
		}

		public boolean renderAsNormalBlock() {
			return false;
		}
		
		public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
			return null;
		}

	    /**
	     * Ticks the block if it's been scheduled
	     */
		@Override
		public void updateTick(World parWorld, int parX, int parY, int parZ, Random parRand)
		{
		    super.updateTick(parWorld, parX, parY, parZ, parRand);
		    int growStage = parWorld.getBlockMetadata(parX, parY, parZ) + 1;

		    if (growStage > 7)
		    {
		        growStage = 7;
		    }

		    parWorld.setBlockMetadataWithNotify(parX, parY, parZ, growStage, 2);
		}

		@Override
		public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
			return null;
		}

		public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
			return new TileEntityMakoCrystal();
		}

}
