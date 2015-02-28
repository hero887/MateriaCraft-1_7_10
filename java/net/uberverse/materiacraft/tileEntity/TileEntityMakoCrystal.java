package net.uberverse.materiacraft.tileEntity;

import com.jcraft.jogg.Packet;

import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.uberverse.materiacraft.api.MCCrystalBase;

public class TileEntityMakoCrystal extends TileEntity {
	private int idx = 0;
	private int ticksgrown = 0;
	private boolean harvested = false;

	public boolean isHarvested() {
		return harvested;
	}

	public void setHarvested(boolean harvested) {
		this.harvested = harvested;
	}

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public int getTicksgrown() {
		return ticksgrown;
	}

	public void setTicksgrown(int ticksgrown) {
		this.ticksgrown = ticksgrown;

	}

	public boolean growPlant(World world, boolean night) {
		if (world != null)
			if (world.getBlock(xCoord, yCoord, zCoord) instanceof MCCrystalBase) {
				return ((MCCrystalBase) world.getBlock(xCoord, yCoord, zCoord)).growCrop(world, xCoord, yCoord, zCoord, world.rand, night);
			}
		return false;
	}

	@Override
	public boolean canUpdate() {
		return true;
	}

	public int getIndex() {
		return idx;
	}

	public void init(int itemDamage) {
		this.idx = itemDamage;
	}

	@Override
	public void writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);
		tag.setInteger("index", idx);
	}

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		this.idx = tag.getInteger("index");
	}

	@Override
	public boolean shouldRefresh(Block oldBlock, Block newBlock, int oldMeta, int newMeta, World world, int x, int y, int z) {
		return oldBlock != newBlock;
	}

	@Override
	public S35PacketUpdateTileEntity getDescriptionPacket() {
		NBTTagCompound nbt = new NBTTagCompound();
		this.writeToNBT(nbt);
		return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 0, nbt);
	}

	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
		this.readFromNBT(pkt.func_148857_g());
	}
}
