package com.anzanama.modularity.common.item;

import com.anzanama.modularity.Registry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

/**
 * Class Description Here
 *
 * @author Andrew Graber
 * @version 9/28/2017
 */
public class ItemBase extends Item {

    public ItemBase(String name) {
        setRegistryName(name);
        setUnlocalizedName(name);
    }

}
