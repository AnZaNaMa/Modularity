package com.anzanama.modularity.common.part;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileEntityBase extends TileEntity {

    @Override
    public NBTTagCompound getUpdateTag() {
        return writeToPacketNBT(new NBTTagCompound());
    }

    @Override
    public SPacketUpdateTileEntity getUpdatePacket() {
        NBTTagCompound nbtTag = new NBTTagCompound();
        this.writeToPacketNBT(nbtTag);
        return new SPacketUpdateTileEntity(getPos(), 1, nbtTag);
    }

    @Override
    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity packet) {
        this.readFromPacket(packet);
    }

    protected void readFromPacket(SPacketUpdateTileEntity packet) {
        readFromNBT(packet.getNbtCompound());
    }

    protected NBTTagCompound writeToPacketNBT(NBTTagCompound compound) {
        return writeToNBT(compound);
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        //PUT STUFF HERE
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        //PUT STUFF HERE

        return compound;
    }
}
