package ru.fals3r.helpers;

public class TimeHelper
{
    private long lastMS = -1L;

    public void updateLastMS()
    {
        this.lastMS = System.currentTimeMillis();
    }

    public void updateLastMS(long plusMS)
    {
        this.lastMS += plusMS;
    }

    public boolean hasTimePassedM(long MS)
    {
        return System.currentTimeMillis() >= this.lastMS + MS;
    }

    public boolean hasTimePassedS(float speed)
    {
        return (float)System.currentTimeMillis() >= (float)this.lastMS + speed * 10.0F;
    }
}
