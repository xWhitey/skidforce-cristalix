package ru.fals3r.clickgui.elements.menu;

import java.awt.Color;
import net.minecraft.client.Minecraft;
import ru.fals3r.clickgui.elements.Element;
import ru.fals3r.clickgui.elements.ModuleButton;
import ru.fals3r.helpers.RenderHelper;
import ru.fals3r.settings.Setting;

public class ElementComboBox extends Element
{
    public ElementComboBox(ModuleButton iparent, Setting iset)
    {
        this.parent = iparent;
        this.set = iset;
        super.setup();
    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        Color color = new Color(-13350562);
        int i = (new Color(color.getRed(), color.getGreen(), color.getBlue(), 150)).getRGB();
        RenderHelper.drawRect(this.x, this.y, this.x + this.width, this.y + this.height, -2039584);
        RenderHelper.drawGradientSideways(this.x, this.y, this.x + this.width - 100.0D, this.y + this.height, -5460820, 14737632);
        RenderHelper.drawOctagon((float)this.x + 8.0F, (float)this.y + 1.0F, (float)this.width - 16.0F, 14.0F, 1.0F, 6.0F, -16746560);

        if (this.comboextended)
        {
            RenderHelper.drawRect(this.x + 12.0D, this.y + 15.0D, this.x + this.width - 12.0D, this.y + this.height, -2236963);
        }

        Minecraft.getMinecraft().fontRendererObj.drawStringWithShadow(this.setstrg, (float)((int)(this.x + this.width / 2.0D - (double)(Minecraft.getMinecraft().fontRendererObj.getStringWidth(this.setstrg) / 2))), (float)((int)(this.y + 4.0D)), -1);
        int j = color.getRGB();

        if (this.comboextended)
        {
            double d0 = this.y + 16.0D;

            for (String s : this.set.getOptions())
            {
                String s1 = s.substring(0, 1).toUpperCase() + s.substring(1, s.length());
                Minecraft.getMinecraft().fontRendererObj.drawString(s1, (int)(this.x + this.width / 2.0D - (double)(Minecraft.getMinecraft().fontRendererObj.getStringWidth(s1) / 2)), (int)(d0 + 1.0D), s.equalsIgnoreCase(this.set.getValString()) ? -16746560 : -12105913);

                if (s.equalsIgnoreCase(this.set.getValString()))
                {
                    ;
                }

                if ((double)mouseX >= this.x && (double)mouseX <= this.x + this.width && (double)mouseY >= d0 && (double)mouseY < d0 + (double)Minecraft.getMinecraft().fontRendererObj.FONT_HEIGHT + 2.0D)
                {
                    ;
                }

                d0 += (double)(Minecraft.getMinecraft().fontRendererObj.FONT_HEIGHT + 4);
            }
        }
    }

    public boolean mouseClicked(int mouseX, int mouseY, int mouseButton)
    {
        if (mouseButton == 0)
        {
            if (this.isButtonHovered(mouseX, mouseY))
            {
                this.comboextended = !this.comboextended;
                return true;
            }

            if (!this.comboextended)
            {
                return false;
            }

            double d0 = this.y + 16.0D;

            for (String s : this.set.getOptions())
            {
                if ((double)mouseX >= this.x && (double)mouseX <= this.x + this.width && (double)mouseY >= d0 && (double)mouseY <= d0 + (double)Minecraft.getMinecraft().fontRendererObj.FONT_HEIGHT + 2.0D)
                {
                    if (this.clickgui != null && this.clickgui.setmgr != null)
                    {
                        this.clickgui.setmgr.getSettingByName(this.set.getName()).setValString(s.toLowerCase());
                    }

                    return true;
                }

                d0 += (double)(Minecraft.getMinecraft().fontRendererObj.FONT_HEIGHT + 6);
            }
        }

        return super.mouseClicked(mouseX, mouseY, mouseButton);
    }

    public boolean isButtonHovered(int mouseX, int mouseY)
    {
        return (double)mouseX >= this.x && (double)mouseX <= this.x + this.width && (double)mouseY >= this.y && (double)mouseY <= this.y + 15.0D;
    }
}
