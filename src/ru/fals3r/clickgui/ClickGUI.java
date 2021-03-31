package ru.fals3r.clickgui;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import org.lwjgl.opengl.GL11;
import ru.fals3r.SkidForce;
import ru.fals3r.clickgui.elements.Element;
import ru.fals3r.clickgui.elements.ModuleButton;
import ru.fals3r.clickgui.elements.menu.ElementSlider;
import ru.fals3r.functions.Category;
import ru.fals3r.functions.Function;
import ru.fals3r.settings.SettingsManager;

public class ClickGUI extends GuiScreen
{
    public static ArrayList<Panel> panels;
    public static ArrayList<Panel> rpanels;
    private ModuleButton mb = null;
    public SettingsManager setmgr = SkidForce.settingsManager;

    public ClickGUI()
    {
        panels = new ArrayList<Panel>();
        double d0 = 105.0D;
        double d1 = 25.0D;
        double d2 = 10.0D;
        double d3 = 10.0D;
        double d4 = d1 + 3.0D;

        for (final Category category : Category.values())
        {
            String s = Character.toUpperCase(category.name().toLowerCase().charAt(0)) + category.name().toLowerCase().substring(1);
            panels.add(new Panel(s, d2, d3, d0, d1, true, this)
            {
                public void setup()
                {
                    for (Function function : SkidForce.functionManager.functions())
                    {
                        if (function.getCategory().equals(category))
                        {
                            this.Elements.add(new ModuleButton(function, this));
                        }
                    }
                }
            });
            d2 += d0 + 5.0D;
        }

        rpanels = new ArrayList<Panel>();

        for (Panel panel : panels)
        {
            rpanels.add(panel);
        }

        Collections.reverse(rpanels);
    }

    /**
     * Called from the main game loop to update the screen.
     */
    public void updateScreen()
    {
        for (Panel panel : panels)
        {
            for (ModuleButton modulebutton : panel.Elements)
            {
                for (Element element : modulebutton.menuelements)
                {
                    element.tick();

                    if (!panel.extended)
                    {
                        element.anim = 0.0F;
                        element.anim2 = 0.0F;
                    }

                    if (!modulebutton.extended)
                    {
                        element.anim = 0.0F;
                        element.anim2 = 0.0F;
                    }
                }
            }
        }
    }

    /**
     * Draws the screen and all the components in it.
     */
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        Gui.drawRect(0, 0, (new ScaledResolution(this.mc)).getScaledWidth(), (new ScaledResolution(this.mc)).getScaledHeight(), 1342177280);

        for (Panel panel : panels)
        {
            panel.drawScreen(mouseX, mouseY, partialTicks);
        }

        this.mb = null;
        label126:

        for (Panel panel1 : panels)
        {
            if (panel1 != null && panel1.visible && panel1.extended && panel1.Elements != null && panel1.Elements.size() > 0)
            {
                Iterator iterator = panel1.Elements.iterator();
                ModuleButton modulebutton;

                while (true)
                {
                    if (!iterator.hasNext())
                    {
                        continue label126;
                    }

                    modulebutton = (ModuleButton)iterator.next();

                    if (modulebutton.listening)
                    {
                        break;
                    }
                }

                this.mb = modulebutton;
                break;
            }
        }

        for (Panel panel2 : panels)
        {
            if (panel2.extended && panel2.visible && panel2.Elements != null)
            {
                for (ModuleButton modulebutton1 : panel2.Elements)
                {
                    if (modulebutton1.extended && modulebutton1.menuelements != null && !modulebutton1.menuelements.isEmpty())
                    {
                        double d0 = 0.0D;
                        Color color = new Color(-13350562);
                        int i = (new Color(color.getRed(), color.getGreen(), color.getBlue(), 170)).getRGB();

                        for (Element element : modulebutton1.menuelements)
                        {
                            element.offset = d0;
                            element.update();
                            element.drawScreen(mouseX, mouseY, partialTicks);
                            d0 += element.height;
                        }
                    }
                }
            }
        }

        if (this.mb != null)
        {
            ScaledResolution scaledresolution = new ScaledResolution(this.mc);
            drawRect(0, 0, this.width, this.height, -872415232);
            GL11.glPushMatrix();
            Minecraft.getMinecraft().fontRendererObj.drawStringWithShadow("Bind Manager", (float)(scaledresolution.getScaledWidth() / 2), (float)(scaledresolution.getScaledHeight() / 2 - 30), -15558688);
            Minecraft.getMinecraft().fontRendererObj.drawStringWithShadow("Press any key...", (float)(scaledresolution.getScaledWidth() / 2), (float)(scaledresolution.getScaledHeight() / 2 - 10), -1);
            Minecraft.getMinecraft().fontRendererObj.drawStringWithShadow("'Escape' - unbound", (float)(scaledresolution.getScaledWidth() / 2), (float)(scaledresolution.getScaledHeight() / 2), -1);
            GL11.glPopMatrix();
        }

        for (Panel panel3 : rpanels)
        {
            if (panel3.extended && panel3.visible && panel3.Elements != null)
            {
                for (ModuleButton modulebutton2 : panel3.Elements)
                {
                    ;
                }
            }
        }

        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    /**
     * Called when the mouse is clicked. Args : mouseX, mouseY, clickedButton
     */
    public void mouseClicked(int mouseX, int mouseY, int mouseButton)
    {
        if (this.mb == null)
        {
            for (Panel panel : rpanels)
            {
                if (panel.extended && panel.visible && panel.Elements != null)
                {
                    label68:

                    for (ModuleButton modulebutton : panel.Elements)
                    {
                        if (modulebutton.extended)
                        {
                            Iterator iterator = modulebutton.menuelements.iterator();

                            while (true)
                            {
                                if (!iterator.hasNext())
                                {
                                    continue label68;
                                }

                                Element element = (Element)iterator.next();

                                if (element.mouseClicked(mouseX, mouseY, mouseButton))
                                {
                                    break;
                                }
                            }

                            return;
                        }
                    }
                }
            }

            for (Panel panel1 : rpanels)
            {
                if (panel1.mouseClicked(mouseX, mouseY, mouseButton))
                {
                    return;
                }
            }

            try
            {
                super.mouseClicked(mouseX, mouseY, mouseButton);
            }
            catch (IOException ioexception)
            {
                ioexception.printStackTrace();
            }
        }
    }

    /**
     * Called when a mouse button is released.
     */
    public void mouseReleased(int mouseX, int mouseY, int state)
    {
        if (this.mb == null)
        {
            for (Panel panel : rpanels)
            {
                if (panel.extended && panel.visible && panel.Elements != null)
                {
                    for (ModuleButton modulebutton : panel.Elements)
                    {
                        if (modulebutton.extended)
                        {
                            for (Element element : modulebutton.menuelements)
                            {
                                element.mouseReleased(mouseX, mouseY, state);
                            }
                        }
                    }
                }
            }

            for (Panel panel1 : rpanels)
            {
                panel1.mouseReleased(mouseX, mouseY, state);
            }

            super.mouseReleased(mouseX, mouseY, state);
        }
    }

    /**
     * Fired when a key is typed (except F11 which toggles full screen). This is the equivalent of
     * KeyListener.keyTyped(KeyEvent e). Args : character (character on the key), keyCode (lwjgl Keyboard key code)
     */
    protected void keyTyped(char typedChar, int keyCode)
    {
        for (Panel panel : rpanels)
        {
            if (panel != null && panel.visible && panel.extended && panel.Elements != null && panel.Elements.size() > 0)
            {
                for (ModuleButton modulebutton : panel.Elements)
                {
                    try
                    {
                        if (modulebutton.keyTyped(typedChar, keyCode))
                        {
                            return;
                        }
                    }
                    catch (IOException ioexception1)
                    {
                        ioexception1.printStackTrace();
                    }
                }
            }
        }

        try
        {
            super.keyTyped(typedChar, keyCode);
        }
        catch (IOException ioexception)
        {
            ioexception.printStackTrace();
        }
    }

    /**
     * Adds the buttons (and other controls) to the screen in question. Called when the GUI is displayed and when the
     * window resizes, the buttonList is cleared beforehand.
     */
    public void initGui()
    {
        for (Panel panel : panels)
        {
            for (ModuleButton modulebutton : panel.Elements)
            {
                for (Element element : modulebutton.menuelements)
                {
                    element.anim = 0.0F;
                    element.anim2 = 0.0F;
                }
            }
        }
    }

    /**
     * Called when the screen is unloaded. Used to disable keyboard repeat events
     */
    public void onGuiClosed()
    {
        for (Panel panel : rpanels)
        {
            if (panel.extended && panel.visible && panel.Elements != null)
            {
                for (ModuleButton modulebutton : panel.Elements)
                {
                    if (modulebutton.extended)
                    {
                        for (Element element : modulebutton.menuelements)
                        {
                            if (element instanceof ElementSlider)
                            {
                                ((ElementSlider)element).dragging = false;
                            }
                        }
                    }
                }
            }
        }
    }

    public void closeAllSettings()
    {
        for (Panel panel : rpanels)
        {
            if (panel != null && panel.visible && panel.extended && panel.Elements != null && panel.Elements.size() > 0)
            {
                for (ModuleButton modulebutton : panel.Elements)
                {
                    ;
                }
            }
        }
    }
}
