package com.anzanama.modularity;

import com.anzanama.modularity.proxy.CommonProxy;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;


@Mod(modid = Modularity.MODID, name = Modularity.MODNAME, version = Modularity.VERSION)
public class Modularity {
    public static final String MODNAME = "Modularity";
    public static final String MODID = "modularity";
    public static final String VERSION = "1.0";
    public static Logger log;

    @SidedProxy(clientSide = "com.anzanama.modularity.proxy.ClientProxy",
            serverSide = "com.anzanama.modularity.proxy.ServerProxy")
    private static CommonProxy proxy = new CommonProxy();

    @Mod.Instance
    public static Modularity instance;

    public static CreativeTabs tab = new CreativeTabs(MODID) {
        @Override
        public ItemStack getTabIconItem() {
            return new ItemStack(Registry.part_structure);
        }
    };

    @Mod.EventHandler
    public static void preInit(FMLPreInitializationEvent e) {
        log = e.getModLog();
        proxy.preInit(e);
    }

    @Mod.EventHandler
    public static void init(FMLInitializationEvent e) {
        proxy.init(e);
    }

    @Mod.EventHandler
    public static void postInit(FMLPostInitializationEvent e) {
        proxy.postInit(e);
    }
}