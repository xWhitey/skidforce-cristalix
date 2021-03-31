package ru.fals3r.helpers;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MovementInput;

public class MovementHelper
{
    private static Minecraft mc = Minecraft.getMinecraft();

    public static void setSpeed(double speed)
    {
        if (mc.player != null)
        {
            MovementInput movementinput = mc.player.movementInput;
            float f = movementinput.field_192832_b;
            float f1 = movementinput.moveStrafe;
            float f2 = Minecraft.getMinecraft().player.rotationYaw;

            if (f == 0.0F && f1 == 0.0F)
            {
                mc.player.motionX = 0.0D;
                mc.player.motionZ = 0.0D;
            }
            else if (f != 0.0F)
            {
                if (f1 >= 1.0F)
                {
                    f2 += (float)(f > 0.0F ? -35 : 35);
                    f1 = 0.0F;
                }
                else if (f1 <= -1.0F)
                {
                    f2 += (float)(f > 0.0F ? 35 : -35);
                    f1 = 0.0F;
                }

                if (f > 0.0F)
                {
                    f = 1.0F;
                }
                else if (f < 0.0F)
                {
                    f = -1.0F;
                }
            }

            double d0 = Math.cos(Math.toRadians((double)(f2 + 90.0F)));
            double d1 = Math.sin(Math.toRadians((double)(f2 + 90.0F)));
            double d2 = (double)f * speed * d0 + (double)f1 * speed * d1;
            d2 = (double)f * speed * d1 - (double)f1 * speed * d0;
            mc.player.motionX = (double)f * speed * d0 + (double)f1 * speed * d1;
            mc.player.motionZ = (double)f * speed * d1 - (double)f1 * speed * d0;
        }
    }

    public static boolean isMoving(EntityLivingBase target)
    {
        return target.moveForward != 0.0F || target.moveStrafing != 0.0F;
    }

    public static double getMovementSpeed()
    {
        return Math.sqrt(Minecraft.getMinecraft().player.motionX * Minecraft.getMinecraft().player.motionX + Minecraft.getMinecraft().player.motionZ * Minecraft.getMinecraft().player.motionZ);
    }

    public static float getDirection()
    {
        float f = mc.player.rotationYaw;

        if (mc.player.moveForward < 0.0F)
        {
            f += 180.0F;
        }

        float f1 = 1.0F;

        if (mc.player.moveForward < 0.0F)
        {
            f1 = -0.5F;
        }
        else if (mc.player.moveForward > 0.0F)
        {
            f1 = 0.5F;
        }

        if (mc.player.moveStrafing > 0.0F)
        {
            f -= 90.0F * f1;
        }

        if (mc.player.moveStrafing < 0.0F)
        {
            f += 90.0F * f1;
        }

        f = f * 0.017453292F;
        return f;
    }

    public static float getMovementYaw()
    {
        float f = mc.player.moveForward < 0.0F ? 180.0F : 0.0F;
        float f1 = mc.player.moveStrafing > 0.0F ? -45.0F : (mc.player.moveStrafing < 0.0F ? 45.0F : 0.0F);

        if (mc.player.moveForward == 0.0F)
        {
            f1 *= 2.0F;
        }
        else if (mc.player.moveForward < 0.0F)
        {
            f1 *= -1.0F;
        }

        return mc.player.rotationYaw + f + f1;
    }

    public static void setMotionSpeed(double speed)
    {
        mc.player.motionX = -(Math.sin((double)getDirection()) * speed);
        mc.player.motionZ = Math.cos((double)getDirection()) * speed;
    }

    public static EnumFacing getMovementFacing()
    {
        return EnumFacing.getHorizontal(net.minecraft.util.math.MathHelper.floor((double)(getMovementYaw() * 4.0F / 360.0F) + 0.5D) & 3);
    }
}
