package com.uberverse.materiacraft.blocks;

import java.util.Random;

import net.minecraft.block.BlockBush;
import net.minecraft.block.IGrowable;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import com.uberverse.materiacraft.MateriaCraft;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * QUESTION: Should I not extend BlockBush? If not, what code form Block Bush should
 *  I copy and paste into here to get some of the characteristics I want for crystal growth but not
 *  too plant like. For example, I don't want the crystal to require water near by to grow. 
 */
public class MCCrystalGrowth extends BlockBush //Note: I cut this out for now: implements IGrowable
{

	protected int maxGrowthStage = 7;
	
	 @SideOnly(Side.CLIENT)
	    protected IIcon[] iIcon;

	 /**
	  * QUESTION: How can I code the CrystalGrowth class to require a stone pickaxe to "harvest" the crystal?
	  * Basically I want the crystal to once fully matured to become like a solid block that needs to be mined
	  * with a drop of the itemCrystal I want it to be (as specified in the blockNameOfTheCrystalHere class)
	  */
	    public MCCrystalGrowth()
	    {
	     // Basic block setup
	        setTickRandomly(true);
	        float f = 0.5F;
	        setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 0.25F, 0.5F + f);
	        setHardness(3.0F);
	        setResistance(5.0F);
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
	        return 1; // blocks are 0, "crossed squares" is 1, torch is 2
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
	
