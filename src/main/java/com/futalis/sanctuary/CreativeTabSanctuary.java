package com.futalis.sanctuary;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import static net.minecraft.init.Items.EMERALD;

public class CreativeTabSanctuary extends CreativeTabs {

    private final ItemStack emerald;

    public CreativeTabSanctuary() {
        super(Sanctuary.MODID);
        emerald = new ItemStack(Items.EMERALD);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public ItemStack getTabIconItem() {
        return emerald;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void displayAllRelevantItems(final NonNullList<ItemStack> items) {
        items.add(emerald.copy());
        super.displayAllRelevantItems(items);
    }
}
