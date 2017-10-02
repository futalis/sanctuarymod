package com.futalis.sanctuary;

import com.futalis.sanctuary.blocks.BlockSanctuaryBlock;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModBlocks {

    @GameRegistry.ObjectHolder("sanctuary:sanctuaryblock")
    public static BlockSanctuaryBlock blockSanctuaryBlock;

    @SideOnly(Side.CLIENT)
    public static void initModels() {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(blockSanctuaryBlock), 0,
                new ModelResourceLocation(blockSanctuaryBlock.getRegistryName(), "inventory"));
    }

    @SideOnly(Side.CLIENT)
    public static void initItemModels() {

    }
}
