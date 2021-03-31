package ru.fals3r.functions.impl.movement;

import ru.fals3r.SkidForce;
import ru.fals3r.functions.Category;
import ru.fals3r.functions.Function;
import ru.fals3r.helpers.MovementHelper;

public class HClip extends Function
{
    public HClip()
    {
        this.registerName("HClip");
        this.registerCategory(Category.Movement);
        SkidForce.settingsManager.addDouble("Value", "HClipValue", 0.0D, 10.0D, 2.0D, this);
        SkidForce.settingsManager.addBoolean("Improve", "HClipImprove", false, this);
    }

    public void onEnable()
    {
        double d0 = SkidForce.settingsManager.getSettingByName("HClipValue").getValDouble();
        double d1 = this.mc.player.posX - Math.sin((double)MovementHelper.getDirection()) * d0;
        double d2 = this.mc.player.posZ + Math.cos((double)MovementHelper.getDirection()) * d0;

        if (SkidForce.settingsManager.getSettingByName("HClipImprove").getValBoolean())
        {
            this.mc.player.setPositionAndUpdate(d1, this.mc.player.posY, d2);
        }
        else
        {
            this.mc.player.setPosition(d1, this.mc.player.posY, d2);
        }

        this.toggle(false);
    }
}
