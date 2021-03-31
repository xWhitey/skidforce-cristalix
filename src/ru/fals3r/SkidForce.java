package ru.fals3r;

import ru.fals3r.functions.FunctionManager;
import ru.fals3r.hooks.ProfillerHook;
import ru.fals3r.settings.SettingsManager;

public class SkidForce
{
    public static boolean isInitialized = false;
    public static FunctionManager functionManager;
    public static SettingsManager settingsManager;

    public SkidForce()
    {
        isInitialized = true;
        settingsManager = new SettingsManager();
        functionManager = new FunctionManager();
        new ProfillerHook();
    }
}
