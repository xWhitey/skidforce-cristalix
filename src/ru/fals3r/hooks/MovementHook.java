package ru.fals3r.hooks;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.util.MovementInputFromOptions;
import ru.fals3r.SkidForce;
import ru.fals3r.functions.Function;

public class MovementHook extends MovementInputFromOptions
{
	public static MovementHook INSTANCE = new MovementHook(Minecraft.getMinecraft().gameSettings);
	
    public MovementHook(GameSettings gameSettingsIn)
    {
        super(gameSettingsIn);
    }

    public void updatePlayerMoveState()
    {
        super.updatePlayerMoveState();

        for (Function function : SkidForce.functionManager.functions())
        {
            if (function.getToggled())
            {
                function.onUpdate();
            }
        }
    }
}
