package ru.fals3r.functions;

import java.util.ArrayList;
import java.util.List;
import ru.fals3r.functions.impl.combat.KillAura;
import ru.fals3r.functions.impl.combat.Triggerbot;
import ru.fals3r.functions.impl.movement.Fly;
import ru.fals3r.functions.impl.movement.HClip;
import ru.fals3r.functions.impl.movement.JetPack;
import ru.fals3r.functions.impl.movement.Speed;
import ru.fals3r.functions.impl.movement.Sprint;
import ru.fals3r.functions.impl.movement.Step;
import ru.fals3r.functions.impl.movement.VClip;
import ru.fals3r.functions.impl.player.SpeedMine;
import ru.fals3r.functions.impl.player.Timer;
import ru.fals3r.functions.impl.render.BlockESP;
import ru.fals3r.functions.impl.render.ClickGui;
import ru.fals3r.functions.impl.render.NameTags;
import ru.fals3r.functions.impl.render.PlayerESP;
import ru.fals3r.functions.impl.world.Crasher;
import ru.fals3r.functions.impl.world.SurvivalNuker;

public class FunctionManager
{
    private ArrayList<Function> functionList = new ArrayList<Function>();

    public FunctionManager()
    {
        this.functionList.add(new KillAura());
        this.functionList.add(new ClickGui());
        this.functionList.add(new Fly());
        this.functionList.add(new Speed());
        this.functionList.add(new Sprint());
        this.functionList.add(new VClip());
        this.functionList.add(new HClip());
        this.functionList.add(new PlayerESP());
        this.functionList.add(new BlockESP());
        this.functionList.add(new NameTags());
        this.functionList.add(new SpeedMine());
        this.functionList.add(new Timer());
        this.functionList.add(new SurvivalNuker());
        this.functionList.add(new Crasher());
        this.functionList.add(new Triggerbot());
        this.functionList.add(new Step());
        this.functionList.add(new JetPack());
    }

    public List<Function> functions()
    {
        return this.functionList;
    }

    public int functionSize()
    {
        return this.functionList.size();
    }

    public Function function(Class clazz)
    {
        for (Function function : this.functions())
        {
            if (function.getClass() == clazz)
            {
                return function;
            }
        }

        return null;
    }
}
