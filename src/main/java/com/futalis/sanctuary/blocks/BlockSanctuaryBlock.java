package com.futalis.sanctuary.blocks;

import com.futalis.sanctuary.tiles.TileSanctuaryBlock;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockSanctuaryBlock extends BlockBase implements ITileEntityProvider {


    public BlockSanctuaryBlock() {
        super(Material.GLASS, "sanctuaryblock");
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new TileSanctuaryBlock(this, meta);
    }


    public boolean hasTileEntity(int metadata){
        return true;
    }
}
