package ru.fals3r.clickgui.elements.menu;

import java.awt.Color;
import net.minecraft.client.Minecraft;
import org.lwjgl.opengl.GL11;
import ru.fals3r.clickgui.elements.Element;
import ru.fals3r.clickgui.elements.ModuleButton;
import ru.fals3r.helpers.MathHelper;
import ru.fals3r.helpers.RenderHelper;
import ru.fals3r.settings.Setting;

public class ElementSlider extends Element
{
    public boolean dragging;

    public ElementSlider(ModuleButton iparent, Setting iset)
    {
        this.parent = iparent;
        this.set = iset;
        this.dragging = false;
        super.setup();
    }

    public void tick()
    {
        double d0 = (this.set.getValDouble() - this.set.getMin()) / (this.set.getMax() - this.set.getMin());

        if (d0 < 0.0D)
        {
            d0 = 0.0D;
        }

        this.anim = (float)((double)this.anim + (d0 - (double)this.anim) / 4.0D);
        double d1 = this.set.onlyInt() ? 1000.0D : 100.0D;
        this.anim2 = (float)((double)this.anim2 + ((double)Math.round(this.set.getValDouble() * d1) / d1 - (double)this.anim2) / 2.0D);
    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        double d0 = this.set.onlyInt() ? (double)((int)Math.round((double)this.anim2 * 1000.0D)) / 1000.0D : (double)Math.round((double)this.anim2 * 100.0D) / 100.0D;
        String s = "";

        if (this.set.onlyInt())
        {
            s = "" + (int)d0;
        }
        else
        {
            s = "" + MathHelper.getNormalDouble(d0, 1);
        }

        boolean flag = this.isSliderHovered(mouseX, mouseY) || this.dragging;
        Color color = new Color(-13350562);
        int i = (new Color(color.getRed(), color.getGreen(), color.getBlue(), flag ? 250 : 200)).getRGB();
        int j = (new Color(color.getRed(), color.getGreen(), color.getBlue(), flag ? 255 : 230)).getRGB();
        double d3 = (this.set.getValDouble() - this.set.getMin()) / (this.set.getMax() - this.set.getMin());
        RenderHelper.drawRect(this.x, this.y, this.x + this.width, this.y + this.height, -2039584);
        RenderHelper.drawGradientSideways(this.x, this.y, this.x + this.width - 100.0D, this.y + this.height, -5460820, 14737632);
        Minecraft.getMinecraft().fontRendererObj.drawString(this.setstrg + " (" + s + ")", (int)(this.x + 8.0D), (int)(this.y + 3.0D), -12105913);
        RenderHelper.drawRect(this.x + 8.0D, this.y + 11.0D, this.x + 8.0D + (this.width - 16.0D), this.y + 12.5D, -15724528);
        RenderHelper.drawRect(this.x + 8.0D, this.y + 11.0D, (double)((float)(this.x + 11.0D + (double)this.anim * (this.width - 21.0D))), this.y + 12.5D, -15558688);
        GL11.glPushMatrix();
        RenderHelper.drawBorderedCircle((float)(this.x + 11.0D + (double)this.anim * (this.width - 21.0D)), (float)(this.y + 12.0D), 3.0F, 1, -15558688, -15558688);
        GL11.glPopMatrix();

        if (this.dragging)
        {
            double d1 = this.set.getMax() - this.set.getMin();
            double d2 = this.set.getMin() + net.minecraft.util.math.MathHelper.clamp(((double)mouseX - (this.x + 8.0D)) / (this.width - 16.0D), 0.0D, 1.0D) * d1;
            this.set.setValDouble(d2);
        }
    }

    public boolean mouseClicked(int mouseX, int mouseY, int mouseButton)
    {
        if (mouseButton == 0 && this.isSliderHovered(mouseX, mouseY))
        {
            this.dragging = true;
            return true;
        }
        else
        {
            return super.mouseClicked(mouseX, mouseY, mouseButton);
        }
    }

    public void mouseReleased(int mouseX, int mouseY, int state)
    {
        this.dragging = false;
    }

    public boolean isSliderHovered(int mouseX, int mouseY)
    {
        return (double)mouseX >= this.x + 6.0D && (double)mouseX <= this.x + 8.0D + (this.width - 13.0D) && (double)mouseY >= this.y + 9.0D && (double)mouseY <= this.y + 15.0D;
    }
}
