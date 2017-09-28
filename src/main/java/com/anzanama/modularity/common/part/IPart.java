package com.anzanama.modularity.common.part;

import net.minecraft.item.ItemStack;

public interface IPart {
    void updatePart(TileEntityBase tile);
    boolean isValidPlacement(EnumPlacement placement);
    ItemStack getDropStack();
    EnumPlacement getPlacement();
    void writeToBytes(byte[] bytes, int startIndex);
}
