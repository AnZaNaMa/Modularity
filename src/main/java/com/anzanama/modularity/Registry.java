package com.anzanama.modularity;

import com.anzanama.modularity.common.block.BlockBase;
import com.anzanama.modularity.common.item.ItemBase;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Class Description Here
 *
 * @author Andrew Graber
 * @version 9/28/2017
 */
@Mod.EventBusSubscriber
public class Registry {

    public static BlockBase part_structure = new BlockBase("part_structure", Material.ROCK).setCreativeTab(CreativeTabs.MATERIALS);

    public static ItemBase wrench = new ItemBase("wrench").setCreativeTab(CreativeTabs.MATERIALS);

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        event.getRegistry().registerAll(
                part_structure
        );
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        event.getRegistry().registerAll(
                wrench
        );
    }

    @SubscribeEvent
    public static void registerItemModels(ModelRegistryEvent event) {
        wrench.registerItemModel();
    }

    @SideOnly(Side.CLIENT)
    public static void registerItemRenderer(Item item, int meta, String id) {
        ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(Modularity.MODID +
                ":" + id, "inventory"));
    }
}
