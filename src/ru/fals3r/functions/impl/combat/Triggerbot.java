package ru.fals3r.functions.impl.combat;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumHand;
import ru.fals3r.SkidForce;
import ru.fals3r.functions.Category;
import ru.fals3r.functions.Function;
import ru.fals3r.helpers.TimeHelper;

public class Triggerbot extends Function
{
    public TimeHelper timer = new TimeHelper();

    public Triggerbot()
    {
        this.registerName("Triggerbot");
        this.registerCategory(Category.Combat);
        SkidForce.settingsManager.addDouble("Delay", "TriggerbotDelay", 0.0D, 1500.0D, 200.0D, this);
        SkidForce.settingsManager.addBoolean("1.9 Clicks", "Triggerbot19Clicks", true, this);
        SkidForce.settingsManager.addBoolean("Players only", "TriggerbotPlayersOnly", true, this);
    }

    public void onUpdate()
    {
        if (this.mc.player.getCooledAttackStrength(0.0F) >= 1.0F || !SkidForce.settingsManager.getSettingByName("Triggerbot19Clicks").getValBoolean())
        {
            if (SkidForce.settingsManager.getSettingByName("Triggerbot19Clicks").getValBoolean() || this.timer.hasTimePassedM((long)SkidForce.settingsManager.getSettingByName("TriggerbotDelay").getValDouble()))
            {
                if (this.mc.objectMouseOver.entityHit != null)
                {
                    if (SkidForce.settingsManager.getSettingByName("TriggerbotPlayersOnly").getValBoolean() && !(this.mc.objectMouseOver.entityHit instanceof EntityPlayer))
                    {
                        return;
                    }

                    this.mc.playerController.attackEntity(this.mc.player, this.mc.objectMouseOver.entityHit);
                    this.mc.player.swingArm(EnumHand.MAIN_HAND);
                }
            }
        }
    }
}
