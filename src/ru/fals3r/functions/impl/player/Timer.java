package ru.fals3r.functions.impl.player;

import java.lang.reflect.Field;
import net.minecraft.client.Minecraft;
import ru.fals3r.SkidForce;
import ru.fals3r.functions.Category;
import ru.fals3r.functions.Function;
import ru.fals3r.helpers.MathHelper;

public class Timer extends Function
{
    private net.minecraft.util.Timer timer;
    Field tickLength;

    public Timer()
    {
        this.registerName("Timer");
        this.registerCategory(Category.Player);
        SkidForce.settingsManager.addDouble("Value", "TimerValue", 0.1D, 5.0D, 1.0D, this);
    }

    public void onUpdate()
    {
        try
        {
            this.tickLength.set(this.timer, Float.valueOf((float)(50.0D / SkidForce.settingsManager.getSettingByName("TimerValue").getValDouble())));
        }
        catch (IllegalArgumentException illegalargumentexception)
        {
            illegalargumentexception.printStackTrace();
        }
        catch (IllegalAccessException illegalaccessexception)
        {
            illegalaccessexception.printStackTrace();
        }
    }

    public void onEnable()
    {
        Field field = MathHelper.getField(Minecraft.class, "Y:timer:timer");

        try
        {
            this.timer = (net.minecraft.util.Timer)field.get(Minecraft.getMinecraft());
        }
        catch (IllegalArgumentException illegalargumentexception)
        {
            illegalargumentexception.printStackTrace();
        }
        catch (IllegalAccessException illegalaccessexception)
        {
            illegalaccessexception.printStackTrace();
        }

        this.tickLength = MathHelper.getField(net.minecraft.util.Timer.class, "e:tickLength");
    }

    public void onDisable()
    {
        try
        {
            this.tickLength.set(this.timer, Float.valueOf(50.0F));
        }
        catch (IllegalArgumentException illegalargumentexception)
        {
            illegalargumentexception.printStackTrace();
        }
        catch (IllegalAccessException illegalaccessexception)
        {
            illegalaccessexception.printStackTrace();
        }
    }
}
