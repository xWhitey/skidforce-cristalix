package ru.fals3r.functions.impl.render;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import ru.fals3r.SkidForce;
import ru.fals3r.functions.Category;
import ru.fals3r.functions.Function;
import ru.fals3r.helpers.RenderHelper;

public class BlockESP extends Function
{
    private BlockPos nearestBlock;
    private long currentMS = 0L;
    protected long lastMS = -1L;
    private int range = 50;
    private int maxChests = 200;
    public boolean shouldInform = true;
    private ArrayList<BlockPos> matchingBlocks = new ArrayList<BlockPos>();
    private List<Integer[]> list = new ArrayList<Integer[]>();

    public BlockESP()
    {
        this.registerName("BlockESP");
        this.registerCategory(Category.Render);
        SkidForce.settingsManager.addInt("Range", "XrayRange", 0, 500, 100, this);
        SkidForce.settingsManager.addBoolean("DiamondOre", "XrayDiamondOre", false, this);
        SkidForce.settingsManager.addBoolean("EmeraldOre", "XrayEmeraldOre", false, this);
        SkidForce.settingsManager.addBoolean("GoldOre", "XrayGoldOre", false, this);
        SkidForce.settingsManager.addBoolean("IronOre", "XrayIronOre", false, this);
        SkidForce.settingsManager.addBoolean("Spawner", "XraySpawner", false, this);
        SkidForce.settingsManager.addBoolean("Chests", "XrayChests", false, this);
        SkidForce.settingsManager.addBoolean("Lava", "XrayLava", false, this);
    }

    public void onRender3D(float partialTicks)
    {
        this.maxChests = (int)SkidForce.settingsManager.getSettingByName("XrayRange").getValDouble();
        int i = 0;

        for (BlockPos blockpos : this.matchingBlocks)
        {
            if (i >= this.maxChests)
            {
                break;
            }

            ++i;

            if (this.getBlock(blockpos) == Blocks.DIAMOND_ORE && SkidForce.settingsManager.getSettingByName("XrayDiamondOre").getValBoolean())
            {
                RenderHelper.fuckerESP(blockpos, 0.2F, 0.5F, 1.0F);
            }

            if (this.getBlock(blockpos) == Blocks.EMERALD_ORE && SkidForce.settingsManager.getSettingByName("XrayEmeraldOre").getValBoolean())
            {
                RenderHelper.fuckerESP(blockpos, 0.25F, 1.0F, 0.25F);
            }

            if (this.getBlock(blockpos) == Blocks.GOLD_ORE && SkidForce.settingsManager.getSettingByName("XrayGoldOre").getValBoolean())
            {
                RenderHelper.fuckerESP(blockpos, 1.0F, 1.0F, 0.25F);
            }

            if (this.getBlock(blockpos) == Blocks.IRON_ORE && SkidForce.settingsManager.getSettingByName("XrayIronOre").getValBoolean())
            {
                RenderHelper.fuckerESP(blockpos, 0.7F, 0.7F, 0.7F);
            }

            if (this.getBlock(blockpos) == Blocks.MOB_SPAWNER && SkidForce.settingsManager.getSettingByName("XraySpawner").getValBoolean())
            {
                RenderHelper.fuckerESP(blockpos, 0.15F, 0.15F, 0.4F);
            }

            if (this.getBlock(blockpos) == Blocks.CHEST && SkidForce.settingsManager.getSettingByName("XrayChests").getValBoolean())
            {
                RenderHelper.fuckerESP(blockpos, 0.8F, 0.5F, 0.0F);
            }

            if (this.getBlock(blockpos) == Blocks.LAVA && SkidForce.settingsManager.getSettingByName("XrayLava").getValBoolean())
            {
                RenderHelper.fuckerESP(blockpos, 1.0F, 0.5F, 0.15F);
            }
        }

        if (i >= this.maxChests && this.shouldInform)
        {
            this.shouldInform = false;
        }
        else if (i < this.maxChests)
        {
            this.shouldInform = true;
        }
    }

    public final void updateMS()
    {
        this.currentMS = System.currentTimeMillis();
    }

    public final void updateLastMS()
    {
        this.lastMS = System.currentTimeMillis();
    }

    public final boolean hasTimePassedM(long MS)
    {
        return this.currentMS >= this.lastMS + MS;
    }

    public final boolean hasTimePassedS(float speed)
    {
        return (float)this.currentMS >= (float)this.lastMS + 1000.0F / speed;
    }

    public void onUpdate()
    {
        this.updateMS();

        if (this.hasTimePassedM(3000L))
        {
            this.matchingBlocks.clear();

            for (int i = this.range; i >= -this.range; --i)
            {
                for (int j = this.range; j >= -this.range; --j)
                {
                    for (int k = this.range; k >= -this.range; --k)
                    {
                        int l = (int)(this.mc.player.posX + (double)j);
                        int i1 = (int)(this.mc.player.posY + (double)i);
                        int j1 = (int)(this.mc.player.posZ + (double)k);
                        BlockPos blockpos = new BlockPos(l, i1, j1);
                        IBlockState iblockstate = this.mc.world.getBlockState(blockpos);
                        Block block = iblockstate.getBlock();
                        block.getMetaFromState(iblockstate);

                        if (this.validBlock(blockpos))
                        {
                            this.matchingBlocks.add(blockpos);
                        }
                    }
                }
            }

            this.updateLastMS();
        }
    }

    public boolean validBlock(BlockPos pos)
    {
        return this.getBlock(pos) == Blocks.DIAMOND_ORE && SkidForce.settingsManager.getSettingByName("XrayDiamondOre").getValBoolean() || this.getBlock(pos) == Blocks.EMERALD_ORE && SkidForce.settingsManager.getSettingByName("XrayEmeraldOre").getValBoolean() || this.getBlock(pos) == Blocks.GOLD_ORE && SkidForce.settingsManager.getSettingByName("XrayGoldOre").getValBoolean() || this.getBlock(pos) == Blocks.IRON_ORE && SkidForce.settingsManager.getSettingByName("XrayIronOre").getValBoolean() || this.getBlock(pos) == Blocks.MOB_SPAWNER && SkidForce.settingsManager.getSettingByName("XraySpawner").getValBoolean() || this.getBlock(pos) == Blocks.CHEST && SkidForce.settingsManager.getSettingByName("XrayChests").getValBoolean() || this.getBlock(pos) == Blocks.LAVA && SkidForce.settingsManager.getSettingByName("XrayLava").getValBoolean();
    }

    public BlockPos getFixedLocation()
    {
        return new BlockPos(this.mc.player.posX < 0.0D ? (int)this.mc.player.posX - 1 : (int)this.mc.player.posX, (int)this.mc.player.posY, this.mc.player.posZ < 0.0D ? (int)this.mc.player.posZ - 1 : (int)this.mc.player.posZ);
    }

    public Block getBlock(BlockPos pos)
    {
        return this.mc.world.getBlockState(pos).getBlock();
    }

    public float getPlayerBlockDistance(BlockPos pos)
    {
        Entity entity = this.mc.player;
        Vec3d vec3d = entity.getPositionVector().addVector(0.0D, (double)entity.getEyeHeight(), 0.0D);
        return MathHelper.sqrt(vec3d.squareDistanceTo((double)((float)pos.getX() + 0.5F), (double)((float)pos.getY() + 0.5F), (double)((float)pos.getZ() + 0.5F)));
    }

    private BlockPos getNearestBlock()
    {
        int i = (int)SkidForce.settingsManager.getSettingByName("XrayRange").getValDouble();

        for (int j = -i; j <= i; ++j)
        {
            for (int k = -i; k <= i; ++k)
            {
                for (int l = -i; l <= i; ++l)
                {
                    BlockPos blockpos = this.getFixedLocation().add(k, j, l);

                    if (this.validBlock(blockpos))
                    {
                        return blockpos;
                    }
                }
            }
        }

        return null;
    }
}
