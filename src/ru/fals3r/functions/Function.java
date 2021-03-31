package ru.fals3r.functions;

import net.minecraft.client.Minecraft;
import net.minecraft.util.math.MathHelper;

public class Function
{
    private String name;
    private String mode = "";
    private int bind;
    private boolean toggled;
    private Category category;
    private long lastEnableTime;
    private long lastDisableTime;
    protected Minecraft mc = Minecraft.getMinecraft();

    public Function()
    {
        this.registerSettings();
    }

    public void registerSettings()
    {
    }

    public void registerName(String str)
    {
        this.name = str;
    }

    public void registerMode(String str)
    {
        this.mode = str;
    }

    public void registerBind(int integer)
    {
        this.bind = integer;
    }

    public void registerCategory(Category cat)
    {
        this.category = cat;
    }

    public void toggle()
    {
        if (this.toggled)
        {
            long i = Minecraft.getSystemTime() - this.lastEnableTime;
            this.lastDisableTime = Minecraft.getSystemTime() - (i < 300L ? 300L - i : 0L);
            this.toggled = false;
            this.onDisable();
        }
        else
        {
            long j = Minecraft.getSystemTime() - this.lastDisableTime;
            this.lastEnableTime = Minecraft.getSystemTime() - (j < 300L ? 300L - j : 0L);
            this.toggled = true;
            this.onEnable();
        }
    }

    public void toggle(boolean bool)
    {
        if (bool != this.toggled)
        {
            this.toggle();
        }
    }

    public String getName()
    {
        return this.name;
    }

    public String getMode()
    {
        return this.mode;
    }

    public int getBind()
    {
        return this.bind;
    }

    public Category getCategory()
    {
        return this.category;
    }

    public boolean getToggled()
    {
        return this.toggled;
    }

    public double offset()
    {
        return (double)Math.abs(MathHelper.clamp((float)(Minecraft.getSystemTime() - (this.toggled ? this.lastEnableTime : this.lastDisableTime)), -300.0F, 300.0F) / 300.0F);
    }

    public void onEnable()
    {
    }

    public void onDisable()
    {
    }

    public void onUpdate()
    {
    }

    public void onRender3D(float partialTicks)
    {
    }

    public void onRender2D(float partialTicks)
    {
    }
}
