package com.anzanama.modularity.common.part;

import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;

import java.security.InvalidParameterException;
import java.util.HashMap;

public class TilePartStructure extends TileEntityBase implements IPartBase, ITickable {
    private HashMap<EnumPlacement, IPart> parts;
    private HashMap<EnumFacing, IPartBase> adjacentParts;
    private byte clock;

    public TilePartStructure(HashMap<EnumPlacement, IPart> parts) {
        super();
        this.parts = parts;
        findAdjacentParts();
        clock = 0;
    }

    public TilePartStructure() {
        this(new HashMap<>());
    }

    @Override
    public void update() {
        for(EnumPlacement placement : parts.keySet()) {
            parts.get(placement).updatePart(this);
        }
        if(clock%100==0) {
            findAdjacentParts();
        }
        clock++;
    }

    @Override
    public HashMap<EnumPlacement, IPart> getParts() {
        return parts;
    }

    @Override
    public boolean addPart(IPart part, EnumPlacement placement) {
        if(part.isValidPlacement(placement) && !parts.containsKey(placement)) {
            parts.put(placement, part);
            return true;
        }
        return false;
    }

    @Override
    public ItemStack removePart(EnumPlacement placement) {
        if(parts.containsKey(placement)) {
            return (parts.get(placement).getDropStack());
        }
        return ItemStack.EMPTY;
    }

    protected void findAdjacentParts() {
        adjacentParts = new HashMap<>(6);
        TileEntity te = null;
        adjacentParts.put(EnumFacing.EAST, (te = world.getTileEntity(pos.add(1, 0, 0))) instanceof IPartBase
                ? (IPartBase)te : IPartBase.None);
        adjacentParts.put(EnumFacing.WEST, (te = world.getTileEntity(pos.add(-1, 0, 0))) instanceof IPartBase
                ? (IPartBase)te : IPartBase.None);
        adjacentParts.put(EnumFacing.NORTH, (te = world.getTileEntity(pos.add(0, 0, 1))) instanceof IPartBase
                ? (IPartBase)te : IPartBase.None);
        adjacentParts.put(EnumFacing.SOUTH, (te = world.getTileEntity(pos.add(0, 0, -1))) instanceof IPartBase
                ? (IPartBase)te : IPartBase.None);
        adjacentParts.put(EnumFacing.UP, (te = world.getTileEntity(pos.add(0, 1, 0))) instanceof IPartBase
                ? (IPartBase)te : IPartBase.None);
        adjacentParts.put(EnumFacing.DOWN, (te = world.getTileEntity(pos.add(0, -1, 0))) instanceof IPartBase
                ? (IPartBase)te : IPartBase.None);
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        if(compound.hasKey("parts")) {
            parts = partsFromBytes(compound.getByteArray("parts"));
        } else {
            parts = new HashMap<>();
        }
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        compound.setByteArray("parts", partsToBytes());
        return compound;
    }

    private byte[] partsToBytes() {
        byte[] bytes = new byte[parts.size()*8];
        for(int i=0; i<parts.size(); i++) {
            parts.get(i).writeToBytes(bytes, i*8);
        }
        return bytes;
    }

    private HashMap<EnumPlacement, IPart> partsFromBytes(byte[] bytes) {
        HashMap<EnumPlacement, IPart> newParts = new HashMap<>();
        if(bytes.length%8 != 0) {
            throw new InvalidParameterException("Corrupted Byte Array Received!");
        }
        for(int i=0; i<(bytes.length/8); i++) {
            IPart part = PartFactory.genFromBytes(bytes, i*8);
            newParts.put(part.getPlacement(), part);
        }
        return newParts;
    }
}
