package com.anzanama.modularity;

import com.anzanama.modularity.common.part.StructurePart;
import com.anzanama.modularity.proxy.CommonProxy;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.common.MinecraftForge;
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

    @Mod.Instance
    public Modularity instance = new Modularity();

    @SidedProxy(clientSide = "com.anzanama.modularity.proxy.ClientProxy",
            serverSide = "com.anzanama.modularity.proxy.ServerProxy")
    private CommonProxy proxy = new CommonProxy();

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent e) {
        log = e.getModLog();
        proxy.preInit(e);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent e) {
        proxy.init(e);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent e) {
        proxy.postInit(e);
    }
}