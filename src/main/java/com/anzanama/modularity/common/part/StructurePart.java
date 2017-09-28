package com.anzanama.modularity.common.part;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

import java.util.ArrayList;
import java.util.HashMap;

public class StructurePart extends TileEntityBase implements IPartBase, ITickable {
    private HashMap<EnumPlacement, IPart> parts;
    private HashMap<EnumFacing, IPartBase> adjacentParts;

    public StructurePart(HashMap<EnumPlacement, IPart> parts) {
        super();
        this.parts = parts;
        findAdjacentParts();
    }

    public StructurePart() {
        this(new HashMap<>());
    }

    @Override
    public void update() {
        for(EnumPlacement placement : parts.keySet()) {
            parts.get(placement).updatePart(this);
        }
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

    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        compound.setByteArray("parts", partsToBytes());
        return null;
    }

    @SubscribeEvent
    public void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event) {
        if(!event.player.getEntityWorld().isRemote) {
            event.player.capabilities.allowFlying = true;
            event.player.capabilities.disableDamage = true;
            event.player.sendPlayerAbilities();
        }
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

        }
        for(int i=0; i<(bytes.length/8); i++) {

        }
        return null;
    }
}
