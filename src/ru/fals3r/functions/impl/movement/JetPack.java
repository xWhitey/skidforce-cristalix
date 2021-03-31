package ru.fals3r.functions.impl.movement;

import org.lwjgl.input.Keyboard;
import ru.fals3r.functions.Category;
import ru.fals3r.functions.Function;

public class JetPack extends Function
{
    public JetPack()
    {
        this.registerName("JetPack");
        this.registerCategory(Category.Movement);
    }

    public void onUpdate()
    {
        if (Keyboard.isKeyDown(57))
        {
            this.mc.player.jump();
        }
    }
}
