package com.anzanama.modularity.common.block;

import com.anzanama.modularity.Modularity;
import com.anzanama.modularity.Registry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Class Description Here
 *
 * @author Andrew Graber
 * @version 9/28/2017
 */
public class BlockBase extends Block {
    protected String name;

    public BlockBase(String name, Material material) {
        super(material);
        this.name = name;
        setUnlocalizedName(Modularity.MODID + "." + name);
        setRegistryName(name);
        setCreativeTab(Modularity.tab);
    }

    @SideOnly(Side.CLIENT)
    public void registerItemModel(Item itemBlock) {
        Registry.registerItemRenderer(itemBlock, 0, name);
    }

    public Item createItemBlock() {
        return new ItemBlock(this);
    }

    @Override
    public BlockBase setCreativeTab(CreativeTabs tab) {
        super.setCreativeTab(tab);
        return this;
    }
}
