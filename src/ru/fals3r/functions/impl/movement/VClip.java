package ru.fals3r.functions.impl.movement;

import ru.fals3r.SkidForce;
import ru.fals3r.functions.Category;
import ru.fals3r.functions.Function;

public class VClip extends Function
{
    public VClip()
    {
        this.registerName("VClip");
        this.registerCategory(Category.Movement);
        SkidForce.settingsManager.addDouble("Value", "VClipValue", 0.0D, 10.0D, 2.0D, this);
        SkidForce.settingsManager.addBoolean("Up", "VClipUp", false, this);
        SkidForce.settingsManager.addBoolean("Improve", "VClipImprove", false, this);
    }

    public void onEnable()
    {
        if (SkidForce.settingsManager.getSettingByName("VClipUp").getValBoolean())
        {
            if (SkidForce.settingsManager.getSettingByName("VClipImprove").getValBoolean())
            {
                this.mc.player.setPositionAndUpdate(this.mc.player.posX, this.mc.player.posY + SkidForce.settingsManager.getSettingByName("VClipValue").getValDouble(), this.mc.player.posZ);
            }
            else
            {
                this.mc.player.setPosition(this.mc.player.posX, this.mc.player.posY + SkidForce.settingsManager.getSettingByName("VClipValue").getValDouble(), this.mc.player.posZ);
            }
        }
        else if (SkidForce.settingsManager.getSettingByName("VClipImprove").getValBoolean())
        {
            this.mc.player.setPositionAndUpdate(this.mc.player.posX, this.mc.player.posY - SkidForce.settingsManager.getSettingByName("VClipValue").getValDouble(), this.mc.player.posZ);
        }
        else
        {
            this.mc.player.setPosition(this.mc.player.posX, this.mc.player.posY - SkidForce.settingsManager.getSettingByName("VClipValue").getValDouble(), this.mc.player.posZ);
        }

        this.toggle(false);
    }
}
