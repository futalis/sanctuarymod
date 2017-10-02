package com.futalis.sanctuary;


import com.futalis.sanctuary.handlers.ServerEventHandler;
import com.futalis.sanctuary.proxy.CommonProxy;
import com.futalis.sanctuary.tiles.SanctuaryBlockReference;
import com.futalis.sanctuary.tiles.TileSanctuaryBlock;
import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

import static net.minecraftforge.registries.GameData.registerEntity;

@Mod(modid = Sanctuary.MODID, name = Sanctuary.MODNAME, version = Sanctuary.MODVERSION, /*dependencies = "required-after:forge@[13.19.0.2129,)",*/ useMetadata = true)
public class Sanctuary {

    public static final String MODID = "sanctuary";
    public static final String MODNAME = "Sanctuary";
    public static final String MODVERSION = "0.0.1";

    public static final CreativeTabSanctuary creativeTab = new CreativeTabSanctuary();
    public static List<SanctuaryBlockReference> Stage1 = new ArrayList<SanctuaryBlockReference>();

    @SidedProxy(clientSide = "com.futalis.sanctuary.proxy.ClientProxy", serverSide = "com.futalis.sanctuary.proxy.ServerProxy")
    public static CommonProxy proxy;

    @Mod.Instance
    public static Sanctuary instance;

    public static Logger logger;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event){
        logger = event.getModLog();
        proxy.preInit(event);

    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent e) {
        proxy.init(e);
        MinecraftForge.EVENT_BUS.register(new ServerEventHandler());
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent e) {
        proxy.postInit(e);
    }

    @Mod.EventHandler
    public void serverLoad(FMLServerStartingEvent event) {

    }
}
