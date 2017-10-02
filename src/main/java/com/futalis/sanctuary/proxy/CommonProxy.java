package com.futalis.sanctuary.proxy;

import com.futalis.sanctuary.*;

import com.futalis.sanctuary.blocks.BlockSanctuaryBlock;
import com.futalis.sanctuary.tiles.TileSanctuaryBlock;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;

import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;


import java.io.File;

@Mod.EventBusSubscriber
public class CommonProxy {

    // Config instance
    public static Configuration config;

    public void preInit(FMLPreInitializationEvent e) {
        File directory = e.getModConfigurationDirectory();
        config = new Configuration(new File(directory.getPath(), "sanctuary.cfg"));
        //Config.readConfig();


        // Initialization of blocks and items typically goes here:
        ModEntities.init();


        //MainCompatHandler.registerWaila();


    }

    public void init(FMLInitializationEvent e) {

    }

    public void postInit(FMLPostInitializationEvent e) {
        if (config.hasChanged()) {
            config.save();
        }
    }

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        event.getRegistry().register(new BlockSanctuaryBlock());
        GameRegistry.registerTileEntity(TileSanctuaryBlock.class, Sanctuary.MODID + "_tilesanctuaryblock");
         }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        //event.getRegistry().register(new FirstItem());

        event.getRegistry().register(new ItemBlock(ModBlocks.blockSanctuaryBlock).setRegistryName(ModBlocks.blockSanctuaryBlock.getRegistryName()));
    }




}
