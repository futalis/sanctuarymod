package com.futalis.sanctuary.proxy;

import com.futalis.sanctuary.ModBlocks;
import com.futalis.sanctuary.ModEntities;
import com.futalis.sanctuary.ModItems;
import com.futalis.sanctuary.Sanctuary;
import com.futalis.sanctuary.render.TileSanctuaryRenderer;
import com.futalis.sanctuary.tiles.TileSanctuaryBlock;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(Side.CLIENT)
public class ClientProxy extends CommonProxy {
    @Override
    public void preInit(FMLPreInitializationEvent e) {
        super.preInit(e);

        OBJLoader.INSTANCE.addDomain(Sanctuary.MODID);

        // Typically initialization of models and such goes here:
        ModEntities.initModels();

    }

    @Override
    public void init(FMLInitializationEvent e) {
        super.init(e);

    }

    @Override
    public void postInit(FMLPostInitializationEvent e) {
        super.postInit(e);
        ModBlocks.initItemModels();
    }

    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent event) {
        ModBlocks.initModels();
        ModItems.initModels();
        ClientRegistry.bindTileEntitySpecialRenderer(TileSanctuaryBlock.class, new TileSanctuaryRenderer());
    }


}
