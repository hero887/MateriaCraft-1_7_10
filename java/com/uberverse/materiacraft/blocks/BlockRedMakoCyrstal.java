package com.uberverse.materiacraft.blocks;

import java.util.ArrayList;
import java.util.Random;

import com.uberverse.materiacraft.MateriaCraft;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockRedMakoCyrstal extends MCCrystalGrowth {

    public BlockRedMakoCyrstal(Material rock) {
		super (Material.rock);
	}

	/**
     * Returns the quantity of items to drop on block destruction.
     */
    @Override
    public int quantityDropped(int parMetadata, int parFortune, Random parRand)
    {
        return (parMetadata/1);
    }

    @Override
    public Item getItemDropped(int parMetadata, Random parRand, int parFortune)  
    {
     // DEBUG
     System.out.println("blockRedMakoCrystal getItemDropped()");
        return (MateriaCraft.itemRedMakoCrystal);
    }
    
    /**
     * Some Vanilla code found on item drops. Not sure if I should use it or not yet. Experimenting.
     * @return
     
    protected Item func_149866_i()
    {
        return Items.wheat_seeds;
    }
    
    @Override
    public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune)
    {
        ArrayList<ItemStack> ret = super.getDrops(world, x, y, z, metadata, fortune);

        if (metadata >= 7)
        {
            for (int i = 0; i < 3 + fortune; ++i)
            {
                if (world.rand.nextInt(15) <= metadata)
                {
                    ret.add(new ItemStack(this.func_149866_i(), 1, 0));
                }
            }
        }

        return ret;
    }
    */
    
    //TO-DO: Make the textures stages
    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister parIIconRegister)
    {
          iIcon = new IIcon[maxGrowthStage+1];
          // seems that crops like to have 8 growth icons, but okay to repeat actual texture
          // to make generic should loop to maxGrowthStage
          iIcon[0] = parIIconRegister.registerIcon("MateriaCraft:redMakoCrystal_stage_0");
          iIcon[1] = parIIconRegister.registerIcon("MateriaCraft:redMakoCrystal_stage_0");
          iIcon[2] = parIIconRegister.registerIcon("MateriaCraft:redMakoCrystal_stage_1");
          iIcon[3] = parIIconRegister.registerIcon("MateriaCraft:redMakoCrystal_stage_1");
          iIcon[4] = parIIconRegister.registerIcon("MateriaCraft:redMakoCrystal_stage_2");
          iIcon[5] = parIIconRegister.registerIcon("MateriaCraft:redMakoCrystal_stage_2");
          iIcon[6] = parIIconRegister.registerIcon("MateriaCraft:redMakoCrystal_stage_3");
          iIcon[7] = parIIconRegister.registerIcon("MateriaCraft:redMakoCrystal_stage_3");
    }
}

