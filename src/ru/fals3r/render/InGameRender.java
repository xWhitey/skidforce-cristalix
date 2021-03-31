package ru.fals3r.render;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import org.lwjgl.opengl.GL11;
import ru.fals3r.SkidForce;
import ru.fals3r.functions.Function;
import ru.fals3r.helpers.RenderHelper;
import ru.fals3r.hooks.ProfillerHook;

public class InGameRender
{
    private static Minecraft mc = ProfillerHook.mc;

    public static void doRender()
    {
        new ScaledResolution(mc);
        GL11.glPushMatrix();
        GL11.glScaled(2.0D, 2.0D, 2.0D);
        GL11.glTranslated(3.0D, 3.0D, 0.0D);
        mc.fontRendererObj.drawString("SkidForce", 0, 0, -16746560);
        GL11.glTranslated(-0.5D, -0.5D, 0.0D);
        mc.fontRendererObj.drawString("SkidForce", 0, 0, -14506000);
        GL11.glScaled(1.0D, 1.0D, 1.0D);
        GL11.glPopMatrix();
        mc.fontRendererObj.drawString("vk.com/skidforce", 5, 22, -6250336);
        GL11.glTranslated(-0.5D, -0.5D, 0.0D);
        mc.fontRendererObj.drawString("vk.com/skidforce", 5, 22, -2039584);
        drawArrayList();
    }

    private static void drawArrayList()
    {
        ScaledResolution scaledresolution = new ScaledResolution(mc);
        int i = scaledresolution.getScaledWidth();
        int j = scaledresolution.getScaledHeight();
        float f = -0.9F;

        for (Function function : sortedFunctions())
        {
            float f1 = 12.0F * f;
            float f2 = 30.0F;
            String s = function.getName();
            String s1 = function.getMode();
            int k = mc.fontRendererObj.getStringWidth(s + s1);
            double d0 = function.offset();

            if (!function.getToggled())
            {
                d0 = 1.0D - d0;
            }

            if (d0 != 0.0D)
            {
                float f3 = (float)Math.round((double)k * Math.sin(Math.toRadians(d0 * 90.0D)));
                int l = 6;
                int i1 = 0;
                RenderHelper.drawRect((double)(i - 1), (double)(f1 + 12.0F), (double)((float)(i - l) - f3 + 1.0F), (double)(f1 + f2 - 8.4F), Integer.MIN_VALUE);
                RenderHelper.drawRect((double)((float)(i - l) - f3 + 2.0F), (double)(f1 + 12.0F), (double)((float)(i - l) - f3), (double)(f1 + f2 - 8.4F), -855638017);
                mc.fontRendererObj.drawStringWithShadow(s + s1, (float)i - f3 + 4.0F - (float)l - (float)i1, 13.5F + f1, Color.WHITE.getRGB());
                f += 0.8F;
            }
        }
    }

    private static List<Function> sortedFunctions()
    {
        ArrayList<Function> arraylist = new ArrayList<Function>();

        for (Function function : SkidForce.functionManager.functions())
        {
            arraylist.add(function);
        }

        Comparator<Function> comparator = new Comparator<Function>()
        {
            public int compare(Function b1, Function b2)
            {
                String s = b1.getName();
                String s1 = b2.getName();
                s = String.format("%s%s", b1.getName(), b1.getMode());
                s1 = String.format("%s%s", b2.getName(), b2.getMode());
                return Integer.compare(InGameRender.mc.fontRendererObj.getStringWidth(s1), InGameRender.mc.fontRendererObj.getStringWidth(s));
            }
        };
        Collections.sort(arraylist, comparator);
        return arraylist;
    }
}
