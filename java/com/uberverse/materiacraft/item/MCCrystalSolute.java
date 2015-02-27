package com.uberverse.materiacraft.item;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * I know, I extended ItemFood. That's because I want the various mixtures of CrystalSolute to be edible with
 * status effects. I want to always add poison for maybe 2 mins (or just less enough to not kill the player)
 * because the solute is not good to eat of course. Then I want to customize the status effects in the
 * itemNameOfTheCrystalSolute. For example eat RedCrystalSolute and get poison AND catch on fire (I know
 * catching on fire is not a status affect, but wouldn't that be sweet!?) Although that status effect really
 * is bad, I plan to make other solute mixtures have better status effects. 
 *
 */
public class MCCrystalSolute extends ItemFood //implements IPlantable
{
    private final Block theBlockPlant;
    /**
     * Block ID of the soil this seed food should be planted on.
     */
    private final Block soilId;

    public MCCrystalSolute(int parHealAmount, float parSaturationModifier, boolean wolf,
          Block parBlockPlant, Block parSoilBlock)
    {
        super(parHealAmount, parSaturationModifier, false);
        theBlockPlant = parBlockPlant;
        soilId = parSoilBlock;
    }
    
    /**
    @Override
    protected void onFoodEaten(ItemStack i, World w, EntityPlayer p) {
        if(!w.isRemote) {
            p.addPotionEffect(new PotionEffect(Potion.absorption.id, 2400, 0));

        if(op) {
                p.addPotionEffect(new PotionEffect(Potion.regeneration.id, 600, 4));
                p.addPotionEffect(new PotionEffect(Potion.resistance.id, 6000, 0));
                p.addPotionEffect(new PotionEffect(Potion.fireResistance.id, 6000, 0));
            }
        } else {
            super.onFoodEaten(i, w, p);
        }
    }
    */

    @Override
    public boolean onItemUse(ItemStack parItemStack, EntityPlayer parPlayer, 
          World parWorld, int parX, int parY, int parZ, int par7, float par8, 
          float par9, float par10)
    {
     // not sure what this parameter does, copied it from potato
        if (par7 != 1)
        {
            return false;
        }
		return false;
    }
}
        
        /**
        // check if player has capability to edit
        else if (parPlayer.canPlayerEdit(parX, parY+1, parZ, par7, parItemStack))
        {
            // check that the soil block can sustain the plant
            // and that block above is air so there is room for plant to grow
            if (parWorld.getBlock(parX, parY, parZ).canSustainPlant(parWorld, 
                  parX, parY, parZ, ForgeDirection.UP, this) && parWorld
                  .isAirBlock(parX, parY+1, parZ))
            {
             // place the plant block
                parWorld.setBlock(parX, parY+1, parZ, theBlockPlant);
                // decrement the stack of seed items
                --parItemStack.stackSize;
                return true;
            }
            else
            {
                return false;
            }
        }
        else
        {
            return false;
        }
    }
    @Override
    public EnumPlantType getPlantType(IBlockAccess world, int x, int y, int z)
    {
        return EnumPlantType.Cave;
    }

    @Override
    public Block getPlant(IBlockAccess world, int x, int y, int z)
    {
        return theBlockPlant;
    }

    @Override
    public int getPlantMetadata(IBlockAccess world, int x, int y, int z)
    {
        return 0;
    }

    public Block getSoilId() 
    {
        return soilId;
    }
*/
       
