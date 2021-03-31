package ru.fals3r.functions.impl.movement;

import ru.fals3r.SkidForce;
import ru.fals3r.functions.Category;
import ru.fals3r.functions.Function;

public class Step extends Function
{
    public Step()
    {
        this.registerName("Step");
        this.registerCategory(Category.Movement);
        SkidForce.settingsManager.addDouble("Height", "StepHeight", 0.0D, 10.0D, 1.0D, this);
    }

    public void onUpdate()
    {
        this.mc.player.stepHeight = (float)SkidForce.settingsManager.getSettingByName("StepHeight").getValDouble();
    }

    public void onDisable()
    {
        super.onDisable();
        this.mc.player.stepHeight = 0.6F;
    }
}
