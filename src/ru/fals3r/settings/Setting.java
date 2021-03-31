package ru.fals3r.settings;

import java.util.ArrayList;
import ru.fals3r.functions.Function;

public class Setting
{
    private String display;
    private String name;
    private Function parent;
    private String mode;
    private String pMode;
    private String sval;
    private ArrayList<String> options;
    private boolean bval;
    private double dval;
    private double min;
    private double max;
    private boolean onlyint = false;
    public boolean extended;
    private boolean extraSetting;
    private String currentMode;
    private Setting mainSetting;

    public Setting(String iDisplay, String iname, Function iparent, String isval, ArrayList<String> ioptions)
    {
        this.display = iDisplay;
        this.name = iname;
        this.parent = iparent;
        this.sval = isval;
        this.options = ioptions;
        this.mode = "Combo";
        this.extraSetting = false;
    }

    public Setting(String iDisplay, String iname, Function iparent, boolean ibval)
    {
        this.display = iDisplay;
        this.name = iname;
        this.parent = iparent;
        this.bval = ibval;
        this.mode = "Check";
        this.extraSetting = false;
    }

    public Setting(String iDisplay, String iname, Function iparent, double idval, double imin, double imax, boolean ionlyint)
    {
        this.display = iDisplay;
        this.name = iname;
        this.pMode = "";
        this.parent = iparent;
        this.dval = idval;
        this.min = imin;
        this.max = imax;
        this.onlyint = ionlyint;
        this.mode = "Slider";
        this.extraSetting = false;
    }

    public Setting(String iDisplay, String iname, Function iparent, String isval, ArrayList<String> ioptions, Setting mainSetting, String currentMode)
    {
        this.display = iDisplay;
        this.name = iname;
        this.parent = iparent;
        this.sval = isval;
        this.options = ioptions;
        this.mode = "Combo";
        this.extraSetting = true;
        this.currentMode = currentMode;
        this.mainSetting = mainSetting;
    }

    public Setting(String iDisplay, String iname, Function iparent, boolean ibval, Setting mainSetting, String currentMode)
    {
        this.display = iDisplay;
        this.name = iname;
        this.parent = iparent;
        this.bval = ibval;
        this.mode = "Check";
        this.extraSetting = true;
        this.currentMode = currentMode;
        this.mainSetting = mainSetting;
    }

    public Setting(String iDisplay, String iname, Function iparent, double idval, double imin, double imax, boolean ionlyint, Setting mainSetting, String currentMode)
    {
        this.display = iDisplay;
        this.name = iname;
        this.pMode = "";
        this.parent = iparent;
        this.dval = idval;
        this.min = imin;
        this.max = imax;
        this.onlyint = ionlyint;
        this.mode = "Slider";
        this.extraSetting = true;
        this.currentMode = currentMode;
        this.mainSetting = mainSetting;
    }

    public String getSimpleName()
    {
        return this.display;
    }

    public String getName()
    {
        return this.name;
    }

    public Function getParentMod()
    {
        return this.parent;
    }

    public String getValString()
    {
        return this.sval;
    }

    public void setValString(String in)
    {
        this.sval = in;
    }

    public ArrayList<String> getOptions()
    {
        return this.options;
    }

    public boolean getValBoolean()
    {
        return this.bval;
    }

    public void setValBoolean(boolean in)
    {
        this.bval = in;
    }

    public double getValDouble()
    {
        if (this.onlyint)
        {
            this.dval = (double)((int)this.dval);
        }

        return this.dval;
    }

    public void setValDouble(double in)
    {
        this.dval = in;
    }

    public double getMin()
    {
        return this.min;
    }

    public double getMax()
    {
        return this.max;
    }

    public String getMode()
    {
        return this.mode;
    }

    public boolean isCombo()
    {
        return this.mode.equalsIgnoreCase("Combo");
    }

    public boolean isColorCombo()
    {
        return this.mode.equalsIgnoreCase("ColorCombo");
    }

    public boolean isCheck()
    {
        return this.mode.equalsIgnoreCase("Check");
    }

    public boolean isSlider()
    {
        return this.mode.equalsIgnoreCase("Slider");
    }

    public String getpMode()
    {
        return this.pMode;
    }

    public boolean onlyInt()
    {
        return this.onlyint;
    }

    public boolean isExtra()
    {
        return this.extraSetting;
    }

    public Setting getMainSetting()
    {
        return this.mainSetting;
    }

    public boolean shouldRender()
    {
        if (!this.extraSetting)
        {
            return true;
        }
        else
        {
            if (this.mainSetting.isCombo())
            {
                if (this.mainSetting.getValString().equalsIgnoreCase(this.currentMode))
                {
                    return true;
                }
            }
            else if (this.mainSetting.isCheck() && this.mainSetting.getValBoolean())
            {
                return true;
            }

            return false;
        }
    }
}
