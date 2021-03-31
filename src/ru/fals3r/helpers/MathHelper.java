package ru.fals3r.helpers;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;
import net.minecraft.client.Minecraft;
import net.minecraft.util.Timer;
import org.lwjgl.input.Keyboard;

public class MathHelper
{
    private static Minecraft mc = Minecraft.getMinecraft();
    public static Timer timer;

    public MathHelper()
    {
        try
        {
            Field field = getField(Minecraft.class, "B:profiler:profiler");
            Field field1 = getField(Field.class, "modifiers");
            field1.setInt(field, field.getModifiers() & -17);
            field.set(Minecraft.getMinecraft(), this);
            Field field2 = getField(Minecraft.class, "Y:timer:timer");
            timer = (Timer)field2.get(Minecraft.getMinecraft());
        }
        catch (Exception var4)
        {
            ;
        }
    }

    public static Field getField(Class cl, String name)
    {
        String[] astring;

        for (String s : astring = name.split(":"))
        {
            try
            {
                Field field = cl.getDeclaredField(s);
                field.setAccessible(true);
                return field;
            }
            catch (Exception var7)
            {
                ;
            }
        }

        return null;
    }

    public static int getRandomInt(int min, int max)
    {
        Random random = new Random();
        int i = random.nextInt(max + 1 - min) + min;
        return i;
    }

    public static float randomOffset(float f, float offset)
    {
        return f + ((new Random()).nextFloat() - 0.5F) * offset;
    }

    public static double randomOffset(double f, double offset)
    {
        return f + ((new Random()).nextDouble() - 0.5D) * offset;
    }

    public static double getNormalDouble(double d, int numberAfterZopyataya)
    {
        return (new BigDecimal(d)).setScale(numberAfterZopyataya, RoundingMode.HALF_EVEN).doubleValue();
    }

    public static double getNormalDouble(double d)
    {
        return (new BigDecimal(d)).setScale(2, RoundingMode.HALF_EVEN).doubleValue();
    }

    public static String getKeyFromInt(int i)
    {
        return Keyboard.getKeyName(i);
    }

    public static int getIntFromKey(String i)
    {
        return Keyboard.getKeyIndex(i);
    }

    public static double interpolatePartialTicks(double from, double to)
    {
        return from + (to - from) * (double)timer.field_194147_b;
    }
}
