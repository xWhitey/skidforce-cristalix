package ru.fals3r.functions.impl.player;

import java.lang.reflect.Field;
import net.minecraft.client.multiplayer.PlayerControllerMP;
import ru.fals3r.functions.Category;
import ru.fals3r.functions.Function;
import ru.fals3r.helpers.MathHelper;

public class SpeedMine extends Function
{
    private Field curBlockDamageMP;
    private Field blockHitDelay;

    public SpeedMine()
    {
        this.registerName("SpeedMine");
        this.registerCategory(Category.Player);
    }

    public void onUpdate()
    {
        try
        {
            this.curBlockDamageMP.set(this.mc.playerController, Float.valueOf(1.0F));
            this.blockHitDelay.set(this.mc.playerController, Integer.valueOf(0));
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
        this.curBlockDamageMP = MathHelper.getField(PlayerControllerMP.class, "e:curBlockDamageMP");
        this.blockHitDelay = MathHelper.getField(PlayerControllerMP.class, "g:blockHitDelay");
    }

    public void onDisable()
    {
    }
}
