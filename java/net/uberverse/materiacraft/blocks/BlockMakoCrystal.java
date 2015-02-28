package net.uberverse.materiacraft.blocks;

import java.util.ArrayList;
import java.util.Random;

import com.sun.xml.internal.stream.Entity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.uberverse.materiacraft.MateriaCraft;
import net.uberverse.materiacraft.ModInfo;
import net.uberverse.materiacraft.api.MCCrystalBase;
import net.uberverse.materiacraft.item.MCItems;
import net.uberverse.materiacraft.tileEntity.TileEntityMakoCrystal;
import net.uberverse.materiacraft.utils.DamageSourceMakoCrystal;

public class BlockMakoCrystal extends MCCrystalBase { //implements ITileEntityProvider
	public BlockMakoCrystal() {
		
		  // Basic block setup
        setBlockName("Block Mako Crystal");
        setBlockTextureName("materiacraft:makoCrystal_stage_0");
    }

    /**
     * Returns the quantity of items to drop on block destruction.
     */
    @Override
    public int quantityDropped(int parMetadata, int parFortune, Random parRand)
    {
        return (parMetadata/2);
    }

    @Override
    public Item getItemDropped(int parMetadata, Random parRand, int parFortune)  
    {
     // DEBUG
     System.out.println("BlockMakoCrystal getItemDropped()");
        return (MCItems.makoPowder);
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister parIIconRegister)
    {
          iIcon = new IIcon[maxGrowthStage+1];
          // seems that crops like to have 8 growth icons, but okay to repeat actual texture if you want
          // to make generic should loop to maxGrowthStage
          iIcon[0] = parIIconRegister.registerIcon(ModInfo.modid + ":redMakoCrystal_stage_0");
          iIcon[1] = parIIconRegister.registerIcon(ModInfo.modid + ":redMakoCrystal_stage_0");
          iIcon[2] = parIIconRegister.registerIcon(ModInfo.modid + ":redMakoCrystal_stage_1");
          iIcon[3] = parIIconRegister.registerIcon(ModInfo.modid + ":redMakoCrystal_stage_1");
          iIcon[4] = parIIconRegister.registerIcon(ModInfo.modid + ":redMakoCrystal_stage_2");
          iIcon[5] = parIIconRegister.registerIcon(ModInfo.modid + ":redMakoCrystal_stage_2");
          iIcon[6] = parIIconRegister.registerIcon(ModInfo.modid + ":redMakoCrystal_stage_3");
          iIcon[7] = parIIconRegister.registerIcon(ModInfo.modid + ":redMakoCrystal_stage_3");
    }
}