package ru.fals3r.functions.impl.movement;

import java.util.ArrayList;
import ru.fals3r.SkidForce;
import ru.fals3r.functions.Category;
import ru.fals3r.functions.Function;
import ru.fals3r.helpers.MovementHelper;

public class Speed extends Function
{
    public int tick;

    public Speed()
    {
        this.registerName("Speed");
        this.registerCategory(Category.Movement);
        this.registerBind(47);
        ArrayList<String> arraylist = new ArrayList<String>();
        arraylist.add("Legit");
        arraylist.add("DoodleJump");
        arraylist.add("Motion");
        SkidForce.settingsManager.addMode("Mode", "SpeedMode", "Motion", arraylist, this);
        SkidForce.settingsManager.addDouble("Motion Value", "SpeedValue", 0.0D, 5.0D, 1.0D, this);
    }

    public void onUpdate()
    {
        if (SkidForce.settingsManager.getSettingByName("SpeedMode").getValString().equalsIgnoreCase("Motion"))
        {
            this.registerMode("\u0412\u00a77 Motion");
            MovementHelper.setSpeed(SkidForce.settingsManager.getSettingByName("SpeedValue").getValDouble());
        }
        else if (SkidForce.settingsManager.getSettingByName("SpeedMode").getValString().equalsIgnoreCase("Legit"))
        {
            this.registerMode("\u0412\u00a77 Legit");
            ++this.tick;

            if (this.tick >= 15)
            {
                this.tick = 0;
                this.mc.player.motionZ *= 1.45D;
            }
        }
        else if (SkidForce.settingsManager.getSettingByName("SpeedMode").getValString().equalsIgnoreCase("DoodleJump") && this.mc.player.onGround)
        {
            this.registerMode("\u0412\u00a77 DoodleJump");
            this.mc.player.jump();
            this.mc.player.jump();
            this.mc.player.jump();
        }
    }
}
