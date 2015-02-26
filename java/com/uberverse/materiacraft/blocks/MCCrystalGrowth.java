package com.uberverse.materiacraft.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.IGrowable;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;

import com.uberverse.materiacraft.MateriaCraft;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * QUESTION: Should I not extend BlockBush? If not, what code form Block Bush should
 *  I copy and paste into here to get some of the characteristics I want for crystal growth but not
 *  too plant like. For example, I don't want the crystal to require water near by to grow. 
 */
public class MCCrystalGrowth extends Block //implements IGrowable implements IPlantable
{

	protected int maxGrowthStage = 7;
	
	 @SideOnly(Side.CLIENT)
	    protected IIcon[] iIcon;

	 /**
	  * QUESTION: How can I code the CrystalGrowth class to require a stone pickaxe to "harvest" the crystal?
	  * Basically I want the crystal to once fully matured to become like a solid block that needs to be mined
	  * with a drop of the itemCrystal I want it to be (as specified in the blockNameOfTheCrystalHere class)
	  */
	    public MCCrystalGrowth(Material rock)
	    {
	     // Basic block setup
	    	super(Material.rock);
	        setTickRandomly(true);
	        float f = 0.5F;
	        setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 0.25F, 0.5F + f);
	        setHardness(3.0F);
	        setResistance(5.0F);
	        setHarvestLevel("pickaxe", 2); //check what level of pickaxe "2" is. Desired pickaxe level = stone
	        setLightLevel(0.5F);
	        setStepSound(soundTypeGravel);
	        disableStats();
	        
	    }
	    
		/**
	     * QUESTION: Will this code here work for the concept of when breaking the fully matured crystal
	     * it will make a breaking glass noise?
	     */
	    public String getBreakSound()
        {
            return "dig.glass";
        }
	    
	    //Amount of light emitted 
	    protected int lightValue;
	   //Flag if block should use the brightest neighbor light value as its own
	    protected boolean useNeighborBrightness;
	    //Indicates how many hits it takes to break a block.
	    protected float blockHardness;
	    //Indicates the block's resistance to explosions.
	    protected float blockResistance;

	    /**
	     * is the block grass, dirt or farmland
	     */
	    protected boolean canPlaceBlockOn(Material parMaterial)
	    {
	        return parMaterial == Material.rock;
	    }
	    
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
	    
	    /**QUESTION: How do I render the crystal to look like Redstone dust at first stage & then grow up 
	     * as a crop after that w/the "crossed squares? Or must I choose one or the other?
	     */
	    @Override
	    public int getRenderType()
	    {
	        return 1; // blocks are 0, "crossed squares" is 1, torch is 2, crops is 6
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
	    
	    /**
	     * Checks to see if its valid to put this block at the specified coordinates. Args: world, x, y, z
	     */
	    public boolean canPlaceBlockAt(World world, int x, int y, int z)
	    {
	        return super.canPlaceBlockAt(world, x, y, z) && this.canBlockStay(world, x, y, z);
	    }
	    
	    /**
	     * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are
	     * their own) Args: x, y, z, neighbor Block
	     */
	    public void onNeighborBlockChange(World world, int x, int y, int z, Block block)
	    {
	        super.onNeighborBlockChange(world, x, y, z, block);
	        this.checkAndDropBlock(world, x, y, z);
	        
	    }
	        
	     /**
	      * checks if the block can stay, if not drop as item
	      */
	    protected void checkAndDropBlock(World world, int x, int y, int z)
	     {
	        if (!this.canBlockStay(world, x, y, z))
	         {
	             this.dropBlockAsItem(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
	             world.setBlock(x, y, z, getBlockById(0), 0, 2);
	         }
	     }
	    
	    /**
	     * Can this block stay at this position.  Similar to canPlaceBlockAt except gets checked often with plants.
	    
	    public boolean canBlockStay(World p_149718_1_, int p_149718_2_, int p_149718_3_, int p_149718_4_)
	    {
	        return  p_149718_1_.getBlock(p_149718_2_, p_149718_3_ - 1, p_149718_4_).canSustainPlant(p_149718_1_, p_149718_2_, p_149718_3_ - 1, p_149718_4_, ForgeDirection.UP, this);
	    }
	     */
	    
	    /**
	     * Is this block (a) opaque and (b) a full 1m cube?  This determines whether or not to render the shared face of two
	     * adjacent blocks and also whether the player can attach torches, redstone wire, etc to this block.
	     */
	    public boolean isOpaqueCube()
	    {
	        return false;
	    }

	    /**
	     * If this block doesn't render as an ordinary block it will return False (examples: signs, buttons, stairs, etc)
	     */
	    public boolean renderAsNormalBlock()
	    {
	        return false;
	    }
	    
	    /**
	     * QUESTION: Should I use this Vanilla code rather? I have iIcon registering customized
	     * inside the uniquely individual Block[ColorHere]MakoCrystal class so I was assuming this is 
	     * duplicated code that is unnecessary.
	    @SideOnly(Side.CLIENT)
	    public void registerBlockIcons(IIconRegister register)
	    {
	        this.iIcon = new IIcon[8];

	        for (int i = 0; i < this.iIcon.length; ++i)
	        {
	            this.iIcon[i] = register.registerIcon(this.getTextureName() + "_stage_" + i);
	        }
	    }
	    */
	    
	    /**
	     *QUESTION: All the following code has been copied from the BlockCrops vanilla code. Not sure if I need it. Do you?
	     * Ticks the block if it's been scheduled
	    
	    public void updateTick(World p_149674_1_, int p_149674_2_, int p_149674_3_, int p_149674_4_, Random p_149674_5_)
	    {
	        super.updateTick(p_149674_1_, p_149674_2_, p_149674_3_, p_149674_4_, p_149674_5_);

	        if (p_149674_1_.getBlockLightValue(p_149674_2_, p_149674_3_ + 1, p_149674_4_) >= 9)
	        {
	            int l = p_149674_1_.getBlockMetadata(p_149674_2_, p_149674_3_, p_149674_4_);

	            if (l < 7)
	            {
	                float f = this.func_149864_n(p_149674_1_, p_149674_2_, p_149674_3_, p_149674_4_);

	                if (p_149674_5_.nextInt((int)(25.0F / f) + 1) == 0)
	                {
	                    ++l;
	                    p_149674_1_.setBlockMetadataWithNotify(p_149674_2_, p_149674_3_, p_149674_4_, l, 2);
	                }
	            }
	        }
	    }

	    public void func_149863_m(World p_149863_1_, int p_149863_2_, int p_149863_3_, int p_149863_4_)
	    {
	        int l = p_149863_1_.getBlockMetadata(p_149863_2_, p_149863_3_, p_149863_4_) + MathHelper.getRandomIntegerInRange(p_149863_1_.rand, 2, 5);

	        if (l > 7)
	        {
	            l = 7;
	        }

	        p_149863_1_.setBlockMetadataWithNotify(p_149863_2_, p_149863_3_, p_149863_4_, l, 2);
	    }

	    private float func_149864_n(World p_149864_1_, int p_149864_2_, int p_149864_3_, int p_149864_4_)
	    {
	        float f = 1.0F;
	        Block block = p_149864_1_.getBlock(p_149864_2_, p_149864_3_, p_149864_4_ - 1);
	        Block block1 = p_149864_1_.getBlock(p_149864_2_, p_149864_3_, p_149864_4_ + 1);
	        Block block2 = p_149864_1_.getBlock(p_149864_2_ - 1, p_149864_3_, p_149864_4_);
	        Block block3 = p_149864_1_.getBlock(p_149864_2_ + 1, p_149864_3_, p_149864_4_);
	        Block block4 = p_149864_1_.getBlock(p_149864_2_ - 1, p_149864_3_, p_149864_4_ - 1);
	        Block block5 = p_149864_1_.getBlock(p_149864_2_ + 1, p_149864_3_, p_149864_4_ - 1);
	        Block block6 = p_149864_1_.getBlock(p_149864_2_ + 1, p_149864_3_, p_149864_4_ + 1);
	        Block block7 = p_149864_1_.getBlock(p_149864_2_ - 1, p_149864_3_, p_149864_4_ + 1);
	        boolean flag = block2 == this || block3 == this;
	        boolean flag1 = block == this || block1 == this;
	        boolean flag2 = block4 == this || block5 == this || block6 == this || block7 == this;

	        for (int l = p_149864_2_ - 1; l <= p_149864_2_ + 1; ++l)
	        {
	            for (int i1 = p_149864_4_ - 1; i1 <= p_149864_4_ + 1; ++i1)
	            {
	                float f1 = 0.0F;

	                if (p_149864_1_.getBlock(l, p_149864_3_ - 1, i1).canSustainPlant(p_149864_1_, l, p_149864_3_ - 1, i1, ForgeDirection.UP, this))
	                {
	                    f1 = 1.0F;

	                    if (p_149864_1_.getBlock(l, p_149864_3_ - 1, i1).isFertile(p_149864_1_, l, p_149864_3_ - 1, i1))
	                    {
	                        f1 = 3.0F;
	                    }
	                }

	                if (l != p_149864_2_ || i1 != p_149864_4_)
	                {
	                    f1 /= 4.0F;
	                }

	                f += f1;
	            }
	        }

	        if (flag2 || flag && flag1)
	        {
	            f /= 2.0F;
	        }

	        return f;
	    }

	    //Gets the block's texture. Args: side, meta
	     
	    @SideOnly(Side.CLIENT)
	    public IIcon getIcon(int p_149691_1_, int p_149691_2_)
	    {
	        if (p_149691_2_ < 0 || p_149691_2_ > 7)
	        {
	            p_149691_2_ = 7;
	        }

	        return this.field_149867_a[p_149691_2_];
	    }
	    */

	    /**
		@Override
		public EnumPlantType getPlantType(IBlockAccess world, int x, int y,
				int z) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Block getPlant(IBlockAccess world, int x, int y, int z) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public int getPlantMetadata(IBlockAccess world, int x, int y, int z) {
			// TODO Auto-generated method stub
			return 0;
		}
		*/
	    
	    /**
	     * Unsure if I need to use this code yet.
	     *     @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister p_149651_1_)
    {
        this.field_149867_a = new IIcon[8];

        for (int i = 0; i < this.field_149867_a.length; ++i)
        {
            this.field_149867_a[i] = p_149651_1_.registerIcon(this.getTextureName() + "_stage_" + i);
        }
    }
	     */
	    
	  /**func_149851_a returns true if bonemeal is allowed, false otherwise.
	@Override
	public boolean func_149851_a(World p_149851_1_, int p_149851_2_, 
			int p_149851_3_, int p_149851_4_, boolean p_149851_5_) {
		//TODO Auto-generated method stub
		return false;
	}

	//func_149852_a returns true at the same time bonemeal is used if conditions for a growth-tick are acceptable.
	@Override
	public boolean func_149852_a(World p_149852_1_, Random p_149852_2_,
			int p_149852_3_, int p_149852_4_, int p_149852_5_) {
		//TODO Auto-generated method stub
		return false;
	}

	 //func_149853_b processes the actual growth-tick logic, which is usually increasing metadata or replacing the block.
	@Override
	public void func_149853_b(World p_149853_1_, Random p_149853_2_,
			int p_149853_3_, int p_149853_4_, int p_149853_5_) {
		//TODO Auto-generated method stub
		*/
	}
	
