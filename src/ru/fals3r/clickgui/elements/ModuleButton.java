package ru.fals3r.clickgui.elements;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import net.minecraft.client.Minecraft;
import org.lwjgl.opengl.GL11;
import ru.fals3r.SkidForce;
import ru.fals3r.clickgui.Panel;
import ru.fals3r.clickgui.elements.menu.ElementCheckBox;
import ru.fals3r.clickgui.elements.menu.ElementComboBox;
import ru.fals3r.clickgui.elements.menu.ElementSlider;
import ru.fals3r.functions.Function;
import ru.fals3r.helpers.RenderHelper;
import ru.fals3r.settings.Setting;

public class ModuleButton
{
    public Function func;
    public ArrayList<Element> menuelements;
    public Panel parent;
    public double x;
    public double y;
    public double width;
    public double height;
    public boolean extended = false;
    public boolean listening = false;

    public ModuleButton(Function ifunc, Panel pl)
    {
        this.func = ifunc;
        this.height = (double)(Minecraft.getMinecraft().fontRendererObj.FONT_HEIGHT + 9);
        this.parent = pl;
        this.menuelements = new ArrayList<Element>();

        if (SkidForce.settingsManager.getSettingsByMod(ifunc) != null)
        {
            for (Setting setting : SkidForce.settingsManager.getSettingsByMod(ifunc))
            {
                if (setting.isCheck())
                {
                    this.menuelements.add(new ElementCheckBox(this, setting));
                }
                else if (setting.isSlider())
                {
                    this.menuelements.add(new ElementSlider(this, setting));
                }
                else if (setting.isCombo())
                {
                    this.menuelements.add(new ElementComboBox(this, setting));
                }
            }
        }
    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        Color color = new Color(-13350562);
        int i = (new Color(color.getRed(), color.getGreen(), color.getBlue(), 150)).getRGB();
        int j = -5263441;

        if (this.func.getToggled())
        {
            j = -1052689;
        }

        if (this.isHovered(mouseX, mouseY))
        {
            RenderHelper.drawRect(this.x - 2.0D, this.y, this.x + this.width + 2.0D, this.y + this.height + 1.0D, 572466736);
        }

        Minecraft.getMinecraft().fontRendererObj.drawString(this.func.getName(), (int)(this.x + 4.0D), (int)(this.y - 2.0D + this.height / 2.0D), this.func.getToggled() ? -12895429 : -6513508);

        if (SkidForce.settingsManager.getSettingsByMod(this.func) != null)
        {
            GL11.glPushMatrix();
            int k = (int)(this.x + this.width - 6.0D);
            Minecraft.getMinecraft().fontRendererObj.drawString(">", k, (int)(this.y - 2.0D + this.height / 2.0D), this.func.getToggled() ? -12895429 : -6513508);
            GL11.glPopMatrix();
        }
    }

    public boolean mouseClicked(int mouseX, int mouseY, int mouseButton)
    {
        if (!this.isHovered(mouseX, mouseY))
        {
            return false;
        }
        else
        {
            if (mouseButton == 0)
            {
                this.func.toggle();
            }
            else if (mouseButton == 1)
            {
                if (this.menuelements != null && this.menuelements.size() > 0)
                {
                    boolean flag = !this.extended;
                    this.extended = flag;
                }
            }
            else if (mouseButton == 2)
            {
                this.listening = true;
            }

            return true;
        }
    }

    public boolean keyTyped(char typedChar, int keyCode) throws IOException
    {
        if (this.listening)
        {
            if (keyCode != 1)
            {
                this.func.registerBind(keyCode);
            }
            else
            {
                this.func.registerBind(0);
            }

            this.listening = false;
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean isHovered(int mouseX, int mouseY)
    {
        return (double)mouseX >= this.x && (double)mouseX <= this.x + this.width && (double)mouseY >= this.y && (double)mouseY <= this.y + this.height;
    }
}
