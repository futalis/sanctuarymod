package com.futalis.sanctuary.tiles;

import com.futalis.sanctuary.Sanctuary;
import com.futalis.sanctuary.util.LogHelper;
import com.google.common.collect.Lists;

import net.minecraft.block.Block;

import net.minecraft.nbt.NBTTagCompound;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.List;

public class TileSanctuaryBlock extends TileEntity {
    private Block block;
    private int stage;


    public TileSanctuaryBlock(Block block, int meta) {
        this.block = block;
        this.stage = meta;
    }

    public TileSanctuaryBlock() {
    }

    @Override
    public BlockPos getPos() {
        return super.getPos();
    }

    private void registerInhibitor() {
        if (!this.getWorld().isRemote) {
            String stage = "";
            List<SanctuaryBlockReference> RefList = Sanctuary.Stage1;
            SanctuaryBlockReference ref = new SanctuaryBlockReference(this.getPos().getX(), this.getPos().getY(), this.getPos().getZ(), this.world.provider.getDimension());
            LogHelper.info(stage + " Inhibitor registering at " + ref.toString() + "; List Length: " + (RefList.size() + 1));
            if (RefList.contains(ref)) {
                return;
            } else {
                RefList.add(ref);
                Sanctuary.Stage1 = RefList;
                /*if(!getWorld().isRemote) {
                    //TileSanctuaryRenderer.renderPerimeter(ref);
                }*/
                System.out.println(stage + " Inhibitor registering at " + ref.toString() + "; List Length: " + (RefList.size()));
            }

        }
    }

    private void unregisterInhibitor() {
        if (!this.getWorld().isRemote) {
            String stage = "";
            List<SanctuaryBlockReference> RefList = Sanctuary.Stage1;
            SanctuaryBlockReference ref = new SanctuaryBlockReference(this.getPos().getX(), this.getPos().getY(), this.getPos().getZ(), this.world.provider.getDimension());
            LogHelper.info(stage + " Inhibitor unregistering at " + ref.toString() + "; List Length: " + (RefList.size() - 1));
            if (!RefList.contains(ref)) {
                return;
            } else {
                RefList.remove(ref);
                Sanctuary.Stage1 = RefList;
                System.out.println(stage + " Inhibitor unregistering at " + ref.toString() + "; List Length: " + (RefList.size()));
            }
        }
    }

    @Override
    public void validate() {
        super.validate();
        this.registerInhibitor();
    }

    @Override
    public void invalidate() {
        super.invalidate();
        this.unregisterInhibitor();
    }


    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt) {

        nbt.setInteger("stage", this.stage);
        return super.writeToNBT(nbt);
    }

    @Override
    public AxisAlignedBB getRenderBoundingBox() {
        net.minecraft.util.math.AxisAlignedBB bb = INFINITE_EXTENT_AABB;
        return bb;
    }
}
