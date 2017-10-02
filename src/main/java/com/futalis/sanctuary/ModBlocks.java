package com.futalis.sanctuary;

import com.futalis.sanctuary.blocks.BlockSanctuaryBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModBlocks {

    @GameRegistry.ObjectHolder("sanctuary:sanctuaryblock")
    public static BlockSanctuaryBlock blockSanctuaryBlock;

    @SideOnly(Side.CLIENT)
    public static void initModels() {
    }

    @SideOnly(Side.CLIENT)
    public static void initItemModels() {

    }
}
