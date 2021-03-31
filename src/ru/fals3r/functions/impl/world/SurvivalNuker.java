package ru.fals3r.functions.impl.world;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.BlockGravel;
import net.minecraft.block.BlockIce;
import net.minecraft.block.BlockLog;
import net.minecraft.block.BlockNetherBrick;
import net.minecraft.block.BlockOre;
import net.minecraft.block.BlockPackedIce;
import net.minecraft.block.BlockQuartz;
import net.minecraft.block.BlockSand;
import net.minecraft.block.BlockStone;
import net.minecraft.block.BlockWeb;
import net.minecraft.init.Blocks;
import net.minecraft.network.play.client.CPacketPlayerDigging;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import ru.fals3r.SkidForce;
import ru.fals3r.functions.Category;
import ru.fals3r.functions.Function;
import ru.fals3r.helpers.TimeHelper;

public class SurvivalNuker extends Function
{
    public int tick;
    public TimeHelper timer = new TimeHelper();

    public SurvivalNuker()
    {
        this.registerName("SurvivalNuker");
        this.registerCategory(Category.World);
        SkidForce.settingsManager.addInt("Range", "SurvivalNukerRange", 1, 20, 5, this);
        SkidForce.settingsManager.addInt("Tick Delay", "SurvivalNukerTickDelay", 0, 10, 2, this);
        SkidForce.settingsManager.addInt("Timer Delay", "SurvivalNukerTimerDelay", 0, 300, 100, this);
        SkidForce.settingsManager.addBoolean("Use timer delay", "SurvivalNukerUseTimerDelay", false, this);
        SkidForce.settingsManager.addBoolean("Swing", "SurvivalNukerSwing", true, this);
        SkidForce.settingsManager.addBoolean("Save Floor", "SurvivalNukerSaveFloor", true, this);
        SkidForce.settingsManager.addBoolean("Dirt", "SurvivalNukerDirt", true, this);
        SkidForce.settingsManager.addBoolean("Sand", "SurvivalNukerSand", true, this);
        SkidForce.settingsManager.addBoolean("Gravel", "SurvivalNukerGravel", true, this);
        SkidForce.settingsManager.addBoolean("Ore", "SurvivalNukerOre", true, this);
        SkidForce.settingsManager.addBoolean("Stone", "SurvivalNukerStone", true, this);
        SkidForce.settingsManager.addBoolean("Coal Block", "SurvivalNukerCoalBlock", true, this);
        SkidForce.settingsManager.addBoolean("End Brick", "SurvivalNukerEndBrick", true, this);
        SkidForce.settingsManager.addBoolean("Log", "SurvivalNukerLog", true, this);
        SkidForce.settingsManager.addBoolean("Quartz", "SurvivalNukerQuartz", true, this);
        SkidForce.settingsManager.addBoolean("Ice", "SurvivalNukerIce", true, this);
        SkidForce.settingsManager.addBoolean("Packed Ice", "SurvivalNukerPackedIce", true, this);
        SkidForce.settingsManager.addBoolean("Wool", "SurvivalNukerWool", true, this);
        SkidForce.settingsManager.addBoolean("Web", "SurvivalNukerWeb", true, this);
        SkidForce.settingsManager.addBoolean("Nether Brick", "SurvivalNukerNetherBrick", true, this);
        SkidForce.settingsManager.addBoolean("Purpur Block", "SurvivalNukerPurpurBlock", true, this);
        SkidForce.settingsManager.addBoolean("Clay", "SurvivalNukerClay", true, this);
        SkidForce.settingsManager.addBoolean("Sandstone", "SurvivalNukerSandStone", true, this);
    }

    public boolean isValidBlock(Block b)
    {
        if (b instanceof BlockDirt && SkidForce.settingsManager.getSettingByName("SurvivalNukerDirt").getValBoolean())
        {
            return true;
        }
        else if (b instanceof BlockSand && SkidForce.settingsManager.getSettingByName("SurvivalNukerSand").getValBoolean())
        {
            return true;
        }
        else if (b instanceof BlockGravel && SkidForce.settingsManager.getSettingByName("SurvivalNukerGravel").getValBoolean())
        {
            return true;
        }
        else if (b instanceof BlockOre && SkidForce.settingsManager.getSettingByName("SurvivalNukerOre").getValBoolean())
        {
            return true;
        }
        else if (b instanceof BlockStone && SkidForce.settingsManager.getSettingByName("SurvivalNukerStone").getValBoolean())
        {
            return true;
        }
        else if (b == Blocks.COAL_BLOCK && SkidForce.settingsManager.getSettingByName("SurvivalNukerCoalBlock").getValBoolean())
        {
            return true;
        }
        else if (b == Blocks.END_BRICKS && SkidForce.settingsManager.getSettingByName("SurvivalNukerEndBrick").getValBoolean())
        {
            return true;
        }
        else if (b instanceof BlockLog && SkidForce.settingsManager.getSettingByName("SurvivalNukerLog").getValBoolean())
        {
            return true;
        }
        else if (b instanceof BlockQuartz && SkidForce.settingsManager.getSettingByName("SurvivalNukerQuartz").getValBoolean())
        {
            return true;
        }
        else if (b instanceof BlockIce && SkidForce.settingsManager.getSettingByName("SurvivalNukerIce").getValBoolean())
        {
            return true;
        }
        else if (b instanceof BlockPackedIce && SkidForce.settingsManager.getSettingByName("SurvivalNukerPackedIce").getValBoolean())
        {
            return true;
        }
        else if (b == Blocks.WOOL && SkidForce.settingsManager.getSettingByName("SurvivalNukerWool").getValBoolean())
        {
            return true;
        }
        else if (b instanceof BlockWeb && SkidForce.settingsManager.getSettingByName("SurvivalNukerWeb").getValBoolean())
        {
            return true;
        }
        else if (b instanceof BlockNetherBrick && SkidForce.settingsManager.getSettingByName("SurvivalNukerNetherBrick").getValBoolean())
        {
            return true;
        }
        else if (b == Blocks.PURPUR_BLOCK && SkidForce.settingsManager.getSettingByName("SurvivalNukerPurpurBlock").getValBoolean())
        {
            return true;
        }
        else if (b == Blocks.CLAY && SkidForce.settingsManager.getSettingByName("SurvivalNukerClay").getValBoolean())
        {
            return true;
        }
        else if (b == Blocks.HARDENED_CLAY && SkidForce.settingsManager.getSettingByName("SurvivalNukerClay").getValBoolean())
        {
            return true;
        }
        else if (b == Blocks.STAINED_HARDENED_CLAY && SkidForce.settingsManager.getSettingByName("SurvivalNukerClay").getValBoolean())
        {
            return true;
        }
        else if (b == Blocks.SANDSTONE && SkidForce.settingsManager.getSettingByName("SurvivalNukerSandStone").getValBoolean())
        {
            return true;
        }
        else
        {
            return b == Blocks.RED_SANDSTONE && SkidForce.settingsManager.getSettingByName("SurvivalNukerSandStone").getValBoolean();
        }
    }

    public void onUpdate()
    {
        ++this.tick;
        double d0 = SkidForce.settingsManager.getSettingByName("SurvivalNukerRange").getValDouble();
        int i = 6;
        int j = 0;

        for (int k = -i; k <= i; ++k)
        {
            for (int l = -i; l <= i; ++l)
            {
                for (int i1 = -i; i1 <= i; ++i1)
                {
                    BlockPos blockpos = new BlockPos(this.mc.player.posX + (double)k, this.mc.player.posY + (double)this.mc.player.getEyeHeight() + (double)l, this.mc.player.posZ + (double)i1);

                    if (((double)blockpos.getY() >= this.mc.player.posY || !SkidForce.settingsManager.getSettingByName("SurvivalNukerSaveFloor").getValBoolean()) && Math.sqrt(this.mc.player.getDistanceSq(blockpos)) < (double)this.mc.playerController.getBlockReachDistance())
                    {
                        int j1 = (int)SkidForce.settingsManager.getSettingByName("SurvivalNukerTickDelay").getValDouble();
                        long k1 = (long)SkidForce.settingsManager.getSettingByName("SurvivalNukerTimerDelay").getValDouble();

                        if (SkidForce.settingsManager.getSettingByName("SurvivalNukerUseTimerDelay").getValBoolean())
                        {
                            if (this.timer.hasTimePassedM(k1) && this.isValidBlock(this.mc.world.getBlockState(blockpos).getBlock()))
                            {
                                this.mc.player.connection.sendPacket(new CPacketPlayerDigging(CPacketPlayerDigging.Action.START_DESTROY_BLOCK, blockpos, EnumFacing.DOWN));
                                this.mc.player.connection.sendPacket(new CPacketPlayerDigging(CPacketPlayerDigging.Action.STOP_DESTROY_BLOCK, blockpos, EnumFacing.DOWN));
                                this.mc.player.connection.sendPacket(new CPacketPlayerDigging(CPacketPlayerDigging.Action.START_DESTROY_BLOCK, blockpos, EnumFacing.DOWN));

                                if (SkidForce.settingsManager.getSettingByName("SurvivalNukerSwing").getValBoolean())
                                {
                                    this.mc.player.swingArm(EnumHand.MAIN_HAND);
                                }

                                this.timer.updateLastMS();
                            }
                        }
                        else if (this.tick > j1 - 1 && this.isValidBlock(this.mc.world.getBlockState(blockpos).getBlock()))
                        {
                            this.mc.player.connection.sendPacket(new CPacketPlayerDigging(CPacketPlayerDigging.Action.START_DESTROY_BLOCK, blockpos, EnumFacing.DOWN));
                            this.mc.player.connection.sendPacket(new CPacketPlayerDigging(CPacketPlayerDigging.Action.STOP_DESTROY_BLOCK, blockpos, EnumFacing.DOWN));
                            this.mc.player.connection.sendPacket(new CPacketPlayerDigging(CPacketPlayerDigging.Action.START_DESTROY_BLOCK, blockpos, EnumFacing.DOWN));

                            if (SkidForce.settingsManager.getSettingByName("SurvivalNukerSwing").getValBoolean())
                            {
                                this.mc.player.swingArm(EnumHand.MAIN_HAND);
                            }

                            this.tick = 0;
                        }
                    }
                }
            }
        }
    }
}
