package com.anzanama.modularity.common.part;

import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;

public interface IPartBase {
    public static None None = new None();

    HashMap<EnumPlacement, IPart> getParts();
    boolean addPart(IPart part, EnumPlacement placement);
    ItemStack removePart(EnumPlacement placement);

    public class None implements IPartBase {

        @Override
        public HashMap<EnumPlacement, IPart> getParts() {
            return null;
        }

        @Override
        public boolean addPart(IPart part, EnumPlacement placement) {
            return false;
        }

        @Override
        public ItemStack removePart(EnumPlacement placement) {
            return null;
        }
    }

}