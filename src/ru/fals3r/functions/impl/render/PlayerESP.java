package ru.fals3r.functions.impl.render;

import java.util.ArrayList;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import ru.fals3r.SkidForce;
import ru.fals3r.functions.Category;
import ru.fals3r.functions.Function;
import ru.fals3r.helpers.RenderHelper;

public class PlayerESP extends Function
{
    public PlayerESP()
    {
        this.registerName("PlayerESP");
        this.registerCategory(Category.Render);
        this.registerBind(21);
        ArrayList<String> arraylist = new ArrayList<String>();
        arraylist.add("3D Box");
        arraylist.add("Outline");
        SkidForce.settingsManager.addMode("Mode", "PlayerESPMode", "Outline", arraylist, this);
    }

    public void onUpdate()
    {
        if (SkidForce.settingsManager.getSettingByName("PlayerESPMode").getValString().equalsIgnoreCase("Outline"))
        {
            for (Entity entity : this.mc.world.loadedEntityList)
            {
                if (entity instanceof EntityLivingBase && entity instanceof EntityPlayer)
                {
                    entity.setGlowing(true);
                }
            }
        }
    }

    public void onRender3D(float partialTicks)
    {
        if (SkidForce.settingsManager.getSettingByName("PlayerESPMode").getValString().equalsIgnoreCase("3D Box"))
        {
            for (Entity entity : this.mc.world.loadedEntityList)
            {
                if (entity instanceof EntityLivingBase && entity instanceof EntityPlayer)
                {
                    RenderHelper.entityESPBox(entity, 0.2F, 0.6F, 1.0F, 1.0F);
                }
            }
        }
    }

    public void onDisable()
    {
        if (SkidForce.settingsManager.getSettingByName("PlayerESPMode").getValString().equalsIgnoreCase("Outline"))
        {
            for (Entity entity : this.mc.world.loadedEntityList)
            {
                if (entity instanceof EntityLivingBase && entity instanceof EntityPlayer)
                {
                    entity.setGlowing(false);
                }
            }
        }
    }
}
