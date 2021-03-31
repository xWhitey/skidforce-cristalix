package ru.fals3r.functions.impl.movement;

import java.util.ArrayList;
import ru.fals3r.SkidForce;
import ru.fals3r.functions.Category;
import ru.fals3r.functions.Function;
import ru.fals3r.helpers.MovementHelper;

public class Fly extends Function
{
    public Fly()
    {
        this.registerName("Fly");
        this.registerCategory(Category.Movement);
        this.registerBind(33);
        ArrayList<String> arraylist = new ArrayList<String>();
        arraylist.add("Creative");
        arraylist.add("Motion");
        SkidForce.settingsManager.addMode("Mode", "FlyMode", "Motion", arraylist, this);
        SkidForce.settingsManager.addDouble("Motion Value", "FlyMotionValue", 0.0D, 5.0D, 1.0D, this);
        SkidForce.settingsManager.addDouble("Motion-Y Value", "FlyMotionYValue", 0.0D, 2.0D, 0.5D, this);
    }

    public void onUpdate()
    {
        if (SkidForce.settingsManager.getSettingByName("FlyMode").getValString().equalsIgnoreCase("Motion"))
        {
            this.registerMode("\u0412\u00a77 Motion");
            this.mc.player.motionY = -0.1D;
            double d0 = SkidForce.settingsManager.getSettingByName("FlyMotionYValue").getValDouble();

            if (this.mc.gameSettings.keyBindJump.isKeyDown())
            {
                this.mc.player.motionY = d0;
            }

            if (this.mc.gameSettings.keyBindSneak.isKeyDown())
            {
                this.mc.player.motionY = -d0;
            }

            MovementHelper.setSpeed(SkidForce.settingsManager.getSettingByName("FlyMotionValue").getValDouble());
        }
        else if (SkidForce.settingsManager.getSettingByName("FlyMode").getValString().equalsIgnoreCase("Creative"))
        {
            this.registerMode("\u0412\u00a77 Creative");
        }
    }

    public void onEnable()
    {
        super.onEnable();

        if (SkidForce.settingsManager.getSettingByName("FlyMode").getValString().equalsIgnoreCase("Creative"))
        {
            this.mc.player.capabilities.isFlying = true;
        }
    }

    public void onDisable()
    {
        super.onDisable();

        if (SkidForce.settingsManager.getSettingByName("FlyMode").getValString().equalsIgnoreCase("Creative"))
        {
            this.mc.player.capabilities.isFlying = false;
        }
    }
}
