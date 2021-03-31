package ru.fals3r.hooks;

import java.lang.reflect.Field;
import net.minecraft.client.Minecraft;
import net.minecraft.profiler.Profiler;
import net.minecraft.util.Timer;
import org.lwjgl.input.Keyboard;
import ru.fals3r.SkidForce;
import ru.fals3r.functions.Function;
import ru.fals3r.render.InGameRender;

public class ProfillerHook extends Profiler
{
    public static Minecraft mc;
    public static Timer timer;
    private boolean[] keyStates;

    public ProfillerHook()
    {
        this.initHiddenFields();
    }

    /**
     * Start section
     */
    public void startSection(String name)
    {
        for (Function function : SkidForce.functionManager.functions())
        {
            if (this.checkKey(function.getBind()))
            {
                function.toggle();
            }
        }

        this.initOtherHooks();

        if (name == "gui")
        {
            mc.entityRenderer.setupOverlayRendering();
            InGameRender.doRender();

            for (Function function1 : SkidForce.functionManager.functions())
            {
                if (function1.getToggled())
                {
                    function1.onRender2D(timer.field_194147_b);
                }
            }
        }

        if (name == "hand")
        {
            for (Function function2 : SkidForce.functionManager.functions())
            {
                if (function2.getToggled())
                {
                    function2.onRender3D(timer.field_194147_b);
                }
            }
        }

        super.startSection(name);
    }

    public void initOtherHooks()
    {
        if (mc.player != null && !(mc.player.movementInput instanceof MovementHook))
        {
            mc.player.movementInput = new MovementHook(mc.gameSettings);
        }
    }

    public void initHiddenFields()
    {
        mc = Minecraft.getMinecraft();
        this.keyStates = new boolean[256];

        try
        {
            Field field = this.getField(Minecraft.class, "B:profiler:profiler");
            Field field1 = this.getField(Field.class, "modifiers");
            field1.setInt(field, field.getModifiers() & -17);
            field.set(Minecraft.getMinecraft(), this);
            Field field2 = this.getField(Minecraft.class, "Y:timer:timer");
            timer = (Timer)field2.get(Minecraft.getMinecraft());
        }
        catch (Exception var4)
        {
            ;
        }
    }

    public Field getField(Class cl, String name)
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
            catch (Exception var81)
            {
                ;
            }
        }

        return null;
    }

    public boolean checkKey(int i)
    {
        boolean flag;

        if (mc.currentScreen != null)
        {
            flag = false;
        }
        else if (Keyboard.isKeyDown(i) != this.keyStates[i])
        {
            boolean[] aboolean = this.keyStates;
            flag = !this.keyStates[i];
            aboolean[i] = flag;
        }
        else
        {
            flag = false;
        }

        return flag;
    }
}
