package com.futalis.sanctuary.render;

import com.futalis.sanctuary.tiles.TileSanctuaryBlock;

import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;

import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

import java.util.List;


@SideOnly(Side.CLIENT)
public class TileSanctuaryRenderer extends TileEntitySpecialRenderer<TileSanctuaryBlock> {

    //Original Function
    @Override
    public void render(TileSanctuaryBlock te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {


        GlStateManager.pushAttrib();
        GlStateManager.pushMatrix();


        GlStateManager.translate(-x, -y, -z);


        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferBuilder = tessellator.getBuffer();
        bufferBuilder.begin(GL11.GL_POLYGON, DefaultVertexFormats.POSITION);
        bufferBuilder.setTranslation(x, y, z);
        bufferBuilder.pos(x+.5, y + .5, z+.5).endVertex();
        bufferBuilder.pos(x+.5, y + .5, z+0).endVertex();
        bufferBuilder.pos(x + .5, y + 30.5, z + .5).endVertex();
        bufferBuilder.pos(x + .5, y + 30.5, z + 0).endVertex();
        tessellator.draw();
        //System.out.println("TESR @" +te.getPos().getX()+", "+ te.getPos().getY()+", " + te.getPos().getZ());

        GL11.glPopAttrib();
        GL11.glPopMatrix();
    }

    @Override
    public boolean isGlobalRenderer(TileSanctuaryBlock te) {
        return true;
    }
}