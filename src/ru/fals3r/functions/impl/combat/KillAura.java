package ru.fals3r.functions.impl.combat;

import com.google.common.base.Predicate;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.ToDoubleFunction;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.Vec3d;
import ru.fals3r.SkidForce;
import ru.fals3r.functions.Category;
import ru.fals3r.functions.Function;
import ru.fals3r.helpers.TimeHelper;

public class KillAura extends Function
{
    public EntityLivingBase target;
    public TimeHelper timer = new TimeHelper();

    public KillAura()
    {
        this.registerName("KillAura");
        this.registerCategory(Category.Combat);
        this.registerBind(19);
        SkidForce.settingsManager.addDouble("Range", "KillauraRange", 0.0D, 10.0D, 6.0D, this);
        SkidForce.settingsManager.addDouble("Delay", "KillauraDelay", 0.0D, 1500.0D, 200.0D, this);
        SkidForce.settingsManager.addBoolean("1.9 Clicks", "Killaura19Clicks", true, this);
        SkidForce.settingsManager.addBoolean("Players only", "KillauraPlayersOnly", false, this);
    }

    public void onUpdate()
    {
        if (this.mc.player.getCooledAttackStrength(0.0F) >= 1.0F || !SkidForce.settingsManager.getSettingByName("Killaura19Clicks").getValBoolean())
        {
            if (this.target == null || !this.validEntity(this.target) || (double)this.mc.player.getDistanceToEntity(this.target) > SkidForce.settingsManager.getSettingByName("KillauraRange").getValDouble())
            {
                this.target = this.getTarget();
            }

            if (this.target != null && this.validEntity(this.target) && (double)this.mc.player.getDistanceToEntity(this.target) <= SkidForce.settingsManager.getSettingByName("KillauraRange").getValDouble() && this.timer.hasTimePassedM((long)SkidForce.settingsManager.getSettingByName("KillauraDelay").getValDouble()))
            {
                this.mc.playerController.attackEntity(this.mc.player, this.target);
                this.mc.player.swingArm(EnumHand.MAIN_HAND);
                this.timer.updateLastMS();
            }
        }
    }

    public EntityLivingBase getTarget()
    {
        List<EntityLivingBase> list = this.mc.world.getEntities(EntityLivingBase.class, this::validEntity);
        return list.isEmpty() ? null : (EntityLivingBase)Collections.min(list, Comparator.comparingDouble(EntityLivingBase::getHealth));
    }

    public boolean validEntity(EntityLivingBase ent)
    {
        if (SkidForce.settingsManager.getSettingByName("KillauraPlayersOnly").getValBoolean() && !(ent instanceof EntityPlayer))
        {
            return false;
        }
        else
        {
            return ent != this.mc.player && ent.getEntityId() != -69 && ent.deathTime == 0 && ent.canBeAttackedWithItem() && this.getDistFromEyesToEnt(ent) <= SkidForce.settingsManager.getSettingByName("KillauraRange").getValDouble() && (!(ent instanceof EntityTameable) || ((EntityTameable)ent).getOwner() == null || !((EntityTameable)ent).getOwner().getName().equals(this.mc.player.getName())) && !(ent instanceof EntityArmorStand);
        }
    }

    public double getDistFromEyesToEnt(Entity ent)
    {
        Vec3d vec3d = this.mc.player.getPositionEyes(1.0F).addVector(this.mc.player.motionX, this.mc.player.motionY, this.mc.player.motionZ);
        return vec3d.distanceTo(ent.getPositionVector().addVector(ent.motionX, ent.motionY, ent.motionZ));
    }

    private double getDistanceToEntityCenter(Entity ent)
    {
        return getAngleFromVectors(this.mc.player.getLook(1.0F), ent.getPositionEyes(1.0F).subtract(this.mc.player.getPositionEyes(1.0F)));
    }

    public static double getAngleFromVectors(Vec3d vec1, Vec3d vec2)
    {
        return (1.0D - vec1.dotProduct(vec2) / (vec1.lengthVector() * vec2.lengthVector())) * 180.0D;
    }
}
