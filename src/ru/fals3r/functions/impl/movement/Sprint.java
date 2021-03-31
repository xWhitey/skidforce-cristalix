package ru.fals3r.functions.impl.movement;

import ru.fals3r.functions.Category;
import ru.fals3r.functions.Function;
import ru.fals3r.helpers.MovementHelper;

public class Sprint extends Function
{
    public Sprint()
    {
        this.registerName("Sprint");
        this.registerCategory(Category.Movement);
        this.registerBind(38);
    }

    public void onUpdate()
    {
        if (MovementHelper.isMoving(this.mc.player) && !this.mc.player.isSneaking())
        {
            this.mc.player.setSprinting(true);
        }
    }
}
