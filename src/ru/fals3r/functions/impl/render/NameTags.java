package ru.fals3r.functions.impl.render;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import org.lwjgl.opengl.GL11;
import ru.fals3r.functions.Category;
import ru.fals3r.functions.Function;

public class NameTags extends Function
{
    public NameTags()
    {
        this.registerName("NameTags");
        this.registerCategory(Category.Render);
    }

    public void onRender3D(float partialTicks)
    {
        EntityPlayer entityplayer = null;

        for (Object object : this.mc.world.loadedEntityList)
        {
            if (object instanceof EntityPlayer)
            {
                EntityPlayer entityplayer1 = (EntityPlayer)object;

                if (entityplayer1 instanceof EntityPlayer && entityplayer1 != this.mc.player)
                {
                    this.handleEntity(entityplayer1, partialTicks);
                }
            }
        }
    }

    public void handleEntity(Entity entityIn, float partialTicks)
    {
        double d0 = 1337.0D;
        double d1 = entityIn.getDistanceSqToEntity(this.mc.getRenderManager().renderViewEntity);

        if (d1 <= d0 * d0)
        {
            boolean flag = entityIn.isSneaking();
            float f = this.mc.getRenderManager().playerViewY;
            float f1 = this.mc.getRenderManager().playerViewX;
            boolean flag1 = this.mc.getRenderManager().options.thirdPersonView == 2;
            float f2 = entityIn.height + 0.5F - (flag ? 0.25F : 0.0F);
            int i = "deadmau5".equals(entityIn.getName()) ? -10 : 0;
            double d2 = entityIn.lastTickPosX + (entityIn.posX - entityIn.lastTickPosX) * (double)partialTicks - TileEntityRendererDispatcher.staticPlayerX;
            double d3 = entityIn.lastTickPosY + (entityIn.posY - entityIn.lastTickPosY) * (double)partialTicks - TileEntityRendererDispatcher.staticPlayerY;
            double d4 = entityIn.lastTickPosZ + (entityIn.posZ - entityIn.lastTickPosZ) * (double)partialTicks - TileEntityRendererDispatcher.staticPlayerZ;
            this.renderNametag(this.mc.fontRendererObj, entityIn, entityIn.getDisplayName().getFormattedText() + " \u0412\u00a7c| " + (int)((EntityLivingBase)entityIn).getHealth() + " HP\u0412\u00a7r", (float)d2, (float)d3 + f2, (float)d4, i, f, f1, flag1, flag);
        }
    }

    public void renderNametag(FontRenderer fontRendererIn, Entity ent, String str, float x, float y, float z, int verticalShift, float viewerYaw, float viewerPitch, boolean isThirdPersonFrontal, boolean isSneaking)
    {
        GlStateManager.pushMatrix();
        GlStateManager.translate(x, y, z);
        GlStateManager.glNormal3f(0.0F, 1.0F, 0.0F);
        GlStateManager.rotate(-viewerYaw, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotate((float)(isThirdPersonFrontal ? -1 : 1) * viewerPitch, 1.0F, 0.0F, 0.0F);
        float f = this.mc.player.getDistanceToEntity(ent) / 10.0F;

        if (f < 0.6F)
        {
            f = 0.6F;
        }

        f = f / 35.0F;
        GlStateManager.scale(-f, -f, f);
        GlStateManager.disableLighting();
        GlStateManager.depthMask(false);

        if (!isSneaking)
        {
            GlStateManager.disableDepth();
        }

        GL11.glEnable(GL11.GL_BLEND);
        GlStateManager.enableBlend();
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        int i = fontRendererIn.getStringWidth(str) / 2;
        GlStateManager.disableTexture2D();
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();
        bufferbuilder.begin(7, DefaultVertexFormats.POSITION_COLOR);
        bufferbuilder.pos((double)(-i - 1), (double)(-1 + verticalShift), 0.0D).color(0.0F, 0.0F, 0.0F, 0.25F).endVertex();
        bufferbuilder.pos((double)(-i - 1), (double)(8 + verticalShift), 0.0D).color(0.0F, 0.0F, 0.0F, 0.25F).endVertex();
        bufferbuilder.pos((double)(i + 1), (double)(8 + verticalShift), 0.0D).color(0.0F, 0.0F, 0.0F, 0.25F).endVertex();
        bufferbuilder.pos((double)(i + 1), (double)(-1 + verticalShift), 0.0D).color(0.0F, 0.0F, 0.0F, 0.25F).endVertex();
        tessellator.draw();
        GlStateManager.enableTexture2D();
        GL11.glDisable(GL11.GL_BLEND);

        if (!isSneaking)
        {
            fontRendererIn.drawString(str, -fontRendererIn.getStringWidth(str) / 2, verticalShift, 553648127);
            GlStateManager.enableDepth();
        }

        GlStateManager.depthMask(true);
        fontRendererIn.drawString(str, -fontRendererIn.getStringWidth(str) / 2, verticalShift, isSneaking ? 553648127 : -1);
        GlStateManager.enableLighting();
        GlStateManager.disableBlend();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.popMatrix();
    }

    public void nametags(EntityPlayer entity, String tag, double pX, double pY, double pZ)
    {
        FontRenderer fontrenderer = this.mc.fontRendererObj;
        pY = pY + 0.9D;
        float f = this.mc.player.getDistanceToEntity(entity) / 10.0F;

        if (f < 1.6F)
        {
            f = 1.6F;
        }

        if (entity instanceof EntityLivingBase)
        {
            if (entity instanceof EntityPlayer)
            {
                double d0 = Math.ceil((double)entity.getHealth()) / 2.0D;
                tag = tag + " \u0412\u00a7cHP: " + (int)d0;
            }

            RenderManager rendermanager = this.mc.getRenderManager();
            int i = 16776960;
            float f1 = f * 2.0F;
            f1 = f1 / 100.0F;
            GL11.glPushMatrix();
            GL11.glTranslatef((float)pX, (float)pY + 1.5F, (float)pZ);
            GL11.glNormal3f(0.0F, 1.0F, 0.0F);
            GL11.glRotatef(-rendermanager.playerViewY, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(rendermanager.playerViewX, 1.0F, 0.0F, 0.0F);
            GL11.glScalef(-f1, -f1, f1);
            GL11.glDisable(GL11.GL_LIGHTING);
            GL11.glDisable(GL11.GL_DEPTH_TEST);
            Tessellator tessellator = Tessellator.getInstance();
            BufferBuilder bufferbuilder = tessellator.getBuffer();
            int j = this.mc.fontRendererObj.getStringWidth(tag) / 2;
            GL11.glEnable(GL11.GL_BLEND);
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
            Gui.drawRect(-j - 1, -1, j + 1, this.mc.fontRendererObj.FONT_HEIGHT, 1879048192);
            this.mc.fontRendererObj.drawString(tag, -j, 0, 16777215);
            GL11.glDisable(GL11.GL_BLEND);
            GL11.glEnable(GL11.GL_LIGHTING);
            GL11.glEnable(GL11.GL_DEPTH_TEST);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            GL11.glPopMatrix();
        }
    }
}
